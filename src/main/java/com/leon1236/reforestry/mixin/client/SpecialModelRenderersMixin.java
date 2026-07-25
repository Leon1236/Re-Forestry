package com.leon1236.reforestry.mixin.client;

import com.mojang.serialization.MapCodec;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.client.MachineSpecialRenderer;
import com.leon1236.reforestry.core.client.MillSpecialRenderer;

@Mixin(SpecialModelRenderers.class)
public class SpecialModelRenderersMixin {
	@Shadow
	@Final
	private static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends SpecialModelRenderer.Unbaked<?>>> ID_MAPPER;

	@Inject(method = "bootstrap", at = @At("TAIL"))
	private static void reforestry$bootstrap(CallbackInfo info) {
		ID_MAPPER.put(ReForestry.id("machine"), MachineSpecialRenderer.Unbaked.MAP_CODEC);
		ID_MAPPER.put(ReForestry.id("mill"), MillSpecialRenderer.Unbaked.MAP_CODEC);
	}
}
