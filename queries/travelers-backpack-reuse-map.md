# Traveler’s Backpack (Fabric) → Re-Forestry storage reuse map

**Date:** 2026-07-30  
**Policy:** CE = **WHAT**. Fabric TB = **HOW** (source to **adopt into our packages**).  
**Related:** [`storage-B0-api.md`](storage-B0-api.md), Track B in [`item-gap-implementation-plan.md`](item-gap-implementation-plan.md)

## Non-negotiable

1. **No runtime dependency on Traveler’s Backpack.** Storage must work with only `reforestry` installed. Do not put `travelersbackpack` in `fabric.mod.json` `depends` / `suggests` for this feature.
2. **Adopt = copy/adapt TB Java into `com.leon1236.reforestry.*`**, rename, strip TB product features, tailor to CE. TB is a reference clone under `MarkDown_Maker/`, not a library.
3. **API-first:** finish/use [`api/storage`](../src/main/java/com/leon1236/reforestry/api/storage/) (CE-shaped) before/as the first step of bringing bags alive. Impl dogfoods that API. Do not invent a TB-style public API.
4. **Project rule:** [`.cursor/rules/reforestry-standalone-adopt.mdc`](../.cursor/rules/reforestry-standalone-adopt.mdc) — all adopted external code must stay free of donor-mod dependencies unless the change is intentional interop.

```text
api.storage (public, CE)  →  storage.* (impl: adopted TB + CE behavior)
                                  ↑
                    TB clone (reference only; never soft-dep)
```

---

## Sources

| Role | Location |
|------|----------|
| Fabric TB (adopt source from here) | `MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` — MC **26.1.2**, Fabric, mod **11.2.9** |
| Forge TB (obsolete) | `…/2026-07-28_18-01-07/…` — do **not** use |
| MCP / graphify | `Tiviacz1337-Travelers-Backpack`; `MarkDown_Maker/graphify/Tiviacz1337-Travelers-Backpack` |
| CE guide | MCP `thedarkcolour-ForestryCE` → `forestry/storage/`, `forestry/api/storage/` |
| Re-Forestry B0 | `ItemInventory`, Alyzer open-menu, `api.storage/*`, `BackpackFilterNaturalist` |

TB Java prefix:  
`…/Tiviacz1337-Travelers-Backpack/src/main/java/com/tiviacz/travelersbackpack/`

**Version caution:** TB is 26.1; Re-Forestry is 26.2 — verify against MCP `Minecraft-26.2`.

---

## API-first — live in *our* mod only

### Already in `api.storage` (B0)

| Type | Role |
|------|------|
| `EnumBackpackType` | NORMAL / WOVEN / NATURALIST |
| `IBackpackDefinition` | name, primary/secondary colour, filter |
| `IBackpackInterface` | `createBackpack`, `createNaturalistBackpack`, `createNaturalistBackpackFilter` |
| `BackpackEvents` | Fabric STOW / RESUPPLY (return true to cancel) |

This matches CE’s public `forestry.api.storage` surface (Forge events → `BackpackEvents`).

### Before / as B1 (API gate)

- Implement real items so `BackpackInterface` **stops throwing** on `createBackpack` / `createNaturalistBackpack`.
- Register bag definitions through the same interface/module path CE uses.
- Keep `BackpackMode` **internal** to `storage` (CE does not put mode in `api/`) unless an addon clearly needs it later.
- Do **not** expose TB wear/upgrades/fluids/`EffectFluid` under `api.storage`.

### Where adopted TB code goes

Impl only, e.g. `com.leon1236.reforestry.storage.items.ItemBackpack`, `…gui.ContainerBackpack`, `…inventory.ItemInventoryBackpack`. No `com.tiviacz.*` in shipped code.

---

## High-value TB code to adopt (copy into RF)

| TB class | Becomes in RF | CE forces you to change |
|----------|---------------|-------------------------|
| `inventory/BackpackContainer.java` | RF open-GUI helper | Drop wearable IDs; held bag only |
| `init/ModScreenHandlerTypes.java` | `StorageMenuTypes` | ids `backpack` / `naturalist_backpack` |
| `inventory/menu/BackpackItemMenu` + base menus | `ContainerBackpack` | Strip upgrades/tools/tanks; **3×5 / 5×9** |
| `inventory/menu/slot/DisabledSlot.java` | Same idea in RF | Keep |
| `inventory/menu/slot/BackpackSlotItemHandler.java` | Filtered / no-nest slot | CE definition filter |
| `init/ModDataComponents.java` | RF `backpack_mode` component | Four CE modes only |
| `util/ContainerContentsHelper.java` | Optional | B0 already uses `CONTAINER` |
| `util/InventoryHelper.java` | RF storage insert/extract | Stow + chest transfer |
| `item/TravelersBackpackItem.java` | `ItemBackpack` (partial) | No BlockItem / place / equip |
| `mixin/ItemEntityMixin.java` | RF pickup (prefer Fabric event) | CE stow + `BackpackEvents.STOW` |
| `inventory/upgrades/refill/RefillUpgrade.java` | Resupply **algorithm** only | Mode RESUPPLY; not an upgrade item |
| `inventory/upgrades/pickup/` + filter TagKey reload | Pickup / tag patterns | CE definition tags |
| `client/screens/BackpackScreen.java` | RF backpack screen wiring | CE `backpack.png` / `backpack_t2.png` |

### Medium value

- TagKey filter reload → CE allow/reject tags  
- Client tint registration → CE dual-layer bag colors  
- Bulk register style → CE bag ids via RF `FeatureGroup`

---

## No TB analog — implement from CE

| CE feature | Notes |
|------------|--------|
| Bag list + definitions | `BackpackItems` / `BackpackDefinition` |
| Modes NEUTRAL / LOCKED / RECEIVE / RESUPPLY | CE damage-meta → RF data component |
| Naturalist 125-slot paged GUI | CE containers + paged inventory |
| Crates | `ItemCrated`, filled model — **zero** TB code |
| Forestry GUI / textures / lang | RF assets + `for textures only/…` |

---

## Implement order

0. **API gate** — `api.storage` ready; no `travelersbackpack` mod dependency; interface will return real items once B1 lands  
1. Mode component + internal `BackpackMode`  
2. `ItemInventoryBackpack` on B0 `ItemInventory`  
3. Menus/screens (adopted TB registration + CE layout)  
4. `ItemBackpack` + wire `IBackpackInterface.createBackpack`  
5. Pickup + resupply (adopted algorithms, CE rules, events)  
6. Register CE bags/definitions  
7. Naturalist + `createNaturalistBackpack`  
8. Crates — CE only  

---

## Do not copy / do not depend on

- Runtime or compile dependency on the Traveler’s Backpack **mod**
- Wear / attachment / Trinkets, placeable backpack BE, fluids/hose/tanks  
- Upgrade tree (except pickup/refill **algorithms**), sleeping bag, abilities, death/commands  
- Most of TB `network/**`, Forge Config API Port mega-config  

---

## Bottom line

**Adopt TB source into Re-Forestry** for Fabric menu/open, components, slots, insert/extract, pickup, refill algorithms.  
**Own API under `api.storage`** so addons and the game never need TB installed.  
**Follow CE** for 15/45/125, modes, filters, named bags, naturalist paging, crates, Forestry GUIs.
