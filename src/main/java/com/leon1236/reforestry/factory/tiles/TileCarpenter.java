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
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.recipes.ICarpenterRecipe;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.recipes.RecipeFluidAmount;
import com.leon1236.reforestry.core.recipes.RecipeUtils;
import com.leon1236.reforestry.core.render.TankRenderInfo;
import com.leon1236.reforestry.core.tiles.IRenderableTile;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.gui.ContainerCarpenter;

public class TileCarpenter extends TilePowered implements WorldlyContainer, IRenderableTile {
    public static final int SLOT_CRAFTING_1 = 0;
    public static final int SLOT_CRAFTING_COUNT = 9;
    public static final int SLOT_BOX = 9;
    public static final int SLOT_PRODUCT = 10;
    public static final int SLOT_CAN_INPUT = 11;
    public static final int SLOT_STORAGE_1 = 12;
    public static final int SLOT_STORAGE_COUNT = 18;
    public static final int SLOT_COUNT = SLOT_STORAGE_1 + SLOT_STORAGE_COUNT;
    public static final int ERROR_SLOT_COUNT = 4;

    private static final int TICKS_PER_RECIPE_TIME = 1;
    private static final int ENERGY_PER_WORK_CYCLE = 2040;
    private static final int ENERGY_PER_RECIPE_TIME = ENERGY_PER_WORK_CYCLE / 10;
    private static final long CAPACITY = 40000;
    private static final long MAX_RECEIVE = 1100;
    private static final long TANK_CAPACITY_MB = 10000;
    public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final SimpleContainer craftPreview = new SimpleContainer(1);
    private final MultiFluidTank tanks;
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;

    @Nullable
    private ICarpenterRecipe currentRecipe;

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

