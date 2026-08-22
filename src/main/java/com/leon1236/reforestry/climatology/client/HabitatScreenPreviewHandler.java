package com.leon1236.reforestry.climatology.client;

import java.util.HashSet;
import java.util.Set;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

import com.leon1236.reforestry.api.climate.IClimateHousing;
import com.leon1236.reforestry.api.climate.IClimateTransformer;
import com.leon1236.reforestry.climatology.features.ClimatologyItems;
import com.leon1236.reforestry.climatology.items.ItemHabitatScreen;

@Environment(EnvType.CLIENT)
public final class HabitatScreenPreviewHandler {
	private static final int PREVIEW_COLOR = 0xAAF2B233;
	private static final Set<BlockPos> PREVIEW = new HashSet<>();
	private static BlockPos origin;
	private static int range = -1;
	private static boolean circular;

	private HabitatScreenPreviewHandler() {
	}

	public static void init() {
		ClientTickEvents.END_CLIENT_TICK.register(HabitatScreenPreviewHandler::onClientTick);
		LevelRenderEvents.BEFORE_GIZMOS.register(context -> {
			if (PREVIEW.isEmpty()) {
				return;
			}
			GizmoStyle style = GizmoStyle.fill(PREVIEW_COLOR);
			try (var _ = context.levelRenderer().collectPerFrameRenderThreadGizmos()) {
				for (BlockPos pos : PREVIEW) {
					Gizmos.cuboid(new AABB(pos).inflate(0.002), style).setAlwaysOnTop();
				}
			}
		});
	}

	private static void onClientTick(Minecraft client) {
		Player player = client.player;
		if (player == null || client.level == null || client.level.getGameTime() % 20 != 0) {
			return;
		}
		ItemStack stack = findScreen(player);
		if (stack.isEmpty()
				|| !ItemHabitatScreen.isValid(stack, client.level)
				|| !ItemHabitatScreen.isPreviewModeActive(stack)) {
			clear();
			return;
		}
		BlockPos pos = ItemHabitatScreen.getLinkedPos(stack);
		if (pos == null || player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) > 128 * 128) {
			clear();
			return;
		}
		if (!(client.level.getBlockEntity(pos) instanceof IClimateHousing housing)) {
			clear();
			return;
		}
		IClimateTransformer transformer = housing.getTransformer();
		update(pos, transformer.getRange(), transformer.isCircular());
	}

	private static ItemStack findScreen(Player player) {
		ItemStack main = player.getMainHandItem();
		if (!main.isEmpty() && main.getItem() == ClimatologyItems.HABITAT_SCREEN.item()) {
			return main;
		}
		ItemStack off = player.getOffhandItem();
		if (!off.isEmpty() && off.getItem() == ClimatologyItems.HABITAT_SCREEN.item()) {
			return off;
		}
		return ItemStack.EMPTY;
	}

	private static void update(BlockPos center, int newRange, boolean newCircular) {
		if (center.equals(origin) && newRange == range && newCircular == circular) {
			return;
		}
		PREVIEW.clear();
		for (int x = -newRange; x <= newRange; x++) {
			for (int z = -newRange; z <= newRange; z++) {
				boolean valid;
				if (newCircular) {
					double distance = Math.round(Math.sqrt(x * x + z * z));
					valid = distance <= newRange && distance > (newRange - 1);
				} else {
					valid = x == -newRange || x == newRange || z == -newRange || z == newRange;
				}
				if (valid) {
					PREVIEW.add(center.offset(x, 0, z));
				}
			}
		}
		origin = center.immutable();
		range = newRange;
		circular = newCircular;
	}

	private static void clear() {
		PREVIEW.clear();
		origin = null;
		range = -1;
		circular = false;
	}
}
