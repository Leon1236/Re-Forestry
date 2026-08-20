package com.leon1236.reforestry.sorting.network.packets;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.sorting.tiles.TileGeneticFilter;

public record FilterChangeGenomePayload(BlockPos pos, Direction facing, short index, boolean active,
		@Nullable Identifier species) implements CustomPacketPayload {
	public static final Type<FilterChangeGenomePayload> TYPE = new Type<>(ReForestry.id("filter_change_genome"));

	public static final StreamCodec<RegistryFriendlyByteBuf, FilterChangeGenomePayload> STREAM_CODEC = StreamCodec.of(
			FilterChangeGenomePayload::encode,
			FilterChangeGenomePayload::decode);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	private static void encode(RegistryFriendlyByteBuf buffer, FilterChangeGenomePayload payload) {
		buffer.writeBlockPos(payload.pos);
		buffer.writeShort(payload.facing.get3DDataValue());
		buffer.writeShort(payload.index);
		buffer.writeBoolean(payload.active);
		if (payload.species != null) {
			buffer.writeBoolean(true);
			buffer.writeIdentifier(payload.species);
		} else {
			buffer.writeBoolean(false);
		}
	}

	private static FilterChangeGenomePayload decode(RegistryFriendlyByteBuf buffer) {
		BlockPos pos = buffer.readBlockPos();
		Direction facing = Direction.from3DDataValue(buffer.readShort());
		short index = buffer.readShort();
		boolean active = buffer.readBoolean();
		Identifier species = buffer.readBoolean() ? buffer.readIdentifier() : null;
		return new FilterChangeGenomePayload(pos, facing, index, active, species);
	}

	public static void handle(FilterChangeGenomePayload payload, ServerPlayNetworking.Context context) {
		ServerPlayer player = context.player();
		BlockEntity blockEntity = player.level().getBlockEntity(payload.pos());
		if (!(blockEntity instanceof TileGeneticFilter tile) || !tile.isUsableByPlayer(player)) {
			return;
		}
		if (tile.getLogic().setGenomeFilter(payload.facing(), payload.index(), payload.active(), payload.species())
				&& player.level() instanceof ServerLevel serverLevel) {
			tile.setChanged();
			tile.getLogic().getNetworkHandler().sendToPlayers(tile.getLogic(), serverLevel, player);
		}
	}
}
