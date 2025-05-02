package com.perfectparity.world.item;

import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModItems {
    public static final Item BUSH;
    public static final Item CACTUS_FLOWER;
    public static final Item SHORT_DRY_GRASS;
    public static final Item TALL_DRY_GRASS;
    // public static final Item TEST_BLOCK;
    // public static final Item TEST_INSTANCE_BLOCK;
    public static final Item WILDFLOWERS;
    public static final Item LEAF_LITTER;
    public static final Item FIREFLY_BUSH;

    public static final Item BROWN_EGG;
    public static final Item BLUE_EGG;

    static {
        BUSH = registerBlock(ModBlocks.BUSH);
        CACTUS_FLOWER = registerBlock(ModBlocks.CACTUS_FLOWER);
        SHORT_DRY_GRASS = registerBlock(ModBlocks.SHORT_DRY_GRASS);
        TALL_DRY_GRASS = registerBlock(ModBlocks.TALL_DRY_GRASS);
        // TEST_BLOCK = registerBlock(ModBlocks.TEST_BLOCK);
        // TEST_INSTANCE_BLOCK = registerBlock(ModBlocks.TEST_INSTANCE_BLOCK);
        WILDFLOWERS = registerBlock(ModBlocks.WILDFLOWERS);
        LEAF_LITTER = registerBlock(ModBlocks.LEAF_LITTER);
        FIREFLY_BUSH = registerBlock(ModBlocks.FIREFLY_BUSH);
        BLUE_EGG = registerItem(itemId("blue_egg"), properties -> new CustomEggItem("cold", properties), new Item.Properties().stacksTo(16));
        BROWN_EGG = registerItem(itemId("brown_egg"), properties -> new CustomEggItem("warm", properties), new Item.Properties().stacksTo(16));
    }

    private static ResourceKey<Item> itemId(String string) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(string));
    }

    public static Item registerBlock(Block block) {
        return registerBlock(block, BlockItem::new);
    }

    public static Item registerBlock(Block block, BiFunction<Block, Item.Properties, Item> biFunction) {
        return registerBlock(block, biFunction, new Item.Properties());
    }

    public static Item registerBlock(Block block, BiFunction<Block, Item.Properties, Item> biFunction, Item.Properties properties) {
        return registerItem(blockIdToItemId(block.builtInRegistryHolder().key()), (propertiesx) -> biFunction.apply(block, propertiesx), properties.useBlockDescriptionPrefix());
    }

    public static Item registerItem(ResourceKey<Item> resourceKey, Function<Item.Properties, Item> function, Item.Properties properties) {
        Item item = function.apply(properties.setId(resourceKey));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, resourceKey, item);
    }

    private static ResourceKey<Item> blockIdToItemId(ResourceKey<Block> resourceKey) {
        return ResourceKey.create(Registries.ITEM, resourceKey.location());
    }

    public static void initialize() {
        // add after short dry grass later
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup) -> itemGroup.addAfter(Items.FERN, ModItems.SHORT_DRY_GRASS));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup) -> itemGroup.addAfter(Items.LARGE_FERN, ModItems.TALL_DRY_GRASS));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup) -> itemGroup.addAfter(ModItems.SHORT_DRY_GRASS, ModItems.BUSH));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup) -> itemGroup.addAfter(Items.SPORE_BLOSSOM, ModItems.FIREFLY_BUSH));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup) -> itemGroup.addAfter(Items.TORCHFLOWER, ModItems.CACTUS_FLOWER));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup) -> itemGroup.addAfter(Items.PINK_PETALS, ModItems.WILDFLOWERS));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup) -> itemGroup.addAfter(ModItems.WILDFLOWERS, ModItems.LEAF_LITTER));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.addAfter(Items.EGG, ModItems.BROWN_EGG));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.addAfter(ModItems.BROWN_EGG, ModItems.BLUE_EGG));

        registerFuels();
        registerCompostable();
    }

    public static void registerFuels() {
        FuelRegistryEvents.BUILD.register((builder, context) -> builder.add(LEAF_LITTER, 100));
        FuelRegistryEvents.BUILD.register((builder, context) -> builder.add(SHORT_DRY_GRASS, 100));
        FuelRegistryEvents.BUILD.register((builder, context) -> builder.add(TALL_DRY_GRASS, 100));
    }

    public static void registerCompostable() {
        CompostingChanceRegistry.INSTANCE.add(ModItems.BUSH, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.FIREFLY_BUSH, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CACTUS_FLOWER, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.LEAF_LITTER, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.SHORT_DRY_GRASS, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.TALL_DRY_GRASS, 0.3f);
    }

}
