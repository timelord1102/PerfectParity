package com.perfectparity.datagen;

import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;


public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public static final TagKey<Block> REPLACEABLE_BY_MUSHROOMS = create("replaceable_by_mushrooms");
    public static final TagKey<Block> TRIGGERS_AMBIENT_DESERT_DRY_VEGETATION_BLOCK_SOUNDS = create("plays_ambient_desert_dry_vegetation_block_sounds");
    public static final TagKey<Block> TRIGGERS_AMBIENT_DESERT_SAND_BLOCK_SOUNDS = create("plays_ambient_desert_sand_block_sounds");

    private static TagKey<Block> create(String string) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(string));
    }

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        addVanillaTags();
        // Bush
        getOrCreateTagBuilder(BlockTags.REPLACEABLE).add(ModBlocks.BUSH);
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(ModBlocks.BUSH);

        // Firefly Bush
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(ModBlocks.FIREFLY_BUSH);

        // Cactus Flower
        getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(ModBlocks.CACTUS_FLOWER);
        getOrCreateTagBuilder(BlockTags.FLOWERS).add(ModBlocks.CACTUS_FLOWER);
        getOrCreateTagBuilder(BlockTags.BEE_ATTRACTIVE).add(ModBlocks.CACTUS_FLOWER);
        getOrCreateTagBuilder(BlockTags.WALL_POST_OVERRIDE).add(ModBlocks.CACTUS_FLOWER);

        //Leaf Litter
        getOrCreateTagBuilder(BlockTags.REPLACEABLE).add(ModBlocks.LEAF_LITTER);
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(ModBlocks.LEAF_LITTER);
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS).add(ModBlocks.LEAF_LITTER);

        //Dry Grass
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(ModBlocks.SHORT_DRY_GRASS, ModBlocks.TALL_DRY_GRASS);
        getOrCreateTagBuilder(BlockTags.REPLACEABLE).add(ModBlocks.SHORT_DRY_GRASS, ModBlocks.TALL_DRY_GRASS);
    }

    protected void addVanillaTags() {
        getOrCreateTagBuilder(REPLACEABLE_BY_MUSHROOMS)
                .forceAddTags(BlockTags.LEAVES, BlockTags.SMALL_FLOWERS)
                .add(
                        Blocks.PALE_MOSS_CARPET,
                        Blocks.SHORT_GRASS,
                        Blocks.FERN,
                        Blocks.DEAD_BUSH,
                        Blocks.VINE,
                        Blocks.GLOW_LICHEN,
                        Blocks.SUNFLOWER,
                        Blocks.LILAC,
                        Blocks.ROSE_BUSH,
                        Blocks.PEONY,
                        Blocks.TALL_GRASS,
                        Blocks.LARGE_FERN,
                        Blocks.HANGING_ROOTS,
                        Blocks.PITCHER_PLANT,
                        Blocks.WATER,
                        Blocks.SEAGRASS,
                        Blocks.TALL_SEAGRASS,
                        Blocks.BROWN_MUSHROOM,
                        Blocks.RED_MUSHROOM,
                        Blocks.BROWN_MUSHROOM_BLOCK,
                        Blocks.RED_MUSHROOM_BLOCK,
                        Blocks.WARPED_ROOTS,
                        Blocks.NETHER_SPROUTS,
                        Blocks.CRIMSON_ROOTS,
                        ModBlocks.LEAF_LITTER,
                        ModBlocks.SHORT_DRY_GRASS,
                        ModBlocks.TALL_DRY_GRASS,
                        ModBlocks.BUSH,
                        ModBlocks.FIREFLY_BUSH
                );

        getOrCreateTagBuilder(BlockTags.DEAD_BUSH_MAY_PLACE_ON)
                .add(Blocks.FARMLAND);

        getOrCreateTagBuilder(TRIGGERS_AMBIENT_DESERT_DRY_VEGETATION_BLOCK_SOUNDS)
                .forceAddTags(BlockTags.TERRACOTTA)
                .add(Blocks.SAND, Blocks.RED_SAND);

        getOrCreateTagBuilder(TRIGGERS_AMBIENT_DESERT_SAND_BLOCK_SOUNDS)
                .add(Blocks.SAND, Blocks.RED_SAND).forceAddTags(BlockTags.TERRACOTTA);
    }
}
