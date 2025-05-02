package com.perfectparity;

import com.perfectparity.entity.client.*;
import com.perfectparity.entity.models.ModModelLayers;
import com.perfectparity.particle.FireflyParticle;
import com.perfectparity.particle.ModParticles;
import com.perfectparity.utils.DryFoliageColor;
import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class PerfectParityClient implements ClientModInitializer {
	private static final String PATH = "/assets/minecraft/textures/colormap/dry_foliage.png";
	@Override
	public void onInitializeClient() {
		registerColormap();
		registerModelLayers();
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BUSH, RenderType.cutout());
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getAverageGrassColor(world, pos), ModBlocks.BUSH);

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FIREFLY_BUSH, RenderType.cutout());
		ParticleFactoryRegistry.getInstance().register(ModParticles.FIREFLY, FireflyParticle.FireflyProvider::new);

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CACTUS_FLOWER, RenderType.cutout());

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEAF_LITTER, RenderType.cutout());
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
				world != null && pos != null ? DryFoliageColor.getTint(world, pos) : DryFoliageColor.FOLIAGE_DRY_DEFAULT, ModBlocks.LEAF_LITTER);

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TALL_DRY_GRASS, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SHORT_DRY_GRASS, RenderType.cutout());

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WILDFLOWERS, RenderType.cutout());
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if (tintIndex == 1) {
				return BiomeColors.getAverageGrassColor(world, pos);
			}
			return 0xFFFFFFFF;
		}, ModBlocks.WILDFLOWERS);
	}
	private static void registerColormap() {
		try (InputStream in = ClientEntityEvents.class.getResourceAsStream(PATH)) {
			if (in == null) {
				throw new IOException("Resource not found: " + PATH);
			}
			BufferedImage img = ImageIO.read(in);
			int w = img.getWidth(), h = img.getHeight();
			int[] pixels = new int[w * h];
			img.getRGB(0, 0, w, h, pixels, 0, w);
			DryFoliageColor.init(pixels);
			PerfectParity.LOGGER.info("Loaded dry_foliage colormap ({}×{})", w, h);
		} catch (IOException e) {
			PerfectParity.LOGGER.error("Failed to load dry_foliage.png from classpath", e);
		}
	}

	private static void registerModelLayers() {
		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.WARM_COW,
				WarmCowModel::createBodyLayer   // static method returning LayerDefinition
		);
		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.WARM_COW_BABY,
				WarmCowModel::createBabyBodyLayer   // or your baby version method
		);
		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.COLD_COW,
				ColdCowModel::createBodyLayer   // static method returning LayerDefinition
		);
		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.COLD_COW_BABY,
				ColdCowModel::createBabyBodyLayer
		);

		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.COLD_CHICKEN,
				ColdChickenModel::createBodyLayer   // static method returning LayerDefinition
		);
		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.COLD_CHICKEN_BABY,
				ColdChickenModel::createBabyBodyLayer
		);

		//EntityRendererRegistry.register(ModEntities.TEST_COW, ModCowRenderer::new);
	}
}