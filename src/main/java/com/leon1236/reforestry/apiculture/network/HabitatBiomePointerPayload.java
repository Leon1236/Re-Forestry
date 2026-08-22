package com.leon1236.reforestry.apiculture.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;

public record HabitatBiomePointerPayload(BlockPos target) implements CustomPacketPayload {
	public static final CustomPacketPayload.Type<HabitatBiomePointerPayload> TYPE =
			new CustomPacketPayload.Type<>(ReForestry.id("habitat_biome_pointer"));

	public static final StreamCodec<RegistryFriendlyByteBuf, HabitatBiomePointerPayload> STREAM_CODEC =
			StreamCodec.composite(BlockPos.STREAM_CODEC, HabitatBiomePointerPayload::target, HabitatBiomePointerPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
