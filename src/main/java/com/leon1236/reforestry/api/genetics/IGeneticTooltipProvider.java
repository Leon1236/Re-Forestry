package com.leon1236.reforestry.api.genetics;

import java.util.List;

import net.minecraft.network.chat.Component;

public interface IGeneticTooltipProvider {
	void addTooltip(List<Component> tooltip, IGenome genome);
}
