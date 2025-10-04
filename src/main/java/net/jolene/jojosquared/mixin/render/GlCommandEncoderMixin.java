package net.jolene.jojosquared.mixin.render;

import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.textures.GpuTextureView;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.client.shader.IPostPassHolder;
import net.jolene.jojosquared.client.shader.ManagedPostShader;
import net.jolene.jojosquared.client.shader.ModPostShaders;
import net.minecraft.client.gl.GlCommandEncoder;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.Supplier;

@Mixin(GlCommandEncoder.class)
public class GlCommandEncoderMixin implements IPostPassHolder {
    @Unique private Pair<ManagedPostShader, RenderPass> renderingPostPass;

    @Inject(method = "createRenderPass(Ljava/util/function/Supplier;Lcom/mojang/blaze3d/textures/GpuTextureView;Ljava/util/OptionalInt;Lcom/mojang/blaze3d/textures/GpuTextureView;Ljava/util/OptionalDouble;)Lcom/mojang/blaze3d/systems/RenderPass;", at = @At("TAIL"))
    private void modifyPostEffectPass(Supplier<String> supplier, GpuTextureView gpuTextureView, OptionalInt optionalInt, @Nullable GpuTextureView gpuTextureView2, OptionalDouble optionalDouble, CallbackInfoReturnable<RenderPass> cir)
    {
        if (renderingPostPass != null)
            return; // we're already rendering a post effect pass

        String name = supplier.get();

        if (!name.regionMatches(true, 0, "post pass ", 0, "post pass ".length()))
            return;

        String[] split = name.substring("Post pass ".length()).split("/");
        String passShaderName = split[0];

        split = passShaderName.split(":");
        if (!split[0].equals(JoJoSquared.MOD_ID))
            return;
        passShaderName = split[1];

        ManagedPostShader shader = ModPostShaders.getFrameTotal().get(JoJoSquared.location(passShaderName));
        if (shader != null) { renderingPostPass = new Pair<>(shader, cir.getReturnValue()); }
    }

    @Override public @Nullable Pair<ManagedPostShader, RenderPass> jojosquared$getCurrentlyRendering() { return renderingPostPass; }
    @Override public void jojosquared$clearCurrentlyRendering() { renderingPostPass = null; }
}