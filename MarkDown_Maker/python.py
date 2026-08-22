#!/usr/bin/env python3
"""Clone GitHub repositories, flatten each into a Markdown file, archive the run.

The script lives in a project root laid out like this (missing folders are
created at runtime)::

    project_root/
    ├── python.py
    ├── input/
    │   └── repo.txt
    ├── github_clone/
    ├── Markdown_files/
    └── Finished_github_clone/

Four phases run in order:

1.  **Read**    – parse ``input/repo.txt``: one repository URL per line;
    blank lines and lines starting with ``#`` are ignored.
2.  **Clone**   – ``git clone`` each URL into ``github_clone/<owner-repo>``
    (owner and repo taken from the URL, trailing ``.git`` removed, so two
    repos with the same name but different owners never collide). Existing
    folders are skipped; failures are logged and never abort the run.
3.  **Convert** – for every folder in ``github_clone/``, write one
    ``Markdown_files/<name>.md`` containing an H1 title, a directory tree,
    and each text file's contents in a fenced code block with a language
    hint. Binary, oversized, and unreadable files are listed by name only.
4.  **Archive** – move ``input/repo.txt`` and every entry of
    ``github_clone/`` into ``Finished_github_clone/<YYYY-MM-DD>/`` (a
    timestamp/counter suffix is appended if that folder already exists, so
    nothing is ever overwritten).

Only the Python standard library is used; a ``git`` executable must be on
PATH for the clone phase. Requires Python 3.9+.

Usage::

    python python.py               # shallow clones (git clone --depth 1)
    python python.py --no-shallow  # full-history clones
"""

from __future__ import annotations

import argparse
import os
import re
import shutil
import subprocess
import sys
from datetime import datetime
from pathlib import Path
from typing import Iterator, Optional, Sequence

# --------------------------------------------------------------------------- #
# Configuration
# --------------------------------------------------------------------------- #

PROJECT_ROOT = Path(__file__).resolve().parent

INPUT_DIR = PROJECT_ROOT / "input"
REPO_LIST_FILE = INPUT_DIR / "repo.txt"
CLONE_DIR = PROJECT_ROOT / "github_clone"
MARKDOWN_DIR = PROJECT_ROOT / "Markdown_files"
ARCHIVE_DIR = PROJECT_ROOT / "Finished_github_clone"

#: Clone with ``--depth 1`` by default; the ``--no-shallow`` flag flips this.
SHALLOW_CLONE_DEFAULT = True

#: Hard cap for one ``git clone`` so a stuck network cannot hang the run.
GIT_TIMEOUT_SECONDS = 300

#: Text files larger than this are listed, not inlined (None disables the cap).
MAX_TEXT_FILE_BYTES: Optional[int] = 1_000_000

#: Extensions treated as binary without sniffing the content.
BINARY_EXTENSIONS = {
    # images
    ".png", ".jpg", ".jpeg", ".gif", ".bmp", ".ico", ".icns", ".webp",
    ".tif", ".tiff", ".psd", ".heic", ".avif",
    # audio / video
    ".mp3", ".wav", ".ogg", ".flac", ".m4a", ".aac",
    ".mp4", ".m4v", ".avi", ".mov", ".mkv", ".webm", ".wmv",
    # archives
    ".zip", ".tar", ".gz", ".tgz", ".bz2", ".xz", ".7z", ".rar", ".zst",
    # executables / compiled artefacts
    ".exe", ".dll", ".so", ".dylib", ".bin", ".o", ".obj", ".a", ".lib",
    ".class", ".jar", ".war", ".pyc", ".pyo", ".pyd", ".whl",
    ".deb", ".rpm", ".msi", ".apk", ".dmg", ".iso", ".wasm",
    # fonts
    ".ttf", ".otf", ".woff", ".woff2", ".eot",
    # documents / data blobs
    ".pdf", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx",
    ".odt", ".ods", ".odp",
    ".db", ".sqlite", ".sqlite3", ".parquet", ".pickle", ".pkl",
    ".npy", ".npz", ".pt", ".pth", ".onnx", ".h5",
}

