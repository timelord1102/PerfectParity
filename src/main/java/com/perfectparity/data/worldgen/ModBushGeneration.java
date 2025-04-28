package com.perfectparity.data.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBushGeneration {
    public static void generateBushes() {
        // Bushes
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST,
                Biomes.FOREST, Biomes.PLAINS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_FOREST,
                        Biomes.RIVER, Biomes.FROZEN_RIVER),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:patch_bush")));

        // Firefly Bushes
        BiomeModifications.addFeature(BiomeSelectors.excludeByKey(
                        Biomes.GROVE, Biomes.CHERRY_GROVE, Biomes.DEEP_DARK, Biomes.DRIPSTONE_CAVES, Biomes.LUSH_CAVES,
                        Biomes.JAGGED_PEAKS, Biomes.FROZEN_PEAKS, Biomes.STONY_PEAKS, Biomes.SNOWY_SLOPES, Biomes.MEADOW,
                        Biomes.SWAMP, Biomes.DESERT, Biomes.NETHER_WASTES, Biomes.SOUL_SAND_VALLEY, Biomes.BASALT_DELTAS,
                        Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST, Biomes.THE_END, Biomes.END_BARRENS, Biomes.END_HIGHLANDS,
                        Biomes.END_MIDLANDS, Biomes.SMALL_END_ISLANDS
                ), GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:patch_firefly_bush_near_water")));

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP), GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:patch_firefly_bush_swamp")));

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.SWAMP), GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:patch_firefly_bush_near_water_swamp")));


        // Dry Grass
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DESERT), GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:patch_dry_grass_desert")));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BADLANDS, Biomes.WOODED_BADLANDS, Biomes.ERODED_BADLANDS), GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.parse("minecraft:patch_dry_grass_badlands")));
    }
}
