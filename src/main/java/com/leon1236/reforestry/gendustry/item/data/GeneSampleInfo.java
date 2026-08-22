package com.leon1236.reforestry.gendustry.item.data;

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

public record GeneSampleInfo(ISpeciesType<?, ?> type, IChromosome<?> chromosome, IAllele allele) {
	public static final Codec<GeneSampleInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Identifier.CODEC.fieldOf("type").forGetter(info -> info.type().id()),
			Identifier.CODEC.fieldOf("chromosome").forGetter(info -> info.chromosome().id()),
			IAllele.CODEC.fieldOf("allele").forGetter(GeneSampleInfo::allele)
	).apply(instance, GeneSampleInfo::create));

	public static final StreamCodec<RegistryFriendlyByteBuf, GeneSampleInfo> STREAM_CODEC =
			ByteBufCodecs.fromCodecWithRegistries(CODEC);

	private static GeneSampleInfo create(Identifier typeId, Identifier chromosomeId, IAllele allele) {
		return decode(typeId, chromosomeId, allele).getOrThrow();
	}

	private static DataResult<GeneSampleInfo> decode(Identifier typeId, Identifier chromosomeId, IAllele allele) {
		ISpeciesType<?, ?> type = IForestryApi.get().getGeneticManager().getSpeciesTypeSafe(typeId);
		if (type == null) {
			return DataResult.error(() -> "Unknown species type: " + typeId);
		}
		return type.getKaryotype().getChromosome(chromosomeId)
				.<DataResult<GeneSampleInfo>>map(chromosome -> DataResult.success(new GeneSampleInfo(type, chromosome, allele)))
				.orElseGet(() -> DataResult.error(() -> "Unknown chromosome: " + chromosomeId + " for type " + typeId));
	}
}
