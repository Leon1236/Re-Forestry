#!/usr/bin/env python3
"""Dump Loom-mapped Minecraft sources into MarkDown_Maker repo-dump format for ingest.py.

Usage:
  python3 tools/dump_minecraft_sources_md.py [src_root] [out_md]

Defaults:
  src_root = MarkDown_Maker/tmp_minecraft_26_2_src
  out_md   = MarkDown_Maker/Markdown_files/Minecraft-26.2.md
"""

from __future__ import annotations

import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
DEFAULT_SRC = ROOT / "MarkDown_Maker" / "tmp_minecraft_26_2_src"
DEFAULT_OUT = ROOT / "MarkDown_Maker" / "Markdown_files" / "Minecraft-26.2.md"
REPO_NAME = "Minecraft-26.2"


def lang_for(path: Path) -> str:
    return "java" if path.suffix == ".java" else path.suffix.lstrip(".") or "text"


def main() -> int:
    src_root = Path(sys.argv[1]) if len(sys.argv) > 1 else DEFAULT_SRC
    out_md = Path(sys.argv[2]) if len(sys.argv) > 2 else DEFAULT_OUT
    if not src_root.is_dir():
        print(f"Missing source root: {src_root}", file=sys.stderr)
        return 1

    java_files = sorted(src_root.rglob("*.java"))
    if not java_files:
        print(f"No .java files under {src_root}", file=sys.stderr)
        return 1

    rels = [p.relative_to(src_root).as_posix() for p in java_files]
    out_md.parent.mkdir(parents=True, exist_ok=True)

    with out_md.open("w", encoding="utf-8") as f:
        f.write(f"# {REPO_NAME}\n\n")
        f.write("## Directory structure\n\n```text\n")
        f.write(f"{REPO_NAME}/\n")
        for rel in rels:
            f.write(f"├── {rel}\n")
        f.write("```\n\n## File contents\n\n")
        for path, rel in zip(java_files, rels):
            try:
                content = path.read_text(encoding="utf-8", errors="replace")
            except OSError as e:
                print(f"  ! skip {rel}: {e}", file=sys.stderr)
                continue
            f.write(f"### {rel}\n\n")
            f.write(f"```{lang_for(path)}\n")
            f.write(content)
            if not content.endswith("\n"):
                f.write("\n")
            f.write("```\n\n")

    print(f"Wrote {out_md} ({len(rels)} files)")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
