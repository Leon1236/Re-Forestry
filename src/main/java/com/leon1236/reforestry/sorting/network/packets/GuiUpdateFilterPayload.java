package com.leon1236.reforestry.sorting.network.packets;

import io.netty.buffer.Unpooled;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.sorting.FilterLogic;

public record GuiUpdateFilterPayload(BlockPos pos, byte[] data) implements CustomPacketPayload {
	public static final Type<GuiUpdateFilterPayload> TYPE = new Type<>(ReForestry.id("gui_update_filter"));

	public static final StreamCodec<RegistryFriendlyByteBuf, GuiUpdateFilterPayload> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC,
			GuiUpdateFilterPayload::pos,
			ByteBufCodecs.BYTE_ARRAY,
			GuiUpdateFilterPayload::data,
			GuiUpdateFilterPayload::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static GuiUpdateFilterPayload of(BlockPos pos, FilterLogic logic) {
		FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
		logic.writeGuiData(buffer);
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.readBytes(bytes);
		return new GuiUpdateFilterPayload(pos, bytes);
	}

	public void applyTo(FilterLogic logic) {
		logic.readGuiData(new FriendlyByteBuf(Unpooled.wrappedBuffer(this.data)));
	}
}
