package com.leon1236.reforestry.extra_bees.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extra_bees.alveary.AlvearyMutationHandler;
import com.leon1236.reforestry.extra_bees.gui.ContainerAlvearyMutator;

public class ScreenAlvearyMutator extends AbstractContainerScreen<ContainerAlvearyMutator> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/alveary_mutator.png");
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 176;

	public ScreenAlvearyMutator(ContainerAlvearyMutator menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

		guiGraphics.text(font, Component.translatable("gui.reforestry.alveary_mutator.mutagens"),
				leftPos + 8, topPos + 52, 0x555555, false);

		int y = topPos + 64;
		for (AlvearyMutationHandler.Mutagen mutagen : AlvearyMutationHandler.getMutagens()) {
			Component line = mutagen.stack().getHoverName().copy()
					.append(Component.literal(" — "))
					.append(Component.translatable("gui.reforestry.alveary_mutator.multiplier", mutagen.multiplier()));
			guiGraphics.text(font, line, leftPos + 12, y, 0x404040, false);
			y += 10;
		}
	}
}
