package com.leon1236.reforestry.extratrees.plugin;

import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.client.plugin.IClientRegistration;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.api.plugin.IGeneticRegistration;
import com.leon1236.reforestry.api.plugin.ILepidopterologyRegistration;
import com.leon1236.reforestry.extratrees.genetics.ExtraTreesFruits;
import com.leon1236.reforestry.extratrees.genetics.ExtraTreesMothIds;
import com.leon1236.reforestry.extratrees.genetics.ExtraTreesMothSpecies;
import com.leon1236.reforestry.extratrees.genetics.ExtraTreesTreeSpecies;

public class ExtraTreesForestryPlugin implements IForestryPlugin {
	@Override
	public Identifier id() {
		return ReForestry.id("extra_trees");
	}

	@Override
	public boolean shouldLoad() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("extra_trees"));
	}

	@Override
	public void registerGenetics(IGeneticRegistration registration) {
		ExtraTreesMothSpecies.registerTaxa(registration);
	}

	@Override
	public void registerArboriculture(IArboricultureRegistration registration) {
		ExtraTreesFruits.register(registration);
		ExtraTreesTreeSpecies.register(registration);
	}

	@Override
	public void registerLepidopterology(ILepidopterologyRegistration registration) {
		ExtraTreesMothSpecies.register(registration);
	}

	@Override
	public void registerClient(Consumer<Consumer<IClientRegistration>> registrar) {
		registrar.accept(client -> {
			for (Identifier speciesId : ExtraTreesMothIds.ALL) {
				String path = speciesId.getPath();
				client.setButterflySprites(
						speciesId,
						Identifier.fromNamespaceAndPath(speciesId.getNamespace(), "item/butterfly/" + path),
						Identifier.fromNamespaceAndPath(speciesId.getNamespace(), "textures/entity/butterfly/" + path + ".png"));
			}
		});
	}
}
