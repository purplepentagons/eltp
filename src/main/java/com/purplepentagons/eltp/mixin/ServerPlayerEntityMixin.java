package com.purplepentagons.eltp.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.authlib.GameProfile;
import com.purplepentagons.eltp.entity.ServerPlayerAccess;

import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

@Mixin(ServerPlayerEntity.class)
abstract class ServerPlayerEntityMixin implements ServerPlayerAccess {
    @Unique
    private int eltp$knifeViolenceScore;
    @Unique
    private int eltp$lastKnifeHitTime;
    @Unique
    private UUID eltp$knifeComboEntityUuid;

    public int eltp$getKnifeViolenceScore() {
        return eltp$knifeViolenceScore;
    }

    public void eltp$setKnifeViolenceScore(int newScore) {
        eltp$knifeViolenceScore = newScore;
    }

    public void eltp$addKnifeViolenceScore(int newScore) {
        eltp$knifeViolenceScore += newScore;
    }

    public UUID eltp$getKnifeComboEntityUUID() {
        return eltp$knifeComboEntityUuid;
    }

    public void eltp$setKnifeComboEntityUUID(UUID entityUuid) {
        eltp$knifeComboEntityUuid = entityUuid;
    }

    public void eltp$confirmKnifeHit() {
        eltp$lastKnifeHitTime = ((ServerPlayerEntity) (Object) this).age;
    }

    @Inject(method = "<init>*", at = @At(value = "TAIL"))
    private void populateField(MinecraftServer server, ServerWorld world, GameProfile profile, SyncedClientOptions clientOptions, CallbackInfo info) {
        eltp$knifeViolenceScore = 0;
        eltp$lastKnifeHitTime = 0;
    }

    @Inject(method = "tick", at = @At(value = "TAIL")) 
    private void clearKnifeViolenceScore(CallbackInfo info) {
        if(((ServerPlayerEntity) (Object) this).age - eltp$lastKnifeHitTime > 5*20) {
            eltp$setKnifeViolenceScore(0);
        }
    }
}
