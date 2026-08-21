# Wave 5 — Genetics public API (implementation report)

Wave 5 is coded and compiles (`./gradlew classes`, JDK 25). It is a **public facade** over the existing bee and tree genomes. There is no second genome engine, no mail, no butterfly items/species/entities, no sorting S2, and no Gendustry.

## What landed

| Stage | What addons can do now |
|---|---|
| GP0a1 | Name `ISpecies` / `IIndividual` / `ILifeStage`. 69 bees and 50 trees implement them. |
| GP0a2 | `IForestryApi.getGeneticManager()`. `getTaxon("apis")` / `"quercus"` resolve. 126 bee/tree taxon JSON. |
| GP0a3 | `IIndividualHandlerItem.get(stack)` returns a real `IIndividual`. Life stage is the enum. |
| GP0b | `registerGenetics` runs from core **before** apiculture/arboriculture. Flower/pollen/error/client hooks exist. |
| GP0c | Extra Bees can `modifySpecies`. Extra Trees can `registerFruit`. An industrial apiary can hold `IBee`. `getTreeManager()` works. |
| GP0d | `BUTTERFLY` species type is registered with karyotype and life stages. 34 butterfly taxa. Zero butterfly items. |

## Ids kept local (do not rename)

| Kind | Re-Forestry | CE 1.21.1 |
|---|---|---|
| Bee items | `bee_drone_ge` / `bee_princess_ge` / `bee_queen_ge` / `bee_larvae_ge` | `drone_bee` / … |
| Tree items | `sapling`, `pollen_fertile` | `tree_sapling` |
| Species | `reforestry:bee_forest`, `reforestry:tree_oak` | `forestry:forest`, `forestry:oak` |
| Genome components | `bee_genome` + `tree_genome` | one `GENOME` |
| Butterfly life-stage ids (items not registered) | `butterfly`, `butterfly_serum`, `caterpillar`, `cocoon` | same paths |
| Species types | `bee_species`, `tree_species`, `butterfly_species` | same paths |

`ForestryBeeSpecies` / `ForestryTreeSpecies` / `ForestryButterflySpecies` point at **live local ids**. CE `CHRONOFUGE` stays `bee_relic`. Tree aliases: `SOUR_CHERRY`→hill cherry, `GREENHEART`→sipiri, `ZEBRANO`→zebrawood, `CAMELTHORN`→desert acacia.

## Fabric vs CE (durable)

- Packages stay `com.leon1236.reforestry.api.genetics` next to existing `IGenome`. No mass-move to `api.core.genetics`.
- Species stay Java (`DefaultBeeSpecies` / `DefaultTreeSpecies`). Taxa are datapack JSON (160 files). `copy_ce_taxa.py` rewrites `forestry:` → `reforestry:` inside JSON.
- `IMutation` stays Identifier-based; `IMutationManager` wraps existing lists.
- `IIndividualHandlerItem` is static helpers + `IIndividualItem` on GE items. Data components, not Fabric Transfer.
- `IBeeSpeciesType` / `ITreeSpeciesType` do **not** extend `ISpeciesType` (Java wildcard bound). They still expose CE lookups (effect/activity/jubilance, fruit/effect/`getTree`).
- `IButterflySpeciesType` **does** extend `ISpeciesType<IButterflySpecies, IButterfly>`.
- `IButterflySpecies` / `IButterflyCocoon` / `IButterflyEffect` extend `IRegistryAlleleValue` because our chromosomes are registry chromosomes.
- Plugin order: genetics → lepidopterology (core init) → apiculture → arboriculture (their modules) → pollen (after all modules).
- `IFruit.getFruitChance(IGenome, LevelAccessor)` matches CE. Nested `IFruit.Product` remains for our fruit impls; Extra Trees can use any `IProduct`.

## Reviews (five independent)

| Gate | Must left | Notes |
|---|---|---|
| CE parity | none after fix | `IFruit` two-arg chance + `getSpecialty`; tree products from fruit allele; species-type lookups; `ForestryAlleles` API re-export |
| Fabric 26.2 | none | `PathfinderMob` spawn return; `TextColor.getValue()`; no `hasChunkAt` |
| No second genome | none | still `bee_genome` / `tree_genome`; Identifier mutations; no Transfer |
| Plugin completeness | none for Wave 5 wiring | leftover CE `META-INF/services/forestry.api.*` removed; jubilance map now lives on `BeeSpeciesType` |
| DoD / ids | none | 160 taxa; 35 butterfly constants; no new butterfly items |

Deferred Should (documented, not this wave): taxon **allele maps** are stored in JSON but not applied to genomes yet (CE resolves them against a karyotype); `IBee.getSuitableBiomes` is empty; `addVillageBee` was not in the plan; `IBeeSpecies` keeps record-style accessors so `DefaultBeeSpecies` does not break; client `registerClient` leaf/bee model maps are collected then discarded except analyzer plugins; `IHiveBuilder.addDrop` has no allele-map overload; `IPollen` is not generic/`LevelAccessor`.

## Leftover gaps

- **D0–D4** butterfly content (items, entity, 35 species, chest recipe).
- **S2** sorting / filter-rule growth on `IGeneticRegistration`.
- **Gendustry** / Extra Bees species / Extra Trees fruits as content.
- Taxon default-allele inheritance (JSON is copied; genomes do not yet inherit genus alleles).

## Smoke (world not required to mark coded)

- GP0a1–a3: `/give` a forest drone; alyzer still opens; `IIndividualHandlerItem.isIndividual` true.
- GP0a2: taxon `apis` / `quercus` resolve.
- GP0b: game still boots; hives/species unchanged.
- GP0c: a no-op `modifySpecies` plugin compiles; farm/apiary still produce.
- GP0d: boot with empty butterfly type; no new items in tabs.
