package com.leon1236.reforestry.core.escritoire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.genetics.capability.IndividualItems;

public class EscritoireGameBoard {
	private static final RandomSource RAND = RandomSource.create();
	public static final int TOKEN_COUNT_MAX = 22;
	public static final int TOKEN_COUNT_MIN = 6;
	public static final String NBT_GAME_TOKENS = "GameTokens";

	private final List<EscritoireGameToken> gameTokens = new ArrayList<>(TOKEN_COUNT_MAX);
	private int tokenCount;

	public EscritoireGameBoard() {
	}

	public boolean initialize(ItemStack specimen) {
		Identifier typeId = IndividualItems.getSpeciesTypeId(specimen);
		if (typeId == null || !EscritoireResearch.isSupportedSpecimen(specimen)) {
			return false;
		}

		this.tokenCount = EscritoireResearch.getTokenCount(specimen);
		if (this.tokenCount < TOKEN_COUNT_MIN) {
			return false;
		}

		this.gameTokens.clear();
		for (int i = 0; i < this.tokenCount / 2; i++) {
			Identifier speciesId = EscritoireResearch.pickRandomSpeciesId(typeId, RAND);
			if (speciesId == null) {
				reset();
				return false;
			}
			this.gameTokens.add(new EscritoireGameToken(typeId, speciesId));
			this.gameTokens.add(new EscritoireGameToken(typeId, speciesId));
		}
		Collections.shuffle(this.gameTokens);
		return true;
	}

	@Nullable
	public EscritoireGameToken getToken(int index) {
		if (index >= this.tokenCount || index >= this.gameTokens.size()) {
			return null;
		}
		return this.gameTokens.get(index);
	}

	public int getTokenCount() {
		return this.tokenCount;
	}

	public void hideProbedTokens() {
		for (EscritoireGameToken token : this.gameTokens) {
			if (token.isProbed()) {
				token.setProbed(false);
			}
		}
	}

	private List<EscritoireGameToken> getUnrevealedTokens() {
		List<EscritoireGameToken> unrevealed = new ArrayList<>();
		for (EscritoireGameToken token : this.gameTokens) {
			if (!token.isVisible()) {
				unrevealed.add(token);
			}
		}
		return unrevealed;
	}

	@Nullable
	private EscritoireGameToken getSelected() {
		for (EscritoireGameToken token : this.gameTokens) {
			if (token.isSelected()) {
				return token;
			}
		}
		return null;
	}

	private boolean isBoardCleared() {
		for (EscritoireGameToken token : this.gameTokens) {
			if (!token.isMatched()) {
				return false;
			}
		}
		return true;
	}

	public void probe() {
		List<EscritoireGameToken> tokens = getUnrevealedTokens();
		if (tokens.isEmpty()) {
			return;
		}
		EscritoireGameToken token = tokens.get(RAND.nextInt(tokens.size()));
		token.setProbed(true);
	}

	public EscritoireGame.Status choose(EscritoireGameToken token) {
		EscritoireGame.Status status = EscritoireGame.Status.PLAYING;
		if (token.isMatched() || token.isSelected()) {
			return status;
		}

		EscritoireGameToken selected = getSelected();
		if (selected == null) {
			token.setSelected();
			hideProbedTokens();
		} else if (token.matches(selected)) {
			selected.setMatched();
			token.setMatched();
			if (isBoardCleared()) {
				status = EscritoireGame.Status.SUCCESS;
			}
			hideProbedTokens();
		} else {
			token.setFailed();
			selected.setFailed();
			status = EscritoireGame.Status.FAILURE;
		}
		return status;
	}

	public void reset() {
		this.gameTokens.clear();
		this.tokenCount = 0;
	}

	public void write(ValueOutput output) {
		output.putInt("TokenCount", this.tokenCount);
		if (this.tokenCount <= 0) {
			return;
		}
		ValueOutput.ValueOutputList list = output.childrenList(NBT_GAME_TOKENS);
		for (int i = 0; i < this.tokenCount && i < this.gameTokens.size(); i++) {
			EscritoireGameToken token = this.gameTokens.get(i);
			ValueOutput child = list.addChild();
			child.putByte("Slot", (byte) i);
			token.write(child);
		}
	}

	public void read(ValueInput input) {
		this.gameTokens.clear();
		this.tokenCount = input.getIntOr("TokenCount", 0);
		EscritoireGameToken[] tokens = new EscritoireGameToken[Math.min(this.tokenCount, TOKEN_COUNT_MAX)];
		int loaded = 0;
		for (ValueInput child : input.childrenListOrEmpty(NBT_GAME_TOKENS)) {
			int index = child.getByteOr("Slot", (byte) -1);
			if (index < 0 || index >= tokens.length) {
				continue;
			}
			tokens[index] = new EscritoireGameToken(child);
			loaded++;
		}
		if (loaded > 0) {
			this.tokenCount = Math.min(this.tokenCount, tokens.length);
			for (int i = 0; i < this.tokenCount; i++) {
				if (tokens[i] != null) {
					this.gameTokens.add(tokens[i]);
				}
			}
			this.tokenCount = this.gameTokens.size();
		} else {
			this.tokenCount = 0;
		}
	}

	public void writeData(FriendlyByteBuf data) {
		data.writeVarInt(this.tokenCount);
		for (int i = 0; i < this.tokenCount && i < this.gameTokens.size(); i++) {
			this.gameTokens.get(i).writeData(data);
		}
	}

	public void readData(FriendlyByteBuf data) {
		this.tokenCount = data.readVarInt();
		this.gameTokens.clear();
		for (int i = 0; i < this.tokenCount; i++) {
			this.gameTokens.add(new EscritoireGameToken(data));
		}
	}
}
