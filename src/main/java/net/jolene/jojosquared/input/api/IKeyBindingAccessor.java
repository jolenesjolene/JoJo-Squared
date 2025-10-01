package net.jolene.jojosquared.input.api;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.ArrayList;
import java.util.HashSet;

public interface IKeyBindingAccessor {
    public static HashSet<InputUtil.Key> heldKeys = new HashSet<>();
    public static HashSet<InputUtil.Key> prioritizedKeys = new HashSet<>();
    public InputUtil.Key jojosquared$getBoundKey();

    public static IKeyBindingAccessor get(KeyBinding binding)
    {
        return (IKeyBindingAccessor) binding;
    }
}