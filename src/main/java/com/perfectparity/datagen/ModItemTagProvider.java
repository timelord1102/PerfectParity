package com.perfectparity.datagen;

import com.perfectparity.world.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public static final TagKey<Item> FLOWERS = create("flowers");

    private static TagKey<Item> create(String string) {
        return TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(string));
    }

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        addVanillaTags();
        getOrCreateTagBuilder(ItemTags.BEE_FOOD).add(ModItems.CACTUS_FLOWER);
    }

    protected void addVanillaTags() {
        getOrCreateTagBuilder(FLOWERS).add(
                Items.CHERRY_LEAVES,
                Items.FLOWERING_AZALEA_LEAVES,
                Items.FLOWERING_AZALEA,
                Items.MANGROVE_PROPAGULE,
                Items.PINK_PETALS,
                //ModItems.WILDFLOWERS,
                Items.CHORUS_FLOWER,
                Items.SPORE_BLOSSOM,
                ModItems.CACTUS_FLOWER
        ).addOptionalTags(
                ItemTags.SMALL_FLOWERS,
                ConventionalItemTags.TALL_FLOWERS
        );
    }
}
