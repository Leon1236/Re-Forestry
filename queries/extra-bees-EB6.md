# EB6 — Extra Bees centrifuge / squeezer datapack

**Date:** 2026-08-21  
**Donor:** `ACGaming-Binnie` `extrabees` `RecipeRegister` / `EnumHoneyComb.addRecipe` / `EnumHoneyDrop.addRecipe` / `EnumPropolis.addRecipe`  
**Extract:** `queries/extra-bees-items.json`  
**Generator:** `python3 tools/generate_extra_bees_machine_recipes.py --apply`

## Player exit

- Centrifuge processes all **74** active Extra Bees combs
- Squeezer processes EB drops/propolis **only when** the Binnie liquid maps to a fluid that exists in Re-Forestry

## Counts

| Kind | Written | Soft-skipped |
|---|---:|---:|
| Centrifuge (active combs) | **74** | 0 recipes omitted (22 try-product lines soft-skipped inside recipes) |
| Squeezer honey drops | **20** | 4 (energy empty; acid/poison/liquidnitrogen missing) |
| Squeezer propolis | **1** (water) | 3 (oil/fuel/creosote missing) |
| **Soft-skip lines total** | | **29** |

Paths:

- `data/reforestry/recipe/centrifuge/extra_bees/*_comb.json`
- `data/reforestry/recipe/squeezer/extra_bees/honey_drop_*.json`
- `data/reforestry/recipe/squeezer/extra_bees/propolis_water.json`

## Fluid map (local only)

| Binnie liquid | Re-Forestry / vanilla |
|---|---|
| `for.honey` | `reforestry:honey` |
| `juice` | `reforestry:juice` |
| `milk` | `reforestry:milk` (Forestry milk — same as coconut squeezer / biogas; not `minecraft:milk`) |
| `seedoil` | `reforestry:seed_oil` |
| `short.mead` | `reforestry:short_mead` |
| `Water` | `minecraft:water` |

Amounts: drops **200** mB / time **10**; propolis **500** mB / time **20** (Binnie). Dye-drop remnants: Extra Bees `dye_*` for the seven ExtraBeeItems dyes; other dye metas → modern `minecraft:*_dye` at chance **1.0**.

## Soft-skips (intentional)

| Class | Examples |
|---|---|
| IC2 items | `itemHarz`, `itemCofeePowder` on resin/coffee combs |
| OreDict without local item | `itemRubber`, `dustSulfur`, `dustSmall*`, `sawdust`, `dustCertusQuartz`, … |
| Missing fluids | `acid`, `poison`, `liquidnitrogen`, `oil`, `fuel`, `creosote` |
| Empty liquid | `ENERGY` drop (no squeezer in Binnie either when fluid missing) |

Partial centrifuge recipes still ship when other products resolve (e.g. uranium/stone wax+honey; sawdust keeps honey_drop). Binnie often deactivates the whole comb when a try-product OreDict is empty; Re-Forestry keeps the resolvable lines.

### Exact soft-skip lines (generator dump, 29)

```
centrifuge resin: soft-skip try product (ic2:itemHarz)
centrifuge resin: soft-skip try product (ic2:itemHarz)
centrifuge uranium: soft-skip try product (oredict:dropUranium)
centrifuge uranium: soft-skip try product (oredict:crushedUranium)
centrifuge latex: soft-skip try product (oredict:itemRubber)
centrifuge acidic: soft-skip try product (oredict:dustSulfur)
centrifuge coffee: soft-skip try product (ic2:itemCofeePowder)
centrifuge shadow: soft-skip try product (oredict:dustObsidian)
centrifuge sodalite: soft-skip try product (oredict:dustSmallSodalite)
centrifuge sodalite: soft-skip try product (oredict:dustSmallAluminum)
centrifuge pyrite: soft-skip try product (oredict:dustSmallPyrite)
centrifuge pyrite: soft-skip try product (oredict:dustSmallIron)
centrifuge bauxite: soft-skip try product (oredict:dustSmallBauxite)
centrifuge bauxite: soft-skip try product (oredict:dustSmallAluminum)
centrifuge cinnabar: soft-skip try product (oredict:dustSmallCinnabar)
centrifuge sphalerite: soft-skip try product (oredict:dustSmallSphalerite)
centrifuge sphalerite: soft-skip try product (oredict:dustSmallZinc)
centrifuge saltpeter: soft-skip try product (oredict:dustSaltpeter)
centrifuge sawdust: soft-skip try product (oredict:dustSawdust)
centrifuge sawdust: soft-skip try product (oredict:sawdust)
centrifuge certus: soft-skip try product (oredict:dustCertusQuartz)
centrifuge enderpearl: soft-skip try product (oredict:dustEnderPearl)
squeezer drop energy: soft-skip (empty liquid)
squeezer drop acid: soft-skip (unregistered Binnie/IC2 fluid 'acid')
squeezer drop poison: soft-skip (unregistered Binnie/IC2 fluid 'poison')
squeezer drop ice: soft-skip (unregistered Binnie/IC2 fluid 'liquidnitrogen')
squeezer propolis oil: soft-skip (unregistered Binnie/IC2 fluid 'oil')
squeezer propolis fuel: soft-skip (unregistered Binnie/IC2 fluid 'fuel')
squeezer propolis creosote: soft-skip (unregistered Binnie/IC2 fluid 'creosote')
```

## JEI

Factory JEI already lists `reforestry:centrifuge` / `reforestry:squeezer` from the recipe manager — no Extra Bees JEI plugin needed once datapack loads.

## Gaps

- No Binnie `acid` / `poison` / `liquidnitrogen` / BuildCraft-style `oil`/`fuel` / Railcraft `creosote` in this stack
- OreDict try-products stay gated until a later optional compat maps them
- Extract dye remnant ids now use `misc[].reforestry_id` (`dye_red`, …); generator still remaps via misc as a safety net

## Player checks

1. Put `bee_comb_barren` in a powered centrifuge → beeswax + honey_drop
2. Squeeze `honey_drop_red` → honey + `dye_red` remnant
3. Squeeze `honey_drop_milk` → `reforestry:milk` (200 mB)
4. Squeeze `propolis_water` → water
5. `honey_drop_acid` / `propolis_oil` have **no** squeezer recipe (soft-skip)
