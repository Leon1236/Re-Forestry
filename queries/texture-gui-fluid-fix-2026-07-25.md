# Texture / GUI / fluid client fixes (2026-07-25)

## Root causes
1. Factory (+ soldering) screens used gray `fill()` panels instead of CE GUI PNGs.
2. Circuit boards / electron tubes / soldering iron lacked MC 26.2 `assets/.../items/*.json` model definitions (and tints).
3. Buckets only used empty `minecraft:item/bucket` with no liquid layer.
4. World fluids had opaque cube block models and no `FluidRenderingRegistry` registration (only transfer-API tint).

## Fixes
- Screens blit CE textures via `FactoryGuiTextures`; soldering uses `solder.png`; GUI heights aligned to CE.
- Item defs + constant tints for circuits/tubes; colors from CE `colour.properties` / `EnumElectronTube`.
- Buckets: `layer0` bucket + `layer1` `liquids/can.contents` tinted per fluid.
- `FluidClientHandler` registers `FluidRenderingRegistry` + still texture materials; fluid block models are particle-only.
- Generated missing `items/*.json` stubs for remaining `models/item` entries.

## Block model follow-up

Transparent/wrong world blocks caused by:
1. **Alveary** blockstates pointed at `models/block/apiculture/alveary_*` that were never copied from CE → copied + `forestry:`→`reforestry:`.
2. **Factory machines** (fermenter/bottler/moistener/rainmaker) used particle-only models (CE TESR style) while we render `RenderShape.MODEL` with no BER → switched to `cube_all` on `.0` textures.
3. **Fluids** used pre-colored still sprites *plus* a color tint (washed-out/odd); switched to translucent Material with **no** block tint. Particle-only fluid block models remain (correct; `FluidRenderingRegistry` draws the liquid).

Client restart required after these asset/client changes.
