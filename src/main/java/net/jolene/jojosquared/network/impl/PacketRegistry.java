package net.jolene.jojosquared.network.impl;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.api.IMessagePacket;
import net.jolene.jojosquared.network.payload.MessageS2CPayload;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.HashMap;

public class PacketRegistry {
    private static final HashMap<Identifier, IMessagePacket> PACKETS = new HashMap<>();
    public static final Identifier MESSAGEC2S = JoJoSquared.location("messagec2s");
    public static final Identifier MESSAGES2C = JoJoSquared.location("messages2c");

    static {
        register(MESSAGES2C, new MessageS2C());
        register(MESSAGEC2S, new MessageC2S());
    }

    public static void register(Identifier id, IMessagePacket packet)
    {
        PACKETS.put(id, packet);
    }

    public static void invoke(Identifier id, @Nullable ServerPlayerEntity serverPlayer, Object... args)
    {
        IMessagePacket packetClass = PACKETS.get(id);
        if (id == null)
            return;

        try
        {
            packetClass.invoke(serverPlayer, args);
        } catch (Exception e) {
            PACKETS.remove(id);
            JoJoSquared.LOGGER.warn("Removing packet {} as it doesn't contain a working invoke method!\nEx: {}", id, e.toString());
            e.printStackTrace();
        }
    }
}