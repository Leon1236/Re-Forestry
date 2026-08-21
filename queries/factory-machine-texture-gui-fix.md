# Factory machine block + GUI texture fix (2026-07-25)

## Cause
- CE machines (centrifuge, still, squeezer, bottler, carpenter, fermenter, moistener) use **particle-only** block models + `RenderMachine` BER (`*_base.png` / tank atlases). Rainmaker uses `RenderMill`.
- Fabricator is a normal multi-face cube — why it looked correct.
- We had briefly forced `cube_all` on `.0` particle textures (wrong art) and drew orange `fill()` progress bars on GUIs (misaligned vs CE atlas UV blits).

## Fix
- Ported `RenderMachine` / `RenderMill` for MC 26.2 submit API.
- **Textures must use block-atlas `SpriteId` + `TextureAtlasSprite`** (like chests/pots). Raw `entityCutout(Identifier)` left the geometry black.
- Sprite ids: `Sheets.BLOCKS_MAPPER.apply(reforestry:centrifuge_base)` etc. (files already under `textures/block/`, included by vanilla `atlases/blocks.json` directory source).
- Registered BERs + model layers in `FactoryClientHandler`.
- Particle-only block models; item icons use `item/generated` + `.0`.
- `BlockMachine` horizontal `FACING`; `noOcclusion` on TESR-style machines.
- GUI screens blit CE progress UVs; smelter reuses `squeezersocket.png`.

## Smelter / inventory (follow-up)
- Smelter is **not** old TESR art — CE uses JSON `machines/base_machine` + `machines/smelter/base.png` (64×64). Copied those assets; removed still-copied `smelter_*` TESR stubs and smelter BER.
- Inventory icons for TESR machines use `minecraft:special` (`reforestry:machine` / `reforestry:mill`). Smelter inventory uses the JSON block model.
- Smelter GUI: `textures/gui/smelter.png` from CE.

## Inventory label + error tabs (follow-up)
- CE never draws vanilla “Inventory” (`GuiForestry.renderLabels` skips it). `ScreenForestry` draws only a centered title.
- Error icons were inside the panel (top-right). CE puts them on left-side ledgers. `GuiErrorTabs` draws collapsed left ledger tabs outside the GUI.
