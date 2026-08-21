package com.leon1236.reforestry.extra_bees.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.apiculture.multiblock.MultiblockLogicAlveary;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesTiles;

public class TileAlvearyRainShield extends TileExtraBeeAlveary
		implements IAlvearyComponent.BeeModifier<MultiblockLogicAlveary> {
	private static final IBeeModifier MODIFIER = new IBeeModifier() {
		@Override
		public boolean isSealed() {
			return true;
		}
	};

	public TileAlvearyRainShield(BlockPos pos, BlockState state) {
		super(ExtraBeesTiles.ALVEARY_RAIN_SHIELD.type(), BlockExtraBeeAlvearyType.RAIN_SHIELD, pos, state);
	}

	@Override
	public IBeeModifier getBeeModifier() {
		return MODIFIER;
	}
}
