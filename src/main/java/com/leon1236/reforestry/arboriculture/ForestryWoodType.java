package com.leon1236.reforestry.arboriculture;

import java.util.Locale;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;

public enum ForestryWoodType implements IWoodType {
    LARCH(ForestryLeafType.LARCH),
    TEAK(ForestryLeafType.TEAK),
    ACACIA_DESERT(ForestryLeafType.CAMELTHORN),
    LIME(ForestryLeafType.LIME),
    CHESTNUT(ForestryLeafType.CHESTNUT),
    WENGE(ForestryLeafType.WENGE),
    BAOBAB(ForestryLeafType.BAOBAB),
    SEQUOIA(ForestryLeafType.SEQUOIA, 4.0f),

    KAPOK(ForestryLeafType.KAPOK),
    EBONY(ForestryLeafType.EBONY),
    ELM(ForestryLeafType.ELM),
    MAHOGANY(ForestryLeafType.MAHOGANY),
    BALSA(ForestryLeafType.BALSA, 1.0f),
    WILLOW(ForestryLeafType.WILLOW),
    WALNUT(ForestryLeafType.WALNUT),
    GREENHEART(ForestryLeafType.GREENHEART, 7.5f),
    HILL_CHERRY(ForestryLeafType.SOUR_CHERRY),

    MAHOE(ForestryLeafType.MAHOE),
    POPLAR(ForestryLeafType.POPLAR),
    PALM(ForestryLeafType.DATE),
    PAPAYA(ForestryLeafType.PAPAYA),
    PINE(ForestryLeafType.PINE, 3.0f),
    PLUM(ForestryLeafType.PLUM),
    MAPLE(ForestryLeafType.MAPLE),
    CITRUS(ForestryLeafType.LEMON),

    GIGANTEUM(ForestryLeafType.GIANT_SEQUOIA, 4.0f),
    IPE(ForestryLeafType.IPE),
    PADAUK(ForestryLeafType.PADAUK),
    COCOBOLO(ForestryLeafType.COCOBOLO),
    FIR(ForestryLeafType.FIR),
    COCONUT(ForestryLeafType.COCONUT),
    BEECH(ForestryLeafType.BEECH),
    FEIJOA(ForestryLeafType.FEIJOA),
    DOGWOOD(ForestryLeafType.DOGWOOD),
    GINKGO(ForestryLeafType.GINKGO),
    JACARANDA(ForestryLeafType.JACARANDA),
    PEWEN(ForestryLeafType.PEWEN),
    MACROCARPA(ForestryLeafType.MACROCARPA),
    OLIVE(ForestryLeafType.OLIVE),
    ORANGE(ForestryLeafType.ORANGE),
    PEAR(ForestryLeafType.PEAR),
    KAURI(ForestryLeafType.KAURI),
    ZEBRAWOOD(ForestryLeafType.ZEBRANO);

    public static final float DEFAULT_HARDNESS = 2.0f;
    public static final ForestryWoodType[] VALUES = values();

    public final String serializedName;
    private final float hardness;
    private final ForestryLeafType leafType;
    private final BlockSetType blockSetType;
    private final WoodType woodType;

    ForestryWoodType(ForestryLeafType leafType) {
        this(leafType, DEFAULT_HARDNESS);
    }

    ForestryWoodType(ForestryLeafType leafType, float hardness) {
        this.serializedName = name().toLowerCase(Locale.ENGLISH);
        this.leafType = leafType;
        this.hardness = hardness;
        this.blockSetType = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(ReForestry.id(this.serializedName));
        this.woodType = WoodTypeBuilder.copyOf(WoodType.OAK).register(ReForestry.id(this.serializedName), this.blockSetType);
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    @Override
    public float getHardness() {
        return hardness;
    }

    @Override
    public WoodType getVanillaWoodType() {
        return woodType;
    }

    @Override
    public BlockSetType getBlockSetType() {
        return blockSetType;
    }

    @Override
    public boolean setDefaultLeaves(LevelAccessor level, BlockPos pos, IGenome genome, RandomSource rand, @Nullable GameProfile owner) {
        return setDefaultLeavesImpl(level, pos, genome, rand, this.leafType);
    }

    static boolean setDefaultLeavesImpl(LevelAccessor level, BlockPos pos, IGenome genome, RandomSource rand, ForestryLeafType leafType) {
        IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
        FeatureBlockGroup<? extends Block, ForestryLeafType> leavesGroup;
        if (fruit.isFruitLeaf() && rand.nextFloat() <= fruit.getFruitChance(genome)) {
            leavesGroup = ArboricultureBlocks.LEAVES_DEFAULT_FRUIT;
        } else {
            leavesGroup = ArboricultureBlocks.LEAVES_DEFAULT;
        }
        BlockState defaultLeaves = leavesGroup.get(leafType).block().defaultBlockState();
        return level.setBlock(pos, defaultLeaves, 19);
    }

    @Override
    public String toString() {
        return serializedName;
    }
}
