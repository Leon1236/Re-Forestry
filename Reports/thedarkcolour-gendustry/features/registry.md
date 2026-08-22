# thedarkcolour-gendustry — registry

- Alias: `gendustry`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry`
- Package/path root: `src/main/java/thedarkcolour/gendustry/registry`
- Java files scanned: **8**
- Date: 2026-07-30

## Summary
Module `registry` in `thedarkcolour-gendustry` is rooted at `src/main/java/thedarkcolour/gendustry/registry` (8 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `GBlockEntities`
- `GBlocks`
- `GCreativeTabs`
- `GFluids`
- `GItems`
- `GMenus`
- `GRecipeTypes`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py gendustry "registry"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/thedarkcolour/gendustry/registry/GBlockEntities.java`
    - L1: package thedarkcolour.gendustry.registry;
    - L23: @FeatureProvider
    - L24: public class GBlockEntities {
    - L27: public static final FeatureTileType<IndustrialApiaryBlockEntity> INDUSTRIAL_APIARY = REGISTRY.tile(IndustrialApiaryBlockEntity::new, "industrial_apiary", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.INDUSTRIAL_A
    - L28: public static final FeatureTileType<MutagenProducerBlockEntity> MUTAGEN_PRODUCER = REGISTRY.tile(MutagenProducerBlockEntity::new, "mutagen_producer", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.MUTAGEN_PRODUCER
    - L29: public static final FeatureTileType<DnaExtractorBlockEntity> DNA_EXTRACTOR = REGISTRY.tile(DnaExtractorBlockEntity::new, "dna_extractor", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.DNA_EXTRACTOR).block()));
    - L30: public static final FeatureTileType<ProteinLiquefierBlockEntity> PROTEIN_LIQUEFIER = REGISTRY.tile(ProteinLiquefierBlockEntity::new, "protein_liquefier", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.PROTEIN_LIQU
    - L31: public static final FeatureTileType<SamplerBlockEntity> SAMPLER = REGISTRY.tile(SamplerBlockEntity::new, "sampler", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.SAMPLER).block()));
    - L32: public static final FeatureTileType<MutatronBlockEntity> MUTATRON = REGISTRY.tile(MutatronBlockEntity::new, "mutatron", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.MUTATRON).block()));
    - L33: public static final FeatureTileType<AdvancedMutatronBlockEntity> ADVANCED_MUTATRON = REGISTRY.tile(AdvancedMutatronBlockEntity::new, "advanced_mutatron", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.ADVANCED_MUT
    - L34: public static final FeatureTileType<ImprinterBlockEntity> IMPRINTER = REGISTRY.tile(ImprinterBlockEntity::new, "imprinter", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.IMPRINTER).block()));
    - L35: public static final FeatureTileType<GeneticTransposerBlockEntity> GENETIC_TRANSPOSER = REGISTRY.tile(GeneticTransposerBlockEntity::new, "genetic_transposer", () -> Set.of(GBlocks.MACHINE.get(GendustryMachineType.GENETIC_
  - `src/main/java/thedarkcolour/gendustry/registry/GBlocks.java`
    - L1: package thedarkcolour.gendustry.registry;
    - L15: @FeatureProvider
    - L16: public class GBlocks {
    - L19: public static final FeatureBlockGroup<GendustryMachineBlock, GendustryMachineType> MACHINE = REGISTRY
  - `src/main/java/thedarkcolour/gendustry/registry/GCreativeTabs.java`
    - L1: package thedarkcolour.gendustry.registry;
    - L20: @FeatureProvider
    - L21: public class GCreativeTabs {
    - L24: public static final FeatureCreativeTab GENDUSTRY = REGISTRY.creativeTab("gendustry", tab -> {
    - L31: public static final FeatureCreativeTab GENE_SAMPLES = REGISTRY.creativeTab("gene_samples", tab -> {
  - `src/main/java/thedarkcolour/gendustry/registry/GFluids.java`
    - L1: package thedarkcolour.gendustry.registry;
    - L22: @FeatureProvider
    - L23: public enum GFluids {
    - L47: public final ResourceLocation getId() {
    - L51: public FeatureFluid getFeature() {
    - L55: public BucketItem getBucket() {
    - L59: public Fluid fluid() {
    - L63: public Fluid getFlowing() {
    - L67: public FluidStack fluidStack(int mb) {
  - `src/main/java/thedarkcolour/gendustry/registry/GItems.java`
    - L1: package thedarkcolour.gendustry.registry;
    - L22: @FeatureProvider
    - L23: public class GItems {
    - L26: public static final FeatureItemGroup<ItemForestry, GendustryResourceType> RESOURCE = REGISTRY.itemGroup(subtype -> new ItemForestry(), GendustryResourceType.values()).create();
    - L27: public static final FeatureItemGroup<GendustryUpgradeItem, GendustryUpgradeType> UPGRADE = REGISTRY.itemGroup(GendustryUpgradeItem::new, GendustryUpgradeType.values()).identifier("upgrade", FeatureGroup.IdentifierType.SU
    - L28: public static final FeatureItemGroup<GendustryUpgradeItem, EliteGendustryUpgradeType> ELITE_UPGRADE = REGISTRY.itemGroup(GendustryUpgradeItem::new, EliteGendustryUpgradeType.values()).identifier("elite_upgrade", FeatureG
    - L29: public static final FeatureItem<Item> POLLEN_KIT = REGISTRY.item(PollenKitItem::new, "pollen_kit");
    - L30: public static final FeatureItem<Item> GENE_SAMPLE = REGISTRY.item(GeneSampleItem::new, "gene_sample");
    - L31: public static final FeatureItem<Item> GENETIC_TEMPLATE = REGISTRY.item(GeneticTemplateItem::new, "genetic_template");
  - `src/main/java/thedarkcolour/gendustry/registry/GMenus.java`
    - L1: package thedarkcolour.gendustry.registry;
    - L17: @FeatureProvider
    - L18: public class GMenus {
    - L22: public static final FeatureMenuType<ProducerMenu> PROCESSOR = REGISTRY.menuType(ProducerMenu::fromNetwork, "processor");
    - L23: public static final FeatureMenuType<ThreeInputMenu<? extends IHintTile>> SAMPLER = REGISTRY.menuType(ThreeInputMenu::samplerFromNetwork, "sampler");
    - L24: public static final FeatureMenuType<ThreeInputMenu<? extends IHintTile>> IMPRINTER = REGISTRY.menuType(ThreeInputMenu::imprinterFromNetwork, "imprinter");
    - L25: public static final FeatureMenuType<ThreeInputMenu<? extends IHintTile>> GENETIC_TRANSPOSER = REGISTRY.menuType(ThreeInputMenu::geneticTransposerFromNetwork, "genetic_transposer");
    - L26: public static final FeatureMenuType<MutatronMenu> MUTATRON = REGISTRY.menuType(MutatronMenu::fromNetwork, "mutatron");
    - L27: public static final FeatureMenuType<AdvancedMutatronMenu> ADVANCED_MUTATRON = REGISTRY.menuType(AdvancedMutatronMenu::fromNetwork, "advanced_mutatron");
    - L28: public static final FeatureMenuType<ReplicatorMenu> REPLICATOR = REGISTRY.menuType(ReplicatorMenu::fromNetwork, "replicator");
    - L29: public static final FeatureMenuType<IndustrialApiaryMenu> INDUSTRIAL_APIARY = REGISTRY.menuType(IndustrialApiaryMenu::fromNetwork, "industrial_apiary");
  - `src/main/java/thedarkcolour/gendustry/registry/GRecipeTypes.java`
    - L1: package thedarkcolour.gendustry.registry;
    - L19: @FeatureProvider
    - L20: public class GRecipeTypes {
    - L23: public static final FeatureRecipeType<MutagenRecipe> MUTAGEN = REGISTRY.recipeType("mutagen", MutagenRecipe.Serializer::new);
    - L24: public static final FeatureRecipeType<ProteinRecipe> PROTEIN = REGISTRY.recipeType("protein", ProteinRecipe.Serializer::new);
    - L25: public static final FeatureRecipeType<DnaRecipe> DNA = REGISTRY.recipeType("dna", DnaRecipe.Serializer::new);
    - L27: public static final RegistryObject<SimpleCraftingRecipeSerializer<?>> GENETIC_TEMPLATE_SERIALIZER = REGISTRY.getRegistry(Registries.RECIPE_SERIALIZER).register("genetic_template", () -> new SimpleCraftingRecipeSerializer
  - `src/main/java/thedarkcolour/gendustry/registry/package-info.java`
    - L1: @net.minecraft.MethodsReturnNonnullByDefault
    - L2: @net.minecraft.FieldsAreNonnullByDefault
    - L3: @javax.annotation.ParametersAreNonnullByDefault
    - L4: package thedarkcolour.gendustry.registry;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/thedarkcolour-gendustry` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- enum `GFluids` in `GFluids.java`

## Port relevance to Re-Forestry
- Mentions of `registry` appear in `files/implemented-features.md` — check that file for port status.
- Addon module shape for future `reforestry:gendustry`; not yet ported.

## Source map
- `src/main/java/thedarkcolour/gendustry/registry/GBlockEntities.java`
- `src/main/java/thedarkcolour/gendustry/registry/GBlocks.java`
- `src/main/java/thedarkcolour/gendustry/registry/GCreativeTabs.java`
- `src/main/java/thedarkcolour/gendustry/registry/GFluids.java`
- `src/main/java/thedarkcolour/gendustry/registry/GItems.java`
- `src/main/java/thedarkcolour/gendustry/registry/GMenus.java`
- `src/main/java/thedarkcolour/gendustry/registry/GRecipeTypes.java`
- `src/main/java/thedarkcolour/gendustry/registry/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
