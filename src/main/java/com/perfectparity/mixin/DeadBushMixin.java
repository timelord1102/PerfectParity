package com.perfectparity.mixin;

import com.mojang.serialization.MapCodec;
import com.perfectparity.sound.AmbientDesertBlockSoundsPlayer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DeadBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DeadBushBlock.class)
public class DeadBushMixin extends BushBlock {
    @Shadow @Final public static MapCodec<DeadBushBlock> CODEC;

    protected DeadBushMixin(Properties properties) {
        super(properties);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        AmbientDesertBlockSoundsPlayer.playAmbientDeadBushSounds(level, blockPos, randomSource);
    }

    @Override
    public MapCodec<DeadBushBlock> codec() {
        return this.CODEC;
    }
}
