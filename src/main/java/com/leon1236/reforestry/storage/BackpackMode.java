package com.leon1236.reforestry.storage;

import java.util.Locale;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

public enum BackpackMode implements StringRepresentable {
	NEUTRAL(null),
	LOCKED("for.storage.backpack.mode.locked"),
	RECEIVE("for.storage.backpack.mode.receiving"),
	RESUPPLY("for.storage.backpack.mode.resupply");

	public static final BackpackMode[] VALUES = values();
	public static final Codec<BackpackMode> CODEC = StringRepresentable.fromEnum(BackpackMode::values);
	public static final StreamCodec<ByteBuf, BackpackMode> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);

	@Nullable
	private final String translationKey;

	BackpackMode(@Nullable String translationKey) {
		this.translationKey = translationKey;
	}

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ENGLISH);
	}

	@Nullable
	public String getTranslationKey() {
		return this.translationKey;
	}
}
