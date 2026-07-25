package com.leon1236.reforestry.api.core;

import it.unimi.dsi.fastutil.Hash;
import org.jetbrains.annotations.Nullable;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IProduct {
    Hash.Strategy<IProduct> ITEM_ONLY_STRATEGY = new Hash.Strategy<>() {
        @Override
        public int hashCode(@Nullable IProduct o) {
            return o == null ? 0 : o.item().hashCode();
        }

        @Override
        public boolean equals(@Nullable IProduct a, @Nullable IProduct b) {
            return (a == null || b == null) ? a == b : a.item() == b.item();
        }
    };

    Item item();

    float chance();

    ItemStack createStack();

    default ItemStack createRandomStack(RandomSource random) {
        return createStack();
    }
}
