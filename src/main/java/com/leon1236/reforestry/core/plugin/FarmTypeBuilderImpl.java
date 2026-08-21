package com.leon1236.reforestry.core.plugin;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.IFarmHousing;
import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.api.agriculture.IFarmableFactory;
import com.leon1236.reforestry.api.agriculture.IWaterConsumption;
import com.leon1236.reforestry.api.agriculture.Soil;
import com.leon1236.reforestry.api.plugin.IFarmTypeBuilder;
import com.leon1236.reforestry.api.plugin.IWindfallFarmableBuilder;
import com.leon1236.reforestry.farming.farmlogic.FarmType;
import com.leon1236.reforestry.farming.farmlogic.farmables.FarmableInfo;

public final class FarmTypeBuilderImpl implements IFarmTypeBuilder {
	private final Identifier id;
	private final ImmutableSet.Builder<Soil> soils = ImmutableSet.builder();
	private final IdentityHashMap<Item, WindfallFarmableBuilderImpl> windfallFarmables = new IdentityHashMap<>();
	private final IdentityHashMap<Item, Consumer<IWindfallFarmableBuilder>> windfallFarmableModifications = new IdentityHashMap<>();
	private final ImmutableList.Builder<ItemStack> germlings = ImmutableList.builder();
	private final ImmutableList.Builder<ItemStack> products = ImmutableList.builder();
	private final ImmutableList.Builder<IFarmable> farmables = ImmutableList.builder();

	private BiFunction<IFarmType, Boolean, IFarmLogic> factory;
	private ItemStack icon;
	@Nullable
	private ToIntFunction<IFarmHousing> fertilizerConsumption;
	@Nullable
	private IWaterConsumption waterConsumption;

	public FarmTypeBuilderImpl(Identifier id, BiFunction<IFarmType, Boolean, IFarmLogic> factory, ItemStack icon) {
		this.id = id;
		this.factory = factory;
		this.icon = icon;
	}

	@Override
	public IFarmTypeBuilder setIcon(ItemStack stack) {
		this.icon = stack;
		return this;
	}

	@Override
	public IFarmTypeBuilder setLogicFactory(BiFunction<IFarmType, Boolean, IFarmLogic> factory) {
		this.factory = factory;
		return this;
	}

	@Override
	public IFarmTypeBuilder setFertilizerConsumption(ToIntFunction<IFarmHousing> consumption) {
		this.fertilizerConsumption = consumption;
		return this;
	}

	@Override
	public IFarmTypeBuilder setWaterConsumption(IWaterConsumption waterConsumption) {
		this.waterConsumption = waterConsumption;
		return this;
	}

	@Override
	public IFarmTypeBuilder addSoil(ItemStack resource, BlockState soilState) {
		this.soils.add(new Soil(resource, soilState));
		return this;
	}

	@Override
	public IFarmTypeBuilder addGermling(ItemStack germling) {
		this.germlings.add(germling);
		return this;
	}

	@Override
	public IFarmTypeBuilder addGermlings(Iterable<ItemStack> seedling) {
		this.germlings.addAll(seedling);
		return this;
	}

	@Override
	public IFarmTypeBuilder addProduct(ItemStack product) {
		this.products.add(product);
		return this;
	}

	@Override
	public IFarmTypeBuilder addProducts(Collection<ItemStack> products) {
		this.products.addAll(products);
		return this;
	}

	@Override
	public IFarmTypeBuilder addFarmable(IFarmable farmable) {
		this.farmables.add(farmable);
		return this;
	}

	@Override
	public IFarmTypeBuilder addWindfallFarmable(Item germling, IFarmableFactory factory, Consumer<IWindfallFarmableBuilder> action) {
		if (this.windfallFarmables.containsKey(germling)) {
			throw new IllegalStateException("A windfall farmable for farm type " + this.id
					+ " was already associated with the germling " + BuiltInRegistries.ITEM.getKey(germling)
					+ ": " + this.windfallFarmables.get(germling)
					+ ". Did you mean to use IFarmTypeBuilder#modifyWindfallFarmable ?");
		}
		WindfallFarmableBuilderImpl builder = new WindfallFarmableBuilderImpl(factory);
		action.accept(builder);
		this.windfallFarmables.put(germling, builder);
		return this;
	}

	@Override
	public IFarmTypeBuilder modifyWindfallFarmable(Item germling, Consumer<IWindfallFarmableBuilder> action) {
		this.windfallFarmableModifications.merge(germling, action, Consumer::andThen);
		return this;
	}

	public IFarmType build() {
		if (this.fertilizerConsumption == null) {
			throw new NullPointerException("Missing required call to IFarmTypeBuilder#setFertilizerConsumption on farm type " + this.id);
		}
		if (this.waterConsumption == null) {
			throw new NullPointerException("Missing required call to IFarmTypeBuilder#setWaterConsumption on farm type " + this.id);
		}

		for (var entry : this.windfallFarmableModifications.entrySet()) {
			WindfallFarmableBuilderImpl builder = this.windfallFarmables.get(entry.getKey());
			if (builder != null) {
				entry.getValue().accept(builder);
			}
		}
		for (var entry : this.windfallFarmables.entrySet()) {
			this.farmables.add(entry.getValue().build(entry.getKey()));
		}

		ImmutableList<ItemStack> extraGermlings = this.germlings.build();
		ImmutableList<ItemStack> extraProducts = this.products.build();
		if (!extraGermlings.isEmpty() || !extraProducts.isEmpty()) {
			this.farmables.add(new FarmableInfo(extraGermlings, extraProducts));
		}

		return new FarmType(
				this.id,
				this.icon,
				this.factory,
				this.farmables.build(),
				this.fertilizerConsumption,
				this.waterConsumption,
				this.soils.build()
		);
	}
}
