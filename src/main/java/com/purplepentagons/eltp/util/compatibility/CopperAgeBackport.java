package com.purplepentagons.eltp.util.compatibility;

import net.fabricmc.loader.api.FabricLoader;

public class CopperAgeBackport {
    public static boolean LOADED = FabricLoader.getInstance().isModLoaded("copperagebackport");   
}
