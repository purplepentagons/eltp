package com.purplepentagons.eltp.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.serialization.JsonOps;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.recipe.injection.ModRecipeInjections;
import com.purplepentagons.eltp.recipe.injection.RecipeEntryInjection;
import com.purplepentagons.eltp.recipe.injection.SimpleRecipeInjection;
import com.purplepentagons.eltp.recipe.injection.TransformRecipeInjection;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.network.packet.s2c.play.SynchronizeRecipesS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class InjectRecipesEvent {
    private static RecipeManager recipeManager;
    private static WrapperLookup registryLookup;
    private static RegistryOps<JsonElement> registryOps;

    public static void initialize() {
        Identifier eventIdentifier = EvenLessTreePunching.id("inject_recipes");

        ServerLifecycleEvents.SERVER_STARTED.register(eventIdentifier, InjectRecipesEvent::injectRecipes);
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register(eventIdentifier, InjectRecipesEvent::injectRecipesAndSync);
    }

    // if something goes wrong it will error and stay null
    protected static RecipeEntry<Recipe<?>> parseRecipeEntry(Identifier identifier, JsonElement json, RegistryOps<JsonElement> registryOps) {
        RecipeEntry<Recipe<?>> recipeEntry = null;
        try {
            Recipe<?> recipe = Recipe.CODEC.parse(registryOps, json).getOrThrow(JsonParseException::new);
            recipeEntry = new RecipeEntry<Recipe<?>>(identifier, recipe);
        } catch(IllegalArgumentException | JsonParseException runtimeException) {
            EvenLessTreePunching.LOGGER.error("Parsing error loading recipe {}", identifier, runtimeException);
        }
        return recipeEntry;
    }


    private static void injectRecipesAndSync(MinecraftServer server, LifecycledResourceManager lifecycledResourceManager, boolean success) {
        injectRecipes(server);

        Collection<RecipeEntry<?>> recipes = recipeManager.values();
        
        SynchronizeRecipesS2CPacket syncPacket = new SynchronizeRecipesS2CPacket(recipes);

        for(ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            player.networkHandler.sendPacket(syncPacket);
        }
    }

    private static void injectRecipes(MinecraftServer server) {
        recipeManager = server.getRecipeManager();
        registryLookup = server.getRegistryManager();
        registryOps = registryLookup.getOps(JsonOps.INSTANCE);

        Collection<RecipeEntry<?>> recipes = new ArrayList<RecipeEntry<?>>();
        recipes.addAll(recipeManager.values());

        RecipeEntryInjection recipeEntryInjection = getNewRecipes(recipes);
        recipes.addAll(recipeEntryInjection.additionalRecipeEntries());
        recipes.removeAll(recipeEntryInjection.removedRecipeEntries());

        EvenLessTreePunching.LOGGER.info("Added {} extra recipes. Removed {} recipes.", recipeEntryInjection.additionalRecipeEntries().size(), recipeEntryInjection.removedRecipeEntries().size());

        recipeManager.setRecipes(recipes);
    }

    private static RecipeEntryInjection getNewRecipes(Collection<RecipeEntry<?>> recipes) {
        RecipeEntryInjection recipeEntryInjection = new RecipeEntryInjection(
            new ArrayList<RecipeEntry<?>>(), 
            new ArrayList<RecipeEntry<?>>()
        );

        
        for (RecipeEntry<?> recipeEntry : recipes) {
            // eventually move all of these to a seperate object; i don't know what the general structure is yet
            // addPlanksRecipeEntries(recipeEntryInjection, recipeEntry, registryOps);

            for(TransformRecipeInjection transformRecipeInjection : ModRecipeInjections.TRANSFORM_RECIPE_INJECTIONS) {
                boolean canTransform = transformRecipeInjection.canTransform(recipeEntry.value(), registryLookup);

                if (canTransform) {
                    transformRecipeInjection.transformRecipe(recipeEntry, registryLookup, registryOps, recipeEntryInjection);
                }
            }
        }

        for (SimpleRecipeInjection simpleRecipeInjection : ModRecipeInjections.SIMPLE_RECIPE_INJECTIONS) {
            if (!simpleRecipeInjection.canInject()) {
                continue;
            }
            
            Map<Identifier, JsonElement> jsonRecipes = simpleRecipeInjection.getRecipes();

            for (Map.Entry<Identifier, JsonElement> recipeMapEntry : jsonRecipes.entrySet()) {
                Identifier identifier = recipeMapEntry.getKey();
                JsonElement jsonRecipe = recipeMapEntry.getValue();

                RecipeEntry<?> recipeEntry = parseRecipeEntry(identifier, jsonRecipe, registryOps);
                recipeEntryInjection.additionalRecipeEntries().add(recipeEntry);
            }
        }

        return recipeEntryInjection;
    }
}
