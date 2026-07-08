package com.purplepentagons.eltp;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.purplepentagons.eltp.effect.ModStatusEffects;
import com.purplepentagons.eltp.event.ModEvents;
import com.purplepentagons.eltp.item.ModItems;
import com.purplepentagons.eltp.recipe.ModRecipes;

public class EvenLessTreePunching implements ModInitializer {
	public static final String MOD_ID = "eltp";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModEvents.initialize();
		ModStatusEffects.initialize();
		ModRecipes.initialize();
		LOGGER.info("Even Less Tree Punching initialized");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
