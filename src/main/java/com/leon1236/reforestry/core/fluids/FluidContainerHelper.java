package com.leon1236.reforestry.core.fluids;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ContainerStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.core.items.FluidContainerContents;
import com.leon1236.reforestry.core.items.ItemFluidContainerForestry;

public final class FluidContainerHelper {
    public enum FillStatus {
        SUCCESS,
        INVALID_INPUT,
        NO_FLUID,
        NO_SPACE,
        NO_SPACE_FLUID
    }

    private FluidContainerHelper() {
    }

    public static boolean isFillableContainerWithRoom(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        ContainerItemContext context = ContainerItemContext.withConstant(stack.copyWithCount(1));
        return context.find(FluidStorage.ITEM) != null;
    }

    public static boolean canAcceptFluid(Level level, BlockPos pos, Direction facing, FluidVariant variant) {
        if (variant.isBlank()) {
            return false;
        }
        Storage<FluidVariant> handler = FluidStorage.SIDED.find(level, pos, facing);
        if (handler == null) {
            return false;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            return handler.insert(variant, 1, transaction) > 0;
        }
    }

    public static FillStatus fillContainers(Storage<FluidVariant> tank, Container container, int inputSlot, int outputSlot,
            FluidVariant variant, boolean doFill) {
        ItemStack input = container.getItem(inputSlot);
        if (input.isEmpty() || variant.isBlank()) {
            return FillStatus.INVALID_INPUT;
        }
        ItemStack output = container.getItem(outputSlot);

        if (input.is(Items.BUCKET)) {
            net.minecraft.world.item.Item filledBucketItem = variant.getFluid().getBucket();
            if (filledBucketItem == null || filledBucketItem == Items.BUCKET) {
                return FillStatus.INVALID_INPUT;
            }
            if (!output.isEmpty() && (!output.is(filledBucketItem) || output.getCount() >= output.getMaxStackSize())) {
                return FillStatus.NO_SPACE;
            }
            if (!doFill) {
                return FillStatus.SUCCESS;
            }
            try (Transaction transaction = Transaction.openOuter()) {
                if (tank.extract(variant, FluidConstants.BUCKET, transaction) != FluidConstants.BUCKET) {
                    return FillStatus.NO_FLUID;
                }
                transaction.commit();
            }
            ItemStack filledBucket = new ItemStack(filledBucketItem);
            if (output.isEmpty()) {
                container.setItem(outputSlot, filledBucket);
            } else {
                output.grow(1);
            }
            container.removeItem(inputSlot, 1);
            return FillStatus.SUCCESS;
        }

        ContainerItemContext context = ContainerItemContext.withConstant(input.copyWithCount(1));
        Storage<FluidVariant> itemStorage = context.find(FluidStorage.ITEM);
        if (itemStorage == null) {
            return FillStatus.INVALID_INPUT;
        }

        long containerCapacity;
        try (Transaction transaction = Transaction.openOuter()) {
            containerCapacity = itemStorage.insert(variant, Long.MAX_VALUE, transaction);
            if (containerCapacity <= 0) {
                return FillStatus.INVALID_INPUT;
            }
        }

        long drainAmount;
        try (Transaction transaction = Transaction.openOuter()) {
            drainAmount = tank.extract(variant, containerCapacity, transaction);
            if (drainAmount <= 0) {
                return FillStatus.NO_FLUID;
            }
        }

        ItemStack working = input.copyWithCount(1);
        ContainerItemContext fillContext = ContainerItemContext.withConstant(working);
        Storage<FluidVariant> fillStorage = fillContext.find(FluidStorage.ITEM);
        if (fillStorage == null) {
            return FillStatus.INVALID_INPUT;
        }

        long filledAmount;
        try (Transaction transaction = Transaction.openOuter()) {
            filledAmount = fillStorage.insert(variant, drainAmount, transaction);
            if (filledAmount <= 0) {
                return FillStatus.NO_FLUID;
            }
            transaction.commit();
        }

        ItemStack filledStack = fillContext.getItemVariant().toStack(1);
        long amountInContainer = amountInStack(filledStack);
        boolean moveToOutput = amountInContainer >= drainAmount;
        if (moveToOutput) {
            if (!output.isEmpty() && (output.getCount() >= output.getMaxStackSize()
                    || !ItemStack.isSameItemSameComponents(filledStack, output))) {
                return FillStatus.NO_SPACE;
            }
        } else if (input.getCount() > 1) {
            return FillStatus.NO_SPACE;
        }

        if (!doFill) {
            return FillStatus.SUCCESS;
        }

        try (Transaction transaction = Transaction.openOuter()) {
            if (tank.extract(variant, filledAmount, transaction) != filledAmount) {
                return FillStatus.NO_FLUID;
            }
            transaction.commit();
        }

        if (moveToOutput) {
            if (output.isEmpty()) {
                container.setItem(outputSlot, filledStack);
            } else {
                output.grow(1);
            }
            container.removeItem(inputSlot, 1);
        } else {
            container.setItem(inputSlot, filledStack);
        }
        return FillStatus.SUCCESS;
    }

