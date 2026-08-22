# SuperMartijn642-SuperMartijn642sCoreLib — render

| Field | Value |
|---|---|
| Repo / alias | SuperMartijn642-SuperMartijn642sCoreLib / `corelib` |
| Package | `com.supermartijn642.core.render` |
| Clone | `MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/SuperMartijn642-SuperMartijn642sCoreLib` |
| Loader snapshot | Forge 1.16.5 / mod `supermartijn642corelib` 1.1.22 |
| Size | 9 Java files, ~843 LOC (inventory: **M**) |
| Subpackages | none (flat package) |
| Graph | `python3 tools/graphify_query.py corelib "RenderUtils"` |

## Summary

The **render** module is CoreLib’s client-side rendering toolkit: thin functional interfaces for custom block-entity and item renderers, a helper that reuses BER logic in item form, a Forge `RenderType` wrapper with a fluent OpenGL state builder, debug/world overlay line-and-quad drawing, a world-render hook event, and vanilla atlas id constants.

Nothing in this package registers itself. **registry** (`ClientRegistrationHandler`) is the main wiring layer for `CustomItemRenderer` / `CustomBlockEntityRenderer`. **mixin** (`LevelRendererMixin`) posts `RenderWorldEvent`. **block** (`BlockShape`) is a runtime dependency of `RenderUtils`. The test mod demonstrates custom item registration and overlay drawing.

The package is entirely client-only and Forge 1.16.5-shaped (`MatrixStack`, `IRenderTypeBuffer`, `ItemStackTileEntityRenderer`, `BakedModelWrapper`, `ForgeHooksClient`).

## Player/API surface

**Public types mods call**

| Surface | Role |
|---|---|
| `CustomItemRenderer` | Functional interface: `render(itemStack, transformType, poseStack, bufferSource, light, overlay)`. Static `of(…)` wraps into Forge `ItemStackTileEntityRenderer`. |
| `CustomBlockEntityRenderer<T>` | Functional interface for BER drawing. Static `of(…)` wraps into anonymous `TileEntityRenderer<T>`. |
| `BlockEntityCustomItemRenderer<T>` | `CustomItemRenderer` that lazily creates/updates a fake `TileEntity`, optionally draws the item’s baked model first, then calls `TileEntityRendererDispatcher.instance.renderItem(…)`. |
| `CustomRendererBakedModelWrapper` | `BakedModelWrapper` with `isCustomRenderer() == true` and perspective handled on `this`. Factory: `wrap(IBakedModel)`. |
| `RenderConfiguration` | Subclass of `RenderType`. `create(modid, name, format, primitive, bufferSize, …, RenderStateConfiguration)` and `wrap(RenderType)`. Helpers: `setupState()`, `clearState()`, `begin(buffer)`, `end(buffer)`. Nested `PrimitiveType` enum (lines, triangles, quads, strips, fans). |
| `RenderStateConfiguration` | Immutable ordered list of setup/clear runnables. `builder()` with fluent texture, transparency, depth, culling, lightmap, overlay, layering, depth/color mask, line-width options; `append(RenderStateEntry)` for custom states. |
| `RenderUtils` | Camera/buffer accessors; `renderShape` / `renderShapeSides` / `renderBox` / `renderBoxSides` overloads for `BlockShape`, `VoxelShape`, `AxisAlignedBB` (with/without alpha; depth-tested or not). |
| `RenderWorldEvent` | Forge `Event` carrying `MatrixStack` + `partialTicks`. Subscribe on Forge event bus (client). |
| `TextureAtlases` | Static getters for vanilla atlas `ResourceLocation`s (blocks, particles, mob effects, paintings, shulker boxes, beds, banners, shields, signs, chests). |

**Registration helpers (outside package, primary consumers)**

