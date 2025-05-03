package com.perfectparity.world.item;

import com.perfectparity.entity.utils.MobVariant;
import com.perfectparity.utils.interfaces.VariantMob;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class CustomThrownEgg extends ThrowableItemProjectile {
    private String variant;

    public CustomThrownEgg(EntityType<? extends CustomThrownEgg> entityType, Level level) {
        super(entityType, level);
    }

    public CustomThrownEgg(Level level, double d, double e, double f, ItemStack itemStack, String variant) {
        super(EntityType.EGG, d, e, f, level, itemStack);
        this.variant = variant;
    }

    public CustomThrownEgg(Level level, LivingEntity livingEntity, ItemStack itemStack, String variant) {
        super(EntityType.EGG, livingEntity, level, itemStack);
        this.variant = variant;
    }

    public void handleEntityEvent(byte b) {
        if (b == 3) {
            double d = 0.08;

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - (double)0.5F) * 0.08, ((double)this.random.nextFloat() - (double)0.5F) * 0.08, ((double)this.random.nextFloat() - (double)0.5F) * 0.08);
            }
        }

    }
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            if (this.random.nextInt(8) == 0) {
                int i = 1;
                if (this.random.nextInt(32) == 0) {
                    i = 4;
                }
                for(int j = 0; j < i; ++j) {
                    Chicken chicken = EntityType.CHICKEN.create(this.level(), EntitySpawnReason.TRIGGERED);
                    if (chicken != null) {
                        chicken.setAge(-24000);
                        ((VariantMob) chicken).setVariant(MobVariant.getById(variant));
                        chicken.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                        if (!chicken.fudgePositionAfterSizeChange(EntityDimensions.fixed(0.0F, 0.0F))) {
                            break;
                        }
                        this.level().addFreshEntity(chicken);
                    }
                }
            }

            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.BLUE_EGG; // or your custom item
    }
}
