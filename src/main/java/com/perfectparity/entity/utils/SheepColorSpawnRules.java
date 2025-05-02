package com.perfectparity.entity.utils;

import com.perfectparity.datagen.ModBiomeTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.biome.Biome;

public class SheepColorSpawnRules {
    private static final SheepColorSpawnConfiguration TEMPERATE_SPAWN_CONFIGURATION;
    private static final SheepColorSpawnConfiguration WARM_SPAWN_CONFIGURATION;
    private static final SheepColorSpawnConfiguration COLD_SPAWN_CONFIGURATION;

    public SheepColorSpawnRules() {
    }

    private static SheepColorProvider commonColors(DyeColor dyeColor) {
        return weighted(builder().add(single(dyeColor), 499).add(single(DyeColor.PINK), 1).build());
    }

    public static DyeColor getSheepColor(Holder<Biome> holder, RandomSource randomSource) {
        SheepColorSpawnConfiguration sheepColorSpawnConfiguration = getSheepColorConfiguration(holder);
        return sheepColorSpawnConfiguration.colors().get(randomSource);
    }

    private static SheepColorSpawnConfiguration getSheepColorConfiguration(Holder<Biome> holder) {
        if (holder.is(ModBiomeTagProvider.SPAWNS_WARM_VARIANT_FARM_ANIMALS)) {
            return WARM_SPAWN_CONFIGURATION;
        } else {
            return holder.is(ModBiomeTagProvider.SPAWNS_COLD_VARIANT_FARM_ANIMALS) ? COLD_SPAWN_CONFIGURATION : TEMPERATE_SPAWN_CONFIGURATION;
        }
    }

    private static SheepColorProvider weighted(SimpleWeightedRandomList<SheepColorProvider> weightedList) {
        if (weightedList.isEmpty()) {
            throw new IllegalArgumentException("List must be non-empty");
        } else {
            return (randomSource) -> ((SheepColorProvider)weightedList.getRandom(randomSource).get().data()).get(randomSource);
        }
    }

    private static SheepColorProvider single(DyeColor dyeColor) {
        return (randomSource) -> dyeColor;
    }

    private static SimpleWeightedRandomList.Builder<SheepColorProvider> builder() {
        return SimpleWeightedRandomList.builder();
    }

    static {
        TEMPERATE_SPAWN_CONFIGURATION = new SheepColorSpawnConfiguration(weighted(builder().add(single(DyeColor.BLACK), 5).add(single(DyeColor.GRAY), 5).add(single(DyeColor.LIGHT_GRAY), 5).add(single(DyeColor.BROWN), 3).add(commonColors(DyeColor.WHITE), 82).build()));
        WARM_SPAWN_CONFIGURATION = new SheepColorSpawnConfiguration(weighted(builder().add(single(DyeColor.GRAY), 5).add(single(DyeColor.LIGHT_GRAY), 5).add(single(DyeColor.WHITE), 5).add(single(DyeColor.BLACK), 3).add(commonColors(DyeColor.BROWN), 82).build()));
        COLD_SPAWN_CONFIGURATION = new SheepColorSpawnConfiguration(weighted(builder().add(single(DyeColor.LIGHT_GRAY), 5).add(single(DyeColor.GRAY), 5).add(single(DyeColor.WHITE), 5).add(single(DyeColor.BROWN), 3).add(commonColors(DyeColor.BLACK), 82).build()));
    }

    static record SheepColorSpawnConfiguration(SheepColorProvider colors) {
        SheepColorSpawnConfiguration(SheepColorProvider colors) {
            this.colors = colors;
        }

        public SheepColorProvider colors() {
            return this.colors;
        }
    }

    @FunctionalInterface
    interface SheepColorProvider {
        DyeColor get(RandomSource randomSource);
    }
}
