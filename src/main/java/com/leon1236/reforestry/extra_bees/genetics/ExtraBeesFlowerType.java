package com.leon1236.reforestry.extra_bees.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.ForestryTags;
import com.leon1236.reforestry.api.core.genetics.IFruitBearer;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.apiculture.genetics.IFlowerType;
public enum ExtraBeesFlowerType implements IFlowerType {
	WATER,
	SUGAR,
	ROCK,
	BOOK,
	DEAD,
	REDSTONE,
	WOOD,
	LEAVES,
	SAPLING,
	FRUIT,
	MYSTICAL;

	private static final TagKey<Block> ROCK_FLOWERS =
			TagKey.create(Registries.BLOCK, ReForestry.id("flowers/rock"));
	private static final TagKey<Block> REDSTONE_FLOWERS =
			TagKey.create(Registries.BLOCK, ReForestry.id("flowers/redstone"));
	private static final TagKey<Block> FRUIT_FLOWERS =
			TagKey.create(Registries.BLOCK, ReForestry.id("flowers/fruit"));
	private static final TagKey<Block> SAPLING_FLOWERS =
			TagKey.create(Registries.BLOCK, ReForestry.id("flowers/sapling"));
	private static final TagKey<Block> MYSTICAL_FLOWERS =
			TagKey.create(Registries.BLOCK, ReForestry.id("flowers/mystical"));
	private static final TagKey<Block> WATER_FLOWERS =
			TagKey.create(Registries.BLOCK, ReForestry.id("flowers/water"));
	private static final TagKey<Block> SUGAR_FLOWERS =
			TagKey.create(Registries.BLOCK, ReForestry.id("flowers/sugar"));
	private static final TagKey<Block> BOOK_FLOWERS =
			TagKey.create(Registries.BLOCK, ReForestry.id("flowers/book"));
	private static final TagKey<Block> DEAD_FLOWERS =
			TagKey.create(Registries.BLOCK, ReForestry.id("flowers/dead"));

	private final Identifier id = ReForestry.id("flower_type_" + name().toLowerCase(Locale.ENGLISH));

	@Override
	public Identifier id() {
		return id;
	}

	@Override
	public boolean isAcceptableFlower(Level level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		return switch (this) {
			case WATER -> state.is(WATER_FLOWERS) || state.is(Blocks.LILY_PAD);
			case SUGAR -> state.is(SUGAR_FLOWERS) || state.is(Blocks.SUGAR_CANE);
			case ROCK -> state.is(ROCK_FLOWERS)
					|| state.is(BlockTags.BASE_STONE_OVERWORLD)
					|| state.is(BlockTags.BASE_STONE_NETHER)
					|| state.is(Blocks.COBBLESTONE)
					|| state.is(Blocks.STONE);
			case BOOK -> state.is(BOOK_FLOWERS) || state.is(Blocks.BOOKSHELF);
			case DEAD -> state.is(DEAD_FLOWERS) || state.is(Blocks.DEAD_BUSH);
			case REDSTONE -> state.is(REDSTONE_FLOWERS)
					|| state.is(Blocks.REDSTONE_TORCH)
					|| state.is(Blocks.REDSTONE_WALL_TORCH)
					|| state.is(Blocks.REDSTONE_BLOCK)
					|| state.is(Blocks.REDSTONE_ORE)
					|| state.is(Blocks.DEEPSLATE_REDSTONE_ORE);
			case WOOD -> state.is(BlockTags.LOGS);
			case LEAVES -> state.is(BlockTags.LEAVES);
			case SAPLING -> state.is(SAPLING_FLOWERS)
					|| state.is(ForestryTags.Blocks.TREE_SAPLINGS)
					|| BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath().contains("sapling");
			case FRUIT -> {
				if (state.is(FRUIT_FLOWERS)
						|| state.is(Blocks.MELON)
						|| state.is(Blocks.MELON_STEM)
						|| state.is(Blocks.ATTACHED_MELON_STEM)) {
					yield true;
				}
				BlockEntity tile = level.getBlockEntity(pos);
				yield tile instanceof IFruitBearer;
			}
			case MYSTICAL -> isMysticalFlower(state);
		};
	}

	@Override
	public List<ItemStack> affectProducts(Level level, BlockPos pos, IIndividual individual, List<ItemStack> products) {
		if (this != MYSTICAL || !FabricLoader.getInstance().isModLoaded("botania")) {
			return products;
		}
		Item petal = BuiltInRegistries.ITEM.getOptional(Identifier.fromNamespaceAndPath("botania", "petal")).orElse(null);
		if (petal == null) {
			petal = BuiltInRegistries.ITEM.getOptional(Identifier.fromNamespaceAndPath("botania", "white_petal")).orElse(null);
		}
		if (petal == null) {
			return products;
		}
		List<ItemStack> result = new ArrayList<>(products);
		for (int k = 0; k < 50; k++) {
			int x2 = pos.getX() - 7 + level.getRandom().nextInt(15);
			int y2 = pos.getY() - 7 + level.getRandom().nextInt(15);
			int z2 = pos.getZ() - 3 + level.getRandom().nextInt(7);
			BlockPos sample = new BlockPos(x2, y2, z2);
			if (!level.isLoaded(sample)) {
				continue;
			}
			if (isMysticalFlower(level.getBlockState(sample))) {
				result.add(new ItemStack(petal));
			}
		}
		return result;
	}

	private static boolean isMysticalFlower(BlockState state) {
		if (state.is(MYSTICAL_FLOWERS)) {
			return true;
		}
		if (!FabricLoader.getInstance().isModLoaded("botania")) {
			return false;
		}
		Identifier key = BuiltInRegistries.BLOCK.getKey(state.getBlock());
		if (!"botania".equals(key.getNamespace())) {
			return false;
		}
		String path = key.getPath();
		return path.equals("flower") || path.contains("mystical_flower") || path.endsWith("_mystical_flower");
	}
}
