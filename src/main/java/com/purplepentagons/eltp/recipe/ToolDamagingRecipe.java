package com.purplepentagons.eltp.recipe;

import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.util.ModTags;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.util.collection.DefaultedList;

public interface ToolDamagingRecipe {
    default public DefaultedList<ItemStack> getRemainder(final CraftingRecipeInput input) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(input.getSize(), ItemStack.EMPTY);

        for(int i = 0; i < defaultedList.size(); ++i) {
            ItemStack stack = input.getStackInSlot(i);
            Item item = stack.getItem();
            
            if(stack.isIn(ModTags.Items.CRAFTING_TOOLS)) {
                EvenLessTreePunching.LOGGER.info("Tool in eltp:tool_damaging recipe");
                defaultedList.set(i, getToolRecipeRemainder(stack));
            } else if(item.hasRecipeRemainder()) {
                defaultedList.set(i, new ItemStack(item.getRecipeRemainder()));
            } 
        }

        return defaultedList;
    }
    
    private ItemStack getToolRecipeRemainder(ItemStack stack) {
        ItemStack damagedStack = stack.copy();
        int newDamage = damagedStack.getDamage() + 1;
        if (newDamage >= damagedStack.getMaxDamage()) {
            return ItemStack.EMPTY;
        } else {
            damagedStack.setDamage(newDamage);
        }
        return damagedStack;
    }
}
