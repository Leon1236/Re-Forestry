package com.leon1236.reforestry.core.gui;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.circuits.ItemCircuitBoard;
import com.leon1236.reforestry.core.circuits.ItemInventorySolderingIron;
import com.leon1236.reforestry.core.features.CoreMenuTypes;

public class ContainerSolderingIron extends AbstractContainerMenu {
    private static final int INPUT_BOARD = 0;
    private static final int OUTPUT_BOARD = 1;
    private static final int INGREDIENT_START = 2;

    private final ItemInventorySolderingIron inventory;
    private final Inventory playerInventory;

    public ContainerSolderingIron(int containerId, Player player, ItemInventorySolderingIron inventory) {
        super(CoreMenuTypes.SOLDERING_IRON.type(), containerId);
        this.inventory = inventory;
        this.playerInventory = player.getInventory();

        addSlot(new FilteredSlot(inventory, INPUT_BOARD, 152, 12, stack -> stack.getItem() instanceof ItemCircuitBoard));
        addSlot(new OutputSlot(inventory, OUTPUT_BOARD, 152, 92));
        for (int i = 0; i < 4; i++) {
            int slot = INGREDIENT_START + i;
            addSlot(new FilteredSlot(inventory, slot, 12, 32 + i * 20,
                    stack -> inventory.canPlaceItem(slot, stack)));
        }

        addPlayerInventory(8, 123);
    }

    private void addPlayerInventory(int left, int top) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInventory, column + row * 9 + 9, left + column * 18, top + row * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, left + column * 18, top + 58));
        }
    }

    public ItemInventorySolderingIron getSolderingInventory() {
        return inventory;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            int machineSlots = 6;
            if (index < machineSlots) {
                if (!moveItemStackTo(stack, machineSlots, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stack, 0, machineSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return result;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    private static final class FilteredSlot extends Slot {
        private final SlotFilter filter;

        FilteredSlot(net.minecraft.world.Container container, int slot, int x, int y, SlotFilter filter) {
            super(container, slot, x, y);
            this.filter = filter;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return filter.test(stack);
        }
    }

    private static final class OutputSlot extends Slot {
        OutputSlot(net.minecraft.world.Container container, int slot, int x, int y) {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }

    @FunctionalInterface
    private interface SlotFilter {
        boolean test(ItemStack stack);
    }
}
