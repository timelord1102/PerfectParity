package com.perfectparity.mixin;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OverworldBiomeBuilder.class)
public class BiomeBuilderMixin {
    @Shadow
    @Mutable
    @Final
    private ResourceKey<Registry<OverworldBiomeBuilder>>[][] PLATEAU_BIOMES;

    @Shadow @Mutable @Final
    private ResourceKey<Registry<OverworldBiomeBuilder>>[][] PLATEAU_BIOMES_VARIANT;


    /**
     *
     * Changes biome spawn behavior to be more in line with 1.21.5. Greater Pale Garden sizes
     *
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void overridePlateauBiomes(CallbackInfo ci) {
        this.PLATEAU_BIOMES = new ResourceKey[][] {
                { Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA },
                { Biomes.MEADOW, Biomes.MEADOW, Biomes.FOREST, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA },
                { Biomes.MEADOW, Biomes.MEADOW, Biomes.MEADOW, Biomes.MEADOW, Biomes.PALE_GARDEN },
                { Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, Biomes.FOREST, Biomes.FOREST, Biomes.JUNGLE },
                { Biomes.BADLANDS, Biomes.BADLANDS, Biomes.BADLANDS, Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS }
        };

        this.PLATEAU_BIOMES_VARIANT = new ResourceKey[][] {
                { Biomes.ICE_SPIKES, null, null, null, null },
                { Biomes.CHERRY_GROVE, null, Biomes.MEADOW, Biomes.MEADOW, Biomes.OLD_GROWTH_PINE_TAIGA },
                { Biomes.CHERRY_GROVE, Biomes.CHERRY_GROVE, Biomes.FOREST, Biomes.BIRCH_FOREST, null },
                { null, null, null, null, null },
                { Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, null, null }
        };
    }
}
