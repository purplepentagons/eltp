package com.purplepentagons.eltp.recipe.injection;

import java.util.Map;

import com.google.common.collect.Lists;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.compatibility.CopperAgeBackport;
import com.purplepentagons.eltp.util.RecipeUtil;
import com.purplepentagons.eltp.util.RecipeUtil.UnparsedIngredient;

import net.minecraft.util.Identifier;

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

    public static SimpleRecipeInjection STICKS_FROM_LOGS_INJECTION = new SimpleRecipeInjection(Map.of(
        EvenLessTreePunching.id("sticks_from_logs_with_axe"), RecipeUtil.shapedRecipe(
            Map.of(
                'L', UnparsedIngredient.tag("minecraft:logs"),
                'X', UnparsedIngredient.tag("minecraft:axes")
            ),
            Lists.newArrayList(
                "XL"
            ),
            Identifier.of("minecraft:stick"),
            4,
            "eltp:shaped_tool_damaging"
        ),
        EvenLessTreePunching.id("sticks_from_logs_with_saw"), RecipeUtil.shapedRecipe(
            Map.of(
                'L', UnparsedIngredient.tag("minecraft:logs"),
                'X', UnparsedIngredient.tag("eltp:saws")
            ),
            Lists.newArrayList(
                "XL"
            ),
            Identifier.of("minecraft:stick"),
            8,
            "eltp:shaped_tool_damaging"
        )
    ));

    public static void initialize() {
        SimpleRecipeInjection.inject(CopperAgeBackport.Recipes.RECIPE_INJECTION);
        SimpleRecipeInjection.inject(PLANT_FIBER_FLOWER_INJECTION);
        SimpleRecipeInjection.inject(STICKS_FROM_LOGS_INJECTION);

        TransformRecipeInjection.inject(new PlanksRecipeInjection());
    }
}
