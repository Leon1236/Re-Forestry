package com.leon1236.reforestry.core.client;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import org.joml.Matrix3x2fStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.escritoire.ContainerEscritoire;
import com.leon1236.reforestry.core.escritoire.EscritoireGame;
import com.leon1236.reforestry.core.escritoire.EscritoireTextSource;
import com.leon1236.reforestry.core.escritoire.TileEscritoire;

@Environment(EnvType.CLIENT)
public class ScreenEscritoire extends ScreenForestry<ContainerEscritoire> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/escritoire.png");
	private static final int IMAGE_WIDTH = 228;
	private static final int IMAGE_HEIGHT = 235;
	private static final int LETTER_COLOR = ARGB.opaque(0xcfa738);
	private static final ItemStack LEVEL_ITEM = new ItemStack(Items.PAPER);

	private final ItemStack levelItem = LEVEL_ITEM.copy();
	private final EscritoireTextSource textSource = new EscritoireTextSource();
	private final TileEscritoire tile;
	private final ProbeButton probeButton;
	private final List<GameTokenWidget> tokenWidgets = new ArrayList<>();

	public ScreenEscritoire(ContainerEscritoire menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		this.tile = menu.getTile();
		setHintKey("escritoire");
		this.probeButton = new ProbeButton(TEXTURE, 14, 16);

		EscritoireGame game = this.tile.getGame();
		addToken(game, 115, 51, 0);
		addToken(game, 115, 77, 1);
		addToken(game, 94, 90, 2);
		addToken(game, 73, 77, 3);
		addToken(game, 73, 51, 4);
		addToken(game, 94, 38, 5);

		addToken(game, 115, 25, 6);
		addToken(game, 136, 38, 7);
		addToken(game, 136, 64, 8);
		addToken(game, 136, 90, 9);
		addToken(game, 115, 103, 10);
		addToken(game, 94, 116, 11);
		addToken(game, 73, 103, 12);
		addToken(game, 52, 90, 13);
		addToken(game, 52, 64, 14);
		addToken(game, 52, 38, 15);
		addToken(game, 73, 25, 16);
		addToken(game, 94, 12, 17);

		addToken(game, 52, 12, 18);
		addToken(game, 136, 12, 19);
		addToken(game, 52, 116, 20);
		addToken(game, 136, 116, 21);
	}

	private void addToken(EscritoireGame game, int x, int y, int index) {
		this.tokenWidgets.add(new GameTokenWidget(game, TEXTURE, x, y, index));
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

		this.probeButton.draw(graphics, this.leftPos, this.topPos);
		for (GameTokenWidget widget : this.tokenWidgets) {
			widget.draw(graphics, this.leftPos, this.topPos);
		}

		for (int i = 0; i <= this.tile.getGame().getBountyLevel() / 4; i++) {
			graphics.fakeItem(this.levelItem, this.leftPos + 170 + i * 8, this.topPos + 7);
		}

		Matrix3x2fStack pose = graphics.pose();
		pose.pushMatrix();
		pose.translate(this.leftPos + 170, this.topPos + 10);
		pose.scale(0.5f, 0.5f);
		Component attempt = Component.translatable(
						"for.gui.escritoire.attempt.number",
						EscritoireGame.BOUNTY_MAX - this.tile.getGame().getBountyLevel())
				.withStyle(ChatFormatting.UNDERLINE, ChatFormatting.ITALIC);
		graphics.text(this.font, attempt, 0, 18, LETTER_COLOR, false);
		graphics.textWithWordWrap(this.font, this.textSource.getText(this.tile.getGame()), 0, 36, 180, LETTER_COLOR, false);
		pose.popMatrix();
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		super.extractRenderState(graphics, mouseX, mouseY, partialTick);
		if (this.probeButton.isMouseOver(mouseX, mouseY, this.leftPos, this.topPos)) {
			this.probeButton.drawTooltip(graphics, this.font, mouseX, mouseY);
			return;
		}
		for (GameTokenWidget widget : this.tokenWidgets) {
			if (widget.isMouseOver(mouseX, mouseY, this.leftPos, this.topPos)) {
				widget.drawTooltip(graphics, this.font, mouseX, mouseY);
				return;
			}
		}
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		LocalPlayer player = this.minecraft != null ? this.minecraft.player : null;
		MultiPlayerGameMode gameMode = this.minecraft != null ? this.minecraft.gameMode : null;
		if (player != null && gameMode != null) {
			if (this.probeButton.isMouseOver(event.x(), event.y(), this.leftPos, this.topPos)) {
				this.probeButton.setPressed(true);
				sendButton(player, gameMode, this.probeButton.buttonId());
				return true;
			}
			for (GameTokenWidget widget : this.tokenWidgets) {
				if (widget.isMouseOver(event.x(), event.y(), this.leftPos, this.topPos)) {
					sendButton(player, gameMode, widget.buttonId());
					return true;
				}
			}
		}
		return super.mouseClicked(event, doubleClick);
	}

	@Override
	public boolean mouseReleased(MouseButtonEvent event) {
		if (this.probeButton.isPressed()) {
			this.probeButton.setPressed(false);
		}
		return super.mouseReleased(event);
	}

	private void sendButton(LocalPlayer player, MultiPlayerGameMode gameMode, int buttonId) {
		if (this.menu.clickMenuButton(player, buttonId)) {
			gameMode.handleInventoryButtonClick(this.menu.containerId, buttonId);
		}
	}
}
