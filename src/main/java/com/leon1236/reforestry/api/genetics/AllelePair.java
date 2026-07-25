package com.leon1236.reforestry.api.genetics;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.RandomSource;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;

public record AllelePair<A extends IAllele>(A active, A inactive) {
    public static final Codec<AllelePair<IAllele>> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IAllele.CODEC.fieldOf("active").forGetter(AllelePair::active),
            IAllele.CODEC.fieldOf("inactive").forGetter(AllelePair::inactive)
    ).apply(instance, AllelePair::new));

    public static <A extends IAllele> AllelePair<A> create(A first, A second) {
        return first.dominant() || !second.dominant() ? new AllelePair<>(first, second) : new AllelePair<>(second, first);
    }

    public AllelePair<A> inheritOther(RandomSource random, AllelePair<A> other) {
        A fromThis = random.nextBoolean() ? active : inactive;
        A fromOther = random.nextBoolean() ? other.active : other.inactive;
        return random.nextBoolean() ? create(fromThis, fromOther) : create(fromOther, fromThis);
    }

    public AllelePair<A> inheritHaploid(RandomSource random) {
        A picked = random.nextBoolean() ? active : inactive;
        return new AllelePair<>(picked, picked);
    }
}
