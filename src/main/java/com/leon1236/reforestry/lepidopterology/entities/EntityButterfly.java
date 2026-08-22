package com.leon1236.reforestry.lepidopterology.entities;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ReforestryBiomeTags;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.pollen.IPollen;
import com.leon1236.reforestry.api.genetics.pollen.IPollenType;
import com.leon1236.reforestry.api.lepidopterology.IEntityButterfly;
import com.leon1236.reforestry.api.lepidopterology.ILepidopteristTracker;
import com.leon1236.reforestry.api.lepidopterology.genetics.ButterflyLifeStage;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.genetics.pollen.PollenManager;
import com.leon1236.reforestry.lepidopterology.ModuleLepidopterology;
import com.leon1236.reforestry.lepidopterology.genetics.Butterfly;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflyChromosomes;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflySpeciesType;

public class EntityButterfly extends PathfinderMob implements IEntityButterfly {
	private static final String NBT_BUTTERFLY = "BTFLY";
	private static final String NBT_POLLEN_TYPE = "PLNTP";
	private static final String NBT_POLLEN = "PLN";
	private static final String NBT_STATE = "STATE";
	private static final String NBT_EXHAUSTION = "EXH";
	private static final String NBT_HOME = "HOME";

	public static final int COOLDOWNS = 1500;

	private static final EntityDataAccessor<String> DATAWATCHER_ID_SPECIES =
			SynchedEntityData.defineId(EntityButterfly.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<Integer> DATAWATCHER_ID_SIZE =
			SynchedEntityData.defineId(EntityButterfly.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Byte> DATAWATCHER_ID_STATE =
			SynchedEntityData.defineId(EntityButterfly.class, EntityDataSerializers.BYTE);

	private static final float DEFAULT_BUTTERFLY_SIZE = 0.75f;
	private static final EnumButterflyState DEFAULT_STATE = EnumButterflyState.FLYING;

	public static final int EXHAUSTION_REST = 1000;
	public static final int EXHAUSTION_CONSUMPTION = 100 * EXHAUSTION_REST;
	public static final int MAX_LIFESPAN = 24000 * 7;

	private static final int CLUSTER_WIDTH = 128;
	private static final int CLUSTER_HEIGHT = 64;
	private static final int CLUSTER_LIMIT = 20;

	@Nullable
	private Vec3 flightTarget;
	private int exhaustion;
	private IButterfly contained = IForestryApi.get().getGeneticManager()
			.createDefaultIndividual(ForestrySpeciesTypes.BUTTERFLY);
	@Nullable
	private IPollen pollen;

	public int cooldownPollination = 0;
	public int cooldownEgg = 0;
	public int cooldownMate = 0;
	private boolean isImmuneToFire;

	@Nullable
	private IButterflySpecies species;
	private float size = DEFAULT_BUTTERFLY_SIZE;
	private EnumButterflyState state = DEFAULT_STATE;

	public EntityButterfly(EntityType<? extends EntityButterfly> type, Level world) {
		super(type, world);
	}

	public static EntityButterfly create(EntityType<EntityButterfly> type, Level world, IButterfly butterfly,
			BlockPos homePos) {
		EntityButterfly entity = new EntityButterfly(type, world);
		entity.setIndividual(butterfly);
		entity.setHomeTo(homePos, ModuleLepidopterology.getMaxDistance());
		return entity;
	}

	public static boolean isMaxButterflyCluster(Vec3 center, Level level) {
		return level.getEntitiesOfClass(EntityButterfly.class,
				AABB.ofSize(center, CLUSTER_WIDTH, CLUSTER_HEIGHT, CLUSTER_WIDTH)).size() > CLUSTER_LIMIT;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATAWATCHER_ID_SPECIES, "");
		builder.define(DATAWATCHER_ID_SIZE, (int) (DEFAULT_BUTTERFLY_SIZE * 100));
		builder.define(DATAWATCHER_ID_STATE, (byte) DEFAULT_STATE.ordinal());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new AIButterflyFlee(this));
		this.goalSelector.addGoal(9, new AIButterflyMate(this));
		this.goalSelector.addGoal(10, new AIButterflyPollinate(this));
		this.goalSelector.addGoal(11, new AIButterflyRest(this));
		this.goalSelector.addGoal(12, new AIButterflyRise(this));
		this.goalSelector.addGoal(13, new AIButterflyWander(this));
	}

