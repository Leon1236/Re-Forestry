package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;
import com.leon1236.reforestry.core.utils.VecUtil;

public abstract class NonStackingBeeEffect implements IBeeEffect {
	private static final int WORK_THROTTLE = 550;

	private final HashMap<ResourceKey<Level>, HashSet<BlockPos>> trackedOwners;
	private final Identifier id;
	private final boolean dominant;

	public NonStackingBeeEffect(Identifier id, boolean dominant) {
		this.id = id;
		this.dominant = dominant;
		this.trackedOwners = new HashMap<>();
		ServerTickEvents.START_LEVEL_TICK.register(this::performGlobalEffect);
	}

	@Override
	public Identifier id() {
		return this.id;
	}

	@Override
	public IEffectData doEffect(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		if ((housing.level().getGameTime() & 64L) == 0) {
			this.trackedOwners.computeIfAbsent(housing.level().dimension(), key -> new HashSet<>()).add(housing.position());
		}
		return IBeeEffect.super.doEffect(genome, storedData, housing);
	}

	@Override
	public boolean isDominant() {
		return this.dominant;
	}

	private void performGlobalEffect(Level level) {
		if (level.isClientSide() || level.getGameTime() % WORK_THROTTLE != 0) {
			return;
		}

		HashSet<BlockPos> owners = this.trackedOwners.computeIfAbsent(level.dimension(), key -> new HashSet<>());
		HashSet<BlockPos> affectedHives = new HashSet<>();

		for (Iterator<BlockPos> iterator = owners.iterator(); iterator.hasNext(); ) {
			BlockPos pos = iterator.next();
			IBeeHousing housing = getHousing(level, pos);

			if (housing != null && !housing.getErrorLogic().hasErrors()) {
				ItemStack queenStack = housing.beeInventory().getQueen();
				if (queenStack.getItem() instanceof ItemBeeGE beeItem && "queen".equals(beeItem.lifeStage())) {
					IGenome genome = queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
					if (genome != null && (genome.getActiveAllele(BeeChromosomes.EFFECT).value() == this
							|| genome.getInactiveAllele(BeeChromosomes.EFFECT).value() == this)) {
						Vec3i territory = BeeCanWork.getAdjustedTerritory(genome, housing);
						affectNearbyTiles(affectedHives, level, pos, territory);
						continue;
					}
				}
			}

			iterator.remove();
		}
	}

	private void affectNearbyTiles(HashSet<BlockPos> affectedHives, Level level, BlockPos pos, Vec3i territory) {
		BlockPos topLeft = pos.offset(VecUtil.center(territory));
		BlockPos bottomRight = topLeft.offset(territory);

		int topLeftX = topLeft.getX();
		int topLeftZ = topLeft.getZ();
		int bottomRightX = bottomRight.getX();
		int bottomRightZ = bottomRight.getZ();
		int territoryX = territory.getX();
		int territoryY = territory.getY();
		int territoryZ = territory.getZ();

		for (int x = SectionPos.blockToSectionCoord(topLeftX); x <= SectionPos.blockToSectionCoord(bottomRightX); x++) {
			for (int z = SectionPos.blockToSectionCoord(topLeftZ); z <= SectionPos.blockToSectionCoord(bottomRightZ); z++) {
				if (!level.getChunkSource().hasChunk(x, z)) {
					continue;
				}
				for (Map.Entry<BlockPos, BlockEntity> entry : level.getChunk(x, z).getBlockEntities().entrySet()) {
					BlockPos targetPos = entry.getKey();
					if (!(entry.getValue() instanceof IBeeHousing housing)) {
						continue;
					}
					if (targetPos.equals(pos) || affectedHives.contains(targetPos)) {
						continue;
					}

					int targetX = targetPos.getX();
					if (targetX >= topLeftX && targetX < topLeftX + territoryX) {
						int targetY = targetPos.getY();
						if (targetY >= topLeft.getY() && targetY < topLeft.getY() + territoryY) {
							int targetZ = targetPos.getZ();
							if (targetZ >= topLeftZ && targetZ < topLeftZ + territoryZ) {
								if (affectedHives.add(targetPos)) {
									doEffectForHive(level, housing);
								}
							}
						}
					}
				}
			}
		}
	}

	private static IBeeHousing getHousing(Level level, BlockPos pos) {
		if (level.getBlockEntity(pos) instanceof IBeeHousing housing) {
			return housing;
		}
		return null;
	}

	protected abstract void doEffectForHive(Level level, IBeeHousing housing);
}
