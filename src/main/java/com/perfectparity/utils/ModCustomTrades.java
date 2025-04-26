package com.perfectparity.utils;

import com.perfectparity.world.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

public class ModCustomTrades {
    public static void registerCustomTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories ->{
                    factories.add((entity, randomSource) -> new MerchantOffer(
                            new ItemCost(Items.EMERALD, 3),
                            new ItemStack(ModItems.FIREFLY_BUSH, 1),
                            12, 12, 0.075f));
                });
    }
}
