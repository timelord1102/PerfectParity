package com.perfectparity.mixin;

import com.perfectparity.entity.utils.MobVariant;
import com.perfectparity.utils.interfaces.VariantMob;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Pig.class)
public class PigMixin extends Animal implements VariantMob {

    @Final
    @Shadow
    private static EntityDataAccessor<Boolean> DATA_SADDLE_ID;
    @Final
    @Shadow
    private static EntityDataAccessor<Integer> DATA_BOOST_TIME;
    @Shadow private final ItemBasedSteering steering;

    @Unique
    private static final EntityDataAccessor<String> DATA_VARIANT_ID =
            SynchedEntityData.defineId(Pig.class, EntityDataSerializers.STRING);

    protected PigMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.steering = new ItemBasedSteering(this.entityData, DATA_BOOST_TIME, DATA_SADDLE_ID);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ItemTags.PIG_FOOD);
    }

    /**
     * @author timelord1102
     * @reason custom variant logic
     */
    @Overwrite
    public @Nullable Pig getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        Pig pig = (Pig)EntityType.PIG.create(serverLevel);
        if (pig != null && ageableMob instanceof Pig pig2) {
            ((VariantMob) pig).setVariant(this.random.nextBoolean() ? this.getVariant() : ((VariantMob) pig2).getVariant());
        }

        return pig;
    }

    @Override
    public MobVariant getVariant() {
        return MobVariant.getById(this.getTypeVariant());
    }

    private String getTypeVariant(){
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public void setVariant(MobVariant pigVariant) {
        this.entityData.set(DATA_VARIANT_ID, pigVariant.getId());
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        compoundTag.putString("variant", this.getTypeVariant());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        this.entityData.set(DATA_VARIANT_ID, compoundTag.getString("variant"));
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    public void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_VARIANT_ID, "normal");
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
