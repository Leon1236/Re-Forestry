package com.leon1236.reforestry.modules.features;

import java.util.function.Consumer;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;

public class FeatureCreativeTab extends ModFeature {
    private final CreativeModeTab tab;

    public FeatureCreativeTab(Identifier moduleId, String name, Identifier registryId, Consumer<CreativeModeTab.Builder> builder) {
        super(moduleId, name);
        CreativeModeTab.Builder tabBuilder = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("itemGroup." + name));
        builder.accept(tabBuilder);
        this.tab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, registryId, tabBuilder.build());
    }

    public CreativeModeTab tab() {
        return tab;
    }
}
