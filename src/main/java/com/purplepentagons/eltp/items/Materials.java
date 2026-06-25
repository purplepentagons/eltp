package com.purplepentagons.eltp.items;

import net.minecraft.item.Item;

public class Materials extends ModItems {
    public static final Item IRON_CHUNK = register(new Item(new Item.Settings()), "iron_chunk");
    public static final Item GOLD_CHUNK = register(new Item(new Item.Settings()), "gold_chunk");
    public static final Item COPPER_CHUNK = register(new Item(new Item.Settings()), "copper_chunk");

    public static final Item FLINT_SHARD = register(new Item(new Item.Settings()), "flint_shard");

    public static void initialize() {

    }
}
