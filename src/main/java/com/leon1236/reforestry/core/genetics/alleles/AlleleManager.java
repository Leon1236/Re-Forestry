package com.leon1236.reforestry.core.genetics.alleles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.IAlleleManager;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.IKaryotypeBuilder;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.alleles.IBooleanAllele;
import com.leon1236.reforestry.api.genetics.alleles.IFloatAllele;
import com.leon1236.reforestry.api.genetics.alleles.IIntegerAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.alleles.IValueAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.core.genetics.KaryotypeBuilder;
import com.leon1236.reforestry.core.genetics.chromosomes.Chromosomes;

public final class AlleleManager implements IAlleleManager {
    public static final AlleleManager INSTANCE = new AlleleManager();

    private final Map<Identifier, IAllele> allelesById = new LinkedHashMap<>();
    private final Map<Identifier, IKaryotype> karyotypesById = new LinkedHashMap<>();

    private final Codec<IAllele> alleleCodec = Identifier.CODEC.flatXmap(
            id -> {
                IAllele allele = allelesById.get(id);
                return allele != null ? DataResult.success(allele) : DataResult.error(() -> "Unknown allele: " + id);
            },
            allele -> DataResult.success(allele.alleleId()));

    private AlleleManager() {
    }

    @Override
    public IBooleanAllele booleanAllele(boolean value, boolean dominant) {
        Identifier id = ReForestry.id(value + "b" + (dominant ? "d" : ""));
        return intern(id, () -> new BooleanAllele(id, value, dominant));
    }

    @Override
    public IFloatAllele floatAllele(float value, boolean dominant) {
        Identifier id = ReForestry.id(value + "f" + (dominant ? "d" : ""));
        return intern(id, () -> new FloatAllele(id, value, dominant));
    }

    @Override
    public IIntegerAllele integerAllele(int value, boolean dominant) {
        Identifier id = ReForestry.id(value + "i" + (dominant ? "d" : ""));
        return intern(id, () -> new IntegerAllele(id, value, dominant));
    }

    @Override
    public <V> IValueAllele<V> valueAllele(String baseId, V value, boolean dominant) {
        Identifier id = ReForestry.id(baseId + (dominant ? "d" : ""));
        return intern(id, () -> new ValueAllele<>(id, value, dominant));
    }

    @Override
    public <V extends IRegistryAlleleValue> IRegistryAllele<V> registryAllele(V value, boolean dominant) {
        Identifier id = value.id();
        return intern(id, () -> new RegistryAllele<>(id, value, dominant));
    }

    @SuppressWarnings("unchecked")
    private <A extends IAllele> A intern(Identifier id, Supplier<A> factory) {
        IAllele existing = allelesById.get(id);
        if (existing != null) {
            return (A) existing;
        }
        A allele = factory.get();
        allelesById.put(id, allele);
        return allele;
    }

    @Override
    public IAllele getAllele(Identifier id) {
        return allelesById.get(id);
    }

    @Override
    public IChromosome<IBooleanAllele> booleanChromosome(Identifier id) {
        return Chromosomes.booleanChromosome(id);
    }

    @Override
    public IChromosome<IFloatAllele> floatChromosome(Identifier id) {
        return Chromosomes.floatChromosome(id);
    }

    @Override
    public IChromosome<IIntegerAllele> integerChromosome(Identifier id) {
        return Chromosomes.integerChromosome(id);
    }

    @Override
    public <V> IChromosome<IValueAllele<V>> valueChromosome(Identifier id) {
        return Chromosomes.valueChromosome(id);
    }

    @Override
    public <V extends IRegistryAlleleValue> IRegistryChromosome<V> registryChromosome(Identifier id) {
        return Chromosomes.registryChromosome(id);
    }

    @Override
    public IKaryotypeBuilder karyotypeBuilder() {
        return new KaryotypeBuilder();
    }

    @Override
    public void registerKaryotype(IKaryotype karyotype) {
        if (karyotypesById.putIfAbsent(karyotype.id(), karyotype) != null) {
            throw new IllegalStateException("Duplicate karyotype id: " + karyotype.id());
        }
    }

    @Override
    public IKaryotype getKaryotype(Identifier id) {
        IKaryotype karyotype = karyotypesById.get(id);
        if (karyotype == null) {
            throw new IllegalArgumentException("Unknown karyotype: " + id);
        }
        return karyotype;
    }

    @Override
    public Codec<IAllele> alleleCodec() {
        return alleleCodec;
    }
}
