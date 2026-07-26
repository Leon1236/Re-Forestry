package com.leon1236.reforestry.apiculture;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.util.TickHelper;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.IActivityType;
import com.leon1236.reforestry.apiculture.tiles.TileHive;

public class WorldgenBeekeepingLogic implements IBeekeepingLogic {
    private final TileHive housing;
    private final HasFlowersCache hasFlowersCache = new HasFlowersCache(2);
    private final TickHelper tickHelper;
    private IEffectData[] effectData = new IEffectData[2];
    private boolean active;
    private boolean initialized;

    public WorldgenBeekeepingLogic(TileHive housing) {
        this.housing = housing;
        this.tickHelper = new TickHelper(housing.getBlockPos().hashCode());
    }

    @Override
    public boolean canWork() {
        tickHelper.onTick();

        if (!initialized || tickHelper.updateOnInterval(200)) {
            initialized = true;
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

    @Override
    public List<BlockPos> getFlowerPositions() {
        return hasFlowersCache.getFlowerCoords();
    }

    public boolean canDoBeeFX() {
        return active;
    }

    @Override
    public void doBeeFX() {
        IGenome genome = housing.getContainedGenome();
        effectData = applyEffects(genome, effectData);
    }

    private IEffectData[] applyEffects(IGenome genome, IEffectData[] storedData) {
        IBeeEffect effect = genome.getActiveAllele(BeeChromosomes.EFFECT).value();
        storedData[0] = applyEffect(effect, genome, storedData[0]);

        if (!effect.isCombinable()) {
            return storedData;
        }

        IBeeEffect secondary = genome.getInactiveAllele(BeeChromosomes.EFFECT).value();
        if (!secondary.isCombinable()) {
            return storedData;
        }

        storedData[1] = applyEffect(secondary, genome, storedData[1]);
        return storedData;
    }

    private IEffectData applyEffect(IBeeEffect effect, IGenome genome, IEffectData storedData) {
        storedData = effect.validateStorage(storedData);
        return effect.doFX(genome, storedData, housing);
    }
}
