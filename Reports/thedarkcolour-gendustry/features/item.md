# thedarkcolour-gendustry — item

- Alias: `gendustry`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`
- Package/path root: `src/main/java/thedarkcolour/gendustry/item`
- Java files scanned: **13**
- Date: 2026-07-30

## Summary
Module `item` in `thedarkcolour-gendustry` is rooted at `src/main/java/thedarkcolour/gendustry/item` (13 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `DebugWand`
- `EliteGendustryUpgradeType`
- `GendustryResourceType`
- `GendustryUpgradeItem`
- `GendustryUpgradeType`
- `GeneSampleItem`
- `GeneticTemplateItem`
- `IGendustryUpgradeType`
- `PollenKitItem`
- `SpeciesTypeItem`
- `GeneSampleInfo`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py gendustry "item"`
- Source root exists: **True**
- Nested packages under this module:
  - `data`
- Declaration skim (first files):
  - `src/main/java/thedarkcolour/gendustry/item/DebugWand.java`
    - L1: package thedarkcolour.gendustry.item;
    - L12: public class DebugWand extends Item {
    - L13: public DebugWand() {
    - L17: @Override
    - L18: public InteractionResult useOn(UseOnContext ctx) {
  - `src/main/java/thedarkcolour/gendustry/item/EliteGendustryUpgradeType.java`
    - L1: package thedarkcolour.gendustry.item;
    - L7: public enum EliteGendustryUpgradeType implements IItemSubtype, IGendustryUpgradeType {
    - L31: @Override
    - L32: public String getSerializedName() {
    - L36: @Override
    - L37: public int maxStackSize() {
    - L41: @Override
    - L42: public int energyCost() {
  - `src/main/java/thedarkcolour/gendustry/item/GendustryResourceType.java`
    - L1: package thedarkcolour.gendustry.item;
    - L7: public enum GendustryResourceType implements IItemSubtype {
    - L22: @Override
    - L23: public String getSerializedName() {
  - `src/main/java/thedarkcolour/gendustry/item/GendustryUpgradeItem.java`
    - L1: package thedarkcolour.gendustry.item;
    - L15: public class GendustryUpgradeItem extends Item {
    - L17: @Nullable
    - L20: public GendustryUpgradeItem(IGendustryUpgradeType type) {
    - L26: @Override
    - L27: public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advanced) {
    - L37: public IGendustryUpgradeType getType() {
  - `src/main/java/thedarkcolour/gendustry/item/GendustryUpgradeType.java`
    - L1: package thedarkcolour.gendustry.item;
    - L7: public enum GendustryUpgradeType implements IItemSubtype, IGendustryUpgradeType {
    - L54: @Override
    - L55: public String getSerializedName() {
    - L59: @Override
    - L60: public int maxStackSize() {
    - L64: @Override
    - L65: public int energyCost() {
  - `src/main/java/thedarkcolour/gendustry/item/GeneSampleItem.java`
    - L1: package thedarkcolour.gendustry.item;
    - L24: public class GeneSampleItem extends SpeciesTypeItem {
    - L25: public static final String NBT_CHROMOSOME = "chromosome";
    - L26: public static final String NBT_ALLELE = "allele";
    - L28: public GeneSampleItem() {
    - L32: public static ItemStack createStack(ISpeciesType<?, ?> speciesType, IChromosome<?> chromosome, IAllele allele) {
    - L41: @Nullable
    - L42: public static IChromosome<?> getChromosome(ItemStack stack) {
    - L54: @Nullable
    - L55: public static IAllele getAllele(ItemStack stack) {
    - L67: @Override
    - L68: public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag pIsAdvanced) {
  - `src/main/java/thedarkcolour/gendustry/item/GeneticTemplateItem.java`
    - L1: package thedarkcolour.gendustry.item;
    - L27: public class GeneticTemplateItem extends SpeciesTypeItem {
    - L28: public static final String NBT_ALLELES = "alleles";
    - L30: public GeneticTemplateItem() {
    - L34: public static void addAlleles(ItemStack template, Map<IChromosome<?>, IAllele> samples) {
    - L40: public static Map<IChromosome<?>, IAllele> getAlleles(ItemStack template) {
    - L73: public static boolean isComplete(ItemStack stack) {
    - L79: @Override
    - L80: public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
  - `src/main/java/thedarkcolour/gendustry/item/IGendustryUpgradeType.java`
    - L1: package thedarkcolour.gendustry.item;
    - L3: public interface IGendustryUpgradeType {
  - `src/main/java/thedarkcolour/gendustry/item/PollenKitItem.java`
    - L1: package thedarkcolour.gendustry.item;
    - L13: public class PollenKitItem extends Item {
    - L14: public PollenKitItem() {
    - L18: @Override
    - L19: public InteractionResult useOn(UseOnContext ctx) {
  - `src/main/java/thedarkcolour/gendustry/item/SpeciesTypeItem.java`
    - L1: package thedarkcolour.gendustry.item;
    - L13: public abstract class SpeciesTypeItem extends Item {
    - L14: public static final String NBT_SPECIES_TYPE = "speciesType";
    - L16: public SpeciesTypeItem(Properties properties) {
    - L20: @Override
    - L21: public Component getName(ItemStack stack) {
    - L33: @Nullable
    - L34: public static ISpeciesType<?, ?> getSpeciesType(ItemStack stack) {
    - L46: public static void setSpeciesType(ItemStack stack, ISpeciesType<?, ?> type) {
  - `src/main/java/thedarkcolour/gendustry/item/data/GeneSampleInfo.java`
    - L1: package thedarkcolour.gendustry.item.data;
    - L11: public record GeneSampleInfo(ISpeciesType<?, ?> type, IChromosome<?> chromosome, IAllele allele) {
    - L12: public static final Codec<GeneSampleInfo> CODEC = RecordCodecBuilder.create(instance -> {
  - `src/main/java/thedarkcolour/gendustry/item/data/package-info.java`
    - L1: @net.minecraft.MethodsReturnNonnullByDefault
    - L2: @net.minecraft.FieldsAreNonnullByDefault
    - L3: @javax.annotation.ParametersAreNonnullByDefault
    - L4: package thedarkcolour.gendustry.item.data;

## Data & assets
Related resource paths (heuristic name match):
- `src/main/resources/assets/gendustry/textures/item/bucket_protein.png`
- `src/main/resources/assets/gendustry/textures/item/sieve_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/bucket_liquid_dna.png`
- `src/main/resources/assets/gendustry/textures/item/genetics_processor.png`
- `src/main/resources/assets/gendustry/textures/item/productivity_elite_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/mutation_elite_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/humidifier_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/immutable_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/pollen_kit.png`
- `src/main/resources/assets/gendustry/textures/item/gene_sample.png`
- `src/main/resources/assets/gendustry/textures/item/productivity_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/fertility_elite_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/weatherproof_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/youth_elite_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/cooler_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/scrubber_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/blank_genetic_template.png`
- `src/main/resources/assets/gendustry/textures/item/activity_simulator_elite_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/stabilizer_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/climate_control_module.png`
- `src/main/resources/assets/gendustry/textures/item/territory_elite_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/bucket_mutagen.png`
- `src/main/resources/assets/gendustry/textures/item/upgrade_frame.png`
- `src/main/resources/assets/gendustry/textures/item/pollination_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/heater_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/sky_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/territory_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/automation_upgrade.png`
- `src/main/resources/assets/gendustry/textures/item/blank_gene_sample.png`
- `src/main/resources/assets/gendustry/textures/item/elite_upgrade_frame.png`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- enum `EliteGendustryUpgradeType` in `EliteGendustryUpgradeType.java`
- enum `GendustryResourceType` in `GendustryResourceType.java`
- enum `GendustryUpgradeType` in `GendustryUpgradeType.java`
- interface `IGendustryUpgradeType` in `IGendustryUpgradeType.java`
- record `GeneSampleInfo` in `GeneSampleInfo.java`

## Port relevance to Re-Forestry
- Mentions of `item` appear in `files/implemented-features.md` — check that file for port status.
- Addon module shape for future `reforestry:gendustry`; not yet ported.

## Source map
- `src/main/java/thedarkcolour/gendustry/item/DebugWand.java`
- `src/main/java/thedarkcolour/gendustry/item/EliteGendustryUpgradeType.java`
- `src/main/java/thedarkcolour/gendustry/item/GendustryResourceType.java`
- `src/main/java/thedarkcolour/gendustry/item/GendustryUpgradeItem.java`
- `src/main/java/thedarkcolour/gendustry/item/GendustryUpgradeType.java`
- `src/main/java/thedarkcolour/gendustry/item/GeneSampleItem.java`
- `src/main/java/thedarkcolour/gendustry/item/GeneticTemplateItem.java`
- `src/main/java/thedarkcolour/gendustry/item/IGendustryUpgradeType.java`
- `src/main/java/thedarkcolour/gendustry/item/PollenKitItem.java`
- `src/main/java/thedarkcolour/gendustry/item/SpeciesTypeItem.java`
- `src/main/java/thedarkcolour/gendustry/item/data/GeneSampleInfo.java`
- `src/main/java/thedarkcolour/gendustry/item/data/package-info.java`
- `src/main/java/thedarkcolour/gendustry/item/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
