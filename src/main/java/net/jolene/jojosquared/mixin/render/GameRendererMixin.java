package net.jolene.jojosquared.mixin.render;

import net.jolene.jojosquared.client.shader.ModPostShaders;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.Pool;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final private MinecraftClient client;
    @Shadow @Final private Pool pool;

    @Inject(method = "onResized", at = @At("HEAD"))
    private void resizeDepthBuffer(int width, int height, CallbackInfo ci)
    { ModPostShaders.depthBuffer.resize(width, height); }

    @Mixin(GameRenderer.class)
    public interface Accessor {
        @org.spongepowered.asm.mixin.gen.Accessor("pool")
        public Pool getPool();
    }
}