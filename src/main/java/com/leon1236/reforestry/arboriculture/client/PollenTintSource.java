package com.leon1236.reforestry.arboriculture.client;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public record PollenTintSource() implements ItemTintSource {
    public static final PollenTintSource INSTANCE = new PollenTintSource();
    public static final MapCodec<PollenTintSource> MAP_CODEC = MapCodec.unit(INSTANCE);

    @Override
    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        IGenome genome = stack.get(ArboricultureDataComponents.TREE_GENOME.type());
        if (genome == null) {
            return ARGB.opaque(0xffffff);
        }
        return ARGB.opaque(genome.getActiveAllele(TreeChromosomes.SPECIES).value().escritoireColor());
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}
