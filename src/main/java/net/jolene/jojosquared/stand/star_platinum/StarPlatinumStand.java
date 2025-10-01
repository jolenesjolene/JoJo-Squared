package net.jolene.jojosquared.stand.star_platinum;

import net.jolene.jojosquared.entity.ModEntities;
import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.ability.StandAbility;
import net.jolene.jojosquared.stand.star_platinum.ability.SPDefault;
import net.jolene.jojosquared.stand.star_platinum.ability.SPSpecial;
import net.jolene.jojosquared.stand.star_platinum.ability.SPUtilityPrimary;
import net.jolene.jojosquared.stand.star_platinum.ability.SPUtilitySecondary;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StarPlatinumStand extends Stand {
    public StarPlatinumStand(@NotNull LivingEntity owner) {
        super(owner, ModEntities.STAR_PLATINUM);
        this.setAbilities(createAbilities());
    }

    public List<StandAbility> createAbilities()
    {
        return List.of(
                new SPDefault(this),
                new SPUtilityPrimary(this),
                new SPUtilitySecondary(this),
                new SPSpecial(this)
        );
    }

    @Override
    public void tick() {
        super.tick();
    }
}