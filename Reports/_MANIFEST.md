# Reports Manager Manifest

- Started: 2026-07-30
- Finished: 2026-07-30
- Scope: A (content + interop) — 12 repos
- Feature unit: module
- Bootstrap: Phase 0 inventory → Phase 1 one-module reports → Phase 2 synthesizers

## Phase status

| Phase | Status | Notes |
|---|---|---|
| 0 Inventory | done | 12/12 `00-INVENTORY.md` |
| 1 Module reports | done | 156/156 `features/*.md` (Binnie `all` skipped) |
| 2 Comprehensive | done | 12/12 `COMPREHENSIVE.md` |
| Final audit | done | OVERALL PASS |

## Audit snapshot

| Repo | Modules | Inventory | Features | Comprehensive |
|---|---:|---|---|---|
| thedarkcolour-ForestryCE | 17 | OK | 17/17 | OK (~23 KB) |
| thedarkcolour-Immersive-Forestry | 17 | OK | 17/17 | OK (~18 KB) |
| ForestryMC-ForestryMC | 20 | OK | 20/20 | OK (~15 KB) |
| ACGaming-Binnie | 11 | OK | 11/11 | OK (~14 KB) |
| thedarkcolour-gendustry | 10 | OK | 10/10 | OK (~17 KB) |
| mezz-JustEnoughItems | 9 | OK | 9/9 | OK (~11 KB) |
| Tiviacz1337-Travelers-Backpack | 21 | OK | 21/21 | OK (~19 KB) |
| bernie-g-geckolib | 15 | OK | 15/15 | OK (~15 KB) |
| SuperMartijn642-SuperMartijn642sCoreLib | 11 | OK | 11/11 | OK (~12 KB) |
| TechReborn-Energy | 3 | OK | 3/3 | OK (~12 KB) |
| thedarkcolour-ModKit | 4 | OK | 4/4 | OK (~11 KB) |
| Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X | 18 | OK | 18/18 | OK (~14 KB) |

## Layout

```
Reports/
  _MANIFEST.md
  _MODULE_QUEUE.tsv
  <Repo>/
    00-INVENTORY.md
    features/<slug>.md
    COMPREHENSIVE.md
```

## Tooling

- Per-module scaffold/research helper: `tools/module_feature_report.py`
- Queue: `Reports/_MODULE_QUEUE.tsv`
