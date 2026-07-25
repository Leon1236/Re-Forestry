package com.leon1236.reforestry.core.genetics.mutations;

import net.minecraft.resources.Identifier;

public record MutationPair(Identifier first, Identifier second) {
    public static MutationPair of(Identifier a, Identifier b) {
        return a.compareTo(b) <= 0 ? new MutationPair(a, b) : new MutationPair(b, a);
    }
}
