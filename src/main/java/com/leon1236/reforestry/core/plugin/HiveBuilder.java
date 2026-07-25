package com.leon1236.reforestry.core.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.hives.IHive;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDefinition;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDrop;
import com.leon1236.reforestry.api.plugin.IHiveBuilder;
import com.leon1236.reforestry.apiculture.genetics.HiveDrop;
import com.leon1236.reforestry.apiculture.hives.Hive;

public final class HiveBuilder implements IHiveBuilder {
    private final IHiveDefinition definition;
    private final List<IHiveDrop> drops = new ArrayList<>();
    private float generationChance;

    public HiveBuilder(IHiveDefinition definition) {
        this.definition = definition;
        this.generationChance = definition.getGenChance();
    }

    @Override
    public IHiveBuilder addDrop(double chance, Identifier speciesId, Supplier<List<ItemStack>> extraItems,
            float ignobleChance) {
        drops.add(new HiveDrop(chance, speciesId, extraItems, ignobleChance));
        return this;
    }

    @Override
    public IHiveBuilder addCustomDrop(IHiveDrop drop) {
        drops.add(drop);
        return this;
    }

    @Override
    public void setGenerationChance(float generationChance) {
        this.generationChance = generationChance;
    }

    public IHive build() {
        return new Hive(definition, generationChance, List.copyOf(drops));
    }
}
