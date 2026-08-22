package com.leon1236.reforestry.core.genetics;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.core.network.PacketRegistry;
import com.leon1236.reforestry.core.network.packets.GenomeTrackerSyncPayload;

public class BreedingTracker extends SavedData implements IBreedingTracker {
	public static final String TYPE_KEY = "TYPE";
	public static final String SPECIES_KEY = "SD";
	public static final String MUTATIONS_KEY = "MD";
	public static final String RESEARCHED_KEY = "RD";
	private static final String MUTATION_FORMAT = "%s-%s=%s";

	private final Identifier typeId;
	private final Set<Identifier> discoveredSpecies = new HashSet<>();
	private final Set<String> discoveredMutations = new HashSet<>();
	private final Set<String> researchedMutations = new HashSet<>();

	@Nullable
	private GameProfile username;
	@Nullable
	private Level level;

	public BreedingTracker(Identifier typeId) {
		this(typeId, List.of(), List.of(), List.of());
	}

	protected BreedingTracker(Identifier typeId, List<Identifier> discoveredSpecies, List<String> discoveredMutations,
			List<String> researchedMutations) {
		this.typeId = typeId;
		this.discoveredSpecies.addAll(discoveredSpecies);
		this.discoveredMutations.addAll(discoveredMutations);
		this.researchedMutations.addAll(researchedMutations);
	}

	public static Codec<BreedingTracker> codec(Identifier typeId) {
		return RecordCodecBuilder.create(instance -> instance.group(
				Identifier.CODEC.listOf().optionalFieldOf(SPECIES_KEY, List.of()).forGetter(tracker -> List.copyOf(tracker.discoveredSpecies)),
				Codec.STRING.listOf().optionalFieldOf(MUTATIONS_KEY, List.of()).forGetter(tracker -> List.copyOf(tracker.discoveredMutations)),
				Codec.STRING.listOf().optionalFieldOf(RESEARCHED_KEY, List.of()).forGetter(tracker -> List.copyOf(tracker.researchedMutations))
		).apply(instance, (species, mutations, researched) -> new BreedingTracker(typeId, species, mutations, researched)));
	}

	public static SavedDataType<BreedingTracker> typeFor(Identifier speciesTypeId, @Nullable GameProfile profile) {
		return typeFor(speciesTypeId, profile, () -> new BreedingTracker(speciesTypeId), codec(speciesTypeId));
	}

	public static <T extends BreedingTracker> SavedDataType<T> typeFor(
			Identifier speciesTypeId,
			@Nullable GameProfile profile,
			Supplier<T> constructor,
			Codec<T> codec) {
		String playerKey = profile == null || profile.id() == null ? "common" : profile.id().toString();
		Identifier fileId = ReForestry.id("breeding_tracker/" + speciesTypeId.getPath() + "/" + playerKey);
		return new SavedDataType<>(fileId, constructor, codec, DataFixTypes.SAVED_DATA_SCOREBOARD);
	}

	public void setUsername(@Nullable GameProfile username) {
		this.username = username;
	}

	public void setLevel(@Nullable Level level) {
		this.level = level;
	}

	public Identifier typeId() {
		return typeId;
	}

	private static String getMutationString(IMutation mutation) {
		return String.format(MUTATION_FORMAT,
				mutation.firstParent(),
				mutation.secondParent(),
				mutation.result());
	}

	@Override
	public void syncToPlayer(Player player) {
		if (player instanceof ServerPlayer serverPlayer) {
			PacketRegistry.sendToPlayer(serverPlayer, new GenomeTrackerSyncPayload(writeNetworkTag(
					this.discoveredSpecies, this.discoveredMutations, this.researchedMutations)));
		}
	}

	protected void sendUpdate(Collection<Identifier> discoveredSpecies, Collection<String> discoveredMutations,
			Collection<String> researchedMutations) {
		if (!(this.level instanceof ServerLevel serverLevel) || this.username == null || this.username.id() == null) {
			return;
		}
		ServerPlayer player = serverLevel.getServer().getPlayerList().getPlayer(this.username.id());
		if (player == null) {
			return;
		}
		PacketRegistry.sendToPlayer(player, new GenomeTrackerSyncPayload(writeNetworkTag(
				discoveredSpecies, discoveredMutations, researchedMutations)));
	}

