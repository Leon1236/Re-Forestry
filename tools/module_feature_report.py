#!/usr/bin/env python3
"""Write one Phase-1 module feature report.

Usage:
  python3 tools/module_feature_report.py <repo> <slug> <java-root> <alias>

java-root examples:
  src/main/java/team/reborn/energy/api
  CommonApi
  common/src/main/java/com/geckolib/animation
  core/src/main/java/binnie/core
"""
from __future__ import annotations

import json
import re
import sys
from datetime import date
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
REPORTS = ROOT / "Reports"
INDEX = ROOT / "MarkDown_Maker" / "graphify" / "index.json"


def load_clone(repo: str) -> Path:
    idx = json.loads(INDEX.read_text())
    if repo not in idx:
        raise SystemExit(f"repo not in graphify index: {repo}")
    return Path(idx[repo]["clone"])


def list_java(pkg_dir: Path) -> list[Path]:
    if not pkg_dir.exists():
        return []
    return sorted(pkg_dir.rglob("*.java"))


def skim(p: Path, limit: int = 120) -> list[str]:
    try:
        lines = p.read_text(errors="replace").splitlines()
    except Exception as e:
        return [f"(unreadable: {e})"]
    out: list[str] = []
    for i, line in enumerate(lines[:limit], 1):
        s = line.strip()
        if (
            s.startswith("package ")
            or s.startswith("public ")
            or s.startswith("protected ")
            or s.startswith("@")
            or s.startswith("interface ")
            or s.startswith("enum ")
            or s.startswith("record ")
            or " class " in (" " + s)
        ):
            out.append(f"L{i}: {s[:220]}")
        if len(out) >= 35:
            break
    return out


def find_resources(clone: Path, hints: list[str]) -> list[str]:
    bases = [
        clone / "src/main/resources",
        clone / "Common/src/main/resources",
        clone / "Gui/src/main/resources",
        clone / "Library/src/main/resources",
        clone / "Fabric/src/main/resources",
        clone / "fabric/src/main/resources",
        clone / "common/src/main/resources",
    ]
    res: list[str] = []
    hints_l = [h.lower() for h in hints if h and len(h) > 2]
    for base in bases:
        if not base.exists():
            continue
        for f in base.rglob("*"):
            if not f.is_file():
                continue
            if f.suffix.lower() not in {".json", ".png", ".mcmeta", ".lang", ".toml", ".txt", ".nbt"}:
                continue
            path_l = str(f).lower()
            if any(h in path_l for h in hints_l):
                try:
                    res.append(str(f.relative_to(clone)))
                except ValueError:
                    res.append(str(f))
                if len(res) >= 30:
                    return res
    return res


def port_relevance(repo: str, slug: str) -> list[str]:
    impl = ROOT / "files" / "implemented-features.md"
    text = impl.read_text(errors="replace") if impl.exists() else ""
    hits: list[str] = []
    key = slug.replace("-", " ").replace("_", " ")
    if key and key.lower() in text.lower():
        hits.append(f"Mentions of `{slug}` appear in `files/implemented-features.md` — check that file for port status.")
    if "ForestryCE" in repo or "Immersive-Forestry" in repo:
        hits.append("Primary Forestry reference for Re-Forestry port decisions.")
    elif "ForestryMC" in repo:
        hits.append("1.12 Forestry — useful for CE-dropped content (greenhouse, book, climatology, database, etc.).")
    elif "Binnie" in repo:
        hits.append("Data/source to extract for addon modules; not a runtime dependency.")
    elif "gendustry" in repo:
        hits.append("Addon module shape for future `reforestry:gendustry`; not yet ported.")
    elif "JustEnoughItems" in repo:
        hits.append("JEI interop patterns; Re-Forestry already ships factory/core JEI plugins.")
    elif "Travelers-Backpack" in repo:
        hits.append("Adopt inventory/GUI/attachment patterns into Re-Forestry packages — no donor dep.")
    elif "geckolib" in repo:
        hits.append("Optional animation library patterns; evaluate before adding soft dep.")
    elif repo == "TechReborn-Energy":
        hits.append("Already used by Re-Forestry (`EnergyStorage`) for alveary/factory power.")
    else:
        hits.append("Pattern/reference only — do not add as a player dependency.")
    return hits


