# Extra Trees Wave 7 independent review (2026-08-21)

## Verdict

PASS after Must/Should fixes below.

## Verified counts

| Area | Expected | Actual |
|---|---:|---:|
| Product woods + shrub + log-only | 30+1+4 (=35 types) | 35 |
| Overlap woods skipped | 6 | 6 |
| New fruits | 54 | 54 |
| Species registered | 88 | 88 |
| Binomial skips | 9 | 9 |
| Mutations | 97 | 97 |
| Growth generators | 88 | 88 |
| Machines | 4 | 4 (lumbermill, press, brewery, distillery) |
| Foods | 59 | 59 (Papayimar skipped per ET2/ET5) |
| Fluids | 104 | 104 |
| Moths | 22 | 22 |
| Donor depends | none | none |

## Skips (documented)

- 9 binomial-duplicate species; 6 overlap woods
- No designer / infuser / nursery
- Papayimar food (Binnie fruit allele commented; Food enum remains) — orphan `c:crops/papayimar` tag removed in this review
- `alcohol_fruit` fluid id remap (avoid CE `juice`); `ginger_ale` snake_case

## Fixes in this review

### Must
- Removed orphan `data/c/tags/item/crops/papayimar.json` referencing unregistered `reforestry:papayimar`
- Wired all 88 ET species into `LeafBlockStateResolver` and `SaplingBlockStateResolver` (+ `items/sapling.json`) so leaves/saplings no longer silently fall back to oak for every ET species

### Should
- Added ModuleCore `glass_fitting` item, texture, lang, creative tab, and craft recipe (designer consumer still deferred)
