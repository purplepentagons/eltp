package com.purplepentagons.eltp.compatibility;

import java.util.Map;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.recipe.injection.SimpleRecipeInjection;
import com.purplepentagons.eltp.util.RecipeUtil;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class CopperAgeBackport {
    public static boolean LOADED = FabricLoader.getInstance().isModLoaded("copperagebackport");  

    public class Recipes {
        public static JsonObject COPPER_HAMMER = RecipeUtil.shapedRecipe(
        Lists.newArrayList(
            'X',
            '#'
        ),
        Lists.newArrayList(Identifier.of("minecraft:copper_ingot"),Identifier.of("minecraft:stick")),
        Lists.newArrayList("item", "item"),
        Lists.newArrayList(
            "XXX",
            "XXX",
            " # "
        ),
        EvenLessTreePunching.id("copper_hammer"),
        1,
        "minecraft:crafting_shaped"
        );

        public static SimpleRecipeInjection RECIPE_INJECTION = new SimpleRecipeInjection(LOADED, Map.of(
            EvenLessTreePunching.id("copper_hammer"), COPPER_HAMMER
        ));
    }
}
