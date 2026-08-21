package com.leon1236.reforestry.core.genetics;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import com.leon1236.reforestry.api.genetics.TaxonomicRank;

public record TaxonDefinition(@Nullable String parent, String name, @Nullable TaxonomicRank rank) {
	public static final Codec<TaxonDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.STRING.optionalFieldOf("parent").forGetter(def -> Optional.ofNullable(def.parent)),
			Codec.STRING.fieldOf("name").forGetter(TaxonDefinition::name),
			TaxonomicRank.CODEC.optionalFieldOf("rank").forGetter(def -> Optional.ofNullable(def.rank))
	).apply(instance, (parent, name, rank) -> new TaxonDefinition(parent.orElse(null), name, rank.orElse(null))));
}
