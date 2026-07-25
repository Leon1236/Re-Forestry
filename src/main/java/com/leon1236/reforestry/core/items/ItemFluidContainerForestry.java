package com.leon1236.reforestry.core.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.core.items.definitions.EnumContainerType;

public class ItemFluidContainerForestry extends Item {
    private final EnumContainerType type;

    public ItemFluidContainerForestry(EnumContainerType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public EnumContainerType getType() {
        return this.type;
    }

    @Override
    public Component getName(ItemStack stack) {
        FluidContainerContents contents = FluidContainerContents.get(stack);
        if (contents.amount() > 0 && !contents.variant().isBlank()) {
            String grammarKey = "item.reforestry." + this.type.getSerializedName() + ".grammar";
            return Component.translatable(grammarKey, FluidVariantAttributes.getName(contents.variant()));
        }
        return Component.translatable("item.reforestry." + this.type.getSerializedName() + ".empty");
    }
}
