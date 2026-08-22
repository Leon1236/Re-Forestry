package com.leon1236.reforestry.gendustry.compat.jei.producers;

import java.util.Collection;
import java.util.Collections;

import mezz.jei.api.gui.handlers.IGuiClickableArea;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;

import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.gendustry.blockentity.DnaExtractorBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.MutagenProducerBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.ProteinLiquefierBlockEntity;
import com.leon1236.reforestry.gendustry.client.ScreenProducer;
import com.leon1236.reforestry.gendustry.compat.jei.GendustryJeiRecipeTypes;

public class ProducerGuiContainerHandler implements IGuiContainerHandler<ScreenProducer> {
	@Override
	public Collection<IGuiClickableArea> getGuiClickableAreas(ScreenProducer containerScreen, double guiMouseX, double guiMouseY) {
		BlockEntity blockEntity = containerScreen.getMenu().getTile();
		if (blockEntity instanceof MutagenProducerBlockEntity) {
			return Collections.singleton(IGuiClickableArea.createBasic(48, 40, 55, 18, GendustryJeiRecipeTypes.MUTAGEN_PRODUCER));
		}
		if (blockEntity instanceof DnaExtractorBlockEntity) {
			return Collections.singleton(IGuiClickableArea.createBasic(48, 40, 55, 18, GendustryJeiRecipeTypes.DNA_EXTRACTOR));
		}
		if (blockEntity instanceof ProteinLiquefierBlockEntity) {
			return Collections.singleton(IGuiClickableArea.createBasic(48, 40, 55, 18, GendustryJeiRecipeTypes.PROTEIN_LIQUEFIER));
		}
		return Collections.emptyList();
	}
}
