package com.leon1236.reforestry.factory.tiles;

import java.util.List;

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
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.recipes.ISqueezerContainerRecipe;
import com.leon1236.reforestry.api.recipes.ISqueezerRecipe;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.items.FluidContainerContents;
import com.leon1236.reforestry.core.items.ItemFluidContainerForestry;
import com.leon1236.reforestry.core.recipes.RecipeUtils;
import com.leon1236.reforestry.core.render.TankRenderInfo;
import com.leon1236.reforestry.core.tiles.IRenderableTile;
import com.leon1236.reforestry.core.tiles.SocketedPoweredTile;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.gui.ContainerSqueezer;

public class TileSqueezer extends SocketedPoweredTile implements WorldlyContainer, IRenderableTile {
    public static final int SLOT_RESOURCE_1 = 0;
    public static final int SLOTS_RESOURCE_COUNT = 9;
    public static final int SLOT_REMNANT = 9;
    public static final int SLOT_CAN_INPUT = 10;
    public static final int SLOT_CAN_OUTPUT = 11;
    public static final int SLOT_COUNT = 12;
    public static final int ERROR_SLOT_COUNT = 4;

    private static final int TICKS_PER_RECIPE_TIME = 1;
    private static final int ENERGY_PER_WORK_CYCLE = 2000;
    private static final int ENERGY_PER_RECIPE_TIME = ENERGY_PER_WORK_CYCLE / 10;
    private static final long CAPACITY = 40000;
    private static final long MAX_RECEIVE = 1100;
    private static final long TANK_CAPACITY_MB = 10000;
    public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final MultiFluidTank tanks;
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;

    @Nullable
    private ISqueezerRecipe currentRecipe;

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

