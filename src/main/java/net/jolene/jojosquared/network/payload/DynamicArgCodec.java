package net.jolene.jojosquared.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DynamicArgCodec <B extends ByteBuf, V> implements PacketCodec<B, Object[]> {
    private static final HashMap<Class<?>, Function<PacketByteBuf, Object>> DECODERS = new HashMap<>();
    private static final HashMap<Class<?>, BiConsumer<PacketByteBuf, Object>> ENCODERS = new HashMap<>();

    static {
        DECODERS.put(Integer.class, PacketByteBuf::readInt);
        DECODERS.put(Long.class, PacketByteBuf::readLong);
        DECODERS.put(Float.class, PacketByteBuf::readFloat);
        DECODERS.put(Double.class, PacketByteBuf::readDouble);
        DECODERS.put(Boolean.class, PacketByteBuf::readBoolean);
        DECODERS.put(Byte.class, PacketByteBuf::readByte);
        DECODERS.put(Short.class, PacketByteBuf::readShort);
        DECODERS.put(String.class, PacketByteBuf::readString);
        //DECODERS.put(UUID.class, PacketByteBuf::readUuid);
        DECODERS.put(Identifier.class, PacketByteBuf::readIdentifier);
        //DECODERS.put(BlockPos.Mutable.class, PacketByteBuf::readBlockPos);
        //DECODERS.put(BlockPos.class, PacketByteBuf::readBlockPos);
        //DECODERS.put(Vector3f.class, PacketByteBuf::readVector3f);
        DECODERS.put(BlockHitResult.class, PacketByteBuf::readBlockHitResult);
        //DECODERS.put(NbtCompound.class, PacketByteBuf::readNbt);

        ENCODERS.put(Integer.class, (buf, o) -> buf.writeInt((Integer) o));
        ENCODERS.put(int.class, (buf, o) -> buf.writeInt((Integer) o));
        ENCODERS.put(Long.class, (buf, o) -> buf.writeLong((Long) o));
        ENCODERS.put(long.class, (buf, o) -> buf.writeLong((Long) o));
        ENCODERS.put(Float.class, (buf, o) -> buf.writeFloat((Float) o));
        ENCODERS.put(float.class, (buf, o) -> buf.writeFloat((Float) o));
        ENCODERS.put(Double.class, (buf, o) -> buf.writeDouble((Double) o));
        ENCODERS.put(double.class, (buf, o) -> buf.writeDouble((Double) o));
        ENCODERS.put(Boolean.class, (buf, o) -> buf.writeBoolean((Boolean) o));
        ENCODERS.put(boolean.class, (buf, o) -> buf.writeBoolean((Boolean) o));
        ENCODERS.put(Byte.class, (buf, o) -> buf.writeByte((Byte) o));
        ENCODERS.put(byte.class, (buf, o) -> buf.writeByte((Byte) o));
        ENCODERS.put(Short.class, (buf, o) -> buf.writeShort((Short) o));
        ENCODERS.put(short.class, (buf, o) -> buf.writeShort((Short) o));
        ENCODERS.put(Character.class, (buf, o) -> buf.writeChar((Character) o));
        ENCODERS.put(char.class, (buf, o) -> buf.writeChar((Character) o));
        ENCODERS.put(String.class, (buf, o) -> buf.writeString((String) o));
        //ENCODERS.put(UUID.class, (buf, o) -> buf.writeUuid((UUID) o));
        ENCODERS.put(Identifier.class, (buf, o) -> buf.writeIdentifier((Identifier) o));
        //ENCODERS.put(BlockPos.Mutable.class, (buf, o) -> buf.writeBlockPos((BlockPos) o));
        //ENCODERS.put(BlockPos.class, (buf, o) -> buf.writeBlockPos((BlockPos) o));
        //ENCODERS.put(Vector3f.class, (buf, o) -> buf.writeVector3f((Vector3f)o));
        ENCODERS.put(BlockHitResult.class, (buf, o) -> buf.writeBlockHitResult((BlockHitResult) o));
        //ENCODERS.put(NbtCompound.class, (buf, o) -> buf.writeNbt((NbtCompound) o));
    }

    @Override
    public Object[] decode(B _buf) {
        PacketByteBuf buf = new PacketByteBuf(_buf);
        return decodeBufferToVArgs(buf);
    }

    @Override
    public void encode(B _buf, Object[] value) {
        PacketByteBuf buf = new PacketByteBuf(_buf);
        encodeBufferFromVArgs(buf, value);
    }

    public void encodeValue(PacketByteBuf buf, Object value) {
        BiConsumer<PacketByteBuf, Object> encoder = ENCODERS.get(value.getClass());
        if (encoder == null) {
            throw new IllegalArgumentException("No encoder for type: " + value.getClass().getName());
        }
        encoder.accept(buf, value);
    }

    public Object decodeValue(PacketByteBuf buf, Class<?> type) {
        Function<PacketByteBuf, Object> decoder = DECODERS.get(type);
        if (decoder == null) {
            throw new IllegalArgumentException("No decoder for type: " + type.getName());
        }
        return decoder.apply(buf);
    }

    public static String generateSignature(Object... args) {
        if (args.length == 0)
            return "void";  // special marker for no arguments

        StringBuilder sig = new StringBuilder();

        for (Object arg : args) {
            if (arg == null)
            {
                sig.append("?;");
                continue;
            }

            Class<?> c = arg.getClass();

            sig.append(c.getName()).append(";");
        }

        return sig.toString();
    }

    private void encodeBufferFromVArgs(PacketByteBuf buf, Object... args) {
        // type metadata
        buf.writeString(generateSignature(args));

        // actual params
        for (Object arg : args) {
            encodeValue(buf, arg);
        }

        // final buffer looks like:
        // ["Ljava.lang.String;java.lang.Integer;Ljava.lang.Long;Ljava.lang.Double", "Hello", 42, 10000000000L, 3.14]
    }

    private Object[] decodeBufferToVArgs(PacketByteBuf buf) {
        String sig = buf.readString();

        if (sig.equals("void")) {
            return new Object[0]; // no arguments
        }

        List<Object> result = new ArrayList<>();
        String[] sigClasses = sig.split(";");

        for (String className : sigClasses) {
            if (className.isEmpty()) continue; // skip empty due to trailing ";"
            try {
                Class<?> clazz = Class.forName(className);
                result.add(decodeValue(buf, clazz));
            } catch (Exception e) {
                throw new RuntimeException("Failed decoding PacketByteBuf to VArgs:\n" +
                        className + " was not found.\n[" + sig + "]");
            }
        }

        if (buf.readableBytes() > 0) {
            throw new RuntimeException("Warning: extra unread bytes in buffer! Signature: " + sig);
        }

        return result.toArray();
    }
}