| Helper | Uses |
|---|---|
| `ClientRegistrationHandler.registerCustomItemRenderer(…)` | Converts `CustomItemRenderer` → `ItemStackTileEntityRenderer.of`, stores via reflection on `Item.ister` at client setup. |
| `ClientRegistrationHandler.registerCustomBlockEntityRenderer(…)` | Converts `CustomBlockEntityRenderer` → `TileEntityRenderer` via `CustomBlockEntityRenderer.of`, binds with `ClientRegistry.bindTileEntityRenderer`. |
| `ClientRegistrationHandler.registerAtlasSprite(TextureAtlases.getBlocks(), …)` | Typical pairing in dependent mods (atlas constants only; registration lives in registry). |

**Not player-facing:** `RenderStateConfiguration.RenderStateEntry` (public but low-level); prebuilt `RenderConfiguration` instances inside `RenderUtils` (private static).

## Architecture

```
Dependent mod client init
  ClientRegistrationHandler.registerCustomItemRenderer(item, CustomItemRenderer)
       │  FMLClientSetupEvent
       ▼
  CustomItemRenderer.of → ItemStackTileEntityRenderer
  reflection → Item.ister supplier

  ClientRegistrationHandler.registerCustomBlockEntityRenderer(type, CustomBlockEntityRenderer)
       ▼
  CustomBlockEntityRenderer.of → TileEntityRenderer
  ClientRegistry.bindTileEntityRenderer

BlockEntityCustomItemRenderer (optional pattern)
  initEntity supplier → cached TileEntity
  entityUpdater(itemStack, entity)
  [optional] renderDefaultModel (ItemRenderer + ForgeHooksClient layered path)
  TileEntityRendererDispatcher.renderItem(entity, …)

RenderUtils debug overlays
  RenderConfiguration.create(… RenderStateConfiguration.builder …)
  getMainBufferSource() → begin/end batch
  BlockShape.forEachEdge / forEachBox → POSITION_COLOR vertices

World hook (requires mixin, not in this package)
  LevelRendererMixin @Inject renderLevel
       ▼
  MinecraftForge.EVENT_BUS.post(RenderWorldEvent)
  TestModClient: translate by -camera; RenderUtils.renderShape*
```

Three layers:

1. **Renderer adapters** — `CustomItemRenderer`, `CustomBlockEntityRenderer`, `BlockEntityCustomItemRenderer`, `CustomRendererBakedModelWrapper` (model flag only).
2. **Render pipeline helpers** — `RenderStateConfiguration` → `RenderConfiguration` → buffer batching.
3. **Convenience / hooks** — `RenderUtils` shape drawing, `RenderWorldEvent`, `TextureAtlases` constants.

## Data & assets

- **No assets or datapack JSON** under this Java package.
- `RenderConfiguration.create` registers render-layer ids at runtime as `{modid}:{name}` (CoreLib’s own debug layers use `supermartijn642corelib:lines`, `:lines_no_depth`, `:quads`, `:quads_no_depth`).
- `TextureAtlases` returns hard-coded vanilla paths (`textures/atlas/blocks.png`, etc.) — documentation/convenience only; does not stitch atlases itself.
- `CustomRendererBakedModelWrapper` is meant to be applied during model bake (by the dependent mod or a model overwrite); CoreLib does **not** auto-wrap models in this snapshot.
- Mixin config reference: `modid.mixins.json` lists `LevelRendererMixin` (sibling **mixin** package) — required for `RenderWorldEvent` to fire.

## Dependencies

| Depends on | Why |
|---|---|
| Mojang Blaze3D (`MatrixStack`, `RenderSystem`, `GlStateManager`, `IVertexBuilder`) | All drawing and GL state |
| Vanilla client render stack (`RenderType`, `IRenderTypeBuffer`, `ItemRenderer`, `TileEntityRendererDispatcher`, `DefaultVertexFormats`, shapes) | Core rendering |
| Forge client (`BakedModelWrapper`, `ForgeHooksClient`, `ItemStackTileEntityRenderer`, `TileEntityRenderer`, `ModelBakeEvent` consumers via registry) | Item/BER adapters and layered item draw |
| LWJGL `GL11` | Depth func constants; legacy `GL_QUADS` primitive |
| `com.supermartijn642.core.ClientUtils` | Minecraft, texture manager, item renderer, window size |
| `com.supermartijn642.core.block.BlockShape` | Edge/box iteration for `RenderUtils` |
| Forge EventBus (`RenderWorldEvent`) | World render hook |

