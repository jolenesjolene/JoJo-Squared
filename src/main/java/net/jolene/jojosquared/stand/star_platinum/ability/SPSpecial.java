package net.jolene.jojosquared.stand.star_platinum.ability;

import net.jolene.jojosquared.stand.api.StandAbility;
import net.jolene.jojosquared.stand.star_platinum.StarPlatinumStand;

public class SPSpecial extends StandAbility {
    private final StarPlatinumStand parent;
    private int cooldown = 0;
    public SPSpecial(StarPlatinumStand parent)
    { this.parent = parent; }

    @Override
    public void actionPress(int context) {

    }

    @Override
    public void actionRelease(int context, int ticksHeldFor) {

    }

    @Override
    public String getTranslationKey() {
        return "ability.jojosquared.sp_special";
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }
}