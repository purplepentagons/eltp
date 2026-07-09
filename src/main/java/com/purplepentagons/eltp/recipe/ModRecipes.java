package com.purplepentagons.eltp.recipe;

import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.recipe.injection.ModRecipeInjections;

import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes {   
    public static final RecipeType<ShapedToolDamagingRecipe> SHAPED_TOOL_DAMAGE_RECIPE_TYPE = Registry.register(
        Registries.RECIPE_TYPE,
        EvenLessTreePunching.id("shaped_tool_damaging"),
        new RecipeType<ShapedToolDamagingRecipe>() {}
    );

    public static final ShapedToolDamagingRecipeSerializer SHAPED_TOOL_DAMAGE_RECIPE_SERIALIZER = Registry.register(
        Registries.RECIPE_SERIALIZER,
        EvenLessTreePunching.id("shaped_tool_damaging"),
        new ShapedToolDamagingRecipeSerializer()
    );

    public static final RecipeType<ShapelessToolDamagingRecipe> SHAPELESS_TOOL_DAMAGE_RECIPE_TYPE = Registry.register(
        Registries.RECIPE_TYPE, 
        EvenLessTreePunching.id("shapeless_tool_damaging"),
        new RecipeType<ShapelessToolDamagingRecipe>() {}
    );

    public static final ShapelessToolDamagingRecipeSerializer SHAPELESS_TOOL_DAMAGE_RECIPE_SERIALIZER = Registry.register(
        Registries.RECIPE_SERIALIZER, 
        EvenLessTreePunching.id("shapeless_tool_damaging"), 
        new ShapelessToolDamagingRecipeSerializer()
    );

    public static void initialize() {
        ModRecipeInjections.initialize();
    }
}
