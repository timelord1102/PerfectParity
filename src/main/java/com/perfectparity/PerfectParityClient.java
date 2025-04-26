package com.perfectparity;

import com.perfectparity.particle.FireflyParticle;
import com.perfectparity.particle.ModParticles;
import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;

public class PerfectParityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BUSH, RenderType.cutout());
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getAverageGrassColor(world, pos), ModBlocks.BUSH);

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FIREFLY_BUSH, RenderType.cutout());
		ParticleFactoryRegistry.getInstance().register(ModParticles.FIREFLY, FireflyParticle.FireflyProvider::new);

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CACTUS_FLOWER, RenderType.cutout());
	}
}