package net.jolene.jojosquared.input.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;

@Environment(EnvType.CLIENT)
public abstract class StickyInputModule extends InputModule {
    public StickyInputModule(KeyBinding binding)
    { super(binding); }
}