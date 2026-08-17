package com.leon1236.reforestry.core.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.leon1236.reforestry.ReForestry;

public final class ForestryHints {
	private static final Map<String, List<String>> HINTS = load();

	private ForestryHints() {
	}

	public static List<String> get(String key) {
		return HINTS.getOrDefault(key, List.of());
	}

	private static Map<String, List<String>> load() {
		Properties properties = new Properties();
		try (InputStream stream = ForestryHints.class.getResourceAsStream("/data/reforestry/hints.properties")) {
			if (stream == null) {
				ReForestry.LOGGER.warn("Missing hints.properties");
				return Map.of();
			}
			properties.load(stream);
		} catch (IOException e) {
			ReForestry.LOGGER.error("Failed to load hints.properties", e);
			return Map.of();
		}

		Map<String, List<String>> map = new HashMap<>();
		for (String key : properties.stringPropertyNames()) {
			String list = properties.getProperty(key, "");
			if (list.isEmpty()) {
				continue;
			}
			List<String> hints = new ArrayList<>();
			for (String hint : list.split(";+")) {
				String trimmed = hint.trim();
				if (!trimmed.isEmpty()) {
					hints.add(trimmed);
				}
			}
			if (!hints.isEmpty()) {
				map.put(key, List.copyOf(hints));
			}
		}
		return Collections.unmodifiableMap(map);
	}
}
