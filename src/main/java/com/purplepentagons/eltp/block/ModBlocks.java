package com.purplepentagons.eltp.block;

import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    private static final AbstractBlock.Settings ANDESITE_COBBLESTONE_SETTINGS = Blocks.COBBLESTONE.getSettings().mapColor(MapColor.STONE_GRAY);
    private static final AbstractBlock.Settings DIORITE_COBBLESTONE_SETTINGS = Blocks.COBBLESTONE.getSettings().mapColor(MapColor.OFF_WHITE);
    private static final AbstractBlock.Settings GRANITE_COBBLESTONE_SETTINGS = Blocks.COBBLESTONE.getSettings().mapColor(MapColor.DIRT_BROWN);

    public static final Block ANDESITE_COBBLESTONE = register(new Block(ANDESITE_COBBLESTONE_SETTINGS), "andesite_cobblestone");
    public static final Block DIORITE_COBBLESTONE = register(new Block(DIORITE_COBBLESTONE_SETTINGS), "diorite_cobblestone");
    public static final Block GRANITE_COBBLESTONE = register(new Block(GRANITE_COBBLESTONE_SETTINGS), "granite_cobblestone");

    public static final Block ANDESITE_COBBLESTONE_STAIRS = register(new StairsBlock(ANDESITE_COBBLESTONE.getDefaultState(), ANDESITE_COBBLESTONE_SETTINGS), "andesite_cobblestone_stairs");
    public static final Block DIORITE_COBBLESTONE_STAIRS = register(new StairsBlock(DIORITE_COBBLESTONE.getDefaultState(), DIORITE_COBBLESTONE_SETTINGS), "diorite_cobblestone_stairs");
    public static final Block GRANITE_COBBLESTONE_STAIRS = register(new StairsBlock(GRANITE_COBBLESTONE.getDefaultState(), GRANITE_COBBLESTONE_SETTINGS), "granite_cobblestone_stairs");

    public static final Block ANDESITE_COBBLESTONE_WALL = register(new WallBlock(ANDESITE_COBBLESTONE_SETTINGS), "andesite_cobblestone_wall");
    public static final Block DIORITE_COBBLESTONE_WALL = register(new WallBlock(DIORITE_COBBLESTONE_SETTINGS), "diorite_cobblestone_wall");
    public static final Block GRANITE_COBBLESTONE_WALL = register(new WallBlock(GRANITE_COBBLESTONE_SETTINGS), "granite_cobblestone_wall");

    public static final Block ANDESITE_COBBLESTONE_SLAB = register(new SlabBlock(ANDESITE_COBBLESTONE_SETTINGS), "andesite_cobblestone_slab");
    public static final Block DIORITE_COBBLESTONE_SLAB = register(new SlabBlock(DIORITE_COBBLESTONE_SETTINGS), "diorite_cobblestone_slab");
    public static final Block GRANITE_COBBLESTONE_SLAB = register(new SlabBlock(GRANITE_COBBLESTONE_SETTINGS), "granite_cobblestone_slab");


    private static Block register(Block block, String id) {
        Identifier blockID = EvenLessTreePunching.id(id);

        Registry.register(Registries.BLOCK, blockID, block);

        return block;
    }

    public static void initialize() {

    }
}
