package net.jolene.jojosquared.item.custom;

import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Objects;

public class StandArrowItem extends Item {

    public StandArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        boolean hasStrengthIIOrHigher = user.hasStatusEffect(StatusEffects.STRENGTH)
                && Objects.requireNonNull(user.getStatusEffect(StatusEffects.STRENGTH)).getAmplifier() >= 1; // Strength II = amplifier 1

        if (hasStrengthIIOrHigher) {
            // Totem effect instead of death
            if (!world.isClient) {
                user.getItemCooldownManager().set(getDefaultStack(), 24000); // 20 minutes cooldown
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            } else {
                // Client-side particles (crit)
                for (int i = 0; i < 20; i++) {
                    world.addParticleClient(
                            ParticleTypes.CRIT,
                            user.getX(),
                            user.getY() + 1,
                            user.getZ(),
                            (world.random.nextDouble() - 0.5) * 0.5,
                            (world.random.nextDouble()) * 0.5,
                            (world.random.nextDouble() - 0.5) * 0.5
                    );
                }
            }
            return ActionResult.SUCCESS;
        }

        // Player does NOT have Strength II+
        if (!world.isClient) {
            // Do NOT change the item
            // Do NOT play break sound

            // Kill player with custom message
            String playerName = user.getName().getString();
            Text[] messages = new Text[] {
                    Text.translatable("death.jojosquared.reject_1", playerName),
                    Text.translatable("death.jojosquared.reject_2", playerName),
                    Text.translatable("death.jojosquared.reject_3", playerName),
                    Text.translatable("death.jojosquared.reject_4", playerName)
            };

            Objects.requireNonNull(world.getServer()).getPlayerManager().broadcast(
                    messages[world.getRandom().nextInt(messages.length)],
                    false
            );


            user.setHealth(0.0F); // You can use .damage(...) instead if you want item drops
        } else {
            // Client-side crit particles
            for (int i = 0; i < 10; i++) {
                world.addParticleClient(
                        ParticleTypes.CRIT,
                        user.getX(),
                        user.getY() + 1,
                        user.getZ(),
                        (world.random.nextDouble() - 0.5) * 0.5,
                        (world.random.nextDouble()) * 0.5,
                        (world.random.nextDouble() - 0.5) * 0.5
                );
            }
        }

        return ActionResult.SUCCESS;
    }
}