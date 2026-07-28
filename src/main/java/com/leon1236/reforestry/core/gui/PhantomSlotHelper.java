package com.leon1236.reforestry.core.gui;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.item.ItemStack;

public final class PhantomSlotHelper {
	private PhantomSlotHelper() {
	}

	public static void clickGhost(SlotGhostCrafting slot, int button, ContainerInput input, Player player) {
		ItemStack held = player.containerMenu.getCarried();
		ItemStack inSlot = slot.getItem();

		if (button == 2) {
			slot.setGhost(ItemStack.EMPTY);
			return;
		}

		if (button != 0 && button != 1) {
			return;
		}

		if (inSlot.isEmpty()) {
			if (!held.isEmpty()) {
				slot.setGhost(held);
			}
			return;
		}

		if (held.isEmpty()) {
			if (input == ContainerInput.QUICK_MOVE || button == 0) {
				slot.setGhost(ItemStack.EMPTY);
			}
			return;
		}

		slot.setGhost(held);
	}
}
