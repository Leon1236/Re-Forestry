package com.leon1236.reforestry.modules.features;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableTable;
import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.leon1236.reforestry.api.core.IBlockSubtype;

public class FeatureBlockTable<B extends Block, R extends IBlockSubtype, C extends IBlockSubtype> {
    private final ImmutableTable<R, C, FeatureBlock<B>> featureByTypes;

    private FeatureBlockTable(ImmutableTable<R, C, FeatureBlock<B>> featureByTypes) {
        this.featureByTypes = featureByTypes;
    }

    public FeatureBlock<B> get(R rowType, C columnType) {
        FeatureBlock<B> feature = featureByTypes.get(rowType, columnType);
        if (feature == null) {
            throw new IllegalArgumentException("No feature registered for " + rowType + ", " + columnType);
        }
        return feature;
    }

    public Collection<FeatureBlock<B>> getRowFeatures(R rowType) {
        return featureByTypes.row(rowType).values();
    }

    public Collection<FeatureBlock<B>> getColumnFeatures(C columnType) {
        return featureByTypes.column(columnType).values();
    }

    public Collection<B> getRowBlocks(R rowType) {
        return getRowFeatures(rowType).stream().map(FeatureBlock::block).collect(Collectors.toList());
    }

    public Collection<B> getColumnBlocks(C columnType) {
        return getColumnFeatures(columnType).stream().map(FeatureBlock::block).collect(Collectors.toList());
    }

    public Collection<B> getBlocks() {
        return featureByTypes.values().stream().map(FeatureBlock::block).collect(Collectors.toList());
    }

    public Collection<BlockItem> getItems() {
        return featureByTypes.values().stream()
                .map(FeatureBlock::item)
                .filter(item -> item != null)
                .collect(Collectors.toList());
    }

    public Collection<FeatureBlock<B>> getFeatures() {
        return featureByTypes.values();
    }

    public ImmutableTable<R, C, FeatureBlock<B>> getFeatureByTypes() {
        return featureByTypes;
    }

    public ItemStack stack(R rowType, C columnType) {
        return stack(rowType, columnType, 1);
    }

    public ItemStack stack(R rowType, C columnType, int amount) {
        BlockItem item = get(rowType, columnType).item();
        if (item == null) {
            throw new IllegalStateException("No item for " + rowType + ", " + columnType);
        }
        return new ItemStack(item, amount);
    }

    @FunctionalInterface
    public interface BlockFactory<B extends Block, R extends IBlockSubtype, C extends IBlockSubtype> {
        B create(R row, C column, BlockBehaviour.Properties properties);
    }

    public static class Builder<B extends Block, R extends IBlockSubtype, C extends IBlockSubtype> {
        private final Identifier moduleId;
        private final BlockFactory<B, R, C> constructor;
        private final Set<R> rowTypes = new LinkedHashSet<>();
        private final Set<C> columnTypes = new LinkedHashSet<>();
        @Nullable
        private BiFunction<B, Item.Properties, BlockItem> itemConstructor;
        @Nullable
        private BiFunction<R, C, String> identifierFunction;

        public Builder(Identifier moduleId, BlockFactory<B, R, C> constructor) {
            this.moduleId = moduleId;
            this.constructor = constructor;
        }

        public Builder<B, R, C> rowTypes(R[] types) {
            this.rowTypes.addAll(Arrays.asList(types));
            return this;
        }

        public Builder<B, R, C> columnTypes(C[] types) {
            this.columnTypes.addAll(Arrays.asList(types));
            return this;
        }

        public Builder<B, R, C> item(BiFunction<B, Item.Properties, BlockItem> itemConstructor) {
            this.itemConstructor = itemConstructor;
            return this;
        }

        public Builder<B, R, C> identifier(BiFunction<R, C, String> identifierFunction) {
            this.identifierFunction = identifierFunction;
            return this;
        }

        public FeatureBlockTable<B, R, C> create() {
            ImmutableTable.Builder<R, C, FeatureBlock<B>> table = ImmutableTable.builder();
            for (R rowType : rowTypes) {
                for (C columnType : columnTypes) {
                    String name = identifierFunction != null
                            ? identifierFunction.apply(rowType, columnType)
                            : rowType.getSerializedName() + "_" + columnType.getSerializedName();
                    Identifier registryId = Identifier.fromNamespaceAndPath(moduleId.getNamespace(), name);
                    Function<B, BlockItem> boundItemConstructor = itemConstructor == null
                            ? null
                            : block -> itemConstructor.apply(block, FeatureItem.seededBlockItemProperties(registryId));
                    table.put(rowType, columnType, new FeatureBlock<>(moduleId, name, registryId,
                            () -> constructor.create(rowType, columnType, FeatureBlock.seededProperties(registryId)),
                            boundItemConstructor));
                }
            }
            return new FeatureBlockTable<>(table.build());
        }
    }
}
