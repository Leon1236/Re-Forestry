# thedarkcolour-ModKit — item

## Summary

Package `thedarkcolour.modkit.item` ships five creative/dev wand items for world editing and entity cleanup. Three share an abstract two-corner fill/undo base (`AbstractFillWand`); two are standalone (`DistanceWandItem`, `KillWand`). Registration, creative tab, and datagen live outside this package in root `ModKit` / `ModKitDataGen`. ~494 LOC across 7 files (including `package-info`). Forge 1.20.1 / Minecraft 1.20.1 (inventory). Graph alias `modkit`: fill-family clustered around `AbstractFillWand` (community 3); kill/distance closer to community 13.

## Player/API surface

There is **no public API interface** in this package — only concrete `Item` subclasses. Players interact via registered items under modid `modkit`:

| Registry id | Class | Rarity | Role |
|---|---|---|---|
| `modkit:fill_wand` | `FillWandItem` | RARE | Pick filler block (sneak-use-on), then two corners → fill |
| `modkit:clear_wand` | `ClearWandItem` | EPIC | Two corners → fill with air |
| `modkit:clone_wand` | `CloneWandItem` | UNCOMMON | Sneak two corners → save structure; normal click → paste |
| `modkit:distance_wand` | `DistanceWandItem` | EPIC | Two clicks → print XYZ span |
| `modkit:kill_wand` | `KillWand` | EPIC | Hit living entity → instant kill, no XP, no slime children |

Shared fill-wand controls (server-side):

- **Use on block** → `handleUse` (subclass-specific).
- **Use in air, sneak** → clear NBT `StartPos`.
- **Use in air, not sneak, with pending undo** → bow-charge (`UseAnim.BOW`, 40 ticks) then restore last region.
- Tooltip shows `StartPos` when set; name gets a trailing `*` while a start corner is stored.
- Cooldowns: fill/clear 5 ticks after apply; clone paste 25 ticks.

Creative tab `itemGroup.modkit` lists all five (icon = clone wand). No recipes, no advancement gates, no permission checks — anyone holding the item can use it.

## Architecture

```
Item (vanilla)
├── AbstractFillWand          // StartPos NBT, region fill, in-memory undoMap
│   ├── FillWandItem          // + FillBlock NBT; sneak picks BlockState
│   ├── ClearWandItem         // fill with Blocks.AIR
│   └── CloneWandItem         // + structureMap (relative BlockPos → BlockState)
├── DistanceWandItem          // StartPos only; measures inclusive axis lengths
└── KillWand                  // hurtEnemy / canAttackBlock overrides
```

Call flow (fill family):

1. `useOn` → server → abstract `handleUse(level, stack, pos, player)`.
2. Subclass either `saveStartPos` (writes `StartPos` via `NbtUtils.writeBlockPos`) or `fill` / paste.
3. `fill` iterates `BlockPos.betweenClosed(start, end)`, snapshots old states into `ImmutableMap`, `level.setBlock(..., 2)`, stores map in `undoMap` keyed by `Player`, clears `StartPos`.
4. Undo: `finishUsingItem` walks `undoMap` and restores with `setBlock(..., 2)`.

`CloneWandItem` diverges: sneak-second-corner builds **relative** positions (`mutable.subtract(start)`); paste uses `setBlockAndUpdate` and writes its own undo snapshot into the inherited `undoMap`. Structure data is **not** on the ItemStack — only in `structureMap` on the Item instance.

Registration (outside package): `DeferredRegister<Item>` in `ModKit.java` (`FILL_WAND` … `KILL_WAND`). Datagen: `ModKitDataGen` → `DataHelper.createItemModels(true, true, false, null)` auto-generates 2D item models; English names auto-derived by `MKEnglishProvider` (`generateNames=true`).

## Data & assets

| Kind | Location | Notes |
|---|---|---|
| Item textures | `src/main/resources/assets/modkit/textures/item/{fill,clear,clone,distance,kill}_wand.png` | Hand-authored |
| Item models | `src/generated/resources/assets/modkit/models/item/*.json` | `parent: minecraft:item/generated`, `layer0: modkit:item/<id>` |
| Lang | `src/generated/.../lang/en_us.json` | `item.modkit.*_wand` display names; tab title hand-added |
| Stack NBT keys | on ItemStack (1.20.1 tag API) | `StartPos` (Compound, BlockPos); `FillBlock` (Compound, BlockState) — fill wand only |
| In-memory state | Item fields | `AbstractFillWand.undoMap: Map<Player, Map<BlockPos,BlockState>>`; `CloneWandItem.structureMap: Map<Player, ImmutableMap<…>>` |

No loot tables, tags, or recipes for these items. No client-only classes in the package.

## Dependencies

