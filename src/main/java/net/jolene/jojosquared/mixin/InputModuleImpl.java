package net.jolene.jojosquared.mixin;

import net.jolene.jojosquared.input.ModKeyBindings;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class InputModuleImpl {
    @Inject(method = "handleInputEvents", at = @At("HEAD"), cancellable = true)
    private void handleCustomInputs(CallbackInfo ci)
    {
        ModKeyBindings.handleInputs();
//        if (!ModKeyBindings.handleInputs())
//            ci.cancel();
    }
}