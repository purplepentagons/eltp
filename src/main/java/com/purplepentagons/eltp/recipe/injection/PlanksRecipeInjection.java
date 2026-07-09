package com.purplepentagons.eltp.recipe.injection;

import java.util.Map;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.recipe.ShapedToolDamagingRecipe;
import com.purplepentagons.eltp.util.RecipeUtil;
import com.purplepentagons.eltp.util.RecipeUtil.UnparsedIngredient;

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

    private static JsonObject logPlanksRecipe(Identifier logItem, Identifier planksKey, String toolKey, int count) {
        return RecipeUtil.shapedRecipe(
            Map.of(
                'F', UnparsedIngredient.tag(toolKey),
                'S', UnparsedIngredient.item(logItem)
            ),
            Lists.newArrayList(
                "F",
                "S"
            ),
            planksKey, count,
            "eltp:shaped_tool_damaging"
        );
    }

    private static void planksTransformFunction(RecipeEntry<?> recipeEntry, WrapperLookup wrapperLookup, RegistryOps<JsonElement> registryOps, RecipeEntryInjection recipeEntryInjection) {
        Recipe<?> recipe = recipeEntry.value();
        
        Identifier planksIdentifier = Registries.ITEM.getId(recipe.getResult(wrapperLookup).getItem());
        String planksName = planksIdentifier.getPath();

        for (ItemStack logStack : recipe.getIngredients().getFirst().getMatchingStacks()) {
            Identifier logIdentifier = Registries.ITEM.getId(logStack.getItem());
            String logName = logIdentifier.getPath();

            Identifier axeRecipeIdentifier = EvenLessTreePunching.id("%s_from_%s_with_axe".formatted(planksName, logName));
            Identifier sawRecipeIdentifier = EvenLessTreePunching.id("%s_from_%s_with_saw".formatted(planksName, logName));
            JsonElement axeRecipeJsonElement = logPlanksRecipe(logIdentifier, planksIdentifier, "minecraft:axes", 2);
            JsonElement sawRecipeJsonElement = logPlanksRecipe(logIdentifier, planksIdentifier, "eltp:saws", 4);
            RecipeEntry<?> axeRecipeEntry = RecipeUtil.parseRecipeEntry(axeRecipeIdentifier, axeRecipeJsonElement, registryOps);
            RecipeEntry<?> sawRecipeEntry = RecipeUtil.parseRecipeEntry(sawRecipeIdentifier, sawRecipeJsonElement, registryOps);
            recipeEntryInjection.additionalRecipeEntries().add(axeRecipeEntry);
            recipeEntryInjection.additionalRecipeEntries().add(sawRecipeEntry);
        }

        recipeEntryInjection.removedRecipeEntries().add(recipeEntry);
    }

    private static boolean planksTransformPredicate(Recipe<?> recipe, WrapperLookup wrapperLookup) {
        return recipe.getResult(wrapperLookup).isIn(ItemTags.PLANKS) &&
            !(recipe instanceof ShapedToolDamagingRecipe) && 
            recipe.getIngredients().size() == 1;
    }
}
