package com.leon1236.reforestry.apiculture.features;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.client.BeeColorParticleOptions;
import com.leon1236.reforestry.modules.features.FeatureParticleType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ApicultureParticles {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("apiculture"));

    public static final FeatureParticleType<BeeColorParticleOptions> BEE_ROUND_TRIP = REGISTRY.particleType("bee_round_trip_particle",
            FabricParticleTypes.complex(BeeColorParticleOptions::codec, BeeColorParticleOptions::streamCodec));
    public static final FeatureParticleType<BeeColorParticleOptions> BEE_EXPLORER = REGISTRY.particleType("bee_explore_particle",
            FabricParticleTypes.complex(BeeColorParticleOptions::codec, BeeColorParticleOptions::streamCodec));

    public static void init() {
    }
}
