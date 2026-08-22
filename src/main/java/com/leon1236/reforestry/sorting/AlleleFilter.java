package com.leon1236.reforestry.sorting;

import java.util.Objects;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;

public class AlleleFilter {
	@Nullable
	public Identifier activeSpecies;

	@Nullable
	public Identifier inactiveSpecies;

	public boolean isValid(Identifier active, Identifier inactive) {
		return (this.activeSpecies == null || this.activeSpecies.equals(active))
				&& (this.inactiveSpecies == null || this.inactiveSpecies.equals(inactive));
	}

	public boolean isEmpty() {
		return this.activeSpecies == null && this.inactiveSpecies == null;
	}

	public boolean setActive(@Nullable Identifier species) {
		boolean changed = !Objects.equals(this.activeSpecies, species);
		this.activeSpecies = species;
		return changed;
	}

	public boolean setInactive(@Nullable Identifier species) {
		boolean changed = !Objects.equals(this.inactiveSpecies, species);
		this.inactiveSpecies = species;
		return changed;
	}
}
