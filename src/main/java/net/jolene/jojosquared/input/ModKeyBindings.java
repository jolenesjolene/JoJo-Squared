package net.jolene.jojosquared.input;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.input.api.InputModule;
import net.jolene.jojosquared.input.impl.CycleAbilityKey;
import net.jolene.jojosquared.input.impl.PrimaryAttackKey;
import net.jolene.jojosquared.input.impl.SecondaryAttackKey;
import net.jolene.jojosquared.input.impl.SummonStandKey;
import net.minecraft.client.MinecraftClient;
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

    public static final KeyBinding cycleAbility = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojosquared.cycle_ability",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "category.jojosquared.stand"
    ));

    public static final KeyBinding primaryAttack = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojosquared.primary_attack",
            InputUtil.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_1,
            "category.jojosquared.stand"
    ));

    public static final KeyBinding secondaryAttack = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.jojosquared.secondary_attack",
            InputUtil.Type.MOUSE,
            GLFW.GLFW_MOUSE_BUTTON_2,
            "category.jojosquared.stand"
    ));

    public static void registerModKeys()
    {
        BINDINGS.add(new SummonStandKey());
        BINDINGS.add(new PrimaryAttackKey());
        BINDINGS.add(new SecondaryAttackKey());
        BINDINGS.add(new CycleAbilityKey());

        JoJoSquared.LOGGER.info("[Client (JoJoSquared/Keybindings)]: Registering key bindings for {}", JoJoSquared.MOD_ID);
    }

    /// Handle all the registered custom inputs.
    /// @return True if it shouldn't interfere with inputs bound to the same key, otherwise false.
    public static boolean handleInputs()
    {
        boolean shouldContinue = true;
        for (InputModule binding : BINDINGS)
        {
            KeyBinding key = getOriginal(binding.getBinding()); // handler for duplicate keys
            boolean isKeybindYielding = binding.invoke(key.isPressed());
            if (isKeybindYielding != shouldContinue)
                shouldContinue = isKeybindYielding;
            // still continue with the custom inputs
        }
        return shouldContinue;
    }

    private static KeyBinding getOriginal(KeyBinding binding)
    {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options == null)
            return binding;

        KeyBinding original = binding;
        for (KeyBinding b : client.options.allKeys)
        {
            if (!b.equals(binding))
                continue;

            if (!b.getTranslationKey().equals(binding.getTranslationKey())) // lmao this is so janky but wtv
            {
                original = b;
            }
        }
        return original;
    }
}