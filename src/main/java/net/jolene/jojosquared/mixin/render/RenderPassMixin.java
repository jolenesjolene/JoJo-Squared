package net.jolene.jojosquared.mixin.render;

import com.mojang.blaze3d.systems.RenderPass;
import net.jolene.jojosquared.client.shader.IPostPassHolder;
import net.jolene.jojosquared.client.shader.ManagedPostShader;
import net.minecraft.client.gl.GlCommandEncoder;
import net.minecraft.client.gl.RenderPassImpl;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPassImpl.class)
public abstract class RenderPassMixin {
    @Shadow @Final private GlCommandEncoder resourceManager;

    @Inject(method = "drawIndexed", at = @At("HEAD"))
    private void drawIndexed(int i, int j, int k, int l, CallbackInfo ci)
    {
        IPostPassHolder holder = IPostPassHolder.get(resourceManager);
        Pair<ManagedPostShader, RenderPass> currentlyRendering = holder.jojosquared$getCurrentlyRendering();
        if (currentlyRendering == null)
            return; // not rendering a custom post pass :(

        RenderPass self = ((RenderPass) (Object) this);
        if (!currentlyRendering.getRight().equals(self))
            return; // not rendering us :(

        currentlyRendering.getLeft().beforeDrawPostPass(self);
        holder.jojosquared$clearCurrentlyRendering();
    }
}