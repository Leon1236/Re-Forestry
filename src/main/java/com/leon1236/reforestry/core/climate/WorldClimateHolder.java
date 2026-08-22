package com.leon1236.reforestry.core.climate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.climate.IClimateState;
import com.leon1236.reforestry.api.climate.IClimateTransformer;
import com.leon1236.reforestry.api.climate.IWorldClimateHolder;
import com.leon1236.reforestry.api.climate.Position2D;

public class WorldClimateHolder extends SavedData implements IWorldClimateHolder {
	private static final TransformerData DEFAULT_DATA = new TransformerData(0L, ClimateStateHelper.INSTANCE.absent(), 0, false, new long[0]);

	private static final String TRANSFORMERS_KEY = "Transformers";
	private static final String CHUNK_KEY = "Chunk";
	private static final String TRANSFORMERS_DATA_KEY = "Data";
	private static final String STATE_DATA_KEY = "Data";
	private static final String POS_KEY = "Pos";
	private static final String RANGE_KEY = "Range";
	private static final String CIRCULAR_KEY = "circular";
	private static final String CHUNKS_KEY = "Chunks";

	public static final Codec<WorldClimateHolder> CODEC = CompoundTag.CODEC.xmap(WorldClimateHolder::load, WorldClimateHolder::save);

	public static final SavedDataType<WorldClimateHolder> TYPE = new SavedDataType<>(
			ReForestry.id("climate"),
			WorldClimateHolder::new,
			CODEC,
			DataFixTypes.SAVED_DATA_SCOREBOARD);

	private final Map<Long, TransformerData> transformers = new HashMap<>();
	private final Map<Long, long[]> transformersByChunk = new HashMap<>();
	private final Map<Long, Long> chunkUpdates = new HashMap<>();

	@Nullable
	private ServerLevel level;

	public WorldClimateHolder() {
	}

	private static WorldClimateHolder load(CompoundTag nbt) {
		WorldClimateHolder holder = new WorldClimateHolder();
		holder.read(nbt);
		return holder;
	}

	private CompoundTag save() {
		return write(new CompoundTag());
	}

	public void setLevel(@Nullable ServerLevel level) {
		this.level = level;
	}

	private void read(CompoundTag nbt) {
		transformers.clear();
		transformersByChunk.clear();
		ListTag transformerData = nbt.getListOrEmpty(TRANSFORMERS_KEY);
		for (int i = 0; i < transformerData.size(); i++) {
			TransformerData data = new TransformerData(transformerData.getCompoundOrEmpty(i));
			transformers.put(data.position, data);
		}
		ListTag chunkData = nbt.getListOrEmpty(CHUNK_KEY);
		for (int i = 0; i < chunkData.size(); i++) {
			CompoundTag tagCompound = chunkData.getCompoundOrEmpty(i);
			long pos = tagCompound.getLongOr(POS_KEY, 0L);
			long[] chunkTransformers = tagCompound.getLongArray(TRANSFORMERS_DATA_KEY).orElse(new long[0]);
			transformersByChunk.put(pos, chunkTransformers);
		}
	}

	private CompoundTag write(CompoundTag compound) {
		ListTag transformerData = new ListTag();
		for (TransformerData data : transformers.values()) {
			transformerData.add(data.write(new CompoundTag()));
		}
		compound.put(TRANSFORMERS_KEY, transformerData);
		ListTag chunkData = new ListTag();
		for (Map.Entry<Long, long[]> entry : transformersByChunk.entrySet()) {
			CompoundTag tagCompound = new CompoundTag();
			tagCompound.putLong(POS_KEY, entry.getKey());
			tagCompound.putLongArray(TRANSFORMERS_DATA_KEY, entry.getValue());
			chunkData.add(tagCompound);
		}
		compound.put(CHUNK_KEY, chunkData);
		return compound;
	}

	@Override
	public IClimateState getClimate(long position) {
		return transformers.getOrDefault(position, DEFAULT_DATA).climateState;
	}

	@Override
	public void addTransformer(long chunkPos, long transformerPos) {
		long[] oldData = transformersByChunk.get(chunkPos);
		long[] newData;
		if (oldData != null) {
			for (long pos : oldData) {
				if (pos == transformerPos) {
					return;
				}
			}
			newData = Arrays.copyOf(oldData, oldData.length + 1);
		} else {
			newData = new long[1];
		}
		newData[newData.length - 1] = transformerPos;
		transformersByChunk.put(chunkPos, newData);
		setDirty();
		markChunkUpdate(chunkPos);
	}

	@Override
	public void removeTransformer(long chunkPos, long transformerPos) {
		long[] oldData = transformersByChunk.get(chunkPos);
		if (oldData == null) {
			return;
		}
		for (long pos : oldData) {
			if (pos == transformerPos) {
				if (oldData.length == 1) {
					transformersByChunk.remove(chunkPos);
					chunkUpdates.remove(chunkPos);
				} else {
					long[] newData = new long[oldData.length - 1];
					int index = 0;
					for (long value : oldData) {
						if (value != transformerPos) {
							newData[index++] = value;
						}
					}
					transformersByChunk.put(chunkPos, newData);
				}
				setDirty();
				markChunkUpdate(chunkPos);
				return;
			}
		}
	}

	private void markChunkUpdate(long chunkPos) {
		if (level != null) {
			chunkUpdates.put(chunkPos, level.getGameTime());
		}
	}

