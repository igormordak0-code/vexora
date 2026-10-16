package net.g4ngar_;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VEXORAClient implements ModInitializer {
    public static final String MOD_ID = "vexora";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("VEXORA GUI loaded");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
