package com.leon1236.reforestry.api.gui;

import java.util.List;

import net.minecraft.world.level.Level;

public interface IMachineRecipeSource {
	List<MachineRecipeEntry> getGuiRecipes(Level level);
}
