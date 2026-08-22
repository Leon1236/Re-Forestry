# Wave 9 Stage 7 — FACTORY-VIS + BOOK-MACHINES

## F-VIS-BER

Smelter and fabricator stay on CE-accurate block models (no `RenderMachine` BER — no world tank meshes). Squeezer product-tank fill remains `RenderMachine` + `IRenderableTile` (CE blockstate fill variants deferred).

## Rainmaker polish

Client-side charge/spindle dust and rainmaker cloud particles when charging (`TileMill`, `TileMillRainmaker`).

## GD-UI-OWNER

`GuiOwnerLedger` on industrial apiary screen (`ScreenIndustrialApiary` → `ScreenForestry.setOwnerLedger`).

## BOOK-MACHINES

Added missing `core/machines/smelter.json` almanac entry. Other 12 machine/engine pages were already on disk.
