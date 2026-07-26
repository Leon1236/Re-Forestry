package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.AABB;

import com.leon1236.reforestry.api.core.IArmorNaturalist;
import com.leon1236.reforestry.api.core.ISpectacleBlock;

@Environment(EnvType.CLIENT)
public final class SpectaclesHighlightRenderer {
	private SpectaclesHighlightRenderer() {
	}

	public static void register() {
		LevelRenderEvents.BEFORE_GIZMOS.register(context -> {
			Minecraft minecraft = Minecraft.getInstance();
			Player player = minecraft.player;
			if (player == null || minecraft.level == null || !IArmorNaturalist.hasNaturalistEye(player)) {
				return;
			}

			float partialTick = minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(false);
			int argb = getRainbowArgb(minecraft.level.getGameTime(), partialTick);
			GizmoStyle style = GizmoStyle.stroke(argb);

			int renderDistance = minecraft.options.getEffectiveRenderDistance();
			BlockPos playerPos = player.blockPosition();
			int playerChunkX = SectionPos.blockToSectionCoord(playerPos.getX());
			int playerChunkZ = SectionPos.blockToSectionCoord(playerPos.getZ());

			try (var ignored = context.levelRenderer().collectPerFrameRenderThreadGizmos()) {
				for (int chunkX = playerChunkX - renderDistance; chunkX <= playerChunkX + renderDistance; chunkX++) {
					for (int chunkZ = playerChunkZ - renderDistance; chunkZ <= playerChunkZ + renderDistance; chunkZ++) {
						if (!minecraft.level.getChunkSource().hasChunk(chunkX, chunkZ)) {
							continue;
						}
						LevelChunk chunk = minecraft.level.getChunk(chunkX, chunkZ);
						for (BlockEntity be : chunk.getBlockEntities().values()) {
							if (be instanceof ISpectacleBlock spectacle && spectacle.isHighlighted(player)) {
								BlockPos pos = be.getBlockPos();
								Gizmos.cuboid(new AABB(pos).inflate(0.001), style).setAlwaysOnTop();
							}
						}
					}
				}
			}
		});
	}

	private static int getRainbowArgb(long time, float partialTicks) {
		float hue = (180 * Mth.sin((time + partialTicks) / 30.0f) - 180) / 360.0f;
		if (hue < 0.0f) {
			hue += 1.0f;
		}
		return ARGB.opaque(Mth.hsvToRgb(hue, 0.5f, 0.8f));
	}
}
