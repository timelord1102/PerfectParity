package com.perfectparity.world.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CustomEggItem extends EggItem {
    private final String variant;

    public CustomEggItem(String variant, Item.Properties properties) {
        super(properties);
        this.variant = variant;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (level instanceof ServerLevel serverLevel) {
            EntityType<CustomThrownEgg> entityType = Objects.equals(variant, "cold") ? ModEntityTypes.BLUE_EGG : ModEntityTypes.BROWN_EGG;
            System.out.println(entityType);
            CustomThrownEgg egg = variant.equals("cold") ? new ColdThrownEgg(entityType, serverLevel, player) : new CustomThrownEgg(entityType, serverLevel, player, variant);
            egg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            serverLevel.addFreshEntity(egg);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemStack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public @NotNull Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        EntityType<CustomThrownEgg> entityType = Objects.equals(variant, "cold") ? ModEntityTypes.BLUE_EGG : ModEntityTypes.BROWN_EGG;
        System.out.println(entityType);
        return variant.equals("cold") ? new ColdThrownEgg(entityType, position.x(), position.y(), position.z(), level) : new CustomThrownEgg(entityType, position.x(), position.y(), position.z(), level, variant);
    }
}

