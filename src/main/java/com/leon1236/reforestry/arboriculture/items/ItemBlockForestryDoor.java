package com.leon1236.reforestry.arboriculture.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryDoor;
import com.leon1236.reforestry.arboriculture.WoodHelper;

public class ItemBlockForestryDoor extends DoubleHighBlockItem {
    private final BlockForestryDoor doorBlock;

    public ItemBlockForestryDoor(BlockForestryDoor block, Properties properties) {
        super(block, properties);
        this.doorBlock = block;
    }

    @Override
    public Component getName(ItemStack stack) {
        return WoodHelper.getDisplayName(WoodBlockKind.DOOR, false, doorBlock.getWoodType());
    }
}
