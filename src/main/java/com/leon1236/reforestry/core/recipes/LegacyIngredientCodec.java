package com.leon1236.reforestry.core.recipes;

import java.util.List;
import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;

import net.minecraft.world.item.crafting.Ingredient;

public final class LegacyIngredientCodec {
	public static final Codec<Ingredient> CODEC = new Codec<>() {
		@Override
		public <T> DataResult<Pair<Ingredient, T>> decode(DynamicOps<T> ops, T input) {
			DataResult<Pair<Ingredient, T>> direct = Ingredient.CODEC.decode(ops, input);
			if (direct.result().isPresent()) {
				return direct;
			}
			JsonElement json = ops.convertTo(JsonOps.INSTANCE, input);
			JsonElement normalized = normalize(json);
			if (normalized.equals(json)) {
				return direct;
			}
			T converted = JsonOps.INSTANCE.convertTo(ops, normalized);
			return Ingredient.CODEC.decode(ops, converted);
		}

		@Override
		public <T> DataResult<T> encode(Ingredient input, DynamicOps<T> ops, T prefix) {
			return Ingredient.CODEC.encode(input, ops, prefix);
		}
	};

	public static final Codec<Optional<Ingredient>> OPTIONAL = new Codec<>() {
		@Override
		public <T> DataResult<Pair<Optional<Ingredient>, T>> decode(DynamicOps<T> ops, T input) {
			JsonElement json = ops.convertTo(JsonOps.INSTANCE, input);
			if (json == null || json.isJsonNull() || (json.isJsonArray() && json.getAsJsonArray().isEmpty())) {
				return DataResult.success(Pair.of(Optional.empty(), input));
			}
			return CODEC.decode(ops, input).map(pair -> Pair.of(Optional.of(pair.getFirst()), pair.getSecond()));
		}

		@Override
		public <T> DataResult<T> encode(Optional<Ingredient> input, DynamicOps<T> ops, T prefix) {
			if (input.isEmpty()) {
				return DataResult.success(ops.createList(java.util.stream.Stream.empty()));
			}
			return CODEC.encode(input.get(), ops, prefix);
		}
	};

	private LegacyIngredientCodec() {
	}

	private static JsonElement normalize(JsonElement json) {
		if (json == null || json.isJsonNull()) {
			return json;
		}
		if (json.isJsonPrimitive()) {
			return json;
		}
		if (json.isJsonArray()) {
			JsonArray out = new JsonArray();
			for (JsonElement element : json.getAsJsonArray()) {
				out.add(normalize(element));
			}
			return out;
		}
		if (json.isJsonObject()) {
			JsonObject object = json.getAsJsonObject();
			if (object.has("item") && object.get("item").isJsonPrimitive()) {
				return new JsonPrimitive(object.get("item").getAsString());
			}
			if (object.has("tag") && object.get("tag").isJsonPrimitive()) {
				String tag = object.get("tag").getAsString();
				return new JsonPrimitive(tag.startsWith("#") ? tag : "#" + tag);
			}
			if (object.has("ingredient")) {
				return normalize(object.get("ingredient"));
			}
		}
		return json;
	}

	public static Codec<List<Ingredient>> listCodec() {
		return CODEC.listOf();
	}
}
