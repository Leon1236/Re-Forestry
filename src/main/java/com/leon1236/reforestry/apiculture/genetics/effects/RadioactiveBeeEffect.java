package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.blocks.BlockAlveary;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.core.damage.CoreDamageTypes;
import com.leon1236.reforestry.core.utils.BlockUtil;
import com.leon1236.reforestry.core.utils.VecUtil;

public class RadioactiveBeeEffect extends ThrottledBeeEffect {
	public RadioactiveBeeEffect() {
		super(ForestryBeeEffects.RADIOACTIVE, true, 40, false, true);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		harmEntities(genome, housing);
		return destroyEnvironment(genome, storedData, housing);
	}

	private void harmEntities(IGenome genome, IBeeHousing housing) {
		if (!(housing.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		List<LivingEntity> entities = ThrottledBeeEffect.getEntitiesInRange(genome, housing, LivingEntity.class);
		for (LivingEntity entity : entities) {
			int damage = 8;
			int count = BeeManager.armorApiaristHelper.wearsItems(entity, this, true);
			damage -= count * 2;
			if (damage <= 0) {
				continue;
			}
			entity.hurtServer(serverLevel, CoreDamageTypes.source(serverLevel, CoreDamageTypes.RADIOACTIVE), damage);
		}
	}

	private static IEffectData destroyEnvironment(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		Level level = housing.level();
		RandomSource rand = level.getRandom();

		Vec3i territory = genome.getActiveAllele(BeeChromosomes.TERRITORY).value();
		Vec3i area = new Vec3i(territory.getX() * 2, territory.getY() * 2, territory.getZ() * 2);
		BlockPos posHousing = housing.position();

		for (int i = 0; i < 20; i++) {
			BlockPos randomPos = VecUtil.getRandomPositionInArea(rand, area);
			BlockPos posBlock = randomPos.offset(posHousing).offset(VecUtil.center(area));

			if (posBlock.getY() <= 1 || level.isOutsideBuildHeight(posBlock)) {
				continue;
			}
			if (posBlock.getX() == posHousing.getX() && posBlock.getZ() == posHousing.getZ() && posBlock.getY() <= posHousing.getY()) {
				continue;
			}
			if (!level.isLoaded(posBlock) || level.isEmptyBlock(posBlock)) {
				continue;
			}

			BlockState state = level.getBlockState(posBlock);
			if (state.getBlock() instanceof BlockAlveary) {
				continue;
			}

			BlockEntity tile = level.getBlockEntity(posBlock);
			if (tile instanceof IBeeHousing) {
				continue;
			}
			if (state.getDestroySpeed(level, posBlock) < 0) {
				continue;
			}

			if (level.removeBlock(posBlock, false)) {
				BlockUtil.sendDestroyEffects(level, posBlock, state);
			}
			break;
		}

		return storedData;
	}
}
