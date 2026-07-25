package com.leon1236.reforestry.core.features;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.items.FluidContainerContents;
import com.leon1236.reforestry.core.items.FluidContainerItemStorage;
import com.leon1236.reforestry.core.items.ItemFluidContainerForestry;
import com.leon1236.reforestry.core.items.definitions.EnumContainerType;
import com.leon1236.reforestry.modules.features.FeatureDataComponent;
import com.leon1236.reforestry.modules.features.FeatureItemGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FluidsItems {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

    public static final FeatureDataComponent<FluidContainerContents> FLUID_CONTENTS =
            REGISTRY.dataComponent("fluid_container_contents", builder -> builder
                    .persistent(FluidContainerContents.CODEC)
                    .networkSynchronized(FluidContainerContents.PACKET_CODEC));

    public static final FeatureItemGroup<ItemFluidContainerForestry, EnumContainerType> CONTAINERS =
            REGISTRY.itemGroup((type, properties) -> new ItemFluidContainerForestry(type, properties
                    .stacksTo(1)
                    .component(FLUID_CONTENTS.type(), FluidContainerContents.EMPTY)), EnumContainerType.values())
                    .create();

    public static ItemStack createFilled(EnumContainerType type, ForestryFluids fluid) {
        return createFilled(type, fluid.getFluid());
    }

    public static ItemStack createFilled(EnumContainerType type, Fluid fluid) {
        ItemStack stack = new ItemStack(CONTAINERS.item(type));
        FluidContainerContents.set(stack, FluidVariant.of(fluid), FluidConstants.BUCKET);
        return stack;
    }

    public static boolean isEmpty(ItemStack stack) {
        if (!(stack.getItem() instanceof ItemFluidContainerForestry)) {
            return false;
        }
        return FluidContainerContents.get(stack).amount() <= 0;
    }

    public static void init() {
        for (EnumContainerType type : EnumContainerType.values()) {
            Item item = CONTAINERS.item(type);
            FluidStorage.ITEM.registerForItems((stack, context) -> new FluidContainerItemStorage(context, type), item);
        }
    }
}
