package com.perfectparity.sound;

import com.perfectparity.PerfectParity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class ModSounds {

    public static final SoundEvent FIREFLY_BUSH_IDLE = registerSoundEvent("block.firefly_bush.idle");
    public static final SoundEvent CACTUS_FLOWER_BREAK = registerSoundEvent("block.cactus_flower.break");
    public static final SoundEvent CACTUS_FLOWER_PLACE = registerSoundEvent("block.cactus_flower.place");

    public static final SoundType CACTUS_FLOWER = new SoundType(1.0F, 1.0F, CACTUS_FLOWER_BREAK, SoundEvents.EMPTY, CACTUS_FLOWER_PLACE, SoundEvents.EMPTY, SoundEvents.EMPTY);

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation resourceLocation = ResourceLocation.tryBuild(ResourceLocation.DEFAULT_NAMESPACE, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createVariableRangeEvent(resourceLocation));
    }

    public static void registerSounds() {
        PerfectParity.LOGGER.info("Registering Sounds for PerfectParity");
    }
}
