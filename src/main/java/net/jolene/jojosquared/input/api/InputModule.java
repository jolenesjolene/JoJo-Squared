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
    /// The main method of the InputModule.
    /// @return True if it shouldn't interfere with inputs bound to the same key, otherwise false (stop Minecraft's default keybinding)
    public abstract boolean invoke(boolean pressed);
}