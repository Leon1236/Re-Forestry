# Stage F2 — Recipe infra (no machine types yet)

## Files created

- `api/recipes/IForestryRecipe.java` — `Recipe<RecipeInput>` (MC 26.2), **not** CE's `Recipe<Container>`. CE's Forge `Recipe<Container>` API had `getId()`, `matches`, `assemble`, `canCraftInDimensions`, `getRemainingItems`, `getIngredients`, `isSpecial`, `getGroup`, `getToastSymbol`. 26.2's `Recipe<T extends RecipeInput>` dropped `getId()` (recipes are keyed by `RecipeHolder` outside the interface now — irrelevant to this stage), `canCraftInDimensions`, `getRemainingItems`, `getIngredients`, and `getToastSymbol` entirely, and added new abstract members `showNotification()`, `placementInfo()`, `recipeBookCategory()`. Ported/adapted:
  - `matches`/`assemble` — same "ignore, we don't use vanilla grid matching" default as CE, adapted to the new single-arg `assemble(T input)` signature (no more `RegistryAccess`).
  - `isSpecial()` → default `true` (unchanged intent from CE).
  - `group()` (renamed from `getGroup()`) → default `"reforestry"` (was `"forestry"`).
  - `showNotification()` → default `false` (Forestry recipes never toast; CE had no equivalent since this method didn't exist yet).
  - `placementInfo()` → default `PlacementInfo.NOT_PLACEABLE` (replaces `getIngredients()`; Forestry recipes aren't grid-placeable).
  - `recipeBookCategory()` → default `RecipeBookCategories.CRAFTING_MISC` (existing vanilla constant, reused since Forestry recipes never appear in the vanilla recipe book — `display()` stays at the inherited empty-list default, so the book never asks for a display anyway; this default only exists to satisfy the abstract method).
  - `getSerializer()`/`getType()` stay abstract (inherited from `Recipe`), same as CE.
- `api/core/IProduct.java` — ported ~1:1 from CE (`Hash.Strategy` item-only equality, `createStack()`, `createRandomStack(RandomSource)`). No obsolete APIs involved.
- `api/core/Product.java` — shared recipe product (item + count + chance), **distinct from** `IFruit.Product` / `IBeeSpecies.Product` genetics nested records (untouched, per instructions). CE's field was `@Nullable CompoundTag tag`; 26.2 items use the data-component system instead of NBT tags, so this is `DataComponentPatch components` (matches `ItemStackTemplate`'s modern equivalent). `Codec`/`StreamCodec` both ported: `Codec.intRange`/`Codec.floatRange` still exist in 26.2's Mojang serialization lib (confirmed via vanilla usages), so the CE codec shape carries over unchanged aside from swapping the tag field for `DataComponentPatch.CODEC`/`STREAM_CODEC`. `createStack()` uses `BuiltInRegistries.ITEM.wrapAsHolder(item)` (not `Item.builtInRegistryHolder()`, which is `@Deprecated` in 26.2) since `ItemStack`'s component-aware constructor now takes a `Holder<Item>`.
- `modules/features/FeatureRecipeType.java` — mirrors CE's `forestry/modules/features/FeatureRecipeType.java` shape (holds a `RecipeType<R>` + `RecipeSerializer<? extends R>`, exposes `type()`/`serializer()`), but registers directly with `Registry.register(BuiltInRegistries.RECIPE_TYPE/RECIPE_SERIALIZER, ...)` instead of Forge `DeferredRegister`, matching the pattern already used by `FeatureBlock`/`FeatureMenuType`/`FeatureBlockEntityType` in this project. CE's vanilla-side equivalent, `RecipeType.simple(ResourceLocation)`, doesn't exist for arbitrary namespaces in 26.2 (`RecipeType.register(String)` only registers into `minecraft:`), so `FeatureRecipeType` builds its own anonymous `RecipeType<R>` the same way vanilla's `RecipeType.register` does internally.

## Files modified

- `modules/features/IFeatureRegistry.java` — added `recipeType(String name, Supplier<RecipeSerializer<? extends R>> serializer)` default method, same call shape as every other feature helper in this interface (builds the `Identifier` from `getModuleId()` + name, delegates to the `Feature*` constructor).

## How later stages register one type + serializer (the pattern for F3a/F5+)

Each factory recipe interface/impl pair (e.g. `ICentrifugeRecipe`/`CentrifugeRecipe`) defines its own `MapCodec`/`StreamCodec` and a `public static final RecipeSerializer<CentrifugeRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);` (same shape as vanilla `SmeltingRecipe.SERIALIZER`). The owning `Feature*RecipeTypes` class (created in a later F-stage, one per module needing recipes — e.g. `FactoryRecipeTypes` from F5 onward) then calls, per recipe type:

```java
public static final FeatureRecipeType<CentrifugeRecipe> CENTRIFUGE =
        REGISTRY.recipeType("centrifuge", () -> CentrifugeRecipe.SERIALIZER);
```

`REGISTRY` is the module's `IFeatureRegistry` (e.g. `ModFeatureRegistry.get(ReForestry.id("factory"))`), same as every other `FactoryBlocks`/`FactoryTiles` field. This one call registers both `BuiltInRegistries.RECIPE_TYPE` and `BuiltInRegistries.RECIPE_SERIALIZER` entries under `reforestry:centrifuge` and returns a `FeatureRecipeType<CentrifugeRecipe>` whose `.type()` is passed to `RecipeManager.getAllRecipesFor(type)` lookups and whose `.serializer()` is only needed if something inspects the registry entry directly (usually not).

## Non-obvious choices

- **No `FactoryRecipeTypes.java` yet.** Per stage scope, that class (and the 11 individual recipe interfaces/records) is deferred to F5+ — this stage only proves the registration *mechanism* compiles.
- **No hygroregulator.** F16 owns it (it's registered on the Factory module in CE, but the recipe itself is apiculture/alveary domain).
- **`RecipeBookCategories.CRAFTING_MISC` reused, not a new category.** 26.2's `Recipe.recipeBookCategory()` is abstract with no universal "none" constant. Since Forestry recipes keep `display()` at its inherited empty-list default, the recipe book UI never actually calls `recipeBookCategory()` for them in practice — the default here only exists to satisfy the interface contract without inventing a new registry entry.
- **`IForestryRecipe` is non-generic over `RecipeInput`** (extends `Recipe<RecipeInput>` directly, not `Recipe<T extends RecipeInput>`), mirroring CE's choice to fix the generic to one shared container-like type (`Container` in CE) rather than parameterize per-recipe. Concrete machine recipe interfaces (`ICentrifugeRecipe`, etc., from later stages) will define their own item-access methods on top of this rather than relying on `RecipeInput.getItem(index)` directly, exactly as CE's machine recipes ignored `Container` and used custom matching methods.

## Verified via MCP

- `thedarkcolour-ForestryCE`: `forestry/api/recipes/IForestryRecipe.java`, `forestry/api/core/Product.java`, `forestry/api/core/IProduct.java`, `forestry/modules/features/FeatureRecipeType.java`, `forestry/modules/features/IFeatureRegistry.java`.
- `Minecraft-26.2`: `net/minecraft/world/item/crafting/Recipe.java`, `RecipeInput.java`, `RecipeType.java`, `RecipeSerializer.java`, `PlacementInfo.java`, `RecipeBookCategory.java`/`RecipeBookCategories.java`, `SmeltingRecipe.java`/`AbstractCookingRecipe.java`/`SingleItemRecipe.java` (serializer/codec pattern reference), `net/minecraft/world/item/ItemStackTemplate.java`, `ItemStack.java` (component-patch constructors), `net/minecraft/core/registries/BuiltInRegistries.java` (`RECIPE_TYPE`/`RECIPE_SERIALIZER` are plain `Registry.register` targets, not Forge-style deferred), `net/minecraft/core/Registry.java` (`register`/`wrapAsHolder`).

## DoD status

- `./gradlew compileJava` — **BUILD SUCCESSFUL**, zero deprecation warnings.
- Zero factory recipe JSON in the datapack (none added).
- Zero stub `RecipeType` registrations for unused machines (`FactoryRecipeTypes.java` not created).

## Blockers for F3a

None from this stage. F3a (first real machine, per the inventory doc's suggested order — Centrifuge) can now:
1. Define `ICentrifugeRecipe extends IForestryRecipe` + `CentrifugeRecipe` record with its own `MAP_CODEC`/`STREAM_CODEC`/`SERIALIZER`.
2. Create `FactoryRecipeTypes.java` with `CENTRIFUGE = REGISTRY.recipeType("centrifuge", () -> CentrifugeRecipe.SERIALIZER);` as its first (and, per this stage's scope, *only*) entry.
3. Confirm `TilePowered`/`ForestryEnergyStorage` (per F1's blocker note) exist before wiring the tile.
