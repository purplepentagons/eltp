package purplepentagons.eltp.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import purplepentagons.eltp.EvenLessTreePunching;

public class Items {
    public static Item register(Item item, String id) {
        Identifier itemID = EvenLessTreePunching.id(id);

        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        return registeredItem;
    }

    public static void initialize() {
        Chunks.initialize();
    }
}