#: Case-insensitive special file names → fenced-code language hints.
SPECIAL_FILENAMES = {
    "dockerfile": "dockerfile",
    "makefile": "makefile",
    "gnumakefile": "makefile",
    "cmakelists.txt": "cmake",
    "gemfile": "ruby",
    "rakefile": "ruby",
    "vagrantfile": "ruby",
    "jenkinsfile": "groovy",
    ".gitignore": "text",
    ".gitattributes": "text",
    ".editorconfig": "ini",
    ".env": "bash",
    "license": "text",
    "notice": "text",
}

#: File extensions → fenced-code language hints ("" means no hint).
EXTENSION_LANGUAGES = {
    ".py": "python", ".pyw": "python", ".pyi": "python",
    ".js": "javascript", ".mjs": "javascript", ".cjs": "javascript",
    ".ts": "typescript", ".tsx": "tsx", ".jsx": "jsx",
    ".json": "json", ".jsonc": "json",
    ".html": "html", ".htm": "html", ".xml": "xml", ".svg": "xml",
    ".css": "css", ".scss": "scss", ".sass": "sass", ".less": "less",
    ".md": "markdown", ".markdown": "markdown", ".rst": "rst", ".txt": "text",
    ".yml": "yaml", ".yaml": "yaml", ".toml": "toml",
    ".ini": "ini", ".cfg": "ini", ".conf": "ini", ".properties": "properties",
    ".sh": "bash", ".bash": "bash", ".zsh": "bash", ".fish": "fish",
    ".ps1": "powershell", ".psm1": "powershell", ".bat": "batch", ".cmd": "batch",
    ".c": "c", ".h": "c",
    ".cpp": "cpp", ".cc": "cpp", ".cxx": "cpp", ".hpp": "cpp", ".hh": "cpp",
    ".cs": "csharp", ".java": "java",
    ".kt": "kotlin", ".kts": "kotlin", ".scala": "scala",
    ".go": "go", ".rs": "rust", ".rb": "ruby", ".php": "php", ".swift": "swift",
    ".pl": "perl", ".pm": "perl", ".lua": "lua", ".r": "r", ".jl": "julia",
    ".sql": "sql", ".graphql": "graphql", ".proto": "protobuf",
    ".dart": "dart", ".ex": "elixir", ".exs": "elixir", ".erl": "erlang",
    ".hs": "haskell", ".clj": "clojure", ".vue": "vue", ".svelte": "svelte",
    ".tf": "hcl", ".hcl": "hcl", ".gradle": "groovy", ".groovy": "groovy",
    ".csv": "csv", ".tsv": "tsv",
}

# --------------------------------------------------------------------------- #
# Small shared helpers
# --------------------------------------------------------------------------- #


def announce(phase: int, title: str) -> None:
    """Print a banner so each phase is easy to spot in the console output."""
    bar = "=" * 72
    print(f"\n{bar}\nPhase {phase}: {title}\n{bar}")


def _last_line(text: str) -> str:
    """Return the last non-empty line of *text* (git puts the reason there)."""
    lines = [line.strip() for line in text.splitlines() if line.strip()]
    return lines[-1] if lines else "no error output"


def _remove_tree(path: Path) -> None:
    """Best-effort removal of a partially created clone directory."""
    if path.exists():
        shutil.rmtree(path, ignore_errors=True)


def ensure_directories() -> None:
    """Create every folder the script relies on (idempotent)."""
    for directory in (INPUT_DIR, CLONE_DIR, MARKDOWN_DIR, ARCHIVE_DIR):
        directory.mkdir(parents=True, exist_ok=True)


# --------------------------------------------------------------------------- #
# Phase 1 — read the repository list
# --------------------------------------------------------------------------- #


