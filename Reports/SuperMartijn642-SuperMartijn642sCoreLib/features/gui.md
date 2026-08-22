# SuperMartijn642-SuperMartijn642sCoreLib — gui

## Summary

Largest CoreLib feature package: **widget-first GUI toolkit** for Forge **1.16.5** (`supermartijn642corelib` 1.1.22). Covers server containers (`BaseContainer` hierarchy + builder-style `CustomSlot`), client screens that host a single root `Widget` (`WidgetScreen` / `WidgetContainerScreen`), a nested widget tree (`Widget` → `BaseWidget` → object/item/BE specializations + premade controls), and shared draw helpers (`ScreenUtils`, GLFW cursors). **28 Java files, ~4122 LOC** under `com.supermartijn642.core.gui` (+ `widget` / `widget.premade`). Ships five GUI textures under `assets/supermartijn642corelib/textures/gui/`. Designed so dependent mods share similar GUI code across older MC lines; this clone is Forge-only and not a Re-Forestry dependency.

## Player/API surface

Public types other mods extend or call:

| Type | Role |
|---|---|
| `Widget` | Client widget contract: size/pos, `initialize`/`update`/`discard`, layered render (`background` → `render` → `foreground` → `overlay` → `tooltips`), focus, cursor, mouse/key/`charTyped` with `hasBeenHandled` chaining, narration message. |
| `BaseWidget` | Default composite: child list, hover-focus, narrator delay, event fan-out (focused child first), tooltip gather via `getTooltips(Consumer)`. Rejects nesting `ContainerWidget` into a plain widget. |
| `ContainerWidget<T>` / `BaseContainerWidget<T>` | Widgets that must `initialize(container)`; can nest other container widgets. |
| `ObjectBaseWidget` / `ObjectBaseContainerWidget` | Keep a typed `object` alive via `getObject` + `validateObject`; close screen/container if invalid (`alwaysRenewObject` option). |
| `BlockEntityBaseWidget` / `BlockEntityBaseContainerWidget` | Object = BE at fixed `BlockPos` / level; invalid when removed. |
| `ItemBaseWidget` / `ItemBaseContainerWidget` | Object = `ItemStack` from slot/hand supplier + predicate. |
| Premade: `AbstractButtonWidget`, `ButtonWidget`, `LabelWidget`, `TextFieldWidget`, `ScrollbarWidget`, `ScissorWidget` | Buttons (sound + pointing cursor), labels, edit box, value scrollbar (builder), scissor clip of children. |
| `WidgetScreen<T>` / `WidgetContainerScreen<T,X>` | Vanilla `Screen` / `ContainerScreen` wrappers: center widget, remap mouse into widget space, inventory-key close, optional pause. Container variant draws slots (incl. custom size), floating item, Forge draw events, cursor request. |
| `BaseContainer` / `BaseContainerType<T>` | `Container` + player/level; `addSlots` / `addPlayerSlots`; type factory with PacketBuffer serialize/deserialize (`IContainerFactory`). |
| `ObjectBaseContainer` / `BlockEntityBaseContainer` / `ItemBaseContainer` | Same validate-or-close pattern as widgets, on the server menu. |
| `CustomSlot` / `CustomSlotImpl` | Builder slot: getter/setter/inserter/extractor/capacity/filter/listeners; vanilla inventory, player inventory, or Forge `IItemHandler`; variable width/height; show/hide background/item/highlight; `move` / `setActive`. |
| `ScreenUtils` | Text (plain/centered/shadow), 9-slice screen/button backgrounds, textured quads, fill rect, tooltip layout (ported from vanilla), matrix-aware `withScissor`, `requestCursor`. |
| `CursorType` / `CursorTypes` | GLFW standard cursors (arrow, I-beam, crosshair, hand, V/H resize); pending cursor applied once per frame via mixin. |

Typo in API: `Widget.curser(...)` (used consistently).

## Architecture

```
                    ┌─────────────────────────────┐
                    │ BaseContainerType.create()  │  PacketBuffer ser/deser
                    └──────────────┬──────────────┘
                                   ▼
 BlockEntity / Item / Object ──► BaseContainer (+ CustomSlot builder)
                                   │
                                   ▼ open menu
              WidgetContainerScreen ◄── root Widget / ContainerWidget
              WidgetScreen (no slots)
                                   │
                     centered; mouse offset into widget coords
                                   │
              BaseWidget children ──► premade (Button, TextField, Scrollbar, Scissor, Label)
                                   │
              ScreenUtils draw + CursorTypes.requestCursor
                                   │
              GameRendererMixin TAIL → CursorTypes.applyPending()
              AbstractContainerScreenMixin → CustomSlot hover size
```

**Render layers (container screen):** widget background → optional slot backgrounds → Forge `DrawBackground` → widget main → slots/items/highlight → widget foreground → vanilla slot tooltips → Forge `DrawForeground` → floating/snapback item → widget overlay → widget tooltips.

