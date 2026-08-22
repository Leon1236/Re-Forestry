# Inventory — Tiviacz1337-Travelers-Backpack

- Clone path: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Graph alias: backpack
- Loader / MC version: Fabric Loader 0.19.2 / Minecraft 26.1.2 (mod `travelersbackpack` 11.2.9; Fabric API 0.146.0+26.1.2; Java 25; branch `26.1-fabric`)
- Date: 2026-07-30

## Modules

| id | slug | package / path | one-line purpose | estimated size (S/M/L) |
|---|---|---|---|---|
| 1 | advancements | `com.tiviacz.travelersbackpack.advancements` / `src/main/java/com/tiviacz/travelersbackpack/advancements` | Custom advancement triggers (e.g. backpack actions) | S |
| 2 | api | `com.tiviacz.travelersbackpack.api` / `src/main/java/com/tiviacz/travelersbackpack/api` | Public fluid-effect API surface (`EffectFluid`) | S |
| 3 | attachment | `com.tiviacz.travelersbackpack.attachment` / `src/main/java/com/tiviacz/travelersbackpack/attachment` | Worn-backpack player attachment + lookup helpers | S |
| 4 | block | `com.tiviacz.travelersbackpack.block` / `src/main/java/com/tiviacz/travelersbackpack/block` | Placeable backpack block + sleeping-bag block | S |
| 5 | blockentity | `com.tiviacz.travelersbackpack.blockentity` / `src/main/java/com/tiviacz/travelersbackpack/blockentity` | Placed backpack block entity (inventory / tanks host) | S |
| 6 | client | `com.tiviacz.travelersbackpack.client` / `src/main/java/com/tiviacz/travelersbackpack/client` | Screens, widgets, models, worn/item renderers, HUD/tooltips | L |
| 7 | commands | `com.tiviacz.travelersbackpack.commands` / `src/main/java/com/tiviacz/travelersbackpack/commands` | Admin/player commands for backpacks | S |
| 8 | common | `com.tiviacz.travelersbackpack.common` / `src/main/java/com/tiviacz/travelersbackpack/common` | Server actions, backpack abilities, crafting recipes | M |
| 9 | compat | `com.tiviacz.travelersbackpack.compat` / `src/main/java/com/tiviacz/travelersbackpack/compat` | Optional interop (Trinkets, Accessories, JEI/REI/EMI, graves, etc.) | L |
| 10 | component | `com.tiviacz.travelersbackpack.component` / `src/main/java/com/tiviacz/travelersbackpack/component` | Client/render-facing component helpers (e.g. `RenderInfo`) | S |
| 11 | config | `com.tiviacz.travelersbackpack.config` / `src/main/java/com/tiviacz/travelersbackpack/config` | Forge Config API Port settings | S |
| 12 | datagen | `com.tiviacz.travelersbackpack.datagen` / `src/main/java/com/tiviacz/travelersbackpack/datagen` | Fabric datagen (recipes, loot, tags) | S |
| 13 | entity | `com.tiviacz.travelersbackpack.entity` / `src/main/java/com/tiviacz/travelersbackpack/entity` | Mod entity type(s) tied to backpack features | S |
| 14 | fluids | `com.tiviacz.travelersbackpack.fluids` / `src/main/java/com/tiviacz/travelersbackpack/fluids` | Potion/milk fluids + drinkable effect registry | M |
| 15 | handlers | `com.tiviacz.travelersbackpack.handlers` / `src/main/java/com/tiviacz/travelersbackpack/handlers` | Event handlers: sleep, death, tick, keybinds, loot, clicks | M |
| 16 | init | `com.tiviacz.travelersbackpack.init` / `src/main/java/com/tiviacz/travelersbackpack/init` | Registry bootstrap (items, blocks, menus, components, network) | M |
| 17 | inventory | `com.tiviacz.travelersbackpack.inventory` / `src/main/java/com/tiviacz/travelersbackpack/inventory` | Wrapper, menus, handlers, fluid tanks, upgrade system | L |
| 18 | item | `com.tiviacz.travelersbackpack.item` / `src/main/java/com/tiviacz/travelersbackpack/item` | Backpack / hose / sleeping-bag items + upgrade items | M |
| 19 | mixin | `com.tiviacz.travelersbackpack.mixin` / `src/main/java/com/tiviacz/travelersbackpack/mixin` | Fabric mixins for player/entity/inventory hooks | M |
| 20 | network | `com.tiviacz.travelersbackpack.network` / `src/main/java/com/tiviacz/travelersbackpack/network` | Client/server packets (sync, slots, filters, actions) | M |
| 21 | util | `com.tiviacz.travelersbackpack.util` / `src/main/java/com/tiviacz/travelersbackpack/util` | Shared helpers (inventory, NBT/stacks, constants) | M |

## Out of scope for module agents

- Root package classes under `com.tiviacz.travelersbackpack` (`TravelersBackpack`, `TravelersBackpackClient`, `TravelersBackpackMixinPlugin`) — mod bootstrap / client entry / mixin plugin, not a feature package
- Build/CI (`build.gradle`, `gradle.properties`, `.github/`), docs (`README.md`, `CHANGELOG.md`), and license
- Non-Java assets under `src/main/resources` and `src/main/generated` unless a module deep-dive needs them as evidence
- Other repos — this inventory is Travelers-Backpack-only

## Notes for manager

- Fabric port on branch `26.1-fabric` (MC **26.1.2**, not 26.2). Soft deps include Trinkets/Accessories, JEI/REI/EMI, Comforts, graves mods, etc. under **compat**.
- Largest surfaces: **inventory** (~73 Java; upgrades + tanks + menus), **client** (~40; screens/models/render), **compat** (~35; Trinkets and friends). Sleeping is split across **block** (`SleepingBagBlock`), **item** (`SleepingBagItem`), and **handlers** (`SleepHandler`).
- Graph alias `backpack` → `python3 tools/graphify_query.py backpack "…"`.
- Module count: **21**.
