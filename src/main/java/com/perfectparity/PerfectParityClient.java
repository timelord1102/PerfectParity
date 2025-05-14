package com.perfectparity;

import com.perfectparity.entity.models.ModModelLayers;
import com.perfectparity.entity.models.chicken.ColdChickenModel;
import com.perfectparity.entity.models.cow.ColdCowModel;
import com.perfectparity.entity.models.cow.ModCowModel;
import com.perfectparity.entity.models.cow.WarmCowModel;
import com.perfectparity.entity.models.pig.ColdPigModel;
import com.perfectparity.entity.models.pig.ModPigModel;
import com.perfectparity.particle.FireflyParticle;
import com.perfectparity.particle.ModParticles;
import com.perfectparity.utils.DryFoliageColor;
import com.perfectparity.world.item.ModEntityTypes;
import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.GrassColor;

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
		ColorProviderRegistry.BLOCK.register(
				(state, world, pos, tintIndex) ->
						(world != null && pos != null) ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.getDefaultColor(),
				ModBlocks.BUSH
		);
		ColorProviderRegistry.ITEM.register((state, tintIndex) -> GrassColor.getDefaultColor(), ModBlocks.BUSH.asItem());

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

		EntityRendererRegistry.register(
				ModEntityTypes.BLUE_EGG,
                ThrownItemRenderer::new
		);

		EntityRendererRegistry.register(
				ModEntityTypes.BROWN_EGG,
				ThrownItemRenderer::new
		);

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
				WarmCowModel::createBodyLayer
		);
		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.COLD_COW,
				ColdCowModel::createBodyLayer
		);

		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.NEW_COW,
				ModCowModel::createBodyLayer
		);

		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.COLD_CHICKEN,
				ColdChickenModel::createBodyLayer
		);
		/*
		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.COLD_CHICKEN_BABY,
				ColdChickenModel::createBabyBodyLayer
		);
	*/
		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.COLD_PIG,
				ColdPigModel::createBodyLayer
		);

		EntityModelLayerRegistry.registerModelLayer(
				ModModelLayers.NEW_PIG,
				ModPigModel::createBodyLayer
		);

	}
}