package com.leon1236.reforestry.extra_bees.genetics.effects;

import java.util.Calendar;
import java.util.List;

import it.unimi.dsi.fastutil.ints.IntList;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.plugin.IApicultureRegistration;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.effects.ThrottledBeeEffect;
import com.leon1236.reforestry.core.damage.CoreDamageTypes;
import com.leon1236.reforestry.extra_bees.genetics.ExtraBeesBeeEffects;
import com.leon1236.reforestry.extra_bees.genetics.ExtraBeesFlowerType;

public final class ExtraBeesEffects {
	private ExtraBeesEffects() {
	}

	public static void register(IApicultureRegistration registration) {
		registration.registerBeeEffect(ExtraBeesBeeEffects.ECTOPLASM, new TerritoryBlockEffect(ExtraBeesBeeEffects.ECTOPLASM, 20,
				ExtraBeesEffectLogic::placeEctoplasm, null));
		registration.registerBeeEffect(ExtraBeesBeeEffects.ACID, new TerritoryBlockEffect(ExtraBeesBeeEffects.ACID, 20,
				ExtraBeesEffectLogic::doAcid, null));
		registration.registerBeeEffect(ExtraBeesBeeEffects.SPAWN_ZOMBIE, new SpawnMobEffect(ExtraBeesBeeEffects.SPAWN_ZOMBIE, ExtraBeesEffectLogic.zombie()));
		registration.registerBeeEffect(ExtraBeesBeeEffects.SPAWN_SKELETON, new SpawnMobEffect(ExtraBeesBeeEffects.SPAWN_SKELETON, ExtraBeesEffectLogic.skeleton()));
		registration.registerBeeEffect(ExtraBeesBeeEffects.SPAWN_CREEPER, new SpawnMobEffect(ExtraBeesBeeEffects.SPAWN_CREEPER, ExtraBeesEffectLogic.creeper()));
		registration.registerBeeEffect(ExtraBeesBeeEffects.LIGHTNING, new LightningEffect());
		registration.registerBeeEffect(ExtraBeesBeeEffects.RADIOACTIVE, new EbRadioactiveEffect());
		registration.registerBeeEffect(ExtraBeesBeeEffects.METEOR, new MeteorEffect());
		registration.registerBeeEffect(ExtraBeesBeeEffects.HUNGER, new HungerEffect());
		registration.registerBeeEffect(ExtraBeesBeeEffects.FOOD, new FoodEffect());
		registration.registerBeeEffect(ExtraBeesBeeEffects.BLINDNESS, new PlayerPotionEffect(ExtraBeesBeeEffects.BLINDNESS, MobEffects.BLINDNESS, 200, ExtraBeesEffectHelper.blindnessFx()));
		registration.registerBeeEffect(ExtraBeesBeeEffects.CONFUSION, new PlayerPotionEffect(ExtraBeesBeeEffects.CONFUSION, MobEffects.NAUSEA, 200, null));
		registration.registerBeeEffect(ExtraBeesBeeEffects.FIREWORKS, new FireworksEffect(ExtraBeesBeeEffects.FIREWORKS, 8, true));
		registration.registerBeeEffect(ExtraBeesBeeEffects.FESTIVAL, new FireworksEffect(ExtraBeesBeeEffects.FESTIVAL, 12, false));
		registration.registerBeeEffect(ExtraBeesBeeEffects.BIRTHDAY, new BirthdayEffect());
		registration.registerBeeEffect(ExtraBeesBeeEffects.TELEPORT, new TeleportEffect());
		registration.registerBeeEffect(ExtraBeesBeeEffects.GRAVITY, new PullEffect(ExtraBeesBeeEffects.GRAVITY, true));
		registration.registerBeeEffect(ExtraBeesBeeEffects.THIEF, new PullEffect(ExtraBeesBeeEffects.THIEF, false));
		registration.registerBeeEffect(ExtraBeesBeeEffects.WITHER, new PlayerPotionEffect(ExtraBeesBeeEffects.WITHER, MobEffects.WITHER, 200, ExtraBeesEffectHelper.witherFx()));
		registration.registerBeeEffect(ExtraBeesBeeEffects.WATER, new TerritoryBlockEffect(ExtraBeesBeeEffects.WATER, 40,
				ExtraBeesEffectLogic::fillWater, ExtraBeesEffectHelper.waterFx()));
		registration.registerBeeEffect(ExtraBeesBeeEffects.SLOW, new PlayerPotionEffect(ExtraBeesBeeEffects.SLOW, MobEffects.WEAKNESS, 200, null));
		registration.registerBeeEffect(ExtraBeesBeeEffects.BONEMEAL_SAPLING, new BonemealEffect(ExtraBeesBeeEffects.BONEMEAL_SAPLING, BonemealKind.SAPLING));
		registration.registerBeeEffect(ExtraBeesBeeEffects.BONEMEAL_FRUIT, new BonemealEffect(ExtraBeesBeeEffects.BONEMEAL_FRUIT, BonemealKind.FRUIT));
		registration.registerBeeEffect(ExtraBeesBeeEffects.BONEMEAL_MUSHROOM, new BonemealEffect(ExtraBeesBeeEffects.BONEMEAL_MUSHROOM, BonemealKind.MUSHROOM));
		registration.registerBeeEffect(ExtraBeesBeeEffects.POWER, new TerritoryBlockEffect(ExtraBeesBeeEffects.POWER, 20,
				ExtraBeesEffectLogic::insertPower, null));
	}

