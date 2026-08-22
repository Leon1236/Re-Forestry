# ACGaming-Binnie — genetics-api

- Alias: `binnie`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie`
- Package/path root: `genetics-api/src/main/java/binnie/genetics/api`
- Java files scanned: **15**
- Date: 2026-07-30

## Summary
Module `genetics-api` in `ACGaming-Binnie` is rooted at `genetics-api/src/main/java/binnie/genetics/api` (15 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `GeneticsApi`
- `IItemChargeable`
- `IItemSerum`
- `ITreeBreedingSystem`
- `IAcclimatiserManager`
- `IToleranceType`
- `AnalystConstants`
- `IAnalystIcons`
- `IAnalystManager`
- `IAnalystPagePlugin`
- `IBehaviourPlugin`
- `IBiologyPlugin`
- `IClimatePlugin`
- `IProducePlugin`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py binnie "genetics-api"`
- Source root exists: **True**
- Nested packages under this module:
  - `acclimatiser`
  - `analyst`
- Declaration skim (first files):
  - `genetics-api/src/main/java/binnie/genetics/api/GeneticsApi.java`
    - L1: package binnie.genetics.api;
    - L8: public class GeneticsApi {
    - L9: @Nullable
    - L10: public static IAcclimatiserManager acclimatiserManager;
    - L12: @Nullable
    - L13: public static IAnalystManager analystManager;
  - `genetics-api/src/main/java/binnie/genetics/api/IItemChargeable.java`
    - L1: package binnie.genetics.api;
    - L8: public interface IItemChargeable {
    - L13: @Nullable
  - `genetics-api/src/main/java/binnie/genetics/api/IItemSerum.java`
    - L1: package binnie.genetics.api;
    - L8: public interface IItemSerum extends IItemChargeable {
    - L11: @Nullable
  - `genetics-api/src/main/java/binnie/genetics/api/ITreeBreedingSystem.java`
    - L1: package binnie.genetics.api;
    - L12: public interface ITreeBreedingSystem extends IBreedingSystem {
  - `genetics-api/src/main/java/binnie/genetics/api/acclimatiser/IAcclimatiserManager.java`
    - L1: package binnie.genetics.api.acclimatiser;
    - L5: public interface IAcclimatiserManager {
  - `genetics-api/src/main/java/binnie/genetics/api/acclimatiser/IToleranceType.java`
    - L1: package binnie.genetics.api.acclimatiser;
    - L5: public interface IToleranceType {
  - `genetics-api/src/main/java/binnie/genetics/api/analyst/AnalystConstants.java`
    - L1: package binnie.genetics.api.analyst;
    - L3: public class AnalystConstants {
    - L4: public static final String KEY = "genetics.gui.analyst";
    - L5: public static final String PRODUCTS_KEY = KEY + ".products";
    - L6: public static final String APPEARANCE_KEY = KEY + ".appearance";
    - L7: public static final String SOIL_KEY = KEY + ".soil";
    - L8: public static final String BIOLOGY_KEY = KEY + ".biology";
    - L9: public static final String BEHAVIOUR_KEY = KEY + ".behaviour";
    - L10: public static final String DESCRIPTION_KEY = KEY + ".description";
    - L11: public static final String GENOME_KEY = KEY + ".genome";
    - L12: public static final String KARYOGRAM_KEY = KEY + ".karyogram";
    - L13: public static final String MUTATIONS_KEY = KEY + ".mutations";
  - `genetics-api/src/main/java/binnie/genetics/api/analyst/IAnalystIcons.java`
    - L1: package binnie.genetics.api.analyst;
    - L5: public interface IAnalystIcons {
  - `genetics-api/src/main/java/binnie/genetics/api/analyst/IAnalystManager.java`
    - L1: package binnie.genetics.api.analyst;
    - L15: public interface IAnalystManager {
    - L32: @SideOnly(Side.CLIENT)
    - L35: @SideOnly(Side.CLIENT)
    - L38: @SideOnly(Side.CLIENT)
    - L41: @SideOnly(Side.CLIENT)
    - L44: @SideOnly(Side.CLIENT)
  - `genetics-api/src/main/java/binnie/genetics/api/analyst/IAnalystPagePlugin.java`
    - L1: package binnie.genetics.api.analyst;
    - L12: public interface IAnalystPagePlugin<T extends IIndividual> {
    - L15: @SideOnly(Side.CLIENT)
  - `genetics-api/src/main/java/binnie/genetics/api/analyst/IBehaviourPlugin.java`
    - L1: package binnie.genetics.api.analyst;
    - L6: public interface IBehaviourPlugin<T extends IIndividual> {
  - `genetics-api/src/main/java/binnie/genetics/api/analyst/IBiologyPlugin.java`
    - L1: package binnie.genetics.api.analyst;
    - L8: public interface IBiologyPlugin<T extends IIndividual> {
    - L9: @SideOnly(Side.CLIENT)

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `IItemChargeable` in `IItemChargeable.java`
- interface `IItemSerum` in `IItemSerum.java`
- interface `ITreeBreedingSystem` in `ITreeBreedingSystem.java`
- interface `IAcclimatiserManager` in `IAcclimatiserManager.java`
- interface `IToleranceType` in `IToleranceType.java`
- interface `IAnalystIcons` in `IAnalystIcons.java`
- interface `IAnalystManager` in `IAnalystManager.java`
- interface `IAnalystPagePlugin` in `IAnalystPagePlugin.java`
- key type `IAnalystPagePlugin` (`IAnalystPagePlugin.java`)
- interface `IBehaviourPlugin` in `IBehaviourPlugin.java`
- key type `IBehaviourPlugin` (`IBehaviourPlugin.java`)
- interface `IBiologyPlugin` in `IBiologyPlugin.java`
- key type `IBiologyPlugin` (`IBiologyPlugin.java`)
- interface `IClimatePlugin` in `IClimatePlugin.java`
- key type `IClimatePlugin` (`IClimatePlugin.java`)
- interface `IProducePlugin` in `IProducePlugin.java`
- key type `IProducePlugin` (`IProducePlugin.java`)

## Port relevance to Re-Forestry
- Data/source to extract for addon modules; not a runtime dependency.

## Source map
- `genetics-api/src/main/java/binnie/genetics/api/GeneticsApi.java`
- `genetics-api/src/main/java/binnie/genetics/api/IItemChargeable.java`
- `genetics-api/src/main/java/binnie/genetics/api/IItemSerum.java`
- `genetics-api/src/main/java/binnie/genetics/api/ITreeBreedingSystem.java`
- `genetics-api/src/main/java/binnie/genetics/api/acclimatiser/IAcclimatiserManager.java`
- `genetics-api/src/main/java/binnie/genetics/api/acclimatiser/IToleranceType.java`
- `genetics-api/src/main/java/binnie/genetics/api/analyst/AnalystConstants.java`
- `genetics-api/src/main/java/binnie/genetics/api/analyst/IAnalystIcons.java`
- `genetics-api/src/main/java/binnie/genetics/api/analyst/IAnalystManager.java`
- `genetics-api/src/main/java/binnie/genetics/api/analyst/IAnalystPagePlugin.java`
- `genetics-api/src/main/java/binnie/genetics/api/analyst/IBehaviourPlugin.java`
- `genetics-api/src/main/java/binnie/genetics/api/analyst/IBiologyPlugin.java`
- `genetics-api/src/main/java/binnie/genetics/api/analyst/IClimatePlugin.java`
- `genetics-api/src/main/java/binnie/genetics/api/analyst/IProducePlugin.java`
- `genetics-api/src/main/java/binnie/genetics/api/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
