package com.perfectparity.data.registries;

import com.perfectparity.entity.utils.WolfSoundSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class ModRegistries {
    public static final ResourceKey<Registry<WolfSoundSet>> WOLF_SOUND_VARIANT=
            ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("wolf_sound_variant"));
    private static <T> ResourceKey<Registry<T>> createRegistryKey(String string) {
        return ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace(string));
    }

    public static void registerRegistries(){

    }

}
