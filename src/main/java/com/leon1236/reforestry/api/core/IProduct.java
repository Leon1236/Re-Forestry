package com.leon1236.reforestry.api.core;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import it.unimi.dsi.fastutil.Hash;

public interface IProduct {
	Hash.Strategy<IProduct> ITEM_ONLY_STRATEGY = new Hash.Strategy<>() {
		@Override
		public int hashCode(IProduct o) {
			return o == null ? 0 : System.identityHashCode(o.item());
		}

		@Override
		public boolean equals(IProduct a, IProduct b) {
			return a == b || (a != null && b != null && a.item() == b.item());
		}
	};

	Item item();

	default int count() {
		return 1;
	}

	float chance();

	default ItemStack createStack() {
		return new ItemStack(item(), count());
	}

	default ItemStack createRandomStack(RandomSource random) {
		return createStack();
	}
}
