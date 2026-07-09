package com.purplepentagons.eltp.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class ShapelessToolDamagingRecipe implements CraftingRecipe, ToolDamagingRecipe {
    protected final String group;
    protected final CraftingRecipeCategory category;
    protected final ItemStack result;
    protected final DefaultedList<Ingredient> ingredients;
    private final ShapelessRecipe rawShapelessEquivalent; 

    public ShapelessToolDamagingRecipe(String group, CraftingRecipeCategory category, ItemStack result, DefaultedList<Ingredient> ingredients) {
        this.group = group;
        this.category = category;
        this.result = result;
        this.ingredients = ingredients;
        this.rawShapelessEquivalent = new ShapelessRecipe(group, category, result, ingredients);
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, WrapperLookup lookup) {
        return this.result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return this.rawShapelessEquivalent.fits(width, height);
    }

    @Override
    public ItemStack getResult(WrapperLookup registriesLookup) {
       return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SHAPELESS_TOOL_DAMAGE_RECIPE_SERIALIZER;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        return this.rawShapelessEquivalent.matches(input, world);
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return this.category;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingRecipeInput input) {
        return ToolDamagingRecipe.super.getRemainder(input);
    }
    
}
