package net.jolene.jojosquared.input;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.input.api.IKeyBindingAccessor;
import net.jolene.jojosquared.input.api.InputModule;
import net.jolene.jojosquared.input.api.StickyInputModule;
import net.jolene.jojosquared.input.impl.CycleAbilityKey;
import net.jolene.jojosquared.input.impl.PrimaryAttackKey;
import net.jolene.jojosquared.input.impl.SecondaryAttackKey;
import net.jolene.jojosquared.input.impl.SummonStandKey;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    public static void keyReleased(InputUtil.Key key)
    {
        for (InputModule binding : BINDINGS)
        {
            if (binding instanceof StickyInputModule)
                continue;

            InputUtil.Key boundKey = IKeyBindingAccessor.get(binding.getBinding()).jojosquared$getBoundKey();
            if (boundKey.equals(key))
            { binding.invoke(false); }
        }
    }

    public static void processHeldKeys(List<InputUtil.Key> heldKeys)
    {
        for (InputModule binding : BINDINGS)
        {
            InputUtil.Key boundKey = IKeyBindingAccessor.get(binding.getBinding()).jojosquared$getBoundKey();
            boolean pressed = heldKeys.contains(boundKey);

            if (binding.state == pressed)
                continue;
            binding.state = pressed;

            if (!pressed && binding instanceof StickyInputModule)
                continue;

            if (pressed)
            {
                if (binding.isBlocking())
                {
                    if (!IKeyBindingAccessor.prioritizedKeys.contains(boundKey))
                        IKeyBindingAccessor.prioritizedKeys.add(boundKey);
                }
                else { IKeyBindingAccessor.prioritizedKeys.remove(boundKey); }

                binding.invoke(true);
            }
        }
    }
}