package com.purplepentagons.eltp.recipe.injection;

import java.util.Collection;

import net.minecraft.recipe.RecipeEntry;

public record RecipeEntryInjection(Collection<RecipeEntry<?>> removedRecipeEntries, Collection<RecipeEntry<?>> additionalRecipeEntries) {}
