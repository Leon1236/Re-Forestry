package com.leon1236.reforestry.api.arboriculture;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public interface ICharcoalPileWall {
    int getCharcoalAmount();

    boolean matches(BlockState state);

    List<ItemStack> getDisplayItems();
}
