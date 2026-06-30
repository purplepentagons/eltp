package com.purplepentagons.eltp.event;

import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.item.ModItems;
import com.purplepentagons.eltp.util.EventUtils;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FlintKnapEvent {
    public static void initialize() {
        UseBlockCallback.EVENT.register(
            EvenLessTreePunching.id("flint_break"), 
            (PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) -> {

            if (player.isSpectator()) {
                return ActionResult.PASS;
            }

            if(player.getMainHandStack().getItem() == Items.FLINT) {
                BlockPos blockPos = hitResult.getBlockPos();

                if (knappableBlock(world, blockPos)) {
                    Vec3d pos = hitResult.getPos();
                    int count = getShardCount();

                    if (!player.isCreative()) {
                        player.getMainHandStack().decrement(1);
                    }

                    if (count > 0) {
                        ItemStack shards = new ItemStack(ModItems.FLINT_SHARD, count);
                        EventUtils.spawnItem(world, shards, pos);
                    } else if(world instanceof ServerWorld serverWorld) {
                        ItemStackParticleEffect flintParticleEffect = new ItemStackParticleEffect(
                            ParticleTypes.ITEM,
                            new ItemStack(Items.FLINT)
                        );
                        
                        serverWorld.spawnParticles(
                            flintParticleEffect,
                            (double)pos.getX(), (double)pos.getY() + 0.1d, (double)pos.getZ(),
                            5,
                            0.15, 0.15, 0.15,
                            0.05
                        );
                    }

                    world.playSound(null, blockPos, SoundEvents.BLOCK_ANCIENT_DEBRIS_BREAK, SoundCategory.PLAYERS, 1.0f, 2.0f);
                    return ActionResult.SUCCESS;
                }
            }

            return ActionResult.PASS;
        });
    }

    private static int getShardCount() {
        return EventUtils.random.nextBetween(0, 2);
    }
    
    private static boolean knappableBlock(World world, BlockPos blockPos) {
        return world.getBlockState(blockPos).isIn(BlockTags.PICKAXE_MINEABLE);
    }
}
