package com.leon1236.reforestry.api.plugin;

import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.api.arboriculture.genetics.ITreeEffect;

public interface IArboricultureRegistration {
	ITreeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int escritoireColor, IWoodType woodType);

	default ITreeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, TextColor escritoireColor, IWoodType woodType) {
		return registerSpecies(id, genus, species, dominant, escritoireColor.getValue(), woodType);
	}

	void registerFruit(Identifier id, IFruit fruit);

	void registerTreeEffect(Identifier id, ITreeEffect effect);

	void registerRefractoryWaxable(Block block, Block waxedForm);

	void registerCharcoalPitWall(BlockState state, int charcoal);

	default void registerCharcoalPitWall(Block block, int charcoal) {
		for (BlockState state : block.getStateDefinition().getPossibleStates()) {
			registerCharcoalPitWall(state, charcoal);
		}
	}
}
