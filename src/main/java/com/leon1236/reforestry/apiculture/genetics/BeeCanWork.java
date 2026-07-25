package com.leon1236.reforestry.apiculture.genetics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.LightPreference;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.ClimateHelper;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.utils.VecUtil;

public final class BeeCanWork {
    public static final int APIARY_MIN_LEVEL_LIGHT = 11;

    private BeeCanWork() {
    }

    public static Set<IError> getCanWork(IGenome genome, IBeeHousing housing) {
        Level level = housing.level();
        Set<IError> errorStates = new HashSet<>();

        if (housing.isRaining() && !canFlyInRain(genome, housing)) {
            errorStates.add(ForestryError.IS_RAINING);
        }

        IActivityType activity = genome.getActiveAllele(BeeChromosomes.ACTIVITY).value();
        if (!isAlwaysActive(genome, housing)) {
            long gameTime = level.getGameTime();
            long dayTime = IActivityType.getBeeDayTime(level);
            BlockPos pos = housing.position();

            if (!activity.isActive(gameTime, dayTime, pos)) {
                errorStates.add(activity.getInactiveError(gameTime, dayTime, pos));
            }

            if (housing.getBlockLightValue() > APIARY_MIN_LEVEL_LIGHT) {
                if (activity.getLightPreference() == LightPreference.DARK) {
                    errorStates.add(ForestryError.NOT_GLOOMY);
                }
            } else if (activity.getLightPreference() == LightPreference.LIGHT) {
                errorStates.add(ForestryError.NOT_BRIGHT);
            }
        }

        if (!level.dimensionType().hasCeiling() && level.dimension() != Level.END) {
            if (!housing.canBlockSeeTheSky() && !canWorkUnderground(genome, housing)) {
                errorStates.add(ForestryError.NO_SKY);
            }
        }

        IBeeSpecies species = genome.getActiveAllele(BeeChromosomes.SPECIES).value();
        TemperatureType actualTemperature = housing.temperature();
        TemperatureType beeBaseTemperature = species.getTemperature();
        ToleranceType beeToleranceTemperature = genome.getActiveAllele(BeeChromosomes.TEMPERATURE_TOLERANCE).value();
        if (!ClimateHelper.isWithinLimits(actualTemperature, beeBaseTemperature, beeToleranceTemperature)) {
            if (beeBaseTemperature.ordinal() > actualTemperature.ordinal()) {
                errorStates.add(ForestryError.TOO_COLD);
            } else {
                errorStates.add(ForestryError.TOO_HOT);
            }
        }

        HumidityType actualHumidity = housing.humidity();
        HumidityType beeBaseHumidity = species.getHumidity();
        ToleranceType beeToleranceHumidity = genome.getActiveAllele(BeeChromosomes.HUMIDITY_TOLERANCE).value();
        if (!ClimateHelper.isWithinLimits(actualHumidity, beeBaseHumidity, beeToleranceHumidity)) {
            if (beeBaseHumidity.ordinal() > actualHumidity.ordinal()) {
                errorStates.add(ForestryError.TOO_ARID);
            } else {
                errorStates.add(ForestryError.TOO_HUMID);
            }
        }

        return errorStates;
    }

    public static Vec3i getAdjustedTerritory(IGenome genome, IBeeHousing housing) {
        Vec3i territory = genome.getActiveAllele(BeeChromosomes.TERRITORY).value();
        for (IBeeModifier modifier : housing.getBeeModifiers()) {
            territory = modifier.modifyTerritory(genome, territory);
        }
        return territory;
    }

    public static Vec3i getParticleArea(IGenome genome, IBeeHousing housing) {
        Vec3i area = getAdjustedTerritory(genome, housing);
        return new Vec3i(Math.max(1, area.getX()), Math.max(1, area.getY()), Math.max(1, area.getZ()));
    }

    public static Iterator<BlockPos.MutableBlockPos> getAreaIterator(IGenome genome, IBeeHousing housing) {
        Vec3i area = getAdjustedTerritory(genome, housing);
        BlockPos housingPos = housing.position();
        BlockPos minPos = housingPos.offset(-area.getX() / 2, -area.getY() / 2, -area.getZ() / 2);
        BlockPos maxPos = minPos.offset(area);
        return VecUtil.getAllInBoxFromCenterMutable(housing.level(), minPos, housingPos, maxPos);
    }

    public static boolean isAlwaysActive(IGenome genome, IBeeHousing housing) {
        for (IBeeModifier modifier : housing.getBeeModifiers()) {
            if (modifier.isAlwaysActive(genome)) {
                return true;
            }
        }
        return false;
    }

    private static boolean canWorkUnderground(IGenome genome, IBeeHousing housing) {
        if (genome.getActiveAllele(BeeChromosomes.CAVE_DWELLING).value()) {
            return true;
        }
        for (IBeeModifier modifier : housing.getBeeModifiers()) {
            if (modifier.isSunlightSimulated()) {
                return true;
            }
        }
        return false;
    }

    private static boolean canFlyInRain(IGenome genome, IBeeHousing housing) {
        if (genome.getActiveAllele(BeeChromosomes.TOLERATES_RAIN).value()) {
            return true;
        }
        for (IBeeModifier modifier : housing.getBeeModifiers()) {
            if (modifier.isSealed()) {
                return true;
            }
        }
        return false;
    }
}
