# thedarkcolour-gendustry — client

## Summary

Gendustry **client module** for NeoForge/Forge **1.20.1** + Forestry CE **2.10.0**. Scope is narrow: **screen registration and machine GUIs only** — no block-entity renderers, models, particles, or keybinds under `thedarkcolour.gendustry.client`. **10 Java files, ~426 LOC** split between one bootstrap class (`ClientHandler`) and seven screen classes in `client.screen` (plus two `package-info.java`).

All screens extend Forestry CE’s `GuiForestryTitled` and reuse CE widgets (`TankWidget`, `GuiBetterButton`) and ledger hooks (`addErrorLedger`, `addPowerLedger`, `addHintLedger`, `addClimateLedger`, `addOwnerLedger`). Machine logic lives in sibling packages (`menu`, `blockentity`); this package only binds `GMenus` → screen factories and draws progress bars, tank widgets, and a few interactive overlays.

## Player / API surface

Nothing here is a public addon API. Players interact through **eight registered container screens**:

| Menu (`GMenus`) | Screen class | Machines / role | GUI texture |
|---|---|---|---|
| `PROCESSOR` | `ProducerScreen` | Mutagen producer, DNA extractor, protein liquefier (shared layout) | `textures/gui/processor.png` |
| `SAMPLER` | `ThreeInputScreen` | Sampler | `textures/gui/sampler.png` |
| `IMPRINTER` | `ThreeInputScreen` | Imprinter | same `sampler.png` |
| `GENETIC_TRANSPOSER` | `ThreeInputScreen` | Genetic transposer | same `sampler.png` |
| `MUTATRON` | `MutatronScreen` | Mutatron | `textures/gui/mutatron.png` |
| `ADVANCED_MUTATRON` | `AdvancedMutatronScreen` | Advanced mutatron (choice slots + paging) | `textures/gui/advanced_mutatron.png` |
| `REPLICATOR` | `ReplicatorScreen` | Replicator (dual tanks) | `textures/gui/replicator.png` |
| `INDUSTRIAL_APIARY` | `IndustrialApiaryScreen` | Industrial apiary (queen health bar) | `textures/gui/apiary.png` |

**Bootstrap (mod integrators only):**

| Type | Role |
|---|---|
| `ClientHandler` | Implements Forestry `IClientModuleHandler`; registers all `MenuScreens` on `FMLClientSetupEvent` via `enqueueWork`. Wired from `GendustryModule.registerClientHandler`. |

**Per-screen player-visible behavior:**

- **Progress bars** — horizontal strips blitted from texture atlas extension at `u=176` (coordinates vary per machine).
- **Fluid tanks** — `TankWidget` on mutatron family, producers, replicator (tank index 0/1).
- **Ledgers** — error, power, hints on most machines; industrial apiary adds owner + climate ledgers.
- **Advanced mutatron** — left/right paging buttons (visible when >4 mutations), clickable choice slots with selected/hover chrome, server sync via `handleInventoryButtonClick`.
- **Industrial apiary** — queen health bar; tooltip shows percent; bar fill **inverts** when a queen (not princess) occupies the slot.
- **Producer** — optional labware icon overlay when `tile.usesLabware`.

## Architecture

```
GendustryModule.registerClientHandler(Consumer<IClientModuleHandler>)
        │
        ▼
ClientHandler.registerEvents(modBus)
        └── FMLClientSetupEvent → enqueueWork
                └── MenuScreens.register(GMenus.*, Screen::new)  × 8

Forestry CE GuiForestryTitled<M>
        │
        ├── AbstractMutatronScreen<M> ──► MutatronScreen
        │                              └── AdvancedMutatronScreen (+ buttons, choice overlays)
        ├── ThreeInputScreen ──► SAMPLER | IMPRINTER | GENETIC_TRANSPOSER
        ├── ProducerScreen ──► PROCESSOR (3 producer block entities)
        ├── ReplicatorScreen
        └── IndustrialApiaryScreen (+ climate/owner ledgers, health tooltip)

Each screen:
  constructor → bind texture, cache tile from menu
  drawWidgets → progress blit (+ labware / choice chrome)
  addLedgers → error / power / hint (+ climate / owner on apiary)
  static { HINTS.putAll(hintsKey, TranslationKeys…) }  // CE hint registry
```

