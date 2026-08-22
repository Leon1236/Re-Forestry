package com.leon1236.reforestry.apiculture;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;

public final class BeeStackHelper {
    private BeeStackHelper() {
    }

    public static boolean isPristine(ItemStack stack) {
        return stack.getOrDefault(ApicultureDataComponents.BEE_PRISTINE.type(), true);
    }

    public static int getGeneration(ItemStack stack) {
        return stack.getOrDefault(ApicultureDataComponents.BEE_GENERATION.type(), 0);
    }

    public static void setPristine(ItemStack stack, boolean pristine) {
        if (pristine) {
            stack.remove(ApicultureDataComponents.BEE_PRISTINE.type());
        } else {
            stack.set(ApicultureDataComponents.BEE_PRISTINE.type(), false);
        }
    }

    public static void setGeneration(ItemStack stack, int generation) {
        if (generation == 0) {
            stack.remove(ApicultureDataComponents.BEE_GENERATION.type());
        } else {
            stack.set(ApicultureDataComponents.BEE_GENERATION.type(), generation);
        }
    }

    public static void copyCaptivityTraits(ItemStack from, ItemStack to) {
        setPristine(to, isPristine(from));
        setGeneration(to, getGeneration(from));
    }

    public static boolean checkIgnobleDecay(RandomSource random, int generation, float modifier) {
        return (generation > 96 + random.nextInt(6) + random.nextInt(6)) && (random.nextFloat() < 0.02f * modifier);
    }

    public static ItemStack createBeeStack(Item item, IGenome genome, boolean pristine, int generation) {
        ItemStack stack = new ItemStack(item);
        stack.set(ApicultureDataComponents.BEE_GENOME.type(), genome);
        setPristine(stack, pristine);
        setGeneration(stack, generation);
        return stack;
    }
}
