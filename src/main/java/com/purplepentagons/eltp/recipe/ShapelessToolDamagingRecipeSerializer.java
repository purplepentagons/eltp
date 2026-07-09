package com.purplepentagons.eltp.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.collection.DefaultedList;

public class ShapelessToolDamagingRecipeSerializer implements RecipeSerializer<ShapelessToolDamagingRecipe> {
    public static final MapCodec<ShapelessToolDamagingRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
        Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.getGroup()),
        CraftingRecipeCategory.CODEC.fieldOf("category").orElse(CraftingRecipeCategory.MISC).forGetter(r -> r.category),
        ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(r -> r.result),
        Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").flatXmap((ingredients) -> {
            Ingredient[] ingredientsArray = ingredients.stream().filter((ingredient) -> !ingredient.isEmpty()).toArray((i) -> new Ingredient[i]);
            if (ingredientsArray.length == 0) {
                return DataResult.error(() -> "No ingredients for shapeless tool damaging recipe");
            } else {
                return ingredientsArray.length > 9 ? DataResult.error(() -> "Too many ingredients for shapeless tool damaging recipe") :  DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredientsArray));
            }
        }, DataResult::success).forGetter((recipe) -> recipe.ingredients)
    ).apply(instance, ShapelessToolDamagingRecipe::new));

    public static final PacketCodec<RegistryByteBuf, ShapelessToolDamagingRecipe> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING, ShapelessToolDamagingRecipe::getGroup,
        CraftingRecipeCategory.PACKET_CODEC, recipe -> recipe.category,
        ItemStack.PACKET_CODEC, recipe -> recipe.result,
        Ingredient.PACKET_CODEC.collect(PacketCodecs.toCollection(DefaultedList::ofSize)), recipe -> recipe.ingredients,
        ShapelessToolDamagingRecipe::new
    );

    @Override
    public MapCodec<ShapelessToolDamagingRecipe> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, ShapelessToolDamagingRecipe> packetCodec() {
        return PACKET_CODEC;
    }
}
