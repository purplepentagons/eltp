package com.purplepentagons.eltp.item;

import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.block.ModBlocks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item COPPER_HAMMER = register(new HammerItem(ModToolMaterials.COPPER, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ModToolMaterials.COPPER, HammerItem.BASE_ATTACK_DAMAGE, HammerItem.BASE_ATTACK_SPEED
    ))), "copper_hammer");
    public static final Item IRON_HAMMER = register(new HammerItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.IRON, HammerItem.BASE_ATTACK_DAMAGE, HammerItem.BASE_ATTACK_SPEED
    ))), "iron_hammer");
    public static final Item DIAMOND_HAMMER = register(new HammerItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, HammerItem.BASE_ATTACK_DAMAGE, HammerItem.BASE_ATTACK_SPEED
    ))), "diamond_hammer");
    public static final Item NETHERITE_HAMMER = register(new HammerItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, HammerItem.BASE_ATTACK_DAMAGE, HammerItem.BASE_ATTACK_SPEED
    ))), "netherite_hammer");
    public static final Item GOLDEN_HAMMER = register(new HammerItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(HammerItem.createAttributeModifiers(
        ToolMaterials.GOLD, HammerItem.BASE_ATTACK_DAMAGE, HammerItem.BASE_ATTACK_SPEED
    ))), "golden_hammer");

    public static final Item COPPER_KNIFE = register(new KnifeItem(ModToolMaterials.COPPER, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ModToolMaterials.COPPER, KnifeItem.BASE_ATTACK_DAMAGE, KnifeItem.BASE_ATTACK_SPEED
    ))), "copper_knife");
    public static final Item IRON_KNIFE = register(new KnifeItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.IRON, KnifeItem.BASE_ATTACK_DAMAGE, KnifeItem.BASE_ATTACK_SPEED
    ))), "iron_knife");
    public static final Item DIAMOND_KNIFE = register(new KnifeItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, KnifeItem.BASE_ATTACK_DAMAGE, KnifeItem.BASE_ATTACK_SPEED
    ))), "diamond_knife");
    public static final Item NETHERITE_KNIFE = register(new KnifeItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, KnifeItem.BASE_ATTACK_DAMAGE, KnifeItem.BASE_ATTACK_SPEED
    ))), "netherite_knife");
    public static final Item GOLDEN_KNIFE = register(new KnifeItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ToolMaterials.GOLD, KnifeItem.BASE_ATTACK_DAMAGE, KnifeItem.BASE_ATTACK_SPEED
    ))), "golden_knife");
    public static final Item FLINT_KNIFE = register(new KnifeItem(ModToolMaterials.FLINT, new Item.Settings().attributeModifiers(KnifeItem.createAttributeModifiers(
        ModToolMaterials.FLINT, KnifeItem.BASE_ATTACK_DAMAGE, KnifeItem.BASE_ATTACK_SPEED
    ))), "flint_knife");

    public static final Item COPPER_SAW = register(new SawItem(ModToolMaterials.COPPER, new Item.Settings().attributeModifiers(MiningToolItem.createAttributeModifiers(
        ModToolMaterials.COPPER, SawItem.BASE_ATTACK_DAMAGE, SawItem.BASE_ATTACK_SPEED
    ))), "copper_saw");
    public static final Item IRON_SAW = register(new SawItem(ToolMaterials.IRON, new Item.Settings().attributeModifiers(MiningToolItem.createAttributeModifiers(
        ToolMaterials.IRON, SawItem.BASE_ATTACK_DAMAGE, SawItem.BASE_ATTACK_SPEED
    ))), "iron_saw");
    public static final Item DIAMOND_SAW = register(new SawItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(MiningToolItem.createAttributeModifiers(
        ToolMaterials.DIAMOND, SawItem.BASE_ATTACK_DAMAGE, SawItem.BASE_ATTACK_SPEED
    ))), "diamond_saw");
    public static final Item GOLDEN_SAW = register(new SawItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(MiningToolItem.createAttributeModifiers(
        ToolMaterials.GOLD, SawItem.BASE_ATTACK_DAMAGE, SawItem.BASE_ATTACK_SPEED
    ))), "golden_saw");
    public static final Item NETHERITE_SAW = register(new SawItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(MiningToolItem.createAttributeModifiers(
        ToolMaterials.NETHERITE, SawItem.BASE_ATTACK_DAMAGE, SawItem.BASE_ATTACK_SPEED
    ))), "netherite_saw");


    public static final Item FLINT_SHOVEL = register(new ShovelItem(ModToolMaterials.FLINT, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.FLINT, 1.5F, -3.0F))), "flint_shovel");
    public static final Item FLINT_PICKAXE = register(new PickaxeItem(ModToolMaterials.FLINT, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.FLINT, 1.0F, -2.8F))), "flint_pickaxe");
    public static final Item FLINT_HOE = register(new HoeItem(ModToolMaterials.FLINT, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.FLINT, 0.0F, -3.0F))), "flint_hoe");
    public static final Item FLINT_AXE = register(new AxeItem(ModToolMaterials.FLINT, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.FLINT, 4.0F, -3.2F))), "flint_axe");

    public static final Item IRON_CHUNK = register(new Item(new Item.Settings()), "iron_chunk");
    public static final Item GOLD_CHUNK = register(new Item(new Item.Settings()), "gold_chunk");
    public static final Item COPPER_CHUNK = register(new Item(new Item.Settings()), "copper_chunk");

    public static final Item FLINT_SHARD = register(new Item(new Item.Settings()), "flint_shard");
    public static final Item PLANT_STRING = register(new Item(new Item.Settings()), "plant_string");
    public static final Item PLANT_FIBER = register(new Item(new Item.Settings()), "plant_fiber");

    public static final BlockItem ANDESITE_COBBLESTONE = registerBlockItem(ModBlocks.ANDESITE_COBBLESTONE);
    public static final BlockItem DIORITE_COBBLESTONE = registerBlockItem(ModBlocks.DIORITE_COBBLESTONE);
    public static final BlockItem GRANITE_COBBLESTONE = registerBlockItem(ModBlocks.GRANITE_COBBLESTONE);

    public static final BlockItem ANDESITE_COBBLESTONE_STAIRS = registerBlockItem(ModBlocks.ANDESITE_COBBLESTONE_STAIRS);
    public static final BlockItem DIORITE_COBBLESTONE_STAIRS = registerBlockItem(ModBlocks.DIORITE_COBBLESTONE_STAIRS);
    public static final BlockItem GRANITE_COBBLESTONE_STAIRS = registerBlockItem(ModBlocks.GRANITE_COBBLESTONE_STAIRS);

    public static final BlockItem ANDESITE_COBBLESTONE_WALL = registerBlockItem(ModBlocks.ANDESITE_COBBLESTONE_WALL);
    public static final BlockItem DIORITE_COBBLESTONE_WALL = registerBlockItem(ModBlocks.DIORITE_COBBLESTONE_WALL);
    public static final BlockItem GRANITE_COBBLESTONE_WALL = registerBlockItem(ModBlocks.GRANITE_COBBLESTONE_WALL);

    public static final BlockItem ANDESITE_COBBLESTONE_SLAB = registerBlockItem(ModBlocks.ANDESITE_COBBLESTONE_SLAB);
    public static final BlockItem DIORITE_COBBLESTONE_SLAB = registerBlockItem(ModBlocks.DIORITE_COBBLESTONE_SLAB);
    public static final BlockItem GRANITE_COBBLESTONE_SLAB = registerBlockItem(ModBlocks.GRANITE_COBBLESTONE_SLAB);
    

    private static final RegistryKey<ItemGroup> ELTP_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), EvenLessTreePunching.id("item_group"));
    private static final ItemGroup ELTP_ITEM_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(IRON_HAMMER))
        .displayName(Text.translatable("creativeTab.eltp"))
        .build();

    private static <T extends Item> T register(T item, String id) {
        Identifier itemID = EvenLessTreePunching.id(id);

        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        ItemGroupEvents.modifyEntriesEvent(ELTP_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(registeredItem);
        });

        return item;
    }

    private static BlockItem registerBlockItem(Block block) {
        String id = Registries.BLOCK.getId(block).getPath();

        return register(new BlockItem(block, new Item.Settings()), id);
    }

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, ELTP_ITEM_GROUP_KEY, ELTP_ITEM_GROUP);
    }
}