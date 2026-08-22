package com.leon1236.reforestry.core.network;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityLevelChangeEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.core.genetics.root.BreedingTrackerManager;
import com.leon1236.reforestry.apiculture.network.HabitatBiomePointerPayload;
import com.leon1236.reforestry.core.network.packets.EscritoireGameSyncPayload;
import com.leon1236.reforestry.core.network.packets.GenomeTrackerSyncPayload;
import com.leon1236.reforestry.core.network.packets.WorktableRecipeRequestPayload;

public final class PacketRegistry {
	private static final Identifier[] SYNC_SPECIES_TYPES = {
			ForestrySpeciesTypes.BEE,
			ForestrySpeciesTypes.TREE,
			ForestrySpeciesTypes.BUTTERFLY
	};

	private PacketRegistry() {
	}

	public static void init() {
		registerClientbound(GenomeTrackerSyncPayload.TYPE, GenomeTrackerSyncPayload.STREAM_CODEC);
		registerClientbound(EscritoireGameSyncPayload.TYPE, EscritoireGameSyncPayload.STREAM_CODEC);
		registerClientbound(HabitatBiomePointerPayload.TYPE, HabitatBiomePointerPayload.STREAM_CODEC);
		registerServerbound(WorktableRecipeRequestPayload.TYPE, WorktableRecipeRequestPayload.STREAM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(WorktableRecipeRequestPayload.TYPE, WorktableRecipeRequestPayload::handle);
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> syncBreedingTrackers(handler.getPlayer()));
		ServerEntityLevelChangeEvents.AFTER_PLAYER_CHANGE_LEVEL.register((player, origin, destination) ->
				syncBreedingTrackers(player));
	}

	public static <T extends CustomPacketPayload> void registerClientbound(
			CustomPacketPayload.Type<T> type,
			StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
		PayloadTypeRegistry.clientboundPlay().register(type, codec);
	}

	public static <T extends CustomPacketPayload> void registerServerbound(
			CustomPacketPayload.Type<T> type,
			StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
		PayloadTypeRegistry.serverboundPlay().register(type, codec);
	}

	public static void sendToPlayer(ServerPlayer player, CustomPacketPayload payload) {
		ServerPlayNetworking.send(player, payload);
	}

	public static void syncBreedingTrackers(ServerPlayer player) {
		for (Identifier typeId : SYNC_SPECIES_TYPES) {
			IBreedingTracker tracker = BreedingTrackerManager.INSTANCE.getTracker(
					typeId, player.level(), player.getGameProfile());
			tracker.syncToPlayer(player);
		}
	}
}
