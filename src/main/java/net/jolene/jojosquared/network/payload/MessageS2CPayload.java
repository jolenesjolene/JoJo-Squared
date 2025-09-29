package net.jolene.jojosquared.network.payload;

import io.netty.buffer.ByteBuf;
import net.jolene.jojosquared.network.impl.PacketRegistry;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record MessageS2CPayload(Object... args) implements CustomPayload {
    public static final Id<MessageS2CPayload> ID = new Id<>(PacketRegistry.MESSAGES2C);
    public static final PacketCodec<ByteBuf, MessageS2CPayload> CODEC = PacketCodec.tuple(new DynamicArgCodec<>(), MessageS2CPayload::args, MessageS2CPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}