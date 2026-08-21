package com.leon1236.reforestry.sorting.network.packets;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;
import com.leon1236.reforestry.sorting.tiles.TileGeneticFilter;

public record FilterChangeRulePayload(BlockPos pos, Direction facing, IFilterRuleType rule) implements CustomPacketPayload {
	public static final Type<FilterChangeRulePayload> TYPE = new Type<>(ReForestry.id("filter_change_rule"));

	public static final StreamCodec<RegistryFriendlyByteBuf, FilterChangeRulePayload> STREAM_CODEC = StreamCodec.of(
			FilterChangeRulePayload::encode,
			FilterChangeRulePayload::decode);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	private static void encode(RegistryFriendlyByteBuf buffer, FilterChangeRulePayload payload) {
		buffer.writeBlockPos(payload.pos);
		buffer.writeShort(payload.facing.get3DDataValue());
		buffer.writeShort(IForestryApi.get().getFilterManager().getId(payload.rule));
	}

	private static FilterChangeRulePayload decode(RegistryFriendlyByteBuf buffer) {
		BlockPos pos = buffer.readBlockPos();
		Direction facing = Direction.from3DDataValue(buffer.readShort());
		IFilterRuleType rule = IForestryApi.get().getFilterManager().getRuleOrDefault(buffer.readShort());
		return new FilterChangeRulePayload(pos, facing, rule);
	}

	public static void handle(FilterChangeRulePayload payload, ServerPlayNetworking.Context context) {
		ServerPlayer player = context.player();
		BlockEntity blockEntity = player.level().getBlockEntity(payload.pos());
		if (!(blockEntity instanceof TileGeneticFilter tile) || !tile.isUsableByPlayer(player)) {
			return;
		}
		if (tile.getLogic().setRule(payload.facing(), payload.rule()) && player.level() instanceof ServerLevel serverLevel) {
			tile.setChanged();
			tile.getLogic().getNetworkHandler().sendToPlayers(tile.getLogic(), serverLevel, player);
		}
	}
}
