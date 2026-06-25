package com.purplepentagons.eltp.items.tools;

import com.purplepentagons.eltp.items.ModItems;
import com.purplepentagons.eltp.utils.ModToolMaterials;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class FlintTools extends ModItems {
    public static final Item FLINT_SHOVEL = register(new ShovelItem(ModToolMaterials.FLINT, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.FLINT, 1.5F, -3.0F))), "flint_shovel");

    public static void initialize() {
        
    }
}
