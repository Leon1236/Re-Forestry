package com.leon1236.reforestry.apiculture.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.apiculture.InventoryBeeHousing;
import com.leon1236.reforestry.apiculture.features.ApicultureMenuTypes;
import com.leon1236.reforestry.apiculture.multiblock.TileAlveary;

public class ContainerAlveary extends AbstractContainerMenu {
    private static final int[][] PRODUCT_SLOT_POSITIONS = {
            {116, 52}, {137, 39}, {137, 65}, {116, 78}, {95, 65}, {95, 39}, {116, 26},
    };

    private final TileAlveary tile;
    private final int housingSlotCount;

    public ContainerAlveary(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos));
    }

    public ContainerAlveary(int containerId, Inventory playerInventory, TileAlveary tile) {
        super(ApicultureMenuTypes.ALVEARY.type(), containerId);
        this.tile = tile;
        checkContainerSize(tile, InventoryBeeHousing.SLOT_COUNT);

        addSlot(new Slot(tile, InventoryBeeHousing.SLOT_QUEEN, 29, 39));
        addSlot(new Slot(tile, InventoryBeeHousing.SLOT_DRONE, 29, 65));
        for (int i = 0; i < PRODUCT_SLOT_POSITIONS.length; i++) {
            addSlot(new Slot(tile, InventoryBeeHousing.SLOT_PRODUCT_1 + i,
                    PRODUCT_SLOT_POSITIONS[i][0], PRODUCT_SLOT_POSITIONS[i][1]));
        }
        this.housingSlotCount = slots.size();

        addStandardInventorySlots(playerInventory, 8, 107);
        addDataSlots(tile.getProgressData());
        addDataSlots(tile.getErrorData());
        tile.getBeekeepingLogic().onGuiOpened();
    }

    public TileAlveary getTile() {
        return tile;
    }

    public int getWorkProgressPercent() {
        return tile.getProgressData().get(0);
    }

    public int getErrorCount() {
        return tile.getErrorData().get(0);
    }

    public short getErrorId(int index) {
        return (short) tile.getErrorData().get(index + 1);
    }

    private static TileAlveary resolveTile(Inventory playerInventory, BlockPos pos) {
        BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(pos);
        if (blockEntity instanceof TileAlveary tile) {
            return tile;
        }
        throw new IllegalStateException("No alveary block entity at " + pos);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            if (index < housingSlotCount) {
                if (!moveItemStackTo(stack, housingSlotCount, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stack, 0, housingSlotCount, false)) {
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
