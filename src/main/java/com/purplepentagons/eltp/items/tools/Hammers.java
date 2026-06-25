package com.purplepentagons.eltp.items.tools;

import com.purplepentagons.eltp.items.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;

public class Hammers extends ModItems {
    private static final float HAMMER_ATTACK_DAMAGE = 4.0f;
    private static final float HAMMER_ATTACK_SPEED = -3.2f;

    public static final Item IRON_HAMMER = register(new HammerItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.IRON, HAMMER_ATTACK_DAMAGE, HAMMER_ATTACK_SPEED
    ))), "iron_hammer");
    public static final Item DIAMOND_HAMMER = register(new HammerItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, HAMMER_ATTACK_DAMAGE, HAMMER_ATTACK_SPEED
    ))), "diamond_hammer");
    public static final Item NETHERITE_HAMMER = register(new HammerItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, HAMMER_ATTACK_DAMAGE, HAMMER_ATTACK_SPEED
    ))), "netherite_hammer");
    public static final Item GOLDEN_HAMMER = register(new HammerItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.GOLD, HAMMER_ATTACK_DAMAGE, HAMMER_ATTACK_SPEED
    ))), "golden_hammer");

    public static void initialize() {

    }
}
