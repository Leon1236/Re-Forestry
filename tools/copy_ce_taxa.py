# Copy Forestry CE taxon JSON into data/reforestry/taxon (rewrite forestry: → reforestry:).
# Usage: python3 tools/copy_ce_taxa.py [--butterflies]
import argparse
from pathlib import Path

CE_ROOT = Path("/tmp/ForestryCE")
BEE_TREE = CE_ROOT / "src/generated/resources/data/forestry/taxon"
BUTTERFLY = CE_ROOT / "src/generated/resources_butterflies/data/forestry/taxon"
DEST = Path("src/main/resources/data/reforestry/taxon")


def copy_from(source: Path) -> int:
    DEST.mkdir(parents=True, exist_ok=True)
    count = 0
    for path in sorted(source.glob("*.json")):
        text = path.read_text().replace("forestry:", "reforestry:")
        (DEST / path.name).write_text(text)
        count += 1
    return count


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--butterflies", action="store_true")
    args = parser.parse_args()
    if args.butterflies:
        print("copied", copy_from(BUTTERFLY), "butterfly taxon files")
    else:
        print("copied", copy_from(BEE_TREE), "bee/tree taxon files")


if __name__ == "__main__":
    main()
