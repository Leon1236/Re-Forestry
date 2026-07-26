package com.leon1236.reforestry.core.client;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.fluids.PipetteContents;

public record PipetteFluidTintSource() implements ItemTintSource {
	public static final PipetteFluidTintSource INSTANCE = new PipetteFluidTintSource();
	public static final MapCodec<PipetteFluidTintSource> MAP_CODEC = MapCodec.unit(INSTANCE);

	@Override
	public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
		PipetteContents contents = PipetteContents.create(stack);
		if (contents == null) {
			return ARGB.opaque(0xffffff);
		}
		return ARGB.opaque(RenderUtil.getFluidColor(contents.getContents().variant().getFluid()));
	}

	@Override
	public MapCodec<? extends ItemTintSource> type() {
		return MAP_CODEC;
	}
}
