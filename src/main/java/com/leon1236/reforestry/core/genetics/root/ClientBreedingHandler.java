package com.leon1236.reforestry.core.genetics.root;

import java.util.LinkedHashMap;
import java.util.Map;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.core.genetics.BreedingTracker;
import com.leon1236.reforestry.core.network.packets.GenomeTrackerSyncPayload;

@Environment(EnvType.CLIENT)
public final class ClientBreedingHandler implements BreedingTrackerManager.ClientAccess {
	private static final ClientBreedingHandler INSTANCE = new ClientBreedingHandler();

	private final Map<Identifier, BreedingTracker> trackerByType = new LinkedHashMap<>();

	private ClientBreedingHandler() {
	}

	public static void register() {
		BreedingTrackerManager.setClientAccess(INSTANCE);
		ClientPlayNetworking.registerGlobalReceiver(GenomeTrackerSyncPayload.TYPE, INSTANCE::receive);
		ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> INSTANCE.clear());
	}

	@Override
	public IBreedingTracker getTracker(Identifier speciesTypeId) {
		return trackerFor(speciesTypeId);
	}

	private BreedingTracker trackerFor(Identifier speciesTypeId) {
		return trackerByType.computeIfAbsent(speciesTypeId, BreedingTrackerManager::createTracker);
	}

	private void receive(GenomeTrackerSyncPayload payload, ClientPlayNetworking.Context context) {
		CompoundTag nbt = payload.nbt();
		Identifier typeId = Identifier.tryParse(nbt.getStringOr(BreedingTracker.TYPE_KEY, ""));
		if (typeId == null) {
			return;
		}
		trackerFor(typeId).mergeFromNetwork(nbt);
	}

	private void clear() {
		trackerByType.clear();
	}
}
