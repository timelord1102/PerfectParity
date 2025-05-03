package com.perfectparity.entity.models.pig;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

@Environment(EnvType.CLIENT)
public class ColdPigModel extends ModPigModel {

    public ColdPigModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = createBasePigModel();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F).texOffs(28, 32).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 11.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition createBabyBodyLayer() {
        MeshDefinition meshDefinition = createBasePigModel();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F).texOffs(28, 32).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 11.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
        return LayerDefinition.create(PigModel.BABY_TRANSFORMER.apply(meshDefinition), 64, 64);
    }
}
