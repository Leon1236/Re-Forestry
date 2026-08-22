# thedarkcolour-ForestryCE — plugin

- Alias: `CE`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/thedarkcolour-ForestryCE`
- Package/path root: `src/main/java/forestry/plugin`
- Java files scanned: **15**
- Date: 2026-07-30

## Summary
Module `plugin` in `thedarkcolour-ForestryCE` is rooted at `src/main/java/forestry/plugin` (15 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `BeeTaxonomy`
- `ButterflyTaxonomy`
- `DefaultBeeSpecies`
- `DefaultButterflySpecies`
- `DefaultFarms`
- `DefaultForestryPlugin`
- `DefaultTreeSpecies`
- `DefaultWoods`
- `TreeTaxonomy`
- `BeeAnalyzerPlugin`
- `ButterflyAnalyzerPlugin`
- `DefaultForestryClientRegistration`
- `TreeAnalyzerPlugin`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py CE "plugin"`
- Source root exists: **True**
- Nested packages under this module:
  - `client`
- Declaration skim (first files):
  - `src/main/java/forestry/plugin/BeeTaxonomy.java`
    - L1: package forestry.plugin;
    - L8: public class BeeTaxonomy {
    - L9: @SuppressWarnings("CodeBlock2Expr")
    - L10: public static void defineTaxa(IGeneticRegistration genetics) {
  - `src/main/java/forestry/plugin/ButterflyTaxonomy.java`
    - L1: package forestry.plugin;
    - L6: public class ButterflyTaxonomy {
    - L7: @SuppressWarnings("CodeBlock2Expr")
    - L8: public static void defineTaxa(IGeneticRegistration genetics) {
  - `src/main/java/forestry/plugin/DefaultBeeSpecies.java`
    - L1: package forestry.plugin;
    - L33: public class DefaultBeeSpecies {
    - L34: @SuppressWarnings("CodeBlock2Expr")
    - L35: public static void register(IApicultureRegistration apiculture) {
  - `src/main/java/forestry/plugin/DefaultButterflySpecies.java`
    - L1: package forestry.plugin;
    - L14: public class DefaultButterflySpecies {
    - L15: @SuppressWarnings("CodeBlock2Expr")
    - L16: public static void register(ILepidopterologyRegistration butterflies) {
  - `src/main/java/forestry/plugin/DefaultFarms.java`
    - L1: package forestry.plugin;
    - L28: public class DefaultFarms {
    - L29: public static void registerFarmTypes(IFarmingRegistration farming) {
  - `src/main/java/forestry/plugin/DefaultForestryPlugin.java`
    - L1: package forestry.plugin;
    - L66: public class DefaultForestryPlugin implements IForestryPlugin {
    - L67: public static final ResourceLocation ID = ForestryConstants.forestry("default");
    - L69: @Override
    - L70: public void registerGenetics(IGeneticRegistration genetics) {
  - `src/main/java/forestry/plugin/DefaultTreeSpecies.java`
    - L1: package forestry.plugin;
    - L22: public class DefaultTreeSpecies {
    - L23: public static void register(IArboricultureRegistration arboriculture) {
  - `src/main/java/forestry/plugin/DefaultWoods.java`
    - L1: package forestry.plugin;
    - L10: class DefaultWoods {
  - `src/main/java/forestry/plugin/TreeTaxonomy.java`
    - L1: package forestry.plugin;
    - L6: public class TreeTaxonomy {
    - L7: @SuppressWarnings("CodeBlock2Expr")
    - L8: public static void defineTaxa(IGeneticRegistration genetics) {
  - `src/main/java/forestry/plugin/client/BeeAnalyzerPlugin.java`
    - L1: package forestry.plugin.client;
    - L40: public class BeeAnalyzerPlugin implements IAnalyzerPlugin<IBeeSpecies, IBee> {
    - L95: @Override
    - L96: public void drawPage1(IAnalyzerGraphics<IBeeSpecies, IBee> graphics, IBee individual, ILifeStage stage, ItemStack specimen) {
    - L110: @Override
    - L111: public void drawPage2(IAnalyzerGraphics<IBeeSpecies, IBee> graphics, IBee individual, ILifeStage stage, ItemStack specimen) {
  - `src/main/java/forestry/plugin/client/ButterflyAnalyzerPlugin.java`
    - L1: package forestry.plugin.client;
    - L19: public class ButterflyAnalyzerPlugin implements IAnalyzerPlugin<IButterflySpecies, IButterfly> {
    - L22: @Override
    - L23: public void drawPage1(IAnalyzerGraphics<IButterflySpecies, IButterfly> graphics, IButterfly individual, ILifeStage stage, ItemStack specimen) {
    - L35: @Override
    - L36: public void drawPage2(IAnalyzerGraphics<IButterflySpecies, IButterfly> graphics, IButterfly individual, ILifeStage stage, ItemStack specimen) {
    - L44: @Override
    - L45: public void drawPage3(IAnalyzerGraphics<IButterflySpecies, IButterfly> graphics, IButterfly individual, ILifeStage stage, ItemStack specimen) {
    - L67: @Override
    - L68: public void drawPage4(IAnalyzerGraphics<IButterflySpecies, IButterfly> graphics, IButterfly individual, ILifeStage stage, ItemStack specimen) {
  - `src/main/java/forestry/plugin/client/DefaultForestryClientRegistration.java`
    - L1: package forestry.plugin.client;
    - L16: public class DefaultForestryClientRegistration implements Consumer<IClientRegistration> {
    - L17: @Override
    - L18: public void accept(IClientRegistration client) {

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/kubejs.plugins.txt`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/thedarkcolour-ForestryCE` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `DefaultForestryPlugin` (`DefaultForestryPlugin.java`)
- key type `BeeAnalyzerPlugin` (`BeeAnalyzerPlugin.java`)
- key type `ButterflyAnalyzerPlugin` (`ButterflyAnalyzerPlugin.java`)
- key type `TreeAnalyzerPlugin` (`TreeAnalyzerPlugin.java`)

## Port relevance to Re-Forestry
- Mentions of `plugin` appear in `files/implemented-features.md` — check that file for port status.
- Primary Forestry reference for Re-Forestry port decisions.

## Source map
- `src/main/java/forestry/plugin/BeeTaxonomy.java`
- `src/main/java/forestry/plugin/ButterflyTaxonomy.java`
- `src/main/java/forestry/plugin/DefaultBeeSpecies.java`
- `src/main/java/forestry/plugin/DefaultButterflySpecies.java`
- `src/main/java/forestry/plugin/DefaultFarms.java`
- `src/main/java/forestry/plugin/DefaultForestryPlugin.java`
- `src/main/java/forestry/plugin/DefaultTreeSpecies.java`
- `src/main/java/forestry/plugin/DefaultWoods.java`
- `src/main/java/forestry/plugin/TreeTaxonomy.java`
- `src/main/java/forestry/plugin/client/BeeAnalyzerPlugin.java`
- `src/main/java/forestry/plugin/client/ButterflyAnalyzerPlugin.java`
- `src/main/java/forestry/plugin/client/DefaultForestryClientRegistration.java`
- `src/main/java/forestry/plugin/client/TreeAnalyzerPlugin.java`
- `src/main/java/forestry/plugin/client/package-info.java`
- `src/main/java/forestry/plugin/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
