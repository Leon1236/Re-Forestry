package com.leon1236.reforestry.apiculture.commands;

import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public final class CommandBee {
    private CommandBee() {
    }

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("bee")
                .then(CommandBeeGive.register());
    }
}
