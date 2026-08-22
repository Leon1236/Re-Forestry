# ACGaming-Binnie — design-api

- Alias: `binnie`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie`
- Package/path root: `design-api/src/main/java/binnie/design/api`
- Java files scanned: **12**
- Date: 2026-07-30

## Summary
Module `design-api` in `ACGaming-Binnie` is rooted at `design-api/src/main/java/binnie/design/api` (12 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `DesignAPI`
- `IBlockDesign`
- `IBlockDesignProperties`
- `IDesign`
- `IDesignCategory`
- `IDesignManager`
- `IDesignMaterial`
- `IDesignSystem`
- `IDesignerType`
- `ILayout`
- `IPattern`
- `IToolHammer`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py binnie "design-api"`
- Source root exists: **True**
- Declaration skim (first files):
  - `design-api/src/main/java/binnie/design/api/DesignAPI.java`
    - L1: package binnie.design.api;
    - L5: public class DesignAPI {
    - L6: @Nullable
    - L7: public static IDesignManager manager;
  - `design-api/src/main/java/binnie/design/api/IBlockDesign.java`
    - L1: package binnie.design.api;
    - L3: public interface IBlockDesign {
  - `design-api/src/main/java/binnie/design/api/IBlockDesignProperties.java`
    - L1: package binnie.design.api;
    - L3: public interface IBlockDesignProperties {
  - `design-api/src/main/java/binnie/design/api/IDesign.java`
    - L1: package binnie.design.api;
    - L3: public interface IDesign {
  - `design-api/src/main/java/binnie/design/api/IDesignCategory.java`
    - L1: package binnie.design.api;
    - L5: public interface IDesignCategory {
  - `design-api/src/main/java/binnie/design/api/IDesignManager.java`
    - L1: package binnie.design.api;
    - L6: public interface IDesignManager {
  - `design-api/src/main/java/binnie/design/api/IDesignMaterial.java`
    - L1: package binnie.design.api;
    - L5: public interface IDesignMaterial {
  - `design-api/src/main/java/binnie/design/api/IDesignSystem.java`
    - L1: package binnie.design.api;
    - L10: public interface IDesignSystem {
    - L11: @Nullable
    - L12: @SideOnly(Side.CLIENT)
    - L15: @Nullable
    - L16: @SideOnly(Side.CLIENT)
    - L19: @SideOnly(Side.CLIENT)
    - L24: @Nullable
    - L33: @Nullable
  - `design-api/src/main/java/binnie/design/api/IDesignerType.java`
    - L1: package binnie.design.api;
    - L5: public interface IDesignerType {
  - `design-api/src/main/java/binnie/design/api/ILayout.java`
    - L1: package binnie.design.api;
    - L7: public interface ILayout {
    - L22: @SideOnly(Side.CLIENT)
    - L25: @SideOnly(Side.CLIENT)
  - `design-api/src/main/java/binnie/design/api/IPattern.java`
    - L1: package binnie.design.api;
    - L8: public interface IPattern extends ISpriteRegister {
    - L9: @SideOnly(Side.CLIENT)
    - L12: @SideOnly(Side.CLIENT)
  - `design-api/src/main/java/binnie/design/api/IToolHammer.java`
    - L1: package binnie.design.api;
    - L6: public interface IToolHammer {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `IBlockDesign` in `IBlockDesign.java`
- interface `IBlockDesignProperties` in `IBlockDesignProperties.java`
- interface `IDesign` in `IDesign.java`
- interface `IDesignCategory` in `IDesignCategory.java`
- interface `IDesignManager` in `IDesignManager.java`
- interface `IDesignMaterial` in `IDesignMaterial.java`
- interface `IDesignSystem` in `IDesignSystem.java`
- interface `IDesignerType` in `IDesignerType.java`
- interface `ILayout` in `ILayout.java`
- interface `IPattern` in `IPattern.java`
- interface `IToolHammer` in `IToolHammer.java`

## Port relevance to Re-Forestry
- Data/source to extract for addon modules; not a runtime dependency.

## Source map
- `design-api/src/main/java/binnie/design/api/DesignAPI.java`
- `design-api/src/main/java/binnie/design/api/IBlockDesign.java`
- `design-api/src/main/java/binnie/design/api/IBlockDesignProperties.java`
- `design-api/src/main/java/binnie/design/api/IDesign.java`
- `design-api/src/main/java/binnie/design/api/IDesignCategory.java`
- `design-api/src/main/java/binnie/design/api/IDesignManager.java`
- `design-api/src/main/java/binnie/design/api/IDesignMaterial.java`
- `design-api/src/main/java/binnie/design/api/IDesignSystem.java`
- `design-api/src/main/java/binnie/design/api/IDesignerType.java`
- `design-api/src/main/java/binnie/design/api/ILayout.java`
- `design-api/src/main/java/binnie/design/api/IPattern.java`
- `design-api/src/main/java/binnie/design/api/IToolHammer.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
