package com.purplepentagons.eltp.entity.damage;

import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> BLEEDING_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, EvenLessTreePunching.id("bleeding"));

    public static void initialize() {

    }
}
