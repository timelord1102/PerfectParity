package com.perfectparity.mixin;

import com.perfectparity.entity.custom.MobVariant;
import com.perfectparity.utils.interfaces.VariantMob;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Chicken.class)
public class ChickenMixin extends Animal implements VariantMob {
    @Unique
    private static final EntityDataAccessor<String> DATA_VARIANT_ID =
            SynchedEntityData.defineId(Chicken.class, EntityDataSerializers.STRING);

    protected ChickenMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Nullable
    public Chicken getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        Chicken chicken = (Chicken)EntityType.CHICKEN.create(serverLevel, EntitySpawnReason.BREEDING);
        if (chicken != null && ageableMob instanceof Chicken chicken2) {
            ((VariantMob) chicken).setVariant(this.random.nextBoolean() ? this.getVariant() : ((VariantMob) chicken2).getVariant());
        }

        return chicken;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VARIANT_ID, "normal");
    }

    public MobVariant getVariant() {
        return MobVariant.getById(this.getTypeVariant());
    }

    private String getTypeVariant(){
        return this.entityData.get(DATA_VARIANT_ID);
    }

    public void setVariant(MobVariant chickenVariant) {
        this.entityData.set(DATA_VARIANT_ID, chickenVariant.getId());
    }


    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_VARIANT_ID, tag.getString("variant"));

    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficulty,
                                        EntitySpawnReason reason, @Nullable SpawnGroupData data) {

        if (reason != EntitySpawnReason.BREEDING && reason != EntitySpawnReason.TRIGGERED) {
            Holder<Biome> biomeHolder = accessor.getBiome(this.blockPosition());
            MobVariant variant = MobVariant.getFromBiome(biomeHolder);
            this.setVariant(variant);
        }
        return super.finalizeSpawn(accessor, difficulty, reason, data);
    }
}
