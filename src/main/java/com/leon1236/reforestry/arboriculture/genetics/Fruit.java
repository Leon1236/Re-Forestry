package com.leon1236.reforestry.arboriculture.genetics;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;

public class Fruit extends DummyFruit {
    private final List<Product> products;
    protected final int ripeningPeriod;

    public Fruit(Identifier id, boolean dominant, int ripeningPeriod, List<Product> products) {
        super(id, dominant);
        this.products = List.copyOf(products);
        this.ripeningPeriod = ripeningPeriod;
    }

    @Override
    public List<ItemStack> getFruits(IGenome genome, Level level, int ripeningTime) {
        if (ripeningTime < ripeningPeriod) {
            return List.of();
        }
        RandomSource rand = level.getRandom();
        List<ItemStack> stacks = new ArrayList<>(products.size());
        for (Product product : products) {
            if (product.chance() == 1.0f || product.chance() >= rand.nextFloat()) {
                stacks.add(new ItemStack(product.item()));
            }
        }
        return stacks;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }
}
