package com.leon1236.reforestry.factory.recipes;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.core.fluids.FluidContainerHelper;
import com.leon1236.reforestry.core.items.FluidContainerContents;
import com.leon1236.reforestry.core.items.ItemFluidContainerForestry;

public final class BottlerRecipe {
    public final FluidVariant fluid;
    public final long fluidAmount;
    public final ItemStack inputStack;
    public final ItemStack outputStack;
    public final boolean fillRecipe;

    private BottlerRecipe(ItemStack inputStack, FluidVariant fluid, long fluidAmount, ItemStack outputStack, boolean fillRecipe) {
        this.inputStack = inputStack;
        this.fluid = fluid;
        this.fluidAmount = fluidAmount;
        this.outputStack = outputStack;
        this.fillRecipe = fillRecipe;
    }

    @Nullable
    public static BottlerRecipe createEmptyingRecipe(ItemStack filled) {
        if (!FluidContainerHelper.isFilledContainer(filled)) {
            return null;
        }
        FluidVariant variant = FluidContainerHelper.fluidIn(filled);
        if (variant.isBlank()) {
            return null;
        }
        long amount = amountIn(filled);
        if (amount <= 0) {
            return null;
        }
        ItemStack empty;
        if (filled.is(Items.WATER_BUCKET) || filled.is(Items.LAVA_BUCKET)) {
            empty = new ItemStack(Items.BUCKET);
        } else {
            empty = new ItemStack(filled.getItem());
        }
        return new BottlerRecipe(filled.copyWithCount(1), variant, amount, empty, false);
    }

    private static long amountIn(ItemStack stack) {
        if (stack.getItem() instanceof ItemFluidContainerForestry) {
            return FluidContainerContents.get(stack).amount();
        }
        return FluidContainerHelper.isFilledContainer(stack) ? net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants.BUCKET : 0;
    }

    @Nullable
    public static BottlerRecipe createFillingRecipe(Fluid fluid, ItemStack empty) {
        FluidVariant variant = FluidVariant.of(fluid);
        ItemStack copy = empty.copyWithCount(1);
        ContainerItemContext context = ContainerItemContext.withConstant(copy);
        Storage<FluidVariant> storage = context.find(FluidStorage.ITEM);
        if (storage == null) {
            return null;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            long filled = storage.insert(variant, Long.MAX_VALUE, transaction);
            if (filled <= 0) {
                return null;
            }
            transaction.commit();
            ItemStack filledStack = context.getItemVariant().toStack(1);
            return new BottlerRecipe(empty.copyWithCount(1), variant, filled, filledStack, true);
        }
    }

    public boolean matchEmpty(ItemStack emptyCan, FluidVariant resource) {
        return !emptyCan.isEmpty()
                && ItemStack.isSameItemSameComponents(emptyCan, this.inputStack)
                && this.fluid.equals(resource)
                && this.fillRecipe;
    }

    public boolean matchFilled(ItemStack filledCan) {
        return !this.outputStack.isEmpty()
                && !this.fillRecipe
                && ItemStack.isSameItemSameComponents(this.outputStack, filledCan);
    }
}
