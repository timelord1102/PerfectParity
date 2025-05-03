package com.perfectparity.world.item;

import com.perfectparity.world.level.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

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
        if (!FabricLoader.getInstance().isModLoaded("wilderwild")) {
            WILDFLOWERS = registerBlock(ModBlocks.WILDFLOWERS);
        } else WILDFLOWERS = null;
        LEAF_LITTER = registerBlock(ModBlocks.LEAF_LITTER);
        FIREFLY_BUSH = registerBlock(ModBlocks.FIREFLY_BUSH);
        BLUE_EGG = registerItem(itemId("blue_egg"), new CustomEggItem("cold", new Item.Properties().stacksTo(16)));
        BROWN_EGG = registerItem(itemId("brown_egg"), new CustomEggItem("warm", new Item.Properties().stacksTo(16)));
    }

    private static ResourceKey<Item> itemId(String string) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(string));
    }

    public static Item registerBlock(Block block) {
        return registerBlock(new BlockItem(block, new Item.Properties()));
    }


    public static Item registerBlock(BlockItem blockItem) {
        return registerBlock(blockItem.getBlock(), blockItem);
    }

    public static Item registerBlock(Block block, Item item) {
        return registerItem(BuiltInRegistries.BLOCK.getKey(block), item);
    }

    public static Item registerItem(ResourceLocation resourceLocation, Item item) {
        return registerItem(ResourceKey.create(BuiltInRegistries.ITEM.key(), resourceLocation), item);
    }

    public static Item registerItem(ResourceKey<Item> resourceKey, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).registerBlocks(Item.BY_BLOCK, item);
        }

        return (Item)Registry.register(BuiltInRegistries.ITEM, resourceKey, item);
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
        if (!(WILDFLOWERS == null)) {
            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                    .register((itemGroup) -> itemGroup.addAfter(Items.PINK_PETALS, ModItems.WILDFLOWERS));
            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                    .register((itemGroup) -> itemGroup.addAfter(ModItems.WILDFLOWERS, ModItems.LEAF_LITTER));
        } else {
            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                    .register((itemGroup) -> itemGroup.addAfter(Items.PINK_PETALS, ModItems.LEAF_LITTER));
        }
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.addAfter(Items.EGG, ModItems.BROWN_EGG));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.addAfter(ModItems.BROWN_EGG, ModItems.BLUE_EGG));

        registerFuels();
        registerCompostable();
    }

    public static void registerFuels() {
        FuelRegistry.INSTANCE.add(LEAF_LITTER, 100);
        FuelRegistry.INSTANCE.add(SHORT_DRY_GRASS, 100);
        FuelRegistry.INSTANCE.add(TALL_DRY_GRASS, 100);
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
