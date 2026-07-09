package com.purplepentagons.eltp.util;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.purplepentagons.eltp.EvenLessTreePunching;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryOps;
import net.minecraft.util.Identifier;

public class RecipeUtil {
    public static RecipeEntry<Recipe<?>> parseRecipeEntry(Identifier identifier, JsonElement json, RegistryOps<JsonElement> registryOps) {
        RecipeEntry<Recipe<?>> recipeEntry = null;
        try {
            Recipe<?> recipe = Recipe.CODEC.parse(registryOps, json).getOrThrow(JsonParseException::new);
            recipeEntry = new RecipeEntry<Recipe<?>>(identifier, recipe);
        } catch(IllegalArgumentException | JsonParseException runtimeException) {
            EvenLessTreePunching.LOGGER.error("Parsing error loading recipe {}", identifier, runtimeException);
        }
        return recipeEntry;
    }

    public record UnparsedIngredient(Identifier identifier, String type) {
        public static UnparsedIngredient of(String identifierString, String type) {
            return new UnparsedIngredient(Identifier.of(identifierString), type);
        } 

        public static UnparsedIngredient of(Identifier identifier, String type) {
            return new UnparsedIngredient(identifier, type);
        }

        public static UnparsedIngredient item(Identifier identifier) {
            return new UnparsedIngredient(identifier, "item");
        }

        public static UnparsedIngredient item(String identifierString) {
            return new UnparsedIngredient(Identifier.of(identifierString), "item");
        }

        public static UnparsedIngredient tag(Identifier identifier) {
            return new UnparsedIngredient(identifier, "tag");
        }

        public static UnparsedIngredient tag(String identifierString) {
            return new UnparsedIngredient(Identifier.of(identifierString), "tag");
        }
    }

    public static JsonObject shapedRecipe(Map<Character, UnparsedIngredient> ingredients, ArrayList<String> pattern, Identifier output, int count, String recipeType) {
        JsonObject json = new JsonObject();
        json.addProperty("type", recipeType);

        JsonObject keyList = new JsonObject();
        JsonObject ingredientKey;
        for (Map.Entry<Character, UnparsedIngredient> ingredient : ingredients.entrySet()) {
            ingredientKey = new JsonObject();
            UnparsedIngredient unparsedIngredient = ingredient.getValue();
            Character ingredientKeyCharacter = ingredient.getKey();
            ingredientKey.addProperty(unparsedIngredient.type(), unparsedIngredient.identifier().toString());
            keyList.add(ingredientKeyCharacter + "", ingredientKey);
        }
        
        JsonArray patternArray = new JsonArray();
        for (String patternRow : pattern) {
            patternArray.add(patternRow);
        }
        json.add("pattern", patternArray);

        json.add("key", keyList);

        JsonObject result = new JsonObject();
        result.addProperty("id", output.toString());
        result.addProperty("count", count);

        json.add("result", result);

        return json;
    }

    public static JsonObject shapelessRecipe(ArrayList<UnparsedIngredient> ingredients, ArrayList<String> type, Identifier output, int count, String recipeType) {
        JsonObject json = new JsonObject();

        json.addProperty("type", recipeType);

        JsonArray ingredientsJson = new JsonArray();
        JsonObject ingredientKey;

        for(int i = 0; i < ingredients.size(); ++i) {
            ingredientKey = new JsonObject();
            ingredientKey.addProperty(type.get(i), ingredients.get(i).toString());
            ingredientsJson.add(ingredientKey);
        }

        JsonObject result = new JsonObject();
        result.addProperty("id", output.toString());
        result.addProperty("count", count);

        json.add("result", result);

        return json;
    }
}
