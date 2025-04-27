package com.perfectparity.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModBushBlock extends BushBlock implements BonemealableBlock {
    public static final MapCodec<net.minecraft.world.level.block.BushBlock> CODEC = simpleCodec(ModBushBlock::new);
    private static final VoxelShape SHAPE = Block.box(2.0F, 0.0F, 2.0F, 14.0F, 13.0F, 14.0F);

    public MapCodec<net.minecraft.world.level.block.BushBlock> codec() {
        return CODEC;
    }

    public ModBushBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return ModBonemealableBlock.hasSpreadableNeighbourPos(levelReader, blockPos, blockState);
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        ModBonemealableBlock.findSpreadableNeighbourPos(serverLevel, blockPos, blockState).ifPresent((blockPosx) -> serverLevel.setBlockAndUpdate(blockPosx, this.defaultBlockState()));
    }
}
