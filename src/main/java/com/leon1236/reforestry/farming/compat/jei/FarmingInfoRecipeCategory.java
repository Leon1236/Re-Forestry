package com.leon1236.reforestry.farming.compat.jei;

import java.util.List;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.api.agriculture.Soil;
import com.leon1236.reforestry.api.circuits.ICircuit;
import com.leon1236.reforestry.core.circuits.EnumCircuitBoardType;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.JeiRecipeLayoutHelper;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.features.CoreItems;

public class FarmingInfoRecipeCategory extends ForestryRecipeCategory<FarmingInfoRecipe> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/jei/recipes.png");
	private static final int DARK_GRAY = 0xFF404040;

	private final IDrawable slotDrawable;
	private final IDrawable addition;
	private final IDrawable arrow;
	private final IDrawable icon;

	public FarmingInfoRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createBlankDrawable(144, 90), "for.jei.farming");
		this.slotDrawable = guiHelper.getSlotDrawable();
		this.addition = guiHelper.createDrawable(TEXTURE, 44, 0, 15, 15);
		this.arrow = guiHelper.createDrawable(TEXTURE, 59, 0, 15, 15);
		ItemStack intricateCircuitboard = new ItemStack(CoreItems.CIRCUITBOARDS.item(EnumCircuitBoardType.INTRICATE));
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, intricateCircuitboard);
	}

	@Override
	public IRecipeType<FarmingInfoRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.FARMING;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, FarmingInfoRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 64, 19)
				.setBackground(this.slotDrawable, -1, -1)
				.add(recipe.tube());

		IFarmType properties = recipe.properties();

		List<IRecipeSlotBuilder> soilSlots = JeiRecipeLayoutHelper.layoutSlotGrid(builder, RecipeIngredientRole.INPUT, 2, 2, 1, 55, 18);
		List<IRecipeSlotBuilder> germlingSlots = JeiRecipeLayoutHelper.layoutSlotGrid(builder, RecipeIngredientRole.INPUT, 2, 2, 55, 55, 18);
		List<IRecipeSlotBuilder> productSlots = JeiRecipeLayoutHelper.layoutSlotGrid(builder, RecipeIngredientRole.OUTPUT, 2, 2, 109, 55, 18);
		int soilSlotsSize = soilSlots.size();
		int germlingSlotsSize = germlingSlots.size();
		int productSlotsSize = productSlots.size();

		soilSlots.forEach(slot -> slot.setBackground(this.slotDrawable, -1, -1));
		germlingSlots.forEach(slot -> slot.setBackground(this.slotDrawable, -1, -1));
		productSlots.forEach(slot -> slot.setBackground(this.slotDrawable, -1, -1));

		int[] germlingSlotIndex = {0};
		int[] productSlotIndex = {0};
		int soilSlotIndex = 0;

		for (IFarmable farmable : properties.getFarmables()) {
			farmable.addGermlings(germling -> germlingSlots.get(germlingSlotIndex[0]++ % germlingSlotsSize).add(germling));
			farmable.addProducts(product -> productSlots.get(productSlotIndex[0]++ % productSlotsSize).add(product));
		}
		for (Soil soil : properties.getSoils()) {
			soilSlots.get(soilSlotIndex++ % soilSlotsSize).add(soil.resource());
		}
	}

	@Override
	public void draw(FarmingInfoRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.addition.draw(graphics, 37, 64);
		this.arrow.draw(graphics, 91, 64);
		int recipeWidth = this.getWidth();
		Font font = Minecraft.getInstance().font;
		ICircuit circuit = recipe.circuit();
		int textX = (recipeWidth - font.width(circuit.getDisplayName().getString())) / 2;
		graphics.text(font, circuit.getDisplayName(), textX, 3, DARK_GRAY, false);

		Component soilName = Component.translatable("for.jei.farming.soil");
		graphics.text(font, soilName, 18 - (font.width(soilName.getString())) / 2, 45, DARK_GRAY, false);

		Component germlingsName = Component.translatable("for.jei.farming.germlings");
		graphics.text(font, germlingsName, (recipeWidth - font.width(germlingsName.getString())) / 2, 45, DARK_GRAY, false);

		Component productsName = Component.translatable("for.jei.farming.products");
		graphics.text(font, productsName, 126 - (font.width(productsName.getString())) / 2, 45, DARK_GRAY, false);
	}
}
