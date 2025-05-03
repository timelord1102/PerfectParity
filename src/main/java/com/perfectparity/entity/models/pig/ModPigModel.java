package com.perfectparity.entity.models.pig;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

@Environment(EnvType.CLIENT)
public class ModPigModel<T extends Entity> extends QuadrupedModel<T> {
    public static final CubeDeformation cubeDeformation = CubeDeformation.NONE;
    public ModPigModel(ModelPart modelPart) {
        super(modelPart, false, 4.0F, 4.0F, 2.0F, 2.0F, 24);
    }

    public static LayerDefinition createBodyLayer() {
        return LayerDefinition.create(createBasePigModel(), 64, 64);
    }


    protected static MeshDefinition createBasePigModel() {
        MeshDefinition meshDefinition = QuadrupedModel.createBodyMesh(6, cubeDeformation);
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, cubeDeformation).texOffs(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, cubeDeformation), PartPose.offset(0.0F, 12.0F, -6.0F));
        return meshDefinition;
    }
}
