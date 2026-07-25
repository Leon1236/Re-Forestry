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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.api.items.ICraftingPlan;
import com.leon1236.reforestry.api.predicates.FabricatorMoltenFluids;
import com.leon1236.reforestry.api.recipes.IFabricatorRecipe;
import com.leon1236.reforestry.api.recipes.IFabricatorSmeltingRecipe;
import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.MultiFluidTank;
import com.leon1236.reforestry.core.inventory.InventoryUtil;
import com.leon1236.reforestry.core.recipes.RecipeUtils;
import com.leon1236.reforestry.core.tiles.TilePowered;
import com.leon1236.reforestry.factory.features.FactoryTiles;
import com.leon1236.reforestry.factory.gui.ContainerFabricator;

public class TileFabricator extends TilePowered implements WorldlyContainer {
    public static final int MAX_HEAT = 5000;

    public static final int SLOT_METAL = 0;
    public static final int SLOT_PLAN = 1;
    public static final int SLOT_RESULT = 2;
    public static final int SLOT_INVENTORY_1 = 3;
    public static final int SLOT_INVENTORY_COUNT = 18;
    public static final int SLOT_COUNT = SLOT_INVENTORY_1 + SLOT_INVENTORY_COUNT;
    public static final int ERROR_SLOT_COUNT = 4;

    private static final long CAPACITY = 3300;
    private static final long MAX_RECEIVE = 1100;
    private static final int ENERGY_PER_WORK_CYCLE = 200;
    private static final long TANK_CAPACITY_MB = 8000;
    public static final long TANK_CAPACITY = FluidUnits.mbToDroplets(TANK_CAPACITY_MB);
    private static final long SOLIDIFY_DRAIN = FluidUnits.mbToDroplets(5);

