package com.leon1236.reforestry.api.genetics;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.mutations.IMutationCondition;

public interface IMutation {
	Identifier typeId();

	Identifier firstParent();

	Identifier secondParent();

	Identifier result();

	float getChance();

	List<IMutationCondition> getConditions();

	List<Component> getSpecialConditions();

	boolean isPartner(Identifier speciesId);

	Identifier getPartner(Identifier speciesId);

	boolean isSecret();
}
