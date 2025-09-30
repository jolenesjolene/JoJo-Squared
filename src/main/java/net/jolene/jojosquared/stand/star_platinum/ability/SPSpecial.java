package net.jolene.jojosquared.stand.star_platinum.ability;

import net.jolene.jojosquared.stand.api.PressContext;
import net.jolene.jojosquared.stand.api.StandAbility;
import net.jolene.jojosquared.stand.star_platinum.StarPlatinumStand;

public class SPSpecial extends StandAbility {
    private final StarPlatinumStand parent;

    public SPSpecial(StarPlatinumStand parent)
    { this.parent = parent; }

    @Override
    public void actionPress(PressContext context, StandAbility ability) {

    }

    @Override
    public void actionRelease(PressContext context, StandAbility ability, int ticksHeldFor) {

    }
}