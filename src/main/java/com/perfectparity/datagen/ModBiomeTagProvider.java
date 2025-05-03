package com.perfectparity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends FabricTagProvider<Biome> {
    public static final TagKey<Biome> SPAWNS_WARM_VARIANT_FARM_ANIMALS = create("spawns_warm_variant_farm_animals");
    public static final TagKey<Biome> SPAWNS_COLD_VARIANT_FARM_ANIMALS = create("spawns_cold_variant_farm_animals");

    public ModBiomeTagProvider(FabricDataOutput output, ResourceKey<? extends Registry<Biome>> registryKey, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registryKey, registriesFuture);
    }

    private static TagKey<Biome> create(String string) {
        return TagKey.create(Registries.BIOME, ResourceLocation.withDefaultNamespace(string));
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        addVanillaTags();
    }

    protected void addVanillaTags() {
        getOrCreateTagBuilder(SPAWNS_WARM_VARIANT_FARM_ANIMALS).add(
                Biomes.DESERT,
                Biomes.WARM_OCEAN,
                Biomes.MANGROVE_SWAMP,
                Biomes.DEEP_LUKEWARM_OCEAN,
                Biomes.LUKEWARM_OCEAN
                )
                .forceAddTag(
                        BiomeTags.IS_NETHER
                ).forceAddTag(
                        BiomeTags.IS_SAVANNA
                ).forceAddTag(
                        BiomeTags.IS_JUNGLE
                ).forceAddTag(
                        BiomeTags.IS_BADLANDS
                );

        getOrCreateTagBuilder(SPAWNS_COLD_VARIANT_FARM_ANIMALS).add(
                Biomes.SNOWY_PLAINS,
                Biomes.ICE_SPIKES,
                Biomes.FROZEN_PEAKS,
                Biomes.JAGGED_PEAKS,
                Biomes.SNOWY_SLOPES,
                Biomes.FROZEN_OCEAN,
                Biomes.DEEP_FROZEN_OCEAN,
                Biomes.GROVE,
                Biomes.DEEP_DARK,
                Biomes.FROZEN_RIVER,
                Biomes.SNOWY_TAIGA,
                Biomes.SNOWY_BEACH,
                Biomes.COLD_OCEAN,
                Biomes.DEEP_COLD_OCEAN,
                Biomes.OLD_GROWTH_PINE_TAIGA,
                Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                Biomes.TAIGA,
                Biomes.WINDSWEPT_FOREST,
                Biomes.WINDSWEPT_GRAVELLY_HILLS,
                Biomes.WINDSWEPT_GRAVELLY_HILLS,
                Biomes.STONY_PEAKS
                )
                .forceAddTag(
                        BiomeTags.IS_END
                );


    }
}
