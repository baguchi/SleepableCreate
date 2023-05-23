package baguchan.sleepable_create.mixin;

import baguchan.sleepable_create.IHasSleep;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements IHasSleep {
    private AbstractContraptionEntity abstractContraptionEntity;
    protected PlayerMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }


    @Override
    public void setStaticSleep(AbstractContraptionEntity staticSleep) {
        this.abstractContraptionEntity = staticSleep;
    }

    @Override
    public AbstractContraptionEntity getStaticSleep() {
        return this.abstractContraptionEntity;
    }
}