    public static FillStatus drainContainers(Storage<FluidVariant> tank, Container container, int inputSlot, int outputSlot,
            boolean doDrain) {
        ItemStack input = container.getItem(inputSlot);
        if (input.isEmpty()) {
            return FillStatus.INVALID_INPUT;
        }

        Fluid bucket = bucketFluid(input);
        if (bucket != null) {
            ItemStack emptyBucket = new ItemStack(Items.BUCKET);
            ItemStack outputStack = container.getItem(outputSlot);
            if (!outputStack.isEmpty()
                    && (!ItemStack.isSameItemSameComponents(outputStack, emptyBucket)
                    || outputStack.getCount() + 1 > outputStack.getMaxStackSize())) {
                return FillStatus.NO_SPACE;
            }
            try (Transaction transaction = Transaction.openOuter()) {
                if (tank.insert(FluidVariant.of(bucket), FluidConstants.BUCKET, transaction) != FluidConstants.BUCKET) {
                    return FillStatus.NO_SPACE_FLUID;
                }
                if (!doDrain) {
                    return FillStatus.SUCCESS;
                }
                transaction.commit();
            }
            if (doDrain) {
                if (outputStack.isEmpty()) {
                    container.setItem(outputSlot, emptyBucket);
                } else {
                    outputStack.grow(1);
                }
                container.removeItem(inputSlot, 1);
            }
            return FillStatus.SUCCESS;
        }

        ItemStack outputStack = container.getItem(outputSlot);
        ItemStack simulated = input.copyWithCount(1);
        ContainerItemContext simulateContext = ContainerItemContext.withConstant(simulated);
        Storage<FluidVariant> simulateStorage = simulateContext.find(FluidStorage.ITEM);
        if (simulateStorage == null) {
            return FillStatus.INVALID_INPUT;
        }

        ItemStack drainedItemSimulated;
        try (Transaction transaction = Transaction.openOuter()) {
            if (StorageUtil.move(simulateStorage, tank, variant -> true, FluidConstants.BUCKET, transaction) <= 0) {
                return FillStatus.INVALID_INPUT;
            }
            drainedItemSimulated = simulateContext.getItemVariant().toStack(1);
        }

        if (!outputStack.isEmpty() && !drainedItemSimulated.isEmpty()
                && (!ItemStack.isSameItemSameComponents(outputStack, drainedItemSimulated)
                || outputStack.getCount() + 1 > outputStack.getMaxStackSize())) {
            if (input.getCount() > 1 || !isEmptyContainer(drainedItemSimulated)) {
                return FillStatus.NO_SPACE;
            }
        }

        if (!doDrain) {
            return FillStatus.SUCCESS;
        }

        if (drainIntoTank(container, inputSlot, tank)) {
            ItemStack resultItem = container.getItem(inputSlot);
            if (!resultItem.isEmpty() && isEmptyContainer(resultItem) && input.getCount() == 1) {
                if (outputStack.isEmpty()) {
                    container.setItem(outputSlot, resultItem.copyWithCount(1));
                    container.setItem(inputSlot, ItemStack.EMPTY);
                } else if (ItemStack.isSameItemSameComponents(outputStack, resultItem)
                        && outputStack.getCount() < outputStack.getMaxStackSize()) {
                    outputStack.grow(1);
                    container.setItem(inputSlot, ItemStack.EMPTY);
                }
            }
            return FillStatus.SUCCESS;
        }
        return FillStatus.INVALID_INPUT;
    }

    public static boolean isEmptyContainer(ItemStack stack) {
        if (stack.getItem() instanceof ItemFluidContainerForestry) {
            return FluidContainerContents.get(stack).amount() <= 0;
        }
        return stack.is(Items.BUCKET);
    }

    public static boolean isFilledContainer(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (stack.getItem() instanceof ItemFluidContainerForestry) {
            return FluidContainerContents.get(stack).amount() > 0;
        }
        if (bucketFluid(stack) != null) {
            return true;
        }
        return hasExtractableFluid(stack);
    }

    public static boolean isDrainableFilledContainer(ItemStack stack) {
        if (stack.getItem() instanceof ItemFluidContainerForestry) {
            FluidContainerContents contents = FluidContainerContents.get(stack);
            return contents.amount() >= FluidConstants.BUCKET;
        }
        if (bucketFluid(stack) != null) {
            return true;
        }
        return hasExtractableFluid(stack);
    }

    public static boolean drainFromSlotToTank(Container container, int slot, Storage<FluidVariant> tank) {
        if (Transaction.isOpen()) {
            return false;
        }
        ItemStack stack = container.getItem(slot);
        if (stack.isEmpty()) {
            return false;
        }
        if (tryDrainVanillaBucket(container, slot, tank)) {
            return true;
        }
        Storage<FluidVariant> itemStorage = itemFluidStorage(container, slot);
        if (itemStorage == null) {
            return false;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            long moved = StorageUtil.move(itemStorage, tank, variant -> true, Long.MAX_VALUE, transaction);
            if (moved > 0) {
                transaction.commit();
                return true;
            }
        }
        return false;
    }

