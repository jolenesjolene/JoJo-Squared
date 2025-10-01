package net.jolene.jojosquared.input.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.input.api.InputModule;
import net.jolene.jojosquared.input.api.StickyInputModule;
import net.jolene.jojosquared.stand.api.PressContext;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class PrimaryAttackKey extends InputModule {
    public PrimaryAttackKey() { super(ModKeyBindings.primaryAttack); }

    @Override
    public void invoke(boolean pressed) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null)
            return;
        IStandOwner standOwner = IStandOwner.get(client.player);
        Stand stand = standOwner.jojosquared$getStand();

        if (stand == null || stand.isBusy())
            return;

        if (pressed)
            stand.attack(PressContext.PRIMARY);
        else
            stand.releaseAtk(PressContext.PRIMARY);
    }

    @Override
    public boolean isBlocking() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null)
        { return false; }

        IStandOwner standOwner = IStandOwner.get(client.player);
        Stand stand = standOwner.jojosquared$getStand();

        return stand != null && !stand.isBusy();
    }
}