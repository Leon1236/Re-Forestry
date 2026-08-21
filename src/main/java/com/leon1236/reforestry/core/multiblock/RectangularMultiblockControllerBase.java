package com.leon1236.reforestry.core.multiblock;

import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.core.tiles.TileUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class RectangularMultiblockControllerBase extends MultiblockControllerForestry {

	private final IMultiblockSizeLimits sizeLimits;

	protected RectangularMultiblockControllerBase(Level world, IMultiblockSizeLimits sizeLimits) {
		super(world);
		this.sizeLimits = sizeLimits;
	}

	@Override
	protected void isMachineWhole() throws MultiblockValidationException {
		int minX = this.sizeLimits.getMinimumXSize();
		int minY = this.sizeLimits.getMinimumYSize();
		int minZ = this.sizeLimits.getMinimumZSize();

		if (this.connectedParts.size() < this.sizeLimits.getMinimumNumberOfBlocksForAssembledMachine()) {
			throw new MultiblockValidationException(Component.translatable("for.multiblock.error.small", minX, minY, minZ).getString());
		}

		BlockPos maximumCoord = getMaximumCoord();
		BlockPos minimumCoord = getMinimumCoord();

		int deltaX = maximumCoord.getX() - minimumCoord.getX() + 1;
		int deltaY = maximumCoord.getY() - minimumCoord.getY() + 1;
		int deltaZ = maximumCoord.getZ() - minimumCoord.getZ() + 1;

		int maxX = this.sizeLimits.getMaximumXSize();
		int maxY = this.sizeLimits.getMaximumYSize();
		int maxZ = this.sizeLimits.getMaximumZSize();

		if (maxX > 0 && deltaX > maxX) {
			throw new MultiblockValidationException(Component.translatable("for.multiblock.error.large.x", maxX).getString());
		}
		if (maxY > 0 && deltaY > maxY) {
			throw new MultiblockValidationException(Component.translatable("for.multiblock.error.large.y", maxY).getString());
		}
		if (maxZ > 0 && deltaZ > maxZ) {
			throw new MultiblockValidationException(Component.translatable("for.multiblock.error.large.z", maxZ).getString());
		}
		if (deltaX < minX) {
			throw new MultiblockValidationException(Component.translatable("for.multiblock.error.small.x", minX).getString());
		}
		if (deltaY < minY) {
			throw new MultiblockValidationException(Component.translatable("for.multiblock.error.small.y", minY).getString());
		}
		if (deltaZ < minZ) {
			throw new MultiblockValidationException(Component.translatable("for.multiblock.error.small.z", minZ).getString());
		}

		BlockEntity te;
		IMultiblockComponent part;
		Class<? extends RectangularMultiblockControllerBase> myClass = this.getClass();

		for (int x = minimumCoord.getX(); x <= maximumCoord.getX(); x++) {
			for (int y = minimumCoord.getY(); y <= maximumCoord.getY(); y++) {
				for (int z = minimumCoord.getZ(); z <= maximumCoord.getZ(); z++) {

					BlockPos pos = new BlockPos(x, y, z);
					te = TileUtil.getTile(this.level, pos);
					if (te instanceof IMultiblockComponent) {
						part = (IMultiblockComponent) te;

						if (!myClass.equals(part.getMultiblockLogic().getController().getClass())) {
							throw new MultiblockValidationException(Component.translatable("for.multiblock.error.invalid.part", Component.translatable(getUnlocalizedType()).getString()).getString());
						}
					} else {

						part = null;
					}

					int extremes = 0;

					if (x == minimumCoord.getX()) {
						extremes++;
					}
					if (y == minimumCoord.getY()) {
						extremes++;
					}
					if (z == minimumCoord.getZ()) {
						extremes++;
					}

					if (x == maximumCoord.getX()) {
						extremes++;
					}
					if (y == maximumCoord.getY()) {
						extremes++;
					}
					if (z == maximumCoord.getZ()) {
						extremes++;
					}

					if (extremes >= 1) {

						int exteriorLevel = y - minimumCoord.getY();
						if (part != null) {
							isGoodForExteriorLevel(part, exteriorLevel);
						} else {
							isBlockGoodForExteriorLevel(exteriorLevel, this.level, pos);
						}
					} else {
						if (part != null) {
							isGoodForInterior(part);
						} else {
							isBlockGoodForInterior(this.level, pos);
						}
					}
				}
			}
		}
	}

	protected IMultiblockSizeLimits getSizeLimits() {
		return this.sizeLimits;
	}

	protected abstract void isGoodForExteriorLevel(IMultiblockComponent part, int level) throws MultiblockValidationException;

	protected abstract void isGoodForInterior(IMultiblockComponent part) throws MultiblockValidationException;
}
