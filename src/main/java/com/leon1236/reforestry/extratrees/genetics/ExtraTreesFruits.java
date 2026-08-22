package com.leon1236.reforestry.extratrees.genetics;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.api.plugin.IArboricultureRegistration;
import com.leon1236.reforestry.arboriculture.genetics.RipeningFruit;
import com.leon1236.reforestry.extratrees.blocks.ExtraTreesPodType;
import com.leon1236.reforestry.extratrees.features.ExtraTreesItems;
import com.leon1236.reforestry.extratrees.items.EnumExtraTreesFood;

public final class ExtraTreesFruits {
	private static final Identifier FRUIT_TINY = ReForestry.id("block/fruit/tiny");
	private static final Identifier FRUIT_SMALL = ReForestry.id("block/fruit/small");
	private static final Identifier FRUIT_AVERAGE = ReForestry.id("block/fruit/average");
	private static final Identifier FRUIT_LARGE = ReForestry.id("block/fruit/large");
	private static final Identifier FRUIT_LARGER = ReForestry.id("block/fruit/larger");
	private static final Identifier FRUIT_PEAR = ReForestry.id("block/fruit/pear");

	public static final IFruit BLACKTHORN = new RipeningFruit(ReForestry.id("fruit_blackthorn"), true, 10, FRUIT_SMALL,
			0xde2f69, 0x6d8f1e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BLACKTHORN), 1.0f)));
	public static final IFruit CHERRY_PLUM = new RipeningFruit(ReForestry.id("fruit_cherry_plum"), true, 10, FRUIT_SMALL,
			0xe81c4b, 0x6d8f1e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CHERRY_PLUM), 1.0f)));
	public static final IFruit PEACH = new RipeningFruit(ReForestry.id("fruit_peach"), true, 10, FRUIT_AVERAGE,
			0xfaa023, 0x6d8f1e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.PEACH), 1.0f)));
	public static final IFruit NECTARINE = new RipeningFruit(ReForestry.id("fruit_nectarine"), true, 10, FRUIT_AVERAGE,
			0xfa5523, 0x6d8f1e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.NECTARINE), 1.0f)));
	public static final IFruit APRICOT = new RipeningFruit(ReForestry.id("fruit_apricot"), true, 10, FRUIT_AVERAGE,
			0xfacf23, 0x6d8f1e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.APRICOT), 1.0f)));
	public static final IFruit ALMOND = new RipeningFruit(ReForestry.id("fruit_almond"), true, 10, FRUIT_SMALL,
			0x8ee376, 0x6d8f1e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.ALMOND), 1.0f)));
	public static final IFruit WILD_CHERRY = new RipeningFruit(ReForestry.id("fruit_wild_cherry"), true, 10, FRUIT_TINY,
			0xff0000, 0x6d8f1e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.WILD_CHERRY), 1.0f)));
	public static final IFruit SOUR_CHERRY = new RipeningFruit(ReForestry.id("fruit_sour_cherry"), true, 10, FRUIT_TINY,
			0x9c092b, 0x6d8f1e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.SOUR_CHERRY), 1.0f)));
	public static final IFruit BLACK_CHERRY = new RipeningFruit(ReForestry.id("fruit_black_cherry"), true, 10, FRUIT_TINY,
			0x4a0a19, 0x6d8f1e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BLACK_CHERRY), 1.0f)));
	public static final IFruit FIG = new RipeningFruit(ReForestry.id("fruit_fig"), true, 9, FRUIT_SMALL,
			0x6c3f46, 0xd8b162, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.FIG), 1.0f)));
	public static final IFruit ELDERBERRY = new RipeningFruit(ReForestry.id("fruit_elderberry"), true, 9, FRUIT_TINY,
			0x515b43, 0x71975d, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.ELDERBERRY), 1.0f)));
	public static final IFruit MANDERIN = new RipeningFruit(ReForestry.id("fruit_manderin"), true, 10, FRUIT_AVERAGE,
			0xff940a, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.MANDERIN), 1.0f)));
	public static final IFruit TANGERINE = new RipeningFruit(ReForestry.id("fruit_tangerine"), true, 10, FRUIT_AVERAGE,
			0xff940a, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.TANGERINE), 1.0f)));
	public static final IFruit SATSUMA = new RipeningFruit(ReForestry.id("fruit_satsuma"), true, 10, FRUIT_AVERAGE,
			0xff940a, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.SATSUMA), 1.0f)));
	public static final IFruit KEY_LIME = new RipeningFruit(ReForestry.id("fruit_key_lime"), true, 10, FRUIT_SMALL,
			0x9bff44, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.KEY_LIME), 1.0f)));
	public static final IFruit LIME = new RipeningFruit(ReForestry.id("fruit_lime"), true, 10, FRUIT_AVERAGE,
			0x9bff44, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.LIME), 1.0f)));
	public static final IFruit FINGER_LIME = new RipeningFruit(ReForestry.id("fruit_finger_lime"), true, 10, FRUIT_SMALL,
			0xaa3b38, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.FINGER_LIME), 1.0f)));
	public static final IFruit POMELO = new RipeningFruit(ReForestry.id("fruit_pomelo"), true, 10, FRUIT_LARGER,
			0x5cd34a, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.POMELO), 1.0f)));
	public static final IFruit GRAPEFRUIT = new RipeningFruit(ReForestry.id("fruit_grapefruit"), true, 10, FRUIT_LARGE,
			0xff940a, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.GRAPEFRUIT), 1.0f)));
	public static final IFruit KUMQUAT = new RipeningFruit(ReForestry.id("fruit_kumquat"), true, 10, FRUIT_SMALL,
			0xff940a, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.KUMQUAT), 1.0f)));
	public static final IFruit CITRON = new RipeningFruit(ReForestry.id("fruit_citron"), true, 10, FRUIT_LARGE,
			0xffec60, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CITRON), 1.0f)));
	public static final IFruit BUDDHA_HAND = new RipeningFruit(ReForestry.id("fruit_buddha_hand"), true, 10, FRUIT_LARGE,
			0xffec60, 0x37f043, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BUDDHA_HAND), 1.0f)));
	public static final IFruit CRABAPPLE = new RipeningFruit(ReForestry.id("fruit_crabapple"), true, 10, FRUIT_AVERAGE,
			0xffbd4c, 0x78c953, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CRABAPPLE), 1.0f)));
	public static final IFruit MANGO = new RipeningFruit(ReForestry.id("fruit_mango"), true, 10, FRUIT_AVERAGE,
			0xf2a636, 0x658c15, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.MANGO), 1.0f)));
	public static final IFruit OSANGE_ORANGE = new RipeningFruit(ReForestry.id("fruit_osange_orange"), true, 10, FRUIT_LARGER,
			0xa2bf27, 0x979752, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.OSANGE_ORANGE), 1.0f)));
	public static final IFruit BANANA = new ExtraTreesPodFruit(ReForestry.id("fruit_banana"), true, ExtraTreesPodType.BANANA,
			List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BANANA), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BANANA), 1.0f)));
	public static final IFruit RED_BANANA = new ExtraTreesPodFruit(ReForestry.id("fruit_red_banana"), true, ExtraTreesPodType.RED_BANANA,
			List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.RED_BANANA), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.RED_BANANA), 1.0f)));
	public static final IFruit PLANTAIN = new ExtraTreesPodFruit(ReForestry.id("fruit_plantain"), true, ExtraTreesPodType.PLANTAIN,
			List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.PLANTAIN), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.PLANTAIN), 1.0f)));
	public static final IFruit COFFEE = new RipeningFruit(ReForestry.id("fruit_coffee"), true, 8, FRUIT_TINY,
			0xf84f66, 0x716d1d, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.COFFEE), 1.0f)));
	public static final IFruit CASHEW = new RipeningFruit(ReForestry.id("fruit_cashew"), true, 8, FRUIT_AVERAGE,
			0xe94b17, 0xc4851c, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CASHEW), 1.0f)));
	public static final IFruit AVOCADO = new RipeningFruit(ReForestry.id("fruit_avocado"), true, 10, FRUIT_PEAR,
			0x211f10, 0x9cbe72, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.AVOCADO), 1.0f)));
	public static final IFruit NUTMEG = new RipeningFruit(ReForestry.id("fruit_nutmeg"), true, 9, FRUIT_TINY,
			0xac8355, 0xe2c32d, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.NUTMEG), 1.0f)));
	public static final IFruit ALLSPICE = new RipeningFruit(ReForestry.id("fruit_allspice"), true, 9, FRUIT_TINY,
			0x714636, 0xe7a47a, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.ALLSPICE), 1.0f)));
	public static final IFruit CHILLI = new RipeningFruit(ReForestry.id("fruit_chilli"), true, 10, FRUIT_SMALL,
			0xe71832, 0x716265, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CHILLI), 1.0f)));
	public static final IFruit STAR_ANISE = new RipeningFruit(ReForestry.id("fruit_star_anise"), true, 8, FRUIT_TINY,
			0xd45c05, 0x85442e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.STAR_ANISE), 1.0f)));
	public static final IFruit STARFRUIT = new RipeningFruit(ReForestry.id("fruit_starfruit"), true, 10, FRUIT_AVERAGE,
			0xe5d22e, 0x95c20d, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.STARFRUIT), 1.0f)));
	public static final IFruit CANDLENUT = new RipeningFruit(ReForestry.id("fruit_candlenut"), true, 8, FRUIT_SMALL,
			0xdecab2, 0x7da873, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CANDLENUT), 1.0f)));
	public static final IFruit HAZELNUT = new RipeningFruit(ReForestry.id("fruit_hazelnut"), true, 7, FRUIT_SMALL,
			0xdcb276, 0x7d791e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.HAZELNUT), 1.0f)));
	public static final IFruit BUTTERNUT = new RipeningFruit(ReForestry.id("fruit_butternut"), true, 7, FRUIT_SMALL,
			0xf5b462, 0xb2b750, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BUTTERNUT), 1.0f)));
	public static final IFruit BEECHNUT = new RipeningFruit(ReForestry.id("fruit_beechnut"), true, 8, FRUIT_TINY,
			0x5f3e35, 0xdbbe7c, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BEECHNUT), 1.0f)));
	public static final IFruit PECAN = new RipeningFruit(ReForestry.id("fruit_pecan"), true, 8, FRUIT_SMALL,
			0xf0cf89, 0xa2ac4c, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.PECAN), 1.0f)));
	public static final IFruit BRAZIL_NUT = new RipeningFruit(ReForestry.id("fruit_brazil_nut"), true, 10, FRUIT_LARGE,
			0x965530, 0x59a769, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BRAZIL_NUT), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BRAZIL_NUT), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BRAZIL_NUT), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BRAZIL_NUT), 1.0f)));
	public static final IFruit ACORN = new RipeningFruit(ReForestry.id("fruit_acorn"), true, 6, FRUIT_TINY,
			0xad6a1d, 0x72b226, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.ACORN), 1.0f)));
	public static final IFruit GINGKO_NUT = new RipeningFruit(ReForestry.id("fruit_gingko_nut"), true, 7, FRUIT_TINY,
			0xe5daad, 0x8c975b, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.GINGKO_NUT), 1.0f)));
	public static final IFruit CLOVE = new RipeningFruit(ReForestry.id("fruit_clove"), true, 9, FRUIT_TINY,
			0xab4445, 0x687c2c, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CLOVE), 1.0f)));
	public static final IFruit BLACKCURRANT = new RipeningFruit(ReForestry.id("fruit_blackcurrant"), true, 8, FRUIT_TINY,
			0x4b4e53, 0x8f8c53, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BLACKCURRANT), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BLACKCURRANT), 1.0f)));
	public static final IFruit REDCURRANT = new RipeningFruit(ReForestry.id("fruit_redcurrant"), true, 8, FRUIT_TINY,
			0xe61e0e, 0xc6800e, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.REDCURRANT), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.REDCURRANT), 1.0f)));
	public static final IFruit BLACKBERRY = new RipeningFruit(ReForestry.id("fruit_blackberry"), true, 8, FRUIT_TINY,
			0x494371, 0x8f6d71, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BLACKBERRY), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BLACKBERRY), 1.0f)));
	public static final IFruit RASPBERRY = new RipeningFruit(ReForestry.id("fruit_raspberry"), true, 8, FRUIT_TINY,
			0xdd6971, 0xecd1c5, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.RASPBERRY), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.RASPBERRY), 1.0f)));
	public static final IFruit BLUEBERRY = new RipeningFruit(ReForestry.id("fruit_blueberry"), true, 8, FRUIT_TINY,
			0x6093be, 0x9bb297, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BLUEBERRY), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.BLUEBERRY), 1.0f)));
	public static final IFruit CRANBERRY = new RipeningFruit(ReForestry.id("fruit_cranberry"), true, 8, FRUIT_TINY,
			0xde1a30, 0xbaa730, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CRANBERRY), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.CRANBERRY), 1.0f)));
	public static final IFruit JUNIPER = new RipeningFruit(ReForestry.id("fruit_juniper"), true, 8, FRUIT_TINY,
			0x606372, 0x9b8c72, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.JUNIPER), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.JUNIPER), 1.0f)));
	public static final IFruit GOOSEBERRY = new RipeningFruit(ReForestry.id("fruit_gooseberry"), true, 8, FRUIT_TINY,
			0xb9cf50, 0xb99f50, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.GOOSEBERRY), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.GOOSEBERRY), 1.0f)));
	public static final IFruit GOLDEN_RASPBERRY = new RipeningFruit(ReForestry.id("fruit_golden_raspberry"), true, 8, FRUIT_TINY,
			0xf3b03b, 0xbeb03b, List.of(new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.GOLDEN_RASPBERRY), 1.0f), new IFruit.Product(ExtraTreesItems.FOODS.item(EnumExtraTreesFood.GOLDEN_RASPBERRY), 1.0f)));

	public static final List<IFruit> ALL = List.of(BLACKTHORN, CHERRY_PLUM, PEACH, NECTARINE, APRICOT, ALMOND, WILD_CHERRY, SOUR_CHERRY, BLACK_CHERRY, FIG, ELDERBERRY, MANDERIN, TANGERINE, SATSUMA, KEY_LIME, LIME, FINGER_LIME, POMELO, GRAPEFRUIT, KUMQUAT, CITRON, BUDDHA_HAND, CRABAPPLE, MANGO, OSANGE_ORANGE, BANANA, RED_BANANA, PLANTAIN, COFFEE, CASHEW, AVOCADO, NUTMEG, ALLSPICE, CHILLI, STAR_ANISE, STARFRUIT, CANDLENUT, HAZELNUT, BUTTERNUT, BEECHNUT, PECAN, BRAZIL_NUT, ACORN, GINGKO_NUT, CLOVE, BLACKCURRANT, REDCURRANT, BLACKBERRY, RASPBERRY, BLUEBERRY, CRANBERRY, JUNIPER, GOOSEBERRY, GOLDEN_RASPBERRY);

	private ExtraTreesFruits() {
	}

	public static void register(IArboricultureRegistration registration) {
		for (IFruit fruit : ALL) {
			registration.registerFruit(fruit.id(), fruit);
		}
	}
}
