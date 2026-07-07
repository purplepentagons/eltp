package com.purplepentagons.eltp.event;

import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.effect.ModStatusEffects;
import com.purplepentagons.eltp.util.ModTags;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class BleedOnHitEvent {
    public static void initialize() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register(EvenLessTreePunching.id("bleed_on_hit"), 
            (LivingEntity entity, DamageSource source, float damageTaken, float baseDamageTaken, boolean blocked) -> {
                if (
                    entity instanceof ServerPlayerEntity player &&
                    player.getWorld() instanceof ServerWorld serverWorld &&
                    damageTaken >= 5 &&
                    source.isIn(ModTags.Damage.BLEEDS) &&
                    !blocked
                ) {
                    applyBleedingEffect(player, damageTaken);
                    spawnBloodParticles(serverWorld, player, source);
                }
            }
        );
    }

    private static void spawnBloodParticles(ServerWorld serverWorld, ServerPlayerEntity player, DamageSource source) {
        BlockStateParticleEffect bloodParticle = new BlockStateParticleEffect(
            ParticleTypes.BLOCK,
            Blocks.NETHER_WART_BLOCK.getDefaultState()
        );

        if (source.isOf(DamageTypes.FALL)) {
            serverWorld.spawnParticles(
                bloodParticle, 
                player.getX(), player.getY() + 0.05d, player.getZ(),
                60,
                (double)player.getWidth(), 0.1, (double)player.getWidth(),
                0.002
            );
            return;
        }

        serverWorld.spawnParticles(
            bloodParticle, 
            player.getX(), player.getY() + 0.1d, player.getZ(),
            30,
            (double)player.getWidth(), (double)player.getHeight(), (double)player.getWidth(),
            0.002
        );
    }

    private static void applyBleedingEffect(ServerPlayerEntity player, float damageTaken) {
        StatusEffectInstance bleedingEffectInstance = new StatusEffectInstance(
            ModStatusEffects.BLEEDING,
            3*20*(int)(damageTaken),
            0,
            false,
            true,
            true
        );

        player.addStatusEffect(bleedingEffectInstance);
    }
}
