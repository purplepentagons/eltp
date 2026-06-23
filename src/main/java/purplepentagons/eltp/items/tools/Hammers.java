package purplepentagons.eltp.items.tools;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import purplepentagons.eltp.items.Items;

public class Hammers extends Items {
    public static final Item IRON_HAMMER = register(new HammerItem(ToolMaterials.IRON, 4.0f), "iron_hammer");
    public static final Item DIAMOND_HAMMER = register(new HammerItem(ToolMaterials.DIAMOND, 4.0f), "diamond_hammer");
    public static final Item NETHERITE_HAMMER = register(new HammerItem(ToolMaterials.NETHERITE, 4.0f), "netherite_hammer");
    // i do not have a gold hammer texture yet
    // public static final Item GOLD_HAMMER = register(new HammerItem(ToolMaterials.GOLD, 4.0f), "gold_hammer");

    // later detect if copper exists
    // public static final Item IRON_HAMMER = register(new HammerItem(ToolMaterials.COPPER, 4.0f), "copper_hammer");



    public static void initialize() {

    }
}