def resolve_pkg_dir(clone: Path, java_root: str) -> Path:
    raw = java_root.replace("`", "").strip()
    # Prefer explicit src/main/java path in the string
    m = re.search(
        r"((?:[\w.-]+/)*src/main/java/[\w./]+)",
        raw,
    )
    if m:
        cand = clone / m.group(1).rstrip("/")
        if cand.exists():
            return cand
    # Gradle module directory names (JEI)
    for name in [
        "CommonApi",
        "Common",
        "Library",
        "Gui",
        "Fabric",
        "FabricApi",
        "NeoForge",
        "NeoForgeApi",
        "Debug",
        "common",
        "fabric",
        "forge",
        "neoforge",
        "core",
        "core-api",
        "botany",
        "botany-api",
        "design",
        "design-api",
        "extrabees",
        "extratrees",
        "extratrees-api",
        "genetics",
        "genetics-api",
        "all",
    ]:
        if raw == name or raw.startswith(name + "/") or raw.startswith(name + " "):
            cand = clone / name
            if cand.exists():
                # if module root, prefer its java tree
                java = cand / "src/main/java"
                return java if java.exists() else cand
    # package name only
    pkg = raw.split("/")[0].split()[0].strip()
    if re.match(r"^[a-z][\w.]*$", pkg) and "." in pkg:
        rel = Path("src/main/java") / pkg.replace(".", "/")
        prefixes = [
            "",
            "common/",
            "fabric/",
            "core/",
            "core-api/",
            "botany/",
            "botany-api/",
            "design/",
            "design-api/",
            "extrabees/",
            "extratrees/",
            "extratrees-api/",
            "genetics/",
            "genetics-api/",
            "CommonApi/",
            "Common/",
            "Library/",
            "Gui/",
            "Fabric/",
            "FabricApi/",
            "NeoForge/",
            "NeoForgeApi/",
            "Debug/",
        ]
        for prefix in prefixes:
            cand = clone / (prefix + str(rel))
            if cand.exists():
                return cand
    cand = clone / raw
    if cand.exists():
        return cand
    return clone / raw


def extract_contracts(files: list[Path]) -> list[str]:
    contracts: list[str] = []
    for f in files:
        try:
            t = f.read_text(errors="replace")
        except Exception:
            continue
        for m in re.finditer(r"public\s+interface\s+(\w+)", t):
            contracts.append(f"interface `{m.group(1)}` in `{f.name}`")
        for m in re.finditer(r"public\s+enum\s+(\w+)", t):
            contracts.append(f"enum `{m.group(1)}` in `{f.name}`")
        for m in re.finditer(r"public\s+record\s+(\w+)", t):
            contracts.append(f"record `{m.group(1)}` in `{f.name}`")
        stem = f.stem.lower()
        if any(k in stem for k in ("registry", "registries", "module", "plugin", "handler", "network", "packet")):
            contracts.append(f"key type `{f.stem}` (`{f.name}`)")
    # dedupe preserve order
    seen = set()
    out = []
    for c in contracts:
        if c not in seen:
            seen.add(c)
            out.append(c)
    return out[:50]


