package com.purplepentagons.eltp.utils;

import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> HAND_BREAKABLE = createTag("hand_breakable");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(Registries.BLOCK.getKey(), EvenLessTreePunching.id(name));
        }
    }
    
    public static class Items {
        public static final TagKey<Item> HAMMERS = createTag("hammers");
        public static final TagKey<Item> KNIVES = createTag("knives");
        public static final TagKey<Item> SAWS = createTag("saws");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registries.ITEM.getKey(), EvenLessTreePunching.id(name));
        }
    }
}
