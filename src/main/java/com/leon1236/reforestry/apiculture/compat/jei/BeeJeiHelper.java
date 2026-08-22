package com.leon1236.reforestry.apiculture.compat.jei;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.core.features.CoreDataComponents;
import com.leon1236.reforestry.modules.features.FeatureItem;

final class BeeJeiHelper {
	private static final FeatureItem<?>[] LIFE_STAGES = {
			ApicultureItems.BEE_DRONE,
			ApicultureItems.BEE_PRINCESS,
			ApicultureItems.BEE_QUEEN,
			ApicultureItems.BEE_LARVAE
	};

	private BeeJeiHelper() {
	}

	static ItemStack analyzedStack(FeatureItem<?> item, Identifier speciesId) {
		IGenome genome = ApicultureGenetics.getDefaultGenome(speciesId);
		ItemStack stack = BeeStackHelper.createBeeStack(item.item(), genome, true, 0);
		stack.set(CoreDataComponents.ANALYZED.type(), true);
		return stack;
	}

	static List<ItemStack> allLifeStages(Identifier speciesId) {
		List<ItemStack> stacks = new ArrayList<>(LIFE_STAGES.length);
		for (FeatureItem<?> item : LIFE_STAGES) {
			stacks.add(analyzedStack(item, speciesId));
		}
		return stacks;
	}

	@Nullable
	static Identifier activeSpeciesId(ItemStack stack) {
		IGenome genome = stack.get(ApicultureDataComponents.BEE_GENOME.type());
		if (genome == null) {
			return null;
		}
		return genome.getActiveAllele(BeeChromosomes.SPECIES).alleleId();
	}

	static Component speciesName(Identifier speciesId) {
		return Component.translatable("allele.reforestry.bee_species." + speciesId.getPath());
	}

	static void drawCentered(GuiGraphicsExtractor graphics, Component text, int x, int y, int color) {
		Font font = Minecraft.getInstance().font;
		String[] lines = text.getString().split(" ");
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			int width = font.width(line);
			graphics.text(font, line, Math.round(x - width / 2f), y + i * font.lineHeight, color, false);
		}
	}

	static String formatPercentage(float chance) {
		return String.valueOf(Math.round(chance * 100f));
	}
}
