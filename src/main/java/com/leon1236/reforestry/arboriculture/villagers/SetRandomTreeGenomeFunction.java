package com.leon1236.reforestry.arboriculture.villagers;

import java.util.ArrayList;
import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;

public class SetRandomTreeGenomeFunction extends LootItemConditionalFunction {
    public static final MapCodec<SetRandomTreeGenomeFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .and(instance.group(
                            Codec.INT.fieldOf("max_complexity").forGetter(function -> function.maxComplexity),
                            Codec.INT.fieldOf("min_count").forGetter(function -> function.minCount),
                            Codec.INT.fieldOf("max_count").forGetter(function -> function.maxCount)))
                    .apply(instance, SetRandomTreeGenomeFunction::new));

    private final int maxComplexity;
    private final int minCount;
    private final int maxCount;

    private SetRandomTreeGenomeFunction(List<LootItemCondition> predicates, int maxComplexity, int minCount, int maxCount) {
        super(predicates);
        this.maxComplexity = maxComplexity;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public MapCodec<SetRandomTreeGenomeFunction> codec() {
        return MAP_CODEC;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        List<ITreeSpecies> potentialSpecies = new ArrayList<>();
        for (ITreeSpecies species : ArboricultureGenetics.getAllSpecies()) {
            if (TreeSpeciesComplexity.get(species) <= this.maxComplexity) {
                potentialSpecies.add(species);
            }
        }
        if (potentialSpecies.isEmpty()) {
            return ItemStack.EMPTY;
        }
        ITreeSpecies chosen = potentialSpecies.get(context.getRandom().nextInt(potentialSpecies.size()));
        ItemStack sell = new ItemStack(stack.getItem());
        sell.set(ArboricultureDataComponents.TREE_GENOME.type(), ArboricultureGenetics.getDefaultGenome(chosen.id()));
        sell.setCount(SetRandomForestryWoodFunction.price(context.getRandom(), this.minCount, this.maxCount));
        return sell;
    }
}