    private final NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final NonNullList<ItemStack> craftItems = NonNullList.withSize(9, ItemStack.EMPTY);
    private final Container craftingInventory = new Container() {
        @Override
        public int getContainerSize() {
            return craftItems.size();
        }

        @Override
        public boolean isEmpty() {
            for (ItemStack stack : craftItems) {
                if (!stack.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public ItemStack getItem(int slot) {
            return craftItems.get(slot);
        }

        @Override
        public ItemStack removeItem(int slot, int amount) {
            ItemStack result = ContainerHelper.removeItem(craftItems, slot, amount);
            if (!result.isEmpty()) {
                setChanged();
            }
            return result;
        }

        @Override
        public ItemStack removeItemNoUpdate(int slot) {
            return ContainerHelper.takeItem(craftItems, slot);
        }

        @Override
        public void setItem(int slot, ItemStack stack) {
            craftItems.set(slot, stack);
            if (stack.getCount() > getMaxStackSize()) {
                stack.setCount(getMaxStackSize());
            }
            setChanged();
        }

        @Override
        public void setChanged() {
            TileFabricator.this.setChanged();
        }

        @Override
        public boolean stillValid(Player player) {
            return Container.stillValidBlockEntity(TileFabricator.this, player);
        }

        @Override
        public void clearContent() {
            craftItems.clear();
        }
    };
    private final MultiFluidTank tanks;
    private final int[] syncedErrorIds = new int[ERROR_SLOT_COUNT];
    private int syncedErrorCount;

    private int heat;
    private int meltingPoint;

    private final ContainerData machineData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> heat;
                case 1 -> getMeltingPoint();
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> heat = value;
                case 1 -> meltingPoint = value;
                default -> {
                }
            }
        }

        @Override
        public int getCount() {
            return 2;
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

    public TileFabricator(BlockPos pos, BlockState state) {
        super(FactoryTiles.FABRICATOR.type(), pos, state, CAPACITY, MAX_RECEIVE);
        setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
        this.tanks = MultiFluidTank.builder(this::setChanged)
                .tank("Molten", TANK_CAPACITY, FabricatorMoltenFluids::test)
                .build();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileFabricator tile) {
        tile.tickSmelting();
        tile.doWork();
        tile.syncErrors();
    }

    public MultiFluidTank getTankManager() {
        return this.tanks;
    }

    public FilteredFluidStorage getMoltenTank() {
        return this.tanks.tank("Molten");
    }

    public Container getCraftingInventory() {
        return this.craftingInventory;
    }

    public ContainerData getMachineData() {
        return machineData;
    }

    public ContainerData getErrorData() {
        return errorData;
    }

    public int getHeat() {
        return this.heat;
    }

    public int getHeatScaled(int pixels) {
        return this.heat * pixels / MAX_HEAT;
    }

    public int getMeltingPoint() {
        ItemStack metal = getItem(SLOT_METAL);
        if (!metal.isEmpty() && getLevel() instanceof ServerLevel serverLevel) {
            IFabricatorSmeltingRecipe recipe = RecipeUtils.getFabricatorMeltingRecipe(serverLevel, metal);
            return recipe == null ? 0 : recipe.getMeltingPoint();
        }
        if (getMoltenTank().getAmount() > 0) {
            return this.meltingPoint;
        }
        return 0;
    }

    public int getMeltingPointScaled(int pixels) {
        int point = getMeltingPoint();
        if (point <= 0) {
            return 0;
        }
        return point * pixels / MAX_HEAT;
    }

    private void tickSmelting() {
        FilteredFluidStorage moltenTank = getMoltenTank();
        if (moltenTank.getAmount() < moltenTank.getCapacity()) {
            trySmelting();
        }

        if (moltenTank.getAmount() > 0 && this.heat < getMeltingPoint() - 100) {
            try (Transaction transaction = Transaction.openOuter()) {
                moltenTank.extract(moltenTank.getResource(), SOLIDIFY_DRAIN, transaction);
                transaction.commit();
            }
        }

        if (this.heat > 2500) {
            this.heat -= 2;
        } else if (this.heat > 0) {
            this.heat--;
        }
    }

    private void trySmelting() {
        if (!(getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }

        ItemStack smeltResource = getItem(SLOT_METAL);
        if (smeltResource.isEmpty()) {
            return;
        }

        IFabricatorSmeltingRecipe smelt = RecipeUtils.getFabricatorMeltingRecipe(serverLevel, smeltResource);
        if (smelt == null || smelt.getMeltingPoint() > this.heat) {
            return;
        }

        FluidVariant resultFluid = smelt.getResultFluid();
        long resultAmount = smelt.getResultFluidAmount();
        FilteredFluidStorage moltenTank = getMoltenTank();
        try (Transaction transaction = Transaction.openOuter()) {
            if (moltenTank.insert(resultFluid, resultAmount, transaction) != resultAmount) {
                return;
            }
            transaction.commit();
        }

        removeItem(SLOT_METAL, 1);
        this.meltingPoint = smelt.getMeltingPoint();
    }

    @Nullable
    private IFabricatorRecipe getRecipe() {
        if (!(getLevel() instanceof ServerLevel serverLevel)) {
            return null;
        }

        ItemStack plan = getItem(SLOT_PLAN);
        FilteredFluidStorage moltenTank = getMoltenTank();
        FluidVariant liquid = moltenTank.getResource();
        long liquidAmount = moltenTank.getAmount();
        IFabricatorRecipe recipe = RecipeUtils.getFabricatorRecipe(serverLevel, liquid, liquidAmount, plan, this.craftingInventory);
        if (!liquid.isBlank() && recipe != null) {
            FluidVariant required = recipe.getResultFluid();
            if (liquid.getFluid() != required.getFluid() || liquidAmount < recipe.getResultFluidAmount()) {
                return null;
            }
        }
        return recipe;
    }

    private ItemStack getCraftResult(@Nullable IFabricatorRecipe recipe) {
        if (recipe == null) {
            return ItemStack.EMPTY;
        }
        return recipe.getResultStack();
    }

    private void craftResult() {
        IFabricatorRecipe recipe = getRecipe();
        ItemStack craftResult = getCraftResult(recipe);
        if (recipe == null || craftResult.isEmpty() || !getItem(SLOT_RESULT).isEmpty()) {
            return;
        }

        FluidVariant requiredFluid = recipe.getResultFluid();
        long requiredAmount = recipe.getResultFluidAmount();
        FilteredFluidStorage moltenTank = getMoltenTank();

        if (!removeFromInventory(recipe, false)) {
            return;
        }

        try (Transaction transaction = Transaction.openOuter()) {
            if (moltenTank.extract(requiredFluid, requiredAmount, transaction) != requiredAmount) {
                return;
            }
            if (!removeFromInventory(recipe, true)) {
                return;
            }
            transaction.commit();
        }

        ItemStack planStack = getItem(SLOT_PLAN);
        if (!planStack.isEmpty()) {
            Item planItem = planStack.getItem();
            if (planItem instanceof ICraftingPlan craftingPlan) {
                setItem(SLOT_PLAN, craftingPlan.planUsed(planStack, craftResult));
            }
        }

        setItem(SLOT_RESULT, craftResult);
    }

    private boolean removeFromInventory(IFabricatorRecipe recipe, boolean doRemove) {
        var ingredients = recipe.getCraftingGridRecipe().placementInfo().ingredients();
        if (doRemove) {
            return InventoryUtil.consumeIngredients(this, SLOT_INVENTORY_1, SLOT_INVENTORY_COUNT, ingredients);
        }
        return InventoryUtil.canConsumeIngredients(
                InventoryUtil.getStacks(this, SLOT_INVENTORY_1, SLOT_INVENTORY_COUNT), ingredients);
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
        boolean hasRecipe = true;
        boolean hasLiquidResources = true;
        boolean hasResources = true;

        if (getLevel() instanceof ServerLevel serverLevel) {
            ItemStack plan = getItem(SLOT_PLAN);
            FilteredFluidStorage moltenTank = getMoltenTank();
            IFabricatorRecipe recipe = RecipeUtils.getFabricatorRecipe(
                    serverLevel, moltenTank.getResource(), moltenTank.getAmount(), plan, this.craftingInventory);
            if (recipe != null) {
                hasResources = removeFromInventory(recipe, false);
                FluidVariant requiredFluid = recipe.getResultFluid();
                long requiredAmount = recipe.getResultFluidAmount();
                hasLiquidResources = moltenTank.getAmount() >= requiredAmount
                        && moltenTank.getResource().getFluid() == requiredFluid.getFluid();
            } else {
                hasRecipe = RecipeUtils.getFabricatorMeltingRecipe(serverLevel, getItem(SLOT_METAL)) != null;
            }
        }

        IErrorLogic errorLogic = getErrorLogic();
        errorLogic.setCondition(!hasRecipe, ForestryError.NO_RECIPE);
        errorLogic.setCondition(!hasLiquidResources, ForestryError.NO_RESOURCE_LIQUID);
        errorLogic.setCondition(!hasResources, ForestryError.NO_RESOURCE_INVENTORY);

        return hasRecipe;
    }

    @Override
    protected boolean workCycle() {
        this.heat += 100;
        if (this.heat > MAX_HEAT) {
            this.heat = MAX_HEAT;
        }
        craftResult();
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
        if (stack.isEmpty()) {
            return false;
        }
        if (!(getLevel() instanceof ServerLevel serverLevel)) {
            return slot >= SLOT_INVENTORY_1 && slot < SLOT_INVENTORY_1 + SLOT_INVENTORY_COUNT;
        }
        if (slot == SLOT_METAL) {
            return RecipeUtils.isFabricatorSmeltingInput(serverLevel, stack);
        }
        if (slot == SLOT_PLAN) {
            return RecipeUtils.isFabricatorPlan(serverLevel, stack);
        }
        if (slot >= SLOT_INVENTORY_1 && slot < SLOT_INVENTORY_1 + SLOT_INVENTORY_COUNT) {
            if (RecipeUtils.isFabricatorPlan(serverLevel, stack)) {
                return false;
            }
            return !RecipeUtils.isFabricatorSmeltingInput(serverLevel, stack);
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
        craftItems.clear();
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        int[] slots = new int[SLOT_INVENTORY_COUNT + 2];
        slots[0] = SLOT_METAL;
        slots[1] = SLOT_RESULT;
        for (int i = 0; i < SLOT_INVENTORY_COUNT; i++) {
            slots[i + 2] = SLOT_INVENTORY_1 + i;
        }
        return slots;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return slot == SLOT_METAL || (slot >= SLOT_INVENTORY_1 && slot < SLOT_INVENTORY_1 + SLOT_INVENTORY_COUNT);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
        return slot == SLOT_RESULT;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Heat", this.heat);
        output.putInt("MeltingPoint", this.meltingPoint);
        ContainerHelper.saveAllItems(output, items);
        ContainerHelper.saveAllItems(output.child("CraftItems"), craftItems);
        this.tanks.writeValue(output.child("Tanks"));
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.heat = input.getIntOr("Heat", 0);
        this.meltingPoint = input.getIntOr("MeltingPoint", input.getIntOr("MeltingPont", 0));
        items.clear();
        ContainerHelper.loadAllItems(input, items);
        craftItems.clear();
        ContainerHelper.loadAllItems(input.childOrEmpty("CraftItems"), craftItems);
        this.tanks.readValue(input.childOrEmpty("Tanks"));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ContainerFabricator(containerId, playerInventory, this);
    }
}
