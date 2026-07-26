package com.leon1236.reforestry.core.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerPlugin;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;
import com.leon1236.reforestry.core.client.genetics.AnalyzerScreenGraphics;
import com.leon1236.reforestry.core.client.genetics.GeneticClientManager;
import com.leon1236.reforestry.core.gui.ContainerAlyzer;
import com.leon1236.reforestry.core.inventory.ItemInventoryAlyzer;

public class ScreenPortableAnalyzer extends ScreenForestry<ContainerAlyzer> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/portablealyzer.png");
	private static final int IMAGE_WIDTH = 247;
	private static final int IMAGE_HEIGHT = 238;
	private static final int SCREEN_COLOR = 0xFF404040;
	private static final int DOMINANT_RED = 0xFFEC3661;
	private static final int RECESSIVE_BLUE = 0xFF3687EC;

	private final ItemInventoryAlyzer itemInventory;

	public ScreenPortableAnalyzer(ContainerAlyzer container, Inventory playerInv, Component name) {
		super(container, playerInv, name, IMAGE_WIDTH, IMAGE_HEIGHT);
		this.itemInventory = container.getAlyzerInventory();
	}

	public static int getColorCoding(boolean dominant) {
		return dominant ? DOMINANT_RED : RECESSIVE_BLUE;
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int specimenSlot = getSpecimenSlot();
		if (specimenSlot < ItemInventoryAlyzer.SLOT_ANALYZE_1) {
			drawAnalyticsOverview(graphics);
			return;
		}

		ItemStack stackInSlot = this.itemInventory.getItem(specimenSlot);
		IGenome genome = IndividualItems.getGenome(stackInSlot);
		String lifeStage = IndividualItems.getLifeStage(stackInSlot);
		Identifier typeId = IndividualItems.getSpeciesTypeId(stackInSlot);
		if (genome == null || lifeStage == null || typeId == null) {
			drawAnalyticsOverview(graphics);
			return;
		}

		IAnalyzerPlugin plugin = GeneticClientManager.INSTANCE.getAnalyzerPlugin(typeId);
		if (plugin == null) {
			drawUnsupported(graphics);
			return;
		}

		AnalyzerScreenGraphics analyzerGraphics = new AnalyzerScreenGraphics(graphics, this.font, genome);
		switch (specimenSlot) {
			case ItemInventoryAlyzer.SLOT_ANALYZE_1 -> plugin.drawPage1(analyzerGraphics, genome, lifeStage, stackInSlot);
			case ItemInventoryAlyzer.SLOT_ANALYZE_2 -> plugin.drawPage2(analyzerGraphics, genome, lifeStage, stackInSlot);
			case ItemInventoryAlyzer.SLOT_ANALYZE_3 -> plugin.drawPage3(analyzerGraphics, genome, lifeStage, stackInSlot);
			case ItemInventoryAlyzer.SLOT_ANALYZE_4 -> plugin.drawPage4(analyzerGraphics, genome, lifeStage, stackInSlot);
			case ItemInventoryAlyzer.SLOT_ANALYZE_5 -> plugin.drawPage5(analyzerGraphics, genome, lifeStage, stackInSlot);
			default -> drawAnalyticsOverview(graphics);
		}
	}

	private int getSpecimenSlot() {
		for (int k = ItemInventoryAlyzer.SLOT_SPECIMEN; k <= ItemInventoryAlyzer.SLOT_ANALYZE_5; k++) {
			ItemStack stackInSlot = this.itemInventory.getItem(k);
			if (!stackInSlot.isEmpty() && IndividualItems.filter(stackInSlot, genome -> IndividualItems.isAnalyzed(stackInSlot))) {
				return k;
			}
		}
		return -1;
	}

	private void drawAnalyticsOverview(GuiGraphicsExtractor graphics) {
		int y = 24;
		Component title = Component.translatable("for.gui.portablealyzer");
		int titleWidth = this.font.width(title);
		graphics.text(this.font, title, (208 - titleWidth) / 2, y, SCREEN_COLOR, false);
		graphics.textWithWordWrap(this.font, Component.translatable("for.gui.portablealyzer.help"), 16, 42, 200, SCREEN_COLOR, false);
		y = 84;
		graphics.text(this.font, Component.translatable("for.gui.alyzer.overview").append(":"), 16, y, SCREEN_COLOR, false);
		y += 12;
		graphics.text(this.font, Component.literal("I  : ").append(Component.translatable("for.gui.general")), 16, y, SCREEN_COLOR, false);
		y += 12;
		graphics.text(this.font, Component.literal("II : ").append(Component.translatable("for.gui.environment")), 16, y, SCREEN_COLOR, false);
		y += 12;
		graphics.text(this.font, Component.literal("III: ").append(Component.translatable("for.gui.produce")), 16, y, SCREEN_COLOR, false);
		y += 12;
		graphics.text(this.font, Component.literal("IV : ").append(Component.translatable("for.gui.evolution")), 16, y, SCREEN_COLOR, false);
	}

	private void drawUnsupported(GuiGraphicsExtractor graphics) {
		graphics.textWithWordWrap(this.font, Component.translatable("for.gui.alyzer.nodescription"), 16, 42, 200, SCREEN_COLOR, false);
	}
}
