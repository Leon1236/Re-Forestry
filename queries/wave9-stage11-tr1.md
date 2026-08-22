# Wave 9 Stage 11 — TR1 optional Trinkets spectacles

**Date:** 2026-08-22

## TR1 — done

Optional Trinkets compat for Spectacles (`naturalist_helmet`) in head accessory slots. Helmet slot already worked via A5 (`IArmorNaturalist`).

### Pattern (CE Curios → Fabric Trinkets)

| CE | Re-Forestry |
|---|---|
| `forestry.compat.curios.CuriosCompat` | `core.compat.trinkets.TrinketsCompat` |
| `GeneticsUtil.hasNaturalistEye` checks helmet + Curios `head` | `IArmorNaturalist.hasNaturalistEye` checks helmet + hook |
| `curios:head` item tag | `trinkets` slot tags `head/face`, `head/hat` |
| `ModList.isLoaded("curios")` | `FabricLoader.isModLoaded("trinkets")` |

Core never imports Trinkets types at compile time: `TrinketsCompat` uses reflection against Trinkets API at runtime when the mod is loaded. `NaturalistEyeHooks` holds a `Predicate<Player>` registered from `TrinketsCompat.init()`.

### Files

- `core/NaturalistEyeHooks.java` — optional trinkets wear check hook
- `core/compat/trinkets/TrinketsCompat.java` — register trinket + eye check via `TrinketsApi`
- `api/core/IArmorNaturalist.java` — helmet first, then hook
- `ReForestry.java` — `TrinketsCompat.init()` when Trinkets loaded
- `data/trinkets/tags/items/head/face.json` + `head/hat.json` — slot tags
- `build.gradle` — no Trinkets dependency; reflection-only compat
- Almanac: `for.gui.book.patchouli.entry.beekeeping.suit2` notes helmet + optional Trinkets slot

### Player checks

1. Without Trinkets: spectacles in helmet slot still show pollinated-leaf / multiblock outlines.
2. With Trinkets: spectacles equip in head face/hat slot; outlines work without wearing a helmet.
3. `./gradlew compileJava` passes without Trinkets on runtime classpath.
