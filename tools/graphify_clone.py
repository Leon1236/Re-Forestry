#!/usr/bin/env python3
"""Build a code-only graphify graph for one cloned reference repo.

Usage:
  python3 tools/graphify_clone.py <repo-name>
  python3 tools/graphify_clone.py --all
  python3 tools/graphify_clone.py --list

Writes MarkDown_Maker/graphify/<repo>/graphify-out/{graph.json,...}
and updates MarkDown_Maker/graphify/index.json.
"""

from __future__ import annotations

import argparse
import json
import os
import shutil
import subprocess
import sys
import time
from pathlib import Path

from reference_repos import SCAN_ROOTS, SKIP, latest_clones

ROOT = Path(__file__).resolve().parents[1]
GRAPHIFY_ROOT = ROOT / "MarkDown_Maker" / "graphify"
INDEX_PATH = GRAPHIFY_ROOT / "index.json"


def resolve_graphify_bin() -> str:
    env_path = os.environ.get("GRAPHIFY")
    if env_path:
        return env_path
    found = shutil.which("graphify")
    if found:
        return found
    fallback = Path.home() / "micromamba" / "envs" / "cei" / "bin" / "graphify"
    if fallback.is_file():
        return str(fallback)
    return "graphify"


def hardlink_or_copy_tree(src: Path, dst: Path) -> None:
    if dst.exists():
        shutil.rmtree(dst)
    dst.mkdir(parents=True, exist_ok=True)
    try:
        subprocess.run(
            ["cp", "-a", "--link", f"{src}/.", str(dst)],
            check=True,
            capture_output=True,
            text=True,
        )
    except subprocess.CalledProcessError:
        subprocess.run(
            ["cp", "-a", f"{src}/.", str(dst)],
            check=True,
        )


def stage_scan(repo: str, clone: Path, rel_roots: list[str]) -> Path:
    out_repo = GRAPHIFY_ROOT / repo
    stage = out_repo / "_scan"
    if stage.exists():
        shutil.rmtree(stage)
    stage.mkdir(parents=True, exist_ok=True)

    if rel_roots == ["."]:
        hardlink_or_copy_tree(clone, stage)
        return stage

    if len(rel_roots) == 1:
        src = clone / rel_roots[0]
        if not src.is_dir():
            raise SystemExit(f"{repo}: missing scan root {src}")
        hardlink_or_copy_tree(src, stage)
        return stage

    for rel in rel_roots:
        src = clone / rel
        if not src.is_dir():
            print(f"WARN {repo}: skip missing {rel}", file=sys.stderr)
            continue
        dest = stage / rel
        dest.parent.mkdir(parents=True, exist_ok=True)
        hardlink_or_copy_tree(src, dest)
    return stage


def run_extract(scan: Path) -> Path:
    env = os.environ.copy()
    env["GRAPHIFY_FORCE"] = "1"
    cmd = [resolve_graphify_bin(), "extract", str(scan), "--code-only"]
    print(f"+ {' '.join(cmd)}", flush=True)
    proc = subprocess.run(cmd, env=env, text=True, capture_output=True)
    sys.stdout.write(proc.stdout)
    sys.stderr.write(proc.stderr)
    if proc.returncode != 0:
        raise SystemExit(f"graphify extract failed ({proc.returncode})")
    graph = scan / "graphify-out" / "graph.json"
    if not graph.is_file():
        raise SystemExit(f"missing {graph}")
    return scan / "graphify-out"


def promote_out(repo: str, produced: Path) -> Path:
    out_repo = GRAPHIFY_ROOT / repo
    final = out_repo / "graphify-out"
    if final.exists():
        shutil.rmtree(final)
    shutil.copytree(produced, final)
    return final


def graph_stats(graph_path: Path) -> tuple[int, int]:
    data = json.loads(graph_path.read_text(encoding="utf-8"))
    nodes = len(data.get("nodes", []))
    links = data.get("links", data.get("edges", []))
    return nodes, len(links)


def update_index(entry: dict) -> None:
    GRAPHIFY_ROOT.mkdir(parents=True, exist_ok=True)
    index = {}
    if INDEX_PATH.is_file():
        index = json.loads(INDEX_PATH.read_text(encoding="utf-8"))
    index[entry["repo"]] = entry
    INDEX_PATH.write_text(json.dumps(index, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")


def build_one(repo: str) -> dict:
    clones = latest_clones()
    if repo not in clones:
        raise SystemExit(f"No clone found for {repo}")
    if repo in SKIP:
        raise SystemExit(f"{repo} is skipped (not useful for code graphs)")
    roots = SCAN_ROOTS.get(repo)
    if not roots:
        raise SystemExit(f"No SCAN_ROOTS entry for {repo}")

    clone = clones[repo]
    t0 = time.time()
    print(f"=== {repo} ===", flush=True)
    print(f"clone: {clone}", flush=True)
    print(f"roots: {roots}", flush=True)

    scan = stage_scan(repo, clone, roots)
    produced = run_extract(scan)
    final = promote_out(repo, produced)

    # Drop staging to save disk; keep only graphify-out
    stage = GRAPHIFY_ROOT / repo / "_scan"
    if stage.exists():
        shutil.rmtree(stage)

    nodes, edges = graph_stats(final / "graph.json")
    meta = {
        "repo": repo,
        "clone": str(clone),
        "scan_roots": roots,
        "graph": str(final / "graph.json"),
        "nodes": nodes,
        "edges": edges,
        "seconds": round(time.time() - t0, 1),
        "built_at": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
    }
    (GRAPHIFY_ROOT / repo / "meta.json").write_text(
        json.dumps(meta, indent=2, ensure_ascii=False) + "\n", encoding="utf-8"
    )
    update_index(meta)
    print(
        f"OK {repo}: {nodes} nodes / {edges} edges → {meta['graph']} ({meta['seconds']}s)",
        flush=True,
    )
    return meta


def main() -> None:
    ap = argparse.ArgumentParser()
    ap.add_argument("repo", nargs="?")
    ap.add_argument("--all", action="store_true")
    ap.add_argument("--list", action="store_true")
    args = ap.parse_args()

    clones = latest_clones()
    planned = sorted(r for r in clones if r in SCAN_ROOTS and r not in SKIP)

    if args.list:
        for r in planned:
            print(f"{r}\t{clones[r]}")
        skipped = sorted(r for r in clones if r not in SCAN_ROOTS or r in SKIP)
        for r in skipped:
            reason = "SKIP" if r in SKIP else "no SCAN_ROOTS"
            print(f"# {r}\t{reason}\t{clones[r]}")
        return

    if args.all:
        failures = []
        for r in planned:
            try:
                build_one(r)
            except SystemExit as e:
                print(f"FAIL {r}: {e}", file=sys.stderr)
                failures.append(r)
        if failures:
            raise SystemExit(f"Failed: {', '.join(failures)}")
        return

    if not args.repo:
        ap.error("pass a repo name, --all, or --list")
    build_one(args.repo)


if __name__ == "__main__":
    main()
