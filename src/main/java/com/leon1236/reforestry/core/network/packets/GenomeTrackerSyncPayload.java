package com.leon1236.reforestry.core.network.packets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import com.leon1236.reforestry.ReForestry;

public record GenomeTrackerSyncPayload(CompoundTag nbt) implements CustomPacketPayload {
	public static final Type<GenomeTrackerSyncPayload> TYPE = new Type<>(ReForestry.id("genome_tracker_update"));

	public static final StreamCodec<RegistryFriendlyByteBuf, GenomeTrackerSyncPayload> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.COMPOUND_TAG,
			GenomeTrackerSyncPayload::nbt,
			GenomeTrackerSyncPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
