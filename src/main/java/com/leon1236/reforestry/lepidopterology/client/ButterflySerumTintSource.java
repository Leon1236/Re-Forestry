package com.leon1236.reforestry.lepidopterology.client;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyDataComponents;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;

public record ButterflySerumTintSource() implements ItemTintSource {
	public static final ButterflySerumTintSource INSTANCE = new ButterflySerumTintSource();
	public static final MapCodec<ButterflySerumTintSource> MAP_CODEC = MapCodec.unit(INSTANCE);

	@Override
	public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
		IGenome genome = stack.get(LepidopterologyDataComponents.BUTTERFLY_GENOME.type());
		if (genome == null) {
			return ARGB.opaque(0xffffff);
		}
		return ARGB.opaque(genome.getActiveAllele(ButterflyChromosomes.SPECIES).value().getSerumColor());
	}

	@Override
	public MapCodec<? extends ItemTintSource> type() {
		return MAP_CODEC;
	}
}
