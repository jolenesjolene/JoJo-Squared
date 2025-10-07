package net.jolene.jojosquared.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.block.ModBlocks;
import net.jolene.jojosquared.item.custom.MenacingSpoonItem;
import net.jolene.jojosquared.item.custom.StandArrowItem;
import net.jolene.jojosquared.item.custom.SteelBallItem;
import net.jolene.jojosquared.item.custom.ThrowingKnifeItem;
import net.jolene.jojosquared.sound.ModSounds;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item GROUP_SPRITE = registerItem("group_sprite", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"group_sprite"))).maxCount(1)));
    public static final Item STAND_ARROW = registerItem("stand_arrow", new StandArrowItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"stand_arrow"))).maxCount(1)));
    public static final Item BROKEN_STAND_ARROW = registerItem("broken_stand_arrow", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"broken_stand_arrow"))).maxCount(1)));
    public static final Item STAND_DISC = registerItem("stand_disc", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"stand_disc"))).maxCount(1)));
    public static final Item STEEL_BALL = registerItem("steel_ball", new SteelBallItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"steel_ball"))).maxCount(2)));
    public static final Item THROWING_KNIFE = registerItem("throwing_knife", new ThrowingKnifeItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"throwing_knife"))).maxCount(16)));
    public static final Item STARDUST_SHARD = registerItem("stardust_shard", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"stardust_shard")))));
    public static final Item ROKAKAKA = registerItem("rokakaka", new Item(new Item.Settings().food(ModFoodComponents.ROKAKAKA)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"rokakaka")))));
    public static final Item GOLDEN_ROKAKAKA = registerItem("golden_rokakaka", new Item(new Item.Settings().food(ModFoodComponents.GOLDEN_ROKAKAKA)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"golden_rokakaka")))));
    public static final Item ROKAKAKA_PIT = registerItem("rokakaka_pit", new BlockItem(ModBlocks.ROKAKAKA_PLANT, new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"rokakaka_pit")))));

    public static final Item MENACING_SPOON = registerItem("menacing_spoon", new MenacingSpoonItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"menacing_spoon"))).maxCount(1)));
    public static final Item MORIOH_CHO_RADIO = registerItem("morioh_cho_radio",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.MORIOH_CHO_RADIO_KEY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID,"morioh_cho_radio"))).maxCount(1)));

    // DON'T DELETE
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(JoJoSquared.MOD_ID, name), item);
    }

    public static void registerModItems() {
        JoJoSquared.LOGGER.info("Registering Items for {}", JoJoSquared.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(STEEL_BALL);
            entries.add(THROWING_KNIFE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(STAND_ARROW);
            entries.add(BROKEN_STAND_ARROW);
            entries.add(STAND_DISC);
            entries.add(MORIOH_CHO_RADIO);
            entries.add(MENACING_SPOON);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
                    entries.add(MORIOH_CHO_RADIO);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(ROKAKAKA);
            entries.add(GOLDEN_ROKAKAKA);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ROKAKAKA_PIT);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(STARDUST_SHARD);
        });
    }
}
