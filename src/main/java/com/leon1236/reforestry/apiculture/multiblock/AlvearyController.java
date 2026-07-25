package com.leon1236.reforestry.apiculture.multiblock;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.IBeeHousingInventory;
import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.climate.IClimateControlled;
import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockComponent;
import com.leon1236.reforestry.apiculture.AlvearyBeeModifier;
import com.leon1236.reforestry.apiculture.InventoryBeeHousing;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.BeekeepingLogic;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;
import com.leon1236.reforestry.core.multiblock.IMultiblockControllerInternal;
import com.leon1236.reforestry.core.multiblock.MultiblockValidationException;
import com.leon1236.reforestry.core.multiblock.RectangularMultiblockControllerBase;

public class AlvearyController extends RectangularMultiblockControllerBase implements IAlvearyControllerInternal, IClimateControlled {
	private static final int WILDCARD_COLOR = 0xffdc16;

	private final InventoryBeeHousing inventory;
	private final IBeekeepingLogic beekeepingLogic;

	private final Set<IBeeModifier> beeModifiers = new HashSet<>();
	private final Set<IBeeListener> beeListeners = new HashSet<>();
	private final Set<IAlvearyComponent.Climatiser<?>> climatisers = new HashSet<>();
	private final Set<IAlvearyComponent.Active<?>> activeComponents = new HashSet<>();

	private IClimateProvider climate = IForestryApi.INSTANCE.getClimateManager().createDummyClimateProvider();
	private byte temperatureSteps;
	private byte humiditySteps;

	public AlvearyController(Level level) {
		super(level, AlvearyMultiblockSizeLimits.INSTANCE);
		this.inventory = new InventoryBeeHousing(InventoryBeeHousing.SLOT_COUNT, this::markChunkDirty);
		this.beekeepingLogic = new BeekeepingLogic(this);
		this.beeModifiers.add(new AlvearyBeeModifier());
	}

	private void markChunkDirty() {
		BlockPos reference = getReferenceCoord();
		if (reference != null && this.level != null && !this.level.isClientSide()) {
			this.level.blockEntityChanged(reference);
		}
	}

	@Override
	public IBeeHousingInventory beeInventory() {
		return this.inventory;
	}

	@Override
	public IBeekeepingLogic getBeekeepingLogic() {
		return this.beekeepingLogic;
	}

	@Override
	public Container getInternalInventory() {
		return this.inventory;
	}

	@Override
	public Iterable<IBeeListener> getBeeListeners() {
		return this.beeListeners;
	}

	@Override
	public Iterable<IBeeModifier> getBeeModifiers() {
		return this.beeModifiers;
	}

	@Override
	protected void onAttachedPartWithMultiblockData(IMultiblockComponent part, CompoundTag data) {
		read(data, this.level.registryAccess());
	}

	@Override
	protected void onBlockAdded(IMultiblockComponent newPart) {
		if (newPart instanceof IAlvearyComponent.BeeModifier<?> source) {
			this.beeModifiers.add(source.getBeeModifier());
		}
		if (newPart instanceof IAlvearyComponent.BeeListener<?> source) {
			this.beeListeners.add(source.getBeeListener());
		}
		if (newPart instanceof IAlvearyComponent.Climatiser<?> climatiser) {
			this.climatisers.add(climatiser);
		}
		if (newPart instanceof IAlvearyComponent.Active<?> active) {
			this.activeComponents.add(active);
		}
	}

	@Override
	protected void onBlockRemoved(IMultiblockComponent oldPart) {
		if (oldPart instanceof IAlvearyComponent.BeeModifier<?> source) {
			this.beeModifiers.remove(source.getBeeModifier());
		}
		if (oldPart instanceof IAlvearyComponent.BeeListener<?> source) {
			this.beeListeners.remove(source.getBeeListener());
		}
		if (oldPart instanceof IAlvearyComponent.Climatiser<?> climatiser) {
			this.climatisers.remove(climatiser);
		}
		if (oldPart instanceof IAlvearyComponent.Active<?> active) {
			this.activeComponents.remove(active);
		}
	}

