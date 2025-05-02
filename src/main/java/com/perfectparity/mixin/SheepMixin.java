package com.perfectparity.mixin;

import com.perfectparity.entity.utils.SheepColorSpawnRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheep.class)
public abstract class SheepMixin  {
    @Shadow public abstract void setColor(DyeColor dyeColor);

    @Unique
    private static DyeColor newGetRandomSheepColor(ServerLevelAccessor serverLevelAccessor, BlockPos blockPos) {
        Holder<Biome> holder = serverLevelAccessor.getBiome(blockPos);
        return SheepColorSpawnRules.getSheepColor(holder, serverLevelAccessor.getRandom());
    }

    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void customFinalize(ServerLevelAccessor serverLevelAccessor , DifficultyInstance difficultyInstance, EntitySpawnReason entitySpawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        this.setColor(newGetRandomSheepColor(serverLevelAccessor, ((Entity)(Object)this).blockPosition()));
    }
}
