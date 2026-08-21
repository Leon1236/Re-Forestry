package com.leon1236.reforestry.core.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import it.unimi.dsi.fastutil.objects.Reference2FloatMap;
import it.unimi.dsi.fastutil.objects.Reference2FloatOpenHashMap;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.IKaryotypeBuilder;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.plugin.ISpeciesTypeBuilder;
import com.leon1236.reforestry.core.genetics.KaryotypeBuilder;

public final class SpeciesTypeBuilderImpl implements ISpeciesTypeBuilder {
	private final List<Consumer<IKaryotypeBuilder>> karyotypeCustomizers = new ArrayList<>();
	private final List<ILifeStage> stages = new ArrayList<>();
	private final List<Consumer<Reference2FloatMap<Item>>> researchMaterials = new ArrayList<>();
	private ILifeStage defaultStage;
	@Nullable
	private IKaryotype karyotype;

	@Override
	public ISpeciesTypeBuilder setKaryotype(Consumer<IKaryotypeBuilder> karyotype) {
		karyotypeCustomizers.add(karyotype);
		return this;
	}

	@Override
	public ISpeciesTypeBuilder setKaryotype(IKaryotype karyotype) {
		this.karyotype = karyotype;
		return this;
	}

	@Override
	public ISpeciesTypeBuilder addStages(ILifeStage... stages) {
		this.stages.addAll(List.of(stages));
		return this;
	}

	@Override
	public ISpeciesTypeBuilder addResearchMaterials(Consumer<Reference2FloatMap<Item>> materials) {
		researchMaterials.add(materials);
		return this;
	}

	@Override
	public ISpeciesTypeBuilder setDefaultStage(ILifeStage stage) {
		this.defaultStage = stage;
		return this;
	}

	@Override
	public ILifeStage getDefaultStage() {
		return defaultStage != null ? defaultStage : (stages.isEmpty() ? null : stages.getFirst());
	}

	@Override
	public List<ILifeStage> getStages() {
		return List.copyOf(stages);
	}

	@Override
	public void buildResearchMaterials(Reference2FloatMap<Item> materialMap) {
		for (Consumer<Reference2FloatMap<Item>> consumer : researchMaterials) {
			consumer.accept(materialMap);
		}
	}

	@Nullable
	public IKaryotype buildKaryotype(Identifier id) {
		if (!karyotypeCustomizers.isEmpty()) {
			KaryotypeBuilder builder = new KaryotypeBuilder();
			for (Consumer<IKaryotypeBuilder> customizer : karyotypeCustomizers) {
				customizer.accept(builder);
			}
			return builder.create(id);
		}
		return karyotype;
	}

	public List<Consumer<IKaryotypeBuilder>> karyotypeCustomizers() {
		return karyotypeCustomizers;
	}

	public Reference2FloatMap<Item> researchMaterialMap() {
		Reference2FloatOpenHashMap<Item> map = new Reference2FloatOpenHashMap<>();
		buildResearchMaterials(map);
		return map;
	}
}
