package com.leon1236.reforestry.sorting.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.api.genetics.filter.IFilterLogic;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.features.ArboricultureItems;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.core.genetics.GeneticItemHelper;
import com.leon1236.reforestry.core.genetics.root.BreedingTrackerManager;

public class SpeciesWidget extends FilterWidget implements ISelectableProvider<Identifier> {
	private static final Map<Identifier, ItemStack> ICONS = new HashMap<>();

	private final ImmutableSet<Identifier> entries;
	private final Direction facing;
	private final int index;
	private final boolean active;
	private final ScreenGeneticFilter gui;

	public SpeciesWidget(int xPos, int yPos, Direction facing, int index, boolean active, ScreenGeneticFilter gui) {
		super(xPos, yPos);
		this.facing = facing;
		this.index = index;
		this.active = active;
		this.gui = gui;
		ImmutableSet.Builder<Identifier> discovered = ImmutableSet.builder();
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.level != null && minecraft.player != null) {
			addDiscovered(discovered, ForestrySpeciesTypes.BEE, minecraft);
			addDiscovered(discovered, ForestrySpeciesTypes.TREE, minecraft);
		}
		this.entries = discovered.build();
	}

	private static void addDiscovered(ImmutableSet.Builder<Identifier> discovered, Identifier typeId, Minecraft minecraft) {
		IBreedingTracker tracker = BreedingTrackerManager.INSTANCE.getTracker(typeId, minecraft.level, minecraft.player.getGameProfile());
		for (Identifier id : tracker.getDiscoveredSpecies()) {
			if (!icon(id).isEmpty()) {
				discovered.add(id);
			}
		}
	}

	@Override
	public void draw(GuiGraphicsExtractor graphics, int startX, int startY) {
		int x = this.xPos + startX;
		int y = this.yPos + startY;
		IFilterLogic logic = this.gui.getLogic();
		Identifier species = logic.getGenomeFilter(this.facing, this.index, this.active);
		if (species != null) {
			ItemStack icon = icon(species);
			if (!icon.isEmpty()) {
				graphics.fakeItem(icon, x, y);
			}
		}
		if (this.gui.selection.isSame(this)) {
			graphics.blit(RenderPipelines.GUI_TEXTURED, SelectionWidget.TEXTURE, x - 1, y - 1, 212.0f, 0.0f, 18, 18, 256, 256);
		}
	}

	@Override
	public ImmutableSet<Identifier> getEntries() {
		return this.entries;
	}

	@Override
	public void onSelect(@Nullable Identifier selectable) {
		IFilterLogic logic = this.gui.getLogic();
		if (logic.setGenomeFilter(this.facing, this.index, this.active, selectable)) {
			logic.sendToServer(this.facing, this.index, this.active, selectable);
		}
		if (this.gui.selection.isSame(this)) {
			this.gui.onModuleClick(this);
		}
		playClick();
	}

	@Override
	public void draw(Identifier selectable, GuiGraphicsExtractor graphics, int y, int x) {
		ItemStack icon = icon(selectable);
		if (!icon.isEmpty()) {
			graphics.fakeItem(icon, x, y);
		}
	}

	@Override
	public Component getName(Identifier selectable) {
		if (ApicultureGenetics.getSpeciesSafe(selectable) != null) {
			return Component.translatable("allele.reforestry.bee_species." + selectable.getPath());
		}
		if (ArboricultureGenetics.getSpeciesSafe(selectable) != null) {
			return Component.translatable("allele.reforestry.tree_species." + selectable.getPath());
		}
		return Component.literal(selectable.toString());
	}

	@Nullable
	@Override
	public List<Component> getToolTip(int mouseX, int mouseY) {
		IFilterLogic logic = this.gui.getLogic();
		Identifier species = logic.getGenomeFilter(this.facing, this.index, this.active);
		if (species == null) {
			return null;
		}
		return List.of(getName(species));
	}

	@Override
	public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
		ItemStack stack = this.gui.getMenu().getCarried();
		if (!stack.isEmpty() && IndividualItems.isIndividual(stack)) {
			var typeId = IndividualItems.getSpeciesTypeId(stack);
			var genome = IndividualItems.getGenome(stack);
			if (typeId != null && genome != null) {
				Identifier species = GeneticItemHelper.speciesId(genome, typeId, mouseButton == 0);
				onSelect(species);
				return;
			}
		}
		if (mouseButton == 1) {
			onSelect(null);
		} else {
			playClick();
			this.gui.onModuleClick(this);
		}
	}

	private static ItemStack icon(Identifier speciesId) {
		ItemStack cached = ICONS.get(speciesId);
		if (cached != null) {
			return cached;
		}
		ItemStack created = ItemStack.EMPTY;
		if (ApicultureGenetics.getSpeciesSafe(speciesId) != null) {
			created = BeeStackHelper.createBeeStack(
					ApicultureItems.BEE_DRONE.item(),
					ApicultureGenetics.getDefaultGenome(speciesId),
					true,
					0);
		} else if (ArboricultureGenetics.getSpeciesSafe(speciesId) != null) {
			created = new ItemStack(ArboricultureItems.SAPLING.item());
			created.set(ArboricultureDataComponents.TREE_GENOME.type(), ArboricultureGenetics.getDefaultGenome(speciesId));
		}
		ICONS.put(speciesId, created);
		return created;
	}

	private static void playClick() {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.player != null) {
			minecraft.player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1.0f, 1.0f);
		}
	}
}
