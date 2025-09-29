package net.jolene.jojosquared.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class EquivalentExchange extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    public EquivalentExchange(ClientWorld clientWorld, double x, double y, double z,
                              SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);

        this.spriteProvider = spriteProvider;

        this.velocityMultiplier = 0.8f;
        this.maxAge = 20;

        this.scale(1.5f);

        this.setSpriteForAge(spriteProvider);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;

        this.alpha = 0.0f;

        this.velocityX = 0.0;
        this.velocityY = 0.04;
        this.velocityZ = 0.0;

        this.gravityStrength = 0.0f;
    }

    @Override
    public void tick() {
        super.tick();

        this.setSpriteForAge(this.spriteProvider);

        int fadeDuration = 5;

        if (this.age < fadeDuration) {
            this.alpha = (float) this.age / fadeDuration;
        } else if (this.age > this.maxAge - fadeDuration) {
            this.alpha = (float) (this.maxAge - this.age) / fadeDuration;
        } else {
            this.alpha = 1.0f;
        }

        this.velocityY += 0.002;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public int getBrightness(float tickDelta) {
        return 0xF000F0;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            return new EquivalentExchange(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }
}
