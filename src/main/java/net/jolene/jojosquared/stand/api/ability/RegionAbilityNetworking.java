package net.jolene.jojosquared.stand.api.ability;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.api.MessageListener;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.mixin.IStandOwner;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class RegionAbilityNetworking {
    @MessageListener("region_ability_s2c")
    public static void s2c(@Nullable ServerPlayerEntity serverPlayer, Integer ownerId, String abilityClassName, Boolean adding)
    {
        assert serverPlayer != null : "Got C2S packet for S2C?";

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null)
            return;

        if (client.world.getEntityById(ownerId) instanceof LivingEntity entity)
        {
            IStandOwner owner = IStandOwner.get(entity);
            Stand stand = owner.jojosquared$getStand();
            if (stand == null)
                return;

            stand.getAbilities().forEach(ability -> {
                if (!ability.getClass().getName().equals(abilityClassName))
                    return;

                if (adding)
                {
                    IRegionAbility.addAbility(entity, entity.getWorld(), ability);
                }
                else {
                    IRegionAbility.removeAbility(entity, entity.getWorld(), ability);
                }
            });
        }
    }
}