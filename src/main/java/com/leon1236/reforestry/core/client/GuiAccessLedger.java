package com.leon1236.reforestry.core.client;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.AccessMode;
import com.leon1236.reforestry.api.gui.IContainerSidedAccess;
import com.leon1236.reforestry.core.blocks.BlockMachine;
import com.leon1236.reforestry.core.tiles.TileBase;

@Environment(EnvType.CLIENT)
public final class GuiAccessLedger {
	private static final int MIN_WIDTH = 24;
	private static final int MIN_HEIGHT = 24;
	private static final int MAX_WIDTH = 140;
	private static final int MAX_HEIGHT = 140;
	private static final int VIEWPORT_PAD = 8;
	private static final int HEADER = 20;
	private static final int TEXTURE_SIZE = 256;
	private static final float ANIM_SPEED = 8.0f;
	private static final float ROTATE_SENSITIVITY = 0.5f;
	private static final float MAX_PITCH = 80.0f;
	private static final float CLICK_DRAG_THRESHOLD = 3.0f;
	private static final Identifier LEDGER_RIGHT = ReForestry.id("textures/gui/ledger.png");

	private final IContainerSidedAccess accessContainer;
	private final TileBase tile;
	private final BlockState[] neighborStates = new BlockState[Direction.values().length];
	private boolean open;
	private boolean entityModel;
	private float currentWidth = MIN_WIDTH;
	private float currentHeight = MIN_HEIGHT;
	private long lastUpdateMs;
	private int ledgerX;
	private int ledgerY;
	private float yaw;
	private float pitch = AccessMachinePipRenderState.DEFAULT_PITCH;
	private boolean viewportPressed;
	private boolean viewportDragged;
	private double pressX;
	private double pressY;
	private double lastDragX;
	private double lastDragY;

	public GuiAccessLedger(IContainerSidedAccess accessContainer, TileBase tile) {
		this.accessContainer = accessContainer;
		this.tile = tile;
		Arrays.fill(this.neighborStates, Blocks.AIR.defaultBlockState());
		resetViewToFront();
	}

