package net.jolene.jojosquared.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.jolene.jojosquared.block.ModBlocks;
import net.jolene.jojosquared.client.shader.ModPostShaders;
import net.jolene.jojosquared.client.stand.model.StarPlatinumModel;
import net.jolene.jojosquared.client.stand.renderer.StarPlatinumRenderer;
import net.jolene.jojosquared.client.util.JoJoSquaredClientConfig;
import net.jolene.jojosquared.entity.ModEntities;
import net.jolene.jojosquared.entity.client.SteelBallProjectileModel;
import net.jolene.jojosquared.entity.client.SteelBallProjectileRenderer;
import net.jolene.jojosquared.entity.client.ThrowingKnifeProjectileModel;
import net.jolene.jojosquared.entity.client.ThrowingKnifeProjectileRenderer;
import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.network.impl.PacketRegistry;
import net.jolene.jojosquared.network.payload.MessageS2CPayload;
import net.jolene.jojosquared.particle.*;
import net.minecraft.client.render.BlockRenderLayer;

@Environment(EnvType.CLIENT)
public class JoJoSquaredClient implements ClientModInitializer {
    public static JoJoSquaredClientConfig CONFIG = new JoJoSquaredClientConfig();

    @Override
    public void onInitializeClient() {
        //Particle Factory
        ParticleFactoryRegistry.getInstance().register(ModParticles.MENACING, Menacing.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SPARKLE, Sparkle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.MANIFEST, Manifest.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.EQUIVALENT_EXCHANGE, EquivalentExchange.Factory::new);
        BlockRenderLayerMap.putBlock(ModBlocks.ROKAKAKA_PLANT, BlockRenderLayer.CUTOUT);
        EntityModelLayerRegistry.registerModelLayer(SteelBallProjectileModel.STEEL_BALL, SteelBallProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.STEEL_BALL, SteelBallProjectileRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ThrowingKnifeProjectileModel.THROWING_KNIFE, ThrowingKnifeProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.THROWING_KNIFE, ThrowingKnifeProjectileRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(StarPlatinumModel.layer, StarPlatinumModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.STAR_PLATINUM, StarPlatinumRenderer::new);

        ModPostShaders.registerModPostShaders();
        ModKeyBindings.registerModKeys();

        ClientPlayNetworking.registerGlobalReceiver(MessageS2CPayload.ID, (payload, context) -> {
            context.client().execute(() -> PacketRegistry.invoke(PacketRegistry.MESSAGES2C, null, payload.args()));
        });
    }
}