# ACGaming-Binnie — core-api

- Alias: `binnie`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie`
- Package/path root: `core-api/src/main/java/binnie/core/api`
- Java files scanned: **22**
- Date: 2026-07-30

## Summary
Module `core-api` in `ACGaming-Binnie` is rooted at `core-api/src/main/java/binnie/core/api` (22 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `IBinnieRecipe`
- `ICraftingManager`
- `IBreedingSystem`
- `IFieldKitPlugin`
- `IGene`
- `IItemAnalysable`
- `Alignment`
- `IArea`
- `IBinnieSprite`
- `IBorder`
- `IGuiItem`
- `IPoint`
- `ITexture`
- `ITitledWidget`
- `ITopLevelWidget`
- `IWidget`
- `IWidgetAttribute`
- `RenderStage`
- `Event`
- `EventHandlerOrigin`
- `OnEventHandler`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py binnie "core-api"`
- Source root exists: **True**
- Nested packages under this module:
  - `genetics`
  - `gui`
  - `gui/events`
- Declaration skim (first files):
  - `core-api/src/main/java/binnie/core/api/IBinnieRecipe.java`
    - L1: package binnie.core.api;
    - L5: public interface IBinnieRecipe {
  - `core-api/src/main/java/binnie/core/api/ICraftingManager.java`
    - L1: package binnie.core.api;
    - L5: public interface ICraftingManager<T extends IBinnieRecipe> {
  - `core-api/src/main/java/binnie/core/api/genetics/IBreedingSystem.java`
    - L1: package binnie.core.api.genetics;
    - L27: public interface IBreedingSystem {
    - L54: @Nullable
    - L59: @Nullable
    - L118: @SideOnly(Side.CLIENT)
  - `core-api/src/main/java/binnie/core/api/genetics/IFieldKitPlugin.java`
    - L1: package binnie.core.api.genetics;
    - L9: public interface IFieldKitPlugin {
  - `core-api/src/main/java/binnie/core/api/genetics/IGene.java`
    - L1: package binnie.core.api.genetics;
    - L10: public interface IGene extends INbtReadable, INbtWritable {
  - `core-api/src/main/java/binnie/core/api/genetics/IItemAnalysable.java`
    - L1: package binnie.core.api.genetics;
    - L5: public interface IItemAnalysable {
    - L10: @Deprecated
  - `core-api/src/main/java/binnie/core/api/gui/Alignment.java`
    - L1: package binnie.core.api.gui;
    - L3: public enum Alignment {
    - L17: public int x() {
    - L21: public int y() {
    - L25: public Alignment opposite() {
  - `core-api/src/main/java/binnie/core/api/gui/IArea.java`
    - L1: package binnie.core.api.gui;
    - L3: public interface IArea {
  - `core-api/src/main/java/binnie/core/api/gui/IBinnieSprite.java`
    - L1: package binnie.core.api.gui;
    - L7: public interface IBinnieSprite {
    - L8: @SideOnly(Side.CLIENT)
  - `core-api/src/main/java/binnie/core/api/gui/IBorder.java`
    - L1: package binnie.core.api.gui;
    - L3: public interface IBorder {
  - `core-api/src/main/java/binnie/core/api/gui/IGuiItem.java`
    - L1: package binnie.core.api.gui;
    - L7: public interface IGuiItem {
  - `core-api/src/main/java/binnie/core/api/gui/IPoint.java`
    - L1: package binnie.core.api.gui;
    - L3: public interface IPoint {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `IBinnieRecipe` in `IBinnieRecipe.java`
- interface `ICraftingManager` in `ICraftingManager.java`
- interface `IBreedingSystem` in `IBreedingSystem.java`
- interface `IFieldKitPlugin` in `IFieldKitPlugin.java`
- key type `IFieldKitPlugin` (`IFieldKitPlugin.java`)
- interface `IGene` in `IGene.java`
- interface `IItemAnalysable` in `IItemAnalysable.java`
- enum `Alignment` in `Alignment.java`
- interface `IArea` in `IArea.java`
- interface `IBinnieSprite` in `IBinnieSprite.java`
- interface `IBorder` in `IBorder.java`
- interface `IGuiItem` in `IGuiItem.java`
- interface `IPoint` in `IPoint.java`
- interface `ITexture` in `ITexture.java`
- interface `ITitledWidget` in `ITitledWidget.java`
- interface `ITopLevelWidget` in `ITopLevelWidget.java`
- interface `IWidget` in `IWidget.java`
- interface `IWidgetAttribute` in `IWidgetAttribute.java`
- enum `RenderStage` in `RenderStage.java`
- enum `EventHandlerOrigin` in `EventHandlerOrigin.java`
- key type `EventHandlerOrigin` (`EventHandlerOrigin.java`)
- interface `OnEventHandler` in `OnEventHandler.java`
- key type `OnEventHandler` (`OnEventHandler.java`)

## Port relevance to Re-Forestry
- Data/source to extract for addon modules; not a runtime dependency.

## Source map
- `core-api/src/main/java/binnie/core/api/IBinnieRecipe.java`
- `core-api/src/main/java/binnie/core/api/ICraftingManager.java`
- `core-api/src/main/java/binnie/core/api/genetics/IBreedingSystem.java`
- `core-api/src/main/java/binnie/core/api/genetics/IFieldKitPlugin.java`
- `core-api/src/main/java/binnie/core/api/genetics/IGene.java`
- `core-api/src/main/java/binnie/core/api/genetics/IItemAnalysable.java`
- `core-api/src/main/java/binnie/core/api/gui/Alignment.java`
- `core-api/src/main/java/binnie/core/api/gui/IArea.java`
- `core-api/src/main/java/binnie/core/api/gui/IBinnieSprite.java`
- `core-api/src/main/java/binnie/core/api/gui/IBorder.java`
- `core-api/src/main/java/binnie/core/api/gui/IGuiItem.java`
- `core-api/src/main/java/binnie/core/api/gui/IPoint.java`
- `core-api/src/main/java/binnie/core/api/gui/ITexture.java`
- `core-api/src/main/java/binnie/core/api/gui/ITitledWidget.java`
- `core-api/src/main/java/binnie/core/api/gui/ITopLevelWidget.java`
- `core-api/src/main/java/binnie/core/api/gui/IWidget.java`
- `core-api/src/main/java/binnie/core/api/gui/IWidgetAttribute.java`
- `core-api/src/main/java/binnie/core/api/gui/RenderStage.java`
- `core-api/src/main/java/binnie/core/api/gui/events/Event.java`
- `core-api/src/main/java/binnie/core/api/gui/events/EventHandlerOrigin.java`
- `core-api/src/main/java/binnie/core/api/gui/events/OnEventHandler.java`
- `core-api/src/main/java/binnie/core/api/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
