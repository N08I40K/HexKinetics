package net.sonunte.hexkinetics.fabric

import net.fabricmc.api.ClientModInitializer
import net.sonunte.hexkinetics.HexKineticsClient

object HexKineticsClientFabric : ClientModInitializer {
    override fun onInitializeClient() {
        HexKineticsClient.init()
    }
}