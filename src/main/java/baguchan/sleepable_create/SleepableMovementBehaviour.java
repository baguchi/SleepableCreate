package baguchan.sleepable_create;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.behaviour.MovingInteractionBehaviour;
import com.simibubi.create.content.contraptions.behaviour.SimpleBlockMovingInteraction;
import com.simibubi.create.content.equipment.bell.AbstractBellBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SleepableMovementBehaviour extends MovingInteractionBehaviour {


    public boolean handlePlayerInteraction(Player player, InteractionHand activeHand, BlockPos localPos,
                                           AbstractContraptionEntity contraptionEntity) {
        if(player instanceof IHasSleep sleep && contraptionEntity.level.dimensionType().bedWorks()){
            sleep.setStaticSleep(contraptionEntity);
            playSound(contraptionEntity);
        }else {
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