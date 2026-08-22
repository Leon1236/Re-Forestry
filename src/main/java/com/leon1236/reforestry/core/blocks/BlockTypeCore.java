package com.leon1236.reforestry.core.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.leon1236.reforestry.core.escritoire.TileEscritoire;
import com.leon1236.reforestry.core.features.CoreTiles;
import com.leon1236.reforestry.core.tiles.TileAnalyzer;

public enum BlockTypeCore implements IBlockType {
	ANALYZER("analyzer", new MachineProperties.Builder<>(CoreTiles.ANALYZER, "analyzer")
			.setServerTicker(TileAnalyzer::serverTick)
			.create()),
	ESCRITOIRE("escritoire", createEscritoireProperties());

	private final String serializedName;
	private final IMachineProperties<?> machineProperties;

	private static MachineProperties<? extends TileEscritoire> createEscritoireProperties() {
		VoxelShape desk = Block.box(0, 8, 0, 16, 11.5, 16);
		VoxelShape standRB = Block.box(13, 0, 13, 15, 10, 15);
		VoxelShape standRF = Block.box(13, 0, 1, 15, 10, 3);
		VoxelShape standLB = Block.box(1, 0, 13, 3, 10, 15);
		VoxelShape standLF = Block.box(1, 0, 1, 3, 10, 3);
		VoxelShape commonShape = Shapes.or(desk, standLB, standLF, standRB, standRF);

		VoxelShape[] shapes = {
				Shapes.or(commonShape, Block.box(0, 10, 0, 16, 16, 3.5)),
				Shapes.or(commonShape, Block.box(12.5, 10, 0, 16, 16, 16)),
				Shapes.or(commonShape, Block.box(0, 10, 12.5, 16, 16, 16)),
				Shapes.or(commonShape, Block.box(0, 10, 0, 3.5, 16, 16)),
		};

		return new MachineProperties.Builder<>(CoreTiles.ESCRITOIRE, "escritoire")
				.setShape((state, level, pos, context) -> shapes[state.getValue(BlockMachine.FACING).get2DDataValue()])
				.create();
	}

	BlockTypeCore(String serializedName, IMachineProperties<?> machineProperties) {
		this.serializedName = serializedName;
		this.machineProperties = machineProperties;
	}

	@Override
	public IMachineProperties<?> getMachineProperties() {
		return machineProperties;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
