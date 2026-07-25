package com.leon1236.reforestry.core.gui;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.circuits.ISocketable;
import com.leon1236.reforestry.core.circuits.ItemSolderingIron;
import com.leon1236.reforestry.core.tiles.TileBase;

public abstract class ContainerSocketedMachine<T extends TileBase & ISocketable> extends ContainerMachine<T> {
    protected ContainerSocketedMachine(MenuType<?> menuType, int containerId,
                                       net.minecraft.world.entity.player.Inventory playerInventory,
                                       T tile, int inventoryY) {
        super(menuType, containerId, playerInventory, tile, inventoryY);
    }

    protected void addCircuitSocket(int socketIndex, int x, int y) {
        addSlot(new SlotCircuitSocket(tile, socketIndex, x, y));
    }

    @Override
    public void clicked(int slotIndex, int button, ContainerInput clickType, Player player) {
        if (slotIndex >= 0 && slotIndex < slots.size()) {
            Slot slot = slots.get(slotIndex);
            if (slot instanceof SlotCircuitSocket socketSlot) {
                ItemStack carried = getCarried();
                if (!carried.isEmpty() && carried.getItem() instanceof ItemSolderingIron) {
                    if (socketSlot.tryRemoveWithSolderingIron(player, carried)) {
                        broadcastChanges();
                        return;
                    }
                }
            }
        }
        super.clicked(slotIndex, button, clickType, player);
    }
}
