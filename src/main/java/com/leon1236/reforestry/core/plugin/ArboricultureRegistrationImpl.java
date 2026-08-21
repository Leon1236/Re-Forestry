package com.leon1236.reforestry.core.plugin;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.api.arboriculture.genetics.ITreeEffect;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.api.plugin.ITreeSpeciesBuilder;
import com.leon1236.reforestry.arboriculture.TreeManager;
import com.leon1236.reforestry.arboriculture.charcoal.CharcoalManager;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

public final class ArboricultureRegistrationImpl implements IArboricultureRegistration {
	private final CharcoalManager charcoalPitWalls = new CharcoalManager();
	private final Map<Block, Block> refractoryWaxables = new LinkedHashMap<>();

	@Override
	public ITreeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int escritoireColor, IWoodType woodType) {
		return ArboricultureGenetics.registerSpecies(id, genus, species, dominant, escritoireColor, woodType);
	}

	@Override
	public void registerFruit(Identifier id, IFruit fruit) {
		if (!id.equals(fruit.id())) {
			throw new IllegalArgumentException("Fruit id mismatch: registered " + id + " but fruit reports " + fruit.id());
		}
		TreeChromosomes.FRUIT.registerValue(id, fruit);
		AlleleManager.INSTANCE.registryAllele(fruit, fruit.isDominant());
	}

	@Override
	public void registerTreeEffect(Identifier id, ITreeEffect effect) {
		if (!id.equals(effect.id())) {
			throw new IllegalArgumentException("Tree effect id mismatch: registered " + id + " but effect reports " + effect.id());
		}
		TreeChromosomes.EFFECT.registerValue(id, effect);
		AlleleManager.INSTANCE.registryAllele(effect, effect.isDominant());
	}

	@Override
	public void registerRefractoryWaxable(Block block, Block waxedForm) {
		refractoryWaxables.put(block, waxedForm);
	}

	@Override
	public void registerCharcoalPitWall(BlockState state, int charcoal) {
		this.charcoalPitWalls.addWall(state, charcoal);
	}

	public CharcoalManager getCharcoalManager() {
		return this.charcoalPitWalls;
	}

	public TreeManager buildTreeManager() {
		return new TreeManager(ImmutableMap.copyOf(refractoryWaxables), charcoalPitWalls);
	}
}
