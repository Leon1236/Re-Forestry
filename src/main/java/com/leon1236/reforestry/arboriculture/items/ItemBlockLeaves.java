package com.leon1236.reforestry.arboriculture.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.arboriculture.blocks.BlockAbstractLeaves;
import com.leon1236.reforestry.arboriculture.blocks.BlockDefaultLeaves;
import com.leon1236.reforestry.arboriculture.blocks.BlockDefaultLeavesFruit;
import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;

public class ItemBlockLeaves extends BlockItem {
    public ItemBlockLeaves(BlockAbstractLeaves block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        ForestryLeafType type = leafType();
        if (type != null && type.getSpecies() != null) {
            return getDisplayName(type.getSpecies());
        }
        return Component.translatable("for.trees.grammar.leaves.type");
    }

    private ForestryLeafType leafType() {
        if (getBlock() instanceof BlockDefaultLeaves leaves) {
            return leaves.getType();
        }
        if (getBlock() instanceof BlockDefaultLeavesFruit leaves) {
            return leaves.getType();
        }
        return null;
    }

    public static Component getDisplayName(ITreeSpecies species) {
        Component speciesName = Component.translatable("allele.reforestry.tree_species." + species.id().getPath());
        Component leaves = Component.translatable("for.trees.grammar.leaves.type");
        return Component.translatable("for.trees.grammar.leaves", speciesName, leaves);
    }
}
