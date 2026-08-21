# F-TINT — filled can/capsule/refractory tint

**Date:** 2026-08-20  
**CE:** 1.21.1 `FluidContainerModel` + loader `forestry:fluid_container`  
**Local ids:** `reforestry:can` / `capsule` / `refractory` (not CE `wax_capsule` / `refractory_capsule`)

## Player-facing

Filled cans, wax capsules, and refractory capsules show the fluid color in the contents layer. Empty stacks stay opaque white (same as empty pipette).

## Why not the CE loader

CE ships a NeoForge geometry loader `forestry:fluid_container` (`FluidContainerModel` wrapping `DynamicFluidContainerModel`). Can/capsule/refractory JSON looks like:

```json
{
  "loader": "forestry:fluid_container",
  "parent": "neoforge:item/default",
  "gui_light": "front",
  "textures": {
    "base": "forestry:item/liquids/can.bottle",
    "fluid": "forestry:item/liquids/can.contents"
  }
}
```

Minecraft 26.2 item definitions have no NeoForge loaders. This stage copies the pipette path instead: layered `item/generated` plus an `ItemTintSource`.

| Layer | Texture | Tint |
|---|---|---|
| 0 | `reforestry:item/liquids/{id}.bottle` | `minecraft:constant` `-1` |
| 1 | `reforestry:item/liquids/{id}.contents` | `reforestry:fluid_container` |

Tint codec is registered in `ItemTintSourcesMixin` next to `reforestry:pipette_fluid`. Color comes from `FluidContainerContents` on the stack (`reforestry:fluid_container_contents`) via `RenderUtil.getFluidColor`.

## Glass / jar leftover models

CE also has `models/item/glass.json` and `jar.json` using the same loader, with `empty` / `filled` model switching. Those are **not** fluid-container items:

- `EnumContainerType` is only `CAN` / `CAPSULE` / `REFRACTORY`
- `FluidsItems.CONTAINERS` registers those three; `CoreItems` has no `glass` or `jar` item
- Molten glass is `reforestry:bucket_glass` (`ForestryFluids.GLASS`), not `reforestry:glass`

Local copies still had `"loader": "reforestry:fluid_container"`, which does not exist on Fabric 26.2. Replaced with vanilla `item/generated` using existing `liquids/glass.*` and `liquids/jar.*` textures so resource load does not fail. No tint (not `ItemFluidContainerForestry`). `glass_filled` / `jar_empty` / `jar_filled` models were already vanilla generated.

## Not in this stage

- Rename to CE 1.21.1 `wax_capsule` / `refractory_capsule`
- Drink-from-container
- Recipe-unlock advancements (`F-ADV`)
