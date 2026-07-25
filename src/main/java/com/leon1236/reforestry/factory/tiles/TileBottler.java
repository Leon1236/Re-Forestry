package com.leon1236.reforestry.factory.tiles;

import java.util.EnumMap;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper.FillStatus;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.render.TankRenderInfo;
import com.leon1236.reforestry.core.tiles.IRenderableTile;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.gui.ContainerBottler;
import com.leon1236.reforestry.factory.recipes.BottlerRecipe;

public class TileBottler extends TilePowered implements WorldlyContainer, IRenderableTile {
    public static final int SLOT_INPUT_FULL_CONTAINER = 0;
    public static final int SLOT_INPUT_EMPTY_CONTAINER = 1;
    public static final int SLOT_EMPTYING_PROCESSING = 2;
    public static final int SLOT_FILLING_PROCESSING = 3;
    public static final int SLOT_OUTPUT_EMPTY_CONTAINER = 4;
    public static final int SLOT_OUTPUT_FULL_CONTAINER = 5;
    public static final int SLOT_COUNT = 6;
    public static final int ERROR_SLOT_COUNT = 4;

    private static final int TICKS_PER_RECIPE_TIME = 5;
    private static final int ENERGY_PER_RECIPE_TIME = 1000;
    private static final long CAPACITY = 40000;
    private static final long MAX_RECEIVE = 1100;
    private static final long TANK_CAPACITY_MB = 10000;
    public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final MultiFluidTank tanks;
    private final EnumMap<Direction, Boolean> canDump = new EnumMap<>(Direction.class);
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;
    private boolean dumpingFluid;

    @Nullable
    private BottlerRecipe currentRecipe;

