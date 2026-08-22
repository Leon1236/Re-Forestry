# GD0 — Gendustry module shell

**Date:** 2026-08-21  
**Donor:** `thedarkcolour-gendustry` (copied into `com.leon1236.reforestry.gendustry`)

## Shipped

- Module `reforestry:gendustry` (`ModuleGendustry`), config toggle via `modules.properties`
- Depends on `reforestry:arboriculture` (pollen kit → `pollen_fertile`)
- Plugin stub `GendustryForestryPlugin` on `reforestry:plugin`
- Creative tab `gendustry` (icon: pollen kit until machines exist)
- 10 resource parts + 17 upgrades + 6 elite upgrades + `pollen_kit`
- Tag `reforestry:upgrades` (all 23)
- Models/textures/lang; crafting recipes for all craftable GD0 items
- Pollen kit uses `IPollenManager.getPollen` / `IPollen.createStack`
- `IPollenType.createStack(IGenome)` + `TreePollenType` → `TreeLifeStage.POLLEN` (fixes Wave 5 stub that returned `ItemStack.EMPTY`)

## Skips (documented)

| Skip | Reason |
|---|---|
| Machines / gene_sample / genetic_template | Later stages (GD3+, GD4) |
| industrial_scoop / industrial_grafter / debug_wand | Wave 7 skip list |
| Upgrade gameplay | Inert until GD7b — do not claim they modify apiaries |

## Connected follow-up (GD1)

`mutation_elite_upgrade` / `fertility_elite_upgrade` crafts shipped with GD1 fluids.

## Tag mapping

Donor `forge:*` → local `c:*` (same as rest of Re-Forestry). Forestry item ids → `reforestry:`.

## Player checks

1. Config: `reforestry:gendustry=true` in `config/reforestry/modules.properties`
2. Creative tab **Gendustry** shows 10 parts + 23 upgrades + pollen kit
3. `/give @s reforestry:labware` / `reforestry:pollen_kit` / an upgrade
4. Use pollen kit on a pollinated Forestry leaf → pollen item + kit consumed
