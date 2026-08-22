package com.leon1236.reforestry.core.book.client;

import java.util.List;
import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.book.IBookPage;
import com.leon1236.reforestry.api.gui.MachineRecipeEntry;
import com.leon1236.reforestry.core.book.BookPage;
import com.leon1236.reforestry.core.recipes.CraftingPatternHelper;

@Environment(EnvType.CLIENT)
public final class BookPageRenderer {
	private static final int TEXT_COLOR = 0xFF404040;
	private static final int GHOST_OVERLAY = 0x66FFFFFF;
	private static final Identifier CRAFTING_TEXTURE = ReForestry.id("textures/gui/almanac/crafting.png");
	private static final Identifier FARM_GUI_TEXTURE = ReForestry.id("textures/gui/almanac/guis/multi_farm_1024.png");

	private BookPageRenderer() {
	}

	public static void render(
			GuiGraphicsExtractor graphics,
			Font font,
			Level level,
			IBookPage page,
			int left,
			int top,
			int width,
			int height
	) {
		switch (page.type()) {
			case "text" -> renderText(graphics, font, page, left, top, width);
			case "crafting" -> renderCrafting(graphics, font, level, page, left, top, width);
			case "spotlight" -> renderSpotlight(graphics, font, page, left, top, width);
			case "image" -> renderImage(graphics, page, left, top);
			case "carpenter/single", "carpenter/double" -> renderCarpenter(graphics, font, level, page, left, top, width);
			case "fabricator/single", "fabricator/double" -> renderFabricator(graphics, font, level, page, left, top, width);
			case "farm_gui" -> renderFarmGui(graphics, font, page, left, top, width);
			case "farm_layout", "farm_layout_large" -> renderFarmLayout(graphics, font, page, left, top, width);
			default -> renderText(graphics, font, page, left, top, width);
		}
	}

	private static void renderText(GuiGraphicsExtractor graphics, Font font, IBookPage page, int left, int top, int width) {
		if (page.textKey().isEmpty()) {
			return;
		}
		Component text = BookTextHelper.resolve(page.textKey());
		graphics.textWithWordWrap(font, text, left, top, width, TEXT_COLOR, false);
	}

	private static void renderSpotlight(GuiGraphicsExtractor graphics, Font font, IBookPage page, int left, int top, int width) {
		int y = top;
		if (!page.titleKey().isEmpty()) {
			Component title = BookTextHelper.resolve(page.titleKey());
			int titleWidth = font.width(title);
			graphics.text(font, title, left + (width - titleWidth) / 2, y, TEXT_COLOR, false);
			y += 14;
		}
		ItemStack stack = stackFromId(page.itemId());
		if (!stack.isEmpty()) {
			graphics.item(stack, left + width / 2 - 8, y + 4);
			y += 28;
		}
		if (!page.textKey().isEmpty()) {
			graphics.textWithWordWrap(font, BookTextHelper.resolve(page.textKey()), left, y, width, TEXT_COLOR, false);
		}
	}

	private static void renderCrafting(GuiGraphicsExtractor graphics, Font font, Level level, IBookPage page, int left, int top, int width) {
		int y = top;
		if (!page.titleKey().isEmpty()) {
			Component title = BookTextHelper.resolve(page.titleKey());
			int titleWidth = font.width(title);
			graphics.text(font, title, left + (width - titleWidth) / 2, y, TEXT_COLOR, false);
			y += 14;
		}
		boolean dual = !BookPage.EMPTY_ID.equals(page.recipeId2());
		int gridX = dual ? left + 4 : left + (width - 108) / 2;
		drawCraftingPage(graphics, level, page.recipeId(), gridX, y);
		if (dual) {
			drawCraftingPage(graphics, level, page.recipeId2(), left + width / 2 + 4, y);
		}
		y += 72;
		if (!page.textKey().isEmpty()) {
			graphics.textWithWordWrap(font, BookTextHelper.resolve(page.textKey()), left, y, width, TEXT_COLOR, false);
		}
	}

