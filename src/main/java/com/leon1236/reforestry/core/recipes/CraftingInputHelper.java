package com.leon1236.reforestry.core.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;

public final class CraftingInputHelper {
    private CraftingInputHelper() {
    }

    public static CraftingInput fromGrid(Container backing, int startSlot) {
        List<ItemStack> stacks = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            stacks.add(backing.getItem(startSlot + i));
        }
        return CraftingInput.of(3, 3, stacks);
    }
}
