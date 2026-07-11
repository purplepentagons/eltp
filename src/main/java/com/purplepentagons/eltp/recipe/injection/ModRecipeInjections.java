package com.purplepentagons.eltp.recipe.injection;

import java.util.Map;

import com.google.common.collect.Lists;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.compatibility.CopperAgeBackport;
import com.purplepentagons.eltp.util.RecipeUtil;
import com.purplepentagons.eltp.util.RecipeUtil.UnparsedIngredient;

public class ModRecipeInjections {

    public static SimpleRecipeInjection PLANT_FIBER_FLOWER_INJECTION = new SimpleRecipeInjection(Map.of(
        EvenLessTreePunching.id("plant_fiber_from_small_flowers"), RecipeUtil.shapelessRecipe(
            Lists.newArrayList(
                UnparsedIngredient.tag("eltp:knives"),
                UnparsedIngredient.tag("minecraft:small_flowers")   
            ), 
            EvenLessTreePunching.id("plant_fiber"), 1,
            "eltp:shapeless_tool_damaging"),
        EvenLessTreePunching.id("plant_fiber_from_large_flowers"), RecipeUtil.shapelessRecipe(
            Lists.newArrayList(
                UnparsedIngredient.tag("eltp:knives"),
                UnparsedIngredient.tag("minecraft:tall_flowers")   
            ),
            EvenLessTreePunching.id("plant_fiber"), 2,
            "eltp:shapeless_tool_damaging")
    ));

    public static void initialize() {
        SimpleRecipeInjection.inject(CopperAgeBackport.Recipes.RECIPE_INJECTION);
        SimpleRecipeInjection.inject(PLANT_FIBER_FLOWER_INJECTION);

        TransformRecipeInjection.inject(new PlanksRecipeInjection());
    }
}
