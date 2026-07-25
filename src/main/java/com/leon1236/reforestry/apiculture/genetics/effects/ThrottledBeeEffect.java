package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.core.genetics.EffectData;
import com.leon1236.reforestry.core.utils.VecUtil;

public abstract class ThrottledBeeEffect extends DummyBeeEffect {
    private final boolean isCombinable;
    private final int throttle;
    private final boolean requiresWorkingQueen;

    protected ThrottledBeeEffect(Identifier id, boolean dominant, int throttle, boolean requiresWorking, boolean isCombinable) {
        super(id, dominant);
        this.throttle = throttle;
        this.isCombinable = isCombinable;
        this.requiresWorkingQueen = requiresWorking;
    }

    public static AABB getBounding(IBeeHousing housing, IGenome genome) {
        Vec3i territory = BeeCanWork.getAdjustedTerritory(genome, housing);
        BlockPos min = housing.position().offset(VecUtil.center(territory));
        BlockPos max = min.offset(territory);
        return new AABB(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
    }

    public static <T extends Entity> List<T> getEntitiesInRange(IGenome genome, IBeeHousing housing, Class<T> entityClass) {
        AABB boundingBox = getBounding(housing, genome);
        return housing.level().getEntities(EntityTypeTest.forClass(entityClass), boundingBox, Entity::isAlive);
    }

    @Override
    public boolean isCombinable() {
        return this.isCombinable;
    }

    @Override
    public IEffectData validateStorage(IEffectData storedData) {
        if (storedData instanceof EffectData) {
            return storedData;
        }
        return new EffectData(1, 0);
    }

    @Override
    public final IEffectData doEffect(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        if (isThrottled(storedData, housing)) {
            return storedData;
        }
        return doEffectThrottled(genome, storedData, housing);
    }

    private boolean isThrottled(IEffectData storedData, IBeeHousing housing) {
        if (this.requiresWorkingQueen && housing.getErrorLogic().hasErrors()) {
            return true;
        }

        int time = storedData.getInteger(0);
        time++;
        storedData.setInteger(0, time);

        if (time < this.throttle) {
            return true;
        }

        storedData.setInteger(0, 0);
        return false;
    }

    abstract IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing);
}
