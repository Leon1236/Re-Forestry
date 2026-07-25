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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.fuels.FermenterFuel;
import com.leon1236.reforestry.api.fuels.FuelManager;
import com.leon1236.reforestry.api.recipes.IFermenterRecipe;
import com.leon1236.reforestry.api.recipes.IVariableFermentable;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.recipes.RecipeUtils;
import com.leon1236.reforestry.core.render.TankRenderInfo;
import com.leon1236.reforestry.core.tiles.IRenderableTile;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.gui.ContainerFermenter;

public class TileFermenter extends TilePowered implements WorldlyContainer, IRenderableTile {
    public static final int SLOT_RESOURCE = 0;
    public static final int SLOT_FUEL = 1;
    public static final int SLOT_CAN_OUTPUT = 2;
    public static final int SLOT_CAN_INPUT = 3;
    public static final int SLOT_INPUT = 4;
    public static final int SLOT_COUNT = 5;
    public static final int ERROR_SLOT_COUNT = 4;

    private static final long CAPACITY = 80000;
    private static final long MAX_RECEIVE = 2000;
    private static final int ENERGY_PER_WORK_CYCLE = 4200;
    private static final long TANK_CAPACITY_MB = 10000;
    public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final MultiFluidTank tanks;
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;

    @Nullable
    private IFermenterRecipe currentRecipe;
    private float currentResourceModifier = 1.0f;
    private int fermentationTime;
    private int fermentationTotalTime;
    private int fuelBurnTime;
    private int fuelTotalTime;
    private int fuelCurrentFerment;

    private final ContainerData machineData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> fuelBurnTime;
                case 1 -> fuelTotalTime;
                case 2 -> fermentationTime;
                case 3 -> fermentationTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> fuelBurnTime = value;
                case 1 -> fuelTotalTime = value;
                case 2 -> fermentationTime = value;
                case 3 -> fermentationTotalTime = value;
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

