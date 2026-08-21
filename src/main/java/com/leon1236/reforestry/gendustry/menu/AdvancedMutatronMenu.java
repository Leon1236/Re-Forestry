package com.leon1236.reforestry.gendustry.menu;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.capability.IIndividualHandlerItem;
import com.leon1236.reforestry.gendustry.blockentity.AbstractMutatronBlockEntity;
import com.leon1236.reforestry.gendustry.blockentity.AdvancedMutatronBlockEntity;
import com.leon1236.reforestry.gendustry.features.GMenus;

public class AdvancedMutatronMenu extends AbstractMutatronMenu<AdvancedMutatronBlockEntity> {
	public static final int BUTTON_CYCLE_LEFT = 10;
	public static final int BUTTON_CYCLE_RIGHT = 11;
	public static final int CHOICE_CLICKED = 12;

	public Slot[] choices;
	private final SimpleContainerData data;

	private List<IMutation> possibilities = List.of();
	private List<ItemStack> icons = List.of();

	@Nullable
	private Runnable dataListener;

	public AdvancedMutatronMenu(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, AdvancedMutatronBlockEntity.class));
	}

	public AdvancedMutatronMenu(int containerId, Inventory playerInventory, AdvancedMutatronBlockEntity tile) {
		super(GMenus.ADVANCED_MUTATRON.type(), containerId, playerInventory, tile);
		this.data = new SimpleContainerData(3);
		addDataSlots(this.data);
	}

	@Override
	protected void addMachineSlots(AdvancedMutatronBlockEntity tile) {
		super.addMachineSlots(tile);
		this.choices = new ChoiceSlot[4];
		for (int i = 0; i < 4; i++) {
			ChoiceSlot choice = new ChoiceSlot(i, 63 + i * 16, 71);
			addSlot(choice);
			this.choices[i] = choice;
		}
	}

	@Override
	public void broadcastChanges() {
		List<IMutation> newPossibilities = this.tile.getPossibilities();
		if (this.possibilities != newPossibilities) {
			this.possibilities = newPossibilities;

			IMutation current = this.tile.getCurrentMutation();
			setSelected(current == null ? -1 : newPossibilities.indexOf(current));
			setOffset(0);

			ISpeciesType<?, ?> speciesType = null;
			ItemStack primary = this.tile.getItem(AbstractMutatronBlockEntity.SLOT_PRIMARY);
			var individual = IIndividualHandlerItem.getIndividual(primary);
			if (individual != null) {
				speciesType = individual.getType();
			}

			ISpeciesType<?, ?> iconType = speciesType;
			this.icons = iconType == null
					? List.of()
					: newPossibilities.stream()
							.map(mutation -> AbstractMutatronBlockEntity.createMutationIcon(mutation, iconType))
							.toList();
			refreshChoiceSlots();
			setPossibilityCount(this.possibilities.size());
		}

		super.broadcastChanges();
	}

	private void refreshChoiceSlots() {
		int offset = getOffset();
		for (int i = 0; i < 4; i++) {
			int index = offset + i;
			if (index < this.icons.size()) {
				this.choices[i].set(this.icons.get(index));
			} else {
				this.choices[i].set(ItemStack.EMPTY);
			}
		}
	}

	@Override
	public boolean clickMenuButton(Player player, int id) {
		if (id == BUTTON_CYCLE_LEFT || id == BUTTON_CYCLE_RIGHT
				|| (id >= CHOICE_CLICKED && id < CHOICE_CLICKED + 4)) {
			int offset = getOffset();
			if (getPossibilityCount() > 4) {
				if (id == BUTTON_CYCLE_LEFT) {
					if (offset > 0) {
						setOffset(offset - 1);
						refreshChoiceSlots();
					}
				} else if (id == BUTTON_CYCLE_RIGHT) {
					if (offset + 4 < getPossibilityCount()) {
						setOffset(offset + 1);
						refreshChoiceSlots();
					}
				}
			}
			if (id >= CHOICE_CLICKED && id < CHOICE_CLICKED + 4) {
				setSelected(getOffset() + (id - CHOICE_CLICKED));
			}
			return true;
		}
		return super.clickMenuButton(player, id);
	}

	public int getPossibilityCount() {
		return this.data.get(0);
	}

	public void setPossibilityCount(int possibilityCount) {
		this.data.set(0, possibilityCount);
	}

	public int getOffset() {
		return this.data.get(1);
	}

	public void setOffset(int offset) {
		this.data.set(1, offset);
	}

	public int getSelected() {
		return this.data.get(2);
	}

	public void setSelected(int selected) {
		this.data.set(2, selected);

		if (selected >= 0 && this.tile.getLevel() != null && !this.tile.getLevel().isClientSide()) {
			List<IMutation> options = this.tile.getPossibilities();
			if (selected < options.size()) {
				this.tile.setCurrentMutation(
						options.get(selected),
						this.tile.getItem(AbstractMutatronBlockEntity.SLOT_PRIMARY),
						this.tile.getItem(AbstractMutatronBlockEntity.SLOT_SECONDARY));
			}
		}
	}

	public void setDataListener(Runnable listener) {
		this.dataListener = listener;
	}

	@Override
	public void setData(int id, int value) {
		super.setData(id, value);
		if (this.dataListener != null) {
			this.dataListener.run();
		}
	}

	public static class ChoiceSlot extends Slot {
		public final int choiceIndex;

		public ChoiceSlot(int choiceIndex, int x, int y) {
			super(new SimpleContainer(1), 0, x, y);
			this.choiceIndex = choiceIndex;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		public boolean mayPickup(Player player) {
			return false;
		}

		@Override
		public boolean isFake() {
			return true;
		}

		@Override
		public boolean isHighlightable() {
			return false;
		}
	}
}
