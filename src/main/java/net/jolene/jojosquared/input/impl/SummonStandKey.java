package net.jolene.jojosquared.input.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.input.ModKeyBindings;
import net.jolene.jojosquared.input.api.StickyInputModule;
import net.jolene.jojosquared.sound.ModSounds;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class SummonStandKey extends StickyInputModule {
    public SummonStandKey() {
        super(ModKeyBindings.summonStand);
    }

    @Override
    public void invoke(boolean pressed) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null)
            return;
        IStandOwner standOwner = IStandOwner.get(client.player);
        Stand stand = standOwner.getStand();

        if (stand != null) {
            if (!stand.isSummoned()) {
                stand.summon();
                JoJoSquared.LOGGER.info("[Client (JoJoSquared/Keybindings)]: Summoning stand");
                float pitch = 1.0f + client.player.getRandom().nextFloat() * 0.2f;
                client.player.playSound(ModSounds.MANIFEST_STAND, 1f, pitch);
            } else {
                stand.withdraw();
                JoJoSquared.LOGGER.info("[Client (JoJoSquared/Keybindings)]: Removing stand");
                float pitch = 1.0f + client.player.getRandom().nextFloat() * 0.2f;
                client.player.playSound(ModSounds.WITHDRAW_STAND, 1f, pitch);
            }
        }
    }

    @Override
    public boolean isBlocking() {
        return false;
    }
}