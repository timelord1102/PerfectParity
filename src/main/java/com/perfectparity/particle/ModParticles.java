package com.perfectparity.particle;

import com.perfectparity.PerfectParity;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModParticles {

    public static final SimpleParticleType FIREFLY = registerParticle("firefly", FabricParticleTypes.simple(true));

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.tryBuild(ResourceLocation.DEFAULT_NAMESPACE, name), particleType);
    }

    public static void registerParticles() {
        PerfectParity.LOGGER.info("Registering Particles for PerfectParity");
    }
}
