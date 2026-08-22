package com.leon1236.reforestry.cultivation.inventory;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.agriculture.HorizontalDirection;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.cultivation.tiles.TilePlanter;
import com.leon1236.reforestry.farming.multiblock.IFarmInventoryInternal;
import com.leon1236.reforestry.farming.multiblock.InventoryPlantation;

public class InventoryPlanter extends InventoryPlantation implements IFarmInventoryInternal {
	public static final InventoryConfig CONFIG = new InventoryConfig(
			0, 4,
			4, 4,
			8, 4,
			12, 1,
			13, 1
	);

	public InventoryPlanter(TilePlanter housing) {
		super(housing, CONFIG, housing::setChanged);
	}

	public boolean plantGermling(IFarmable germling, Player player, BlockPos pos) {
		for (Direction direction : HorizontalDirection.VALUES) {
			if (plantGermling(germling, player, pos, direction)) {
				return true;
			}
		}
		return false;
	}

	public boolean plantGermling(IFarmable germling, Player player, BlockPos pos, Direction direction) {
		int index = getSlotIndex(direction.getOpposite());
		ItemStack germlingStack = this.germlingsInventory.getItem(index);
		if (germlingStack.isEmpty() || !germling.isGermling(germlingStack)) {
			return false;
		}
		if (germling.plantSaplingAt(player, germlingStack, player.level(), pos)) {
			this.germlingsInventory.removeItem(index, 1);
			return true;
		}
		return false;
	}

	private static int getSlotIndex(Direction direction) {
		return switch (direction) {
			case NORTH -> 0;
			case EAST -> 1;
			case SOUTH -> 2;
			default -> 3;
		};
	}
}
