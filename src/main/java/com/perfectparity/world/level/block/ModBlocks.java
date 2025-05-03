package com.perfectparity.world.level.block;

import com.perfectparity.sound.ModSounds;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModBlocks {
    public static final Block BUSH;
    public static final Block CACTUS_FLOWER;
    public static final Block SHORT_DRY_GRASS;
    public static final Block TALL_DRY_GRASS;
    // public static final Block TEST_BLOCK;
    // public static final Block TEST_INSTANCE_BLOCK;
    public static final Block WILDFLOWERS;
    public static final Block LEAF_LITTER;
    public static final Block FIREFLY_BUSH;

    static {
        BUSH = register("bush", new ModBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().pushReaction(PushReaction.DESTROY)));
        CACTUS_FLOWER = register("cactus_flower", new CactusFlowerBlock( BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).noCollission().instabreak().ignitedByLava().sound(ModSounds.CACTUS_FLOWER).pushReaction(PushReaction.DESTROY)));
        SHORT_DRY_GRASS = register("short_dry_grass", new ShortDryGrassBlock( BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).replaceable().noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));
        TALL_DRY_GRASS = register("tall_dry_grass", new TallDryGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).replaceable().noCollission().instabreak().sound(SoundType.GRASS).ignitedByLava().offsetType(BlockBehaviour.OffsetType.XYZ).pushReaction(PushReaction.DESTROY)));
        // TEST_BLOCK = register("test_block", TestBlock::new, Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(-1.0F, 3600000.0F).noLootTable());
        // TEST_INSTANCE_BLOCK = register("test_instance_block", TestInstanceBlock::new, Properties.of().noOcclusion().strength(-1.0F, 3600000.0F).noLootTable().isViewBlocking(Blocks::never));
        WILDFLOWERS = register("wildflowers", new PinkPetalsBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().sound(SoundType.PINK_PETALS).pushReaction(PushReaction.DESTROY)));
        LEAF_LITTER = register("leaf_litter", new LeafLitterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).replaceable().noCollission().sound(ModSounds.LEAF_LITTER).pushReaction(PushReaction.DESTROY)));
        FIREFLY_BUSH = register("firefly_bush", new FireflyBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).ignitedByLava().lightLevel((blockStatex) -> 2).noCollission().instabreak().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
    }

    private static ResourceKey<Block> blockId(String string) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(string));
    }

    public static Block register(String string, Block block) {
        return (Block)Registry.register(BuiltInRegistries.BLOCK, string, block);
    }

    public static Block register(ResourceKey<Block> resourceKey, Block block) {
        return (Block)Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }

    public static void initialize() {
    }

    public static VoxelShape column(double d, double e, double f) {
        return column(d, d, e, f);
    }

    public static VoxelShape column(double d, double e, double f, double g) {
        double h = d / (double)2.0F;
        double i = e / (double)2.0F;
        return Block.box((double)8.0F - h, f, (double)8.0F - i, (double)8.0F + h, g, (double)8.0F + i);
    }

}
