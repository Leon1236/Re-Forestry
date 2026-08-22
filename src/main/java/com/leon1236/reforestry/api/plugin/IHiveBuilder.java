package com.leon1236.reforestry.api.plugin;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.hives.IHiveDrop;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface IHiveBuilder {
    default IHiveBuilder addDrop(double chance, Identifier speciesId, Supplier<List<ItemStack>> extraItems) {
        return addDrop(chance, speciesId, extraItems, 0f, Map.of());
    }

    default IHiveBuilder addDrop(double chance, Identifier speciesId, Supplier<List<ItemStack>> extraItems,
            float ignobleChance) {
        return addDrop(chance, speciesId, extraItems, ignobleChance, Map.of());
    }

    IHiveBuilder addDrop(double chance, Identifier speciesId, Supplier<List<ItemStack>> extraItems, float ignobleChance,
            Map<IChromosome<?>, IAllele> alleles);

    IHiveBuilder addCustomDrop(IHiveDrop drop);

    void setGenerationChance(float generationChance);
}
