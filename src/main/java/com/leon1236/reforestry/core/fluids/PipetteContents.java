package com.leon1236.reforestry.core.fluids;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.core.items.FluidContainerContents;

public final class PipetteContents {
	private final FluidContainerContents contents;

	@Nullable
	public static PipetteContents create(ItemStack stack) {
		FluidContainerContents contents = FluidContainerContents.get(stack);
		if (contents.amount() <= 0 || contents.variant().isBlank()) {
			return null;
		}
		return new PipetteContents(contents);
	}

	private PipetteContents(FluidContainerContents contents) {
		this.contents = contents;
	}

	public FluidContainerContents getContents() {
		return this.contents;
	}

	public boolean isFull() {
		return this.contents.amount() >= FluidConstants.BUCKET;
	}

	public void addTooltip(Consumer<Component> tooltip) {
		long mb = FluidUnits.dropletsToMb(this.contents.amount());
		tooltip.accept(FluidVariantAttributes.getName(this.contents.variant()).copy()
				.append(" (" + mb + " mb)")
				.withStyle(ChatFormatting.GRAY));
	}
}
