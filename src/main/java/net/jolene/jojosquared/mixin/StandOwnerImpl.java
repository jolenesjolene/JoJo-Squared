package net.jolene.jojosquared.mixin;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.jolene.jojosquared.stand.star_platinum.StarPlatinumStand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class StandOwnerImpl implements IStandOwner {
    @Unique private Stand stand;

    @Override @Unique
    public @Nullable Stand jojosquared$getStand() {
        return stand;
    }

    @Override @Unique
    public void jojosquared$setStand(@NotNull Stand stand) {
        if (this.stand != null)
            this.stand.withdraw();

        this.stand = stand;
        stand.setOwner((LivingEntity)(Object)this);
        stand.summon();
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void ensureHasStand(CallbackInfo ci)
    {
        if (stand != null)
            return;
        LivingEntity self = ((LivingEntity) (Object)this);
        if (self.isRemoved() || self.isDead())
            return;

        JoJoSquared.LOGGER.info("[{} (JoJoSquared/Stand|LivingEntity)]: Giving stand to entity of class {}", (self.getWorld().isClient ? "Client" : "Server"), self.getClass().getSimpleName());
        stand = new StarPlatinumStand(self);
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void removeStand(Entity.RemovalReason reason, CallbackInfo ci)
    {
        if (stand == null)
            return;

        stand.onRemove();
        stand = null;
        LivingEntity self = ((LivingEntity) (Object)this);
        JoJoSquared.LOGGER.info("[{} (JoJoSquared/Stand|LivingEntity)]: Removed stand of entity of class {}", (self.getWorld().isClient ? "Client" : "Server"), self.getClass().getSimpleName());
    }
}