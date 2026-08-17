package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.core.genetics.EffectData;
import com.leon1236.reforestry.core.utils.VecUtil;

public class FungificationBeeEffect extends ThrottledBeeEffect {
	private static final int MAX_BLOCK_FIND_TRIES = 10;
	private static final int ENTITY_THROTTLE = 6;

	public FungificationBeeEffect() {
		super(ForestryBeeEffects.MYCOPHILIC, true, 20, false, false);
	}

	@Override
	public IEffectData validateStorage(IEffectData storedData) {
		if (storedData instanceof EffectData data && data.getIntSize() == 2) {
			return storedData;
		}
		return new EffectData(2, 0);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		doBlockEffect(genome, housing);

		int entityThrottle = storedData.getInteger(1);
		if (entityThrottle >= ENTITY_THROTTLE) {
			doEntityEffect(genome, housing);
			entityThrottle = 0;
		} else {
			++entityThrottle;
		}
		storedData.setInteger(1, entityThrottle);

		return storedData;
	}

	private void doBlockEffect(IGenome genome, IBeeHousing housing) {
		Level world = housing.level();
		if (!(world instanceof ServerLevel serverLevel)) {
			return;
		}
		BlockPos housingCoordinates = housing.position();
		Vec3i area = BeeCanWork.getParticleArea(genome, housing);
		Vec3i halfArea = new Vec3i(area.getX() / 2, area.getY() / 2, area.getZ() / 2);

		for (int attempt = 0; attempt < MAX_BLOCK_FIND_TRIES; ++attempt) {
			BlockPos pos = VecUtil.getRandomPositionInArea(world.getRandom(), area).subtract(halfArea).offset(housingCoordinates);
			if (world.isLoaded(pos)) {
				BlockState blockState = world.getBlockState(pos);
				if (convertToMycelium(world, blockState, pos)) {
					return;
				} else if (growGiantMushroom(serverLevel, blockState, pos)) {
					return;
				}
			}
		}
	}

	private static void doEntityEffect(IGenome genome, IBeeHousing housing) {
		List<AbstractCow> cows = ThrottledBeeEffect.getEntitiesInRange(genome, housing, AbstractCow.class);
		for (AbstractCow cow : cows) {
			if (convertCowToMooshroom(cow)) {
				return;
			}
		}
	}

	private static boolean convertToMycelium(Level world, BlockState blockState, BlockPos pos) {
		Block block = blockState.getBlock();
		if (block == Blocks.GRASS_BLOCK || block == Blocks.DIRT && world.canSeeSkyFromBelowWater(pos)) {
			world.setBlockAndUpdate(pos, Blocks.MYCELIUM.defaultBlockState());
			return true;
		}
		return false;
	}

	private static boolean growGiantMushroom(ServerLevel world, BlockState blockState, BlockPos pos) {
		Block block = blockState.getBlock();
		if (block instanceof MushroomBlock mushroom) {
			mushroom.growMushroom(world, pos, blockState, world.getRandom());
			return true;
		}
		return false;
	}

	private static boolean convertCowToMooshroom(AbstractCow cow) {
		if (cow instanceof MushroomCow) {
			return false;
		}
		Level world = cow.level();
		cow.discard();
		MushroomCow mooshroom = new MushroomCow(EntityTypes.MOOSHROOM, world);
		mooshroom.snapTo(cow.getX(), cow.getY(), cow.getZ(), cow.getYRot(), cow.getXRot());
		mooshroom.setHealth(cow.getHealth());
		mooshroom.yBodyRot = cow.yBodyRot;
		world.addFreshEntity(mooshroom);
		world.addParticle(ParticleTypes.EXPLOSION, cow.getX(), cow.getY() + cow.getBbHeight() / 2.0F, cow.getZ(), 0.0D, 0.0D, 0.0D);
		return true;
	}
}
