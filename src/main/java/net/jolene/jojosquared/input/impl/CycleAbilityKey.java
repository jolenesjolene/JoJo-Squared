package net.jolene.jojosquared.input.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.input.api.InputModule;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class CycleAbilityKey extends InputModule {
    public CycleAbilityKey() {
        super(ModKeyBindings.cycleAbility);
    }

    private boolean wasPressed = false;
    @Override
    public boolean invoke(boolean pressed) {
        if (wasPressed == pressed)
            return true; // no work required

        if (pressed)
        {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null)
                return true;
            IStandOwner standOwner = IStandOwner.get(client.player);
            Stand stand = standOwner.jojosquared$getStand();

            if (stand == null || !stand.isSummoned())
            {
                wasPressed = true;
                return true;
            }

            stand.incrementAbilityIndex();
            client.player.sendMessage(Text.literal("Set power to " + Text.translatable(stand.getCurrentAbility().getTranslationKey()).getString()), true);
            client.player.playSound(SoundEvents.BLOCK_DISPENSER_FAIL, 1f, 1f);
            JoJoSquared.LOGGER.info("[Client (JoJoSquared/Keybindings)]: Incrementing stand ability");
        }

        wasPressed = pressed;
        return true;
    }
}