package com.leon1236.reforestry.gendustry.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.gendustry.blockentity.GeneticTransposerBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.IGendustryHintTile;
import com.leon1236.reforestry.gendustry.blockentity.ImprinterBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.SamplerBlockEntity;
import com.leon1236.reforestry.gendustry.features.GMenus;

public class ThreeInputMenu<T extends TilePowered & WorldlyContainer & IGendustryHintTile>
		extends ContainerMachine<T> implements IContainerEnergy {
	public static final int SLOT_INPUT = 0;
	public static final int SLOT_TEMPLATE_OR_BLANK = 1;
	public static final int SLOT_LABWARE = 2;
	public static final int SLOT_OUTPUT = 3;
	public static final int ERROR_SLOT_COUNT = 4;

	private static final int INVENTORY_Y = 84;

	public ThreeInputMenu(MenuType<?> menuType, int containerId, Inventory playerInventory, T tile) {
		super(menuType, containerId, playerInventory, tile, INVENTORY_Y);
		addDataSlots(tile.getProgressData());
		addDataSlots(tile.getErrorData());
		addDataSlots(tile.getEnergyData());
	}

	public static ThreeInputMenu<SamplerBlockEntity> sampler(int containerId, Inventory playerInventory, BlockPos pos) {
		return new ThreeInputMenu<>(GMenus.SAMPLER.type(), containerId, playerInventory,
				resolveTile(playerInventory, pos, SamplerBlockEntity.class));
	}

	public static ThreeInputMenu<SamplerBlockEntity> sampler(int containerId, Inventory playerInventory, SamplerBlockEntity tile) {
		return new ThreeInputMenu<>(GMenus.SAMPLER.type(), containerId, playerInventory, tile);
	}

	public static ThreeInputMenu<ImprinterBlockEntity> imprinter(int containerId, Inventory playerInventory, BlockPos pos) {
		return new ThreeInputMenu<>(GMenus.IMPRINTER.type(), containerId, playerInventory,
				resolveTile(playerInventory, pos, ImprinterBlockEntity.class));
	}

	public static ThreeInputMenu<ImprinterBlockEntity> imprinter(int containerId, Inventory playerInventory, ImprinterBlockEntity tile) {
		return new ThreeInputMenu<>(GMenus.IMPRINTER.type(), containerId, playerInventory, tile);
	}

	public static ThreeInputMenu<GeneticTransposerBlockEntity> geneticTransposer(int containerId, Inventory playerInventory, BlockPos pos) {
		return new ThreeInputMenu<>(GMenus.GENETIC_TRANSPOSER.type(), containerId, playerInventory,
				resolveTile(playerInventory, pos, GeneticTransposerBlockEntity.class));
	}

	public static ThreeInputMenu<GeneticTransposerBlockEntity> geneticTransposer(int containerId, Inventory playerInventory,
			GeneticTransposerBlockEntity tile) {
		return new ThreeInputMenu<>(GMenus.GENETIC_TRANSPOSER.type(), containerId, playerInventory, tile);
	}

	@Override
	protected void addMachineSlots(T tile) {
		addSlot(new FilteredSlot(tile, SLOT_INPUT, 32, 49));
		addSlot(new FilteredSlot(tile, SLOT_TEMPLATE_OR_BLANK, 65, 28));
		addSlot(new FilteredSlot(tile, SLOT_LABWARE, 89, 28));
		addSlot(new OutputSlot(tile, SLOT_OUTPUT, 128, 49));
	}

	public int getProgressPercent() {
		return tile.getProgressData().get(0);
	}

	public int getErrorCount() {
		return tile.getErrorData().get(0);
	}

	public short getErrorId(int index) {
		return (short) tile.getErrorData().get(index + 1);
	}

	public String getHintsKey() {
		return tile.getHintsKey();
	}

	@Override
	public int getEnergyStored() {
		return tile.getEnergyData().get(0);
	}

	@Override
	public int getEnergyCapacity() {
		return tile.getEnergyData().get(1);
	}

	@Override
	public int getEnergyMaxReceive() {
		return tile.getEnergyData().get(2);
	}

	@Override
	public int getEnergyUsage() {
		return tile.getEnergyData().get(3);
	}

	private static final class FilteredSlot extends Slot {
		private final WorldlyContainer container;
		private final int slotIndex;

		FilteredSlot(WorldlyContainer container, int index, int x, int y) {
			super(container, index, x, y);
			this.container = container;
			this.slotIndex = index;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return container.canPlaceItem(slotIndex, stack);
		}
	}

	private static final class OutputSlot extends Slot {
		OutputSlot(WorldlyContainer container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
