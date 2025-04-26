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

public class ModCactusFlowerBlock extends ModVegetationBlock {
    public static final MapCodec<ModCactusFlowerBlock> CODEC = simpleCodec(ModCactusFlowerBlock::new);
    private static final VoxelShape SHAPE = column((double)14.0F, (double)0.0F, (double)12.0F);

    public MapCodec<? extends ModCactusFlowerBlock> codec() {
        return CODEC;
    }

    public ModCactusFlowerBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        BlockState blockState2 = blockGetter.getBlockState(blockPos);
        return blockState2.is(Blocks.CACTUS) || blockState2.is(Blocks.FARMLAND) || blockState2.isFaceSturdy(blockGetter, blockPos, Direction.UP, SupportType.CENTER);
    }

    public static VoxelShape column(double d, double e, double f) {
        return column(d, d, e, f);
    }

    public static VoxelShape column(double d, double e, double f, double g) {
        double h = d / (double)2.0F;
        double i = e / (double)2.0F;
        return box((double)8.0F - h, f, (double)8.0F - i, (double)8.0F + h, g, (double)8.0F + i);
    }
}
