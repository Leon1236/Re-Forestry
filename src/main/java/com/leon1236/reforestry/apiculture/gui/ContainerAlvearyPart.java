package com.leon1236.reforestry.apiculture.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.apiculture.multiblock.TileAlveary;

public abstract class ContainerAlvearyPart<T extends TileAlveary> extends AbstractContainerMenu {
    protected final T tile;
    private final int partSlotCount;

    protected ContainerAlvearyPart(MenuType<?> menuType, int containerId, Inventory playerInventory, T tile,
            int inventoryY) {
        super(menuType, containerId);
        this.tile = tile;
        addPartSlots(tile);
        this.partSlotCount = slots.size();
        addStandardInventorySlots(playerInventory, 8, inventoryY);
    }

    protected abstract void addPartSlots(T tile);

    public T getTile() {
        return tile;
    }

    protected static <T extends TileAlveary> T resolveTile(Inventory playerInventory, BlockPos pos, Class<T> type) {
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
            if (index < partSlotCount) {
                if (!moveItemStackTo(stack, partSlotCount, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stack, 0, partSlotCount, false)) {
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
        return tile.stillValid(player);
    }
}
