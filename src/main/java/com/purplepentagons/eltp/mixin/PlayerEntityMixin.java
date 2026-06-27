package com.purplepentagons.eltp.mixin;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.purplepentagons.eltp.utils.ModTags;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

@Debug(export = true)
@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Shadow(prefix = "target")
    private PlayerInventory inventory;

    @ModifyReturnValue(method = "getBlockBreakingSpeed", at = @At(value = "RETURN"))
    public float restrictBlocks(float original, @Local(argsOnly = true) BlockState block) {
        ItemStack mainHandItem = this.inventory.getMainHandStack();
        if (mainHandItem.getItem().isCorrectForDrops(mainHandItem, block)) {
            return original;
        }

        if (block.isIn(ModTags.Blocks.HAND_BREAKABLE)) {
            return original;
        }
        
        return 0.0f;
    }

}
