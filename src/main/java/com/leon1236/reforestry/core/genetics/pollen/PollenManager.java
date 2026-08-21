package com.leon1236.reforestry.core.genetics.pollen;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.pollen.IPollen;
import com.leon1236.reforestry.api.genetics.pollen.IPollenManager;
import com.leon1236.reforestry.api.genetics.pollen.IPollenType;

public final class PollenManager implements IPollenManager {
	public static final PollenManager INSTANCE = new PollenManager();

	private PollenManager() {
	}

	@Override
	public boolean canPollinate(Level level, BlockPos pos) {
		for (IPollenType type : PollenTypes.all()) {
			if (type.canPollinate(level, pos)) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Nullable
	public IPollen getPollen(Level level, BlockPos pos) {
		for (IPollenType type : PollenTypes.all()) {
			Optional<IGenome> pollen = type.tryCollectPollen(level, pos, level.getRandom());
			if (pollen.isPresent()) {
				return createPollen(type, pollen.get());
			}
		}
		return null;
	}

	@Override
	@Nullable
	public IPollen getPollenOfType(Level level, BlockPos pos, Set<Identifier> pollenTypes) {
		for (IPollenType type : PollenTypes.all()) {
			if (!pollenTypes.contains(type.id())) {
				continue;
			}
			Optional<IGenome> pollen = type.tryCollectPollen(level, pos, level.getRandom());
			if (pollen.isPresent()) {
				return createPollen(type, pollen.get());
			}
		}
		return null;
	}

	@Override
	@Nullable
	public IPollenType getPollenType(Identifier id) {
		for (IPollenType type : PollenTypes.all()) {
			if (type.id().equals(id)) {
				return type;
			}
		}
		return null;
	}

	@Override
	public Collection<IPollenType> getAllPollenTypes() {
		return PollenTypes.all();
	}

	public IPollen createPollen(IPollenType type, IGenome genome) {
		return new GenomePollen(type, genome);
	}

	private record GenomePollen(IPollenType type, IGenome pollen) implements IPollen {
		@Override
		public IPollenType getType() {
			return type;
		}

		@Override
		public IGenome getPollen() {
			return pollen;
		}

		@Override
		public ItemStack createStack() {
			return ItemStack.EMPTY;
		}
	}
}
