package net.sonunte.hexkinetics.forge;

import dev.architectury.platform.forge.EventBuses;
import net.sonunte.hexkinetics.HexKinetics;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * This is your loading entrypoint on forge, in case you need to initialize
 * something platform-specific.
 */
@Mod(HexKinetics.MOD_ID)
public class HexKineticsForge {
    public HexKineticsForge() {
        // Submit our event bus to let architectury register our content on the right time
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(HexKinetics.MOD_ID, bus);
        bus.addListener(HexKineticsClientForge::init);
        HexKinetics.init();


    }
}
