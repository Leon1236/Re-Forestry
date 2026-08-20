package com.leon1236.reforestry.core.escritoire;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.capability.IndividualItems;

public final class InventoryEscritoire {
	public static final int SLOT_ANALYZE = 0;
	public static final int SLOT_RESULTS_1 = 1;
	public static final int SLOTS_RESULTS_COUNT = 6;
	public static final int SLOT_INPUT_1 = 7;
	public static final int SLOTS_INPUT_COUNT = 5;
	public static final int SLOT_COUNT = SLOT_INPUT_1 + SLOTS_INPUT_COUNT;

	private InventoryEscritoire() {
	}

	public static boolean isSlotInRange(int slotIndex, int start, int count) {
		return slotIndex >= start && slotIndex < start + count;
	}

	public static boolean canSlotAccept(TileEscritoire tile, int slotIndex, ItemStack stack) {
		if (isSlotInRange(slotIndex, SLOT_INPUT_1, tile.getGame().getSampleSize(SLOTS_INPUT_COUNT))) {
			ItemStack specimen = tile.getItem(SLOT_ANALYZE);
			if (specimen.isEmpty() || !IndividualItems.isIndividual(specimen)) {
				return false;
			}
			return EscritoireResearch.getResearchSuitability(specimen, stack) > 0f;
		}
		return slotIndex == SLOT_ANALYZE && EscritoireResearch.isSupportedSpecimen(stack);
	}

	public static boolean isLocked(TileEscritoire tile, int slotIndex) {
		if (slotIndex == SLOT_ANALYZE) {
			return false;
		}
		if (tile.getItem(SLOT_ANALYZE).isEmpty()) {
			return true;
		}
		if (isSlotInRange(slotIndex, SLOT_INPUT_1, SLOTS_INPUT_COUNT)) {
			return slotIndex >= SLOT_INPUT_1 + tile.getGame().getSampleSize(SLOTS_INPUT_COUNT);
		}
		return false;
	}
}