def read_repo_list(repo_file: Path) -> list[str]:
    """Return the de-duplicated repository URLs listed in *repo_file*.

    Surrounding whitespace is stripped; blank lines and lines starting with
    ``#`` are ignored. A missing or unreadable file is reported, not fatal.
    """
    if not repo_file.is_file():
        print(f"[WARN] {repo_file} not found — nothing to clone this run.")
        return []

    try:
        # utf-8-sig quietly swallows a BOM from Windows-edited files.
        lines = repo_file.read_text(encoding="utf-8-sig", errors="replace").splitlines()
    except OSError as exc:
        print(f"[FAIL] Could not read {repo_file}: {exc}")
        return []

    urls: list[str] = []
    seen: set[str] = set()
    for number, raw in enumerate(lines, start=1):
        line = raw.strip()
        if not line or line.startswith("#"):
            continue
        if line in seen:
            print(f"[SKIP] Line {number}: duplicate URL {line}")
            continue
        seen.add(line)
        urls.append(line)
    return urls


# --------------------------------------------------------------------------- #
# Phase 2 — clone each repository
# --------------------------------------------------------------------------- #


#: Characters that are unsafe (or awkward) in a folder/file name are replaced
#: with this. Keeps owner/repo names filesystem- and shell-friendly.
_UNSAFE_NAME_CHARS = re.compile(r"[^A-Za-z0-9._-]+")


def _sanitize_name_part(part: str) -> str:
    """Make *part* safe to use as a path segment on any common filesystem."""
    cleaned = _UNSAFE_NAME_CHARS.sub("-", part).strip("-")
    return cleaned or "unknown"


def owner_and_repo_from_url(url: str) -> tuple[str, str]:
    """Extract ``(owner, repo)`` from a GitHub-style *url*.

    Handles ``https://github.com/owner/name(.git)``, with or without a
    trailing slash, as well as scp-like ``git@github.com:owner/name.git``.
    Both segments are sanitized for safe use in a filesystem path.
    """
    # Normalize the scp-like form (git@host:owner/name.git) to use slashes,
    # so both URL styles can be parsed the same way afterwards.
    normalized = url.strip()
    if "://" not in normalized and ":" in normalized:
        host_part, _, path_part = normalized.partition(":")
        normalized = f"{host_part}/{path_part}"

    path = normalized.rstrip("/")
    path = path.split("://", 1)[-1]  # drop scheme, if any
    path = path.split("@", 1)[-1]  # drop user@ prefix, if any
    segments = [segment for segment in path.split("/") if segment]

    if len(segments) < 2:
        raise ValueError(f"cannot derive an owner/repository name from {url!r}")

    owner, repo = segments[-2], segments[-1]
    if repo.endswith(".git"):
        repo = repo[: -len(".git")]

    owner = _sanitize_name_part(owner)
    repo = _sanitize_name_part(repo)
    if not repo or repo in {".", ".."}:
        raise ValueError(f"cannot derive a repository name from {url!r}")
    return owner, repo


def repo_name_from_url(url: str) -> str:
    """Derive the clone folder / Markdown file base name from *url*.

    Combines the owner and repository name, e.g.
    ``https://github.com/owner/my-project.git`` → ``owner-my-project``.
    This keeps repos with the same name but different authors from
    colliding in ``github_clone/`` or ``Markdown_files/``.
    """
    owner, repo = owner_and_repo_from_url(url)
    return f"{owner}-{repo}"


