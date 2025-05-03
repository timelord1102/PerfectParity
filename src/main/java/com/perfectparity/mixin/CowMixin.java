package com.perfectparity.mixin;

import com.perfectparity.entity.utils.MobVariant;
import com.perfectparity.utils.interfaces.VariantMob;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;


@Mixin(Cow.class)
public abstract class CowMixin extends Animal implements VariantMob {

    @Unique
    private static final EntityDataAccessor<String> DATA_VARIANT_ID =
            SynchedEntityData.defineId(Cow.class, EntityDataSerializers.STRING);

    protected CowMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * @author timelord1102
     * @reason custom variant logic
     */
    @Overwrite
    @Nullable
    public Cow getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        Cow cow = (Cow)EntityType.COW.create(serverLevel);
        if (cow != null && ageableMob instanceof Cow cow2) {
            ((VariantMob) cow).setVariant(this.random.nextBoolean() ? this.getVariant() : ((VariantMob) cow2).getVariant());
        }

        return cow;
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

    public void setVariant(MobVariant cowVariant) {
        this.entityData.set(DATA_VARIANT_ID, cowVariant.getId());
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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {

        if (mobSpawnType != MobSpawnType.BREEDING) {
            Holder<Biome> biomeHolder = serverLevelAccessor.getBiome(this.blockPosition());
            MobVariant variant = MobVariant.getFromBiome(biomeHolder);
            this.setVariant(variant);
        }
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType,spawnGroupData);
    }
}


