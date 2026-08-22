# Genetics data model — bees and trees

Traced end to end from `thedarkcolour-ForestryCE`. This is the design the Fabric port's data layer needs to replicate — it's what actually distinguishes "Forest bee" from "Meadows bee" underneath the 4 shared item types.

## The shape

Every species is built through a chained builder, registered once per species in a per-genetics-type file:
- Bees: `forestry/plugin/DefaultBeeSpecies.java` (49,430 chars, all 69 species)
- Trees: `forestry/plugin/DefaultTreeSpecies.java` (59,911 chars, all 50 species)
- Butterflies: `forestry/plugin/DefaultButterflySpecies.java` (13,646 chars) — not in current scope, noted for later

Shared fields (`ISpeciesBuilder`, used by every species type):
- `dominant` (boolean — genetic dominance)
- `genus` / `species` (scientific binomial name strings, e.g. genus "Apis", species "mellifera")
- `setGenome(Consumer<IGenomeBuilder>)` — override specific chromosome values from the type's default karyotype
- `addMutations(Consumer<IMutationsRegistration>)` — which species-pairs can mutate into this one, and at what chance
- `complexity` (1–10, Escritoire/analyzer minigame difficulty — defaults from mutation-tree depth if unset)
- `escritoireColor`, `glint` (enchant glint, for milestone species), `secret` (hidden from discovery — holiday/easter-egg species), `authority` (flavor-text discoverer name)
- `factory` — swappable species class

Bee-specific (`IBeeSpeciesBuilder`): `addProduct(stack, chance)`, `addSpecialty(stack, chance)` (jubilant-state-only), body/stripe/outline colors, custom jubilance condition.

## Bee karyotype — 13 chromosomes (`BeeChromosomes`)
| Chromosome | Type | Meaning |
|---|---|---|
| SPECIES | registry | self-reference |
| SPEED | float | queen production speed |
| LIFESPAN | int | queen lifespan |
| FERTILITY | int | drones given on queen death |
| TEMPERATURE_TOLERANCE | enum (ToleranceType) | acceptable range around ideal temperature |
| HUMIDITY_TOLERANCE | enum (ToleranceType) | acceptable range around ideal humidity |
| ACTIVITY | registry | when the bee is awake (day/night/always) |
| CAVE_DWELLING | boolean | can work without sky access |
| TOLERATES_RAIN | boolean | can work in rain |
| FLOWER_TYPE | registry | required/plantable flower type |
| EFFECT | registry | special bee effect (links to the polymorphic IBeeEffect system) |
| POLLINATION | int | pollination speed |
| TERRITORY | Vec3i | 3D working range |

## Tree karyotype — 10 chromosomes (`TreeChromosomes`)
| Chromosome | Type | Meaning |
|---|---|---|
| SPECIES | registry | self-reference |
| HEIGHT | float | tree height modifier |
| SAPLINGS | float | sapling drop chance |
| FRUIT | registry | fruit grown, if any |
| YIELD | float | fruit/leaf drop chance |
| SAPPINESS | float | fruit ripening speed |
| EFFECT | registry | **unimplemented in base Forestry** — every base-game tree uses `TREE_EFFECT_NONE`, per the source comment |
| MATURATION | int | random ticks before sapling grows into a tree |
| GIRTH | int | trunk diameter (2 = 2×2 giant tree, needs 4 saplings) |
| FIREPROOF | boolean | shared with `ButterflyChromosomes.FIREPROOF` — chromosome constants are reused across species types where it makes sense |

Worth flagging: tree EFFECT being unimplemented is real scope reduction — the port doesn't need to replicate tree-effect behaviors, just the "none" default, unless restoring functionality Forestry itself never shipped.

## Worked example — Forest bee, complete
```java
apiculture.registerSpecies(ForestryBeeSpecies.FOREST, GENUS_HONEY, SPECIES_FOREST, true, new Color(0x19d0ec))
    .addProduct(BEE_COMBS.stack(EnumHoneyComb.HONEY), 0.30f)
    .setGenome(genome -> {
        genome.set(BeeChromosomes.POLLINATION, ForestryAlleles.POLLINATION_SLOWER);
        genome.set(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_3);
        genome.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_1);
    });
```
Only chromosomes that differ from the bee type's default karyotype are set explicitly — the rest inherit defaults. 30% chance of a Honey comb per work cycle, dominant allele, light-blue outline in the analyzer UI.

