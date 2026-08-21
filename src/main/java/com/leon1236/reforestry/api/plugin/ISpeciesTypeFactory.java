package com.leon1236.reforestry.api.plugin;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.ISpeciesType;

public interface ISpeciesTypeFactory {
	ISpeciesType<?, ?> create(@Nullable IKaryotype karyotype, ISpeciesTypeBuilder builder);
}
