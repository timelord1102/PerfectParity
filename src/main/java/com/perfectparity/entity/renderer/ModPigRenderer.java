package com.perfectparity.entity.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.perfectparity.entity.utils.MobVariant;
import com.perfectparity.entity.models.ModModelLayers;
import com.perfectparity.entity.models.pig.ModPigModel;
import com.perfectparity.entity.utils.AdultAndBabyModelPair;
import com.perfectparity.utils.interfaces.VariantMob;
import net.minecraft.Util;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ModPigRenderer extends MobRenderer<Pig, ModPigRenderState, ModPigModel> {

    private static final Map<MobVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MobVariant.class), map -> {
                map.put(MobVariant.NORMAL,
                        ResourceLocation.withDefaultNamespace("textures/entity/pig/temperate_pig.png"));
                map.put(MobVariant.COLD,
                        ResourceLocation.withDefaultNamespace("textures/entity/pig/cold_pig.png"));
                map.put(MobVariant.WARM,
                        ResourceLocation.withDefaultNamespace("textures/entity/pig/warm_pig.png"));
            });

    private Map<MobVariant, AdultAndBabyModelPair<ModPigModel>> VARIANT_MODELS = new HashMap<>();

    public ModPigRenderer(EntityRendererProvider.Context context) {
        super(context, new ModPigModel(context.bakeLayer(ModelLayers.PIG)), 0.7F);
        this.VARIANT_MODELS = bakeModels(context);
        this.addLayer(new SaddleLayer(this, new PigModel(context.bakeLayer(ModelLayers.PIG_SADDLE)), new PigModel(context.bakeLayer(ModelLayers.PIG_BABY_SADDLE)), ResourceLocation.withDefaultNamespace("textures/entity/pig/pig_saddle.png")));
    }

    private static Map<MobVariant, AdultAndBabyModelPair<ModPigModel>> bakeModels(EntityRendererProvider.Context context) {
        EnumMap<MobVariant, AdultAndBabyModelPair<ModPigModel>> map = new EnumMap<>(MobVariant.class);
        map.put(MobVariant.NORMAL, new AdultAndBabyModelPair<>(
                new ModPigModel(context.bakeLayer(ModModelLayers.NEW_PIG)),
                new ModPigModel(context.bakeLayer(ModModelLayers.NEW_PIG_BABY))
        ));
        map.put(MobVariant.WARM, new AdultAndBabyModelPair<>(
                new ModPigModel(context.bakeLayer(ModModelLayers.COLD_PIG)),
                new ModPigModel(context.bakeLayer(ModModelLayers.COLD_PIG_BABY))
        ));
        map.put(MobVariant.COLD, new AdultAndBabyModelPair<>(
                new ModPigModel(context.bakeLayer(ModModelLayers.COLD_PIG)),
                new ModPigModel(context.bakeLayer(ModModelLayers.COLD_PIG_BABY))
        ));
        return map;    }

    @Override
    public ResourceLocation getTextureLocation(ModPigRenderState pigRenderState) {
        return pigRenderState.variant == null ? MissingTextureAtlasSprite.getLocation() : LOCATION_BY_VARIANT.get(pigRenderState.variant);
    }

    @Override
    public ModPigRenderState createRenderState() {
        return new ModPigRenderState();
    }

    @Override
    public void extractRenderState(Pig pig, ModPigRenderState pigRenderState, float f) {
        super.extractRenderState(pig, pigRenderState, f);
        pigRenderState.isSaddled = pig.isSaddled();
        pigRenderState.variant = (MobVariant) ((VariantMob) pig).getVariant();
    }

    @Override
    public void render(ModPigRenderState pigRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if (pigRenderState.variant != null) {
            this.model = (ModPigModel) ((AdultAndBabyModelPair)this.VARIANT_MODELS.get(pigRenderState.variant)).getModel(pigRenderState.isBaby);
            if (pigRenderState.isBaby) {

            }
            super.render(pigRenderState, poseStack, multiBufferSource, i);
        }
    }
}
