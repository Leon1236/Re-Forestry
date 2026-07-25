package com.leon1236.reforestry.core.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.core.fluids.FluidUnits;

public record RecipeFluidAmount(FluidVariant variant, int amountMb) {
	private static final Codec<FluidVariant> FLUID_ID_CODEC = BuiltInRegistries.FLUID.byNameCodec()
			.xmap(FluidVariant::of, FluidVariant::getFluid);

	public static final Codec<RecipeFluidAmount> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			FLUID_ID_CODEC.fieldOf("fluid").forGetter(RecipeFluidAmount::variant),
			Codec.intRange(1, Integer.MAX_VALUE).fieldOf("amount").forGetter(RecipeFluidAmount::amountMb)
	).apply(instance, RecipeFluidAmount::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, RecipeFluidAmount> STREAM_CODEC = StreamCodec.composite(
			FluidVariant.PACKET_CODEC, RecipeFluidAmount::variant,
			ByteBufCodecs.VAR_INT, RecipeFluidAmount::amountMb,
			RecipeFluidAmount::new
	);

	public long amountDroplets() {
		return FluidUnits.mbToDroplets(this.amountMb);
	}

	public Fluid fluid() {
		return this.variant.getFluid();
	}
}
