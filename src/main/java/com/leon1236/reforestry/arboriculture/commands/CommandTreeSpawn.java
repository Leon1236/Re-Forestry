package com.leon1236.reforestry.arboriculture.commands;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;

public final class CommandTreeSpawn {
    private static final SimpleCommandExceptionType INVALID_SPECIES =
            new SimpleCommandExceptionType(new LiteralMessage("Invalid tree species"));

    private CommandTreeSpawn() {
    }

    public static ArgumentBuilder<CommandSourceStack, ?> register(String name, ITreeSpawner treeSpawner) {
        return Commands.literal(name)
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.argument("type", IdentifierArgument.id())
                        .suggests((context, builder) ->
                                SharedSuggestionProvider.suggestResource(ArboricultureGenetics.getAllSpeciesIds(), builder))
                        .executes(a -> run(
                                treeSpawner,
                                a.getSource(),
                                resolveSpecies(IdentifierArgument.getId(a, "type")))));
    }

    private static ITreeSpecies resolveSpecies(Identifier id) throws CommandSyntaxException {
        ITreeSpecies species = ArboricultureGenetics.getSpeciesSafe(id);
        if (species == null) {
            throw INVALID_SPECIES.create();
        }
        return species;
    }

    public static int run(ITreeSpawner treeSpawner, CommandSourceStack source, ITreeSpecies species)
            throws CommandSyntaxException {
        return treeSpawner.spawn(source, species, source.getPlayerOrException());
    }
}
