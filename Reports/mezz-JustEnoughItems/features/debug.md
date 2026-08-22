# mezz-JustEnoughItems — debug

- Alias: `JEI`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/mezz-JustEnoughItems`
- Package/path root: `Debug/src/main/java`
- Java files scanned: **26**
- Date: 2026-07-30

## Summary
Module `debug` in `mezz-JustEnoughItems` is rooted at `Debug/src/main/java` (26 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `DebugBrewingStandScreenHandler`
- `DebugCategoryDecorator`
- `DebugConfig`
- `DebugExclusionAreaHandler`
- `DebugFocusRecipeCategory`
- `DebugGhostIngredientHandler`
- `DebugGhostIngredientHandlerTwo`
- `DebugRecipe`
- `DebugRecipeCategory`
- `DebugSimpleRecipeManagerPlugin`
- `ErrorRecipe`
- `ErrorRecipeCategory`
- `FluidSubtypeHandlerTest`
- `JeiDebugPlugin`
- `ObnoxiouslyLargeCategory`
- `ObnoxiouslyLargeRecipe`
- `DebugIngredient`
- `DebugIngredientHelper`
- `DebugIngredientListFactory`
- `DebugIngredientRenderer`
- `ErrorIngredient`
- `ErrorIngredientHelper`
- `ErrorIngredientListFactory`
- `ErrorIngredientRenderer`
- `package-info`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py JEI "debug"`
- Source root exists: **True**
- Nested packages under this module:
  - `mezz/jei/debug`
  - `mezz/jei/debug/ingredients`
