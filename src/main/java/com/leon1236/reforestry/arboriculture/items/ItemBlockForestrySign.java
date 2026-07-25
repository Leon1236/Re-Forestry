package com.leon1236.reforestry.arboriculture.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryStandingSign;
import com.leon1236.reforestry.arboriculture.WoodHelper;

public class ItemBlockForestrySign extends SignItem {
    private final BlockForestryStandingSign standingBlock;

    public ItemBlockForestrySign(BlockForestryStandingSign standingBlock, Block wallBlock, Properties properties) {
        super(standingBlock, wallBlock, properties);
        this.standingBlock = standingBlock;
    }

    @Override
    public Component getName(ItemStack stack) {
        return WoodHelper.getDisplayName(WoodBlockKind.SIGN, false, standingBlock.getWoodType());
    }
}
