package com.leon1236.reforestry.apiculture.villagers;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;

public class SetBeeSpeciesGenomeFunction extends LootItemConditionalFunction {
    public static final MapCodec<SetBeeSpeciesGenomeFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .and(instance.group(
                            Identifier.CODEC.fieldOf("species").forGetter(function -> function.species),
                            Codec.STRING.optionalFieldOf("stage", "drone").forGetter(function -> function.stage),
                            Codec.INT.fieldOf("min_count").forGetter(function -> function.minCount),
                            Codec.INT.fieldOf("max_count").forGetter(function -> function.maxCount)))
                    .apply(instance, SetBeeSpeciesGenomeFunction::new));

    private final Identifier species;
    private final String stage;
    private final int minCount;
    private final int maxCount;

    private SetBeeSpeciesGenomeFunction(
            List<LootItemCondition> predicates,
            Identifier species,
            String stage,
            int minCount,
            int maxCount) {
        super(predicates);
        this.species = species;
        this.stage = stage;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public MapCodec<SetBeeSpeciesGenomeFunction> codec() {
        return MAP_CODEC;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        Item item = switch (this.stage) {
            case "princess" -> ApicultureItems.BEE_PRINCESS.item();
            case "queen" -> ApicultureItems.BEE_QUEEN.item();
            case "larvae" -> ApicultureItems.BEE_LARVAE.item();
            default -> ApicultureItems.BEE_DRONE.item();
        };
        ItemStack bee = BeeStackHelper.createBeeStack(
                item,
                ApicultureGenetics.getDefaultGenome(this.species),
                true,
                0);
        bee.setCount(SetRandomVillageCombFunction.price(context.getRandom(), this.minCount, this.maxCount));
        return bee;
    }
}