- Declaration skim (first files):
  - `Debug/src/main/java/mezz/jei/debug/DebugBrewingStandScreenHandler.java`
    - L1: package mezz.jei.debug;
    - L16: public class DebugBrewingStandScreenHandler implements IGuiContainerHandler<BrewingStandScreen> {
    - L19: public DebugBrewingStandScreenHandler(Supplier<Optional<IScreenHelper>> screenHelperSupplier) {
    - L23: @Override
    - L24: public List<Rect2i> getGuiExtraAreas(BrewingStandScreen containerScreen) {
    - L35: @Override
    - L36: public Optional<? extends IClickableIngredient<?>> getClickableIngredientUnderMouse(IClickableIngredientFactory factory, BrewingStandScreen containerScreen, double mouseX, double mouseY) {
  - `Debug/src/main/java/mezz/jei/debug/DebugCategoryDecorator.java`
    - L1: package mezz.jei.debug;
    - L9: class DebugCategoryDecorator<T> implements IRecipeCategoryDecorator<T> {
    - L12: @SuppressWarnings("unchecked")
    - L13: public static <T> DebugCategoryDecorator<T> getInstance() {
    - L17: @Override
    - L18: public void draw(
  - `Debug/src/main/java/mezz/jei/debug/DebugConfig.java`
    - L1: package mezz.jei.debug;
    - L3: public final class DebugConfig {
    - L10: public static boolean isCrashingTestIngredientsEnabled() {
    - L14: public static boolean isCrashingTestRecipesEnabled() {
  - `Debug/src/main/java/mezz/jei/debug/DebugExclusionAreaHandler.java`
    - L1: package mezz.jei.debug;
    - L18: public class DebugExclusionAreaHandler implements IGlobalGuiHandler {
    - L50: public DebugExclusionAreaHandler(BooleanSupplier screenHasGuiProperties) {
    - L54: @Override
    - L55: public Collection<Rect2i> getGuiExtraAreas() {
    - L83: @SuppressWarnings("unchecked")
  - `Debug/src/main/java/mezz/jei/debug/DebugFocusRecipeCategory.java`
    - L1: package mezz.jei.debug;
    - L21: public class DebugFocusRecipeCategory<F> implements IRecipeCategory<DebugRecipe> {
    - L22: public static final IRecipeType<DebugRecipe> TYPE = IRecipeType.create(ModIds.JEI_ID, "debug_focus", DebugRecipe.class);
    - L23: public static final int RECIPE_WIDTH = 160;
    - L24: public static final int RECIPE_HEIGHT = 60;
    - L28: public DebugFocusRecipeCategory(IPlatformFluidHelper<F> platformFluidHelper) {
    - L33: @Override
    - L34: public IRecipeType<DebugRecipe> getRecipeType() {
    - L38: @Override
    - L39: public Component getTitle() {
    - L43: @Override
    - L44: public int getWidth() {
  - `Debug/src/main/java/mezz/jei/debug/DebugGhostIngredientHandler.java`
    - L1: package mezz.jei.debug;
    - L20: public class DebugGhostIngredientHandler<T extends AbstractContainerScreen<?>> implements IGhostIngredientHandler<T> {
    - L26: public DebugGhostIngredientHandler(IIngredientManager ingredientManager, Supplier<Optional<IScreenHelper>> screenHelperSupplier) {
    - L31: @Override
    - L32: public <I> List<Target<I>> getTargetsTyped(T gui, ITypedIngredient<I> typedIngredient, boolean doStart) {
    - L67: @Override
    - L68: public void onComplete() {
    - L72: @Override
    - L73: public <I> boolean quickMove(T gui, ITypedIngredient<I> typedIngredient) {
    - L86: @Override
    - L87: public Rect2i getArea() {
    - L91: @Override
  - `Debug/src/main/java/mezz/jei/debug/DebugGhostIngredientHandlerTwo.java`
    - L1: package mezz.jei.debug;
    - L20: public class DebugGhostIngredientHandlerTwo<T extends AbstractContainerScreen<?>> implements IGhostIngredientHandler<T> {
    - L26: public DebugGhostIngredientHandlerTwo(IIngredientManager ingredientManager, Supplier<Optional<IScreenHelper>> screenHelperSupplier) {
    - L31: @Override
    - L32: public <I> List<Target<I>> getTargetsTyped(T gui, ITypedIngredient<I> typedIngredient, boolean doStart) {
    - L63: @Override
    - L64: public void onComplete() {
    - L74: @Override
    - L75: public Rect2i getArea() {
    - L79: @Override
    - L80: public void accept(I ingredient) {
  - `Debug/src/main/java/mezz/jei/debug/DebugRecipe.java`
    - L1: package mezz.jei.debug;
    - L8: public class DebugRecipe {
    - L14: public DebugRecipe() {
    - L22: public Button getButton() {
    - L26: public boolean checkHover(double mouseX, double mouseY) {
    - L30: public Identifier getId() {
  - `Debug/src/main/java/mezz/jei/debug/DebugRecipeCategory.java`
    - L1: package mezz.jei.debug;
    - L50: public class DebugRecipeCategory<F> implements IRecipeCategory<DebugRecipe> {
    - L51: public static final IRecipeType<DebugRecipe> TYPE = IRecipeType.create(ModIds.JEI_ID, "debug", DebugRecipe.class);
    - L52: public static final int RECIPE_WIDTH = 160;
    - L53: public static final int RECIPE_HEIGHT = 60;
    - L63: public DebugRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> platformFluidHelper, IIngredientManager ingredientManager) {
    - L74: public void setRuntime(IJeiRuntime runtime) {
    - L78: @Override
    - L79: public IRecipeType<DebugRecipe> getRecipeType() {
    - L83: @Override
    - L84: public Component getTitle() {
    - L88: @Override
  - `Debug/src/main/java/mezz/jei/debug/DebugSimpleRecipeManagerPlugin.java`
    - L1: package mezz.jei.debug;
    - L22: public class DebugSimpleRecipeManagerPlugin implements ISimpleRecipeManagerPlugin<RecipeHolder<CraftingRecipe>> {
    - L25: public DebugSimpleRecipeManagerPlugin(IJeiHelpers jeiHelpers) {
    - L29: @Override
    - L30: public boolean isHandledInput(ITypedIngredient<?> input) {
    - L35: @Override
    - L36: public boolean isHandledOutput(ITypedIngredient<?> output) {
    - L41: @Override
    - L42: public List<RecipeHolder<CraftingRecipe>> getRecipesForInput(ITypedIngredient<?> input) {
    - L46: @Override
    - L47: public List<RecipeHolder<CraftingRecipe>> getRecipesForOutput(ITypedIngredient<?> output) {
    - L51: @Override
  - `Debug/src/main/java/mezz/jei/debug/ErrorRecipe.java`
    - L1: package mezz.jei.debug;
    - L3: public class ErrorRecipe {
    - L6: public ErrorRecipe(CrashType type) {
    - L10: public CrashType getType() {
    - L14: public enum CrashType {
  - `Debug/src/main/java/mezz/jei/debug/ErrorRecipeCategory.java`
    - L1: package mezz.jei.debug;
    - L21: public class ErrorRecipeCategory extends AbstractRecipeCategory<ErrorRecipe> {
    - L22: public static final IRecipeType<ErrorRecipe> TYPE = IRecipeType.create(ModIds.JEI_ID, "error", ErrorRecipe.class);
    - L24: public ErrorRecipeCategory(IGuiHelper guiHelper) {
    - L34: @Override
    - L35: public void setRecipe(IRecipeLayoutBuilder builder, ErrorRecipe recipe, IFocusGroup focuses) {
    - L41: @Override
    - L42: public void draw(ErrorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
    - L48: @Override
    - L49: public void createRecipeExtras(IRecipeExtrasBuilder builder, ErrorRecipe recipe, IFocusGroup focuses) {
    - L55: @Override
    - L56: public void onDisplayedIngredientsUpdate(ErrorRecipe recipe, List<IRecipeSlotDrawable> recipeSlots, IFocusGroup focuses) {

## Data & assets
Related resource paths (heuristic name match):
- `Common/src/main/resources/assets/jei/textures/jei/gui/debug.png`

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_16-21-34/mezz-JustEnoughItems` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- key type `DebugBrewingStandScreenHandler` (`DebugBrewingStandScreenHandler.java`)
- key type `DebugExclusionAreaHandler` (`DebugExclusionAreaHandler.java`)
- key type `DebugGhostIngredientHandler` (`DebugGhostIngredientHandler.java`)
- key type `DebugGhostIngredientHandlerTwo` (`DebugGhostIngredientHandlerTwo.java`)
- key type `DebugSimpleRecipeManagerPlugin` (`DebugSimpleRecipeManagerPlugin.java`)
- enum `CrashType` in `ErrorRecipe.java`
- key type `FluidSubtypeHandlerTest` (`FluidSubtypeHandlerTest.java`)
- key type `JeiDebugPlugin` (`JeiDebugPlugin.java`)
- record `DebugIngredient` in `DebugIngredient.java`
- enum `CrashType` in `ErrorIngredient.java`
- record `ErrorIngredient` in `ErrorIngredient.java`

## Port relevance to Re-Forestry
- Mentions of `debug` appear in `files/implemented-features.md` — check that file for port status.
- JEI interop patterns; Re-Forestry already ships factory/core JEI plugins.

## Source map
- `Debug/src/main/java/mezz/jei/debug/DebugBrewingStandScreenHandler.java`
- `Debug/src/main/java/mezz/jei/debug/DebugCategoryDecorator.java`
- `Debug/src/main/java/mezz/jei/debug/DebugConfig.java`
- `Debug/src/main/java/mezz/jei/debug/DebugExclusionAreaHandler.java`
- `Debug/src/main/java/mezz/jei/debug/DebugFocusRecipeCategory.java`
- `Debug/src/main/java/mezz/jei/debug/DebugGhostIngredientHandler.java`
- `Debug/src/main/java/mezz/jei/debug/DebugGhostIngredientHandlerTwo.java`
- `Debug/src/main/java/mezz/jei/debug/DebugRecipe.java`
- `Debug/src/main/java/mezz/jei/debug/DebugRecipeCategory.java`
- `Debug/src/main/java/mezz/jei/debug/DebugSimpleRecipeManagerPlugin.java`
- `Debug/src/main/java/mezz/jei/debug/ErrorRecipe.java`
- `Debug/src/main/java/mezz/jei/debug/ErrorRecipeCategory.java`
- `Debug/src/main/java/mezz/jei/debug/FluidSubtypeHandlerTest.java`
- `Debug/src/main/java/mezz/jei/debug/JeiDebugPlugin.java`
- `Debug/src/main/java/mezz/jei/debug/ObnoxiouslyLargeCategory.java`
- `Debug/src/main/java/mezz/jei/debug/ObnoxiouslyLargeRecipe.java`
- `Debug/src/main/java/mezz/jei/debug/ingredients/DebugIngredient.java`
- `Debug/src/main/java/mezz/jei/debug/ingredients/DebugIngredientHelper.java`
- `Debug/src/main/java/mezz/jei/debug/ingredients/DebugIngredientListFactory.java`
- `Debug/src/main/java/mezz/jei/debug/ingredients/DebugIngredientRenderer.java`
- `Debug/src/main/java/mezz/jei/debug/ingredients/ErrorIngredient.java`
- `Debug/src/main/java/mezz/jei/debug/ingredients/ErrorIngredientHelper.java`
- `Debug/src/main/java/mezz/jei/debug/ingredients/ErrorIngredientListFactory.java`
- `Debug/src/main/java/mezz/jei/debug/ingredients/ErrorIngredientRenderer.java`
- `Debug/src/main/java/mezz/jei/debug/ingredients/package-info.java`
- `Debug/src/main/java/mezz/jei/debug/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