	@Override
	public void onDestroyed(BlockPos lastPos) {
		if (!this.level.isClientSide()) {
			Containers.dropContents(this.level, lastPos, this.inventory);
		}
	}

	@Override
	protected void onMachineAssembled() {
		super.onMachineAssembled();
		this.climate = IForestryApi.INSTANCE.getClimateManager().createClimateProvider(this.level, getCenterCoord());
	}

	@Override
	protected void isMachineWhole() throws MultiblockValidationException {
		super.isMachineWhole();

		BlockPos maximumCoord = getMaximumCoord();
		BlockPos minimumCoord = getMinimumCoord();

		int slabY = maximumCoord.getY() + 1;
		for (int slabX = minimumCoord.getX(); slabX <= maximumCoord.getX(); slabX++) {
			for (int slabZ = minimumCoord.getZ(); slabZ <= maximumCoord.getZ(); slabZ++) {
				BlockState state = this.level.getBlockState(new BlockPos(slabX, slabY, slabZ));
				if (!state.is(BlockTags.WOODEN_SLABS)) {
					throw new MultiblockValidationException(
							Component.translatable("for.multiblock.alveary.error.needSlabs").getString());
				}
			}
		}

		int airY = maximumCoord.getY();
		for (int airX = minimumCoord.getX() - 1; airX <= maximumCoord.getX() + 1; airX++) {
			for (int airZ = minimumCoord.getZ() - 1; airZ <= maximumCoord.getZ() + 1; airZ++) {
				if (isCoordInMultiblock(airX, airY, airZ)) {
					continue;
				}
				BlockPos pos = new BlockPos(airX, airY, airZ);
				if (this.level.getBlockState(pos).isSolidRender()) {
					throw new MultiblockValidationException(
							Component.translatable("for.multiblock.alveary.error.needSpace").getString());
				}
			}
		}
	}

	@Override
	protected void isGoodForExteriorLevel(IMultiblockComponent part, int level) throws MultiblockValidationException {
		if (level == 2 && !(part instanceof TileAlvearyPlain)) {
			throw new MultiblockValidationException(
					Component.translatable("for.multiblock.alveary.error.needPlainOnTop").getString());
		}
	}

	@Override
	protected void isGoodForInterior(IMultiblockComponent part) throws MultiblockValidationException {
		if (!(part instanceof TileAlvearyPlain)) {
			throw new MultiblockValidationException(
					Component.translatable("for.multiblock.alveary.error.needPlainInterior").getString());
		}
	}

	@Override
	protected void onAssimilate(IMultiblockControllerInternal assimilated) {
	}

	@Override
	public void onAssimilated(IMultiblockControllerInternal assimilator) {
	}

	@Override
	protected boolean serverTick(int tickCount) {
		for (IAlvearyComponent.Active<?> activeComponent : this.activeComponents) {
			activeComponent.updateServer(tickCount);
		}

		boolean canWork = this.beekeepingLogic.canWork();
		if (canWork) {
			this.beekeepingLogic.doWork();
		}

		this.temperatureSteps = 0;
		this.humiditySteps = 0;
		for (IAlvearyComponent.Climatiser<?> climatiser : this.climatisers) {
			climatiser.changeClimate(tickCount, this);
		}

		if ((this.level.getGameTime() & 63L) == 0L) {
			this.climate = IForestryApi.INSTANCE.getClimateManager().createClimateProvider(this.level, getCenterCoord());
		}

		return canWork;
	}

	@Override
	protected void clientTick(int tickCount) {
		for (IAlvearyComponent.Active<?> activeComponent : this.activeComponents) {
			activeComponent.updateClient(tickCount);
		}

		ItemStack queen = this.inventory.getQueen();
		if (!(queen.getItem() instanceof ItemBeeGE beeItem) || !"queen".equals(beeItem.lifeStage())) {
			return;
		}

		if (updateOnInterval(10)) {
			this.beekeepingLogic.doBeeFX();
		}
		if (updateOnInterval(50)) {
			spawnPollenDust(queenColor(queen));
		}
	}

	private static int queenColor(ItemStack queen) {
		IGenome genome = queen.get(ApicultureDataComponents.BEE_GENOME.type());
		int color = genome != null
				? genome.getActiveAllele(BeeChromosomes.SPECIES).value().bodyColor()
				: WILDCARD_COLOR;
		return ARGB.opaque(color);
	}

