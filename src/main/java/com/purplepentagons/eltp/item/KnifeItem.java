package com.purplepentagons.eltp.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class KnifeItem extends SwordItem {
    public static final int BASE_ATTACK_DAMAGE = 2;
    public static final float BASE_ATTACK_SPEED = -2.2f;

    public KnifeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }
 
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }
}
