package com.leon1236.reforestry.arboriculture.client;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import net.fabricmc.fabric.api.blockgetter.v2.FabricBlockGetter;
import net.fabricmc.fabric.api.client.renderer.v1.mesh.QuadEmitter;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.block.dispatch.SingleVariant;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.arboriculture.tiles.LeafRenderData;
import com.leon1236.reforestry.arboriculture.tiles.SpeciesRenderData;

/**
 * Shared block-level equivalent of {@code TreeSpeciesSelectProperty}: dispatches to one of several
 * baked per-species {@link BlockStateModel}s at emit time, reading the species off the block
 * entity's render data rather than the (property-less) blockstate. Used by both the leaves block
 * (with a pollinated variant table and a fruit overlay) and the sapling block (base table only).
 */
public final class SpeciesDispatchModel {
    private SpeciesDispatchModel() {
    }

    @FunctionalInterface
    public interface OverlayBaker {
        Overlay bake(ModelBaker baker);
    }

    @FunctionalInterface
    public interface Overlay {
        void emit(QuadEmitter emitter, LeafRenderData data);
    }

    public static final class Unbaked implements BlockStateModel.UnbakedRoot {
        private final Map<Identifier, SingleVariant.Unbaked> base;
        private final Map<Identifier, SingleVariant.Unbaked> pollinated;
        private final SingleVariant.Unbaked fallback;
        @Nullable
        private final OverlayBaker overlayBaker;
        private final ModelBaker.SharedOperationKey<Baked> key = new ModelBaker.SharedOperationKey<>() {
            @Override
            public Baked compute(ModelBaker baker) {
                return new Baked(bakeAll(base, baker), bakeAll(pollinated, baker), fallback.bake(baker),
                        overlayBaker == null ? null : overlayBaker.bake(baker));
            }
        };

        public Unbaked(Map<Identifier, Identifier> speciesToModel, Map<Identifier, Identifier> pollinatedSpeciesToModel,
                        Identifier fallbackModel, @Nullable OverlayBaker overlayBaker) {
            this.base = toVariants(speciesToModel);
            this.pollinated = toVariants(pollinatedSpeciesToModel);
            this.fallback = new SingleVariant.Unbaked(new Variant(fallbackModel));
            this.overlayBaker = overlayBaker;
        }

        private static Map<Identifier, SingleVariant.Unbaked> toVariants(Map<Identifier, Identifier> speciesToModel) {
            ImmutableMap.Builder<Identifier, SingleVariant.Unbaked> builder = ImmutableMap.builder();
            for (Map.Entry<Identifier, Identifier> entry : speciesToModel.entrySet()) {
                builder.put(entry.getKey(), new SingleVariant.Unbaked(new Variant(entry.getValue())));
            }
            return builder.build();
        }

        private static Map<Identifier, BlockStateModel> bakeAll(Map<Identifier, SingleVariant.Unbaked> unbaked, ModelBaker baker) {
            ImmutableMap.Builder<Identifier, BlockStateModel> builder = ImmutableMap.builder();
            for (Map.Entry<Identifier, SingleVariant.Unbaked> entry : unbaked.entrySet()) {
                builder.put(entry.getKey(), entry.getValue().bake(baker));
            }
            return builder.build();
        }

        @Override
        public void resolveDependencies(Resolver resolver) {
            base.values().forEach(variant -> variant.resolveDependencies(resolver));
            pollinated.values().forEach(variant -> variant.resolveDependencies(resolver));
            fallback.resolveDependencies(resolver);
        }

        @Override
        public BlockStateModel bake(BlockState state, ModelBaker baker) {
            return baker.compute(key);
        }

        @Override
        public Object visualEqualityGroup(BlockState state) {
            return this;
        }
    }

    private static final class Baked implements BlockStateModel {
        private final Map<Identifier, BlockStateModel> base;
        private final Map<Identifier, BlockStateModel> pollinated;
        private final BlockStateModel fallback;
        @Nullable
        private final Overlay overlay;

        private Baked(Map<Identifier, BlockStateModel> base, Map<Identifier, BlockStateModel> pollinated,
                      BlockStateModel fallback, @Nullable Overlay overlay) {
            this.base = base;
            this.pollinated = pollinated;
            this.fallback = fallback;
            this.overlay = overlay;
        }

        private BlockStateModel resolve(SpeciesRenderData data) {
            boolean pollinatedState = data instanceof LeafRenderData leaf && leaf.pollinated();
            Map<Identifier, BlockStateModel> table = pollinatedState ? pollinated : base;
            return data.species() == null ? fallback : table.getOrDefault(data.species(), fallback);
        }

        @Override
        public void emitQuads(QuadEmitter emitter, BlockAndTintGetter level, BlockPos pos, BlockState state,
                               RandomSource random, Predicate<Direction> cullTest) {
            Object renderData = ((FabricBlockGetter) level).getBlockEntityRenderData(pos);
            SpeciesRenderData species = renderData instanceof SpeciesRenderData s ? s : new SpeciesRenderData.Simple(null);
            resolve(species).emitQuads(emitter, level, pos, state, random, cullTest);
            if (overlay != null && renderData instanceof LeafRenderData leaf && leaf.hasFruit()) {
                overlay.emit(emitter, leaf);
            }
        }

        @Override
        public void collectParts(RandomSource random, List<BlockStateModelPart> output) {
            fallback.collectParts(random, output);
        }

        @Override
        public Material.Baked particleMaterial() {
            return fallback.particleMaterial();
        }

        @Override
        public int materialFlags() {
            return fallback.materialFlags();
        }
    }
}
