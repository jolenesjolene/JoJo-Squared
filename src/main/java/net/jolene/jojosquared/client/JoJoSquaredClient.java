package net.jolene.jojosquared.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.jolene.jojosquared.block.ModBlocks;
import net.jolene.jojosquared.client.stand.model.StarPlatinumModel;
import net.jolene.jojosquared.client.stand.renderer.StarPlatinumRenderer;
import net.jolene.jojosquared.entity.ModEntities;
import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.particle.EquivalentExchange;
import net.jolene.jojosquared.particle.Menacing;
import net.jolene.jojosquared.particle.ModParticles;
import net.minecraft.client.render.BlockRenderLayer;

@Environment(EnvType.CLIENT)
public class JoJoSquaredClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Particle Factory
        ParticleFactoryRegistry.getInstance().register(ModParticles.MENACING, Menacing.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.EQUIVALENT_EXCHANGE, EquivalentExchange.Factory::new);
        BlockRenderLayerMap.putBlock(ModBlocks.ROKAKAKA_PLANT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ROKAKAKA_PLANT_TOP, BlockRenderLayer.CUTOUT);

        EntityModelLayerRegistry.registerModelLayer(StarPlatinumModel.layer, StarPlatinumModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.STAR_PLATINUM, StarPlatinumRenderer::new);

        ModKeyBindings.registerModKeys();
    }
}