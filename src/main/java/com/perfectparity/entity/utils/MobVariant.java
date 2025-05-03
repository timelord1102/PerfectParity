package com.perfectparity.entity.utils;

import com.mojang.serialization.Codec;
import com.perfectparity.datagen.ModBiomeTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.biome.Biome;

public enum MobVariant implements StringRepresentable {
    NORMAL("normal"),
    COLD("cold"),
    WARM("warm");

    public static final Codec<MobVariant> CODEC = StringRepresentable.fromEnum(MobVariant::values);

    private final String id;

    MobVariant(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static MobVariant getFromBiome(Holder<Biome> biomeHolder) {
        if (biomeHolder.is(ModBiomeTagProvider.SPAWNS_WARM_VARIANT_FARM_ANIMALS)) {
            return MobVariant.WARM;
        } else if (biomeHolder.is(ModBiomeTagProvider.SPAWNS_COLD_VARIANT_FARM_ANIMALS)) {
            return MobVariant.COLD;
        } else {
            return MobVariant.NORMAL;
        }
    }

    public static MobVariant getById(String id) {
        for (MobVariant variant : values()) {
            if (variant.id.equals(id)) {
                return variant;
            }
        }
        return NORMAL; // or throw if you prefer
    }

    @Override
    public String getSerializedName() {
        return this.id;
    }
}