**Screen reuse pattern:** Gendustry minimizes GUI classes by sharing one screen per **layout family**, not per block. `ThreeInputScreen` and `ProducerScreen` branch on tile type only for hint keys (via `getHintsKey()` / static `HINTS` maps). `AbstractMutatronScreen` centralizes mutagen tank + progress for both mutatron tiers.

**Advanced mutatron client–server contract** (implemented in `AdvancedMutatronScreen` + `AdvancedMutatronMenu`, outside this package but required to understand the screen):

- `BUTTON_CYCLE_LEFT` / `BUTTON_CYCLE_RIGHT` — paging when possibilities > 4.
- `CHOICE_CLICKED + choiceIndex` — sent from `slotClicked` on `ChoiceSlot`.
- `menu.setDataListener(this::updateButtonVisibility)` — client reacts to synced `ContainerData`.

Graphify (alias `gendustry`): `ClientHandler` community 15, linked to `GendustryModule`, `GMenus`, and all screen classes. `ThreeInputScreen` community 2 (tight with `ThreeInputMenu` and sampler/imprinter/transposer tiles). `GuiForestryTitled` is an external CE node — confirms zero local GUI framework.

## Data & assets

**GUI textures** (`assets/gendustry/textures/gui/`):

| File | Used by client code |
|---|---|
| `processor.png` | `ProducerScreen` |
| `sampler.png` | `ThreeInputScreen` (all three three-input machines) |
| `mutatron.png` | `MutatronScreen` |
| `advanced_mutatron.png` | `AdvancedMutatronScreen` |
| `replicator.png` | `ReplicatorScreen` |
| `apiary.png` | `IndustrialApiaryScreen` |
| `extractor.png`, `imprinter.png`, `liquifier.png`, `transposer.png`, `mutatron_advanced.png` | **Present in assets; not referenced by this package** |

**Error atlas icons** (`assets/gendustry/textures/forestry/atlas/gui/errors/`): 12 PNGs (`no_dna`, `no_mutagen`, `no_protein`, `no_samples`, etc.) — consumed by Forestry CE error ledger, not loaded directly here.

**Hints:** Screen static blocks register hint **ids** (not full translation keys) into CE `GuiForestry.HINTS` via `TranslationKeys.HINT_*` constants from `thedarkcolour.gendustry.data`. Actual lang strings live in datagen/lang (outside this package).

**Progress / overlay UV contract:** Atlas extension region starts at `(176, 0)` or `(176, 60)` depending on screen; advanced mutatron choice chrome uses `v ∈ {176, 194, 212}` for normal / selected / hover.

No models, blockstates, sounds, or shaders in this package.

## Dependencies

**Within Gendustry (sibling packages — not part of this report, but required at runtime):**

| Package | Used for |
|---|---|
| `registry.GMenus` | Menu type tokens for `MenuScreens.register` |
| `menu.*` | Screen type parameters, tile access, advanced mutatron button ids |
| `blockentity.*` | Tile references, `HINTS_KEY`, progress/health scaling |
| `data.TranslationKeys` | Hint id constants |
| `Gendustry.loc(...)` | ResourceLocation for textures |

**Forestry CE (hard dependency — do not ship without Forestry):**

| CE type | Usage |
|---|---|
| `forestry.api.client.IClientModuleHandler` | Client bootstrap contract |
| `forestry.core.gui.GuiForestryTitled` | Base screen for all machines |
| `forestry.core.gui.widgets.TankWidget` | Fluid tank rendering |
| `forestry.core.gui.buttons.GuiBetterButton` / `StandardButtonTextureSets` | Advanced mutatron paging |
| `forestry.core.config.Constants.TEXTURE_PATH_GUI` | Texture path prefix |
| `forestry.core.tiles.TilePowered` | Progress source for three-input machines |
| `forestry.api.apiculture.genetics.BeeLifeStage` | Queen vs princess health bar |
| `forestry.api.genetics.capability.IIndividualHandlerItem` | Queen detection on apiary screen |

