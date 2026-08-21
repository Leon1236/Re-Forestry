# A5 — Naturalist helmet (spectacles)

## API
- Ported CE `IArmorNaturalist` + `ISpectacleBlock` under `api.core`.
- No Forge capabilities — item implements `IArmorNaturalist` (same Fabric pattern as `IArmorApiarist`).
- Wear check is `IArmorNaturalist.hasNaturalistEye(player)` so client/modules never hardcode `ItemSpectacles`.

## Client highlight (26.2)
- CE used `RenderLevelStageEvent` + custom `RenderType` lines (no depth).
- Fabric 26.2: `LevelRenderEvents.BEFORE_GIZMOS` + vanilla `Gizmos.cuboid(...).setAlwaysOnTop()` (x-ray equivalent).
- Rainbow color matches CE `RenderUtil.getRainbowColor`.

## Highlight targets (CE parity)
- `TileLeaves`: pollinated only.
- `TileHive`: always (default `ISpectacleBlock`).
- `MultiblockTileEntityForestry`: creative + controller reference coord only.
