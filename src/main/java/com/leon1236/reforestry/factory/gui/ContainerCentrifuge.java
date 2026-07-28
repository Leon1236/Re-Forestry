package com.leon1236.reforestry.factory.gui;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.gui.IContainerRecipeBook;
import com.leon1236.reforestry.api.gui.MachineRecipeEntry;
import com.leon1236.reforestry.core.gui.ContainerSocketedMachine;
import com.leon1236.reforestry.core.gui.MachineGuiRecipes;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileCentrifuge;

public class ContainerCentrifuge extends ContainerSocketedMachine<TileCentrifuge> implements IContainerRecipeBook {
    private static final int RESOURCE_X = 16;
    private static final int RESOURCE_Y = 37;
    private static final int SOCKET_X = 79;
    private static final int SOCKET_Y = 37;
    private static final int PRODUCT_X = 112;
    private static final int PRODUCT_Y = 19;
    private static final int PRODUCT_GAP = 18;
    private static final int PRODUCT_COLUMNS = 3;
    private static final int INVENTORY_Y = 84;

    public ContainerCentrifuge(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileCentrifuge.class));
    }

    public ContainerCentrifuge(int containerId, Inventory playerInventory, TileCentrifuge tile) {
        super(FactoryMenuTypes.CENTRIFUGE.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getProgressData());
        addDataSlots(tile.getErrorData());
    }

    @Override
    protected void addMachineSlots(TileCentrifuge tile) {
        addSlot(new Slot(tile, TileCentrifuge.SLOT_RESOURCE, RESOURCE_X, RESOURCE_Y));
        addCircuitSocket(0, SOCKET_X, SOCKET_Y);
        for (int i = 0; i < TileCentrifuge.SLOT_PRODUCT_COUNT; i++) {
            int column = i % PRODUCT_COLUMNS;
            int row = i / PRODUCT_COLUMNS;
            addSlot(new Slot(tile, TileCentrifuge.SLOT_PRODUCT_1 + i,
                    PRODUCT_X + column * PRODUCT_GAP, PRODUCT_Y + row * PRODUCT_GAP));
        }
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

    @Override
    public List<MachineRecipeEntry> getGuiRecipes() {
        Level level = tile.getLevel();
        if (level == null) {
            return List.of();
        }
        return MachineGuiRecipes.centrifuge(level);
    }

    @Override
    public boolean selectRecipe(int index, Player player) {
        return false;
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (super.clickMenuButton(player, id)) {
            return true;
        }
        if (IContainerRecipeBook.isRecipeButton(id)) {
            return selectRecipe(IContainerRecipeBook.recipeIndex(id), player);
        }
        return false;
    }
}
