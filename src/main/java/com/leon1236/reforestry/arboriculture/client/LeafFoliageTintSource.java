package com.leon1236.reforestry.arboriculture.client;

import net.fabricmc.fabric.api.blockgetter.v2.FabricBlockGetter;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.tiles.SpeciesRenderData;

/**
 * The staged leaf textures are grayscale (tint-dependent, like vanilla's own oak_leaves.png), so
 * this registration is required, not optional - without it every leaves block would render with an
 * unintended neutral multiply instead of its species color. Reuses ITreeSpecies.escritoireColor()
 * rather than adding a dedicated leaf-tint field (see CLAUDE.md 11.7 non-goals).
 */
public final class LeafFoliageTintSource implements BlockTintSource {
    private static final int WILDCARD_COLOR = 0xffffff;

    @Override
    public int color(BlockState state) {
        return ARGB.opaque(WILDCARD_COLOR);
    }

    @Override
    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
        Object data = ((FabricBlockGetter) level).getBlockEntityRenderData(pos);
        if (!(data instanceof SpeciesRenderData species) || species.species() == null) {
            return color(state);
        }
        ITreeSpecies treeSpecies = ArboricultureGenetics.getSpecies(species.species());
        return ARGB.opaque(treeSpecies.escritoireColor());
    }
}
