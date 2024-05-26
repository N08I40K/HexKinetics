package net.sonunte.hexkinetics.fabric.interop;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.sonunte.hexkinetics.fabric.HexKineticsConfigFabric;

@Environment(EnvType.CLIENT)
public class ModMenuInterop implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(HexKineticsConfigFabric.class, parent).get();
    }
}
