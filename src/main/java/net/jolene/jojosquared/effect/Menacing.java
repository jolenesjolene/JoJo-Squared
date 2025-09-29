package net.jolene.jojosquared.effect;
import net.jolene.jojosquared.particle.ModParticles;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class Menacing extends StatusEffect {
    public Menacing(StatusEffectCategory category, int color) {
        super(category, color, ModParticles.MENACING);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}

