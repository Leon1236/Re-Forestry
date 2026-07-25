package com.leon1236.reforestry.apiculture.client;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record BeeColorParticleOptions(ParticleType<BeeColorParticleOptions> type, int color) implements ParticleOptions {
    public static MapCodec<BeeColorParticleOptions> codec(ParticleType<BeeColorParticleOptions> type) {
        return Codec.INT.xmap(color -> new BeeColorParticleOptions(type, color), BeeColorParticleOptions::color)
                .fieldOf("color");
    }

    public static StreamCodec<RegistryFriendlyByteBuf, BeeColorParticleOptions> streamCodec(ParticleType<BeeColorParticleOptions> type) {
        return ByteBufCodecs.INT.map(color -> new BeeColorParticleOptions(type, color), BeeColorParticleOptions::color).cast();
    }

    @Override
    public ParticleType<BeeColorParticleOptions> getType() {
        return type;
    }
}
