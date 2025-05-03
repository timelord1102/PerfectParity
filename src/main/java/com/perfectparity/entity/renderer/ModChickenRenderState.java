package com.perfectparity.entity.renderer;

import com.perfectparity.entity.utils.MobVariant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ModChickenRenderState extends LivingEntityRenderState {
    public float flap;
    public float flapSpeed;
    @Nullable
    public MobVariant variant;

    public ModChickenRenderState() {
    }
}
