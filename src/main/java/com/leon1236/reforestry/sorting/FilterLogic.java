package com.leon1236.reforestry.sorting;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ILocationProvider;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.api.genetics.filter.FilterData;
import com.leon1236.reforestry.api.genetics.filter.IFilterLogic;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;
import com.leon1236.reforestry.core.genetics.GeneticItemHelper;
import com.leon1236.reforestry.sorting.network.FilterPackets;

public class FilterLogic implements IFilterLogic {
	private final ILocationProvider locatable;
	private final INetworkHandler networkHandler;
	private IFilterRuleType[] filterRules = new IFilterRuleType[6];
	private AlleleFilter[][] genomeFilter = new AlleleFilter[6][3];

	public FilterLogic(ILocationProvider locatable, INetworkHandler networkHandler) {
		this.locatable = locatable;
		this.networkHandler = networkHandler;

		for (int i = 0; i < this.filterRules.length; i++) {
			this.filterRules[i] = IForestryApi.INSTANCE.getFilterManager().getDefaultRule();
		}
	}

	@Override
	public INetworkHandler getNetworkHandler() {
		return this.networkHandler;
	}

	@Override
	public CompoundTag write(CompoundTag data, HolderLookup.Provider registries) {
		for (int i = 0; i < this.filterRules.length; i++) {
			data.putString("TypeFilter" + i, this.filterRules[i].getId());
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				AlleleFilter filter = this.genomeFilter[i][j];
				if (filter == null) {
					continue;
				}
				if (filter.activeSpecies != null) {
					data.putString("GenomeFilterS" + i + "-" + j + "-" + 0, filter.activeSpecies.toString());
				}
				if (filter.inactiveSpecies != null) {
					data.putString("GenomeFilterS" + i + "-" + j + "-" + 1, filter.inactiveSpecies.toString());
				}
			}
		}
		return data;
	}

	public void write(ValueOutput output) {
		for (int i = 0; i < this.filterRules.length; i++) {
			output.putString("TypeFilter" + i, this.filterRules[i].getId());
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				AlleleFilter filter = this.genomeFilter[i][j];
				if (filter == null) {
					continue;
				}
				if (filter.activeSpecies != null) {
					output.putString("GenomeFilterS" + i + "-" + j + "-" + 0, filter.activeSpecies.toString());
				}
				if (filter.inactiveSpecies != null) {
					output.putString("GenomeFilterS" + i + "-" + j + "-" + 1, filter.inactiveSpecies.toString());
				}
			}
		}
	}

	public void read(ValueInput input) {
		for (int i = 0; i < this.filterRules.length; i++) {
			this.filterRules[i] = IForestryApi.INSTANCE.getFilterManager().getRuleOrDefault(input.getStringOr("TypeFilter" + i, ""));
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				AlleleFilter filter = new AlleleFilter();
				String active = input.getStringOr("GenomeFilterS" + i + "-" + j + "-" + 0, "");
				if (!active.isEmpty()) {
					filter.activeSpecies = Identifier.tryParse(active);
				}
				String inactive = input.getStringOr("GenomeFilterS" + i + "-" + j + "-" + 1, "");
				if (!inactive.isEmpty()) {
					filter.inactiveSpecies = Identifier.tryParse(inactive);
				}
				this.genomeFilter[i][j] = filter;
			}
		}
	}

	@Override
	public void read(CompoundTag data, HolderLookup.Provider registries) {
		for (int i = 0; i < this.filterRules.length; i++) {
			this.filterRules[i] = IForestryApi.INSTANCE.getFilterManager().getRuleOrDefault(data.getStringOr("TypeFilter" + i, ""));
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				AlleleFilter filter = new AlleleFilter();
				if (data.contains("GenomeFilterS" + i + "-" + j + "-" + 0)) {
					filter.activeSpecies = Identifier.tryParse(data.getStringOr("GenomeFilterS" + i + "-" + j + "-" + 0, ""));
				}
				if (data.contains("GenomeFilterS" + i + "-" + j + "-" + 1)) {
					filter.inactiveSpecies = Identifier.tryParse(data.getStringOr("GenomeFilterS" + i + "-" + j + "-" + 1, ""));
				}
				this.genomeFilter[i][j] = filter;
			}
		}
	}

	@Override
	public void writeGuiData(FriendlyByteBuf buffer) {
		writeFilterRules(buffer, this.filterRules);
		writeGenomeFilters(buffer, this.genomeFilter);
	}

	@Override
	public void readGuiData(FriendlyByteBuf buffer) {
		this.filterRules = readFilterRules(buffer);
		this.genomeFilter = readGenomeFilters(buffer);
	}

	public static void writeFilterRules(FriendlyByteBuf buffer, IFilterRuleType[] filterRules) {
		for (IFilterRuleType filterRule : filterRules) {
			buffer.writeShort(IForestryApi.INSTANCE.getFilterManager().getId(filterRule));
		}
	}

	public static void writeGenomeFilters(FriendlyByteBuf buffer, AlleleFilter[][] genomeFilter) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				AlleleFilter filter = genomeFilter[i][j];
				if (filter == null) {
					buffer.writeBoolean(false);
					buffer.writeBoolean(false);
					continue;
				}
				if (filter.activeSpecies != null) {
					buffer.writeBoolean(true);
					buffer.writeIdentifier(filter.activeSpecies);
				} else {
					buffer.writeBoolean(false);
				}
				if (filter.inactiveSpecies != null) {
					buffer.writeBoolean(true);
					buffer.writeIdentifier(filter.inactiveSpecies);
				} else {
					buffer.writeBoolean(false);
				}
			}
		}
	}

	public static IFilterRuleType[] readFilterRules(FriendlyByteBuf buffer) {
		IFilterRuleType[] filterRules = new IFilterRuleType[6];
		for (int i = 0; i < 6; i++) {
			filterRules[i] = IForestryApi.INSTANCE.getFilterManager().getRuleOrDefault(buffer.readShort());
		}
		return filterRules;
	}

	public static AlleleFilter[][] readGenomeFilters(FriendlyByteBuf buffer) {
		AlleleFilter[][] genomeFilters = new AlleleFilter[6][3];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				AlleleFilter filter = new AlleleFilter();
				if (buffer.readBoolean()) {
					filter.activeSpecies = buffer.readIdentifier();
				}
				if (buffer.readBoolean()) {
					filter.inactiveSpecies = buffer.readIdentifier();
				}
				genomeFilters[i][j] = filter;
			}
		}
		return genomeFilters;
	}

	@Override
	public boolean isValid(ItemStack stack, Direction facing) {
		return IndividualItems.filter(stack, (genome, stage) -> {
			Identifier typeId = IndividualItems.getSpeciesTypeId(stack);
			return typeId != null && isValid(facing, stack, new FilterData(typeId, genome, stage));
		});
	}

	@Override
	public boolean isValid(Direction facing, ItemStack stack, FilterData filterData) {
		IFilterRuleType rule = getRule(facing);
		if (rule == DefaultFilterRuleType.CLOSED) {
			return false;
		}
		if (rule == DefaultFilterRuleType.ITEM) {
			return true;
		}
		Identifier requiredRoot = rule.getSpeciesTypeId();
		if (requiredRoot != null && !filterData.typeId().equals(requiredRoot)) {
			return false;
		}
		if (rule == DefaultFilterRuleType.ANYTHING || rule.isValid(stack, filterData)) {
			IGenome genome = filterData.genome();
			Identifier active = GeneticItemHelper.speciesId(genome, filterData.typeId(), true);
			Identifier inactive = GeneticItemHelper.speciesId(genome, filterData.typeId(), false);
			if (active == null || inactive == null) {
				return false;
			}
			return isValidAllelePair(facing, active, inactive);
		}
		return false;
	}

	@Override
	public boolean isValidAllelePair(Direction orientation, Identifier active, Identifier inactive) {
		AlleleFilter[] directionFilters = this.genomeFilter[orientation.ordinal()];

		if (directionFilters == null) {
			return true;
		}

		boolean foundFilter = false;
		for (int i = 0; i < 3; i++) {
			AlleleFilter filter = directionFilters[i];
			if (filter != null && !filter.isEmpty()) {
				foundFilter = true;
				if (filter.isValid(active, inactive)) {
					return true;
				}
			}
		}
		return !foundFilter;
	}

	@Override
	public IFilterRuleType getRule(Direction facing) {
		return this.filterRules[facing.ordinal()];
	}

	@Override
	public boolean setRule(Direction facing, IFilterRuleType rule) {
		if (this.filterRules[facing.ordinal()] != rule) {
			this.filterRules[facing.ordinal()] = rule;
			return true;
		}
		return false;
	}

	@Nullable
	public AlleleFilter getGenomeFilter(Direction facing, int index) {
		return this.genomeFilter[facing.ordinal()][index];
	}

	@Nullable
	@Override
	public Identifier getGenomeFilter(Direction facing, int index, boolean active) {
		AlleleFilter filter = getGenomeFilter(facing, index);
		if (filter == null) {
			return null;
		}
		return active ? filter.activeSpecies : filter.inactiveSpecies;
	}

	@Override
	public boolean setGenomeFilter(Direction facing, int index, boolean active, @Nullable Identifier species) {
		AlleleFilter filter = this.genomeFilter[facing.ordinal()][index];
		if (filter == null) {
			filter = this.genomeFilter[facing.ordinal()][index] = new AlleleFilter();
		}
		if (active) {
			return filter.setActive(species);
		}
		return filter.setInactive(species);
	}

	@Override
	public void sendToServer(Direction facing, int index, boolean active, @Nullable Identifier allele) {
		FilterPackets.sendGenome(this.locatable.getCoordinates(), facing, index, active, allele);
	}

	@Override
	public void sendToServer(Direction facing, IFilterRuleType rule) {
		FilterPackets.sendRule(this.locatable.getCoordinates(), facing, rule);
	}
}
