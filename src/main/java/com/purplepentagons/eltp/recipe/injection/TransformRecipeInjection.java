package com.purplepentagons.eltp.recipe.injection;

import java.util.ArrayList;

import com.google.gson.JsonElement;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class TransformRecipeInjection {
    public static ArrayList<TransformRecipeInjection> transformRecipeInjections = new ArrayList<TransformRecipeInjection>();

    public static TransformRecipeInjection inject(TransformRecipeInjection injection) {
        transformRecipeInjections.add(injection);
        return injection;
    }

    public static ArrayList<TransformRecipeInjection> getInjections() {
        return transformRecipeInjections;
    }

    @FunctionalInterface
    public interface RecipeTransformFunction {
       void transform(RecipeEntry<?> recipe, WrapperLookup wrapperLookup, RegistryOps<JsonElement> registryOps, RecipeEntryInjection recipeEntryInjection);
    }

    @FunctionalInterface
    public interface RecipeTransformPredicate {
        boolean test(Recipe<?> recipe, WrapperLookup wrapperLookup);
    }

    protected RecipeTransformFunction transformationFunction;
    protected RecipeTransformPredicate transformationPredicate;

    public TransformRecipeInjection(
        RecipeTransformFunction transformationFunction, 
        RecipeTransformPredicate transformationPredicate
    ) {
        this.transformationFunction = transformationFunction;
        this.transformationPredicate = transformationPredicate;
    }

    public boolean canTransform(Recipe<?> recipe, WrapperLookup wrapperLookup) {
        return transformationPredicate.test(recipe, wrapperLookup);
    }

    public void transformRecipe(RecipeEntry<?> recipeEntry, WrapperLookup wrapperLookup, RegistryOps<JsonElement> registryOps, RecipeEntryInjection recipeEntryInjection) {
        transformationFunction.transform(recipeEntry, wrapperLookup, registryOps, recipeEntryInjection);
    }
}
