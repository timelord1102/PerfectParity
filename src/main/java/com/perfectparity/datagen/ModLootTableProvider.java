package com.perfectparity.datagen;

import com.perfectparity.world.level.block.ModBlocks;
import com.perfectparity.world.level.block.LeafLitterBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        add(ModBlocks.BUSH, createShearsOnlyDrop(ModBlocks.BUSH));
        add(ModBlocks.SHORT_DRY_GRASS, createShearsOnlyDrop(ModBlocks.SHORT_DRY_GRASS));
        add(ModBlocks.TALL_DRY_GRASS, createShearsOnlyDrop(ModBlocks.TALL_DRY_GRASS));
        dropSelf(ModBlocks.FIREFLY_BUSH);
        dropSelf(ModBlocks.CACTUS_FLOWER);
        add(ModBlocks.LEAF_LITTER, createSegmentedBlockDrops(ModBlocks.LEAF_LITTER));
        add(ModBlocks.WILDFLOWERS, createPetalsDrops(ModBlocks.WILDFLOWERS));
    }

    public LootTable.Builder createSegmentedBlockDrops(Block block) {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(IntStream.rangeClosed(1, 4).boxed().toList(), (integer) -> SetItemCountFunction.setCount(ConstantValue.exactly((float)integer)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(LeafLitterBlock.AMOUNT, integer)))))));
    }
}