**Minecraft / loader:**

- `MenuScreens`, `GuiGraphics`, `AbstractContainerScreen` slot/button APIs.
- Forge `IEventBus`, `FMLClientSetupEvent`, `enqueueWork`.
- `MultiPlayerGameMode.handleInventoryButtonClick` for menu buttons.

**Not a Re-Forestry dependency** — adopt patterns into `reforestry:gendustry` when that addon ships (`reforestry-standalone-adopt`).

## Notable algorithms / contracts

1. **Deferred screen registration** — All `MenuScreens.register` calls run inside `FMLClientSetupEvent.enqueueWork` to avoid early client class loading (standard Forge/Forestry addon pattern).

2. **Atlas progress blit** — `tile.getProgressScaled(maxPixels)` drives partial-width `graphics.blit(texture, x, y, 176, v, progress, 18)` — same CE convention as factory machines.

3. **Static hint registration** — Each screen class registers its hint lists once in a `static` block keyed by block entity `HINTS_KEY`. Multiple keys can map to overlapping lists (e.g. `AbstractMutatronScreen` registers both mutatron hint ids).

4. **Three-input shared texture** — Sampler, imprinter, and genetic transposer share `sampler.png` and `ThreeInputScreen`; per-machine differentiation is title + hints only (not separate backgrounds).

5. **Producer labware overlay** — When `ProducerBlockEntity.usesLabware`, draws 18×18 icon at `(63, 18)` from atlas `(176, 78)`.

6. **Advanced mutatron choice UI** — Renders up to four 16×18 choice frames; selected row uses `menu.getSelected() == i + menu.getOffset()`; hover uses mouse bounds; click delegates to server via inventory button id `CHOICE_CLICKED + index`.

7. **Industrial apiary health bar** — `getHealthScaled(pixels)` with queen inversion: if stack is `BeeLifeStage.QUEEN`, progress becomes `pixels - progress` (bar drains as queen ages). Tooltip on hover shows integer percent.

8. **Ledger stack** — `addLedgers()` order: error → power → hints (→ owner → climate on apiary). Matches CE machine GUI conventions.

## Port relevance to Re-Forestry

| Gendustry client piece | Re-Forestry target | Verdict |
|---|---|---|
| `ClientHandler` + `IClientModuleHandler` | Module client entrypoint + `MenuScreens.register` (see `FactoryClientHandler`, `ApicultureClientHandler`) | **Direct pattern** — add `GendustryClientHandler` under `reforestry:gendustry` client entrypoint when addon starts |
| `GuiForestryTitled` subclass screens | `ScreenForestry` subclasses in `com.leon1236.reforestry.gendustry.client` | **Port screen-by-screen** — RF already has ledger stack (`GuiPowerLedger`, `GuiClimateLedger`, `GuiHintLedger`) and tank click regions |
| `TankWidget` | `ScreenForestry.addTankClickRegion` + tooltip draw | **Replace widget** — RF does not use CE `TankWidget`; map tank coords from gendustry screens |
| `HINTS.putAll` static blocks | `ForestryHints` + `hints.properties` + `setHintKey` | **Change registration style** — same ids, data-driven properties instead of CE static map |
| `GuiBetterButton` + `handleInventoryButtonClick` | Vanilla `Button` + RF menu `clickMenuButton` / custom packet | **Rewrite for Fabric 26.2** — advanced mutatron paging needs matching server menu support |
| Texture paths `gendustry:textures/gui/*` | `assets/reforestry/textures/gui/gendustry/*` (or CE-parity paths) | **Copy assets** — decide whether to use unused per-machine textures or keep shared `sampler.png` |
| Error ledger icons | RF error ledger / `ForestryHints` or CE-parity atlas | **Depends on RF error system** when gendustry machines expose `GendustryError` states |
| Queen health inversion | Same genetics API once industrial apiary exists | **Reuse logic** — port `getProgress` verbatim when bee stages match RF genetics |

