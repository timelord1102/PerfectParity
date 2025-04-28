package com.perfectparity.world.level.block;

import com.mojang.serialization.MapCodec;
import com.perfectparity.sound.AmbientDesertBlockSoundsPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DryVegetationBlock extends ModVegetationBlock {
    public static final MapCodec<DryVegetationBlock> CODEC = simpleCodec(DryVegetationBlock::new);
    private static final VoxelShape SHAPE = ModBlocks.column(12.0F, 0.0F, 13.0F);
    private static final int IDLE_SOUND_CHANCE = 150;
    private static final int IDLE_SOUND_BADLANDS_DECREASED_CHANCE = 5;

    public MapCodec<? extends DryVegetationBlock> codec() {
        return CODEC;
    }

    public DryVegetationBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(BlockTags.DEAD_BUSH_MAY_PLACE_ON);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        AmbientDesertBlockSoundsPlayer.playAmbientDeadBushSounds(level, blockPos, randomSource);
    }
}