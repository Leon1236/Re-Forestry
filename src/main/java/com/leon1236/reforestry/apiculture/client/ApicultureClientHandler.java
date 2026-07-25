package com.leon1236.reforestry.apiculture.client;

import java.util.List;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.apiculture.blocks.BlockHoneyComb;
import com.leon1236.reforestry.apiculture.features.ApicultureBlocks;
import com.leon1236.reforestry.apiculture.features.ApicultureMenuTypes;
import com.leon1236.reforestry.apiculture.features.ApicultureParticles;
import com.leon1236.reforestry.modules.features.FeatureBlock;

public class ApicultureClientHandler implements IClientModuleHandler {
    @Override
    public void registerClient() {
        MenuScreens.register(ApicultureMenuTypes.BEE_HOUSING.type(), ScreenBeeHousing::new);
        MenuScreens.register(ApicultureMenuTypes.ALVEARY.type(), ScreenAlveary::new);
        MenuScreens.register(ApicultureMenuTypes.ALVEARY_HYGROREGULATOR.type(), ScreenAlvearyHygroregulator::new);
        MenuScreens.register(ApicultureMenuTypes.ALVEARY_SIEVE.type(), ScreenAlvearySieve::new);
        MenuScreens.register(ApicultureMenuTypes.ALVEARY_SWARMER.type(), ScreenAlvearySwarmer::new);

        ParticleProviderRegistry.getInstance().register(ApicultureParticles.BEE_ROUND_TRIP.type(), BeeTravelParticle.Provider::new);
        ParticleProviderRegistry.getInstance().register(ApicultureParticles.BEE_EXPLORER.type(), BeeTravelParticle.Provider::new);

        Block[] combBlocks = ApicultureBlocks.BEE_COMB.getAll().values().stream()
                .map(FeatureBlock<BlockHoneyComb>::block)
                .toArray(Block[]::new);
        BlockColorRegistry.register(List.of(new BeeCombTintSource(false), new BeeCombTintSource(true)), combBlocks);
    }
}
