package baguchan.sleepable_create.mixin;

import baguchan.sleepable_create.IHasStaticRespawn;
import com.mojang.authlib.GameProfile;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements IHasStaticRespawn {

    public ResourceKey<Level> prevRespawnDimension = Level.OVERWORLD;
    @Nullable
    public BlockPos prevRespawnPosition;

    protected ServerPlayerMixin(Level p_219727_, BlockPos p_219728_, float p_219729_, GameProfile p_219730_, @Nullable ProfilePublicKey p_219731_) {
        super(p_219727_, p_219728_, p_219729_, p_219730_, p_219731_);
    }


    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundTag p_36215_, CallbackInfo callbackInfo) {
        if (p_36215_.contains("StaticLocalPos")) {
            this.setStaticLocalPos(NbtUtils.readBlockPos(p_36215_.getCompound("StaticLocalPos")));
        }
        if (p_36215_.contains("StaticRespawnID")) {
            if (level.getEntity(p_36215_.getInt("StaticRespawnID")) instanceof AbstractContraptionEntity abstractContraptionEntity) {
                this.setStaticRespawn(abstractContraptionEntity);
            }
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(CompoundTag p_36265_, CallbackInfo callbackInfo) {
        if (this.getStaticLocalPos() != null) {
            p_36265_.put("StaticLocalPos", NbtUtils.writeBlockPos(this.getStaticLocalPos()));
        }
        if (this.getStaticRespawn() != null) {
            p_36265_.putInt("StaticRespawnID", this.getStaticRespawn().getId());
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo callbackInfo) {
        ServerPlayer serverPlayer = (ServerPlayer) ((Object) this);
        if (getStaticRespawn() != null && getStaticRespawn().isAliveOrStale() && getStaticRespawn().level == this.level) {

            serverPlayer.respawnDimension = getStaticRespawn().level.dimension();
            serverPlayer.respawnForced = true;
            serverPlayer.respawnPosition = getStaticRespawn().blockPosition().offset(getStaticLocalPos()).above();

            this.prevRespawnDimension = serverPlayer.respawnDimension;
            this.prevRespawnPosition = serverPlayer.respawnPosition;
        } else {
            if (this.prevRespawnPosition != null) {
                serverPlayer.respawnDimension = this.prevRespawnDimension;
                serverPlayer.respawnPosition = this.prevRespawnPosition;
                this.prevRespawnPosition = null;
                serverPlayer.respawnForced = false;
                this.setStaticRespawn(null);
                this.setStaticLocalPos(null);
            }
        }
    }
}
