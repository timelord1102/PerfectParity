//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.perfectparity.world.level.block;

import com.mojang.serialization.MapCodec;
import com.perfectparity.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import com.perfectparity.sound.ModSounds;

public class FireflyBushBlock extends ModVegetationBlock implements ModBonemealableBlock {
    private static final double FIREFLY_CHANCE_PER_TICK = 0.7;
    private static final double FIREFLY_HORIZONTAL_RANGE = (double)10.0F;
    private static final double FIREFLY_VERTICAL_RANGE = (double)5.0F;
    private static final int FIREFLY_SPAWN_MAX_BRIGHTNESS_LEVEL = 13;
    private static final int FIREFLY_AMBIENT_SOUND_CHANCE_ONE_IN = 30;
    public static final MapCodec<FireflyBushBlock> CODEC = simpleCodec(FireflyBushBlock::new);

    public FireflyBushBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends FireflyBushBlock> codec() {
        return CODEC;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(30) == 0 && isMoonVisible(level) &&  level.getHeight(Types.MOTION_BLOCKING_NO_LEAVES, blockPos.getX(), blockPos.getY()) <= blockPos.getY()) {
            level.playLocalSound(blockPos, ModSounds.FIREFLY_BUSH_IDLE, SoundSource.AMBIENT, 1.0F, 1.0F, false);
        }
        if (isMoonVisible(level) || (level.getMaxLocalRawBrightness(blockPos) <= 13)) {
            double d = (double)blockPos.getX() + randomSource.nextDouble() * (double)10.0F - (double)5.0F;
            double e = (double)blockPos.getY() + randomSource.nextDouble() * (double)5.0F;
            double f = (double)blockPos.getZ() + randomSource.nextDouble() * (double)10.0F - (double)5.0F;
            level.addParticle(ModParticles.FIREFLY, d, e, f, (double)0.0F, (double)0.0F, (double)0.0F);
        }

    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return ModBonemealableBlock.hasSpreadableNeighbourPos(levelReader, blockPos, blockState);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        ModBonemealableBlock.findSpreadableNeighbourPos(serverLevel, blockPos, blockState).ifPresent((blockPosx) -> serverLevel.setBlockAndUpdate(blockPosx, this.defaultBlockState()));
    }

    public boolean isMoonVisible(Level level) {
        if (!level.dimensionType().natural()) {
            return false;
        } else {
            int i = (int)(level.getDayTime() % 24000L);
            return i >= 12600 && i <= 23400;
        }
    }
}
