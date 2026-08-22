# Storage B1–B3 — filter backpacks (Traveler’s Backpack adopt)

**Date:** 2026-08-17  
**CE:** WHAT (ids, 15/45 slots, modes, filters, recipes, GUIs)  
**Traveler’s Backpack Fabric 26.1:** HOW (adopted into `com.leon1236.reforestry.storage` — no `travelersbackpack` dependency)

## What players get

Fourteen Forestry backpacks: miner/digger/forester/hunter/adventurer/builder/brewer, each as normal (15 slots) and woven (45 slots).

- Right-click opens the Forestry GUI (`backpack.png` / `backpack_t2.png`).
- Shift-right-click cycles Neutral → Locked → Receiving → Resupply.
- Locked bags do not auto-stow. Receiving + shift-use on a chest pulls matching items in; other modes dump the bag into the chest.
- Picking up a matching item tops off existing player stacks first, then stows into any matching bag.
- Resupply mode tops off matching stacks in the player inventory while no other GUI is open (`storage.enable_backpack_resupply` in `config/reforestry/server.properties`, default true).

`createNaturalistBackpack` still throws (B4). Crates are B5–B6.

## API (this pass)

- `api.ForestryTags.Items.*_ALLOW` / `*_REJECT` — CE `forestry.api.ForestryTags` backpack tags, namespace `reforestry`.
- `IBackpackInterface.createBackpack` / `createNaturalistBackpack` now take `Item.Properties` last. Minecraft 26.2 requires `Properties.setId` at construction (`FeatureItem` already supplies this). Not in CE’s 1.20 signature.
- `createBackpack` returns a real `ItemBackpack` (no longer throws).
- `BackpackMode` stays internal (CE does not put it in `api/`).

## What was adopted from Traveler’s Backpack

Copied/adapted into our packages, then stripped of wear/upgrades/tanks:

| TB | Re-Forestry |
|---|---|
| `ExtendedMenuProvider` open-GUI | `ItemBackpack` held-bag only |
| Insert-stacked helper | `BackpackInventoryHelper` |
| Pickup mixin on `ItemEntity.playerTouch` | `mixin.ItemEntityMixin` — Fabric has no pickup event |
| Refill / top-off loop | `BackpackResupplyHandler` (CE resupply mode, not a TB upgrade item) |
| Disabled/locked player slot | `ContainerBackpack.LockedSlot` |
| No nested backpacks in bag slots | `ItemInventoryBackpack.canSlotAccept` |

Chest I/O uses Fabric `ItemStorage.SIDED` + `StorageUtil.move` (same pattern as factory tanks), not TB’s custom handler.

## CE → Fabric notes

| CE | Re-Forestry |
|---|---|
| Damage value = mode | `reforestry:backpack_mode` data component |
| Forge `EntityItemPickupEvent` | Mixin (documented; no Fabric hook) |
| Forge `IItemHandler` chest hit | `ItemStorage.SIDED.find` |
| `forge:` tags | `c:` conventional tags (`tools/generate_backpack_data.py`) |
| ItemColor dual tint | 26.2 item model `minecraft:constant` tints + `reforestry:backpack_mode` select |
| `onItemUseFirst` | `useOn` while sneaking |

## Registry ids

`miner_bag`, `digger_bag`, `forester_bag`, `hunter_bag`, `adventurer_bag`, `builder_bag`, `brewer_bag`, plus `_woven` for each.

## Not copied from TB

Wearable backpack, Trinkets, placeable block, fluids/hose, upgrade tree, sleeping bag, abilities, TB networking/config.
