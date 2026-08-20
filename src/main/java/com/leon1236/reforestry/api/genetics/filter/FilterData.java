package com.leon1236.reforestry.api.genetics.filter;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.IGenome;

public record FilterData(Identifier typeId, IGenome genome, String stage) {
}
