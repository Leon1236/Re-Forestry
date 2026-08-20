package com.leon1236.reforestry.core.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import com.leon1236.reforestry.ReForestry;

public final class ReforestryCommands {
    private ReforestryCommands() {
    }

    public static void registerSubcommand(
            CommandDispatcher<CommandSourceStack> dispatcher,
            ArgumentBuilder<CommandSourceStack, ?> subcommand) {
        CommandNode<CommandSourceStack> existing = dispatcher.getRoot().getChild(ReForestry.MOD_ID);
        if (existing != null) {
            existing.addChild(subcommand.build());
        } else {
            dispatcher.register(Commands.literal(ReForestry.MOD_ID).then(subcommand));
        }
    }
}
