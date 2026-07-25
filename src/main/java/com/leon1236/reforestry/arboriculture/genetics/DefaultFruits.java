package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.items.EnumFruit;

public final class DefaultFruits {
    private static final Identifier POMES = ReForestry.id("block/leaves/fruits.pomes");
    private static final Identifier NUTS = ReForestry.id("block/leaves/fruits.nuts");
    private static final Identifier BERRIES = ReForestry.id("block/leaves/fruits.berries");
    private static final Identifier CITRUS = ReForestry.id("block/leaves/fruits.citrus");
    private static final Identifier PLUMS = ReForestry.id("block/leaves/fruits.plums");

    public static final IFruit NONE = new DummyFruit(ReForestry.id("fruit_none"), false);
    public static final IFruit APPLE = new RipeningFruit(ReForestry.id("fruit_apple"), false, 10, POMES,
            0xFF1C2B, 0xe3f49c, List.of(new IFruit.Product(Items.APPLE, 1.0f)));
    public static final IFruit COCOA = new PodFruit(ReForestry.id("fruit_cocoa"), false, "cocoa",
            List.of(new IFruit.Product(Items.COCOA_BEANS, 1.0f)));
    public static final IFruit CHESTNUT = new RipeningFruit(ReForestry.id("fruit_chestnut"), true, 6, NUTS,
            0x76403C, 0xc4d24a, List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.CHESTNUT).item(), 1.0f)));
    public static final IFruit WALNUT = new RipeningFruit(ReForestry.id("fruit_walnut"), true, 8, NUTS,
            0xBC784E, 0xc4d24a, List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.WALNUT).item(), 1.0f)));
    public static final IFruit CHERRY = new RipeningFruit(ReForestry.id("fruit_cherry"), true, 10, BERRIES,
            0xCC1C10, 0xc4d24a, List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.CHERRY).item(), 1.0f)));
    public static final IFruit DATES = new PodFruit(ReForestry.id("fruit_dates"), false, "dates",
            List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.DATES).item(), 1.0f)));
    public static final IFruit PAPAYA = new PodFruit(ReForestry.id("fruit_papaya"), false, "papaya",
            List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.PAPAYA).item(), 1.0f)));
    public static final IFruit LEMON = new RipeningFruit(ReForestry.id("fruit_lemon"), true, 10, CITRUS,
            0xFFD500, 0x99ff00, List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.LEMON).item(), 1.0f)));
    public static final IFruit PLUM = new RipeningFruit(ReForestry.id("fruit_plum"), true, 10, PLUMS,
            0x773352, 0xeeff1a, List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.PLUM).item(), 1.0f)));
    public static final IFruit COCONUT = new PodFruit(ReForestry.id("fruit_coconut"), false, "coconut",
            List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.COCONUT).item(), 1.0f)));
    public static final IFruit PEAR = new RipeningFruit(ReForestry.id("fruit_pear"), true, 10, POMES,
            0xD8D345, 0xE3DD9C, List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.PEAR).item(), 1.0f)));
    public static final IFruit FEIJOA = new RipeningFruit(ReForestry.id("fruit_feijoa"), true, 10, BERRIES,
            0x7AB15C, 0x6A7D7B, List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.FEIJOA).item(), 1.0f)));
    public static final IFruit ORANGE = new RipeningFruit(ReForestry.id("fruit_orange"), true, 10, CITRUS,
            0xF4842D, 0xBCA627, List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.ORANGE).item(), 1.0f)));
    public static final IFruit OLIVE = new RipeningFruit(ReForestry.id("fruit_olive"), true, 10, BERRIES,
            0xAAC348, 0x604632, List.of(new IFruit.Product(CoreItems.FRUITS.get(EnumFruit.OLIVE).item(), 1.0f)));

    public static final List<IFruit> ALL = List.of(NONE, APPLE, COCOA, CHESTNUT, WALNUT, CHERRY, DATES, PAPAYA,
            LEMON, PLUM, COCONUT, PEAR, FEIJOA, ORANGE, OLIVE);

    private DefaultFruits() {
    }
}
