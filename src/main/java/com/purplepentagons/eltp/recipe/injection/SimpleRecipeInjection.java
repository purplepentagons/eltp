package com.purplepentagons.eltp.recipe.injection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;

import net.minecraft.util.Identifier;

public class SimpleRecipeInjection {
    private static ArrayList<SimpleRecipeInjection> simpleRecipeInjections = new ArrayList<SimpleRecipeInjection>();

    public static SimpleRecipeInjection inject(SimpleRecipeInjection injection) {
        simpleRecipeInjections.add(injection);
        return injection;
    }

    public static ArrayList<SimpleRecipeInjection> getInjections() {
        return simpleRecipeInjections;
    }

    protected Map<Identifier, JsonElement> jsonRecipes;
    protected boolean canInject;

    public SimpleRecipeInjection(boolean canInject, Map<Identifier, JsonElement> jsonRecipes) {
        this(canInject);
        this.jsonRecipes.putAll(jsonRecipes);
    }

    public SimpleRecipeInjection(Map<Identifier, JsonElement> jsonRecipes) {
        this();
        this.jsonRecipes.putAll(jsonRecipes);
    }

    public SimpleRecipeInjection(boolean canInject) {
        this();
        this.canInject = canInject;
    }

    public SimpleRecipeInjection() {
        this.canInject = true;
        this.jsonRecipes = new HashMap<Identifier, JsonElement>(Map.of());
    }

    public Map<Identifier, JsonElement> getRecipes() {
        return this.jsonRecipes;
    }

    public void addRecipe(Identifier identifier, JsonElement jsonElement) {
        this.jsonRecipes.put(identifier, jsonElement);
    }

    public boolean canInject() {
        return this.canInject;
    }
}
