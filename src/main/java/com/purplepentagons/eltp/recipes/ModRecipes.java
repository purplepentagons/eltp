package com.purplepentagons.eltp.recipes;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.util.Identifier;

public class ModRecipes {
    public static JsonObject COPPER_HAMMER = RecipeUtil.shapedRecipe(
        Lists.newArrayList(
            'X',
            '#'
        ),
        Lists.newArrayList(Identifier.of("minecraft:iron_ingot"),Identifier.of("minecraft:stick")),
        Lists.newArrayList("item", "item"),
        Lists.newArrayList(
            "XXX",
            "XXX",
            " # "
        ),
        EvenLessTreePunching.id("copper_hammer"),
        1
    );

    public static void initialize() {

    }
}
