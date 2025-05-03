package com.perfectparity.entity.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.perfectparity.entity.utils.MobVariant;
import com.perfectparity.entity.models.ModModelLayers;
import com.perfectparity.entity.models.chicken.ModChickenModel;
import com.perfectparity.entity.utils.AdultAndBabyModelPair;
import com.perfectparity.utils.interfaces.VariantMob;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ModChickenRenderer extends MobRenderer<Chicken, ModChickenRenderState, ModChickenModel> {

    private static final Map<MobVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MobVariant.class), map -> {
                map.put(MobVariant.NORMAL,
                        ResourceLocation.withDefaultNamespace("textures/entity/chicken/temperate_chicken.png"));
                map.put(MobVariant.COLD,
                        ResourceLocation.withDefaultNamespace("textures/entity/chicken/cold_chicken.png"));
                map.put(MobVariant.WARM,
                        ResourceLocation.withDefaultNamespace("textures/entity/chicken/warm_chicken.png"));
            });

    private Map<MobVariant, AdultAndBabyModelPair<ModChickenModel>> VARIANT_MODELS = new HashMap<>();

    public ModChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ModChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), 0.7F);
        this.VARIANT_MODELS = bakeModels(context);
    }

    private static Map<MobVariant, AdultAndBabyModelPair<ModChickenModel>> bakeModels(EntityRendererProvider.Context context) {
        EnumMap<MobVariant, AdultAndBabyModelPair<ModChickenModel>> map = new EnumMap<>(MobVariant.class);
        map.put(MobVariant.NORMAL, new AdultAndBabyModelPair<>(
                new ModChickenModel(context.bakeLayer(ModelLayers.CHICKEN)),
                new ModChickenModel(context.bakeLayer(ModelLayers.CHICKEN_BABY))
        ));
        map.put(MobVariant.WARM, new AdultAndBabyModelPair<>(
                new ModChickenModel(context.bakeLayer(ModelLayers.CHICKEN)),
                new ModChickenModel(context.bakeLayer(ModelLayers.CHICKEN_BABY))
        ));
        map.put(MobVariant.COLD, new AdultAndBabyModelPair<>(
                new ModChickenModel(context.bakeLayer(ModModelLayers.COLD_CHICKEN)),
                new ModChickenModel(context.bakeLayer(ModModelLayers.COLD_CHICKEN_BABY))
        ));
        return map;
    }

    @Override
    public ModChickenRenderState createRenderState() {
        return new ModChickenRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(ModChickenRenderState chickenRenderState) {
        try {
            return LOCATION_BY_VARIANT.get(chickenRenderState.variant);
        } catch (Exception e) {
            return ResourceLocation.withDefaultNamespace("textures/entity/chicken.png");
        }
    }

    @Override
    public void extractRenderState(Chicken chicken, ModChickenRenderState chickenRenderState, float f) {
        chickenRenderState.variant = ((VariantMob) chicken).getVariant();
        chickenRenderState.isBaby = chicken.isBaby();
        super.extractRenderState(chicken, chickenRenderState, f);
    }

    @Override
    public void render(ModChickenRenderState chickenRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if (chickenRenderState.variant != null) {
            this.model = (ModChickenModel) ((AdultAndBabyModelPair)this.VARIANT_MODELS.get(chickenRenderState.variant)).getModel(chickenRenderState.isBaby);
            if (chickenRenderState.isBaby) {

            }
            super.render(chickenRenderState, poseStack, multiBufferSource, i);
        }
    }
}
