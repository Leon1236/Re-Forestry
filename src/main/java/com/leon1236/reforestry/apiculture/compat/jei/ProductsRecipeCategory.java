package com.leon1236.reforestry.apiculture.compat.jei;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.core.compat.jei.ChanceTooltipCallback;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;

public class ProductsRecipeCategory extends ForestryRecipeCategory<ProductRecipe> {
	private static final int SPECIES_SLOT_X = 30;
	private static final int SPECIES_SLOT_Y = 16;
	private static final int PRODUCT_SLOTS_X = 93;
	private static final int PRODUCT_SLOTS_Y = 5;
	private static final int SPECIALTY_SLOTS_Y = 33;

	private final IDrawable icon;

	public ProductsRecipeCategory(IDrawable background, IDrawable icon) {
		super(background, "for.jei.products.reforestry.bee_species");
		this.icon = icon;
	}

	@Override
	public IRecipeType<ProductRecipe> getRecipeType() {
		return ApicultureJeiRecipeTypes.BEE_PRODUCTS;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ProductRecipe recipe, IFocusGroup focuses) {
		builder.addInvisibleIngredients(RecipeIngredientRole.INPUT).addItemStacks(recipe.inputs);

		builder.addSlot(RecipeIngredientRole.INPUT, SPECIES_SLOT_X, SPECIES_SLOT_Y)
				.add(recipe.displayInput);

		if (!recipe.products.isEmpty()) {
			setProductsList(builder, recipe.products, PRODUCT_SLOTS_Y);
		}
		if (!recipe.specialties.isEmpty()) {
			setProductsList(builder, recipe.specialties, SPECIALTY_SLOTS_Y);
		}
	}

	private static void setProductsList(IRecipeLayoutBuilder builder, List<IProduct> products, int slotsY) {
		List<IProduct> sorted = products.stream()
				.sorted(Comparator.comparing(IProduct::chance).reversed())
				.toList();
		if (sorted.size() <= 3) {
			for (int i = 0; i < sorted.size(); i++) {
				IProduct product = sorted.get(i);
				builder.addSlot(RecipeIngredientRole.OUTPUT, PRODUCT_SLOTS_X + i * 22, slotsY)
						.add(product.createStack())
						.addRichTooltipCallback(new ChanceTooltipCallback(product.chance()));
			}
			return;
		}

		LinkedList<ItemStack> cycling = new LinkedList<>();
		for (IProduct product : sorted) {
			cycling.add(product.createStack());
		}
		IRecipeSlotRichTooltipCallback callback = new ProductChanceTooltip(sorted);
		for (int i = 0; i < 3; i++) {
			builder.addSlot(RecipeIngredientRole.OUTPUT, PRODUCT_SLOTS_X + i * 22, slotsY)
					.addItemStacks(new ArrayList<>(cycling))
					.addRichTooltipCallback(callback);
			cycling.addLast(cycling.removeFirst());
		}
	}

	@Override
	public void draw(ProductRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		BeeJeiHelper.drawCentered(graphics, BeeJeiHelper.speciesName(recipe.speciesId), SPECIES_SLOT_X + 9, SPECIES_SLOT_Y + 22, 0xffffff);
	}

	private record ProductChanceTooltip(List<IProduct> products) implements IRecipeSlotRichTooltipCallback {
		@Override
		public void onRichTooltip(IRecipeSlotView recipeSlotView, ITooltipBuilder tooltip) {
			recipeSlotView.getDisplayedItemStack().ifPresent(stack -> {
				for (IProduct product : this.products) {
					if (product.item() == stack.getItem()) {
						new ChanceTooltipCallback(product.chance()).onRichTooltip(recipeSlotView, tooltip);
						return;
					}
				}
			});
		}
	}
}
