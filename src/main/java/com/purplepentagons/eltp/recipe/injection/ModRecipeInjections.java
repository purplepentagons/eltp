package com.purplepentagons.eltp.recipe.injection;

import java.util.ArrayList;

import com.purplepentagons.eltp.compatibility.CopperAgeBackport;

public class ModRecipeInjections {

    public static ArrayList<SimpleRecipeInjection> SIMPLE_RECIPE_INJECTIONS = new ArrayList<SimpleRecipeInjection>();
    public static ArrayList<TransformRecipeInjection> TRANSFORM_RECIPE_INJECTIONS = new ArrayList<TransformRecipeInjection>();

    public static void initialize() {
        SIMPLE_RECIPE_INJECTIONS.add(CopperAgeBackport.Recipes.RECIPE_INJECTION);

        TransformRecipeInjection planksRecipeInjection = new PlanksRecipeInjection();

        TRANSFORM_RECIPE_INJECTIONS.add(planksRecipeInjection);
    }
}
