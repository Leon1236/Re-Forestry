package com.leon1236.reforestry.core.compat.jei;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.api.arboriculture.genetics.ITree;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;
import com.leon1236.reforestry.gendustry.blockentity.AbstractMutatronBlockEntity;

public final class GeneticsJeiHelper {
	private GeneticsJeiHelper() {
	}

	public static ItemStack createAnalyzedStack(
			ISpeciesType<?, ?> speciesType,
			ILifeStage stage,
			Identifier speciesId,
			@Nullable IGenome genome
	) {
		ISpecies<?> species = speciesType.getSpecies(speciesId);
		IGenome resolved = genome != null ? genome : species.getDefaultGenome();
		IIndividual individual = species.createIndividual(resolved);
		individual.analyze();
		return individual.createStack(stage);
	}

	public static IGenome mutatedGenome(Mutation mutation, ISpeciesType<?, ?> speciesType) {
		return AbstractMutatronBlockEntity.createMutatedGenome(mutation, speciesType);
	}

	public static List<ItemStack> allLifeStages(ISpeciesType<?, ?> speciesType, Identifier speciesId, @Nullable IGenome genome) {
		List<ItemStack> stacks = new ArrayList<>(speciesType.getLifeStages().size());
		for (ILifeStage stage : speciesType.getLifeStages()) {
			stacks.add(createAnalyzedStack(speciesType, stage, speciesId, genome));
		}
		return stacks;
	}

	public static void resolveProducts(
			ISpecies<?> species,
			List<IProduct> productsOut,
			List<IProduct> specialtiesOut
	) {
		if (species instanceof IBeeSpecies bee) {
			productsOut.addAll(bee.products());
			specialtiesOut.addAll(bee.specialties());
		} else if (species instanceof com.leon1236.reforestry.api.arboriculture.ITreeSpecies treeSpecies) {
			ITree tree = treeSpecies.createIndividual();
			productsOut.addAll(tree.getProducts());
			specialtiesOut.addAll(tree.getSpecialties());
		} else if (species instanceof IButterflySpecies butterfly) {
			productsOut.addAll(butterfly.getButterflyLoot());
			specialtiesOut.addAll(butterfly.getCaterpillarProducts());
		}
	}

	public static boolean hasProducts(ISpecies<?> species) {
		List<IProduct> products = new ArrayList<>();
		List<IProduct> specialties = new ArrayList<>();
		resolveProducts(species, products, specialties);
		return !products.isEmpty() || !specialties.isEmpty();
	}

	public static void drawCentered(GuiGraphicsExtractor graphics, Component text, int x, int y, int color) {
		Font font = Minecraft.getInstance().font;
		String[] lines = text.getString().split(" ");
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			int width = font.width(line);
			graphics.text(font, line, Math.round(x - width / 2f), y + i * font.lineHeight, color, false);
		}
	}

	public static String formatPercentage(float chance) {
		return String.valueOf(Math.round(chance * 100f));
	}
}