**Priority when porting gendustry addon:** (1) `ClientHandler` registration skeleton, (2) `ProducerScreen` + `ThreeInputScreen` (simplest progress bars), (3) `ReplicatorScreen` + mutatron family, (4) `AdvancedMutatronScreen` (only non-trivial interactivity), (5) `IndustrialApiaryScreen` (climate/owner ledgers already exist in RF).

Net: this package is **thin CE-style screen glue** — low risk, high parity value. Do not depend on Gendustry jar; copy/adapt into `reforestry:gendustry.client` mirroring existing RF module client handlers.

## Source map

Clone root: `MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`

| File | Lines | Notes |
|---|---|---|
| `.../client/ClientHandler.java` | 37 | `IClientModuleHandler`; 8 `MenuScreens.register` calls |
| `.../client/package-info.java` | 3 | Nullability annotations |
| `.../client/screen/AbstractMutatronScreen.java` | 48 | Base mutatron GUI; tank + progress + hints |
| `.../client/screen/MutatronScreen.java` | 15 | Texture binding only |
| `.../client/screen/AdvancedMutatronScreen.java` | 84 | Paging buttons, choice overlays, slot click → menu button |
| `.../client/screen/ThreeInputScreen.java` | 60 | Shared sampler/imprinter/transposer screen |
| `.../client/screen/ProducerScreen.java` | 63 | Shared producer screen; labware overlay |
| `.../client/screen/ReplicatorScreen.java` | 51 | Dual tanks, progress bar |
| `.../client/screen/IndustrialApiaryScreen.java` | 62 | Health bar, climate/owner ledgers, tooltip |
| `.../client/screen/package-info.java` | 3 | Nullability annotations |

**Related (outside `client`, cited for wiring only):**

- `.../GendustryModule.java` — `registerClientHandler(new ClientHandler())`
- `.../registry/GMenus.java` — menu types bound to screens
- `.../menu/AdvancedMutatronMenu.java` — button ids, `ChoiceSlot`, synced data
- `.../data/TranslationKeys.java` — hint id constants
- Forestry CE `forestry/core/gui/GuiForestryTitled.java` — screen base class

## Open questions / gaps

- **Unused GUI PNGs** — `extractor.png`, `imprinter.png`, `liquifier.png`, `transposer.png`, `mutatron_advanced.png` exist under assets but no Java reference. Intentional future split, or leftover art? Confirm before porting textures.
- **Three-input texture sharing** — Imprinter and genetic transposer use `sampler.png`. Acceptable for parity, or should RF use dedicated backgrounds?
- **No client rendering beyond screens** — Industrial apiary block animation, item models, JEI ghost overlays, and BER are absent here; Phase 2 must scan other packages (`compat/jei`, block models, etc.).
- **CE `GuiForestry.HINTS` vs RF `ForestryHints`** — Gendustry registers at class-load time into CE static map; RF loads from `hints.properties`. Need a gendustry hint section in properties (or datagen) before screens work.
- **Advanced mutatron on Fabric** — Requires `ContainerData` sync + menu button handling equivalent to Forge `clickMenuButton`; verify RF menu base supports the same protocol before porting screen.
- **Error ledger icons** — 12 custom error textures under `forestry/atlas/gui/errors/`; confirm RF error ledger can resolve gendustry-specific `GendustryError` entries and atlas paths.
- **MC version gap** — Clone targets **1.20.1 Forge**; Re-Forestry targets **26.2 Fabric**. Screen APIs (`GuiGraphics`, slot click signatures) differ slightly — re-verify method names against MCP `Minecraft-26.2` at port time, not from this report alone.
