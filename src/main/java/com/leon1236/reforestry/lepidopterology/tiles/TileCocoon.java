package com.leon1236.reforestry.lepidopterology.tiles;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.lepidopterology.blocks.BlockCocoon;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyTiles;
import com.leon1236.reforestry.lepidopterology.genetics.Butterfly;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflySpeciesType;

public class TileCocoon extends BlockEntity {
	private int maturationTime;
	private IButterfly caterpillar = ButterflySpeciesType.INSTANCE.getDefaultSpecies().createIndividual();
	private boolean isSolid;

	public TileCocoon(BlockPos pos, BlockState state, boolean isSolid) {
		super(isSolid ? LepidopterologyTiles.SOLID_COCOON.type() : LepidopterologyTiles.COCOON.type(), pos, state);
		this.isSolid = isSolid;
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		if (this.caterpillar instanceof Butterfly butterfly) {
			output.store("Caterpillar", Butterfly.CODEC, butterfly);
		}
		output.putInt("CATMAT", this.maturationTime);
		output.putBoolean("isSolid", this.isSolid);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.caterpillar = input.read("Caterpillar", Butterfly.CODEC)
				.map(IButterfly.class::cast)
				.orElseGet(() -> ButterflySpeciesType.INSTANCE.getDefaultSpecies().createIndividual());
		this.maturationTime = input.getIntOr("CATMAT", 0);
		this.isSolid = input.getBooleanOr("isSolid", this.isSolid);
	}

	public void onBlockTick() {
		Level level = this.level;
		if (level == null) {
			return;
		}
		this.maturationTime++;
		IGenome caterpillarGenome = this.caterpillar.getGenome();
		int lifespan = caterpillarGenome.getActiveAllele(ButterflyChromosomes.LIFESPAN).value();
		int fertility = Math.max(1, caterpillarGenome.getActiveAllele(ButterflyChromosomes.FERTILITY).value());
		int caterpillarMatureTime = Math.round((float) lifespan / (fertility * 2));

		if (this.maturationTime >= caterpillarMatureTime) {
			int age = getBlockState().getValue(BlockCocoon.AGE);
			if (age < 2) {
				this.maturationTime = 0;
				BlockState blockState = getBlockState().setValue(BlockCocoon.AGE, age + 1);
				level.setBlock(this.worldPosition, blockState, Block.UPDATE_NEIGHBORS | Block.UPDATE_CLIENTS);
			} else if (this.caterpillar.canTakeFlight(level, getBlockPos().getX(), getBlockPos().getY(),
					getBlockPos().getZ())) {
				for (ItemStack drop : getCocoonDrops()) {
					Block.popResource(level, this.worldPosition, drop);
				}
				level.setBlockAndUpdate(getBlockPos(), Blocks.AIR.defaultBlockState());
				attemptButterflySpawn(level, this.caterpillar, getBlockPos());
			}
		}
	}

	private static void attemptButterflySpawn(Level world, IButterfly butterfly, BlockPos pos) {
		ButterflySpeciesType.INSTANCE.spawnButterflyInWorld(world, butterfly.copy(), pos.getX(), pos.getY() + 0.1f,
				pos.getZ());
	}

	public IButterfly getCaterpillar() {
		return this.caterpillar;
	}

	public void setCaterpillar(IButterfly caterpillar) {
		this.caterpillar = caterpillar;
		setChanged();
	}

	public List<ItemStack> getCocoonDrops() {
		IButterflyCocoon cocoon = this.caterpillar.getGenome().getActiveAllele(ButterflyChromosomes.COCOON).value();
		return this.caterpillar.getCocoonDrop(this.isSolid, cocoon);
	}
}
