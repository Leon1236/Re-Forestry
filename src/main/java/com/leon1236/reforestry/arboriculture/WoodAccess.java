package com.leon1236.reforestry.arboriculture;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;

public enum WoodAccess {
	INSTANCE;

	private final Map<IWoodType, Map<WoodBlockKind, BlockState>> normal = new HashMap<>();
	private final Map<IWoodType, Map<WoodBlockKind, BlockState>> fireproof = new HashMap<>();
	private final Map<IWoodType, Map<WoodBlockKind, Supplier<Item>>> normalItems = new HashMap<>();
	private final Map<IWoodType, Map<WoodBlockKind, Supplier<Item>>> fireproofItems = new HashMap<>();
	private final Map<IWoodType, TagKey<Block>> logBlockTags = new HashMap<>();
	private final Map<IWoodType, TagKey<Block>> fireproofLogBlockTags = new HashMap<>();
	private final Map<IWoodType, TagKey<Item>> logItemTags = new HashMap<>();
	private final Map<IWoodType, TagKey<Item>> fireproofLogItemTags = new HashMap<>();
	private final List<IWoodType> registeredTypes = new ArrayList<>();

	public void register(IWoodType woodType, WoodBlockKind kind, boolean isFireproof, BlockState state) {
		register(woodType, kind, isFireproof, state, state.getBlock()::asItem);
	}

	public void register(IWoodType woodType, WoodBlockKind kind, boolean isFireproof, BlockState state, Supplier<Item> item) {
		Map<IWoodType, Map<WoodBlockKind, BlockState>> table = isFireproof ? fireproof : normal;
		table.computeIfAbsent(woodType, ignored -> new EnumMap<>(WoodBlockKind.class)).put(kind, state);
		Map<IWoodType, Map<WoodBlockKind, Supplier<Item>>> items = isFireproof ? fireproofItems : normalItems;
		items.computeIfAbsent(woodType, ignored -> new EnumMap<>(WoodBlockKind.class)).put(kind, item);
		if (!registeredTypes.contains(woodType)) {
			registeredTypes.add(woodType);
		}
	}

	public <T extends Block & IWoodTyped> void register(T block) {
		register(block.getWoodType(), block.getBlockKind(), block.isFireproof(), block.defaultBlockState());
	}

	public BlockState getBlockState(IWoodType woodType, WoodBlockKind kind, boolean isFireproof) {
		Map<WoodBlockKind, BlockState> byKind = (isFireproof ? fireproof : normal).get(woodType);
		if (byKind == null) {
			throw new IllegalArgumentException("No wood blocks registered for wood type: " + woodType.getSerializedName());
		}
		BlockState state = byKind.get(kind);
		if (state == null) {
			throw new IllegalArgumentException("No " + kind.getSerializedName() + " registered for wood type: " + woodType.getSerializedName());
		}
		return state;
	}

	public ItemStack getStack(IWoodType woodType, WoodBlockKind kind, boolean isFireproof) {
		Map<WoodBlockKind, Supplier<Item>> byKind = (isFireproof ? fireproofItems : normalItems).get(woodType);
		if (byKind != null) {
			Supplier<Item> item = byKind.get(kind);
			if (item != null) {
				return new ItemStack(item.get());
			}
		}
		return new ItemStack(getBlockState(woodType, kind, isFireproof).getBlock());
	}

	public TagKey<Block> getLogBlockTag(IWoodType woodType, boolean fireproof) {
		TagKey<Block> tag = (fireproof ? fireproofLogBlockTags : logBlockTags).get(woodType);
		if (tag == null) {
			throw new IllegalArgumentException("No log block tag for wood type: " + woodType.getSerializedName());
		}
		return tag;
	}

	public TagKey<Item> getLogItemTag(IWoodType woodType, boolean fireproof) {
		TagKey<Item> tag = (fireproof ? fireproofLogItemTags : logItemTags).get(woodType);
		if (tag == null) {
			throw new IllegalArgumentException("No log item tag for wood type: " + woodType.getSerializedName());
		}
		return tag;
	}

	public void registerLogTag(IWoodType woodType, boolean fireproof, TagKey<Block> logBlockTag, TagKey<Item> logItemTag) {
		if (fireproof) {
			fireproofLogBlockTags.put(woodType, logBlockTag);
			fireproofLogItemTags.put(woodType, logItemTag);
		} else {
			logBlockTags.put(woodType, logBlockTag);
			logItemTags.put(woodType, logItemTag);
		}
	}

	public List<IWoodType> getRegisteredWoodTypes() {
		return List.copyOf(registeredTypes);
	}
}