	private void spawnPollenDust(int color) {
		BlockPos center = getCenterCoord();
		double fxX = center.getX() + 0.5;
		double fxY = center.getY() + 1.0;
		double fxZ = center.getZ() + 0.5;
		float distance = 1.6f;
		float spread = distance * (this.level.getRandom().nextFloat() - 0.5f);
		fxY += this.level.getRandom().nextFloat() * 0.8f;

		DustParticleOptions dust = new DustParticleOptions(color, 1.0f);
		this.level.addParticle(dust, fxX - distance, fxY, fxZ + spread, 0, 0, 0);
		this.level.addParticle(dust, fxX + distance, fxY, fxZ + spread, 0, 0, 0);
		this.level.addParticle(dust, fxX + spread, fxY, fxZ - distance, 0, 0, 0);
		this.level.addParticle(dust, fxX + spread, fxY, fxZ + distance, 0, 0, 0);
	}

	@Override
	public CompoundTag write(CompoundTag data, HolderLookup.Provider registries) {
		super.write(data, registries);

		data.putByte("temperatureSteps", this.temperatureSteps);
		data.putByte("humiditySteps", this.humiditySteps);

		TagValueOutput output = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, registries);
		this.inventory.save(output);
		data.put("Items", output.buildResult());
		return data;
	}

	@Override
	public void read(CompoundTag data, HolderLookup.Provider registries) {
		super.read(data, registries);

		this.temperatureSteps = data.getByteOr("temperatureSteps", (byte) 0);
		this.humiditySteps = data.getByteOr("humiditySteps", (byte) 0);

		CompoundTag items = data.getCompoundOrEmpty("Items");
		ValueInput input = TagValueInput.create(ProblemReporter.DISCARDING, registries, items);
		this.inventory.load(input);
	}

	@Override
	public BlockPos getCoordinates() {
		return getCenterCoord().above();
	}

	@Override
	public Level level() {
		return this.level;
	}

	@Override
	public BlockPos position() {
		return getCoordinates();
	}

	@Override
	public Vec3 getBeeFXCoordinates() {
		BlockPos center = getCenterCoord();
		return new Vec3(center.getX() + 0.5, center.getY() + 1.5, center.getZ() + 0.5);
	}

	@Override
	public HumidityType humidity() {
		return this.climate.humidity().up(this.humiditySteps);
	}

	@Override
	public TemperatureType temperature() {
		if (this.temperatureSteps >= 0 && (isHellish() || getBiome().is(BiomeTags.IS_NETHER))) {
			return TemperatureType.HELLISH;
		}
		return this.climate.temperature().up(this.temperatureSteps);
	}

	private boolean isHellish() {
		for (IBeeModifier modifier : this.beeModifiers) {
			if (modifier.isHellish()) {
				return true;
			}
		}
		return false;
	}

	@Nullable
	@Override
	public GameProfile getOwner() {
		return getOwnerHandler().getOwner();
	}

	@Override
	public String getUnlocalizedType() {
		return "for.multiblock.alveary.type";
	}

	@Override
	public Holder<Biome> getBiome() {
		return this.level.getBiome(getCoordinates());
	}

	@Override
	public int getBlockLightValue() {
		return this.level.getMaxLocalRawBrightness(getTopCenterCoord().above());
	}

	@Override
	public boolean canBlockSeeTheSky() {
		return this.level.getBrightness(LightLayer.SKY, getTopCenterCoord().offset(0, 2, 0)) >= 10;
	}

	@Override
	public boolean isRaining() {
		return this.level.isRaining()
				&& this.level.getBrightness(LightLayer.SKY, getTopCenterCoord().offset(0, 2, 0)) > 7;
	}

	@Override
	public void addTemperatureChange(byte steps) {
		this.temperatureSteps += steps;
	}

	@Override
	public void addHumidityChange(byte steps) {
		this.humiditySteps += steps;
	}

	@Override
	public int getHealthScaled(int scale) {
		return this.beekeepingLogic.getWorkProgressPercent() * scale / 100;
	}
}
