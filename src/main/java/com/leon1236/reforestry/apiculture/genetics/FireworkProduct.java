package com.leon1236.reforestry.apiculture.genetics;

import java.util.List;

import it.unimi.dsi.fastutil.ints.IntList;

import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;

import com.leon1236.reforestry.api.core.IProduct;

public record FireworkProduct(float chance) implements IProduct {
    private static final DyeColor[] COLORS = {DyeColor.RED, DyeColor.WHITE, DyeColor.BLUE};
    private static final FireworkExplosion.Shape[] SHAPES = FireworkExplosion.Shape.values();

    @Override
    public Item item() {
        return Items.FIREWORK_ROCKET;
    }

	@Override
	public ItemStack createStack() {
		return createRandomStack(RandomSource.create(0L));
	}

    @Override
    public ItemStack createRandomStack(RandomSource random) {
        ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
        DyeColor color = COLORS[random.nextInt(COLORS.length)];
        FireworkExplosion.Shape shape = SHAPES[random.nextInt(SHAPES.length)];
        FireworkExplosion explosion = new FireworkExplosion(
                shape,
                IntList.of(color.getFireworkColor()),
                IntList.of(),
                false,
                random.nextBoolean());
        firework.set(DataComponents.FIREWORKS, new Fireworks(2, List.of(explosion)));
        return firework;
    }
}
