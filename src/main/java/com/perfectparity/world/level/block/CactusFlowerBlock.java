package com.perfectparity.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CactusFlowerBlock extends ModVegetationBlock {
    public static final MapCodec<CactusFlowerBlock> CODEC = simpleCodec(CactusFlowerBlock::new);
    private static final VoxelShape SHAPE = ModBlocks.column((double)14.0F, (double)0.0F, (double)12.0F);

    public MapCodec<? extends CactusFlowerBlock> codec() {
        return CODEC;
    }

    public CactusFlowerBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        BlockState blockState2 = blockGetter.getBlockState(blockPos);
        return blockState2.is(Blocks.CACTUS) || blockState2.is(Blocks.FARMLAND) || blockState2.isFaceSturdy(blockGetter, blockPos, Direction.UP, SupportType.CENTER);
    }
}
