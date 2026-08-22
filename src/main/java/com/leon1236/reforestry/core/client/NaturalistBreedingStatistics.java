package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

import com.leon1236.reforestry.api.apiculture.IApiaristTracker;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.core.genetics.root.BreedingTrackerManager;

@Environment(EnvType.CLIENT)
public final class NaturalistBreedingStatistics {
	private static final int TEXT_COLOR = 0xFF404040;

	private NaturalistBreedingStatistics() {
	}

	public static void display(GuiGraphicsExtractor graphics, Font font, Identifier speciesTypeId, int x, int y) {
		Player player = Minecraft.getInstance().player;
		if (player == null) {
			return;
		}

		IBreedingTracker tracker = BreedingTrackerManager.INSTANCE.getTracker(
				speciesTypeId, player.level(), player.getGameProfile());
		int lineHeight = font.lineHeight;
		graphics.text(font, Component.translatable("for.gui.speciescount")
				.append(": ")
				.append(tracker.getSpeciesBred() + "/" + totalSpecies(speciesTypeId)), x, y, TEXT_COLOR, false);
		y += lineHeight * 2;

		if (tracker instanceof IApiaristTracker apiarist) {
			graphics.text(font, Component.translatable("for.gui.queens")
					.append(": ")
					.append(Integer.toString(apiarist.getQueenCount())), x, y, TEXT_COLOR, false);
			y += lineHeight;
			graphics.text(font, Component.translatable("for.gui.princesses")
					.append(": ")
					.append(Integer.toString(apiarist.getPrincessCount())), x, y, TEXT_COLOR, false);
			y += lineHeight;
			graphics.text(font, Component.translatable("for.gui.drones")
					.append(": ")
					.append(Integer.toString(apiarist.getDroneCount())), x, y, TEXT_COLOR, false);
		}
	}

	private static int totalSpecies(Identifier speciesTypeId) {
		if (ForestrySpeciesTypes.BEE.equals(speciesTypeId)) {
			return ApicultureGenetics.getAllSpeciesIds().size();
		}
		if (ForestrySpeciesTypes.TREE.equals(speciesTypeId)) {
			return ArboricultureGenetics.getAllSpeciesIds().size();
		}
		return 0;
	}
}
