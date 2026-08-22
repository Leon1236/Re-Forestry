# Usage: python3 tools/extract_butterfly_species.py
from pathlib import Path
import re

CE_SRC = Path("/tmp/ForestryCE/src/butterflies/java/forestry/lepidopterology/plugin/DefaultButterflySpecies.java")
OUT = Path("src/main/java/com/leon1236/reforestry/lepidopterology/genetics/DefaultButterflySpecies.java")

HEADER = """package com.leon1236.reforestry.lepidopterology.genetics;

import net.minecraft.network.chat.TextColor;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.lepidopterology.ForestryButterflySpecies;
import com.leon1236.reforestry.api.plugin.ILepidopterologyRegistration;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;

import static com.leon1236.reforestry.api.genetics.ForestryTaxa.*;

public final class DefaultButterflySpecies {
	private DefaultButterflySpecies() {
	}

	public static void register(ILepidopterologyRegistration butterflies) {
"""

FOOTER = """	}
}
"""

MUTATION = """			.addMutations(mutations -> mutations.add(ForestryButterflySpecies.LATTICED_HEATH, ForestryButterflySpecies.BRIMSTONE, 7f));
"""


def strip_java_comments(text: str) -> str:
	text = re.sub(r"/\*.*?\*/", "", text, flags=re.S)
	lines = []
	for line in text.splitlines():
		stripped = line.lstrip()
		if stripped.startswith("//"):
			continue
		if "//" in line:
			line = line[: line.index("//")].rstrip()
			if not line.strip():
				continue
		lines.append(line)
	return "\n".join(lines)


def extract_body(src: str) -> str:
	start = src.index("public static void register")
	brace = src.index("{", start)
	depth = 0
	end = None
	for i, ch in enumerate(src[brace:], brace):
		if ch == "{":
			depth += 1
		elif ch == "}":
			depth -= 1
			if depth == 0:
				end = i
				break
	if end is None:
		raise SystemExit("could not find end of register()")
	body = src[brace + 1 : end]
	body = strip_java_comments(body)
	body = body.replace(
		"ForestryCocoons.SILK",
		"AlleleManager.INSTANCE.registryAllele(ButterflyChromosomes.SILK_COCOON, true)",
	)
	body = re.sub(r"\n{3,}", "\n\n", body)
	if '.setAuthority("Nedelosk");' not in body:
		raise SystemExit("bombyx mori authority line missing; cannot attach mutation")
	body = body.replace(
		'.setAuthority("Nedelosk");',
		'.setAuthority("Nedelosk")\n' + MUTATION.rstrip(),
		1,
	)
	return body.rstrip() + "\n"


def main() -> None:
	src = CE_SRC.read_text()
	body = extract_body(src)
	OUT.parent.mkdir(parents=True, exist_ok=True)
	OUT.write_text(HEADER + body + FOOTER)
	print("wrote", OUT, "species builders from", CE_SRC)


if __name__ == "__main__":
	main()
