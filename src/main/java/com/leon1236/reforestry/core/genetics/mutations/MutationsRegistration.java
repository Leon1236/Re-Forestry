package com.leon1236.reforestry.core.genetics.mutations;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.plugin.IMutationBuilder;
import com.leon1236.reforestry.api.plugin.IMutationsRegistration;

public final class MutationsRegistration implements IMutationsRegistration {
    private final Map<MutationPair, MutationBuilder> builders = new LinkedHashMap<>();

    @Override
    public IMutationBuilder add(Identifier firstParent, Identifier secondParent, float chancePercent) {
        if (firstParent.equals(secondParent)) {
            throw new IllegalArgumentException("A bee cannot mutate with itself: " + firstParent);
        }
        MutationPair pair = MutationPair.of(firstParent, secondParent);
        if (builders.containsKey(pair)) {
            throw new IllegalArgumentException("A mutation between " + firstParent + " and " + secondParent + " is already registered for this species");
        }
        MutationBuilder builder = new MutationBuilder(firstParent, secondParent, chancePercent);
        builders.put(pair, builder);
        return builder;
    }

    @Override
    public IMutationBuilder get(Identifier firstParent, Identifier secondParent) {
        MutationBuilder builder = builders.get(MutationPair.of(firstParent, secondParent));
        if (builder == null) {
            throw new IllegalArgumentException("No mutation registered between " + firstParent + " and " + secondParent);
        }
        return builder;
    }

    public Collection<MutationBuilder> builders() {
        return builders.values();
    }
}
