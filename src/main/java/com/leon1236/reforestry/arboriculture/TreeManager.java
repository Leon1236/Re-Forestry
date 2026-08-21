package com.leon1236.reforestry.arboriculture;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.ICharcoalManager;
import com.leon1236.reforestry.api.arboriculture.ITreeManager;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;

public final class TreeManager implements ITreeManager {
	private final ImmutableMap<Block, Block> refractoryWaxables;
	private final ICharcoalManager charcoalManager;

	public TreeManager(ImmutableMap<Block, Block> refractoryWaxables, ICharcoalManager charcoalManager) {
		this.refractoryWaxables = refractoryWaxables;
		this.charcoalManager = charcoalManager;
	}

	@Override
	@Nullable
	public Block getRefractoryWaxed(Block block) {
		return refractoryWaxables.get(block);
	}

	@Override
	public ICharcoalManager getCharcoalManager() {
		return charcoalManager;
	}

	@Override
	public ItemStack getStack(IWoodType woodType, WoodBlockKind kind, boolean fireproof) {
		return WoodAccess.INSTANCE.getStack(woodType, kind, fireproof);
	}

	@Override
	public BlockState getBlock(IWoodType woodType, WoodBlockKind kind, boolean fireproof) {
		return WoodAccess.INSTANCE.getBlockState(woodType, kind, fireproof);
	}

	@Override
	public TagKey<Block> getLogBlockTag(IWoodType kind, boolean fireproof) {
		return WoodAccess.INSTANCE.getLogBlockTag(kind, fireproof);
	}

	@Override
	public TagKey<Item> getLogItemTag(IWoodType kind, boolean fireproof) {
		return WoodAccess.INSTANCE.getLogItemTag(kind, fireproof);
	}

	@Override
	public List<IWoodType> getRegisteredWoodTypes() {
		return WoodAccess.INSTANCE.getRegisteredWoodTypes();
	}

	@Override
	public void register(IWoodType woodType, WoodBlockKind woodBlockKind, boolean fireproof, BlockState blockState, Supplier<Item> itemStack) {
		WoodAccess.INSTANCE.register(woodType, woodBlockKind, fireproof, blockState, itemStack);
	}

	@Override
	public void registerLogTag(IWoodType woodType, boolean fireproof, TagKey<Block> logBlockTag, TagKey<Item> logItemTag) {
		WoodAccess.INSTANCE.registerLogTag(woodType, fireproof, logBlockTag, logItemTag);
	}
}
