package com.leon1236.reforestry.farming.farmlogic.farmables;

import com.google.common.collect.ImmutableSet;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.ForestryTags;
import com.leon1236.reforestry.api.agriculture.ICrop;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.DefaultFruits;
import com.leon1236.reforestry.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.tiles.TileSapling;
import com.leon1236.reforestry.core.utils.BlockUtil;
import com.leon1236.reforestry.farming.farmlogic.crops.CropDestroy;

public class FarmableGE implements IFarmable {
	@Nullable
	private ImmutableSet<Item> windfall;

	private ImmutableSet<Item> windfall() {
		if (this.windfall == null) {
			ImmutableSet.Builder<Item> builder = new ImmutableSet.Builder<>();
			for (ITreeSpecies species : ArboricultureGenetics.getAllSpecies()) {
				IGenome genome = ArboricultureGenetics.getDefaultGenome(species.id());
				IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
				if (fruit != DefaultFruits.NONE) {
					for (IFruit.Product product : fruit.getProducts()) {
						builder.add(product.item());
					}
				}
			}
			this.windfall = builder.build();
		}
		return this.windfall;
	}

	@Override
	public boolean isSaplingAt(Level level, BlockPos pos, BlockState state) {
		return state.is(ForestryTags.Blocks.TREE_SAPLINGS);
	}

	@Override
	@Nullable
	public ICrop getCropAt(Level level, BlockPos pos, BlockState state) {
		if (!state.is(BlockTags.LOGS)) {
			return null;
		}

		return new CropDestroy(level, state, pos, null);
	}

	@Override
	public boolean plantSaplingAt(Player player, ItemStack germling, Level level, BlockPos pos) {
		IGenome genome = germling.get(ArboricultureDataComponents.TREE_GENOME.type());
		if (genome == null) {
			return false;
		}
		if (!level.getBlockState(pos).canBeReplaced()) {
			return false;
		}
		if (!level.getBlockState(pos.below()).is(BlockTags.SUPPORTS_VEGETATION)) {
			return false;
		}
		BlockState planted = ArboricultureBlocks.SAPLING.block().defaultBlockState();
		if (!BlockUtil.setBlockWithPlaceSound(level, pos, planted)) {
			return false;
		}
		if (level.getBlockEntity(pos) instanceof TileSapling sapling) {
			sapling.setGenome(genome);
		}
		return true;
	}

	@Override
	public boolean isGermling(ItemStack stack) {
		return stack.is(ArboricultureItems.SAPLING.item())
				&& stack.get(ArboricultureDataComponents.TREE_GENOME.type()) != null;
	}

	@Override
	public boolean isWindfall(ItemStack stack) {
		return windfall().contains(stack.getItem());
	}
}
