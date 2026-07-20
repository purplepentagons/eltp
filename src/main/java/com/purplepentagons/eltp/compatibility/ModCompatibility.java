package com.purplepentagons.eltp.compatibility;

import com.purplepentagons.eltp.recipe.injection.SimpleRecipeInjection;

public class ModCompatibility {
    public static void initialize() {
        SimpleRecipeInjection.inject(CopperAgeBackport.Recipes.RECIPE_INJECTION);
    }
}
