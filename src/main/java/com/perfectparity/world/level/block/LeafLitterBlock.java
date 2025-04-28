package com.perfectparity.world.level.block;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.mojang.serialization.MapCodec;

import java.util.function.BiFunction;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LeafLitterBlock extends ModVegetationBlock {
    public static final MapCodec<LeafLitterBlock> CODEC = simpleCodec(LeafLitterBlock::new);
    public static final int MIN_LEAVES = 1;
    public static final int MAX_LEAVES = 4;
    public static final EnumProperty<Direction> FACING;
    public static final IntegerProperty AMOUNT;
    private static final BiFunction<Direction, Integer, VoxelShape> SHAPE_BY_PROPERTIES;

    public LeafLitterBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AMOUNT, 1));
    }


    protected MapCodec<LeafLitterBlock> codec() {
        return CODEC;
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        return !blockPlaceContext.isSecondaryUseActive() && blockPlaceContext.getItemInHand().is(this.asItem()) && blockState.getValue(AMOUNT) < 4 || super.canBeReplaced(blockState, blockPlaceContext);
    }

    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.below();
        return levelReader.getBlockState(blockPos2).isFaceSturdy(levelReader, blockPos2, Direction.UP);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE_BY_PROPERTIES.apply(blockState.getValue(FACING), blockState.getValue(AMOUNT));
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos());
        return blockState.is(this) ? blockState.setValue(AMOUNT, Math.min(4, blockState.getValue(AMOUNT) + 1)) : this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, AMOUNT);
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        AMOUNT = ModBlockStateProperties.SEGMENT_AMOUNT;
        SHAPE_BY_PROPERTIES = Util.memoize((direction, integer) -> {
            VoxelShape[] voxelShapes = new VoxelShape[]{Block.box(8.0F, 0.0F, 8.0F, 16.0F, 1.0F, 16.0F), Block.box(8.0F, 0.0F, 0.0F, 16.0F, 1.0F, 8.0F), Block.box(0.0F, 0.0F, 0.0F, 8.0F, 1.0F, 8.0F), Block.box(0.0F, 0.0F, 8.0F, 8.0F, 1.0F, 16.0F)};
            VoxelShape voxelShape = Shapes.empty();

            for(int i = 0; i < integer; ++i) {
                int j = Math.floorMod(i - direction.get2DDataValue(), 4);
                voxelShape = Shapes.or(voxelShape, voxelShapes[j]);
            }

            return voxelShape.singleEncompassing();
        });
    }
}
