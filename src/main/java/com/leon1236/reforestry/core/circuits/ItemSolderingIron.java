package com.leon1236.reforestry.core.circuits;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.core.gui.ContainerSolderingIron;

public class ItemSolderingIron extends Item implements ISolderingIron {
    public ItemSolderingIron(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            player.openMenu(new SolderingMenuProvider());
        }
        return InteractionResult.SUCCESS;
    }

    private final class SolderingMenuProvider implements net.minecraft.world.MenuProvider {
        @Override
        public AbstractContainerMenu createMenu(int containerId, net.minecraft.world.entity.player.Inventory inventory, Player player) {
            return new ContainerSolderingIron(containerId, player, new ItemInventorySolderingIron(player));
        }

        @Override
        public Component getDisplayName() {
            return Component.translatable("item.reforestry.soldering_iron");
        }
    }
}
