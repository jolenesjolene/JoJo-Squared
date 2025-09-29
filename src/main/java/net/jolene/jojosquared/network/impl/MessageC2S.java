package net.jolene.jojosquared.network.impl;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.api.IMessagePacket;
import net.jolene.jojosquared.network.payload.ModNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class MessageC2S implements IMessagePacket {
    @Override
    public void invoke(@Nullable ServerPlayerEntity serverPlayer, Object[] args) {
        if (args.length > 1) // namespace
        {
            Object[] remaining = Arrays.copyOfRange(args, 1, args.length);
            Object[] fullArgs = new Object[1 + remaining.length];
            fullArgs[0] = serverPlayer;
            System.arraycopy(remaining, 0, fullArgs, 1, remaining.length);

            ModNetworking.invokeListener((String)args[0], fullArgs);
        }
        else {
            ModNetworking.invokeListener((String)args[0], serverPlayer);
        }
    }
}