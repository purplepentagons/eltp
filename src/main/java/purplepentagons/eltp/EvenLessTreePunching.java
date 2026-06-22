package purplepentagons.eltp;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import purplepentagons.eltp.items.Items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvenLessTreePunching implements ModInitializer {
	public static final String MOD_ID = "eltp";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		Items.initialize();
		LOGGER.info("Even Less Tree Punching initialized");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
