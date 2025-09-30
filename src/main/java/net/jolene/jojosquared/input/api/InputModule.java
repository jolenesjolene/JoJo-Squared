package net.jolene.jojosquared.input.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;

@Environment(EnvType.CLIENT)
public abstract class InputModule {
    private final KeyBinding binding;

    public InputModule(KeyBinding binding)
    {
        this.binding = binding;
    }

    public KeyBinding getBinding() { return binding; }
    public abstract void invoke(boolean pressed);
}