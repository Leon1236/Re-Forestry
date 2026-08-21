package com.leon1236.reforestry.extratrees.genetics;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.blocks.BlockFruitPod;
import com.leon1236.reforestry.arboriculture.genetics.Fruit;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.tiles.TileFruitPod;
import com.leon1236.reforestry.core.tiles.TileUtil;
import com.leon1236.reforestry.core.utils.BlockUtil;
import com.leon1236.reforestry.extratrees.blocks.ExtraTreesPodType;
import com.leon1236.reforestry.extratrees.features.ExtraTreesBlocks;

public class ExtraTreesPodFruit extends Fruit {
	private final ExtraTreesPodType type;

	public ExtraTreesPodFruit(Identifier id, boolean dominant, ExtraTreesPodType type, List<IFruit.Product> products) {
		super(id, dominant, 2, products);
		this.type = type;
	}

	public ExtraTreesPodType getType() {
		return type;
	}

	@Override
	public boolean requiresFruitBlocks() {
		return true;
	}

	@Override
	public boolean trySpawnFruitBlock(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos pos) {
		if (rand.nextFloat() > getFruitChance(genome)) {
			return false;
		}
		IFruit activeAllele = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
		float yield = genome.getActiveAllele(TreeChromosomes.YIELD).value();
		return setFruitBlock(level, genome, activeAllele, yield, pos);
	}

	@Override
	public TagKey<Block> getLogTag() {
		return type.logTag();
	}

	public static boolean setFruitBlock(LevelAccessor level, IGenome genome, IFruit fruit, float yield, BlockPos pos) {
		Direction facing = BlockUtil.getValidPodFacing(level, pos, fruit.getLogTag());
		if (facing == null || !(fruit instanceof ExtraTreesPodFruit podFruit)
				|| !ExtraTreesBlocks.PODS.getAll().containsKey(podFruit.getType())) {
			return false;
		}

		BlockFruitPod fruitPod = ExtraTreesBlocks.PODS.get(podFruit.getType()).block();
		BlockState state = fruitPod.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, facing);
		boolean placed = level.setBlock(pos, state, 18);
		if (!placed) {
			return false;
		}

		if (level.getBlockState(pos).getBlock() != fruitPod) {
			return false;
		}

		TileFruitPod pod = TileUtil.getTile(level, pos, TileFruitPod.class);
		if (pod == null) {
			level.setBlock(pos, Blocks.AIR.defaultBlockState(), 18);
			return false;
		}

		pod.setProperties(genome, fruit, yield);
		return true;
	}
}
