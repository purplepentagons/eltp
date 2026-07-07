package com.purplepentagons.eltp.event;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.serialization.JsonOps;
import com.purplepentagons.eltp.EvenLessTreePunching;
import com.purplepentagons.eltp.recipe.ModRecipes;
import com.purplepentagons.eltp.recipe.RecipeUtil;
import com.purplepentagons.eltp.recipe.ShapedToolDamagingRecipe;
import com.purplepentagons.eltp.util.compatibility.CopperAgeBackport;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.SynchronizeRecipesS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class InjectRecipesEvent {
    private static RecipeManager recipeManager;
    private static WrapperLookup registryLookup;
    private static RegistryOps<JsonElement> registryOps;

    // if something goes wrong it will error and stay null
    protected static RecipeEntry<Recipe<?>> parseRecipeEntry(Identifier identifier, JsonElement json) {
        RecipeEntry<Recipe<?>> recipeEntry = null;
        try {
            Recipe<?> recipe = Recipe.CODEC.parse(registryOps, json).getOrThrow(JsonParseException::new);
            recipeEntry = new RecipeEntry<Recipe<?>>(identifier, recipe);
        } catch(IllegalArgumentException | JsonParseException runtimeException) {
            EvenLessTreePunching.LOGGER.error("Parsing error loading recipe {}", identifier, runtimeException);
        }
        return recipeEntry;
    }

    public static void initialize() {
        Identifier eventIdentifier = EvenLessTreePunching.id("inject_recipes");

        ServerLifecycleEvents.SERVER_STARTED.register(eventIdentifier, InjectRecipesEvent::injectRecipes);
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register(eventIdentifier, InjectRecipesEvent::injectRecipesAndSync);
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

        EvenLessTreePunching.LOGGER.info("Added {} extra recipes. Removed {} recipes.", recipeEntryInjection.additionalRecipeEntries().size(), recipeEntryInjection.removedRecipeEntries.size());

        recipeManager.setRecipes(recipes);
    }

    private static RecipeEntryInjection getNewRecipes(Collection<RecipeEntry<?>> recipes) {
        RecipeEntryInjection recipeEntryInjection = new RecipeEntryInjection(
            new ArrayList<RecipeEntry<?>>(), 
            new ArrayList<RecipeEntry<?>>()
        );

        
        for (RecipeEntry<?> recipeEntry : recipes) {
            // eventually move all of these to a seperate object; i don't know what the general structure is yet
            addPlanksRecipeEntries(recipeEntryInjection, recipeEntry);
        }
        
        if (CopperAgeBackport.LOADED) {
            RecipeEntry<?> copperHammerRecipeEntry = parseRecipeEntry(EvenLessTreePunching.id("copper_hammer"), ModRecipes.COPPER_HAMMER);

            recipeEntryInjection.additionalRecipeEntries.add(copperHammerRecipeEntry);
        }

        return recipeEntryInjection;
    }

    private static void addPlanksRecipeEntries(RecipeEntryInjection recipeInjection, RecipeEntry<?> recipeEntry) {
        Recipe<?> recipe = recipeEntry.value();

        boolean validRecipe = 
        recipe.getResult(registryLookup).isIn(ItemTags.PLANKS) &&
        !(recipe instanceof ShapedToolDamagingRecipe) && 
        recipe.getIngredients().size() == 1;
        
        if(!validRecipe) {
            return;
        }
        
        Collection<RecipeEntry<?>> additionalPlanksRecipeEntries = new ArrayList<RecipeEntry<?>>();

        Identifier planksIdentifier = Registries.ITEM.getId(recipe.getResult(registryLookup).getItem());
        String planksName = planksIdentifier.getPath();

        for (ItemStack logStack : recipe.getIngredients().getFirst().getMatchingStacks()) {
            Identifier logIdentifier = Registries.ITEM.getId(logStack.getItem());
            String logName = logIdentifier.getPath();

            Identifier recipeIdentifier = EvenLessTreePunching.id("%s_from_%s_with_axe".formatted(planksName, logName));
            JsonElement recipeJsonElement = RecipeUtil.logPlanksRecipe(logIdentifier, planksIdentifier);
            RecipeEntry<?> planksRecipeEntry = parseRecipeEntry(recipeIdentifier, recipeJsonElement);
            additionalPlanksRecipeEntries.add(planksRecipeEntry);
        }


        recipeInjection.additionalRecipeEntries.addAll(additionalPlanksRecipeEntries);
        recipeInjection.removedRecipeEntries.add(recipeEntry);
    }

    private record RecipeEntryInjection(Collection<RecipeEntry<?>> removedRecipeEntries, Collection<RecipeEntry<?>> additionalRecipeEntries) {}
}
