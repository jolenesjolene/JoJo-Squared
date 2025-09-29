package net.jolene.jojosquared.effect;
import net.jolene.jojosquared.particle.ModParticles;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class EquivalentExchange extends StatusEffect {
    public EquivalentExchange(StatusEffectCategory category, int color) {
        super(category, color, ModParticles.EQUIVALENT_EXCHANGE);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}

