package net.sonunte.hexkinetics.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sonunte.hexkinetics.HexKinetics;
import net.sonunte.hexkinetics.common.casting.actions.great_spells.OpAcceleration;
import net.sonunte.hexkinetics.common.casting.actions.great_spells.OpZeroG;

@Mod.EventBusSubscriber(modid = HexKinetics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EventsHandler {
    @SubscribeEvent
    public static void tickEnd(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START)
            return;
        OpZeroG.tickZeroGEntities();
        OpAcceleration.tickAcceleratedEntities();
    }

    @SubscribeEvent
    public static void entityUnload(EntityLeaveLevelEvent event) {
        OpZeroG.unloadZeroGEntity(event.getEntity());
    }
}
