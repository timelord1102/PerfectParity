package com.perfectparity.world.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ColdThrownEgg extends CustomThrownEgg {

    public ColdThrownEgg(EntityType<CustomThrownEgg> entityType, double d, double e, double f, Level level) {
        super(entityType, d, e, f, level, "cold");
    }

    public ColdThrownEgg(EntityType<CustomThrownEgg> entityType, Level level, LivingEntity livingEntity) {
        super(entityType, level, livingEntity, "cold");
    }


    public ColdThrownEgg(EntityType<CustomThrownEgg> entityType, Level world) {
        super(entityType, world, "cold");
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.BLUE_EGG;
    }
}
