package com.leon1236.reforestry.api.plugin;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.hives.IHiveDrop;

public interface IHiveBuilder {
    default IHiveBuilder addDrop(double chance, Identifier speciesId, Supplier<List<ItemStack>> extraItems) {
        return addDrop(chance, speciesId, extraItems, 0f);
    }

    IHiveBuilder addDrop(double chance, Identifier speciesId, Supplier<List<ItemStack>> extraItems, float ignobleChance);

    IHiveBuilder addCustomDrop(IHiveDrop drop);

    void setGenerationChance(float generationChance);
}
