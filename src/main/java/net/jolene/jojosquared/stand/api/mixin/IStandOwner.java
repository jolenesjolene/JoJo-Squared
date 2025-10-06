package net.jolene.jojosquared.stand.api.mixin;

import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.ability.StandAbility;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IStandOwner {
    public @Nullable Stand getStand();
    public void setStand(@NotNull Stand stand, int standId, boolean summoned);

    public boolean isAffectedByRegionAbility(Class<? extends StandAbility> abilityClass);

    public static IStandOwner get(LivingEntity entity)
    {
        return (IStandOwner) entity;
    }
}