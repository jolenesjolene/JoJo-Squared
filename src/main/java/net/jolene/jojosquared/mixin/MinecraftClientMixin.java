package net.jolene.jojosquared.mixin;

import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.input.api.IKeyBindingAccessor;
import net.jolene.jojosquared.stand.api.ability.IRegionAbility;
import net.jolene.jojosquared.stand.api.ability.StandAbility;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow @Nullable public ClientPlayerEntity player;

    @Shadow private volatile boolean paused;

    @Shadow protected abstract void handleInputEvents();

    @Shadow @Nullable public ClientWorld world;

    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void handleInputEvents(CallbackInfo ci)
    {
        ModKeyBindings.processHeldKeys(IKeyBindingAccessor.heldKeys);
        IKeyBindingAccessor.heldKeys.clear();
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void handleTimestop(CallbackInfo ci)
    {
        if (player == null || player.clientWorld == null || world == null)
            return;

        HashSet<StandAbility> abilitiesInRange = IRegionAbility.get(player.clientWorld).jojosquared$getAffectedBy(player, true);
        if (abilitiesInRange == null)
            return;

        if (!(abilitiesInRange.isEmpty()))
        {
            paused = true;
            world.tickEntities(); // overriden in a mixin, should be fine to call here
        }
    }
}