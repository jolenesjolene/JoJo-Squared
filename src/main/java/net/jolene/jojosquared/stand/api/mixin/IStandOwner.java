package net.jolene.jojosquared.stand.api.mixin;

import net.jolene.jojosquared.stand.api.Stand;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IStandOwner {
    public @Nullable Stand jojosquared$getStand();
    public void jojosquared$setStand(@NotNull Stand stand);

    public static IStandOwner get(LivingEntity entity)
    {
        return (IStandOwner) entity;
    }
}