| Depends on | Used for |
|---|---|
| Vanilla `Item` / `ItemStack` / `UseOnContext` / `UseAnim` | Core item behavior |
| `NbtUtils` read/write BlockPos & BlockState | Corner / filler persistence on stack |
| `Registries.BLOCK` holder lookup | Deserializing `FillBlock` |
| Guava `ImmutableMap` | Undo / structure snapshots |
| JetBrains `@Nullable` | Annotations |
| `package-info` nullness | `@ParametersAreNonnullByDefault` / `@MethodsReturnNonnullByDefault` |

**Does not depend on** ModKit `data` helpers at runtime (datagen only from root). No Forge capabilities, networking, or configs in this package. Soft coupling: registered and tabbed from `ModKit`; lang/models from `data` providers.

## Notable algorithms/contracts

1. **Region fill** — inclusive AABB via `BlockPos.betweenClosed`; flag `2` on `setBlock` (client update, no neighbor/shape updates). Clone paste uses `setBlockAndUpdate` instead.
2. **Undo** — one pending region per `Player` identity on the Item instance; holding use finishes at 40 ticks. Cleared after restore. Not serialized; lost on restart / item instance replace.
3. **Fill wand pick** — sneak-use-on stores full `BlockState` (properties included) as `FillBlock`. Apply requires both `StartPos` and `FillBlock`.
4. **Clone** — saved map keys are offsets from first corner; paste anchors at clicked pos. Empty `structureMap` for player → silent no-op on normal click.
5. **Distance** — per-axis: if equal → `0`, else `abs(delta) + 1` (inclusive count). Message not action-bar (`displayClientMessage(..., false)`).
6. **Kill** — `skipDropExperience()`, `kill()`, `setHealth(0)`; if `Slime`, `setSize(0, false)`. `canAttackBlock` returns `!player.isCreative()` (allows breaking blocks outside creative — unusual for a “wand”).
7. **Tooltip contract mismatch** — tip says sneak-air undoes; code: sneak-air clears `StartPos`, **non-sneak** air + pending undo starts hold-to-undo.

## Port relevance to Re-Forestry

**Low priority as a product feature** — these are ModKit’s own creator tools, not Forestry content. Do **not** add ModKit as a dependency (standalone-adopt rule).

Worth stealing **patterns** only if Re-Forestry wants in-dev world helpers or similar item UX:

| Pattern | Port note for MC 26.2 / Fabric |
|---|---|
| Two-corner + NBT corner marker | Replace `getTagElement` / `addTagElement` with `DataComponentType` (Re-Forestry already uses components for genetics) |
| In-memory `Map<Player, …>` undo | Prefer UUID keys + clear on disconnect; or store undo on server-level attachment — Player-as-key is fragile |
| Bow-charge confirm for destructive undo | Portable; use Fabric/vanilla use-duration APIs (signatures differ on 26.2 — verify in MCP `Minecraft-26.2`) |
| Auto item models + auto English from registry | Belongs to ModKit **`data`** module report, not here; Re-Forestry already has its own feature registration |
| Instant-kill wand | Not relevant to Forestry gameplay |

No Re-Forestry class currently mirrors these wands. Closest existing habit: item-bound state via data components (`ApicultureDataComponents`, `CoreDataComponents`, etc.).

## Source map

| Path | Role |
|---|---|
| `…/item/AbstractFillWand.java` | Shared start/fill/undo/tooltip/name/use animation |
| `…/item/FillWandItem.java` | Filler-block pick + region fill |
| `…/item/ClearWandItem.java` | Region clear to air |
| `…/item/CloneWandItem.java` | Copy/paste structure + undo |
| `…/item/DistanceWandItem.java` | Two-point measure |
| `…/item/KillWand.java` | Instant entity kill |
| `…/item/package-info.java` | Package nullness defaults |
| `…/ModKit.java` (root) | DeferredRegister + creative tab (out of package) |
| `…/ModKitDataGen.java` (root) | Item models + English (out of package) |
| `assets/modkit/textures/item/*_wand.png` | Textures |
| `src/generated/.../models/item/*_wand.json` | Generated models |
| `src/generated/.../lang/en_us.json` | Generated names |

Graph: `python3 tools/graphify_query.py modkit "…"`. Inheritance edge: `FillWandItem`/`ClearWandItem`/`CloneWandItem` → `AbstractFillWand` → `Item`.

## Open questions/gaps

- Intentional that undo tip contradicts sneak-clear behavior, or stale copy?
- `undoMap` / `structureMap` keyed by `Player` — multiplayer disconnect / dimension change / creative-copy item edge cases unhandled; also shared across all stacks of the same registered Item singleton.
- No max region size — large `betweenClosed` can stall the server.
- Fill uses flag `2` vs clone `setBlockAndUpdate` — inconsistent neighbor/TE updates; TE/block-entity contents never copied on clone.
- `ClearWandItem.appendHoverText` is a no-op override of super only.
- Class naming: `KillWand` vs `*WandItem` siblings.
- 26.2 Item method renames / data-component migration not validated here (would need MCP pass if porting).
- Whether these tools should remain in a published ModKit jar vs testmod-only is a product question outside this package.
