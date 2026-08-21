package com.leon1236.reforestry.gendustry.item.data;

import java.util.IdentityHashMap;
import java.util.Map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public record GeneticTemplateInfo(ISpeciesType<?, ?> type, Map<IChromosome<?>, IAllele> alleles) {
	public static final Codec<GeneticTemplateInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Identifier.CODEC.fieldOf("type").forGetter(info -> info.type().id()),
			Codec.unboundedMap(Identifier.CODEC, IAllele.CODEC).fieldOf("alleles").forGetter(GeneticTemplateInfo::encodeAlleles)
	).apply(instance, GeneticTemplateInfo::create));

	public static final StreamCodec<RegistryFriendlyByteBuf, GeneticTemplateInfo> STREAM_CODEC =
			ByteBufCodecs.fromCodecWithRegistries(CODEC);

	public GeneticTemplateInfo {
		alleles = Map.copyOf(alleles);
	}

	public GeneticTemplateInfo withAlleles(Map<IChromosome<?>, IAllele> extra) {
		IdentityHashMap<IChromosome<?>, IAllele> merged = new IdentityHashMap<>(this.alleles);
		merged.putAll(extra);
		return new GeneticTemplateInfo(this.type, merged);
	}

	public boolean isComplete() {
		return this.type.getKaryotype().chromosomes().size() == this.alleles.size();
	}

	private static Map<Identifier, IAllele> encodeAlleles(GeneticTemplateInfo info) {
		IdentityHashMap<Identifier, IAllele> encoded = new IdentityHashMap<>(info.alleles.size());
		info.alleles.forEach((chromosome, allele) -> encoded.put(chromosome.id(), allele));
		return encoded;
	}

	private static GeneticTemplateInfo create(Identifier typeId, Map<Identifier, IAllele> encoded) {
		return decode(typeId, encoded).getOrThrow();
	}

	private static DataResult<GeneticTemplateInfo> decode(Identifier typeId, Map<Identifier, IAllele> encoded) {
		ISpeciesType<?, ?> type = IForestryApi.get().getGeneticManager().getSpeciesTypeSafe(typeId);
		if (type == null) {
			return DataResult.error(() -> "Unknown species type: " + typeId);
		}
		IdentityHashMap<IChromosome<?>, IAllele> alleles = new IdentityHashMap<>(encoded.size());
		for (Map.Entry<Identifier, IAllele> entry : encoded.entrySet()) {
			var chromosome = type.getKaryotype().getChromosome(entry.getKey());
			if (chromosome.isEmpty()) {
				return DataResult.error(() -> "Unknown chromosome: " + entry.getKey() + " for type " + typeId);
			}
			alleles.put(chromosome.get(), entry.getValue());
		}
		return DataResult.success(new GeneticTemplateInfo(type, alleles));
	}
}
