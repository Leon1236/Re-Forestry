package com.leon1236.reforestry.modules;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;

final class ModuleConfig {
    private static final Path PATH = FabricLoader.getInstance().getConfigDir()
            .resolve("reforestry").resolve("modules.properties");

    private ModuleConfig() {
    }

    static Map<Identifier, Boolean> loadOrCreate(List<IForestryModule> modules) {
        Map<Identifier, Boolean> existing = read();
        write(modules, existing);
        return read();
    }

    private static void write(List<IForestryModule> modules, Map<Identifier, Boolean> existing) {
        try {
            Files.createDirectories(PATH.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(PATH)) {
                writer.write("# Disabling these modules can greatly change how the mod functions.");
                writer.newLine();
                writer.write("# Your mileage may vary, please report any issues.");
                writer.newLine();
                writer.newLine();
                for (IForestryModule module : modules) {
                    ForestryModule info = module.getClass().getAnnotation(ForestryModule.class);
                    if (info != null) {
                        writer.write("# " + info.name());
                        writer.newLine();
                        if (!info.description().isEmpty()) {
                            writer.write("# " + info.description());
                            writer.newLine();
                        }
                    }
                    if (!module.getModuleDependencies().isEmpty()) {
                        writer.write("# Dependencies: " + module.getModuleDependencies());
                        writer.newLine();
                    }
                    if (module.isCore()) {
                        writer.write("# This module cannot be disabled.");
                        writer.newLine();
                    }
                    boolean enabled = module.isCore() || existing.getOrDefault(module.getId(), true);
                    writer.write(module.getId() + "=" + enabled);
                    writer.newLine();
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write " + PATH, e);
        }
    }

    private static Map<Identifier, Boolean> read() {
        Map<Identifier, Boolean> result = new HashMap<>();
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
                Identifier id = Identifier.parse(trimmed.substring(0, split).trim());
                boolean enabled = Boolean.parseBoolean(trimmed.substring(split + 1).trim());
                result.put(id, enabled);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read " + PATH, e);
        }
        return result;
    }
}