    public static FluidVariant fluidIn(ItemStack stack) {
        if (stack.getItem() instanceof ItemFluidContainerForestry) {
            return FluidContainerContents.get(stack).variant();
        }
        Fluid fluid = bucketFluid(stack);
        if (fluid != null && fluid != Fluids.EMPTY) {
            return FluidVariant.of(fluid);
        }
        ContainerItemContext context = ContainerItemContext.withConstant(stack.copyWithCount(1));
        Storage<FluidVariant> storage = context.find(FluidStorage.ITEM);
        if (storage == null) {
            return FluidVariant.blank();
        }
        for (var view : storage.nonEmptyViews()) {
            if (!view.isResourceBlank() && view.getAmount() > 0) {
                return view.getResource();
            }
        }
        return FluidVariant.blank();
    }

    public static boolean canTankAccept(Storage<FluidVariant> tank, FluidVariant variant) {
        if (variant.isBlank()) {
            return false;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            return tank.insert(variant, 1, transaction) > 0;
        }
    }

    public static boolean drainIntoTank(Container container, int slot, Storage<FluidVariant> tank) {
        if (Transaction.isOpen()) {
            return false;
        }
        ItemStack stack = container.getItem(slot);
        if (stack.isEmpty()) {
            return false;
        }
        if (tryDrainVanillaBucket(container, slot, tank)) {
            return true;
        }
        Storage<FluidVariant> itemStorage = itemFluidStorage(container, slot);
        if (itemStorage == null) {
            return false;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            long moved = StorageUtil.move(itemStorage, tank, variant -> true, Long.MAX_VALUE, transaction);
            if (moved > 0) {
                transaction.commit();
                return true;
            }
        }
        return false;
    }

    public static boolean fillFromTank(Container container, int inputSlot, int outputSlot, Storage<FluidVariant> tank) {
        if (Transaction.isOpen()) {
            return false;
        }
        ItemStack input = container.getItem(inputSlot);
        if (input.isEmpty() || !isEmptyContainer(input)) {
            return false;
        }
        Storage<FluidVariant> itemStorage = itemFluidStorage(container, inputSlot);
        if (itemStorage == null) {
            return false;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            long moved = StorageUtil.move(tank, itemStorage, variant -> true, Long.MAX_VALUE, transaction);
            if (moved <= 0) {
                return false;
            }
            transaction.commit();
        }
        ItemStack filled = container.getItem(inputSlot);
        if (filled.isEmpty()) {
            return true;
        }
        FluidContainerContents contents = FluidContainerContents.get(filled);
        if (contents.amount() >= FluidConstants.BUCKET) {
            ItemStack output = container.getItem(outputSlot);
            if (output.isEmpty()) {
                container.setItem(outputSlot, filled);
                container.setItem(inputSlot, ItemStack.EMPTY);
            } else if (ItemStack.isSameItemSameComponents(output, filled) && output.getCount() < output.getMaxStackSize()) {
                output.grow(1);
                container.removeItem(inputSlot, 1);
            }
        }
        return true;
    }

    private static ContainerItemContext itemContext(Container container, int slot) {
        SingleSlotStorage<net.fabricmc.fabric.api.transfer.v1.item.ItemVariant> slotStorage =
                ContainerStorage.of(container, null).getSlot(slot);
        return ContainerItemContext.ofSingleSlot(slotStorage);
    }

    private static Storage<FluidVariant> itemFluidStorage(Container container, int slot) {
        return itemContext(container, slot).find(FluidStorage.ITEM);
    }

    private static long amountInStack(ItemStack stack) {
        if (stack.getItem() instanceof ItemFluidContainerForestry) {
            return FluidContainerContents.get(stack).amount();
        }
        return bucketFluid(stack) != null ? FluidConstants.BUCKET : 0;
    }

    private static boolean tryDrainVanillaBucket(Container container, int slot, Storage<FluidVariant> tank) {
        ItemStack stack = container.getItem(slot);
        Fluid fluid = bucketFluid(stack);
        if (fluid == null) {
            return false;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            if (tank.insert(FluidVariant.of(fluid), FluidConstants.BUCKET, transaction) == FluidConstants.BUCKET) {
                transaction.commit();
                container.setItem(slot, new ItemStack(Items.BUCKET));
                return true;
            }
        }
        return false;
    }

    private static boolean hasExtractableFluid(ItemStack stack) {
        ContainerItemContext context = ContainerItemContext.withConstant(stack.copyWithCount(1));
        Storage<FluidVariant> storage = context.find(FluidStorage.ITEM);
        if (storage == null) {
            return false;
        }
        for (var view : storage.nonEmptyViews()) {
            if (!view.isResourceBlank() && view.getAmount() > 0) {
                return true;
            }
        }
        return false;
    }

    private static Fluid bucketFluid(ItemStack stack) {
        if (!(stack.getItem() instanceof net.minecraft.world.item.BucketItem bucket)) {
            return null;
        }
        Fluid fluid = bucket.getContent();
        if (fluid == null || fluid == Fluids.EMPTY || fluid.getBucket() != bucket) {
            return null;
        }
        return fluid;
    }
}
