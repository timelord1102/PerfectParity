package com.perfectparity.world.level.block;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.mojang.serialization.MapCodec;
import com.perfectparity.sound.AmbientDesertBlockSoundsPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShortDryGrassBlock extends DryVegetationBlock implements BonemealableBlock {
    public static final MapCodec<ShortDryGrassBlock> CODEC = simpleCodec(ShortDryGrassBlock::new);
    private static final VoxelShape SHAPE = ModBlocks.column(12.0F, 0.0F, 10.0F);

    public MapCodec<ShortDryGrassBlock> codec() {
        return CODEC;
    }

    public ShortDryGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        serverLevel.setBlockAndUpdate(blockPos, ModBlocks.TALL_DRY_GRASS.defaultBlockState());
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        AmbientDesertBlockSoundsPlayer.playAmbientDryGrassSounds(level, blockPos, randomSource);
    }
}
