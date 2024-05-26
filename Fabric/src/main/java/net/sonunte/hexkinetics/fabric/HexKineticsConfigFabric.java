package net.sonunte.hexkinetics.fabric;

import at.petrak.hexcasting.api.misc.MediaConstants;
import dev.architectury.platform.Platform;
import net.minecraft.resources.ResourceLocation;
import net.sonunte.hexkinetics.HexKinetics;
import net.sonunte.hexkinetics.api.config.HexKineticsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.EnvType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static at.petrak.hexcasting.api.mod.HexConfig.noneMatch;

@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
@Config(name = HexKinetics.MOD_ID)
public class HexKineticsConfigFabric extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject
    public final Common common = new Common();
    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.TransitiveObject
    public final Client client = new Client();
    @ConfigEntry.Category("server")
    @ConfigEntry.Gui.TransitiveObject
    public final Server server = new Server();

    public static void init() {
        AutoConfig.register(HexKineticsConfigFabric.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        var instance = AutoConfig.getConfigHolder(HexKineticsConfigFabric.class).getConfig();

        HexKineticsConfig.setCommon(instance.common);

        if (Platform.getEnv().equals(EnvType.CLIENT)) {
            HexKineticsConfig.setClient(instance.client);
        }

        // Needed for logical server in singleplayer, do not access server configs from client code
        HexKineticsConfig.setServer(instance.server);
    }


    @Config(name = "common")
    private static class Common implements ConfigData, HexKineticsConfig.CommonConfigAccess {
    }

    @Config(name = "client")
    private static class Client implements ConfigData, HexKineticsConfig.ClientConfigAccess {
    }


    @Config(name = "server")
    private static class Server implements ConfigData, HexKineticsConfig.ServerConfigAccess {

        @ConfigEntry.Gui.CollapsibleObject
        private SettingsTranslocation settingsTranslocation = new SettingsTranslocation();

        @Override
        public boolean getMoveTileEntities() {
            return SettingsTranslocation.moveTileEntities;
        }

        @Override
        public boolean isTranslocationAllowed(@NotNull ResourceLocation blockId) {
            return noneMatch(settingsTranslocation.translocationDenyList, blockId);
        }

        static class SettingsTranslocation {
            private static boolean moveTileEntities = DEFAULT_MOVE_TILE_ENTITIES;

            @ConfigEntry.Gui.Tooltip
            private List<String> translocationDenyList = HexKineticsConfig.ServerConfigAccess.Companion.getDEFAULT_TRANSLOCATION_DENY_LIST();
        }

        @Override
        public void validatePostLoad() throws ValidationException {

            // costs of misc spells

            if (this.settingsTranslocation.translocationDenyList.stream().anyMatch(b -> !isValidReslocArg(b)))
                this.settingsTranslocation.translocationDenyList = HexKineticsConfig.ServerConfigAccess.Companion.getDEFAULT_TRANSLOCATION_DENY_LIST();
        }
    }

    private int bound(int toBind, int lower, int upper) {
        return Math.min(Math.max(toBind, lower), upper);
    }

    private double bound(double toBind, double lower, double upper) {
        return Math.min(Math.max(toBind, lower), upper);
    }

    private static boolean isValidReslocArg(Object o) {
        return o instanceof String s && ResourceLocation.isValidResourceLocation(s);

    }
}