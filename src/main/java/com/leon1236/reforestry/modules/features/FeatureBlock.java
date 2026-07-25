package com.leon1236.reforestry.modules.features;

import java.util.function.Function;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FeatureBlock<B extends Block> extends ModFeature {
    public static BlockBehaviour.Properties seededProperties(Identifier registryId) {
        return BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, registryId));
    }

    private final B block;
    @Nullable
    private final BlockItem item;

    public FeatureBlock(Identifier moduleId, String name, Identifier registryId,
                         Supplier<B> blockConstructor,
                         @Nullable Function<B, BlockItem> itemConstructor) {
        super(moduleId, name);
        this.block = Registry.register(BuiltInRegistries.BLOCK, registryId, blockConstructor.get());
        this.item = itemConstructor == null ? null
                : Registry.register(BuiltInRegistries.ITEM, registryId, itemConstructor.apply(this.block));
    }

    public B block() {
        return block;
    }

    @Nullable
    public BlockItem item() {
        return item;
    }
}
