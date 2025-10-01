package net.jolene.jojosquared.stand.api.network;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.api.MessageListener;
import net.jolene.jojosquared.network.payload.ModNetworking;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class StandOwnerNetworking {
    @MessageListener("stand_owner_c2s")
    public static void getStandC2S(@Nullable ServerPlayerEntity serverPlayer, Integer entityId)
    {
        assert serverPlayer != null : "Got S2C packet for C2S?";

        ServerWorld world = serverPlayer.getWorld();
        if (world == null)
            return;

        if (world.getEntityById(entityId) instanceof LivingEntity entity)
        {
            IStandOwner owner = IStandOwner.get(entity);
            Stand stand = owner.jojosquared$getStand();

            if (stand == null)
                ModNetworking.sendMessageS2C(serverPlayer, "stand_owner_s2c", entityId, "none", Integer.MIN_VALUE, false);
            else
            {
                ModNetworking.sendMessageS2C(serverPlayer, "stand_owner_s2c", entityId, stand.getClass().getName(), (stand.getEntity() != null ? stand.getEntity().getId() : Integer.MIN_VALUE), stand.isSummoned());
                JoJoSquared.LOGGER.info("[Server (JoJoSquared/Stand|StandOwnerNetworking)]: Responding | entityId: {} | className: {} | isSummoned: {}", entity, stand.getClass().getName(), stand.isSummoned());
            }
        }
    }

    @MessageListener("stand_owner_s2c")
    public static void getStandS2C(@Nullable ServerPlayerEntity serverPlayer, Integer entityId, String className, Integer standId, Boolean summoned)
    {
        assert serverPlayer == null : "Got C2S packet for S2C?";

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null)
            return;

        if (client.world.getEntityById(entityId) instanceof LivingEntity entity)
        {
            if (className.equals("none")) {
                return; // we asked the server if the entity had a stand, turns out, it doesn't
            }

            try {
                IStandOwner owner = IStandOwner.get(entity);
                Stand stand = owner.jojosquared$getStand();

                if (stand != null && stand.getClass().getName().equals(className))
                    return; // nothing to do, entity already has the stand we're setting

                JoJoSquared.LOGGER.info("[Client (JoJoSquared/Stand|OwnerNetworking)]: Received stand class \"{}\" for entity of class \"{}\" (ID: {} | Summoned: {})", className, entity.getClass().getSimpleName(), entity.getId(), summoned);
                //noinspection unchecked
                Class<? extends Stand> standClass = (Class<? extends Stand>) Class.forName(className);
                Stand s = standClass.getDeclaredConstructor(LivingEntity.class).newInstance(entity);
                owner.jojosquared$setStand(s, standId, summoned);
            }
            catch (Exception e)
            {
                JoJoSquared.LOGGER.warn("[Client (JoJo Squared/Stand|OwnerNetworking)]: Failed to get stand class for entity!", e);
            }
        }
    }
}