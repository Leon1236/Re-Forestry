# S2 — Lepidopterology genetic filter rules

**Date:** 2026-08-21  
**Stage:** Wave 7 `S2`. Wave 7 remaining stages: **none**.

## What shipped

- `LepidopterologyFilterRuleType`: `FLUTTER` / `BUTTERFLY` / `SERUM` / `CATERPILLAR` / `COCOON` — mirrors CE `LepidopterologyFilterRuleType`.
- `LepidopterologyFilterRule`: attaches PURE_BREED / NOCTURNAL / PURE_NOCTURNAL / FLYER / PURE_FLYER onto `DefaultFilterRuleType` containers.
- Registered from `ReforestryPlugin.registerFilter` when `reforestry:lepidopterology` is **enabled** (`isModuleEnabled`, same EB2a gate). CE order: register rule types, then `LepidopterologyFilterRule.init()` so constructors `addLogic` onto default containers.
- Species picker (`SpeciesWidget`) discovers `ForestrySpeciesTypes.BUTTERFLY` — CE butterflies and Extra Trees moths (`moth_*`) share that type. Labels use `IButterflySpecies.getDisplayName()`.
- Analyzer sprites already at `textures/reforestry/atlas/gui/analyzer/{flutter,butterfly,serum,caterpillar,cocoon}.png`.
- Lang keys `for.gui.filter.reforestry.lepidopterology.*` already present.

## FilterData stages

String stages match `ButterflyLifeStage.getSerializedName()`: `butterfly` / `serum` / `caterpillar` / `cocoon`. `FLUTTER` returns true for any stage once the face rule’s species type is already butterfly.

## Shared container rules

| Rule | Butterfly check (CE `isSameAlleles` = allele `equals`) |
|---|---|
| PURE_BREED | active SPECIES allele equals inactive |
| NOCTURNAL | active `NEVER_SLEEPS` |
| PURE_NOCTURNAL | active `NEVER_SLEEPS` and alleles equal |
| FLYER | active `TOLERATES_RAIN` |
| PURE_FLYER | active `TOLERATES_RAIN` and alleles equal |

No CAVE / PURE_CAVE for butterflies (CE same).

## Package

`com.leon1236.reforestry.lepidopterology.genetics` (same shape as bee/tree filter rules). No donor mod dependency.
