package net.jolene.jojosquared.stand.api;

public abstract class StandAbility {
    public abstract void actionPress(int pressContext);
    public abstract void actionRelease(int pressContext, int ticksHeldFor);

    public abstract String getTranslationKey();

    public abstract int getCooldown();
    public void tick() {};
}