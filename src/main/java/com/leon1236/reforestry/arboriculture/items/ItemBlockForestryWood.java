package com.leon1236.reforestry.arboriculture.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.arboriculture.IWoodTyped;
import com.leon1236.reforestry.arboriculture.WoodHelper;

public class ItemBlockForestryWood<B extends Block & IWoodTyped> extends BlockItem {
    private final IWoodTyped wood;

    public ItemBlockForestryWood(B block, Properties properties) {
        super(block, properties);
        this.wood = block;
    }

    @Override
    public Component getName(ItemStack stack) {
        return WoodHelper.getDisplayName(wood, wood.getWoodType());
    }
}