	@Override
	public void updateTransformer(IClimateTransformer transformer) {
		BlockPos position = transformer.getCoordinates();
		long longPos = position.asLong();
		TransformerData data = transformers.get(longPos);
		if (data != null) {
			boolean needChunkUpdate = data.range != transformer.getRange() || data.circular != transformer.isCircular() || data.chunks.length == 0;
			boolean needClimateUpdate = !data.climateState.equals(transformer.getCurrent());
			data.climateState = transformer.getCurrent().toImmutable();
			if (needChunkUpdate) {
				data.circular = transformer.isCircular();
				data.range = transformer.getRange();
				data.chunks = updateTransformerChunks(transformer, needClimateUpdate);
			} else if (needClimateUpdate) {
				for (long chunkPos : data.chunks) {
					markChunkUpdate(chunkPos);
				}
			}
		} else {
			long[] transformerChunks = updateTransformerChunks(transformer, false);
			transformers.put(longPos, new TransformerData(longPos, transformer.getCurrent().toImmutable(), transformer.getRange(), transformer.isCircular(), transformerChunks));
		}
		setDirty();
	}

	private long[] updateTransformerChunks(IClimateTransformer transformer, boolean forceDirty) {
		BlockPos transformerPos = transformer.getCoordinates();
		long longPos = transformerPos.asLong();
		int range = transformer.getRange();
		Set<Long> chunkSet = new HashSet<>();
		for (int x = transformerPos.getX() - range; x <= transformerPos.getX() + range; x += 16) {
			for (int z = transformerPos.getZ() - range; z <= transformerPos.getZ() + range; z += 16) {
				long chunkPos = ChunkPos.pack(x >> 4, z >> 4);
				addTransformer(chunkPos, longPos);
				chunkSet.add(chunkPos);
				if (forceDirty) {
					markChunkUpdate(chunkPos);
				}
			}
		}
		return chunkSet.stream().mapToLong(Long::longValue).toArray();
	}

	@Override
	public void removeTransformer(IClimateTransformer transformer) {
		removeTransformerChunks(transformer);
		transformers.remove(transformer.getCoordinates().asLong());
		setDirty();
	}

	private void removeTransformerChunks(IClimateTransformer transformer) {
		long longPos = transformer.getCoordinates().asLong();
		TransformerData data = transformers.get(longPos);
		if (data == null) {
			return;
		}
		for (long chunkPos : data.chunks) {
			removeTransformer(chunkPos, longPos);
		}
	}

	@Override
	public int getRange(long position) {
		return transformers.getOrDefault(position, DEFAULT_DATA).range;
	}

	@Override
	public boolean isCircular(long position) {
		return transformers.getOrDefault(position, DEFAULT_DATA).circular;
	}

	@Override
	public boolean isPositionInTransformerRange(long position, Position2D blockPos) {
		BlockPos pos = BlockPos.of(position);
		int range = getRange(position);
		if (isCircular(position)) {
			double distance = Math.round(blockPos.getDistance(pos));
			return range > 0.0F && distance <= range;
		}
		return Mth.abs(blockPos.getX() - pos.getX()) <= range && Mth.abs(blockPos.getZ() - pos.getZ()) <= range;
	}

	@Override
	public IClimateState getState(BlockPos pos) {
		long chunkPos = ChunkPos.pack(pos.getX() >> 4, pos.getZ() >> 4);
		long[] chunkTransformers = transformersByChunk.get(chunkPos);
		if (chunkTransformers == null) {
			return ClimateStateHelper.INSTANCE.absent();
		}
		double transformerCount = 0;
		IClimateState state = ClimateStateHelper.INSTANCE.mutableZero();
		for (long transformerPos : chunkTransformers) {
			if (isPositionInTransformerRange(transformerPos, new Position2D(pos))) {
				state = state.add(getClimate(transformerPos));
				transformerCount++;
			}
		}
		return transformerCount > 0 ? state.multiply(1.0D / transformerCount).toImmutable() : ClimateStateHelper.INSTANCE.absent();
	}

	@Override
	public boolean hasTransformers(BlockPos pos) {
		return transformersByChunk.containsKey(ChunkPos.pack(pos.getX() >> 4, pos.getZ() >> 4));
	}

	@Override
	public long getLastUpdate(BlockPos pos) {
		return getLastUpdate(ChunkPos.pack(pos.getX() >> 4, pos.getZ() >> 4));
	}

	@Override
	public long getLastUpdate(long chunkPos) {
		return chunkUpdates.getOrDefault(chunkPos, 0L);
	}

	private static class TransformerData {
		private IClimateState climateState;
		private int range;
		private boolean circular;
		private final long position;
		private long[] chunks;

		private TransformerData(long position, IClimateState climateState, int range, boolean circular, long[] chunks) {
			this.position = position;
			this.climateState = climateState;
			this.range = range;
			this.circular = circular;
			this.chunks = chunks;
		}

		private TransformerData(CompoundTag nbt) {
			position = nbt.getLongOr(POS_KEY, 0L);
			range = nbt.getIntOr(RANGE_KEY, 0);
			climateState = ClimateStateHelper.INSTANCE.create(nbt.getCompoundOrEmpty(STATE_DATA_KEY));
			circular = nbt.getBooleanOr(CIRCULAR_KEY, false);
			chunks = nbt.getLongArray(CHUNKS_KEY).orElse(new long[0]);
		}

		private CompoundTag write(CompoundTag nbt) {
			nbt.putLong(POS_KEY, position);
			nbt.put(STATE_DATA_KEY, ClimateStateHelper.INSTANCE.writeToNBT(new CompoundTag(), climateState));
			nbt.putInt(RANGE_KEY, range);
			nbt.putBoolean(CIRCULAR_KEY, circular);
			nbt.putLongArray(CHUNKS_KEY, chunks);
			return nbt;
		}
	}
}
