package com.perfectparity.entity.renderer;

import com.perfectparity.entity.utils.MobVariant;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import org.jetbrains.annotations.Nullable;

public class ModPigRenderState extends PigRenderState {
    @Nullable
    public MobVariant variant;

    public ModPigRenderState() {
        super();
    }
}
