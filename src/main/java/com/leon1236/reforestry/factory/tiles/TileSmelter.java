package com.leon1236.reforestry.factory.tiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

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
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.recipes.ISmelterRecipe;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.recipes.IngredientStack;
import com.leon1236.reforestry.core.tiles.SocketedPoweredTile;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.gui.ContainerSmelter;
import com.leon1236.reforestry.factory.recipes.SmelterRecipe;

public class TileSmelter extends SocketedPoweredTile implements WorldlyContainer {
    public static final int SLOT_INPUT_1 = 0;
    public static final int SLOT_INPUT_COUNT = 9;
    public static final int SLOT_OUTPUT = 10;
    public static final int SLOT_COUNT = SLOT_OUTPUT + 1;
    public static final int ERROR_SLOT_COUNT = 4;

    private static final int TICKS_PER_RECIPE_TIME = 1;
    private static final int ENERGY_PER_WORK_CYCLE = 2000;
    private static final int ENERGY_PER_RECIPE_TIME = ENERGY_PER_WORK_CYCLE / 10;
    private static final long CAPACITY = 40000;
    private static final long MAX_RECEIVE = 1100;

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final SimpleContainer craftPreviewInventory = new SimpleContainer(1);
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;
    @Nullable
    private ISmelterRecipe currentRecipe;

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

    public TileSmelter(BlockPos pos, BlockState state) {
        super(FactoryTiles.SMELTER.type(), pos, state, CAPACITY, MAX_RECEIVE);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileSmelter tile) {
        tile.doWork();
        tile.syncErrors();
    }

    public ContainerData getProgressData() {
        return progressData;
    }

    public ContainerData getErrorData() {
        return errorData;
    }

    public Container getCraftPreviewInventory() {
        return craftPreviewInventory;
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
        boolean hasRecipe = currentRecipe != null;
        boolean canAdd = true;

        if (hasResources && hasRecipe) {
            ItemStack output = currentRecipe.getOutput();
            if (!output.isEmpty()) {
                canAdd = InventoryUtil.tryAddStack(this, output, SLOT_OUTPUT, 1, true);
            }
        }

        IErrorLogic errorLogic = getErrorLogic();
        errorLogic.setCondition(!hasResources, ForestryError.NO_RESOURCE);
        errorLogic.setCondition(!hasRecipe, ForestryError.NO_RECIPE);
        errorLogic.setCondition(!canAdd, ForestryError.NO_SPACE_INVENTORY);

        return hasResources && hasRecipe && canAdd;
    }

    @Override
    protected boolean workCycle() {
        if (currentRecipe == null) {
            return false;
        }
        if (!removeResources(currentRecipe.getInputs())) {
            return false;
        }
        InventoryUtil.tryAddStack(this, currentRecipe.getOutput(), SLOT_OUTPUT, 1, true);
        return true;
    }

    private boolean checkRecipe() {
        ISmelterRecipe matching = findRecipe();
        if (currentRecipe != matching) {
            currentRecipe = matching;
            if (matching != null) {
                craftPreviewInventory.setItem(0, matching.getOutput());
                int recipeTime = matching.getProcessingTime();
                setTicksPerWorkCycle(recipeTime * TICKS_PER_RECIPE_TIME);
                setEnergyPerWorkCycle(recipeTime * ENERGY_PER_RECIPE_TIME);
            } else {
                craftPreviewInventory.clearContent();
                setTicksPerWorkCycle(0);
            }
        }
        getErrorLogic().setCondition(currentRecipe == null, ForestryError.NO_RECIPE);
        return currentRecipe != null;
    }

    @Nullable
    private ISmelterRecipe findRecipe() {
        if (!(getLevel() instanceof ServerLevel serverLevel)) {
            return null;
        }
        List<ItemStack> resources = getResourceStacks();
        if (resources.isEmpty()) {
            return null;
        }
        var recipes = serverLevel.recipeAccess().getRecipes();
        for (RecipeHolder<?> holder : recipes) {
            if (holder.value() instanceof ISmelterRecipe recipe && SmelterRecipe.canAlloy(recipe, resources)) {
                return recipe;
            }
        }
        return null;
    }

    private boolean hasResources() {
        return !InventoryUtil.isEmpty(this, SLOT_INPUT_1, SLOT_INPUT_COUNT);
    }

    private List<ItemStack> getResourceStacks() {
        List<ItemStack> stacks = new ArrayList<>();
        for (int slot = SLOT_INPUT_1; slot < SLOT_INPUT_1 + SLOT_INPUT_COUNT; slot++) {
            ItemStack stack = getItem(slot);
            if (!stack.isEmpty()) {
                stacks.add(stack);
            }
        }
        return stacks;
    }

    private boolean removeResources(List<IngredientStack> requirements) {
        Map<Integer, Integer> toRemove = new HashMap<>();

        for (IngredientStack requirement : requirements) {
            int remaining = requirement.count();
            for (int slot = SLOT_INPUT_1; slot < SLOT_INPUT_1 + SLOT_INPUT_COUNT; slot++) {
                if (remaining <= 0) {
                    break;
                }
                ItemStack stack = getItem(slot);
                if (stack.isEmpty() || !requirement.ingredient().test(stack)) {
                    continue;
                }
                int take = Math.min(remaining, stack.getCount());
                toRemove.merge(slot, take, Integer::sum);
                remaining -= take;
            }
            if (remaining > 0) {
                return false;
            }
        }

        for (Map.Entry<Integer, Integer> entry : toRemove.entrySet()) {
            int slot = entry.getKey();
            int amount = entry.getValue();
            ItemStack stack = getItem(slot);
            if (stack.getCount() <= amount) {
                setItem(slot, ItemStack.EMPTY);
            } else {
                stack.shrink(amount);
            }
        }
        setChanged();
        return true;
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
        return slot >= SLOT_INPUT_1 && slot < SLOT_INPUT_1 + SLOT_INPUT_COUNT;
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
        return slot == SLOT_OUTPUT;
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
        return new ContainerSmelter(containerId, playerInventory, this);
    }
}
