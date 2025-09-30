package net.jolene.jojosquared.stand.api;

public abstract class StandAbility {
    public abstract void actionPress(PressContext context, StandAbility ability);
    public abstract void actionRelease(PressContext context, StandAbility ability, int ticksHeldFor);
}