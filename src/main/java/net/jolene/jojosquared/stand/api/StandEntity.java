package net.jolene.jojosquared.stand.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.api.MessageListener;
import net.jolene.jojosquared.network.payload.ModNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StandEntity extends Entity {
    public boolean instanceOwns = false;
    private Stand owner;

    public float floatDistance = 0.425f;

    public float renderYaw, renderPitch;
    public float realYaw, realPitch;

    public Vec3d renderOffset;
    public double dx, dy, dz;
    public double renderX, renderY, renderZ = 0f;
    public Vec3d renderOffsetPos = new Vec3d(0., 0., 0.);

    public AnimationState manifest = new AnimationState();
    public AnimationState withdraw = new AnimationState();
    public AnimationState idle = new AnimationState();
    public AnimationState passive = new AnimationState();
    public AnimationState default_hold = new AnimationState();
    public AnimationState default_1 = new AnimationState();
    public AnimationState default_2 = new AnimationState();
    public AnimationState default_3 = new AnimationState();

    public StandEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public void setOwner(Stand owner)
    {
        if (owner == null || owner.getOwner() == null)
            return;

        this.owner = owner;
        if (this.getWorld().isClient)
        {
            if (owner.getOwner() == null)
                return;

            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null)
                return;

            this.instanceOwns = owner.getOwner().equals(client.player);
        }
        else { this.instanceOwns = true; }

        JoJoSquared.LOGGER.info("[{} (JoJoSquared)]: Set owner \"{}\"'s stand to stand with ID {} ({})", (this.getWorld().isClient ? "Client" : "Server"), owner.getOwner().getName().getLiteralString(), this.getId(), (instanceOwns ? "owned by instance" : "not owned by instance"));
    }

    public Stand getOwner() {
        return this.owner;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void readCustomData(ReadView view) {

    }

    @Override
    protected void writeCustomData(WriteView view) {

    }

    @Override
    public void tick() {
        if (this.getWorld().isClient)
        {
            this.updateAnimationStates();
        }

        // remove ourselves if we don't have an owner
        // checks if server context because it's normal for the client to not have synced yet
        if (this.owner == null && !this.getWorld().isClient)
        {
            remove(RemovalReason.DISCARDED);
            return;
        }

        if (this.owner != null)
        {
            this.owner.tick();
        }

        super.tick();
    }

    private int pose = Integer.MIN_VALUE;
    private int lastPose = pose;
    private AnimationState current;
    private int endTick = Integer.MIN_VALUE;

    public void setAnimation(int pose)
    { this.setAnimation(pose, Integer.MIN_VALUE); }
    public void setAnimation(int pose, Integer length)
    { this.setAnimation(pose, length, true); }
    public void setAnimation(int pose, Integer length, boolean networked)
    {
        if (networked)
        {
            if (this.getWorld().isClient)
            {
                ModNetworking.sendMessageC2S("base_stand_entity_c2s", this.getId(), pose, length);
            }
            else {
                ServerWorld world = (ServerWorld)this.getWorld();
                world.getPlayers().forEach((player)-> ModNetworking.sendMessageS2C(player, "base_stand_entity_s2c", this.getId(), pose, length));
            }
        }

        if (length != Integer.MIN_VALUE)
            this.endTick = this.age + length;
        this.pose = pose;
        if (this.current != null) this.current.stop();
    }

    @Environment(EnvType.CLIENT)
    private void updateAnimationStates() {
        if (this.current != null && this.endTick != Integer.MIN_VALUE)
        {
            if (this.endTick <= this.age)
            {
                current.stop();
                lastPose = pose;
                current = idle;
                current.start(this.age);
                this.endTick = Integer.MIN_VALUE;
                return;
            }
        }

        if (lastPose != pose)
        {
            switch (pose)
            {
                case Animations.MANIFEST-> current = manifest;
                case Animations.WITHDRAW-> current = withdraw;
                case Animations.PASSIVE -> current = passive;
                case Animations.DEFAULT_HOLD-> current = default_hold;
                case Animations.DEFAULT_1-> current = default_1;
                case Animations.DEFAULT_2 -> current = default_2;
                case Animations.DEFAULT_3 -> current = default_3;
                default -> current = idle;
            }

            current.start(this.age);
        }
        lastPose = pose;
    }

    public interface Animations {
        int MANIFEST = 0;
        int WITHDRAW = 1;
        int IDLE = 2;
        int PASSIVE = 3;
        int DEFAULT_HOLD = 4;
        int DEFAULT_1 = 5;
        int DEFAULT_2 = 6;
        int DEFAULT_3 = 7;
    }

    public static class BaseStandEntityNetworking {
        @MessageListener("base_stand_entity_c2s")
        public static void playAnimationC2S(@Nullable ServerPlayerEntity serverPlayer, Integer entityId, Integer animId, Integer length)
        {
            assert serverPlayer != null : "Got S2C packet for C2S!";

            if (serverPlayer.getWorld() == null)
                return;

            if (serverPlayer.getWorld().getEntityById(entityId) instanceof StandEntity standEntity)
            {
                standEntity.setAnimation(animId, length, false);
            }
        }

        @Environment(EnvType.CLIENT)
        @MessageListener("base_stand_entity_s2c")
        public static void playAnimationS2C(@Nullable ServerPlayerEntity serverPlayer, Integer entityId, Integer animId, Integer length)
        {
            assert serverPlayer == null : "Got C2S packet for S2C!";

            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world == null)
                return;

            if (client.world.getEntityById(entityId) instanceof StandEntity standEntity)
            {
                if (!standEntity.instanceOwns) // we already set the animations clientside
                    standEntity.setAnimation(animId, length, false);
            }
        }
    }
}