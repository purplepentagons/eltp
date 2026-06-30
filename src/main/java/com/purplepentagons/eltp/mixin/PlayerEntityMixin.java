package com.purplepentagons.eltp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.purplepentagons.eltp.util.ModTags;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Shadow
    private PlayerInventory inventory;

    @ModifyReturnValue(method = "getBlockBreakingSpeed", at = @At(value = "RETURN"))
    public float restrictBlocks(float original, @Local(argsOnly = true) BlockState state) {
        ItemStack mainHandItem = this.inventory.getMainHandStack();
        
        if (mainHandItem.getItem().isCorrectForDrops(mainHandItem, state)) {
            return original;
        }

        if (mainHandItem.getMiningSpeedMultiplier(state) > 1.0f) {
            return original;
        }

        if (state.isIn(ModTags.Blocks.FIST_BREAKABLE)) {
            return original;
        }

        if (!state.isToolRequired() && 
            !state.isIn(BlockTags.SHOVEL_MINEABLE) &&
            !state.isIn(BlockTags.PICKAXE_MINEABLE) &&
            !state.isIn(BlockTags.AXE_MINEABLE) &&
            !state.isIn(BlockTags.HOE_MINEABLE) &&
            !state.isIn(BlockTags.SWORD_EFFICIENT)
        ) {
            return original;
        }
        
        if (state.getBlock().getHardness() == 0.0f) {
            return original;
        }
        
        return 0.0f;
    }
}
