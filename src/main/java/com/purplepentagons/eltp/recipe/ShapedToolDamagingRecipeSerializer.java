package com.purplepentagons.eltp.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;

public class ShapedToolDamagingRecipeSerializer implements RecipeSerializer<ShapedToolDamagingRecipe> {
    public static final MapCodec<ShapedToolDamagingRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
        Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.getGroup()),
        CraftingRecipeCategory.CODEC.fieldOf("category").orElse(CraftingRecipeCategory.MISC).forGetter(r -> r.category),
        RawShapedRecipe.CODEC.forGetter((r) -> r.raw),
        ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(r -> r.result),
        Codec.BOOL.optionalFieldOf("show_notification", true).forGetter(r -> r.showNotification)
    ).apply(instance, ShapedToolDamagingRecipe::new));

    public static final PacketCodec<RegistryByteBuf, ShapedToolDamagingRecipe> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING, ShapedToolDamagingRecipe::getGroup,
        CraftingRecipeCategory.PACKET_CODEC, recipe -> recipe.category,
        RawShapedRecipe.PACKET_CODEC, recipe -> recipe.raw,
        ItemStack.PACKET_CODEC, recipe -> recipe.result,
        PacketCodecs.BOOL, recipe -> recipe.showNotification,
        ShapedToolDamagingRecipe::new
    );

    @Override
    public MapCodec<ShapedToolDamagingRecipe> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, ShapedToolDamagingRecipe> packetCodec() {
        return PACKET_CODEC;
    }
}
