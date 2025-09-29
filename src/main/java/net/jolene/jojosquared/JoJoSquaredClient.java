package net.jolene.jojosquared;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.jolene.jojosquared.block.ModBlocks;
import net.jolene.jojosquared.particle.EquivalentExchange;
import net.jolene.jojosquared.particle.Menacing;
import net.jolene.jojosquared.particle.ModParticles;
import net.minecraft.client.render.BlockRenderLayer;

public class JoJoSquaredClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //Particle Factory
        ParticleFactoryRegistry.getInstance().register(ModParticles.MENACING, Menacing.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.EQUIVALENT_EXCHANGE, EquivalentExchange.Factory::new);
        BlockRenderLayerMap.putBlock(ModBlocks.ROKAKAKA_PLANT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ROKAKAKA_PLANT_TOP, BlockRenderLayer.CUTOUT);
    }
}