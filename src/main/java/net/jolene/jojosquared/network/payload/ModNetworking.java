package net.jolene.jojosquared.network.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.api.MessageListener;
import net.jolene.jojosquared.network.api.MessageListenerScanner;
import net.jolene.jojosquared.network.impl.PacketRegistry;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModNetworking {
    private static List<Class<?>> listeners = new ArrayList<>();

    public static void registerPackets()
    {
        PayloadTypeRegistry.playS2C().register(MessageS2CPayload.ID, MessageS2CPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(MessageC2SPayload.ID, MessageC2SPayload.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(MessageS2CPayload.ID, (payload, context) -> {
            context.client().execute(() -> PacketRegistry.invoke(PacketRegistry.MESSAGES2C, null, payload.args()));
        });

        ServerPlayNetworking.registerGlobalReceiver(MessageC2SPayload.ID, (payload, context) -> {
            context.server().execute(()-> PacketRegistry.invoke(PacketRegistry.MESSAGEC2S, context.player(), payload.args()));
        });

        long start = System.currentTimeMillis();
        listeners = new MessageListenerScanner().scan();
        JoJoSquared.LOGGER.info("Found {} listeners in {}s.", listeners.size(), (System.currentTimeMillis()-start)/1000.0);
    }

    public static void sendMessageS2C(ServerPlayerEntity serverPlayer, String namespace, Object... varargs)
    {
        Object[] args = new Object[varargs.length + 1];
        args[0] = namespace;
        System.arraycopy(varargs, 0, args, 1, varargs.length);
        ServerPlayNetworking.send(serverPlayer, new MessageS2CPayload(args));
    }

    public static void sendMessageC2S(String namespace, Object... varargs)
    {
        Object[] args = new Object[varargs.length + 1];
        args[0] = namespace;
        System.arraycopy(varargs, 0, args, 1, varargs.length);
        ClientPlayNetworking.send(new MessageC2SPayload(args));
    }

    public static void invokeListener(String namespace, Object... varargs)
    {
        listeners.forEach(clazz -> {
            for (Method m : clazz.getMethods())
            {
                if (!m.isAnnotationPresent(MessageListener.class))
                    continue;

                MessageListener listener = m.getAnnotation(MessageListener.class);
                if (!listener.value().equals(namespace))
                    continue;

                String argsDescriptor = DynamicArgCodec.generateSignature(varargs);
                if (matches(m, argsDescriptor))
                {
                    try
                    {
                        m.invoke(null, varargs);
                        continue;
                    }
                    catch (Exception ignored) { }
                }
            }
        });
    }

    public static String getMethodDescriptor(Method method) {
        StringBuilder sb = new StringBuilder();
        for (Class<?> param : method.getParameterTypes()) {
            sb.append(getDescriptor(param)).append(";");
        }
        return sb.toString();
    }

    private static String getDescriptor(Class<?> clazz) {
        return clazz.getName(); // should be replaced in the future with more jvm-style descriptors, but not yet
    }

    private static boolean matches(Method m, String argsDescriptor) {
        Class<?>[] params = m.getParameterTypes();
        String[] argTypes = argsDescriptor.split(";");

        if (params.length != argTypes.length) return false;

        for (int i = 0; i < params.length; i++) {
            String argType = argTypes[i];

            if ("?".equals(argType)) continue;

            if (!params[i].getName().equals(argType)) {
                return false;
            }
        }

        return true;
    }
}
