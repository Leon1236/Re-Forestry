package com.leon1236.reforestry.arboriculture.client;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import org.jspecify.annotations.Nullable;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;

public record TreeSpeciesSelectProperty() implements SelectItemModelProperty<Identifier> {
    public static final SelectItemModelProperty.Type<TreeSpeciesSelectProperty, Identifier> TYPE =
            SelectItemModelProperty.Type.create(MapCodec.unit(new TreeSpeciesSelectProperty()), Identifier.CODEC);

    @Override
    public @Nullable Identifier get(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity owner, int seed, ItemDisplayContext displayContext) {
        IGenome genome = itemStack.get(ArboricultureDataComponents.TREE_GENOME.type());
        if (genome == null) {
            return null;
        }
        return genome.getActiveAllele(TreeChromosomes.SPECIES).value().id();
    }

    @Override
    public Codec<Identifier> valueCodec() {
        return Identifier.CODEC;
    }

    @Override
    public SelectItemModelProperty.Type<TreeSpeciesSelectProperty, Identifier> type() {
        return TYPE;
    }
}
