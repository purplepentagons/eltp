package com.purplepentagons.eltp.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MarkedForDeathEffect extends StatusEffect {

    protected MarkedForDeathEffect() {
        // marked for death is used to apply bleeding, etc
        super(StatusEffectCategory.HARMFUL, 0xFF0000);
    }
    
}
