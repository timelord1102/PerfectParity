package com.perfectparity.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class DryFoliageColor {
    public static final int FOLIAGE_DRY_DEFAULT = -10732494;
    public static final int FOLIAGE_DRY_PALE_GARDEN = 10528412;
    private static int[] pixels = new int[65536];

    private DryFoliageColor() { }

    /** Initialize with your 2D colormap pixels (e.g. 256×256 gradient). */
    public static void init(int[] is) {
        pixels = is;
    }

    /**
     * Core lookup: map normalized (d,e) into your gradient.
     */
    public static int get(double d, double e) {
        d = Mth.clamp(d, 0.0F, 1.0F);
        e = Mth.clamp(e, 0.0F, 1.0F);
        return ModColorMapUtil.get(d, e, pixels, FOLIAGE_DRY_DEFAULT);
    }

    /**
     * Samples your gradient at the 4 biome‐corners around `pos` and averages
     * the ARGB channels to smooth transitions—just like Minecraft’s getBlockTint.
     */
    public static int getTint(BlockAndTintGetter world, BlockPos pos) {

        // positions of the four “corners”
        BlockPos north  = pos.north();
        BlockPos east   = pos.east();
        BlockPos ne     = north.east();

        // sample each corner
        int c1 = sample(world, pos);
        int c2 = sample(world, north);
        int c3 = sample(world, east);
        int c4 = sample(world, ne);

        // average ARGB channels
        int a = (((c1 >>>24)&0xFF) + ((c2>>>24)&0xFF) +
                ((c3>>>24)&0xFF) + ((c4>>>24)&0xFF)) / 4;
        int r = (((c1 >>>16)&0xFF) + ((c2>>>16)&0xFF) +
                ((c3>>>16)&0xFF) + ((c4>>>16)&0xFF)) / 4;
        int g = (((c1 >>> 8)&0xFF) + ((c2>>> 8)&0xFF) +
                ((c3>>> 8)&0xFF) + ((c4>>> 8)&0xFF)) / 4;
        int b = (((c1      )&0xFF) + ((c2     )&0xFF) +
                ((c3      )&0xFF) + ((c4     )&0xFF)) / 4;

        return (a<<24) | (r<<16) | (g<<8) | b;
    }

    /** Helper: look up the gradient based on that corner’s biome. */
    private static int sample(BlockAndTintGetter world, BlockPos at) {
        if (world.getBiomeFabric(at).is(Biomes.PALE_GARDEN)) {
            return FOLIAGE_DRY_PALE_GARDEN;
        }
        Biome biome = world.getBiomeFabric(at).value();
        double d    = Mth.clamp(biome.getBaseTemperature(), 0f, 1f);
        double e    = Mth.clamp(((GetBiomeDownfall) (Object) biome).projectParity$getDownfall(),0f, 1f);
        return get(d, e);
    }
}
