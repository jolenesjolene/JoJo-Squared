package net.jolene.jojosquared.mixin.render.access;

import com.mojang.blaze3d.buffers.GpuBuffer;
import net.minecraft.client.gl.PostEffectPass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(PostEffectPass.class)
public interface PostPassAccessor {
    @Accessor("uniformBuffers")
    public Map<String, GpuBuffer> getUniformBuffers();
}