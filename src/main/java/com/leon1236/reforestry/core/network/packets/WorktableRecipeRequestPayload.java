package com.leon1236.reforestry.core.network.packets;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.worktable.gui.ContainerWorktable;
import com.leon1236.reforestry.worktable.tiles.TileWorktable;

public record WorktableRecipeRequestPayload(BlockPos pos, List<ItemStack> stacks) implements CustomPacketPayload {
	public static final int SLOT_COUNT = 9;

	public static final Type<WorktableRecipeRequestPayload> TYPE = new Type<>(ReForestry.id("worktable_recipe_request"));

	public static final StreamCodec<RegistryFriendlyByteBuf, WorktableRecipeRequestPayload> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC,
			WorktableRecipeRequestPayload::pos,
			ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list(SLOT_COUNT)),
			WorktableRecipeRequestPayload::stacks,
			WorktableRecipeRequestPayload::new);

	public WorktableRecipeRequestPayload {
		stacks = normalize(stacks);
	}

	public static WorktableRecipeRequestPayload of(BlockPos pos, List<ItemStack> stacks) {
		return new WorktableRecipeRequestPayload(pos, stacks);
	}

	private static List<ItemStack> normalize(List<ItemStack> stacks) {
		List<ItemStack> normalized = new ArrayList<>(SLOT_COUNT);
		int count = Math.min(stacks.size(), SLOT_COUNT);
		for (int i = 0; i < count; i++) {
			ItemStack stack = stacks.get(i);
			normalized.add(stack == null || stack.isEmpty() ? ItemStack.EMPTY : stack.copy());
		}
		while (normalized.size() < SLOT_COUNT) {
			normalized.add(ItemStack.EMPTY);
		}
		return List.copyOf(normalized);
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(WorktableRecipeRequestPayload payload, ServerPlayNetworking.Context context) {
		ServerPlayer player = context.player();
		if (!(player.containerMenu instanceof ContainerWorktable menu)) {
			return;
		}
		TileWorktable tile = menu.getTile();
		if (!tile.getBlockPos().equals(payload.pos()) || !tile.stillValid(player)) {
			return;
		}
		tile.applyGhostCrafting(payload.stacks());
		menu.broadcastChanges();
	}
}
