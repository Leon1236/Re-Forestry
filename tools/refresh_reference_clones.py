#!/usr/bin/env python3
"""Fetch latest GitHub reference clones, dump Markdown, ingest mods.db, rebuild graphify.

Usage:
  python3 tools/refresh_reference_clones.py --check
  python3 tools/refresh_reference_clones.py
  python3 tools/refresh_reference_clones.py CE JEI FAPI
  python3 tools/refresh_reference_clones.py --include-frozen --force
"""

from __future__ import annotations

import argparse
import importlib.util
import os
import shutil
import sqlite3
import subprocess
import sys
from dataclasses import dataclass
from pathlib import Path
from types import ModuleType

from graphify_query import ALIASES
from reference_repos import (
    BY_NAME,
    CLONE_WORK,
    DUMP_SCRIPT,
    INGEST_SCRIPT,
    MARKDOWN_DIR,
    MODS_DB,
    REPOS,
    RefRepo,
    default_refresh_names,
    latest_clones,
)

GIT_TIMEOUT_SECONDS = 300
LS_REMOTE_TIMEOUT_SECONDS = 45


@dataclass
class PlanItem:
    repo: RefRepo
    local_sha: str
    remote_sha: str
    action: str
    detail: str


def load_module(name: str, path: Path) -> ModuleType:
    spec = importlib.util.spec_from_file_location(name, path)
    if spec is None or spec.loader is None:
        raise SystemExit(f"Cannot load {path}")
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    return module


def git_env() -> dict[str, str]:
    env = os.environ.copy()
    env["GIT_TERMINAL_PROMPT"] = "0"
    return env


def run_git(
    args: list[str],
    cwd: Path | None = None,
    timeout: int = GIT_TIMEOUT_SECONDS,
) -> subprocess.CompletedProcess[str]:
    return subprocess.run(
        args,
        cwd=cwd,
        capture_output=True,
        text=True,
        timeout=timeout,
        env=git_env(),
    )


def local_sha(clone: Path | None) -> str:
    if clone is None or not (clone / ".git").exists():
        return ""
    result = run_git(["git", "rev-parse", "HEAD"], cwd=clone, timeout=15)
    if result.returncode != 0:
        return ""
    return result.stdout.strip()


def remote_sha(url: str, branch: str) -> tuple[str, str]:
    try:
        result = run_git(
            ["git", "ls-remote", "--heads", url, f"refs/heads/{branch}"],
            timeout=LS_REMOTE_TIMEOUT_SECONDS,
        )
    except subprocess.TimeoutExpired:
        return "", "ls-remote timed out"
    except OSError as exc:
        return "", f"git missing: {exc}"
    if result.returncode != 0:
        err = (result.stderr or result.stdout).strip().splitlines()
        return "", err[-1] if err else f"ls-remote exit {result.returncode}"
    prefix = f"refs/heads/{branch}"
    for line in result.stdout.splitlines():
        if "\t" not in line:
            continue
        sha, ref = line.split("\t", 1)
        if ref == prefix:
            return sha, ""
    return "", f"branch {branch!r} not found"


def resolve_requested(
    names: list[str],
    include_frozen: bool,
    include_skipped: bool,
) -> list[RefRepo]:
    if not names:
        chosen = [BY_NAME[name] for name in default_refresh_names()]
        if include_frozen:
            chosen.extend(repo for repo in REPOS if repo.frozen)
        if include_skipped:
            chosen.extend(repo for repo in REPOS if repo.skip_by_default)
        seen: set[str] = set()
        ordered: list[RefRepo] = []
        for repo in chosen:
            if repo.name in seen:
                continue
            seen.add(repo.name)
            ordered.append(repo)
        return ordered

    resolved: list[RefRepo] = []
    unknown: list[str] = []
    for raw in names:
        key = ALIASES.get(raw.lower(), raw)
        repo = BY_NAME.get(key)
        if repo is None:
            matches = [item for item in REPOS if raw.lower() in item.name.lower()]
            if len(matches) == 1:
                repo = matches[0]
        if repo is None:
            unknown.append(raw)
        else:
            resolved.append(repo)
    if unknown:
        known = ", ".join(item.name for item in REPOS)
        raise SystemExit(f"Unknown repo(s): {', '.join(unknown)}\nKnown: {known}")
    return resolved


