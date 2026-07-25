package com.leon1236.reforestry.modules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.api.modules.IModuleManager;

public final class ModuleManager implements IModuleManager {
    public static final ModuleManager INSTANCE = new ModuleManager();

    private final LinkedHashMap<Identifier, IForestryModule> loadedModules = new LinkedHashMap<>();

    private ModuleManager() {
    }

    public void load(List<IForestryModule> modules) {
        Map<Identifier, IForestryModule> byId = new LinkedHashMap<>();
        for (IForestryModule module : modules) {
            byId.put(module.getId(), module);
        }

        Map<Identifier, Boolean> config = ModuleConfig.loadOrCreate(modules);

        Set<Identifier> enabled = new LinkedHashSet<>();
        for (IForestryModule module : modules) {
            if (module.isCore() || config.getOrDefault(module.getId(), true)) {
                enabled.add(module.getId());
            }
        }

        boolean changed;
        do {
            changed = false;
            for (Identifier id : new ArrayList<>(enabled)) {
                IForestryModule module = byId.get(id);
                if (!enabled.containsAll(module.getModuleDependencies())) {
                    enabled.remove(id);
                    changed = true;
                    ReForestry.LOGGER.warn("Module {} is missing dependencies, disabling it", id);
                }
            }
        } while (changed);

        for (Identifier id : enabled) {
            IForestryModule module = byId.get(id);
            for (String modId : module.getModDependencies()) {
                if (!FabricLoader.getInstance().isModLoaded(modId)) {
                    ReForestry.LOGGER.warn("Module {} is missing mod dependencies: {}", id, module.getModDependencies());
                }
            }
        }

        List<IForestryModule> loadOrder = new ArrayList<>();
        Set<Identifier> loaded = new HashSet<>();
        List<IForestryModule> remaining = new ArrayList<>();
        for (Identifier id : enabled) {
            remaining.add(byId.get(id));
        }
        do {
            changed = false;
            for (int i = 0; i < remaining.size(); i++) {
                IForestryModule module = remaining.get(i);
                if (loaded.containsAll(module.getModuleDependencies())) {
                    remaining.remove(i);
                    loaded.add(module.getId());
                    loadOrder.add(module);
                    changed = true;
                    break;
                }
            }
        } while (changed);

        for (IForestryModule module : loadOrder) {
            ReForestry.LOGGER.info("Loading reforestry module: {}", module.getId());
            module.init();
            loadedModules.put(module.getId(), module);
        }
    }

    @Override
    public Collection<IForestryModule> getLoadedModules() {
        return Collections.unmodifiableCollection(loadedModules.values());
    }

    @Override
    public boolean isModuleLoaded(Identifier id) {
        return loadedModules.containsKey(id);
    }
}
