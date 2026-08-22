package com.leon1236.reforestry.extratrees.compat.jei;

import java.util.List;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.extratrees.blocks.ExtraTreeMachineType;
import com.leon1236.reforestry.extratrees.features.ExtraTreesBlocks;
import com.leon1236.reforestry.extratrees.features.ExtraTreesItems;
import com.leon1236.reforestry.extratrees.recipes.BreweryRecipeManager;
import com.leon1236.reforestry.extratrees.recipes.DistilleryRecipeManager;
import com.leon1236.reforestry.extratrees.recipes.FruitPressRecipeManager;
import com.leon1236.reforestry.extratrees.tags.ExtraTreesTags;
import com.leon1236.reforestry.extratrees.tiles.TileBrewery;
import com.leon1236.reforestry.extratrees.tiles.TileDistillery;
import com.leon1236.reforestry.extratrees.tiles.TilePress;

@JeiPlugin
public class ExtraTreesJeiPlugin implements IModPlugin {
	public static final IRecipeType<FruitPressRecipeManager.FruitPressRecipe> FRUIT_PRESS =
			IRecipeType.create(ReForestry.id("fruit_press"), FruitPressRecipeManager.FruitPressRecipe.class);
	public static final IRecipeType<BreweryRecipeManager.BreweryRecipe> BREWERY =
			IRecipeType.create(ReForestry.id("brewery"), BreweryRecipeManager.BreweryRecipe.class);
	public static final IRecipeType<DistilleryRecipeManager.DistilleryRecipe> DISTILLERY =
			IRecipeType.create(ReForestry.id("distillery"), DistilleryRecipeManager.DistilleryRecipe.class);

	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("extra_trees");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		if (!extraTreesEnabled()) {
			return;
		}
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IPlatformFluidHelper<?> fluidHelper = jeiHelpers.getPlatformFluidHelper();
		registry.addRecipeCategories(
				new FruitPressCategory<>(jeiHelpers.getGuiHelper(), fluidHelper),
				new BreweryCategory<>(jeiHelpers.getGuiHelper(), fluidHelper),
				new DistilleryCategory<>(jeiHelpers.getGuiHelper(), fluidHelper)
		);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if (!extraTreesEnabled()) {
			return;
		}
		registration.addRecipes(FRUIT_PRESS, List.copyOf(FruitPressRecipeManager.recipes()));
		registration.addRecipes(BREWERY, List.copyOf(BreweryRecipeManager.recipes()));
		registration.addRecipes(DISTILLERY, List.copyOf(DistilleryRecipeManager.recipes()));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if (!extraTreesEnabled()) {
			return;
		}
		registration.addCraftingStation(FRUIT_PRESS, ExtraTreesBlocks.MACHINES.get(ExtraTreeMachineType.PRESS).block());
		registration.addCraftingStation(BREWERY, ExtraTreesBlocks.MACHINES.get(ExtraTreeMachineType.BREWERY).block());
		registration.addCraftingStation(DISTILLERY, ExtraTreesBlocks.MACHINES.get(ExtraTreeMachineType.DISTILLERY).block());
	}

	private static boolean extraTreesEnabled() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("extra_trees"));
	}

	private static ItemStack grainStackFor(net.minecraft.tags.TagKey<net.minecraft.world.item.Item> tag) {
		if (tag == ExtraTreesTags.Items.GRAIN_BARLEY) {
			return new ItemStack(ExtraTreesItems.GRAIN_BARLEY.item());
		}
		if (tag == ExtraTreesTags.Items.GRAIN_WHEAT) {
			return new ItemStack(ExtraTreesItems.GRAIN_WHEAT.item());
		}
		if (tag == ExtraTreesTags.Items.GRAIN_RYE) {
			return new ItemStack(ExtraTreesItems.GRAIN_RYE.item());
		}
		if (tag == ExtraTreesTags.Items.GRAIN_CORN) {
			return new ItemStack(ExtraTreesItems.GRAIN_CORN.item());
		}
		if (tag == ExtraTreesTags.Items.GRAIN_ROASTED) {
			return new ItemStack(ExtraTreesItems.GRAIN_ROASTED.item());
		}
		return ItemStack.EMPTY;
	}

	private static ItemStack ingredientStackFor(net.minecraft.tags.TagKey<net.minecraft.world.item.Item> tag) {
		if (tag == ExtraTreesTags.Items.HOPS && ExtraTreesBlocks.HOPS.item() != null) {
			return new ItemStack(ExtraTreesBlocks.HOPS.item());
		}
		return ItemStack.EMPTY;
	}

	private static final class FruitPressCategory<F> extends ForestryRecipeCategory<FruitPressRecipeManager.FruitPressRecipe> {
		private final IDrawable icon;
		private final IPlatformFluidHelper<F> fluidHelper;
		private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

		private FruitPressCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
			super(guiHelper.createBlankDrawable(100, 60), "block.reforestry.press");
			this.fluidHelper = fluidHelper;
			this.fluidType = fluidHelper.getFluidIngredientType();
			this.icon = guiHelper.createDrawableIngredient(
					mezz.jei.api.constants.VanillaTypes.ITEM_STACK,
					new ItemStack(ExtraTreesBlocks.MACHINES.get(ExtraTreeMachineType.PRESS).block()));
		}

		@Override
		public IRecipeType<FruitPressRecipeManager.FruitPressRecipe> getRecipeType() {
			return FRUIT_PRESS;
		}

		@Override
		public IDrawable getIcon() {
			return icon;
		}

		@Override
		public void setRecipe(IRecipeLayoutBuilder builder, FruitPressRecipeManager.FruitPressRecipe recipe, IFocusGroup focuses) {
			builder.addInputSlot(1, 22).add(recipe.inputStack());
			builder.addSlot(RecipeIngredientRole.OUTPUT, 55, 1)
					.setFluidRenderer(TilePress.TANK_CAPACITY, false, 16, 58)
					.add(fluidType, fluidHelper.create(recipe.output().defaultFluidState().typeHolder(), recipe.amountDroplets()));
		}
	}

	private static final class BreweryCategory<F> extends ForestryRecipeCategory<BreweryRecipeManager.BreweryRecipe> {
		private final IDrawable icon;
		private final IPlatformFluidHelper<F> fluidHelper;
		private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

		private BreweryCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
			super(guiHelper.createBlankDrawable(120, 60), "block.reforestry.brewery");
			this.fluidHelper = fluidHelper;
			this.fluidType = fluidHelper.getFluidIngredientType();
			this.icon = guiHelper.createDrawableIngredient(
					mezz.jei.api.constants.VanillaTypes.ITEM_STACK,
					new ItemStack(ExtraTreesBlocks.MACHINES.get(ExtraTreeMachineType.BREWERY).block()));
		}

		@Override
		public IRecipeType<BreweryRecipeManager.BreweryRecipe> getRecipeType() {
			return BREWERY;
		}

		@Override
		public IDrawable getIcon() {
			return icon;
		}

		@Override
		public void setRecipe(IRecipeLayoutBuilder builder, BreweryRecipeManager.BreweryRecipe recipe, IFocusGroup focuses) {
			if (recipe.inputFluid() != null) {
				builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
						.setFluidRenderer(TileBrewery.TANK_CAPACITY, false, 16, 58)
						.add(fluidType, fluidHelper.create(
								recipe.inputFluid().defaultFluidState().typeHolder(),
								BreweryRecipeManager.BUCKET_DROPLETS));
			}
			builder.addInputSlot(24, 22).add(recipe.requiredYeast());
			if (recipe.grainTag() != null) {
				ItemStack grain = ExtraTreesJeiPlugin.grainStackFor(recipe.grainTag());
				if (!grain.isEmpty()) {
					builder.addInputSlot(42, 4).add(grain);
					builder.addInputSlot(42, 22).add(grain.copy());
				}
			}
			if (recipe.ingredientTag() != null) {
				ItemStack ingredient = ExtraTreesJeiPlugin.ingredientStackFor(recipe.ingredientTag());
				if (!ingredient.isEmpty()) {
					builder.addInputSlot(60, 22).add(ingredient);
				}
			}
			builder.addSlot(RecipeIngredientRole.OUTPUT, 90, 1)
					.setFluidRenderer(TileBrewery.TANK_CAPACITY, false, 16, 58)
					.add(fluidType, fluidHelper.create(
							recipe.outputFluid().defaultFluidState().typeHolder(),
							BreweryRecipeManager.BUCKET_DROPLETS));
		}
	}

	private static final class DistilleryCategory<F> extends ForestryRecipeCategory<DistilleryRecipeManager.DistilleryRecipe> {
		private final IDrawable icon;
		private final IPlatformFluidHelper<F> fluidHelper;
		private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

		private DistilleryCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
			super(guiHelper.createBlankDrawable(120, 60), "block.reforestry.distillery");
			this.fluidHelper = fluidHelper;
			this.fluidType = fluidHelper.getFluidIngredientType();
			this.icon = guiHelper.createDrawableIngredient(
					mezz.jei.api.constants.VanillaTypes.ITEM_STACK,
					new ItemStack(ExtraTreesBlocks.MACHINES.get(ExtraTreeMachineType.DISTILLERY).block()));
		}

		@Override
		public IRecipeType<DistilleryRecipeManager.DistilleryRecipe> getRecipeType() {
			return DISTILLERY;
		}

		@Override
		public IDrawable getIcon() {
			return icon;
		}

		@Override
		public void setRecipe(IRecipeLayoutBuilder builder, DistilleryRecipeManager.DistilleryRecipe recipe, IFocusGroup focuses) {
			builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
					.setFluidRenderer(TileDistillery.TANK_CAPACITY, false, 16, 58)
					.add(fluidType, fluidHelper.create(
							recipe.input().defaultFluidState().typeHolder(),
							recipe.inputAmount()));
			builder.addSlot(RecipeIngredientRole.OUTPUT, 90, 1)
					.setFluidRenderer(TileDistillery.TANK_CAPACITY, false, 16, 58)
					.add(fluidType, fluidHelper.create(
							recipe.output().defaultFluidState().typeHolder(),
							recipe.outputAmount()));
		}
	}
}
