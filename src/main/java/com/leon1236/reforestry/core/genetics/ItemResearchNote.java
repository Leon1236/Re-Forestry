package com.leon1236.reforestry.core.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.server.players.NameAndId;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.core.features.CoreDataComponents;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.genetics.mutations.EnumMutateChance;
import com.leon1236.reforestry.core.genetics.root.BreedingTrackerManager;

public class ItemResearchNote extends Item {
	public ItemResearchNote(Properties properties) {
		super(properties);
	}

	@Override
	public Component getName(ItemStack stack) {
		ResearchNoteContents note = stack.get(CoreDataComponents.RESEARCH_NOTE.type());
		String researcherName = "Sengir";
		if (note != null && note.researcher().isPresent()) {
			researcherName = note.researcher().get().name();
		}
		return Component.translatable(getDescriptionId(), researcherName);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
			Consumer<Component> tooltip, TooltipFlag flag) {
		ResearchNoteContents note = stack.get(CoreDataComponents.RESEARCH_NOTE.type());
		if (note == null) {
			tooltip.accept(Component.translatable("for.researchNote.error.0").withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
			tooltip.accept(Component.translatable("for.researchNote.error.1"));
			return;
		}
		List<Component> noteTooltip = getTooltip(note);
		if (noteTooltip.isEmpty()) {
			tooltip.accept(Component.translatable("for.researchNote.error.0").withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
			tooltip.accept(Component.translatable("for.researchNote.error.1"));
			return;
		}
		noteTooltip.forEach(tooltip);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		ResearchNoteContents note = heldItem.get(CoreDataComponents.RESEARCH_NOTE.type());
		if (note != null && registerResults(level, player, note)) {
			heldItem.shrink(1);
		}
		return InteractionResult.SUCCESS;
	}

	@Nullable
	private static IMutation getEncodedMutation(ResearchNoteContents contents) {
		if (ForestrySpeciesTypes.BEE.equals(contents.typeId())) {
			return ApicultureGenetics.findMutation(contents.parent0(), contents.parent1(), contents.result().orElse(null));
		}
		if (ForestrySpeciesTypes.TREE.equals(contents.typeId())) {
			return ArboricultureGenetics.findMutation(contents.parent0(), contents.parent1(), contents.result().orElse(null));
		}
		return null;
	}

	public static List<Component> getTooltip(ResearchNoteContents contents) {
		IMutation mutation = getEncodedMutation(contents);
		if (mutation == null) {
			return List.of();
		}
		ArrayList<Component> tooltips = new ArrayList<>();

		Component species1 = Component.literal("'").append(speciesDisplayName(mutation.typeId(), mutation.firstParent()))
				.append("'").withStyle(ChatFormatting.YELLOW);
		Component species2 = Component.literal("'").append(speciesDisplayName(mutation.typeId(), mutation.secondParent()))
				.append("'").withStyle(ChatFormatting.YELLOW);
		String mutationChanceKey = EnumMutateChance.rateChance(mutation.getChance()).toString().toLowerCase(Locale.ENGLISH);
		Component mutationChance = Component.translatable("for.researchNote.chance." + mutationChanceKey)
				.withStyle(ChatFormatting.BLUE);
		Component speciesResult = speciesDisplayName(mutation.typeId(), mutation.result()).copy()
				.withStyle(ChatFormatting.LIGHT_PURPLE);

		tooltips.add(Component.translatable("for.researchNote.discovery.0"));
		tooltips.add(Component.translatable("for.researchNote.discovery.1", species1, species2).withStyle(ChatFormatting.GRAY));
		tooltips.add(Component.translatable("for.researchNote.discovery.2", mutationChance).withStyle(ChatFormatting.GRAY));
		tooltips.add(Component.translatable("for.researchNote.discovery.3", speciesResult).withStyle(ChatFormatting.GRAY));

		for (Component line : mutation.getSpecialConditions()) {
			tooltips.add(line.copy().withStyle(ChatFormatting.GOLD));
		}

		return tooltips;
	}

	public static boolean registerResults(Level level, Player player, ResearchNoteContents contents) {
		IMutation encoded = getEncodedMutation(contents);
		if (encoded == null) {
			return false;
		}

		IBreedingTracker tracker = BreedingTrackerManager.INSTANCE.getTracker(encoded.typeId(), level, player.getGameProfile());
		if (tracker.isResearched(encoded)) {
			player.sendSystemMessage(Component.translatable("for.chat.cannotmemorizeagain"));
			return false;
		}

		Identifier speciesFirst = encoded.firstParent();
		Identifier speciesSecond = encoded.secondParent();
		Identifier speciesResult = encoded.result();

		tracker.registerSpecies(speciesFirst);
		tracker.registerSpecies(speciesSecond);
		tracker.registerSpecies(speciesResult);
		tracker.researchMutation(encoded);

		player.sendSystemMessage(Component.translatable("for.chat.memorizednote"));
		player.sendSystemMessage(Component.translatable("for.chat.memorizednote2",
				speciesDisplayName(encoded.typeId(), speciesFirst).withStyle(ChatFormatting.GRAY),
				speciesDisplayName(encoded.typeId(), speciesSecond).withStyle(ChatFormatting.GRAY),
				speciesDisplayName(encoded.typeId(), speciesResult).withStyle(ChatFormatting.GREEN)));

		return true;
	}

	public static ResearchNoteContents createMutationNote(GameProfile researcher, IMutation mutation) {
		return new ResearchNoteContents(
				Optional.of(new NameAndId(researcher)),
				mutation.typeId(),
				mutation.firstParent(),
				mutation.secondParent(),
				Optional.of(mutation.result()));
	}

	public static ItemStack createMutationNoteStack(GameProfile researcher, IMutation mutation) {
		ItemStack created = new ItemStack(CoreItems.RESEARCH_NOTE.item());
		created.set(CoreDataComponents.RESEARCH_NOTE.type(), createMutationNote(researcher, mutation));
		return created;
	}

	private static MutableComponent speciesDisplayName(Identifier typeId, Identifier speciesId) {
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			return Component.translatable("allele.reforestry.tree_species." + speciesId.getPath());
		}
		return Component.translatable("allele.reforestry.bee_species." + speciesId.getPath());
	}
}
