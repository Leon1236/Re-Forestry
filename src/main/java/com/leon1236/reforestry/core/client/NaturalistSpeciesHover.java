package com.leon1236.reforestry.core.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.core.genetics.mutations.EnumMutateChance;
import com.leon1236.reforestry.core.genetics.root.BreedingTrackerManager;

@Environment(EnvType.CLIENT)
public final class NaturalistSpeciesHover {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/apiaristinventory.png");
	private static final int TEXT_COLOR = 0xFF404040;
	private static final int LINE_HEIGHT = 12;
	private static final int SPECIES_X = 10;
	private static final int MUTATION_COLUMN_START = 10;
	private static final int MUTATION_COLUMN_WIDTH = 16;
	private static final int MUTATION_COLUMN_WRAP = 75;
	private static final int MUTATION_ROW_HEIGHT = 18;
	private static final int PURE_MAX_MUTATIONS = 25;
	private static final int HYBRID_MAX_MUTATIONS = 10;

	private final CycleTimer timer = new CycleTimer(0);
	private final HashMap<Identifier, ItemStack> iconStacks = new HashMap<>();
	private Identifier cachedTypeId;
	private int lineY;

	public void display(GuiGraphicsExtractor graphics, Font font, Identifier speciesTypeId, ItemStack hovered) {
		this.timer.onDraw();

		if (hovered.isEmpty() || !IndividualItems.isIndividual(hovered)) {
			NaturalistBreedingStatistics.display(graphics, font, speciesTypeId, SPECIES_X, 32);
			return;
		}

		Identifier itemType = IndividualItems.getSpeciesTypeId(hovered);
		if (itemType == null || !itemType.equals(speciesTypeId)) {
			NaturalistBreedingStatistics.display(graphics, font, speciesTypeId, SPECIES_X, 32);
			return;
		}

		if (!IndividualItems.isAnalyzed(hovered)) {
			graphics.text(font, Component.translatable("for.gui.unknown"), SPECIES_X, LINE_HEIGHT, TEXT_COLOR, false);
			return;
		}

		IGenome genome = IndividualItems.getGenome(hovered);
		if (genome == null) {
			return;
		}

		IChromosome<?> speciesChromosome = genome.karyotype().speciesChromosome();
		if (!(speciesChromosome instanceof IRegistryChromosome<?> registry)) {
			return;
		}

		Player player = Minecraft.getInstance().player;
		if (player == null) {
			return;
		}

		@SuppressWarnings("unchecked")
		IRegistryChromosome<IRegistryAlleleValue> typed = (IRegistryChromosome<IRegistryAlleleValue>) registry;
		IRegistryAllele<IRegistryAlleleValue> activeAllele = genome.getActiveAllele(typed);
		IRegistryAllele<IRegistryAlleleValue> inactiveAllele = genome.getInactiveAllele(typed);
		Identifier activeId = activeAllele.value().id();
		Identifier inactiveId = inactiveAllele.value().id();
		boolean haploid = isHaploid(IndividualItems.getLifeStage(hovered));
		boolean pureBred = haploid || activeId.equals(inactiveId);

		prepareIcons(speciesTypeId);
		IBreedingTracker tracker = BreedingTrackerManager.INSTANCE.getTracker(
				speciesTypeId, player.level(), player.getGameProfile());

		this.lineY = LINE_HEIGHT;
		displaySpeciesInformation(
				graphics, font, tracker, speciesTypeId, true, activeId, typed.getDisplayName(activeAllele),
				SPECIES_X, pureBred ? PURE_MAX_MUTATIONS : HYBRID_MAX_MUTATIONS);
		if (!pureBred) {
			displaySpeciesInformation(
					graphics, font, tracker, speciesTypeId, true, inactiveId, typed.getDisplayName(inactiveAllele),
					SPECIES_X, HYBRID_MAX_MUTATIONS);
		}
	}

	private void displaySpeciesInformation(
			GuiGraphicsExtractor graphics,
			Font font,
			IBreedingTracker tracker,
			Identifier typeId,
			boolean analyzed,
			Identifier speciesId,
			Component name,
			int x,
			int maxMutationCount) {
		if (!analyzed) {
			graphics.text(font, Component.translatable("for.gui.unknown"), x, this.lineY, TEXT_COLOR, false);
			return;
		}

		graphics.text(font, name, x, this.lineY, TEXT_COLOR, false);
		drawItem(graphics, font, iconStack(typeId, speciesId), x + 67, this.lineY - 4);
		this.lineY += LINE_HEIGHT;

		int column = MUTATION_COLUMN_START;
		List<List<? extends IMutation>> pages = splitMutations(mutationsFrom(typeId, speciesId), maxMutationCount);
		for (IMutation combination : this.timer.getCycledItem(pages, Collections::emptyList)) {
			if (combination.isSecret()) {
				continue;
			}
			if (tracker.isDiscovered(combination)) {
				drawMutationIcon(graphics, font, typeId, combination, speciesId, column);
			} else {
				drawUnknownIcon(graphics, combination, column);
			}
			column += MUTATION_COLUMN_WIDTH;
			if (column > MUTATION_COLUMN_WRAP) {
				column = MUTATION_COLUMN_START;
				this.lineY += MUTATION_ROW_HEIGHT;
			}
		}

		this.lineY += LINE_HEIGHT;
		this.lineY += LINE_HEIGHT;
	}

