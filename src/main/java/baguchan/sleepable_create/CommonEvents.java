package baguchan.sleepable_create;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SleepableCreate.MODID)
public class CommonEvents {

    @SubscribeEvent
    public static void syncEvent(PlayerEvent.Clone event) {
        Player original = event.getOriginal();
        Player player = event.getEntity();
        ((IHasStaticRespawn) player).setStaticRespawn(((IHasStaticRespawn) original).getStaticRespawn());
        ((IHasStaticRespawn) player).setStaticLocalPos(((IHasStaticRespawn) original).getStaticLocalPos());
    }
}
