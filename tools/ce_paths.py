#!/usr/bin/env python3
"""Latest frozen Forestry CE 1.20.1 clone paths for extract/import scripts.

Usage:
  python3 -c "from ce_paths import generated, rel; print(rel(generated()))"
"""

from __future__ import annotations

from pathlib import Path

from reference_repos import ROOT, latest_clones

CE120 = "thedarkcolour-ForestryCE-1.20.1"
FALLBACK = (
    ROOT
    / "MarkDown_Maker"
    / "Finished_github_clone"
    / "2026-08-17"
    / "thedarkcolour-ForestryCE-1.20.1"
)


def clone_root() -> Path:
    found = latest_clones().get(CE120)
    if found is not None and found.is_dir():
        return found
    return FALLBACK


def generated() -> Path:
    return clone_root() / "src" / "generated" / "resources"


def main_resources() -> Path:
    return clone_root() / "src" / "main" / "resources"


def rel(path: Path) -> str:
    try:
        return path.relative_to(ROOT).as_posix()
    except ValueError:
        return str(path)
