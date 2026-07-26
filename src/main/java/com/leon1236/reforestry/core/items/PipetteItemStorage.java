package com.leon1236.reforestry.core.items;

import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantItemStorage;

public final class PipetteItemStorage extends SingleVariantItemStorage<FluidVariant> {
	public PipetteItemStorage(ContainerItemContext context) {
		super(context);
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
		return FluidConstants.BUCKET;
	}

	@Override
	protected ItemVariant getUpdatedVariant(ItemVariant currentVariant, FluidVariant newResource, long newAmount) {
		ItemStack stack = currentVariant.toStack();
		FluidContainerContents.set(stack, newResource, newAmount);
		return ItemVariant.of(stack);
	}
}
