package com.leon1236.reforestry.arboriculture.villagers;

import java.util.List;
import java.util.Locale;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.WoodAccess;

public class SetRandomForestryWoodFunction extends LootItemConditionalFunction {
    private static final Codec<WoodBlockKind> KIND_CODEC = Codec.STRING.xmap(
            name -> WoodBlockKind.valueOf(name.toUpperCase(Locale.ROOT)),
            kind -> kind.getSerializedName());

    public static final MapCodec<SetRandomForestryWoodFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .and(instance.group(
                            KIND_CODEC.fieldOf("kind").forGetter(function -> function.kind),
                            Codec.INT.fieldOf("min_count").forGetter(function -> function.minCount),
                            Codec.INT.fieldOf("max_count").forGetter(function -> function.maxCount)))
                    .apply(instance, SetRandomForestryWoodFunction::new));

    private final WoodBlockKind kind;
    private final int minCount;
    private final int maxCount;

    private SetRandomForestryWoodFunction(List<LootItemCondition> predicates, WoodBlockKind kind, int minCount, int maxCount) {
        super(predicates);
        this.kind = kind;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public MapCodec<SetRandomForestryWoodFunction> codec() {
        return MAP_CODEC;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        ItemStack sell = WoodAccess.INSTANCE.getStack(ForestryWoodType.getRandom(context.getRandom()), this.kind, false);
        sell.setCount(price(context.getRandom(), this.minCount, this.maxCount));
        return sell;
    }

    static int price(RandomSource random, int min, int max) {
        return min >= max ? min : min + random.nextInt(max - min + 1);
    }
}
