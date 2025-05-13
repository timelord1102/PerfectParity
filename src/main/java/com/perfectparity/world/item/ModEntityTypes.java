package com.perfectparity.world.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntityTypes {
    public static final EntityType<CustomThrownEgg> BLUE_EGG;
    public static final EntityType<CustomThrownEgg> BROWN_EGG;

    private static <T extends Entity> EntityType<T> register(String string, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, string, builder.build(string));
    }

    public static void registerEntityTypes () {

    }


    static {
        BROWN_EGG = register(
                "brown_egg",
                EntityType.Builder.<CustomThrownEgg>of(
                                (entityType, world) -> new CustomThrownEgg(entityType, world, "warm"),
                                MobCategory.MISC
                        )
                        .sized(0.25f, 0.25f)
                        .clientTrackingRange(4)
                        .updateInterval(10)
        );
        BLUE_EGG = register("blue_egg", EntityType.Builder.<CustomThrownEgg>of(ColdThrownEgg::new, MobCategory.MISC).sized(0.25f, 0.25f)
                .clientTrackingRange(4)
                .updateInterval(10));
    }
}