def plan_refresh(repos: list[RefRepo], force: bool) -> list[PlanItem]:
    clones = latest_clones()
    planned: list[PlanItem] = []
    for repo in repos:
        current = local_sha(clones.get(repo.name))
        remote, error = remote_sha(repo.url, repo.branch)
        if error:
            action = "skip-optional" if repo.optional else "fail"
            planned.append(PlanItem(repo, current, "", action, error))
            continue
        if current and current == remote and not force:
            planned.append(PlanItem(repo, current, remote, "skip", "already at remote HEAD"))
            continue
        if current and current == remote and force:
            planned.append(PlanItem(repo, current, remote, "refresh", "force rebuild at same SHA"))
            continue
        if not current:
            planned.append(PlanItem(repo, "", remote, "refresh", "no local clone"))
            continue
        planned.append(
            PlanItem(repo, current, remote, "refresh", f"{current[:12]} → {remote[:12]}")
        )
    return planned


def clone_repo(repo: RefRepo, dest: Path) -> None:
    if dest.exists():
        shutil.rmtree(dest)
    dest.parent.mkdir(parents=True, exist_ok=True)
    command = [
        "git",
        "clone",
        "--depth",
        "1",
        "--branch",
        repo.branch,
        "--single-branch",
        "--",
        repo.url,
        str(dest),
    ]
    print(f"+ git clone --branch {repo.branch} {repo.name}", flush=True)
    result = run_git(command, timeout=GIT_TIMEOUT_SECONDS)
    if result.returncode != 0:
        if dest.exists():
            shutil.rmtree(dest, ignore_errors=True)
        err = (result.stderr or result.stdout).strip().splitlines()
        raise RuntimeError(err[-1] if err else f"git clone exit {result.returncode}")


def ingest_markdown(ingest_mod: ModuleType, conn: sqlite3.Connection, md_path: Path) -> None:
    print(f"Ingest {md_path.name} ...", flush=True)
    entries = ingest_mod.parse_md_file(md_path)
    if not entries:
        raise RuntimeError(f"no file entries parsed from {md_path.name}")
    ingest_mod.ingest_repo(conn, md_path.stem, md_path.name, entries)
    print(f"  {md_path.stem}: {len(entries)} files → {MODS_DB.name}", flush=True)


def print_plan(items: list[PlanItem]) -> None:
    width = max(len(item.repo.name) for item in items)
    for item in items:
        local = item.local_sha[:12] if item.local_sha else "missing"
        remote = item.remote_sha[:12] if item.remote_sha else "-----"
        print(
            f"{item.repo.name:<{width}}  {item.repo.branch:<28}  "
            f"{local}  {remote}  {item.action}  {item.detail}"
        )


