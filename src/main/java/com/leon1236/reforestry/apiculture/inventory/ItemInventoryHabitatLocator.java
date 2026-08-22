package com.leon1236.reforestry.apiculture.inventory;

import java.util.Set;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.genetics.IBee;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorSource;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.apiculture.items.HabitatLocatorLogic;
import com.leon1236.reforestry.core.inventory.ItemInventory;
import com.leon1236.reforestry.core.inventory.ItemInventoryAlyzer;

public class ItemInventoryHabitatLocator extends ItemInventory implements IErrorSource {
	public static final int SLOT_SPECIMEN = 0;
	public static final int SLOT_ANALYZED = 1;
	public static final int SLOT_ENERGY = 2;

	public ItemInventoryHabitatLocator(Player player, InteractionHand hand) {
		super(player, hand, 3);
	}

	@Override
	public boolean canSlotAccept(int slotIndex, ItemStack stack) {
		if (slotIndex == SLOT_ENERGY) {
			return ItemInventoryAlyzer.isAlyzingFuel(stack);
		}
		if (slotIndex == SLOT_SPECIMEN) {
			return IndividualItems.isIndividual(stack) && IndividualItems.getSpeciesTypeId(stack).equals(com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes.BEE);
		}
		return false;
	}

	@Override
	public void setItem(int index, ItemStack itemStack) {
		super.setItem(index, itemStack);
		if (index == SLOT_SPECIMEN && !itemStack.isEmpty()) {
			startSearchFromSpecimen(itemStack);
		}
	}

	private void startSearchFromSpecimen(ItemStack specimen) {
		if (!getItem(SLOT_ANALYZED).isEmpty()) {
			return;
		}
		if (!ItemInventoryAlyzer.isAlyzingFuel(getItem(SLOT_ENERGY))) {
			return;
		}
		IBee bee = HabitatLocatorLogic.beeFromStack(specimen);
		if (bee == null) {
			return;
		}
		removeItem(SLOT_ENERGY, 1);
		super.setItem(SLOT_ANALYZED, specimen.copy());
		super.setItem(SLOT_SPECIMEN, ItemStack.EMPTY);
		HabitatLocatorLogic.searchFromSpecimen(player, bee);
	}

	@Override
	public Set<IError> getErrors() {
		if (!getItem(SLOT_ANALYZED).isEmpty()) {
			return Set.of();
		}
		ItemStack specimen = getItem(SLOT_SPECIMEN);
		if (specimen.isEmpty() || !IndividualItems.isIndividual(specimen)) {
			return Set.of(ForestryError.NO_SPECIMEN);
		}
		if (!ItemInventoryAlyzer.isAlyzingFuel(getItem(SLOT_ENERGY))) {
			return Set.of(ForestryError.NO_HONEY);
		}
		return Set.of();
	}
}
