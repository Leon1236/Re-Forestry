package com.leon1236.reforestry.gendustry.compat.jei;

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.gendustry.item.GeneSampleItem;
import com.leon1236.reforestry.gendustry.item.data.GeneSampleInfo;

final class GeneSampleInterpreter implements ISubtypeInterpreter<ItemStack> {
	@Override
	public Object getSubtypeData(ItemStack ingredient, UidContext context) {
		if (context == UidContext.Recipe) {
			return "written";
		}
		GeneSampleInfo info = GeneSampleItem.getInfo(ingredient);
		if (info == null) {
			return null;
		}
		return info.type().id() + "|" + info.chromosome().id() + "|" + info.allele().alleleId();
	}
}
