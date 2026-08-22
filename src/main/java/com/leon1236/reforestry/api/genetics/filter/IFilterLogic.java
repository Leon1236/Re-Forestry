package com.leon1236.reforestry.api.genetics.filter;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.core.INbtReadable;
import com.leon1236.reforestry.api.core.INbtWritable;

public interface IFilterLogic extends INbtWritable, INbtReadable {
	void writeGuiData(FriendlyByteBuf buffer);

	void readGuiData(FriendlyByteBuf data);

	boolean isValid(ItemStack stack, Direction facing);

	boolean isValid(Direction facing, ItemStack stack, FilterData filterData);

	boolean isValidAllelePair(Direction orientation, Identifier active, Identifier inactive);

	IFilterRuleType getRule(Direction facing);

	boolean setRule(Direction facing, IFilterRuleType rule);

	@Nullable
	Identifier getGenomeFilter(Direction facing, int index, boolean active);

	boolean setGenomeFilter(Direction facing, int index, boolean active, @Nullable Identifier species);

	void sendToServer(Direction facing, int index, boolean active, @Nullable Identifier allele);

	void sendToServer(Direction facing, IFilterRuleType rule);

	INetworkHandler getNetworkHandler();

	interface INetworkHandler {
		void sendToPlayers(IFilterLogic logic, ServerLevel level, Player player);
	}
}
