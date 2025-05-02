package com.perfectparity.renderer;

import com.perfectparity.entity.custom.MobVariant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class CowRenderState extends LivingEntityRenderState {
    @Nullable
    public MobVariant variant;

    public CowRenderState() {
    }
}
