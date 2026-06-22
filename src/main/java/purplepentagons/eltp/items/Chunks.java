package purplepentagons.eltp.items;

import net.minecraft.item.Item;

public class Chunks extends Items {
    public static final Item IRON_CHUNK = register(new Item(new Item.Settings()), "iron_chunk");
    public static final Item GOLD_CHUNK = register(new Item(new Item.Settings()), "gold_chunk");
    public static final Item COPPER_CHUNK = register(new Item(new Item.Settings()), "copper_chunk");

    public static void initialize() {

    }
}
