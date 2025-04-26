package com.perfectparity.datagen;

import com.perfectparity.world.item.ModItems;
import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class ModModelProvider extends FabricModelProvider {

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
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
    }
}
