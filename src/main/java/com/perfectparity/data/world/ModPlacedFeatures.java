package com.perfectparity.data.world;

import com.perfectparity.PerfectParity;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PATCH_BUSH = registryKey("patch_bush");

    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = bootstrapContext.lookup(Registries.CONFIGURED_FEATURE);

        register(bootstrapContext, PATCH_BUSH, configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_BUSH), RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    }

    public static ResourceKey<PlacedFeature> registryKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.tryBuild(PerfectParity.MOD_ID, name));
    }

    public static void register(BootstrapContext<PlacedFeature> bootstrapContext, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config,
                                List<PlacementModifier> modifiers) {
        bootstrapContext.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends  Feature<FC>> void register(BootstrapContext<PlacedFeature> context,
                                                                                           ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config,
                                                                                           PlacementModifier... modifiers) {
        register(context, key, config, List.of(modifiers));
    }


}
