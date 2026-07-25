package com.leon1236.reforestry.api.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record Product(Item item, int count, DataComponentPatch components, float chance) implements IProduct {
    public static final Codec<Product> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(Product::item),
            Codec.intRange(1, 64).optionalFieldOf("count", 1).forGetter(Product::count),
            DataComponentPatch.CODEC.optionalFieldOf("components", DataComponentPatch.EMPTY).forGetter(Product::components),
            Codec.floatRange(0f, 1f).fieldOf("chance").forGetter(Product::chance)
    ).apply(instance, Product::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Product> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.registry(Registries.ITEM), Product::item,
            ByteBufCodecs.VAR_INT, Product::count,
            DataComponentPatch.STREAM_CODEC, Product::components,
            ByteBufCodecs.FLOAT, Product::chance,
            Product::new
    );

    @Override
    public ItemStack createStack() {
        return new ItemStack(BuiltInRegistries.ITEM.wrapAsHolder(item), count, components);
    }

    public static Product of(Item item) {
        return new Product(item, 1, DataComponentPatch.EMPTY, 1f);
    }

    public static Product of(Item item, int count, float chance) {
        return new Product(item, count, DataComponentPatch.EMPTY, chance);
    }
}
