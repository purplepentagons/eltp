package com.purplepentagons.eltp.event;

import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.items.ModItems;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class FlintKnapEvent {
    public static Random random = Random.create();

    public static void initialize() {
        UseBlockCallback.EVENT.register(EvenLessTreePunching.id("flint_break"), (PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) -> {
            Vec3d pos = hitResult.getPos();
            if(player.getMainHandStack().getItem() == Items.FLINT) {
                if (world.getBlockState(hitResult.getBlockPos()).isIn(BlockTags.PICKAXE_MINEABLE)) {
                    int count = getShardCount();
                    player.getMainHandStack().decrement(1);
                    if (count > 0) {
                        ItemEntity item = EntityType.ITEM.create(world);
                        item.setStack(new ItemStack(ModItems.FLINT_SHARD, count));
                        item.setPosition(pos);
                        item.addVelocity(new Vec3d(random.nextFloat()*0.15, 0.25, random.nextFloat()*0.15));
                        world.spawnEntity(item);
                    }
                    world.playSound(null, hitResult.getBlockPos(), SoundEvents.BLOCK_ANCIENT_DEBRIS_BREAK, SoundCategory.PLAYERS, 1.0f, 2.0f);
                    return ActionResult.SUCCESS;
                }
            }

            return ActionResult.PASS;
        });
    }

    private static int getShardCount() {
        return random.nextBetween(0, 2);
    }
}
