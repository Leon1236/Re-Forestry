package com.leon1236.reforestry.farming.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.agriculture.IFarmable;

public class InventoryFarm extends InventoryPlantation {
	public static final InventoryConfig CONFIG = new InventoryConfig(
			0, 6,
			6, 6,
			12, 8,
			20, 1,
			21, 1
	);

	public InventoryFarm(FarmController farmController, Runnable onChanged) {
		super(farmController, CONFIG, onChanged);
	}

	public static boolean isProductSlot(int slot) {
		return slot >= CONFIG.productionStart && slot < CONFIG.productionStart + CONFIG.productionCount;
	}

	public boolean plantGermling(IFarmable germling, Player player, BlockPos pos) {
		for (int i = 0; i < this.germlingsInventory.getContainerSize(); i++) {
			ItemStack germlingStack = this.germlingsInventory.getItem(i);
			if (germlingStack.isEmpty() || !germling.isGermling(germlingStack)) {
				continue;
			}
			if (germling.plantSaplingAt(player, germlingStack, player.level(), pos)) {
				this.germlingsInventory.removeItem(i, 1);
				return true;
			}
		}
		return false;
	}
}
