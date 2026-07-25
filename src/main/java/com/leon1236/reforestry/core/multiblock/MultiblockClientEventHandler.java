package com.leon1236.reforestry.core.multiblock;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientBlockEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLevelEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.client.multiplayer.ClientLevel;

import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;

@Environment(EnvType.CLIENT)
public final class MultiblockClientEventHandler {
	private static ClientLevel lastLevel;

	private MultiblockClientEventHandler() {
	}

	public static void register() {
		ClientTickEvents.START_LEVEL_TICK.register(MultiblockRegistry::tickStart);

		ClientChunkEvents.CHUNK_LOAD.register((level, chunk) ->
				MultiblockRegistry.onChunkLoaded(level, chunk.getPos().x(), chunk.getPos().z()));

		ClientChunkEvents.CHUNK_UNLOAD.register(MultiblockEventHandler::unloadMultiblockParts);

		ClientBlockEntityEvents.BLOCK_ENTITY_LOAD.register((blockEntity, level) -> {
			if (blockEntity instanceof IMultiblockComponent part) {
				part.getMultiblockLogic().validate(level, part);
			}
		});

		ClientLevelEvents.AFTER_CLIENT_LEVEL_CHANGE.register((client, level) -> {
			if (lastLevel != null) {
				MultiblockRegistry.onWorldUnloaded(lastLevel);
			}
			lastLevel = level;
		});
	}
}
