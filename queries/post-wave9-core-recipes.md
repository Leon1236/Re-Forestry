# Post–Wave 9 polish — core recipes

**Date:** 2026-08-22  
**Status:** done

Added 12 CE-shaped crafting recipes that the Forester's Almanac referenced but were missing:

- `compost_wheat`, `humus_compost`, `humus_fertilizer`
- `gear_bronze`, `gear_copper`, `gear_tin`
- `resource_storage_apatite`, `apatite_from_resource_storage_apatite`
- `resource_storage_tin`, `ingot_tin_from_resource_storage_tin`
- `resource_storage_bronze`, `ingot_bronze_from_resource_storage_bronze`

`python3 tools/validate_book.py` → **OK** (2846 recipes indexed).

Blocks/items were already registered; only datapack recipes were missing.
