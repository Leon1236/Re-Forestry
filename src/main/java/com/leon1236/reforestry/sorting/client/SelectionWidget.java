package com.leon1236.reforestry.sorting.client;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;

public class SelectionWidget extends FilterWidget {
	public static final Identifier TEXTURE = ReForestry.id("textures/gui/filter_selection.png");

	final FilterScrollBar scrollBar;
	@Nullable
	private SelectionLogic<?> logic;
	final ScreenGeneticFilter gui;

	public SelectionWidget(int xPos, int yPos, FilterScrollBar scrollBar, ScreenGeneticFilter gui) {
		super(xPos, yPos);
		this.width = 212;
		this.height = 88;
		this.scrollBar = scrollBar;
		this.gui = gui;
	}

	public <S> void setProvider(@Nullable ISelectableProvider<S> provider) {
		if (provider == null) {
			this.logic = null;
		} else {
			this.logic = new SelectionLogic<>(this, provider);
		}
	}

	public boolean isSame(ISelectableProvider<?> provider) {
		return this.logic != null && this.logic.isSame(provider);
	}

	@Nullable
	public SelectionLogic<?> getLogic() {
		return this.logic;
	}

	@Override
	public void draw(GuiGraphicsExtractor graphics, int startX, int startY) {
		if (this.logic == null) {
			return;
		}
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, startX + this.xPos, startY + this.yPos, 0.0f, 0.0f,
				this.width, this.height, 256, 256);
		this.logic.draw(graphics);
		graphics.text(this.gui.getMinecraftFont(), Component.translatable("for.gui.filter.seletion"),
				startX + this.xPos + 12, startY + this.yPos + 4, 0xFF404040, false);
	}

	@Override
	public boolean isMouseOver(double mouseX, double mouseY) {
		return this.logic != null && super.isMouseOver(mouseX, mouseY);
	}

	@Nullable
	@Override
	public List<Component> getToolTip(int mouseX, int mouseY) {
		if (this.logic == null) {
			return null;
		}
		return this.logic.getToolTip(mouseX, mouseY);
	}

	@Override
	public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
		if (this.logic == null) {
			return;
		}
		this.logic.select(mouseX, mouseY);
	}

	public void filterEntries(String filter) {
		if (this.logic == null) {
			return;
		}
		this.logic.filterEntries(filter);
	}
}
