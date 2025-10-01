package net.jolene.jojosquared.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.block.ModBlocks;
import net.jolene.jojosquared.client.stand.model.StarPlatinumModel;
import net.jolene.jojosquared.client.stand.renderer.StarPlatinumRenderer;
import net.jolene.jojosquared.entity.ModEntities;
import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.network.impl.PacketRegistry;
import net.jolene.jojosquared.network.payload.MessageS2CPayload;
import net.jolene.jojosquared.particle.EquivalentExchange;
import net.jolene.jojosquared.particle.Menacing;
import net.jolene.jojosquared.particle.ModParticles;
import net.jolene.jojosquared.stand.api.ability.IRegionAbility;
import net.jolene.jojosquared.stand.api.ability.StandAbility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BlockRenderLayer;

import java.util.HashSet;

@Environment(EnvType.CLIENT)
public class JoJoSquaredClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Particle Factory
        ParticleFactoryRegistry.getInstance().register(ModParticles.MENACING, Menacing.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SPARKLE, EquivalentExchange.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.EQUIVALENT_EXCHANGE, EquivalentExchange.Factory::new);
        BlockRenderLayerMap.putBlock(ModBlocks.ROKAKAKA_PLANT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ROKAKAKA_PLANT_TOP, BlockRenderLayer.CUTOUT);

        EntityModelLayerRegistry.registerModelLayer(StarPlatinumModel.layer, StarPlatinumModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.STAR_PLATINUM, StarPlatinumRenderer::new);

        ModKeyBindings.registerModKeys();

        ClientPlayNetworking.registerGlobalReceiver(MessageS2CPayload.ID, (payload, context) -> {
            context.client().execute(() -> PacketRegistry.invoke(PacketRegistry.MESSAGES2C, null, payload.args()));
        });

        HudElementRegistry.attachElementBefore(VanillaHudElements.MISC_OVERLAYS, JoJoSquared.location("timestop"), ((context, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.world == null)
                return;

            HashSet<StandAbility> abilities = IRegionAbility.get(client.world).jojosquared$getAffectedBy(client.player, false);
            if (abilities == null || abilities.isEmpty())
                return;

            context.fill(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), 0x7D000000);
        }));
    }
}