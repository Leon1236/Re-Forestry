package com.leon1236.reforestry.apiculture.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.apiculture.blocks.BlockAlvearyType;

public class TileAlvearyStabiliser extends TileAlveary implements IAlvearyComponent.BeeModifier<MultiblockLogicAlveary> {
	private static final IBeeModifier MODIFIER = new IBeeModifier() {
		@Override
		public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutation, float currentChance) {
			return 0.0f;
		}
	};

	public TileAlvearyStabiliser(BlockPos pos, BlockState state) {
		super(BlockAlvearyType.STABILISER, pos, state);
	}

	@Override
	public IBeeModifier getBeeModifier() {
		return MODIFIER;
	}
}
