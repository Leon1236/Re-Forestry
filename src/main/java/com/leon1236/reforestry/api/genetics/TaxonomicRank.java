package com.leon1236.reforestry.api.genetics;

import java.util.List;
import java.util.Locale;

import com.mojang.serialization.Codec;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

public enum TaxonomicRank implements StringRepresentable {
	DOMAIN(0x777fff, true),
	KINGDOM(0x77c3ff),
	PHYLUM(0x77ffb6, true),
	CLASS(0x7bff77),
	ORDER(0xbeff77),
	FAMILY(0xfffd77),
	GENUS(0xffba77);

	public static final List<TaxonomicRank> VALUES = List.of(values());

	public static final Codec<TaxonomicRank> CODEC = StringRepresentable.fromEnum(TaxonomicRank::values);

	public static final StreamCodec<RegistryFriendlyByteBuf, TaxonomicRank> STREAM_CODEC = StreamCodec.of(
			(buf, rank) -> buf.writeVarInt(rank.ordinal()),
			buf -> VALUES.get(buf.readVarInt())
	);

	private final String serializedName = name().toLowerCase(Locale.ROOT);

	private final int colour;
	private final boolean droppable;

	TaxonomicRank(int colour) {
		this(colour, false);
	}

	TaxonomicRank(int colour, boolean droppable) {
		this.colour = colour;
		this.droppable = droppable;
	}

	public int getColour() {
		return colour;
	}

	public boolean isDroppable() {
		return droppable;
	}

	public TaxonomicRank next() {
		return VALUES.get(Mth.clamp(ordinal() + 1, 0, VALUES.size() - 1));
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}
}
