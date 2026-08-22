package com.leon1236.reforestry.climatology.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.climate.IClimateHousing;
import com.leon1236.reforestry.api.climate.IClimateState;
import com.leon1236.reforestry.core.climate.ClimateStateHelper;
import com.leon1236.reforestry.core.tiles.TileUtil;

public record SelectClimateTargetPayload(BlockPos pos, float temperature, float humidity) implements CustomPacketPayload {
	public static final Type<SelectClimateTargetPayload> TYPE = new Type<>(ReForestry.id("select_climate_targeted"));

	public static final StreamCodec<RegistryFriendlyByteBuf, SelectClimateTargetPayload> STREAM_CODEC = StreamCodec.of(
			SelectClimateTargetPayload::encode,
			SelectClimateTargetPayload::decode);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	private static void encode(RegistryFriendlyByteBuf buf, SelectClimateTargetPayload payload) {
		buf.writeBlockPos(payload.pos);
		buf.writeFloat(payload.temperature);
		buf.writeFloat(payload.humidity);
	}

	private static SelectClimateTargetPayload decode(RegistryFriendlyByteBuf buf) {
		return new SelectClimateTargetPayload(buf.readBlockPos(), buf.readFloat(), buf.readFloat());
	}

	public static void handle(SelectClimateTargetPayload payload, ServerPlayNetworking.Context context) {
		ServerPlayer player = context.player();
		if (!player.level().isLoaded(payload.pos())) {
			return;
		}
		BlockEntity blockEntity = player.level().getBlockEntity(payload.pos());
		if (!(blockEntity instanceof IClimateHousing housing)) {
			return;
		}
		if (!housing.getCoordinates().equals(payload.pos()) || !TileUtil.isUsableByPlayer(player, blockEntity)) {
			return;
		}
		IClimateState state = ClimateStateHelper.of(payload.temperature(), payload.humidity());
		housing.getTransformer().setTarget(ClimateStateHelper.INSTANCE.clamp(state));
	}
}
