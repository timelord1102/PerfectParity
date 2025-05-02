package com.perfectparity.entity.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;

@Environment(EnvType.CLIENT)
public record AdultAndBabyModelPair<T extends Model>(T adultModel, T babyModel) {
    public AdultAndBabyModelPair(T adultModel, T babyModel) {
        this.adultModel = adultModel;
        this.babyModel = babyModel;
    }

    public T getModel(boolean bl) {
        return (T)(bl ? this.babyModel : this.adultModel);
    }

    public T adultModel() {
        return this.adultModel;
    }

    public T babyModel() {
        return this.babyModel;
    }
}
