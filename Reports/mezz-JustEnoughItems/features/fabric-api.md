# mezz-JustEnoughItems — fabric-api

- Alias: `JEI`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/mezz-JustEnoughItems`
- Package/path root: `FabricApi/src/main/java`
- Java files scanned: **8**
- Date: 2026-07-30

## Summary
Module `fabric-api` in `mezz-JustEnoughItems` is rooted at `FabricApi/src/main/java` (8 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `FabricTypes`
- `package-info`
- `IJeiFluidIngredient`
- `JeiFluidIngredient`
- `package-info`
- `package-info`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py JEI "fabric-api"`
- Source root exists: **True**
- Nested packages under this module:
  - `mezz/jei/api`
  - `mezz/jei/api/fabric`
  - `mezz/jei/api/fabric/constants`
  - `mezz/jei/api/fabric/ingredients`
  - `mezz/jei/api/fabric/ingredients/fluids`
- Declaration skim (first files):
  - `FabricApi/src/main/java/mezz/jei/api/fabric/constants/FabricTypes.java`
    - L1: package mezz.jei.api.fabric.constants;
    - L16: public final class FabricTypes {
    - L20: public static final IIngredientTypeWithSubtypes<Fluid, IJeiFluidIngredient> FLUID_STACK = new IIngredientTypeWithSubtypes<>() {
    - L21: @Override
    - L22: public String getUid() {
    - L26: @Override
    - L27: public Class<? extends IJeiFluidIngredient> getIngredientClass() {
    - L31: @Override
    - L32: public Class<? extends Fluid> getIngredientBaseClass() {
    - L36: @Override
    - L37: public Fluid getBase(IJeiFluidIngredient ingredient) {
    - L41: @Override
  - `FabricApi/src/main/java/mezz/jei/api/fabric/constants/package-info.java`
    - L1: @NullMarked
    - L2: package mezz.jei.api.fabric.constants;
  - `FabricApi/src/main/java/mezz/jei/api/fabric/ingredients/fluids/IJeiFluidIngredient.java`
    - L1: package mezz.jei.api.fabric.ingredients.fluids;
    - L11: @ApiStatus.NonExtendable
    - L12: public interface IJeiFluidIngredient {
  - `FabricApi/src/main/java/mezz/jei/api/fabric/ingredients/fluids/JeiFluidIngredient.java`
    - L1: package mezz.jei.api.fabric.ingredients.fluids;
    - L10: public record JeiFluidIngredient(FluidVariant fluid, long amount) implements IJeiFluidIngredient {
    - L11: @Override
    - L12: public FluidVariant getFluidVariant() {
    - L16: @Override
    - L17: public long getAmount() {
  - `FabricApi/src/main/java/mezz/jei/api/fabric/ingredients/fluids/package-info.java`
    - L1: @NullMarked
    - L2: package mezz.jei.api.fabric.ingredients.fluids;
  - `FabricApi/src/main/java/mezz/jei/api/fabric/ingredients/package-info.java`
    - L1: @NullMarked
    - L2: package mezz.jei.api.fabric.ingredients;
  - `FabricApi/src/main/java/mezz/jei/api/fabric/package-info.java`
    - L1: @NullMarked
    - L2: package mezz.jei.api.fabric;
  - `FabricApi/src/main/java/mezz/jei/api/package-info.java`
    - L1: @NullMarked
    - L2: package mezz.jei.api;

## Data & assets
Related resource paths (heuristic name match):
- `Common/src/main/resources/pack.mcmeta`
- `Common/src/main/resources/jei-icon.png`
- `Common/src/main/resources/assets/jei/atlases/gui.json`
- `Common/src/main/resources/assets/jei/lang/no_no.json`
- `Common/src/main/resources/assets/jei/lang/pt_pt.json`
- `Common/src/main/resources/assets/jei/lang/lt_lt.json`
- `Common/src/main/resources/assets/jei/lang/zh_cn.json`
- `Common/src/main/resources/assets/jei/lang/tr_tr.json`
- `Common/src/main/resources/assets/jei/lang/lzh.json`
- `Common/src/main/resources/assets/jei/lang/vi_vn.json`
- `Common/src/main/resources/assets/jei/lang/bg_bg.json`
- `Common/src/main/resources/assets/jei/lang/fr_fr.json`
- `Common/src/main/resources/assets/jei/lang/zh_tw.json`
- `Common/src/main/resources/assets/jei/lang/pl_pl.json`
- `Common/src/main/resources/assets/jei/lang/kk_kz.json`
- `Common/src/main/resources/assets/jei/lang/sv_se.json`
- `Common/src/main/resources/assets/jei/lang/es_ar.json`
- `Common/src/main/resources/assets/jei/lang/uk_ua.json`
- `Common/src/main/resources/assets/jei/lang/it_it.json`
- `Common/src/main/resources/assets/jei/lang/ko_kr.json`
- `Common/src/main/resources/assets/jei/lang/id_id.json`
- `Common/src/main/resources/assets/jei/lang/fi_fi.json`
- `Common/src/main/resources/assets/jei/lang/cs_cz.json`
- `Common/src/main/resources/assets/jei/lang/fil_ph.json`
- `Common/src/main/resources/assets/jei/lang/ru_ru.json`
- `Common/src/main/resources/assets/jei/lang/pt_br.json`
- `Common/src/main/resources/assets/jei/lang/es_es.json`
- `Common/src/main/resources/assets/jei/lang/el_gr.json`
- `Common/src/main/resources/assets/jei/lang/de_de.json`
- `Common/src/main/resources/assets/jei/lang/ja_jp.json`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/mezz-JustEnoughItems` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `IJeiFluidIngredient` in `IJeiFluidIngredient.java`
- record `JeiFluidIngredient` in `JeiFluidIngredient.java`

## Port relevance to Re-Forestry
- JEI interop patterns; Re-Forestry already ships factory/core JEI plugins.

## Source map
- `FabricApi/src/main/java/mezz/jei/api/fabric/constants/FabricTypes.java`
- `FabricApi/src/main/java/mezz/jei/api/fabric/constants/package-info.java`
- `FabricApi/src/main/java/mezz/jei/api/fabric/ingredients/fluids/IJeiFluidIngredient.java`
- `FabricApi/src/main/java/mezz/jei/api/fabric/ingredients/fluids/JeiFluidIngredient.java`
- `FabricApi/src/main/java/mezz/jei/api/fabric/ingredients/fluids/package-info.java`
- `FabricApi/src/main/java/mezz/jei/api/fabric/ingredients/package-info.java`
- `FabricApi/src/main/java/mezz/jei/api/fabric/package-info.java`
- `FabricApi/src/main/java/mezz/jei/api/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
