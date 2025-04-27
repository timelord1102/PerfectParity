package com.perfectparity.data.worldgen.features;

import com.perfectparity.PerfectParity;
import com.perfectparity.data.worldgen.configurations.FallenTreeConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class ModFeatures {
    public static final Feature<FallenTreeConfiguration> FALLEN_TREE;

    public static void registerFeatures() {
        PerfectParity.LOGGER.info("PerfectParity: Registering custom features");
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String string, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, string, feature);
    }

    static {
        FALLEN_TREE = register("fallen_tree", new FallenTreeFeature(FallenTreeConfiguration.CODEC));
    }
}
