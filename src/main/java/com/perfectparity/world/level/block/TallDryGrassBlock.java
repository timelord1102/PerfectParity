package com.perfectparity.world.level.block;

import com.mojang.serialization.MapCodec;
import com.perfectparity.sound.AmbientDesertBlockSoundsPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TallDryGrassBlock extends DryVegetationBlock implements ModBonemealableBlock {
    public static final MapCodec<TallDryGrassBlock> CODEC = simpleCodec(TallDryGrassBlock::new);
    private static final VoxelShape SHAPE = ModBlocks.column(14.0F, 0.0F, 16.0F);

    public MapCodec<TallDryGrassBlock> codec() {
        return CODEC;
    }

    public TallDryGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return ModBonemealableBlock.hasSpreadableNeighbourPos(levelReader, blockPos, ModBlocks.SHORT_DRY_GRASS.defaultBlockState());
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        ModBonemealableBlock.findSpreadableNeighbourPos(serverLevel, blockPos, ModBlocks.SHORT_DRY_GRASS.defaultBlockState()).ifPresent((blockPosx) -> serverLevel.setBlockAndUpdate(blockPosx, ModBlocks.SHORT_DRY_GRASS.defaultBlockState()));
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        AmbientDesertBlockSoundsPlayer.playAmbientDryGrassSounds(level, blockPos, randomSource);
    }
}
