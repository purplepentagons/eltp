package com.purplepentagons.eltp.items;

import com.purplepentagons.eltp.EvenLessTreePunching;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item IRON_HAMMER = register(new HammerItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.IRON, HammerItem.HAMMER_ATTACK_DAMAGE, HammerItem.HAMMER_ATTACK_SPEED
    ))), "iron_hammer");
    public static final Item DIAMOND_HAMMER = register(new HammerItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, HammerItem.HAMMER_ATTACK_DAMAGE, HammerItem.HAMMER_ATTACK_SPEED
    ))), "diamond_hammer");
    public static final Item NETHERITE_HAMMER = register(new HammerItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, HammerItem.HAMMER_ATTACK_DAMAGE, HammerItem.HAMMER_ATTACK_SPEED
    ))), "netherite_hammer");
    public static final Item GOLDEN_HAMMER = register(new HammerItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.GOLD, HammerItem.HAMMER_ATTACK_DAMAGE, HammerItem.HAMMER_ATTACK_SPEED
    ))), "golden_hammer");

    public static final Item IRON_KNIFE = register(new KnifeItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.IRON, KnifeItem.KNIFE_ATTACK_DAMAGE, KnifeItem.KNIFE_ATTACK_SPEED
    ))), "iron_knife");
    public static final Item DIAMOND_KNIFE = register(new KnifeItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, KnifeItem.KNIFE_ATTACK_DAMAGE, KnifeItem.KNIFE_ATTACK_SPEED
    ))), "diamond_knife");
    public static final Item NETHERITE_KNIFE = register(new KnifeItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, KnifeItem.KNIFE_ATTACK_DAMAGE, KnifeItem.KNIFE_ATTACK_SPEED
    ))), "netherite_knife");
    public static final Item GOLDEN_KNIFE = register(new KnifeItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.GOLD, KnifeItem.KNIFE_ATTACK_DAMAGE, KnifeItem.KNIFE_ATTACK_SPEED
    ))), "golden_knife");
     public static final Item FLINT_KNIFE = register(new KnifeItem(ModToolMaterials.FLINT, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ModToolMaterials.FLINT, KnifeItem.KNIFE_ATTACK_DAMAGE, KnifeItem.KNIFE_ATTACK_SPEED
    ))), "flint_knife");

    public static final Item FLINT_SHOVEL = register(new ShovelItem(ModToolMaterials.FLINT, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.FLINT, 1.5F, -3.0F))), "flint_shovel");

    public static final Item IRON_CHUNK = register(new Item(new Item.Settings()), "iron_chunk");
    public static final Item GOLD_CHUNK = register(new Item(new Item.Settings()), "gold_chunk");
    public static final Item COPPER_CHUNK = register(new Item(new Item.Settings()), "copper_chunk");

    public static final Item FLINT_SHARD = register(new Item(new Item.Settings()), "flint_shard");

    public static final RegistryKey<ItemGroup> ELTP_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), EvenLessTreePunching.id("item_group"));
    public static final ItemGroup ELTP_ITEM_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(IRON_HAMMER))
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
    }
}