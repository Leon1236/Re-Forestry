package com.leon1236.reforestry.api.apiculture.genetics;

import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IClimateSensitive;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.core.IProductProducer;
import com.leon1236.reforestry.api.core.ISpecialtyProducer;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IBeeSpecies extends IRegistryAlleleValue, IClimateSensitive, ISpecies<IBee>, IProductProducer, ISpecialtyProducer {
	String genus();

	String species();

	boolean dominant();

	int outlineColor();

	int bodyColor();

	int stripesColor();

	boolean secret();

	boolean glint();

	String authority();

	List<Product> products();

	List<Product> specialties();

	IBeeJubilance jubilance();

	default boolean isJubilant(IGenome genome, IBeeHousing housing) {
		return jubilance().isJubilant(this, genome, housing);
	}

	@Override
	default List<Product> getProducts() {
		return products();
	}

	@Override
	default List<Product> getSpecialties() {
		return specialties();
	}

	default int getBody() {
		return bodyColor();
	}

	default int getStripes() {
		return stripesColor();
	}

	default int getOutline() {
		return outlineColor();
	}

	record Product(Item item, int count, float chance) implements IProduct {
		public Product(Item item, float chance) {
			this(item, 1, chance);
		}

		@Override
		public ItemStack createStack() {
			return new ItemStack(item, count);
		}
	}
}
