package com.purplepentagons.eltp.recipe;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.util.Identifier;

public class RecipeUtil {
    public static JsonObject shapedRecipe(ArrayList<Character> keys, ArrayList<Identifier> items, ArrayList<String> type, ArrayList<String> pattern, Identifier output, int count, String recipeType) {
        JsonObject json = new JsonObject();
        json.addProperty("type", recipeType);

        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < pattern.size(); ++i) {
            jsonArray.add(pattern.get(i));
        }
        json.add("pattern", jsonArray);

        JsonObject individualKey;
        JsonObject keyList = new JsonObject();

        for (int i = 0; i < keys.size(); ++i) {
            individualKey = new JsonObject();
            individualKey.addProperty(type.get(i), items.get(i).toString());
            keyList.add(keys.get(i) + "", individualKey);
        }

        json.add("key", keyList);

        JsonObject result = new JsonObject();
        result.addProperty("id", output.toString());
        result.addProperty("count", count);

        json.add("result", result);

        return json;
    }

    private static JsonObject toolTransformSingleItemRecipe(Identifier firstKey, Identifier secondKey, ArrayList<String> pattern, Identifier resultKey, int count) {
        return shapedRecipe(
            Lists.newArrayList(
                'F',
                'S'
            ),
            Lists.newArrayList(firstKey, secondKey),
            Lists.newArrayList("tag", "item"),
            pattern,
            resultKey,
            count,
            "eltp:shaped_tool_damaging"
        );
    }

    public static JsonObject logPlanksRecipe(Identifier logItem, Identifier planksKey) {
        return toolTransformSingleItemRecipe(
            Identifier.of("minecraft:axes"), 
            logItem,
            Lists.newArrayList(
                "F ",
                "S "
            ),
            planksKey,
            2
        );
    }
}
