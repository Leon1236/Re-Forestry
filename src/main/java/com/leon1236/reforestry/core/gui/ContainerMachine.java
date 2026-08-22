package com.leon1236.reforestry.core.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.api.core.AccessMode;
import com.leon1236.reforestry.api.gui.IContainerSidedAccess;
import com.leon1236.reforestry.core.tiles.TileBase;

public abstract class ContainerMachine<T extends TileBase> extends AbstractContainerMenu implements IContainerSidedAccess {
    protected final T tile;
    private final int machineSlotCount;
    private final SimpleContainerData accessData = new SimpleContainerData(Direction.values().length);

    protected ContainerMachine(MenuType<?> menuType, int containerId, Inventory playerInventory, T tile, int inventoryY) {
        this(menuType, containerId, playerInventory, tile, 8, inventoryY);
    }

    protected ContainerMachine(MenuType<?> menuType, int containerId, Inventory playerInventory, T tile, int inventoryX, int inventoryY) {
        super(menuType, containerId);
        this.tile = tile;
        addMachineSlots(tile);
        this.machineSlotCount = slots.size();
        addStandardInventorySlots(playerInventory, inventoryX, inventoryY);
        addDataSlots(this.accessData);
        syncAccessFromTile();
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
                if (slot.isFake() || !moveItemStackTo(stack, machineSlotCount, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackToMachine(stack)) {
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

    private boolean moveItemStackToMachine(ItemStack stack) {
        boolean merged = false;
        if (stack.isStackable()) {
            for (int i = 0; i < this.machineSlotCount && !stack.isEmpty(); i++) {
                Slot target = this.slots.get(i);
                if (target.isFake() || !target.hasItem() || !target.mayPlace(stack)) {
                    continue;
                }
                ItemStack inSlot = target.getItem();
                if (!ItemStack.isSameItemSameComponents(stack, inSlot)) {
                    continue;
                }
                int max = Math.min(stack.getMaxStackSize(), target.getMaxStackSize(stack));
                int space = max - inSlot.getCount();
                if (space <= 0) {
                    continue;
                }
                int moved = Math.min(space, stack.getCount());
                inSlot.grow(moved);
                stack.shrink(moved);
                target.setChanged();
                merged = true;
            }
        }
        for (int i = 0; i < this.machineSlotCount && !stack.isEmpty(); i++) {
            Slot target = this.slots.get(i);
            if (target.isFake() || target.hasItem() || !target.mayPlace(stack)) {
                continue;
            }
            int max = Math.min(stack.getMaxStackSize(), target.getMaxStackSize(stack));
            ItemStack placed = stack.split(Math.min(max, stack.getCount()));
            target.setByPlayer(placed);
            merged = true;
        }
        return merged;
    }

    @Override
    public boolean stillValid(Player player) {
        return tile.isUsableByPlayer(player);
    }

    @Override
    public AccessMode getAccess(Direction direction) {
        return AccessMode.values()[this.accessData.get(direction.get3DDataValue())];
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (IContainerSidedAccess.directionFromButtonId(id) != null) {
            return handleAccessButton(player, id);
        }
        return false;
    }

    protected boolean handleAccessButton(Player player, int id) {
        Direction direction = IContainerSidedAccess.directionFromButtonId(id);
        if (direction == null) {
            return false;
        }
        int index = direction.get3DDataValue();
        if (player instanceof net.minecraft.server.level.ServerPlayer) {
            tile.cycleAccess(direction);
            this.accessData.set(index, tile.getAccess(direction).ordinal());
        } else {
            AccessMode current = AccessMode.values()[this.accessData.get(index)];
            this.accessData.set(index, current.next().ordinal());
        }
        return true;
    }

    private void syncAccessFromTile() {
        for (Direction direction : Direction.values()) {
            this.accessData.set(direction.get3DDataValue(), tile.getAccess(direction).ordinal());
        }
    }
}
