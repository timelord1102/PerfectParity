package com.perfectparity.particle;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class FallingLeavesParticle extends TextureSheetParticle {
    private static final float ACCELERATION_SCALE = 0.0025F;
    private static final int INITIAL_LIFETIME = 300;
    private static final int CURVE_ENDPOINT_TIME = 300;
    private float rotSpeed;
    private final float particleRandom;
    private final float spinAcceleration;
    private final float windBig;
    private boolean swirl;
    private boolean flowAway;
    private double xaFlowScale;
    private double zaFlowScale;
    private double swirlPeriod;

    protected FallingLeavesParticle(ClientLevel clientLevel, double d, double e, double f, SpriteSet spriteSet, float g, float h, boolean bl, boolean bl2, float i, float j) {
        super(clientLevel, d, e, f);
        this.setSprite(spriteSet.get(this.random.nextInt(12), 12));
        this.rotSpeed = (float)Math.toRadians(this.random.nextBoolean() ? (double)-30.0F : (double)30.0F);
        this.particleRandom = this.random.nextFloat();
        this.spinAcceleration = (float)Math.toRadians(this.random.nextBoolean() ? (double)-5.0F : (double)5.0F);
        this.windBig = h;
        this.swirl = bl;
        this.flowAway = bl2;
        this.lifetime = 300;
        this.gravity = g * 1.2F * 0.0025F;
        float k = i * (this.random.nextBoolean() ? 0.05F : 0.075F);
        this.quadSize = k;
        this.setSize(k, k);
        this.friction = 1.0F;
        this.yd = (double)(-j);
        this.xaFlowScale = Math.cos(Math.toRadians((double)(this.particleRandom * 60.0F))) * (double)this.windBig;
        this.zaFlowScale = Math.sin(Math.toRadians((double)(this.particleRandom * 60.0F))) * (double)this.windBig;
        this.swirlPeriod = Math.toRadians((double)(1000.0F + this.particleRandom * 3000.0F));
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        }

        if (!this.removed) {
            float f = (float)(300 - this.lifetime);
            float g = Math.min(f / 300.0F, 1.0F);
            double d = (double)0.0F;
            double e = (double)0.0F;
            if (this.flowAway) {
                d += this.xaFlowScale * Math.pow((double)g, (double)1.25F);
                e += this.zaFlowScale * Math.pow((double)g, (double)1.25F);
            }

            if (this.swirl) {
                d += (double)g * Math.cos((double)g * this.swirlPeriod) * (double)this.windBig;
                e += (double)g * Math.sin((double)g * this.swirlPeriod) * (double)this.windBig;
            }

            this.xd += d * (double)0.0025F;
            this.zd += e * (double)0.0025F;
            this.yd -= (double)this.gravity;
            this.rotSpeed += this.spinAcceleration / 20.0F;
            this.oRoll = this.roll;
            this.roll += this.rotSpeed / 20.0F;
            this.move(this.xd, this.yd, this.zd);
            if (this.onGround || this.lifetime < 299 && (this.xd == (double)0.0F || this.zd == (double)0.0F)) {
                this.remove();
            }

            if (!this.removed) {
                this.xd *= (double)this.friction;
                this.yd *= (double)this.friction;
                this.zd *= (double)this.friction;
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class CherryProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public CherryProvider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            return new FallingLeavesParticle(clientLevel, d, e, f, this.sprites, 0.25F, 2.0F, false, true, 1.0F, 0.0F);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class PaleOakProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public PaleOakProvider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            return new FallingLeavesParticle(clientLevel, d, e, f, this.sprites, 0.07F, 10.0F, true, false, 2.0F, 0.021F);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class TintedLeavesProvider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet sprites;

        public TintedLeavesProvider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(ColorParticleOption colorParticleOption, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            Particle particle = new FallingLeavesParticle(clientLevel, d, e, f, this.sprites, 0.07F, 10.0F, true, false, 2.0F, 0.021F);
            particle.setColor(colorParticleOption.getRed(), colorParticleOption.getGreen(), colorParticleOption.getBlue());
            return particle;
        }
    }
}
