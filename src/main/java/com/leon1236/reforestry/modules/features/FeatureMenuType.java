package com.leon1236.reforestry.modules.features;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class FeatureMenuType<T extends AbstractContainerMenu, D> extends ModFeature {
    private final MenuType<T> type;

    public FeatureMenuType(Identifier moduleId, String name, Identifier registryId,
                            ExtendedMenuType.ExtendedFactory<T, D> factory,
                            StreamCodec<? super RegistryFriendlyByteBuf, D> streamCodec) {
        super(moduleId, name);
        this.type = Registry.register(BuiltInRegistries.MENU, registryId, new ExtendedMenuType<>(factory, streamCodec));
    }

    public MenuType<T> type() {
        return type;
    }
}
