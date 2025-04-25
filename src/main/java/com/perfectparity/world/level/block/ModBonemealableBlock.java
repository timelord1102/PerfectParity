package com.perfectparity.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Optional;

public interface ModBonemealableBlock extends net.minecraft.world.level.block.BonemealableBlock {
    static boolean hasSpreadableNeighbourPos(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return getSpreadableNeighbourPos(Direction.Plane.HORIZONTAL.stream().toList(), levelReader, blockPos, blockState).isPresent();
    }

    static Optional<BlockPos> findSpreadableNeighbourPos(Level level, BlockPos blockPos, BlockState blockState) {
        return getSpreadableNeighbourPos(Direction.Plane.HORIZONTAL.shuffledCopy(level.random), level, blockPos, blockState);
    }

    private static Optional<BlockPos> getSpreadableNeighbourPos(List<Direction> list, LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        for(Direction direction : list) {
            BlockPos blockPos2 = blockPos.relative(direction);
            if (levelReader.isEmptyBlock(blockPos2) && blockState.canSurvive(levelReader, blockPos2)) {
                return Optional.of(blockPos2);
            }
        }

        return Optional.empty();
    }
}
