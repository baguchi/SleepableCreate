package baguchan.sleepable_create;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.behaviour.MovingInteractionBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class AnchorInteractionBehaviour extends MovingInteractionBehaviour implements MovementBehaviour {

    @Override
    public boolean renderAsNormalBlockEntity() {
        return true;
    }

    @Override
    public boolean mustTickWhileDisabled() {
        return true;
    }

    @Override
    public void tick(MovementContext context) {
        if (context.world == null || !context.world.isClientSide || context.position == null
                || context.state.getValue(RespawnAnchorBlock.CHARGE) <= 0 || context.disabled)
            return;

        RandomSource random = context.world.random;

        if (random.nextInt(100) == 0) {
            context.world.playSound((Player) null, (double) context.position.x() + 0.5D, (double) context.position.y() + 0.5D, (double) context.position.z() + 0.5D, SoundEvents.RESPAWN_ANCHOR_AMBIENT, SoundSource.BLOCKS, 1.0F, 1.0F);
        }

        double d0 = (double) context.position.x + 0.5D + (0.5D - random.nextDouble());
        double d1 = (double) context.position.y + 1.0D;
        double d2 = (double) context.position.z + 0.5D + (0.5D - random.nextDouble());
        double d3 = (double) random.nextFloat() * 0.04D;
        context.world.addParticle(ParticleTypes.REVERSE_PORTAL, d0, d1, d2, 0.0D, d3, 0.0D);
    }

    public boolean handlePlayerInteraction(Player player, InteractionHand activeHand, BlockPos localPos,
                                           AbstractContraptionEntity contraptionEntity) {
        if (!contraptionEntity.level.isClientSide()) {
            ItemStack stack = player.getItemInHand(activeHand);
            if (stack.is(Items.GLOWSTONE)) {
                StructureTemplate.StructureBlockInfo info = contraptionEntity.getContraption().getBlocks()
                        .get(localPos);
                if (info.state.is(Blocks.RESPAWN_ANCHOR)) {
                    if (info.state.getValue(RespawnAnchorBlock.CHARGE) < 4) {
                        BlockState newState = info.state.setValue(RespawnAnchorBlock.CHARGE, Integer.valueOf(info.state.getValue(RespawnAnchorBlock.CHARGE) + 1));
                        stack.shrink(1);
                        playSound(contraptionEntity, SoundEvents.RESPAWN_ANCHOR_CHARGE);
                        contraptionEntity.setBlock(localPos, new StructureTemplate.StructureBlockInfo(info.pos, newState, info.nbt));
                    }
                }
            } else if (player instanceof IHasStaticRespawn sleep && contraptionEntity.level.dimensionType().respawnAnchorWorks()) {
                StructureTemplate.StructureBlockInfo info = contraptionEntity.getContraption().getBlocks()
                        .get(localPos);
                if (info.state.is(Blocks.RESPAWN_ANCHOR)) {
                    if (info.state.getValue(RespawnAnchorBlock.CHARGE) > 0) {
                        sleep.setStaticRespawn(contraptionEntity);
                        sleep.setStaticLocalPos(localPos);
                        player.displayClientMessage(Component.translatable("block.minecraft.set_spawn"), true);

                        playSound(contraptionEntity, SoundEvents.RESPAWN_ANCHOR_SET_SPAWN);
                    }
                }
            } else {
                player.displayClientMessage(Component.translatable("sleepable_create.too_dangeous"), true);
            }
        }

        return true;
    }

    public static void playSound(AbstractContraptionEntity contraptionEntity, SoundEvent soundEvent) {
        Level world = contraptionEntity.level;
        BlockPos pos = new BlockPos(contraptionEntity.blockPosition());
        // Vanilla bell sound
        world.playSound(null, pos, soundEvent,
                SoundSource.BLOCKS, 1f, 1f);
    }

}