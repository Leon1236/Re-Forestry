package com.leon1236.reforestry.core.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.core.tiles.TileBase;

public abstract class ContainerMachine<T extends TileBase> extends AbstractContainerMenu {
    protected final T tile;
    private final int machineSlotCount;

    protected ContainerMachine(MenuType<?> menuType, int containerId, Inventory playerInventory, T tile, int inventoryY) {
        super(menuType, containerId);
        this.tile = tile;
        addMachineSlots(tile);
        this.machineSlotCount = slots.size();
        addStandardInventorySlots(playerInventory, 8, inventoryY);
    }

    protected abstract void addMachineSlots(T tile);

    public T getTile() {
        return tile;
    }

    protected static <T extends TileBase> T resolveTile(Inventory playerInventory, BlockPos pos, Class<T> type) {
        BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(pos);
        if (type.isInstance(blockEntity)) {
            return type.cast(blockEntity);
        }
        throw new IllegalStateException("No " + type.getSimpleName() + " block entity at " + pos);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            if (index < machineSlotCount) {
                if (!moveItemStackTo(stack, machineSlotCount, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stack, 0, machineSlotCount, false)) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return result;
    }

    @Override
    public boolean stillValid(Player player) {
        return tile.isUsableByPlayer(player);
    }
}
