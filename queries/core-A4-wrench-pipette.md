# A4 — Wrench + pipette

## API
- **No `IWrenchable`** in CE — wrench calls `BlockState.rotate(CLOCKWISE_90)` (works for `BlockMachine` FACING and any block that overrides rotate).
- Ported CE `forestry.api.core.IToolPipette` → `com.leon1236.reforestry.api.core.IToolPipette`.

## Pipette transfer
- CE used Forge `FluidHandlerItemStack` (bucket volume). Fabric: `FluidStorage.ITEM` + `PipetteItemStorage` / shared `fluid_container_contents` DataComponent.
- GUI tank click via vanilla `clickMenuButton` + `handleInventoryButtonClick` (no custom packet) — Fabric-friendly equivalent of CE `PacketPipetteClick`.

## Tooltip / client
- Wrench: `item.reforestry.wrench.tooltip`.
- Pipette: fluid name + mb via `PipetteContents`; layer1 tinted with `reforestry:pipette_fluid` ItemTintSource.
