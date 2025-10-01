package net.jolene.jojosquared.input.api;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.ArrayList;
import java.util.List;

public interface IKeyBindingAccessor {
    public static List<InputUtil.Key> heldKeys = new ArrayList<>();
    public static List<InputUtil.Key> prioritizedKeys = new ArrayList<>();
    public InputUtil.Key jojosquared$getBoundKey();

    public static IKeyBindingAccessor get(KeyBinding binding)
    {
        return (IKeyBindingAccessor) binding;
    }
}