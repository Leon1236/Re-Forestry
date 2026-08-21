# Worktable W1–W2 — Fabric vs CE

## Networking

CE 1.21.1 uses three Forge packets (`PacketWorktableRecipeRequest`, `PacketWorktableMemoryUpdate`, `PacketWorktableRecipeUpdate`) plus `PacketGuiSelectRequest` for recall/clear/lock/conflict.

GUI actions still use vanilla `clickMenuButton` / `handleInventoryButtonClick` (same pattern as `ContainerNaturalistBackpack` / `ScreenForestry`). Recipe memory is saved on the block entity (`ValueOutput` / `ValueInput`) and synced with `ClientboundBlockEntityDataPacket` + `getUpdateTag` / `saveCustomOnly`. Ghost grid and storage slots sync through the menu.

Button ids (below access-ledger 200):

| Id | Action |
|---|---|
| 0–8 | Recall memorized recipe |
| 10–18 | Toggle lock on memorized recipe |
| 19 | Clear craft grid |
| 20 / 21 | Previous / next conflict recipe |

## W2 JEI transfer (landed)

Carpenter JEI transfer can use `clickMenuButton` because carpenter recipes have unique results. Vanilla crafting cannot — many recipes share the same output, so the server must see the 9 ghost ingredients.

| | CE 1.21.1 | Re-Forestry (Fabric 26.2) |
|---|---|---|
| Wire id | `forestry:worktable_recipe_request` (`PacketIdServer.WORKTABLE_RECIPE_REQUEST`) | `reforestry:worktable_recipe_request` |
| Class | `PacketWorktableRecipeRequest` (BlockPos + serialized `MemorizedRecipe`) | `WorktableRecipeRequestPayload` (BlockPos + 9 `ItemStack`s) |
| Client | `WorktableMenu.sendWorktableRecipeRequest` | `ClientPlayNetworking.send` from `WorktableRecipeTransferHandler` |
| Server | `WorktableTile.setCurrentRecipe(MemorizedRecipe)` | Open `ContainerWorktable` required; `TileWorktable.applyGhostCrafting` then `updateCurrentRecipeFromDisplay` |
| Recipe type | JEI `RecipeTypes.CRAFTING` | Same vanilla `minecraft:crafting` (`RecipeHolder<CraftingRecipe>`) |

CE also sends `PacketWorktableRecipeUpdate` / `PacketWorktableMemoryUpdate`. We keep W1 BE + menu slot sync instead of those S2C packets.

Register codec with `PayloadTypeRegistry.serverboundPlay()` (26.2 FAPI name; not `playC2S`). Receiver: `ServerPlayNetworking.registerGlobalReceiver` from `PacketRegistry.init()`.

`WorktableJeiPlugin` is a `jei_mod_plugin` entrypoint: transfer handler, crafting-station catalyst, click area `65,43,10,6` (CE `WorktableScreen`), and the existing `for.jei.description.worktable` text.

## 26.2 APIs

- Tile save uses `ValueInput` / `ValueOutput`, not `CompoundTag` (`ContainerHelper.saveAllItems` on ValueOutput).
- Crafting uses `CraftingInput` + `ServerLevel.recipeAccess().getAllMatches(RecipeType.CRAFTING, …)`. Client icon lookup uses `getSynchronizedRecipes()`.
- `RecipeHolder.id()` is `ResourceKey<Recipe<?>>` (stored as `Identifier`).
- `CraftingRecipe.assemble(CraftingInput)` — no `registryAccess`.
- No NeoForge `CommonHooks.setCraftingPlayer`. Remaining items come from `CraftingRecipe.getRemainingItems`.
- Chunk checks: `level.isLoaded(pos)` when sending BE updates.

## Skipped CE bits (out of scope or no Fabric equivalent)

- CE `DummyMenu` / `TransientCraftingContainer` — `WorktableCraftingContainer` is a `SimpleContainer(9)` + `CraftingInputHelper`.
- CE lock overlay sprite (`ForestrySprites.SLOT_LOCKED`) — locked slots use a dark fill instead.
- CE `GuiBetterButton` conflict arrows — simple drawn arrows + the same clickMenuButton ids.
- CE puts the block on the main Forestry tab. Re-Forestry uses a module tab (`itemGroup.worktable`) like factory/storage. `itemGroup.worktable` is in `en_us.json`.
- Hints `worktable=` is already empty in `hints.properties`; left unchanged.
