package com.purplepentagons.eltp.recipe.injection;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.JsonElement;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.recipe.RecipeUtil;
import com.purplepentagons.eltp.recipe.ShapedToolDamagingRecipe;
import com.purplepentagons.eltp.util.compatibility.CopperAgeBackport;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModRecipeInjections {

    public static ArrayList<SimpleRecipeInjection> SIMPLE_RECIPE_INJECTIONS = new ArrayList<SimpleRecipeInjection>();
    public static ArrayList<TransformRecipeInjection> TRANSFORM_RECIPE_INJECTIONS = new ArrayList<TransformRecipeInjection>();

    public static void initialize() {
        SIMPLE_RECIPE_INJECTIONS.add(CopperAgeBackport.Recipes.RECIPE_INJECTION);

        TransformRecipeInjection planksRecipeInjection = new TransformRecipeInjection(ModRecipeInjections::planksTransformFunction, ModRecipeInjections::planksTransformPredicate);

        TRANSFORM_RECIPE_INJECTIONS.add(planksRecipeInjection);
    }

    private static boolean planksTransformPredicate(Recipe<?> recipe, WrapperLookup wrapperLookup) {
        return recipe.getResult(wrapperLookup).isIn(ItemTags.PLANKS) &&
            !(recipe instanceof ShapedToolDamagingRecipe) && 
            recipe.getIngredients().size() == 1;
    }

    private static void planksTransformFunction(RecipeEntry<?> recipeEntry, WrapperLookup wrapperLookup, RegistryOps<JsonElement> registryOps, RecipeEntryInjection recipeEntryInjection) {
        Recipe<?> recipe = recipeEntry.value();
        
        Collection<RecipeEntry<?>> additionalPlanksRecipeEntries = new ArrayList<RecipeEntry<?>>();

        Identifier planksIdentifier = Registries.ITEM.getId(recipe.getResult(wrapperLookup).getItem());
        String planksName = planksIdentifier.getPath();

        for (ItemStack logStack : recipe.getIngredients().getFirst().getMatchingStacks()) {
            Identifier logIdentifier = Registries.ITEM.getId(logStack.getItem());
            String logName = logIdentifier.getPath();

            Identifier recipeIdentifier = EvenLessTreePunching.id("%s_from_%s_with_axe".formatted(planksName, logName));
            JsonElement recipeJsonElement = RecipeUtil.logPlanksRecipe(logIdentifier, planksIdentifier);
            RecipeEntry<?> planksRecipeEntry = RecipeUtil.parseRecipeEntry(recipeIdentifier, recipeJsonElement, registryOps);
            additionalPlanksRecipeEntries.add(planksRecipeEntry);
        }

        recipeEntryInjection.additionalRecipeEntries().addAll(additionalPlanksRecipeEntries);
        recipeEntryInjection.removedRecipeEntries().add(recipeEntry);
    }
}
