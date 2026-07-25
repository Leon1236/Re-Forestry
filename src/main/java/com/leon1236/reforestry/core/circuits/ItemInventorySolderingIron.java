package com.leon1236.reforestry.core.circuits;

import com.google.common.collect.ImmutableSet;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.circuits.ICircuit;
import com.leon1236.reforestry.api.circuits.ICircuitLayout;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.api.core.IErrorSource;

public class ItemInventorySolderingIron implements Container, IErrorSource {
    private static final int INPUT_BOARD = 0;
    private static final int OUTPUT_BOARD = 1;
    private static final int INGREDIENT_START = 2;
    private static final int INGREDIENT_COUNT = 4;

    private final NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);
    private final Player player;
    private int layoutIndex;

    public ItemInventorySolderingIron(Player player) {
        this.player = player;
        this.layoutIndex = 0;
    }

    public ICircuitLayout getLayout() {
        var layouts = IForestryApi.INSTANCE.getCircuitManager().getLayouts();
        if (layouts.isEmpty()) {
            return null;
        }
        return layouts.get(Math.floorMod(this.layoutIndex, layouts.size()));
    }

    public void advanceLayout() {
        this.layoutIndex++;
    }

    public void regressLayout() {
        this.layoutIndex--;
    }

    public void tryAssemble() {
        ICircuitLayout layout = getLayout();
        if (layout == null) {
            return;
        }
        ItemStack inputBoard = getItem(INPUT_BOARD);
        if (inputBoard.isEmpty() || inputBoard.getCount() > 1 || !getItem(OUTPUT_BOARD).isEmpty()) {
            return;
        }
        if (!IForestryApi.INSTANCE.getCircuitManager().isCircuitBoard(inputBoard)) {
            return;
        }
        if (!(inputBoard.getItem() instanceof ItemCircuitBoard circuitBoardItem)) {
            return;
        }
        EnumCircuitBoardType type = circuitBoardItem.getType();
        if (countFilledIngredients(type.getSockets()) != type.getSockets()) {
            return;
        }
        ICircuit[] circuits = collectCircuits(layout, true);
        if (countNonNull(circuits) != type.getSockets()) {
            return;
        }
        setItem(OUTPUT_BOARD, ItemCircuitBoard.createCircuitBoard(type, layout, circuits));
        setItem(INPUT_BOARD, ItemStack.EMPTY);
    }

    private int countFilledIngredients(int sockets) {
        int count = 0;
        for (int i = 0; i < sockets; i++) {
            if (!getItem(INGREDIENT_START + i).isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private ICircuit[] collectCircuits(ICircuitLayout layout, boolean consume) {
        ICircuit[] circuits = new ICircuit[INGREDIENT_COUNT];
        for (int i = 0; i < INGREDIENT_COUNT; i++) {
            ItemStack ingredient = getItem(INGREDIENT_START + i);
            if (!ingredient.isEmpty()) {
                ICircuit circuit = IForestryApi.INSTANCE.getCircuitManager().getCircuit(layout, ingredient);
                if (circuit != null) {
                    if (consume) {
                        removeItem(INGREDIENT_START + i, ingredient.getCount());
                    }
                    circuits[i] = circuit;
                }
            }
        }
        return circuits;
    }

    private static int countNonNull(ICircuit[] circuits) {
        int count = 0;
        for (ICircuit circuit : circuits) {
            if (circuit != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public ImmutableSet<IError> getErrors() {
        ImmutableSet.Builder<IError> errors = ImmutableSet.builder();
        ICircuitLayout layout = getLayout();
        if (layout == null) {
            errors.add(ForestryError.NO_CIRCUIT_LAYOUT);
        }
        ItemStack inputBoard = getItem(INPUT_BOARD);
        if (inputBoard.isEmpty()) {
            errors.add(ForestryError.NO_CIRCUIT_BOARD);
        } else if (inputBoard.getItem() instanceof ItemCircuitBoard circuitBoardItem) {
            EnumCircuitBoardType type = circuitBoardItem.getType();
            if (countFilledIngredients(type.getSockets()) != type.getSockets()) {
                errors.add(ForestryError.CIRCUIT_MISMATCH);
            } else if (layout != null && countNonNull(collectCircuits(layout, false)) != type.getSockets()) {
                errors.add(ForestryError.NO_CIRCUIT_LAYOUT);
            }
        }
        return errors.build();
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.items) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(this.items, slot, amount);
        onContentsChanged(slot);
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.items.set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        onContentsChanged(slot);
    }

    private void onContentsChanged(int slot) {
        if (slot == INPUT_BOARD || (slot >= INGREDIENT_START && slot < INGREDIENT_START + INGREDIENT_COUNT)) {
            tryAssemble();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.player == player;
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void setChanged() {
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        ICircuitLayout layout = getLayout();
        if (slot == INPUT_BOARD) {
            return stack.getItem() instanceof ItemCircuitBoard;
        }
        if (slot >= INGREDIENT_START && slot < INGREDIENT_START + INGREDIENT_COUNT && layout != null) {
            return IForestryApi.INSTANCE.getCircuitManager().getCircuit(layout, stack) != null;
        }
        return false;
    }
}
