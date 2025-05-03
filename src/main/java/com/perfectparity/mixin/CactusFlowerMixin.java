package com.perfectparity.mixin;

import com.perfectparity.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(CactusBlock.class)  // ← replace with the actual class (e.g., CactusBlock.class)
public abstract class CactusFlowerMixin extends Block {
	// If you need to reference the AGE property:
	@Shadow
	public static IntegerProperty AGE;

	public CactusFlowerMixin(Properties props) {
		super(props);
	}

	/**
	 * @author timelord1102
	 * @reason Replace vanilla cactus randomTick with custom to generate Cactus Flowers
	 */
	@Overwrite
	public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		BlockPos blockPos2 = blockPos.above();
		if (serverLevel.isEmptyBlock(blockPos2)) {
			int i = 1;
			int j = (Integer)blockState.getValue(AGE);

			while(serverLevel.getBlockState(blockPos.below(i)).is(this)) {
				++i;
				if (i == 3 && j == 15) {
					return;
				}
			}

			if (j == 8 && this.canSurvive(this.defaultBlockState(), serverLevel, blockPos.above())) {
				double d = i >= 3 ? (double)0.25F : 0.1;
				if (randomSource.nextDouble() <= d) {
					serverLevel.setBlockAndUpdate(blockPos2, ModBlocks.CACTUS_FLOWER.defaultBlockState());
				}
			} else if (j == 15 && i < 3) {
				serverLevel.setBlockAndUpdate(blockPos2, this.defaultBlockState());
				BlockState blockState2 = (BlockState)blockState.setValue(AGE, 0);
				serverLevel.setBlock(blockPos, blockState2, 260);
				serverLevel.neighborChanged(blockState2, blockPos2, this, null, false);
			}

			if (j < 15) {
				serverLevel.setBlock(blockPos, (BlockState)blockState.setValue(AGE, j + 1), 260);
			}

		}
	}
}