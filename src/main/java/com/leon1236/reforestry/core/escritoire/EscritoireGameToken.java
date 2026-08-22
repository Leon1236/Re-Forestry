package com.leon1236.reforestry.core.escritoire;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.ReForestry;

public class EscritoireGameToken {
	public static final String NBT_TOKEN_SPECIES = "tokenSpecies";
	public static final String NBT_TOKEN_TYPE = "tokenSpeciesType";

	public static final Identifier OVERLAY_FAILED = ReForestry.id("textures/reforestry/atlas/gui/errors/errored.png");
	public static final Identifier OVERLAY_SELECTED = ReForestry.id("textures/reforestry/atlas/gui/errors/unknown.png");

	private enum State {
		UNREVEALED,
		PROBED,
		SELECTED,
		MATCHED,
		FAILED;

		public static final State[] VALUES = values();
	}

	@Nullable
	private Identifier tokenTypeId;
	@Nullable
	private Identifier tokenSpeciesId;
	private ItemStack tokenStack = ItemStack.EMPTY;
	private State state = State.UNREVEALED;

	public EscritoireGameToken(Identifier typeId, Identifier speciesId) {
		setTokenSpecies(typeId, speciesId);
	}

	public EscritoireGameToken(ValueInput input) {
		read(input);
	}

	public EscritoireGameToken(FriendlyByteBuf data) {
		readData(data);
	}

	private void setTokenSpecies(Identifier typeId, Identifier speciesId) {
		this.tokenTypeId = typeId;
		this.tokenSpeciesId = speciesId;
		this.tokenStack = EscritoireResearch.createTokenStack(typeId, speciesId);
	}

	public ItemStack getTokenStack() {
		return this.tokenStack;
	}

	public boolean isVisible() {
		return this.state != State.UNREVEALED;
	}

	public boolean isProbed() {
		return this.state == State.PROBED;
	}

	public boolean isMatched() {
		return this.state == State.MATCHED;
	}

	public boolean isSelected() {
		return this.state == State.SELECTED;
	}

	public void setFailed() {
		this.state = State.FAILED;
	}

	public void setProbed(boolean probed) {
		this.state = probed ? State.PROBED : State.UNREVEALED;
	}

	public void setSelected() {
		this.state = State.SELECTED;
	}

	public void setMatched() {
		this.state = State.MATCHED;
	}

	public int getTokenColour() {
		if (this.tokenTypeId == null || this.tokenSpeciesId == null || !isVisible()) {
			return 0xffffff;
		}
		int iconColor = EscritoireResearch.getEscritoireColor(this.tokenTypeId, this.tokenSpeciesId);
		if (this.state == State.MATCHED) {
			return multiplyRgb(iconColor, 0.7f);
		}
		return iconColor;
	}

	public Component getTooltip() {
		return !this.tokenStack.isEmpty() ? this.tokenStack.getHoverName() : Component.translatable("for.gui.unknown");
	}

	@Nullable
	public Identifier getOverlayToken() {
		return switch (this.state) {
			case FAILED -> OVERLAY_FAILED;
			case SELECTED -> OVERLAY_SELECTED;
			default -> null;
		};
	}

	public boolean matches(EscritoireGameToken other) {
		return ItemStack.matches(this.tokenStack, other.getTokenStack());
	}

	public void write(ValueOutput output) {
		output.putInt("state", this.state.ordinal());
		if (this.tokenSpeciesId != null && this.tokenTypeId != null) {
			output.putString(NBT_TOKEN_SPECIES, this.tokenSpeciesId.toString());
			output.putString(NBT_TOKEN_TYPE, this.tokenTypeId.toString());
		}
	}

	public void read(ValueInput input) {
		int stateOrdinal = input.getIntOr("state", State.UNREVEALED.ordinal());
		if (stateOrdinal >= 0 && stateOrdinal < State.VALUES.length) {
			this.state = State.VALUES[stateOrdinal];
		} else {
			this.state = State.UNREVEALED;
		}
		String tokenSpecies = input.getStringOr(NBT_TOKEN_SPECIES, "");
		String tokenType = input.getStringOr(NBT_TOKEN_TYPE, "");
		if (!tokenSpecies.isEmpty() && !tokenType.isEmpty()) {
			setTokenSpecies(Identifier.parse(tokenType), Identifier.parse(tokenSpecies));
		}
	}

	public void writeData(FriendlyByteBuf data) {
		data.writeEnum(this.state);
		if (this.tokenSpeciesId != null && this.tokenTypeId != null) {
			data.writeBoolean(true);
			data.writeIdentifier(this.tokenSpeciesId);
			data.writeIdentifier(this.tokenTypeId);
		} else {
			data.writeBoolean(false);
		}
	}

	public void readData(FriendlyByteBuf data) {
		this.state = data.readEnum(State.class);
		if (data.readBoolean()) {
			Identifier speciesId = data.readIdentifier();
			Identifier typeId = data.readIdentifier();
			setTokenSpecies(typeId, speciesId);
		}
	}

	private static int multiplyRgb(int colour, float factor) {
		int r = Math.min(255, (int) (((colour >> 16) & 0xff) * factor));
		int g = Math.min(255, (int) (((colour >> 8) & 0xff) * factor));
		int b = Math.min(255, (int) ((colour & 0xff) * factor));
		return (r << 16) | (g << 8) | b;
	}
}
