package net.jolene.jojosquared.input.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;

@Environment(EnvType.CLIENT)
public abstract class InputModule {
    private final KeyBinding binding;
    public boolean state = false;

    public InputModule(KeyBinding binding)
    {
        this.binding = binding;
    }

    public KeyBinding getBinding() { return binding; }
    /// The main method of the InputModule.
    /// @return True if it should prioritize (stop Minecraft's default keybinding), otherwise false
    public abstract void invoke(boolean pressed);
    public abstract boolean isBlocking();
}