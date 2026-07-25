# ARB-11.9d — Charcoal module

Ported CE charcoal pile into arboriculture (CE planned merge).

## Behaviour

- Place `reforestry:log_pile`, enclose with registered walls (dirt=2, clay/netherrack=3, end stone/bricks=6, gravel=1).
- Light with fire → pile activates, ages, becomes `ash_block` with charcoal yield from walls + base (config `charcoal.amount_base`, default 8).
- Breaking ash drops vanilla charcoal (count = amount) + some `reforestry:ash`.
- `decorative_log_pile` is craftable/convertible; does not cook.
- Charcoal block fuel burn time 16000; log piles 1200.

## Config

- `charcoal.amount_base` (default 8)
- `charcoal.wall_check_range` (default 16)
