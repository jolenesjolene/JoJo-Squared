package net.jolene.jojosquared.stand.star_platinum.ability;

import net.jolene.jojosquared.stand.api.ability.StandAbility;
import net.jolene.jojosquared.stand.star_platinum.StarPlatinumStand;

public class SPUtilityPrimary extends StandAbility {
    private final StarPlatinumStand parent;
    private int cooldown = 0;
    public SPUtilityPrimary(StarPlatinumStand parent)
    { this.parent = parent; }

    @Override
    public void actionPress(int context) {

    }

    @Override
    public void actionRelease(int context, int ticksHeldFor) {

    }

    @Override
    public String getTranslationKey() {
        return "ability.jojosquared.sp_utility_primary";
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }
}