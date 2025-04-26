package com.perfectparity.world.level.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {
    public static final Block BUSH;
    public static final Block CACTUS_FLOWER;
    // public static final Block SHORT_DRY_GRASS;
    // public static final Block TALL_DRY_GRASS;
    // public static final Block TEST_BLOCK;
    // public static final Block TEST_INSTANCE_BLOCK;
    // public static final Block WILDFLOWERS;
    // public static final Block LEAF_LITTER;
    public static final Block FIREFLY_BUSH;

    static {
        BUSH = register("bush", ModBushBlockBonemealableBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY));
        CACTUS_FLOWER = register("cactus_flower", ModCactusFlowerBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).noCollission().instabreak().ignitedByLava()/*.sound(SoundType.CACTUS_FLOWER)*/.pushReaction(PushReaction.DESTROY));
        // SHORT_DRY_GRASS = register("short_dry_grass", ShortDryGrassBlock::new, Properties.of().mapColor(MapColor.COLOR_YELLOW).replaceable().noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().offsetType(OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
        // TALL_DRY_GRASS = register("tall_dry_grass", TallDryGrassBlock::new, Properties.of().mapColor(MapColor.COLOR_YELLOW).replaceable().noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().offsetType(OffsetType.XYZ).pushReaction(PushReaction.DESTROY));
        // TEST_BLOCK = register("test_block", TestBlock::new, Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(-1.0F, 3600000.0F).noLootTable());
        // TEST_INSTANCE_BLOCK = register("test_instance_block", TestInstanceBlock::new, Properties.of().noOcclusion().strength(-1.0F, 3600000.0F).noLootTable().isViewBlocking(Blocks::never));
        // WILDFLOWERS = register("wildflowers", FlowerBedBlock::new, Properties.of().mapColor(MapColor.PLANT).noCollission().sound(SoundType.PINK_PETALS).pushReaction(PushReaction.DESTROY));
        // LEAF_LITTER = register("leaf_litter", LeafLitterBlock::new, Properties.of().mapColor(MapColor.COLOR_BROWN).replaceable().noCollission().sound(SoundType.LEAF_LITTER).pushReaction(PushReaction.DESTROY));
        FIREFLY_BUSH = register("firefly_bush", ModFireflyBushBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).ignitedByLava().lightLevel((blockStatex) -> 2).noCollission().instabreak().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY));
    }

    private static ResourceKey<Block> blockId(String string) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(string));
    }

    private static Block register(String string, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        return register(blockId(string), function, properties);
    }

    public static Block register(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties) {
        Block block = (Block)function.apply(properties.setId(resourceKey));
        return (Block) Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }

    public static void initialize() {
    }

}
