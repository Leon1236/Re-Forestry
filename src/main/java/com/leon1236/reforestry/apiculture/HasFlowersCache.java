package com.leon1236.reforestry.apiculture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.util.TickHelper;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.IFlowerType;

public class HasFlowersCache {
    private final int flowerCheckInterval;
    private final TickHelper tickHelper = new TickHelper(0);

    @Nullable
    private FlowerData flowerData;
    private final ArrayList<BlockPos> flowerCoords = new ArrayList<>();
    private final List<BlockState> flowers = new ArrayList<>();
    private boolean needsSync;

    public HasFlowersCache() {
        this.flowerCheckInterval = 200;
    }

    public HasFlowersCache(int checkInterval) {
        this.flowerCheckInterval = checkInterval;
    }

    private static final class FlowerData {
        private final IFlowerType flowerType;
        private final Vec3i territory;
        private Iterator<BlockPos.MutableBlockPos> areaIterator;

        private FlowerData(IGenome genome, IBeeHousing housing) {
            this.flowerType = genome.getActiveAllele(BeeChromosomes.FLOWER_TYPE).value();
            this.territory = genome.getActiveAllele(BeeChromosomes.TERRITORY).value();
            this.areaIterator = BeeCanWork.getAreaIterator(genome, housing);
        }

        private void resetIterator(IGenome genome, IBeeHousing housing) {
            this.areaIterator = BeeCanWork.getAreaIterator(genome, housing);
        }
    }

    public void update(IGenome genome, IBeeHousing beeHousing) {
        if (flowerData == null) {
            flowerData = new FlowerData(genome, beeHousing);
            flowerCoords.clear();
            flowers.clear();
        }
        Level level = beeHousing.level();
        tickHelper.onTick();

        if (!flowerCoords.isEmpty() && tickHelper.updateOnInterval(flowerCheckInterval)) {
            Iterator<BlockPos> iterator = flowerCoords.iterator();
            while (iterator.hasNext()) {
                BlockPos flowerPos = iterator.next();
                if (level.isLoaded(flowerPos) && !flowerData.flowerType.isAcceptableFlower(level, flowerPos)) {
                    iterator.remove();
                    flowers.clear();
                    needsSync = true;
                }
            }
        }

        int flowerCount = flowerCoords.size();
        int ticksPerCheck = 1 + (flowerCount * flowerCount);

        if (tickHelper.updateOnInterval(ticksPerCheck)) {
            if (flowerData.areaIterator.hasNext()) {
                BlockPos.MutableBlockPos blockPos = flowerData.areaIterator.next();
                if (flowerData.flowerType.isAcceptableFlower(level, blockPos)) {
                    addFlowerPos(blockPos.immutable());
                }
            } else {
                flowerData.resetIterator(genome, beeHousing);
            }
        }
    }

    public boolean hasFlowers() {
        return !flowerCoords.isEmpty();
    }

    public boolean needsSync() {
        boolean returnVal = needsSync;
        needsSync = false;
        return returnVal;
    }

    public void onNewQueen(IGenome genome, IBeeHousing housing) {
        if (flowerData != null) {
            IFlowerType flowerType = genome.getActiveAllele(BeeChromosomes.FLOWER_TYPE).value();
            if (flowerData.flowerType != flowerType
                    || !flowerData.territory.equals(genome.getActiveAllele(BeeChromosomes.TERRITORY).value())) {
                flowerData = new FlowerData(genome, housing);
                flowerCoords.clear();
                flowers.clear();
            }
        }
    }

    public List<BlockPos> getFlowerCoords() {
        return Collections.unmodifiableList(flowerCoords);
    }

    public List<BlockState> getFlowers(Level level) {
        if (flowers.isEmpty() && !flowerCoords.isEmpty()) {
            for (BlockPos flowerCoord : flowerCoords) {
                flowers.add(level.getBlockState(flowerCoord));
            }
        }
        return Collections.unmodifiableList(flowers);
    }

    public void addFlowerPos(BlockPos blockPos) {
        flowerCoords.add(blockPos);
        flowers.clear();
        needsSync = true;
    }

    public void forceLookForFlowers(IGenome genome, IBeeHousing housing) {
        if (flowerData == null) {
            flowerData = new FlowerData(genome, housing);
        }
        flowerCoords.clear();
        flowers.clear();
        flowerData.resetIterator(genome, housing);
        Level level = housing.level();
        while (flowerData.areaIterator.hasNext()) {
            BlockPos.MutableBlockPos blockPos = flowerData.areaIterator.next();
            if (flowerData.flowerType.isAcceptableFlower(level, blockPos)) {
                addFlowerPos(blockPos.immutable());
            }
        }
    }
}
