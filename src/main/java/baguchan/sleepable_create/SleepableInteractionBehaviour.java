package baguchan.sleepable_create;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.behaviour.MovingInteractionBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SleepableInteractionBehaviour extends MovingInteractionBehaviour {


    public boolean handlePlayerInteraction(Player player, InteractionHand activeHand, BlockPos localPos,
                                           AbstractContraptionEntity contraptionEntity) {
        if (player instanceof IHasStaticRespawn sleep && contraptionEntity.level.dimensionType().bedWorks()) {
            sleep.setStaticRespawn(contraptionEntity);
            player.displayClientMessage(Component.translatable("block.minecraft.set_spawn"), true);
        } else {
            player.displayClientMessage(Component.translatable("sleepable_create.too_dangeous"), true);
        }

        return true;
    }

    public static void playSound(AbstractContraptionEntity contraptionEntity) {
        Level world = contraptionEntity.level;
        BlockPos pos = new BlockPos(contraptionEntity.blockPosition());
            // Vanilla bell sound
            world.playSound(null, pos, SoundEvents.RESPAWN_ANCHOR_SET_SPAWN,
                    SoundSource.BLOCKS, 1f, 1f);
    }

}