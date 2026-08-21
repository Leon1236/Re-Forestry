# GeckoLib 5 → Re-Forestry usability map

**Date:** 2026-07-30  
**Policy:** CE = **WHAT** (which things animate, and how they look in Forestry). GeckoLib = **optional HOW** for Blockbench/geo polish — not required for CE parity.  
**Decision:** Do **not** add GeckoLib to Gradle until a real `GeoEntity` / `GeoBlockEntity` ships (butterflies or optional polish).

## Sources

| Role | Location |
|------|----------|
| Library (MCP / clone) | `bernie-g-geckolib` — GeckoLib **5.5.3**, MC **26.2**, Java **25** |
| Local clone | `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` |
| Graphify | alias `geckolib` → `MarkDown_Maker/graphify/bernie-g-geckolib` (scan: `common` + `fabric`) |
| Docs | https://wiki.geckolib.com (GeckoLib 5) |
| CE visuals | MCP `thedarkcolour-ForestryCE` — `ModelPart` / particles, **no** GeckoLib |

Java package: `com.geckolib.*` (GeckoLib 5). Old tutorials use `software.bernie.geckolib` — ignore those.

---

## What it is

Fabric/Forge/NeoForge library for **Bedrock-style** skeletal models + keyframe animations (Blockbench export).

| Module | Role |
|--------|------|
| `common` | Almost all API |
| `fabric` | Entrypoints, Fabric events/SPI/networking |
| `forge` / `neoforge` | Other loaders — Re-Forestry ignores |

**Maven (verified live):**

```groovy
repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = 'GeckoLib'
                url = 'https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/'
            }
        }
        filter { includeGroupAndSubgroups('com.geckolib') }
    }
}

modImplementation "com.geckolib:geckolib-fabric-26.2:5.5.3"
```

Also add `depends.geckolib` in `fabric.mod.json` when adopting.

---

## Consumer API (names that matter)

| Need | Use |
|------|-----|
| Root | `GeoAnimatable` → `registerControllers` + `getAnimatableInstanceCache` |
| Entity | `GeoEntity` + `GeoEntityRenderer` + `DefaultedEntityGeoModel` |
| Block machine | `GeoBlockEntity` + `GeoBlockRenderer` + `DefaultedBlockGeoModel` |
| Item / armor | `GeoItem` + `GeoItemRenderer` / `GeoArmorRenderer` (no separate `GeoArmor` animatable) |
| Replace vanilla mob | `GeoReplacedEntity` + `GeoReplacedEntityRenderer` |
| Controllers | `AnimationController` + `RawAnimation` |
| Per-frame predicate | **`AnimationTest`** (GeckoLib 5; old name was `AnimationState`) |
| Cache | `GeckoLibUtil.createInstanceCache(this)` |
| Helpers | `DefaultAnimations` (`genericWalkIdleController`, `misc.idle`, `move.walk`, …) |

There is **no** `GeoBlock` interface — blocks use `GeoBlockEntity`.

GeckoLib only registers its **resource reload listener**. Your mod still registers renderers:

- Entities: Fabric `EntityRendererRegistry.register`
- Block entities: `BlockEntityRendererFactories.register`
- Items: `SingletonGeoAnimatable.createGeoRenderer` → `GeoRenderProvider`

---

## Asset layout

Loader roots: `assets/<modid>/geckolib/models/**` and `…/geckolib/animations/**`.  
Textures stay under normal `textures/…`.

Defaulted entity id `reforestry:example`:

| Asset | Path |
|-------|------|
| Model | `assets/reforestry/geckolib/models/entity/example.geo.json` |
| Animations | `assets/reforestry/geckolib/animations/entity/example.animation.json` |
| Texture | `assets/reforestry/textures/entity/example.png` |

Code `Identifier`s are **stripped** (no `geckolib/models/` prefix, no `.geo.json` suffix) → `reforestry:entity/example`.

Formats: Bedrock geometry ~1.21.0, actor animations ~1.8.0. Author in Blockbench → export geo + animation JSON.

---

## Fit for Re-Forestry

| Priority | Content | Why | Recommendation |
|----------|---------|-----|----------------|
| **Best future** | Butterflies (lepidopterology) | Real winged mob; CE uses `ModelPart` wing flap | Port CE `ModelPart` first; GeckoLib only for Blockbench polish |
| **Optional polish** | Energy engines (F-energy) | CE `RenderEngine` piston/trunk BER | Keep CE progress + `ModelPart` |
| **Already done** | Rainmaker mill | `RenderMill` + `TileMill.clientTick` | Do **not** rewrite |
| **Wrong tool** | Bees | Particles only (`BeeTravelParticle`) — same as CE | Stay particles |
| **Out of scope** | Factory tanks, boats, bee items, GUIs, JEI, multiblocks | Not skeletal animation | Skip |

**Today:** no GeckoLib in `build.gradle`. Only living entities are Forestry boats (vanilla boat renderer).

---

## Do not use (for this mod)

- `GeoReplacedEntity` — no vanilla-mob reskins planned  
- `GeoArmorRenderer` for apiarist armor — CE is tinted armor items  
- GUI / JEI / genetics screens  
- Replacing mill blade or bee particle FX  

---

## Later integration spike (only after Phase D entity exists)

1. Cloudsmith repo + `com.geckolib:geckolib-fabric-26.2:5.5.3` + `depends.geckolib`
2. Entity implements `GeoEntity` + cache + `registerControllers`
3. Client: `EntityRendererRegistry.register(..., ctx -> new GeoEntityRenderer<>(ctx, type))`
4. Blockbench assets under `assets/reforestry/geckolib/...`

---

## Bottom line

**GeckoLib is mapped and MC 26.2-ready**, but Re-Forestry does not need it yet. Highest payoff is **butterflies**; engines are optional. Default for CE parity remains **`ModelPart` + tick progress**, not geo.

Reuse this note before re-researching: `python3 tools/graphify_query.py geckolib "…"`.
