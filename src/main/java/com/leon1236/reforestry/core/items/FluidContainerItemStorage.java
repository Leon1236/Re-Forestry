package com.leon1236.reforestry.core.items;

import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantItemStorage;

import com.leon1236.reforestry.core.items.definitions.EnumContainerType;

public final class FluidContainerItemStorage extends SingleVariantItemStorage<FluidVariant> {
    private static final long CAPACITY = FluidConstants.BUCKET;
    private static final double CAPSULE_MAX_TEMPERATURE = 310.15;

    private final EnumContainerType containerType;

    public FluidContainerItemStorage(ContainerItemContext context, EnumContainerType containerType) {
        super(context);
        this.containerType = containerType;
    }

    @Override
    protected FluidVariant getBlankResource() {
        return FluidVariant.blank();
    }

    @Override
    protected FluidVariant getResource(ItemVariant currentVariant) {
        return FluidContainerContents.get(currentVariant.toStack()).variant();
    }

    @Override
    protected long getAmount(ItemVariant currentVariant) {
        return FluidContainerContents.get(currentVariant.toStack()).amount();
    }

    @Override
    protected long getCapacity(FluidVariant variant) {
        return CAPACITY;
    }

    @Override
    protected ItemVariant getUpdatedVariant(ItemVariant currentVariant, FluidVariant newResource, long newAmount) {
        ItemStack stack = currentVariant.toStack();
        FluidContainerContents.set(stack, newResource, newAmount);
        return ItemVariant.of(stack);
    }

    @Override
    protected boolean canInsert(FluidVariant resource) {
        return contentsAllowed(resource);
    }

    @Override
    protected boolean canExtract(FluidVariant resource) {
        return contentsAllowed(resource);
    }

    private boolean contentsAllowed(FluidVariant variant) {
        if (variant.isBlank()) {
            return false;
        }
        if (this.containerType == EnumContainerType.CAPSULE) {
            return FluidVariantAttributes.getTemperature(variant) < CAPSULE_MAX_TEMPERATURE;
        }
        return true;
    }
}
