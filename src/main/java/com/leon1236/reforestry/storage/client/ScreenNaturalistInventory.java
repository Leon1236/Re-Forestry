package com.leon1236.reforestry.storage.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.AllelePair;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.core.gui.NaturalistInventoryLayout;
import com.leon1236.reforestry.storage.gui.ContainerNaturalistBackpack;

@Environment(EnvType.CLIENT)
public class ScreenNaturalistInventory extends ScreenForestry<ContainerNaturalistBackpack> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/apiaristinventory.png");
	private static final int IMAGE_WIDTH = 196;
	private static final int IMAGE_HEIGHT = 202;
	private static final int TEXT_COLOR = 0xFF404040;
	private static final int LEFT_BUTTON_X = 99;
	private static final int RIGHT_BUTTON_X = 180;
	private static final int BUTTON_Y = 7;
	private static final int BUTTON_SIZE = 12;

	public ScreenNaturalistInventory(ContainerNaturalistBackpack menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		this.inventoryLabelY = NaturalistInventoryLayout.PLAYER_INV_Y - 11;
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		super.extractLabels(graphics, mouseX, mouseY);
		int page = this.menu.getCurrentPage() + 1;
		Component header = Component.translatable("for.gui.page").append(" " + page + "/" + NaturalistInventoryLayout.MAX_PAGE);
		int headerX = 95 + (98 - this.font.width(header)) / 2;
		graphics.text(this.font, header, headerX, 10, TEXT_COLOR, false);

		ItemStack hovered = getHoveredStack();
		if (hovered.isEmpty() || !IndividualItems.isIndividual(hovered)) {
			return;
		}
		if (!IndividualItems.isAnalyzed(hovered)) {
			graphics.text(this.font, Component.translatable("for.gui.unknown"), 10, 32, TEXT_COLOR, false);
			return;
		}
		IGenome genome = IndividualItems.getGenome(hovered);
		if (genome == null) {
			return;
		}
		IChromosome<?> speciesChromosome = genome.karyotype().speciesChromosome();
		AllelePair<?> pair = genome.chromosomes().get(speciesChromosome);
		if (pair == null) {
			return;
		}
		@SuppressWarnings({"unchecked", "rawtypes"})
		Component speciesName = ((IChromosome) speciesChromosome).getDisplayName(pair.active());
		graphics.text(this.font, speciesName, 10, 32, TEXT_COLOR, false);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (this.minecraft != null && this.minecraft.gameMode != null && this.minecraft.player != null) {
			int page = this.menu.getCurrentPage();
			if (isHovering(LEFT_BUTTON_X, BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE, event.x(), event.y()) && page > 0) {
				this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, page - 1);
				return true;
			}
			if (isHovering(RIGHT_BUTTON_X, BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE, event.x(), event.y())
					&& page < NaturalistInventoryLayout.MAX_PAGE - 1) {
				this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, page + 1);
				return true;
			}
		}
		return super.mouseClicked(event, doubleClick);
	}

	private ItemStack getHoveredStack() {
		Slot slot = this.hoveredSlot;
		if (slot == null || !slot.hasItem()) {
			return ItemStack.EMPTY;
		}
		return slot.getItem();
	}
}
