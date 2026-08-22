package com.leon1236.reforestry.core.genetics;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.ITaxon;
import com.leon1236.reforestry.api.genetics.TaxonomicRank;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public record TaxonDefinition(@Nullable String parent, String name, @Nullable TaxonomicRank rank,
		@Nullable Identifier type, Map<IChromosome<?>, ITaxon.TaxonAllele> allelesByChromosome) {

	public TaxonDefinition(@Nullable String parent, String name, @Nullable TaxonomicRank rank) {
		this(parent, name, rank, null, Map.of());
	}

	private record Unresolved(Optional<String> parent, String name, Optional<TaxonomicRank> rank,
			Optional<Identifier> type, Optional<Dynamic<?>> alleles) {
	}

	private static final Codec<Unresolved> UNRESOLVED_CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.STRING.optionalFieldOf("parent").forGetter(Unresolved::parent),
			Codec.STRING.fieldOf("name").forGetter(Unresolved::name),
			TaxonomicRank.CODEC.optionalFieldOf("rank").forGetter(Unresolved::rank),
			Identifier.CODEC.optionalFieldOf("type").forGetter(Unresolved::type),
			Codec.PASSTHROUGH.optionalFieldOf("alleles").forGetter(Unresolved::alleles)
	).apply(instance, Unresolved::new));

	public static final Codec<TaxonDefinition> CODEC = UNRESOLVED_CODEC.flatXmap(TaxonDefinition::resolve,
			definition -> DataResult.success(toUnresolved(definition)));

	private static DataResult<TaxonDefinition> resolve(Unresolved unresolved) {
		String parent = unresolved.parent.orElse(null);
		TaxonomicRank rank = unresolved.rank.orElse(null);
		Identifier type = unresolved.type.orElse(null);
		Map<IChromosome<?>, ITaxon.TaxonAllele> alleles = Map.of();
		if (unresolved.alleles.isPresent()) {
			if (type == null) {
				return DataResult.error(() -> "Taxon '" + unresolved.name
						+ "' declares alleles but no 'type' to resolve their chromosomes against");
			}
			IKaryotype karyotype = karyotypeFor(type);
			if (karyotype == null) {
				return DataResult.error(() -> "Taxon '" + unresolved.name + "' references unknown species type '"
						+ type + "'");
			}
			Object raw = unresolved.alleles.get().getValue();
			if (!(raw instanceof JsonElement element) || !element.isJsonObject()) {
				return DataResult.error(() -> "Taxon '" + unresolved.name + "' alleles must be a JSON object");
			}
			alleles = parseAlleles(karyotype, element.getAsJsonObject());
		}
		return DataResult.success(new TaxonDefinition(parent, unresolved.name, rank, type, alleles));
	}

	private static Unresolved toUnresolved(TaxonDefinition definition) {
		Optional<Dynamic<?>> alleles = Optional.empty();
		if (!definition.allelesByChromosome.isEmpty()) {
			JsonObject json = new JsonObject();
			for (var entry : definition.allelesByChromosome.entrySet()) {
				json.add(entry.getKey().id().toString(), encodeAllele(entry.getValue()));
			}
			alleles = Optional.of(new Dynamic<>(JsonOps.INSTANCE, json));
		}
		return new Unresolved(Optional.ofNullable(definition.parent), definition.name,
				Optional.ofNullable(definition.rank), Optional.ofNullable(definition.type), alleles);
	}

	private static JsonObject encodeAllele(ITaxon.TaxonAllele taxonAllele) {
		JsonObject object = new JsonObject();
		if (taxonAllele.reference() != null) {
			object.addProperty("value", taxonAllele.reference().toString());
		} else if (taxonAllele.allele() != null) {
			object.addProperty("value", taxonAllele.allele().alleleId().toString());
		}
		object.addProperty("dominant", taxonAllele.required());
		return object;
	}

	private static Map<IChromosome<?>, ITaxon.TaxonAllele> parseAlleles(IKaryotype karyotype, JsonObject allelesJson) {
		Map<Identifier, ITaxon.TaxonAllele> byId = GenomeCodecs.parseTaxonAlleles(karyotype, allelesJson);
		Map<IChromosome<?>, ITaxon.TaxonAllele> byChromosome = new LinkedHashMap<>();
		for (var entry : byId.entrySet()) {
			karyotype.getChromosome(entry.getKey())
					.ifPresent(chromosome -> byChromosome.put(chromosome, entry.getValue()));
		}
		return Map.copyOf(byChromosome);
	}

	@Nullable
	private static IKaryotype karyotypeFor(Identifier typeId) {
		ISpeciesType<?, ?> type = IForestryApi.get().getGeneticManager().getSpeciesTypeSafe(typeId);
		return type == null ? null : type.getKaryotype();
	}

	public Map<IChromosome<?>, ITaxon.TaxonAllele> alleles() {
		return allelesByChromosome;
	}
}
