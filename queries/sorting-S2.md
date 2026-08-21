# S2 — Lepidopterology genetic filter rules

**Date:** 2026-08-21  
**Stage:** Wave 7 `S2`. Wave 7 remaining stages: **none**.

## What shipped

- `LepidopterologyFilterRuleType`: `FLUTTER` / `BUTTERFLY` / `SERUM` / `CATERPILLAR` / `COCOON` — mirrors CE `LepidopterologyFilterRuleType`.
- `LepidopterologyFilterRule`: attaches PURE_BREED / NOCTURNAL / PURE_NOCTURNAL / FLYER / PURE_FLYER onto `DefaultFilterRuleType` containers.
- Registered from `ReforestryPlugin.registerFilter` when `reforestry:lepidopterology` is loaded (`LepidopterologyFilterRule.init()` then rule types).
- Species picker (`SpeciesWidget`) discovers `ForestrySpeciesTypes.BUTTERFLY` — CE butterflies and Extra Trees moths (`moth_*`) share that type.
- Analyzer sprites already at `textures/reforestry/atlas/gui/analyzer/{flutter,butterfly,serum,caterpillar,cocoon}.png`.
- Lang keys `for.gui.filter.reforestry.lepidopterology.*` already present.

## FilterData stages

String stages match `ButterflyLifeStage.getSerializedName()`: `butterfly` / `serum` / `caterpillar` / `cocoon`. `FLUTTER` returns true for any stage once the face rule’s species type is already butterfly.

## Shared container rules

| Rule | Butterfly check |
|---|---|
| PURE_BREED | active species id equals inactive |
| NOCTURNAL | active `NEVER_SLEEPS` |
| PURE_NOCTURNAL | active and inactive `NEVER_SLEEPS` |
| FLYER | active `TOLERATES_RAIN` |
| PURE_FLYER | active and inactive `TOLERATES_RAIN` |

No CAVE / PURE_CAVE for butterflies (CE same).

## Package

`com.leon1236.reforestry.lepidopterology.genetics` (same shape as bee/tree filter rules). No donor mod dependency.
