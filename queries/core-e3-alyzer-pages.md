# CORE-E3 — Alyzer product + mutation pages

CE 1.21.1 `AnalyzerScreenGraphics` + `BeeAnalyzerPlugin` / `TreeAnalyzerPlugin` page 3–4, mapped onto Fabric 26.2.

## Item icons

CE `GuiUtil.drawItemStack` uses `GuiGraphics.renderItem` + `renderItemDecorations`. Those GUI `ItemRenderer` paths are not what 26.2 screens use. Match other Re-Forestry GUIs: `GuiGraphicsExtractor.fakeItem` then `itemDecorations`.

## Mutation atlas UVs

From CE `forestry/apiimpl/client/genetics/AnalyzerScreenGraphics.java` (verified against `portablealyzer.png` 256×256):

| Sprite | blit dest | u, v | w×h |
|---|---|---|---|
| Undiscovered `?` | `(currentX, currentY)` and `(currentX+33, currentY)` | **78, 240** | 16×16 |
| Chance arrow | `(currentX+18, currentY+4)` | **100 + 15×rate**, **247** | 15×9 |

`EnumMutateChance.rateChance`: HIGHER→1, HIGH→2, NORMAL→3, LOW→4, LOWEST→5, else 0 (covers HIGHEST and NONE). Arrow U is 100, 115, 130, 145, 160, 175.

26.2 blit: `graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, u, v, w, h, 256, 256)` — not CE’s `graphics.blit(texture, x, y, u, v, w, h)`.

## API shape

CE `IAnalyzerGraphics` is generic over species/individual. Ours stays non-generic. `drawProductList(Function<S, List<IProduct>>)` still collects active/inactive species products and dedupes with `IProduct.ITEM_ONLY_STRATEGY`. Mutation icons are `Function<Identifier, ItemStack>` from the bee/tree plugins (drone / sapling stacks).

Tree fruit alleles return `IFruit.Product`, not `IProduct`. Page 3 wraps them with `Product.of(item, 1, chance)`. Trees stay haploid (active fruit only), matching CE `TreeAnalyzerPlugin`.

Drones always `setHaploid(true)` on pages 1–3. CE also gates that on `ForestryConfig.SERVER.useHaploidDrones`; we have no such config.

Discovery uses the CORE-E2 client tracker: `BreedingTrackerManager.getTracker` on the client level.

Do not use this note for A-DISC1 (apiarist counts) or A-DISC2 (naturalist chest mutation pane).
