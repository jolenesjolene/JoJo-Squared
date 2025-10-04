package net.jolene.jojosquared.mixin.render.access;

import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectPass;
import net.minecraft.client.gl.PostEffectPipeline;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.ProjectionMatrix2;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mixin(PostEffectProcessor.class)
public interface PostEffectAccessor {
    @Accessor("passes")
    public abstract List<PostEffectPass> getPasses();
    @Accessor("internalTargets")
    public Map<Identifier, PostEffectPipeline.Targets> internalTargets();
    @Accessor("externalTargets")
    public Set<Identifier> externalTargets();
    @Accessor("framebuffers")
    public Map<Identifier, Framebuffer> framebuffers();
    @Accessor("projectionMatrix")
    public ProjectionMatrix2 projectionMatrix();
}