**Focus model:** while the root is focused and not dragging, the first child under the mouse becomes `focusedWidget` (drawn last / receives events first). Narration fires ~750 ms after focus if no child holds focus.

**Object lifecycle:** `validateObjectOrClose()` re-fetches when invalid or `alwaysRenewObject`; failure closes via `CommonUtils.closeContainer` (menu) or `ClientUtils.closeScreen` (widget).

Graphify (alias `corelib`): `Widget`/`BaseWidget` community ~1; `WidgetScreen` ~40; `WidgetContainerScreen` ~41; `ScreenUtils` ~43; `BaseContainer`/`BaseContainerType` ~6; premade widgets in separate communities (button ~75, text field ~22, scrollbar ~64, label ~82). Path `WidgetScreen`↔`BaseWidget` is short via shared Screen/widget imports.

## Data & assets

Under `src/main/resources/assets/supermartijn642corelib/textures/gui/`:

| Asset | Used by |
|---|---|
| `background.png` | `ScreenUtils.drawScreenBackground` (9-slice, 9×9 atlas layout) |
| `buttons.png` | `ScreenUtils.drawButtonBackground` / `ButtonWidget` (3 vertical states) |
| `slot.png` | `WidgetContainerScreen` default slot chrome |
| `scrollbar_background.png` (+ `.mcmeta` nine_slice) | `ScrollbarWidget` default track |
| `scroller.png` | `ScrollbarWidget` thumb (3 vertical states) |

Lang: narration keys such as `gui.narrate.editBox`, `supermartijn642corelib.widgets.scrollbar.narration` (via `TextComponents`; not owned exclusively by this package’s resources tree in this clone). No datapack / recipes.

## Dependencies

**Within CoreLib**

- `ClientUtils` — Minecraft, font, player, close screen.
- `CommonUtils` — close container on server/player.
- `TextComponents` — empty title, narration formatting, translations.
- `render.RenderUtils.getMainBufferSource()` — flush before/after scissor.
- `util.Holder` — scrollbar builder.
- **mixin** (sibling module): `GameRendererMixin` (cursor apply), `AbstractContainerScreenMixin` (`CustomSlot` hover bounds).

**External (this clone)**

- Minecraft 1.16.5: `Screen`, `ContainerScreen`, `Container`/`ContainerType`, `Slot`, `MatrixStack`, Tessellator, GLFW.
- Forge: `IContainerFactory`, `IItemHandler`, `MinecraftForge.EVENT_BUS`, `GuiContainerEvent.DrawBackground` / `DrawForeground`.

**Not a dependency of Re-Forestry** — copy/adapt only (`reforestry-standalone-adopt`). Do not add `supermartijn642corelib` to `fabric.mod.json`.

## Notable algorithms/contracts

1. **Handled-event chaining** — mouse/key handlers take `hasBeenHandled` and OR child results so parents can still observe without stealing (focused child first).
2. **Widget-local coordinates** — screens subtract centering offset before calling into the widget tree; slot drawing stays in the same translated space.
3. **CustomSlot 1.16 mutability** — `getItem()` caches `lastReturnedStack` / count because older Minecraft mutates returned stacks in place; insert/extract sync back through setter/extractor.
4. **Variable-size slots** — `WidgetContainerScreen` scales or centers item render for non-18×18 slots; mixin overrides `isHovering(Slot,…)` to use custom width/height.
5. **Nine-slice backgrounds** — corner/edge/center UV fractions from fixed atlas sizes (screen 9×9 logical, buttons 5×15).
6. **Scissor** — transform corners by pose matrix → window pixels (`guiScale`, Y flip) → `RenderSystem.enableScissor`; flush buffer source around the runnable.
7. **Pending cursor** — widgets `requestCursor` during render; mixin applies GLFW cursor at end of `GameRenderer.render` so last request wins for the frame.
8. **Bug (upstream)** — `ObjectBaseWidget.left()` and `ObjectBaseContainerWidget.left()` return `this.width(object)` instead of `this.left(object)` when valid (copy-paste); `top()` is correct. Do not port that line literally.

## Port relevance to Re-Forestry

