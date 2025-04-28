package com.perfectparity.sound;

import com.perfectparity.PerfectParity;
import com.perfectparity.datagen.ModBlockTagProvider;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

/**
 * The 1.21.6 implementation. 1.21.5 already had a framework for it
 */

public class AmbientDesertBlockSoundsPlayer {
    private static final int IDLE_SOUND_CHANCE = 2100;
    private static final int DRY_GRASS_SOUND_CHANCE = 200;
    private static final int DEAD_BUSH_SOUND_CHANCE = 130;
    private static final int DEAD_BUSH_SOUND_BADLANDS_DECREASED_CHANCE = 3;
    private static final int SURROUNDING_BLOCKS_PLAY_SOUND_THRESHOLD = 3;
    private static final int SURROUNDING_BLOCKS_DISTANCE_HORIZONTAL_CHECK = 8;
    private static final int SURROUNDING_BLOCKS_DISTANCE_VERTICAL_CHECK = 5;
    private static final int HORIZONTAL_DIRECTIONS = 4;

    public static void playAmbientSandSounds(Level level, BlockPos blockPos, RandomSource randomSource) {
        if (!level.getBlockState(blockPos.above()).is(Blocks.AIR)) {
            return;
        }
        if (randomSource.nextInt(2100) == 0 && AmbientDesertBlockSoundsPlayer.shouldPlayAmbientSandSound(level, blockPos)) {
            if (level instanceof ClientLevel client) {
                PerfectParity.LOGGER.info("Playing sand Sound");
                client.playLocalSound(
                        blockPos.getX() + 0.5,
                        blockPos.getY() + 0.5,
                        blockPos.getZ() + 0.5,
                        ModSounds.DRY_GRASS,
                        SoundSource.AMBIENT,
                        1.0F,
                        1.0F,
                        false          // no distance delay
                );
            }
        }
    }

    public static void playAmbientDryGrassSounds(Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(200) == 0 && AmbientDesertBlockSoundsPlayer.shouldPlayDesertDryVegetationBlockSounds(level, blockPos.below())) {
            if (level instanceof ClientLevel client) {
                client.playLocalSound(
                        blockPos.getX() + 0.5,
                        blockPos.getY() + 0.5,
                        blockPos.getZ() + 0.5,
                        ModSounds.DRY_GRASS,
                        SoundSource.AMBIENT,
                        1.0F,
                        1.0F,
                        false          // no distance delay
                );
            }
        }
    }

    public static void playAmbientDeadBushSounds(Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(130) == 0) {
            BlockState blockState = level.getBlockState(blockPos.below());
            if ((blockState.is(Blocks.RED_SAND) || blockState.is(BlockTags.TERRACOTTA)) && randomSource.nextInt(3) != 0) {
                return;
            }
            if (AmbientDesertBlockSoundsPlayer.shouldPlayDesertDryVegetationBlockSounds(level, blockPos.below())) {
                level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), ModSounds.DEAD_BUSH_IDLE, SoundSource.AMBIENT, 1.0f, 1.0f, false);
            }
        }
    }

    public static boolean shouldPlayDesertDryVegetationBlockSounds(Level level, BlockPos blockPos) {
        return level.getBlockState(blockPos).is(ModBlockTagProvider.TRIGGERS_AMBIENT_DESERT_DRY_VEGETATION_BLOCK_SOUNDS) && level.getBlockState(blockPos.below()).is(ModBlockTagProvider.TRIGGERS_AMBIENT_DESERT_DRY_VEGETATION_BLOCK_SOUNDS);
    }

    private static boolean shouldPlayAmbientSandSound(Level level, BlockPos blockPos) {
        int n = 0;
        int n2 = 0;
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            int n3;
            int n4;
            boolean bl;
            mutableBlockPos.set(blockPos).move(direction, 8);
            if (AmbientDesertBlockSoundsPlayer.columnContainsTriggeringBlock(level, mutableBlockPos) && n++ >= 3) {
                return true;
            }
            if (bl = (n4 = (n3 = 4 - ++n2) + n) >= 3) continue;
            return false;
        }
        return false;
    }

    private static boolean columnContainsTriggeringBlock(Level level, BlockPos.MutableBlockPos mutableBlockPos) {
        int n = level.getHeight(Heightmap.Types.WORLD_SURFACE, mutableBlockPos.getX(), mutableBlockPos.getY()) - 1;
        if (Math.abs(n - mutableBlockPos.getY()) <= 5) {
            boolean bl = level.getBlockState(mutableBlockPos.setY(n + 1)).isAir();
            return bl && AmbientDesertBlockSoundsPlayer.canTriggerAmbientDesertSandSounds(level.getBlockState(mutableBlockPos.setY(n)));
        }
        mutableBlockPos.move(Direction.UP, 6);
        BlockState blockState = level.getBlockState(mutableBlockPos);
        mutableBlockPos.move(Direction.DOWN);
        for (int i = 0; i < 10; ++i) {
            BlockState blockState2 = level.getBlockState(mutableBlockPos);
            if (blockState.isAir() && AmbientDesertBlockSoundsPlayer.canTriggerAmbientDesertSandSounds(blockState2)) {
                return true;
            }
            blockState = blockState2;
            mutableBlockPos.move(Direction.DOWN);
        }
        return false;
    }

    private static boolean canTriggerAmbientDesertSandSounds(BlockState blockState) {
        return blockState.is(ModBlockTagProvider.TRIGGERS_AMBIENT_DESERT_SAND_BLOCK_SOUNDS);
    }
}