    public TileCarpenter(BlockPos pos, BlockState state) {
        super(FactoryTiles.CARPENTER.type(), pos, state, CAPACITY, MAX_RECEIVE);
        this.tanks = MultiFluidTank.builder(this::setChanged)
                .tank("Resource", TANK_CAPACITY)
                .build();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileCarpenter tile) {
        if (tile.updateOnInterval(20)) {
            FluidContainerHelper.drainContainers(tile.getResourceTank(), tile, SLOT_CAN_INPUT, SLOT_STORAGE_1, true);
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

    public Container getCraftPreviewInventory() {
        return this.craftPreview;
    }

    public ContainerData getProgressData() {
        return progressData;
    }

    public ContainerData getErrorData() {
        return errorData;
    }

    public void checkRecipe() {
        Level level = getLevel();
        if (level == null || level.isClientSide()) {
            return;
        }

        FilteredFluidStorage tank = getResourceTank();
        FluidVariant fluid = tank.getResource();
        long fluidAmount = tank.getAmount();
        ItemStack box = getItem(SLOT_BOX);

        if (this.currentRecipe == null || !this.currentRecipe.matches(fluid, fluidAmount, box, this, level)) {
            ICarpenterRecipe recipe = RecipeUtils.getCarpenterRecipe((ServerLevel) level, fluid, fluidAmount, box, this);
            this.currentRecipe = recipe;

            if (recipe != null) {
                int recipeTime = recipe.getPackagingTime();
                setTicksPerWorkCycle(recipeTime * TICKS_PER_RECIPE_TIME);
                setEnergyPerWorkCycle(recipeTime * ENERGY_PER_RECIPE_TIME);
                this.craftPreview.setItem(0, recipe.getResultStack());
            } else {
                this.craftPreview.setItem(0, ItemStack.EMPTY);
            }
            setChanged();
        }
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
        if (updateOnInterval(20)) {
            checkRecipe();
        }

        IErrorLogic errorLogic = getErrorLogic();
        errorLogic.clearErrors();

        boolean hasRecipe = this.currentRecipe != null;
        boolean hasLiquidResources = true;
        boolean hasItemResources = true;
        boolean canAdd = true;

        if (hasRecipe) {
            hasLiquidResources = removeLiquidResources(false);
            hasItemResources = removeItemResources(false);

            ItemStack pendingProduct = this.currentRecipe.getResultStack();
            canAdd = InventoryUtil.addStack(this, pendingProduct, SLOT_PRODUCT, 1, false) == pendingProduct.getCount();
        }

        errorLogic.setCondition(!hasRecipe, ForestryError.NO_RECIPE);
        errorLogic.setCondition(!hasLiquidResources, ForestryError.NO_RESOURCE_LIQUID);
        errorLogic.setCondition(!hasItemResources, ForestryError.NO_RESOURCE_INVENTORY);
        errorLogic.setCondition(!canAdd, ForestryError.NO_SPACE_INVENTORY);

        return hasRecipe && hasItemResources && hasLiquidResources && canAdd;
    }

    @Override
    protected boolean workCycle() {
        if (!removeLiquidResources(true) || !removeItemResources(true)) {
            return false;
        }

        if (this.currentRecipe != null) {
            ItemStack pendingProduct = this.currentRecipe.getResultStack();
            InventoryUtil.tryAddStack(this, pendingProduct, SLOT_PRODUCT, 1, true);
        }
        return true;
    }

    private boolean removeLiquidResources(boolean doRemove) {
        if (this.currentRecipe == null) {
            return true;
        }

        FluidVariant requiredFluid = this.currentRecipe.getInputFluid();
        long requiredAmount = this.currentRecipe.getInputFluidAmount();
        if (requiredAmount <= 0 || requiredFluid.isBlank()) {
            return true;
        }

        FilteredFluidStorage tank = getResourceTank();
        if (tank.getAmount() < requiredAmount || !tank.getResource().equals(requiredFluid)) {
            return false;
        }

        if (doRemove) {
            try (Transaction transaction = Transaction.openOuter()) {
                tank.extract(requiredFluid, requiredAmount, transaction);
                transaction.commit();
            }
        }
        return true;
    }

    private boolean removeItemResources(boolean doRemove) {
        if (this.currentRecipe == null) {
            return true;
        }

        Ingredient requiredBox = this.currentRecipe.getBox();
        if (requiredBox != null) {
            ItemStack box = getItem(SLOT_BOX);
            if (box.isEmpty()) {
                return false;
            }
            if (doRemove) {
                removeItem(SLOT_BOX, 1);
            }
        }

        var ingredients = this.currentRecipe.getCraftingGridRecipe().placementInfo().ingredients();
        if (doRemove) {
            return InventoryUtil.consumeIngredients(this, SLOT_STORAGE_1, SLOT_STORAGE_COUNT, ingredients);
        }
        return InventoryUtil.canConsumeIngredients(
                InventoryUtil.getStacks(this, SLOT_STORAGE_1, SLOT_STORAGE_COUNT), ingredients);
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
        Level level = getLevel();
        if (slot == SLOT_CAN_INPUT) {
            return FluidContainerHelper.isFilledContainer(stack);
        }
        if (slot == SLOT_BOX) {
            return level instanceof ServerLevel serverLevel && RecipeUtils.isCarpenterBox(serverLevel, stack);
        }
        if (slot >= SLOT_CRAFTING_1 && slot < SLOT_CRAFTING_1 + SLOT_CRAFTING_COUNT) {
            return true;
        }
        if (slot >= SLOT_STORAGE_1 && slot < SLOT_STORAGE_1 + SLOT_STORAGE_COUNT) {
            return true;
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
        return new int[] {SLOT_CAN_INPUT, SLOT_PRODUCT, SLOT_STORAGE_1, SLOT_STORAGE_1 + 1, SLOT_STORAGE_1 + 2,
                SLOT_STORAGE_1 + 3, SLOT_STORAGE_1 + 4, SLOT_STORAGE_1 + 5, SLOT_STORAGE_1 + 6, SLOT_STORAGE_1 + 7,
                SLOT_STORAGE_1 + 8, SLOT_STORAGE_1 + 9, SLOT_STORAGE_1 + 10, SLOT_STORAGE_1 + 11, SLOT_STORAGE_1 + 12,
                SLOT_STORAGE_1 + 13, SLOT_STORAGE_1 + 14, SLOT_STORAGE_1 + 15, SLOT_STORAGE_1 + 16,
                SLOT_STORAGE_1 + 17};
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return slot == SLOT_CAN_INPUT || (slot >= SLOT_STORAGE_1 && slot < SLOT_STORAGE_1 + SLOT_STORAGE_COUNT);
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
        output.store("CraftPreview", ItemStack.CODEC, this.craftPreview.getItem(0));
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        this.tanks.readValue(input.childOrEmpty("Tanks"));
        this.craftPreview.setItem(0, input.read("CraftPreview", ItemStack.CODEC).orElse(ItemStack.EMPTY));
        this.currentRecipe = null;
        checkRecipe();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ContainerCarpenter(containerId, playerInventory, this);
    }
}
