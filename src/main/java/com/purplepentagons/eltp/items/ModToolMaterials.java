package com.purplepentagons.eltp.items;

import com.google.common.base.Suppliers;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public enum ModToolMaterials implements ToolMaterial {
   FLINT(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 1.5F, 0.0F, 5, () -> Ingredient.ofItems(ModItems.FLINT_SHARD, Items.FLINT));

   private final TagKey<Block> inverseTag;
   private final int itemDurability;
   private final float miningSpeed;
   private final float attackDamage;
   private final int enchantability;
   private final Supplier<Ingredient> repairIngredient;

   private ModToolMaterials(final TagKey<Block> inverseTag, final int itemDurability, final float miningSpeed, final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
      this.inverseTag = inverseTag;
      this.itemDurability = itemDurability;
      this.miningSpeed = miningSpeed;
      this.attackDamage = attackDamage;
      this.enchantability = enchantability;
      this.repairIngredient = Suppliers.memoize(repairIngredient::get);
   }

    public int getDurability() {
      return this.itemDurability;
   }

   public float getMiningSpeedMultiplier() {
      return this.miningSpeed;
   }

   public float getAttackDamage() {
      return this.attackDamage;
   }

   public TagKey<Block> getInverseTag() {
      return this.inverseTag;
   }

   public int getEnchantability() {
      return this.enchantability;
   }

   public Ingredient getRepairIngredient() {
      return (Ingredient)this.repairIngredient.get();
   }
}