    public TileSqueezer(BlockPos pos, BlockState state) {
        super(FactoryTiles.SQUEEZER.type(), pos, state, CAPACITY, MAX_RECEIVE);
        this.tanks = MultiFluidTank.builder(this::setChanged)
                .tank("Product", TANK_CAPACITY, FilteredFluidStorage.any())
                .build();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileSqueezer tile) {
        if (tile.updateOnInterval(20)) {
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

    public FilteredFluidStorage getProductTank() {
        return this.tanks.tank("Product");
    }

    @Override
    public TankRenderInfo getResourceTankInfo() {
        return TankRenderInfo.EMPTY;
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

        boolean hasResources = hasResources();
        boolean hasRecipe = true;
        boolean canFill = true;
        boolean canAdd = true;

        if (hasResources) {
            hasRecipe = this.currentRecipe != null;
            if (hasRecipe) {
                if (this.currentRecipe instanceof ISqueezerContainerRecipe containerRecipe) {
                    canFill = canProcessContainerRecipe(containerRecipe, false);
                } else {
                    try (Transaction transaction = Transaction.openOuter()) {
                        canFill = getProductTank().insert(
                                this.currentRecipe.getOutputFluid(),
                                this.currentRecipe.getOutputAmount(),
                                transaction) == this.currentRecipe.getOutputAmount();
                    }
                }
                if (!this.currentRecipe.getRemnants().isEmpty()) {
                    canAdd = InventoryUtil.tryAddStack(this, this.currentRecipe.getRemnants(), SLOT_REMNANT, 1, true);
                }
            }
        }

        IErrorLogic errorLogic = getErrorLogic();
        errorLogic.setCondition(!hasResources, ForestryError.NO_RESOURCE);
        errorLogic.setCondition(!hasRecipe, ForestryError.NO_RECIPE);
        errorLogic.setCondition(!canFill, ForestryError.NO_SPACE_TANK);
        errorLogic.setCondition(!canAdd, ForestryError.NO_SPACE_INVENTORY);

        return hasResources && hasRecipe && canFill && canAdd;
    }

    @Override
    protected boolean workCycle() {
        if (this.currentRecipe == null) {
            return false;
        }
        if (this.currentRecipe instanceof ISqueezerContainerRecipe containerRecipe) {
            return processContainerRecipe(containerRecipe);
        }
        if (!InventoryUtil.consumeIngredients(this, SLOT_RESOURCE_1, SLOTS_RESOURCE_COUNT, this.currentRecipe.getInputs())) {
            return false;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            if (getProductTank().insert(
                    this.currentRecipe.getOutputFluid(),
                    this.currentRecipe.getOutputAmount(),
                    transaction) != this.currentRecipe.getOutputAmount()) {
                return false;
            }
            transaction.commit();
        }
        return addRemnant();
    }

    private boolean processContainerRecipe(ISqueezerContainerRecipe recipe) {
        int slot = findContainerSlot(recipe);
        if (slot < 0 || !canProcessContainerRecipe(recipe, true)) {
            return false;
        }
        return addRemnant();
    }

    private boolean canProcessContainerRecipe(ISqueezerContainerRecipe recipe, boolean doDrain) {
        int slot = findContainerSlot(recipe);
        if (slot < 0) {
            return false;
        }
        ItemStack stack = getItem(slot);
        FluidVariant variant = FluidContainerHelper.fluidIn(stack);
        long amount = FluidContainerContents.get(stack).amount();
        if (variant.isBlank() || amount <= 0) {
            return false;
        }
        if (!doDrain) {
            try (Transaction transaction = Transaction.openOuter()) {
                return getProductTank().insert(variant, amount, transaction) == amount;
            }
        }
        return FluidContainerHelper.drainFromSlotToTank(this, slot, getProductTank());
    }

    private int findContainerSlot(ISqueezerContainerRecipe recipe) {
        ItemStack empty = recipe.getEmptyContainer();
        for (int i = SLOT_RESOURCE_1; i < SLOT_RESOURCE_1 + SLOTS_RESOURCE_COUNT; i++) {
            ItemStack stack = getItem(i);
            if (FluidContainerHelper.isDrainableFilledContainer(stack) && ItemStack.isSameItem(empty, stack)) {
                return i;
            }
        }
        return -1;
    }

    private boolean addRemnant() {
        if (this.currentRecipe == null || this.currentRecipe.getRemnants().isEmpty()) {
            return true;
        }
        if (getLevel().getRandom().nextFloat() >= this.currentRecipe.getRemnantsChance() * getOutputMultiplier()) {
            return true;
        }
        return InventoryUtil.tryAddStack(this, this.currentRecipe.getRemnants().copy(), SLOT_REMNANT, 1, true);
    }

    private void checkRecipe() {
        ISqueezerRecipe matchingRecipe = null;

        if (hasResources()) {
            List<ItemStack> resources = InventoryUtil.getStacks(this, SLOT_RESOURCE_1, SLOTS_RESOURCE_COUNT);

            boolean containsSets = false;
            if (this.currentRecipe != null && !(this.currentRecipe instanceof ISqueezerContainerRecipe)) {
                containsSets = InventoryUtil.canConsumeIngredients(resources, this.currentRecipe.getInputs());
            }

            if (this.currentRecipe != null && containsSets) {
                matchingRecipe = this.currentRecipe;
            } else if (getLevel() instanceof ServerLevel serverLevel) {
                matchingRecipe = RecipeUtils.getSqueezerRecipe(serverLevel, resources);
            }

            if (matchingRecipe == null && getLevel() instanceof ServerLevel serverLevel) {
                for (ItemStack resource : resources) {
                    if (!resource.isEmpty()) {
                        matchingRecipe = RecipeUtils.getSqueezerContainerRecipe(serverLevel, resource);
                        if (matchingRecipe != null) {
                            break;
                        }
                    }
                }
            }
        }

        if (this.currentRecipe != matchingRecipe) {
            this.currentRecipe = matchingRecipe;
            if (matchingRecipe != null) {
                int recipeTime = matchingRecipe.getProcessingTime();
                setTicksPerWorkCycle(recipeTime * TICKS_PER_RECIPE_TIME);
                setEnergyPerWorkCycle(recipeTime * ENERGY_PER_RECIPE_TIME);
            }
        }

        getErrorLogic().setCondition(this.currentRecipe == null, ForestryError.NO_RECIPE);
    }

    private boolean hasResources() {
        return !InventoryUtil.isEmpty(this, SLOT_RESOURCE_1, SLOTS_RESOURCE_COUNT);
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
        if (slot == SLOT_CAN_INPUT) {
            return FluidContainerHelper.isEmptyContainer(stack);
        }
        if (slot >= SLOT_RESOURCE_1 && slot < SLOT_RESOURCE_1 + SLOTS_RESOURCE_COUNT) {
            if (FluidContainerHelper.isEmptyContainer(stack) && !(stack.getItem() instanceof ItemFluidContainerForestry)) {
                return false;
            }
            if (getLevel() instanceof ServerLevel serverLevel) {
                return RecipeUtils.isSqueezerIngredient(serverLevel, stack)
                        || RecipeUtils.getSqueezerContainerRecipe(serverLevel, stack) != null;
            }
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
        return InventoryUtil.contiguousSlots(SLOT_COUNT);
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
        return slot == SLOT_REMNANT || slot == SLOT_CAN_OUTPUT;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
        this.tanks.writeValue(output.child("Tanks"));
        saveSockets(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        this.tanks.readValue(input.childOrEmpty("Tanks"));
        this.currentRecipe = null;
        loadSockets(input);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ContainerSqueezer(containerId, playerInventory, this);
    }
}
