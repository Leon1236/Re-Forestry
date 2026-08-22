package com.leon1236.reforestry.core.genetics;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.Vec3i;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.ITaxon;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

public final class GenomeCodecs {
	private GenomeCodecs() {
	}

	public static Map<Identifier, ITaxon.TaxonAllele> parseTaxonAlleles(IKaryotype karyotype, JsonObject allelesJson) {
		Map<Identifier, ITaxon.TaxonAllele> parsed = new LinkedHashMap<>();
		for (Map.Entry<String, JsonElement> entry : allelesJson.entrySet()) {
			Identifier chromosomeId = Identifier.tryParse(entry.getKey());
			if (chromosomeId == null) {
				ReForestry.LOGGER.warn("Taxon allele skipped: invalid chromosome id '{}'", entry.getKey());
				continue;
			}
			IChromosome<?> chromosome = karyotype.getChromosome(chromosomeId).orElse(null);
			if (chromosome == null) {
				ReForestry.LOGGER.warn("Taxon allele skipped: unknown chromosome '{}' for karyotype {}", chromosomeId,
						karyotype.id());
				continue;
			}
			if (!entry.getValue().isJsonObject()) {
				ReForestry.LOGGER.warn("Taxon allele skipped: chromosome '{}' is not an object", chromosomeId);
				continue;
			}
			ITaxon.TaxonAllele taxonAllele = parseTaxonAllele(chromosome, entry.getValue().getAsJsonObject());
			if (taxonAllele != null) {
				parsed.put(chromosomeId, taxonAllele);
			}
		}
		return parsed;
	}

	private static JsonObject encodeTaxonAllele(ITaxon.TaxonAllele taxonAllele) {
		JsonObject object = new JsonObject();
		if (taxonAllele.reference() != null) {
			object.addProperty("value", taxonAllele.reference().toString());
		} else if (taxonAllele.allele() != null) {
			object.addProperty("value", taxonAllele.allele().alleleId().toString());
		}
		if (taxonAllele.required()) {
			object.addProperty("dominant", true);
		}
		return object;
	}

	private static ITaxon.TaxonAllele parseTaxonAllele(IChromosome<?> chromosome, JsonObject object) {
		boolean dominant = object.has("dominant") && object.get("dominant").getAsBoolean();
		JsonElement valueElement = object.get("value");
		if (valueElement == null) {
			ReForestry.LOGGER.warn("Taxon allele skipped: chromosome '{}' missing value", chromosome.id());
			return null;
		}
		if (chromosome instanceof IRegistryChromosome<?> registryChromosome) {
			Identifier reference = parseIdentifier(valueElement);
			if (reference == null) {
				return null;
			}
			return ITaxon.TaxonAllele.reference(reference, dominant);
		}
		IAllele allele = parseDataAllele(chromosome, valueElement, dominant);
		return allele == null ? null : ITaxon.TaxonAllele.data(allele, dominant);
	}

	private static IAllele parseDataAllele(IChromosome<?> chromosome, JsonElement valueElement, boolean dominant) {
		if (chromosome.id().equals(com.leon1236.reforestry.ReForestry.id("temperature_tolerance"))
				|| chromosome.id().equals(com.leon1236.reforestry.ReForestry.id("humidity_tolerance"))) {
			if (!valueElement.isJsonPrimitive()) {
				return null;
			}
			ToleranceType tolerance = ToleranceType.valueOf(valueElement.getAsString());
			return AlleleManager.INSTANCE.valueAllele(chromosome.id().getPath(), tolerance, dominant);
		}
		if (chromosome.id().equals(com.leon1236.reforestry.ReForestry.id("territory"))) {
			return parseTerritoryAllele(valueElement, dominant);
		}
		if (valueElement.isJsonPrimitive()) {
			var primitive = valueElement.getAsJsonPrimitive();
			if (primitive.isBoolean()) {
				return AlleleManager.INSTANCE.booleanAllele(primitive.getAsBoolean(), dominant);
			}
			if (primitive.isNumber()) {
				Number number = primitive.getAsNumber();
				if (number instanceof Float || number instanceof Double) {
					return AlleleManager.INSTANCE.floatAllele(number.floatValue(), dominant);
				}
				return AlleleManager.INSTANCE.integerAllele(number.intValue(), dominant);
			}
		}
		ReForestry.LOGGER.warn("Taxon allele skipped: unsupported value for chromosome '{}'", chromosome.id());
		return null;
	}

	private static IAllele parseTerritoryAllele(JsonElement valueElement, boolean dominant) {
		if (!valueElement.isJsonArray() || valueElement.getAsJsonArray().size() != 3) {
			return null;
		}
		int x = valueElement.getAsJsonArray().get(0).getAsInt();
		int y = valueElement.getAsJsonArray().get(1).getAsInt();
		int z = valueElement.getAsJsonArray().get(2).getAsInt();
		return AlleleManager.INSTANCE.valueAllele("territory", new Vec3i(x, y, z), dominant);
	}

	private static Identifier parseIdentifier(JsonElement valueElement) {
		if (!valueElement.isJsonPrimitive() || !valueElement.getAsJsonPrimitive().isString()) {
			ReForestry.LOGGER.warn("Taxon allele skipped: registry value must be a string id");
			return null;
		}
		Identifier id = Identifier.tryParse(valueElement.getAsString());
		if (id == null) {
			ReForestry.LOGGER.warn("Taxon allele skipped: invalid registry id '{}'", valueElement.getAsString());
		}
		return id;
	}
}
