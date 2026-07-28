package com.leon1236.reforestry.core.access;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;

import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.storage.base.FilteringStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;

import team.reborn.energy.api.EnergyStorage;

import com.leon1236.reforestry.api.core.AccessMode;
import com.leon1236.reforestry.api.core.ISidedAccess;

public final class AccessStorageHelper {
	private AccessStorageHelper() {
	}

	@Nullable
	public static <T> Storage<T> wrap(ISidedAccess access, @Nullable Direction direction, @Nullable Storage<T> backing) {
		if (backing == null) {
			return null;
		}
		if (direction == null) {
			return backing;
		}
		AccessMode mode = access.getAccess(direction);
		return switch (mode) {
			case NONE -> null;
			case INPUT -> FilteringStorage.insertOnlyOf(backing);
			case OUTPUT -> FilteringStorage.extractOnlyOf(backing);
			case BOTH -> backing;
		};
	}

	@Nullable
	public static EnergyStorage wrapEnergy(ISidedAccess access, @Nullable Direction direction, @Nullable EnergyStorage backing) {
		if (backing == null) {
			return null;
		}
		if (direction == null) {
			return backing;
		}
		AccessMode mode = access.getAccess(direction);
		return switch (mode) {
			case NONE -> null;
			case INPUT -> new RestrictedEnergy(backing, true, false);
			case OUTPUT -> new RestrictedEnergy(backing, false, true);
			case BOTH -> backing;
		};
	}

	private static final class RestrictedEnergy implements EnergyStorage {
		private final EnergyStorage backing;
		private final boolean allowInsert;
		private final boolean allowExtract;

		private RestrictedEnergy(EnergyStorage backing, boolean allowInsert, boolean allowExtract) {
			this.backing = backing;
			this.allowInsert = allowInsert;
			this.allowExtract = allowExtract;
		}

		@Override
		public boolean supportsInsertion() {
			return this.allowInsert && this.backing.supportsInsertion();
		}

		@Override
		public long insert(long maxAmount, TransactionContext transaction) {
			StoragePreconditions.notNegative(maxAmount);
			if (!this.allowInsert) {
				return 0;
			}
			return this.backing.insert(maxAmount, transaction);
		}

		@Override
		public boolean supportsExtraction() {
			return this.allowExtract && this.backing.supportsExtraction();
		}

		@Override
		public long extract(long maxAmount, TransactionContext transaction) {
			StoragePreconditions.notNegative(maxAmount);
			if (!this.allowExtract) {
				return 0;
			}
			return this.backing.extract(maxAmount, transaction);
		}

		@Override
		public long getAmount() {
			return this.backing.getAmount();
		}

		@Override
		public long getCapacity() {
			return this.backing.getCapacity();
		}
	}
}
