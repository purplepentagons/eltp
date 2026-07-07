package com.purplepentagons.eltp.util;

import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.block.Block;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> FIST_BREAKABLE = createTag("fist_breakable");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, EvenLessTreePunching.id(name));
        }
    }
    
    public static class Items {
        public static final TagKey<Item> HAMMERS = createTag("hammers");
        public static final TagKey<Item> KNIVES = createTag("knives");
        public static final TagKey<Item> SAWS = createTag("saws");

        public static final TagKey<Item> CRAFTING_TOOLS = createTag("crafting_tools");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, EvenLessTreePunching.id(name));
        }
    }
    
    public static class Damage {
        public static final TagKey<DamageType> BLEEDS = createTag("bleeds");

        private static TagKey<DamageType> createTag(String name) {
            return TagKey.of(RegistryKeys.DAMAGE_TYPE, EvenLessTreePunching.id(name));
        }
    }
}
