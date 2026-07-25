package com.leon1236.reforestry.core.multiblock;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkSource;

import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockController;
import com.leon1236.reforestry.api.multiblock.IMultiblockLogic;
import com.leon1236.reforestry.core.tiles.TileUtil;

public final class MultiblockUtil {
	private MultiblockUtil() {
	}

	public static List<IMultiblockComponent> getNeighboringParts(Level world, IMultiblockComponent part) {
		BlockPos partCoord = part.getCoordinates();

		List<BlockPos> neighbors = new ArrayList<>(Direction.values().length);
		for (Direction facing : Direction.values()) {
			neighbors.add(partCoord.relative(facing));
		}

		List<IMultiblockComponent> neighborParts = new ArrayList<>();
		ChunkSource chunkProvider = world.getChunkSource();
		for (BlockPos neighbor : neighbors) {
			if (!chunkProvider.hasChunk(neighbor.getX() >> 4, neighbor.getZ() >> 4)) {
				continue;
			}

			TileUtil.actOnTile(world, neighbor, IMultiblockComponent.class, neighborParts::add);
		}
		return neighborParts;
	}

	@Nullable
	public static <C extends IMultiblockComponent> C getComponent(BlockGetter world, BlockPos pos, Class<C> componentClass) {
		return TileUtil.getTile(world, pos, componentClass);
	}

	@Nullable
	public static <C extends IMultiblockComponent, L extends IMultiblockLogic> L getLogic(
			BlockGetter world, BlockPos pos, Class<C> componentClass, Class<L> logicClass) {
		C component = getComponent(world, pos, componentClass);
		if (component == null) {
			return null;
		}
		IMultiblockLogic logic = component.getMultiblockLogic();
		return logicClass.isInstance(logic) ? logicClass.cast(logic) : null;
	}

	@Nullable
	public static <C extends IMultiblockComponent, L extends IMultiblockLogic, M extends IMultiblockController> M getController(
			BlockGetter world, BlockPos pos, Class<C> componentClass, Class<L> logicClass, Class<M> controllerClass) {
		L logic = getLogic(world, pos, componentClass, logicClass);
		if (logic == null || !logic.isConnected()) {
			return null;
		}
		IMultiblockController controller = logic.getController();
		return controllerClass.isInstance(controller) ? controllerClass.cast(controller) : null;
	}
}