    public TileFermenter(BlockPos pos, BlockState state) {
        super(FactoryTiles.FERMENTER.type(), pos, state, CAPACITY, MAX_RECEIVE);
        setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
        this.tanks = MultiFluidTank.builder(this::setChanged)
                .tank("Resource", TANK_CAPACITY, FilteredFluidStorage.any())
                .tank("Product", TANK_CAPACITY, FilteredFluidStorage.only(ForestryFluids.BIOMASS.getFluid(), ForestryFluids.SHORT_MEAD.getFluid()))
                .build();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileFermenter tile) {
        if (tile.updateOnInterval(20)) {
            FluidContainerHelper.drainIntoTank(tile, SLOT_INPUT, tile.getResourceTank());
            FilteredFluidStorage productTank = tile.getProductTank();
            if (productTank.getAmount() > 0) {
                FluidContainerHelper.fillFromTank(tile, SLOT_CAN_INPUT, SLOT_CAN_OUTPUT, productTank);
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

    public ContainerData getMachineData() {
        return machineData;
    }

    public ContainerData getErrorData() {
        return errorData;
    }

    public int getBurnTimeRemainingScaled(int pixels) {
        if (this.fuelTotalTime == 0) {
            return 0;
        }
        return this.fuelBurnTime * pixels / this.fuelTotalTime;
    }

    public int getFermentationProgressScaled(int pixels) {
        if (this.fermentationTotalTime == 0) {
            return 0;
        }
        return this.fermentationTime * pixels / this.fermentationTotalTime;
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
        checkFuel();

        int fermented = Math.min(this.fermentationTime, this.fuelCurrentFerment);
        long fermentedDroplets = FluidUnits.mbToDroplets(fermented);

        boolean hasRecipe = this.currentRecipe != null;
        boolean hasFuel = this.fuelBurnTime > 0;
        boolean hasResource = this.fermentationTime > 0 || !getItem(SLOT_RESOURCE).isEmpty();
        boolean hasFluidResource = true;
        boolean hasFluidSpace = true;

        if (hasRecipe && fermented > 0) {
            FilteredFluidStorage resourceTank = getResourceTank();
            FluidVariant requiredFluid = this.currentRecipe.getInputFluid();
            try (Transaction transaction = Transaction.openOuter()) {
                long drained = resourceTank.extract(requiredFluid, fermentedDroplets, transaction);
                hasFluidResource = drained == fermentedDroplets;
            }

            int productMb = Math.round(fermented * this.currentRecipe.getModifier() * this.currentResourceModifier);
            Fluid output = this.currentRecipe.getOutput();
            FluidVariant outputVariant = FluidVariant.of(output);
            try (Transaction transaction = Transaction.openOuter()) {
                hasFluidSpace = getProductTank().insert(outputVariant, FluidUnits.mbToDroplets(productMb), transaction)
                        == FluidUnits.mbToDroplets(productMb);
            }
        } else if (hasRecipe) {
            hasFluidResource = false;
        }

        IErrorLogic errorLogic = getErrorLogic();
        errorLogic.setCondition(!hasRecipe, ForestryError.NO_RECIPE);
        errorLogic.setCondition(!hasFuel, ForestryError.NO_FUEL);
        errorLogic.setCondition(!hasResource, ForestryError.NO_RESOURCE);
        errorLogic.setCondition(!hasFluidResource, ForestryError.NO_RESOURCE_LIQUID);
        errorLogic.setCondition(!hasFluidSpace, ForestryError.NO_SPACE_TANK);

        return hasRecipe && hasFuel && hasResource && hasFluidResource && hasFluidSpace;
    }

    @Override
    protected boolean workCycle() {
        if (this.currentRecipe == null) {
            return false;
        }

        int fermented = Math.min(this.fermentationTime, this.fuelCurrentFerment);
        if (fermented <= 0) {
            return false;
        }

        long fermentedDroplets = FluidUnits.mbToDroplets(fermented);
        int productMb = Math.round(fermented * this.currentRecipe.getModifier() * this.currentResourceModifier);
        Fluid output = this.currentRecipe.getOutput();
        FluidVariant outputVariant = FluidVariant.of(output);
        long productDroplets = FluidUnits.mbToDroplets(productMb);

        FilteredFluidStorage resourceTank = getResourceTank();
        FilteredFluidStorage productTank = getProductTank();
        try (Transaction transaction = Transaction.openOuter()) {
            if (resourceTank.extract(this.currentRecipe.getInputFluid(), fermentedDroplets, transaction) != fermentedDroplets) {
                return false;
            }
            if (productTank.insert(outputVariant, productDroplets, transaction) != productDroplets) {
                return false;
            }
            transaction.commit();
        }

        this.fuelBurnTime--;
        this.fermentationTime -= fermented;

        if (this.fermentationTime <= 0) {
            this.currentRecipe = null;
            this.fermentationTotalTime = 0;
        }
        return true;
    }

    private void checkRecipe() {
        if (this.currentRecipe != null) {
            return;
        }

        ItemStack resource = getItem(SLOT_RESOURCE);
        FilteredFluidStorage resourceTank = getResourceTank();
        FluidVariant fluid = resourceTank.getResource();
        long fluidAmount = resourceTank.getAmount();

        if (!fluid.isBlank() && fluidAmount > 0 && !resource.isEmpty()) {
            if (getLevel() instanceof ServerLevel serverLevel) {
                this.currentRecipe = RecipeUtils.getFermenterRecipe(serverLevel, resource, fluid, fluidAmount);
            }
        }

        if (this.currentRecipe != null) {
            this.fermentationTotalTime = this.fermentationTime = this.currentRecipe.getFermentationValue();
            this.currentResourceModifier = determineResourceMod(resource);
            removeItem(SLOT_RESOURCE, 1);
        } else {
            this.fermentationTotalTime = this.fermentationTime = 0;
        }
    }

    private void checkFuel() {
        if (this.fuelBurnTime > 0) {
            return;
        }

        ItemStack fuel = getItem(SLOT_FUEL);
        if (fuel.isEmpty()) {
            return;
        }

        FermenterFuel fermenterFuel = findFermenterFuel(fuel);
        if (fermenterFuel == null) {
            return;
        }

        this.fuelBurnTime = this.fuelTotalTime = fermenterFuel.burnDuration();
        this.fuelCurrentFerment = fermenterFuel.fermentPerCycle();
        removeItem(SLOT_FUEL, 1);
    }

    @Nullable
    private static FermenterFuel findFermenterFuel(ItemStack stack) {
        if (FuelManager.fermenterFuel == null) {
            return null;
        }
        for (var entry : FuelManager.fermenterFuel.entrySet()) {
            if (ItemStack.isSameItem(entry.getKey(), stack)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static float determineResourceMod(ItemStack stack) {
        if (stack.getItem() instanceof IVariableFermentable fermentable) {
            return fermentable.getFermentationModifier(stack);
        }
        return 1.0f;
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
            return getLevel() instanceof ServerLevel serverLevel && RecipeUtils.isFermenterInput(serverLevel, stack);
        }
        if (slot == SLOT_FUEL) {
            return findFermenterFuel(stack) != null;
        }
        if (slot == SLOT_INPUT) {
            return FluidContainerHelper.isFilledContainer(stack)
                    && FluidContainerHelper.canTankAccept(getResourceTank(), FluidContainerHelper.fluidIn(stack));
        }
        if (slot == SLOT_CAN_INPUT) {
            return FluidContainerHelper.isEmptyContainer(stack);
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
        return slot == SLOT_CAN_OUTPUT;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("FermentationTime", this.fermentationTime);
        output.putInt("FermentationTotalTime", this.fermentationTotalTime);
        output.putInt("FuelBurnTime", this.fuelBurnTime);
        output.putInt("FuelTotalTime", this.fuelTotalTime);
        output.putInt("FuelCurrentFerment", this.fuelCurrentFerment);
        output.putFloat("ResourceModifier", this.currentResourceModifier);
        ContainerHelper.saveAllItems(output, items);
        this.tanks.writeValue(output.child("Tanks"));
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.fermentationTime = input.getIntOr("FermentationTime", 0);
        this.fermentationTotalTime = input.getIntOr("FermentationTotalTime", 0);
        this.fuelBurnTime = input.getIntOr("FuelBurnTime", 0);
        this.fuelTotalTime = input.getIntOr("FuelTotalTime", 0);
        this.fuelCurrentFerment = input.getIntOr("FuelCurrentFerment", 0);
        this.currentResourceModifier = input.getFloatOr("ResourceModifier", 1.0f);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        this.tanks.readValue(input.childOrEmpty("Tanks"));
        this.currentRecipe = null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ContainerFermenter(containerId, playerInventory, this);
    }
}
