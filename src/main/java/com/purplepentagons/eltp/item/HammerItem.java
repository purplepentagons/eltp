package com.purplepentagons.eltp.item;

import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HammerItem extends ToolItem {
    public static final float BASE_ATTACK_DAMAGE = 4.0f;
    public static final float BASE_ATTACK_SPEED = -3.2f;

    public HammerItem(ToolMaterial material, Item.Settings settings) {
        super(material, settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers (ToolMaterial material, float attackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
        .add(
            EntityAttributes.GENERIC_ATTACK_DAMAGE, 
            new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, attackDamage + material.getAttackDamage(), Operation.ADD_VALUE),
            AttributeModifierSlot.MAINHAND
        )
        .add(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, Operation.ADD_VALUE),
            AttributeModifierSlot.MAINHAND
        ).build();
    }
    
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }
    
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        if (shouldDealAdditionalDamage(attacker)) {
            World world = target.getEntityWorld();
            
            BlockPos pos = target.getSteppingPos();
            attacker.onLanding();
            world.playSound(null, pos, SoundEvents.ITEM_MACE_SMASH_GROUND, SoundCategory.PLAYERS, 1.0f, 1.0f);
            if (target.getEntityWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                    new BlockStateParticleEffect(
                        ParticleTypes.DUST_PILLAR,
                        serverWorld.getBlockState(pos.down(1))
                    ),
                    (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(),
                    384,
                    1.75, 0.5, 1.75,
                    0.002
                );
            }
        }
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        Entity source = damageSource.getSource();
        if (source instanceof LivingEntity livingEntity) {
            if (shouldDealAdditionalDamage(livingEntity)) {
                float fallDistance = livingEntity.fallDistance;

                return (float)(Math.min(1000, 1.0f + Math.pow(fallDistance/2 - 0.75f, 2.0f/3.0f) * 3));
            } else {
                return 0.0F;
            }
        } else {
            return 0.0F;
        }
    }

    public static boolean shouldDealAdditionalDamage(LivingEntity attacker) {
        return attacker.fallDistance > 1.5f && !attacker.isFallFlying();
    }
}

