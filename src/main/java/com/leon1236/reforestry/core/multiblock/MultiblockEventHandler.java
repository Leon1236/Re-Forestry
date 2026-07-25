package com.leon1236.reforestry.core.multiblock;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;

import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;

public final class MultiblockEventHandler {
	private MultiblockEventHandler() {
	}

	public static void register() {
		ServerTickEvents.START_LEVEL_TICK.register(MultiblockRegistry::tickStart);

		ServerChunkEvents.CHUNK_LOAD.register((level, chunk, generated) ->
				MultiblockRegistry.onChunkLoaded(level, chunk.getPos().x(), chunk.getPos().z()));

		ServerChunkEvents.CHUNK_UNLOAD.register((level, chunk) ->
				unloadMultiblockParts(level, chunk));

		ServerBlockEntityEvents.BLOCK_ENTITY_LOAD.register((blockEntity, level) -> {
			if (blockEntity instanceof IMultiblockComponent part) {
				part.getMultiblockLogic().validate(level, part);
			}
		});

		ServerLevelEvents.UNLOAD.register((server, level) -> MultiblockRegistry.onWorldUnloaded(level));
	}

	static void unloadMultiblockParts(net.minecraft.world.level.Level level, LevelChunk chunk) {
		for (BlockEntity blockEntity : chunk.getBlockEntities().values()) {
			if (blockEntity instanceof IMultiblockComponent part) {
				part.getMultiblockLogic().onChunkUnload(level, part);
			}
		}
	}
}
