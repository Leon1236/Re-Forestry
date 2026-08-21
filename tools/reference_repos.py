#!/usr/bin/env python3
"""Catalog of GitHub reference clones used by Re-Forestry lookup.

Usage:
  python3 tools/reference_repos.py
  python3 tools/reference_repos.py --json
"""

from __future__ import annotations

import argparse
import json
from dataclasses import asdict, dataclass
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
ARCHIVE = ROOT / "MarkDown_Maker" / "Finished_github_clone"
CLONE_WORK = ROOT / "MarkDown_Maker" / "github_clone"
MARKDOWN_DIR = ROOT / "MarkDown_Maker" / "Markdown_files"
INGEST_SCRIPT = ROOT / "MarkDown_Maker" / "Markdown_files" / "ingest.py"
DUMP_SCRIPT = ROOT / "MarkDown_Maker" / "python.py"
MODS_DB = ROOT / "MarkDown_Maker" / "Markdown_files" / "mods.db"


@dataclass(frozen=True)
class RefRepo:
    name: str
    url: str
    branch: str
    scan_roots: tuple[str, ...] | None
    frozen: bool = False
    optional: bool = False
    skip_by_default: bool = False


REPOS: tuple[RefRepo, ...] = (
    RefRepo(
        "ACGaming-Binnie",
        "https://github.com/ACGaming/Binnie.git",
        "master-MC1.12",
        (".",),
    ),
    RefRepo(
        "FabricMC-fabric-api",
        "https://github.com/FabricMC/fabric-api.git",
        "26.2",
        (".",),
    ),
    RefRepo(
        "FabricMC-yarn",
        "https://github.com/FabricMC/yarn.git",
        "1.21.11",
        None,
        skip_by_default=True,
    ),
    RefRepo(
        "ForestryMC-ForestryMC",
        "https://github.com/ForestryMC/ForestryMC.git",
        "mc-1.12",
        ("src",),
    ),
    RefRepo(
        "SuperMartijn642-SuperMartijn642sCoreLib",
        "https://github.com/SuperMartijn642/SuperMartijn642sCoreLib.git",
        "forge-1.16",
        ("src",),
    ),
    RefRepo(
        "TechReborn-Energy",
        "https://github.com/TechReborn/Energy.git",
        "master",
        ("src",),
    ),
    RefRepo(
        "Tiviacz1337-Travelers-Backpack",
        "https://github.com/Tiviacz1337/Travelers-Backpack.git",
        "26.1-fabric",
        ("src",),
    ),
    RefRepo(
        "Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X",
        "https://github.com/Tutorials-By-Kaupenjoe/Fabric-Tutorial-26.X.git",
        "main",
        ("src",),
    ),
    RefRepo(
        "bernie-g-geckolib",
        "https://github.com/bernie-g/geckolib.git",
        "main",
        ("common", "fabric"),
    ),
    RefRepo(
        "mezz-JustEnoughItems",
        "https://github.com/mezz/JustEnoughItems.git",
        "26.2",
        ("Common", "CommonApi", "Fabric", "FabricApi", "Library"),
    ),
    RefRepo(
        "thedarkcolour-ForestryCE",
        "https://github.com/thedarkcolour/ForestryCE.git",
        "1.21.1",
        ("src",),
    ),
    RefRepo(
        "thedarkcolour-ForestryCE-1.20.1",
        "https://github.com/thedarkcolour/ForestryCE.git",
        "1.20.1",
        ("src",),
        frozen=True,
    ),
    RefRepo(
        "thedarkcolour-Immersive-Forestry",
        "https://github.com/thedarkcolour/Immersive-Forestry.git",
        "immersive-forestry/1.21.1",
        ("src",),
        optional=True,
    ),
    RefRepo(
        "thedarkcolour-ModKit",
        "https://github.com/thedarkcolour/ModKit.git",
        "1.20.1",
        ("src",),
    ),
    RefRepo(
        "thedarkcolour-gendustry",
        "https://github.com/thedarkcolour/gendustry.git",
        "1.20.1",
        ("src",),
    ),
)

BY_NAME = {repo.name: repo for repo in REPOS}
SCAN_ROOTS = {
    repo.name: list(repo.scan_roots) for repo in REPOS if repo.scan_roots is not None
}
SKIP = {repo.name for repo in REPOS if repo.scan_roots is None}


def latest_clones() -> dict[str, Path]:
    repos: dict[str, Path] = {}
    if not ARCHIVE.is_dir():
        return repos
    for day in sorted(ARCHIVE.iterdir()):
        if not day.is_dir():
            continue
        for child in day.iterdir():
            if child.is_dir() and child.name != "__pycache__":
                repos[child.name] = child
    return repos


def default_refresh_names() -> list[str]:
    return [
        repo.name
        for repo in REPOS
        if not repo.frozen and not repo.skip_by_default
    ]


def main() -> None:
    ap = argparse.ArgumentParser(description=__doc__)
    ap.add_argument("--json", action="store_true")
    args = ap.parse_args()
    if args.json:
        print(json.dumps([asdict(repo) for repo in REPOS], indent=2))
        return
    for repo in REPOS:
        flags = []
        if repo.frozen:
            flags.append("frozen")
        if repo.optional:
            flags.append("optional")
        if repo.skip_by_default:
            flags.append("skip-default")
        if repo.scan_roots is None:
            flags.append("no-graphify")
        extra = f" [{' '.join(flags)}]" if flags else ""
        print(f"{repo.name}\t{repo.branch}\t{repo.url}{extra}")


if __name__ == "__main__":
    main()
