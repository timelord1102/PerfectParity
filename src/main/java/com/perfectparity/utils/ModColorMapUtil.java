package com.perfectparity.utils;

public interface ModColorMapUtil {
    static int get(double d, double e, int[] is, int i) {
        e *= d;
        int j = (int)(((double)1.0F - d) * (double)255.0F);
        int k = (int)(((double)1.0F - e) * (double)255.0F);
        int l = k << 8 | j;
        return l >= is.length ? i : is[l];
    }
}