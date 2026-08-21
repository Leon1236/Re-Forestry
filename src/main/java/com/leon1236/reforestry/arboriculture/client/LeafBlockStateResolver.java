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
 * <p>The CE + Extra Trees species -> texture-group table below is generated reference data - see
 * tools/generate_leaf_block_models.py, which also writes the group model JSONs this class
 * references. CE and Extra Trees registered species are accounted for; unknown ids fall back to oak.
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
            Map.entry("zebrawood", "jungle"),
            Map.entry("orchard_apple", "oak"),
            Map.entry("sweet_crabapple", "oak"),
            Map.entry("flowering_crabapple", "oak"),
            Map.entry("prairie_crabapple", "oak"),
            Map.entry("blackthorn", "oak"),
            Map.entry("cherry_plum", "oak"),
            Map.entry("peach", "oak"),
            Map.entry("nectarine", "oak"),
            Map.entry("apricot", "oak"),
            Map.entry("almond", "oak"),
            Map.entry("wild_cherry", "oak"),
            Map.entry("black_cherry", "oak"),
            Map.entry("manderin", "jungle"),
            Map.entry("satsuma", "jungle"),
            Map.entry("tangerine", "jungle"),
            Map.entry("et_lime", "jungle"),
            Map.entry("key_lime", "jungle"),
            Map.entry("finger_lime", "jungle"),
            Map.entry("pomelo", "jungle"),
            Map.entry("grapefruit", "jungle"),
            Map.entry("kumquat", "jungle"),
            Map.entry("citron", "jungle"),
            Map.entry("buddha_hand", "jungle"),
            Map.entry("banana", "palm"),
            Map.entry("red_banana", "palm"),
            Map.entry("plantain", "palm"),
            Map.entry("butternut", "oak"),
            Map.entry("rowan", "oak"),
            Map.entry("hemlock", "spruce"),
            Map.entry("ash", "oak"),
            Map.entry("alder", "oak"),
            Map.entry("copper_beech", "oak"),
            Map.entry("aspen", "oak"),
            Map.entry("yew", "spruce"),
            Map.entry("cypress", "spruce"),
            Map.entry("douglas_fir", "spruce"),
            Map.entry("hazel", "oak"),
            Map.entry("sycamore", "oak"),
            Map.entry("whitebeam", "oak"),
            Map.entry("hawthorn", "oak"),
            Map.entry("pecan", "oak"),
            Map.entry("et_elm", "oak"),
            Map.entry("elder", "oak"),
            Map.entry("holly", "oak"),
            Map.entry("hornbeam", "oak"),
            Map.entry("sallow", "willow"),
            Map.entry("acorn_oak", "oak"),
            Map.entry("et_fir", "spruce"),
            Map.entry("cedar", "spruce"),
            Map.entry("red_maple", "maple"),
            Map.entry("loblolly_pine", "spruce"),
            Map.entry("sweetgum", "oak"),
            Map.entry("locust", "oak"),
            Map.entry("osange_orange", "jungle"),
            Map.entry("old_fustic", "jungle"),
            Map.entry("brazilwood", "jungle"),
            Map.entry("logwood", "jungle"),
            Map.entry("rosewood", "jungle"),
            Map.entry("purpleheart", "jungle"),
            Map.entry("iroko", "oak"),
            Map.entry("brazilnut", "jungle"),
            Map.entry("rose_gum", "jungle"),
            Map.entry("swamp_gum", "jungle"),
            Map.entry("box", "oak"),
            Map.entry("clove", "oak"),
            Map.entry("coffee", "jungle"),
            Map.entry("rainbow_gum", "jungle"),
            Map.entry("pink_ivory", "oak"),
            Map.entry("blackcurrant", "oak"),
            Map.entry("redcurrant", "oak"),
            Map.entry("blackberry", "oak"),
            Map.entry("raspberry", "oak"),
            Map.entry("blueberry", "oak"),
            Map.entry("cranberry", "oak"),
            Map.entry("juniper", "spruce"),
            Map.entry("gooseberry", "oak"),
            Map.entry("golden_raspberry", "oak"),
            Map.entry("cinnamon", "jungle"),
            Map.entry("cashew", "jungle"),
            Map.entry("avocado", "jungle"),
            Map.entry("nutmeg", "jungle"),
            Map.entry("allspice", "jungle"),
            Map.entry("chilli", "jungle"),
            Map.entry("star_anise", "jungle"),
            Map.entry("mango", "jungle"),
            Map.entry("starfruit", "jungle"),
            Map.entry("candlenut", "oak"),
            Map.entry("dwarf_hazel", "oak")
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