## Worked example — mutation chains (Common bee)
```java
apiculture.registerSpecies(ForestryBeeSpecies.COMMON, GENUS_HONEY, SPECIES_COMMON, true, new Color(0xb2b2b2))
    .addProduct(BEE_COMBS.stack(EnumHoneyComb.HONEY), 0.35f)
    .setGenome(genome -> { genome.set(BeeChromosomes.SPEED, ForestryAlleles.SPEED_SLOWER); })
    .addMutations(mutations -> {
        for (int i = 0; i < overworldHiveBees.length; i++)
            for (int j = i + 1; j < overworldHiveBees.length; j++)
                mutations.add(overworldHiveBees[i], overworldHiveBees[j], 15);
    });
```
`overworldHiveBees` is the 10 species tied to overworld village hives (Forest, Marshy, Meadows, Modest, Savanna, Tropical, Valiant, Wintry, Lush, Aquatic). Common bee has a 15% mutation chance from breeding any two of them together — mutation chains can be generated programmatically, not just hardcoded pairs.

## Worked example — Oak tree, complete
```java
arboriculture.registerSpecies(ForestryTreeSpecies.OAK, GENUS_QUERCUS, SPECIES_OAK, false, TextColor.fromRgb(4764952), VanillaWoodType.OAK)
    .setTreeFeature(FeatureTreeVanilla::new)
    .setDecorativeLeaves(ArboricultureBlocks.LEAVES_DECORATIVE.stack(ForestryLeafType.OAK))
    .addVanillaStates(Blocks.OAK_LEAVES.getStateDefinition().getPossibleStates())
    .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT.get(ForestryLeafType.OAK).block().getStateDefinition().getPossibleStates())
    .addVanillaStates(ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(ForestryLeafType.OAK).block().getStateDefinition().getPossibleStates())
    .addVanillaSapling(Items.OAK_SAPLING)
    .setGenome(genome -> {
        genome.set(TreeChromosomes.FRUIT, ForestryAlleles.FRUIT_APPLE);
        genome.set(TreeChromosomes.SAPLINGS, ForestryAlleles.SAPLINGS_AVERAGE);
        genome.set(TreeChromosomes.MATURATION, ForestryAlleles.MATURATION_FASTER);
    });
```
Trees don't have a bee-style `addProduct` list — fruit comes entirely from the FRUIT chromosome. Two things this reveals that bees don't have:
- **`registerSpecies` takes an extra `IWoodType` argument** — every tree species is linked directly to a block-system wood type (`VanillaWoodType.OAK` here, or a `ForestryWoodType` for Forestry's own species). This is the concrete link between the genetics inventory and the 1,335-block inventory from earlier.
- **`setTreeFeature`** hooks into vanilla's worldgen `Feature` system — actual tree shape/growth is generated through Minecraft's own tree-feature machinery, one generator class per distinct tree shape (`FeatureTreeVanilla` for vanilla-shaped trees, `FeatureSilverLime` and similarly-named classes for Forestry's own shapes).

Full tree-specific builder surface (`ITreeSpeciesBuilder`): `setTreeFeature`/`setGenerator` (growth shape), `addVanillaStates` (registers "dumb" BE-less leaf blockstates — vanilla leaves plus Forestry's own default/decorative leaf forms — as members of the species, so most leaves on a tree don't need genetic data attached, only special ones do), `addVanillaSapling` (lets the analyzer recognize vanilla saplings), `setDecorativeLeaves`, `setWoodType`, `setRarity` (natural worldgen spawn chance — defaults to 0, i.e. never spawns naturally unless set).

## Bee effect behavior system
The EFFECT chromosome points at an `IBeeEffect` — genuinely behavior code, not data, and it's also a registry-allele-value in its own right (so effects double as alleles). Two methods matter:
- `doEffect(genome, storedData, housing)` — server-side, called by the apiary each effect tick.
- `doFX(genome, storedData, housing)` — client-side particles.

Most effects extend a `ThrottledBeeEffect` base (handles "only run every N ticks", exact constructor flags not yet individually verified). Two concrete examples:
- **AggressiveBeeEffect**: every ~40 ticks, finds all `LivingEntity` within the hive's territory range and damages them (4 damage, reduced by 1 per piece of apiarist armor worn, via a custom damage type).
- **GlowBerryGrowEffect**: every ~200 ticks, picks a random position within the territory area and, if it's an un-berried cave-vine blockstate, flips it to berried.

Good news for the port: this logic is written against plain vanilla Minecraft types (`LivingEntity`, `Level`, `BlockPos`, `BlockState`) — nothing Forge-specific in the two effects checked. This should port to Fabric close to verbatim; the harder part is the housing/genome plumbing that calls into it, not the effect bodies themselves.

## Not yet done
- Extracting all 69 bee / 50 tree species' actual data (mechanical, once the Fabric-side data model is designed — better done in bulk with a script than by hand).
- The rest of the `IBeeEffect` implementations beyond the two sampled here (there are several more under `apiculture/genetics/effects/`, plus the throttling base class itself).
- Mutation-chain data as a whole (sampled one loop-generated case; most are likely literal pairs — worth a full pass before designing the Fabric mutation-storage format).