| Idea | Verdict for RF (Fabric 26.2) |
|---|---|
| Depend on / ship CoreLib | **No** — standalone adopt only. |
| Full widget tree + `WidgetScreen` | **Low priority** — RF already has CE-style `ScreenForestry` + ledgers (`GuiPowerLedger`, `GuiClimateLedger`, …), not a CoreLib widget hierarchy. Porting wholesale would fight Forestry GUI conventions. |
| `ScreenUtils` 9-slice / fill / scissor | **Optional util ideas** — only if RF needs shared helpers beyond `GuiGraphics` / existing `RenderUtil`; rewrite for 26.2 APIs (no `RenderSystem.pushMatrix` / Tessellator begin-7). |
| `CustomSlot` builder + Forge `IItemHandler` | **Do not port as-is** — RF uses Fabric transfer / CE slot types (`SlotGhostCrafting`, machine slots). Pattern of active/movable/custom-size slots can inspire local helpers without Forge item handlers. |
| `ObjectBase*` validate-or-close | **Useful pattern** — mirrors “close GUI if TE gone / held item invalid”; RF menus already use `stillValid`; keep CE shape unless item-GUIs need the same client-side renew loop. |
| `BaseContainerType` PacketBuffer factory | **Skip** — Fabric uses `ExtendedScreenHandlerType` / packet codecs already in RF containers. |
| Premade TextField / Scrollbar / Scissor | **Reference only** — valuable if RF adds analyzer/config UIs with scrollable lists; implement with vanilla 26.2 widgets/`GuiGraphics.enableScissor` rather than copying 1.16 GL code. |
| GLFW custom cursors + mixin | **Usually skip** — nice polish; needs a Fabric render-end hook; not required for Forestry parity. |
| Forge `GuiContainerEvent` posts | **N/A** — Fabric has different screen events; RF should not re-fire Forge events. |

Net: treat **gui** as a **pattern library** (layered widget render, scissor scroll areas, fluent slots, object-lifetime GUIs), not a drop-in port. Forestry UI source of truth remains CE/IF → existing `ScreenForestry` / ledger stack.

## Source map

Clone root: `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib`

| File | Lines | Notes |
|---|---|---|
| `.../gui/BaseContainer.java` | ~75 | Menu base + player slots |
| `.../gui/BaseContainerType.java` | ~41 | Typed container factory |
| `.../gui/ObjectBaseContainer.java` | ~65 | Validate-or-close object |
| `.../gui/BlockEntityBaseContainer.java` | ~43 | BE object |
| `.../gui/ItemBaseContainer.java` | ~42 | ItemStack object |
| `.../gui/CustomSlot.java` | ~141 | Slot API + builder |
| `.../gui/CustomSlotImpl.java` | ~484 | Slot implementation |
| `.../gui/CursorType.java` | ~25 | GLFW cursor handle |
| `.../gui/CursorTypes.java` | ~59 | Standards + pending apply |
| `.../gui/ScreenUtils.java` | ~378 | Draw / scissor / tooltip / cursor request |
| `.../gui/WidgetScreen.java` | ~144 | Non-container host |
| `.../gui/WidgetContainerScreen.java` | ~282 | Container host + slots |
| `.../gui/widget/Widget.java` | ~141 | Interface |
| `.../gui/widget/BaseWidget.java` | ~276 | Composite default |
| `.../gui/widget/ContainerWidget.java` | ~20 | Init-with-container |
| `.../gui/widget/BaseContainerWidget.java` | ~41 | Container composite |
| `.../gui/widget/ObjectBaseWidget.java` | ~327 | Object lifecycle (**left() bug**) |
| `.../gui/widget/ObjectBaseContainerWidget.java` | ~328 | Same for container widgets |
| `.../gui/widget/BlockEntityBaseWidget.java` | ~38 | |
| `.../gui/widget/BlockEntityBaseContainerWidget.java` | ~39 | |
| `.../gui/widget/ItemBaseWidget.java` | ~50 | |
| `.../gui/widget/ItemBaseContainerWidget.java` | ~51 | |
| `.../gui/widget/premade/AbstractButtonWidget.java` | ~68 | |
| `.../gui/widget/premade/ButtonWidget.java` | ~46 | |
| `.../gui/widget/premade/LabelWidget.java` | ~51 | |
| `.../gui/widget/premade/TextFieldWidget.java` | ~410 | |
| `.../gui/widget/premade/ScrollbarWidget.java` | ~330 | Builder + drag/wheel |
| `.../gui/widget/premade/ScissorWidget.java` | ~127 | Clip children |

Related (other modules, cited only):

- `.../mixin/GameRendererMixin.java` — apply pending cursor
- `.../mixin/AbstractContainerScreenMixin.java` — custom slot hover
- `.../ClientUtils.java`, `.../CommonUtils.java`, `.../TextComponents.java`
- `.../render/RenderUtils.java` — buffer flush for scissor
- Test: `src/test/java/.../test/TestScreen.java` (out of inventory scope)

## Open questions/gaps

- This clone is **1.16.5 Forge only**; newer CoreLib branches may have Fabric ports or 1.18+ render API updates — not verified here. Confirm before adopting draw code against MC 26.2.
- Whether SuperMartijn’s multi-version story still uses the same `Widget` layering on modern versions (README markets 1.12–1.16) — gap for Phase 2 if RF ever needs cross-version notes.
- Exact lang file ownership for narration keys in this tree was not fully audited (may live in generated/resources elsewhere).
- `ObjectBaseWidget.left()` bug: confirm still present on upstream latest before filing; if porting the class, fix to call `left(object)`.
- Interaction with RF JEI ghost overlays / ledgers was not tested; CoreLib’s full `render()` override of `ContainerScreen` may conflict with Fabric JEI if copied wholesale.
