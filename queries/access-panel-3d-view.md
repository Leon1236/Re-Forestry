# Access panel 3D view / pick (validated)

Date: 2026-07-28

## Transform (must stay in sync)

PIP `prepare` already applies `scale(s, s, -s)`. Our `renderToTexture` then applies:

### Block-model machines (fabricator, smelter, …)

1. `scale(1, -1, 1)` — upright without mirroring left/right
2. `Rx(pitch)` then `Ry(yaw)` with `yaw = facing.toYRot()`

### Entity/TESR machines (carpenter, centrifuge, still, …)

Particle-only block models; mesh comes from `MachineSpecialRenderer` (same as inventory items).

1. `scale(1, -1, -1)` — matches vanilla item PiP winding (otherwise half the mesh is culled / looks broken)
2. `Rx(pitch)` then `Ry(yaw)` with `yaw = facing.toYRot() + 180` — the extra Z flip mirrors front/back, so +180 keeps the facing side toward the camera

Picking and face tints **must** use the same scale/yaw as the mesh for that machine. Detect via `usesEntityMachineModel` (block model emits zero quads).

## Default pitch

`DEFAULT_PITCH = 15`

## Picking

Project each face center `(dir * 0.5)` through the view matrix; pick nearest cursor hit with greatest `z` (front-most). Cull faces whose transformed normal has `z <= 0`.

Screen: `sx = cx + p.x * scale`, `sy = cy + p.y * scale`.

## Labels (CoFH BlockHelper)

Looking at the front:

| Label | World direction |
|---|---|
| Front | `facing` |
| Back | `facing.opposite` |
| Left | `facing.clockWise` |
| Right | `facing.counterClockWise` |
| Top / Bottom | `UP` / `DOWN` |
