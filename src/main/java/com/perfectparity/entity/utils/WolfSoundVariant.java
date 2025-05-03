package com.perfectparity.entity.utils;
import net.minecraft.util.RandomSource;

import java.util.HashMap;
import java.util.Map;

public enum WolfSoundVariant {
    CLASSIC("minecraft:classic", ""),
    PUGLIN("minecraft:puglin", "_puglin"),
    SAD("minecraft:sad", "_sad"),
    ANGRY("minecraft:angry", "_angry"),
    GRUMPY("minecraft:grumpy", "_grumpy"),
    BIG("minecraft:big", "_big"),
    CUTE("minecraft:cute", "_cute");

    private final String identifier;
    private final String soundEventSuffix;
    private static final RandomSource randomSource = RandomSource.createNewThreadLocalInstance();
    private static final Map<String, WolfSoundVariant> BY_ID = new HashMap<>();

    static {
        for (WolfSoundVariant variant : values()) {
            BY_ID.put(variant.identifier, variant);
        }
    }

    private WolfSoundVariant(final String string2, final String string3) {
        this.identifier = string2;
        this.soundEventSuffix = string3;
    }

    public static WolfSoundVariant getRandom() {
        WolfSoundVariant[] values = values();
        return values[randomSource.nextInt(values.length)];
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public static WolfSoundVariant getById(String id) {
        return BY_ID.getOrDefault(id, CLASSIC); // fallback if not found
    }
    public String getSoundEventSuffix() {
        return this.soundEventSuffix;
    }

    static {
        for (WolfSoundVariant variant : values()) {
            BY_ID.put(variant.identifier, variant);
        }
    }
}
