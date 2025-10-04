package net.jolene.jojosquared.client.shader;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public interface IPostPassHolder {
    public @Nullable Pair<ManagedPostShader, RenderPass> jojosquared$getCurrentlyRendering();
    public void jojosquared$clearCurrentlyRendering();

    public static IPostPassHolder get(CommandEncoder from)
    {
        return (IPostPassHolder) from;
    }
}