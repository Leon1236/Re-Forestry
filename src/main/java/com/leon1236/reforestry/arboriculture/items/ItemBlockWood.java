package com.leon1236.reforestry.arboriculture.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ItemBlockWood extends BlockItem {
    private final Item vanillaItem;

    public ItemBlockWood(Block block, Properties properties, Item vanillaItem) {
        super(block, properties);
        this.vanillaItem = vanillaItem;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable("block.reforestry.fireproof", Component.translatable(vanillaItem.getDescriptionId()));
    }
}
