package com.leon1236.reforestry.apiculture;

import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.util.TickHelper;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.IActivityType;
import com.leon1236.reforestry.apiculture.tiles.TileHive;

public class WorldgenBeekeepingLogic implements IBeekeepingLogic {
    private final TileHive housing;
    private final HasFlowersCache hasFlowersCache = new HasFlowersCache(2);
    private final TickHelper tickHelper;
    private boolean active;

    public WorldgenBeekeepingLogic(TileHive housing) {
        this.housing = housing;
        this.tickHelper = new TickHelper(housing.getBlockPos().hashCode());
    }

    @Override
    public boolean canWork() {
        tickHelper.onTick();

        if (tickHelper.updateOnInterval(200)) {
            Level level = housing.level();
            if (level == null) {
                active = false;
                return false;
            }
            IGenome genome = housing.getContainedGenome();
            hasFlowersCache.update(genome, housing);
            IActivityType activity = genome.getActiveAllele(BeeChromosomes.ACTIVITY).value();
            boolean canWork = activity.isActive(level.getGameTime(), IActivityType.getBeeDayTime(level), housing.position())
                    && (!housing.isRaining() || genome.getActiveAllele(BeeChromosomes.TOLERATES_RAIN).value());
            active = canWork;
        }

        return active;
    }

    @Override
    public void doWork() {
    }

    @Override
    public int getWorkProgressPercent() {
        return 0;
    }
}
