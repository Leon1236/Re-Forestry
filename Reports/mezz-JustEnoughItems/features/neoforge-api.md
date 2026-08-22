# mezz-JustEnoughItems — neoforge-api

- Alias: `JEI`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/mezz-JustEnoughItems`
- Package/path root: `NeoForgeApi/src/main/java`
- Java files scanned: **2**
- Date: 2026-07-30

## Summary
Module `neoforge-api` in `mezz-JustEnoughItems` is rooted at `NeoForgeApi/src/main/java` (2 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `NeoForgeTypes`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py JEI "neoforge-api"`
- Source root exists: **True**
- Nested packages under this module:
  - `mezz/jei/api/neoforge`
- Declaration skim (first files):
  - `NeoForgeApi/src/main/java/mezz/jei/api/neoforge/NeoForgeTypes.java`
    - L1: package mezz.jei.api.neoforge;
    - L14: public final class NeoForgeTypes {
    - L18: public static final IIngredientTypeWithSubtypes<Fluid, FluidStack> FLUID_STACK = new IIngredientTypeWithSubtypes<>() {
    - L19: @Override
    - L20: public String getUid() {
    - L24: @Override
    - L25: public Class<? extends FluidStack> getIngredientClass() {
    - L29: @Override
    - L30: public Class<? extends Fluid> getIngredientBaseClass() {
    - L34: @Override
    - L35: public Fluid getBase(FluidStack ingredient) {
    - L39: @Override
  - `NeoForgeApi/src/main/java/mezz/jei/api/neoforge/package-info.java`
    - L1: @NullMarked
    - L2: package mezz.jei.api.neoforge;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/mezz-JustEnoughItems` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- JEI interop patterns; Re-Forestry already ships factory/core JEI plugins.

## Source map
- `NeoForgeApi/src/main/java/mezz/jei/api/neoforge/NeoForgeTypes.java`
- `NeoForgeApi/src/main/java/mezz/jei/api/neoforge/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
