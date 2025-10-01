package net.jolene.jojosquared.mixin;

import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.ability.IRegionAbility;
import net.jolene.jojosquared.stand.api.ability.StandAbility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.HashSet;
import java.util.stream.Collectors;

@Mixin(World.class)
public class RegionAbilityImpl implements IRegionAbility {
    @Override
    public HashSet<StandAbility> jojosquared$getAffectedBy(Entity entity) {
        return jojosquared$getAffectedBy(entity, false);
    }

    @Override
    public HashSet<StandAbility> jojosquared$getAffectedBy(Entity entity, boolean excludeOwner) {
        HashSet<StandAbility> abilities = IRegionAbility.REGION_ABILITIES.get(((World)(Object)this));
        if (abilities == null) return null;

        abilities = abilities.stream()
                .filter(ability -> ability.affectsEntity(entity, excludeOwner))
                .collect(Collectors.toCollection(HashSet::new));

        if (!excludeOwner) {
            return new HashSet<>(abilities);
        }

        return abilities.stream()
                .filter(ability -> {
                    Stand stand = ability.getOwner();
                    if (stand == null) return true;

                    LivingEntity owner = stand.getOwner();
                    if (owner == null) return true;

                    return !owner.equals(entity);
                }).collect(Collectors.toCollection(HashSet::new));
    }
}