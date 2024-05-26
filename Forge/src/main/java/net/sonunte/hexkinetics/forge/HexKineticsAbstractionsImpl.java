package net.sonunte.hexkinetics.forge;

import net.sonunte.hexkinetics.HexKineticsAbstractions;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class HexKineticsAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexKineticsAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
	
    public static void initPlatformSpecific() {
        HexKineticsConfigForge.init();
    }
}
