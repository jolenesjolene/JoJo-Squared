package net.jolene.jojosquared.stand.star_platinum.ability;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jolene.jojosquared.client.stand.state.StarPlatinumRenderState;
import net.jolene.jojosquared.network.payload.ModNetworking;
import net.jolene.jojosquared.sound.ModSounds;
import net.jolene.jojosquared.stand.api.PressContext;
import net.jolene.jojosquared.stand.api.StandAbility;
import net.jolene.jojosquared.stand.api.StandEntity;
import net.jolene.jojosquared.stand.api.hitbox.StandAbilityHitbox;
import net.jolene.jojosquared.stand.api.network.StandC2SContext;
import net.jolene.jojosquared.stand.star_platinum.StarPlatinumStand;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SPDefault extends StandAbility {
    private final StarPlatinumStand parent;

    private int cooldown = 0;
    private int punchCombo = 0;
    private int lastPressContext;
    private StandAbilityHitbox hitbox;

    public SPDefault(StarPlatinumStand parent)
    { this.parent = parent; }

    @Override
    public void actionPress(int context) {
        if (parent.getEntity() == null)
            return;

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
        if (parent.getEntity() == null)
            return;

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
                // adds vec3() for eye height so the hitbox/sound isn't in the ground
                Vec3d pos = parent.getPos().add(new Vec3d(0, parent.getOwner().getEyeHeight(parent.getOwner().getPose()), 0));
                World world = parent.getOwner().getWorld();

                world.playSound(parent.getEntity(), pos.x, pos.y, pos.z, ModSounds.SWING, SoundCategory.NEUTRAL);

                if (world.isClient)
                {
                    float hitboxSize = 0.8f;
                    float hitboxDistance = 2.5f;

                    Vec3d punchOffset = Vec3d.fromPolar(parent.getOwner().getPitch(), parent.getOwner().getHeadYaw()).multiply(hitboxDistance);
                    hitbox = new StandAbilityHitbox(pos.add(punchOffset), new Vec3d(hitboxSize, hitboxSize, hitboxSize), parent.getEntity().age, 3);
                    for (LivingEntity ent : hitbox.getEntitiesInside(parent.getOwner().getWorld()))
                    {
                        if (ent.equals(parent.getOwner()))
                            continue;

                        ModNetworking.sendMessageC2S("base_stand_atk_c2s", ent.getId(), 1 + punchCombo, StandC2SContext.DAMAGE_ENTITY);
                    }
                }
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
        if (this.parent.getEntity() == null)
            return;

        int lastCooldown = cooldown;
        if (cooldown > 0)
            cooldown--;
        else
            cooldown = 0;

        if (hitbox != null)
        {
            if (hitbox.getDestroyTick() <= this.parent.getEntity().age)
            {
                hitbox = null;
            }
        }

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

    @Override @Environment(EnvType.CLIENT)
    public void render(EntityRenderState state, MatrixStack matrices, float age, VertexConsumerProvider vertexConsumer) {
        matrices.push();
        matrices.translate(-state.x, -state.y, -state.z); // entity space -> world space

        if (hitbox != null)
        {
            hitbox.visualize(matrices, vertexConsumer);
        }

        matrices.pop();
    }
}