package com.perfectparity.data.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModLeafLitterGeneration {
    public static void generateLitter() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DARK_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:patch_leaf_litter")));

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:trees_birch_and_oak_leaf_litter")));

        if (!FabricLoader.getInstance().isModLoaded("wilderwild")) {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:wildflowers_birch_forest")));
        }

        if (!FabricLoader.getInstance().isModLoaded("wilderwild")) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.MEADOW),
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:wildflowers_meadow")));
        }
    }
}
