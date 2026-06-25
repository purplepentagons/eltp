package com.purplepentagons.eltp.items;

import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.items.tools.FlintTools;
import com.purplepentagons.eltp.items.tools.Hammers;
import com.purplepentagons.eltp.items.tools.Knives;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final RegistryKey<ItemGroup> ELTP_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), EvenLessTreePunching.id("item_group"));
    public static final ItemGroup ELTP_ITEM_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(Hammers.IRON_HAMMER))
        .displayName(Text.translatable("creativeTab.eltp"))
        .build();

    public static Item register(Item item, String id) {
        Identifier itemID = EvenLessTreePunching.id(id);

        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        ItemGroupEvents.modifyEntriesEvent(ELTP_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(registeredItem);
        });

        return registeredItem;
    }

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, ELTP_ITEM_GROUP_KEY, ELTP_ITEM_GROUP);

        Materials.initialize();
        Hammers.initialize();
        Knives.initialize();
        FlintTools.initialize();
    }
}