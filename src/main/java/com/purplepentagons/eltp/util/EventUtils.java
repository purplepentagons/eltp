package com.purplepentagons.eltp.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class EventUtils {
    public static Random random = Random.create();

    public static void spawnItem(World world, ItemStack stack, Vec3d pos) {
        ItemEntity item = EntityType.ITEM.create(world);
        item.setStack(stack);
        item.setPosition(pos);
        item.addVelocity(new Vec3d(random.nextFloat()*0.3 - 0.15, 0.25, random.nextFloat()*0.3 - 0.15));
        world.spawnEntity(item);
    }

    public static boolean playerCanCrit(PlayerEntity player, LivingEntity target) {
        return (player.getAttackCooldownProgress(0.5f) > 0.9f) && (player.fallDistance > 0.0F) && !player.isOnGround() && !player.isClimbing() && !player.isTouchingWater() && !player.hasStatusEffect(StatusEffects.BLINDNESS) && !player.hasVehicle() && target instanceof LivingEntity && !player.isSprinting();
    }
}
