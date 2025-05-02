package com.perfectparity.renderer;

import com.perfectparity.entity.custom.MobVariant;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import org.jetbrains.annotations.Nullable;

public class ModPigRenderState extends PigRenderState {
    @Nullable
    public MobVariant variant;

    public ModPigRenderState() {
        super();
    }
}
