package com.purplepentagons.eltp.effect;

import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class HyperviolentEffect extends StatusEffect {
    protected HyperviolentEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xFF0000);
        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, EvenLessTreePunching.id("hyperviolent_attack_damage"), 0.15f, Operation.ADD_MULTIPLIED_BASE);
        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, EvenLessTreePunching.id("hyperviolent_attack_speed"), 0.1f, Operation.ADD_MULTIPLIED_BASE);
    }
}
