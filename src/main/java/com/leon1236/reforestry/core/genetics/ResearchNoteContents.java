package com.leon1236.reforestry.core.genetics;

import java.util.Optional;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.server.players.NameAndId;

public record ResearchNoteContents(
		Optional<NameAndId> researcher,
		Identifier typeId,
		Identifier parent0,
		Identifier parent1,
		Optional<Identifier> result
) {
	private static final StreamCodec<ByteBuf, NameAndId> NAME_AND_ID_STREAM_CODEC = StreamCodec.composite(
			UUIDUtil.STREAM_CODEC, NameAndId::id,
			ByteBufCodecs.PLAYER_NAME, NameAndId::name,
			NameAndId::new
	);

	public static final Codec<ResearchNoteContents> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			NameAndId.CODEC.optionalFieldOf("researcher").forGetter(ResearchNoteContents::researcher),
			Identifier.CODEC.fieldOf("type").forGetter(ResearchNoteContents::typeId),
			Identifier.CODEC.fieldOf("parent0").forGetter(ResearchNoteContents::parent0),
			Identifier.CODEC.fieldOf("parent1").forGetter(ResearchNoteContents::parent1),
			Identifier.CODEC.optionalFieldOf("result").forGetter(ResearchNoteContents::result)
	).apply(instance, ResearchNoteContents::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, ResearchNoteContents> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.optional(NAME_AND_ID_STREAM_CODEC), ResearchNoteContents::researcher,
			Identifier.STREAM_CODEC, ResearchNoteContents::typeId,
			Identifier.STREAM_CODEC, ResearchNoteContents::parent0,
			Identifier.STREAM_CODEC, ResearchNoteContents::parent1,
			ByteBufCodecs.optional(Identifier.STREAM_CODEC), ResearchNoteContents::result,
			ResearchNoteContents::new
	);
}
