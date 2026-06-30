package com.purplepentagons.eltp.event;

import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.entity.ServerPlayerAccess;
import com.purplepentagons.eltp.util.EventUtils;
import com.purplepentagons.eltp.util.ModTags;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class KnifeDamageEvent {
    public static void initialize() {
        AttackEntityCallback.EVENT.register(
            EvenLessTreePunching.id("knife_violence"),
            (PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) -> {
                if(entity instanceof LivingEntity target && player instanceof ServerPlayerEntity serverPlayer) {
                    ServerPlayerAccess serverPlayerAccess = (ServerPlayerAccess) serverPlayer;
                    if(player.getMainHandStack().isIn(ModTags.Items.KNIVES)) {

                        if (player.getAttackCooldownProgress(0.0f) < 0.5f) {
                            return ActionResult.PASS;
                        }

                        int bonusScore = determineKnifeScoreBonus(serverPlayer, target);

                        if (target.getUuid() != serverPlayerAccess.eltp$getKnifeComboEntityUUID()) {
                            serverPlayerAccess.eltp$setKnifeComboEntityUUID(entity.getUuid());
                        }

                        serverPlayerAccess.eltp$addKnifeViolenceScore(bonusScore);
                        serverPlayerAccess.eltp$confirmKnifeHit();

                        int score = serverPlayerAccess.eltp$getKnifeViolenceScore();
                    
                        // apply hyperviolence to player and marked for death to target
                    
                        serverPlayer.sendMessage(Text.of(Integer.toString(score)));

                    } else {
                        // apply bleeding
                        
                        serverPlayerAccess.eltp$setKnifeViolenceScore(0);
                    }   
                }
                return ActionResult.PASS;
            }
        );
    }

    private static int determineKnifeScoreBonus(ServerPlayerEntity player, LivingEntity target) {
        ServerPlayerAccess playerAccess = (ServerPlayerAccess)player;

        int bonusScore = 20;

        // + GREAT JOB
        if (player.getAttackCooldownProgress(0.0f) < 0.8f) {
            return 10;
        }

        // + PUNISH
        if(player.getLastAttackedTime() < 10) {
            bonusScore += 30;
        }
        
        // + DESPERATION
        if(player.getHealth() < player.getMaxHealth()*0.20) {
            bonusScore += 20;
        }

        // + ON FIRE
        if(player.isOnFire()) {
            bonusScore += 20;
        }

        // + SNEAKY
        if(player.isSneaky()) {
            bonusScore += 20;
        }

        // + COMBO
        if (playerAccess.eltp$getKnifeComboEntityUUID() == target.getUuid()) {
            bonusScore += 30;
        }

        // + CRIT
        if(EventUtils.playerCanCrit(player, target)) {
            bonusScore *= 1.5;
        }

        return bonusScore;
    }
}
