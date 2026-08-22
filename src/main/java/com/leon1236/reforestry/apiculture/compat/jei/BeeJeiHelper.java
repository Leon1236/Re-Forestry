package com.leon1236.reforestry.apiculture.compat.jei;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.BeeSpeciesType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.compat.jei.GeneticsJeiHelper;
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
		return GeneticsJeiHelper.allLifeStages(BeeSpeciesType.INSTANCE, speciesId, null);
	}

	@Nullable
	static Identifier activeSpeciesId(ItemStack stack) {
		IGenome genome = stack.get(ApicultureDataComponents.BEE_GENOME.type());
		if (genome == null) {
			return null;
		}
		return genome.getActiveAllele(BeeChromosomes.SPECIES).alleleId();
	}
}
