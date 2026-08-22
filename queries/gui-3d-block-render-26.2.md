# GUI 3D block / ghost-block rendering (MC 26.2)

Researched 2026-07-27 against MCP `Minecraft-26.2`, `FabricMC-fabric-api`, `mezz-JustEnoughItems`, and local `GuiAccessLedger` / `GuiRecipeLedger`.

## Big API shift vs 1.20.x

| Gone / renamed | Use on 26.2 |
|---|---|
| `GuiGraphics` | `net.minecraft.client.gui.GuiGraphicsExtractor` |
| `renderItem` / `ItemRenderer` (GUI) | `GuiGraphicsExtractor.item(...)` / `fakeItem(...)` |
| `InventoryScreen.renderEntityInInventory...` | `InventoryScreen.extractEntityInInventoryFollowsMouse(...)` + `graphics.entity(...)` |
| `BlockRenderDispatcher` / `renderSingleBlock` | `BlockModelResolver` + `BlockModelRenderState.submit(...)` |
| Immediate GUI draw | Extract render **state**, then `GuiRenderer` / PIP blits it |

`GuiGraphicsExtractor.pose()` is a **`Matrix3x2fStack`** (2D GUI only). Real 3D uses `PoseStack` inside a Picture-in-Picture (PIP) pass.

## Vanilla GUI item APIs (Yarn)

```java
// GuiGraphicsExtractor
void item(ItemStack itemStack, int x, int y);
void item(ItemStack itemStack, int x, int y, int seed);
void item(LivingEntity owner, ItemStack itemStack, int x, int y, int seed);
void fakeItem(ItemStack itemStack, int x, int y);
void fakeItem(ItemStack itemStack, int x, int y, int seed);
void itemDecorations(Font font, ItemStack itemStack, int x, int y);
void itemDecorations(Font font, ItemStack itemStack, int x, int y, @Nullable String countText);

void entity(
  EntityRenderState renderState,
  float scale,
  Vector3fc translation,
  Quaternionfc rotation,
  @Nullable Quaternionfc overrideCameraAngle,
  int x0, int y0, int x1, int y1
);
```

Internals for items:

- `Minecraft.getItemModelResolver().updateForTopItem(TrackingItemStackRenderState, ItemStack, ItemDisplayContext.GUI, Level, ItemOwner, seed)`
- `GuiRenderState.addItem(new GuiItemRenderState(pose, itemState, x, y, scissor))`
- Lighting: `Lighting.Entry.ITEMS_3D` if `usesBlockLight()`, else `ITEMS_FLAT` (`OversizedItemRenderer` / `GuiItemAtlas`)

`AbstractContainerScreen.extractSlot`: real slots → `item`, fake slots → `fakeItem`.

## Vanilla entity-in-GUI (pattern for 3D PIP)

```java
InventoryScreen.extractEntityInInventoryFollowsMouse(
  GuiGraphicsExtractor graphics,
  int x0, int y0, int x1, int y1,
  int size, float offsetY,
  float mouseX, float mouseY,
  LivingEntity entity
);
```

Ends in `graphics.entity(...)` → `GuiEntityRenderState` → `GuiEntityRenderer` (`PictureInPictureRenderer`) with `Lighting.Entry.ENTITY_IN_UI`.

## Block model APIs (no BlockRenderDispatcher)

```java
// Construct (Minecraft has no public getBlockModelResolver)
new BlockModelResolver(Minecraft.getInstance().getModelManager());

void BlockModelResolver.update(
  BlockModelRenderState renderState,
  BlockState blockState,
  BlockDisplayContext displayContext
);

BlockDisplayContext.create(); // empty context used by TntRenderer etc.

void BlockModelRenderState.submit(
  PoseStack poseStack,
  SubmitNodeCollector submitNodeCollector,
  int externalLightCoords,  // e.g. 15728880 / LightCoordsUtil.FULL_BRIGHT
  int overlayCoords,        // OverlayTexture.NO_OVERLAY
  int outlineColor
);
```

`setupModel(Matrix4fc transformation, boolean hasTranslucency)` picks:

- opaque → `Sheets.cutoutBlockItemSheet()`
- translucent → `Sheets.translucentBlockItemSheet()`

