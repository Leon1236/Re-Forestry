# bernie-g-geckolib — service

- Alias: `geckolib`
- Clone: `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib`
- Package/path root: `common/src/main/java/com/geckolib/service`
- Java files scanned: **5**
- Date: 2026-07-30

## Summary
Module `service` in `bernie-g-geckolib` is rooted at `common/src/main/java/com/geckolib/service` (5 Java sources). This annotated inventory covers its surface, layout, contracts, assets hooks, and Re-Forestry port relevance.

## Player / API surface
Primary types (Java file stems):
- `GeckoLibClient`
- `GeckoLibEvents`
- `GeckoLibNetworking`
- `GeckoLibPlatform`
- `package-info`

## Architecture
- Graph follow-up: `python3 tools/graphify_query.py geckolib "service"`
- Source root exists: **True**
- Declaration skim (first files):
  - `common/src/main/java/com/geckolib/service/GeckoLibClient.java`
    - L1: package com.geckolib.service;
    - L19: public interface GeckoLibClient {
  - `common/src/main/java/com/geckolib/service/GeckoLibEvents.java`
    - L1: package com.geckolib.service;
    - L20: @ApiStatus.Internal
    - L21: public interface GeckoLibEvents {
  - `common/src/main/java/com/geckolib/service/GeckoLibNetworking.java`
    - L1: package com.geckolib.service;
    - L34: public interface GeckoLibNetworking {
    - L54: @ApiStatus.Internal
    - L62: @ApiStatus.Internal
  - `common/src/main/java/com/geckolib/service/GeckoLibPlatform.java`
    - L1: package com.geckolib.service;
    - L10: public interface GeckoLibPlatform {
  - `common/src/main/java/com/geckolib/service/package-info.java`
    - L2: @NullMarked
    - L3: package com.geckolib.service;

## Data & assets
- No strongly name-matched resources under common resource roots; check parent mod resources / datagen providers.

## Dependencies
- In-mod: treat other packages as edges only (depends on / used by); do not expand this report into sibling modules.
- External: inspect clone build metadata under `/home/ivan/Documents/Kodiranje/Fabric Forestry 26.2/MarkDown_Maker/Finished_github_clone/2026-07-28_18-01-07/bernie-g-geckolib` (`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`).

## Notable algorithms / contracts
- interface `GeckoLibClient` in `GeckoLibClient.java`
- interface `GeckoLibEvents` in `GeckoLibEvents.java`
- interface `GeckoLibNetworking` in `GeckoLibNetworking.java`
- key type `GeckoLibNetworking` (`GeckoLibNetworking.java`)
- interface `GeckoLibPlatform` in `GeckoLibPlatform.java`

## Port relevance to Re-Forestry
- Optional animation library patterns; evaluate before adding soft dep.

## Source map
- `common/src/main/java/com/geckolib/service/GeckoLibClient.java`
- `common/src/main/java/com/geckolib/service/GeckoLibEvents.java`
- `common/src/main/java/com/geckolib/service/GeckoLibNetworking.java`
- `common/src/main/java/com/geckolib/service/GeckoLibPlatform.java`
- `common/src/main/java/com/geckolib/service/package-info.java`

## Open questions / gaps
- Confirm nested submodule boundaries called out in the repo inventory notes.
- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.
- Cross-check CE vs Immersive Forestry when the module is Forestry content.
