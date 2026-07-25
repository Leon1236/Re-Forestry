package com.leon1236.reforestry.arboriculture.commands;

import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public final class CommandTree {
    private CommandTree() {
    }

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("tree")
                .then(CommandTreeSpawn.register("spawnTree", new TreeSpawner()))
                .then(CommandTreeSpawn.register("spawnForest", new ForestSpawner()));
    }
}
