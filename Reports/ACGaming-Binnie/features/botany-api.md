# ACGaming-Binnie — botany-api

- Alias: `binnie`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie`
- Package/path root: `botany-api/src/main/java/binnie/botany/api`
- Java files scanned: **29**
- Date: 2026-07-30

## Summary
Module `botany-api` in `ACGaming-Binnie` is rooted at `botany-api/src/main/java/binnie/botany/api` (29 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `BotanyAPI`
- `IBotanyColored`
- `EnumAcidity`
- `EnumFertiliserType`
- `EnumMoisture`
- `EnumSoilType`
- `IBlockSoil`
- `IGardeningManager`
- `package-info`
- `EnumFlowerChromosome`
- `EnumFlowerColor`
- `EnumFlowerStage`
- `IAlleleFlowerColor`
- `IAlleleFlowerEffect`
- `IAlleleFlowerSpecies`
- `IAlleleFlowerSpeciesBuilder`
- `IBotanistTracker`
- `IColorMix`
- `IFlower`
- `IFlowerColor`
- `IFlowerDefinition`
- `IFlowerFactory`
- `IFlowerGenome`
- `IFlowerMutation`
- `IFlowerMutationBuilder`
- `IFlowerRoot`
- `IFlowerType`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py binnie "botany-api"`
- Source root exists: **True**
- Nested packages under this module:
  - `gardening`
  - `genetics`
