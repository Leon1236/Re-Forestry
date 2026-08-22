# Tiviacz1337-Travelers-Backpack — commands

- Alias: `backpack`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack`
- Package/path root: `src/main/java/com/tiviacz/travelersbackpack/commands`
- Java files scanned: **5**
- Date: 2026-07-30

## Summary
Module `commands` in `Tiviacz1337-Travelers-Backpack` is rooted at `src/main/java/com/tiviacz/travelersbackpack/commands` (5 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `AccessCommand`
- `BackpackIconCommands`
- `ClearCommand`
- `RestoreCommand`
- `UnpackCommand`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py backpack "commands"`
- Source root exists: **True**
- Declaration skim (first files):
  - `src/main/java/com/tiviacz/travelersbackpack/commands/AccessCommand.java`
    - L1: package com.tiviacz.travelersbackpack.commands;
    - L21: public class AccessCommand {
    - L22: public AccessCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
    - L34: public int openTargetBlockEntity(CommandSourceStack source, BlockPos blockPos) throws CommandSyntaxException {
    - L45: public int openTargetInventory(CommandSourceStack source, ServerPlayer target) throws CommandSyntaxException {
  - `src/main/java/com/tiviacz/travelersbackpack/commands/BackpackIconCommands.java`
    - L1: package com.tiviacz.travelersbackpack.commands;
    - L10: public class BackpackIconCommands {
    - L11: public BackpackIconCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext commandBuildContext) {
    - L17: public int hideIcon(FabricClientCommandSource source) {
    - L24: public int showIcon() {
  - `src/main/java/com/tiviacz/travelersbackpack/commands/ClearCommand.java`
    - L1: package com.tiviacz.travelersbackpack.commands;
    - L17: public class ClearCommand {
    - L18: public ClearCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
  - `src/main/java/com/tiviacz/travelersbackpack/commands/RestoreCommand.java`
    - L1: package com.tiviacz.travelersbackpack.commands;
    - L24: public class RestoreCommand {
    - L39: public RestoreCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
    - L50: public int restoreBackpack(CommandSourceStack source, String backpackID, ServerPlayer player) {
  - `src/main/java/com/tiviacz/travelersbackpack/commands/UnpackCommand.java`
    - L1: package com.tiviacz.travelersbackpack.commands;
    - L25: public class UnpackCommand {
    - L26: public UnpackCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
    - L38: public int unpackTargetBlockEntity(CommandSourceStack source, BlockPos blockPos) {
    - L57: public int unpackTargetInventory(CommandSourceStack source, ServerPlayer serverPlayer) {
    - L86: public NonNullList<ItemStack> collectItems(BackpackWrapper wrapper) {
    - L94: public NonNullList<ItemStack> collectItems(ItemStackHandler handler) {

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-30/Tiviacz1337-Travelers-Backpack` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- No interfaces/enums/key registration types auto-detected; see declaration skim.

## Port relevance to Re-Forestry
- Mentions of `commands` appear in `files/implemented-features.md` — check that file for port status.
- Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.

## Source map
- `src/main/java/com/tiviacz/travelersbackpack/commands/AccessCommand.java`
- `src/main/java/com/tiviacz/travelersbackpack/commands/BackpackIconCommands.java`
- `src/main/java/com/tiviacz/travelersbackpack/commands/ClearCommand.java`
- `src/main/java/com/tiviacz/travelersbackpack/commands/RestoreCommand.java`
- `src/main/java/com/tiviacz/travelersbackpack/commands/UnpackCommand.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
