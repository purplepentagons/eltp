package com.purplepentagons.eltp.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class ShapedToolDamagingRecipe implements CraftingRecipe, ToolDamagingRecipe {
    final String group;
    public final CraftingRecipeCategory category;
    public final RawShapedRecipe raw;
    public final ItemStack result;
    public final Boolean showNotification;

    public ShapedToolDamagingRecipe(String group, CraftingRecipeCategory category, RawShapedRecipe raw, ItemStack result, Boolean showNotification) {
        this.group = group;
        this.category = category;
        this.raw = raw;
        this.result = result;
        this.showNotification = showNotification;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(final CraftingRecipeInput input) {
        return ToolDamagingRecipe.super.getRemainder(input);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SHAPED_TOOL_DAMAGE_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return this.category;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, WrapperLookup lookup) {
        return this.getResult(lookup).copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= this.raw.getWidth() && height >= this.raw.getHeight();
    }

    @Override
    public ItemStack getResult(WrapperLookup registriesLookup) {
        return this.result;    
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        return this.raw.matches(input);
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.raw.getIngredients();
    }

    public int getWidth() {
        return this.raw.getWidth();
    }

    public int getHeight() {
        return this.raw.getHeight();
    }

}
