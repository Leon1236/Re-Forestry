package com.leon1236.reforestry.modules.features;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public class FeatureBlockGroup<B extends Block, S extends IBlockSubtype> extends FeatureGroup<S, FeatureBlock<B>> {

    private FeatureBlockGroup(ImmutableMap<S, FeatureBlock<B>> featureByType) {
        super(featureByType);
    }

    public ItemStack stack(S subtype) {
        BlockItem item = get(subtype).item();
        if (item == null) {
            throw new IllegalStateException("No item for subtype: " + subtype);
        }
        return new ItemStack(item);
    }

    public Block[] blockArray() {
        return getAll().values().stream().map(FeatureBlock::block).toArray(Block[]::new);
    }

    @FunctionalInterface
    public interface ItemFactory<B extends Block, S extends IBlockSubtype> {
        BlockItem create(B block, S type, Item.Properties properties);
    }

    public static class Builder<B extends Block, S extends IBlockSubtype> extends FeatureGroup.Builder<S, Builder<B, S>> {
        private final BiFunction<S, BlockBehaviour.Properties, B> constructor;
        @Nullable
        private ItemFactory<B, S> itemConstructor;

        public Builder(Identifier moduleId, BiFunction<S, BlockBehaviour.Properties, B> constructor) {
            super(moduleId);
            this.constructor = constructor;
        }

        public Builder<B, S> item(BiFunction<B, Item.Properties, BlockItem> itemConstructor) {
            this.itemConstructor = (block, type, properties) -> itemConstructor.apply(block, properties);
            return this;
        }

        public Builder<B, S> itemWithType(ItemFactory<B, S> itemConstructor) {
            this.itemConstructor = itemConstructor;
            return this;
        }

        public FeatureBlockGroup<B, S> create() {
            ImmutableMap.Builder<S, FeatureBlock<B>> map = ImmutableMap.builder();
            for (S subtype : subtypes) {
                Identifier registryId = getRegistryId(subtype);
                Function<B, BlockItem> boundItemConstructor = itemConstructor == null
                        ? null
                        : block -> itemConstructor.create(block, subtype, FeatureItem.seededBlockItemProperties(registryId));
                map.put(subtype, new FeatureBlock<>(moduleId, getRegistryName(subtype), registryId,
                        () -> constructor.apply(subtype, FeatureBlock.seededProperties(registryId)),
                        boundItemConstructor));
            }
            return new FeatureBlockGroup<>(map.build());
        }
    }
}