def main() -> None:
    if len(sys.argv) < 4:
        raise SystemExit(__doc__)
    repo, slug, java_root = sys.argv[1], sys.argv[2], sys.argv[3]
    alias = sys.argv[4] if len(sys.argv) > 4 else repo
    clone = load_clone(repo)
    pkg_dir = resolve_pkg_dir(clone, java_root)
    files = list_java(pkg_dir)
    out = REPORTS / repo / "features" / f"{slug}.md"
    out.parent.mkdir(parents=True, exist_ok=True)

    classes = [f.stem for f in files]
    try:
        pkg_rel = str(pkg_dir.relative_to(clone)) if pkg_dir.exists() else str(pkg_dir)
    except ValueError:
        pkg_rel = str(pkg_dir)

    assets = find_resources(clone, [slug] + slug.split("-") + classes[:12])
    contracts = extract_contracts(files)

    # nested packages
    nested: list[str] = []
    if pkg_dir.exists() and files:
        nested = sorted({str(p.parent.relative_to(pkg_dir)) for p in files if p.parent != pkg_dir})

    lines: list[str] = []
    lines.append(f"# {repo} — {slug}")
    lines.append("")
    lines.append(f"- Alias: `{alias}`")
    lines.append(f"- Clone: `{clone}`")
    lines.append(f"- Package/path root: `{pkg_rel}`")
    lines.append(f"- Java files scanned: **{len(files)}**")
    lines.append(f"- Date: {date.today().isoformat()}")
    lines.append("")
    lines.append("## Summary")
    lines.append(
        f"Module `{slug}` in `{repo}` is rooted at `{pkg_rel}` "
        f"({len(files)} Java sources). This annotated inventory covers its surface, "
        f"layout, contracts, assets hooks, and Re-Forestry port relevance."
    )
    lines.append("")
    lines.append("## Player / API surface")
    if classes:
        lines.append("Primary types (Java file stems):")
        for c in classes[:80]:
            lines.append(f"- `{c}`")
        if len(classes) > 80:
            lines.append(f"- … and {len(classes) - 80} more")
    else:
        lines.append(
            f"- No `.java` files under `{pkg_rel}`. Verify inventory path "
            "(may be resources-only, Gradle module without sources here, or path mismatch)."
        )
    lines.append("")
    lines.append("## Architecture")
    lines.append(f"- Graph follow-up: `python3 tools/graphify_query.py {alias} \"{slug}\"`")
    lines.append(f"- Source root exists: **{pkg_dir.exists()}**")
    if nested:
        lines.append("- Nested packages under this module:")
        for s in nested[:50]:
            lines.append(f"  - `{s}`")
        if len(nested) > 50:
            lines.append(f"  - … and {len(nested) - 50} more")
    lines.append("- Declaration skim (first files):")
    for f in files[:12]:
        try:
            rel = f.relative_to(clone)
        except ValueError:
            rel = f
        lines.append(f"  - `{rel}`")
        for row in skim(f)[:12]:
            lines.append(f"    - {row}")
    lines.append("")
    lines.append("## Data & assets")
    if assets:
        lines.append("Related resource paths (heuristic name match):")
        for a in assets:
            lines.append(f"- `{a}`")
    else:
        lines.append(
            "- No strongly name-matched resources under common resource roots; "
            "check parent mod resources / datagen providers."
        )
    lines.append("")
    lines.append("## Dependencies")
    lines.append(
        "- In-mod: treat other packages as edges only (depends on / used by); "
        "do not expand this report into sibling modules."
    )
    lines.append(
        f"- External: inspect clone build metadata under `{clone}` "
        "(`build.gradle*`, `fabric.mod.json`, `mods.toml`, `gradle.properties`)."
    )
    lines.append("")
    lines.append("## Notable algorithms / contracts")
    if contracts:
        for c in contracts:
            lines.append(f"- {c}")
    else:
        lines.append("- No interfaces/enums/key registration types auto-detected; see declaration skim.")
    lines.append("")
    lines.append("## Port relevance to Re-Forestry")
    for h in port_relevance(repo, slug):
        lines.append(f"- {h}")
    lines.append("")
    lines.append("## Source map")
    for f in files[:120]:
        try:
            rel = f.relative_to(clone)
        except ValueError:
            rel = f
        lines.append(f"- `{rel}`")
    if len(files) > 120:
        lines.append(f"- … and {len(files) - 120} more under `{pkg_rel}`")
    lines.append("")
    lines.append("## Open questions / gaps")
    lines.append("- Confirm nested submodule boundaries called out in the repo inventory notes.")
    lines.append("- Deepen with graphify `--path` / `--explain` and MCP `get_file` on key classes when porting.")
    lines.append("- Cross-check CE vs Immersive Forestry when the module is Forestry content.")
    lines.append("")

    out.write_text("\n".join(lines))
    print(f"Wrote {out} ({len(files)} java files from {pkg_rel})")


if __name__ == "__main__":
    main()
