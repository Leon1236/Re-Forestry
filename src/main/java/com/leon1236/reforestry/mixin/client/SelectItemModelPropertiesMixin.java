package com.leon1236.reforestry.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.client.TreeSpeciesSelectProperty;

@Mixin(SelectItemModelProperties.class)
public class SelectItemModelPropertiesMixin {
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void reforestry$bootstrap(CallbackInfo info) {
        SelectItemModelProperties.ID_MAPPER.put(ReForestry.id("tree_species"), TreeSpeciesSelectProperty.TYPE);
    }
}
