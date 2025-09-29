package net.jolene.jojosquared.network.api;

import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public interface IMessagePacket {
    public void invoke(@Nullable ServerPlayerEntity serverPlayer, Object[] args);
}
