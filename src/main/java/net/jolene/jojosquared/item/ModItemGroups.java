package net.jolene.jojosquared.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup JOJO_SQUARED_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(JoJoSquared.MOD_ID, "jojo_squared"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.GROUP_SPRITE))
                    .displayName(Text.translatable("itemgroup.jojosquared.jojosquared"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.STAND_ARROW);
                        entries.add(ModItems.BROKEN_STAND_ARROW);
                        entries.add(ModItems.STAND_DISC);
                        entries.add(ModBlocks.STARFALLEN_STONE);
                        entries.add(ModItems.STARDUST_SHARD);
                        entries.add(ModItems.ROKAKAKA);
                        entries.add(ModItems.GOLDEN_ROKAKAKA);
                        entries.add(ModItems.ROKAKAKA_PIT);
                        Item SQUID_INK_PASTA = Registries.ITEM.get(Identifier.of("farmersdelight", "squid_ink_pasta"));
                        if (SQUID_INK_PASTA != Items.AIR) {
                            entries.add(SQUID_INK_PASTA);}
                        entries.add(ModItems.MORIOH_CHO_RADIO);
                        entries.add(ModItems.MENACING_SPOON);
                        entries.add(ModBlocks.MYSTERIOUS_SHIP_METAL);
                    }).build());

    public static void registerModItemGroups() {
        JoJoSquared.LOGGER.info("Registering Item Groups for " + JoJoSquared.MOD_ID);
    }
}

