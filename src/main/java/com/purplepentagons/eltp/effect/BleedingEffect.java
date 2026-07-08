package com.purplepentagons.eltp.effect;

import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.entity.damage.ModDamageTypes;
import com.purplepentagons.eltp.util.EventUtils;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;

public class BleedingEffect extends StatusEffect {

    protected BleedingEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFF0000, new BlockStateParticleEffect(
            ParticleTypes.BLOCK,
            Blocks.NETHER_WART_BLOCK.getDefaultState()
        ));
        this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, EvenLessTreePunching.id("bleeding_movement_speed"), -0.2f, Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(EntityAttributes.GENERIC_JUMP_STRENGTH, EvenLessTreePunching.id("bleeding_jump_strength"), -0.05f, Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, EvenLessTreePunching.id("bleeding_block_break_speed"), -0.2f, Operation.ADD_MULTIPLIED_BASE);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        if (duration % 40 == 0) {
            if (EventUtils.random.nextBoolean()) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        DamageSource damageSource = new DamageSource(
            entity.getWorld()
            .getRegistryManager().
            getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE)
            .getOrThrow(ModDamageTypes.BLEEDING_DAMAGE)
        );
        entity.damage(damageSource, (float)(amplifier + 1));
        return true;
    }
}
