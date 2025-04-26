package com.perfectparity.sound;

import com.perfectparity.PerfectParity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    public static final SoundEvent FIREFLY_BUSH_IDLE = registerSoundEvent("block.firefly_bush.idle");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation resourceLocation = ResourceLocation.tryBuild(ResourceLocation.DEFAULT_NAMESPACE, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createVariableRangeEvent(resourceLocation));
    }

    public static void registerSounds() {
        PerfectParity.LOGGER.info("Registering Sounds for PerfectParity");
    }
}
