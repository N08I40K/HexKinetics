package net.sonunte.hexkinetics.forge;

import at.petrak.hexcasting.api.misc.MediaConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.sonunte.hexkinetics.api.config.HexKineticsConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static at.petrak.hexcasting.api.mod.HexConfig.noneMatch;

public class HexKineticsConfigForge {

    public static void init() {
        Pair<Common, ForgeConfigSpec> config = (new ForgeConfigSpec.Builder()).configure(Common::new);
        Pair<Client, ForgeConfigSpec> clientConfig = (new ForgeConfigSpec.Builder()).configure(Client::new);
        Pair<Server, ForgeConfigSpec> serverConfig = (new ForgeConfigSpec.Builder()).configure(Server::new);
        HexKineticsConfig.setCommon(config.getLeft());
        HexKineticsConfig.setClient(clientConfig.getLeft());
        HexKineticsConfig.setServer(serverConfig.getLeft());
        ModLoadingContext mlc = ModLoadingContext.get();
        mlc.registerConfig(ModConfig.Type.COMMON, config.getRight());
        mlc.registerConfig(ModConfig.Type.CLIENT, clientConfig.getRight());
        mlc.registerConfig(ModConfig.Type.SERVER, serverConfig.getRight());
    }

    public static class Common implements HexKineticsConfig.CommonConfigAccess {
        public Common(ForgeConfigSpec.Builder builder) {

        }
    }

    public static class Client implements HexKineticsConfig.ClientConfigAccess {
        public Client(ForgeConfigSpec.Builder builder) {

        }
    }

    public static class Server implements HexKineticsConfig.ServerConfigAccess {
        private static ForgeConfigSpec.BooleanValue moveTileEntities;
        private static ForgeConfigSpec.ConfigValue<List<? extends String>> translocationDenyList;


        public Server(ForgeConfigSpec.Builder builder) {
            builder.translation("text.autoconfig.hexkinetics.option.server.translocation").push("translocation");

            moveTileEntities = builder
                    .translation("text.autoconfig.hexkinetics.option.server.translocation.moveTileEntities")
                    .define("moveTileEntities", DEFAULT_MOVE_TILE_ENTITIES);
            translocationDenyList = builder
                    .translation("text.autoconfig.hexkinetics.option.server.translocation.translocationDenyList")
                    .defineList("translocationDenyList",
                            HexKineticsConfig.ServerConfigAccess.Companion.getDEFAULT_TRANSLOCATION_DENY_LIST(),
                            HexKineticsConfigForge::isValidReslocArg);

            builder.pop();
        }

        @Override
        public boolean getMoveTileEntities() {
            return moveTileEntities.get();
        }

        @Override
        public boolean isTranslocationAllowed(@NotNull ResourceLocation blockId) {
            return noneMatch(translocationDenyList.get(), blockId);
        }
    }

    private static boolean isValidReslocArg(Object o) {
        return o instanceof String s && ResourceLocation.isValidResourceLocation(s);
    }
}
