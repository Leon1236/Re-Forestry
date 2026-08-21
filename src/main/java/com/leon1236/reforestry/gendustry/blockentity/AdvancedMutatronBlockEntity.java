package com.leon1236.reforestry.gendustry.blockentity;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.gendustry.errors.GendustryError;
import com.leon1236.reforestry.gendustry.features.GBlockEntities;
import com.leon1236.reforestry.gendustry.menu.AdvancedMutatronMenu;

public class AdvancedMutatronBlockEntity extends AbstractMutatronBlockEntity {
	private List<IMutation> possibilities = List.of();
	@Nullable
	private IMutation lastChoice;

	public AdvancedMutatronBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.ADVANCED_MUTATRON.type(), pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, AdvancedMutatronBlockEntity tile) {
		AbstractMutatronBlockEntity.serverTick(level, pos, state, tile);
	}

	@Override
	protected void onMutationsUpdated(List<IMutation> mutations, ItemStack primaryStack, ItemStack secondaryStack) {
		this.possibilities = List.copyOf(mutations);
		this.currentPrimary = primaryStack;
		this.currentSecondary = secondaryStack;

		if (this.lastChoice != null && this.possibilities.contains(this.lastChoice)) {
			setCurrentMutation(this.lastChoice, primaryStack, secondaryStack);
		} else {
			setCurrentMutation(null, primaryStack, secondaryStack);
		}
	}

	@Override
	public void setCurrentMutation(@Nullable IMutation mutation, ItemStack primary, ItemStack secondary) {
		super.setCurrentMutation(mutation, primary, secondary);
		if (mutation != null) {
			this.lastChoice = mutation;
		}
	}

	@Override
	public boolean hasWork() {
		boolean canWork = super.hasWork();
		boolean noSelection = getErrorLogic().setCondition(getCurrentMutation() == null, GendustryError.NO_SELECTION);
		return canWork && !noSelection;
	}

	@Override
	protected boolean hasMutation() {
		return !this.possibilities.isEmpty();
	}

	public List<IMutation> getPossibilities() {
		return this.possibilities;
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		trackPlayer(player);
		return new AdvancedMutatronMenu(containerId, playerInventory, this);
	}
}
