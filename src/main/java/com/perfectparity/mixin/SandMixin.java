package com.perfectparity.mixin;

import com.mojang.serialization.MapCodec;
import com.perfectparity.sound.AmbientDesertBlockSoundsPlayer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColoredFallingBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ColoredFallingBlock.class)
public class SandMixin extends FallingBlock {

    @Shadow @Final public static MapCodec<ColoredFallingBlock> CODEC;

    public SandMixin(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<ColoredFallingBlock> codec() {
        return this.CODEC;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.is(Blocks.SAND) || blockState.is(Blocks.RED_SAND)){
            AmbientDesertBlockSoundsPlayer.playAmbientSandSounds(level, blockPos, randomSource);
        }
        else {
            super.animateTick(blockState, level, blockPos, randomSource);
        }
    }
}
