package net.jolene.jojosquared.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jolene.jojosquared.JoJoSquared;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType MENACING =
            registerParticle("menacing", FabricParticleTypes.simple());
    public static final SimpleParticleType EQUIVALENT_EXCHANGE =
            registerParticle("equivalent_exchange", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(JoJoSquared.MOD_ID, name), particleType);
    }

    public static void registerModParticles() {
        JoJoSquared.LOGGER.info("Registering Particles for " + JoJoSquared.MOD_ID);
    }
}