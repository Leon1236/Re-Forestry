package com.leon1236.reforestry.api.lepidopterology;

import java.util.List;

import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IButterflyCocoon extends IRegistryAlleleValue {
	boolean isDominant();

	List<? extends IProduct> getProducts();
}
