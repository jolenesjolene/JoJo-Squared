package net.jolene.jojosquared.input.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.input.api.InputModule;
import net.jolene.jojosquared.stand.api.PressContext;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class SecondaryAttackKey extends InputModule {
    public SecondaryAttackKey() {
        super(ModKeyBindings.secondaryAttack);
    }

    private boolean wasPressed = false;
    @Override
    public boolean invoke(boolean pressed) {
        if (wasPressed == pressed)
            return false; // no work required

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null)
            return false;
        IStandOwner standOwner = IStandOwner.get(client.player);
        Stand stand = standOwner.jojosquared$getStand();

        if (stand == null || !stand.isSummoned())
        {
            wasPressed = pressed;
            return false;
        }

        if (pressed)
        {
            stand.attack(PressContext.SECONDARY);
            JoJoSquared.LOGGER.info("[Client (JoJoSquared/Keybindings)]: Doing secondary attack");
        }
        else {
            stand.releaseAtk(PressContext.SECONDARY);
            JoJoSquared.LOGGER.info("[Client (JoJoSquared/Keybindings)]: Releasing secondary attack");
        }

        wasPressed = pressed;
        return false;
    }
}