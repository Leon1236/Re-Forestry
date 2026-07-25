package com.leon1236.reforestry.arboriculture.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Player;

import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;

@FunctionalInterface
public interface ITreeSpawner {
    int spawn(CommandSourceStack source, ITreeSpecies species, Player player);
}
