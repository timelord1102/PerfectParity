package com.perfectparity.mixin;

import com.perfectparity.entity.renderer.ModChickenRenderer;
import com.perfectparity.entity.renderer.ModCowRenderer;
import com.perfectparity.entity.renderer.ModPigRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(net.minecraft.client.renderer.entity.EntityRenderers.class)
public class MobRendererMixin {
    @Shadow
    @Final
    @Mutable
    private static java.util.Map<EntityType<?>, EntityRendererProvider<?>> PROVIDERS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onInitComplete(CallbackInfo ci) {
        PROVIDERS.put(
                EntityType.COW,
                (EntityRendererProvider) ModCowRenderer::new
        );
        PROVIDERS.put(
                EntityType.CHICKEN,
                (EntityRendererProvider) ModChickenRenderer::new
        );
        PROVIDERS.put(
                EntityType.PIG,
                (EntityRendererProvider) ModPigRenderer::new
        );
    }
}
