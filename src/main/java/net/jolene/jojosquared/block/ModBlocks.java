package net.jolene.jojosquared.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jolene.jojosquared.JoJoSquared;
import net.jolene.jojosquared.block.classes.RokakakaPlantBlock;
import net.jolene.jojosquared.block.classes.RokakakaPlantTopBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block STARFALLEN_STONE = registerBlock("starfallen_stone",
            AbstractBlock.Settings.copy(Blocks.STONE));

    public static final Block ROKAKAKA_PLANT = registerBlockWithoutBlockItem("rokakaka_plant",
            properties -> new RokakakaPlantBlock(properties.noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block ROKAKAKA_PLANT_TOP = registerBlockWithoutBlockItem("rokakaka_plant_top",
            properties -> new RokakakaPlantTopBlock(properties.noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    private static Block registerBlock(String name, AbstractBlock.Settings blockSettings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(JoJoSquared.MOD_ID, name));
        Block block = new Block(blockSettings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }
    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(JoJoSquared.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(key));
        Registry.register(Registries.ITEM, key, item);
    }
    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(JoJoSquared.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(JoJoSquared.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        JoJoSquared.LOGGER.info("Registering Blocks for " + JoJoSquared.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ModBlocks.STARFALLEN_STONE);
        });
    }
}
