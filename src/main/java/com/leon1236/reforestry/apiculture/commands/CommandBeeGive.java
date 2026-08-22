package com.leon1236.reforestry.apiculture.commands;

import java.util.ArrayList;
import java.util.List;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;

public final class CommandBeeGive {
    private static final SimpleCommandExceptionType INVALID_SPECIES =
            new SimpleCommandExceptionType(new LiteralMessage("Invalid Bee Type"));
    private static final SimpleCommandExceptionType INVALID_STAGE =
            new SimpleCommandExceptionType(new LiteralMessage("Invalid bee type"));
    private static final String DEFAULT_STAGE = "drone";

    private CommandBeeGive() {
    }

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("give")
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .executes(a -> give(
                        a.getSource(),
                        defaultSpeciesId(),
                        DEFAULT_STAGE,
                        a.getSource().getPlayerOrException()))
                .then(Commands.argument("species", IdentifierArgument.id())
                        .suggests((context, builder) -> {
                            List<String> names = new ArrayList<>();
                            for (Identifier id : ApicultureGenetics.getAllSpeciesIds()) {
                                names.add(id.toString());
                                names.add(id.getPath());
                            }
                            return SharedSuggestionProvider.suggest(names, builder);
                        })
                        .executes(a -> give(
                                a.getSource(),
                                resolveSpecies(IdentifierArgument.getId(a, "species")),
                                DEFAULT_STAGE,
                                a.getSource().getPlayerOrException()))
                        .then(Commands.argument("type", StringArgumentType.word())
                                .suggests((context, builder) -> SharedSuggestionProvider.suggest(
                                        List.of("drone", "princess", "queen", "larvae"), builder))
                                .executes(a -> give(
                                        a.getSource(),
                                        resolveSpecies(IdentifierArgument.getId(a, "species")),
                                        StringArgumentType.getString(a, "type"),
                                        a.getSource().getPlayerOrException()))
                                .then(Commands.argument("player", EntityArgument.player())
                                        .executes(a -> give(
                                                a.getSource(),
                                                resolveSpecies(IdentifierArgument.getId(a, "species")),
                                                StringArgumentType.getString(a, "type"),
                                                EntityArgument.getPlayer(a, "player"))))));
    }

    private static Identifier defaultSpeciesId() throws CommandSyntaxException {
        for (Identifier id : ApicultureGenetics.getAllSpeciesIds()) {
            return id;
        }
        throw INVALID_SPECIES.create();
    }

    private static Identifier resolveSpecies(Identifier speciesId) throws CommandSyntaxException {
        if (ApicultureGenetics.getSpeciesSafe(speciesId) != null) {
            return speciesId;
        }
        if (Identifier.DEFAULT_NAMESPACE.equals(speciesId.getNamespace())) {
            Identifier local = ReForestry.id(speciesId.getPath());
            if (ApicultureGenetics.getSpeciesSafe(local) != null) {
                return local;
            }
        }
        throw new SimpleCommandExceptionType(new LiteralMessage("Invalid Bee Type: " + speciesId)).create();
    }

    private static int give(CommandSourceStack source, Identifier speciesId, String stage, Player player)
            throws CommandSyntaxException {
        IBeeSpecies species = ApicultureGenetics.getSpeciesSafe(speciesId);
        if (species == null) {
            throw INVALID_SPECIES.create();
        }
        Item item = itemForStage(stage);
        ItemStack stack = BeeStackHelper.createBeeStack(
                item, ApicultureGenetics.getDefaultGenome(speciesId), true, 0);
        player.getInventory().add(stack);
        source.sendSuccess(() -> Component.translatable(
                "for.chat.command.reforestry.bee.give.given",
                player.getName(),
                stack.getHoverName()), false);
        return 1;
    }

    private static Item itemForStage(String stage) throws CommandSyntaxException {
        return switch (stage) {
            case "drone" -> ApicultureItems.BEE_DRONE.item();
            case "princess" -> ApicultureItems.BEE_PRINCESS.item();
            case "queen" -> ApicultureItems.BEE_QUEEN.item();
            case "larvae" -> ApicultureItems.BEE_LARVAE.item();
            default -> throw INVALID_STAGE.create();
        };
    }
}
