package com.leon1236.reforestry.sorting.client;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.filter.IFilterLogic;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.sorting.gui.ContainerGeneticFilter;
import com.leon1236.reforestry.sorting.gui.SlotGeneticFilter;
import com.leon1236.reforestry.sorting.tiles.TileGeneticFilter;

@Environment(EnvType.CLIENT)
public class ScreenGeneticFilter extends ScreenForestry<ContainerGeneticFilter> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/filter.png");
	private static final int IMAGE_WIDTH = 212;
	private static final int IMAGE_HEIGHT = 222;

	private final TileGeneticFilter tile;
	private final FilterScrollBar scrollBar;
	private final List<FilterWidget> widgets = new ArrayList<>();
	public final SelectionWidget selection;
	@Nullable
	private EditBox searchField;

	public ScreenGeneticFilter(ContainerGeneticFilter container, Inventory inventory, Component title) {
		super(container, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		this.tile = container.getTile();
		setHintKey("filter");

		for (int i = 0; i < 6; i++) {
			Direction facing = Direction.from3DDataValue(i);
			this.widgets.add(new RuleWidget(8 + 36, 18 + i * 18, facing, this));
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
					this.widgets.add(new SpeciesWidget(44 + 36 + j * 45 + k * 18, 18 + i * 18,
							Direction.from3DDataValue(i), j, k == 0, this));
				}
			}
		}
		this.scrollBar = new FilterScrollBar(157 + 36, 150, 12, 64);
		this.selection = new SelectionWidget(0, 134, this.scrollBar, this);
		this.widgets.add(this.selection);
		this.widgets.add(this.scrollBar);
		this.scrollBar.setVisible(false);
	}

	public Font getMinecraftFont() {
		return this.font;
	}

	public int getGuiLeft() {
		return this.leftPos;
	}

	public int getGuiTop() {
		return this.topPos;
	}

	public <S> void onModuleClick(ISelectableProvider<S> provider) {
		if (this.selection.isSame(provider)) {
			deselectFilter();
		} else {
			selectFilter(provider);
		}
	}

	private <S> void selectFilter(ISelectableProvider<S> provider) {
		this.selection.setProvider(provider);
		if (this.searchField != null) {
			this.searchField.setEditable(true);
			this.searchField.setVisible(true);
		}
		this.selection.filterEntries(this.searchField != null ? this.searchField.getValue() : "");
		for (Slot slot : this.menu.slots) {
			if (slot instanceof SlotGeneticFilter filter) {
				filter.setEnabled(false);
			}
		}
	}

	private void deselectFilter() {
		this.selection.setProvider(null);
		if (this.searchField != null) {
			this.searchField.setEditable(false);
			this.searchField.setVisible(false);
		}
		this.scrollBar.setVisible(false);
		for (Slot slot : this.menu.slots) {
			if (slot instanceof SlotGeneticFilter filter) {
				filter.setEnabled(true);
			}
		}
	}

	@Override
	protected void init() {
		super.init();
		String oldString = this.searchField != null ? this.searchField.getValue() : "";
		this.searchField = new EditBox(this.font, this.leftPos + this.selection.getX() + 89 + 36,
				this.selection.getY() + this.topPos + 4, 80, this.font.lineHeight, Component.empty());
		this.searchField.setMaxLength(50);
		this.searchField.setBordered(false);
		this.searchField.setTextColor(16777215);
		this.searchField.setValue(oldString);
		this.searchField.setEditable(this.selection.getLogic() != null);
		this.searchField.setVisible(this.selection.getLogic() != null);
		this.searchField.setResponder(value -> {
			this.scrollBar.setValue(0);
			this.selection.filterEntries(value);
		});
		addRenderableWidget(this.searchField);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0.0f, 0.0f,
				this.imageWidth, this.imageHeight, 256, 256);
		for (FilterWidget widget : this.widgets) {
			widget.draw(graphics, this.leftPos, this.topPos);
		}
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		super.extractRenderState(graphics, mouseX, mouseY, partialTick);
		this.scrollBar.update(mouseX - this.leftPos, mouseY - this.topPos, false);
		int relativeX = mouseX - this.leftPos;
		int relativeY = mouseY - this.topPos;
		for (FilterWidget widget : this.widgets) {
			if (widget.isMouseOver(relativeX, relativeY)) {
				widget.drawTooltip(graphics, this.font, mouseX, mouseY);
				return;
			}
		}
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (this.searchField != null && this.searchField.isVisible() && this.searchField.keyPressed(event)) {
			return true;
		}
		return super.keyPressed(event);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		int relativeX = (int) event.x() - this.leftPos;
		int relativeY = (int) event.y() - this.topPos;
		this.scrollBar.update(relativeX, relativeY, event.button() == 0);
		FilterWidget hovered = null;
		for (FilterWidget widget : this.widgets) {
			if (widget.isMouseOver(relativeX, relativeY)) {
				hovered = widget;
				widget.handleMouseClick(event.x(), event.y(), event.button());
				break;
			}
		}
		if (this.searchField != null) {
			this.searchField.mouseClicked(event, doubleClick);
		}
		if (hovered == null) {
			deselectFilter();
		}
		if (hovered != null) {
			return true;
		}
		return super.mouseClicked(event, doubleClick);
	}

	@Override
	public boolean mouseReleased(MouseButtonEvent event) {
		this.scrollBar.update((int) event.x() - this.leftPos, (int) event.y() - this.topPos, false);
		return super.mouseReleased(event);
	}

	@Override
	public boolean mouseDragged(MouseButtonEvent event, double deltaX, double deltaY) {
		if (event.button() == 0) {
			this.scrollBar.update((int) event.x() - this.leftPos, (int) event.y() - this.topPos, true);
		}
		return super.mouseDragged(event, deltaX, deltaY);
	}

	@Override
	public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
		if (this.scrollBar.isVisible() && this.selection.getLogic() != null
				&& this.selection.isMouseOver(x - this.leftPos, y - this.topPos)) {
			int delta = scrollY > 0 ? -1 : 1;
			this.scrollBar.setValue(this.scrollBar.getValue() + delta);
			return true;
		}
		return super.mouseScrolled(x, y, scrollX, scrollY);
	}

	public IFilterLogic getLogic() {
		return this.tile.getLogic();
	}
}
