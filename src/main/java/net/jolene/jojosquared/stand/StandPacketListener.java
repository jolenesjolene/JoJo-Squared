package net.jolene.jojosquared.stand;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.api.MessageListener;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class StandPacketListener {
    @MessageListener("stand")
    public static void summon(@Nullable ServerPlayerEntity serverPlayer, String arg)
    {
        JoJoSquared.LOGGER.info("Got packet!!! " + arg);
    }
}