	@Override
	public PathfinderMob getEntity() {
		return this;
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		if (this.contained instanceof Butterfly butterfly) {
			output.store(NBT_BUTTERFLY, Butterfly.CODEC, butterfly);
		}
		if (this.pollen != null) {
			output.putString(NBT_POLLEN_TYPE, this.pollen.getType().id().toString());
			IGenome pollenGenome = this.pollen.getPollen();
			output.store(NBT_POLLEN, pollenGenome.karyotype().genomeCodec(), pollenGenome);
		}
		output.putByte(NBT_STATE, (byte) getState().ordinal());
		output.putInt(NBT_EXHAUSTION, this.exhaustion);
		output.putLong(NBT_HOME, getHomePosition().asLong());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		IButterfly butterfly = input.read(NBT_BUTTERFLY, Butterfly.CODEC).orElse(null);
		setIndividual(butterfly);

		String pollenTypeId = input.getStringOr(NBT_POLLEN_TYPE, "");
		if (!pollenTypeId.isEmpty()) {
			Identifier typeId = Identifier.tryParse(pollenTypeId);
			IPollenType type = typeId == null ? null : IForestryApi.get().getPollenManager().getPollenType(typeId);
			IGenome pollenGenome = input.read(NBT_POLLEN, TreeChromosomes.KARYOTYPE.genomeCodec()).orElse(null);
			if (type != null && pollenGenome != null) {
				this.pollen = PollenManager.INSTANCE.createPollen(type, pollenGenome);
			}
		}

		int stateOrdinal = input.getByteOr(NBT_STATE, (byte) DEFAULT_STATE.ordinal());
		if (stateOrdinal >= 0 && stateOrdinal < EnumButterflyState.VALUES.length) {
			setState(EnumButterflyState.VALUES[stateOrdinal]);
		}
		this.exhaustion = input.getIntOr(NBT_EXHAUSTION, 0);
		BlockPos home = BlockPos.of(input.getLongOr(NBT_HOME, BlockPos.ZERO.asLong()));
		setHomeTo(home, ModuleLepidopterology.getMaxDistance());
	}

	public float getWingFlap(float partialTickTime) {
		int offset = this.species != null ? this.species.id().toString().hashCode() : level().getRandom().nextInt();
		return getState().getWingFlap(this, offset, partialTickTime);
	}

	public void setState(EnumButterflyState state) {
		if (this.state != state) {
			this.state = state;
			if (!level().isClientSide()) {
				this.entityData.set(DATAWATCHER_ID_STATE, (byte) state.ordinal());
			}
		}
	}

	public EnumButterflyState getState() {
		return this.state;
	}

	public float getSize() {
		return this.size;
	}

	@Override
	public float getSpeed() {
		return this.contained.getGenome().getActiveAllele(ButterflyChromosomes.SPEED).value();
	}

	@Override
	public boolean fireImmune() {
		return this.isImmuneToFire;
	}

	@Nullable
	public Vec3 getDestination() {
		return this.flightTarget;
	}

	public void setDestination(@Nullable Vec3 destination) {
		this.flightTarget = destination;
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader levelReader) {
		if (!(levelReader instanceof Level level) || !level.isLoaded(pos)) {
			return -100f;
		}

		float weight = 0.0f;
		double distanceToHome = getHomePosition().distSqr(pos);

		if (!isWithinHomeDistanceFromPosition(distanceToHome)) {
			weight -= 7.5f + 0.005 * (distanceToHome / 4);
		}

		if (!getButterfly().isAcceptedEnvironment(level, pos.getX(), pos.getY(), pos.getZ())) {
			weight -= 15.0f;
		}

		if (!level.getEntitiesOfClass(EntityButterfly.class,
				new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1)).isEmpty()) {
			weight -= 1.0f;
		}

		int depth = getFluidDepth(pos);
		if (depth > 0) {
			weight -= 0.1f * depth;
		} else {
			BlockState blockState = level.getBlockState(pos);
			if (blockState.is(BlockTags.FLOWERS)) {
				weight += 2.0f;
			} else if (blockState.getBlock() instanceof SaplingBlock) {
				weight += 1.5f;
			} else if (blockState.getBlock() instanceof BonemealableBlock) {
				weight += 1.0f;
			} else if (blockState.is(BlockTags.LEAVES)) {
				weight += 1.0f;
			}

			BlockPos posBelow = pos.below();
			BlockState blockStateBelow = level.getBlockState(posBelow);
			if (blockState.is(BlockTags.LEAVES)) {
				weight += 5.0f;
			} else if (blockStateBelow.getBlock() instanceof FenceBlock) {
				weight += 1.0f;
			} else if (blockStateBelow.getBlock() instanceof WallBlock) {
				weight += 1.0f;
			}
		}

