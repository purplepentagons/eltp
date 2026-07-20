package com.purplepentagons.eltp.recipe.injection;

import java.util.Map;

import com.google.common.collect.Lists;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.util.RecipeUtil;
import com.purplepentagons.eltp.util.RecipeUtil.UnparsedIngredient;

import net.minecraft.util.Identifier;

public class ModRecipeInjections {

    public static ToggleRecipeInjection PLANT_FIBER_FLOWER_INJECTION = new ToggleRecipeInjection(Lists.newArrayList(
        EvenLessTreePunching.id("plant_fiber_from_tall_flowers"),
        EvenLessTreePunching.id("plant_fiber_from_small_flowers")
    ), false);

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
        SimpleRecipeInjection.inject(STICKS_FROM_LOGS_INJECTION);

        ToggleRecipeInjection.inject(PLANT_FIBER_FLOWER_INJECTION);

        TransformRecipeInjection.inject(new PlanksRecipeInjection());
    }
} 