def clone_repo(url: str, clone_dir: Path, shallow: bool) -> str:
    """Clone *url* into *clone_dir*.

    Returns ``"cloned"``, ``"skipped"`` (target already exists), or
    ``"failed"``. Never raises — every problem is logged instead.
    """
    try:
        name = repo_name_from_url(url)
    except ValueError as exc:
        print(f"[FAIL] {url} — {exc}")
        return "failed"

    destination = clone_dir / name
    if destination.exists():
        print(f"[SKIP] {name} — {destination} already exists.")
        return "skipped"

    command = ["git", "clone"]
    if shallow:
        command += ["--depth", "1"]
    command += ["--", url, str(destination)]

    # Never let git block on an interactive username/password prompt
    # (e.g. for private or non-existent repositories).
    env = os.environ.copy()
    env["GIT_TERMINAL_PROMPT"] = "0"

    try:
        result = subprocess.run(
            command,
            capture_output=True,
            encoding="utf-8",
            errors="replace",
            timeout=GIT_TIMEOUT_SECONDS,
            env=env,
        )
    except subprocess.TimeoutExpired:
        print(f"[FAIL] {name} — clone timed out after {GIT_TIMEOUT_SECONDS}s.")
        _remove_tree(destination)
        return "failed"
    except OSError as exc:  # git disappeared mid-run, permission problems, …
        print(f"[FAIL] {name} — could not run git: {exc}")
        _remove_tree(destination)
        return "failed"

    if result.returncode != 0:
        print(f"[FAIL] {name} — git exited with {result.returncode}: "
              f"{_last_line(result.stderr)}")
        _remove_tree(destination)  # drop any partial checkout
        return "failed"

    print(f"[OK]   Cloned {name} into {destination}")
    return "cloned"


# --------------------------------------------------------------------------- #
# Phase 3 — convert each cloned repository to Markdown
# --------------------------------------------------------------------------- #


def build_tree(root: Path) -> str:
    """Return an ASCII tree of *root* (``.git`` excluded, symlinks not followed)."""
    lines = [f"{root.name}/"]

    def _walk(directory: Path, prefix: str) -> None:
        try:
            entries = sorted(
                (entry for entry in directory.iterdir() if entry.name != ".git"),
                key=lambda entry: (entry.is_file(), entry.name.lower()),  # dirs first
            )
        except OSError as exc:
            lines.append(f"{prefix}└── [unreadable: {exc}]")
            return
        for index, entry in enumerate(entries):
            is_last = index == len(entries) - 1
            connector = "└── " if is_last else "├── "
            is_real_dir = entry.is_dir() and not entry.is_symlink()
            lines.append(f"{prefix}{connector}{entry.name}{'/' if is_real_dir else ''}")
            if is_real_dir:
                _walk(entry, prefix + ("    " if is_last else "│   "))

    _walk(root, "")
    return "\n".join(lines)


def iter_files(directory: Path) -> Iterator[Path]:
    """Yield every regular file under *directory*, skipping ``.git`` and symlinks."""
    try:
        entries = sorted(directory.iterdir(), key=lambda entry: entry.name.lower())
    except OSError:
        return  # unreadable directory — the tree view already flags it
    for entry in entries:
        if entry.name == ".git" or entry.is_symlink():
            continue
        if entry.is_dir():
            yield from iter_files(entry)
        elif entry.is_file():
            yield entry


def is_binary_file(path: Path) -> bool:
    """Heuristically decide whether *path* is binary.

    Known binary extensions are trusted outright; anything else has its
    first 8 KiB sniffed for NUL bytes, which real text files never contain.
    """
    if path.suffix.lower() in BINARY_EXTENSIONS:
        return True
    try:
        with path.open("rb") as handle:
            chunk = handle.read(8192)
    except OSError:
        return True  # unreadable — treat like a binary so it is only listed
    return b"\x00" in chunk


def language_hint(path: Path) -> str:
    """Return the fenced-code language hint for *path* ("" when unknown)."""
    special = SPECIAL_FILENAMES.get(path.name.lower())
    if special is not None:
        return special
    return EXTENSION_LANGUAGES.get(path.suffix.lower(), "")


def fenced_block(content: str, hint: str) -> str:
    """Wrap *content* in a fence long enough to survive backticks in the text."""
    longest_run = max((len(match) for match in re.findall(r"`+", content)), default=0)
    fence = "`" * max(3, longest_run + 1)
    if not content.endswith("\n"):
        content += "\n"
    return f"{fence}{hint}\n{content}{fence}"


