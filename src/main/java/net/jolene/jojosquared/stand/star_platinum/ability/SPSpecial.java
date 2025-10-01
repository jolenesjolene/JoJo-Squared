package net.jolene.jojosquared.stand.star_platinum.ability;

import net.jolene.jojosquared.stand.api.Stand;
import net.jolene.jojosquared.stand.api.ability.IRegionAbility;
import net.jolene.jojosquared.stand.api.ability.PressContext;
import net.jolene.jojosquared.stand.api.ability.StandAbility;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.jolene.jojosquared.stand.star_platinum.StarPlatinumStand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.HashSet;

public class SPSpecial extends StandAbility {
    private final StarPlatinumStand parent;
    private int cooldown = 0;
    public SPSpecial(StarPlatinumStand parent)
    { this.parent = parent; }

    private boolean justReleased = false;
    @Override
    public void actionPress(int context) {
        if (parent.getOwner() == null || parent.getOwner().getWorld() == null)
            return;

        LivingEntity owner = parent.getOwner();
        World ownerWorld = parent.getOwner().getWorld();
        StandEntity standEntity = parent.getEntity();

        if (standEntity == null)
            return;

        if (cooldown != 0)
            return;

        if (context == PressContext.PRIMARY)
        {
            if (IRegionAbility.REGION_ABILITIES.containsKey(ownerWorld))
            {
                if (IRegionAbility.REGION_ABILITIES.get(ownerWorld).contains(this))
                {
                    parent.setStoppedTime(false);
                    IRegionAbility.removeAbility(owner, ownerWorld, this);
                    parent.flushTimestopDamage();
                    justReleased = true;
                    return; // untimestop
                }
            }

            if (parent.getOwner().getWorld().isClient)
            {
                standEntity.setAnimation(StandEntity.Animations.SPECIAL_HOLD);
            }
            else { }
        }
    }

    @Override
    public void actionRelease(int context, int ticksHeldFor) {
        if (justReleased)
        {
            justReleased = false;
            cooldown = 20;
            return;
        }

        if (cooldown != 0)
            return;

        if (parent.getOwner() == null || parent.getOwner().getWorld() == null)
            return;

        LivingEntity owner = parent.getOwner();
        StandEntity standEntity = parent.getEntity();
        World ownerWorld = parent.getOwner().getWorld();

        if (standEntity == null)
            return;

        if (context == PressContext.PRIMARY)
        {
            IRegionAbility.addAbility(owner, ownerWorld, this);
            parent.setStoppedTime(true);
            if (parent.getOwner().getWorld().isClient)
            {
                standEntity.setAnimation(StandEntity.Animations.SPECIAL, 20);
            }
        }
    }

    @Override
    public void tick() {
        if (cooldown > 0)
            cooldown--;
        else
            cooldown = 0;
    }

    @Override
    public Stand getOwner() {
        return this.parent;
    }

    @Override
    public String getTranslationKey() {
        return "ability.jojosquared.sp_special";
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public boolean affectsEntity(Entity entity, boolean excludeOwner) {
        Stand stand = this.getOwner();
        if (stand == null)
            return false;
        LivingEntity owner = stand.getOwner();
        if (owner == null)
            return false;

        if (excludeOwner)
        {
            if (entity.equals(owner) || entity.equals(stand.getEntity()))
                return false;
        }
        return entity.distanceTo(owner) < 128;
    }
}