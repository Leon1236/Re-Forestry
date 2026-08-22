# Addon integration mapping — Gendustry, Extra Bees, Extra Trees as built-in modules

Decision on record: these three addons become native modules of the Fabric port, toggleable in config files, coded the same way as main Forestry. Everything below is verified against the database.

## The architecture already exists — this is the big win

Forestry CE ships a module framework built for exactly this shape:
- `@ForestryModule` classes implementing `IForestryModule`, each with a `ResourceLocation` id, `getModuleDependencies()` (other modules), and `getModDependencies()` (external mods).
- `ForestryModuleManager` discovers annotated modules, resolves the dependency graph, drops modules whose module-dependencies are missing, and loads the rest in dependency order.
- Content registration goes through the same `ModFeatureRegistry.get(MODULE_ID)` → `FeatureBlockGroup`/`FeatureItem`/etc. machinery inventoried earlier.

**Proof it works for addon-shaped content:** thedarkcolour's modern Gendustry (1.20.1) is already written this way. It has zero `DeferredRegister` usage — its `GBlocks.java` is a 4-line `FeatureBlockGroup` over a machine-type enum, identical in shape to `ApicultureBlocks.java`, registered under its own module id (`gendustry:core`), with species/genetics hooks going through `IForestryPlugin` (`GendustryForestryPlugin`). So "coded the same way as main Forestry" isn't an aspiration for Gendustry — it's a port of code that already is.

**The one gap:** CE's module manager only skips modules for *missing dependencies* — there's no config file that disables a present module. The original 1.12.2 Forestry had exactly that (a modules config). So the port needs one addition to the manager: consult a config (e.g. `config/forestry/modules.toml`) before loading, and drop disabled modules plus anything module-dependent on them. Small, well-contained change; the dependency-resolution loop that makes it safe already exists.

Proposed module ids: keep every piece under the single mod, e.g. `forestry:gendustry`, `forestry:extra_bees`, `forestry:extra_trees` (final naming is a style call — Gendustry's port uses its own `gendustry` namespace since it's a separate mod there; as built-in modules, one namespace is cleaner).

## Gendustry — modern source, near-mechanical port

Source: `thedarkcolour-gendustry` (1.20.1, 108 Java files). Content, extracted:
- **10 machines** (one `FeatureBlockGroup` over `GendustryMachineType`): industrial_apiary, mutagen_producer, dna_extractor, protein_liquefier, sampler, mutatron, advanced_mutatron, imprinter, genetic_transposer, replicator.
- **3 fluids** (`GFluids`): mutagen/DNA/protein family (registry names to extract in the detailed pass).
- Own recipe types (`GRecipeTypes`) with recipe caches (mutagen, DNA, protein), menus, block entities, items (upgrades etc.).
- Depends on Forestry's genetics APIs throughout — which the port owns, so the dependency is internal.

Port difficulty: same as any main-Forestry module — the Forge→Fabric mapping table already built covers it.

## Extra Bees + Extra Trees — content-rich, but only 1.12.2 source exists

Source: `ACGaming-Binnie` (Binnie's Mods 2.5.1, MC 1.12.2). No modern port exists in the database — and unlike the CE lineage, this code is pre-flattening, pre-modern-registry, written against Binnie's own GUI/machine framework (`binnie.core`). **This is a content extraction + reimplementation job, not a code translation** — the species/machine *data* comes from the old enums, but the Java gets written fresh in the CE feature/builder style.

Verified content inventory:

| Content | Count | Detail |
|---|---|---|
| Extra Bees species | **116** (`ExtraBeeDefinition`) | Whole branches Forestry lacks: mineral/ore bees (copper, tin, iron, lead, zinc, titanium, tungstate, nickel...), rock/stone line, decomposing line, and more |
| Extra Bees effects | **25** (`ExtraBeesEffect`) | ectoplasm, acid, mob-spawning (zombie/skeleton/creeper), lightning, radioactive, meteor, hunger/food, blindness, confusion, fireworks, teleport, gravity, thief, wither, water, slow, 3× bonemeal, power — a much wilder effect set than base Forestry's |
| Extra Bees hives | 4 | water, rock, nether, marble (from block/texture assets) |
| Extra Bees alveary blocks | 7 | frame, hatchery, lighting, mutator, rain shield, stimulator, transmission |
| Extra Trees species | **98** (`ETTreeDefinition`) | Huge fruit/nut orchard set: apples, stone fruits, citrus line, nut trees... |
| Extra Trees fruits | 61 (`AlleleETFruitDefinition`) | Own fruit-allele set feeding the FRUIT chromosome |
| Extra Trees moths | 22 (`ButterflySpecies`) | Lepidopterology content — implies the lepidopterology module joins the roadmap earlier than planned |
| Extra Trees machines | 6 | lumbermill, brewery, distillery, fruit press, infuser, nursery (+ the designer, see below) |
| Extra Trees wood types | ~40 | alder, apple, ash, banana, beech, box, brazilwood, butternut, cedar, cypress, elder, elm, eucalyptus, fig, fir, gingko, hawthorn, hazel, hemlock, hickory, holly, hornbeam, iroko, locust, logwood, maclura, maple, olive, pear, pinkivory, purpleheart, rosewood, rowan, sweetgum, syzgium, whitebeam, yew... — these multiply through the same wood×kind block cross-product as main arboriculture, so expect roughly another ~1,000 block registry entries |

## The dependency question that needs a decision

Binnie's repo is 5 content mods + shared infrastructure: `core` (413 files — GUI framework, machine framework, liquids), `genetics` (282 files — the Isolator/Sequencer/Inoculator/Acclimatiser/Splicer/Replicator machine line, serums, liquid DNA), `botany` (flowers), `design` (woodworking patterns), plus extrabees/extratrees themselves.

Two of those matter for scope:
1. **Binnie's `genetics` machines** — in 1.12.2, the serum/gene-splicing machinery lives there, *not* in Extra Bees (Extra Bees' own machines are just the alveary parts). Gendustry's mutatron/sampler/imprinter/replicator line covers overlapping ground in modern form. Recommended default: treat Gendustry as *the* genetic-manipulation machine set and skip Binnie-genetics — but that means some classic Extra Bees-era gameplay (serums, the Isolator) doesn't return. Your call whether that's acceptable.
2. **Binnie's `design` + parts of `core`** — Extra Trees' woodworker/panelworker/glassworker "designer" system (stained glass, patterned planks, ~hundreds of pattern textures) is built on the `design` module. Recommended default: include the 6 standalone machines (lumbermill, brewery, distillery, press, infuser, nursery) in extra_trees, and defer the designer system to its own later decision — it's effectively a seventh module of its own size.

Also noteworthy: some Extra Trees machine textures in this repo already live under `assets/forestry/textures/tile/extratrees/...` — the 1.12.2-era mods were already halfway inside Forestry's namespace, which supports the built-in-module approach for asset reuse.