**Depended on by (outside package):**

| Consumer | Usage |
|---|---|
| `registry/ClientRegistrationHandler` | `registerCustomItemRenderer`, `registerCustomBlockEntityRenderer` |
| `mixin/LevelRendererMixin` | Posts `RenderWorldEvent` |
| `test/TestMod`, `test/TestModClient` | Registration + overlay demos |

**Does not depend on:** gui, network, generator, data, item/block registration (except `BlockShape` type).

## Notable algorithms/contracts

1. **Functional renderer → Forge type** — Both `CustomItemRenderer.of` and `CustomBlockEntityRenderer.of` allocate anonymous Forge renderer subclasses that delegate to the functional interface. Keeps mod code free of subclass boilerplate.
2. **Item BER reuse** — `BlockEntityCustomItemRenderer` caches one `TileEntity` instance per renderer object; `entityUpdater` must sync stack NBT/state before each draw. Throws if `initEntity` returns null.
3. **Optional base item model** — When `renderItemModel == true`, `renderDefaultModel` mirrors vanilla item rendering (including layered models via `ForgeHooksClient.drawItemLayered` and foil buffers). Useful when the BER adds geometry on top of the block item model.
4. **Custom renderer model flag** — `CustomRendererBakedModelWrapper.isCustomRenderer()` forces the item renderer down the ISTER path; `handlePerspective` applies transforms then returns `this` (not the inner model).
5. **Render state ordering** — `RenderStateConfiguration.Builder.build()` concatenates fixed slots (texture → transparency → depth → cull → lightmap → overlay → layering → depth mask → color mask → line width) then custom `append` entries. Each entry has independent setup/clear runnables (null clear = no-op).
6. **Debug layer presets** — `RenderUtils` uses four static `RenderConfiguration`s: lines/quads × depth/no-depth; translucent, no texture, no depth mask, view-offset Z layering on lines.
7. **Shape drawing** — Outlines iterate edges; sides iterate axis-aligned boxes from `BlockShape` and emit 6 quads per box (24 vertices). Overloads default alpha to 1.0.
8. **World event timing** — `LevelRendererMixin` injects after block highlight raycast, before `RenderSystem.pushMatrix()` in the block-break overlay slice — “after blocks, around highlight” per class javadoc.
9. **Item ISTER exclusivity** — `ClientRegistrationHandler` throws if an item already has a non-null `ister` supplier when registering a custom renderer.

## Port relevance to Re-Forestry

| Idea | Relevance | Guidance |
|---|---|---|
| `CustomItemRenderer` / ISTER pattern | **High idea, low code reuse** | MC 26.2 uses `SpecialModelRenderer` / `NoDataSpecialModelRenderer` (see `MachineSpecialRenderer`, `MillSpecialRenderer` + mixin). Do not port Forge `ItemStackTileEntityRenderer` or `Item.ister` reflection. |
| `CustomBlockEntityRenderer` adapter | **Low** | Re-Forestry already registers native `BlockEntityRenderer` implementations (`RenderMachine`, `RenderMill`, `FactoryClientHandler`). The `of()` wrapper adds no value on Fabric. |
| `BlockEntityCustomItemRenderer` | **Medium pattern** | Useful when item and block should share draw logic. On 26.2, call the same helper from `SpecialModelRenderer` and `BlockEntityRenderer`, or extract shared geometry code — do not instantiate fake BEs unless necessary (prefer render state objects). |
| `CustomRendererBakedModelWrapper` | **Low** | Replaced by item model JSON `"special": true` / registered special model types. Re-Forestry already uses `SpecialModelRenderers` registration. |
| `RenderConfiguration` + `RenderStateConfiguration` | **Low direct reuse** | 1.16 `RenderType` subclassing + manual `RenderSystem` differs heavily from 26.2 `RenderType`/`RenderPipeline`. For debug overlays, use `RenderLayer.getLines()` / custom `RenderType.create` or Fabric rendering APIs; rewrite state setup for modern pipeline. |
| `RenderUtils` shape/box overlays | **Medium utility** | Handy for multiblock previews, genetics/debug overlays. Port the *math* (edge/box iteration) if needed; swap buffer API to `MultiBufferSource`, `PoseStack`, `VertexConsumer`. Check whether vanilla `LevelRenderer` / `ShapeRenderer` covers the use case first. |
| `RenderWorldEvent` | **Medium hook** | Use Fabric `WorldRenderEvents` (or equivalent 26.2 client event) instead of CoreLib mixin + custom event. Avoid copying `LevelRendererMixin` injection points blindly — mappings and render pipeline changed. |
| `TextureAtlases` | **Low** | Vanilla atlas ids moved (`Sheets`, `TextureAtlasHolder`, etc. on 26.2). Look up current atlas constants in `Minecraft-26.2` when stitching sprites. |
| Standalone adopt | **N/A as dependency** | Re-Forestry must **not** depend on CoreLib. Copy only targeted helpers into `com.leon1236.reforestry.core.client.*` if a gap remains after vanilla/Fabric APIs. |

