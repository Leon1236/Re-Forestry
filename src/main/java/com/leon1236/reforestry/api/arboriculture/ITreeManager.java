package com.leon1236.reforestry.api.arboriculture;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface ITreeManager {
	@Nullable
	Block getRefractoryWaxed(Block block);

	ICharcoalManager getCharcoalManager();

	ItemStack getStack(IWoodType woodType, WoodBlockKind kind, boolean fireproof);

	BlockState getBlock(IWoodType woodType, WoodBlockKind kind, boolean fireproof);

	TagKey<Block> getLogBlockTag(IWoodType kind, boolean fireproof);

	TagKey<Item> getLogItemTag(IWoodType kind, boolean fireproof);

	List<IWoodType> getRegisteredWoodTypes();

	void register(IWoodType woodType, WoodBlockKind woodBlockKind, boolean fireproof, BlockState blockState, Supplier<Item> itemStack);

	void registerLogTag(IWoodType woodType, boolean fireproof, TagKey<Block> logBlockTag, TagKey<Item> logItemTag);
}
