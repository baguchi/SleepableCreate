package baguchan.sleepable_create.mixin;

import baguchan.sleepable_create.IHasSleep;
import com.mojang.authlib.GameProfile;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements IHasSleep {

    public ResourceKey<Level> prevRespawnDimension = Level.OVERWORLD;
    @Nullable
    public BlockPos prevRespawnPosition;

    protected ServerPlayerMixin(Level p_219727_, BlockPos p_219728_, float p_219729_, GameProfile p_219730_, @Nullable ProfilePublicKey p_219731_) {
        super(p_219727_, p_219728_, p_219729_, p_219730_, p_219731_);
    }
    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo callbackInfo) {
        ServerPlayer serverPlayer =  (ServerPlayer) ((Object)this);
        if(getStaticSleep() != null){

            serverPlayer.respawnDimension = getStaticSleep().level.dimension();
            serverPlayer.respawnForced = true;
            serverPlayer.respawnPosition = getStaticSleep().blockPosition();

            this.prevRespawnDimension = serverPlayer.respawnDimension;
            this.prevRespawnPosition = serverPlayer.respawnPosition;
        }else {
            if(this.prevRespawnPosition != null){
                serverPlayer.respawnDimension = this.prevRespawnDimension;
                serverPlayer.respawnPosition = this.prevRespawnPosition;
                this.prevRespawnPosition = null;
                serverPlayer.respawnForced = false;
            }
        }
    }

}
