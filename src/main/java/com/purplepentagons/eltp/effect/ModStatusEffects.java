package com.purplepentagons.eltp.effect;

import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final RegistryEntry<StatusEffect> BLEEDING = register(new BleedingEffect(), "bleeding");
    public static final RegistryEntry<StatusEffect> HYPERVIOLENT = register(new HyperviolentEffect(), "hyperviolent");
    public static final RegistryEntry<StatusEffect> MARKED_FOR_DEATH = register(new MarkedForDeathEffect(), "marked_for_death");

    public static RegistryEntry<StatusEffect> register(StatusEffect statusEffect, String id) {
        Identifier statusEffectID = EvenLessTreePunching.id(id);

        RegistryEntry<StatusEffect> registeredStatusEffect = Registry.registerReference(Registries.STATUS_EFFECT, statusEffectID, statusEffect);

        return registeredStatusEffect;
    }

    public static void initialize() {

    }
}
