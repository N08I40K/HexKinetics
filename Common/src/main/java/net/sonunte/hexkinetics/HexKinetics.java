package net.sonunte.hexkinetics;

import net.sonunte.hexkinetics.common.casting.Patterns;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is effectively the loading entrypoint for most of your code, at least
 * if you are using Architectury as intended.
 */
public class HexKinetics {
    public static final String MOD_ID = "hexkinetics";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static void init() {
        LOGGER.info("HexKinetics says hello!");

        HexKineticsAbstractions.initPlatformSpecific();
        Patterns.registerPatterns();
    }

    /**
     * Shortcut for identifiers specific to this mod.
     */
    public static ResourceLocation id(String string) {
        return new ResourceLocation(MOD_ID, string);
    }
}
