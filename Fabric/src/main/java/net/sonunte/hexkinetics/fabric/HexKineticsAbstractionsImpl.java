package net.sonunte.hexkinetics.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.sonunte.hexkinetics.HexKineticsAbstractions;

import java.nio.file.Path;

public class HexKineticsAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexKineticsAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
	
    public static void initPlatformSpecific() {
        HexKineticsConfigFabric.init();
    }
}
