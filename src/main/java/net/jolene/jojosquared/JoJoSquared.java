package net.jolene.jojosquared;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.jolene.jojosquared.block.ModBlocks;
import net.jolene.jojosquared.effect.ModEffects;
import net.jolene.jojosquared.entity.ModEntities;
import net.jolene.jojosquared.item.ModItemGroups;
import net.jolene.jojosquared.item.ModItems;
import net.jolene.jojosquared.network.payload.ModNetworking;
import net.jolene.jojosquared.particle.ModParticles;
import net.jolene.jojosquared.sound.ModSounds;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoJoSquared implements ModInitializer {
	public static final String MOD_ID = "jojosquared";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier location(String path)
	{
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		setDebug();

		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();
		ModBlocks.registerModBlocks();
		ModParticles.registerModParticles();
		ModSounds.registerModSounds();
		ModEffects.registerEffects();
		ModEntities.registerModEntities();
		ModNetworking.registerPackets();

		LOGGER.info("ORA! (JoJo²)");
	}

	private static void setDebug() {
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			Configurator.setLevel(MOD_ID, Level.DEBUG);
		}
	}
}