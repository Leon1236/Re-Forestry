package com.leon1236.reforestry.core.render;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ARGB;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.hives.IHiveTile;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.client.BeeColorParticleOptions;
import com.leon1236.reforestry.apiculture.features.ApicultureParticles;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.effects.ThrottledBeeEffect;
import com.leon1236.reforestry.core.entities.ParticleSmoke;
import com.leon1236.reforestry.core.utils.VecUtil;

public final class ParticleRender {
    private ParticleRender() {
    }

    public static boolean shouldSpawnParticle(Level level) {
        Minecraft mc = Minecraft.getInstance();
        return switch (mc.options.particles().get()) {
            case MINIMAL -> level.getRandom().nextInt(10) == 0;
            case DECREASED -> level.getRandom().nextInt(3) != 0;
            default -> true;
        };
    }

    public static void addBeeHiveFX(IBeeHousing housing, IGenome genome, List<BlockPos> flowerPositions) {
        Level level = housing.level();
        if (!level.isClientSide() || !shouldSpawnParticle(level)) {
            return;
        }

        Vec3 particleStart = housing.getBeeFXCoordinates();
        BlockPos playerPosition = Minecraft.getInstance().player.blockPosition();
        double playerDistanceSq = playerPosition.distToCenterSqr(particleStart.x, particleStart.y, particleStart.z);
        if (level.getRandom().nextInt(1024) < playerDistanceSq) {
            return;
        }

        int color = ARGB.opaque(genome.getActiveAllele(BeeChromosomes.SPECIES).value().bodyColor());
        RandomSource random = level.getRandom();
        int randomInt = random.nextInt(100);

        if (housing instanceof IHiveTile hiveTile) {
            if (hiveTile.isAngry() || randomInt >= 85) {
                List<LivingEntity> entitiesInRange = ThrottledBeeEffect.getEntitiesInRange(genome, housing, LivingEntity.class);
                if (!entitiesInRange.isEmpty()) {
                    LivingEntity entity = entitiesInRange.get(random.nextInt(entitiesInRange.size()));
                    level.addParticle(new BeeColorParticleOptions(ApicultureParticles.BEE_EXPLORER.type(), color),
                            particleStart.x, particleStart.y, particleStart.z,
                            entity.getX() - particleStart.x,
                            entity.getY() + entity.getBbHeight() / 2.0 - particleStart.y,
                            entity.getZ() - particleStart.z);
                    return;
                }
            }
        }

        if (randomInt < 75 && !flowerPositions.isEmpty()) {
            BlockPos destination = flowerPositions.get(random.nextInt(flowerPositions.size()));
            level.addParticle(new BeeColorParticleOptions(ApicultureParticles.BEE_ROUND_TRIP.type(), color),
                    particleStart.x, particleStart.y, particleStart.z,
                    destination.getX() + 0.5 - particleStart.x,
                    destination.getY() + 0.5 - particleStart.y,
                    destination.getZ() + 0.5 - particleStart.z);
        } else {
            Vec3i area = BeeCanWork.getParticleArea(genome, housing);
            BlockPos destination = VecUtil.getRandomPositionInArea(random, area)
                    .offset(housing.position())
                    .offset(VecUtil.center(area));
            level.addParticle(new BeeColorParticleOptions(ApicultureParticles.BEE_EXPLORER.type(), color),
                    particleStart.x, particleStart.y, particleStart.z,
                    destination.getX() + 0.5 - particleStart.x,
                    destination.getY() + 0.5 - particleStart.y,
                    destination.getZ() + 0.5 - particleStart.z);
        }
    }

    public static void addEntitySnowFX(Level level, double x, double y, double z) {
        if (!shouldSpawnParticle(level)) {
            return;
        }
        level.addParticle(ParticleTypes.SNOWFLAKE, x + level.getRandom().nextGaussian(), y, z + level.getRandom().nextGaussian(), 0, 0, 0);
    }

    public static void addEntitySmokeFX(Level level, double x, double y, double z) {
        if (!shouldSpawnParticle(level)) {
            return;
        }
        Minecraft.getInstance().particleEngine.add(new ParticleSmoke((ClientLevel) level, x, y, z));
    }

    public static void addEntityPotionFX(Level level, double x, double y, double z, int color) {
        if (!shouldSpawnParticle(level)) {
            return;
        }
        level.addParticle(new DustParticleOptions(ARGB.opaque(color), 1.0f), x, y, z, 0, 0, 0);
    }
}
