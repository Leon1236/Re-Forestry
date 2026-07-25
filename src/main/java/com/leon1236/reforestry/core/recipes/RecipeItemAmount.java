package com.leon1236.reforestry.core.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public record RecipeItemAmount(Item item, int count) {
    public static final Codec<RecipeItemAmount> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(RecipeItemAmount::item),
            Codec.intRange(0, 64).optionalFieldOf("count", 1).forGetter(RecipeItemAmount::count)
    ).apply(instance, RecipeItemAmount::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, RecipeItemAmount> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.registry(Registries.ITEM), RecipeItemAmount::item,
            ByteBufCodecs.VAR_INT, RecipeItemAmount::count,
            RecipeItemAmount::new
    );

    public ItemStack toStack() {
        if (this.item == null || this.item == Items.AIR || this.count <= 0) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(this.item, this.count);
    }
}
