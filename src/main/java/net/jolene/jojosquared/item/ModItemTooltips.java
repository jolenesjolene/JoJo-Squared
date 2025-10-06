package net.jolene.jojosquared.item;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.block.ModBlocks;
import net.minecraft.text.Text;

public class ModItemTooltips {
    public static void register() {
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.isOf(ModBlocks.MYSTERIOUS_SHIP_METAL.asItem())) {
                list.add(Text.translatable("item.jojosquared.mysterious_ship_metal.desc"));
            }
        });
    }

    public static void registerModItemTooltips() {
        JoJoSquared.LOGGER.info("Registering Tooltips for " + JoJoSquared.MOD_ID);
        register();
    }
}

