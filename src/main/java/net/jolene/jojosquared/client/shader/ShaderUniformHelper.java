package net.jolene.jojosquared.client.shader;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.buffers.Std140SizeCalculator;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class ShaderUniformHelper implements AutoCloseable {
    private static final Map<Class<?>, BiConsumer<Std140SizeCalculator, Object>> SIZE_ENCODERS;
    private static final Map<Class<?>, BiConsumer<Std140Builder, Object>> BUILDER_ENCODERS;

    private final List<Object> uniforms = new ArrayList<>();
    private final String name;

    private Std140SizeCalculator sizeCalculator = new Std140SizeCalculator();

    /// Marks if {@link #build()} has to be called.
    private boolean dirty = false;
    private @Nullable GpuBuffer buffer;

    public ShaderUniformHelper(String name)
    {
        this.name = "JoJo Squared UBO " + name;
    }

    public ShaderUniformHelper()
    {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null)
            throw new RuntimeException("Attempted to create ShaderUniformHelper w/ random name but client.player == null!");

        this.name = String.valueOf(client.player.getRandom().nextInt());
    }

    public <T> void add(T value) {
        BiConsumer<Std140SizeCalculator, Object> encoder = SIZE_ENCODERS.get(value.getClass());
        if (encoder == null) {
            throw new UnsupportedOperationException("Attempted to encode unsupported value of type \"" + value.getClass().getSimpleName() + "\" to ShaderUniformHelper! " +
                                                    "(Uniform Index: " + uniforms.size() + ")");
        }
        encoder.accept(sizeCalculator, value);
        uniforms.add(value);
        this.dirty = true;
    }

    @SuppressWarnings("UnusedReturnValue")
    public ShaderUniformHelper build()
    {
        if (this.buffer != null)
        {
            this.buffer.close();
            this.buffer = null;
        }

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            Std140Builder builder = Std140Builder.onStack(stack, sizeCalculator.get());

            GpuDevice device = RenderSystem.getDevice();
            this.buffer = device.createBuffer(() -> this.name, GpuBuffer.USAGE_UNIFORM | GpuBuffer.USAGE_COPY_DST, sizeCalculator.get());

            addUniforms(uniforms, builder);
            this.dirty = false;

            //noinspection DataFlowIssue
            device.createCommandEncoder().writeToBuffer(buffer.slice(), builder.get());

            return this;
        } catch (Exception e) {
            JoJoSquared.LOGGER.error("Tried to build ShaderUniformHelper for UBO of name \"{}\" but caught exception!", name, e);
            return null;
        }
    }

    public void reset()
    {
        this.dirty = true;

        if (this.buffer != null)
        {
            this.buffer.close();
            this.buffer = null;
        }

        this.sizeCalculator = new Std140SizeCalculator();
    }

    /// NOTE: Only call after calling {@link #build()}! Otherwise, will throw error!
    public @Nullable GpuBuffer getBuffer()
    {
        if (this.dirty)
            throw new RuntimeException("getBuffer called for \"" + this.name + "\" but build hasn't been called!");
        if (this.buffer == null)
            throw new RuntimeException("getBuffer called for \"" + this.name + "\" but buffer was null!");

        return this.buffer;
    }

    private static void addUniforms(List<Object> uniforms, Std140Builder builder)
    {
        uniforms.forEach(uniform -> addUniform(uniform, builder));
    }

    private static void addUniform(Object uniform, Std140Builder builder)
    {
        BiConsumer<Std140Builder, Object> encoder = BUILDER_ENCODERS.get(uniform.getClass());
        if (encoder == null) {
            throw new UnsupportedOperationException("Attempted to encode unsupported uniform of type \"" + uniform.getClass().getSimpleName() + "\" to ShaderUniformHelper/Std140Builder!");
        }
        encoder.accept(builder, uniform);
    }

    @Override
    public void close()  {
        if (this.buffer != null)
            this.buffer.close();
    }

    static {
        SIZE_ENCODERS = Map.of(
                Matrix4f.class, (calc, obj) -> calc.putMat4f(),
                Matrix4fStack.class, (calc, obj) -> calc.putMat4f(),
                Vector3f.class, (calc, obj) -> calc.putVec3(),
                Float.class,    (calc, obj) -> calc.putFloat()
        );

        BUILDER_ENCODERS = Map.of(
                Matrix4f.class, (builder, obj) -> builder.putMat4f((Matrix4f) obj),
                Matrix4fStack.class, (builder, obj) -> builder.putMat4f((Matrix4f) obj),
                Vector3f.class, (builder, obj) -> builder.putVec3((Vector3f) obj),
                Float.class, (builder, obj) -> builder.putFloat((float) obj)
        );
    }
}