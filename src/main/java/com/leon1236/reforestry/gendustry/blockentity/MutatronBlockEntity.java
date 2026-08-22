package com.leon1236.reforestry.gendustry.blockentity;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.gendustry.features.GBlockEntities;
import com.leon1236.reforestry.gendustry.menu.MutatronMenu;

public class MutatronBlockEntity extends AbstractMutatronBlockEntity {
	public MutatronBlockEntity(BlockPos pos, BlockState state) {
		super(GBlockEntities.MUTATRON.type(), pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, MutatronBlockEntity tile) {
		AbstractMutatronBlockEntity.serverTick(level, pos, state, tile);
	}

	@Override
	protected void onMutationsUpdated(List<IMutation> mutations, ItemStack primaryStack, ItemStack secondaryStack) {
		if (mutations.isEmpty() || this.level == null) {
			setCurrentMutation(null, primaryStack, secondaryStack);
			return;
		}
		int index = this.level.getRandom().nextInt(mutations.size());
		setCurrentMutation(mutations.get(index), primaryStack, secondaryStack);
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		trackPlayer(player);
		return new MutatronMenu(containerId, playerInventory, this);
	}
}
