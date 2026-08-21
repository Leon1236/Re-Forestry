package com.leon1236.reforestry.api.plugin;

import java.util.List;
import java.util.function.Consumer;

import it.unimi.dsi.fastutil.objects.Reference2FloatMap;

import net.minecraft.world.item.Item;

import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.IKaryotypeBuilder;
import com.leon1236.reforestry.api.genetics.ILifeStage;

public interface ISpeciesTypeBuilder {
	ISpeciesTypeBuilder setKaryotype(Consumer<IKaryotypeBuilder> karyotype);

	ISpeciesTypeBuilder setKaryotype(IKaryotype karyotype);

	ISpeciesTypeBuilder addStages(ILifeStage... stages);

	ISpeciesTypeBuilder addResearchMaterials(Consumer<Reference2FloatMap<Item>> materials);

	ISpeciesTypeBuilder setDefaultStage(ILifeStage stage);

	ILifeStage getDefaultStage();

	List<ILifeStage> getStages();

	void buildResearchMaterials(Reference2FloatMap<Item> materialMap);
}
