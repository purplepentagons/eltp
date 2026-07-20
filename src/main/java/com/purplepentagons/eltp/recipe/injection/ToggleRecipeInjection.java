package com.purplepentagons.eltp.recipe.injection;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.util.Identifier;

public class ToggleRecipeInjection {
    private static Collection<ToggleRecipeInjection> toggleRecipeInjections = new ArrayList<ToggleRecipeInjection>();

    private final boolean willToggleOff;
    private final Collection<Identifier> identifiers = new ArrayList<Identifier>();

    public static ToggleRecipeInjection inject(ToggleRecipeInjection injection) {
        toggleRecipeInjections.add(injection);
        return injection;
    }

    public static Collection<ToggleRecipeInjection> getInjections() {
        return toggleRecipeInjections;
    }

    public ToggleRecipeInjection(
        Collection<Identifier> identifiers,
        boolean willToggleOff
    ) {
        this.identifiers.addAll(identifiers);
        this.willToggleOff = willToggleOff;
    }

    public boolean willToggleOff() {
        return willToggleOff;
    }
    
    public boolean willKeepOn() {
        return !willToggleOff;
    }

    public Collection<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void addIdentifier(Identifier identifier) {
        identifiers.add(identifier);
    }
}
