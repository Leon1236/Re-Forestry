package com.leon1236.reforestry.api.climate;

import net.minecraft.core.BlockPos;

public interface IWorldClimateHolder {

	IClimateState getClimate(long position);

	int getRange(long position);

	boolean isCircular(long position);

	void addTransformer(long chunkPos, long transformerPos);

	void removeTransformer(long chunkPos, long transformerPos);

	void updateTransformer(IClimateTransformer transformer);

	void removeTransformer(IClimateTransformer transformer);

	IClimateState getState(BlockPos pos);

	long getLastUpdate(BlockPos pos);

	long getLastUpdate(long chunkPos);

	boolean isPositionInTransformerRange(long position, Position2D blockPos);

	boolean hasTransformers(BlockPos pos);
}
