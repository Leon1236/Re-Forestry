package com.leon1236.reforestry.apiculture.tiles;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ARGB;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeeHousingInventory;
import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.apiculture.hives.IHiveFrame;
import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.ApiaryBeeListener;
import com.leon1236.reforestry.apiculture.ApiaryBeeModifier;
import com.leon1236.reforestry.apiculture.BeehouseBeeModifier;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.BeekeepingLogic;
import com.leon1236.reforestry.apiculture.gui.ContainerBeeHousing;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;

public class TileBeeHousing extends BlockEntity implements Container, IBeeHousing, IBeeHousingInventory,
        ExtendedMenuProvider<BlockPos> {
    public static final int SLOT_QUEEN = 0;
    public static final int SLOT_DRONE = 1;
    public static final int SLOT_PRODUCT_1 = 2;
    public static final int SLOT_PRODUCT_COUNT = 7;
    public static final int SLOT_FRAME_1 = SLOT_PRODUCT_1 + SLOT_PRODUCT_COUNT;
    public static final int SLOT_FRAME_COUNT = 3;
    public static final int SLOT_COUNT = SLOT_FRAME_1 + SLOT_FRAME_COUNT;
    public static final int ERROR_SLOT_COUNT = 8;

    private static final IBeeModifier BEEHOUSE_MODIFIER = new BeehouseBeeModifier();
    private static final IBeeModifier APIARY_MODIFIER = new ApiaryBeeModifier();

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final IBeekeepingLogic beeLogic = new BeekeepingLogic(this);
    private final IErrorLogic errorLogic = IForestryApi.INSTANCE.getErrorManager().createErrorLogic();
    private final IBeeListener apiaryBeeListener = new ApiaryBeeListener(this);
    private final String translationKey;
    private final boolean hasFrames;
    private IClimateProvider climate = IForestryApi.INSTANCE.getClimateManager().createDummyClimateProvider();
    private int workProgressPercent;
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;
    private final ContainerData progressData = new ContainerData() {
        @Override
        public int get(int index) {
            return workProgressPercent;
        }

        @Override
        public void set(int index, int value) {
            workProgressPercent = value;
        }

        @Override
        public int getCount() {
            return 1;
        }
    };
    private final ContainerData errorData = new ContainerData() {
        @Override
        public int get(int index) {
            if (index == 0) {
                return syncedErrorCount;
            }
            int errorIndex = index - 1;
            return errorIndex >= 0 && errorIndex < syncedErrorIds.length ? syncedErrorIds[errorIndex] : -1;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                syncedErrorCount = value;
                return;
            }
            int errorIndex = index - 1;
            if (errorIndex >= 0 && errorIndex < syncedErrorIds.length) {
                syncedErrorIds[errorIndex] = value;
            }
        }

        @Override
        public int getCount() {
            return ERROR_SLOT_COUNT + 1;
        }
    };

    public TileBeeHousing(BlockEntityType<?> type, BlockPos pos, BlockState state, String translationKey, boolean hasFrames) {
        super(type, pos, state);
        this.translationKey = translationKey;
        this.hasFrames = hasFrames;
    }

    public boolean hasFrames() {
        return hasFrames;
    }

    public IBeekeepingLogic getBeeLogic() {
        return beeLogic;
    }

    @Override
    public IBeekeepingLogic getBeekeepingLogic() {
        return beeLogic;
    }

    public ContainerData getProgressData() {
        return progressData;
    }

    public ContainerData getErrorData() {
        return errorData;
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        climate = IForestryApi.INSTANCE.getClimateManager().createClimateProvider(level, getBlockPos());
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileBeeHousing tile) {
        if (tile.beeLogic.canWork()) {
            tile.beeLogic.doWork();
        }
        tile.workProgressPercent = tile.beeLogic.getWorkProgressPercent();
        tile.syncErrors();
        if ((level.getGameTime() & 63L) == 0L) {
            tile.climate = IForestryApi.INSTANCE.getClimateManager().createClimateProvider(level, pos);
        }
    }

    private void syncErrors() {
        syncedErrorCount = 0;
        for (var error : errorLogic.getErrors()) {
            if (syncedErrorCount >= ERROR_SLOT_COUNT) {
                break;
            }
            short id = IForestryApi.INSTANCE.getErrorManager().getNumericId(error);
            syncedErrorIds[syncedErrorCount++] = id;
        }
        for (int i = syncedErrorCount; i < ERROR_SLOT_COUNT; i++) {
            syncedErrorIds[i] = -1;
        }
    }

    private static final int FLOWER_SCAN_RADIUS_XZ = 5;
    private static final int FLOWER_SCAN_RADIUS_Y = 3;
    private static final int FLOWER_RESCAN_INTERVAL = 100;
    private static final int WILDCARD_COLOR = 0xffdc16;

    private final List<BlockPos> flowerPositions = new ArrayList<>();
    private int clientTicks;

    public static void clientTick(Level level, BlockPos pos, BlockState state, TileBeeHousing tile) {
        ItemStack queen = tile.getQueen();
        if (queen.isEmpty() || !(queen.getItem() instanceof ItemBeeGE beeItem) || !"queen".equals(beeItem.lifeStage())) {
            return;
        }
        tile.clientTicks++;
        if (tile.clientTicks % FLOWER_RESCAN_INTERVAL == 0) {
            tile.scanForFlowers(level, pos);
        }
        if (tile.clientTicks % 10 == 0) {
            if (tile.beeLogic instanceof BeekeepingLogic logic) {
                logic.setClientFlowerPositions(tile.flowerPositions);
            }
            tile.beeLogic.doBeeFX();
        }
        if (tile.clientTicks % 50 == 0) {
            spawnPollenDust(level, pos, queenColor(queen));
        }
    }

    private static int queenColor(ItemStack queen) {
        IGenome genome = queen.get(ApicultureDataComponents.BEE_GENOME.type());
        int color = genome != null
                ? genome.getActiveAllele(BeeChromosomes.SPECIES).value().bodyColor()
                : WILDCARD_COLOR;
        return ARGB.opaque(color);
    }

    private void scanForFlowers(Level level, BlockPos pos) {
        flowerPositions.clear();
        for (BlockPos candidate : BlockPos.betweenClosed(
                pos.offset(-FLOWER_SCAN_RADIUS_XZ, -FLOWER_SCAN_RADIUS_Y, -FLOWER_SCAN_RADIUS_XZ),
                pos.offset(FLOWER_SCAN_RADIUS_XZ, FLOWER_SCAN_RADIUS_Y, FLOWER_SCAN_RADIUS_XZ))) {
            if (level.getBlockState(candidate).is(BlockTags.FLOWERS)) {
                flowerPositions.add(candidate.immutable());
            }
        }
    }

    private static void spawnPollenDust(Level level, BlockPos pos, int color) {
        double fxX = pos.getX() + 0.5;
        double fxY = pos.getY() + 0.25;
        double fxZ = pos.getZ() + 0.5;
        float distance = 0.6f;
        float spread = distance * (level.getRandom().nextFloat() - 0.5f);
        float upSpread = level.getRandom().nextFloat() * 6f / 16f;
        fxY += upSpread;

        DustParticleOptions dust = new DustParticleOptions(color, 1.0f);
        level.addParticle(dust, fxX - distance, fxY, fxZ + spread, 0, 0, 0);
        level.addParticle(dust, fxX + distance, fxY, fxZ + spread, 0, 0, 0);
        level.addParticle(dust, fxX + spread, fxY, fxZ - distance, 0, 0, 0);
        level.addParticle(dust, fxX + spread, fxY, fxZ + distance, 0, 0, 0);
    }

    @Override
    public Level level() {
        return getLevel();
    }

    @Override
    public BlockPos position() {
        return getBlockPos();
    }

    @Override
    public IBeeHousingInventory beeInventory() {
        return this;
    }

    @Override
    public IErrorLogic getErrorLogic() {
        return errorLogic;
    }

    @Override
    public TemperatureType temperature() {
        return climate.temperature();
    }

    @Override
    public HumidityType humidity() {
        return climate.humidity();
    }

    @Override
    public Holder<Biome> getBiome() {
        Level level = getLevel();
        if (level == null) {
            throw new IllegalStateException("Bee housing has no level");
        }
        return level.getBiome(getBlockPos());
    }

    @Override
    public Iterable<IBeeModifier> getBeeModifiers() {
        List<IBeeModifier> modifiers = new ArrayList<>();
        modifiers.add(hasFrames ? APIARY_MODIFIER : BEEHOUSE_MODIFIER);
        if (hasFrames) {
            for (int i = 0; i < SLOT_FRAME_COUNT; i++) {
                ItemStack stack = items.get(SLOT_FRAME_1 + i);
                if (!stack.isEmpty() && stack.getItem() instanceof IHiveFrame frame) {
                    modifiers.add(frame.getBeeModifier(stack));
                }
            }
        }
        return modifiers;
    }

    @Override
    public Iterable<IBeeListener> getBeeListeners() {
        return hasFrames ? List.of(apiaryBeeListener) : List.of();
    }

    public void wearOutFrames(int amount) {
        if (!hasFrames) {
            return;
        }
        ItemStack queenStack = getQueen();
        IGenome queenGenome = queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
        if (queenGenome == null) {
            return;
        }
        for (int i = 0; i < SLOT_FRAME_COUNT; i++) {
            int slot = SLOT_FRAME_1 + i;
            ItemStack frameStack = items.get(slot);
            if (!frameStack.isEmpty() && frameStack.getItem() instanceof IHiveFrame frame) {
                items.set(slot, frame.frameUsed(this, frameStack, queenGenome, amount));
            }
        }
        setChanged();
    }

    @Override
    public int getBlockLightValue() {
        Level level = getLevel();
        return level == null ? 0 : level.getMaxLocalRawBrightness(getBlockPos().above());
    }

    @Override
    public boolean canBlockSeeTheSky() {
        Level level = getLevel();
        return level != null && level.getBrightness(LightLayer.SKY, getBlockPos().above()) >= 10;
    }

    @Override
    public boolean isRaining() {
        Level level = getLevel();
        return level != null && level.isRaining() && level.getBrightness(LightLayer.SKY, getBlockPos().above()) > 7;
    }

    @Nullable
    @Override
    public GameProfile getOwner() {
        return null;
    }

    @Override
    public Vec3 getBeeFXCoordinates() {
        BlockPos pos = getBlockPos();
        return new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
    }

    @Override
    public ItemStack getQueen() {
        return getItem(SLOT_QUEEN);
    }

    @Override
    public ItemStack getDrone() {
        return getItem(SLOT_DRONE);
    }

    @Override
    public void setQueen(ItemStack stack) {
        setItem(SLOT_QUEEN, stack);
    }

    @Override
    public void setDrone(ItemStack stack) {
        setItem(SLOT_DRONE, stack);
    }

    @Override
    public boolean addProduct(ItemStack product) {
        for (int i = 0; i < SLOT_PRODUCT_COUNT; i++) {
            int slot = SLOT_PRODUCT_1 + i;
            ItemStack existing = items.get(slot);
            if (existing.isEmpty()) {
                items.set(slot, product.copy());
                setChanged();
                return true;
            }
            if (ItemStack.isSameItemSameComponents(existing, product)
                    && existing.getCount() + product.getCount() <= existing.getMaxStackSize()) {
                existing.grow(product.getCount());
                setChanged();
                return true;
            }
        }
        return false;
    }

    @Override
    public int getContainerSize() {
        return SLOT_COUNT;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (!hasFrames && slot >= SLOT_FRAME_1) {
            return false;
        }
        if (slot >= SLOT_FRAME_1) {
            return stack.getItem() instanceof IHiveFrame;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(items, slot, amount);
        if (!result.isEmpty()) {
            setChanged();
        }
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
        if (stack.getCount() > getMaxStackSize(stack)) {
            stack.setCount(getMaxStackSize(stack));
        }
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.reforestry." + translationKey);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ContainerBeeHousing(containerId, playerInventory, this);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return getBlockPos();
    }
}
