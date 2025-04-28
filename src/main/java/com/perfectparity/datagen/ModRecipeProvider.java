package com.perfectparity.datagen;

import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.MISC, Items.PINK_DYE, 1).requires(ModBlocks.CACTUS_FLOWER)
                        .unlockedBy(getHasName(ModBlocks.CACTUS_FLOWER), has(ModBlocks.CACTUS_FLOWER))
                        .save(exporter,
                        String.valueOf(ResourceLocation.fromNamespaceAndPath(ResourceLocation.DEFAULT_NAMESPACE, "pink_dye_from_cactus_flower")));
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
