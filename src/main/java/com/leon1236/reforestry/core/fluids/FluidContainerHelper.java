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
        ItemStack outputStack = container.getItem(outputSlot);

        ItemStack simulated = input.copyWithCount(1);
        ContainerItemContext simulateContext = ContainerItemContext.withConstant(simulated);
        Storage<FluidVariant> simulateStorage = simulateContext.find(FluidStorage.ITEM);
        if (simulateStorage == null) {
            return FillStatus.INVALID_INPUT;
        }

        try (Transaction transaction = Transaction.openOuter()) {
            if (StorageUtil.move(simulateStorage, tank, variant -> true, FluidConstants.BUCKET, transaction) <= 0) {
                return FillStatus.INVALID_INPUT;
            }
        }

        ItemStack drainedSimulated = input.copyWithCount(1);
        ContainerItemContext drainContext = ContainerItemContext.withConstant(drainedSimulated);
        Storage<FluidVariant> drainStorage = drainContext.find(FluidStorage.ITEM);
        if (drainStorage == null) {
            return FillStatus.INVALID_INPUT;
        }

        try (Transaction transaction = Transaction.openOuter()) {
            if (StorageUtil.move(drainStorage, tank, variant -> true, FluidConstants.BUCKET, transaction) <= 0) {
                return FillStatus.INVALID_INPUT;
            }
            transaction.commit();
        }

        ItemStack drainedItem = drainContext.getItemVariant().toStack(1);
        if (!outputStack.isEmpty() && !drainedItem.isEmpty()
                && (!ItemStack.isSameItemSameComponents(outputStack, drainedItem)
                || outputStack.getCount() + 1 > outputStack.getMaxStackSize())) {
            return FillStatus.NO_SPACE;
        }

        if (!doDrain) {
            return FillStatus.SUCCESS;
        }

        ContainerItemContext realContext = itemContext(container, inputSlot);
        Storage<FluidVariant> realStorage = realContext.find(FluidStorage.ITEM);
        if (realStorage == null) {
            return FillStatus.INVALID_INPUT;
        }

        try (Transaction transaction = Transaction.openOuter()) {
            if (StorageUtil.move(realStorage, tank, variant -> true, FluidConstants.BUCKET, transaction) <= 0) {
                return FillStatus.INVALID_INPUT;
            }
            transaction.commit();
        }

        ItemStack resultItem = realContext.getItemVariant().toStack(input.getCount());
        if (!resultItem.isEmpty() && isEmptyContainer(resultItem)) {
            if (outputStack.isEmpty()) {
                container.setItem(outputSlot, resultItem.copyWithCount(1));
            } else {
                outputStack.grow(1);
            }
            container.removeItem(inputSlot, 1);
        } else if (!resultItem.isEmpty() && isFilledContainer(resultItem)) {
            container.setItem(inputSlot, resultItem);
        } else {
            container.removeItem(inputSlot, 1);
        }
        return FillStatus.SUCCESS;
    }

    public static boolean isEmptyContainer(ItemStack stack) {
        if (stack.getItem() instanceof ItemFluidContainerForestry) {
            return FluidContainerContents.get(stack).amount() <= 0;
        }
        return stack.is(Items.BUCKET);
    }

    public static boolean isFilledContainer(ItemStack stack) {
        if (stack.getItem() instanceof ItemFluidContainerForestry) {
            return FluidContainerContents.get(stack).amount() > 0;
        }
        return bucketFluid(stack) != null;
    }

    public static boolean isDrainableFilledContainer(ItemStack stack) {
        if (stack.getItem() instanceof ItemFluidContainerForestry) {
            FluidContainerContents contents = FluidContainerContents.get(stack);
            return contents.amount() >= FluidConstants.BUCKET;
        }
        return bucketFluid(stack) != null;
    }

    public static boolean drainFromSlotToTank(Container container, int slot, Storage<FluidVariant> tank) {
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
        return fluid == null || fluid == Fluids.EMPTY ? FluidVariant.blank() : FluidVariant.of(fluid);
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

    private static Fluid bucketFluid(ItemStack stack) {
        if (stack.is(Items.WATER_BUCKET)) {
            return Fluids.WATER;
        }
        if (stack.is(Items.LAVA_BUCKET)) {
            return Fluids.LAVA;
        }
        if (stack.is(Items.MILK_BUCKET)) {
            return Fluids.EMPTY;
        }
        return null;
    }
}
