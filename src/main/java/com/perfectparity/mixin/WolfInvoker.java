package com.perfectparity.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Wolf.class)
public interface WolfInvoker {
    @Invoker("canArmorAbsorb")
    boolean callCanArmorAbsorb(DamageSource damageSource);

    @Invoker("setCollarColor")
    void canSetCollarColor(DyeColor dyeColor);
}