		weight += level.getEffectiveSkyBrightness(pos);
		return weight;
	}

	private boolean isWithinHomeDistanceFromPosition(double distanceToHome) {
		return distanceToHome < (double) this.getHomeRadius() * this.getHomeRadius();
	}

	private int getFluidDepth(BlockPos pos) {
		ChunkAccess chunk = level().getChunk(pos);
		int xx = pos.getX() & 15;
		int zz = pos.getZ() & 15;
		int depth = 0;
		BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos(xx, 0, zz);

		int highestFilledSection = chunk.getHighestFilledSectionIndex();
		int top = highestFilledSection == ChunkAccess.NO_FILLED_SECTION
				? chunk.getMinY()
				: SectionPos.sectionToBlockCoord(chunk.getSectionYFromSectionIndex(highestFilledSection))
						+ SectionPos.SECTION_MAX_INDEX;
		for (int y = top; y > 0; --y) {
			BlockState blockState = chunk.getBlockState(cursor.setY(y));
			if (blockState.liquid()) {
				depth++;
			} else if (!blockState.isAir()) {
				break;
			}
		}

		return depth;
	}

	@Override
	@Nullable
	public IPollen getPollen() {
		return this.pollen;
	}

	@Override
	public void setPollen(@Nullable IPollen pollen) {
		this.pollen = pollen;
	}

	@Override
	public void changeExhaustion(int change) {
		this.exhaustion = Math.max(this.exhaustion + change, 0);
	}

	@Override
	public int getExhaustion() {
		return this.exhaustion;
	}

	public boolean canFly() {
		return this.contained.canTakeFlight(level(), getX(), getY(), getZ());
	}

	public void setIndividual(@Nullable IButterfly butterfly) {
		if (butterfly == null) {
			butterfly = IForestryApi.get().getGeneticManager().createDefaultIndividual(ForestrySpeciesTypes.BUTTERFLY);
		}
		this.contained = butterfly;

		IGenome genome = this.contained.getGenome();
		this.isImmuneToFire = genome.getActiveAllele(ButterflyChromosomes.FIREPROOF).value();
		this.size = genome.getActiveAllele(ButterflyChromosomes.SIZE).value();
		this.species = genome.getActiveAllele(ButterflyChromosomes.SPECIES).value();

		if (!level().isClientSide()) {
			this.entityData.set(DATAWATCHER_ID_SIZE, (int) (this.size * 100));
			this.entityData.set(DATAWATCHER_ID_SPECIES, this.species.id().toString());
		}
	}

	@Override
	public IButterfly getButterfly() {
		return this.contained;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
			EntitySpawnReason reason, @Nullable SpawnGroupData spawnDataIn) {
		if (!level().isClientSide()) {
			setIndividual(this.contained);
		}
		return spawnDataIn;
	}

	@Override
	public Component getName() {
		if (this.species == null) {
			return super.getName();
		}
		return this.species.getDisplayName();
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor worldIn, EntitySpawnReason spawnReasonIn) {
		return true;
	}

	@Override
	public int getDimensionChangingDelay() {
		return 1000;
	}

	@Override
	public boolean canTeleport(Level from, Level to) {
		return false;
	}

	@Override
	public boolean canUsePortal(boolean allowVehicles) {
		return false;
	}

	public boolean isRenderable() {
		return this.species != null;
	}

	@Nullable
	public IButterflySpecies getSpecies() {
		return this.species;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void doPush(Entity other) {
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return this.tickCount > MAX_LIFESPAN;
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (isDeadOrDying()) {
			return InteractionResult.FAIL;
		}
		ItemStack stack = player.getItemInHand(hand);
		if (stack.is(ReforestryBiomeTags.Items.SCOOPS)) {
			Level level = level();
			if (level instanceof ServerLevel) {
				IBreedingTracker tracker = ButterflySpeciesType.INSTANCE
						.getBreedingTracker(level, player.getGameProfile());
				ItemStack itemStack = this.contained.createStack(ButterflyLifeStage.BUTTERFLY);
				if (tracker instanceof ILepidopteristTracker lepidopteristTracker) {
					lepidopteristTracker.registerCatch(this.contained);
				} else {
					tracker.registerSpecies(this.contained.getSpecies().id());
				}
				spawnAtLocation((ServerLevel) level, itemStack);
				remove(RemovalReason.KILLED);
			}
			return InteractionResult.SUCCESS;
		}

		return super.mobInteract(player, hand);
	}

	@Override
	protected void dropCustomDeathLoot(ServerLevel serverLevel, DamageSource source, boolean recentlyHitIn) {
		int lootLevel = 0;
		if (source.getEntity() instanceof LivingEntity attacker) {
			Holder<Enchantment> looting = serverLevel.registryAccess()
					.lookupOrThrow(Registries.ENCHANTMENT)
					.getOrThrow(Enchantments.LOOTING);
			lootLevel = EnchantmentHelper.getEnchantmentLevel(looting, attacker);
		}
		for (ItemStack stack : this.contained.getLootDrop(this, recentlyHitIn, lootLevel)) {
			if (!stack.isEmpty()) {
				spawnAtLocation(serverLevel, stack);
			}
		}

		float metabolism = (float) this.contained.getGenome().getActiveAllele(ButterflyChromosomes.METABOLISM).value() / 10;
		if (serverLevel.getRandom().nextFloat() < ModuleLepidopterology.getSerumChance() * metabolism) {
			spawnAtLocation(serverLevel, this.contained.createStack(ButterflyLifeStage.SERUM));
		}

		if (this.pollen != null) {
			ItemStack pollenStack = this.pollen.createStack();
			if (!pollenStack.isEmpty()) {
				spawnAtLocation(serverLevel, pollenStack);
			}
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (level().isClientSide()) {
			if (this.species == null) {
				String speciesUid = this.entityData.get(DATAWATCHER_ID_SPECIES);
				Identifier speciesId = Identifier.tryParse(speciesUid);
				IButterflySpecies synced = speciesId == null ? null : ButterflySpeciesType.INSTANCE.getSpeciesSafe(speciesId);
				if (synced != null) {
					this.species = synced;
					this.size = this.entityData.get(DATAWATCHER_ID_SIZE) / 100f;
				}
			}

			byte stateOrdinal = this.entityData.get(DATAWATCHER_ID_STATE);
			if (this.state.ordinal() != stateOrdinal && stateOrdinal >= 0
					&& stateOrdinal < EnumButterflyState.VALUES.length) {
				setState(EnumButterflyState.VALUES[stateOrdinal]);
			}
		}

		Vec3 motion = getDeltaMovement();
		if (this.state == EnumButterflyState.FLYING && this.flightTarget != null && this.flightTarget.y > position().y) {
			setDeltaMovement(motion.x, motion.y * 0.6 + 0.15, motion.z);
		} else {
			setDeltaMovement(motion.x, motion.y * 0.6, motion.z);
		}

		if (level() instanceof ServerLevel serverLevel) {
			if (this.exhaustion > EXHAUSTION_CONSUMPTION && this.random.nextInt(20) == 0) {
				hurtServer(serverLevel, damageSources().generic(), 1);
			}
			if (this.tickCount > MAX_LIFESPAN) {
				hurtServer(serverLevel, damageSources().generic(), 1);
			}
		}

		if (this.cooldownEgg > 0) {
			this.cooldownEgg--;
		}
		if (this.cooldownPollination > 0) {
			this.cooldownPollination--;
		}
		if (this.cooldownMate > 0) {
			this.cooldownMate--;
		}
	}

	@Override
	protected void customServerAiStep(ServerLevel serverLevel) {
		Vec3 flightTarget = this.flightTarget;
		if (getState().doesMovement && flightTarget != null) {
			Vec3 position = position();
			double diffX = flightTarget.x + 0.5 - position.x;
			double diffY = flightTarget.y + 0.1 - position.y;
			double diffZ = flightTarget.z + 0.5 - position.z;

			Vec3 motion = getDeltaMovement();
			double newX = motion.x + (Math.signum(diffX) * 0.5 - motion.x) * 0.1;
			double newY = motion.y + (Math.signum(diffY) * 0.7 - motion.y) * 0.1;
			double newZ = motion.z + (Math.signum(diffZ) * 0.5 - motion.z) * 0.1;

			setDeltaMovement(newX, newY, newZ);

			float horizontal = (float) (Mth.atan2(newZ, newX) * Mth.RAD_TO_DEG) - 90f;
			setYRot(getYRot() + Mth.wrapDegrees(horizontal - getYRot()));

			setZza(this.contained.getGenome().getActiveAllele(ButterflyChromosomes.SPEED).value());
		} else {
			setDeltaMovement(getDeltaMovement().multiply(1, 0.6, 1));
		}
	}

	@Override
	public boolean causeFallDamage(double fallDistance, float multiplier, DamageSource source) {
		return false;
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	@Override
	public boolean isIgnoringBlockTriggers() {
		return true;
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	@Override
	public ItemStack getPickResult() {
		if (this.species == null) {
			return ItemStack.EMPTY;
		}
		return this.species.createStack(ButterflyLifeStage.BUTTERFLY);
	}

	@Override
	public boolean canMateWith(IEntityButterfly butterfly) {
		if (butterfly.getButterfly().getMate() != null) {
			return false;
		}
		if (getButterfly().getMate() != null) {
			return false;
		}
		return !getButterfly().getGenome().isSameAlleles(butterfly.getButterfly().getGenome());
	}

	@Override
	public boolean canMate() {
		return this.cooldownMate <= 0;
	}
}