	@FunctionalInterface
	private interface BlockAction {
		void apply(Level level, BlockPos pos);
	}

	private static final class TerritoryBlockEffect extends ThrottledBeeEffect {
		private final BlockAction action;
		private final ExtraBeesEffectHelper.ParticleTypesHolder fx;

		private TerritoryBlockEffect(net.minecraft.resources.Identifier id, int throttle, BlockAction action,
				ExtraBeesEffectHelper.ParticleTypesHolder fx) {
			super(id, true, throttle, false, false);
			this.action = action;
			this.fx = fx;
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			BlockPos pos = ExtraBeesEffectHelper.randomTerritoryPos(genome, housing);
			if (!housing.level().isLoaded(pos)) {
				return storedData;
			}
			action.apply(housing.level(), pos);
			return storedData;
		}

		@Override
		public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			if (fx == null) {
				return super.doFX(genome, storedData, housing);
			}
			return ExtraBeesEffectHelper.doFx(genome, storedData, housing, fx);
		}
	}

	private static final class SpawnMobEffect extends ThrottledBeeEffect {
		private final net.minecraft.world.entity.EntityType<? extends Mob> type;

		private SpawnMobEffect(net.minecraft.resources.Identifier id, net.minecraft.world.entity.EntityType<? extends Mob> type) {
			super(id, true, 40, false, false);
			this.type = type;
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			if (!(housing.level() instanceof ServerLevel serverLevel)) {
				return storedData;
			}
			BlockPos pos = ExtraBeesEffectHelper.randomTerritoryPos(genome, housing);
			if (!serverLevel.isLoaded(pos)) {
				return storedData;
			}
			ExtraBeesEffectLogic.spawnMob(serverLevel, pos, type);
			return storedData;
		}
	}

	private static final class LightningEffect extends ThrottledBeeEffect {
		private LightningEffect() {
			super(ExtraBeesBeeEffects.LIGHTNING, true, 40, false, false);
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			Level level = housing.level();
			if (!(level instanceof ServerLevel serverLevel)) {
				return storedData;
			}
			BlockPos pos = ExtraBeesEffectHelper.randomTerritoryPos(genome, housing);
			if (!serverLevel.isLoaded(pos) || level.getRandom().nextInt(100) >= 1 || !level.canSeeSky(pos)) {
				return storedData;
			}
			LightningBolt bolt = EntityTypes.LIGHTNING_BOLT.create(serverLevel, EntitySpawnReason.MOB_SUMMONED);
			if (bolt == null) {
				return storedData;
			}
			bolt.snapTo(pos.getX(), pos.getY(), pos.getZ());
			serverLevel.addFreshEntity(bolt);
			return storedData;
		}

		@Override
		public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			return ExtraBeesEffectHelper.doFx(genome, storedData, housing, ExtraBeesEffectHelper.lightningFx());
		}
	}

	private static final class EbRadioactiveEffect extends ThrottledBeeEffect {
		private EbRadioactiveEffect() {
			super(ExtraBeesBeeEffects.RADIOACTIVE, true, 40, false, false);
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			if (!(housing.level() instanceof ServerLevel serverLevel)) {
				return storedData;
			}
			List<LivingEntity> entities = ThrottledBeeEffect.getEntitiesInRange(genome, housing, LivingEntity.class);
			for (LivingEntity entity : entities) {
				int damage = 4;
				if (entity instanceof Player) {
					int count = BeeManager.getArmorApiaristHelper().wearsItems(entity, this, true);
					if (count > 3) {
						continue;
					} else if (count > 2) {
						damage = 1;
					} else if (count > 1) {
						damage = 2;
					} else if (count > 0) {
						damage = 3;
					}
				}
				entity.hurtServer(serverLevel, CoreDamageTypes.source(serverLevel, CoreDamageTypes.RADIOACTIVE), damage);
			}
			return storedData;
		}

		@Override
		public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			return ExtraBeesEffectHelper.doFx(genome, storedData, housing, ExtraBeesEffectHelper.radioactiveFx());
		}
	}

	private static final class MeteorEffect extends ThrottledBeeEffect {
		private MeteorEffect() {
			super(ExtraBeesBeeEffects.METEOR, true, 40, false, false);
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			Level level = housing.level();
			if (!(level instanceof ServerLevel serverLevel)) {
				return storedData;
			}
			BlockPos pos = ExtraBeesEffectHelper.randomTerritoryPos(genome, housing);
			if (!serverLevel.isLoaded(pos) || level.getRandom().nextInt(100) >= 1 || !level.canSeeSky(pos)) {
				return storedData;
			}
			SmallFireball fireball = new SmallFireball(serverLevel, pos.getX() + 0.5, pos.getY() + 64, pos.getZ() + 0.5,
					new Vec3(0.0, -0.6, 0.0));
			serverLevel.addFreshEntity(fireball);
			return storedData;
		}

		@Override
		public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			return ExtraBeesEffectHelper.doFx(genome, storedData, housing, ExtraBeesEffectHelper.meteorFx());
		}
	}

	private static final class HungerEffect extends ThrottledBeeEffect {
		private HungerEffect() {
			super(ExtraBeesBeeEffects.HUNGER, true, 40, false, false);
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			Level level = housing.level();
			List<Player> players = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Player.class);
			for (Player player : players) {
				if (level.getRandom().nextInt(4) < BeeManager.getArmorApiaristHelper().wearsItems(player, this, true)) {
					continue;
				}
				player.causeFoodExhaustion(4.0f);
				player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100));
			}
			return storedData;
		}
	}

	private static final class FoodEffect extends ThrottledBeeEffect {
		private FoodEffect() {
			super(ExtraBeesBeeEffects.FOOD, true, 40, false, false);
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			List<Player> players = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Player.class);
			for (Player player : players) {
				FoodData food = player.getFoodData();
				food.eat(2, 0.2f);
			}
			return storedData;
		}

		@Override
		public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			return ExtraBeesEffectHelper.doFx(genome, storedData, housing, ExtraBeesEffectHelper.foodFx());
		}
	}

	private static final class PlayerPotionEffect extends ThrottledBeeEffect {
		private final net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> potion;
		private final int duration;
		private final ExtraBeesEffectHelper.ParticleTypesHolder fx;

		private PlayerPotionEffect(net.minecraft.resources.Identifier id,
				net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> potion, int duration,
				ExtraBeesEffectHelper.ParticleTypesHolder fx) {
			super(id, true, 40, false, false);
			this.potion = potion;
			this.duration = duration;
			this.fx = fx;
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			Level level = housing.level();
			List<Player> players = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Player.class);
			for (Player player : players) {
				if (level.getRandom().nextInt(4) < BeeManager.getArmorApiaristHelper().wearsItems(player, this, true)) {
					continue;
				}
				player.addEffect(new MobEffectInstance(potion, duration));
			}
			return storedData;
		}

		@Override
		public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			if (fx == null) {
				return super.doFX(genome, storedData, housing);
			}
			return ExtraBeesEffectHelper.doFx(genome, storedData, housing, fx);
		}
	}

	private static final class FireworksEffect extends ThrottledBeeEffect {
		private final int chanceDenom;
		private final boolean colored;

		private FireworksEffect(net.minecraft.resources.Identifier id, int chanceDenom, boolean colored) {
			super(id, true, 40, false, false);
			this.chanceDenom = chanceDenom;
			this.colored = colored;
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			Level level = housing.level();
			if (!(level instanceof ServerLevel serverLevel)) {
				return storedData;
			}
			BlockPos pos = ExtraBeesEffectHelper.randomTerritoryPos(genome, housing);
			if (!serverLevel.isLoaded(pos) || level.getRandom().nextInt(chanceDenom) >= 1 || !level.canSeeSky(pos)) {
				return storedData;
			}
			ItemStack rocket = colored ? createSpeciesFirework(genome) : createPlainFirework();
			FireworkRocketEntity entity = new FireworkRocketEntity(serverLevel, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, rocket);
			serverLevel.addFreshEntity(entity);
			return storedData;
		}
	}

	private static final class BirthdayEffect extends ThrottledBeeEffect {
		private BirthdayEffect() {
			super(ExtraBeesBeeEffects.BIRTHDAY, true, 40, false, false);
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			Level level = housing.level();
			if (!(level instanceof ServerLevel serverLevel)) {
				return storedData;
			}
			BlockPos pos = ExtraBeesEffectHelper.randomTerritoryPos(genome, housing);
			if (!serverLevel.isLoaded(pos) || level.getRandom().nextInt(12) >= 1 || !level.canSeeSky(pos)) {
				return storedData;
			}
			ItemStack rocket = createBirthdayFirework(isBinnieBirthday());
			FireworkRocketEntity entity = new FireworkRocketEntity(serverLevel, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, rocket);
			serverLevel.addFreshEntity(entity);
			return storedData;
		}

		private static boolean isBinnieBirthday() {
			Calendar calendar = Calendar.getInstance();
			return calendar.get(Calendar.MONTH) == Calendar.APRIL && calendar.get(Calendar.DAY_OF_MONTH) == 11;
		}
	}

	private static ItemStack createSpeciesFirework(IGenome genome) {
		int primary = genome.getActiveAllele(BeeChromosomes.SPECIES).value().bodyColor();
		int secondary = genome.getActiveAllele(BeeChromosomes.SPECIES).value().stripesColor();
		IntList colors = IntList.of(primary, primary, secondary, secondary, primary, secondary);
		FireworkExplosion explosion = new FireworkExplosion(FireworkExplosion.Shape.SMALL_BALL, colors, IntList.of(), false, true);
		ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
		firework.set(DataComponents.FIREWORKS, new Fireworks(0, List.of(explosion)));
		return firework;
	}

	private static ItemStack createPlainFirework() {
		FireworkExplosion explosion = new FireworkExplosion(FireworkExplosion.Shape.SMALL_BALL, IntList.of(0xffffff), IntList.of(), false, false);
		ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
		firework.set(DataComponents.FIREWORKS, new Fireworks(0, List.of(explosion)));
		return firework;
	}

	private static ItemStack createBirthdayFirework(boolean today) {
		IntList colors = today
				? IntList.of(0xffdd00, 0xff0000, 0x00ff00, 0x0000ff)
				: IntList.of(0xffdd00);
		FireworkExplosion explosion = new FireworkExplosion(FireworkExplosion.Shape.STAR, colors, IntList.of(), false, today);
		ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
		firework.set(DataComponents.FIREWORKS, new Fireworks(0, List.of(explosion)));
		return firework;
	}

	private static final class TeleportEffect extends ThrottledBeeEffect {
		private TeleportEffect() {
			super(ExtraBeesBeeEffects.TELEPORT, true, 40, false, false);
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			Level level = housing.level();
			if (level.getRandom().nextInt(80) > 1) {
				return storedData;
			}
			List<Entity> entities = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Entity.class);
			if (entities.isEmpty()) {
				return storedData;
			}
			Entity entity = entities.get(level.getRandom().nextInt(entities.size()));
			if (!(entity instanceof Mob mob)) {
				return storedData;
			}
			BlockPos pos = ExtraBeesEffectHelper.randomTerritoryPos(genome, housing);
			if (!level.isLoaded(pos) || !level.isEmptyBlock(pos) || !level.isEmptyBlock(pos.above())) {
				return storedData;
			}
			int y = Math.max(4, pos.getY());
			mob.snapTo(pos.getX() + 0.5, y, pos.getZ() + 0.5, mob.getYRot(), mob.getXRot());
			mob.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 160, 10));
			return storedData;
		}

		@Override
		public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			return ExtraBeesEffectHelper.doFx(genome, storedData, housing, ExtraBeesEffectHelper.gravityFx());
		}
	}

	private static final class PullEffect extends ThrottledBeeEffect {
		private final boolean attract;

		private PullEffect(net.minecraft.resources.Identifier id, boolean attract) {
			super(id, true, 20, false, false);
			this.attract = attract;
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			BlockPos position = housing.position();
			if (attract) {
				List<Entity> entities = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Entity.class);
				for (Entity entity : entities) {
					float entityStrength = entity instanceof Player ? 100.0f : 1.0f;
					double posX = position.getX() - entity.getX();
					double posY = position.getY() - entity.getY();
					double posZ = position.getZ() - entity.getZ();
					double distSq = posX * posX + posY * posY + posZ * posZ;
					if (distSq < 2.0) {
						return storedData;
					}
					double strength = 0.5 / distSq * entityStrength;
					entity.push(posX * strength, posY * strength, posZ * strength);
				}
			} else {
				List<Player> players = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Player.class);
				for (Player player : players) {
					double posX = position.getX() - player.getX();
					double posY = position.getY() - player.getY();
					double posZ = position.getZ() - player.getZ();
					double distSq = posX * posX + posY * posY + posZ * posZ;
					if (distSq < 2.0) {
						return storedData;
					}
					double strength = 0.5 / distSq;
					player.push(-posX * strength, -posY * strength, -posZ * strength);
				}
			}
			return storedData;
		}

		@Override
		public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			return ExtraBeesEffectHelper.doFx(genome, storedData, housing, ExtraBeesEffectHelper.gravityFx());
		}
	}

	private enum BonemealKind {
		SAPLING,
		FRUIT,
		MUSHROOM
	}

	private static final class BonemealEffect extends ThrottledBeeEffect {
		private final BonemealKind kind;

		private BonemealEffect(net.minecraft.resources.Identifier id, BonemealKind kind) {
			super(id, true, 40, false, false);
			this.kind = kind;
		}

		@Override
		protected IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
			Level level = housing.level();
			if (!(level instanceof ServerLevel serverLevel) || level.getRandom().nextInt(20) > 1) {
				return storedData;
			}
			BlockPos pos = ExtraBeesEffectHelper.randomTerritoryPos(genome, housing);
			if (!serverLevel.isLoaded(pos)) {
				return storedData;
			}
			BlockState state = level.getBlockState(pos);
			switch (kind) {
				case SAPLING -> {
					if (!ExtraBeesFlowerType.SAPLING.isAcceptableFlower(level, pos)) {
						return storedData;
					}
					if (state.getBlock() instanceof BonemealableBlock growable
							&& growable.isValidBonemealTarget(level, pos, state)
							&& growable.isBonemealSuccess(level, level.getRandom(), pos, state)) {
						growable.performBonemeal(serverLevel, level.getRandom(), pos, state);
					}
				}
				case FRUIT -> {
					if (!ExtraBeesFlowerType.FRUIT.isAcceptableFlower(level, pos)) {
						return storedData;
					}
					growWithBoneMeal(serverLevel, pos);
				}
				case MUSHROOM -> {
					if (!state.is(Blocks.BROWN_MUSHROOM) && !state.is(Blocks.RED_MUSHROOM)) {
						return storedData;
					}
					growWithBoneMeal(serverLevel, pos);
				}
			}
			return storedData;
		}

		private static void growWithBoneMeal(ServerLevel level, BlockPos pos) {
			ItemStack stack = new ItemStack(Items.BONE_MEAL);
			net.minecraft.world.item.BoneMealItem.growCrop(stack, level, pos);
		}
	}
}
