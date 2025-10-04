package net.jolene.jojosquared.client.shader;

import net.jolene.jojosquared.JoJoSquared;
import xyz.zetalasis.limelight.shader.api.ShaderType;
import xyz.zetalasis.limelight.shader.event.LimelightShaderEvents;

public class ModPostShaders {
    public static void registerModPostShaders()
    {
        JoJoSquared.LOGGER.info("Registering Shaders for {}", JoJoSquared.MOD_ID);
        LimelightShaderEvents.REGISTER_SHADERS.register(context -> {
            if (context.type() == ShaderType.POST)
            {
                //context.register().accept(JoJoSquared.location("test_shader"));
            }
        });
    }
}