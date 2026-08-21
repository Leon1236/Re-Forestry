package com.leon1236.reforestry.api.plugin;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpeciesType;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;

public interface IBeeSpeciesBuilder extends ISpeciesBuilder<IBeeSpeciesType, IBeeSpecies, IBeeSpeciesBuilder> {
	IBeeSpeciesBuilder setBodyColor(int color);

	default IBeeSpeciesBuilder setBodyColor(TextColor color) {
		return setBodyColor(color.getValue());
	}

	default IBeeSpeciesBuilder setBody(TextColor color) {
		return setBodyColor(color);
	}

	IBeeSpeciesBuilder setStripesColor(int color);

	default IBeeSpeciesBuilder setStripesColor(TextColor color) {
		return setStripesColor(color.getValue());
	}

	default IBeeSpeciesBuilder setStripes(TextColor color) {
		return setStripesColor(color);
	}

	IBeeSpeciesBuilder setOutline(int color);

	default IBeeSpeciesBuilder setOutline(TextColor color) {
		return setOutline(color.getValue());
	}

	IBeeSpeciesBuilder setSecret(boolean secret);

	IBeeSpeciesBuilder setGlint(boolean glint);

	IBeeSpeciesBuilder setAuthority(String authority);

	IBeeSpeciesBuilder setTemperature(TemperatureType temperature);

	IBeeSpeciesBuilder setHumidity(HumidityType humidity);

	IBeeSpeciesBuilder addProduct(Item item, float chance);

	IBeeSpeciesBuilder addProduct(Item item, int count, float chance);

	default IBeeSpeciesBuilder addProduct(IProduct product) {
		return addProduct(product.item(), product.count(), product.chance());
	}

	default IBeeSpeciesBuilder addProduct(ItemStack stack, float chance) {
		return addProduct(stack.getItem(), stack.getCount(), chance);
	}

	IBeeSpeciesBuilder addSpecialty(Item item, float chance);

	default IBeeSpeciesBuilder addSpecialty(IProduct specialty) {
		return addSpecialty(specialty.item(), specialty.chance());
	}

	default IBeeSpeciesBuilder addSpecialty(ItemStack stack, float chance) {
		return addSpecialty(stack.getItem(), chance);
	}

	IBeeSpeciesBuilder setJubilance(IBeeJubilance jubilance);

	IBeeSpeciesBuilder setGenome(Consumer<IGenomeBuilder> genome);

	IBeeSpeciesBuilder addMutations(Consumer<IMutationsRegistration> mutations);

	List<IProduct> buildProducts();

	List<IProduct> buildSpecialties();

	int getBody();

	int getStripes();

	int getOutline();

	IBeeJubilance getJubilance();
}