def main() -> int:
    ap = argparse.ArgumentParser(
        description=__doc__,
        formatter_class=argparse.RawDescriptionHelpFormatter,
    )
    ap.add_argument(
        "repos",
        nargs="*",
        help="repo folder names or aliases (CE, JEI, FAPI, …). Default: tracked non-frozen clones",
    )
    ap.add_argument("--check", action="store_true", help="compare SHAs and exit (no writes)")
    ap.add_argument("--dry-run", action="store_true", help="same as --check")
    ap.add_argument("--force", action="store_true", help="rebuild even when already at remote HEAD")
    ap.add_argument("--include-frozen", action="store_true", help="also refresh ForestryCE 1.20.1")
    ap.add_argument("--include-skipped", action="store_true", help="also refresh yarn")
    ap.add_argument("--skip-graphify", action="store_true", help="clone and ingest only")
    ap.add_argument("--skip-ingest", action="store_true", help="do not update mods.db")
    args = ap.parse_args()

    requested = resolve_requested(args.repos, args.include_frozen, args.include_skipped)
    print("Checking GitHub HEAD for each tracked branch ...", flush=True)
    items = plan_refresh(requested, args.force)
    print_plan(items)

    if args.check or args.dry_run:
        fails = [item for item in items if item.action == "fail"]
        return 1 if fails else 0

    to_refresh = [item for item in items if item.action == "refresh"]
    fails = [item for item in items if item.action == "fail"]
    if not to_refresh:
        if fails:
            print("Nothing refreshed; one or more repos failed the GitHub check.")
            return 1
        print("Nothing to update.")
        return 0

    dump_mod = load_module("md_dump", DUMP_SCRIPT)
    ingest_mod = None if args.skip_ingest else load_module("md_ingest", INGEST_SCRIPT)

    if args.skip_graphify:
        graphify_build_one = None
    else:
        from graphify_clone import build_one as graphify_build_one

    MARKDOWN_DIR.mkdir(parents=True, exist_ok=True)
    CLONE_WORK.mkdir(parents=True, exist_ok=True)

    cloned: list[tuple[RefRepo, Path]] = []
    for item in to_refresh:
        dest = CLONE_WORK / item.repo.name
        try:
            clone_repo(item.repo, dest)
            cloned.append((item.repo, dest))
        except (RuntimeError, subprocess.TimeoutExpired, OSError) as exc:
            print(f"FAIL clone {item.repo.name}: {exc}", file=sys.stderr)
            if item.repo.optional:
                continue
            fails.append(item)

    converted: list[tuple[RefRepo, Path]] = []
    for repo, dest in cloned:
        try:
            print(f"Markdown {repo.name} ...", flush=True)
            dump_mod.convert_repo_to_markdown(dest, MARKDOWN_DIR)
            converted.append((repo, dest))
        except Exception as exc:
            print(f"FAIL markdown {repo.name}: {exc}", file=sys.stderr)
            fails.append(PlanItem(repo, "", "", "fail", str(exc)))

    archived: list[RefRepo] = []
    if converted:
        archive_dir = dump_mod.unique_archive_folder(dump_mod.ARCHIVE_DIR)
        archive_dir.mkdir(parents=True, exist_ok=True)
        lines = [f"{repo.name}\t{repo.branch}\t{repo.url}" for repo, _dest in converted]
        (archive_dir / "manifest.txt").write_text("\n".join(lines) + "\n", encoding="utf-8")
        for repo, dest in converted:
            target = archive_dir / repo.name
            shutil.move(str(dest), str(target))
            print(f"Archived {repo.name} → {target}", flush=True)
            archived.append(repo)
        print(f"Archive folder: {archive_dir}", flush=True)

    if ingest_mod is not None and archived:
        conn = sqlite3.connect(MODS_DB)
        conn.execute("PRAGMA journal_mode=WAL")
        ingest_mod.init_db(conn)
        for repo in archived:
            md_path = MARKDOWN_DIR / f"{repo.name}.md"
            try:
                ingest_markdown(ingest_mod, conn, md_path)
            except Exception as exc:
                print(f"FAIL ingest {repo.name}: {exc}", file=sys.stderr)
                fails.append(PlanItem(repo, "", "", "fail", str(exc)))
        print("Rebuilding mods.db full-text index ...", flush=True)
        conn.execute("INSERT INTO files_fts(files_fts) VALUES ('rebuild')")
        conn.commit()
        conn.close()

    if graphify_build_one is not None:
        for repo in archived:
            if repo.scan_roots is None:
                print(f"Skip graphify {repo.name} (not a code graph)", flush=True)
                continue
            try:
                graphify_build_one(repo.name)
            except SystemExit as exc:
                print(f"FAIL graphify {repo.name}: {exc}", file=sys.stderr)
                fails.append(PlanItem(repo, "", "", "fail", str(exc)))

    print("\nDone.")
    print(f"  refreshed: {', '.join(repo.name for repo in archived) or '(none)'}")
    skipped = [item.repo.name for item in items if item.action.startswith("skip")]
    if skipped:
        print(f"  skipped:   {', '.join(skipped)}")
    if fails:
        print(f"  failed:    {', '.join(item.repo.name for item in fails)}")
        return 1
    print("MCP: mods.db updated. Reload the minecraft-mods MCP if a search still shows old files.")
    return 0


if __name__ == "__main__":
    try:
        raise SystemExit(main())
    except KeyboardInterrupt:
        print("\nInterrupted.", file=sys.stderr)
        raise SystemExit(130)
