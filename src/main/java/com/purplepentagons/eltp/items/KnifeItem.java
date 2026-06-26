package com.purplepentagons.eltp.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class KnifeItem extends SwordItem {
    public static final int KNIFE_ATTACK_DAMAGE = 2;
    public static final float KNIFE_ATTACK_SPEED = -2.2f;

    public KnifeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }
    
}
