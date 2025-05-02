package com.perfectparity.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Cow;

public class ModEntities {

    /*
    private static final ResourceLocation TEST_COW_ID = ResourceLocation.withDefaultNamespace("test_cow");
    private static final ResourceKey<EntityType<?>> TEST_COW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, TEST_COW_ID);
    private static final EntityType<ModCow> TEST_COW_BUILT = EntityType.Builder.of(ModCow::new, MobCategory.CREATURE).sized(0.75f, 0.75f).build(TEST_COW_KEY);
    public static final EntityType<ModCow> TEST_COW = Registry.register(BuiltInRegistries.ENTITY_TYPE, TEST_COW_KEY, TEST_COW_BUILT);
*/
    public static void registerModEntities() {

    }
}

