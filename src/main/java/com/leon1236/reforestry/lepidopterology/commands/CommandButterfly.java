package com.leon1236.reforestry.lepidopterology.commands;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.lepidopterology.genetics.ButterflyLifeStage;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyDataComponents;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyEntities;
import com.leon1236.reforestry.lepidopterology.genetics.LepidopterologyGenetics;
import com.leon1236.reforestry.lepidopterology.items.ItemButterflyGE;

public final class CommandButterfly {
	private static final SimpleCommandExceptionType INVALID_SPECIES =
			new SimpleCommandExceptionType(new LiteralMessage("Invalid butterfly species"));
	private static final SimpleCommandExceptionType INVALID_STAGE =
			new SimpleCommandExceptionType(new LiteralMessage("Invalid butterfly stage"));
	private static final SimpleCommandExceptionType NOT_HOLDING =
			new SimpleCommandExceptionType(new LiteralMessage("Hold a butterfly item to modify"));

	private CommandButterfly() {
	}

	public static ArgumentBuilder<CommandSourceStack, ?> register() {
		return Commands.literal("butterfly")
				.then(registerKill())
				.then(registerGive())
				.then(registerModify());
	}

	private static ArgumentBuilder<CommandSourceStack, ?> registerKill() {
		return Commands.literal("kill")
				.requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
				.executes(CommandButterfly::kill);
	}

	private static ArgumentBuilder<CommandSourceStack, ?> registerGive() {
		return Commands.literal("give")
				.requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
				.then(Commands.argument("species", IdentifierArgument.id())
						.suggests((context, builder) ->
								SharedSuggestionProvider.suggestResource(LepidopterologyGenetics.getAllSpeciesIds(), builder))
						.executes(context -> give(context, ButterflyLifeStage.BUTTERFLY))
						.then(Commands.argument("stage", IdentifierArgument.id())
								.suggests((context, builder) -> SharedSuggestionProvider.suggest(
										java.util.Arrays.stream(ButterflyLifeStage.values())
												.map(stage -> stage.itemId().toString())
												.toList(),
										builder))
								.executes(context -> give(context, resolveStage(IdentifierArgument.getId(context, "stage"))))));
	}

	private static ArgumentBuilder<CommandSourceStack, ?> registerModify() {
		return Commands.literal("modify")
				.requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
				.then(Commands.argument("species", IdentifierArgument.id())
						.suggests((context, builder) ->
								SharedSuggestionProvider.suggestResource(LepidopterologyGenetics.getAllSpeciesIds(), builder))
						.executes(CommandButterfly::modify));
	}

	private static int kill(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		int killCount = 0;
		for (Entity butterfly : context.getSource().getLevel()
				.getEntities(LepidopterologyEntities.BUTTERFLY.entityType(), EntitySelector.ENTITY_STILL_ALIVE)) {
			butterfly.remove(Entity.RemovalReason.KILLED);
			killCount++;
		}
		int finalKillCount = killCount;
		context.getSource().sendSuccess(
				() -> Component.translatable("for.chat.command.reforestry.butterfly.kill.response", finalKillCount),
				true);
		return killCount;
	}

	private static int give(CommandContext<CommandSourceStack> context, ButterflyLifeStage stage)
			throws CommandSyntaxException {
		IButterflySpecies species = resolveSpecies(IdentifierArgument.getId(context, "species"));
		Player player = context.getSource().getPlayerOrException();
		ItemStack stack = species.createStack(stage);
		Component displayName = stack.getHoverName();
		player.getInventory().add(stack);
		context.getSource().sendSuccess(
				() -> Component.translatable("for.chat.command.reforestry.bee.give.given", player.getName(), displayName),
				true);
		return 1;
	}

	private static int modify(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		Player player = context.getSource().getPlayerOrException();
		ItemStack held = player.getMainHandItem();
		if (!(held.getItem() instanceof ItemButterflyGE)) {
			throw NOT_HOLDING.create();
		}
		IButterflySpecies species = resolveSpecies(IdentifierArgument.getId(context, "species"));
		held.set(LepidopterologyDataComponents.BUTTERFLY_GENOME.type(), species.getDefaultGenome());
		held.remove(LepidopterologyDataComponents.BUTTERFLY_MATE_GENOME.type());
		return 1;
	}

	private static IButterflySpecies resolveSpecies(Identifier id) throws CommandSyntaxException {
		try {
			return LepidopterologyGenetics.getSpecies(id);
		} catch (IllegalArgumentException e) {
			throw INVALID_SPECIES.create();
		}
	}

	private static ButterflyLifeStage resolveStage(Identifier id) throws CommandSyntaxException {
		for (ButterflyLifeStage stage : ButterflyLifeStage.values()) {
			if (stage.itemId().equals(id) || stage.getSerializedName().equals(id.getPath())) {
				return stage;
			}
		}
		throw INVALID_STAGE.create();
	}
}