	private CompoundTag writeNetworkTag(Collection<Identifier> discoveredSpecies, Collection<String> discoveredMutations,
			Collection<String> researchedMutations) {
		CompoundTag nbt = new CompoundTag();
		nbt.putString(TYPE_KEY, this.typeId.toString());
		ListTag speciesList = new ListTag();
		for (Identifier speciesId : discoveredSpecies) {
			speciesList.add(StringTag.valueOf(speciesId.toString()));
		}
		nbt.put(SPECIES_KEY, speciesList);
		writeStringList(nbt, MUTATIONS_KEY, discoveredMutations);
		writeStringList(nbt, RESEARCHED_KEY, researchedMutations);
		writeUpdateData(nbt);
		return nbt;
	}

	public void mergeFromNetwork(CompoundTag nbt) {
		readIdentifierList(nbt, SPECIES_KEY, this.discoveredSpecies::add);
		readStringList(nbt, MUTATIONS_KEY, this.discoveredMutations::add);
		readStringList(nbt, RESEARCHED_KEY, this.researchedMutations::add);
		readUpdateData(nbt);
	}

	protected void writeUpdateData(CompoundTag nbt) {
	}

	protected void readUpdateData(CompoundTag nbt) {
	}

	protected List<Identifier> encodedSpecies() {
		return List.copyOf(this.discoveredSpecies);
	}

	protected List<String> encodedMutations() {
		return List.copyOf(this.discoveredMutations);
	}

	protected List<String> encodedResearched() {
		return List.copyOf(this.researchedMutations);
	}

	private static void writeStringList(CompoundTag nbt, String key, Iterable<String> values) {
		ListTag list = new ListTag();
		for (String value : values) {
			list.add(StringTag.valueOf(value));
		}
		nbt.put(key, list);
	}

	private static void readStringList(CompoundTag nbt, String key, Consumer<String> values) {
		ListTag list = nbt.getListOrEmpty(key);
		for (int i = 0; i < list.size(); i++) {
			String value = list.getStringOr(i, "");
			if (!value.isEmpty()) {
				values.accept(value);
			}
		}
	}

	private static void readIdentifierList(CompoundTag nbt, String key, Consumer<Identifier> values) {
		readStringList(nbt, key, raw -> {
			Identifier id = Identifier.tryParse(raw);
			if (id != null) {
				values.accept(id);
			}
		});
	}

	@Override
	public void registerMutation(IMutation mutation) {
		String mutationString = getMutationString(mutation);
		if (this.discoveredMutations.add(mutationString)) {
			setDirty();
			sendUpdate(List.of(), List.of(mutationString), List.of());
		}
	}

	@Override
	public boolean isDiscovered(IMutation mutation) {
		String mutationString = getMutationString(mutation);
		return this.discoveredMutations.contains(mutationString) || this.researchedMutations.contains(mutationString);
	}

	@Override
	public boolean isDiscovered(Identifier speciesId) {
		return this.discoveredSpecies.contains(speciesId);
	}

	@Override
	public Collection<Identifier> getDiscoveredSpecies() {
		return this.discoveredSpecies;
	}

	@Override
	public int getSpeciesBred() {
		return this.discoveredSpecies.size();
	}

	@Override
	public void registerBirth(Identifier speciesId) {
		registerSpecies(speciesId);
	}

	@Override
	public void registerPickup(Identifier speciesId) {
		registerSpecies(speciesId);
	}

	@Override
	public void registerSpecies(Identifier speciesId) {
		if (this.discoveredSpecies.add(speciesId)) {
			setDirty();
			sendUpdate(List.of(speciesId), List.of(), List.of());
		}
	}

	@Override
	public void researchMutation(IMutation mutation) {
		String mutationString = getMutationString(mutation);
		if (this.researchedMutations.add(mutationString)) {
			setDirty();
			registerMutation(mutation);
			sendUpdate(List.of(), List.of(), List.of(mutationString));
		}
	}

	@Override
	public boolean isResearched(IMutation mutation) {
		return this.researchedMutations.contains(getMutationString(mutation));
	}
}
