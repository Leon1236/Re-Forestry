package com.leon1236.reforestry.factory.tiles;

import java.util.ArrayDeque;

import org.jetbrains.annotations.Nullable;

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
import com.leon1236.reforestry.api.recipes.ICentrifugeRecipe;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.tiles.SocketedPoweredTile;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.gui.ContainerCentrifuge;

public class TileCentrifuge extends SocketedPoweredTile implements WorldlyContainer {
    public static final int SLOT_RESOURCE = 0;
    public static final int SLOT_PRODUCT_1 = 1;
    public static final int SLOT_PRODUCT_COUNT = 9;
    public static final int SLOT_COUNT = SLOT_PRODUCT_1 + SLOT_PRODUCT_COUNT;
    public static final int ERROR_SLOT_COUNT = 4;

    private static final long CAPACITY = 40000;
    private static final long MAX_RECEIVE = 800;
    private static final int ENERGY_PER_WORK_CYCLE = 3200;
    private static final int ENERGY_PER_RECIPE_TIME = ENERGY_PER_WORK_CYCLE / 20;

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final ArrayDeque<ItemStack> pendingProducts = new ArrayDeque<>();
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;
    @Nullable
    private ICentrifugeRecipe currentRecipe;

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

    public TileCentrifuge(BlockPos pos, BlockState state) {
        super(FactoryTiles.CENTRIFUGE.type(), pos, state, CAPACITY, MAX_RECEIVE);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileCentrifuge tile) {
        tile.doWork();
        tile.syncErrors();
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
        if (!pendingProducts.isEmpty()) {
            return true;
        }

        IErrorLogic errorLogic = getErrorLogic();
        ItemStack resource = getItem(SLOT_RESOURCE);
        boolean hasResource = !resource.isEmpty();
        errorLogic.setCondition(!hasResource, ForestryError.NO_RESOURCE);
        if (!hasResource) {
            currentRecipe = null;
            return false;
        }

        checkRecipe(resource);
        errorLogic.setCondition(currentRecipe == null, ForestryError.NO_RECIPE);
        return currentRecipe != null;
    }

    @Override
    protected boolean workCycle() {
        if (tryAddPending()) {
            return true;
        }
        if (!pendingProducts.isEmpty()) {
            return false;
        }
        if (currentRecipe == null || getLevel() == null) {
            return false;
        }

        pendingProducts.addAll(currentRecipe.getProducts(getLevel().getRandom(), getOutputMultiplier()));
        removeItem(SLOT_RESOURCE, 1);
        return true;
    }

    private void checkRecipe(ItemStack resource) {
        ICentrifugeRecipe matching = findRecipe(resource);
        if (currentRecipe != matching) {
            currentRecipe = matching;
            if (matching != null) {
                int recipeTime = matching.getProcessingTime();
                setTicksPerWorkCycle(recipeTime);
                setEnergyPerWorkCycle(recipeTime * ENERGY_PER_RECIPE_TIME);
            }
        }
    }

    @Nullable
    private ICentrifugeRecipe findRecipe(ItemStack stack) {
        if (stack.isEmpty() || !(getLevel() instanceof ServerLevel serverLevel)) {
            return null;
        }
        var recipes = serverLevel.recipeAccess().getRecipes();
        for (RecipeHolder<?> holder : recipes) {
            if (holder.value() instanceof ICentrifugeRecipe recipe && recipe.getInput().test(stack)) {
                return recipe;
            }
        }
        return null;
    }

    private boolean tryAddPending() {
        if (pendingProducts.isEmpty()) {
            return false;
        }

        ItemStack next = pendingProducts.peekFirst();
        boolean added = InventoryUtil.tryAddStack(this, next, SLOT_PRODUCT_1, SLOT_PRODUCT_COUNT, true);
        if (added) {
            pendingProducts.removeFirst();
        }

        getErrorLogic().setCondition(!added, ForestryError.NO_SPACE_INVENTORY);
        return added;
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
        return slot == SLOT_RESOURCE;
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
        return slot >= SLOT_PRODUCT_1 && slot < SLOT_PRODUCT_1 + SLOT_PRODUCT_COUNT;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
        saveSockets(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        loadSockets(input);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ContainerCentrifuge(containerId, playerInventory, this);
    }
}