**Verdict for Phase 2:** Treat **render** as a reference for how SuperMartijn642 mods simplify BER/item custom rendering and debug overlays on Forge 1.16.5. Re-Forestry’s existing `SpecialModelRenderer` + `BlockEntityRenderer` stack already covers the high-value paths; adopt ideas (shared draw code, overlay helpers) selectively, not the Forge types or GL state builder verbatim.

## Source map

| Path | Purpose |
|---|---|
| `…/render/CustomItemRenderer.java` | Item custom render functional interface + ISTER wrapper |
| `…/render/CustomBlockEntityRenderer.java` | BER functional interface + `TileEntityRenderer` wrapper |
| `…/render/BlockEntityCustomItemRenderer.java` | Item renderer that drives BER + optional base item model |
| `…/render/CustomRendererBakedModelWrapper.java` | Baked model wrapper forcing custom item renderer path |
| `…/render/RenderConfiguration.java` | `RenderType` subclass, primitive enum, buffer batch helpers |
| `…/render/RenderStateConfiguration.java` | Fluent GL/render-state builder + ordered apply/clear |
| `…/render/RenderUtils.java` | Camera/buffer accessors; shape/box outline and fill drawing |
| `…/render/RenderWorldEvent.java` | Client world-render Forge event payload |
| `…/render/TextureAtlases.java` | Vanilla texture atlas `ResourceLocation` constants |
| *Related (not in package)* | `registry/ClientRegistrationHandler.java` (registration); `mixin/LevelRendererMixin.java` (event injection); `block/BlockShape.java` (`RenderUtils` dependency); `test/TestMod.java`, `test/TestModClient.java` (demos) |

## Open questions/gaps

1. **`CustomRendererBakedModelWrapper` has zero in-repo call sites** in this 1.16.5 snapshot — intended for dependent mods at model-bake time; confirm usage in SuperMartijn642 content mods separately if adoption is considered.
2. **`BlockEntityCustomItemRenderer` thread/lifecycle** — cached TE is never world-attached; some vanilla BER paths may assume level/position (works for simple renderers only).
3. **`RenderUtils.getMainBufferSource`** — uses the main Minecraft buffer source directly; may not flush correctly if called outside the expected render pass (test mod uses it from world/highlight events).
4. **1.16.5 vs newer CoreLib** — this clone is Forge 1.16.5 only; SuperMartijn642’s multi-loader branches may rename types (`PoseStack`, `MultiBufferSource`, different item renderer API). Not verified here.
5. **Fabric 26.2 equivalent for `RenderWorldEvent` injection point** — exact hook after block highlight needs mapping against `Minecraft-26.2` `LevelRenderer` before porting overlay timing.
6. **`GL_QUADS` primitive** — removed in core profile / modern MC pipelines; any ported overlay code must use indexed triangles instead.
