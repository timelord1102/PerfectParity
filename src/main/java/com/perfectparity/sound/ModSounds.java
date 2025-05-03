package com.perfectparity.sound;

import com.perfectparity.PerfectParity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

import java.util.HashMap;
import java.util.Map;

public class ModSounds {

    public static final SoundEvent FIREFLY_BUSH_IDLE = registerSoundEvent("block.firefly_bush.idle");
    public static final SoundEvent CACTUS_FLOWER_BREAK = registerSoundEvent("block.cactus_flower.break");
    public static final SoundEvent CACTUS_FLOWER_PLACE = registerSoundEvent("block.cactus_flower.place");

    public static final SoundEvent LEAF_LITTER_BREAK = registerSoundEvent("block.leaf_litter.break");
    public static final SoundEvent LEAF_LITTER_STEP = registerSoundEvent("block.leaf_litter.step");
    public static final SoundEvent LEAF_LITTER_PLACE = registerSoundEvent("block.leaf_litter.place");
    public static final SoundEvent LEAF_LITTER_HIT = registerSoundEvent("block.leaf_litter.hit");
    public static final SoundEvent LEAF_LITTER_FALL = registerSoundEvent("block.leaf_litter.fall");

    public static final SoundEvent DEAD_BUSH_IDLE = registerSoundEvent("block.deadbush.idle");

    public static final SoundEvent SAND_IDLE = registerSoundEvent("block.sand.idle");

    public static final SoundEvent DRY_GRASS = registerSoundEvent("block.dry_grass.ambient");

    public static final SoundType CACTUS_FLOWER = new SoundType(1.0F, 1.0F, CACTUS_FLOWER_BREAK, SoundEvents.EMPTY, CACTUS_FLOWER_PLACE, SoundEvents.EMPTY, SoundEvents.EMPTY);
    public static final SoundType LEAF_LITTER = new SoundType(1.0F, 1.0F, LEAF_LITTER_BREAK, LEAF_LITTER_STEP, LEAF_LITTER_PLACE, LEAF_LITTER_HIT, LEAF_LITTER_FALL);

    public static final Map<String, SoundEvent> WOLF_CLASSIC = Map.of("ambient", SoundEvents.WOLF_AMBIENT,
            "pant", SoundEvents.WOLF_PANT, "whine", SoundEvents.WOLF_WHINE, "growl", SoundEvents.WOLF_GROWL,
            "death", SoundEvents.WOLF_DEATH, "hurt", SoundEvents.WOLF_HURT);
    public static final Map<String, SoundEvent> WOLF_PUGLIN = registerWolfSounds("_puglin");
    public static final Map<String, SoundEvent> WOLF_SAD = registerWolfSounds("_sad");
    public static final Map<String, SoundEvent> WOLF_ANGRY = registerWolfSounds("_angry");
    public static final Map<String, SoundEvent> WOLF_GRUMPY = registerWolfSounds("_grumpy");
    public static final Map<String, SoundEvent> WOLF_BIG = registerWolfSounds("_big");
    public static final Map<String, SoundEvent> WOLF_CUTE = registerWolfSounds("_cute");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation resourceLocation = ResourceLocation.tryBuild(ResourceLocation.DEFAULT_NAMESPACE, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createVariableRangeEvent(resourceLocation));
    }

    private static Map<String, SoundEvent> registerWolfSounds(String nameSuffix) {
        Map<String, SoundEvent> soundMap = new HashMap<>();

        soundMap.put("ambient", registerSoundEvent("entity.wolf" + nameSuffix + ".ambient"));
        soundMap.put("pant", registerSoundEvent("entity.wolf" + nameSuffix + ".pant"));
        soundMap.put("whine", registerSoundEvent("entity.wolf" + nameSuffix + ".whine"));
        soundMap.put("growl", registerSoundEvent("entity.wolf" + nameSuffix + ".growl"));
        soundMap.put("death", registerSoundEvent("entity.wolf" + nameSuffix + ".death"));
        soundMap.put("hurt", registerSoundEvent("entity.wolf" + nameSuffix + ".hurt"));

        return soundMap;
    }

    public static void registerSounds() {
        PerfectParity.LOGGER.info("Registering Sounds for PerfectParity");
    }
}
