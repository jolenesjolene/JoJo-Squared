package net.jolene.jojosquared.stand.star_platinum.ability;

import net.jolene.jojosquared.sound.ModSounds;
import net.jolene.jojosquared.stand.api.PressContext;
import net.jolene.jojosquared.stand.api.StandAbility;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.jolene.jojosquared.stand.star_platinum.StarPlatinumStand;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3d;

public class SPDefault extends StandAbility {
    private final StarPlatinumStand parent;

    private int cooldown = 0;
    private int punchCombo = 0;
    private int lastPressContext;

    public SPDefault(StarPlatinumStand parent)
    { this.parent = parent; }

    @Override
    public void actionPress(int context) {
        if (cooldown != 0)
            return;

        if (punchCombo == 3)
        {
            punchCombo = 0;
            cooldown = 10;
            return;
        }

        if (context == PressContext.PRIMARY)
        {
            if (punchCombo == 0)
                parent.playAnimation(StandEntity.Animations.DEFAULT_HOLD);
        } else if (context == PressContext.SECONDARY) {return;}
    }

    @Override
    public void actionRelease(int context, int ticksHeldFor) {
        if (cooldown != 0 && punchCombo == 0)
        { return; }

        if (context == PressContext.PRIMARY)
        {
            punchCombo++;
            switch (punchCombo)
            {
                case 1 -> {
                    parent.playAnimation(StandEntity.Animations.DEFAULT_1);
                    parent.addRenderOffset(new Vec3d(0f, 0f, -1.25f));
                    cooldown = 20;
                }
                case 2 -> {
                    parent.playAnimation(StandEntity.Animations.DEFAULT_2);
                    cooldown = 25;
                }
                case 3 -> {
                    parent.playAnimation(StandEntity.Animations.DEFAULT_3);
                    punchCombo = 0;
                    cooldown = 25;
                }
            }
            if (parent.getOwner() != null)
            {
                Vec3d pos = parent.getPos();
                parent.getOwner().getWorld().playSound(parent.getEntity(), pos.x, pos.y, pos.z, ModSounds.SWING, SoundCategory.NEUTRAL);
            }
        }
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
            if (punchCombo > 0)
                punchCombo = 0; // womp womp we lost combo

            if (lastPressContext == PressContext.PRIMARY)
            {
                if (lastCooldown != 0)
                {
                    if (punchCombo == 0)
                    {
                        parent.playAnimation(StandEntity.Animations.IDLE);
                        parent.addRenderOffset(new Vec3d(0f, 0f, 1.25f));
                    }
                }
            }
        }
    }
}