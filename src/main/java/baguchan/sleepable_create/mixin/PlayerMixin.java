package baguchan.sleepable_create.mixin;

import baguchan.sleepable_create.IHasStaticRespawn;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements IHasStaticRespawn {
    private AbstractContraptionEntity abstractContraptionEntity;
    public BlockPos respawnLocalPostion;

    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }


    @Override
    public void setStaticRespawn(AbstractContraptionEntity staticRespawn) {
        this.abstractContraptionEntity = staticRespawn;
    }

    @Override
    public AbstractContraptionEntity getStaticRespawn() {
        return this.abstractContraptionEntity;
    }

    @Override
    public void setStaticLocalPos(BlockPos pos) {
        this.respawnLocalPostion = pos;
    }

    @Override
    public BlockPos getStaticLocalPos() {
        return this.respawnLocalPostion;
    }
}