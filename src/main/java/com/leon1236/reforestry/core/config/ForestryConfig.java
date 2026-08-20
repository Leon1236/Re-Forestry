package com.leon1236.reforestry.core.config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import net.fabricmc.loader.api.FabricLoader;

public final class ForestryConfig {
    private static final Path PATH = FabricLoader.getInstance().getConfigDir()
            .resolve("reforestry").resolve("server.properties");

    private static double treesSpawnNaturally = 1.0;
    private static int charcoalAmountBase = 8;
    private static int charcoalWallCheckRange = 16;
    private static boolean enableBackpackResupply = true;
    private static boolean pollinateVanillaLeaves = true;

    private ForestryConfig() {
    }

    public static double treesSpawnNaturally() {
        return treesSpawnNaturally;
    }

    public static int charcoalAmountBase() {
        return charcoalAmountBase;
    }

    public static int charcoalWallCheckRange() {
        return charcoalWallCheckRange;
    }

    public static boolean enableBackpackResupply() {
        return enableBackpackResupply;
    }

    public static boolean pollinateVanillaLeaves() {
        return pollinateVanillaLeaves;
    }

    public static void init() {
        Map<String, String> existing = read();
        write(existing);
        Map<String, String> loaded = read();
        treesSpawnNaturally = parseDouble(loaded.get("trees.tree_spawn_chance_modifier"), 1.0);
        if (treesSpawnNaturally < 0.0) {
            treesSpawnNaturally = 0.0;
        }
        charcoalAmountBase = parseInt(loaded.get("charcoal.amount_base"), 8);
        if (charcoalAmountBase < 0) {
            charcoalAmountBase = 0;
        }
        charcoalWallCheckRange = parseInt(loaded.get("charcoal.wall_check_range"), 16);
        if (charcoalWallCheckRange < 1) {
            charcoalWallCheckRange = 1;
        }
        enableBackpackResupply = parseBoolean(loaded.get("storage.enable_backpack_resupply"), true);
        pollinateVanillaLeaves = parseBoolean(loaded.get("bees.pollinate_vanilla_leaves"), true);
    }

    private static void write(Map<String, String> existing) {
        try {
            Files.createDirectories(PATH.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(PATH)) {
                writer.write("# Re-Forestry server options");
                writer.newLine();
                writer.newLine();
                writer.write("# Multiplies the chance of a Forestry tree spawning in the wild. Set to 0 to disable.");
                writer.newLine();
                writer.write("# CE defaults this to 0.0; Re-Forestry defaults to 1.0 so wild trees appear.");
                writer.newLine();
                String spawnValue = existing.getOrDefault("trees.tree_spawn_chance_modifier", "1.0");
                writer.write("trees.tree_spawn_chance_modifier=" + spawnValue);
                writer.newLine();
                writer.newLine();
                writer.write("# Base charcoal drop from a cooked log pile when walls are unregistered.");
                writer.newLine();
                String baseValue = existing.getOrDefault("charcoal.amount_base", "8");
                writer.write("charcoal.amount_base=" + baseValue);
                writer.newLine();
                writer.newLine();
                writer.write("# How many blocks outward a log pile scans for a registered charcoal pit wall.");
                writer.newLine();
                String rangeValue = existing.getOrDefault("charcoal.wall_check_range", "16");
                writer.write("charcoal.wall_check_range=" + rangeValue);
                writer.newLine();
                writer.newLine();
                writer.write("# When true, backpacks in Resupply mode top off matching stacks in the player inventory.");
                writer.newLine();
                String resupplyValue = existing.getOrDefault("storage.enable_backpack_resupply", "true");
                writer.write("storage.enable_backpack_resupply=" + resupplyValue);
                writer.newLine();
                writer.newLine();
                writer.write("# Whether bees and player-held pollen can convert vanilla / default Forestry leaves into genetic leaves.");
                writer.newLine();
                String pollinateValue = existing.getOrDefault("bees.pollinate_vanilla_leaves", "true");
                writer.write("bees.pollinate_vanilla_leaves=" + pollinateValue);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write " + PATH, e);
        }
    }

    private static Map<String, String> read() {
        Map<String, String> result = new HashMap<>();
        if (!Files.exists(PATH)) {
            return result;
        }
        try {
            for (String line : Files.readAllLines(PATH)) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                int split = trimmed.indexOf('=');
                if (split < 0) {
                    continue;
                }
                result.put(trimmed.substring(0, split).trim(), trimmed.substring(split + 1).trim());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read " + PATH, e);
        }
        return result;
    }

    private static double parseDouble(String raw, double fallback) {
        if (raw == null || raw.isEmpty()) {
            return fallback;
        }
        try {
            return Double.parseDouble(raw);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    private static boolean parseBoolean(String raw, boolean fallback) {
        if (raw == null || raw.isEmpty()) {
            return fallback;
        }
        if ("true".equalsIgnoreCase(raw) || "yes".equalsIgnoreCase(raw) || "1".equals(raw)) {
            return true;
        }
        if ("false".equalsIgnoreCase(raw) || "no".equalsIgnoreCase(raw) || "0".equals(raw)) {
            return false;
        }
        return fallback;
    }

    private static int parseInt(String raw, int fallback) {
        if (raw == null || raw.isEmpty()) {
            return fallback;
        }
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}
