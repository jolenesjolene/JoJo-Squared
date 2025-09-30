package net.jolene.jojosquared.stand.api;

import net.jolene.jojosquared.JoJoSquared;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;

public class StandEntity extends Entity {
    private Stand owner;
    public StandEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public void setOwner(Stand owner)
    {
        this.owner = owner;
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
}