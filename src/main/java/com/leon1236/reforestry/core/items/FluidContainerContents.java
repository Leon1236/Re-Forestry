package com.leon1236.reforestry.core.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.core.features.FluidsItems;

public record FluidContainerContents(FluidVariant variant, long amount) {
    public static final FluidContainerContents EMPTY = new FluidContainerContents(FluidVariant.blank(), 0);

    public static final Codec<FluidContainerContents> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            FluidVariant.CODEC.fieldOf("variant").forGetter(FluidContainerContents::variant),
            Codec.LONG.fieldOf("amount").forGetter(FluidContainerContents::amount)
    ).apply(instance, FluidContainerContents::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidContainerContents> PACKET_CODEC = StreamCodec.composite(
            FluidVariant.PACKET_CODEC, FluidContainerContents::variant,
            ByteBufCodecs.VAR_LONG, FluidContainerContents::amount,
            FluidContainerContents::new
    );

    public static FluidContainerContents get(ItemStack stack) {
        return stack.getOrDefault(FluidsItems.FLUID_CONTENTS.type(), EMPTY);
    }

    public static void set(ItemStack stack, FluidVariant variant, long amount) {
        if (amount > 0 && !variant.isBlank()) {
            stack.set(FluidsItems.FLUID_CONTENTS.type(), new FluidContainerContents(variant, amount));
        } else {
            stack.set(FluidsItems.FLUID_CONTENTS.type(), EMPTY);
        }
    }
}
