package com.leon1236.reforestry.api.genetics;

import net.minecraft.resources.Identifier;

public final class GeneticTranslationKeys {
	private GeneticTranslationKeys() {
	}

	public static String createTranslationKey(String type, Identifier typeId, Identifier objectId) {
		StringBuilder translationKey = new StringBuilder(type);
		if (!type.isEmpty()) {
			translationKey.append('.');
		}
		translationKey.append(typeId.getNamespace());
		translationKey.append('.');
		translationKey.append(typeId.getPath());
		translationKey.append('.');

		if (objectId.getNamespace().equals(typeId.getNamespace())) {
			translationKey.append(objectId.getPath());
		} else {
			translationKey.append(objectId.getNamespace());
			translationKey.append('.');
			translationKey.append(objectId.getPath());
		}

		return translationKey.toString();
	}
}