    private final ContainerData progressData = new ContainerData() {
        @Override
        public int get(int index) {
            return getProgressScaled(100);
        }

        @Override
        public void set(int index, int value) {
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    private final ContainerData recipeData = new ContainerData() {
        @Override
        public int get(int index) {
            return currentRecipe != null && currentRecipe.fillRecipe ? 1 : 0;
        }

        @Override
        public void set(int index, int value) {
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

    public TileBottler(BlockPos pos, BlockState state) {
        super(FactoryTiles.BOTTLER.type(), pos, state, CAPACITY, MAX_RECEIVE);
        this.tanks = MultiFluidTank.builder(this::setChanged)
                .tank("Resource", TANK_CAPACITY)
                .build();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileBottler tile) {
        if (tile.updateOnInterval(20)) {
            ItemStack leftProcessing = tile.getItem(SLOT_EMPTYING_PROCESSING);
            if (leftProcessing.isEmpty()) {
                ItemStack input = tile.getItem(SLOT_INPUT_FULL_CONTAINER);
                if (!input.isEmpty()) {
                    leftProcessing = tile.removeItem(SLOT_INPUT_FULL_CONTAINER, 1);
                    tile.setItem(SLOT_EMPTYING_PROCESSING, leftProcessing);
                }
            }
            ItemStack rightProcessing = tile.getItem(SLOT_FILLING_PROCESSING);
            if (rightProcessing.isEmpty()) {
                ItemStack input = tile.getItem(SLOT_INPUT_EMPTY_CONTAINER);
                if (!input.isEmpty()) {
                    rightProcessing = tile.removeItem(SLOT_INPUT_EMPTY_CONTAINER, 1);
                    tile.setItem(SLOT_FILLING_PROCESSING, rightProcessing);
                }
            }
        }

        if (tile.canDump()) {
            if (tile.dumpingFluid || tile.updateOnInterval(20)) {
                tile.dumpingFluid = tile.dumpFluid();
            }
        }

        tile.doWork();
        tile.syncErrors();
    }

    public MultiFluidTank getTankManager() {
        return this.tanks;
    }

    public FilteredFluidStorage getResourceTank() {
        return this.tanks.tank("Resource");
    }

    @Override
    public TankRenderInfo getResourceTankInfo() {
        return TankRenderInfo.of(getResourceTank());
    }

    @Override
    public TankRenderInfo getProductTankInfo() {
        return TankRenderInfo.EMPTY;
    }

    public ContainerData getProgressData() {
        return progressData;
    }

    public ContainerData getRecipeData() {
        return recipeData;
    }

    public ContainerData getErrorData() {
        return errorData;
    }

    public boolean isFillRecipe() {
        return this.currentRecipe != null && this.currentRecipe.fillRecipe;
    }

    private void syncErrors() {
        syncedErrorCount = 0;
        for (var error : getErrorLogic().getErrors()) {
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

    private boolean canDump() {
        FilteredFluidStorage tank = getResourceTank();
        FluidVariant variant = tank.getResource();
        if (variant.isBlank() || tank.getAmount() <= 0) {
            this.canDump.clear();
            return false;
        }
        Level level = getLevel();
        if (level == null) {
            return false;
        }
        this.canDump.clear();
        for (Direction facing : Direction.values()) {
            this.canDump.put(facing, FluidContainerHelper.canAcceptFluid(level, worldPosition.relative(facing), facing.getOpposite(), variant));
        }
        for (Direction facing : Direction.values()) {
            if (Boolean.TRUE.equals(this.canDump.get(facing))) {
                return true;
            }
        }
        return false;
    }

    private boolean dumpFluid() {
        FilteredFluidStorage tank = getResourceTank();
        if (tank.getAmount() <= 0) {
            return false;
        }
        Level level = getLevel();
        if (level == null) {
            return false;
        }
        for (Direction facing : Direction.values()) {
            if (!Boolean.TRUE.equals(this.canDump.get(facing))) {
                continue;
            }
            Storage<FluidVariant> destination = FluidStorage.SIDED.find(level, worldPosition.relative(facing), facing.getOpposite());
            if (destination == null) {
                continue;
            }
            try (Transaction transaction = Transaction.openOuter()) {
                long moved = StorageUtil.move(tank, destination, variant -> true, FluidConstants.BUCKET / 20, transaction);
                if (moved > 0) {
                    transaction.commit();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasWork() {
        FillStatus emptyStatus;
        FillStatus fillStatus;
        IErrorLogic errorLogic = getErrorLogic();
        errorLogic.clearErrors();

        checkEmptyRecipe();
        if (this.currentRecipe != null && !this.currentRecipe.fillRecipe) {
            emptyStatus = FluidContainerHelper.drainContainers(getResourceTank(), this, SLOT_EMPTYING_PROCESSING,
                    SLOT_OUTPUT_EMPTY_CONTAINER, false);
        } else {
            emptyStatus = null;
        }

        if (emptyStatus != FillStatus.SUCCESS) {
            checkFillRecipe();
            if (this.currentRecipe == null) {
                return false;
            }
            fillStatus = FluidContainerHelper.fillContainers(getResourceTank(), this, SLOT_FILLING_PROCESSING,
                    SLOT_OUTPUT_FULL_CONTAINER, this.currentRecipe.fluid, false);
        } else {
            return true;
        }

        if (fillStatus == FillStatus.SUCCESS) {
            return true;
        }

        errorLogic.setCondition(fillStatus == FillStatus.NO_FLUID, ForestryError.NO_RESOURCE_LIQUID);
        errorLogic.setCondition(fillStatus == FillStatus.NO_SPACE, ForestryError.NO_SPACE_INVENTORY);
        errorLogic.setCondition(emptyStatus == FillStatus.NO_SPACE_FLUID, ForestryError.NO_SPACE_TANK);
        if (emptyStatus == FillStatus.INVALID_INPUT || fillStatus == FillStatus.INVALID_INPUT || errorLogic.hasErrors()) {
            this.currentRecipe = null;
            return false;
        }
        return true;
    }

    @Override
    protected boolean workCycle() {
        if (this.currentRecipe == null) {
            return true;
        }
        FillStatus status;
        if (this.currentRecipe.fillRecipe) {
            status = FluidContainerHelper.fillContainers(getResourceTank(), this, SLOT_FILLING_PROCESSING,
                    SLOT_OUTPUT_FULL_CONTAINER, this.currentRecipe.fluid, true);
        } else {
            status = FluidContainerHelper.drainContainers(getResourceTank(), this, SLOT_EMPTYING_PROCESSING,
                    SLOT_OUTPUT_EMPTY_CONTAINER, true);
        }
        if (status == FillStatus.SUCCESS) {
            this.currentRecipe = null;
            return true;
        }
        return false;
    }

    private void checkFillRecipe() {
        ItemStack emptyCan = getItem(SLOT_FILLING_PROCESSING);
        if (emptyCan.isEmpty()) {
            return;
        }
        FilteredFluidStorage resourceTank = getResourceTank();
        FluidVariant resource = resourceTank.getResource();
        if (resource.isBlank() || resourceTank.getAmount() <= 0) {
            return;
        }
        if (this.currentRecipe == null || !this.currentRecipe.matchEmpty(emptyCan, resource)) {
            this.currentRecipe = BottlerRecipe.createFillingRecipe(resource.getFluid(), emptyCan);
            if (this.currentRecipe != null) {
                applyRecipeTiming(resource, this.currentRecipe.fluidAmount, true);
            }
        }
    }

    private void checkEmptyRecipe() {
        ItemStack filledCan = getItem(SLOT_EMPTYING_PROCESSING);
        if (filledCan.isEmpty()) {
            return;
        }
        if (this.currentRecipe == null || !this.currentRecipe.matchFilled(filledCan) && !this.currentRecipe.fillRecipe) {
            this.currentRecipe = BottlerRecipe.createEmptyingRecipe(filledCan);
            if (this.currentRecipe != null) {
                applyRecipeTiming(this.currentRecipe.fluid, this.currentRecipe.fluidAmount, false);
            }
        }
    }

    private void applyRecipeTiming(FluidVariant variant, long fillAmount, boolean costsEnergy) {
        Level level = getLevel();
        int viscosity = FluidVariantAttributes.getViscosity(variant, level);
        float viscosityMultiplier = viscosity / 1000.0f;
        viscosityMultiplier = (viscosityMultiplier - 1f) / 20f + 1f;
        long scaledAmount = costsEnergy ? Math.min(fillAmount, getResourceTank().getAmount()) : fillAmount;
        float fillTime = scaledAmount / (float) FluidConstants.BUCKET;
        fillTime *= viscosityMultiplier;
        setTicksPerWorkCycle(Math.max(1, Math.round(fillTime * TICKS_PER_RECIPE_TIME)));
        setEnergyPerWorkCycle(costsEnergy ? Math.max(1, Math.round(fillTime * ENERGY_PER_RECIPE_TIME)) : 0);
    }

    public void onProcessingSlotTake(int slotIndex) {
        if (slotIndex == SLOT_EMPTYING_PROCESSING) {
            if (this.currentRecipe != null && !this.currentRecipe.fillRecipe) {
                this.currentRecipe = null;
                setTicksPerWorkCycle(0);
            }
        } else if (slotIndex == SLOT_FILLING_PROCESSING) {
            if (this.currentRecipe != null && this.currentRecipe.fillRecipe) {
                this.currentRecipe = null;
                setTicksPerWorkCycle(0);
            }
        }
    }

    @Override
    public int getContainerSize() {
        return SLOT_COUNT;
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
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == SLOT_INPUT_EMPTY_CONTAINER) {
            return FluidContainerHelper.isFillableContainerWithRoom(stack);
        }
        if (slot == SLOT_INPUT_FULL_CONTAINER) {
            return FluidContainerHelper.isFilledContainer(stack);
        }
        return false;
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
    public int[] getSlotsForFace(Direction direction) {
        return InventoryUtil.contiguousSlots(SLOT_COUNT);
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
        return slot == SLOT_OUTPUT_EMPTY_CONTAINER || slot == SLOT_OUTPUT_FULL_CONTAINER;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
        this.tanks.writeValue(output.child("Tanks"));
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        this.tanks.readValue(input.childOrEmpty("Tanks"));
        this.currentRecipe = null;
        checkEmptyRecipe();
        checkFillRecipe();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ContainerBottler(containerId, playerInventory, this);
    }
}