	private void drawMutationIcon(
			GuiGraphicsExtractor graphics,
			Font font,
			Identifier typeId,
			IMutation combination,
			Identifier speciesId,
			int x) {
		drawItem(graphics, font, iconStack(typeId, combination.getPartner(speciesId)), x, this.lineY);
	}

	private void drawUnknownIcon(GuiGraphicsExtractor graphics, IMutation mutation, int x) {
		int u;
		int v;
		switch (EnumMutateChance.rateChance(mutation.getChance())) {
			case HIGHEST -> {
				u = 228;
				v = 16;
			}
			case HIGHER -> {
				u = 212;
				v = 16;
			}
			case HIGH -> {
				u = 196;
				v = 16;
			}
			case NORMAL -> {
				u = 228;
				v = 0;
			}
			case LOW -> {
				u = 212;
				v = 0;
			}
			default -> {
				u = 196;
				v = 0;
			}
		}
		graphics.blit(
				RenderPipelines.GUI_TEXTURED,
				TEXTURE,
				x,
				this.lineY,
				(float) u,
				(float) v,
				16,
				16,
				256,
				256);
	}

	private void prepareIcons(Identifier typeId) {
		if (typeId.equals(this.cachedTypeId) && !this.iconStacks.isEmpty()) {
			return;
		}
		this.iconStacks.clear();
		this.cachedTypeId = typeId;
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			for (Identifier speciesId : ApicultureGenetics.getAllSpeciesIds()) {
				this.iconStacks.put(speciesId, beeIcon(speciesId));
			}
		} else if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			for (Identifier speciesId : ArboricultureGenetics.getAllSpeciesIds()) {
				this.iconStacks.put(speciesId, treeIcon(speciesId));
			}
		}
	}

	private ItemStack iconStack(Identifier typeId, Identifier speciesId) {
		ItemStack cached = this.iconStacks.get(speciesId);
		if (cached != null) {
			return cached;
		}
		ItemStack created = ForestrySpeciesTypes.BEE.equals(typeId)
				? beeIcon(speciesId)
				: ForestrySpeciesTypes.TREE.equals(typeId) ? treeIcon(speciesId) : ItemStack.EMPTY;
		if (!created.isEmpty()) {
			this.iconStacks.put(speciesId, created);
		}
		return created;
	}

	private static ItemStack beeIcon(Identifier speciesId) {
		if (ApicultureGenetics.getSpeciesSafe(speciesId) == null) {
			return ItemStack.EMPTY;
		}
		return BeeStackHelper.createBeeStack(
				ApicultureItems.BEE_DRONE.item(),
				ApicultureGenetics.getDefaultGenome(speciesId),
				true,
				0);
	}

	private static ItemStack treeIcon(Identifier speciesId) {
		if (ArboricultureGenetics.getSpeciesSafe(speciesId) == null) {
			return ItemStack.EMPTY;
		}
		ItemStack stack = new ItemStack(ArboricultureItems.SAPLING.item());
		stack.set(ArboricultureDataComponents.TREE_GENOME.type(), ArboricultureGenetics.getDefaultGenome(speciesId));
		return stack;
	}

	private static void drawItem(GuiGraphicsExtractor graphics, Font font, ItemStack stack, int x, int y) {
		if (stack == null || stack.isEmpty()) {
			return;
		}
		graphics.fakeItem(stack, x, y);
		graphics.itemDecorations(font, stack, x, y);
	}

	private static boolean isHaploid(String lifeStage) {
		return "drone".equals(lifeStage) || "pollen".equals(lifeStage);
	}

	private static List<? extends IMutation> mutationsFrom(Identifier typeId, Identifier speciesId) {
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			return ApicultureGenetics.getMutationsFrom(speciesId);
		}
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			return ArboricultureGenetics.getMutationsFrom(speciesId);
		}
		return List.of();
	}

	private static List<List<? extends IMutation>> splitMutations(List<? extends IMutation> mutations, int maxMutationCount) {
		int size = mutations.size();
		if (size <= maxMutationCount) {
			return List.of(mutations);
		}
		List<List<? extends IMutation>> subGroups = new ArrayList<>();
		List<IMutation> subList = new ArrayList<>();
		subGroups.add(subList);
		int count = 0;
		for (IMutation mutation : mutations) {
			if (mutation.isSecret()) {
				continue;
			}
			if (count % maxMutationCount == 0 && count != 0) {
				subList = new ArrayList<>();
				subGroups.add(subList);
			}
			subList.add(mutation);
			count++;
		}
		return subGroups;
	}
}