	public void draw(GuiGraphicsExtractor graphics, Font font, int leftPos, int topPos, int imageWidth, int imageHeight, int mouseX, int mouseY) {
		updateAnimation();

		int width = Math.round(this.currentWidth);
		int height = Math.round(this.currentHeight);
		this.ledgerX = leftPos + imageWidth;
		this.ledgerY = topPos + imageHeight - height - 4;

		drawLedgerTab(graphics, this.ledgerX, this.ledgerY, width, height);
		graphics.fill(this.ledgerX + 6, this.ledgerY + 6, this.ledgerX + 18, this.ledgerY + 18, 0xFFCCCC44);

		if (width < MAX_WIDTH - 1) {
			if (isOverTab(mouseX, mouseY) && !this.open) {
				graphics.setTooltipForNextFrame(font,
						List.of(Component.translatable("for.gui.access")),
						Optional.<TooltipComponent>empty(), mouseX, mouseY);
			}
			return;
		}

		graphics.text(font, Component.translatable("for.gui.access"), this.ledgerX + 22, this.ledgerY + 6, 0xFFE1C92F, true);

		int viewX0 = this.ledgerX + VIEWPORT_PAD;
		int viewY0 = this.ledgerY + HEADER;
		int viewX1 = this.ledgerX + width - VIEWPORT_PAD;
		int viewY1 = this.ledgerY + height - VIEWPORT_PAD;
		if (viewX1 <= viewX0 || viewY1 <= viewY0) {
			return;
		}

		BlockState blockState = this.tile.getBlockState();
		AccessMode[] modes = new AccessMode[Direction.values().length];
		for (Direction direction : Direction.values()) {
			modes[direction.get3DDataValue()] = this.accessContainer.getAccess(direction);
		}
		ScreenRectangle scissor = new ScreenRectangle(viewX0, viewY0, viewX1 - viewX0, viewY1 - viewY0);
		graphics.guiRenderState.addPicturesInPictureState(new AccessMachinePipRenderState(
				blockState,
				this.neighborStates,
				modes,
				this.entityModel,
				this.yaw,
				this.pitch,
				AccessMachinePipRenderState.DEFAULT_SCALE,
				viewX0,
				viewY0,
				viewX1,
				viewY1,
				scissor));

		Direction hovered = pickFace(mouseX, mouseY, viewX0, viewY0, viewX1, viewY1);
		if (hovered != null) {
			AccessMode mode = this.accessContainer.getAccess(hovered);
			graphics.setTooltipForNextFrame(font, tooltipFor(hovered, mode, blockState), Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}

	public boolean mouseClicked(double mouseX, double mouseY, IntConsumer onSelect) {
		if (isOverTab(mouseX, mouseY) && this.currentWidth <= MIN_WIDTH + 1) {
			toggleOpen();
			return true;
		}

		if (!this.open || this.currentWidth < MAX_WIDTH - 1) {
			if (isOverLedger(mouseX, mouseY)) {
				toggleOpen();
				return true;
			}
			return false;
		}

		if (isOverViewport(mouseX, mouseY)) {
			this.viewportPressed = true;
			this.viewportDragged = false;
			this.pressX = mouseX;
			this.pressY = mouseY;
			this.lastDragX = mouseX;
			this.lastDragY = mouseY;
			return true;
		}

		if (isOverLedger(mouseX, mouseY)) {
			toggleOpen();
			return true;
		}
		return false;
	}

	public boolean mouseDragged(double mouseX, double mouseY) {
		if (!this.viewportPressed) {
			return false;
		}
		double dx = mouseX - this.lastDragX;
		double dy = mouseY - this.lastDragY;
		this.lastDragX = mouseX;
		this.lastDragY = mouseY;
		if (!this.viewportDragged) {
			double totalDx = mouseX - this.pressX;
			double totalDy = mouseY - this.pressY;
			if (totalDx * totalDx + totalDy * totalDy >= CLICK_DRAG_THRESHOLD * CLICK_DRAG_THRESHOLD) {
				this.viewportDragged = true;
			}
		}
		if (this.viewportDragged) {
			this.yaw += (float) dx * ROTATE_SENSITIVITY;
			this.pitch = Mth.clamp(this.pitch + (float) dy * ROTATE_SENSITIVITY, -MAX_PITCH, MAX_PITCH);
		}
		return true;
	}

	public boolean mouseReleased(double mouseX, double mouseY, IntConsumer onSelect) {
		if (!this.viewportPressed) {
			return false;
		}
		boolean wasDragged = this.viewportDragged;
		this.viewportPressed = false;
		this.viewportDragged = false;
		if (wasDragged) {
			return true;
		}
		Direction face = pickFace(mouseX, mouseY);
		if (face != null) {
			onSelect.accept(IContainerSidedAccess.accessButtonId(face));
			return true;
		}
		return true;
	}

	public List<Rect2i> getExtraAreas() {
		if (!this.open && this.currentWidth <= MIN_WIDTH + 1) {
			return List.of(new Rect2i(this.ledgerX, this.ledgerY, MIN_WIDTH, MIN_HEIGHT));
		}
		return List.of(new Rect2i(this.ledgerX, this.ledgerY, Math.round(this.currentWidth), Math.round(this.currentHeight)));
	}

	private Direction pickFace(double mouseX, double mouseY) {
		int width = Math.round(this.currentWidth);
		int height = Math.round(this.currentHeight);
		int viewX0 = this.ledgerX + VIEWPORT_PAD;
		int viewY0 = this.ledgerY + HEADER;
		int viewX1 = this.ledgerX + width - VIEWPORT_PAD;
		int viewY1 = this.ledgerY + height - VIEWPORT_PAD;
		return pickFace(mouseX, mouseY, viewX0, viewY0, viewX1, viewY1);
	}

	private Direction pickFace(double mouseX, double mouseY, int viewX0, int viewY0, int viewX1, int viewY1) {
		if (mouseX < viewX0 || mouseX >= viewX1 || mouseY < viewY0 || mouseY >= viewY1) {
			return null;
		}
		float cx = (viewX0 + viewX1) / 2.0f;
		float cy = (viewY0 + viewY1) / 2.0f;
		return AccessMachinePipRenderer.pickMachineFace(
				mouseX,
				mouseY,
				cx,
				cy,
				AccessMachinePipRenderState.DEFAULT_SCALE,
				this.yaw,
				this.pitch,
				this.entityModel);
	}

	private void toggleOpen() {
		this.open = !this.open;
		if (this.open) {
			refreshNeighbors();
			resetViewToFront();
		}
	}

	private void refreshNeighbors() {
		Level level = this.tile.getLevel();
		BlockPos pos = this.tile.getBlockPos();
		for (Direction direction : Direction.values()) {
			if (level != null && level.isLoaded(pos.relative(direction))) {
				this.neighborStates[direction.get3DDataValue()] = level.getBlockState(pos.relative(direction));
			} else {
				this.neighborStates[direction.get3DDataValue()] = Blocks.AIR.defaultBlockState();
			}
		}
	}

	private void resetViewToFront() {
		BlockState blockState = this.tile.getBlockState();
		this.entityModel = AccessMachinePipRenderer.usesEntityMachineModel(blockState);
		Direction facing = blockState.hasProperty(BlockMachine.FACING)
				? blockState.getValue(BlockMachine.FACING)
				: Direction.NORTH;
		this.yaw = AccessMachinePipRenderer.yawToShowFacing(facing, this.entityModel);
		this.pitch = AccessMachinePipRenderState.DEFAULT_PITCH;
	}

	private boolean isOverTab(double mouseX, double mouseY) {
		return mouseX >= this.ledgerX && mouseX < this.ledgerX + MIN_WIDTH
				&& mouseY >= this.ledgerY && mouseY < this.ledgerY + MIN_HEIGHT;
	}

	private boolean isOverLedger(double mouseX, double mouseY) {
		return mouseX >= this.ledgerX && mouseX < this.ledgerX + this.currentWidth
				&& mouseY >= this.ledgerY && mouseY < this.ledgerY + this.currentHeight;
	}

	private boolean isOverViewport(double mouseX, double mouseY) {
		int width = Math.round(this.currentWidth);
		int height = Math.round(this.currentHeight);
		int viewX0 = this.ledgerX + VIEWPORT_PAD;
		int viewY0 = this.ledgerY + HEADER;
		int viewX1 = this.ledgerX + width - VIEWPORT_PAD;
		int viewY1 = this.ledgerY + height - VIEWPORT_PAD;
		return mouseX >= viewX0 && mouseX < viewX1 && mouseY >= viewY0 && mouseY < viewY1;
	}

	private void updateAnimation() {
		long now = System.currentTimeMillis();
		if (this.lastUpdateMs == 0) {
			this.lastUpdateMs = now;
			return;
		}
		float move = ANIM_SPEED * (now - this.lastUpdateMs) / 16.667f;
		this.lastUpdateMs = now;

		if (this.open && this.currentWidth < MAX_WIDTH) {
			this.currentWidth = Math.min(MAX_WIDTH, this.currentWidth + move);
		} else if (!this.open && this.currentWidth > MIN_WIDTH) {
			this.currentWidth = Math.max(MIN_WIDTH, this.currentWidth - move);
		}

		if (this.open && this.currentHeight < MAX_HEIGHT) {
			this.currentHeight = Math.min(MAX_HEIGHT, this.currentHeight + move);
		} else if (!this.open && this.currentHeight > MIN_HEIGHT) {
			this.currentHeight = Math.max(MIN_HEIGHT, this.currentHeight - move);
		}
	}

	private static List<Component> tooltipFor(Direction worldDirection, AccessMode mode, BlockState blockState) {
		return List.of(
				Component.translatable("for.gui.access.face", relativeLabel(worldDirection, blockState)),
				Component.translatable("for.gui.access.mode." + mode.name().toLowerCase())
		);
	}

	private static Component relativeLabel(Direction worldDirection, BlockState blockState) {
		if (worldDirection == Direction.UP) {
			return Component.translatable("for.gui.access.up");
		}
		if (worldDirection == Direction.DOWN) {
			return Component.translatable("for.gui.access.down");
		}
		Direction facing = blockState.hasProperty(BlockMachine.FACING)
				? blockState.getValue(BlockMachine.FACING)
				: Direction.NORTH;
		if (worldDirection == facing) {
			return Component.translatable("for.gui.access.front");
		}
		if (worldDirection == facing.getOpposite()) {
			return Component.translatable("for.gui.access.back");
		}
		if (worldDirection == facing.getClockWise()) {
			return Component.translatable("for.gui.access.left");
		}
		if (worldDirection == facing.getCounterClockWise()) {
			return Component.translatable("for.gui.access.right");
		}
		return Component.translatable("for.gui.access." + worldDirection.getSerializedName());
	}

	private static void drawLedgerTab(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x, y + 4, 0.0f, TEXTURE_SIZE - height + 4, 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x + 4, y, TEXTURE_SIZE - width + 4, 0.0f, width - 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x, y, 0.0f, 0.0f, 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x + 4, y + 4, TEXTURE_SIZE - width + 4, TEXTURE_SIZE - height + 4, width - 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
	}
}