World-style example: `TntRenderer` / `CarriedBlockLayer`.

## Fabric: custom GUI 3D (recommended for access panel scene)

```java
net.fabricmc.fabric.api.client.rendering.v1.PictureInPictureRendererRegistry
  .register(Factory factory);

// Factory:
PictureInPictureRenderer<?> createRenderer(Context ctx);

// Submit from extract pass:
graphics.guiRenderState.addPicturesInPictureState(PictureInPictureRenderState state);
```

Implement:

1. Record implementing `PictureInPictureRenderState` (`x0/y0/x1/y1`, `scale()`, scissor/bounds).
2. `PictureInPictureRenderer<T>` with `renderToTexture(T, PoseStack, SubmitNodeCollector)`.
3. Register on client init.

Reference: Fabric testmod `BannerGuiElementRenderer` / `BannerGuiElementRenderState` / `PictureInPictureRendererTest`.

Javadoc still says `GuiGraphics` in places — actual class is `GuiGraphicsExtractor`.

## JEI

No special block GUI renderer. `ItemStackRenderer` only does:

```java
guiGraphics.fakeItem(ingredient, posX, posY);
guiGraphics.itemDecorations(font, ingredient, posX, posY);
```

Blocks appear as their `BlockItem` GUI models.

## Local Re-Forestry today

- `GuiAccessLedger`: **2D colored squares** in a 3×3 direction grid (UP / W E / N D S). Click → `IContainerSidedAccess.accessButtonId(direction)`.
- `GuiRecipeLedger`: `fakeItem` + translucent `fill` overlay (`GHOST_OVERLAY`) for ghost recipe icons.
- World TESRs: `RenderMachine` / `SubmitNodeCollector` — not GUI.

## Recommended approach for Access panel

### A — Fast path (good enough)

1. Center: `graphics.item(new ItemStack(machineBlock), cx, cy)` (or scale via `pose().pushMatrix()` / `scale`).
2. Ghosts: six `fakeItem` (or glass/`Blocks.GLASS`) around center + colored `fill` alpha tint by `AccessMode` (same pattern as recipe ledger).
3. Hit-test: keep current 2D face rectangles (or isometric diamonds). **Do not** GPU-pick.

### B — True 3D scene (matches request)

1. One custom PIP state holding center `BlockState` + six offset ghost states/colors.
2. In `renderToTexture`:
   - `lighting().setupFor(Lighting.Entry.ITEMS_3D)` (or `ENTITY_IN_UI`).
   - Fixed isometric rotation (e.g. Y≈45°, X≈30°).
   - Center: `blockModelResolver.update` → `submit` opaque.
   - Ghosts: translate by `Direction.getUnitVec3()` (or relative front/back/left/right from machine facing), scale slightly, submit with translucent sheet / tinted alpha.
3. Register via `PictureInPictureRendererRegistry`.
4. From `GuiAccessLedger.draw`: `graphics.guiRenderState.addPicturesInPictureState(...)`.

### Hit-testing (recommended)

Prefer **screen-space regions**, not mesh picking:

1. Define six `Rect2i` / polygons in GUI coords that match projected face positions (UP above, DOWN below, N/S/E/W around).
2. On click/hover: first rect containing mouse → that `Direction` (optionally remap with machine facing to relative labels).
3. Optional upgrade: store the same model matrices used in PIP, unproject mouse with orthographic inverse, ray–AABB against six ghost boxes; still no framebuffer readback.

Relative faces: `Direction.getOpposite()`, `getClockWise()`, and machine `BlockState` facing property.

## Deprecations / pitfalls

- Do not port `GuiGraphics`, `ItemRenderer.renderGuiItem`, or `BlockRenderDispatcher.renderSingleBlock`.
- Do not call world `PoseStack` APIs on `GuiGraphicsExtractor.pose()` (2D only).
- PIP list is class-keyed: one renderer per render-state class; multiple states per frame OK.
- `BlockItem.block` field is `@Deprecated` — use public accessors / registry item→block as appropriate.
- Fullbright light for UI: `15728880` or `LightCoordsUtil.FULL_BRIGHT`.
