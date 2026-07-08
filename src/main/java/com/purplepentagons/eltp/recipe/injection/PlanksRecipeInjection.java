package com.purplepentagons.eltp.recipe.injection;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.recipe.ShapedToolDamagingRecipe;
import com.purplepentagons.eltp.util.RecipeUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class PlanksRecipeInjection extends TransformRecipeInjection {

    public PlanksRecipeInjection() {
        super(PlanksRecipeInjection::planksTransformFunction, PlanksRecipeInjection::planksTransformPredicate);
    }


    private static JsonObject logPlanksRecipe(Identifier logItem, Identifier planksKey) {
        return RecipeUtil.toolTransformSingleItemRecipe(
            Identifier.of("minecraft:axes"), 
            logItem,
            Lists.newArrayList(
                "F ",
                "S "
            ),
            planksKey,
            2
        );
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
            JsonElement recipeJsonElement = logPlanksRecipe(logIdentifier, planksIdentifier);
            RecipeEntry<?> planksRecipeEntry = RecipeUtil.parseRecipeEntry(recipeIdentifier, recipeJsonElement, registryOps);
            additionalPlanksRecipeEntries.add(planksRecipeEntry);
        }

        recipeEntryInjection.additionalRecipeEntries().addAll(additionalPlanksRecipeEntries);
        recipeEntryInjection.removedRecipeEntries().add(recipeEntry);
    }

    private static boolean planksTransformPredicate(Recipe<?> recipe, WrapperLookup wrapperLookup) {
        return recipe.getResult(wrapperLookup).isIn(ItemTags.PLANKS) &&
            !(recipe instanceof ShapedToolDamagingRecipe) && 
            recipe.getIngredients().size() == 1;
    }
}
