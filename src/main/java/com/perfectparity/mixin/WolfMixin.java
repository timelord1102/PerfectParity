package com.perfectparity.mixin;

import com.google.common.collect.Maps;
import com.perfectparity.entity.utils.WolfSoundVariant;
import com.perfectparity.sound.ModSounds;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(Wolf.class)
public class WolfMixin {
    private static final EntityDataAccessor<String> DATA_SOUND_VARIANT_ID = SynchedEntityData.defineId(Wolf.class, EntityDataSerializers.STRING);
    private static final Map<WolfSoundVariant, Map<String, SoundEvent>> SOUND_VARIANT_MAP =
            Util.make(Maps.newEnumMap(WolfSoundVariant.class), map -> {
                map.put(WolfSoundVariant.CLASSIC, ModSounds.WOLF_CLASSIC);
                map.put(WolfSoundVariant.ANGRY, ModSounds.WOLF_ANGRY);
                map.put(WolfSoundVariant.BIG, ModSounds.WOLF_BIG);
                map.put(WolfSoundVariant.CUTE, ModSounds.WOLF_CUTE);
                map.put(WolfSoundVariant.GRUMPY, ModSounds.WOLF_GRUMPY);
                map.put(WolfSoundVariant.PUGLIN, ModSounds.WOLF_PUGLIN);
                map.put(WolfSoundVariant.SAD, ModSounds.WOLF_SAD);
            });


    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        compoundTag.putString("sound_variant", this.getTypeSoundVariant());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        ((Entity)(Object)this).getEntityData().set(DATA_SOUND_VARIANT_ID, compoundTag.getString("sound_variant"));
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_SOUND_VARIANT_ID, "minecraft:normal");
    }

    public WolfSoundVariant getSoundVariant() {
        return WolfSoundVariant.getById(this.getTypeSoundVariant());
    }

    private String getTypeSoundVariant(){
        return ((Entity)(Object)this).getEntityData().get(DATA_SOUND_VARIANT_ID);
    }

    public void setSoundVariant(WolfSoundVariant wolfSoundVariant) {
        ((Entity)(Object)this).getEntityData().set(DATA_SOUND_VARIANT_ID, wolfSoundVariant.getIdentifier());
    }

    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    public void defineSynchedData(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason entitySpawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        this.setSoundVariant(WolfSoundVariant.getRandom());
    }

    /**
     * @author timelord1102
     * @reason adding sound variant logic
     */
    @Overwrite
    public SoundEvent getAmbientSound() {
        if (((NeutralMob)this).isAngry()) {
            return (SoundEvent)SOUND_VARIANT_MAP.get(this.getSoundVariant()).get("growl");
        } else if (((Entity)(Object)this).getRandom().nextInt(3) == 0) {
            return ((TamableAnimal)(Object)this).isTame() && ((LivingEntity)(Object)this).getHealth() < 20.0F ? SOUND_VARIANT_MAP.get(this.getSoundVariant()).get("whine") : SOUND_VARIANT_MAP.get(this.getSoundVariant()).get("pant");
        } else {
            return (SoundEvent)SOUND_VARIANT_MAP.get(this.getSoundVariant()).get("ambient");
        }
    }

    /**
     * @author timelord1102
     * @reason adding sound variant logic
     */
    @Overwrite
    public SoundEvent getHurtSound(DamageSource damageSource) {
        return ((WolfInvoker) this).callCanArmorAbsorb(damageSource) ? SoundEvents.WOLF_ARMOR_DAMAGE : SOUND_VARIANT_MAP.get(this.getSoundVariant()).get("hurt");
    }

    /**
     * @author timelord1102
     * @reason adding sound variant logic
     */
    @Overwrite
    public SoundEvent getDeathSound() {
        return SOUND_VARIANT_MAP.get(this.getSoundVariant()).get("death");
    }

    /**
     * @author timelord1102
     * @reason adding sound variant logic
     */
    @Overwrite
    public Wolf getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        Wolf wolf = (Wolf)EntityType.WOLF.create(serverLevel, EntitySpawnReason.BREEDING);
        if (wolf != null && ageableMob instanceof Wolf wolf2) {
            if (((Entity)(Object)this).getRandom().nextBoolean()) {
                wolf.setVariant(((Wolf)(Object)this).getVariant());
            } else {
                wolf.setVariant(wolf2.getVariant());
            }

            if (((Wolf)(Object)this).isTame()) {
                ((Wolf) wolf).setOwnerUUID(((Wolf)(Object)this).getOwnerUUID());
                wolf.setTame(true, true);
                DyeColor dyeColor = ((Wolf)(Object)this).getCollarColor();
                DyeColor dyeColor2 = wolf2.getCollarColor();
                ((WolfInvoker) (Object) wolf).canSetCollarColor(DyeColor.getMixedColor(serverLevel, dyeColor, dyeColor2));
            }

            ((WolfMixin)(Object)wolf).setSoundVariant(WolfSoundVariant.getRandom());
        }

        return wolf;
    }
}
