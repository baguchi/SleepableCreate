package baguchan.sleepable_create;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;

public interface IHasStaticRespawn {

    void setStaticRespawn(AbstractContraptionEntity staticRespawn);

    AbstractContraptionEntity getStaticRespawn();

    void setStaticLocalPos(BlockPos pos);

    BlockPos getStaticLocalPos();
}
