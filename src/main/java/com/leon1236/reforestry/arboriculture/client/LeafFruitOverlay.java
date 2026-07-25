package com.leon1236.reforestry.arboriculture.client;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.fabricmc.fabric.api.client.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.client.renderer.v1.mesh.QuadEmitter;

import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.sprite.Material;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.tiles.LeafRenderData;

/**
 * The ripening-fruit overlay drawn on top of fruit-bearing leaves: 4 small inset
 * billboards (one per horizontal face) sampling the fruit's sprite and tinted with
 * the already ripeness-lerped color from {@link LeafRenderData#fruitColor()}.
 */
public final class LeafFruitOverlay {
    // The 5 real shared fruit sprite sheets staged under textures/block/leaves/,
    // matching DefaultFruits.java's own (private) POMES/NUTS/BERRIES/CITRUS/PLUMS constants.
    private static final Identifier[] SPRITES = {
            ReForestry.id("block/leaves/fruits.pomes"),
            ReForestry.id("block/leaves/fruits.nuts"),
            ReForestry.id("block/leaves/fruits.berries"),
            ReForestry.id("block/leaves/fruits.citrus"),
            ReForestry.id("block/leaves/fruits.plums"),
    };

    private LeafFruitOverlay() {
    }

    public static SpeciesDispatchModel.Overlay bake(ModelBaker baker) {
        ImmutableMap.Builder<Identifier, Material.Baked> builder = ImmutableMap.builder();
        for (Identifier sprite : SPRITES) {
            builder.put(sprite, baker.materials().get(new Material(sprite), () -> "reforestry:leaf_fruit_overlay"));
        }
        Map<Identifier, Material.Baked> materials = builder.build();
        return (emitter, data) -> emit(emitter, materials, data);
    }

    private static void emit(QuadEmitter emitter, Map<Identifier, Material.Baked> materials, LeafRenderData data) {
        Identifier spriteId = data.fruitSprite();
        Material.Baked material = spriteId == null ? null : materials.get(spriteId);
        if (material == null) {
            return;
        }
        int color = ARGB.opaque(data.fruitColor());
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            emitter.square(dir, 5f / 16f, 5f / 16f, 11f / 16f, 11f / 16f, 1f / 16f);
            emitter.nominalFace(dir);
            emitter.color(color, color, color, color);
            emitter.materialBake(material, MutableQuadView.BAKE_LOCK_UV);
            emitter.emit();
        }
    }
}
