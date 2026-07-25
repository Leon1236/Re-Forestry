package com.leon1236.reforestry.api.plugin;

import net.minecraft.resources.Identifier;

public interface IMutationsRegistration {
    IMutationBuilder add(Identifier firstParent, Identifier secondParent, float chancePercent);

    IMutationBuilder get(Identifier firstParent, Identifier secondParent);
}
