package com.leon1236.reforestry.core.inventory;

import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public final class InventoryUtil {
    public static final int[] NO_SLOTS = new int[0];

    private InventoryUtil() {
    }

    public static int[] contiguousSlots(int count) {
        int[] slots = new int[count];
        for (int i = 0; i < count; i++) {
            slots[i] = i;
        }
        return slots;
    }

    public static boolean isEmpty(Container inventory, int slotStart, int slotCount) {
        for (int i = slotStart; i < slotStart + slotCount; i++) {
            if (!inventory.getItem(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static NonNullList<ItemStack> getStacks(Container inventory) {
        NonNullList<ItemStack> stacks = NonNullList.withSize(inventory.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            stacks.set(i, inventory.getItem(i));
        }
        return stacks;
    }

    public static NonNullList<ItemStack> getStacks(Container inventory, int slotStart, int slotCount) {
        NonNullList<ItemStack> stacks = NonNullList.withSize(slotCount, ItemStack.EMPTY);
        for (int i = 0; i < slotCount; i++) {
            stacks.set(i, inventory.getItem(slotStart + i));
        }
        return stacks;
    }

    public static boolean canConsumeIngredients(List<ItemStack> stock, List<Ingredient> ingredients) {
        return createConsume(ingredients, stock.size(), stock::get).length > 0;
    }

    public static boolean consumeIngredients(Container inventory, int slotStart, int slotCount, List<Ingredient> ingredients) {
        int[] consumeStacks = createConsume(ingredients, slotCount, index -> inventory.getItem(slotStart + index));
        if (consumeStacks.length == 0) {
            return false;
        }
        for (int i = 0; i < consumeStacks.length; i++) {
            int count = consumeStacks[i];
            if (count <= 0) {
                continue;
            }
            ItemStack removed = inventory.removeItem(slotStart + i, count);
            if (removed.getCount() < count) {
                return false;
            }
        }
        return true;
    }

    private static final int[] EMPTY_CONSUME = new int[0];

    private static int[] createConsume(List<Ingredient> ingredients, int stockCount, java.util.function.IntFunction<ItemStack> stock) {
        int[] required = new int[stockCount];
        int found = 0;
        for (Ingredient ingredient : ingredients) {
            if (ingredient.isEmpty()) {
                found++;
                continue;
            }
            for (int i = 0; i < required.length; i++) {
                ItemStack offer = stock.apply(i);
                if (offer.getCount() > required[i] && ingredient.test(offer)) {
                    required[i]++;
                    found++;
                    break;
                }
            }
        }
        if (found < ingredients.size()) {
            return EMPTY_CONSUME;
        }
        return required;
    }

    public static boolean tryAddStack(Container inventory, ItemStack stack, int startSlot, int slots, boolean all) {
        int added = addStack(inventory, stack, startSlot, slots, false);
        boolean success = all ? added == stack.getCount() : added > 0;
        if (success) {
            addStack(inventory, stack, startSlot, slots, true);
        }
        return success;
    }

    public static int addStack(Container inventory, ItemStack stack, int startSlot, int slots, boolean doAdd) {
        if (stack.isEmpty()) {
            return 0;
        }

        int added = 0;
        for (int i = startSlot; i < startSlot + slots; i++) {
            ItemStack inventoryStack = inventory.getItem(i);
            if (inventoryStack.isEmpty() || !inventoryStack.isStackable()
                    || !ItemStack.isSameItemSameComponents(inventoryStack, stack)) {
                continue;
            }

            int remain = stack.getCount() - added;
            int space = inventoryStack.getMaxStackSize() - inventoryStack.getCount();
            if (space <= 0) {
                continue;
            }

            if (space >= remain) {
                if (doAdd) {
                    inventoryStack.grow(remain);
                }
                return stack.getCount();
            }

            if (doAdd) {
                inventoryStack.setCount(inventoryStack.getMaxStackSize());
            }
            added += space;
        }

        if (added >= stack.getCount()) {
            return added;
        }

        for (int i = startSlot; i < startSlot + slots; i++) {
            if (inventory.getItem(i).isEmpty()) {
                if (doAdd) {
                    ItemStack remainder = stack.copy();
                    remainder.setCount(stack.getCount() - added);
                    inventory.setItem(i, remainder);
                }
                return stack.getCount();
            }
        }

        return added;
    }

    public static boolean stowInInventory(ItemStack stack, Container inventory, boolean doAdd) {
        if (stack.isEmpty()) {
            return true;
        }
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack inventoryStack = inventory.getItem(i);
            if (inventoryStack.isEmpty()) {
                if (doAdd) {
                    inventory.setItem(i, stack.copy());
                }
                return true;
            }
            if (inventoryStack.getCount() >= inventoryStack.getMaxStackSize()) {
                continue;
            }
            if (!ItemStack.isSameItemSameComponents(inventoryStack, stack)) {
                continue;
            }
            int space = inventoryStack.getMaxStackSize() - inventoryStack.getCount();
            if (space >= stack.getCount()) {
                if (doAdd) {
                    inventoryStack.grow(stack.getCount());
                }
                return true;
            }
            if (doAdd) {
                inventoryStack.setCount(inventoryStack.getMaxStackSize());
                stack.shrink(space);
            }
            if (stack.isEmpty()) {
                return true;
            }
        }
        return stack.isEmpty();
    }
}
