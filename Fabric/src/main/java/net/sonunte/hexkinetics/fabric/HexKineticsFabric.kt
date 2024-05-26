package net.sonunte.hexkinetics.fabric

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.sonunte.hexkinetics.HexKinetics
import net.sonunte.hexkinetics.common.casting.actions.great_spells.OpAcceleration
import net.sonunte.hexkinetics.common.casting.actions.great_spells.OpZeroG

object HexKineticsFabric : ModInitializer {
    override fun onInitialize() {
        HexKinetics.init()

        ServerTickEvents.END_SERVER_TICK.register {
            OpZeroG.tickZeroGEntities()
            OpAcceleration.tickAcceleratedEntities()
        }

        ServerEntityEvents.ENTITY_UNLOAD.register { entity, _ ->
            OpZeroG.unloadZeroGEntity(entity)
        }
    }
}