def convert_repo_to_markdown(repo_dir: Path, markdown_dir: Path) -> Path:
    """Write ``markdown_dir/<repo>.md`` documenting *repo_dir*; return its path."""
    output_path = markdown_dir / f"{repo_dir.name}.md"
    if output_path.exists():
        print(f"[INFO] {output_path.name} already exists — it will be replaced.")

    parts: list[str] = [f"# {repo_dir.name}", ""]
    parts += ["## Directory structure", "", "```text", build_tree(repo_dir), "```", ""]
    parts += ["## File contents", ""]

    files = sorted(
        iter_files(repo_dir),
        key=lambda p: p.relative_to(repo_dir).as_posix().lower(),
    )

    skipped: list[tuple[str, str]] = []  # (relative path, reason)
    inlined = 0
    for file_path in files:
        relative = file_path.relative_to(repo_dir).as_posix()
        try:
            if is_binary_file(file_path):
                skipped.append((relative, "binary file"))
                continue
            size = file_path.stat().st_size
            if MAX_TEXT_FILE_BYTES is not None and size > MAX_TEXT_FILE_BYTES:
                skipped.append((relative, f"too large ({size:,} bytes)"))
                continue
            # errors="replace" keeps odd encodings from crashing the run.
            content = file_path.read_text(encoding="utf-8", errors="replace")
        except OSError as exc:
            skipped.append((relative, f"unreadable ({exc})"))
            continue

        parts += [f"### {relative}", ""]
        if content:
            parts += [fenced_block(content, language_hint(file_path)), ""]
        else:
            parts += ["*(empty file)*", ""]
        inlined += 1

    if not files:
        parts += ["*(repository contains no files)*", ""]
    elif inlined == 0:
        parts += ["*(no text files to inline)*", ""]

    if skipped:
        parts += ["## Skipped files", ""]
        parts += [f"- `{relative}` — {reason}" for relative, reason in skipped]
        parts.append("")

    output_path.write_text("\n".join(parts), encoding="utf-8")
    print(f"[OK]   Wrote {output_path} "
          f"({inlined} file(s) inlined, {len(skipped)} skipped)")
    return output_path


# --------------------------------------------------------------------------- #
# Phase 4 — archive the run
# --------------------------------------------------------------------------- #


def unique_archive_folder(archive_dir: Path) -> Path:
    """Return a not-yet-existing dated folder inside *archive_dir*.

    Preference order: ``YYYY-MM-DD``, then ``YYYY-MM-DD_HH-MM-SS``, then the
    timestamped name with ``_2``, ``_3``, … appended. Nothing is overwritten.
    """
    now = datetime.now()
    candidate = archive_dir / now.strftime("%Y-%m-%d")
    if not candidate.exists():
        return candidate
    stamped = archive_dir / now.strftime("%Y-%m-%d_%H-%M-%S")
    candidate = stamped
    counter = 2
    while candidate.exists():
        candidate = stamped.with_name(f"{stamped.name}_{counter}")
        counter += 1
    return candidate


def _move(source: Path, destination: Path) -> None:
    """Move *source* to *destination*, logging instead of raising on failure."""
    try:
        shutil.move(str(source), str(destination))
        print(f"[OK]   Moved {source.name} → {destination}")
    except (OSError, shutil.Error) as exc:
        print(f"[FAIL] Could not move {source}: {exc}")


def archive_run(clone_dir: Path, repo_file: Path, archive_dir: Path) -> Optional[Path]:
    """Move *repo_file* and every entry of *clone_dir* into a dated folder.

    Returns the archive folder, or ``None`` when there was nothing to move.
    Individual move failures are logged and do not abort the phase.
    """
    try:
        items = (
            sorted(clone_dir.iterdir(), key=lambda p: p.name.lower())
            if clone_dir.exists()
            else []
        )
    except OSError as exc:
        print(f"[WARN] Could not list {clone_dir}: {exc}")
        items = []

    if not items and not repo_file.is_file():
        print("[INFO] Nothing to archive.")
        return None

    target = unique_archive_folder(archive_dir)
    try:
        target.mkdir(parents=True, exist_ok=True)  # unique name computed above
    except OSError as exc:
        print(f"[FAIL] Could not create archive folder {target}: {exc}")
        return None

    if repo_file.is_file():
        _move(repo_file, target / repo_file.name)
    for item in items:
        _move(item, target / item.name)

    return target


