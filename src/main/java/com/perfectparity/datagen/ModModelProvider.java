package com.perfectparity.datagen;

import com.perfectparity.world.item.ModItems;
import com.perfectparity.world.level.block.ModBlockStateProperties;
import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.Condition;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Optional;


public class ModModelProvider extends FabricModelProvider {

    public static final ModelTemplate LEAF_LITTER_1_MODEL_TEXTURE = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("block/template_leaf_litter_1")), Optional.of("_1"), TextureSlot.TEXTURE);
    public static final ModelTemplate LEAF_LITTER_2_MODEL_TEXTURE = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("block/template_leaf_litter_2")), Optional.of("_2"), TextureSlot.TEXTURE);
    public static final ModelTemplate LEAF_LITTER_3_MODEL_TEXTURE = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("block/template_leaf_litter_3")), Optional.of("_3"), TextureSlot.TEXTURE);
    public static final ModelTemplate LEAF_LITTER_4_MODEL_TEXTURE = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("block/template_leaf_litter_4")), Optional.of("_4"), TextureSlot.TEXTURE);

    public static final TexturedModel.Provider LEAF_LITTER_1 = TexturedModel.createDefault(TextureMapping::defaultTexture, LEAF_LITTER_1_MODEL_TEXTURE);
    public static final TexturedModel.Provider LEAF_LITTER_2 = TexturedModel.createDefault(TextureMapping::defaultTexture, LEAF_LITTER_2_MODEL_TEXTURE);
    public static final TexturedModel.Provider LEAF_LITTER_3 = TexturedModel.createDefault(TextureMapping::defaultTexture, LEAF_LITTER_3_MODEL_TEXTURE);
    public static final TexturedModel.Provider LEAF_LITTER_4 = TexturedModel.createDefault(TextureMapping::defaultTexture, LEAF_LITTER_4_MODEL_TEXTURE);

    public final void createLeafLitter(Block block, BlockModelGenerators blockStateModelGenerator) {
        ResourceLocation resourceLocation = LEAF_LITTER_1.create(block, blockStateModelGenerator.modelOutput);
        ResourceLocation resourceLocation2 = LEAF_LITTER_2.create(block, blockStateModelGenerator.modelOutput);
        ResourceLocation resourceLocation3 = LEAF_LITTER_3.create(block, blockStateModelGenerator.modelOutput);
        ResourceLocation resourceLocation4 = LEAF_LITTER_4.create(block, blockStateModelGenerator.modelOutput);
        blockStateModelGenerator.registerSimpleFlatItemModel(block.asItem());
        blockStateModelGenerator.blockStateOutput.accept(MultiPartGenerator.multiPart(block).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 1, new Integer[]{2, 3, 4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 1, new Integer[]{2, 3, 4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 1, new Integer[]{2, 3, 4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 1, new Integer[]{2, 3, 4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 2, new Integer[]{3, 4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation2)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 2, new Integer[]{3, 4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 2, new Integer[]{3, 4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 2, new Integer[]{3, 4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation2).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 3, new Integer[]{4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation3)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 3, new Integer[]{4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 3, new Integer[]{4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 3, new Integer[]{4}).term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation4)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), net.minecraft.client.data.models.blockstates.Variant.variant().with(VariantProperties.MODEL, resourceLocation4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)).with(Condition.condition().term(ModBlockStateProperties.SEGMENT_AMOUNT, 4).term(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST), Variant.variant().with(VariantProperties.MODEL, resourceLocation4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)));
    }

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCrossBlock(ModBlocks.BUSH, BlockModelGenerators.PlantType.TINTED);
        blockStateModelGenerator.createItemWithGrassTint(ModBlocks.BUSH);

        blockStateModelGenerator.createCrossBlock(ModBlocks.FIREFLY_BUSH, BlockModelGenerators.PlantType.EMISSIVE_NOT_TINTED);
        blockStateModelGenerator.registerSimpleFlatItemModel(ModItems.FIREFLY_BUSH);

        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.CACTUS_FLOWER, BlockModelGenerators.PlantType.NOT_TINTED);

        createLeafLitter(ModBlocks.LEAF_LITTER, blockStateModelGenerator);

        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.TALL_DRY_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createCrossBlockWithDefaultItem(ModBlocks.SHORT_DRY_GRASS, BlockModelGenerators.PlantType.NOT_TINTED);

        blockStateModelGenerator.createFlowerBed(ModBlocks.WILDFLOWERS);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.BLUE_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BROWN_EGG, ModelTemplates.FLAT_ITEM);
    }
}
