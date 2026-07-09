package com.purplepentagons.eltp.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class SawItem extends AxeItem {
    public static final float BASE_ATTACK_DAMAGE = 1.0f;
    public static final float BASE_ATTACK_SPEED = -3.0f;

    public SawItem(ToolMaterial material, Item.Settings settings) {
        super(material, settings);
    }
}
