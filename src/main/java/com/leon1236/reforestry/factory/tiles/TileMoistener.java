package com.leon1236.reforestry.factory.tiles;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.fuels.FuelManager;
import com.leon1236.reforestry.api.fuels.MoistenerFuel;
import com.leon1236.reforestry.api.recipes.IMoistenerRecipe;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.recipes.RecipeUtils;
import com.leon1236.reforestry.core.render.TankRenderInfo;
import com.leon1236.reforestry.core.tiles.IRenderableTile;
import com.leon1236.reforestry.core.tiles.TileBase;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.gui.ContainerMoistener;

public class TileMoistener extends TileBase implements WorldlyContainer, IRenderableTile {
    public static final int SLOT_STASH_1 = 0;
    public static final int SLOT_STASH_COUNT = 6;
    public static final int SLOT_RESERVOIR_1 = 6;
    public static final int SLOT_RESERVOIR_COUNT = 3;
    public static final int SLOT_WORKING = 9;
    public static final int SLOT_PRODUCT = 10;
    public static final int SLOT_RESOURCE = 11;
    public static final int SLOT_COUNT = 12;
    public static final int ERROR_SLOT_COUNT = 4;

    private static final long TANK_CAPACITY_MB = 10000;
    public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);
    private static final long WATER_PER_TICK = FluidUnits.mbToDroplets(1);

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final FilteredFluidStorage resourceTank;
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;

    @Nullable
    private IMoistenerRecipe currentRecipe;
    private int burnTime;
    private int totalTime;
    private int productionTime;
    private int timePerItem;
    @Nullable
    private ItemStack currentProduct;
    @Nullable
    private ItemStack pendingProduct;

    private final ContainerData machineData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> burnTime;
                case 1 -> totalTime;
                case 2 -> productionTime;
                case 3 -> timePerItem;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> burnTime = value;
                case 1 -> totalTime = value;
                case 2 -> productionTime = value;
                case 3 -> timePerItem = value;
                default -> {
                }
            }
        }

        @Override
        public int getCount() {
            return 4;
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

    public TileMoistener(BlockPos pos, BlockState state) {
        super(FactoryTiles.MOISTENER.type(), pos, state);
        this.resourceTank = new FilteredFluidStorage(TANK_CAPACITY, FilteredFluidStorage.only(Fluids.WATER, Fluids.FLOWING_WATER), this::setChanged);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileMoistener tile) {
        if (tile.updateOnInterval(20)) {
            FluidContainerHelper.drainIntoTank(tile, SLOT_PRODUCT, tile.resourceTank);
        }
        tile.doWork();
        tile.syncErrors();
    }

    public FilteredFluidStorage getResourceTank() {
        return this.resourceTank;
    }

    @Override
    public TankRenderInfo getResourceTankInfo() {
        return TankRenderInfo.of(this.resourceTank);
    }

    @Override
    public TankRenderInfo getProductTankInfo() {
        return TankRenderInfo.EMPTY;
    }

    public ContainerData getMachineData() {
        return machineData;
    }

    public ContainerData getErrorData() {
        return errorData;
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

    private void doWork() {
        if (!(getLevel() instanceof ServerLevel)) {
            return;
        }

        int lightValue = getLevel().getLightEmission(getBlockPos().above());
        IErrorLogic errorLogic = getErrorLogic();

        boolean gloomy = lightValue <= 11;
        if (errorLogic.setCondition(!gloomy, ForestryError.NOT_DARK)) {
            return;
        }

        int speed;
        if (lightValue >= 9) {
            speed = 1;
        } else if (lightValue >= 7) {
            speed = 2;
        } else if (lightValue >= 5) {
            speed = 3;
        } else {
            speed = 4;
        }

        if (this.burnTime > 0 && this.pendingProduct == null) {
            boolean hasLiquid = this.resourceTank.getAmount() > 0;
            if (errorLogic.setCondition(!hasLiquid, ForestryError.NO_RESOURCE_LIQUID)) {
                return;
            }

            checkRecipe();

            if (this.currentRecipe == null) {
                return;
            }

            try (Transaction transaction = Transaction.openOuter()) {
                FluidVariant variant = this.resourceTank.getResource();
                if (variant.isBlank() || this.resourceTank.extract(variant, WATER_PER_TICK, transaction) <= 0) {
                    return;
                }
                transaction.commit();
            }

            this.burnTime -= speed;
            this.productionTime -= speed;

            if (this.productionTime <= 0) {
                this.pendingProduct = this.currentProduct == null ? null : this.currentProduct.copy();
                removeItem(SLOT_RESOURCE, 1);
                resetRecipe();
                tryAddPending();
            }
        } else if (this.pendingProduct != null) {
            tryAddPending();
        } else if (rotateWorkingSlot()) {
            checkRecipe();

            if (getItem(SLOT_WORKING).isEmpty()) {
                return;
            }

            MoistenerFuel fuel = findMoistenerFuel(getItem(SLOT_WORKING));
            if (fuel != null) {
                this.burnTime = this.totalTime = fuel.moistenerValue();
            }
        } else {
            rotateReservoir();
        }

        errorLogic.setCondition(this.currentRecipe == null, ForestryError.NO_RECIPE);
    }

    private boolean tryAddPending() {
        if (this.pendingProduct == null) {
            return false;
        }

        boolean added = InventoryUtil.tryAddStack(this, this.pendingProduct, SLOT_PRODUCT, 1, true);
        getErrorLogic().setCondition(!added, ForestryError.NO_SPACE_INVENTORY);

        if (added) {
            this.pendingProduct = null;
        }

        return added;
    }

    public void checkRecipe() {
        IMoistenerRecipe sameRec = null;
        if (getLevel() instanceof ServerLevel serverLevel) {
            sameRec = RecipeUtils.getMoistenerRecipe(serverLevel, getItem(SLOT_RESOURCE));
        }
        if (this.currentRecipe != sameRec) {
            this.currentRecipe = sameRec;
            resetRecipe();
        }

        getErrorLogic().setCondition(this.currentRecipe == null, ForestryError.NO_RECIPE);
    }

    private void resetRecipe() {
        if (this.currentRecipe == null) {
            this.currentProduct = null;
            this.productionTime = 0;
            this.timePerItem = 0;
        } else {
            this.currentProduct = this.currentRecipe.getProduct();
            this.productionTime = this.currentRecipe.getTimePerItem();
            this.timePerItem = this.currentRecipe.getTimePerItem();
        }
    }

    private int getFreeSlot(ItemStack deposit, int startSlot, int endSlot, boolean emptyOnly) {
        int slot = -1;

        for (int i = startSlot; i < endSlot; i++) {
            ItemStack slotStack = getItem(i);
            if (slotStack.isEmpty()) {
                if (slot < 0) {
                    slot = i;
                }
                continue;
            }

            if (emptyOnly) {
                continue;
            }

            if (!ItemStack.isSameItem(slotStack, deposit) || slotStack.getCount() >= slotStack.getMaxStackSize()) {
                continue;
            }

            slot = i;
        }

        return slot;
    }

    private int getFreeStashSlot(ItemStack deposit, boolean emptyOnly) {
        return getFreeSlot(deposit, SLOT_STASH_1, SLOT_RESERVOIR_1, emptyOnly);
    }

    private int getFreeReservoirSlot(ItemStack deposit) {
        return getFreeSlot(deposit, SLOT_RESERVOIR_1, SLOT_RESERVOIR_1 + SLOT_RESERVOIR_COUNT, false);
    }

    private int getNextResourceSlot(int startSlot, int endSlot) {
        int stage = -1;
        int resourceSlot = -1;

        for (int i = startSlot; i < endSlot; i++) {
            ItemStack slotStack = getItem(i);
            if (slotStack.isEmpty()) {
                continue;
            }

            MoistenerFuel fuel = findMoistenerFuel(slotStack);
            if (fuel == null) {
                continue;
            }

            if (stage < 0 || fuel.stage() < stage) {
                stage = fuel.stage();
                resourceSlot = i;
            }
        }

        return resourceSlot;
    }

    private boolean rotateWorkingSlot() {
        IErrorLogic errorLogic = getErrorLogic();

        if (!getItem(SLOT_WORKING).isEmpty()) {
            ItemStack deposit;
            MoistenerFuel fuel = findMoistenerFuel(getItem(SLOT_WORKING));
            if (fuel != null) {
                deposit = fuel.product().copy();
            } else {
                deposit = getItem(SLOT_WORKING).copy();
            }

            int targetSlot = getFreeReservoirSlot(deposit);
            if (errorLogic.setCondition(targetSlot < 0, ForestryError.NO_SPACE_INVENTORY)) {
                return false;
            }

            if (getItem(targetSlot).isEmpty()) {
                setItem(targetSlot, deposit);
            } else {
                getItem(targetSlot).grow(1);
            }

            removeItem(SLOT_WORKING, 1);
        }

        if (!getItem(SLOT_WORKING).isEmpty()) {
            return true;
        }

        int resourceSlot = getNextResourceSlot(SLOT_RESERVOIR_1, SLOT_RESERVOIR_1 + SLOT_RESERVOIR_COUNT);
        if (errorLogic.setCondition(resourceSlot < 0, ForestryError.NO_RESOURCE)) {
            return false;
        }

        setItem(SLOT_WORKING, removeItem(resourceSlot, 1));
        return true;
    }

    private void rotateReservoir() {
        ArrayList<Integer> slotsToShift = new ArrayList<>();

        for (int i = SLOT_RESERVOIR_1; i < SLOT_RESERVOIR_1 + SLOT_RESERVOIR_COUNT; i++) {
            if (getItem(i).isEmpty()) {
                continue;
            }

            if (findMoistenerFuel(getItem(i)) == null) {
                slotsToShift.add(i);
            }
        }

        int shiftedSlots = 0;
        for (int slot : slotsToShift) {
            ItemStack slotStack = getItem(slot);
            int targetSlot = getFreeStashSlot(slotStack, true);
            if (targetSlot < 0) {
                continue;
            }

            setItem(targetSlot, slotStack);
            setItem(slot, ItemStack.EMPTY);
            shiftedSlots++;
        }

        for (int i = 0; i < (!slotsToShift.isEmpty() ? shiftedSlots : 2); i++) {
            int resourceSlot = getNextResourceSlot(SLOT_STASH_1, SLOT_RESERVOIR_1);
            if (resourceSlot < 0) {
                break;
            }
            int targetSlot = getFreeReservoirSlot(getItem(resourceSlot));
            if (targetSlot < 0) {
                break;
            }
            if (getItem(targetSlot).isEmpty()) {
                setItem(targetSlot, getItem(resourceSlot));
                setItem(resourceSlot, ItemStack.EMPTY);
            } else {
                mergeIntoSlot(resourceSlot, targetSlot);
            }
        }
    }

    private void mergeIntoSlot(int sourceSlot, int targetSlot) {
        ItemStack source = getItem(sourceSlot);
        ItemStack target = getItem(targetSlot);
        if (!ItemStack.isSameItemSameComponents(source, target)) {
            return;
        }
        int space = target.getMaxStackSize() - target.getCount();
        int toMove = Math.min(space, source.getCount());
        if (toMove <= 0) {
            return;
        }
        target.grow(toMove);
        source.shrink(toMove);
        if (source.isEmpty()) {
            setItem(sourceSlot, ItemStack.EMPTY);
        }
    }

    @Nullable
    private static MoistenerFuel findMoistenerFuel(ItemStack stack) {
        if (FuelManager.moistenerResource == null || stack.isEmpty()) {
            return null;
        }
        for (var entry : FuelManager.moistenerResource.entrySet()) {
            if (ItemStack.isSameItem(entry.getKey(), stack)) {
                return entry.getValue();
            }
        }
        return null;
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
        if (stack.isEmpty()) {
            return false;
        }
        if (slot == SLOT_RESOURCE) {
            return getLevel() instanceof ServerLevel serverLevel && RecipeUtils.isMoistenerInput(serverLevel, stack);
        }
        if (slot >= SLOT_STASH_1 && slot < SLOT_RESERVOIR_1) {
            return findMoistenerFuel(stack) != null;
        }
        if (slot == SLOT_PRODUCT) {
            return FluidContainerHelper.isFilledContainer(stack)
                    && FluidContainerHelper.canTankAccept(this.resourceTank, FluidContainerHelper.fluidIn(stack));
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
        if (slot == SLOT_PRODUCT) {
            return true;
        }
        return slot >= SLOT_STASH_1 && slot < SLOT_RESERVOIR_1 + SLOT_RESERVOIR_COUNT
                && findMoistenerFuel(stack) == null;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("BurnTime", this.burnTime);
        output.putInt("TotalTime", this.totalTime);
        output.putInt("ProductionTime", this.productionTime);
        output.putInt("TimePerItem", this.timePerItem);
        ContainerHelper.saveAllItems(output, items);
        this.resourceTank.writeValue(output.child("ResourceTank"));
        if (this.pendingProduct != null) {
            output.store("PendingProduct", ItemStack.CODEC, this.pendingProduct);
        }
        if (this.currentProduct != null) {
            output.store("CurrentProduct", ItemStack.CODEC, this.currentProduct);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.burnTime = input.getIntOr("BurnTime", 0);
        this.totalTime = input.getIntOr("TotalTime", 0);
        this.productionTime = input.getIntOr("ProductionTime", 0);
        this.timePerItem = input.getIntOr("TimePerItem", 0);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        this.resourceTank.readValue(input.childOrEmpty("ResourceTank"));
        this.pendingProduct = input.read("PendingProduct", ItemStack.CODEC).orElse(null);
        this.currentProduct = input.read("CurrentProduct", ItemStack.CODEC).orElse(null);
        this.currentRecipe = null;
        checkRecipe();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ContainerMoistener(containerId, playerInventory, this);
    }
}
