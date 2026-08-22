package com.leon1236.reforestry.extratrees.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.extratrees.features.ExtraTreesItems;
import com.leon1236.reforestry.extratrees.fluids.ExtraTreesFluids;
import com.leon1236.reforestry.extratrees.items.EnumExtraTreesFood;
import com.leon1236.reforestry.extratrees.tags.ExtraTreesTags;

public final class ExtraTreesAlcoholRecipes {
	private ExtraTreesAlcoholRecipes() {
	}

	public static void init() {
		registerPress();
		registerBrewery();
		registerDistillery();
	}

	private static void registerPress() {
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CRABAPPLE)), ExtraTreesFluids.APPLE.getFluid(), 150);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.ORANGE)), ExtraTreesFluids.ORANGE.getFluid(), 400);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.LIME)), ExtraTreesFluids.LIME.getFluid(), 300);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.WILD_CHERRY)), ExtraTreesFluids.CHERRY.getFluid(), 100);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.SOUR_CHERRY)), ExtraTreesFluids.CHERRY.getFluid(), 100);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BLACK_CHERRY)), ExtraTreesFluids.CHERRY.getFluid(), 100);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CHERRY_PLUM)), ExtraTreesFluids.PLUM.getFluid(), 100);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.APRICOT)), ExtraTreesFluids.APRICOT.getFluid(), 150);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.GRAPEFRUIT)), ExtraTreesFluids.GRAPEFRUIT.getFluid(), 500);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.PEACH)), ExtraTreesFluids.PEACH.getFluid(), 150);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.SATSUMA)), ExtraTreesFluids.ORANGE.getFluid(), 300);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.FINGER_LIME)), ExtraTreesFluids.LIME.getFluid(), 300);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.KEY_LIME)), ExtraTreesFluids.LIME.getFluid(), 300);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.MANDERIN)), ExtraTreesFluids.ORANGE.getFluid(), 400);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.NECTARINE)), ExtraTreesFluids.PEACH.getFluid(), 150);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.TANGERINE)), ExtraTreesFluids.ORANGE.getFluid(), 300);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.PEAR)), ExtraTreesFluids.PEAR.getFluid(), 300);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BANANA)), ExtraTreesFluids.BANANA.getFluid(), 100);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.RED_BANANA)), ExtraTreesFluids.BANANA.getFluid(), 100);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.ELDERBERRY)), ExtraTreesFluids.ELDERBERRY.getFluid(), 100);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.OLIVE)), ExtraTreesFluids.OLIVE.getFluid(), 100);
		FruitPressRecipeManager.addRecipe(new ItemStack(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CRANBERRY)), ExtraTreesFluids.CRANBERRY.getFluid(), 50);
		FruitPressRecipeManager.addRecipe(new ItemStack(Items.APPLE), ExtraTreesFluids.APPLE.getFluid(), 200);
		FruitPressRecipeManager.addRecipe(new ItemStack(Items.CARROT), ExtraTreesFluids.CARROT.getFluid(), 200);
	}

	private static void registerBrewery() {
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.APPLE.getFluid(), ExtraTreesFluids.ALCOHOL_APPLE.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.APRICOT.getFluid(), ExtraTreesFluids.ALCOHOL_APRICOT.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.BANANA.getFluid(), ExtraTreesFluids.ALCOHOL_BANANA.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.CHERRY.getFluid(), ExtraTreesFluids.ALCOHOL_CHERRY.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.ELDERBERRY.getFluid(), ExtraTreesFluids.ALCOHOL_ELDERBERRY.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.PEACH.getFluid(), ExtraTreesFluids.ALCOHOL_PEACH.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.PEAR.getFluid(), ExtraTreesFluids.ALCOHOL_PEAR.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.PLUM.getFluid(), ExtraTreesFluids.ALCOHOL_PLUM.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.CARROT.getFluid(), ExtraTreesFluids.ALCOHOL_CARROT.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.WHITE_GRAPE.getFluid(), ExtraTreesFluids.WHITE_WINE.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.RED_GRAPE.getFluid(), ExtraTreesFluids.RED_WINE.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.LEMON.getFluid(), ExtraTreesFluids.CITRUS.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.LIME.getFluid(), ExtraTreesFluids.CITRUS.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.ORANGE.getFluid(), ExtraTreesFluids.CITRUS.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.GRAPEFRUIT.getFluid(), ExtraTreesFluids.CITRUS.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.CRANBERRY.getFluid(), ExtraTreesFluids.ALCOHOL_CRANBERRY.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.PINEAPPLE.getFluid(), ExtraTreesFluids.ALCOHOL_PINEAPPLE.getFluid());
		BreweryRecipeManager.addJuiceRecipe(ExtraTreesFluids.TOMATO.getFluid(), ExtraTreesFluids.ALCOHOL_TOMATO.getFluid());
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_BARLEY, ExtraTreesFluids.ALE.getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_BARLEY, ExtraTreesFluids.LAGER.getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.LAGER_YEAST.item()));
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_ROASTED, ExtraTreesFluids.STOUT.getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_CORN, ExtraTreesFluids.CORN_BEER.getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_RYE, ExtraTreesFluids.RYE_BEER.getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_WHEAT, ExtraTreesFluids.WHEAT_BEER.getFluid(), ExtraTreesTags.Items.HOPS, new ItemStack(ExtraTreesItems.YEAST.item()));
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_BARLEY, ExtraTreesFluids.BARLEY.getFluid(), null, new ItemStack(ExtraTreesItems.YEAST.item()));
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_CORN, ExtraTreesFluids.CORN.getFluid(), null, new ItemStack(ExtraTreesItems.YEAST.item()));
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_RYE, ExtraTreesFluids.RYE.getFluid(), null, new ItemStack(ExtraTreesItems.YEAST.item()));
		BreweryRecipeManager.addGrainRecipe(ExtraTreesTags.Items.GRAIN_WHEAT, ExtraTreesFluids.WHEAT.getFluid(), null, new ItemStack(ExtraTreesItems.YEAST.item()));
	}

	private static void registerDistillery() {
		addDistillery(ExtraTreesFluids.ALCOHOL_APPLE, ExtraTreesFluids.APPLE_BRANDY, ExtraTreesFluids.APPLE_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_PEAR, ExtraTreesFluids.PEAR_BRANDY, ExtraTreesFluids.PEAR_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_APRICOT, ExtraTreesFluids.APRICOT_BRANDY, ExtraTreesFluids.APRICOT_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_BANANA, ExtraTreesFluids.FRUIT_BRANDY, ExtraTreesFluids.FRUIT_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_CHERRY, ExtraTreesFluids.CHERRY_BRANDY, ExtraTreesFluids.CHERRY_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_ELDERBERRY, ExtraTreesFluids.ELDERBERRY_BRANDY, ExtraTreesFluids.ELDERBERRY_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_PEACH, ExtraTreesFluids.FRUIT_BRANDY, ExtraTreesFluids.FRUIT_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_PLUM, ExtraTreesFluids.PLUM_BRANDY, ExtraTreesFluids.FRUIT_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_CARROT, ExtraTreesFluids.FRUIT_BRANDY, ExtraTreesFluids.VODKA, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.WHITE_WINE, ExtraTreesFluids.BRANDY, ExtraTreesFluids.BRANDY, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.RED_WINE, ExtraTreesFluids.BRANDY, ExtraTreesFluids.BRANDY, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.SPARKLING_WINE, ExtraTreesFluids.BRANDY, ExtraTreesFluids.BRANDY, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.AGAVE, ExtraTreesFluids.TEQUILA, ExtraTreesFluids.TEQUILA, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.POTATO, ExtraTreesFluids.FRUIT_BRANDY, ExtraTreesFluids.VODKA, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.CITRUS, ExtraTreesFluids.CITRUS_BRANDY, ExtraTreesFluids.FRUIT_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_CRANBERRY, ExtraTreesFluids.FRUIT_BRANDY, ExtraTreesFluids.FRUIT_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_PINEAPPLE, ExtraTreesFluids.FRUIT_BRANDY, ExtraTreesFluids.FRUIT_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.ALCOHOL_TOMATO, ExtraTreesFluids.FRUIT_BRANDY, ExtraTreesFluids.FRUIT_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.FRUIT, ExtraTreesFluids.FRUIT_BRANDY, ExtraTreesFluids.FRUIT_LIQUOR, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.BARLEY, ExtraTreesFluids.WHISKEY, ExtraTreesFluids.VODKA, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.WHEAT, ExtraTreesFluids.WHEAT_WHISKEY, ExtraTreesFluids.VODKA, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.RYE, ExtraTreesFluids.RYE_WHISKEY, ExtraTreesFluids.VODKA, ExtraTreesFluids.NEUTRAL_SPIRIT);
		addDistillery(ExtraTreesFluids.CORN, ExtraTreesFluids.CORN_WHISKEY, ExtraTreesFluids.VODKA, ExtraTreesFluids.NEUTRAL_SPIRIT);
	}

	private static void addDistillery(ExtraTreesFluids source, ExtraTreesFluids single,
			ExtraTreesFluids doubleDistilled, ExtraTreesFluids triple) {
		long inAmount = FluidUnits.mbToDroplets(1000);
		long out1 = FluidUnits.mbToDroplets(800);
		long out2 = FluidUnits.mbToDroplets(400);
		long out3 = FluidUnits.mbToDroplets(200);
		Fluid in = source.getFluid();
		DistilleryRecipeManager.addRecipe(in, inAmount, single.getFluid(), out1, 0);
		DistilleryRecipeManager.addRecipe(in, inAmount, doubleDistilled.getFluid(), out2, 1);
		DistilleryRecipeManager.addRecipe(in, inAmount, triple.getFluid(), out3, 2);
		DistilleryRecipeManager.addRecipe(single.getFluid(), inAmount, doubleDistilled.getFluid(), out2, 0);
		DistilleryRecipeManager.addRecipe(single.getFluid(), inAmount, triple.getFluid(), out3, 1);
		DistilleryRecipeManager.addRecipe(doubleDistilled.getFluid(), inAmount, triple.getFluid(), out2, 0);
	}
}
