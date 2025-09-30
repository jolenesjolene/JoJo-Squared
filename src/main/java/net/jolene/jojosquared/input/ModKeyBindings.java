package net.jolene.jojosquared.input;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.input.api.InputModule;
import net.jolene.jojosquared.input.impl.SummonStandKey;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;

@Environment(EnvType.CLIENT)
public class ModKeyBindings {
    private static final HashSet<InputModule> BINDINGS = new HashSet<>();

    public static final KeyBinding summonStand = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojosquared.summon_stand",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "category.jojosquared.stand"
    ));

    public static void registerModKeys()
    {
        BINDINGS.add(new SummonStandKey());

        JoJoSquared.LOGGER.info("Registering key bindings for {}", JoJoSquared.MOD_ID);
    }

    public static void handleInputs()
    {
        BINDINGS.forEach(inputModule -> {
            inputModule.invoke(inputModule.getBinding().wasPressed());
        });
    }
}