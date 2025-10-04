package net.jolene.jojosquared.mixin.render;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.client.shader.ManagedPostShader;
import net.jolene.jojosquared.client.shader.ModPostShaders;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.*;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @Shadow @Final private DefaultFramebufferSet framebufferSet;
    @Shadow public abstract @Nullable Framebuffer getEntityFramebuffer();

    @Shadow private @Nullable ClientWorld world;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/FrameGraphBuilder;run(Lnet/minecraft/client/util/ObjectAllocator;Lnet/minecraft/client/render/FrameGraphBuilder$Profiler;)V", shift = At.Shift.AFTER))
    private void copyTerrainDepth(ObjectAllocator allocator, RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, Matrix4f positionMatrix, Matrix4f projectionMatrix, GpuBufferSlice fog, Vector4f fogColor, boolean shouldRenderSky, CallbackInfo ci)
    {
        ModPostShaders.bootstrap();

        MinecraftClient client = MinecraftClient.getInstance();
        GameRenderer renderer = client.gameRenderer;

        ModPostShaders.depthBuffer.copyDepthFrom(client.getFramebuffer());

        ManagedPostShader next = ModPostShaders.next();
        while (next != null)
        {
            next.setUniforms("TestShaderConfig", buildTestShaderConfig(projectionMatrix, positionMatrix));
            next.setSampler("InDepthSampler", ModPostShaders.depthBuffer.getDepthAttachmentView());

            next.render(client.getFramebuffer(), ((GameRendererMixin.Accessor)renderer).getPool());
            next = ModPostShaders.next();
        }
    }

    @Unique private Object[] buildTestShaderConfig(Matrix4f projectionMatrix, Matrix4f positionMatrix)
    {
        MinecraftClient client = MinecraftClient.getInstance();
        Camera camera = client.gameRenderer.getCamera();

        Matrix4f modelViewMat = RenderSystem.getModelViewMatrix();

        Matrix4f inverseTransform = new Matrix4f(projectionMatrix);
        inverseTransform.identity();

        inverseTransform.mul(projectionMatrix);
        inverseTransform.mul(modelViewMat);
        //inverseTransform.mul(posMat);

        inverseTransform.invert();

        // CameraPos, WorldTime, Projection Matrix, Position Matrix, Model View Matrix, Inverse Transformation Matrix
        // so 6 entries in config
        Object[] config = new Object[6];

        config[0] = camera.getPos().toVector3f();
        config[1] = (world == null) ? 0.0f : (float) world.getTimeOfDay();
        config[2] = projectionMatrix;
        config[3] = positionMatrix;
        config[4] = modelViewMat; // TODO: might have to change in the future
        config[5] = inverseTransform;

        return config;
    }
}