package com.leon1236.reforestry.core.inventory;

import java.util.Set;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorSource;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;

public class ItemInventoryAlyzer extends ItemInventory implements IErrorSource {
	public static final TagKey<Item> DROP_HONEY = TagKey.create(Registries.ITEM, ReForestry.id("drop_honey"));

	public static final int SLOT_ENERGY = 0;
	public static final int SLOT_SPECIMEN = 1;
	public static final int SLOT_ANALYZE_1 = 2;
	public static final int SLOT_ANALYZE_2 = 3;
	public static final int SLOT_ANALYZE_3 = 4;
	public static final int SLOT_ANALYZE_4 = 5;
	public static final int SLOT_ANALYZE_5 = 6;

	public ItemInventoryAlyzer(Player player, InteractionHand hand) {
		super(player, hand, 7);
	}

	public static boolean isAlyzingFuel(ItemStack stack) {
		return !stack.isEmpty() && stack.is(DROP_HONEY);
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		if (slotIndex == SLOT_ENERGY) {
			return isAlyzingFuel(stack);
		}
		if (hasSpecimen() && getItem(slotIndex).isEmpty()) {
			return false;
		}
		if (slotIndex == SLOT_SPECIMEN) {
			return IndividualItems.isIndividual(stack);
		}
		return IndividualItems.filter(stack, genome -> IndividualItems.isAnalyzed(stack));
	}

	@Override
	public void setItem(int index, ItemStack itemStack) {
		super.setItem(index, itemStack);
		if (index == SLOT_SPECIMEN) {
			analyzeSpecimen(itemStack);
		} else if (index == SLOT_ENERGY) {
			analyzeSpecimen(getItem(SLOT_SPECIMEN));
		}
	}

	private void analyzeSpecimen(ItemStack specimen) {
		if (specimen.isEmpty()) {
			return;
		}
		if (!IndividualItems.isIndividual(specimen)) {
			return;
		}
		if (!IndividualItems.isAnalyzed(specimen)) {
			if (!isAlyzingFuel(getItem(SLOT_ENERGY))) {
				return;
			}
			if (IndividualItems.analyze(specimen, this.player)) {
				removeItem(SLOT_ENERGY, 1);
			}
		}
		super.setItem(SLOT_ANALYZE_1, specimen);
		super.setItem(SLOT_SPECIMEN, ItemStack.EMPTY);
	}

	@Override
	public Set<IError> getErrors() {
		ItemStack specimen = getSpecimen();
		if (specimen.isEmpty()) {
			return Set.of(ForestryError.NO_SPECIMEN);
		}
		if (IndividualItems.isIndividual(specimen) && !IndividualItems.isAnalyzed(specimen) && !isAlyzingFuel(getItem(SLOT_ENERGY))) {
			return Set.of(ForestryError.NO_HONEY);
		}
		return Set.of();
	}

	public ItemStack getSpecimen() {
		for (int i = SLOT_SPECIMEN; i <= SLOT_ANALYZE_5; i++) {
			ItemStack stack = getItem(i);
			if (!stack.isEmpty()) {
				return stack;
			}
		}
		return ItemStack.EMPTY;
	}

	public boolean hasSpecimen() {
		return !getSpecimen().isEmpty();
	}

	public int getCharges() {
		ItemStack energy = getItem(SLOT_ENERGY);
		return energy.isEmpty() ? 0 : energy.getCount();
	}
}
