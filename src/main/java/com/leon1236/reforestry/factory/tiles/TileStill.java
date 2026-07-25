package com.leon1236.reforestry.factory.tiles;

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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.recipes.IStillRecipe;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.render.TankRenderInfo;
import com.leon1236.reforestry.core.tiles.IRenderableTile;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.gui.ContainerStill;

public class TileStill extends TilePowered implements WorldlyContainer, IRenderableTile {
    public static final int SLOT_PRODUCT = 0;
    public static final int SLOT_RESOURCE = 1;
    public static final int SLOT_CAN = 2;
    public static final int SLOT_COUNT = 3;
    public static final int ERROR_SLOT_COUNT = 4;

    private static final int ENERGY_PER_RECIPE_TIME = 200;
    private static final long CAPACITY = 80000;
    private static final long MAX_RECEIVE = 1100;
    private static final long TANK_CAPACITY_MB = 10000;
    public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final MultiFluidTank tanks;
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;

    @Nullable
    private IStillRecipe currentRecipe;
    private FluidVariant bufferedVariant = FluidVariant.blank();
    private long bufferedAmount;

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

    public TileStill(BlockPos pos, BlockState state) {
        super(FactoryTiles.STILL.type(), pos, state, CAPACITY, MAX_RECEIVE);
        this.tanks = MultiFluidTank.builder(this::setChanged)
                .tank("Resource", TANK_CAPACITY, FilteredFluidStorage.only(ForestryFluids.BIOMASS.getFluid()))
                .tank("Product", TANK_CAPACITY, FilteredFluidStorage.only(ForestryFluids.BIO_ETHANOL.getFluid()))
                .build();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileStill tile) {
        if (tile.updateOnInterval(20)) {
            FluidContainerHelper.drainIntoTank(tile, SLOT_CAN, tile.tanks.tank("Resource"));
            FilteredFluidStorage productTank = tile.tanks.tank("Product");
            if (productTank.getAmount() > 0) {
                FluidContainerHelper.fillFromTank(tile, SLOT_RESOURCE, SLOT_PRODUCT, productTank);
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

    public FilteredFluidStorage getProductTank() {
        return this.tanks.tank("Product");
    }

    @Override
    public TankRenderInfo getResourceTankInfo() {
        return TankRenderInfo.of(getResourceTank());
    }

    @Override
    public TankRenderInfo getProductTankInfo() {
        return TankRenderInfo.of(getProductTank());
    }

    public ContainerData getProgressData() {
        return progressData;
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

    @Override
    public boolean hasWork() {
        checkRecipe();

        boolean hasRecipe = this.currentRecipe != null;
        boolean hasTankSpace = true;
        boolean hasLiquidResource = true;

        if (hasRecipe) {
            FilteredFluidStorage productTank = getProductTank();
            long outputAmount = this.currentRecipe.getOutputAmount();
            try (Transaction transaction = Transaction.openOuter()) {
                hasTankSpace = productTank.insert(this.currentRecipe.getOutputFluid(), outputAmount, transaction) == outputAmount;
            }

            if (this.bufferedAmount <= 0) {
                FilteredFluidStorage resourceTank = getResourceTank();
                long drainAmount = (long) this.currentRecipe.getCyclesPerUnit() * this.currentRecipe.getInputAmount();
                FluidVariant sourceVariant = this.bufferedAmount > 0 ? this.bufferedVariant : resourceTank.getResource();
                long sourceAmount = this.bufferedAmount > 0 ? this.bufferedAmount : resourceTank.getAmount();
                if (sourceVariant.isBlank() || sourceAmount < this.currentRecipe.getInputAmount()) {
                    hasLiquidResource = false;
                } else if (this.bufferedAmount <= 0) {
                    try (Transaction transaction = Transaction.openOuter()) {
                        long drained = resourceTank.extract(this.currentRecipe.getInputFluid(), drainAmount, transaction);
                        if (drained == drainAmount) {
                            transaction.commit();
                            this.bufferedVariant = this.currentRecipe.getInputFluid();
                            this.bufferedAmount = drained;
                            hasLiquidResource = true;
                        } else {
                            hasLiquidResource = false;
                        }
                    }
                }
            }
        }

        IErrorLogic errorLogic = getErrorLogic();
        errorLogic.setCondition(!hasRecipe, ForestryError.NO_RECIPE);
        errorLogic.setCondition(!hasTankSpace, ForestryError.NO_SPACE_TANK);
        errorLogic.setCondition(!hasLiquidResource, ForestryError.NO_RESOURCE_LIQUID);

        return hasRecipe && hasLiquidResource && hasTankSpace;
    }

    @Override
    protected boolean workCycle() {
        if (this.currentRecipe == null) {
            return false;
        }
        int cycles = this.currentRecipe.getCyclesPerUnit();
        long outputAmount = this.currentRecipe.getOutputAmount() * cycles;
        FilteredFluidStorage productTank = getProductTank();
        try (Transaction transaction = Transaction.openOuter()) {
            if (productTank.insert(this.currentRecipe.getOutputFluid(), outputAmount, transaction) != outputAmount) {
                return false;
            }
            transaction.commit();
        }
        this.bufferedVariant = FluidVariant.blank();
        this.bufferedAmount = 0;
        return true;
    }

    private void checkRecipe() {
        FluidVariant recipeVariant = this.bufferedAmount > 0 ? this.bufferedVariant : getResourceTank().getResource();
        long recipeAmount = this.bufferedAmount > 0 ? this.bufferedAmount : getResourceTank().getAmount();
        IStillRecipe matching = findRecipe(recipeVariant, recipeAmount);
        if (this.currentRecipe != matching) {
            this.currentRecipe = matching;
            if (matching != null) {
                int recipeTime = matching.getCyclesPerUnit();
                setEnergyPerWorkCycle(ENERGY_PER_RECIPE_TIME * recipeTime);
                setTicksPerWorkCycle(recipeTime);
            } else {
                setTicksPerWorkCycle(0);
            }
        }
    }

    @Nullable
    private IStillRecipe findRecipe(FluidVariant variant, long amount) {
        if (!(getLevel() instanceof ServerLevel serverLevel) || variant.isBlank() || amount <= 0) {
            return null;
        }
        for (RecipeHolder<?> holder : serverLevel.recipeAccess().getRecipes()) {
            if (holder.value() instanceof IStillRecipe recipe && recipe.matches(variant, amount)) {
                return recipe;
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
        if (slot == SLOT_RESOURCE) {
            return FluidContainerHelper.isEmptyContainer(stack);
        }
        if (slot == SLOT_CAN) {
            return FluidContainerHelper.isFilledContainer(stack)
                    && FluidContainerHelper.canTankAccept(getResourceTank(), FluidContainerHelper.fluidIn(stack));
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
        return slot == SLOT_PRODUCT;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
        this.tanks.writeValue(output.child("Tanks"));
        if (this.bufferedAmount > 0 && !this.bufferedVariant.isBlank()) {
            ValueOutput buffer = output.child("Buffer");
            buffer.store("Variant", FluidVariant.CODEC, this.bufferedVariant);
            buffer.putLong("Amount", this.bufferedAmount);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        this.tanks.readValue(input.childOrEmpty("Tanks"));
        ValueInput buffer = input.childOrEmpty("Buffer");
        this.bufferedVariant = buffer.read("Variant", FluidVariant.CODEC).orElse(FluidVariant.blank());
        this.bufferedAmount = buffer.getLongOr("Amount", 0L);
        this.currentRecipe = null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ContainerStill(containerId, playerInventory, this);
    }
}
