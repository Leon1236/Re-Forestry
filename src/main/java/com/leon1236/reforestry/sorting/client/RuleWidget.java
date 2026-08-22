package com.leon1236.reforestry.sorting.client;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.genetics.filter.IFilterLogic;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;

public class RuleWidget extends FilterWidget implements ISelectableProvider<IFilterRuleType> {
	private static final ImmutableSet<IFilterRuleType> ENTRIES = createEntries();

	private final Direction facing;
	private final ScreenGeneticFilter gui;

	public RuleWidget(int xPos, int yPos, Direction facing, ScreenGeneticFilter gui) {
		super(xPos, yPos);
		this.facing = facing;
		this.gui = gui;
	}

	@Override
	public void draw(GuiGraphicsExtractor graphics, int startX, int startY) {
		int x = this.xPos + startX;
		int y = this.yPos + startY;
		IFilterLogic logic = this.gui.getLogic();
		IFilterRuleType rule = logic.getRule(this.facing);
		draw(rule, graphics, y, x);
		if (this.gui.selection.isSame(this)) {
			graphics.blit(RenderPipelines.GUI_TEXTURED, SelectionWidget.TEXTURE, x - 1, y - 1, 212.0f, 0.0f, 18, 18, 256, 256);
		}
	}

	@Override
	public Collection<IFilterRuleType> getEntries() {
		return ENTRIES;
	}

	@Override
	public void draw(IFilterRuleType selectable, GuiGraphicsExtractor graphics, int y, int x) {
		FilterSprites.blit(graphics, selectable.getSprite(), x, y);
	}

	@Override
	public Component getName(IFilterRuleType selectable) {
		return Component.translatable("for.gui.filter." + selectable.getId());
	}

	@Override
	public void onSelect(IFilterRuleType selectable) {
		IFilterLogic logic = this.gui.getLogic();
		if (logic.setRule(this.facing, selectable)) {
			logic.sendToServer(this.facing, selectable);
		}
		if (this.gui.selection.isSame(this)) {
			this.gui.onModuleClick(this);
		}
		playClick();
	}

	@Override
	public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
		if (mouseButton == 1) {
			onSelect(IForestryApi.get().getFilterManager().getDefaultRule());
		} else {
			playClick();
			this.gui.onModuleClick(this);
		}
	}

	@Override
	public List<Component> getToolTip(int mouseX, int mouseY) {
		IFilterLogic logic = this.gui.getLogic();
		IFilterRuleType rule = logic.getRule(this.facing);
		return List.of(getName(rule));
	}

	private static void playClick() {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.player != null) {
			minecraft.player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 1.0f, 1.0f);
		}
	}

	private static ImmutableSet<IFilterRuleType> createEntries() {
		ImmutableSet.Builder<IFilterRuleType> entries = ImmutableSet.builder();
		for (IFilterRuleType rule : IForestryApi.get().getFilterManager().getRules()) {
			entries.add(rule);
		}
		return entries.build();
	}
}
