package net.jolene.jojosquared.client.shader;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.textures.GpuTextureView;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.mixin.render.access.PostEffectAccessor;
import net.jolene.jojosquared.mixin.render.access.PostPassAccessor;
import net.minecraft.client.gl.*;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class ManagedPostShader
{
    private final PostEffectProcessor processor;
    private final ShaderUniformHelper uniformHelper;
    private final List<PostEffectPass> passes;
    private final Map<String, GpuTextureView> samplers = new HashMap<>();

    private final Identifier id;

    public ManagedPostShader(String name, @NotNull PostEffectProcessor postEffect)
    {
        this.id = JoJoSquared.location(name);
        this.processor = postEffect;
        this.uniformHelper = new ShaderUniformHelper(name);
        this.passes = ((PostEffectAccessor)postEffect).getPasses();
    }

    public void render(Framebuffer framebuffer, ObjectAllocator pool)
    {
        FrameGraphBuilder frameGraphBuilder = new FrameGraphBuilder();
        PostEffectProcessor.FramebufferSet framebufferSet = PostEffectProcessor.FramebufferSet.singleton(
                PostEffectProcessor.MAIN, frameGraphBuilder.createObjectNode("main", framebuffer)
        );
        this.processor.render(frameGraphBuilder, framebuffer.textureWidth, framebuffer.textureHeight, framebufferSet);
        frameGraphBuilder.run(pool);

        samplers.clear();
    }

    public void setUniforms(String name, Object... objects) {
        this.uniformHelper.reset();

        for (Object obj : objects) { this.uniformHelper.add(obj); }
        this.uniformHelper.build();

        PostEffectAccessor effectAccessor = (PostEffectAccessor) this.processor;

        effectAccessor.getPasses().forEach(pass -> {
            PostPassAccessor accessor = (PostPassAccessor) pass;
            Map<String, GpuBuffer> uniformBuffers = accessor.getUniformBuffers();

            if (!uniformBuffers.containsKey(name)) { return; }

            GpuBuffer oldBuffer = uniformBuffers.remove(name);
            if (oldBuffer != null) { oldBuffer.close(); }

            uniformBuffers.put(name, this.uniformHelper.getBuffer());
        });
    }
    public void setSampler(String samplerName, GpuTextureView texture)
    {
        samplers.put(samplerName, texture);
    }

    public List<PostEffectPass> getPasses() {
        return passes;
    }
    public Identifier getId() {
        return id;
    }

    /// Runs directly before one of the post effect processor's passes ({@link PostEffectPass}) renders.<br>
    /// See {@link net.jolene.jojosquared.mixin.render.RenderPassMixin}
    @ApiStatus.Internal
    public void beforeDrawPostPass(RenderPass pass)
    { samplers.forEach(pass::bindSampler); }
}