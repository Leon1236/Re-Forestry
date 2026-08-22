#!/usr/bin/env python3
"""Query a reference-repo graphify graph by MCP repo name.

Usage:
  python3 tools/graphify_query.py list
  python3 tools/graphify_query.py thedarkcolour-ForestryCE "TileBottler energy"
  python3 tools/graphify_query.py ForestryCE "path GuiBottler ContainerBottler"
  python3 tools/graphify_query.py --explain Minecraft-26.2 BoundingBox

Short aliases: CE (1.21.1), CE20 (frozen 1.20.1), IF, JEI, FAPI, Energy, MC, local / reforestry
"""

from __future__ import annotations

import argparse
import json
import shutil
import subprocess
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
INDEX_PATH = ROOT / "MarkDown_Maker" / "graphify" / "index.json"

ALIASES = {
    "ce": "thedarkcolour-ForestryCE",
    "forestryce": "thedarkcolour-ForestryCE",
    "ce21": "thedarkcolour-ForestryCE",
    "ce20": "thedarkcolour-ForestryCE-1.20.1",
    "forestryce120": "thedarkcolour-ForestryCE-1.20.1",
    "if": "thedarkcolour-Immersive-Forestry",
    "immersive": "thedarkcolour-Immersive-Forestry",
    "jei": "mezz-JustEnoughItems",
    "fapi": "FabricMC-fabric-api",
    "fabric-api": "FabricMC-fabric-api",
    "energy": "TechReborn-Energy",
    "mc": "Minecraft-26.2",
    "minecraft": "Minecraft-26.2",
    "local": "reforestry-local",
    "reforestry": "reforestry-local",
    "binnie": "ACGaming-Binnie",
    "gendustry": "thedarkcolour-gendustry",
    "modkit": "thedarkcolour-ModKit",
    "geckolib": "bernie-g-geckolib",
    "corelib": "SuperMartijn642-SuperMartijn642sCoreLib",
    "backpack": "Tiviacz1337-Travelers-Backpack",
    "kaupenjoe": "Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X",
    "forestry12": "ForestryMC-ForestryMC",
}


def load_index() -> dict:
    if not INDEX_PATH.is_file():
        raise SystemExit(f"Missing {INDEX_PATH} — run tools/graphify_clone.py --all first")
    return json.loads(INDEX_PATH.read_text(encoding="utf-8"))


def resolve_repo(name: str, index: dict) -> str:
    key = ALIASES.get(name.lower(), name)
    if key in index:
        return key
    matches = [k for k in index if name.lower() in k.lower()]
    if len(matches) == 1:
        return matches[0]
    raise SystemExit(f"Unknown repo {name!r}. Try: python3 tools/graphify_query.py list")


def main() -> None:
    ap = argparse.ArgumentParser()
    ap.add_argument("repo", nargs="?", help="repo name or alias, or 'list'")
    ap.add_argument("question", nargs="*", help="query / path args / explain target")
    ap.add_argument("--explain", action="store_true")
    ap.add_argument("--path", action="store_true", help="shortest path between two node labels")
    ap.add_argument("--budget", type=int, default=2000)
    args = ap.parse_args()

    index = load_index()

    if not args.repo or args.repo == "list":
        for repo, meta in sorted(index.items()):
            print(f"{repo}\t{meta['nodes']}n/{meta['edges']}e\t{meta['graph']}")
        return

    repo = resolve_repo(args.repo, index)
    graph = index[repo]["graph"]
    if not Path(graph).is_file():
        raise SystemExit(f"Missing graph for {repo}: {graph}")

    graphify = shutil.which("graphify") or "graphify"
    q = args.question

    if args.explain:
        if not q:
            raise SystemExit("--explain needs a node label")
        cmd = [graphify, "explain", " ".join(q), "--graph", graph]
    elif args.path or (len(q) >= 2 and q[0] == "path"):
        parts = q[1:] if q and q[0] == "path" else q
        if len(parts) < 2:
            raise SystemExit("--path needs two node labels")
        cmd = [graphify, "path", parts[0], parts[1], "--graph", graph]
    else:
        if not q:
            raise SystemExit("provide a question")
        cmd = [graphify, "query", " ".join(q), "--graph", graph, "--budget", str(args.budget)]

    print(f"# {repo} → {graph}", flush=True)
    proc = subprocess.run(cmd)
    raise SystemExit(proc.returncode)


if __name__ == "__main__":
    main()
