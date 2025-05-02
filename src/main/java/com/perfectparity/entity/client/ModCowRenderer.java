package com.perfectparity.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.perfectparity.entity.custom.MobVariant;
import com.perfectparity.entity.models.ModModelLayers;
import com.perfectparity.entity.utils.AdultAndBabyModelPair;
import com.perfectparity.renderer.CowRenderState;
import com.perfectparity.utils.interfaces.VariantMob;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cow;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ModCowRenderer extends MobRenderer<Cow, CowRenderState, ModCowModel> {

    private static final Map<MobVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MobVariant.class), map -> {
                map.put(MobVariant.NORMAL,
                        ResourceLocation.withDefaultNamespace("textures/entity/cow/cow.png"));
                map.put(MobVariant.COLD,
                        ResourceLocation.withDefaultNamespace("textures/entity/cow/cold_cow.png"));
                map.put(MobVariant.WARM,
                        ResourceLocation.withDefaultNamespace("textures/entity/cow/warm_cow.png"));
            });

    private MobVariant MOD_COW_VARIANT;
    private Map<MobVariant, AdultAndBabyModelPair<ModCowModel>> VARIANT_MODELS = new HashMap<>();

    public ModCowRenderer(EntityRendererProvider.Context context) {
        super(context, new ModCowModel(context.bakeLayer(ModelLayers.COW)), 0.7F);
        this.VARIANT_MODELS = bakeModels(context);
    }

    private static Map<MobVariant, AdultAndBabyModelPair<ModCowModel>> bakeModels(EntityRendererProvider.Context context) {
        EnumMap<MobVariant, AdultAndBabyModelPair<ModCowModel>> map = new EnumMap<>(MobVariant.class);
        map.put(MobVariant.NORMAL, new AdultAndBabyModelPair<>(
                new ModCowModel(context.bakeLayer(ModelLayers.COW)),
                new ModCowModel(context.bakeLayer(ModelLayers.COW_BABY))
        ));
        map.put(MobVariant.WARM, new AdultAndBabyModelPair<>(
                new ModCowModel(context.bakeLayer(ModModelLayers.WARM_COW)),
                new ModCowModel(context.bakeLayer(ModModelLayers.WARM_COW_BABY))
        ));
        map.put(MobVariant.COLD, new AdultAndBabyModelPair<>(
                new ModCowModel(context.bakeLayer(ModModelLayers.COLD_COW)),
                new ModCowModel(context.bakeLayer(ModModelLayers.COLD_COW_BABY))
        ));
        return map;
    }

    @Override
    public CowRenderState createRenderState() {
        return new CowRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(CowRenderState cowRenderState) {
        try {
            return LOCATION_BY_VARIANT.get(cowRenderState.variant);
        } catch (Exception e) {
            return ResourceLocation.withDefaultNamespace("textures/entity/cow/cow.png");
        }
    }

    @Override
    public void extractRenderState(Cow cow, CowRenderState cowRenderState, float f) {
        cowRenderState.variant = ((VariantMob) cow).getVariant();
        cowRenderState.isBaby = cow.isBaby();
        super.extractRenderState(cow, cowRenderState, f);
    }

    @Override
    public void render(CowRenderState cowRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if (cowRenderState.variant != null) {
            this.model = (ModCowModel) ((AdultAndBabyModelPair)this.VARIANT_MODELS.get(cowRenderState.variant)).getModel(cowRenderState.isBaby);
            if (cowRenderState.isBaby) {

            }
            super.render(cowRenderState, poseStack, multiBufferSource, i);
        }
    }
}
