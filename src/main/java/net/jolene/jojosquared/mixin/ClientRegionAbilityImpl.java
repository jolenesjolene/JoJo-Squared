package net.jolene.jojosquared.mixin;

import net.jolene.jojosquared.stand.api.ability.IRegionAbility;
import net.jolene.jojosquared.stand.api.ability.StandAbility;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;

@Mixin(ClientWorld.class)
public class ClientRegionAbilityImpl {
    @Inject(method = "tickEntity", at = @At("HEAD"), cancellable = true)
    private void handleTimestop(Entity entity, CallbackInfo ci)
    {
        IRegionAbility regionAbility = IRegionAbility.get(((ClientWorld)(Object)this));

        HashSet<StandAbility> entityAffectedByAbilities = regionAbility.jojosquared$getAffectedBy(entity, true);
        if (entityAffectedByAbilities == null || entityAffectedByAbilities.isEmpty())
            return;
        ci.cancel();
    }
}