	private static void drawCraftingPage(GuiGraphicsExtractor graphics, Level level, Identifier recipeId, int x, int y) {
		CraftingRecipe crafting = findCraftingRecipe(level, recipeId);
		if (crafting == null) {
			return;
		}
		ItemStack result = crafting.assemble(CraftingInput.EMPTY);
		List<List<ItemStack>> pattern = CraftingPatternHelper.patternSlotsFromCraftingRecipe(crafting);
		drawCraftingGrid(graphics, pattern, result, x, y);
	}

	private static void renderCarpenter(GuiGraphicsExtractor graphics, Font font, Level level, IBookPage page, int left, int top, int width) {
		int y = top;
		drawMachinePair(graphics, font, page, left, y, width, true);
		y += BookPage.EMPTY_ID.equals(page.itemId2()) ? 72 : 136;
		if (!page.textKey().isEmpty()) {
			graphics.textWithWordWrap(font, BookTextHelper.resolve(page.textKey()), left, y, width, TEXT_COLOR, false);
		}
	}

	private static void renderFabricator(GuiGraphicsExtractor graphics, Font font, Level level, IBookPage page, int left, int top, int width) {
		int y = top;
		drawMachinePair(graphics, font, page, left, y, width, false);
		y += BookPage.EMPTY_ID.equals(page.itemId2()) ? 72 : 136;
		if (!page.textKey().isEmpty()) {
			graphics.textWithWordWrap(font, BookTextHelper.resolve(page.textKey()), left, y, width, TEXT_COLOR, false);
		}
	}

	private static void drawMachinePair(
			GuiGraphicsExtractor graphics,
			Font font,
			IBookPage page,
			int left,
			int top,
			int width,
			boolean carpenter
	) {
		if ("carpenter/double".equals(page.type()) || "fabricator/double".equals(page.type())) {
			drawMachineByItem(graphics, font, page.itemId(), left + 4, top, carpenter);
			drawMachineByItem(graphics, font, page.itemId2(), left + width / 2 + 4, top + 64, carpenter);
			return;
		}
		drawMachineByItem(graphics, font, page.itemId(), left + (width - 108) / 2, top, carpenter);
	}

	private static void drawMachineByItem(
			GuiGraphicsExtractor graphics,
			Font font,
			Identifier itemId,
			int x,
			int y,
			boolean carpenter
	) {
		ItemStack stack = stackFromId(itemId);
		Optional<MachineRecipeEntry> recipe = carpenter ? BookRecipeCache.carpenter(stack) : BookRecipeCache.fabricator(stack);
		recipe.ifPresent(entry -> drawMachineRecipe(graphics, font, entry, x, y));
	}

