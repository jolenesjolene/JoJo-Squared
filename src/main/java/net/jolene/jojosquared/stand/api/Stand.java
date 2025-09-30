package net.jolene.jojosquared.stand.api;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.api.MessageListener;
import net.jolene.jojosquared.network.payload.ModNetworking;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.jolene.jojosquared.stand.api.network.StandC2SContext;
import net.jolene.jojosquared.stand.api.network.StandS2CContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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

    public Stand(LivingEntity owner, EntityType<? extends StandEntity> type)
    { this.owner = owner; this.type = type; }
    public void setAbilities(List<? extends StandAbility> abilities)
    {
        assert abilities.size() <= 4 : "Attempted to set abilities to a list bigger than 4!";
        this.abilities = abilities;
    }

    public void tick() {
        // this should literally never happen because tick is called by the entity
        if (this.entity == null)
            throw new IllegalStateException("Stand was ticked but this.entity == null!");

        if (!this.owner.getWorld().isClient) {
            this.entity.setPos(this.owner.getX(), this.owner.getY(), this.owner.getZ());

            this.entity.setPitch(this.owner.getPitch());
            this.entity.setYaw(this.owner.getYaw());
            this.entity.setBodyYaw(this.owner.getBodyYaw());
            this.entity.setHeadYaw(this.owner.getHeadYaw());
        }
    }

    public void remove() {
        isSummoned = false;
        World world = owner.getWorld();
        if (entity != null)
        {
            entity.remove(Entity.RemovalReason.DISCARDED);
            entity = null;
            JoJoSquared.LOGGER.info("[{} (JoJoSquared)]: Removed entity!", (this.owner.getWorld().isClient ? "Client" : "Server"));
        }

        if (world.isClient)
        {
            ModNetworking.sendMessageC2S("base_stand_c2s", StandC2SContext.DESUMMON_STAND);
        }
    }

    public void summon() {
        isSummoned = true;
        World world = owner.getWorld();
        if (world.isClient())
        {
            ModNetworking.sendMessageC2S("base_stand_c2s", StandC2SContext.SUMMON_STAND);
        }
        else {
            if (entity != null)
                return; // no work to be done here
            if (owner == null)
                throw new RuntimeException("Failed to summon stand! Owner was null!");

            ServerWorld sworld = (ServerWorld)world;
            entity = type.spawn(sworld, owner.getBlockPos(), SpawnReason.TRIGGERED);
            if (entity == null)
                throw new RuntimeException("Attempted to summon stand but summoned entity was null!");
            entity.setOwner(this);

            sworld.getPlayers().forEach(player->{
                ModNetworking.sendMessageS2C(player, "base_stand_s2c", owner.getId(), entity.getId(), StandS2CContext.ENTITY_SUMMONED_STAND);
            });
        }
    }

    public boolean isSummoned()
    { return this.isSummoned; }

    public void setOwner(LivingEntity owner) { this.owner = owner; }
    public @Nullable LivingEntity getOwner() { return this.owner; }

    public void setCurrentAbilityIndex(int index) { this.currentAbilityIndex = index % this.abilities.size(); }
    public void incrementAbilityIndex() { setCurrentAbilityIndex(this.currentAbilityIndex + 1); }

    public StandAbility getCurrentAbility()
    {
        return abilities.get(currentAbilityIndex);
    }

    public abstract int animationCount();
    public List<AnimationState> createAnimationStates()
    {
        int count = this.animationCount();
        List<AnimationState> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(new AnimationState());
        }
        return list;
    }

    public static class BaseStandNetworking
    {
        @MessageListener("base_stand_c2s")
        public static void c2s(@Nullable ServerPlayerEntity serverPlayer, Integer context)
        {
            assert serverPlayer != null : "Got S2C on a C2S packet?";
            JoJoSquared.LOGGER.info("[Server (JoJo Squared/Networking|Stand)]: Got packet from player \"{}\"", serverPlayer.getName().getLiteralString());

            switch (context) {
                case StandC2SContext.SUMMON_STAND -> {
                    IStandOwner standOwner = IStandOwner.get(serverPlayer);
                    Stand stand = standOwner.jojosquared$getStand();
                    if (stand == null)
                        return; // idk how this happened lmao

                    stand.summon();
                    JoJoSquared.LOGGER.info("[Server (JoJo Squared/Networking|Stand)]: Summoned stand for player \"{}\"", serverPlayer.getName().getLiteralString());
                }
                case StandC2SContext.DESUMMON_STAND -> {
                    IStandOwner standOwner = IStandOwner.get(serverPlayer);
                    Stand stand = standOwner.jojosquared$getStand();
                    if (stand == null)
                        return; // idk how this happened lmao

                    stand.remove();
                    JoJoSquared.LOGGER.info("[Server (JoJo Squared/Networking|Stand)]: De-summoned stand for player \"{}\"", serverPlayer.getName().getLiteralString());
                }
                default -> JoJoSquared.LOGGER.warn("[Server (JoJo Squared/Networking|Stand)]: Got unknown C2S packet context: {}", context);
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

            JoJoSquared.LOGGER.info("[Client (JoJo Squared/Networking|Stand)]: Got state change packet!");
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
                            ownerStand.entity = stand;
                            stand.setOwner(ownerStand);
                            JoJoSquared.LOGGER.info("[Client (JoJo Squared/Networking|Stand)]: Setup stand for entity with id {}", ownerId);
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