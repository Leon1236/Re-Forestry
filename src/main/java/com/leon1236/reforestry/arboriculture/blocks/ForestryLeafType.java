package com.leon1236.reforestry.arboriculture.blocks;

import java.util.Collections;
import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.IBlockSubtype;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public final class ForestryLeafType implements IBlockSubtype {
    private static final ObjectOpenHashSet<ForestryLeafType> VALUES_SET = new ObjectOpenHashSet<>(50);

    public static final ForestryLeafType OAK = new ForestryLeafType("tree_oak");
    public static final ForestryLeafType DARK_OAK = new ForestryLeafType("tree_dark_oak");
    public static final ForestryLeafType BIRCH = new ForestryLeafType("tree_birch");
    public static final ForestryLeafType LIME = new ForestryLeafType("tree_lime");
    public static final ForestryLeafType WALNUT = new ForestryLeafType("tree_walnut");
    public static final ForestryLeafType CHESTNUT = new ForestryLeafType("tree_chestnut");
    public static final ForestryLeafType CHERRY_VANILLA = new ForestryLeafType("tree_cherry");
    public static final ForestryLeafType SOUR_CHERRY = new ForestryLeafType("tree_hill_cherry");
    public static final ForestryLeafType LEMON = new ForestryLeafType("tree_lemon");
    public static final ForestryLeafType PLUM = new ForestryLeafType("tree_plum");
    public static final ForestryLeafType MAPLE = new ForestryLeafType("tree_maple");
    public static final ForestryLeafType SPRUCE = new ForestryLeafType("tree_spruce");
    public static final ForestryLeafType LARCH = new ForestryLeafType("tree_larch");
    public static final ForestryLeafType PINE = new ForestryLeafType("tree_pine");
    public static final ForestryLeafType SEQUOIA = new ForestryLeafType("tree_sequoia");
    public static final ForestryLeafType GIANT_SEQUOIA = new ForestryLeafType("tree_giant_sequoia");
    public static final ForestryLeafType JUNGLE = new ForestryLeafType("tree_jungle");
    public static final ForestryLeafType TEAK = new ForestryLeafType("tree_teak");
    public static final ForestryLeafType IPE = new ForestryLeafType("tree_ipe");
    public static final ForestryLeafType KAPOK = new ForestryLeafType("tree_kapok");
    public static final ForestryLeafType EBONY = new ForestryLeafType("tree_ebony");
    public static final ForestryLeafType ZEBRANO = new ForestryLeafType("tree_zebrawood");
    public static final ForestryLeafType MAHOGANY = new ForestryLeafType("tree_mahogany");
    public static final ForestryLeafType ACACIA_VANILLA = new ForestryLeafType("tree_acacia");
    public static final ForestryLeafType CAMELTHORN = new ForestryLeafType("tree_desert_acacia");
    public static final ForestryLeafType PADAUK = new ForestryLeafType("tree_padauk");
    public static final ForestryLeafType BALSA = new ForestryLeafType("tree_balsa");
    public static final ForestryLeafType COCOBOLO = new ForestryLeafType("tree_cocobolo");
    public static final ForestryLeafType WENGE = new ForestryLeafType("tree_wenge");
    public static final ForestryLeafType BAOBAB = new ForestryLeafType("tree_baobab");
    public static final ForestryLeafType MAHOE = new ForestryLeafType("tree_mahoe");
    public static final ForestryLeafType WILLOW = new ForestryLeafType("tree_willow");
    public static final ForestryLeafType GREENHEART = new ForestryLeafType("tree_sipiri");
    public static final ForestryLeafType PAPAYA = new ForestryLeafType("tree_papaya");
    public static final ForestryLeafType DATE = new ForestryLeafType("tree_date");
    public static final ForestryLeafType POPLAR = new ForestryLeafType("tree_poplar");
    public static final ForestryLeafType ELM = new ForestryLeafType("tree_elm");
    public static final ForestryLeafType FIR = new ForestryLeafType("tree_fir");
    public static final ForestryLeafType COCONUT = new ForestryLeafType("tree_coconut");
    public static final ForestryLeafType BEECH = new ForestryLeafType("tree_beech");
    public static final ForestryLeafType FEIJOA = new ForestryLeafType("tree_feijoa");
    public static final ForestryLeafType DOGWOOD = new ForestryLeafType("tree_dogwood");
    public static final ForestryLeafType GINKGO = new ForestryLeafType("tree_ginkgo");
    public static final ForestryLeafType JACARANDA = new ForestryLeafType("tree_jacaranda");
    public static final ForestryLeafType PEWEN = new ForestryLeafType("tree_pewen");
    public static final ForestryLeafType MACROCARPA = new ForestryLeafType("tree_macrocarpa");
    public static final ForestryLeafType OLIVE = new ForestryLeafType("tree_olive");
    public static final ForestryLeafType ORANGE = new ForestryLeafType("tree_orange");
    public static final ForestryLeafType PEAR = new ForestryLeafType("tree_pear");
    public static final ForestryLeafType KAURI = new ForestryLeafType("tree_kauri");

    public static final ForestryLeafType[] VALUES = {
            OAK, DARK_OAK, BIRCH, LIME, WALNUT, CHESTNUT, CHERRY_VANILLA, SOUR_CHERRY,
            LEMON, PLUM, MAPLE, SPRUCE, LARCH, PINE, SEQUOIA, GIANT_SEQUOIA, JUNGLE, TEAK, IPE, KAPOK,
            EBONY, ZEBRANO, MAHOGANY, ACACIA_VANILLA, CAMELTHORN, PADAUK, BALSA, COCOBOLO, WENGE,
            BAOBAB, MAHOE, WILLOW, GREENHEART, PAPAYA, DATE, POPLAR, ELM, FIR, COCONUT, BEECH, FEIJOA,
            DOGWOOD, GINKGO, JACARANDA, PEWEN, MACROCARPA, OLIVE, ORANGE, PEAR, KAURI
    };

    private final Identifier speciesId;
    private IFruit fruit;
    private IGenome genome;
    private ITreeSpecies species;

    public ForestryLeafType(String speciesPath) {
        this.speciesId = ReForestry.id(speciesPath);
        VALUES_SET.add(this);
    }

    public void setSpecies(ITreeSpecies species) {
        this.species = species;
        this.genome = ArboricultureGenetics.getDefaultGenome(species.id());
        this.fruit = this.genome.getActiveAllele(TreeChromosomes.FRUIT).value();
    }

    @Override
    public String getSerializedName() {
        return this.speciesId.getPath();
    }

    public IFruit getFruit() {
        return this.fruit;
    }

    public IGenome getGenome() {
        return this.genome;
    }

    public ITreeSpecies getSpecies() {
        return this.species;
    }

    public Identifier getSpeciesId() {
        return this.speciesId;
    }

    public static Set<ForestryLeafType> allValues() {
        return Collections.unmodifiableSet(VALUES_SET);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ForestryLeafType that = (ForestryLeafType) o;
        return this.speciesId.equals(that.speciesId);
    }

    @Override
    public int hashCode() {
        return this.speciesId.hashCode();
    }
}
