package com.leon1236.reforestry.lepidopterology.genetics;

import java.util.List;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;

public final class DummyCocoon implements IButterflyCocoon {
	private final Identifier id;
	private final boolean dominant;
	private final List<IProduct> products;

	public DummyCocoon(Identifier id, boolean dominant, List<IProduct> products) {
		this.id = id;
		this.dominant = dominant;
		this.products = List.copyOf(products);
	}

	@Override
	public Identifier id() {
		return id;
	}

	@Override
	public boolean isDominant() {
		return dominant;
	}

	@Override
	public List<IProduct> getProducts() {
		return products;
	}
}
