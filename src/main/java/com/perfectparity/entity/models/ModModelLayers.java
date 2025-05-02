package com.perfectparity.entity.models;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation COLD_COW = registerLayer("cold_cow");
    public static final ModelLayerLocation COLD_COW_BABY = registerLayer("cold_cow_baby");
    public static final ModelLayerLocation WARM_COW = registerLayer("warm_cow");
    public static final ModelLayerLocation WARM_COW_BABY = registerLayer("warm_cow_baby");
    public static final ModelLayerLocation NEW_COW = registerLayer("new_cow");
    public static final ModelLayerLocation NEW_COW_BABY = registerLayer("new_cow_baby");

    public static final ModelLayerLocation COLD_CHICKEN = registerLayer("cold_chicken");
    public static final ModelLayerLocation COLD_CHICKEN_BABY = registerLayer("cold_chicken_baby");

    public static final ModelLayerLocation COLD_PIG = registerLayer("cold_pig");
    public static final ModelLayerLocation COLD_PIG_BABY = registerLayer("cold_pig_baby");
    public static final ModelLayerLocation NEW_PIG = registerLayer("new_pig");
    public static final ModelLayerLocation NEW_PIG_BABY = registerLayer("new_pig_baby");

    private static ModelLayerLocation registerLayer(String name) {
        // “main” is the layer variant—most models just use “main”
        return new ModelLayerLocation(ResourceLocation.withDefaultNamespace(name), "main");
    }
}