# --------------------------------------------------------------------------- #
# Entry point
# --------------------------------------------------------------------------- #


def parse_args(argv: Optional[Sequence[str]] = None) -> argparse.Namespace:
    """Parse command-line options."""
    parser = argparse.ArgumentParser(
        description="Clone repositories, flatten each into one Markdown file, "
                    "then archive the run.",
    )
    parser.add_argument(
        "--no-shallow",
        action="store_true",
        help="clone full history instead of the default 'git clone --depth 1'",
    )
    return parser.parse_args(argv)


def main(argv: Optional[Sequence[str]] = None) -> int:
    """Run all four phases; always returns 0 so batch callers keep going."""
    args = parse_args(argv)
    shallow = SHALLOW_CLONE_DEFAULT and not args.no_shallow

    ensure_directories()
    print(f"Project root: {PROJECT_ROOT}")

    # Phase 1 — read the repo list ---------------------------------------- #
    announce(1, "Read the repository list")
    urls = read_repo_list(REPO_LIST_FILE)
    print(f"[INFO] {len(urls)} repository URL(s) queued.")

    # Phase 2 — clone each repo ------------------------------------------- #
    announce(2, "Clone repositories")
    clone_stats = {"cloned": 0, "skipped": 0, "failed": 0}
    if not urls:
        print("[INFO] No URLs to clone.")
    elif shutil.which("git") is None:
        print("[FAIL] 'git' was not found on PATH — skipping every clone.")
        clone_stats["failed"] = len(urls)
    else:
        print(f"[INFO] Clone mode: {'shallow (--depth 1)' if shallow else 'full history'}")
        for url in urls:
            clone_stats[clone_repo(url, CLONE_DIR, shallow)] += 1

    # Phase 3 — convert every cloned folder ------------------------------- #
    announce(3, "Convert cloned repositories to Markdown")
    markdown_written = 0
    markdown_failed = 0
    try:
        repo_dirs = sorted(
            (path for path in CLONE_DIR.iterdir() if path.is_dir()),
            key=lambda path: path.name.lower(),
        )
    except OSError as exc:
        print(f"[FAIL] Could not list {CLONE_DIR}: {exc}")
        repo_dirs = []

    if not repo_dirs:
        print("[INFO] No folders in github_clone/ to convert.")
    for repo_dir in repo_dirs:
        try:
            convert_repo_to_markdown(repo_dir, MARKDOWN_DIR)
            markdown_written += 1
        except Exception as exc:  # one bad repo must not stop the run
            print(f"[FAIL] Could not convert {repo_dir.name}: {exc}")
            markdown_failed += 1

    # Phase 4 — archive the run ------------------------------------------- #
    announce(4, "Archive the run")
    archive_target = archive_run(CLONE_DIR, REPO_LIST_FILE, ARCHIVE_DIR)

    # Summary -------------------------------------------------------------- #
    print("\nRun complete.")
    print(f"  Clones   : {clone_stats['cloned']} cloned, "
          f"{clone_stats['skipped']} skipped, {clone_stats['failed']} failed")
    print(f"  Markdown : {markdown_written} file(s) written to {MARKDOWN_DIR}, "
          f"{markdown_failed} failed")
    print(f"  Archive  : {archive_target if archive_target else 'nothing archived'}")
    return 0


if __name__ == "__main__":
    try:
        sys.exit(main())
    except KeyboardInterrupt:
        print("\nInterrupted by user.", file=sys.stderr)
        sys.exit(130)