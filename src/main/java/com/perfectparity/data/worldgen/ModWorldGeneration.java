package com.perfectparity.data.worldgen;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        ModBushGeneration.generateBushes();
        ModTreeGeneration.generateTrees();
        ModLeafLitterGeneration.generateLitter();
    }
}
