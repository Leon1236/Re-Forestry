package com.leon1236.reforestry.apiculture.villagers;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;

public class SetRandomMundaneDroneFunction extends LootItemConditionalFunction {
    private static final Identifier[] MUNDANE = {
            ReForestry.id("bee_forest"),
            ReForestry.id("bee_meadows"),
            ReForestry.id("bee_modest"),
            ReForestry.id("bee_wintry"),
            ReForestry.id("bee_tropical"),
            ReForestry.id("bee_marshy")
    };

    public static final MapCodec<SetRandomMundaneDroneFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .and(instance.group(
                            Codec.INT.fieldOf("min_count").forGetter(function -> function.minCount),
                            Codec.INT.fieldOf("max_count").forGetter(function -> function.maxCount)))
                    .apply(instance, SetRandomMundaneDroneFunction::new));

    private final int minCount;
    private final int maxCount;

    private SetRandomMundaneDroneFunction(List<LootItemCondition> predicates, int minCount, int maxCount) {
        super(predicates);
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public MapCodec<SetRandomMundaneDroneFunction> codec() {
        return MAP_CODEC;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        Identifier speciesId = MUNDANE[context.getRandom().nextInt(MUNDANE.length)];
        ItemStack drone = BeeStackHelper.createBeeStack(
                ApicultureItems.BEE_DRONE.item(),
                ApicultureGenetics.getDefaultGenome(speciesId),
                true,
                0);
        drone.setCount(SetRandomVillageCombFunction.price(context.getRandom(), this.minCount, this.maxCount));
        return drone;
    }
}
