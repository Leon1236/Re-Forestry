package com.leon1236.reforestry.arboriculture.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.arboriculture.blocks.BlockDecorativeLeaves;
import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;

public class ItemBlockDecorativeLeaves extends BlockItem {
    public ItemBlockDecorativeLeaves(BlockDecorativeLeaves block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        BlockDecorativeLeaves block = (BlockDecorativeLeaves) getBlock();
        ForestryLeafType leafType = block.getType();
        if (leafType.getSpecies() == null) {
            return Component.translatable("for.trees.grammar.leaves.type");
        }
        return ItemBlockLeaves.getDisplayName(leafType.getSpecies());
    }
}
