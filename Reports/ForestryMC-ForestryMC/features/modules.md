# ForestryMC-ForestryMC — modules

- Alias: `forestry12`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC`
- Package/path root: `src/main/java/forestry/modules`
- Java files scanned: **8**
- Date: 2026-07-30

## Summary
Module `modules` in `ForestryMC-ForestryMC` is rooted at `src/main/java/forestry/modules` (8 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `BlankForestryModule`
- `ForestryModuleUids`
- `ForestryModules`
- `ForestryPluginUtil`
- `InternalModuleHandler`
- `ModuleHelper`
- `ModuleManager`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py forestry12 "modules"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/forestry/modules/BlankForestryModule.java`
    - L1: package forestry.modules;
    - L26: public class BlankForestryModule implements IForestryModule {
    - L31: public Set<ResourceLocation> getDependencyUids() {
    - L35: @Override
    - L36: public String toString() {
    - L44: public boolean processIMCMessage(FMLInterModComms.IMCMessage message) {
    - L48: public void populateChunk(IChunkGenerator chunkGenerator, World world, Random rand, int chunkX, int chunkZ, boolean hasVillageGenerated) {
    - L51: public void populateChunkRetroGen(World world, Random rand, int chunkX, int chunkZ) {
    - L54: public void decorateBiome(World world, Random rand, BlockPos pos) {
    - L60: public void registerBackpackItems() {
    - L66: public void registerCrates() {
    - L72: public void getHiddenItems(List<ItemStack> hiddenItems) {
  - `src/main/java/forestry/modules/ForestryModuleUids.java`
    - L1: package forestry.modules;
    - L3: public class ForestryModuleUids {
    - L5: public static final String APICULTURE = "apiculture";
    - L6: public static final String ARBORICULTURE = "arboriculture";
    - L7: public static final String CHARCOAL = "charcoal";
    - L8: public static final String CORE = "core";
    - L9: public static final String ENERGY = "energy";
    - L10: public static final String FACTORY = "factory";
    - L11: public static final String WORKTABLE = "worktable";
    - L12: public static final String FARMING = "farming";
    - L13: public static final String CLIMATOLOGY = "climatology";
    - L14: public static final String GREENHOUSE = "greenhouse";
  - `src/main/java/forestry/modules/ForestryModules.java`
    - L1: package forestry.modules;
    - L15: public class ForestryModules implements IModuleContainer {
    - L20: @Override
    - L21: public String getID() {
    - L25: @Override
    - L26: public boolean isAvailable() {
    - L30: @Override
    - L31: public Configuration getModulesConfig() {
    - L35: public static final Configuration getModulesConfiguration() {
    - L42: @Override
    - L43: public boolean isModuleEnabled(IForestryModule module) {
    - L51: @Override
  - `src/main/java/forestry/modules/ForestryPluginUtil.java`
    - L1: package forestry.modules;
    - L19: public class ForestryPluginUtil {
    - L24: public static Map<String, List<IForestryModule>> getForestryModules(ASMDataTable asmDataTable) {
    - L35: public static String getComment(IForestryModule module) {
  - `src/main/java/forestry/modules/InternalModuleHandler.java`
    - L1: package forestry.modules;
    - L29: public class InternalModuleHandler {
    - L31: public enum Stage {
    - L42: protected final Set<BlankForestryModule> modules = new LinkedHashSet();
    - L43: protected final Set<IForestryModule> disabledModules = new LinkedHashSet();
    - L44: protected final ModuleManager moduleManager;
    - L47: public InternalModuleHandler(ModuleManager moduleManager) {
    - L51: public void addModules(Collection<IForestryModule> modules, Collection<IForestryModule> disabledModules) {
    - L64: public Stage getStage() {
    - L68: public void runSetup() {
    - L89: public void runPreInit(Side side) {
  - `src/main/java/forestry/modules/ModuleHelper.java`
    - L1: package forestry.modules;
    - L16: public final class ModuleHelper {
    - L21: public static boolean isEnabled(String moduleID) {
    - L25: public static boolean allEnabled(String... moduleIDs) {
    - L29: public static boolean anyEnabled(String... moduleIDs) {
    - L33: public static boolean isModuleEnabled(String containerID, String moduleID) {
    - L38: public static void addItemToBackpack(String backpackUid, @Nullable ItemStack stack) {
    - L49: public static void registerCrate(@Nullable ItemStack stack) {
    - L60: public static void registerCrate(@Nullable Item item) {
    - L71: public static void registerCrate(@Nullable Block block) {
    - L82: public static void registerCrate(@Nullable String oreDict) {
  - `src/main/java/forestry/modules/ModuleManager.java`
    - L11: package forestry.modules;
    - L52: public class ModuleManager implements IModuleManager {
    - L57: public static final ArrayList<IPickupHandler> pickupHandlers = Lists.newArrayList();
    - L58: public static final ArrayList<ISaveEventHandler> saveEventHandlers = Lists.newArrayList();
    - L59: public static final ArrayList<IResupplyHandler> resupplyHandlers = Lists.newArrayList();
    - L65: public static final Set<IForestryModule> configDisabledModules = new HashSet<>();
    - L66: public static InternalModuleHandler internalHandler;
    - L71: public static ModuleManager getInstance() {
    - L75: @Override
    - L76: public boolean isModuleEnabled(ResourceLocation id) {
    - L80: @Override
    - L81: public void registerContainers(IModuleContainer... containers) {
  - `src/main/java/forestry/modules/package-info.java`
    - L1: @ParametersAreNonnullByDefault
    - L2: @FieldsAreNonnullByDefault
    - L3: @MethodsReturnNonnullByDefault
    - L4: package forestry.modules;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-24/ForestryMC-ForestryMC` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `BlankForestryModule` (`BlankForestryModule.java`)
- key type `ForestryModuleUids` (`ForestryModuleUids.java`)
- key type `ForestryModules` (`ForestryModules.java`)
- key type `ForestryPluginUtil` (`ForestryPluginUtil.java`)
- enum `Stage` in `InternalModuleHandler.java`
- key type `InternalModuleHandler` (`InternalModuleHandler.java`)
- key type `ModuleHelper` (`ModuleHelper.java`)
- key type `ModuleManager` (`ModuleManager.java`)

## Port relevance to Re-Forestry
- Mentions of `modules` appear in `files/implemented-features.md` — check that file for port status.
- 1.12 Forestry — useful for CE-dropped content (greenhouse, book, climatology, database, etc.).

## Source map
- `src/main/java/forestry/modules/BlankForestryModule.java`
- `src/main/java/forestry/modules/ForestryModuleUids.java`
- `src/main/java/forestry/modules/ForestryModules.java`
- `src/main/java/forestry/modules/ForestryPluginUtil.java`
- `src/main/java/forestry/modules/InternalModuleHandler.java`
- `src/main/java/forestry/modules/ModuleHelper.java`
- `src/main/java/forestry/modules/ModuleManager.java`
- `src/main/java/forestry/modules/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
