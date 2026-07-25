package com.leon1236.reforestry.apiculture.tiles;

import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeHousingInventory;
import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.apiculture.hives.IHiveTile;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.WorldgenBeekeepingLogic;
import com.leon1236.reforestry.apiculture.blocks.BlockBeeHive;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureTiles;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.core.damage.CoreDamageTypes;

public class TileHive extends BlockEntity implements IHiveTile, IBeeHousing {
    private final NonNullList<ItemStack> contained = NonNullList.withSize(2, ItemStack.EMPTY);
    private final HiveBeeHousingInventory inventory = new HiveBeeHousingInventory(this);
    private final WorldgenBeekeepingLogic beeLogic = new WorldgenBeekeepingLogic(this);
    private final IErrorLogic errorLogic = IForestryApi.INSTANCE.getErrorManager().createErrorLogic();

    private boolean angry;
    private int calmTime;
    private int tickCounter;

    public TileHive(BlockEntityType<? extends TileHive> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public TileHive(BlockPos pos, BlockState state) {
        this(ApicultureTiles.HIVE.type(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TileHive tile) {
        tile.tick(level);
    }

    public void tick(Level level) {
        tickCounter++;
        if (level.isClientSide()) {
            return;
        }

        boolean canWork = beeLogic.canWork();
        int interval = angry ? 10 : 200;
        if (tickCounter % interval == 0) {
            if (calmTime == 0) {
                if (canWork && angry && level.getDifficulty() != Difficulty.PEACEFUL) {
                    AABB boundingBox = new AABB(worldPosition).inflate(2.0);
                    List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, boundingBox,
                            this::isValidBeeTarget);
                    if (!entities.isEmpty()) {
                        Collections.shuffle(entities);
                        LivingEntity entity = entities.getFirst();
                        if (!entity.isInWater()) {
                            attack(entity, 2);
                        }
                    }
                }
                beeLogic.doWork();
            } else {
                calmTime--;
            }
        }
    }

    private boolean isValidBeeTarget(LivingEntity input) {
        if (!input.isAlive() || input.isInvisible()) {
            return false;
        }
        if (input instanceof Player) {
            return EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(input);
        }
        if (angry) {
            return true;
        }
        if (input instanceof Enemy) {
            return !(input instanceof EnderMan) && !(input instanceof ZombifiedPiglin);
        }
        return false;
    }

    public net.minecraft.resources.Identifier getSpeciesId() {
        if (getBlockState().getBlock() instanceof BlockBeeHive hive) {
            return hive.getSpeciesId();
        }
        return ReForestry.id("bee_forest");
    }

    public void setContained(List<ItemStack> bees) {
        for (int i = 0; i < contained.size(); i++) {
            contained.set(i, i < bees.size() ? bees.get(i).copy() : ItemStack.EMPTY);
        }
        setChanged();
    }

    public IGenome getContainedGenome() {
        ItemStack containedBee = contained.getFirst();
        if (!containedBee.isEmpty()) {
            IGenome genome = containedBee.get(ApicultureDataComponents.BEE_GENOME.type());
            if (genome != null) {
                return genome;
            }
        }
        try {
            return ApicultureGenetics.getDefaultGenome(getSpeciesId());
        } catch (IllegalArgumentException ignored) {
            return ApicultureGenetics.getDefaultGenome(ReForestry.id("bee_forest"));
        }
    }

    @Override
    public void calmBees() {
        calmTime = 5;
        angry = false;
    }

    @Override
    public boolean isAngry() {
        return angry;
    }

    @Override
    public void onAttack(Level world, BlockPos pos, Player player) {
        if (calmTime == 0) {
            angry = true;
        }
    }

    @Override
    public void onBroken(Level world, BlockPos pos, Player player, boolean canHarvest) {
        if (calmTime == 0) {
            attack(player, 10);
        }
        if (canHarvest) {
            for (ItemStack beeStack : contained) {
                if (!beeStack.isEmpty()) {
                    Block.popResource(world, pos, beeStack);
                }
            }
        }
    }

    private static void attack(LivingEntity entity, int maxDamage) {
        Level level = entity.level();
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        double attackAmount = serverLevel.getRandom().nextDouble() / 2.0 + 0.5;
        int damage = (int) (attackAmount * maxDamage);
        if (damage <= 0) {
            return;
        }
        int count = 0;
        if (BeeManager.armorApiaristHelper != null) {
            count = BeeManager.armorApiaristHelper.wearsItems(entity, null, true);
        }
        if (serverLevel.getRandom().nextInt(4) >= count) {
            entity.hurtServer(serverLevel, CoreDamageTypes.source(serverLevel, CoreDamageTypes.HIVE), damage);
        }
    }

    @Override
    public Iterable<IBeeModifier> getBeeModifiers() {
        return List.of();
    }

    @Override
    public Iterable<IBeeListener> getBeeListeners() {
        return List.of();
    }

    @Override
    public IBeeHousingInventory beeInventory() {
        return inventory;
    }

    @Override
    public IBeekeepingLogic getBeekeepingLogic() {
        return beeLogic;
    }

    @Override
    public TemperatureType temperature() {
        return IForestryApi.INSTANCE.getClimateManager().getTemperature(getBiome());
    }

    @Override
    public HumidityType humidity() {
        return IForestryApi.INSTANCE.getClimateManager().getHumidity(getBiome());
    }

    @Override
    public int getBlockLightValue() {
        return level != null && level.isBrightOutside() ? 15 : 0;
    }

    @Override
    public boolean canBlockSeeTheSky() {
        return true;
    }

    @Override
    public boolean isRaining() {
        return level != null && level.isRainingAt(worldPosition.above());
    }

    @Override
    public Level level() {
        return level;
    }

    @Override
    public Holder<Biome> getBiome() {
        if (level == null) {
            throw new IllegalStateException("Hive has no level");
        }
        return level.getBiome(worldPosition);
    }

    @Nullable
    @Override
    public GameProfile getOwner() {
        return null;
    }

    @Override
    public Vec3 getBeeFXCoordinates() {
        return new Vec3(worldPosition.getX() + 0.5, worldPosition.getY() + 0.25, worldPosition.getZ() + 0.5);
    }

    @Override
    public IErrorLogic getErrorLogic() {
        return errorLogic;
    }

    @Override
    public BlockPos position() {
        return worldPosition;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, contained);
        output.putBoolean("angry", angry);
        output.putInt("calmTime", calmTime);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        contained.clear();
        ContainerHelper.loadAllItems(input, contained);
        angry = input.getBooleanOr("angry", false);
        calmTime = input.getIntOr("calmTime", 0);
    }
}
