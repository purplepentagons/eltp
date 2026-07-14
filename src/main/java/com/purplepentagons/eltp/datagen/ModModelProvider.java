package com.purplepentagons.eltp.datagen;

import java.util.Collection;

import com.google.common.collect.Lists;
import com.purplepentagons.eltp.block.ModBlocks;
import com.purplepentagons.eltp.item.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void registerItemModels(ItemModelGenerator itemModelGenerator, Collection<Item> items, Model model) {
        items.forEach(
            (item) -> {
                itemModelGenerator.register(item, model);
            }
        );
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool andesiteCobblestoneTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ANDESITE_COBBLESTONE);
        BlockStateModelGenerator.BlockTexturePool dioriteCobblestoneTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DIORITE_COBBLESTONE);
        BlockStateModelGenerator.BlockTexturePool graniteCobblestoneTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GRANITE_COBBLESTONE);

        andesiteCobblestoneTexturePool.stairs(ModBlocks.ANDESITE_COBBLESTONE_STAIRS);
        andesiteCobblestoneTexturePool.wall(ModBlocks.ANDESITE_COBBLESTONE_WALL);
        andesiteCobblestoneTexturePool.slab(ModBlocks.ANDESITE_COBBLESTONE_SLAB);

        dioriteCobblestoneTexturePool.stairs(ModBlocks.DIORITE_COBBLESTONE_STAIRS);
        dioriteCobblestoneTexturePool.wall(ModBlocks.DIORITE_COBBLESTONE_WALL);
        dioriteCobblestoneTexturePool.slab(ModBlocks.DIORITE_COBBLESTONE_SLAB);

        graniteCobblestoneTexturePool.stairs(ModBlocks.GRANITE_COBBLESTONE_STAIRS);
        graniteCobblestoneTexturePool.wall(ModBlocks.GRANITE_COBBLESTONE_WALL);
        graniteCobblestoneTexturePool.slab(ModBlocks.GRANITE_COBBLESTONE_SLAB);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // handheld maces
        registerItemModels(itemModelGenerator, Lists.newArrayList(
            ModItems.COPPER_HAMMER,
            ModItems.IRON_HAMMER,
            ModItems.GOLDEN_HAMMER,
            ModItems.DIAMOND_HAMMER,
            ModItems.NETHERITE_HAMMER
        ), Models.HANDHELD_MACE);

        // normal items (generated)
        registerItemModels(itemModelGenerator, Lists.newArrayList(
            ModItems.COPPER_CHUNK,
            ModItems.IRON_CHUNK,
            ModItems.GOLD_CHUNK,
            ModItems.PLANT_FIBER,
            ModItems.PLANT_STRING,
            ModItems.FLINT_SHARD
        ), Models.GENERATED);

        // tools (handheld)
        registerItemModels(itemModelGenerator, Lists.newArrayList(
            ModItems.FLINT_AXE,
            ModItems.FLINT_HOE,
            ModItems.FLINT_KNIFE,
            ModItems.FLINT_PICKAXE,
            ModItems.FLINT_SHOVEL,

            ModItems.COPPER_KNIFE,
            ModItems.IRON_KNIFE,
            ModItems.GOLDEN_KNIFE,
            ModItems.DIAMOND_KNIFE,
            ModItems.NETHERITE_KNIFE,

            ModItems.COPPER_SAW,
            ModItems.IRON_SAW,
            ModItems.GOLDEN_SAW,
            ModItems.DIAMOND_SAW,
            ModItems.NETHERITE_SAW
        ), Models.HANDHELD);

    }
}
