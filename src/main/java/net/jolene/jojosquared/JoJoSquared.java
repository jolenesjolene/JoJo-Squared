package net.jolene.jojosquared;

import net.fabricmc.api.ModInitializer;

import net.jolene.jojosquared.block.ModBlocks;
import net.jolene.jojosquared.effect.ModEffects;
import net.jolene.jojosquared.item.ModItemGroups;
import net.jolene.jojosquared.item.ModItems;
import net.jolene.jojosquared.particle.ModParticles;
import net.jolene.jojosquared.sound.ModSounds;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoJoSquared implements ModInitializer {
	public static final String MOD_ID = "jojosquared";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();
		ModBlocks.registerModBlocks();
		ModParticles.registerModParticles();
		ModSounds.registerModSounds();
		ModEffects.registerEffects();

		LOGGER.info("ORA! (JoJo²)");
	}
}