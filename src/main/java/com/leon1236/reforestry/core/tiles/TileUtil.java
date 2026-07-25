package com.leon1236.reforestry.core.tiles;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class TileUtil {
	private TileUtil() {
	}

	public static boolean isUsableByPlayer(Player player, BlockEntity tile) {
		BlockPos pos = tile.getBlockPos();
		Level world = tile.getLevel();

		return !tile.isRemoved()
				&& getTile(world, pos) == tile
				&& player.distanceToSqr(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
	}

	@Nullable
	public static BlockEntity getTile(BlockGetter level, BlockPos pos) {
		return level.getBlockEntity(pos);
	}

	@Nullable
	public static <T> T getTile(BlockGetter world, BlockPos pos, Class<T> tileClass) {
		BlockEntity tileEntity = getTile(world, pos);
		if (tileClass.isInstance(tileEntity)) {
			return tileClass.cast(tileEntity);
		}
		return null;
	}

	public static <T> void actOnTile(LevelReader world, BlockPos pos, Class<T> tileClass, Consumer<T> tileAction) {
		T tile = getTile(world, pos, tileClass);
		if (tile != null) {
			tileAction.accept(tile);
		}
	}
}
