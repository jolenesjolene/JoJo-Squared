package net.jolene.jojosquared.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class MenacingSpoonItem extends Item {

    private long lastParticleTime = 0;

    public MenacingSpoonItem(Settings settings) {
        super(settings);
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        if (!(entity instanceof PlayerEntity player)) return;
        if (!world.isClient) return;

        // Check if player is holding THIS item
        boolean isHolding = player.getMainHandStack().isOf(this) || player.getOffHandStack().isOf(this);
        if (!isHolding) return;

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastParticleTime >= 1000) {  // 1000 ms = 1 second
            lastParticleTime = currentTime;

            double offsetX = world.random.nextDouble() - 0.5;
            double offsetY = world.random.nextDouble();
            double offsetZ = world.random.nextDouble() - 0.5;

            world.addParticleClient(
                    ParticleTypes.END_ROD,
                    player.getX() + offsetX,
                    player.getY() + 1.0 + offsetY,
                    player.getZ() + offsetZ,
                    0.0, 0.05, 0.0
            );
        }
    }
}
