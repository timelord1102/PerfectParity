package com.perfectparity.mixin;

import com.perfectparity.utils.GetBiomeDownfall;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Biome.class)
public class GetBiomeWeatherMixin implements GetBiomeDownfall {
	@Unique
	private float downfall;

	@Inject(method = "<init>*", at = @At("TAIL"))
	private void init(Biome.ClimateSettings climateSettings, BiomeSpecialEffects biomeSpecialEffects, BiomeGenerationSettings biomeGenerationSettings, MobSpawnSettings mobSpawnSettings, CallbackInfo ci) {
		this.downfall = climateSettings.downfall();
	}

	@Override
	public float projectParity$getDownfall() {
		return downfall;
	}
}
