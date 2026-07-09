package com.purplepentagons.eltp.compatibility;

import java.util.Map;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.recipe.injection.SimpleRecipeInjection;
import com.purplepentagons.eltp.util.RecipeUtil;
import com.purplepentagons.eltp.util.RecipeUtil.UnparsedIngredient;

import net.fabricmc.loader.api.FabricLoader;

public class CopperAgeBackport {
    public static boolean LOADED = FabricLoader.getInstance().isModLoaded("copperagebackport");  

    public class Recipes {
        private static Map<Character, UnparsedIngredient> toolIngredientsKey = Map.of(
            'X', UnparsedIngredient.item("minecraft:copper_ingot"),
            '#', UnparsedIngredient.item("minecraft:stick")
        );

        public static JsonObject COPPER_HAMMER = RecipeUtil.shapedRecipe(
            toolIngredientsKey,
            Lists.newArrayList(
                "XXX",
                "XXX",
                " # "
            ),
            EvenLessTreePunching.id("copper_hammer"), 1,
            "minecraft:crafting_shaped"
        );

        public static JsonObject COPPER_KNIFE = RecipeUtil.shapedRecipe(
            toolIngredientsKey,
            Lists.newArrayList(
                "X",
                "#"
            ),
            EvenLessTreePunching.id("copper_knife"), 1,
            "minecraft:crafting_shaped"
        );

        public static JsonObject COPPER_SAW = RecipeUtil.shapedRecipe(
            toolIngredientsKey,
            Lists.newArrayList(
                "  #",
                " #X",
                "#X "
            ),
            EvenLessTreePunching.id("copper_saw"), 1,
            "minecraft:crafting_shaped"
        );

        public static SimpleRecipeInjection RECIPE_INJECTION = new SimpleRecipeInjection(LOADED, Map.of(
            EvenLessTreePunching.id("copper_hammer"), COPPER_HAMMER,
            EvenLessTreePunching.id("copper_knife"), COPPER_KNIFE,
            EvenLessTreePunching.id("copper_saw"), COPPER_SAW
        ));
    }
}
