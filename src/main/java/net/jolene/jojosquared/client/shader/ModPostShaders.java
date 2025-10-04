package net.jolene.jojosquared.client.shader;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.gl.ShaderLoader;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.client.util.Pool;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.*;

@SuppressWarnings("SameParameterValue")
@Environment(EnvType.CLIENT)
public class ModPostShaders {
    private static final Deque<ManagedPostShader> POST_QUEUE = new ArrayDeque<>();
    private static final Map<Identifier, ManagedPostShader> TOTAL_THIS_FRAME = new HashMap<>();
    private static final ShaderLoader loader = MinecraftClient.getInstance().getShaderLoader();

    public static final Framebuffer depthBuffer = new Framebuffer("jojosquared$mainDepth", true) {};

    /// NOTE: Should be called from the shader renderer (see {@link net.jolene.jojosquared.mixin.render.WorldRendererMixin}).<br>
    /// Invokes all event listeners and builds up the queue.
    @ApiStatus.Internal
    public static void bootstrap()
    {
        TOTAL_THIS_FRAME.clear();
        POST_QUEUE.clear();

        //register("test_shader");
    }

    @ApiStatus.Internal
    public static @Nullable ManagedPostShader next()
    {
        if (POST_QUEUE.isEmpty())
            return null;

        return POST_QUEUE.pop();
    }

    @ApiStatus.Internal
    public static Map<Identifier, ManagedPostShader> getFrameTotal()
    {
        return TOTAL_THIS_FRAME;
    }

    private static void register(String name)
    { register(name, DefaultFramebufferSet.MAIN_ONLY); }

    private static void register(String name, Set<Identifier> framebuffers)
    {
        Identifier identifier = JoJoSquared.location(name);

        PostEffectProcessor processor = loader.loadPostEffect(identifier, framebuffers);
        if (processor == null)
        {
            JoJoSquared.LOGGER.error("Attempted to load/register shader \"{}\" but the PostEffectProcessor was null!", name);
            return;
        }

        ManagedPostShader shader = new ManagedPostShader(name, processor);

        TOTAL_THIS_FRAME.put(identifier, shader);
        POST_QUEUE.add(shader);
    }
}