	private static void renderFarmGui(GuiGraphicsExtractor graphics, Font font, IBookPage page, int left, int top, int width) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, FARM_GUI_TEXTURE, left + (width - 96) / 2, top + 4, 0.0f, 0.0f, 96, 54, 256, 256);
		if (!page.textKey().isEmpty()) {
			graphics.textWithWordWrap(font, BookTextHelper.format(page.textKey()), left, top + 64, width, TEXT_COLOR, false);
		}
	}

	private static void renderFarmLayout(GuiGraphicsExtractor graphics, Font font, IBookPage page, int left, int top, int width) {
		if (!BookPage.EMPTY_ID.equals(page.imageId())) {
			int drawWidth = page.imageWidth() > 0 ? page.imageWidth() : 89;
			int drawHeight = page.imageHeight() > 0 ? page.imageHeight() : 89;
			graphics.blit(
					RenderPipelines.GUI_TEXTURED,
					page.imageId(),
					left + (width - drawWidth) / 2,
					top + 4,
					0.0f,
					0.0f,
					drawWidth,
					drawHeight,
					page.textureWidth(),
					page.textureHeight()
			);
		}
		if (!page.textKey().isEmpty()) {
			int textTop = top + (page.imageHeight() > 0 ? page.imageHeight() : 89) + 8;
			graphics.textWithWordWrap(font, BookTextHelper.format(page.textKey()), left, textTop, width, TEXT_COLOR, false);
		}
	}

	private static void renderImage(GuiGraphicsExtractor graphics, IBookPage page, int left, int top) {
		if (BookPage.EMPTY_ID.equals(page.imageId())) {
			return;
		}
		int drawWidth = page.imageWidth() > 0 ? page.imageWidth() : 108;
		int drawHeight = page.imageHeight() > 0 ? page.imageHeight() : 60;
		graphics.blit(
				RenderPipelines.GUI_TEXTURED,
				page.imageId(),
				left,
				top,
				0.0f,
				0.0f,
				drawWidth,
				drawHeight,
				page.textureWidth(),
				page.textureHeight()
		);
	}

	private static void drawMachineRecipe(GuiGraphicsExtractor graphics, Font font, MachineRecipeEntry entry, int x, int y) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, CRAFTING_TEXTURE, x, y, 0.0f, 0.0f, 108, 60, 256, 256);
		if (entry.hasPattern()) {
			for (int slot = 0; slot < 9; slot++) {
				List<ItemStack> options = entry.pattern().get(slot);
				if (!options.isEmpty()) {
					int row = slot / 3;
					int col = slot % 3;
					ItemStack display = options.get((int) (System.currentTimeMillis() / MachineRecipeEntry.CYCLE_MS % options.size()));
					graphics.item(display, x + 1 + col * 19, y + 3 + row * 19);
					if (options.size() > 1) {
						graphics.fill(x + 1 + col * 19, y + 3 + row * 19, x + 17 + col * 19, y + 19 + row * 19, GHOST_OVERLAY);
					}
				}
			}
		}
		if (!entry.result().isEmpty()) {
			graphics.item(entry.result(), x + 71, y + 41);
		}
		if (entry.hasLiquid()) {
			FluidVariant fluid = entry.liquid().orElse(FluidVariant.blank());
			if (!fluid.isBlank()) {
				int color = FluidVariantRendering.getColor(fluid);
				graphics.fill(x + 91, y + 1, x + 107, y + 59, (color & 0x00FFFFFF) | 0x99000000);
			}
			graphics.blit(RenderPipelines.GUI_TEXTURED, CRAFTING_TEXTURE, x + 90, y, 108.0f, 0.0f, 18, 60, 256, 256);
		}
		Component title = entry.result().getHoverName();
		int titleWidth = font.width(title);
		graphics.text(font, title, x - 1 + (108 - titleWidth) / 2, y - 10, TEXT_COLOR, false);
	}

	private static void drawCraftingGrid(
			GuiGraphicsExtractor graphics,
			List<List<ItemStack>> pattern,
			ItemStack result,
			int x,
			int y
	) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, CRAFTING_TEXTURE, x, y, 0.0f, 0.0f, 108, 60, 256, 256);
		for (int slot = 0; slot < Math.min(9, pattern.size()); slot++) {
			List<ItemStack> options = pattern.get(slot);
			if (!options.isEmpty()) {
				int row = slot / 3;
				int col = slot % 3;
				graphics.item(options.get(0), x + 1 + col * 19, y + 3 + row * 19);
			}
		}
		if (!result.isEmpty()) {
			graphics.item(result, x + 71, y + 41);
		}
	}

	private static ItemStack stackFromId(Identifier id) {
		if (BookPage.EMPTY_ID.equals(id)) {
			return ItemStack.EMPTY;
		}
		return BuiltInRegistries.ITEM.get(id)
				.map(holder -> new ItemStack(holder.value()))
				.orElse(ItemStack.EMPTY);
	}

	@Nullable
	private static CraftingRecipe findCraftingRecipe(Level level, Identifier recipeId) {
		if (BookPage.EMPTY_ID.equals(recipeId)) {
			return null;
		}
		ResourceKey<Recipe<?>> key = ResourceKey.create(Registries.RECIPE, recipeId);
		for (RecipeHolder<?> holder : level.recipeAccess().getSynchronizedRecipes().recipes()) {
			if (holder.id().equals(key) && holder.value() instanceof CraftingRecipe crafting) {
				return crafting;
			}
		}
		return null;
	}
}
