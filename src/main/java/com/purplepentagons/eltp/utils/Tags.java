package com.purplepentagons.eltp.utils;

import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

public class Tags {
    public static class Items {
        public static final TagKey<Item> HAMMERS = createTag("hammers");
        public static final TagKey<Item> KNIVES = createTag("knives");
        public static final TagKey<Item> SAWS = createTag("saws");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registries.ITEM.getKey(), EvenLessTreePunching.id(name));
        }
    }
}
