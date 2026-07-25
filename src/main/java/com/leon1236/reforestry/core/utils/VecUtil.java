package com.leon1236.reforestry.core.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

public final class VecUtil {
    public static final Comparator<BlockPos> TOP_DOWN_COMPARATOR = (a, b) -> Integer.compare(b.getY(), a.getY());

    private VecUtil() {
    }

    public static Direction direction(Vec3i a, Vec3i b) {
        int x = Math.abs(a.getX() - b.getX());
        int y = Math.abs(a.getY() - b.getY());
        int z = Math.abs(a.getZ() - b.getZ());
        int max = Math.max(x, Math.max(y, z));
        if (max == x) {
            return Direction.EAST;
        } else if (max == z) {
            return Direction.SOUTH;
        } else {
            return Direction.UP;
        }
    }

    public static BlockPos getRandomPositionInArea(RandomSource random, Vec3i area) {
        int x = random.nextInt(Math.max(1, area.getX()));
        int y = random.nextInt(Math.max(1, area.getY()));
        int z = random.nextInt(Math.max(1, area.getZ()));
        return new BlockPos(x, y, z);
    }

    public static BlockPos sum(Vec3i... vectors) {
        int x = 0;
        int y = 0;
        int z = 0;
        for (Vec3i vec : vectors) {
            x += vec.getX();
            y += vec.getY();
            z += vec.getZ();
        }
        return new BlockPos(x, y, z);
    }

    public static Vec3i center(Vec3i vec) {
        return new Vec3i(-vec.getX() / 2, -(vec.getY() - 1) / 2, -vec.getZ() / 2);
    }

    public static Iterator<BlockPos.MutableBlockPos> getAllInBoxFromCenterMutable(Level level, BlockPos from, BlockPos center, BlockPos to) {
        int minX = Math.min(from.getX(), to.getX());
        int minY = Math.min(from.getY(), to.getY());
        int minZ = Math.min(from.getZ(), to.getZ());
        int maxX = Math.max(from.getX(), to.getX());
        int maxY = Math.max(from.getY(), to.getY());
        int maxZ = Math.max(from.getZ(), to.getZ());
        return new MutableBlockPosSpiralIterator(level, center, minX, minY, minZ, maxX, maxY, maxZ);
    }

    private static final class MutableBlockPosSpiralIterator implements Iterator<BlockPos.MutableBlockPos> {
        private final Level level;
        private final BlockPos center;
        private final int minX;
        private final int minY;
        private final int minZ;
        private final int maxX;
        private final int maxY;
        private final int maxZ;
        private int spiralLayer;
        private final int maxSpiralLayers;
        private int direction;
        private final int minWorldHeight;
        @Nullable
        private BlockPos.MutableBlockPos theBlockPos;
        @Nullable
        private BlockPos.MutableBlockPos next;
        private boolean finished;

        private MutableBlockPosSpiralIterator(Level level, BlockPos center, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
            this.level = level;
            this.center = center;
            this.minX = minX;
            this.minY = minY;
            this.minZ = minZ;
            this.maxX = maxX;
            this.maxY = maxY;
            this.maxZ = maxZ;
            int xDiameter = maxX - minX;
            int zDiameter = maxZ - minZ;
            this.maxSpiralLayers = Math.max(xDiameter, zDiameter) / 2;
            this.spiralLayer = 1;
            this.minWorldHeight = level.getMinY();
            advance();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public BlockPos.MutableBlockPos next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            BlockPos.MutableBlockPos result = next;
            advance();
            return result;
        }

        private void advance() {
            if (finished) {
                next = null;
                return;
            }
            BlockPos.MutableBlockPos pos;
            do {
                pos = nextPos();
            } while (pos != null && (pos.getX() > maxX || pos.getY() > maxY || pos.getZ() > maxZ
                    || pos.getX() < minX || pos.getY() < minY || pos.getZ() < minZ));
            next = pos;
            if (pos == null) {
                finished = true;
            }
        }

        @Nullable
        private BlockPos.MutableBlockPos nextPos() {
            if (theBlockPos == null) {
                theBlockPos = new BlockPos.MutableBlockPos(center.getX(), maxY, center.getZ());
                int y = Math.min(maxY, level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, theBlockPos.getX(), theBlockPos.getZ()) - 1);
                theBlockPos.setY(y);
                return theBlockPos;
            }
            if (spiralLayer > maxSpiralLayers) {
                return null;
            }

            int x = theBlockPos.getX();
            int y = theBlockPos.getY();
            int z = theBlockPos.getZ();

            if (y > minY && y > minWorldHeight) {
                y--;
            } else {
                switch (direction) {
                    case 0 -> {
                        ++x;
                        if (x == center.getX() + spiralLayer) {
                            ++direction;
                        }
                    }
                    case 1 -> {
                        ++z;
                        if (z == center.getZ() + spiralLayer) {
                            ++direction;
                        }
                    }
                    case 2 -> {
                        --x;
                        if (x == center.getX() - spiralLayer) {
                            ++direction;
                        }
                    }
                    case 3 -> {
                        --z;
                        if (z == center.getZ() - spiralLayer) {
                            direction = 0;
                            ++spiralLayer;
                        }
                    }
                    default -> {
                    }
                }
                theBlockPos.set(x, y, z);
                y = Math.min(maxY, level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z) - 1);
            }

            return theBlockPos.set(x, y, z);
        }
    }
}
