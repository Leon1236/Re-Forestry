package com.leon1236.reforestry.api.plugin;

import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.client.plugin.IClientRegistration;

public interface IForestryPlugin {
    Identifier id();

    default boolean shouldLoad() {
        return true;
    }

    default void registerGenetics(IGeneticRegistration registration) {
    }

    default void registerApiculture(IApicultureRegistration registration) {
    }

    default void registerArboriculture(IArboricultureRegistration registration) {
    }

    default void registerLepidopterology(ILepidopterologyRegistration registration) {
    }

    default void registerCircuits(ICircuitRegistration registration) {
    }

    default void registerErrors(IErrorRegistration registration) {
    }

    default void registerPollen(IPollenRegistration registration) {
    }

    default void registerClient(Consumer<Consumer<IClientRegistration>> registrar) {
    }
}
