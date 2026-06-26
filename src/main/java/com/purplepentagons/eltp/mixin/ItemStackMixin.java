package com.purplepentagons.eltp.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @ModifyReturnValue(method = "isIn", at = @At("RETURN"))
    private boolean checkModTags(boolean original, TagKey<Item> tag) {
        tag.
    }
}
