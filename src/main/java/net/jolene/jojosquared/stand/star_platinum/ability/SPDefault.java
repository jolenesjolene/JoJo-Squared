package net.jolene.jojosquared.stand.star_platinum.ability;

import net.jolene.jojosquared.stand.api.PressContext;
import net.jolene.jojosquared.stand.api.StandAbility;
import net.jolene.jojosquared.stand.star_platinum.StarPlatinumStand;
import net.minecraft.util.math.Vec3d;

public class SPDefault extends StandAbility {
    private final StarPlatinumStand parent;

    private int cooldown = 0;
    private int lastPressContext;

    public SPDefault(StarPlatinumStand parent)
    { this.parent = parent; }

    @Override
    public void actionPress(int context) {
        if (cooldown != 0)
        {
            return;
        }

        if (context == PressContext.PRIMARY)
        {
            parent.playAnimation(1);
            parent.addRenderOffset(new Vec3d(0f, 0f, -1.25f));
            cooldown = 20;

        } else if (context == PressContext.SECONDARY) {return;}
    }

    @Override
    public void actionRelease(int context, int ticksHeldFor) {

    }

    @Override
    public String getTranslationKey() {
        return "ability.jojosquared.sp_default";
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public void tick() {
        int lastCooldown = cooldown;
        if (cooldown > 0)
            cooldown--;
        else
            cooldown = 0;

        if (cooldown == 0) {
            if (lastPressContext == PressContext.PRIMARY)
            {
                if (lastCooldown != 0)
                    parent.addRenderOffset(new Vec3d(0f, 0f, 1.25f));
            }
        }
    }
}