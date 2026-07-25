package com.leon1236.reforestry.apiculture.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.apiculture.features.ApicultureMenuTypes;
import com.leon1236.reforestry.apiculture.tiles.TileBeeHousing;

public class ContainerBeeHousing extends AbstractContainerMenu {
    private static final int[][] PRODUCT_SLOT_POSITIONS = {
            {116, 52}, {137, 39}, {137, 65}, {116, 78}, {95, 65}, {95, 39}, {116, 26},
    };
    private static final int[][] FRAME_SLOT_POSITIONS = {
            {66, 23}, {66, 52}, {66, 81},
    };

    private final TileBeeHousing housing;
    private final int housingSlotCount;

    public ContainerBeeHousing(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveHousing(playerInventory, pos));
    }

    public ContainerBeeHousing(int containerId, Inventory playerInventory, TileBeeHousing housing) {
        super(ApicultureMenuTypes.BEE_HOUSING.type(), containerId);
        this.housing = housing;
        checkContainerSize(housing, TileBeeHousing.SLOT_COUNT);
        housing.startOpen(playerInventory.player);

        addSlot(new Slot(housing, TileBeeHousing.SLOT_QUEEN, 29, 39));
        addSlot(new Slot(housing, TileBeeHousing.SLOT_DRONE, 29, 65));
        for (int i = 0; i < PRODUCT_SLOT_POSITIONS.length; i++) {
            addSlot(new Slot(housing, TileBeeHousing.SLOT_PRODUCT_1 + i,
                    PRODUCT_SLOT_POSITIONS[i][0], PRODUCT_SLOT_POSITIONS[i][1]));
        }
        if (housing.hasFrames()) {
            for (int i = 0; i < FRAME_SLOT_POSITIONS.length; i++) {
                addSlot(new Slot(housing, TileBeeHousing.SLOT_FRAME_1 + i,
                        FRAME_SLOT_POSITIONS[i][0], FRAME_SLOT_POSITIONS[i][1]));
            }
        }
        this.housingSlotCount = slots.size();

        addStandardInventorySlots(playerInventory, 8, 107);
        addDataSlots(housing.getProgressData());
        addDataSlots(housing.getErrorData());
        housing.getBeekeepingLogic().onGuiOpened();
    }

    public int getWorkProgressPercent() {
        return housing.getProgressData().get(0);
    }

    public int getErrorCount() {
        return housing.getErrorData().get(0);
    }

    public short getErrorId(int index) {
        return (short) housing.getErrorData().get(index + 1);
    }

    private static TileBeeHousing resolveHousing(Inventory playerInventory, BlockPos pos) {
        BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(pos);
        if (blockEntity instanceof TileBeeHousing housing) {
            return housing;
        }
        throw new IllegalStateException("No bee housing block entity at " + pos);
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
        return housing.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        housing.stopOpen(player);
    }
}
