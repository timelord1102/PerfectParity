package com.perfectparity.entity.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.perfectparity.entity.utils.MobVariant;
import com.perfectparity.entity.models.ModModelLayers;
import com.perfectparity.entity.models.cow.ModCowModel;
import com.perfectparity.entity.utils.AdultAndBabyModelPair;
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

public class ModCowRenderer extends MobRenderer<Cow, ModCowModel<Cow>> {

    private static final Map<MobVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MobVariant.class), map -> {
                map.put(MobVariant.NORMAL,
                        ResourceLocation.withDefaultNamespace("textures/entity/cow/temperate_cow.png"));
                map.put(MobVariant.COLD,
                        ResourceLocation.withDefaultNamespace("textures/entity/cow/cold_cow.png"));
                map.put(MobVariant.WARM,
                        ResourceLocation.withDefaultNamespace("textures/entity/cow/warm_cow.png"));
            });

    private Map<MobVariant, AdultAndBabyModelPair<ModCowModel>> VARIANT_MODELS = new HashMap<>();

    public ModCowRenderer(EntityRendererProvider.Context context) {
        super(context, new ModCowModel(context.bakeLayer(ModelLayers.COW)), 0.7F);
        this.VARIANT_MODELS = bakeModels(context);
    }

    private static Map<MobVariant, AdultAndBabyModelPair<ModCowModel>> bakeModels(EntityRendererProvider.Context context) {
        EnumMap<MobVariant, AdultAndBabyModelPair<ModCowModel>> map = new EnumMap<>(MobVariant.class);
        map.put(MobVariant.NORMAL, new AdultAndBabyModelPair<>(
                new ModCowModel(context.bakeLayer(ModModelLayers.NEW_COW)),
                new ModCowModel(context.bakeLayer(ModModelLayers.NEW_COW))
        ));
        map.put(MobVariant.WARM, new AdultAndBabyModelPair<>(
                new ModCowModel(context.bakeLayer(ModModelLayers.WARM_COW)),
                new ModCowModel(context.bakeLayer(ModModelLayers.WARM_COW))
        ));
        map.put(MobVariant.COLD, new AdultAndBabyModelPair<>(
                new ModCowModel(context.bakeLayer(ModModelLayers.COLD_COW)),
                new ModCowModel(context.bakeLayer(ModModelLayers.COLD_COW))
        ));
        return map;
    }

    @Override
    public ResourceLocation getTextureLocation(Cow cow) {
        try {
            return LOCATION_BY_VARIANT.get(((VariantMob)cow).getVariant());
        } catch (Exception e) {
            return ResourceLocation.withDefaultNamespace("textures/entity/cow/temperate_cow.png");
        }
    }

    @Override
    public void render(Cow cow, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if (((VariantMob)cow).getVariant() != null) {
            this.model = (ModCowModel) ((AdultAndBabyModelPair)this.VARIANT_MODELS.get(((VariantMob)cow).getVariant())).getModel(cow.isBaby());
            super.render(cow, f, g, poseStack, multiBufferSource, i);
        }
    }
}
