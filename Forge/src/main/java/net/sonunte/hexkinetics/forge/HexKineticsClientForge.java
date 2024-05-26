package net.sonunte.hexkinetics.forge;

import net.sonunte.hexkinetics.HexKineticsClient;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Forge client loading entrypoint.
 */
public class HexKineticsClientForge {
    public static void init(FMLClientSetupEvent event) {
        HexKineticsClient.init();
    }
}
