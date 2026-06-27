package com.purplepentagons.eltp.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.gson.JsonElement;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.recipes.ModRecipes;
import com.purplepentagons.eltp.utils.ModCompatibility;

import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "apply", at = @At(value = "HEAD"))
    public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        if (ModCompatibility.COPPERAGEBACKPORT_LOADED) {
            map.put(EvenLessTreePunching.id("copper_hammer"), ModRecipes.COPPER_HAMMER);
        }
    }

}
