package com.purplepentagons.eltp.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {    
    public static JsonObject COPPER_HAMMER = RecipeUtil.shapedRecipe(
        Lists.newArrayList(
            'X',
            '#'
        ),
        Lists.newArrayList(Identifier.of("minecraft:copper_ingot"),Identifier.of("minecraft:stick")),
        Lists.newArrayList("item", "item"),
        Lists.newArrayList(
            "XXX",
            "XXX",
            " # "
        ),
        EvenLessTreePunching.id("copper_hammer"),
        1,
        "minecraft:crafting_shaped"
    );

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

    public static void initialize() {
        
    }
}
