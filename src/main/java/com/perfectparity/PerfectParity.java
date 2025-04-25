package com.perfectparity;

import com.perfectparity.data.world.gen.ModWorldGeneration;
import com.perfectparity.world.item.ModItems;
import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerfectParity implements ModInitializer {
	public static final String MOD_ID = "minecraft";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModBlocks.initialize();
		ModItems.initialize();
		ModWorldGeneration.initialize();


		CompostingChanceRegistry.INSTANCE.add(ModItems.BUSH, 0.3f);
	}
}