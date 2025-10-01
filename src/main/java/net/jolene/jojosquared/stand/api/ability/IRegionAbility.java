package net.jolene.jojosquared.stand.api.ability;

import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.network.payload.ModNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.HashSet;

/// Represents an ability that targets a region in a world.
public interface IRegionAbility {
    public static final HashMap<World, HashSet<StandAbility>> REGION_ABILITIES = new HashMap<>();

    public HashSet<StandAbility> jojosquared$getAffectedBy(Entity entity);
    public HashSet<StandAbility> jojosquared$getAffectedBy(Entity entity, boolean excludeOwner);

    public static IRegionAbility get(World from)
    {
        return (IRegionAbility) from;
    }

    public static void addAbility(LivingEntity owner, World world, StandAbility ability)
    {
        if (owner == null || world == null || ability == null)
            return;

        if (!REGION_ABILITIES.containsKey(world))
            REGION_ABILITIES.put(world, new HashSet<>());

        REGION_ABILITIES.get(world).add(ability);
        if (!world.isClient)
        {
            ServerWorld serverWorld = (ServerWorld) world;
            serverWorld.getPlayers().forEach(serverPlayer -> {
                if (serverPlayer.equals(owner))
                    return;

                ModNetworking.sendMessageS2C(serverPlayer, "region_ability_s2c", owner.getId(), ability.getClass().getName(), true);
            });
        }
        else {
            JoJoSquared.LOGGER.info("[Client]: Added ability!!!");
        }
    }

    public static void removeAbility(LivingEntity owner, World world, StandAbility ability)
    {
        if (!REGION_ABILITIES.containsKey(world))
            return;

        REGION_ABILITIES.get(world).remove(ability);
        if (!world.isClient)
        {
            ServerWorld serverWorld = (ServerWorld) world;
            serverWorld.getPlayers().forEach(serverPlayer -> {
                if (serverPlayer.equals(owner))
                    return;

                ModNetworking.sendMessageS2C(serverPlayer, "region_ability_s2c", owner.getId(), ability.getClass().getName(), false);
            });
        }
        else {
            JoJoSquared.LOGGER.info("[Client]: Removed ability");
        }
    }
}