- Declaration skim (first files):
  - `botany-api/src/main/java/binnie/botany/api/BotanyAPI.java`
    - L1: package binnie.botany.api;
    - L7: public class BotanyAPI {
    - L11: public static IFlowerRoot flowerRoot;
    - L16: public static IFlowerFactory flowerFactory;
    - L21: public static IGardeningManager gardening;
  - `botany-api/src/main/java/binnie/botany/api/IBotanyColored.java`
    - L1: package binnie.botany.api;
    - L8: public interface IBotanyColored extends IStringSerializable {
    - L9: @Nullable
  - `botany-api/src/main/java/binnie/botany/api/gardening/EnumAcidity.java`
    - L1: package binnie.botany.api.gardening;
    - L8: public enum EnumAcidity implements IBotanyColored {
    - L13: @Nullable
    - L20: @Override
    - L21: public String getName() {
    - L25: @Override
    - L26: public TextFormatting getColor() {
    - L30: public static EnumAcidity getFromValue(float rawAcidity) {
  - `botany-api/src/main/java/binnie/botany/api/gardening/EnumFertiliserType.java`
    - L1: package binnie.botany.api.gardening;
    - L3: public enum EnumFertiliserType {
  - `botany-api/src/main/java/binnie/botany/api/gardening/EnumMoisture.java`
    - L1: package binnie.botany.api.gardening;
    - L8: public enum EnumMoisture implements IBotanyColored {
    - L13: @Nullable
    - L20: @Override
    - L21: public String getName() {
    - L25: @Override
    - L26: public TextFormatting getColor() {
    - L30: public static EnumMoisture getFromValue(float rawMoisture) {
  - `botany-api/src/main/java/binnie/botany/api/gardening/EnumSoilType.java`
    - L1: package binnie.botany.api.gardening;
    - L6: public enum EnumSoilType implements IBotanyColored {
    - L17: @Override
    - L18: public String getName() {
    - L22: @Override
    - L23: public TextFormatting getColor() {
  - `botany-api/src/main/java/binnie/botany/api/gardening/IBlockSoil.java`
    - L1: package binnie.botany.api.gardening;
    - L6: public interface IBlockSoil {
  - `botany-api/src/main/java/binnie/botany/api/gardening/IGardeningManager.java`
    - L1: package binnie.botany.api.gardening;
    - L17: public interface IGardeningManager {
  - `botany-api/src/main/java/binnie/botany/api/gardening/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @MethodsReturnNonnullByDefault
    - L3: package binnie.botany.api.gardening;
  - `botany-api/src/main/java/binnie/botany/api/genetics/EnumFlowerChromosome.java`
    - L1: package binnie.botany.api.genetics;
    - L14: public enum EnumFlowerChromosome implements IChromosomeType {
    - L34: @Override
    - L35: public Class<? extends IAllele> getAlleleClass() {
    - L39: @Override
    - L40: public String getName() {
    - L44: @Override
    - L45: public ISpeciesRoot getSpeciesRoot() {
  - `botany-api/src/main/java/binnie/botany/api/genetics/EnumFlowerColor.java`
    - L1: package binnie.botany.api.genetics;
    - L9: public enum EnumFlowerColor implements IBotanyColored {
    - L91: public static final EnumFlowerColor[] VALUES = values();
    - L109: public String getIdent() {
    - L113: public static EnumFlowerColor get(int i) {
    - L117: @Override
    - L118: public String getName() {
  - `botany-api/src/main/java/binnie/botany/api/genetics/EnumFlowerStage.java`
    - L1: package binnie.botany.api.genetics;
    - L5: public enum EnumFlowerStage implements ISpeciesType {
    - L10: public static final EnumFlowerStage[] VALUES = values();
    - L17: public static EnumFlowerStage getStage(IFlower flower) {
    - L21: public static EnumFlowerStage getStage(int age) {
    - L25: public String getName() {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ACGaming-Binnie` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `IBotanyColored` in `IBotanyColored.java`
- enum `EnumAcidity` in `EnumAcidity.java`
- enum `EnumFertiliserType` in `EnumFertiliserType.java`
- enum `EnumMoisture` in `EnumMoisture.java`
- enum `EnumSoilType` in `EnumSoilType.java`
- interface `IBlockSoil` in `IBlockSoil.java`
- interface `IGardeningManager` in `IGardeningManager.java`
- enum `EnumFlowerChromosome` in `EnumFlowerChromosome.java`
- enum `EnumFlowerColor` in `EnumFlowerColor.java`
- enum `EnumFlowerStage` in `EnumFlowerStage.java`
- interface `IAlleleFlowerColor` in `IAlleleFlowerColor.java`
- interface `IAlleleFlowerEffect` in `IAlleleFlowerEffect.java`
- interface `IAlleleFlowerSpecies` in `IAlleleFlowerSpecies.java`
- interface `IAlleleFlowerSpeciesBuilder` in `IAlleleFlowerSpeciesBuilder.java`
- interface `IBotanistTracker` in `IBotanistTracker.java`
- interface `IColorMix` in `IColorMix.java`
- interface `IFlower` in `IFlower.java`
- interface `IFlowerColor` in `IFlowerColor.java`
- interface `IFlowerDefinition` in `IFlowerDefinition.java`
- interface `IFlowerFactory` in `IFlowerFactory.java`
- interface `IFlowerGenome` in `IFlowerGenome.java`
- interface `IFlowerMutation` in `IFlowerMutation.java`
- interface `IFlowerMutationBuilder` in `IFlowerMutationBuilder.java`
- interface `IFlowerRoot` in `IFlowerRoot.java`
- interface `IFlowerType` in `IFlowerType.java`

## Port relevance to Re-Forestry
- Data/source to extract for addon modules; not a runtime dependency.

## Source map
- `botany-api/src/main/java/binnie/botany/api/BotanyAPI.java`
- `botany-api/src/main/java/binnie/botany/api/IBotanyColored.java`
- `botany-api/src/main/java/binnie/botany/api/gardening/EnumAcidity.java`
- `botany-api/src/main/java/binnie/botany/api/gardening/EnumFertiliserType.java`
- `botany-api/src/main/java/binnie/botany/api/gardening/EnumMoisture.java`
- `botany-api/src/main/java/binnie/botany/api/gardening/EnumSoilType.java`
- `botany-api/src/main/java/binnie/botany/api/gardening/IBlockSoil.java`
- `botany-api/src/main/java/binnie/botany/api/gardening/IGardeningManager.java`
- `botany-api/src/main/java/binnie/botany/api/gardening/package-info.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/EnumFlowerChromosome.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/EnumFlowerColor.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/EnumFlowerStage.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IAlleleFlowerColor.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IAlleleFlowerEffect.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IAlleleFlowerSpecies.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IAlleleFlowerSpeciesBuilder.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IBotanistTracker.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IColorMix.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IFlower.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IFlowerColor.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IFlowerDefinition.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IFlowerFactory.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IFlowerGenome.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IFlowerMutation.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IFlowerMutationBuilder.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IFlowerRoot.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/IFlowerType.java`
- `botany-api/src/main/java/binnie/botany/api/genetics/package-info.java`
- `botany-api/src/main/java/binnie/botany/api/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
