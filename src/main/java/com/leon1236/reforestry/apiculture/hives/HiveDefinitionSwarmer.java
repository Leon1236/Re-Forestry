package com.leon1236.reforestry.apiculture.hives;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.hives.IHiveDefinition;
import com.leon1236.reforestry.api.apiculture.hives.IHiveGen;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.ReforestryBiomeTags;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.apiculture.blocks.BlockHiveType;
import com.leon1236.reforestry.apiculture.features.ApicultureBlocks;
import com.leon1236.reforestry.apiculture.tiles.TileHive;
import com.leon1236.reforestry.core.tiles.TileUtil;

public class HiveDefinitionSwarmer implements IHiveDefinition {
	private static final IHiveGen HIVE_GEN = new HiveGenGround(ReforestryBiomeTags.Blocks.SWARM_BEE_GROUND);

	private final List<ItemStack> bees;

	public HiveDefinitionSwarmer(List<ItemStack> bees) {
		this.bees = bees;
	}

	@Override
	public IHiveGen getHiveGen() {
		return HIVE_GEN;
	}

	@Override
	public BlockState getBlockState() {
		return ApicultureBlocks.BEEHIVE.get(BlockHiveType.SWARM).block().defaultBlockState();
	}

	@Override
	public boolean isGoodBiome(Holder<Biome> biome) {
		return true;
	}

	@Override
	public boolean isGoodHumidity(HumidityType humidity) {
		return true;
	}

	@Override
	public boolean isGoodTemperature(TemperatureType temperature) {
		return true;
	}

	@Override
	public float getGenChance() {
		return 128.0f;
	}

	@Override
	public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
		TileUtil.actOnTile(level, pos, TileHive.class, tile -> tile.setContained(this.bees));
	}
}
