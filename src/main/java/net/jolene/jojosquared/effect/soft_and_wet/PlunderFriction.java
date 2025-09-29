package net.jolene.jojosquared.effect.soft_and_wet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class PlunderFriction extends StatusEffect {
    private static final String NBT_LAST_MOTION = "PlunderFrictionLastMotion";

    public PlunderFriction(StatusEffectCategory category, int color) {
        super(category, color, ParticleTypes.BUBBLE_POP);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}