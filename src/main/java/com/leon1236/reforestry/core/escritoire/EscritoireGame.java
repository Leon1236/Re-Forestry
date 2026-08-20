package com.leon1236.reforestry.core.escritoire;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.capability.IndividualItems;

public class EscritoireGame {
	private static final RandomSource RAND = RandomSource.create();

	public static final int BOUNTY_MAX = 16;

	public enum Status {
		EMPTY,
		PLAYING,
		FAILURE,
		SUCCESS;

		public static final Status[] VALUES = values();
	}

	private EscritoireGameBoard gameBoard = new EscritoireGameBoard();
	private long lastUpdate;
	private int bountyLevel = BOUNTY_MAX;
	private Status status = Status.EMPTY;

	@Nullable
	public EscritoireGameToken getToken(int index) {
		return this.gameBoard.getToken(index);
	}

	public Status getStatus() {
		return this.status;
	}

	public long getLastUpdate() {
		return this.lastUpdate;
	}

	public int getBountyLevel() {
		return this.bountyLevel;
	}

	public void write(ValueOutput output) {
		output.putInt("bountyLevel", this.bountyLevel);
		output.putLong("lastUpdate", this.lastUpdate);
		output.putInt("Status", this.status.ordinal());
		this.gameBoard.write(output);
	}

	public void read(ValueInput input) {
		this.bountyLevel = input.getIntOr("bountyLevel", BOUNTY_MAX);
		this.lastUpdate = input.getLongOr("lastUpdate", 0L);
		int statusOrdinal = input.getIntOr("Status", Status.EMPTY.ordinal());
		if (statusOrdinal >= 0 && statusOrdinal < Status.VALUES.length) {
			this.status = Status.VALUES[statusOrdinal];
		} else {
			this.status = Status.EMPTY;
		}
		this.gameBoard = new EscritoireGameBoard();
		this.gameBoard.read(input);
		this.lastUpdate = System.currentTimeMillis();
	}

	public void writeData(FriendlyByteBuf data) {
		data.writeInt(this.bountyLevel);
		this.gameBoard.writeData(data);
		data.writeEnum(this.status);
	}

	public void readData(FriendlyByteBuf data) {
		this.bountyLevel = data.readInt();
		this.gameBoard.readData(data);
		this.status = data.readEnum(Status.class);
	}

	public void initialize(ItemStack specimen) {
		reset();
		if (this.gameBoard.initialize(specimen)) {
			this.status = Status.PLAYING;
			this.bountyLevel = BOUNTY_MAX;
			this.lastUpdate = System.currentTimeMillis();
		}
	}

	public void probe(ItemStack specimen, Container inventory, int startSlot, int slotCount) {
		if (this.status != Status.PLAYING) {
			return;
		}
		IGenome genome = IndividualItems.getGenome(specimen);
		if (genome == null) {
			return;
		}
		if (this.bountyLevel > 1) {
			this.bountyLevel--;
		}
		this.gameBoard.hideProbedTokens();

		int revealCount = getSampleSize(slotCount);
		for (int i = 0; i < revealCount; i++) {
			ItemStack sample = inventory.removeItem(startSlot + i, 1);
			if (!sample.isEmpty() && RAND.nextFloat() < EscritoireResearch.getResearchSuitability(specimen, sample)) {
				this.gameBoard.probe();
			}
		}
		this.lastUpdate = System.currentTimeMillis();
	}

	public void reset() {
		this.bountyLevel = BOUNTY_MAX;
		this.gameBoard.reset();
		this.status = Status.EMPTY;
		this.lastUpdate = System.currentTimeMillis();
	}

	public void choose(int tokenIndex) {
		if (this.status != Status.PLAYING) {
			return;
		}
		EscritoireGameToken token = this.gameBoard.getToken(tokenIndex);
		if (token != null) {
			this.status = this.gameBoard.choose(token);
			this.lastUpdate = System.currentTimeMillis();
		}
	}

	public int getSampleSize(int slotCount) {
		if (this.status == Status.EMPTY) {
			return 0;
		}
		int samples = this.gameBoard.getTokenCount() / 4;
		samples = Math.max(samples, 2);
		return Math.min(samples, slotCount);
	}
}
