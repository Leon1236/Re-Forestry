package com.leon1236.reforestry.mixin.client;

import com.mojang.serialization.MapCodec;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.client.BeeBodyTintSource;
import com.leon1236.reforestry.apiculture.client.BeeOutlineTintSource;
import com.leon1236.reforestry.apiculture.client.BeeStripesTintSource;
import com.leon1236.reforestry.arboriculture.client.PollenTintSource;

@Mixin(ItemTintSources.class)
public class ItemTintSourcesMixin {
    @Shadow
    @Final
    private static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends ItemTintSource>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void reforestry$bootstrap(CallbackInfo info) {
        ID_MAPPER.put(ReForestry.id("bee_body"), BeeBodyTintSource.MAP_CODEC);
        ID_MAPPER.put(ReForestry.id("bee_stripes"), BeeStripesTintSource.MAP_CODEC);
        ID_MAPPER.put(ReForestry.id("bee_outline"), BeeOutlineTintSource.MAP_CODEC);
        ID_MAPPER.put(ReForestry.id("pollen"), PollenTintSource.MAP_CODEC);
    }
}
