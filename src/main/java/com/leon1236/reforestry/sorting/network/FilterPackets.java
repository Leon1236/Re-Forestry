package com.leon1236.reforestry.sorting.network;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.filter.IFilterRuleType;

public final class FilterPackets {
	private static Sender sender = Sender.NOOP;

	private FilterPackets() {
	}

	public static void setSender(Sender sender) {
		FilterPackets.sender = sender == null ? Sender.NOOP : sender;
	}

	public static void sendRule(BlockPos pos, Direction facing, IFilterRuleType rule) {
		sender.sendRule(pos, facing, rule);
	}

	public static void sendGenome(BlockPos pos, Direction facing, int index, boolean active, @Nullable Identifier species) {
		sender.sendGenome(pos, facing, index, active, species);
	}

	public interface Sender {
		Sender NOOP = new Sender() {
			@Override
			public void sendRule(BlockPos pos, Direction facing, IFilterRuleType rule) {
			}

			@Override
			public void sendGenome(BlockPos pos, Direction facing, int index, boolean active, @Nullable Identifier species) {
			}
		};

		void sendRule(BlockPos pos, Direction facing, IFilterRuleType rule);

		void sendGenome(BlockPos pos, Direction facing, int index, boolean active, @Nullable Identifier species);
	}
}
