package com.leon1236.reforestry.core.network.packets;

import io.netty.buffer.Unpooled;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.escritoire.EscritoireGame;

public record EscritoireGameSyncPayload(BlockPos pos, byte[] gameData) implements CustomPacketPayload {
	public static final Type<EscritoireGameSyncPayload> TYPE = new Type<>(ReForestry.id("escritoire_game_sync"));

	public static final StreamCodec<RegistryFriendlyByteBuf, EscritoireGameSyncPayload> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC,
			EscritoireGameSyncPayload::pos,
			ByteBufCodecs.BYTE_ARRAY,
			EscritoireGameSyncPayload::gameData,
			EscritoireGameSyncPayload::new);

	public static EscritoireGameSyncPayload of(BlockPos pos, EscritoireGame game) {
		FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
		game.writeData(buffer);
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.readBytes(bytes);
		return new EscritoireGameSyncPayload(pos, bytes);
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public void applyTo(EscritoireGame game) {
		FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.wrappedBuffer(this.gameData));
		game.readData(buffer);
	}
}
