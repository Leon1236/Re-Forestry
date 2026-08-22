# GD2 — Gendustry mutagen / protein / DNA recipes

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (`GRecipeTypes`, processor recipes, recipe caches)

## Shipped

- Recipe types + serializers: `reforestry:mutagen`, `reforestry:protein`, `reforestry:dna`
- Classes under `com.leon1236.reforestry.gendustry.recipe` (`MutagenRecipe`, `ProteinRecipe`, `DnaRecipe`, `ProcessorRecipe`)
- Caches: `MutagenRecipeCache`, `ProteinRecipeCache`, `DnaRecipeCache` via Fabric `RecipeCacheRegistry` (`SERVER_STARTED` / `END_DATA_PACK_RELOAD` / `SERVER_STOPPING`)
- Datapack recipes (21): 4 mutagen + 7 protein + 10 DNA
- Extract script: `tools/extract_gendustry_processor_recipes.py`
- Module deps now include `apiculture` + `lepidopterology` (DNA species types) in addition to `arboriculture`

## Recipe counts

| Type | Count | Amounts |
|---|---|---|
| mutagen | 4 | redstone 100, glowstone_dust 200, glowstone 800, redstone_block 900 |
| protein | 7 | beef/porkchop 500; cod/pufferfish/rabbit/salmon/tropical_fish 250 |
| dna | 10 | bee drone 100 / larvae 300 / princess 500 / queen 600; tree sapling 100 / pollen 400; butterfly 200 / caterpillar\|cocoon 1000 / serum 800 |

## Registry ids

| Kind | Id |
|---|---|
| Recipe type | `reforestry:mutagen`, `reforestry:protein`, `reforestry:dna` |
| DNA species_type | `reforestry:bee_species`, `reforestry:tree_species`, `reforestry:butterfly_species` |

## Choices

- Codecs + `RecipeSynchronization` like factory recipes (26.2 MapCodec / StreamCodec), not donor Gson serializers.
- Simple vs complex cache split uses Fabric `Ingredient.requiresTesting()` (donor `isSimple()` gone).
- Lookup uses `RecipeManager.getAllOfType` (Fabric recipe API).
- `genetic_template` serializer deferred to GD4 (tightly coupled to gene sample/template items).
- No donor `fabric.mod.json` depends.

## Gaps / next

- Machines that consume these recipes: **GD3** (mutagen producer, protein liquefier, DNA extractor).
- Genetic template crafting serializer: **GD4**.
- Yellorium/uranium mutagen (donor todo) — not shipped in donor either.

## Player checks

1. Join a world with gendustry enabled; confirm no recipe load errors for mutagen/protein/dna
2. `/recipe` or datapack inspect: 4 + 7 + 10 recipes under `reforestry:mutagen|protein|dna`
3. Machines that use caches arrive in GD3
