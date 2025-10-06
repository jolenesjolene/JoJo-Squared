package net.jolene.jojosquared.client.shader;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.stand.api.ability.IRegionAbility;
import net.jolene.jojosquared.stand.api.ability.StandAbility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.Nullable;
import xyz.zetalasis.limelight.shader.LimelightPostShaders;
import xyz.zetalasis.limelight.shader.api.ManagedPostShader;
import xyz.zetalasis.limelight.shader.api.ShaderType;
import xyz.zetalasis.limelight.shader.event.LimelightShaderEvents;
import xyz.zetalasis.limelight.util.FieldHandle;

import java.util.HashSet;

@SuppressWarnings("FieldCanBeLocal")
public class ModPostShaders {
    private static @Nullable ManagedPostShader TIMESTOP_SHADER;
    private static final FieldHandle<ManagedPostShader> TIMESTOP_HANDLE = new FieldHandle<>(()->TIMESTOP_SHADER, s -> TIMESTOP_SHADER = s);

    public static void registerModPostShaders()
    {
        JoJoSquared.LOGGER.info("Registering Shaders for {}", JoJoSquared.MOD_ID);
        LimelightShaderEvents.REGISTER_SHADERS.register(context -> {
            if (context.type() == ShaderType.POST)
            {
                handleTimestopReg(context);
            }
        });
    }

    private static void handleTimestopReg(LimelightShaderEvents.RegisterShadersContext context)
    {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null)
        {
            deleteShader(TIMESTOP_SHADER);
            TIMESTOP_SHADER = null;
            return;
        };

        HashSet<StandAbility> abilities = IRegionAbility.get(client.world).jojosquared$getAffectedBy(client.player, false);
        if (abilities == null || abilities.isEmpty())
        {
            deleteShader(TIMESTOP_SHADER);
            TIMESTOP_SHADER = null;
            return;
        };

        context.register().accept(
                TIMESTOP_HANDLE,
                new ManagedPostShader.Definition(JoJoSquared.location("timestop"), new Pair<>(null, DefaultFramebufferSet.MAIN_ONLY))
        );
    }

    private static void deleteShader(ManagedPostShader shader)
    {
        if (shader == null)
        {
            return;
        }

        LimelightPostShaders.close(shader);
        TIMESTOP_HANDLE.set(null);
    }
}