package com.perfectparity.utils.interfaces;

import com.perfectparity.entity.custom.MobVariant;
import net.minecraft.network.syncher.EntityDataAccessor;

public interface VariantMob {

    MobVariant getVariant();

    void setVariant(MobVariant variant);

    EntityDataAccessor<MobVariant> getDataVariantId();
}
