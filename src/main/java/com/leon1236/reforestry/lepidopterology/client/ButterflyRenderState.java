package com.leon1236.reforestry.lepidopterology.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class ButterflyRenderState extends LivingEntityRenderState {
	public float size = 0.75f;
	public boolean renderable;
	public Identifier texture = Identifier.withDefaultNamespace("missingno");
}
