package com.perfectparity.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class FireflyParticle extends TextureSheetParticle {
    private static final float PARTICLE_FADE_OUT_LIGHT_TIME = 0.3F;
    private static final float PARTICLE_FADE_IN_LIGHT_TIME = 0.1F;
    private static final float PARTICLE_FADE_OUT_ALPHA_TIME = 0.5F;
    private static final float PARTICLE_FADE_IN_ALPHA_TIME = 0.3F;
    private static final int PARTICLE_MIN_LIFETIME = 36;
    private static final int PARTICLE_MAX_LIFETIME = 180;

    FireflyParticle(ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
        super(clientLevel, d, e, f, g, h, i);
        this.speedUpWhenYMotionIsBlocked = true;
        this.friction = 0.96F;
        this.quadSize *= 0.75F;
        this.yd *= (double)0.8F;
        this.xd *= (double)0.8F;
        this.zd *= (double)0.8F;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getLightColor(float f) {
        return (int)(255.0F * getFadeAmount(this.getLifetimeProgress((float)this.age + f), 0.1F, 0.3F));
    }

    public void tick() {
        super.tick();
        if (!this.level.getBlockState(BlockPos.containing(this.x, this.y, this.z)).isAir()) {
            this.remove();
        } else {
            this.setAlpha(getFadeAmount(this.getLifetimeProgress((float)this.age), 0.3F, 0.5F));
            if (Math.random() > 0.95 || this.age == 1) {
                this.setParticleSpeed((double)-0.05F + (double)0.1F * Math.random(), (double)-0.05F + (double)0.1F * Math.random(), (double)-0.05F + (double)0.1F * Math.random());
            }

        }
    }

    private float getLifetimeProgress(float f) {
        return Mth.clamp(f / (float)this.lifetime, 0.0F, 1.0F);
    }

    private static float getFadeAmount(float f, float g, float h) {
        if (f >= 1.0F - g) {
            return (1.0F - f) / g;
        } else {
            return f <= h ? f / h : 1.0F;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class FireflyProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public FireflyProvider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            FireflyParticle fireflyParticle = new FireflyParticle(clientLevel, d, e, f, (double)0.5F - clientLevel.random.nextDouble(), clientLevel.random.nextBoolean() ? h : -h, (double)0.5F - clientLevel.random.nextDouble());
            fireflyParticle.setLifetime(clientLevel.random.nextIntBetweenInclusive(36, 180));
            fireflyParticle.scale(1.5F);
            fireflyParticle.pickSprite(this.sprite);
            fireflyParticle.setAlpha(0.0F);
            return fireflyParticle;
        }
    }
}
