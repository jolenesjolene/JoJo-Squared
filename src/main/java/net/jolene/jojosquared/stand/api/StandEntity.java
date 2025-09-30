package net.jolene.jojosquared.stand.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class StandEntity extends Entity {
    private Stand owner;
    public Integer currentAnimation;
    private AnimationState currentState;
    public List<AnimationState> animations = new ArrayList<>();
    public static final int DEFAULT_ANIM_INDEX = 2;

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
        this.owner = owner;
        this.animations = owner.createAnimationStates();
        JoJoSquared.LOGGER.info("[{} (JoJoSquared)]: Set Owner", (this.getWorld().isClient ? "Client" : "Server"));
    }

    public Stand getOwner() {
        return owner;
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
    {
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
}