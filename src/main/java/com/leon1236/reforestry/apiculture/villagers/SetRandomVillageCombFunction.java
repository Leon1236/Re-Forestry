package com.leon1236.reforestry.apiculture.villagers;

import java.util.ArrayList;
import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import com.leon1236.reforestry.api.ForestryTags;

public class SetRandomVillageCombFunction extends LootItemConditionalFunction {
    public static final MapCodec<SetRandomVillageCombFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .and(instance.group(
                            Codec.INT.fieldOf("min_count").forGetter(function -> function.minCount),
                            Codec.INT.fieldOf("max_count").forGetter(function -> function.maxCount)))
                    .apply(instance, SetRandomVillageCombFunction::new));

    private final int minCount;
    private final int maxCount;

    private SetRandomVillageCombFunction(List<LootItemCondition> predicates, int minCount, int maxCount) {
        super(predicates);
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public MapCodec<SetRandomVillageCombFunction> codec() {
        return MAP_CODEC;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        List<Item> combs = new ArrayList<>();
        BuiltInRegistries.ITEM.getTagOrEmpty(ForestryTags.Items.VILLAGE_COMBS).forEach(holder -> combs.add(holder.value()));
        if (combs.isEmpty()) {
            return ItemStack.EMPTY;
        }
        Item chosen = combs.get(context.getRandom().nextInt(combs.size()));
        return new ItemStack(chosen, price(context.getRandom(), this.minCount, this.maxCount));
    }

    static int price(RandomSource random, int min, int max) {
        return min >= max ? min : min + random.nextInt(max - min + 1);
    }
}
