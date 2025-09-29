package net.jolene.jojosquared.network.payload;

import io.netty.buffer.ByteBuf;
import net.jolene.jojosquared.network.impl.PacketRegistry;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record MessageC2SPayload(Object... args) implements CustomPayload {
    public static final Id<MessageC2SPayload> ID = new Id<>(PacketRegistry.MESSAGEC2S);
    public static final PacketCodec<ByteBuf, MessageC2SPayload> CODEC = PacketCodec.tuple(new DynamicArgCodec<>(), MessageC2SPayload::args, MessageC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() { return ID; }
}