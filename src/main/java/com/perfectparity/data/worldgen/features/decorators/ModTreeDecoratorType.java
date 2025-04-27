package com.perfectparity.data.worldgen.features.decorators;

import com.perfectparity.PerfectParity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class ModTreeDecoratorType {
    public static final TreeDecoratorType<PlaceOnGroundDecorator> PLACE_ON_GROUND =
            Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE,
                    ResourceLocation.tryBuild(ResourceLocation.DEFAULT_NAMESPACE, "place_on_ground"),
                    new TreeDecoratorType<>(PlaceOnGroundDecorator.CODEC));
    public static final TreeDecoratorType<AttachedToLogsDecorator> ATTACHED_TO_LOGS =
            Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE,
                    ResourceLocation.tryBuild(ResourceLocation.DEFAULT_NAMESPACE, "attached_to_logs"),
                    new TreeDecoratorType<>(AttachedToLogsDecorator.CODEC));

    public static void registerTreeDecorators() {
        PerfectParity.LOGGER.info("PerfectParity: Registering tree decorators");
    }
}
