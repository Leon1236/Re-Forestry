# JDT null analysis (IDE-only)

## Source

VS Code / Eclipse JDT LS setting `java.compile.nullAnalysis.mode`. With `automatic`, Minecraft’s `@NonNull` type annotations plus JetBrains annotations on the classpath flood the Problems panel (~3k diagnostics). Gradle does **not** run this analysis.

## Policy (hybrid)

- Day-to-day: mode is **`disabled`** in `.vscode/settings.json` so codec/`forGetter` and override-annotation noise stay out of the way.
- Real null mismatches (e.g. nullable `LocalPlayer` into `@NonNull Player`, `null` UUID into `GameProfile`) are fixed in code.
- Prefer `@Nullable` on intentional nulls. Do not mass-annotate overrides with `@NonNull` unless re-enabling analysis for a focused pass.

## If re-enabled later

Expect floods from: codec method refs, Block/Screen/Recipe overrides missing `@NonNull`, free type-variable `return null`. Fix hotspots incrementally; do not treat silence as a single PR.
