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

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        shapeless(RecipeCategory.MISC, Items.PINK_DYE, 1).requires(ModBlocks.CACTUS_FLOWER)
                .unlockedBy(getHasName(ModBlocks.CACTUS_FLOWER), has(ModBlocks.CACTUS_FLOWER))
                .save(exporter,
                        String.valueOf(ResourceLocation.fromNamespaceAndPath(ResourceLocation.DEFAULT_NAMESPACE, "pink_dye_from_cactus_flower")));
        shaped(RecipeCategory.FOOD, Items.CAKE)
                .define('M', Items.MILK_BUCKET)
                .define('S', Items.SUGAR)
                .define('E', ModItemTagProvider.EGGS)
                .define('W', Items.WHEAT)
                .pattern("MMM")
                .pattern("SES")
                .pattern("WWW")
                .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(exporter);
    }

    @Override
    public String getName() {
        return "";
    }
}
