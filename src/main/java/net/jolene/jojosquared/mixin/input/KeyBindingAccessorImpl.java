package net.jolene.jojosquared.mixin.input;

import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.input.api.IKeyBindingAccessor;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyBinding.class)
public class KeyBindingAccessorImpl implements IKeyBindingAccessor {
    @Shadow private InputUtil.Key boundKey;

    @Unique private static boolean oldPressed;
    @Inject(method = "setKeyPressed", at = @At("HEAD"), cancellable = true)
    private static void setKeyPressed(InputUtil.Key key, boolean pressed, CallbackInfo ci)
    {
        if (oldPressed == pressed)
            return;

        if (pressed)
        {
            heldKeys.add(key);
        }
        else {
            heldKeys.remove(key);
            ModKeyBindings.keyReleased(key);
        }

        oldPressed = pressed;
    }

    @Override
    public InputUtil.Key jojosquared$getBoundKey() {
        return this.boundKey;
    }

    @Inject(method = "wasPressed", at = @At("HEAD"), cancellable = true)
    private void wasPressed(CallbackInfoReturnable<Boolean> cir)
    {
        if (IKeyBindingAccessor.prioritizedKeys.contains(boundKey))
        {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    @Inject(method = "isPressed", at = @At("HEAD"), cancellable = true)
    private void isPressed(CallbackInfoReturnable<Boolean> cir)
    {
        if (IKeyBindingAccessor.prioritizedKeys.contains(boundKey))
        {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}