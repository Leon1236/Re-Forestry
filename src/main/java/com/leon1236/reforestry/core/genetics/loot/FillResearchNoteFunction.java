package com.leon1236.reforestry.core.genetics.loot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.core.features.CoreDataComponents;
import com.leon1236.reforestry.core.genetics.ResearchNoteContents;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

public class FillResearchNoteFunction extends LootItemConditionalFunction {
	public static final MapCodec<FillResearchNoteFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
			instance -> commonFields(instance).apply(instance, FillResearchNoteFunction::new));

	private FillResearchNoteFunction(List<LootItemCondition> predicates) {
		super(predicates);
	}

	@Override
	public MapCodec<FillResearchNoteFunction> codec() {
		return MAP_CODEC;
	}

	@Override
	public ItemStack run(ItemStack stack, LootContext context) {
		Mutation mutation = pickMutation(context.getRandom());
		if (mutation == null) {
			return stack;
		}
		stack.set(CoreDataComponents.RESEARCH_NOTE.type(), new ResearchNoteContents(
				Optional.empty(),
				mutation.typeId(),
				mutation.firstParent(),
				mutation.secondParent(),
				Optional.of(mutation.result())));
		return stack;
	}

	private static Mutation pickMutation(RandomSource random) {
		List<Mutation> mutations = new ArrayList<>();
		mutations.addAll(ApicultureGenetics.getAllMutations());
		mutations.addAll(ArboricultureGenetics.getAllMutations());
		if (mutations.isEmpty()) {
			return null;
		}
		return mutations.get(random.nextInt(mutations.size()));
	}

	public static LootItemConditionalFunction.Builder<?> fillResearchNote() {
		return simpleBuilder(FillResearchNoteFunction::new);
	}
}
