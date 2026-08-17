package com.leon1236.reforestry.core.client;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.gui.IContainerClimate;
import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.api.gui.IContainerRecipeBook;
import com.leon1236.reforestry.api.gui.IContainerSidedAccess;
import com.leon1236.reforestry.api.gui.MachineRecipeEntry;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;
import com.leon1236.reforestry.core.gui.SlotGhostCrafting;

@Environment(EnvType.CLIENT)
public abstract class ScreenForestry<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
	private static final int TITLE_COLOR = 0xFF404040;
	private static final int GHOST_OVERLAY = 0x66FFFFFF;
	private static final int GHOST_FLUID_ALPHA = 0x66000000;

	private final List<TankClickRegion> tankRegions = new ArrayList<>();
	@Nullable
	private GuiRecipeLedger recipeLedger;
	@Nullable
	private GuiAccessLedger accessLedger;
	@Nullable
	private GuiPowerLedger powerLedger;
	@Nullable
	private GuiClimateLedger climateLedger;
	@Nullable
	private GuiHintLedger hintLedger;
	@Nullable
	private String hintKey;
	@Nullable
	private FluidVariant recipeGhostFluid;
	private int recipeGhostAmountMb;
	@Nullable
	private List<List<ItemStack>> recipeGhostPattern;

	protected ScreenForestry(T menu, Inventory inventory, Component title, int imageWidth, int imageHeight) {
		super(menu, inventory, title, imageWidth, imageHeight);
		if (menu instanceof IContainerRecipeBook recipeBook) {
			this.recipeLedger = new GuiRecipeLedger(recipeBook);
		}
		if (menu instanceof ContainerMachine<?> machine && menu instanceof IContainerSidedAccess sidedAccess) {
			this.accessLedger = new GuiAccessLedger(sidedAccess, machine.getTile());
		}
		if (menu instanceof IContainerEnergy energy) {
			this.powerLedger = new GuiPowerLedger(energy);
		}
		if (menu instanceof IContainerClimate climate) {
			int climateTopOffset = this.powerLedger != null ? 36 : 8;
			this.climateLedger = new GuiClimateLedger(climate, climateTopOffset);
		}
	}

	protected void setHintKey(@Nullable String hintKey) {
		this.hintKey = hintKey;
	}

	@Override
	protected void init() {
		super.init();
		this.hintLedger = null;
		if (this.hintKey != null) {
			List<String> hints = ForestryHints.get(this.hintKey);
			if (!hints.isEmpty()) {
				this.hintLedger = new GuiHintLedger(hints, this.font);
			}
		}
	}

	protected void addTankClickRegion(int x, int y, int width, int height, int tankSlot) {
		this.tankRegions.add(new TankClickRegion(x, y, width, height, tankSlot));
	}

	protected boolean hasRecipeGhostLiquid() {
		return this.recipeGhostFluid != null && !this.recipeGhostFluid.isBlank() && this.recipeGhostAmountMb > 0;
	}

	@Nullable
	protected FluidVariant getRecipeGhostFluid() {
		return this.recipeGhostFluid;
	}

	protected int getRecipeGhostAmountMb() {
		return this.recipeGhostAmountMb;
	}

	protected void clearRecipeGhostLiquid() {
		this.recipeGhostFluid = null;
		this.recipeGhostAmountMb = 0;
	}

	protected void setRecipeGhostPattern(@Nullable List<List<ItemStack>> pattern) {
		this.recipeGhostPattern = pattern;
	}

	protected void clearRecipeGhostPattern() {
		this.recipeGhostPattern = null;
	}

	protected void setRecipeGhostLiquid(@Nullable FluidVariant fluid, int amountMb) {
		if (fluid == null || fluid.isBlank() || amountMb <= 0) {
			clearRecipeGhostLiquid();
			return;
		}
		this.recipeGhostFluid = fluid;
		this.recipeGhostAmountMb = amountMb;
	}

	protected void drawRecipeGhostTank(
			GuiGraphicsExtractor graphics,
			int tankX,
			int tankY,
			int tankWidth,
			int tankHeight,
			int capacityMb,
			int color,
			boolean tankEmpty
	) {
		if (!tankEmpty || !hasRecipeGhostLiquid() || capacityMb <= 0) {
			return;
		}
		int filled = Math.min(tankHeight, Math.max(1, tankHeight * this.recipeGhostAmountMb / capacityMb));
		int x = this.leftPos + tankX;
		int y = this.topPos + tankY + (tankHeight - filled);
		graphics.fill(x, y, x + tankWidth, y + filled, (color & 0x00FFFFFF) | GHOST_FLUID_ALPHA);
		graphics.fill(x, y, x + tankWidth, y + filled, GHOST_OVERLAY);
	}

	protected void appendRecipeGhostTankTooltip(List<Component> lines) {
		if (!hasRecipeGhostLiquid()) {
			return;
		}
		lines.add(FluidVariantAttributes.getName(this.recipeGhostFluid));
		lines.add(Component.translatable("for.gui.recipes.liquid.required", this.recipeGhostAmountMb));
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		super.extractRenderState(graphics, mouseX, mouseY, partialTick);
		if (this.hintLedger != null) {
			this.hintLedger.draw(graphics, this.font, this.leftPos, this.topPos, this.imageHeight, mouseX, mouseY);
		}
		if (this.recipeLedger != null) {
			this.recipeLedger.draw(graphics, this.font, this.leftPos, this.topPos, this.imageHeight, mouseX, mouseY);
		}
		if (this.powerLedger != null) {
			this.powerLedger.draw(graphics, this.font, this.leftPos, this.topPos, this.imageWidth, mouseX, mouseY);
		}
		if (this.climateLedger != null) {
			this.climateLedger.draw(graphics, this.font, this.leftPos, this.topPos, this.imageWidth, mouseX, mouseY);
		}
		if (this.accessLedger != null) {
			this.accessLedger.draw(graphics, this.font, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, mouseX, mouseY);
		}
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (this.powerLedger != null && this.powerLedger.mouseClicked(event.x(), event.y())) {
			return true;
		}
		if (this.climateLedger != null && this.climateLedger.mouseClicked(event.x(), event.y())) {
			return true;
		}
		if (this.hintLedger != null && this.hintLedger.mouseClicked(event.x(), event.y())) {
			return true;
		}
		if (tryAccessLedgerClick(event)) {
			return true;
		}
		if (tryRecipeLedgerClick(event)) {
			return true;
		}
		if (tryPipetteTankClick(event)) {
			return true;
		}
		return super.mouseClicked(event, doubleClick);
	}

	@Override
	public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
		if (this.recipeLedger != null && this.recipeLedger.mouseScrolled(x, y, scrollY)) {
			return true;
		}
		return super.mouseScrolled(x, y, scrollX, scrollY);
	}

	protected boolean tryAccessLedgerClick(MouseButtonEvent event) {
		if (this.accessLedger == null || !(this.menu instanceof IContainerSidedAccess)) {
			return false;
		}
		LocalPlayer player = this.minecraft.player;
		MultiPlayerGameMode gameMode = this.minecraft.gameMode;
		if (player == null || gameMode == null) {
			return false;
		}
		return this.accessLedger.mouseClicked(event.x(), event.y(), buttonId -> {
			if (this.menu.clickMenuButton(player, buttonId)) {
				gameMode.handleInventoryButtonClick(this.menu.containerId, buttonId);
			}
		});
	}

	protected boolean tryRecipeLedgerClick(MouseButtonEvent event) {
		if (this.recipeLedger == null || !(this.menu instanceof IContainerRecipeBook recipeBook)) {
			return false;
		}
		LocalPlayer player = this.minecraft.player;
		MultiPlayerGameMode gameMode = this.minecraft.gameMode;
		if (player == null || gameMode == null) {
			return false;
		}
		return this.recipeLedger.mouseClicked(event.x(), event.y(), index -> {
			List<MachineRecipeEntry> recipes = recipeBook.getGuiRecipes();
			if (index >= 0 && index < recipes.size()) {
				MachineRecipeEntry entry = recipes.get(index);
				if (entry.hasLiquid()) {
					setRecipeGhostLiquid(entry.liquid().orElse(null), entry.liquidAmountMb());
				} else {
					clearRecipeGhostLiquid();
				}
				if (entry.hasPattern()) {
					setRecipeGhostPattern(entry.pattern());
				} else {
					clearRecipeGhostPattern();
				}
			}
			int buttonId = IContainerRecipeBook.recipeButtonId(index);
			if (this.menu.clickMenuButton(player, buttonId)) {
				gameMode.handleInventoryButtonClick(this.menu.containerId, buttonId);
			}
		});
	}

	protected boolean tryPipetteTankClick(MouseButtonEvent event) {
		if (!(this.menu instanceof IContainerLiquidTanks)) {
			return false;
		}
		LocalPlayer player = this.minecraft.player;
		MultiPlayerGameMode gameMode = this.minecraft.gameMode;
		if (player == null || gameMode == null) {
			return false;
		}
		ItemStack carried = this.menu.getCarried();
		if (!PipetteTankHelper.canHandleClick(carried)) {
			return false;
		}
		for (TankClickRegion region : this.tankRegions) {
			if (isHovering(region.x, region.y, region.width, region.height, event.x(), event.y())
					&& this.menu.clickMenuButton(player, region.tankSlot)) {
				gameMode.handleInventoryButtonClick(this.menu.containerId, region.tankSlot);
				return true;
			}
		}
		return false;
	}

	@Override
	protected void extractSlot(GuiGraphicsExtractor graphics, Slot slot, int mouseX, int mouseY) {
		if (slot instanceof SlotGhostCrafting) {
			int x = slot.x;
			int y = slot.y;
			int seed = slot.x + slot.y * this.imageWidth;
			ItemStack display = cyclingGhostStack(slot);
			if (!display.isEmpty()) {
				graphics.fakeItem(display, x, y, seed);
				graphics.fill(x, y, x + 16, y + 16, GHOST_OVERLAY);
			}
			return;
		}
		super.extractSlot(graphics, slot, mouseX, mouseY);
	}

	private ItemStack cyclingGhostStack(Slot slot) {
		ItemStack actual = slot.getItem();
		if (this.recipeGhostPattern == null) {
			return actual;
		}
		int index = slot.getContainerSlot();
		if (index < 0 || index >= this.recipeGhostPattern.size()) {
			return actual;
		}
		List<ItemStack> alternatives = this.recipeGhostPattern.get(index);
		if (alternatives.isEmpty()) {
			return actual;
		}
		if (!actual.isEmpty()) {
			boolean matches = false;
			for (ItemStack alternative : alternatives) {
				if (ItemStack.isSameItemSameComponents(actual, alternative)) {
					matches = true;
					break;
				}
			}
			if (!matches) {
				return actual;
			}
		}
		int cycle = MachineRecipeEntry.cycleIndex(alternatives.size(), System.currentTimeMillis());
		return alternatives.get(cycle);
	}

	public List<Rect2i> getRecipeLedgerAreas() {
		List<Rect2i> areas = new ArrayList<>();
		if (this.hintLedger != null) {
			areas.addAll(this.hintLedger.getExtraAreas());
		}
		if (this.recipeLedger != null) {
			areas.addAll(this.recipeLedger.getExtraAreas());
		}
		if (this.powerLedger != null) {
			areas.addAll(this.powerLedger.getExtraAreas());
		}
		if (this.climateLedger != null) {
			areas.addAll(this.climateLedger.getExtraAreas());
		}
		if (this.accessLedger != null) {
			areas.addAll(this.accessLedger.getExtraAreas());
		}
		return areas;
	}

	@Nullable
	public GuiRecipeLedger.HoveredIngredient getRecipeLedgerIngredient(double mouseX, double mouseY) {
		if (this.recipeLedger == null) {
			return null;
		}
		return this.recipeLedger.getHoveredIngredient(mouseX, mouseY);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int titleWidth = this.font.width(this.title);
		graphics.text(this.font, this.title, (this.imageWidth - titleWidth) / 2, this.titleLabelY, TITLE_COLOR, false);
	}

	private record TankClickRegion(int x, int y, int width, int height, int tankSlot) {
	}
}
