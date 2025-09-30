package net.jolene.jojosquared.mixin;

import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.jolene.jojosquared.stand.star_platinum.StarPlatinumStand;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntity.class)
public class StandOwnerImpl implements IStandOwner {
    @Unique private Stand stand = new StarPlatinumStand((LivingEntity)(Object)this);

    @Override @Unique
    public @Nullable Stand jojosquared$getStand() {
        return stand;
    }

    @Override @Unique
    public void jojosquared$setStand(@NotNull Stand stand) {
        this.stand.remove();

        this.stand = stand;
        stand.setOwner((LivingEntity)(Object)this);
        stand.summon();
    }
}