package com.leon1236.reforestry.lepidopterology.client;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import org.jspecify.annotations.Nullable;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyDataComponents;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;

public record ButterflySpeciesSelectProperty() implements SelectItemModelProperty<Identifier> {
	public static final SelectItemModelProperty.Type<ButterflySpeciesSelectProperty, Identifier> TYPE =
			SelectItemModelProperty.Type.create(MapCodec.unit(new ButterflySpeciesSelectProperty()), Identifier.CODEC);

	@Override
	public @Nullable Identifier get(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity owner,
			int seed, ItemDisplayContext displayContext) {
		IGenome genome = itemStack.get(LepidopterologyDataComponents.BUTTERFLY_GENOME.type());
		if (genome == null) {
			return null;
		}
		return genome.getActiveAllele(ButterflyChromosomes.SPECIES).value().id();
	}

	@Override
	public Codec<Identifier> valueCodec() {
		return Identifier.CODEC;
	}

	@Override
	public SelectItemModelProperty.Type<ButterflySpeciesSelectProperty, Identifier> type() {
		return TYPE;
	}
}
