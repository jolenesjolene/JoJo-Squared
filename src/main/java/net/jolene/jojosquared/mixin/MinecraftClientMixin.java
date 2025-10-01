package net.jolene.jojosquared.mixin;

import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.input.api.IKeyBindingAccessor;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void handleInputEvents(CallbackInfo ci)
    {
        ModKeyBindings.processHeldKeys(IKeyBindingAccessor.heldKeys);
        IKeyBindingAccessor.heldKeys.clear();
    }
}