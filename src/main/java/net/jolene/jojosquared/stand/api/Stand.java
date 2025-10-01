package net.jolene.jojosquared.stand.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.api.MessageListener;
import net.jolene.jojosquared.network.payload.ModNetworking;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.jolene.jojosquared.stand.api.network.StandC2SContext;
import net.jolene.jojosquared.stand.api.network.StandS2CContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/// The Stand class, acting as a home for all the attached parts.
/// Extend this class to make a stand & provide functionality.
/// See {@link StandAbility} for how individual abilities work.
public abstract class Stand {
    private final EntityType<? extends StandEntity> type;
    private @Nullable StandEntity entity;

    private List<? extends StandAbility> abilities;
    private int currentAbilityIndex = 0;
    private LivingEntity owner;
    private boolean isSummoned = false;

    private boolean instanceOwns = false;

    private Vec3d renderOffset = new Vec3d(0., 0., 0.);

    public Stand(LivingEntity owner, EntityType<? extends StandEntity> type)
    {
        this.owner = owner;
        this.type = type;

        if (owner.getWorld().isClient)
        {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null)
                return; // automatically set to false, don't have to do anything here

            this.instanceOwns = owner.equals(client.player);
        }
        else { // server
            this.instanceOwns = true; // kinda?
        }
        JoJoSquared.LOGGER.info("[{} (JoJoSquared/Stand|Ctor)]: Created Stand class for entity {} ({})", (this.owner.getWorld().isClient ? "Client" : "Server"), owner.getClass().getSimpleName(), (instanceOwns ? "owned by instance" : "not owned by instance"));
    }

    public void setAbilities(List<? extends StandAbility> abilities)
    {
        assert abilities.size() <= 4 : "Attempted to set abilities to a list bigger than 4!";
        JoJoSquared.LOGGER.info("Created Abilities | Abilities Hash: {}", ((Object)abilities).toString());
        this.abilities = abilities;
    }

    private List<Pair<Integer, Runnable>> tickRuns = new ArrayList<>();
    public void tick() {
        // this should literally never happen because tick is called by the entity
        if (this.entity == null)
            throw new IllegalStateException("Stand was ticked but this.entity == null!");

        Iterator<Pair<Integer, Runnable>> iterator = tickRuns.iterator();
        while (iterator.hasNext()) {
            Pair<Integer, Runnable> run = iterator.next();
            int waiting = run.getLeft();
            if (waiting <= 0) {
                run.getRight().run();
                iterator.remove();
            } else {
                run.setLeft(waiting - 1);
            }
        }

        setPosToOwner();

        for (StandAbility ability : abilities)
        { ability.tick(); }
    }

    public void render(EntityRenderState state, MatrixStack matrices, float age, VertexConsumerProvider vertexConsumers)
    {
        for (StandAbility ability : abilities)
        {
            ability.render(state, matrices, age, vertexConsumers);
        }
    }

    public void withdraw() {
        isSummoned = false;
        World world = owner.getWorld();

        if (entity != null)
        {
            if (world.isClient)
            {
                ModNetworking.sendMessageC2S("base_stand_c2s", StandC2SContext.DESUMMON_STAND);
                this.entity.setAnimation(StandEntity.Animations.WITHDRAW, 5);
            }

            tickRuns.add(new Pair<>(4, this::quietWithdraw));
        }
    }

    public void quietWithdraw()
    {
        if (entity != null)
        {
            entity.discard();
            entity = null;
            JoJoSquared.LOGGER.info("[{} (JoJoSquared/Stand|Withdraw)]: Removed entity!", (this.owner.getWorld().isClient ? "Client" : "Server"));
        }
    }

    public void summon() {
        isSummoned = true;
        World world = owner.getWorld();

        JoJoSquared.LOGGER.info("[{} (JoJoSquared/Stand|Summon)]: Summoning! | owner: {}", (world.isClient ? "Client" : "Server"), owner.getName().getLiteralString());

        if (world.isClient())
        {
            ModNetworking.sendMessageC2S("base_stand_c2s", StandC2SContext.SUMMON_STAND);
        }
        else {
            JoJoSquared.LOGGER.info("[Server (JoJoSquared/Stand|Summon)]: Entity: {}!", entity);
            if (entity != null)
                return; // no work to be done here
            if (owner == null)
                throw new RuntimeException("Failed to summon stand! Owner was null!");

            ServerWorld sworld = (ServerWorld)world;
            this.entity = type.spawn(sworld, owner.getBlockPos(), SpawnReason.TRIGGERED);
            if (this.entity == null)
                throw new RuntimeException("Attempted to summon stand but summoned entity was null!");
            this.entity.setOwner(this);
            setPosToOwner();

            sworld.getPlayers().forEach(player->{
                ModNetworking.sendMessageS2C(player, "base_stand_s2c", owner.getId(), entity.getId(), StandS2CContext.ENTITY_SUMMONED_STAND);
            });
        }
    }

    public void quietSummon()
    {
        isSummoned = true;

        if (this.entity == null)
            return;

        World world = owner.getWorld();
        if (world.isClient)
        {
            this.entity.setAnimation(StandEntity.Animations.IDLE);
        }
    }

    /// Called when the owner of this stand gets removed.
    /// Cleans up & destroys the stand completely.
    public void onRemove()
    {
        if (entity != null)
        {
            entity.discard();
            entity = null;
        }

        tickRuns.clear();
        owner = null;
        abilities = null;
        renderOffset = null;
    }

    private int beginAtkTick = 0;
    public final void attack(int pressContext)
    {
        if (!isSummoned || this.entity  == null)
        { return; }

        World world = owner.getWorld();
        if (world.isClient())
        {
            this.beginAtkTick = this.entity.age;
            getCurrentAbility().actionPress(pressContext);
            ModNetworking.sendMessageC2S("base_stand_atk_c2s", 0, currentAbilityIndex, StandC2SContext.ATTACK, pressContext);
        }
        else {
            ServerWorld sworld = (ServerWorld)world;
            sworld.getPlayers().forEach(player->{
                ModNetworking.sendMessageS2C(player, "base_stand_atk_s2c", 0, currentAbilityIndex, StandS2CContext.ATTACK, pressContext);
            });
        }
    }

    public final void releaseAtk(int pressContext)
    {
        if (!isSummoned || this.entity  == null)
            return;

        World world = owner.getWorld();

        this.beginAtkTick = 0;
        int holdTime = this.entity.age-beginAtkTick;

        if (world.isClient())
        {
            getCurrentAbility().actionRelease(pressContext, holdTime);
            ModNetworking.sendMessageC2S("base_stand_atk_c2s", holdTime, currentAbilityIndex, StandC2SContext.RELEASE, pressContext);
        }
        else {
            ServerWorld sworld = (ServerWorld)world;
            sworld.getPlayers().forEach(player->{
                ModNetworking.sendMessageS2C(player, "base_stand_atk_s2c", holdTime, currentAbilityIndex, StandC2SContext.RELEASE, pressContext);
            });
        }
    }

    public boolean isSummoned()
    { return this.isSummoned; }

    /// Are we in a state where the stand shouldn't block inputs?
    /// @return True if it shouldn't block inputs, otherwise false.
    public boolean isBusy()
    {
        if (this.entity == null)
            return true;
        if (!isSummoned())
            return true;

        // TODO: make this more robust
        return this.entity.manifest.isRunning() || this.entity.withdraw.isRunning();
    }

    public void setOwner(LivingEntity owner) { this.owner = owner; }
    public @Nullable LivingEntity getOwner() { return this.owner; }

    public @Nullable StandEntity getEntity() { return entity; }
    @Environment(EnvType.CLIENT)
    public void setEntityById(int id)
    {
        if (id == Integer.MIN_VALUE)
            return;

        if (this.owner == null)
            return;

        if (this.owner.getWorld().getEntityById(id) instanceof StandEntity ent)
        {
            this.entity = ent;
            this.entity.setOwner(this);
        }
    }

    public void setPosToOwner() {
        if (this.entity == null || this.owner == null)
            return;
        if (!instanceOwns || !this.entity.instanceOwns)
            return;

        this.entity.setPos(this.owner.getX(), this.owner.getY(), this.owner.getZ());

        this.entity.setPitch(this.owner.getPitch());
        this.entity.setYaw(this.owner.getYaw());
        this.entity.setBodyYaw(this.owner.getBodyYaw());
        this.entity.setHeadYaw(this.owner.getHeadYaw());
    }
    public Vec3d getPos() { return (this.entity != null) ? this.entity.getPos() : null; }

    public void setCurrentAbilityIndex(int index) { this.currentAbilityIndex = index % this.abilities.size(); }
    public void incrementAbilityIndex() { setCurrentAbilityIndex(this.currentAbilityIndex + 1); }
    public StandAbility getCurrentAbility() { return abilities.get(currentAbilityIndex); }
    public void playAnimation(int pose)
    { if (this.entity != null) { this.entity.setAnimation(pose); } }

    public Vec3d getRenderOffset() { return renderOffset; }
    public void setRenderOffset(Vec3d offset) { this.renderOffset = offset; }
    public void addRenderOffset(Vec3d offset) { this.renderOffset = this.renderOffset.add(offset); }
    public void clearRenderOffset() { this.renderOffset = new Vec3d(0.,0., 0.); }

    public static class BaseStandNetworking
    {
        @MessageListener("base_stand_c2s")
        public static void c2s(@Nullable ServerPlayerEntity serverPlayer, Integer context)
        {
            assert serverPlayer != null : "Got S2C on a C2S packet?";

            switch (context) {
                case StandC2SContext.SUMMON_STAND -> {
                    IStandOwner standOwner = IStandOwner.get(serverPlayer);
                    Stand stand = standOwner.jojosquared$getStand();
                    if (stand == null)
                        return; // idk how this happened lmao

                    stand.summon();
                }
                case StandC2SContext.DESUMMON_STAND -> {
                    IStandOwner standOwner = IStandOwner.get(serverPlayer);
                    Stand stand = standOwner.jojosquared$getStand();
                    if (stand == null)
                        return; // idk how this happened lmao

                    stand.withdraw();
                }
            }
        }

        @MessageListener("base_stand_atk_c2s")
        public static void standAtkC2S(@Nullable ServerPlayerEntity serverPlayer, Integer holdLength, Integer abilityIndex, Integer context, Integer pressContext)
        {
            assert serverPlayer != null : "Got S2C on a C2S packet?";
            assert context == StandC2SContext.ATTACK || context == StandC2SContext.RELEASE : "Got a packet other than ATTACK/RELEASE for standAtkC2S!";

            IStandOwner owner = IStandOwner.get(serverPlayer);
            Stand ownerStand = owner.jojosquared$getStand();
            if (ownerStand == null)
                return; // how?? how even???

            try
            {
                switch (context)
                {
                    case StandC2SContext.ATTACK -> ownerStand.abilities.get(abilityIndex).actionPress(pressContext);
                    case StandS2CContext.RELEASE -> ownerStand.abilities.get(abilityIndex).actionRelease(pressContext, holdLength);
                    default -> JoJoSquared.LOGGER.warn("[Server (JoJo Squared/Networking|Stand)]: Got unknown C2S atk packet context: {}", context);
                }
            } catch (Exception ignored) { }
        }

        @MessageListener("base_stand_atk_c2s")
        public static void standDamageEntityC2S(@Nullable ServerPlayerEntity serverPlayer, Integer entityId, Integer damage, Integer context)
        {
            assert serverPlayer != null : "Got S2C on a C2S packet?";
            assert context == StandC2SContext.DAMAGE_ENTITY : "Got a packet other than DAMAGE_ENTITY for standDamageEntityC2S!";

            IStandOwner owner = IStandOwner.get(serverPlayer);
            Stand ownerStand = owner.jojosquared$getStand();
            if (ownerStand == null)
                return; // how?? how even???

            ServerWorld world = serverPlayer.getWorld();
            if (world == null)
                return; // probably cheating but wtv (or bad lag lol)

            if (world.getEntityById(entityId) instanceof LivingEntity entity)
            {
                entity.damage(world, world.getDamageSources().playerAttack(serverPlayer), damage);
            }
        }

        @MessageListener("base_stand_s2c")
        public static void s2c(@Nullable ServerPlayerEntity serverPlayer, Integer context)
        {
            assert serverPlayer == null : "Got C2S on a S2C packet?";
            JoJoSquared.LOGGER.info("[Client (JoJo Squared/Networking|Stand)]: Got packet from server");
        }

        @MessageListener("base_stand_s2c")
        public static void standStateChangeS2C(@Nullable ServerPlayerEntity serverPlayer, Integer ownerId, Integer standId, Integer context)
        {
            assert serverPlayer == null : "Got C2S on a S2C packet?";
            assert context == StandS2CContext.ENTITY_SUMMONED_STAND || context == StandS2CContext.ENTITY_DESUMMONED_STAND  : "Got a packet other than ENTITY_SUMMONED_STAND/ENTITY_DESUMMONED_STAND for standSummonS2C!";

            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world == null)
                return; // shouldn't even matter atp we're loading into an entirely different dimension dawg

            if (client.world.getEntityById(ownerId) instanceof LivingEntity ownerEnt)
            {
                IStandOwner owner = IStandOwner.get(ownerEnt);
                Stand ownerStand = owner.jojosquared$getStand();
                if (ownerStand == null)
                    return; // how?? how even???

                switch (context)
                {
                    case StandS2CContext.ENTITY_SUMMONED_STAND -> {
                        if (client.world.getEntityById(standId) instanceof StandEntity stand)
                        {
                            JoJoSquared.LOGGER.warn("[Client (JoJo Squared/Networking|Stand)]: ENTITY_SUMMONED_STAND for ID {}", standId);
                            ownerStand.owner = ownerEnt;
                            ownerStand.entity = stand;
                            ownerStand.entity.setAnimation(StandEntity.Animations.MANIFEST, 10);
                            stand.setOwner(ownerStand);
                        }
                    }
                    case StandS2CContext.ENTITY_DESUMMONED_STAND -> {

                    }
                    default -> JoJoSquared.LOGGER.warn("[Client (JoJo Squared/Networking|Stand)]: Got unknown S2C packet context: {}", context);
                }
            }
        }
    }
}