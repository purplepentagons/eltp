package com.purplepentagons.eltp.items.tools;

import com.purplepentagons.eltp.items.ModItems;
import com.purplepentagons.eltp.utils.ModToolMaterials;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;

public class Knives extends ModItems {
    private static final int KNIFE_ATTACK_DAMAGE = 2;
    private static final float KNIFE_ATTACK_SPEED = -2.2f;

    public static final Item IRON_KNIFE = register(new KnifeItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.IRON, KNIFE_ATTACK_DAMAGE, KNIFE_ATTACK_SPEED
    ))), "iron_knife");
    public static final Item DIAMOND_KNIFE = register(new KnifeItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, KNIFE_ATTACK_DAMAGE, KNIFE_ATTACK_SPEED
    ))), "diamond_knife");
    public static final Item NETHERITE_KNIFE = register(new KnifeItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, KNIFE_ATTACK_DAMAGE, KNIFE_ATTACK_SPEED
    ))), "netherite_knife");
    public static final Item GOLDEN_KNIFE = register(new KnifeItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.GOLD, KNIFE_ATTACK_DAMAGE, KNIFE_ATTACK_SPEED
    ))), "golden_knife");
     public static final Item FLINT_KNIFE = register(new KnifeItem(ModToolMaterials.FLINT, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ModToolMaterials.FLINT, KNIFE_ATTACK_DAMAGE, KNIFE_ATTACK_SPEED
    ))), "flint_knife");

    public static void initialize() {

    }
}
