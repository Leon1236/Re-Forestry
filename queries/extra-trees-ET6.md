# Extra Trees ET6 — 22 moths on butterfly type

**Date:** 2026-08-21  
**Status:** Implemented  
**Donor:** Binnie Extra Trees `ButterflySpecies` (no fabric.mod.json depends)  
**Extract:** `queries/extra-trees-extract/moths.json`  
**Generator:** `python3 tools/generate_extra_trees_ET6.py --apply --assets --lang`

## Player exit

`/reforestry butterfly give reforestry:moth_<id> [reforestry:butterfly|butterfly_serum|caterpillar|cocoon]` for all 22 Extra Trees moths (same GE items as Wave 6 butterflies). Creative lepidopterology tab lists them automatically via `LepidopterologyGenetics.getAllSpeciesIds()`.

## Counts

| Kind | Count | Notes |
|---|---|---|
| Species | **22** | Ids `moth_*` — avoids CE `monarch` / `speckled_wood` collisions |
| Mutations | **0** | Binnie `registerMutations()` empty; none added |
| New taxa | **18** | Skip existing `danaus` / `pararge`; papilionidae for birdwing/rose/hind |
| Textures | **22** entity + **22** item | Copied from Binnie `butterflies/…` → local `entity/butterfly/moth_*` + `item/butterfly/moth_*` |
| Item models | **22** | Plus `items/butterfly.json` select cases (57 total with CE 35) |

## Wiring

- `ExtraTreesMothSpecies` + `ExtraTreesMothIds` under `extratrees.genetics`
- Plugin: `registerGenetics` (taxa), `registerLepidopterology` (species + `setMoth(true)`), `registerClient` (sprites)
- `ModuleExtraTrees` now depends on `lepidopterology`
- Authority `Binnie`; rarity `0.5f` (Binnie default); no custom genome alleles (Binnie left templates default)
- Lang keys: `allele.reforestry.butterfly_species.butterfly_moth_*` from extract display names

## Gaps → S2 / later

| Gap | Why |
|---|---|
| Genetic filter moth rules | **S2** (do not start here) |
| Separate moth life-stage grammar | CE/Binnie share butterfly grammar; `isMoth` flag only |
| Binnie branch uid `trees.*` classification | Local taxa use nymphalidae/papilionidae parents |

## Review (2026-08-21, full)

- **22/22** `moth_*` ids match `moths.json` / Binnie `ButterflySpecies` (display, scientific, color, texture hashes)
- **0** mutations (empty `registerMutations()`; none in Java)
- Assets: 22 entity + 22 item textures, 22 models, 57 `items/butterfly.json` cases (35 CE + 22), 22 lang keys
- `setMoth(true)`, authority Binnie, rarity `0.5f`; no donor `fabric.mod.json` depends
- Compile: `./gradlew classes` OK

## Next

**S2** — genetic filter butterfly/moth rules. Do not start until ET6 is accepted.
