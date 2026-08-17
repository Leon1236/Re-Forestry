package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;

public class ResurrectionBeeEffect extends ThrottledBeeEffect {
	private final List<Resurrectable> resurrectables;

	public ResurrectionBeeEffect(Identifier id, List<Resurrectable> resurrectables) {
		super(id, true, 40, true, true);
		this.resurrectables = new ArrayList<>(resurrectables);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		List<ItemEntity> entities = ThrottledBeeEffect.getEntitiesInRange(genome, housing, ItemEntity.class);
		if (entities.isEmpty()) {
			return storedData;
		}

		Collections.shuffle(this.resurrectables);

		for (ItemEntity entity : entities) {
			if (resurrectEntity(entity)) {
				break;
			}
		}

		return storedData;
	}

	private boolean resurrectEntity(ItemEntity entity) {
		if (!entity.isAlive()) {
			return false;
		}

		ItemStack contained = entity.getItem();
		for (Resurrectable entry : this.resurrectables) {
			if (entry.matches(contained)) {
				if (entry.spawnAndTransform(entity)) {
					contained.shrink(1);
					if (contained.getCount() <= 0) {
						entity.discard();
					}
				}
				return true;
			}
		}

		return false;
	}

	public static List<Resurrectable> getReanimationList() {
		List<Resurrectable> list = new ArrayList<>();
		list.add(new Resurrectable(Items.BONE, EntityTypes.SKELETON));
		list.add(new Resurrectable(Items.ARROW, EntityTypes.SKELETON));
		list.add(new Resurrectable(Items.ROTTEN_FLESH, EntityTypes.ZOMBIE));
		list.add(new Resurrectable(Items.BLAZE_ROD, EntityTypes.BLAZE));
		return list;
	}

	public static List<Resurrectable> getResurrectionList() {
		List<Resurrectable> list = new ArrayList<>();
		list.add(new Resurrectable(Items.GUNPOWDER, EntityTypes.CREEPER));
		list.add(new Resurrectable(Items.ENDER_PEARL, EntityTypes.ENDERMAN));
		list.add(new Resurrectable(Items.STRING, EntityTypes.SPIDER));
		list.add(new Resurrectable(Items.SPIDER_EYE, EntityTypes.SPIDER));
		list.add(new Resurrectable(Items.STRING, EntityTypes.CAVE_SPIDER));
		list.add(new Resurrectable(Items.SPIDER_EYE, EntityTypes.CAVE_SPIDER));
		list.add(new Resurrectable(Items.GHAST_TEAR, EntityTypes.GHAST));
		list.add(new Resurrectable(Blocks.DRAGON_EGG.asItem(), EntityTypes.ENDER_DRAGON));
		return list;
	}

	public record Resurrectable(Item item, EntityType<?> entity) {
		private boolean matches(ItemStack stack) {
			return ItemStack.isSameItemSameComponents(new ItemStack(this.item), stack);
		}

		private boolean spawnAndTransform(ItemEntity at) {
			if (!(at.level() instanceof ServerLevel serverLevel)) {
				return false;
			}
			@SuppressWarnings("unchecked")
			EntityType<? extends Mob> mobType = (EntityType<? extends Mob>) this.entity;
			Mob spawned = spawnMob(serverLevel, mobType, at.getX(), at.getY(), at.getZ());
			if (spawned == null) {
				return false;
			}
			if (spawned instanceof EnderDragon dragon) {
				dragon.getPhaseManager().setPhase(EnderDragonPhase.HOLDING_PATTERN);
			}
			return true;
		}

		private static Mob spawnMob(ServerLevel world, EntityType<? extends Mob> type, double x, double y, double z) {
			Mob living = type.create(world, EntitySpawnReason.MOB_SUMMONED);
			if (living == null) {
				return null;
			}
			living.snapTo(x, y, z, Mth.wrapDegrees(world.getRandom().nextFloat() * 360.0f), 0.0f);
			living.yHeadRot = living.getYRot();
			living.yBodyRot = living.getYRot();
			living.finalizeSpawn(world, world.getCurrentDifficultyAt(BlockPos.containing(x, y, z)), EntitySpawnReason.MOB_SUMMONED, null);
			world.addFreshEntity(living);
			living.playAmbientSound();
			return living;
		}
	}
}
