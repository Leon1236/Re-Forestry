package com.leon1236.reforestry.arboriculture.client;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.fabricmc.fabric.api.client.model.loading.v1.BlockStateResolver;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.ReForestry;

/**
 * Block-level counterpart of TreeSpeciesSelectProperty (item side, since 11.4): dispatches the
 * leaves block's model per-species by reading TileLeaves' render data, since the species lives in
 * the block entity, not the (property-less w.r.t. species) blockstate. Replaces the old flat
 * models/block/leaves.json placeholder.
 *
 * <p>The 50-species -> 15-texture-group table below is generated reference data - see
 * tools/generate_leaf_block_models.py, which also writes the group model JSONs this class
 * references. Every real species is accounted for; there is no species with no leaf art.
 */
public final class LeafBlockStateResolver implements BlockStateResolver {
    private static final Map<String, String> SPECIES_TO_GROUP = Map.ofEntries(
            Map.entry("acacia", "acacia"),
            Map.entry("balsa", "acacia"),
            Map.entry("baobab", "acacia"),
            Map.entry("beech", "oak"),
            Map.entry("birch", "birch"),
            Map.entry("cherry", "cherry"),
            Map.entry("chestnut", "birch"),
            Map.entry("cocobolo", "mangrove"),
            Map.entry("coconut", "palm"),
            Map.entry("dark_oak", "oak"),
            Map.entry("date", "palm"),
            Map.entry("desert_acacia", "acacia"),
            Map.entry("dogwood", "dogwood"),
            Map.entry("ebony", "jungle"),
            Map.entry("elm", "oak"),
            Map.entry("feijoa", "azalea"),
            Map.entry("fir", "spruce"),
            Map.entry("giant_sequoia", "spruce"),
            Map.entry("ginkgo", "ginkgo"),
            Map.entry("hill_cherry", "birch"),
            Map.entry("ipe", "ipe"),
            Map.entry("jacaranda", "jacaranda"),
            Map.entry("jungle", "jungle"),
            Map.entry("kapok", "jungle"),
            Map.entry("kauri", "spruce"),
            Map.entry("larch", "spruce"),
            Map.entry("lemon", "azalea"),
            Map.entry("lime", "birch"),
            Map.entry("macrocarpa", "spruce"),
            Map.entry("mahoe", "oak"),
            Map.entry("mahogany", "jungle"),
            Map.entry("maple", "maple"),
            Map.entry("oak", "oak"),
            Map.entry("olive", "willow"),
            Map.entry("orange", "azalea"),
            Map.entry("padauk", "acacia"),
            Map.entry("papaya", "palm"),
            Map.entry("pear", "oak"),
            Map.entry("pewen", "spruce"),
            Map.entry("pine", "spruce"),
            Map.entry("plum", "oak"),
            Map.entry("poplar", "birch"),
            Map.entry("sequoia", "spruce"),
            Map.entry("sipiri", "mangrove"),
            Map.entry("spruce", "spruce"),
            Map.entry("teak", "jungle"),
            Map.entry("walnut", "acacia"),
            Map.entry("wenge", "oak"),
            Map.entry("willow", "willow"),
            Map.entry("zebrawood", "jungle")
    );

    private static final String FALLBACK_GROUP = "oak";

    @Override
    public void resolveBlockStates(Context context) {
        Map<Identifier, Identifier> base = groupModels("");
        Map<Identifier, Identifier> pollinated = groupModels("_pollinated");
        Identifier fallback = groupModel(FALLBACK_GROUP, "");

        SpeciesDispatchModel.Unbaked model = new SpeciesDispatchModel.Unbaked(base, pollinated, fallback, LeafFruitOverlay::bake);
        for (BlockState state : context.block().getStateDefinition().getPossibleStates()) {
            context.setModel(state, model);
        }
    }

    private static Map<Identifier, Identifier> groupModels(String suffix) {
        ImmutableMap.Builder<Identifier, Identifier> builder = ImmutableMap.builder();
        for (Map.Entry<String, String> entry : SPECIES_TO_GROUP.entrySet()) {
            builder.put(ReForestry.id("tree_" + entry.getKey()), groupModel(entry.getValue(), suffix));
        }
        return builder.build();
    }

    private static Identifier groupModel(String group, String suffix) {
        return ReForestry.id("block/leaves/" + group + suffix);
    }
}
