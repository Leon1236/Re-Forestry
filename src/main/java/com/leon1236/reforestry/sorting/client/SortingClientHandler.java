package com.leon1236.reforestry.sorting.client;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;
import com.leon1236.reforestry.sorting.features.SortingMenus;
import com.leon1236.reforestry.sorting.network.FilterPackets;
import com.leon1236.reforestry.sorting.network.packets.FilterChangeGenomePayload;
import com.leon1236.reforestry.sorting.network.packets.FilterChangeRulePayload;
import com.leon1236.reforestry.sorting.network.packets.GuiUpdateFilterPayload;
import com.leon1236.reforestry.sorting.tiles.TileGeneticFilter;

@Environment(EnvType.CLIENT)
public class SortingClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		MenuScreens.register(SortingMenus.GENETIC_FILTER.type(), ScreenGeneticFilter::new);
		FilterPackets.setSender(new FilterPackets.Sender() {
			@Override
			public void sendRule(BlockPos pos, Direction facing, IFilterRuleType rule) {
				ClientPlayNetworking.send(new FilterChangeRulePayload(pos, facing, rule));
			}

			@Override
			public void sendGenome(BlockPos pos, Direction facing, int index, boolean active, @Nullable Identifier species) {
				ClientPlayNetworking.send(new FilterChangeGenomePayload(pos, facing, (short) index, active, species));
			}
		});
		ClientPlayNetworking.registerGlobalReceiver(GuiUpdateFilterPayload.TYPE, (payload, context) -> {
			context.client().execute(() -> {
				if (context.client().level == null) {
					return;
				}
				BlockEntity blockEntity = context.client().level.getBlockEntity(payload.pos());
				if (blockEntity instanceof TileGeneticFilter tile) {
					payload.applyTo(tile.getLogic());
				}
			});
		});
	}
}
