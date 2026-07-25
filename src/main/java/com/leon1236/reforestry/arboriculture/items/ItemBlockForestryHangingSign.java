package com.leon1236.reforestry.arboriculture.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.blocks.BlockForestryHangingSign;
import com.leon1236.reforestry.arboriculture.WoodHelper;

public class ItemBlockForestryHangingSign extends HangingSignItem {
    private final BlockForestryHangingSign hangingBlock;

    public ItemBlockForestryHangingSign(BlockForestryHangingSign hangingBlock, Block wallHangingBlock, Properties properties) {
        super(hangingBlock, wallHangingBlock, properties);
        this.hangingBlock = hangingBlock;
    }

    @Override
    public Component getName(ItemStack stack) {
        return WoodHelper.getDisplayName(WoodBlockKind.HANGING_SIGN, false, hangingBlock.getWoodType());
    }
}
