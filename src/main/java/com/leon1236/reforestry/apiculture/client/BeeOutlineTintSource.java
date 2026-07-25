package com.leon1236.reforestry.apiculture.client;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;

public record BeeOutlineTintSource() implements ItemTintSource {
    public static final BeeOutlineTintSource INSTANCE = new BeeOutlineTintSource();
    public static final MapCodec<BeeOutlineTintSource> MAP_CODEC = MapCodec.unit(INSTANCE);

    @Override
    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        IGenome genome = stack.get(ApicultureDataComponents.BEE_GENOME.type());
        if (genome == null) {
            return ARGB.opaque(0xffffff);
        }
        return ARGB.opaque(genome.getActiveAllele(BeeChromosomes.SPECIES).value().outlineColor());
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}
