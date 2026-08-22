# Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X

## Directory structure

```text
Tutorials-By-Kaupenjoe-Fabric-Tutorial-26.X/
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   └── main/
│       ├── generated/
│       │   ├── .cache/
│       │   │   ├── 05bae46595fc0bd4302e11de00fed8267f787d53
│       │   │   ├── 130f8a64b4c2c55053b3e123f552da6b92e44921
│       │   │   ├── 378f9509398ea584ca54616156177f9ec588f984
│       │   │   ├── 47bc1c7dfdd699053473afebbde6668d4605aa61
│       │   │   ├── 53d8ea8352608c95fff1fc7a65c403162cc24c7e
│       │   │   ├── 80beaa027efbcf9e17d04d48300d7784fb0cb57f
│       │   │   ├── 96bb53df1af2d21ca7a52efa65985f699489396c
│       │   │   ├── b0e779921aa000a5ad49c5afacbcbc5d79eb02b0
│       │   │   ├── bf8b55c7a3e1253642a8b1063c0b39ff5689945b
│       │   │   ├── ce39c20969e010cd0348db228ef39b23c54b9ea7
│       │   │   ├── e4e32e775410155fd5b39910e36714f200d58a95
│       │   │   └── f3f56cafc2cbe41709406bb540eb1fca38a90f18
│       │   ├── assets/
│       │   │   └── tutorialmod/
│       │   │       ├── blockstates/
│       │   │       │   ├── balsa_leaves.json
│       │   │       │   ├── balsa_log.json
│       │   │       │   ├── balsa_planks.json
│       │   │       │   ├── balsa_sapling.json
│       │   │       │   ├── balsa_wood.json
│       │   │       │   ├── crystallizer.json
│       │   │       │   ├── fluorite_block.json
│       │   │       │   ├── fluorite_button.json
│       │   │       │   ├── fluorite_deepslate_ore.json
│       │   │       │   ├── fluorite_door.json
│       │   │       │   ├── fluorite_end_ore.json
│       │   │       │   ├── fluorite_fence.json
│       │   │       │   ├── fluorite_fence_gate.json
│       │   │       │   ├── fluorite_lamp.json
│       │   │       │   ├── fluorite_nether_ore.json
│       │   │       │   ├── fluorite_ore.json
│       │   │       │   ├── fluorite_pressure_plate.json
│       │   │       │   ├── fluorite_slab.json
│       │   │       │   ├── fluorite_stairs.json
│       │   │       │   ├── fluorite_trapdoor.json
│       │   │       │   ├── fluorite_wall.json
│       │   │       │   ├── honey_berry_bush.json
│       │   │       │   ├── magic_block.json
│       │   │       │   ├── pedestal.json
│       │   │       │   ├── potted_balsa_sapling.json
│       │   │       │   ├── raw_fluorite_block.json
│       │   │       │   ├── rice_crop.json
│       │   │       │   ├── strawberry_crop.json
│       │   │       │   ├── stripped_balsa_log.json
│       │   │       │   └── stripped_balsa_wood.json
│       │   │       ├── equipment/
│       │   │       │   └── fluorite.json
│       │   │       ├── items/
│       │   │       │   ├── balsa_leaves.json
│       │   │       │   ├── balsa_log.json
│       │   │       │   ├── balsa_planks.json
│       │   │       │   ├── balsa_sapling.json
│       │   │       │   ├── balsa_wood.json
│       │   │       │   ├── bar_brawl_music_disc.json
│       │   │       │   ├── chisel.json
│       │   │       │   ├── combustible_spores.json
│       │   │       │   ├── crystallizer.json
│       │   │       │   ├── fluorite.json
│       │   │       │   ├── fluorite_axe.json
│       │   │       │   ├── fluorite_block.json
│       │   │       │   ├── fluorite_boots.json
│       │   │       │   ├── fluorite_button.json
│       │   │       │   ├── fluorite_chestplate.json
│       │   │       │   ├── fluorite_deepslate_ore.json
│       │   │       │   ├── fluorite_door.json
│       │   │       │   ├── fluorite_end_ore.json
│       │   │       │   ├── fluorite_fence.json
│       │   │       │   ├── fluorite_fence_gate.json
│       │   │       │   ├── fluorite_helmet.json
│       │   │       │   ├── fluorite_hoe.json
│       │   │       │   ├── fluorite_horse_armor.json
│       │   │       │   ├── fluorite_lamp.json
│       │   │       │   ├── fluorite_leggings.json
│       │   │       │   ├── fluorite_nether_ore.json
│       │   │       │   ├── fluorite_ore.json
│       │   │       │   ├── fluorite_pickaxe.json
│       │   │       │   ├── fluorite_pressure_plate.json
│       │   │       │   ├── fluorite_shovel.json
│       │   │       │   ├── fluorite_slab.json
│       │   │       │   ├── fluorite_spear.json
│       │   │       │   ├── fluorite_stairs.json
│       │   │       │   ├── fluorite_sword.json
│       │   │       │   ├── fluorite_trapdoor.json
│       │   │       │   ├── fluorite_wall.json
│       │   │       │   ├── honey_berries.json
│       │   │       │   ├── kaupen_bow.json
│       │   │       │   ├── magic_block.json
│       │   │       │   ├── pedestal.json
│       │   │       │   ├── raw_fluorite.json
│       │   │       │   ├── raw_fluorite_block.json
│       │   │       │   ├── rice_shoot.json
│       │   │       │   ├── sculkbeam_staff.json
│       │   │       │   ├── spectre_staff.json
│       │   │       │   ├── strawberry.json
│       │   │       │   ├── strawberry_seeds.json
│       │   │       │   ├── stripped_balsa_log.json
│       │   │       │   └── stripped_balsa_wood.json
│       │   │       ├── models/
│       │   │       │   ├── block/
│       │   │       │   │   ├── balsa_leaves.json
│       │   │       │   │   ├── balsa_log.json
│       │   │       │   │   ├── balsa_planks.json
│       │   │       │   │   ├── balsa_sapling.json
│       │   │       │   │   ├── balsa_wood.json
│       │   │       │   │   ├── crystallizer.json
│       │   │       │   │   ├── crystallizer_on.json
│       │   │       │   │   ├── fluorite_block.json
│       │   │       │   │   ├── fluorite_button.json
│       │   │       │   │   ├── fluorite_button_inventory.json
│       │   │       │   │   ├── fluorite_button_pressed.json
│       │   │       │   │   ├── fluorite_deepslate_ore.json
│       │   │       │   │   ├── fluorite_door_bottom_left.json
│       │   │       │   │   ├── fluorite_door_bottom_left_open.json
│       │   │       │   │   ├── fluorite_door_bottom_right.json
│       │   │       │   │   ├── fluorite_door_bottom_right_open.json
│       │   │       │   │   ├── fluorite_door_top_left.json
│       │   │       │   │   ├── fluorite_door_top_left_open.json
│       │   │       │   │   ├── fluorite_door_top_right.json
│       │   │       │   │   ├── fluorite_door_top_right_open.json
│       │   │       │   │   ├── fluorite_end_ore.json
│       │   │       │   │   ├── fluorite_fence_gate.json
│       │   │       │   │   ├── fluorite_fence_gate_open.json
│       │   │       │   │   ├── fluorite_fence_gate_wall.json
│       │   │       │   │   ├── fluorite_fence_gate_wall_open.json
│       │   │       │   │   ├── fluorite_fence_inventory.json
│       │   │       │   │   ├── fluorite_fence_post.json
│       │   │       │   │   ├── fluorite_fence_side.json
│       │   │       │   │   ├── fluorite_lamp.json
│       │   │       │   │   ├── fluorite_lamp_on.json
│       │   │       │   │   ├── fluorite_nether_ore.json
│       │   │       │   │   ├── fluorite_ore.json
│       │   │       │   │   ├── fluorite_pressure_plate.json
│       │   │       │   │   ├── fluorite_pressure_plate_down.json
│       │   │       │   │   ├── fluorite_slab.json
│       │   │       │   │   ├── fluorite_slab_top.json
│       │   │       │   │   ├── fluorite_stairs.json
│       │   │       │   │   ├── fluorite_stairs_inner.json
│       │   │       │   │   ├── fluorite_stairs_outer.json
│       │   │       │   │   ├── fluorite_trapdoor_bottom.json
│       │   │       │   │   ├── fluorite_trapdoor_open.json
│       │   │       │   │   ├── fluorite_trapdoor_top.json
│       │   │       │   │   ├── fluorite_wall_inventory.json
│       │   │       │   │   ├── fluorite_wall_post.json
│       │   │       │   │   ├── fluorite_wall_side.json
│       │   │       │   │   ├── fluorite_wall_side_tall.json
│       │   │       │   │   ├── honey_berry_bush_stage0.json
│       │   │       │   │   ├── honey_berry_bush_stage1.json
│       │   │       │   │   ├── honey_berry_bush_stage2.json
│       │   │       │   │   ├── honey_berry_bush_stage3.json
│       │   │       │   │   ├── magic_block.json
│       │   │       │   │   ├── potted_balsa_sapling.json
│       │   │       │   │   ├── raw_fluorite_block.json
│       │   │       │   │   ├── rice_crop_stage0.json
│       │   │       │   │   ├── rice_crop_stage1.json
│       │   │       │   │   ├── rice_crop_stage2.json
│       │   │       │   │   ├── rice_crop_stage3.json
│       │   │       │   │   ├── rice_crop_stage4.json
│       │   │       │   │   ├── rice_crop_stage5.json
│       │   │       │   │   ├── rice_crop_stage6.json
│       │   │       │   │   ├── rice_crop_stage7.json
│       │   │       │   │   ├── strawberry_crop_stage0.json
│       │   │       │   │   ├── strawberry_crop_stage1.json
│       │   │       │   │   ├── strawberry_crop_stage2.json
│       │   │       │   │   ├── strawberry_crop_stage3.json
│       │   │       │   │   ├── strawberry_crop_stage4.json
│       │   │       │   │   ├── strawberry_crop_stage5.json
│       │   │       │   │   ├── stripped_balsa_log.json
│       │   │       │   │   └── stripped_balsa_wood.json
│       │   │       │   └── item/
│       │   │       │       ├── balsa_sapling.json
│       │   │       │       ├── bar_brawl_music_disc.json
│       │   │       │       ├── chisel.json
│       │   │       │       ├── chisel_used.json
│       │   │       │       ├── combustible_spores.json
│       │   │       │       ├── fluorite.json
│       │   │       │       ├── fluorite_axe.json
│       │   │       │       ├── fluorite_boots.json
│       │   │       │       ├── fluorite_boots_amethyst_trim.json
│       │   │       │       ├── fluorite_boots_copper_trim.json
│       │   │       │       ├── fluorite_boots_diamond_trim.json
│       │   │       │       ├── fluorite_boots_emerald_trim.json
│       │   │       │       ├── fluorite_boots_gold_trim.json
│       │   │       │       ├── fluorite_boots_iron_trim.json
│       │   │       │       ├── fluorite_boots_lapis_trim.json
│       │   │       │       ├── fluorite_boots_netherite_trim.json
│       │   │       │       ├── fluorite_boots_quartz_trim.json
│       │   │       │       ├── fluorite_boots_redstone_trim.json
│       │   │       │       ├── fluorite_boots_resin_trim.json
│       │   │       │       ├── fluorite_chestplate.json
│       │   │       │       ├── fluorite_chestplate_amethyst_trim.json
│       │   │       │       ├── fluorite_chestplate_copper_trim.json
│       │   │       │       ├── fluorite_chestplate_diamond_trim.json
│       │   │       │       ├── fluorite_chestplate_emerald_trim.json
│       │   │       │       ├── fluorite_chestplate_gold_trim.json
│       │   │       │       ├── fluorite_chestplate_iron_trim.json
│       │   │       │       ├── fluorite_chestplate_lapis_trim.json
│       │   │       │       ├── fluorite_chestplate_netherite_trim.json
│       │   │       │       ├── fluorite_chestplate_quartz_trim.json
│       │   │       │       ├── fluorite_chestplate_redstone_trim.json
│       │   │       │       ├── fluorite_chestplate_resin_trim.json
│       │   │       │       ├── fluorite_door.json
│       │   │       │       ├── fluorite_helmet.json
│       │   │       │       ├── fluorite_helmet_amethyst_trim.json
│       │   │       │       ├── fluorite_helmet_copper_trim.json
│       │   │       │       ├── fluorite_helmet_diamond_trim.json
│       │   │       │       ├── fluorite_helmet_emerald_trim.json
│       │   │       │       ├── fluorite_helmet_gold_trim.json
│       │   │       │       ├── fluorite_helmet_iron_trim.json
│       │   │       │       ├── fluorite_helmet_lapis_trim.json
│       │   │       │       ├── fluorite_helmet_netherite_trim.json
│       │   │       │       ├── fluorite_helmet_quartz_trim.json
│       │   │       │       ├── fluorite_helmet_redstone_trim.json
│       │   │       │       ├── fluorite_helmet_resin_trim.json
│       │   │       │       ├── fluorite_hoe.json
│       │   │       │       ├── fluorite_horse_armor.json
│       │   │       │       ├── fluorite_leggings.json
│       │   │       │       ├── fluorite_leggings_amethyst_trim.json
│       │   │       │       ├── fluorite_leggings_copper_trim.json
│       │   │       │       ├── fluorite_leggings_diamond_trim.json
│       │   │       │       ├── fluorite_leggings_emerald_trim.json
│       │   │       │       ├── fluorite_leggings_gold_trim.json
│       │   │       │       ├── fluorite_leggings_iron_trim.json
│       │   │       │       ├── fluorite_leggings_lapis_trim.json
│       │   │       │       ├── fluorite_leggings_netherite_trim.json
│       │   │       │       ├── fluorite_leggings_quartz_trim.json
│       │   │       │       ├── fluorite_leggings_redstone_trim.json
│       │   │       │       ├── fluorite_leggings_resin_trim.json
│       │   │       │       ├── fluorite_pickaxe.json
│       │   │       │       ├── fluorite_shovel.json
│       │   │       │       ├── fluorite_spear.json
│       │   │       │       ├── fluorite_spear_in_hand.json
│       │   │       │       ├── fluorite_sword.json
│       │   │       │       ├── honey_berries.json
│       │   │       │       ├── kaupen_bow.json
│       │   │       │       ├── kaupen_bow_pulling_0.json
│       │   │       │       ├── kaupen_bow_pulling_1.json
│       │   │       │       ├── kaupen_bow_pulling_2.json
│       │   │       │       ├── raw_fluorite.json
│       │   │       │       ├── rice_shoot.json
│       │   │       │       ├── spectre_staff.json
│       │   │       │       ├── strawberry.json
│       │   │       │       └── strawberry_seeds.json
│       │   │       └── sounds.json
│       │   └── data/
│       │       ├── minecraft/
│       │       │   └── tags/
│       │       │       ├── block/
│       │       │       │   ├── mineable/
│       │       │       │   │   └── pickaxe.json
│       │       │       │   ├── buttons.json
│       │       │       │   ├── crops.json
│       │       │       │   ├── doors.json
│       │       │       │   ├── fence_gates.json
│       │       │       │   ├── fences.json
│       │       │       │   ├── flower_pots.json
│       │       │       │   ├── leaves.json
│       │       │       │   ├── logs.json
│       │       │       │   ├── needs_diamond_tool.json
│       │       │       │   ├── needs_iron_tool.json
│       │       │       │   ├── planks.json
│       │       │       │   ├── pressure_plates.json
│       │       │       │   ├── slabs.json
│       │       │       │   ├── stairs.json
│       │       │       │   ├── trapdoors.json
│       │       │       │   └── walls.json
│       │       │       ├── item/
│       │       │       │   ├── enchantable/
│       │       │       │   │   └── bow.json
│       │       │       │   ├── axes.json
│       │       │       │   ├── chest_armor.json
│       │       │       │   ├── creeper_drop_music_discs.json
│       │       │       │   ├── foot_armor.json
│       │       │       │   ├── head_armor.json
│       │       │       │   ├── hoes.json
│       │       │       │   ├── leaves.json
│       │       │       │   ├── leg_armor.json
│       │       │       │   ├── logs_that_burn.json
│       │       │       │   ├── pickaxes.json
│       │       │       │   ├── planks.json
│       │       │       │   ├── saplings.json
│       │       │       │   ├── shovels.json
│       │       │       │   ├── spears.json
│       │       │       │   └── swords.json
│       │       │       ├── painting_variant/
│       │       │       │   └── placeable.json
│       │       │       ├── point_of_interest_type/
│       │       │       │   └── acquirable_job_site.json
│       │       │       └── villager_trade/
│       │       │           ├── farmer/
│       │       │           │   ├── level_1.json
│       │       │           │   └── level_2.json
│       │       │           ├── librarian/
│       │       │           │   └── level_1.json
│       │       │           └── mason/
│       │       │               └── level_1.json
│       │       └── tutorialmod/
│       │           ├── advancement/
│       │           │   ├── recipes/
│       │           │   │   ├── building_blocks/
│       │           │   │   │   ├── balsa_planks.json
│       │           │   │   │   ├── balsa_wood.json
│       │           │   │   │   ├── fluorite_block.json
│       │           │   │   │   ├── fluorite_slab.json
│       │           │   │   │   ├── fluorite_stairs.json
│       │           │   │   │   ├── fluorite_wall.json
│       │           │   │   │   └── stripped_balsa_wood.json
│       │           │   │   ├── combat/
│       │           │   │   │   ├── fluorite_boots.json
│       │           │   │   │   ├── fluorite_chestplate.json
│       │           │   │   │   ├── fluorite_helmet.json
│       │           │   │   │   ├── fluorite_leggings.json
│       │           │   │   │   ├── fluorite_spear.json
│       │           │   │   │   └── fluorite_sword.json
│       │           │   │   ├── decorations/
│       │           │   │   │   └── fluorite_fence.json
│       │           │   │   ├── misc/
│       │           │   │   │   ├── bar_brawl_music_disc_from_crystallizing.json
│       │           │   │   │   ├── end_rod_from_crystallizing.json
│       │           │   │   │   ├── fluorite.json
│       │           │   │   │   ├── fluorite_from_blasting_fluorite_deepslate_ore.json
│       │           │   │   │   ├── fluorite_from_blasting_fluorite_end_ore.json
│       │           │   │   │   ├── fluorite_from_blasting_fluorite_nether_ore.json
│       │           │   │   │   ├── fluorite_from_blasting_fluorite_ore.json
│       │           │   │   │   ├── fluorite_from_blasting_raw_fluorite.json
│       │           │   │   │   ├── fluorite_from_crystallizing.json
│       │           │   │   │   ├── fluorite_from_smelting_fluorite_deepslate_ore.json
│       │           │   │   │   ├── fluorite_from_smelting_fluorite_end_ore.json
│       │           │   │   │   ├── fluorite_from_smelting_fluorite_nether_ore.json
│       │           │   │   │   ├── fluorite_from_smelting_fluorite_ore.json
│       │           │   │   │   ├── fluorite_from_smelting_raw_fluorite.json
│       │           │   │   │   ├── nether_star_from_crystallizing.json
│       │           │   │   │   ├── raw_fluorite.json
│       │           │   │   │   ├── raw_fluorite_block.json
│       │           │   │   │   ├── raw_fluorite_from_fluorite_and_stick.json
│       │           │   │   │   └── rice_shoot_from_crystallizing.json
│       │           │   │   ├── redstone/
│       │           │   │   │   ├── fluorite_button.json
│       │           │   │   │   ├── fluorite_door.json
│       │           │   │   │   ├── fluorite_fence_gate.json
│       │           │   │   │   ├── fluorite_pressure_plate.json
│       │           │   │   │   └── fluorite_trapdoor.json
│       │           │   │   └── tools/
│       │           │   │       ├── fluorite_axe.json
│       │           │   │       ├── fluorite_hoe.json
│       │           │   │       ├── fluorite_pickaxe.json
│       │           │   │       └── fluorite_shovel.json
│       │           │   └── tutorialmod/
│       │           │       ├── chisel_stone.json
│       │           │       ├── plant_custom.json
│       │           │       └── root.json
│       │           ├── damage_type/
│       │           │   └── stinky.json
│       │           ├── jukebox_song/
│       │           │   └── bar_brawl.json
│       │           ├── loot_table/
│       │           │   └── blocks/
│       │           │       ├── balsa_leaves.json
│       │           │       ├── balsa_log.json
│       │           │       ├── balsa_planks.json
│       │           │       ├── balsa_sapling.json
│       │           │       ├── balsa_wood.json
│       │           │       ├── crystallizer.json
│       │           │       ├── fluorite_block.json
│       │           │       ├── fluorite_button.json
│       │           │       ├── fluorite_deepslate_ore.json
│       │           │       ├── fluorite_door.json
│       │           │       ├── fluorite_end_ore.json
│       │           │       ├── fluorite_fence.json
│       │           │       ├── fluorite_fence_gate.json
│       │           │       ├── fluorite_lamp.json
│       │           │       ├── fluorite_nether_ore.json
│       │           │       ├── fluorite_ore.json
│       │           │       ├── fluorite_pressure_plate.json
│       │           │       ├── fluorite_slab.json
│       │           │       ├── fluorite_stairs.json
│       │           │       ├── fluorite_trapdoor.json
│       │           │       ├── fluorite_wall.json
│       │           │       ├── honey_berry_bush.json
│       │           │       ├── magic_block.json
│       │           │       ├── pedestal.json
│       │           │       ├── potted_balsa_sapling.json
│       │           │       ├── raw_fluorite_block.json
│       │           │       ├── rice_crop.json
│       │           │       ├── strawberry_crop.json
│       │           │       ├── stripped_balsa_log.json
│       │           │       └── stripped_balsa_wood.json
│       │           ├── painting_variant/
│       │           │   ├── saw_them.json
│       │           │   ├── shrimp.json
│       │           │   ├── wanderer.json
│       │           │   └── world.json
│       │           ├── recipe/
│       │           │   ├── balsa_planks.json
│       │           │   ├── balsa_wood.json
│       │           │   ├── bar_brawl_music_disc_from_crystallizing.json
│       │           │   ├── end_rod_from_crystallizing.json
│       │           │   ├── fluorite.json
│       │           │   ├── fluorite_axe.json
│       │           │   ├── fluorite_block.json
│       │           │   ├── fluorite_boots.json
│       │           │   ├── fluorite_button.json
│       │           │   ├── fluorite_chestplate.json
│       │           │   ├── fluorite_door.json
│       │           │   ├── fluorite_fence.json
│       │           │   ├── fluorite_fence_gate.json
│       │           │   ├── fluorite_from_blasting_fluorite_deepslate_ore.json
│       │           │   ├── fluorite_from_blasting_fluorite_end_ore.json
│       │           │   ├── fluorite_from_blasting_fluorite_nether_ore.json
│       │           │   ├── fluorite_from_blasting_fluorite_ore.json
│       │           │   ├── fluorite_from_blasting_raw_fluorite.json
│       │           │   ├── fluorite_from_crystallizing.json
│       │           │   ├── fluorite_from_smelting_fluorite_deepslate_ore.json
│       │           │   ├── fluorite_from_smelting_fluorite_end_ore.json
│       │           │   ├── fluorite_from_smelting_fluorite_nether_ore.json
│       │           │   ├── fluorite_from_smelting_fluorite_ore.json
│       │           │   ├── fluorite_from_smelting_raw_fluorite.json
│       │           │   ├── fluorite_helmet.json
│       │           │   ├── fluorite_hoe.json
│       │           │   ├── fluorite_leggings.json
│       │           │   ├── fluorite_pickaxe.json
│       │           │   ├── fluorite_pressure_plate.json
│       │           │   ├── fluorite_shovel.json
│       │           │   ├── fluorite_slab.json
│       │           │   ├── fluorite_spear.json
│       │           │   ├── fluorite_stairs.json
│       │           │   ├── fluorite_sword.json
│       │           │   ├── fluorite_trapdoor.json
│       │           │   ├── fluorite_wall.json
│       │           │   ├── nether_star_from_crystallizing.json
│       │           │   ├── raw_fluorite.json
│       │           │   ├── raw_fluorite_block.json
│       │           │   ├── raw_fluorite_from_fluorite_and_stick.json
│       │           │   ├── rice_shoot_from_crystallizing.json
│       │           │   └── stripped_balsa_wood.json
│       │           ├── tags/
│       │           │   ├── block/
│       │           │   │   ├── balsa_logs.json
│       │           │   │   ├── incorrect_for_fluorite_tool.json
│       │           │   │   └── needs_fluorite_tool.json
│       │           │   ├── item/
│       │           │   │   ├── balsa_logs.json
│       │           │   │   └── transformable_items.json
│       │           │   └── villager_trade/
│       │           │       └── kaupenger/
│       │           │           ├── level_1.json
│       │           │           └── level_2.json
│       │           ├── trade_set/
│       │           │   └── kaupenger/
│       │           │       ├── level_1.json
│       │           │       └── level_2.json
│       │           ├── villager_trade/
│       │           │   ├── farmer/
│       │           │   │   ├── 1/
│       │           │   │   │   ├── diamond_strawberry_seeds.json
│       │           │   │   │   └── emerald_strawberry.json
│       │           │   │   └── 2/
│       │           │   │       └── emerald_honey_berries.json
│       │           │   ├── kaupenger/
│       │           │   │   ├── 1/
│       │           │   │   │   ├── emerald_fluorite.json
│       │           │   │   │   └── emerald_raw_fluorite.json
│       │           │   │   └── 2/
│       │           │   │       ├── emerald_pedestal.json
│       │           │   │       └── fluorite_spectre_staff.json
│       │           │   ├── librarian/
│       │           │   │   └── 1/
│       │           │   │       └── fluorite_enchanted_book.json
│       │           │   └── mason/
│       │           │       └── 1/
│       │           │           └── fluorite_chisel.json
│       │           └── worldgen/
│       │               ├── configured_feature/
│       │               │   ├── balsa.json
│       │               │   ├── end_fluorite_ore.json
│       │               │   ├── honey_berry_bush.json
│       │               │   ├── nether_fluorite_ore.json
│       │               │   └── overworld_fluorite_ore.json
│       │               └── placed_feature/
│       │                   ├── balsa_placed.json
│       │                   ├── end_fluorite_ore_placed.json
│       │                   ├── honey_berry_bush_placed.json
│       │                   ├── nether_fluorite_ore_placed.json
│       │                   └── overworld_fluorite_ore_placed.json
│       ├── java/
│       │   └── net/
│       │       └── kaupenjoe/
│       │           └── tutorialmod/
│       │               ├── block/
│       │               │   ├── custom/
│       │               │   │   ├── CrystallizerBlock.java
│       │               │   │   ├── FluoriteLampBlock.java
│       │               │   │   ├── HoneyBerryBushBlock.java
│       │               │   │   ├── MagicBlock.java
│       │               │   │   ├── PedestalBlock.java
│       │               │   │   ├── RiceCropBlock.java
│       │               │   │   └── StrawberryCropBlock.java
│       │               │   ├── entity/
│       │               │   │   ├── custom/
│       │               │   │   │   ├── CrystallizerBlockEntity.java
│       │               │   │   │   └── PedestalBlockEntity.java
│       │               │   │   ├── renderer/
│       │               │   │   │   ├── PedestalBlockEntityRenderer.java
│       │               │   │   │   └── PedestalBlockEntityRenderState.java
│       │               │   │   ├── ImplementedInventory.java
│       │               │   │   └── ModBlockEntities.java
│       │               │   └── ModBlocks.java
│       │               ├── compat/
│       │               │   ├── custom/
│       │               │   │   ├── CrystallizerCategory.java
│       │               │   │   └── CrystallizerDisplay.java
│       │               │   ├── TutorialModREIClient.java
│       │               │   └── TutorialModREICommon.java
│       │               ├── creativemodetab/
│       │               │   └── ModCreativeModeTabs.java
│       │               ├── data/
│       │               │   └── ModDataComponents.java
│       │               ├── datagen/
│       │               │   ├── recipe/
│       │               │   │   └── CrystallizerRecipeBuilder.java
│       │               │   ├── villager/
│       │               │   │   ├── ModPOITags.java
│       │               │   │   ├── ModTradeSets.java
│       │               │   │   ├── ModVillagerTrades.java
│       │               │   │   └── ModVillagerTradeTags.java
│       │               │   ├── ModAdvancementsProvider.java
│       │               │   ├── ModBlockLootTableProvider.java
│       │               │   ├── ModBlockTagsProvider.java
│       │               │   ├── ModDamageTypes.java
│       │               │   ├── ModEquipmentAssetProvider.java
│       │               │   ├── ModItemTagsProvider.java
│       │               │   ├── ModJukeboxSongs.java
│       │               │   ├── ModModelProvider.java
│       │               │   ├── ModPaintings.java
│       │               │   ├── ModPaintingTagsProvider.java
│       │               │   ├── ModRecipeProvider.java
│       │               │   ├── ModRegistryDataProvider.java
│       │               │   └── ModSoundsProvider.java
│       │               ├── effect/
│       │               │   ├── ModEffects.java
│       │               │   └── StinkyEffect.java
│       │               ├── food/
│       │               │   └── ModFoods.java
│       │               ├── item/
│       │               │   ├── custom/
│       │               │   │   └── ChiselItem.java
│       │               │   ├── ModArmorMaterials.java
│       │               │   ├── ModItems.java
│       │               │   └── ModToolMaterials.java
│       │               ├── keymapping/
│       │               │   └── ModKeyMappings.java
│       │               ├── loot/
│       │               │   └── ModLootTableModifiers.java
│       │               ├── menu/
│       │               │   ├── custom/
│       │               │   │   ├── CrystallizerMenu.java
│       │               │   │   ├── CrystallizerScreen.java
│       │               │   │   ├── PedestalMenu.java
│       │               │   │   └── PedestalScreen.java
│       │               │   └── ModMenuTypes.java
│       │               ├── mixin/
│       │               │   ├── AbstractClientPlayerMixin.java
│       │               │   └── ExampleMixin.java
│       │               ├── networking/
│       │               │   ├── packet/
│       │               │   │   └── TestPayloadC2S.java
│       │               │   ├── ClientboundPackets.java
│       │               │   ├── ModPackets.java
│       │               │   └── ServerboundPackets.java
│       │               ├── potion/
│       │               │   └── ModPotions.java
│       │               ├── recipe/
│       │               │   ├── custom/
│       │               │   │   ├── CrystallizerRecipe.java
│       │               │   │   └── CrystallizerRecipeInput.java
│       │               │   └── ModRecipes.java
│       │               ├── registries/
│       │               │   ├── ModCompostables.java
│       │               │   ├── ModFlammableBlocks.java
│       │               │   ├── ModFuels.java
│       │               │   ├── ModPotionRecipes.java
│       │               │   └── ModStrippableBlocks.java
│       │               ├── sound/
│       │               │   └── ModSounds.java
│       │               ├── stat/
│       │               │   └── ModStats.java
│       │               ├── tags/
│       │               │   └── ModTags.java
│       │               ├── villager/
│       │               │   └── ModVillagers.java
│       │               ├── worldgen/
│       │               │   ├── gen/
│       │               │   │   └── ModWorldGeneration.java
│       │               │   ├── tree/
│       │               │   │   └── ModTreeGrowers.java
│       │               │   ├── ModConfiguredFeatures.java
│       │               │   └── ModPlacedFeatures.java
│       │               ├── TutorialMod.java
│       │               ├── TutorialModClient.java
│       │               └── TutorialModDataGenerator.java
│       └── resources/
│           ├── assets/
│           │   └── tutorialmod/
│           │       ├── lang/
│           │       │   └── en_us.json
│           │       ├── models/
│           │       │   ├── block/
│           │       │   │   └── pedestal.json
│           │       │   └── item/
│           │       │       └── sculkbeam_staff.json
│           │       ├── sounds/
│           │       │   ├── bar_brawl.ogg
│           │       │   └── chisel_use.ogg
│           │       ├── textures/
│           │       │   ├── block/
│           │       │   │   ├── balsa_leaves.png
│           │       │   │   ├── balsa_log.png
│           │       │   │   ├── balsa_log_top.png
│           │       │   │   ├── balsa_planks.png
│           │       │   │   ├── balsa_sapling.png
│           │       │   │   ├── crystallizer_bottom.png
│           │       │   │   ├── crystallizer_front.png
│           │       │   │   ├── crystallizer_front_on.png
│           │       │   │   ├── crystallizer_side.png
│           │       │   │   ├── crystallizer_top.png
│           │       │   │   ├── fluorite_block.png
│           │       │   │   ├── fluorite_deepslate_ore.png
│           │       │   │   ├── fluorite_door_bottom.png
│           │       │   │   ├── fluorite_door_top.png
│           │       │   │   ├── fluorite_end_ore.png
│           │       │   │   ├── fluorite_lamp.png
│           │       │   │   ├── fluorite_lamp_on.png
│           │       │   │   ├── fluorite_nether_ore.png
│           │       │   │   ├── fluorite_ore.png
│           │       │   │   ├── fluorite_trapdoor.png
│           │       │   │   ├── honey_berry_bush_stage0.png
│           │       │   │   ├── honey_berry_bush_stage1.png
│           │       │   │   ├── honey_berry_bush_stage2.png
│           │       │   │   ├── honey_berry_bush_stage3.png
│           │       │   │   ├── magic_block.png
│           │       │   │   ├── pedestal.png
│           │       │   │   ├── raw_fluorite_block.png
│           │       │   │   ├── rice_crop_stage0.png
│           │       │   │   ├── rice_crop_stage1.png
│           │       │   │   ├── rice_crop_stage2.png
│           │       │   │   ├── rice_crop_stage3.png
│           │       │   │   ├── rice_crop_stage4.png
│           │       │   │   ├── rice_crop_stage5.png
│           │       │   │   ├── rice_crop_stage6.png
│           │       │   │   ├── rice_crop_stage7.png
│           │       │   │   ├── strawberry_crop_stage0.png
│           │       │   │   ├── strawberry_crop_stage1.png
│           │       │   │   ├── strawberry_crop_stage2.png
│           │       │   │   ├── strawberry_crop_stage3.png
│           │       │   │   ├── strawberry_crop_stage4.png
│           │       │   │   ├── strawberry_crop_stage5.png
│           │       │   │   ├── stripped_balsa_log.png
│           │       │   │   └── stripped_balsa_log_top.png
│           │       │   ├── entity/
│           │       │   │   ├── equipment/
│           │       │   │   │   ├── horse_body/
│           │       │   │   │   │   └── fluorite.png
│           │       │   │   │   ├── humanoid/
│           │       │   │   │   │   └── fluorite.png
│           │       │   │   │   ├── humanoid_baby/
│           │       │   │   │   │   └── fluorite.png
│           │       │   │   │   └── humanoid_leggings/
│           │       │   │   │       └── fluorite.png
│           │       │   │   └── villager/
│           │       │   │       └── profession/
│           │       │   │           └── kaupenger.png
│           │       │   ├── gui/
│           │       │   │   ├── crystallizer/
│           │       │   │   │   ├── arrow_progress.png
│           │       │   │   │   └── crystallizer_gui.png
│           │       │   │   └── pedestal/
│           │       │   │       └── pedestal_gui.png
│           │       │   ├── item/
│           │       │   │   ├── bar_brawl_music_disc.png
│           │       │   │   ├── chisel.png
│           │       │   │   ├── chisel_used.png
│           │       │   │   ├── combustible_spores.png
│           │       │   │   ├── fluorite.png
│           │       │   │   ├── fluorite_axe.png
│           │       │   │   ├── fluorite_boots.png
│           │       │   │   ├── fluorite_chestplate.png
│           │       │   │   ├── fluorite_door.png
│           │       │   │   ├── fluorite_helmet.png
│           │       │   │   ├── fluorite_hoe.png
│           │       │   │   ├── fluorite_horse_armor.png
│           │       │   │   ├── fluorite_leggings.png
│           │       │   │   ├── fluorite_pickaxe.png
│           │       │   │   ├── fluorite_shovel.png
│           │       │   │   ├── fluorite_spear.png
│           │       │   │   ├── fluorite_spear_in_hand.png
│           │       │   │   ├── fluorite_sword.png
│           │       │   │   ├── honey_berries.png
│           │       │   │   ├── kaupen_bow.png
│           │       │   │   ├── kaupen_bow_pulling_0.png
│           │       │   │   ├── kaupen_bow_pulling_1.png
│           │       │   │   ├── kaupen_bow_pulling_2.png
│           │       │   │   ├── raw_fluorite.png
│           │       │   │   ├── rice_shoot.png
│           │       │   │   ├── sculkbeam_staff_3d.png
│           │       │   │   ├── spectre_staff.png
│           │       │   │   ├── spectre_staff.png.mcmeta
│           │       │   │   ├── strawberry.png
│           │       │   │   └── strawberry_seeds.png
│           │       │   ├── mob_effect/
│           │       │   │   └── stinky.png
│           │       │   └── painting/
│           │       │       ├── saw_them.png
│           │       │       ├── shrimp.png
│           │       │       ├── wanderer.png
│           │       │       ├── wanderer.png.mcmeta
│           │       │       └── world.png
│           │       └── icon.png
│           ├── fabric.mod.json
│           ├── tutorialmod.classtweaker
│           └── tutorialmod.mixins.json
├── .gitattributes
├── .gitignore
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── LICENSE
├── README.md
└── settings.gradle
```

## File contents

### .gitattributes

```text
#
# https://help.github.com/articles/dealing-with-line-endings/
#
# Linux start script should use lf
/gradlew        text eol=lf

# These are Windows script files and should use crlf
*.bat           text eol=crlf

```

### .gitignore

```text
# gradle

.gradle/
build/
out/
classes/

# eclipse

*.launch

# idea

.idea/
*.iml
*.ipr
*.iws

# vscode

.settings/
.vscode/
bin/
.classpath
.project

# macos

*.DS_Store

# fabric

run/

# java

hs_err_*.log
replay_*.log
*.hprof
*.jfr
```

### build.gradle

```groovy
plugins {
	id 'net.fabricmc.fabric-loom' version "${loom_version}"
	id 'maven-publish'
}

version = project.mod_version
group = project.maven_group

repositories {
	// Add repositories to retrieve artifacts from in here.
	// You should only use this when depending on other mods because
	// Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
	// See https://docs.gradle.org/current/userguide/declaring_repositories.html
	// for more information about repositories.
	maven { url "https://maven.shedaniel.me" }
}

fabricApi {
	configureDataGeneration {
		client = true
	}
}

loom {
	accessWidenerPath = file("src/main/resources/tutorialmod.classtweaker")
}

dependencies {
	// To change the versions see the gradle.properties file
	minecraft "com.mojang:minecraft:${project.minecraft_version}"
	
	implementation "net.fabricmc:fabric-loader:${project.loader_version}"

	// Fabric API. This is technically optional, but you probably want it anyway.
	implementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_api_version}"

	compileOnly("me.shedaniel:RoughlyEnoughItems-api-fabric:26.2.820")
	runtimeOnly("me.shedaniel:RoughlyEnoughItems-fabric:26.2.820")

	include api("dev.architectury:architectury-fabric:21.0.4")
	compileOnly("me.shedaniel.cloth:basic-math:0.6.1")
}

processResources {
	inputs.property "version", project.version

	filesMatching("fabric.mod.json") {
		expand "version": inputs.properties.version
	}
}

tasks.withType(JavaCompile).configureEach {
	it.options.release = 25
}

java {
	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
	// if it is present.
	// If you remove this line, sources will not be generated.
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
}

jar {
	inputs.property "projectName", project.name

	from("LICENSE") {
		rename { "${it}_${project.name}"}
	}
}

// configure the maven publication
publishing {
	publications {
		create("mavenJava", MavenPublication) {
			from components.java
		}
	}

	// See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
	repositories {
		// Add repositories to publish to here.
		// Notice: This block does NOT have the same function as the block in the top level.
		// The repositories here will be used for publishing your artifact, not for
		// retrieving dependencies.
	}
}
```

### gradle.properties

```properties
# Done to increase the memory available to gradle.
org.gradle.jvmargs=-Xmx1G
org.gradle.parallel=true

# IntelliJ IDEA is not yet fully compatible with configuration cache, see: https://github.com/FabricMC/fabric-loom/issues/1349
org.gradle.configuration-cache=false

# Fabric Properties
# check these on https://fabricmc.net/develop
minecraft_version=26.2
loader_version=0.19.3
loom_version=1.17-SNAPSHOT

# Mod Properties
mod_version=0.0.59-26.2
maven_group=net.kaupenjoe.tutorialmod

# Dependencies
fabric_api_version=0.156.0+26.2
```

### gradle/wrapper/gradle-wrapper.properties

```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-9.5.1-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

### gradlew

```
#!/bin/sh

#
# Copyright © 2015 the original authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# SPDX-License-Identifier: Apache-2.0
#

##############################################################################
#
#   Gradle start up script for POSIX generated by Gradle.
#
#   Important for running:
#
#   (1) You need a POSIX-compliant shell to run this script. If your /bin/sh is
#       noncompliant, but you have some other compliant shell such as ksh or
#       bash, then to run this script, type that shell name before the whole
#       command line, like:
#
#           ksh Gradle
#
#       Busybox and similar reduced shells will NOT work, because this script
#       requires all of these POSIX shell features:
#         * functions;
#         * expansions «$var», «${var}», «${var:-default}», «${var+SET}»,
#           «${var#prefix}», «${var%suffix}», and «$( cmd )»;
#         * compound commands having a testable exit status, especially «case»;
#         * various built-in commands including «command», «set», and «ulimit».
#
#   Important for patching:
#
#   (2) This script targets any POSIX shell, so it avoids extensions provided
#       by Bash, Ksh, etc; in particular arrays are avoided.
#
#       The "traditional" practice of packing multiple parameters into a
#       space-separated string is a well documented source of bugs and security
#       problems, so this is (mostly) avoided, by progressively accumulating
#       options in "$@", and eventually passing that to Java.
#
#       Where the inherited environment variables (DEFAULT_JVM_OPTS, JAVA_OPTS,
#       and GRADLE_OPTS) rely on word-splitting, this is performed explicitly;
#       see the in-line comments for details.
#
#       There are tweaks for specific operating systems such as AIX, CygWin,
#       Darwin, MinGW, and NonStop.
#
#   (3) This script is generated from the Groovy template
#       https://github.com/gradle/gradle/blob/2d6327017519d23b96af35865dc997fcb544fb40/platforms/jvm/plugins-application/src/main/resources/org/gradle/api/internal/plugins/unixStartScript.txt
#       within the Gradle project.
#
#       You can find Gradle at https://github.com/gradle/gradle/.
#
##############################################################################

# Attempt to set APP_HOME

# Resolve links: $0 may be a link
app_path=$0

# Need this for daisy-chained symlinks.
while
    APP_HOME=${app_path%"${app_path##*/}"}  # leaves a trailing /; empty if no leading path
    [ -h "$app_path" ]
do
    ls=$( ls -ld "$app_path" )
    link=${ls#*' -> '}
    case $link in             #(
      /*)   app_path=$link ;; #(
      *)    app_path=$APP_HOME$link ;;
    esac
done

# This is normally unused
# shellcheck disable=SC2034
APP_BASE_NAME=${0##*/}
# Discard cd standard output in case $CDPATH is set (https://github.com/gradle/gradle/issues/25036)
APP_HOME=$( cd -P "${APP_HOME:-./}" > /dev/null && printf '%s\n' "$PWD" ) || exit

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD=maximum

warn () {
    echo "$*"
} >&2

die () {
    echo
    echo "$*"
    echo
    exit 1
} >&2

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
nonstop=false
case "$( uname )" in                #(
  CYGWIN* )         cygwin=true  ;; #(
  Darwin* )         darwin=true  ;; #(
  MSYS* | MINGW* )  msys=true    ;; #(
  NONSTOP* )        nonstop=true ;;
esac



# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD=$JAVA_HOME/jre/sh/java
    else
        JAVACMD=$JAVA_HOME/bin/java
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD=java
    if ! command -v java >/dev/null 2>&1
    then
        die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
fi

# Increase the maximum file descriptors if we can.
if ! "$cygwin" && ! "$darwin" && ! "$nonstop" ; then
    case $MAX_FD in #(
      max*)
        # In POSIX sh, ulimit -H is undefined. That's why the result is checked to see if it worked.
        # shellcheck disable=SC2039,SC3045
        MAX_FD=$( ulimit -H -n ) ||
            warn "Could not query maximum file descriptor limit"
    esac
    case $MAX_FD in  #(
      '' | soft) :;; #(
      *)
        # In POSIX sh, ulimit -n is undefined. That's why the result is checked to see if it worked.
        # shellcheck disable=SC2039,SC3045
        ulimit -n "$MAX_FD" ||
            warn "Could not set maximum file descriptor limit to $MAX_FD"
    esac
fi

# Collect all arguments for the java command, stacking in reverse order:
#   * args from the command line
#   * the main class name
#   * -classpath
#   * -D...appname settings
#   * --module-path (only if needed)
#   * DEFAULT_JVM_OPTS, JAVA_OPTS, and GRADLE_OPTS environment variables.

# For Cygwin or MSYS, switch paths to Windows format before running java
if "$cygwin" || "$msys" ; then
    APP_HOME=$( cygpath --path --mixed "$APP_HOME" )

    JAVACMD=$( cygpath --unix "$JAVACMD" )

    # Now convert the arguments - kludge to limit ourselves to /bin/sh
    for arg do
        if
            case $arg in                                #(
              -*)   false ;;                            # don't mess with options #(
              /?*)  t=${arg#/} t=/${t%%/*}              # looks like a POSIX filepath
                    [ -e "$t" ] ;;                      #(
              *)    false ;;
            esac
        then
            arg=$( cygpath --path --ignore --mixed "$arg" )
        fi
        # Roll the args list around exactly as many times as the number of
        # args, so each arg winds up back in the position where it started, but
        # possibly modified.
        #
        # NB: a `for` loop captures its iteration list before it begins, so
        # changing the positional parameters here affects neither the number of
        # iterations, nor the values presented in `arg`.
        shift                   # remove old arg
        set -- "$@" "$arg"      # push replacement arg
    done
fi


# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Collect all arguments for the java command:
#   * DEFAULT_JVM_OPTS, JAVA_OPTS, and optsEnvironmentVar are not allowed to contain shell fragments,
#     and any embedded shellness will be escaped.
#   * For example: A user cannot expect ${Hostname} to be expanded, as it is an environment variable and will be
#     treated as '${Hostname}' itself on the command line.

set -- \
        "-Dorg.gradle.appname=$APP_BASE_NAME" \
        -jar "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" \
        "$@"

# Stop when "xargs" is not available.
if ! command -v xargs >/dev/null 2>&1
then
    die "xargs is not available"
fi

# Use "xargs" to parse quoted args.
#
# With -n1 it outputs one arg per line, with the quotes and backslashes removed.
#
# In Bash we could simply go:
#
#   readarray ARGS < <( xargs -n1 <<<"$var" ) &&
#   set -- "${ARGS[@]}" "$@"
#
# but POSIX shell has neither arrays nor command substitution, so instead we
# post-process each arg (as a line of input to sed) to backslash-escape any
# character that might be a shell metacharacter, then use eval to reverse
# that process (while maintaining the separation between arguments), and wrap
# the whole thing up as a single "set" statement.
#
# This will of course break if any of these variables contains a newline or
# an unmatched quote.
#

eval "set -- $(
        printf '%s\n' "$DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS" |
        xargs -n1 |
        sed ' s~[^-[:alnum:]+,./:=@_]~\\&~g; ' |
        tr '\n' ' '
    )" '"$@"'

exec "$JAVACMD" "$@"
```

### gradlew.bat

```batch
@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem
@rem SPDX-License-Identifier: Apache-2.0
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line



@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -jar "%APP_HOME%\gradle\wrapper\gradle-wrapper.jar" %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%GRADLE_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
```

### LICENSE

```text
MIT License

Copyright (c) 2026 Kaupenjoe

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

### README.md

```markdown
<a href="https://www.youtube.com/playlist?list=PLKGarocXCE1H4l8g3c_KoRMsFzBsVjjAx" target="_blank" rel="noopener noreferrer">
<p align="center">
<img width="1000" alt="fabric-26x-github" src="https://github.com/user-attachments/assets/272c2f85-44f3-4631-9c32-b689c9399495" />
</p></a>

# Fabric Modding Tutorials For Minecraft 26.X 
This is the GitHub Repository for Kaupenjoe's Fabric Modding Tutorials For Minecraft 26.X

The Individual Tutorials are seperated into Branches for ease of access. 

Watch the Tutorials here: <a href="https://www.youtube.com/playlist?list=PLKGarocXCE1H4l8g3c_KoRMsFzBsVjjAx" target="_blank" rel="noopener noreferrer">YouTube Playlist</a>
```

### settings.gradle

```groovy
pluginManagement {
	repositories {
		maven {
			name = 'Fabric'
			url = 'https://maven.fabricmc.net/'
		}
		mavenCentral()
		gradlePluginPortal()
	}
}

// Should match your modid
rootProject.name = 'tutorialmod'
```

### src/main/generated/.cache/05bae46595fc0bd4302e11de00fed8267f787d53

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/Advancements
4fffe40681c47235b011a326e8568bbb49623fd1 data/tutorialmod/advancement/tutorialmod/chisel_stone.json
521c3ed6280f0f223dde0235c3cc85d29b8227b0 data/tutorialmod/advancement/tutorialmod/plant_custom.json
e9ff4065a93497c23a779f407ef099c8b653b79c data/tutorialmod/advancement/tutorialmod/root.json
```

### src/main/generated/.cache/130f8a64b4c2c55053b3e123f552da6b92e44921

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/Tags for minecraft:painting_variant
5811bc92007c2cc55e013e451e0b0103b23bd185 data/minecraft/tags/painting_variant/placeable.json
```

### src/main/generated/.cache/378f9509398ea584ca54616156177f9ec588f984

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/Block Loot Tables
10049efffcd1f02501c0ef51d3b706e68dbec96b data/tutorialmod/loot_table/blocks/balsa_leaves.json
c72e4e554e077418c5531ab0884d1eb43d1d2314 data/tutorialmod/loot_table/blocks/balsa_log.json
27cbd89508407f9d7b02431b002fd6f53adb5422 data/tutorialmod/loot_table/blocks/balsa_planks.json
bc3fabe1fd174ccc0f1a7c25fa881677e206cc7a data/tutorialmod/loot_table/blocks/balsa_sapling.json
34847477a60a509636cfd2579ce85ae7a3a7971f data/tutorialmod/loot_table/blocks/balsa_wood.json
d9472353b7c7816787642fbb1de9232cef946d14 data/tutorialmod/loot_table/blocks/crystallizer.json
1a62cf52312569b737493d3ad2cfe3b8e49f203d data/tutorialmod/loot_table/blocks/fluorite_block.json
16b6883b0a0f65f1d95de357c2158db63ecc4d91 data/tutorialmod/loot_table/blocks/fluorite_button.json
cdadf66a1740ca61b8d8ac80db954ccf1b9e86e6 data/tutorialmod/loot_table/blocks/fluorite_deepslate_ore.json
f82adb1e6fa7549d37b963286f1d459766ecb894 data/tutorialmod/loot_table/blocks/fluorite_door.json
b52cb43b8ba37a5349c33a149f66dc55b5444db7 data/tutorialmod/loot_table/blocks/fluorite_end_ore.json
965d358e5db4873acf23d23a110d8364111644e0 data/tutorialmod/loot_table/blocks/fluorite_fence.json
8ec54630347aea8533d971f83c1594c3860169b2 data/tutorialmod/loot_table/blocks/fluorite_fence_gate.json
40d5b3b91eda8a5704aec411ae05e7e1f42f12e6 data/tutorialmod/loot_table/blocks/fluorite_lamp.json
5e5331c30ae840c4332e9aaf81b2a0b2f8da834c data/tutorialmod/loot_table/blocks/fluorite_nether_ore.json
fa645c01eecfa00707a28170f21f791d43b84152 data/tutorialmod/loot_table/blocks/fluorite_ore.json
cd6855c212c6ed0c0e4684ec353d63087d3e7ebe data/tutorialmod/loot_table/blocks/fluorite_pressure_plate.json
aa7e5f2776fb880f9a565014f78ae22567b4ccd2 data/tutorialmod/loot_table/blocks/fluorite_slab.json
22cc4135ab398ad9f5f08b9afe20ecb1dc152771 data/tutorialmod/loot_table/blocks/fluorite_stairs.json
8e7980789dee0ec3396b8d8af38871a4932e41e7 data/tutorialmod/loot_table/blocks/fluorite_trapdoor.json
57e2e2c41751db517dfa9360009d5df6119614c1 data/tutorialmod/loot_table/blocks/fluorite_wall.json
568062e57223ef08cbb2e20d61ed06e93a307ed4 data/tutorialmod/loot_table/blocks/honey_berry_bush.json
2d9bed7047c1a4ada75161cd0b226c0f4225b0a3 data/tutorialmod/loot_table/blocks/magic_block.json
7c60636e3816d76d5190182420bc2980dc0e2d77 data/tutorialmod/loot_table/blocks/pedestal.json
29ced49426bb0622ca62a46baa136b159a3c14da data/tutorialmod/loot_table/blocks/potted_balsa_sapling.json
16b0ca60d221dced0b45dc98a8638772cb801670 data/tutorialmod/loot_table/blocks/raw_fluorite_block.json
f42f5b7ed3959f76532437420bf820c56f8a289c data/tutorialmod/loot_table/blocks/rice_crop.json
fa079db44981c2146d255387068ba786c6a58262 data/tutorialmod/loot_table/blocks/strawberry_crop.json
e22534de7802bdfc55c5d5a04252c3396d916ec4 data/tutorialmod/loot_table/blocks/stripped_balsa_log.json
e8354abb591d1f0bab72e9ebf2e9d4cd7a80b297 data/tutorialmod/loot_table/blocks/stripped_balsa_wood.json
```

### src/main/generated/.cache/47bc1c7dfdd699053473afebbde6668d4605aa61

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/TutorialMod Sounds
f49d4daf732b3abb101786c5d8a156679cb6246a assets/tutorialmod/sounds.json
```

### src/main/generated/.cache/53d8ea8352608c95fff1fc7a65c403162cc24c7e

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/Tags for minecraft:villager_trade
fa6264ba3de001c31118c881d66958348b33a10c data/minecraft/tags/villager_trade/farmer/level_1.json
ffe3071cba3fe213630952e2953530a4bef22331 data/minecraft/tags/villager_trade/farmer/level_2.json
fc673227bf65f9fe869f9cb8bdd8b34178cd5f69 data/minecraft/tags/villager_trade/librarian/level_1.json
63648306af3e0e54be188fbe2b89090e3ca103a1 data/minecraft/tags/villager_trade/mason/level_1.json
7d23895eb3cf3b1e32d2e899c67ddd3100f89e89 data/tutorialmod/tags/villager_trade/kaupenger/level_1.json
332846f57f29043dcc5a534f6a324d7b4ac665d7 data/tutorialmod/tags/villager_trade/kaupenger/level_2.json
```

### src/main/generated/.cache/80beaa027efbcf9e17d04d48300d7784fb0cb57f

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/Tags for minecraft:block
af81c6a1615e814f15ed8b1226ac6f25cd6be4e6 data/minecraft/tags/block/buttons.json
6357faab516e9eac4a5af148fbc6b0f7afecd208 data/minecraft/tags/block/crops.json
8b21129069f1ac9ac16b52da3381ca127cf969dc data/minecraft/tags/block/doors.json
b96af12f0816fed2734564c13c429281c4307c79 data/minecraft/tags/block/fence_gates.json
9268cd5b22bc00db499526376630c97dcdf9f42b data/minecraft/tags/block/fences.json
d86010b9ebd7690b0c88c9169bf170224fe168c2 data/minecraft/tags/block/flower_pots.json
d3ce8e7370e94ff508085e40482ed386dbd29f1c data/minecraft/tags/block/leaves.json
55d826edf66720ab21b70fe688cb21d598b1c489 data/minecraft/tags/block/logs.json
8c9ce01ceaa198f0262e4a7e430b45107a529b27 data/minecraft/tags/block/mineable/pickaxe.json
91c0c7b996f2a4ec93bb3f508eb6a3d47ceb963a data/minecraft/tags/block/needs_diamond_tool.json
09b69dd749a4b48f17e7f17cf83683a85fe74baa data/minecraft/tags/block/needs_iron_tool.json
5c6708099b2a23842920dfe05ef23a5aeb5b2370 data/minecraft/tags/block/planks.json
efb6bf783ae4b91c349cfc97684ce81ec32b07c9 data/minecraft/tags/block/pressure_plates.json
8c019b47136a16371ef04386ae13da3973127027 data/minecraft/tags/block/slabs.json
3dc421f3f81f32add48f38406a1626f63f9669d3 data/minecraft/tags/block/stairs.json
a57b1c7242906614c9b11700650889a0f35c6054 data/minecraft/tags/block/trapdoors.json
0d1f452be27f38eb1f96dee1ba12db3b118807ba data/minecraft/tags/block/walls.json
d8b534b7d592190656e55bb670f6120fb559188b data/tutorialmod/tags/block/balsa_logs.json
a249c46329a41538f49a564e1f14338aaf1be5a0 data/tutorialmod/tags/block/incorrect_for_fluorite_tool.json
c07c4c3be2f1e2228e70cca883d68ce9bd7ba47e data/tutorialmod/tags/block/needs_fluorite_tool.json
```

### src/main/generated/.cache/96bb53df1af2d21ca7a52efa65985f699489396c

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/TutorialMod Data Provider
9ef89a7ecd1b361c7900616e47e85cb9f487e025 data/tutorialmod/damage_type/stinky.json
a237eef9c44bb29a83e9414b051e6af191bc36e6 data/tutorialmod/jukebox_song/bar_brawl.json
d1c58072b0fb14f5f9140693de3337043bd4f455 data/tutorialmod/painting_variant/saw_them.json
cf6d7212b9f86c564eb6bba56e1c9b392437e697 data/tutorialmod/painting_variant/shrimp.json
0219e1feecc71871a4bff0a9b2f06673370db5ac data/tutorialmod/painting_variant/wanderer.json
d7831086327a0178d8ae8f4ec695095a503a9df0 data/tutorialmod/painting_variant/world.json
d311a7336b1010e2796e58c322ad888489d60255 data/tutorialmod/trade_set/kaupenger/level_1.json
1460d7181a89422dac3dcdc12861eb0f79c8f275 data/tutorialmod/trade_set/kaupenger/level_2.json
2f9e89dda020234ddb9f2f76b4c278c14f50a266 data/tutorialmod/villager_trade/farmer/1/diamond_strawberry_seeds.json
4d38811bd5aa78deef6ba63791711c825cbda033 data/tutorialmod/villager_trade/farmer/1/emerald_strawberry.json
89b4016c80139226b3946a20b470cf5ec74e3728 data/tutorialmod/villager_trade/farmer/2/emerald_honey_berries.json
58fb87e97a82e574e7e034530672794798d1e966 data/tutorialmod/villager_trade/kaupenger/1/emerald_fluorite.json
1e4068cac6304fbcae98af993536820896b8c264 data/tutorialmod/villager_trade/kaupenger/1/emerald_raw_fluorite.json
503709de76eb897e73b32a54b08520ea53945900 data/tutorialmod/villager_trade/kaupenger/2/emerald_pedestal.json
e70404a83e9b1c8525370b33c8e0f0d0e25da57a data/tutorialmod/villager_trade/kaupenger/2/fluorite_spectre_staff.json
9d1668185beaf134df91f4c3b5e2daed213e51df data/tutorialmod/villager_trade/librarian/1/fluorite_enchanted_book.json
a369bb68932e9367572f6b67e79e92ce14b44f5d data/tutorialmod/villager_trade/mason/1/fluorite_chisel.json
446edd36f6786b5272c8fafb1c75a8638f25841c data/tutorialmod/worldgen/configured_feature/balsa.json
9621c70bb77acfb92069ffcd8473d6c3202bb992 data/tutorialmod/worldgen/configured_feature/end_fluorite_ore.json
07544d78ddcdfd3ac3ff8e61f5b72c701369c450 data/tutorialmod/worldgen/configured_feature/honey_berry_bush.json
b8b993d20ad3f38388239f933ab136ffe1f165ca data/tutorialmod/worldgen/configured_feature/nether_fluorite_ore.json
59d8cd874a4570fb60267bf4da1d6c7f499ff080 data/tutorialmod/worldgen/configured_feature/overworld_fluorite_ore.json
7099e2c6dfc4ad4b82ac90ae161bdf73eeb44455 data/tutorialmod/worldgen/placed_feature/balsa_placed.json
de80ea236b53cbf3d4b2b5b0d8a1fbdf373eb56f data/tutorialmod/worldgen/placed_feature/end_fluorite_ore_placed.json
f4bd518ad600fef7c66325719307a2552e38bc45 data/tutorialmod/worldgen/placed_feature/honey_berry_bush_placed.json
4598b2abfc44ffeede61403c4f9955950b058d6a data/tutorialmod/worldgen/placed_feature/nether_fluorite_ore_placed.json
f0ee4e969dc13e40ca7672d1bb28fb9e28fa89a0 data/tutorialmod/worldgen/placed_feature/overworld_fluorite_ore_placed.json
```

### src/main/generated/.cache/b0e779921aa000a5ad49c5afacbcbc5d79eb02b0

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/Tutorial Mod Equipment Asset Definitions
226a9ac042f31ba7ae6bba302cfc4abda057a9b0 assets/tutorialmod/equipment/fluorite.json
```

### src/main/generated/.cache/bf8b55c7a3e1253642a8b1063c0b39ff5689945b

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/Model Definitions
ef38dd02787db065b04d7c489b07cedfa41906e2 assets/tutorialmod/blockstates/balsa_leaves.json
79b0901cb59976f54e06cc9e87ffe6fab99233e0 assets/tutorialmod/blockstates/balsa_log.json
4f9642cdcf8993840e06fe6ccb575831b8dee473 assets/tutorialmod/blockstates/balsa_planks.json
a2e83365a4c75fbf8b0d8b881647621504022629 assets/tutorialmod/blockstates/balsa_sapling.json
c631dec42ef62c88f4defa52fee07fa8b37de440 assets/tutorialmod/blockstates/balsa_wood.json
8f8818bd55ce50d3fe571f04b4dbb77550b1f165 assets/tutorialmod/blockstates/crystallizer.json
2a568c774bd47c66c989aa375efbb434012de0f2 assets/tutorialmod/blockstates/fluorite_block.json
48534abaa08202fd704000b498d15db032626af0 assets/tutorialmod/blockstates/fluorite_button.json
ccf29a4c548cf47abbebf147531799b1ce71c944 assets/tutorialmod/blockstates/fluorite_deepslate_ore.json
85abc87f3f009aef3cca6b955e4fc749a98ee066 assets/tutorialmod/blockstates/fluorite_door.json
0642a88b907390b3788e392330cd6ff776b28b0a assets/tutorialmod/blockstates/fluorite_end_ore.json
babe71ed8aedb6fb79c884a83fce6769d416e6ae assets/tutorialmod/blockstates/fluorite_fence.json
184eb013532acf748e9b89a4aa7539a834bc60f1 assets/tutorialmod/blockstates/fluorite_fence_gate.json
dc02d7aa175726375e0d551694c2e96fcef1dc45 assets/tutorialmod/blockstates/fluorite_lamp.json
85125fa9e49cd8693a77a745c0c1e89080dbd70b assets/tutorialmod/blockstates/fluorite_nether_ore.json
85976c9b7d3e65c2313a5b97b0f71ac0ed6290ab assets/tutorialmod/blockstates/fluorite_ore.json
a7279ac52201e28ffd8d91ec012e92b07ad1ad1d assets/tutorialmod/blockstates/fluorite_pressure_plate.json
5b2f62c28a58d54bb817fe424763b3c161b35425 assets/tutorialmod/blockstates/fluorite_slab.json
08dc9a0a4b2fdb652036f8ecfde97f29f35c84f9 assets/tutorialmod/blockstates/fluorite_stairs.json
40c95d7fbf5022ce7b428f0927d2036f8bd4216a assets/tutorialmod/blockstates/fluorite_trapdoor.json
a67d84aa485e90ae15c430e93fd422c0c8700c56 assets/tutorialmod/blockstates/fluorite_wall.json
122234d5a08c337bde3aa74fc23731cf53f4d7c8 assets/tutorialmod/blockstates/honey_berry_bush.json
2d316979b5247b802b0590d94b40bac75fde6974 assets/tutorialmod/blockstates/magic_block.json
c11df765d5d71cad25f0e3aed9899927c9a7a6f2 assets/tutorialmod/blockstates/pedestal.json
d5888b24b40ff64ae356f7fced1f688d144bb776 assets/tutorialmod/blockstates/potted_balsa_sapling.json
a8f77b79151dd10efabd9241d1094ef852a85523 assets/tutorialmod/blockstates/raw_fluorite_block.json
698ac955b367921f626869dff5b8f31e65f39f49 assets/tutorialmod/blockstates/rice_crop.json
9721c9f9b84c72b9e971bd786894b2ef928cd5fe assets/tutorialmod/blockstates/strawberry_crop.json
d2447a5f4c8e572c8bed93b3390ce12dee4bb49d assets/tutorialmod/blockstates/stripped_balsa_log.json
9adb02a8d006d9f99e3b3c13e2b07431270d0f7a assets/tutorialmod/blockstates/stripped_balsa_wood.json
8fd3cf8d845a1c8e9f4b8e1fad7356add11eab06 assets/tutorialmod/items/balsa_leaves.json
b362a47dded2c6e24021dd5dc7456951688ece3b assets/tutorialmod/items/balsa_log.json
451008e2bb8a1ce87dce9b90b4eabe8c9eee281f assets/tutorialmod/items/balsa_planks.json
498afb63adc806bdccc1771bf02a0f2dc4502a79 assets/tutorialmod/items/balsa_sapling.json
63f55be58528fa0b9f2583508becdf4ab10fad69 assets/tutorialmod/items/balsa_wood.json
f6dfc600d4c35c10cdbbec764da5462f6b780ac4 assets/tutorialmod/items/bar_brawl_music_disc.json
9aec8a64b45eca7155b4dbc76eb828476de91683 assets/tutorialmod/items/chisel.json
66bdc540a4ca7277c988b1eb2de72334361207f0 assets/tutorialmod/items/combustible_spores.json
270501c7870d6751f6115fb9a688b10db2bea993 assets/tutorialmod/items/crystallizer.json
0be1ad5ae2627b4ea20c36c422e6001a29ecc7fe assets/tutorialmod/items/fluorite.json
c9245957b5110d15938ee068c8004fb38061553b assets/tutorialmod/items/fluorite_axe.json
8a886cd8cf919a266d3ea01d8a0c209df2cf4616 assets/tutorialmod/items/fluorite_block.json
5b7ad9d7740023173fd74b916c3ed370e6f91bf1 assets/tutorialmod/items/fluorite_boots.json
55e4604e14bfaf40883e1a6a19c9b2612c2b42d0 assets/tutorialmod/items/fluorite_button.json
c3f82fbe14ca02fe9c1c7044164fa99cc54dabd5 assets/tutorialmod/items/fluorite_chestplate.json
c564ff465950e6a65b4c9c6b6da283a90e1016f6 assets/tutorialmod/items/fluorite_deepslate_ore.json
f868f9765d3ef3ccb172faed0abea196c468b6fe assets/tutorialmod/items/fluorite_door.json
b3a0ac01c570da694c5a48803ac53ad5f4e56424 assets/tutorialmod/items/fluorite_end_ore.json
58f24ee58477850ad3fc9100acfa03a2a3af585c assets/tutorialmod/items/fluorite_fence.json
5ac0c35588481bebe93ed5a7281495e6b653c7d6 assets/tutorialmod/items/fluorite_fence_gate.json
4d10ffe39e0967f1795e019c73549f4a02b56b98 assets/tutorialmod/items/fluorite_helmet.json
35fb84995aed02e3d0aa699239c28c99fcbfce62 assets/tutorialmod/items/fluorite_hoe.json
e470f95645455dee5e30677bc5a6b2832ea2a0d5 assets/tutorialmod/items/fluorite_horse_armor.json
3a6998465b826d1a4e999eaed7a68a897ec60b4d assets/tutorialmod/items/fluorite_lamp.json
57f19cc7c8887b2a0b10335197af52e321a7b928 assets/tutorialmod/items/fluorite_leggings.json
405c3dd954f3ab903fc4991095e4ad2ed1ee6a82 assets/tutorialmod/items/fluorite_nether_ore.json
46ac693ecdb30ad607491083b7824629389d5ff8 assets/tutorialmod/items/fluorite_ore.json
78d9e82bdf3f90fb1ddb5a9bb59bace375311177 assets/tutorialmod/items/fluorite_pickaxe.json
47a65f41f65361a31e9545b57b93e5803cc32d67 assets/tutorialmod/items/fluorite_pressure_plate.json
757964af3137e1b026cf32d071bfa10d947ff065 assets/tutorialmod/items/fluorite_shovel.json
3ecbd52ed13e9ff76416cf6e8da23be4cd9ce827 assets/tutorialmod/items/fluorite_slab.json
ce02627d3de14317ab63357835cf2f1fdf0bf050 assets/tutorialmod/items/fluorite_spear.json
7cf3a9d75d4881cb4b8d235899194f75f9933d6e assets/tutorialmod/items/fluorite_stairs.json
69320920591752e1ce6978942b8cb92912ad6749 assets/tutorialmod/items/fluorite_sword.json
bef53c285a33c53c97402c3f529b9da35d16a172 assets/tutorialmod/items/fluorite_trapdoor.json
d8dab952610c75282cdc531bcc29e44dbc90833d assets/tutorialmod/items/fluorite_wall.json
5dd116392fbbb918fe88361d8a1a3a5ea3def892 assets/tutorialmod/items/honey_berries.json
2a600122bf21778278370c11a57a8f10e24e92a5 assets/tutorialmod/items/kaupen_bow.json
8fe54fac0d8b2763fdecd5fd624085c91405390a assets/tutorialmod/items/magic_block.json
85542db5c5bcf12b3e22909c96154002b18e68a4 assets/tutorialmod/items/pedestal.json
c9f5aefcd09ada451a50297fe87f61e6d9266e2d assets/tutorialmod/items/raw_fluorite.json
00560a755d0223c9b2703475550b6df99093e416 assets/tutorialmod/items/raw_fluorite_block.json
65ced611a3c9c6d021178947f469dc56fe2e3e98 assets/tutorialmod/items/rice_shoot.json
287779a210a53630517d114193507e954f19bc76 assets/tutorialmod/items/sculkbeam_staff.json
d6b54da31b4df9070aae2b6c2465d20031d2a832 assets/tutorialmod/items/spectre_staff.json
8ea9db75282c11ef4cda960b4676d97ea2db022e assets/tutorialmod/items/strawberry.json
c980ab0436cc319420b26d08178142eab0c769c5 assets/tutorialmod/items/strawberry_seeds.json
6c8b806bdbc17652eacf3be99edd544982772edc assets/tutorialmod/items/stripped_balsa_log.json
bb3f7ac8c9bae5a5cc60a9a70c3ebec1ef5bad0e assets/tutorialmod/items/stripped_balsa_wood.json
50cd0f226c556a891283879c9fa0ced1dbd55948 assets/tutorialmod/models/block/balsa_leaves.json
1625ee3eaa44476a2f0c3bd7258eeb2ac8111399 assets/tutorialmod/models/block/balsa_log.json
139c66588668d8d54fbd4d42ceab8e1f6053ced6 assets/tutorialmod/models/block/balsa_planks.json
67d183eec4f8afd4dedc8e2e240b42d5f2016075 assets/tutorialmod/models/block/balsa_sapling.json
04c24ab1e6875d4224994fb72573507ab2677528 assets/tutorialmod/models/block/balsa_wood.json
0065e510246f6a7754873826a47a6c7e9a6a3d16 assets/tutorialmod/models/block/crystallizer.json
fff9f2678ca0ccc4e791a63552fdb65ad9307778 assets/tutorialmod/models/block/crystallizer_on.json
b55e43776f1d0310bd46fe712a3108e00568002d assets/tutorialmod/models/block/fluorite_block.json
546f5e0cddc980f094dd8ca07557ca951ed52ed0 assets/tutorialmod/models/block/fluorite_button.json
1315c1d757931d2d3a8dac761cdde122147c5952 assets/tutorialmod/models/block/fluorite_button_inventory.json
bb2505640e80612e5b6e3d31727354112b05bc67 assets/tutorialmod/models/block/fluorite_button_pressed.json
84bef66c92536b5351dd3e5d0921a7d86c5f2d50 assets/tutorialmod/models/block/fluorite_deepslate_ore.json
4eda3d5663000fae821c17cc287472c6282ed603 assets/tutorialmod/models/block/fluorite_door_bottom_left.json
365fc189d645b4fd13dde327d6a9b8dc3f7271a8 assets/tutorialmod/models/block/fluorite_door_bottom_left_open.json
96e291bc0a82512039d278d182d9e6fc7db578e0 assets/tutorialmod/models/block/fluorite_door_bottom_right.json
807b2259a3a0a195c82efbea2da1e67f3b178153 assets/tutorialmod/models/block/fluorite_door_bottom_right_open.json
2e9a73c6567dbe341577406851c8b78bd1292a68 assets/tutorialmod/models/block/fluorite_door_top_left.json
a0f90c5887a53ef457d52403c2bcbe03dc15bcc6 assets/tutorialmod/models/block/fluorite_door_top_left_open.json
6f715806538fb78fdb41167370b01701c5de037b assets/tutorialmod/models/block/fluorite_door_top_right.json
a3fdc6a5199f29bc6737b94baeea40193da3afdf assets/tutorialmod/models/block/fluorite_door_top_right_open.json
12b846233ac3a5f03bd8c3aa76b2237bd355051c assets/tutorialmod/models/block/fluorite_end_ore.json
72bc6a7bb61f2abd402e30faf60a171c06fb7ee4 assets/tutorialmod/models/block/fluorite_fence_gate.json
dceffda15ab41340e34a216da856c66066574ffa assets/tutorialmod/models/block/fluorite_fence_gate_open.json
d7d11ae7add7ad84af0fe22606c742bc97735144 assets/tutorialmod/models/block/fluorite_fence_gate_wall.json
20384f1c35892f565f32e36e67b7fb03d4cc910a assets/tutorialmod/models/block/fluorite_fence_gate_wall_open.json
6b629abfd9d6c2322bfc994a1e1e2df6f918bd2a assets/tutorialmod/models/block/fluorite_fence_inventory.json
57aef5cb650e146ec2a111c1e3f758dfc2165c91 assets/tutorialmod/models/block/fluorite_fence_post.json
ec571945d46f6d6e5130b054b0d8ac5c25d12ea0 assets/tutorialmod/models/block/fluorite_fence_side.json
e2cc6146e6d60090c450bffd4dcff883805147c2 assets/tutorialmod/models/block/fluorite_lamp.json
f09f3e3f932e6836aba8154d8fdb2a35b41b919c assets/tutorialmod/models/block/fluorite_lamp_on.json
cacfe22960acf0335f417916deffb8407dac45c9 assets/tutorialmod/models/block/fluorite_nether_ore.json
e9f1a660511eb973ed117b73d1f689e5848c15fa assets/tutorialmod/models/block/fluorite_ore.json
53f10a4c0cff0a6ee784045b03931a62789f2465 assets/tutorialmod/models/block/fluorite_pressure_plate.json
f935165e14efd4ffa52838284c4d1672ffdb7065 assets/tutorialmod/models/block/fluorite_pressure_plate_down.json
7763e94f5339a0c686bbaa72c4ace91883e715a9 assets/tutorialmod/models/block/fluorite_slab.json
bee69b8fbe91b8f0765fd0679d464b4eb184d869 assets/tutorialmod/models/block/fluorite_slab_top.json
70e5da434f29c126eb7893defc89b58f56db81f9 assets/tutorialmod/models/block/fluorite_stairs.json
91f975c78323afa270221a73ba38640e77f26ebd assets/tutorialmod/models/block/fluorite_stairs_inner.json
b9880d13624901310d6ffb2e7ab1c75915b8ab97 assets/tutorialmod/models/block/fluorite_stairs_outer.json
b447e2a4b73c23a22fae0e4310574ae7b5b13d68 assets/tutorialmod/models/block/fluorite_trapdoor_bottom.json
267a5b3f9c4354fd1d6149cc8648e32e1c0c9a37 assets/tutorialmod/models/block/fluorite_trapdoor_open.json
09e88500bfe185b60ef9b8e6a03c956dcc755d77 assets/tutorialmod/models/block/fluorite_trapdoor_top.json
80134fe4720ee35b89aa01bfbeab99db280db31a assets/tutorialmod/models/block/fluorite_wall_inventory.json
a8f996033bd0fc845de4f8c216eadc618cd2580d assets/tutorialmod/models/block/fluorite_wall_post.json
b9307fb73d3f961bcf0482231c318bde98613e6f assets/tutorialmod/models/block/fluorite_wall_side.json
f04bd29db0344d3543a186f935b5bcce843dccd7 assets/tutorialmod/models/block/fluorite_wall_side_tall.json
d4f2571a42ea39d2c92dcdcbcd9d835f5e0ea76c assets/tutorialmod/models/block/honey_berry_bush_stage0.json
a6f7246ee184c6cf0823d42ea0f7fbae96f46f23 assets/tutorialmod/models/block/honey_berry_bush_stage1.json
5406dfa73f1fda8a1666beebed37678f095738e8 assets/tutorialmod/models/block/honey_berry_bush_stage2.json
235c845ba3c1ffa4ee1e378a7bc60d54f79a74f7 assets/tutorialmod/models/block/honey_berry_bush_stage3.json
ed27aaa00c275028e1495bb6d6bfdcea1851067b assets/tutorialmod/models/block/magic_block.json
12dd4145c16cf72a64ec1940656933ce49baf02b assets/tutorialmod/models/block/potted_balsa_sapling.json
ed95a1b56248546b751aa94e25c7441dc13fc9fe assets/tutorialmod/models/block/raw_fluorite_block.json
d7f429183aef1d8240963eb832fda200f6d2c5b2 assets/tutorialmod/models/block/rice_crop_stage0.json
69468e9ee8603b2c7f1130c672a208aa75637cdd assets/tutorialmod/models/block/rice_crop_stage1.json
15344a15f5c0f58e7f935c41a8d523aa876205e7 assets/tutorialmod/models/block/rice_crop_stage2.json
974d953078d7c4bdee5dde97fbcfe4a19aba3cd1 assets/tutorialmod/models/block/rice_crop_stage3.json
076ccca5718c65da2699b59f34fa83d946ace9c4 assets/tutorialmod/models/block/rice_crop_stage4.json
b85d6440fb59ed5931f7bf4a6fcdc2e32d51e2da assets/tutorialmod/models/block/rice_crop_stage5.json
4f9f47cf9a5e0ff36a5d53b962e95ed0056e5a9f assets/tutorialmod/models/block/rice_crop_stage6.json
6343d5f89a7b5a55b070ac7147e707de5faa9919 assets/tutorialmod/models/block/rice_crop_stage7.json
47fc1f3bf456f1d24386901de5672bcc448b0368 assets/tutorialmod/models/block/strawberry_crop_stage0.json
026ec2ab2aa281b278c27fbdc3fec6021f20f264 assets/tutorialmod/models/block/strawberry_crop_stage1.json
a32931542da8d8782c8117c56fcfed5e7bc90e66 assets/tutorialmod/models/block/strawberry_crop_stage2.json
8bf32cec233c7dd5d473443c384088760360d3d0 assets/tutorialmod/models/block/strawberry_crop_stage3.json
78f02cad040e6cd9ce6102f99c6f0c4b21ac9b3a assets/tutorialmod/models/block/strawberry_crop_stage4.json
15552caacbe73e57ba0067546d87a314b82138a9 assets/tutorialmod/models/block/strawberry_crop_stage5.json
0ddaae5c5f794ef478528883578c7af85a2563ff assets/tutorialmod/models/block/stripped_balsa_log.json
166aec81377cb68afdf5e43fdc11aed1e73c792e assets/tutorialmod/models/block/stripped_balsa_wood.json
9346b9ba98a86fca7ffc9cd1943590ec3d395714 assets/tutorialmod/models/item/balsa_sapling.json
f746b64540daf2ab6302b3ec01a78490c48fc10b assets/tutorialmod/models/item/bar_brawl_music_disc.json
b91de6357c95acf8ea0622be5d7cb112d6ec423e assets/tutorialmod/models/item/chisel.json
054f35acfe323c98580cd1d618f1373c59837caf assets/tutorialmod/models/item/chisel_used.json
e5fd1938cfc865ceb56f97bf4cfcb90e53bb0ef6 assets/tutorialmod/models/item/combustible_spores.json
274bc448a39a256231897fcbf4596ac4db786fa2 assets/tutorialmod/models/item/fluorite.json
8c9c4e4a0818d354b5a5972a0cad22baf952827d assets/tutorialmod/models/item/fluorite_axe.json
87a028b7dd2dd52b5459f2f89acc33a0ed0d3662 assets/tutorialmod/models/item/fluorite_boots.json
51fcbd5da20ac740b58099d9ae103557a94d8361 assets/tutorialmod/models/item/fluorite_boots_amethyst_trim.json
017115f45a777fed9dfe373f22df00d056a01271 assets/tutorialmod/models/item/fluorite_boots_copper_trim.json
e2f4b31a2edb5e54825b407dcdb5fc8f8e2a766a assets/tutorialmod/models/item/fluorite_boots_diamond_trim.json
1cb59d177bf48f7114790b70807b49df2bc3c7eb assets/tutorialmod/models/item/fluorite_boots_emerald_trim.json
3171915a86158db16d296fa3c74d79a9cab9ae2b assets/tutorialmod/models/item/fluorite_boots_gold_trim.json
61b4107ba5e56d1fa7652115f5fee99b15c2af70 assets/tutorialmod/models/item/fluorite_boots_iron_trim.json
87416c5fe25f1ead822cc37f22701b91d58e6c15 assets/tutorialmod/models/item/fluorite_boots_lapis_trim.json
87ff8d451fe281cdb8906cac30f7a15438739527 assets/tutorialmod/models/item/fluorite_boots_netherite_trim.json
9de25a07c86297e071cfd35ae2a1a348bbdcb6b8 assets/tutorialmod/models/item/fluorite_boots_quartz_trim.json
fe69265220ecc7df3673fdff4f8d74c0ee935306 assets/tutorialmod/models/item/fluorite_boots_redstone_trim.json
3c88bbf1b5c09244c02c5dc527de0e4b66530774 assets/tutorialmod/models/item/fluorite_boots_resin_trim.json
22e12c35f0ea262ff00295f49116e1d63c46969a assets/tutorialmod/models/item/fluorite_chestplate.json
86bc63fa44a9e39626cb7c01162917e1a274542d assets/tutorialmod/models/item/fluorite_chestplate_amethyst_trim.json
3e1664676efc5c1c75416087bec216dd6f954c94 assets/tutorialmod/models/item/fluorite_chestplate_copper_trim.json
7d35d438795e43e0c97e3458d0e51a7ae7d3fa03 assets/tutorialmod/models/item/fluorite_chestplate_diamond_trim.json
ba909a10cb6dc5e6d1d5bf98c996485f89aadd85 assets/tutorialmod/models/item/fluorite_chestplate_emerald_trim.json
dda52a512056adfe4358151caa48ec0fb83aa9d9 assets/tutorialmod/models/item/fluorite_chestplate_gold_trim.json
d651eae3fe30b36c56250609de6357e6228fe10c assets/tutorialmod/models/item/fluorite_chestplate_iron_trim.json
64c9200f45b0be1e52829ed7ef79bdd87763d62b assets/tutorialmod/models/item/fluorite_chestplate_lapis_trim.json
02b537ea4ea4a583bdc103ef3beb8886124527d9 assets/tutorialmod/models/item/fluorite_chestplate_netherite_trim.json
8f638b3f2406d05055799c41361c465755a69b10 assets/tutorialmod/models/item/fluorite_chestplate_quartz_trim.json
7d869e2143daf73c5e0d0e0e31d3205a3b6f6082 assets/tutorialmod/models/item/fluorite_chestplate_redstone_trim.json
1a5734ff3a381496bbcfecf5f2b5caf7782a8231 assets/tutorialmod/models/item/fluorite_chestplate_resin_trim.json
65397e7fa839c6e9746f06dc5068e408315169f7 assets/tutorialmod/models/item/fluorite_door.json
612717cca99ecb510f79f2426cd32aa5c706f58a assets/tutorialmod/models/item/fluorite_helmet.json
42dcc2c2bb54a1d1fc0e71543da1ad940539e10a assets/tutorialmod/models/item/fluorite_helmet_amethyst_trim.json
359b2973c1957e78cc8e7e806db78ac4cea9a2b7 assets/tutorialmod/models/item/fluorite_helmet_copper_trim.json
1aed73dd3d343ab75448b7c721cc286f3e7ece07 assets/tutorialmod/models/item/fluorite_helmet_diamond_trim.json
2e12765a5f5d69aaaa612fabc99db5893da18750 assets/tutorialmod/models/item/fluorite_helmet_emerald_trim.json
2d03d3441b9ec7c8bb3a1da21ac3172a84777afe assets/tutorialmod/models/item/fluorite_helmet_gold_trim.json
7d01aed8c6cb9d5576cf887e919dbdfcbd3e791d assets/tutorialmod/models/item/fluorite_helmet_iron_trim.json
fcd468d77bd4a41be2ce5bfcf67c7083a202744b assets/tutorialmod/models/item/fluorite_helmet_lapis_trim.json
d601c42560f19ed16a9d01a987fb4112d1b6c247 assets/tutorialmod/models/item/fluorite_helmet_netherite_trim.json
ca62353aada7306919ac6174dec8066e6e13ffbe assets/tutorialmod/models/item/fluorite_helmet_quartz_trim.json
78b9292818230aa737091a73fe6cb1f1c7985290 assets/tutorialmod/models/item/fluorite_helmet_redstone_trim.json
ccad94d69eb4e50551dd10dbf1ad427a30b29af6 assets/tutorialmod/models/item/fluorite_helmet_resin_trim.json
9619c77959a24cfa45318d7c384a4fc0ebbca5f2 assets/tutorialmod/models/item/fluorite_hoe.json
0323707305271646cf6bd94eaf86b8c5d131dd59 assets/tutorialmod/models/item/fluorite_horse_armor.json
2a5fadea396a5dd76a2c8cbbe408ae787aa42da7 assets/tutorialmod/models/item/fluorite_leggings.json
6d178bc079ae62122f37c57c1d0df44932395315 assets/tutorialmod/models/item/fluorite_leggings_amethyst_trim.json
8de768d4f3a7c86ce84eb1e2277287710b6da307 assets/tutorialmod/models/item/fluorite_leggings_copper_trim.json
856f3cbe81480cd0de03cc1580abb4b4693d7137 assets/tutorialmod/models/item/fluorite_leggings_diamond_trim.json
6bb57a0a064dde32df8d3d386e7702b31c1f8875 assets/tutorialmod/models/item/fluorite_leggings_emerald_trim.json
c1e5fa47aa0f88b243e7e57799e86d46760acfa1 assets/tutorialmod/models/item/fluorite_leggings_gold_trim.json
7ae71b4ac88f8d77e7d5c6e0a4f06f86b0993365 assets/tutorialmod/models/item/fluorite_leggings_iron_trim.json
98cc57cfe89ff0b5146f28ac40433839d888d87d assets/tutorialmod/models/item/fluorite_leggings_lapis_trim.json
e2d0e4bfb47cc58803b50ea0e7eef5c28b9e6bd1 assets/tutorialmod/models/item/fluorite_leggings_netherite_trim.json
fdf89d1ba9968b0ad59dcabfe0be8455ad8dd8f4 assets/tutorialmod/models/item/fluorite_leggings_quartz_trim.json
204f2ac9e029e76c80a5003e6dec6af25b9b3b10 assets/tutorialmod/models/item/fluorite_leggings_redstone_trim.json
c15b1c9d085125b1cceace13e06010744e86f9eb assets/tutorialmod/models/item/fluorite_leggings_resin_trim.json
3af970bee00cc6e89efc4c09a60fdc425c1b7e91 assets/tutorialmod/models/item/fluorite_pickaxe.json
3c7656dce51b3d594635f204aa9edf91e4080f6b assets/tutorialmod/models/item/fluorite_shovel.json
bc8b1dafc65347754dedd676583419f831775423 assets/tutorialmod/models/item/fluorite_spear.json
468e27d6fdeb66aeddfd231c6f7f2708e5106a37 assets/tutorialmod/models/item/fluorite_spear_in_hand.json
af55229ae4229b1cf495e191ab33ea29d87c070b assets/tutorialmod/models/item/fluorite_sword.json
b4d76c98e3f66c7f9fe3667a7292ab19b617b44a assets/tutorialmod/models/item/honey_berries.json
536f7fa5c568b2f50b0994e1077e3995ae6e67d7 assets/tutorialmod/models/item/kaupen_bow.json
ff12dbc81cdd4a09005664f28404ea71b34d4fca assets/tutorialmod/models/item/kaupen_bow_pulling_0.json
c8063795f1ea5410d6d5a5b9172a1ca3895cb588 assets/tutorialmod/models/item/kaupen_bow_pulling_1.json
1e83638c78991804f9d67d11957969d099fbdb10 assets/tutorialmod/models/item/kaupen_bow_pulling_2.json
86543c2d342d40575f7a48ebccdf047d274127b8 assets/tutorialmod/models/item/raw_fluorite.json
6786a3499fa4202c7b8d2698e02c01e73728c703 assets/tutorialmod/models/item/rice_shoot.json
fa5fcb2abfbef4ec0725adf393d27c38dc99be4c assets/tutorialmod/models/item/spectre_staff.json
0f4c6f5df2121954f3a0de1eb8e66d8c587fed43 assets/tutorialmod/models/item/strawberry.json
85dffe5c3e34ee76908efb087a4ed2c43e945942 assets/tutorialmod/models/item/strawberry_seeds.json
```

### src/main/generated/.cache/ce39c20969e010cd0348db228ef39b23c54b9ea7

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/TutorialMod Recipes
c05e432247d3fdec6f58a57a3c3b4271b582f3f5 data/tutorialmod/advancement/recipes/building_blocks/balsa_planks.json
f6ec1c9ad34db3b98328f74486cc682a56e4bba8 data/tutorialmod/advancement/recipes/building_blocks/balsa_wood.json
83aeb58d4051ad5dacd9819fd367429c84acb881 data/tutorialmod/advancement/recipes/building_blocks/fluorite_block.json
bce372fe5e79dd264759037d94b6e87a0c1010d1 data/tutorialmod/advancement/recipes/building_blocks/fluorite_slab.json
2aadd5727cf2f34d4600840c75d10aea983c8032 data/tutorialmod/advancement/recipes/building_blocks/fluorite_stairs.json
bbada394981ddda504822557f10d950049096595 data/tutorialmod/advancement/recipes/building_blocks/fluorite_wall.json
5f41da392e436bc6273e993181d6fc9a0615aab8 data/tutorialmod/advancement/recipes/building_blocks/stripped_balsa_wood.json
73a8875095653a7c0110c6c8cbec6ef1e8a48459 data/tutorialmod/advancement/recipes/combat/fluorite_boots.json
70db84c7466433976ef670218871ea4bbca9f0eb data/tutorialmod/advancement/recipes/combat/fluorite_chestplate.json
32ddc3f78d88dad2788bd0d38576e9df7a220cfc data/tutorialmod/advancement/recipes/combat/fluorite_helmet.json
ffcbe79953c4dc2081911a824d4cdad4e306d02f data/tutorialmod/advancement/recipes/combat/fluorite_leggings.json
c8c8996762acc8d97e168255e1121644c9074e86 data/tutorialmod/advancement/recipes/combat/fluorite_spear.json
307f13280698361169e01dcf0a1ec42ee3cc1f99 data/tutorialmod/advancement/recipes/combat/fluorite_sword.json
d7a70b491d745a8d62e778939f8565305d8f0f67 data/tutorialmod/advancement/recipes/decorations/fluorite_fence.json
9ba25f0beba256c9723f3e032304b129a331b3f6 data/tutorialmod/advancement/recipes/misc/bar_brawl_music_disc_from_crystallizing.json
62400d04b0b4e5902d3e9a599b3cc286f0fd761f data/tutorialmod/advancement/recipes/misc/end_rod_from_crystallizing.json
5ea34685d2fd40955a8f8b4dacfe6e95419dc5f5 data/tutorialmod/advancement/recipes/misc/fluorite.json
8ef09ad379a2c639bb7837ea3662f495b20de56c data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_fluorite_deepslate_ore.json
ba66041a8214b176cd7130fb8bd255019c3b5428 data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_fluorite_end_ore.json
fe2e5de1e63ce387daf67d743f216f8546cb5226 data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_fluorite_nether_ore.json
37a5a626bebfcf4e90d52d0acdb339c4d0c23f0f data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_fluorite_ore.json
8b05dda2541ae73beb15b39b881eb977a15c8735 data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_raw_fluorite.json
e9e2ac83f358b7e25a5a2f1a9a9c6a1e0d52640f data/tutorialmod/advancement/recipes/misc/fluorite_from_crystallizing.json
3f670e9dd4f598cfa8d82481e1e123fa3ba8e6c4 data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_fluorite_deepslate_ore.json
d2111c8d1ad374d9b7d9090f9912da77a7c211af data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_fluorite_end_ore.json
859df8900d2ea9c4ed69e1d38f74aab7c97a30aa data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_fluorite_nether_ore.json
8bd5c2b14d9117dfa148d78d416c778c9eb50596 data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_fluorite_ore.json
496cc0940c9b6f6231f2d1b765be9beb419e65fe data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_raw_fluorite.json
52e1e5ced1c520af763cc85879df4534a73b1ff2 data/tutorialmod/advancement/recipes/misc/nether_star_from_crystallizing.json
aa3b4344255bf9a17746a277818fca3d92442f26 data/tutorialmod/advancement/recipes/misc/raw_fluorite.json
efe5e49a64d934618f2f4f393817840d4d36d46a data/tutorialmod/advancement/recipes/misc/raw_fluorite_block.json
31bfda9d7059d0072b9b093d43288bc961332f65 data/tutorialmod/advancement/recipes/misc/raw_fluorite_from_fluorite_and_stick.json
7183d1d7c40ced9c6ad697251802a54d901abe42 data/tutorialmod/advancement/recipes/misc/rice_shoot_from_crystallizing.json
402091147394d0789dc96be3c835f04e51aba35e data/tutorialmod/advancement/recipes/redstone/fluorite_button.json
2a76dec37c53a24f1ccca02a5aea04bf163a56b3 data/tutorialmod/advancement/recipes/redstone/fluorite_door.json
f313900e4b3aa3e18bd5d860f09863e72962518b data/tutorialmod/advancement/recipes/redstone/fluorite_fence_gate.json
a599f1e2478c469cde0e9ecf3bf20be06821831d data/tutorialmod/advancement/recipes/redstone/fluorite_pressure_plate.json
c3babd59b1320c24a8d1cffb839eccb0424e8a78 data/tutorialmod/advancement/recipes/redstone/fluorite_trapdoor.json
0948481c597a22e46c2440c8a8ff9b0cac11be05 data/tutorialmod/advancement/recipes/tools/fluorite_axe.json
1b16bc1b910c9824cc18871a593c5433f09bb74e data/tutorialmod/advancement/recipes/tools/fluorite_hoe.json
1e3d39a915957d14b3d11df6e2c569ad25124623 data/tutorialmod/advancement/recipes/tools/fluorite_pickaxe.json
754bea90e1974349e833125e5068d7889cf9f3fe data/tutorialmod/advancement/recipes/tools/fluorite_shovel.json
b2397dc9e1281ec0defc8caca93da04ec8a0db8a data/tutorialmod/recipe/balsa_planks.json
1bacd36ef0d54dbaecc24c1438a626f6caca4d09 data/tutorialmod/recipe/balsa_wood.json
9229d2b13e74c9f2c95719d51feaac6386710219 data/tutorialmod/recipe/bar_brawl_music_disc_from_crystallizing.json
1b617ad581398d9cdeb09463be8a0dbfdf22a590 data/tutorialmod/recipe/end_rod_from_crystallizing.json
a0952adeb2bb18df70bb22bc6dd0c14d3630e3ee data/tutorialmod/recipe/fluorite.json
da2348a8ca52aaa3f8edf148199fc020752998bf data/tutorialmod/recipe/fluorite_axe.json
ca64af5317b667c1838a62e79605bb911b960888 data/tutorialmod/recipe/fluorite_block.json
7ac5607d39563783cecc7280239ee8ede5c2c103 data/tutorialmod/recipe/fluorite_boots.json
f6cdf6b842229e0482b96ecbb981030ec2b10df0 data/tutorialmod/recipe/fluorite_button.json
8c2c867b1a4e4e6b7eed242a5c95764fe184ce80 data/tutorialmod/recipe/fluorite_chestplate.json
4544311c9148b6af8bfe912da16eecf1aae8dd75 data/tutorialmod/recipe/fluorite_door.json
c975787fc2f9370cd11224fa92f6f825f4b19e3f data/tutorialmod/recipe/fluorite_fence.json
ba8fc2cbfc5364d3dd1741a13dea9c8bfbab0a8b data/tutorialmod/recipe/fluorite_fence_gate.json
ec1eb91e2813f63a71cab33f302f0e41eba49deb data/tutorialmod/recipe/fluorite_from_blasting_fluorite_deepslate_ore.json
181d7a4e7e0256da2be272cf363996fddf3a96f3 data/tutorialmod/recipe/fluorite_from_blasting_fluorite_end_ore.json
c98b0b24496af208c935ff73047e0809b0f73307 data/tutorialmod/recipe/fluorite_from_blasting_fluorite_nether_ore.json
9f9ac43e9ca5bdb41f942ec19f77de6c102e4af1 data/tutorialmod/recipe/fluorite_from_blasting_fluorite_ore.json
a879673ec6da51c4ca507880b36fc576699f6081 data/tutorialmod/recipe/fluorite_from_blasting_raw_fluorite.json
92f899913ce7af465786398eae3c284ecdade652 data/tutorialmod/recipe/fluorite_from_crystallizing.json
7969def2de6d03ff9ec37aafb4223c86b0cdc2c0 data/tutorialmod/recipe/fluorite_from_smelting_fluorite_deepslate_ore.json
e197956f7f36f323a420092db8f99ae62ae1a1d6 data/tutorialmod/recipe/fluorite_from_smelting_fluorite_end_ore.json
b63eb544b40467fd1be559c8214eaf3948dcb365 data/tutorialmod/recipe/fluorite_from_smelting_fluorite_nether_ore.json
269442e9855eb45578ba7c42de1f08bdfb178274 data/tutorialmod/recipe/fluorite_from_smelting_fluorite_ore.json
4939217d7e904d5a535a880588c90b2c29b832bf data/tutorialmod/recipe/fluorite_from_smelting_raw_fluorite.json
d81b42218548e8e27d4f30f2358b472a433468f0 data/tutorialmod/recipe/fluorite_helmet.json
e33adb4e3b11c4d37a167886abcf0febb3f91eb4 data/tutorialmod/recipe/fluorite_hoe.json
813ff3e808a6bb3a544d6b6069d05f65c9674da5 data/tutorialmod/recipe/fluorite_leggings.json
a1ac894fadf5e2aa44f027890b1de71bd08d7ea5 data/tutorialmod/recipe/fluorite_pickaxe.json
00dc261f727745815ccc916a6dc42ce816b50b28 data/tutorialmod/recipe/fluorite_pressure_plate.json
5e93c8515a8b0f5f95f09b7b5050001d6437a10e data/tutorialmod/recipe/fluorite_shovel.json
3dbf9c2f41d589e30300c3a2d2ccd7a27223ee8c data/tutorialmod/recipe/fluorite_slab.json
f87dc2e0aa205260aa2f696e6639e258eb93a09a data/tutorialmod/recipe/fluorite_spear.json
7d4e3b7f61e9218667053531222902a1f4659798 data/tutorialmod/recipe/fluorite_stairs.json
26ab825c09303222adda49b3eb1206f263010f27 data/tutorialmod/recipe/fluorite_sword.json
59892caf58b4a6c3d4d107a194daae05abdfdf67 data/tutorialmod/recipe/fluorite_trapdoor.json
af024d2663157cbd35637591247ad0355a354af8 data/tutorialmod/recipe/fluorite_wall.json
c2f3243ee7d75cc27b0b8bce1af64f7f462242b5 data/tutorialmod/recipe/nether_star_from_crystallizing.json
240cd965f0bebec842ce58a232d8ffa4e24fdfc6 data/tutorialmod/recipe/raw_fluorite.json
d5442f8ee99254e3da1b9c0d1fda6ef889820025 data/tutorialmod/recipe/raw_fluorite_block.json
5c903bab5e5432ba33ba490488ed7525e104276a data/tutorialmod/recipe/raw_fluorite_from_fluorite_and_stick.json
c9f6bff73b52ec7841f336212079ae659b33f498 data/tutorialmod/recipe/rice_shoot_from_crystallizing.json
776f8a11f7b4522abfd54887e1dc647be52ffbdd data/tutorialmod/recipe/stripped_balsa_wood.json
```

### src/main/generated/.cache/e4e32e775410155fd5b39910e36714f200d58a95

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/Tags for minecraft:point_of_interest_type
b956f7bc28e1869ce7380d678af03352929a22f1 data/minecraft/tags/point_of_interest_type/acquirable_job_site.json
```

### src/main/generated/.cache/f3f56cafc2cbe41709406bb540eb1fca38a90f18

```
// 26.2	-999999999-01-01T00:00:00	Tutorial Mod/Tags for minecraft:item
9d7e20278499ad8c61d28514ea379378d0f650f6 data/minecraft/tags/item/axes.json
00df68ad445025fb72f90523a34c216c02913aa6 data/minecraft/tags/item/chest_armor.json
526ec127a145db02975e7ec564384fa0a5389523 data/minecraft/tags/item/creeper_drop_music_discs.json
7d94ac012f7023ba65ba42ee0f94d70164cd437f data/minecraft/tags/item/enchantable/bow.json
53cb3c6d8a362ae4f40babcc8793aac2ba15d59e data/minecraft/tags/item/foot_armor.json
b6cac0199f04efaab89eef33f16228f9ff8b196a data/minecraft/tags/item/head_armor.json
b3da0597552a7e770662ca131e0c3ef28f95b596 data/minecraft/tags/item/hoes.json
d3ce8e7370e94ff508085e40482ed386dbd29f1c data/minecraft/tags/item/leaves.json
fbacf4c35ba8ec6403ef2347135efc7f9bdb41a3 data/minecraft/tags/item/leg_armor.json
55d826edf66720ab21b70fe688cb21d598b1c489 data/minecraft/tags/item/logs_that_burn.json
8b79806d1e1d1bff7cf9fb74a9229d5cc3ecad30 data/minecraft/tags/item/pickaxes.json
5c6708099b2a23842920dfe05ef23a5aeb5b2370 data/minecraft/tags/item/planks.json
59bf58fc1965b16f7f19c27255762dfe347bd3ef data/minecraft/tags/item/saplings.json
d757f88c8dab61f8df078fa381dcb263152fd30d data/minecraft/tags/item/shovels.json
600dfb1844085bb1c832154b4e17b2f11dc5a2a6 data/minecraft/tags/item/spears.json
60e1fcd86b0d0a05a5494e406de9fba133e6ace7 data/minecraft/tags/item/swords.json
d8b534b7d592190656e55bb670f6120fb559188b data/tutorialmod/tags/item/balsa_logs.json
021a6231d4588a3f4d5ff725464f03ddfed5fdf5 data/tutorialmod/tags/item/transformable_items.json
```

### src/main/generated/assets/tutorialmod/blockstates/balsa_leaves.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/balsa_leaves"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/balsa_log.json

```json
{
  "variants": {
    "axis=x": {
      "model": "tutorialmod:block/balsa_log",
      "x": 90,
      "y": 90
    },
    "axis=y": {
      "model": "tutorialmod:block/balsa_log"
    },
    "axis=z": {
      "model": "tutorialmod:block/balsa_log",
      "x": 90
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/balsa_planks.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/balsa_planks"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/balsa_sapling.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/balsa_sapling"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/balsa_wood.json

```json
{
  "variants": {
    "axis=x": {
      "model": "tutorialmod:block/balsa_wood",
      "x": 90,
      "y": 90
    },
    "axis=y": {
      "model": "tutorialmod:block/balsa_wood"
    },
    "axis=z": {
      "model": "tutorialmod:block/balsa_wood",
      "x": 90
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/crystallizer.json

```json
{
  "variants": {
    "facing=east,lit=false": {
      "model": "tutorialmod:block/crystallizer",
      "y": 90
    },
    "facing=east,lit=true": {
      "model": "tutorialmod:block/crystallizer_on",
      "y": 90
    },
    "facing=north,lit=false": {
      "model": "tutorialmod:block/crystallizer"
    },
    "facing=north,lit=true": {
      "model": "tutorialmod:block/crystallizer_on"
    },
    "facing=south,lit=false": {
      "model": "tutorialmod:block/crystallizer",
      "y": 180
    },
    "facing=south,lit=true": {
      "model": "tutorialmod:block/crystallizer_on",
      "y": 180
    },
    "facing=west,lit=false": {
      "model": "tutorialmod:block/crystallizer",
      "y": 270
    },
    "facing=west,lit=true": {
      "model": "tutorialmod:block/crystallizer_on",
      "y": 270
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_block.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/fluorite_block"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_button.json

```json
{
  "variants": {
    "face=ceiling,facing=east,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "x": 180,
      "y": 270
    },
    "face=ceiling,facing=east,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "x": 180,
      "y": 270
    },
    "face=ceiling,facing=north,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "x": 180,
      "y": 180
    },
    "face=ceiling,facing=north,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "x": 180,
      "y": 180
    },
    "face=ceiling,facing=south,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "x": 180
    },
    "face=ceiling,facing=south,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "x": 180
    },
    "face=ceiling,facing=west,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "x": 180,
      "y": 90
    },
    "face=ceiling,facing=west,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "x": 180,
      "y": 90
    },
    "face=floor,facing=east,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "y": 90
    },
    "face=floor,facing=east,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "y": 90
    },
    "face=floor,facing=north,powered=false": {
      "model": "tutorialmod:block/fluorite_button"
    },
    "face=floor,facing=north,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed"
    },
    "face=floor,facing=south,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "y": 180
    },
    "face=floor,facing=south,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "y": 180
    },
    "face=floor,facing=west,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "y": 270
    },
    "face=floor,facing=west,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "y": 270
    },
    "face=wall,facing=east,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "uvlock": true,
      "x": 90,
      "y": 90
    },
    "face=wall,facing=east,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "uvlock": true,
      "x": 90,
      "y": 90
    },
    "face=wall,facing=north,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "uvlock": true,
      "x": 90
    },
    "face=wall,facing=north,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "uvlock": true,
      "x": 90
    },
    "face=wall,facing=south,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "uvlock": true,
      "x": 90,
      "y": 180
    },
    "face=wall,facing=south,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "uvlock": true,
      "x": 90,
      "y": 180
    },
    "face=wall,facing=west,powered=false": {
      "model": "tutorialmod:block/fluorite_button",
      "uvlock": true,
      "x": 90,
      "y": 270
    },
    "face=wall,facing=west,powered=true": {
      "model": "tutorialmod:block/fluorite_button_pressed",
      "uvlock": true,
      "x": 90,
      "y": 270
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_deepslate_ore.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/fluorite_deepslate_ore"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_door.json

```json
{
  "variants": {
    "facing=east,half=lower,hinge=left,open=false": {
      "model": "tutorialmod:block/fluorite_door_bottom_left"
    },
    "facing=east,half=lower,hinge=left,open=true": {
      "model": "tutorialmod:block/fluorite_door_bottom_left_open",
      "y": 90
    },
    "facing=east,half=lower,hinge=right,open=false": {
      "model": "tutorialmod:block/fluorite_door_bottom_right"
    },
    "facing=east,half=lower,hinge=right,open=true": {
      "model": "tutorialmod:block/fluorite_door_bottom_right_open",
      "y": 270
    },
    "facing=east,half=upper,hinge=left,open=false": {
      "model": "tutorialmod:block/fluorite_door_top_left"
    },
    "facing=east,half=upper,hinge=left,open=true": {
      "model": "tutorialmod:block/fluorite_door_top_left_open",
      "y": 90
    },
    "facing=east,half=upper,hinge=right,open=false": {
      "model": "tutorialmod:block/fluorite_door_top_right"
    },
    "facing=east,half=upper,hinge=right,open=true": {
      "model": "tutorialmod:block/fluorite_door_top_right_open",
      "y": 270
    },
    "facing=north,half=lower,hinge=left,open=false": {
      "model": "tutorialmod:block/fluorite_door_bottom_left",
      "y": 270
    },
    "facing=north,half=lower,hinge=left,open=true": {
      "model": "tutorialmod:block/fluorite_door_bottom_left_open"
    },
    "facing=north,half=lower,hinge=right,open=false": {
      "model": "tutorialmod:block/fluorite_door_bottom_right",
      "y": 270
    },
    "facing=north,half=lower,hinge=right,open=true": {
      "model": "tutorialmod:block/fluorite_door_bottom_right_open",
      "y": 180
    },
    "facing=north,half=upper,hinge=left,open=false": {
      "model": "tutorialmod:block/fluorite_door_top_left",
      "y": 270
    },
    "facing=north,half=upper,hinge=left,open=true": {
      "model": "tutorialmod:block/fluorite_door_top_left_open"
    },
    "facing=north,half=upper,hinge=right,open=false": {
      "model": "tutorialmod:block/fluorite_door_top_right",
      "y": 270
    },
    "facing=north,half=upper,hinge=right,open=true": {
      "model": "tutorialmod:block/fluorite_door_top_right_open",
      "y": 180
    },
    "facing=south,half=lower,hinge=left,open=false": {
      "model": "tutorialmod:block/fluorite_door_bottom_left",
      "y": 90
    },
    "facing=south,half=lower,hinge=left,open=true": {
      "model": "tutorialmod:block/fluorite_door_bottom_left_open",
      "y": 180
    },
    "facing=south,half=lower,hinge=right,open=false": {
      "model": "tutorialmod:block/fluorite_door_bottom_right",
      "y": 90
    },
    "facing=south,half=lower,hinge=right,open=true": {
      "model": "tutorialmod:block/fluorite_door_bottom_right_open"
    },
    "facing=south,half=upper,hinge=left,open=false": {
      "model": "tutorialmod:block/fluorite_door_top_left",
      "y": 90
    },
    "facing=south,half=upper,hinge=left,open=true": {
      "model": "tutorialmod:block/fluorite_door_top_left_open",
      "y": 180
    },
    "facing=south,half=upper,hinge=right,open=false": {
      "model": "tutorialmod:block/fluorite_door_top_right",
      "y": 90
    },
    "facing=south,half=upper,hinge=right,open=true": {
      "model": "tutorialmod:block/fluorite_door_top_right_open"
    },
    "facing=west,half=lower,hinge=left,open=false": {
      "model": "tutorialmod:block/fluorite_door_bottom_left",
      "y": 180
    },
    "facing=west,half=lower,hinge=left,open=true": {
      "model": "tutorialmod:block/fluorite_door_bottom_left_open",
      "y": 270
    },
    "facing=west,half=lower,hinge=right,open=false": {
      "model": "tutorialmod:block/fluorite_door_bottom_right",
      "y": 180
    },
    "facing=west,half=lower,hinge=right,open=true": {
      "model": "tutorialmod:block/fluorite_door_bottom_right_open",
      "y": 90
    },
    "facing=west,half=upper,hinge=left,open=false": {
      "model": "tutorialmod:block/fluorite_door_top_left",
      "y": 180
    },
    "facing=west,half=upper,hinge=left,open=true": {
      "model": "tutorialmod:block/fluorite_door_top_left_open",
      "y": 270
    },
    "facing=west,half=upper,hinge=right,open=false": {
      "model": "tutorialmod:block/fluorite_door_top_right",
      "y": 180
    },
    "facing=west,half=upper,hinge=right,open=true": {
      "model": "tutorialmod:block/fluorite_door_top_right_open",
      "y": 90
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_end_ore.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/fluorite_end_ore"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_fence.json

```json
{
  "multipart": [
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_fence_post"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_fence_side",
        "uvlock": true
      },
      "when": {
        "north": "true"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_fence_side",
        "uvlock": true,
        "y": 90
      },
      "when": {
        "east": "true"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_fence_side",
        "uvlock": true,
        "y": 180
      },
      "when": {
        "south": "true"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_fence_side",
        "uvlock": true,
        "y": 270
      },
      "when": {
        "west": "true"
      }
    }
  ]
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_fence_gate.json

```json
{
  "variants": {
    "facing=east,in_wall=false,open=false": {
      "model": "tutorialmod:block/fluorite_fence_gate",
      "uvlock": true,
      "y": 270
    },
    "facing=east,in_wall=false,open=true": {
      "model": "tutorialmod:block/fluorite_fence_gate_open",
      "uvlock": true,
      "y": 270
    },
    "facing=east,in_wall=true,open=false": {
      "model": "tutorialmod:block/fluorite_fence_gate_wall",
      "uvlock": true,
      "y": 270
    },
    "facing=east,in_wall=true,open=true": {
      "model": "tutorialmod:block/fluorite_fence_gate_wall_open",
      "uvlock": true,
      "y": 270
    },
    "facing=north,in_wall=false,open=false": {
      "model": "tutorialmod:block/fluorite_fence_gate",
      "uvlock": true,
      "y": 180
    },
    "facing=north,in_wall=false,open=true": {
      "model": "tutorialmod:block/fluorite_fence_gate_open",
      "uvlock": true,
      "y": 180
    },
    "facing=north,in_wall=true,open=false": {
      "model": "tutorialmod:block/fluorite_fence_gate_wall",
      "uvlock": true,
      "y": 180
    },
    "facing=north,in_wall=true,open=true": {
      "model": "tutorialmod:block/fluorite_fence_gate_wall_open",
      "uvlock": true,
      "y": 180
    },
    "facing=south,in_wall=false,open=false": {
      "model": "tutorialmod:block/fluorite_fence_gate",
      "uvlock": true
    },
    "facing=south,in_wall=false,open=true": {
      "model": "tutorialmod:block/fluorite_fence_gate_open",
      "uvlock": true
    },
    "facing=south,in_wall=true,open=false": {
      "model": "tutorialmod:block/fluorite_fence_gate_wall",
      "uvlock": true
    },
    "facing=south,in_wall=true,open=true": {
      "model": "tutorialmod:block/fluorite_fence_gate_wall_open",
      "uvlock": true
    },
    "facing=west,in_wall=false,open=false": {
      "model": "tutorialmod:block/fluorite_fence_gate",
      "uvlock": true,
      "y": 90
    },
    "facing=west,in_wall=false,open=true": {
      "model": "tutorialmod:block/fluorite_fence_gate_open",
      "uvlock": true,
      "y": 90
    },
    "facing=west,in_wall=true,open=false": {
      "model": "tutorialmod:block/fluorite_fence_gate_wall",
      "uvlock": true,
      "y": 90
    },
    "facing=west,in_wall=true,open=true": {
      "model": "tutorialmod:block/fluorite_fence_gate_wall_open",
      "uvlock": true,
      "y": 90
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_lamp.json

```json
{
  "variants": {
    "clicked=false": {
      "model": "tutorialmod:block/fluorite_lamp"
    },
    "clicked=true": {
      "model": "tutorialmod:block/fluorite_lamp_on"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_nether_ore.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/fluorite_nether_ore"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_ore.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/fluorite_ore"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_pressure_plate.json

```json
{
  "variants": {
    "powered=false": {
      "model": "tutorialmod:block/fluorite_pressure_plate"
    },
    "powered=true": {
      "model": "tutorialmod:block/fluorite_pressure_plate_down"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_slab.json

```json
{
  "variants": {
    "type=bottom": {
      "model": "tutorialmod:block/fluorite_slab"
    },
    "type=double": {
      "model": "tutorialmod:block/fluorite_block"
    },
    "type=top": {
      "model": "tutorialmod:block/fluorite_slab_top"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_stairs.json

```json
{
  "variants": {
    "facing=east,half=bottom,shape=inner_left": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "y": 270
    },
    "facing=east,half=bottom,shape=inner_right": {
      "model": "tutorialmod:block/fluorite_stairs_inner"
    },
    "facing=east,half=bottom,shape=outer_left": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "y": 270
    },
    "facing=east,half=bottom,shape=outer_right": {
      "model": "tutorialmod:block/fluorite_stairs_outer"
    },
    "facing=east,half=bottom,shape=straight": {
      "model": "tutorialmod:block/fluorite_stairs"
    },
    "facing=east,half=top,shape=inner_left": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "x": 180
    },
    "facing=east,half=top,shape=inner_right": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=east,half=top,shape=outer_left": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "x": 180
    },
    "facing=east,half=top,shape=outer_right": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=east,half=top,shape=straight": {
      "model": "tutorialmod:block/fluorite_stairs",
      "uvlock": true,
      "x": 180
    },
    "facing=north,half=bottom,shape=inner_left": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "y": 180
    },
    "facing=north,half=bottom,shape=inner_right": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "y": 270
    },
    "facing=north,half=bottom,shape=outer_left": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "y": 180
    },
    "facing=north,half=bottom,shape=outer_right": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "y": 270
    },
    "facing=north,half=bottom,shape=straight": {
      "model": "tutorialmod:block/fluorite_stairs",
      "uvlock": true,
      "y": 270
    },
    "facing=north,half=top,shape=inner_left": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=north,half=top,shape=inner_right": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "x": 180
    },
    "facing=north,half=top,shape=outer_left": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=north,half=top,shape=outer_right": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "x": 180
    },
    "facing=north,half=top,shape=straight": {
      "model": "tutorialmod:block/fluorite_stairs",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=south,half=bottom,shape=inner_left": {
      "model": "tutorialmod:block/fluorite_stairs_inner"
    },
    "facing=south,half=bottom,shape=inner_right": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "y": 90
    },
    "facing=south,half=bottom,shape=outer_left": {
      "model": "tutorialmod:block/fluorite_stairs_outer"
    },
    "facing=south,half=bottom,shape=outer_right": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "y": 90
    },
    "facing=south,half=bottom,shape=straight": {
      "model": "tutorialmod:block/fluorite_stairs",
      "uvlock": true,
      "y": 90
    },
    "facing=south,half=top,shape=inner_left": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=south,half=top,shape=inner_right": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "x": 180,
      "y": 180
    },
    "facing=south,half=top,shape=outer_left": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=south,half=top,shape=outer_right": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "x": 180,
      "y": 180
    },
    "facing=south,half=top,shape=straight": {
      "model": "tutorialmod:block/fluorite_stairs",
      "uvlock": true,
      "x": 180,
      "y": 90
    },
    "facing=west,half=bottom,shape=inner_left": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "y": 90
    },
    "facing=west,half=bottom,shape=inner_right": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "y": 180
    },
    "facing=west,half=bottom,shape=outer_left": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "y": 90
    },
    "facing=west,half=bottom,shape=outer_right": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "y": 180
    },
    "facing=west,half=bottom,shape=straight": {
      "model": "tutorialmod:block/fluorite_stairs",
      "uvlock": true,
      "y": 180
    },
    "facing=west,half=top,shape=inner_left": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "x": 180,
      "y": 180
    },
    "facing=west,half=top,shape=inner_right": {
      "model": "tutorialmod:block/fluorite_stairs_inner",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=west,half=top,shape=outer_left": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "x": 180,
      "y": 180
    },
    "facing=west,half=top,shape=outer_right": {
      "model": "tutorialmod:block/fluorite_stairs_outer",
      "uvlock": true,
      "x": 180,
      "y": 270
    },
    "facing=west,half=top,shape=straight": {
      "model": "tutorialmod:block/fluorite_stairs",
      "uvlock": true,
      "x": 180,
      "y": 180
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_trapdoor.json

```json
{
  "variants": {
    "facing=east,half=bottom,open=false": {
      "model": "tutorialmod:block/fluorite_trapdoor_bottom"
    },
    "facing=east,half=bottom,open=true": {
      "model": "tutorialmod:block/fluorite_trapdoor_open",
      "y": 90
    },
    "facing=east,half=top,open=false": {
      "model": "tutorialmod:block/fluorite_trapdoor_top"
    },
    "facing=east,half=top,open=true": {
      "model": "tutorialmod:block/fluorite_trapdoor_open",
      "y": 90
    },
    "facing=north,half=bottom,open=false": {
      "model": "tutorialmod:block/fluorite_trapdoor_bottom"
    },
    "facing=north,half=bottom,open=true": {
      "model": "tutorialmod:block/fluorite_trapdoor_open"
    },
    "facing=north,half=top,open=false": {
      "model": "tutorialmod:block/fluorite_trapdoor_top"
    },
    "facing=north,half=top,open=true": {
      "model": "tutorialmod:block/fluorite_trapdoor_open"
    },
    "facing=south,half=bottom,open=false": {
      "model": "tutorialmod:block/fluorite_trapdoor_bottom"
    },
    "facing=south,half=bottom,open=true": {
      "model": "tutorialmod:block/fluorite_trapdoor_open",
      "y": 180
    },
    "facing=south,half=top,open=false": {
      "model": "tutorialmod:block/fluorite_trapdoor_top"
    },
    "facing=south,half=top,open=true": {
      "model": "tutorialmod:block/fluorite_trapdoor_open",
      "y": 180
    },
    "facing=west,half=bottom,open=false": {
      "model": "tutorialmod:block/fluorite_trapdoor_bottom"
    },
    "facing=west,half=bottom,open=true": {
      "model": "tutorialmod:block/fluorite_trapdoor_open",
      "y": 270
    },
    "facing=west,half=top,open=false": {
      "model": "tutorialmod:block/fluorite_trapdoor_top"
    },
    "facing=west,half=top,open=true": {
      "model": "tutorialmod:block/fluorite_trapdoor_open",
      "y": 270
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/fluorite_wall.json

```json
{
  "multipart": [
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_wall_post"
      },
      "when": {
        "up": "true"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_wall_side",
        "uvlock": true
      },
      "when": {
        "north": "low"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_wall_side",
        "uvlock": true,
        "y": 90
      },
      "when": {
        "east": "low"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_wall_side",
        "uvlock": true,
        "y": 180
      },
      "when": {
        "south": "low"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_wall_side",
        "uvlock": true,
        "y": 270
      },
      "when": {
        "west": "low"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_wall_side_tall",
        "uvlock": true
      },
      "when": {
        "north": "tall"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_wall_side_tall",
        "uvlock": true,
        "y": 90
      },
      "when": {
        "east": "tall"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_wall_side_tall",
        "uvlock": true,
        "y": 180
      },
      "when": {
        "south": "tall"
      }
    },
    {
      "apply": {
        "model": "tutorialmod:block/fluorite_wall_side_tall",
        "uvlock": true,
        "y": 270
      },
      "when": {
        "west": "tall"
      }
    }
  ]
}
```

### src/main/generated/assets/tutorialmod/blockstates/honey_berry_bush.json

```json
{
  "variants": {
    "age=0": {
      "model": "tutorialmod:block/honey_berry_bush_stage0"
    },
    "age=1": {
      "model": "tutorialmod:block/honey_berry_bush_stage1"
    },
    "age=2": {
      "model": "tutorialmod:block/honey_berry_bush_stage2"
    },
    "age=3": {
      "model": "tutorialmod:block/honey_berry_bush_stage3"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/magic_block.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/magic_block"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/pedestal.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/pedestal"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/potted_balsa_sapling.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/potted_balsa_sapling"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/raw_fluorite_block.json

```json
{
  "variants": {
    "": {
      "model": "tutorialmod:block/raw_fluorite_block"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/rice_crop.json

```json
{
  "variants": {
    "age=0": {
      "model": "tutorialmod:block/rice_crop_stage0"
    },
    "age=1": {
      "model": "tutorialmod:block/rice_crop_stage1"
    },
    "age=2": {
      "model": "tutorialmod:block/rice_crop_stage2"
    },
    "age=3": {
      "model": "tutorialmod:block/rice_crop_stage3"
    },
    "age=4": {
      "model": "tutorialmod:block/rice_crop_stage4"
    },
    "age=5": {
      "model": "tutorialmod:block/rice_crop_stage5"
    },
    "age=6": {
      "model": "tutorialmod:block/rice_crop_stage6"
    },
    "age=7": {
      "model": "tutorialmod:block/rice_crop_stage7"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/strawberry_crop.json

```json
{
  "variants": {
    "age=0": {
      "model": "tutorialmod:block/strawberry_crop_stage0"
    },
    "age=1": {
      "model": "tutorialmod:block/strawberry_crop_stage1"
    },
    "age=2": {
      "model": "tutorialmod:block/strawberry_crop_stage2"
    },
    "age=3": {
      "model": "tutorialmod:block/strawberry_crop_stage3"
    },
    "age=4": {
      "model": "tutorialmod:block/strawberry_crop_stage4"
    },
    "age=5": {
      "model": "tutorialmod:block/strawberry_crop_stage5"
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/stripped_balsa_log.json

```json
{
  "variants": {
    "axis=x": {
      "model": "tutorialmod:block/stripped_balsa_log",
      "x": 90,
      "y": 90
    },
    "axis=y": {
      "model": "tutorialmod:block/stripped_balsa_log"
    },
    "axis=z": {
      "model": "tutorialmod:block/stripped_balsa_log",
      "x": 90
    }
  }
}
```

### src/main/generated/assets/tutorialmod/blockstates/stripped_balsa_wood.json

```json
{
  "variants": {
    "axis=x": {
      "model": "tutorialmod:block/stripped_balsa_wood",
      "x": 90,
      "y": 90
    },
    "axis=y": {
      "model": "tutorialmod:block/stripped_balsa_wood"
    },
    "axis=z": {
      "model": "tutorialmod:block/stripped_balsa_wood",
      "x": 90
    }
  }
}
```

### src/main/generated/assets/tutorialmod/equipment/fluorite.json

```json
{
  "layers": {
    "horse_body": [
      {
        "texture": "tutorialmod:fluorite"
      }
    ],
    "humanoid": [
      {
        "texture": "tutorialmod:fluorite"
      }
    ],
    "humanoid_baby": [
      {
        "texture": "tutorialmod:fluorite"
      }
    ],
    "humanoid_leggings": [
      {
        "texture": "tutorialmod:fluorite"
      }
    ]
  }
}
```

### src/main/generated/assets/tutorialmod/items/balsa_leaves.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/balsa_leaves"
  }
}
```

### src/main/generated/assets/tutorialmod/items/balsa_log.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/balsa_log"
  }
}
```

### src/main/generated/assets/tutorialmod/items/balsa_planks.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/balsa_planks"
  }
}
```

### src/main/generated/assets/tutorialmod/items/balsa_sapling.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/balsa_sapling"
  }
}
```

### src/main/generated/assets/tutorialmod/items/balsa_wood.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/balsa_wood"
  }
}
```

### src/main/generated/assets/tutorialmod/items/bar_brawl_music_disc.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/bar_brawl_music_disc"
  }
}
```

### src/main/generated/assets/tutorialmod/items/chisel.json

```json
{
  "model": {
    "type": "minecraft:condition",
    "component": "tutorialmod:coordinates",
    "on_false": {
      "type": "minecraft:model",
      "model": "tutorialmod:item/chisel"
    },
    "on_true": {
      "type": "minecraft:model",
      "model": "tutorialmod:item/chisel_used"
    },
    "property": "minecraft:has_component"
  }
}
```

### src/main/generated/assets/tutorialmod/items/combustible_spores.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/combustible_spores"
  }
}
```

### src/main/generated/assets/tutorialmod/items/crystallizer.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/crystallizer"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/fluorite"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_axe.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/fluorite_axe"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_block.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_boots.json

```json
{
  "model": {
    "type": "minecraft:select",
    "cases": [
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_quartz_trim"
        },
        "when": "minecraft:quartz"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_iron_trim"
        },
        "when": "minecraft:iron"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_netherite_trim"
        },
        "when": "minecraft:netherite"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_redstone_trim"
        },
        "when": "minecraft:redstone"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_copper_trim"
        },
        "when": "minecraft:copper"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_gold_trim"
        },
        "when": "minecraft:gold"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_emerald_trim"
        },
        "when": "minecraft:emerald"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_diamond_trim"
        },
        "when": "minecraft:diamond"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_lapis_trim"
        },
        "when": "minecraft:lapis"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_amethyst_trim"
        },
        "when": "minecraft:amethyst"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_boots_resin_trim"
        },
        "when": "minecraft:resin"
      }
    ],
    "fallback": {
      "type": "minecraft:model",
      "model": "tutorialmod:item/fluorite_boots"
    },
    "property": "minecraft:trim_material"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_button.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_button_inventory"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_chestplate.json

```json
{
  "model": {
    "type": "minecraft:select",
    "cases": [
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_quartz_trim"
        },
        "when": "minecraft:quartz"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_iron_trim"
        },
        "when": "minecraft:iron"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_netherite_trim"
        },
        "when": "minecraft:netherite"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_redstone_trim"
        },
        "when": "minecraft:redstone"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_copper_trim"
        },
        "when": "minecraft:copper"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_gold_trim"
        },
        "when": "minecraft:gold"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_emerald_trim"
        },
        "when": "minecraft:emerald"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_diamond_trim"
        },
        "when": "minecraft:diamond"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_lapis_trim"
        },
        "when": "minecraft:lapis"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_amethyst_trim"
        },
        "when": "minecraft:amethyst"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_chestplate_resin_trim"
        },
        "when": "minecraft:resin"
      }
    ],
    "fallback": {
      "type": "minecraft:model",
      "model": "tutorialmod:item/fluorite_chestplate"
    },
    "property": "minecraft:trim_material"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_deepslate_ore.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_deepslate_ore"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_door.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/fluorite_door"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_end_ore.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_end_ore"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_fence.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_fence_inventory"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_fence_gate.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_fence_gate"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_helmet.json

```json
{
  "model": {
    "type": "minecraft:select",
    "cases": [
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_quartz_trim"
        },
        "when": "minecraft:quartz"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_iron_trim"
        },
        "when": "minecraft:iron"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_netherite_trim"
        },
        "when": "minecraft:netherite"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_redstone_trim"
        },
        "when": "minecraft:redstone"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_copper_trim"
        },
        "when": "minecraft:copper"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_gold_trim"
        },
        "when": "minecraft:gold"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_emerald_trim"
        },
        "when": "minecraft:emerald"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_diamond_trim"
        },
        "when": "minecraft:diamond"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_lapis_trim"
        },
        "when": "minecraft:lapis"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_amethyst_trim"
        },
        "when": "minecraft:amethyst"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_helmet_resin_trim"
        },
        "when": "minecraft:resin"
      }
    ],
    "fallback": {
      "type": "minecraft:model",
      "model": "tutorialmod:item/fluorite_helmet"
    },
    "property": "minecraft:trim_material"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_hoe.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/fluorite_hoe"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_horse_armor.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/fluorite_horse_armor"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_lamp.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_lamp"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_leggings.json

```json
{
  "model": {
    "type": "minecraft:select",
    "cases": [
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_quartz_trim"
        },
        "when": "minecraft:quartz"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_iron_trim"
        },
        "when": "minecraft:iron"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_netherite_trim"
        },
        "when": "minecraft:netherite"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_redstone_trim"
        },
        "when": "minecraft:redstone"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_copper_trim"
        },
        "when": "minecraft:copper"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_gold_trim"
        },
        "when": "minecraft:gold"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_emerald_trim"
        },
        "when": "minecraft:emerald"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_diamond_trim"
        },
        "when": "minecraft:diamond"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_lapis_trim"
        },
        "when": "minecraft:lapis"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_amethyst_trim"
        },
        "when": "minecraft:amethyst"
      },
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_leggings_resin_trim"
        },
        "when": "minecraft:resin"
      }
    ],
    "fallback": {
      "type": "minecraft:model",
      "model": "tutorialmod:item/fluorite_leggings"
    },
    "property": "minecraft:trim_material"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_nether_ore.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_nether_ore"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_ore.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_ore"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_pickaxe.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/fluorite_pickaxe"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_pressure_plate.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_pressure_plate"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_shovel.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/fluorite_shovel"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_slab.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_slab"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_spear.json

```json
{
  "model": {
    "type": "minecraft:select",
    "cases": [
      {
        "model": {
          "type": "minecraft:model",
          "model": "tutorialmod:item/fluorite_spear"
        },
        "when": [
          "gui",
          "ground",
          "fixed",
          "on_shelf"
        ]
      }
    ],
    "fallback": {
      "type": "minecraft:model",
      "model": "tutorialmod:item/fluorite_spear_in_hand"
    },
    "property": "minecraft:display_context"
  },
  "swap_animation_scale": 1.95
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_stairs.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_stairs"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_sword.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/fluorite_sword"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_trapdoor.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_trapdoor_bottom"
  }
}
```

### src/main/generated/assets/tutorialmod/items/fluorite_wall.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/fluorite_wall_inventory"
  }
}
```

### src/main/generated/assets/tutorialmod/items/honey_berries.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/honey_berries"
  }
}
```

### src/main/generated/assets/tutorialmod/items/kaupen_bow.json

```json
{
  "model": {
    "type": "minecraft:condition",
    "on_false": {
      "type": "minecraft:model",
      "model": "tutorialmod:item/kaupen_bow"
    },
    "on_true": {
      "type": "minecraft:range_dispatch",
      "entries": [
        {
          "model": {
            "type": "minecraft:model",
            "model": "tutorialmod:item/kaupen_bow_pulling_1"
          },
          "threshold": 0.65
        },
        {
          "model": {
            "type": "minecraft:model",
            "model": "tutorialmod:item/kaupen_bow_pulling_2"
          },
          "threshold": 0.9
        }
      ],
      "fallback": {
        "type": "minecraft:model",
        "model": "tutorialmod:item/kaupen_bow_pulling_0"
      },
      "property": "minecraft:use_duration",
      "scale": 0.05
    },
    "property": "minecraft:using_item"
  }
}
```

### src/main/generated/assets/tutorialmod/items/magic_block.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/magic_block"
  }
}
```

### src/main/generated/assets/tutorialmod/items/pedestal.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/pedestal"
  }
}
```

### src/main/generated/assets/tutorialmod/items/raw_fluorite.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/raw_fluorite"
  }
}
```

### src/main/generated/assets/tutorialmod/items/raw_fluorite_block.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/raw_fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/items/rice_shoot.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/rice_shoot"
  }
}
```

### src/main/generated/assets/tutorialmod/items/sculkbeam_staff.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/sculkbeam_staff"
  }
}
```

### src/main/generated/assets/tutorialmod/items/spectre_staff.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/spectre_staff"
  }
}
```

### src/main/generated/assets/tutorialmod/items/strawberry.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/strawberry"
  }
}
```

### src/main/generated/assets/tutorialmod/items/strawberry_seeds.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:item/strawberry_seeds"
  }
}
```

### src/main/generated/assets/tutorialmod/items/stripped_balsa_log.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/stripped_balsa_log"
  }
}
```

### src/main/generated/assets/tutorialmod/items/stripped_balsa_wood.json

```json
{
  "model": {
    "type": "minecraft:model",
    "model": "tutorialmod:block/stripped_balsa_wood"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/balsa_leaves.json

```json
{
  "parent": "minecraft:block/leaves",
  "textures": {
    "all": "tutorialmod:block/balsa_leaves"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/balsa_log.json

```json
{
  "parent": "minecraft:block/cube_column",
  "textures": {
    "end": "tutorialmod:block/balsa_log_top",
    "side": "tutorialmod:block/balsa_log"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/balsa_planks.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/balsa_planks"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/balsa_sapling.json

```json
{
  "parent": "minecraft:block/cross",
  "textures": {
    "cross": "tutorialmod:block/balsa_sapling"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/balsa_wood.json

```json
{
  "parent": "minecraft:block/cube_column",
  "textures": {
    "end": "tutorialmod:block/balsa_log",
    "side": "tutorialmod:block/balsa_log"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/crystallizer.json

```json
{
  "parent": "minecraft:block/orientable_with_bottom",
  "textures": {
    "bottom": "tutorialmod:block/crystallizer_bottom",
    "front": "tutorialmod:block/crystallizer_front",
    "side": "tutorialmod:block/crystallizer_side",
    "top": "tutorialmod:block/crystallizer_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/crystallizer_on.json

```json
{
  "parent": "minecraft:block/orientable_with_bottom",
  "textures": {
    "bottom": "tutorialmod:block/crystallizer_bottom",
    "front": "tutorialmod:block/crystallizer_front_on",
    "side": "tutorialmod:block/crystallizer_side",
    "top": "tutorialmod:block/crystallizer_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_block.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_button.json

```json
{
  "parent": "minecraft:block/button",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_button_inventory.json

```json
{
  "parent": "minecraft:block/button_inventory",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_button_pressed.json

```json
{
  "parent": "minecraft:block/button_pressed",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_deepslate_ore.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/fluorite_deepslate_ore"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_door_bottom_left.json

```json
{
  "parent": "minecraft:block/door_bottom_left",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_door_bottom",
    "top": "tutorialmod:block/fluorite_door_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_door_bottom_left_open.json

```json
{
  "parent": "minecraft:block/door_bottom_left_open",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_door_bottom",
    "top": "tutorialmod:block/fluorite_door_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_door_bottom_right.json

```json
{
  "parent": "minecraft:block/door_bottom_right",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_door_bottom",
    "top": "tutorialmod:block/fluorite_door_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_door_bottom_right_open.json

```json
{
  "parent": "minecraft:block/door_bottom_right_open",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_door_bottom",
    "top": "tutorialmod:block/fluorite_door_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_door_top_left.json

```json
{
  "parent": "minecraft:block/door_top_left",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_door_bottom",
    "top": "tutorialmod:block/fluorite_door_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_door_top_left_open.json

```json
{
  "parent": "minecraft:block/door_top_left_open",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_door_bottom",
    "top": "tutorialmod:block/fluorite_door_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_door_top_right.json

```json
{
  "parent": "minecraft:block/door_top_right",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_door_bottom",
    "top": "tutorialmod:block/fluorite_door_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_door_top_right_open.json

```json
{
  "parent": "minecraft:block/door_top_right_open",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_door_bottom",
    "top": "tutorialmod:block/fluorite_door_top"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_end_ore.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/fluorite_end_ore"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_fence_gate.json

```json
{
  "parent": "minecraft:block/template_fence_gate",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_fence_gate_open.json

```json
{
  "parent": "minecraft:block/template_fence_gate_open",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_fence_gate_wall.json

```json
{
  "parent": "minecraft:block/template_fence_gate_wall",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_fence_gate_wall_open.json

```json
{
  "parent": "minecraft:block/template_fence_gate_wall_open",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_fence_inventory.json

```json
{
  "parent": "minecraft:block/fence_inventory",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_fence_post.json

```json
{
  "parent": "minecraft:block/fence_post",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_fence_side.json

```json
{
  "parent": "minecraft:block/fence_side",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_lamp.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/fluorite_lamp"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_lamp_on.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/fluorite_lamp_on"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_nether_ore.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/fluorite_nether_ore"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_ore.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/fluorite_ore"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_pressure_plate.json

```json
{
  "parent": "minecraft:block/pressure_plate_up",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_pressure_plate_down.json

```json
{
  "parent": "minecraft:block/pressure_plate_down",
  "textures": {
    "texture": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_slab.json

```json
{
  "parent": "minecraft:block/slab",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_block",
    "side": "tutorialmod:block/fluorite_block",
    "top": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_slab_top.json

```json
{
  "parent": "minecraft:block/slab_top",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_block",
    "side": "tutorialmod:block/fluorite_block",
    "top": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_stairs.json

```json
{
  "parent": "minecraft:block/stairs",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_block",
    "side": "tutorialmod:block/fluorite_block",
    "top": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_stairs_inner.json

```json
{
  "parent": "minecraft:block/inner_stairs",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_block",
    "side": "tutorialmod:block/fluorite_block",
    "top": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_stairs_outer.json

```json
{
  "parent": "minecraft:block/outer_stairs",
  "textures": {
    "bottom": "tutorialmod:block/fluorite_block",
    "side": "tutorialmod:block/fluorite_block",
    "top": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_trapdoor_bottom.json

```json
{
  "parent": "minecraft:block/template_trapdoor_bottom",
  "textures": {
    "texture": "tutorialmod:block/fluorite_trapdoor"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_trapdoor_open.json

```json
{
  "parent": "minecraft:block/template_trapdoor_open",
  "textures": {
    "texture": "tutorialmod:block/fluorite_trapdoor"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_trapdoor_top.json

```json
{
  "parent": "minecraft:block/template_trapdoor_top",
  "textures": {
    "texture": "tutorialmod:block/fluorite_trapdoor"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_wall_inventory.json

```json
{
  "parent": "minecraft:block/wall_inventory",
  "textures": {
    "wall": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_wall_post.json

```json
{
  "parent": "minecraft:block/template_wall_post",
  "textures": {
    "wall": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_wall_side.json

```json
{
  "parent": "minecraft:block/template_wall_side",
  "textures": {
    "wall": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/fluorite_wall_side_tall.json

```json
{
  "parent": "minecraft:block/template_wall_side_tall",
  "textures": {
    "wall": "tutorialmod:block/fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/honey_berry_bush_stage0.json

```json
{
  "parent": "minecraft:block/cross",
  "textures": {
    "cross": "tutorialmod:block/honey_berry_bush_stage0"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/honey_berry_bush_stage1.json

```json
{
  "parent": "minecraft:block/cross",
  "textures": {
    "cross": "tutorialmod:block/honey_berry_bush_stage1"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/honey_berry_bush_stage2.json

```json
{
  "parent": "minecraft:block/cross",
  "textures": {
    "cross": "tutorialmod:block/honey_berry_bush_stage2"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/honey_berry_bush_stage3.json

```json
{
  "parent": "minecraft:block/cross",
  "textures": {
    "cross": "tutorialmod:block/honey_berry_bush_stage3"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/magic_block.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/magic_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/potted_balsa_sapling.json

```json
{
  "parent": "minecraft:block/flower_pot_cross",
  "textures": {
    "plant": "tutorialmod:block/balsa_sapling"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/raw_fluorite_block.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "tutorialmod:block/raw_fluorite_block"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/rice_crop_stage0.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/rice_crop_stage0"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/rice_crop_stage1.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/rice_crop_stage1"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/rice_crop_stage2.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/rice_crop_stage2"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/rice_crop_stage3.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/rice_crop_stage3"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/rice_crop_stage4.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/rice_crop_stage4"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/rice_crop_stage5.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/rice_crop_stage5"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/rice_crop_stage6.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/rice_crop_stage6"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/rice_crop_stage7.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/rice_crop_stage7"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/strawberry_crop_stage0.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/strawberry_crop_stage0"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/strawberry_crop_stage1.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/strawberry_crop_stage1"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/strawberry_crop_stage2.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/strawberry_crop_stage2"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/strawberry_crop_stage3.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/strawberry_crop_stage3"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/strawberry_crop_stage4.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/strawberry_crop_stage4"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/strawberry_crop_stage5.json

```json
{
  "parent": "minecraft:block/crop",
  "textures": {
    "crop": "tutorialmod:block/strawberry_crop_stage5"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/stripped_balsa_log.json

```json
{
  "parent": "minecraft:block/cube_column",
  "textures": {
    "end": "tutorialmod:block/stripped_balsa_log_top",
    "side": "tutorialmod:block/stripped_balsa_log"
  }
}
```

### src/main/generated/assets/tutorialmod/models/block/stripped_balsa_wood.json

```json
{
  "parent": "minecraft:block/cube_column",
  "textures": {
    "end": "tutorialmod:block/stripped_balsa_log",
    "side": "tutorialmod:block/stripped_balsa_log"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/balsa_sapling.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:block/balsa_sapling"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/bar_brawl_music_disc.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/bar_brawl_music_disc"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/chisel.json

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "tutorialmod:item/chisel"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/chisel_used.json

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "tutorialmod:item/chisel_used"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/combustible_spores.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/combustible_spores"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_axe.json

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_axe"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_amethyst_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_amethyst"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_copper_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_copper"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_diamond_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_diamond"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_emerald_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_emerald"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_gold_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_gold"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_iron_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_iron"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_lapis_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_lapis"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_netherite_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_netherite"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_quartz_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_quartz"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_redstone_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_redstone"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_boots_resin_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_boots",
    "layer1": "minecraft:trims/items/boots_trim_resin"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_amethyst_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_amethyst"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_copper_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_copper"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_diamond_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_diamond"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_emerald_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_emerald"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_gold_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_gold"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_iron_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_iron"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_lapis_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_lapis"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_netherite_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_netherite"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_quartz_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_quartz"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_redstone_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_redstone"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_chestplate_resin_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_chestplate",
    "layer1": "minecraft:trims/items/chestplate_trim_resin"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_door.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_door"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_amethyst_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_amethyst"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_copper_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_copper"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_diamond_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_diamond"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_emerald_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_emerald"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_gold_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_gold"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_iron_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_iron"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_lapis_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_lapis"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_netherite_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_netherite"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_quartz_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_quartz"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_redstone_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_redstone"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_helmet_resin_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_helmet",
    "layer1": "minecraft:trims/items/helmet_trim_resin"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_hoe.json

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_hoe"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_horse_armor.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_horse_armor"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_amethyst_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_amethyst"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_copper_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_copper"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_diamond_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_diamond"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_emerald_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_emerald"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_gold_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_gold"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_iron_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_iron"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_lapis_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_lapis"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_netherite_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_netherite"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_quartz_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_quartz"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_redstone_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_redstone"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_leggings_resin_trim.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_leggings",
    "layer1": "minecraft:trims/items/leggings_trim_resin"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_pickaxe.json

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_pickaxe"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_shovel.json

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_shovel"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_spear.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_spear"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_spear_in_hand.json

```json
{
  "parent": "minecraft:item/spear_in_hand",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_spear_in_hand"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/fluorite_sword.json

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "tutorialmod:item/fluorite_sword"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/honey_berries.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/honey_berries"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/kaupen_bow.json

```json
{
  "parent": "minecraft:item/bow",
  "textures": {
    "layer0": "tutorialmod:item/kaupen_bow"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/kaupen_bow_pulling_0.json

```json
{
  "parent": "minecraft:item/bow",
  "textures": {
    "layer0": "tutorialmod:item/kaupen_bow_pulling_0"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/kaupen_bow_pulling_1.json

```json
{
  "parent": "minecraft:item/bow",
  "textures": {
    "layer0": "tutorialmod:item/kaupen_bow_pulling_1"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/kaupen_bow_pulling_2.json

```json
{
  "parent": "minecraft:item/bow",
  "textures": {
    "layer0": "tutorialmod:item/kaupen_bow_pulling_2"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/raw_fluorite.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/raw_fluorite"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/rice_shoot.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/rice_shoot"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/spectre_staff.json

```json
{
  "parent": "minecraft:item/handheld",
  "textures": {
    "layer0": "tutorialmod:item/spectre_staff"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/strawberry.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/strawberry"
  }
}
```

### src/main/generated/assets/tutorialmod/models/item/strawberry_seeds.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "tutorialmod:item/strawberry_seeds"
  }
}
```

### src/main/generated/assets/tutorialmod/sounds.json

```json
{
  "bar_brawl": {
    "sounds": [
      {
        "name": "tutorialmod:bar_brawl",
        "stream": true
      }
    ],
    "subtitle": "subtitles.tutorialmod.bar_brawl"
  },
  "chisel_use": {
    "sounds": [
      "tutorialmod:chisel_use"
    ],
    "subtitle": "sounds.tutorialmod.chisel_use"
  }
}
```

### src/main/generated/data/minecraft/tags/block/buttons.json

```json
{
  "values": [
    "tutorialmod:fluorite_button"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/crops.json

```json
{
  "values": [
    "tutorialmod:strawberry_crop"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/doors.json

```json
{
  "values": [
    "tutorialmod:fluorite_door"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/fence_gates.json

```json
{
  "values": [
    "tutorialmod:fluorite_fence_gate"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/fences.json

```json
{
  "values": [
    "tutorialmod:fluorite_fence"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/flower_pots.json

```json
{
  "values": [
    "tutorialmod:potted_balsa_sapling"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/leaves.json

```json
{
  "values": [
    "tutorialmod:balsa_leaves"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/logs.json

```json
{
  "values": [
    "#tutorialmod:balsa_logs"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/mineable/pickaxe.json

```json
{
  "values": [
    "tutorialmod:fluorite_block",
    "tutorialmod:raw_fluorite_block",
    "tutorialmod:fluorite_ore",
    "tutorialmod:fluorite_deepslate_ore",
    "tutorialmod:fluorite_nether_ore",
    "tutorialmod:fluorite_end_ore",
    "tutorialmod:magic_block",
    "tutorialmod:fluorite_stairs",
    "tutorialmod:fluorite_slab",
    "tutorialmod:fluorite_fence",
    "tutorialmod:fluorite_fence_gate",
    "tutorialmod:fluorite_wall",
    "tutorialmod:fluorite_door",
    "tutorialmod:fluorite_trapdoor",
    "tutorialmod:pedestal",
    "tutorialmod:crystallizer"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/needs_diamond_tool.json

```json
{
  "values": [
    "tutorialmod:fluorite_nether_ore",
    "tutorialmod:fluorite_end_ore"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/needs_iron_tool.json

```json
{
  "values": [
    "tutorialmod:fluorite_deepslate_ore"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/planks.json

```json
{
  "values": [
    "tutorialmod:balsa_planks"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/pressure_plates.json

```json
{
  "values": [
    "tutorialmod:fluorite_pressure_plate"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/slabs.json

```json
{
  "values": [
    "tutorialmod:fluorite_slab"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/stairs.json

```json
{
  "values": [
    "tutorialmod:fluorite_stairs"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/trapdoors.json

```json
{
  "values": [
    "tutorialmod:fluorite_trapdoor"
  ]
}
```

### src/main/generated/data/minecraft/tags/block/walls.json

```json
{
  "values": [
    "tutorialmod:fluorite_wall"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/axes.json

```json
{
  "values": [
    "tutorialmod:fluorite_axe"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/chest_armor.json

```json
{
  "values": [
    "tutorialmod:fluorite_chestplate"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/creeper_drop_music_discs.json

```json
{
  "values": [
    "tutorialmod:bar_brawl_music_disc"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/enchantable/bow.json

```json
{
  "values": [
    "tutorialmod:kaupen_bow"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/foot_armor.json

```json
{
  "values": [
    "tutorialmod:fluorite_boots"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/head_armor.json

```json
{
  "values": [
    "tutorialmod:fluorite_helmet"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/hoes.json

```json
{
  "values": [
    "tutorialmod:fluorite_hoe"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/leaves.json

```json
{
  "values": [
    "tutorialmod:balsa_leaves"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/leg_armor.json

```json
{
  "values": [
    "tutorialmod:fluorite_leggings"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/logs_that_burn.json

```json
{
  "values": [
    "#tutorialmod:balsa_logs"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/pickaxes.json

```json
{
  "values": [
    "tutorialmod:fluorite_pickaxe"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/planks.json

```json
{
  "values": [
    "tutorialmod:balsa_planks"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/saplings.json

```json
{
  "values": [
    "tutorialmod:balsa_sapling"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/shovels.json

```json
{
  "values": [
    "tutorialmod:fluorite_shovel"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/spears.json

```json
{
  "values": [
    "tutorialmod:fluorite_spear"
  ]
}
```

### src/main/generated/data/minecraft/tags/item/swords.json

```json
{
  "values": [
    "tutorialmod:fluorite_sword"
  ]
}
```

### src/main/generated/data/minecraft/tags/painting_variant/placeable.json

```json
{
  "values": [
    "tutorialmod:saw_them",
    "tutorialmod:shrimp",
    "tutorialmod:world",
    "tutorialmod:wanderer"
  ]
}
```

### src/main/generated/data/minecraft/tags/point_of_interest_type/acquirable_job_site.json

```json
{
  "values": [
    "tutorialmod:kaupen_poi"
  ]
}
```

### src/main/generated/data/minecraft/tags/villager_trade/farmer/level_1.json

```json
{
  "values": [
    "tutorialmod:farmer/1/emerald_strawberry",
    "tutorialmod:farmer/1/diamond_strawberry_seeds"
  ]
}
```

### src/main/generated/data/minecraft/tags/villager_trade/farmer/level_2.json

```json
{
  "values": [
    "tutorialmod:farmer/2/emerald_honey_berries"
  ]
}
```

### src/main/generated/data/minecraft/tags/villager_trade/librarian/level_1.json

```json
{
  "values": [
    "tutorialmod:librarian/1/fluorite_enchanted_book"
  ]
}
```

### src/main/generated/data/minecraft/tags/villager_trade/mason/level_1.json

```json
{
  "values": [
    "tutorialmod:mason/1/fluorite_chisel"
  ]
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/building_blocks/balsa_planks.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_logs": {
      "conditions": {
        "items": [
          {
            "items": "#tutorialmod:balsa_logs"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:balsa_planks"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_logs"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:balsa_planks"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/building_blocks/balsa_wood.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_log": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:balsa_log"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:balsa_wood"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_log"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:balsa_wood"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/building_blocks/fluorite_block.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_block"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_block"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/building_blocks/fluorite_slab.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_block": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_block"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_slab"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_block"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_slab"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/building_blocks/fluorite_stairs.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_block": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_block"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_stairs"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_block"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_stairs"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/building_blocks/fluorite_wall.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_block": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_block"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_wall"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_block"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_wall"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/building_blocks/stripped_balsa_wood.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_log": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:stripped_balsa_log"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:stripped_balsa_wood"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_log"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:stripped_balsa_wood"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/combat/fluorite_boots.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_boots"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_boots"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/combat/fluorite_chestplate.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_chestplate"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_chestplate"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/combat/fluorite_helmet.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_helmet"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_helmet"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/combat/fluorite_leggings.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_leggings"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_leggings"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/combat/fluorite_spear.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_stick": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:stick"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_spear"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite",
      "has_stick"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_spear"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/combat/fluorite_sword.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_stick": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:stick"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_sword"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite",
      "has_stick"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_sword"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/decorations/fluorite_fence.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_fence"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_fence"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/bar_brawl_music_disc_from_crystallizing.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_redstone": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:redstone"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:bar_brawl_music_disc_from_crystallizing"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_redstone"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:bar_brawl_music_disc_from_crystallizing"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/end_rod_from_crystallizing.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_stick": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:stick"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:end_rod_from_crystallizing"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_stick"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:end_rod_from_crystallizing"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_block": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_block"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_block"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_fluorite_deepslate_ore.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_deepslate_ore": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_deepslate_ore"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_blasting_fluorite_deepslate_ore"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_deepslate_ore"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_blasting_fluorite_deepslate_ore"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_fluorite_end_ore.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_end_ore": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_end_ore"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_blasting_fluorite_end_ore"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_end_ore"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_blasting_fluorite_end_ore"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_fluorite_nether_ore.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_nether_ore": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_nether_ore"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_blasting_fluorite_nether_ore"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_nether_ore"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_blasting_fluorite_nether_ore"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_fluorite_ore.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_ore": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_ore"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_blasting_fluorite_ore"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_ore"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_blasting_fluorite_ore"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_blasting_raw_fluorite.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_raw_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:raw_fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_blasting_raw_fluorite"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_raw_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_blasting_raw_fluorite"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_crystallizing.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_raw_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:raw_fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_crystallizing"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_raw_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_crystallizing"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_fluorite_deepslate_ore.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_deepslate_ore": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_deepslate_ore"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_smelting_fluorite_deepslate_ore"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_deepslate_ore"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_smelting_fluorite_deepslate_ore"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_fluorite_end_ore.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_end_ore": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_end_ore"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_smelting_fluorite_end_ore"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_end_ore"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_smelting_fluorite_end_ore"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_fluorite_nether_ore.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_nether_ore": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_nether_ore"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_smelting_fluorite_nether_ore"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_nether_ore"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_smelting_fluorite_nether_ore"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_fluorite_ore.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite_ore": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite_ore"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_smelting_fluorite_ore"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite_ore"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_smelting_fluorite_ore"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/fluorite_from_smelting_raw_fluorite.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_raw_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:raw_fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_from_smelting_raw_fluorite"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_raw_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_from_smelting_raw_fluorite"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/nether_star_from_crystallizing.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_dirt": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:dirt"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:nether_star_from_crystallizing"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_dirt"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:nether_star_from_crystallizing"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/raw_fluorite.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_raw_fluorite_block": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:raw_fluorite_block"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:raw_fluorite"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_raw_fluorite_block"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:raw_fluorite"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/raw_fluorite_block.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_raw_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:raw_fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:raw_fluorite_block"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_raw_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:raw_fluorite_block"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/raw_fluorite_from_fluorite_and_stick.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_stick": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:stick"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:raw_fluorite_from_fluorite_and_stick"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite",
      "has_stick"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:raw_fluorite_from_fluorite_and_stick"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/misc/rice_shoot_from_crystallizing.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_strawberry": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:strawberry"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:rice_shoot_from_crystallizing"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_strawberry"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:rice_shoot_from_crystallizing"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/redstone/fluorite_button.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_button"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_button"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/redstone/fluorite_door.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_door"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_door"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/redstone/fluorite_fence_gate.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_fence_gate"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_fence_gate"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/redstone/fluorite_pressure_plate.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_pressure_plate"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_pressure_plate"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/redstone/fluorite_trapdoor.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_trapdoor"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_trapdoor"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/tools/fluorite_axe.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_stick": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:stick"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_axe"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite",
      "has_stick"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_axe"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/tools/fluorite_hoe.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_stick": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:stick"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_hoe"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite",
      "has_stick"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_hoe"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/tools/fluorite_pickaxe.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_stick": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:stick"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_pickaxe"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite",
      "has_stick"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_pickaxe"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/recipes/tools/fluorite_shovel.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_stick": {
      "conditions": {
        "items": [
          {
            "items": "minecraft:stick"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "tutorialmod:fluorite_shovel"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_the_recipe",
      "has_fluorite",
      "has_stick"
    ]
  ],
  "rewards": {
    "recipes": [
      "tutorialmod:fluorite_shovel"
    ]
  }
}
```

### src/main/generated/data/tutorialmod/advancement/tutorialmod/chisel_stone.json

```json
{
  "parent": "tutorialmod:tutorialmod/root",
  "criteria": {
    "chisel_stone": {
      "conditions": {
        "location": [
          {
            "condition": "minecraft:location_check",
            "predicate": {}
          },
          {
            "condition": "minecraft:match_tool",
            "predicate": {
              "items": "tutorialmod:chisel"
            }
          }
        ]
      },
      "trigger": "minecraft:item_used_on_block"
    }
  },
  "display": {
    "description": {
      "translate": "advancements.tutorialmod.chisel_stone.description"
    },
    "icon": {
      "id": "tutorialmod:chisel"
    },
    "title": {
      "translate": "advancements.tutorialmod.chisel_stone.title"
    }
  },
  "requirements": [
    [
      "chisel_stone"
    ]
  ],
  "sends_telemetry_event": true
}
```

### src/main/generated/data/tutorialmod/advancement/tutorialmod/plant_custom.json

```json
{
  "parent": "tutorialmod:tutorialmod/root",
  "criteria": {
    "berries": {
      "conditions": {
        "location": [
          {
            "block": "tutorialmod:honey_berry_bush",
            "condition": "minecraft:block_state_property"
          }
        ]
      },
      "trigger": "minecraft:placed_block"
    },
    "rice": {
      "conditions": {
        "location": [
          {
            "block": "tutorialmod:rice_crop",
            "condition": "minecraft:block_state_property"
          }
        ]
      },
      "trigger": "minecraft:placed_block"
    },
    "strawberry": {
      "conditions": {
        "location": [
          {
            "block": "tutorialmod:strawberry_crop",
            "condition": "minecraft:block_state_property"
          }
        ]
      },
      "trigger": "minecraft:placed_block"
    }
  },
  "display": {
    "description": {
      "translate": "advancements.tutorialmod.plant_custom.description"
    },
    "icon": {
      "id": "tutorialmod:rice_shoot"
    },
    "title": {
      "translate": "advancements.tutorialmod.plant_custom.title"
    }
  },
  "requirements": [
    [
      "berries",
      "rice",
      "strawberry"
    ]
  ],
  "sends_telemetry_event": true
}
```

### src/main/generated/data/tutorialmod/advancement/tutorialmod/root.json

```json
{
  "criteria": {
    "has_fluorite": {
      "conditions": {
        "items": [
          {
            "items": "tutorialmod:fluorite"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    }
  },
  "display": {
    "announce_to_chat": false,
    "background": "minecraft:gui/advancements/backgrounds/adventure",
    "description": {
      "translate": "advancements.tutorialmod.root.description"
    },
    "icon": {
      "id": "tutorialmod:fluorite"
    },
    "show_toast": false,
    "title": {
      "translate": "advancements.tutorialmod.root.title"
    }
  },
  "requirements": [
    [
      "has_fluorite"
    ]
  ],
  "sends_telemetry_event": true
}
```

### src/main/generated/data/tutorialmod/damage_type/stinky.json

```json
{
  "exhaustion": 0.1,
  "message_id": "stinky",
  "scaling": "when_caused_by_living_non_player"
}
```

### src/main/generated/data/tutorialmod/jukebox_song/bar_brawl.json

```json
{
  "comparator_output": 15,
  "description": {
    "translate": "jukebox_song.tutorialmod.bar_brawl"
  },
  "length_in_seconds": 162.0,
  "sound_event": "tutorialmod:bar_brawl"
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/balsa_leaves.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "entries": [
        {
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "conditions": [
                {
                  "condition": "minecraft:any_of",
                  "terms": [
                    {
                      "condition": "minecraft:match_tool",
                      "predicate": {
                        "items": "minecraft:shears"
                      }
                    },
                    {
                      "condition": "minecraft:match_tool",
                      "predicate": {
                        "predicates": {
                          "minecraft:enchantments": [
                            {
                              "enchantments": "minecraft:silk_touch",
                              "levels": {
                                "min": 1
                              }
                            }
                          ]
                        }
                      }
                    }
                  ]
                }
              ],
              "name": "tutorialmod:balsa_leaves"
            },
            {
              "type": "minecraft:item",
              "conditions": [
                {
                  "condition": "minecraft:survives_explosion"
                },
                {
                  "chances": [
                    0.05,
                    0.0625,
                    0.083333336,
                    0.1
                  ],
                  "condition": "minecraft:table_bonus",
                  "enchantment": "minecraft:fortune"
                }
              ],
              "name": "tutorialmod:balsa_sapling"
            }
          ]
        }
      ],
      "rolls": 1.0
    },
    {
      "conditions": [
        {
          "condition": "minecraft:inverted",
          "term": {
            "condition": "minecraft:any_of",
            "terms": [
              {
                "condition": "minecraft:match_tool",
                "predicate": {
                  "items": "minecraft:shears"
                }
              },
              {
                "condition": "minecraft:match_tool",
                "predicate": {
                  "predicates": {
                    "minecraft:enchantments": [
                      {
                        "enchantments": "minecraft:silk_touch",
                        "levels": {
                          "min": 1
                        }
                      }
                    ]
                  }
                }
              }
            ]
          }
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "conditions": [
            {
              "chances": [
                0.02,
                0.022222223,
                0.025,
                0.033333335,
                0.1
              ],
              "condition": "minecraft:table_bonus",
              "enchantment": "minecraft:fortune"
            }
          ],
          "functions": [
            {
              "count": {
                "type": "minecraft:uniform",
                "max": 2.0,
                "min": 1.0
              },
              "function": "minecraft:set_count"
            },
            {
              "function": "minecraft:explosion_decay"
            }
          ],
          "name": "minecraft:stick"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/balsa_log.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:balsa_log"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/balsa_planks.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:balsa_planks"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/balsa_sapling.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:balsa_sapling"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/balsa_wood.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:balsa_wood"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/crystallizer.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:crystallizer"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_block.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:fluorite_block"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_button.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:fluorite_button"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_deepslate_ore.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "entries": [
        {
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "conditions": [
                {
                  "condition": "minecraft:match_tool",
                  "predicate": {
                    "predicates": {
                      "minecraft:enchantments": [
                        {
                          "enchantments": "minecraft:silk_touch",
                          "levels": {
                            "min": 1
                          }
                        }
                      ]
                    }
                  }
                }
              ],
              "name": "tutorialmod:fluorite_deepslate_ore"
            },
            {
              "type": "minecraft:item",
              "functions": [
                {
                  "enchantment": "minecraft:fortune",
                  "formula": "minecraft:ore_drops",
                  "function": "minecraft:apply_bonus"
                },
                {
                  "function": "minecraft:explosion_decay"
                }
              ],
              "name": "tutorialmod:raw_fluorite"
            }
          ]
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_door.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "conditions": [
            {
              "block": "tutorialmod:fluorite_door",
              "condition": "minecraft:block_state_property",
              "properties": {
                "half": "lower"
              }
            }
          ],
          "name": "tutorialmod:fluorite_door"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_end_ore.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "entries": [
        {
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "conditions": [
                {
                  "condition": "minecraft:match_tool",
                  "predicate": {
                    "predicates": {
                      "minecraft:enchantments": [
                        {
                          "enchantments": "minecraft:silk_touch",
                          "levels": {
                            "min": 1
                          }
                        }
                      ]
                    }
                  }
                }
              ],
              "name": "tutorialmod:fluorite_end_ore"
            },
            {
              "type": "minecraft:item",
              "functions": [
                {
                  "count": {
                    "type": "minecraft:uniform",
                    "max": 8.0,
                    "min": 5.0
                  },
                  "function": "minecraft:set_count"
                },
                {
                  "enchantment": "minecraft:fortune",
                  "formula": "minecraft:ore_drops",
                  "function": "minecraft:apply_bonus"
                },
                {
                  "function": "minecraft:explosion_decay"
                }
              ],
              "name": "tutorialmod:raw_fluorite"
            }
          ]
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_fence.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:fluorite_fence"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_fence_gate.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:fluorite_fence_gate"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_lamp.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:fluorite_lamp"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_nether_ore.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "entries": [
        {
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "conditions": [
                {
                  "condition": "minecraft:match_tool",
                  "predicate": {
                    "predicates": {
                      "minecraft:enchantments": [
                        {
                          "enchantments": "minecraft:silk_touch",
                          "levels": {
                            "min": 1
                          }
                        }
                      ]
                    }
                  }
                }
              ],
              "name": "tutorialmod:fluorite_nether_ore"
            },
            {
              "type": "minecraft:item",
              "functions": [
                {
                  "count": {
                    "type": "minecraft:uniform",
                    "max": 6.0,
                    "min": 3.0
                  },
                  "function": "minecraft:set_count"
                },
                {
                  "enchantment": "minecraft:fortune",
                  "formula": "minecraft:ore_drops",
                  "function": "minecraft:apply_bonus"
                },
                {
                  "function": "minecraft:explosion_decay"
                }
              ],
              "name": "tutorialmod:raw_fluorite"
            }
          ]
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_ore.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "entries": [
        {
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "conditions": [
                {
                  "condition": "minecraft:match_tool",
                  "predicate": {
                    "predicates": {
                      "minecraft:enchantments": [
                        {
                          "enchantments": "minecraft:silk_touch",
                          "levels": {
                            "min": 1
                          }
                        }
                      ]
                    }
                  }
                }
              ],
              "name": "tutorialmod:fluorite_ore"
            },
            {
              "type": "minecraft:item",
              "functions": [
                {
                  "enchantment": "minecraft:fortune",
                  "formula": "minecraft:ore_drops",
                  "function": "minecraft:apply_bonus"
                },
                {
                  "function": "minecraft:explosion_decay"
                }
              ],
              "name": "tutorialmod:raw_fluorite"
            }
          ]
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_pressure_plate.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:fluorite_pressure_plate"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_slab.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "entries": [
        {
          "type": "minecraft:item",
          "functions": [
            {
              "conditions": [
                {
                  "block": "tutorialmod:fluorite_slab",
                  "condition": "minecraft:block_state_property",
                  "properties": {
                    "type": "double"
                  }
                }
              ],
              "count": 2.0,
              "function": "minecraft:set_count"
            },
            {
              "function": "minecraft:explosion_decay"
            }
          ],
          "name": "tutorialmod:fluorite_slab"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_stairs.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:fluorite_stairs"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_trapdoor.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:fluorite_trapdoor"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/fluorite_wall.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:fluorite_wall"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/honey_berry_bush.json

```json
{
  "type": "minecraft:block",
  "functions": [
    {
      "function": "minecraft:explosion_decay"
    }
  ],
  "pools": [
    {
      "conditions": [
        {
          "block": "tutorialmod:honey_berry_bush",
          "condition": "minecraft:block_state_property",
          "properties": {
            "age": "3"
          }
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:honey_berries"
        }
      ],
      "functions": [
        {
          "count": {
            "type": "minecraft:uniform",
            "max": 3.0,
            "min": 2.0
          },
          "function": "minecraft:set_count"
        },
        {
          "enchantment": "minecraft:fortune",
          "formula": "minecraft:uniform_bonus_count",
          "function": "minecraft:apply_bonus",
          "parameters": {
            "bonusMultiplier": 1
          }
        }
      ],
      "rolls": 1.0
    },
    {
      "conditions": [
        {
          "block": "tutorialmod:honey_berry_bush",
          "condition": "minecraft:block_state_property",
          "properties": {
            "age": "2"
          }
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:honey_berries"
        }
      ],
      "functions": [
        {
          "count": {
            "type": "minecraft:uniform",
            "max": 2.0,
            "min": 1.0
          },
          "function": "minecraft:set_count"
        },
        {
          "enchantment": "minecraft:fortune",
          "formula": "minecraft:uniform_bonus_count",
          "function": "minecraft:apply_bonus",
          "parameters": {
            "bonusMultiplier": 1
          }
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/magic_block.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:magic_block"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/pedestal.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:pedestal"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/potted_balsa_sapling.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "minecraft:flower_pot"
        }
      ],
      "rolls": 1.0
    },
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:balsa_sapling"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/raw_fluorite_block.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:raw_fluorite_block"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/rice_crop.json

```json
{
  "type": "minecraft:block",
  "functions": [
    {
      "function": "minecraft:explosion_decay"
    }
  ],
  "pools": [
    {
      "entries": [
        {
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "conditions": [
                {
                  "block": "tutorialmod:rice_crop",
                  "condition": "minecraft:block_state_property",
                  "properties": {
                    "age": "7"
                  }
                }
              ],
              "name": "tutorialmod:rice_shoot"
            },
            {
              "type": "minecraft:item",
              "name": "tutorialmod:rice_shoot"
            }
          ]
        }
      ],
      "rolls": 1.0
    },
    {
      "conditions": [
        {
          "block": "tutorialmod:rice_crop",
          "condition": "minecraft:block_state_property",
          "properties": {
            "age": "7"
          }
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "functions": [
            {
              "enchantment": "minecraft:fortune",
              "formula": "minecraft:binomial_with_bonus_count",
              "function": "minecraft:apply_bonus",
              "parameters": {
                "extra": 3,
                "probability": 0.5714286
              }
            }
          ],
          "name": "tutorialmod:rice_shoot"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/strawberry_crop.json

```json
{
  "type": "minecraft:block",
  "functions": [
    {
      "function": "minecraft:explosion_decay"
    }
  ],
  "pools": [
    {
      "entries": [
        {
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "conditions": [
                {
                  "block": "tutorialmod:strawberry_crop",
                  "condition": "minecraft:block_state_property",
                  "properties": {
                    "age": "5"
                  }
                }
              ],
              "name": "tutorialmod:strawberry"
            },
            {
              "type": "minecraft:item",
              "name": "tutorialmod:strawberry_seeds"
            }
          ]
        }
      ],
      "rolls": 1.0
    },
    {
      "conditions": [
        {
          "block": "tutorialmod:strawberry_crop",
          "condition": "minecraft:block_state_property",
          "properties": {
            "age": "5"
          }
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "functions": [
            {
              "enchantment": "minecraft:fortune",
              "formula": "minecraft:binomial_with_bonus_count",
              "function": "minecraft:apply_bonus",
              "parameters": {
                "extra": 3,
                "probability": 0.5714286
              }
            }
          ],
          "name": "tutorialmod:strawberry_seeds"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/stripped_balsa_log.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:stripped_balsa_log"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/loot_table/blocks/stripped_balsa_wood.json

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "conditions": [
        {
          "condition": "minecraft:survives_explosion"
        }
      ],
      "entries": [
        {
          "type": "minecraft:item",
          "name": "tutorialmod:stripped_balsa_wood"
        }
      ],
      "rolls": 1.0
    }
  ]
}
```

### src/main/generated/data/tutorialmod/painting_variant/saw_them.json

```json
{
  "asset_id": "tutorialmod:saw_them",
  "author": {
    "color": "gray",
    "translate": "painting.tutorialmod.saw_them.author"
  },
  "height": 2,
  "title": {
    "color": "yellow",
    "translate": "painting.tutorialmod.saw_them.title"
  },
  "width": 2
}
```

### src/main/generated/data/tutorialmod/painting_variant/shrimp.json

```json
{
  "asset_id": "tutorialmod:shrimp",
  "author": {
    "color": "gray",
    "translate": "painting.tutorialmod.shrimp.author"
  },
  "height": 1,
  "title": {
    "color": "yellow",
    "translate": "painting.tutorialmod.shrimp.title"
  },
  "width": 2
}
```

### src/main/generated/data/tutorialmod/painting_variant/wanderer.json

```json
{
  "asset_id": "tutorialmod:wanderer",
  "author": {
    "color": "gray",
    "translate": "painting.tutorialmod.wanderer.author"
  },
  "height": 2,
  "title": {
    "color": "yellow",
    "translate": "painting.tutorialmod.wanderer.title"
  },
  "width": 1
}
```

### src/main/generated/data/tutorialmod/painting_variant/world.json

```json
{
  "asset_id": "tutorialmod:world",
  "author": {
    "color": "gray",
    "translate": "painting.tutorialmod.world.author"
  },
  "height": 2,
  "title": {
    "color": "yellow",
    "translate": "painting.tutorialmod.world.title"
  },
  "width": 2
}
```

### src/main/generated/data/tutorialmod/recipe/balsa_planks.json

```json
{
  "type": "minecraft:crafting_shapeless",
  "category": "building",
  "group": "planks",
  "ingredients": [
    "#tutorialmod:balsa_logs"
  ],
  "result": {
    "count": 4,
    "id": "tutorialmod:balsa_planks"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/balsa_wood.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "building",
  "group": "bark",
  "key": {
    "#": "tutorialmod:balsa_log"
  },
  "pattern": [
    "##",
    "##"
  ],
  "result": {
    "count": 3,
    "id": "tutorialmod:balsa_wood"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/bar_brawl_music_disc_from_crystallizing.json

```json
{
  "type": "tutorialmod:crystallizing",
  "ingredient": "minecraft:redstone",
  "result": {
    "id": "tutorialmod:bar_brawl_music_disc"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/end_rod_from_crystallizing.json

```json
{
  "type": "tutorialmod:crystallizing",
  "ingredient": "minecraft:stick",
  "result": {
    "count": 2,
    "id": "minecraft:end_rod"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite.json

```json
{
  "type": "minecraft:crafting_shapeless",
  "ingredients": [
    "tutorialmod:fluorite_block"
  ],
  "result": {
    "count": 9,
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_axe.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite",
    "S": "minecraft:stick"
  },
  "pattern": [
    "FF",
    "SF",
    "S "
  ],
  "result": {
    "id": "tutorialmod:fluorite_axe"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_block.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "building",
  "key": {
    "#": "tutorialmod:fluorite"
  },
  "pattern": [
    "###",
    "###",
    "###"
  ],
  "result": {
    "id": "tutorialmod:fluorite_block"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_boots.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite"
  },
  "pattern": [
    "F F",
    "F F"
  ],
  "result": {
    "id": "tutorialmod:fluorite_boots"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_button.json

```json
{
  "type": "minecraft:crafting_shapeless",
  "category": "redstone",
  "group": "fluorite",
  "ingredients": [
    "tutorialmod:fluorite"
  ],
  "result": {
    "id": "tutorialmod:fluorite_button"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_chestplate.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite"
  },
  "pattern": [
    "F F",
    "FFF",
    "FFF"
  ],
  "result": {
    "id": "tutorialmod:fluorite_chestplate"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_door.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "redstone",
  "group": "fluorite",
  "key": {
    "#": "tutorialmod:fluorite"
  },
  "pattern": [
    "##",
    "##",
    "##"
  ],
  "result": {
    "count": 3,
    "id": "tutorialmod:fluorite_door"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_fence.json

```json
{
  "type": "minecraft:crafting_shaped",
  "group": "fluorite",
  "key": {
    "#": "minecraft:stick",
    "W": "tutorialmod:fluorite"
  },
  "pattern": [
    "W#W",
    "W#W"
  ],
  "result": {
    "count": 3,
    "id": "tutorialmod:fluorite_fence"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_fence_gate.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "redstone",
  "group": "fluorite",
  "key": {
    "#": "minecraft:stick",
    "W": "tutorialmod:fluorite"
  },
  "pattern": [
    "#W#",
    "#W#"
  ],
  "result": {
    "id": "tutorialmod:fluorite_fence_gate"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_blasting_fluorite_deepslate_ore.json

```json
{
  "type": "minecraft:blasting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:fluorite_deepslate_ore",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_blasting_fluorite_end_ore.json

```json
{
  "type": "minecraft:blasting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:fluorite_end_ore",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_blasting_fluorite_nether_ore.json

```json
{
  "type": "minecraft:blasting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:fluorite_nether_ore",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_blasting_fluorite_ore.json

```json
{
  "type": "minecraft:blasting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:fluorite_ore",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_blasting_raw_fluorite.json

```json
{
  "type": "minecraft:blasting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:raw_fluorite",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_crystallizing.json

```json
{
  "type": "tutorialmod:crystallizing",
  "ingredient": "tutorialmod:raw_fluorite",
  "result": {
    "count": 3,
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_smelting_fluorite_deepslate_ore.json

```json
{
  "type": "minecraft:smelting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:fluorite_deepslate_ore",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_smelting_fluorite_end_ore.json

```json
{
  "type": "minecraft:smelting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:fluorite_end_ore",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_smelting_fluorite_nether_ore.json

```json
{
  "type": "minecraft:smelting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:fluorite_nether_ore",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_smelting_fluorite_ore.json

```json
{
  "type": "minecraft:smelting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:fluorite_ore",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_from_smelting_raw_fluorite.json

```json
{
  "type": "minecraft:smelting",
  "category": "blocks",
  "experience": 0.25,
  "group": "fluorite",
  "ingredient": "tutorialmod:raw_fluorite",
  "result": {
    "id": "tutorialmod:fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_helmet.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite"
  },
  "pattern": [
    "FFF",
    "F F"
  ],
  "result": {
    "id": "tutorialmod:fluorite_helmet"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_hoe.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite",
    "S": "minecraft:stick"
  },
  "pattern": [
    "FF",
    "S ",
    "S "
  ],
  "result": {
    "id": "tutorialmod:fluorite_hoe"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_leggings.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite"
  },
  "pattern": [
    "FFF",
    "F F",
    "F F"
  ],
  "result": {
    "id": "tutorialmod:fluorite_leggings"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_pickaxe.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite",
    "S": "minecraft:stick"
  },
  "pattern": [
    "FFF",
    " S ",
    " S "
  ],
  "result": {
    "id": "tutorialmod:fluorite_pickaxe"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_pressure_plate.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "redstone",
  "key": {
    "#": "tutorialmod:fluorite"
  },
  "pattern": [
    "##"
  ],
  "result": {
    "id": "tutorialmod:fluorite_pressure_plate"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_shovel.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite",
    "S": "minecraft:stick"
  },
  "pattern": [
    "F",
    "S",
    "S"
  ],
  "result": {
    "id": "tutorialmod:fluorite_shovel"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_slab.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "building",
  "key": {
    "#": "tutorialmod:fluorite_block"
  },
  "pattern": [
    "###"
  ],
  "result": {
    "count": 6,
    "id": "tutorialmod:fluorite_slab"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_spear.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite",
    "S": "minecraft:stick"
  },
  "pattern": [
    "  F",
    " S ",
    "S  "
  ],
  "result": {
    "id": "tutorialmod:fluorite_spear"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_stairs.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "building",
  "group": "fluorite",
  "key": {
    "#": "tutorialmod:fluorite_block"
  },
  "pattern": [
    "#  ",
    "## ",
    "###"
  ],
  "result": {
    "count": 4,
    "id": "tutorialmod:fluorite_stairs"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_sword.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "fluorite",
  "key": {
    "F": "tutorialmod:fluorite",
    "S": "minecraft:stick"
  },
  "pattern": [
    "F",
    "F",
    "S"
  ],
  "result": {
    "id": "tutorialmod:fluorite_sword"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_trapdoor.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "redstone",
  "group": "fluorite",
  "key": {
    "#": "tutorialmod:fluorite"
  },
  "pattern": [
    "###",
    "###"
  ],
  "result": {
    "count": 2,
    "id": "tutorialmod:fluorite_trapdoor"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/fluorite_wall.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "building",
  "key": {
    "#": "tutorialmod:fluorite_block"
  },
  "pattern": [
    "###",
    "###"
  ],
  "result": {
    "count": 6,
    "id": "tutorialmod:fluorite_wall"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/nether_star_from_crystallizing.json

```json
{
  "type": "tutorialmod:crystallizing",
  "ingredient": "minecraft:dirt",
  "result": {
    "id": "minecraft:nether_star"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/raw_fluorite.json

```json
{
  "type": "minecraft:crafting_shapeless",
  "group": "fluorite",
  "ingredients": [
    "tutorialmod:raw_fluorite_block"
  ],
  "result": {
    "count": 9,
    "id": "tutorialmod:raw_fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/raw_fluorite_block.json

```json
{
  "type": "minecraft:crafting_shaped",
  "group": "fluorite",
  "key": {
    "R": "tutorialmod:raw_fluorite"
  },
  "pattern": [
    "RRR",
    "RRR",
    "RRR"
  ],
  "result": {
    "id": "tutorialmod:raw_fluorite_block"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/raw_fluorite_from_fluorite_and_stick.json

```json
{
  "type": "minecraft:crafting_shapeless",
  "group": "fluorite",
  "ingredients": [
    "tutorialmod:fluorite",
    "minecraft:stick"
  ],
  "result": {
    "count": 4,
    "id": "tutorialmod:raw_fluorite"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/rice_shoot_from_crystallizing.json

```json
{
  "type": "tutorialmod:crystallizing",
  "ingredient": "tutorialmod:strawberry",
  "result": {
    "count": 2,
    "id": "tutorialmod:rice_shoot"
  }
}
```

### src/main/generated/data/tutorialmod/recipe/stripped_balsa_wood.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "building",
  "group": "bark",
  "key": {
    "#": "tutorialmod:stripped_balsa_log"
  },
  "pattern": [
    "##",
    "##"
  ],
  "result": {
    "count": 3,
    "id": "tutorialmod:stripped_balsa_wood"
  }
}
```

### src/main/generated/data/tutorialmod/tags/block/balsa_logs.json

```json
{
  "values": [
    "tutorialmod:balsa_log",
    "tutorialmod:balsa_wood",
    "tutorialmod:stripped_balsa_log",
    "tutorialmod:stripped_balsa_wood"
  ]
}
```

### src/main/generated/data/tutorialmod/tags/block/incorrect_for_fluorite_tool.json

```json
{
  "values": [
    "#minecraft:needs_diamond_tool"
  ]
}
```

### src/main/generated/data/tutorialmod/tags/block/needs_fluorite_tool.json

```json
{
  "values": [
    "tutorialmod:magic_block",
    "#minecraft:needs_iron_tool"
  ]
}
```

### src/main/generated/data/tutorialmod/tags/item/balsa_logs.json

```json
{
  "values": [
    "tutorialmod:balsa_log",
    "tutorialmod:balsa_wood",
    "tutorialmod:stripped_balsa_log",
    "tutorialmod:stripped_balsa_wood"
  ]
}
```

### src/main/generated/data/tutorialmod/tags/item/transformable_items.json

```json
{
  "values": [
    "tutorialmod:fluorite",
    "minecraft:iron_ingot",
    "minecraft:coal",
    "minecraft:brick"
  ]
}
```

### src/main/generated/data/tutorialmod/tags/villager_trade/kaupenger/level_1.json

```json
{
  "values": [
    "tutorialmod:kaupenger/1/emerald_fluorite",
    "tutorialmod:kaupenger/1/emerald_raw_fluorite"
  ]
}
```

### src/main/generated/data/tutorialmod/tags/villager_trade/kaupenger/level_2.json

```json
{
  "values": [
    "tutorialmod:kaupenger/2/emerald_pedestal",
    "tutorialmod:kaupenger/2/fluorite_spectre_staff"
  ]
}
```

### src/main/generated/data/tutorialmod/trade_set/kaupenger/level_1.json

```json
{
  "amount": 2.0,
  "random_sequence": "tutorialmod:trade_set/kaupenger/level_1",
  "trades": "#tutorialmod:kaupenger/level_1"
}
```

### src/main/generated/data/tutorialmod/trade_set/kaupenger/level_2.json

```json
{
  "amount": 2.0,
  "random_sequence": "tutorialmod:trade_set/kaupenger/level_2",
  "trades": "#tutorialmod:kaupenger/level_2"
}
```

### src/main/generated/data/tutorialmod/villager_trade/farmer/1/diamond_strawberry_seeds.json

```json
{
  "gives": {
    "id": "tutorialmod:strawberry_seeds"
  },
  "max_uses": 12.0,
  "reputation_discount": 0.05,
  "wants": {
    "count": 12.0,
    "id": "minecraft:diamond"
  },
  "xp": 10.0
}
```

### src/main/generated/data/tutorialmod/villager_trade/farmer/1/emerald_strawberry.json

```json
{
  "gives": {
    "id": "tutorialmod:strawberry"
  },
  "max_uses": 12.0,
  "reputation_discount": 0.05,
  "wants": {
    "count": 4.0,
    "id": "minecraft:emerald"
  },
  "xp": 8.0
}
```

### src/main/generated/data/tutorialmod/villager_trade/farmer/2/emerald_honey_berries.json

```json
{
  "gives": {
    "id": "tutorialmod:honey_berries"
  },
  "max_uses": 12.0,
  "reputation_discount": 0.05,
  "wants": {
    "count": 16.0,
    "id": "minecraft:emerald"
  },
  "xp": 10.0
}
```

### src/main/generated/data/tutorialmod/villager_trade/kaupenger/1/emerald_fluorite.json

```json
{
  "gives": {
    "count": 4,
    "id": "tutorialmod:fluorite"
  },
  "max_uses": 12.0,
  "reputation_discount": 0.05,
  "wants": {
    "count": 6.0,
    "id": "minecraft:emerald"
  },
  "xp": 19.0
}
```

### src/main/generated/data/tutorialmod/villager_trade/kaupenger/1/emerald_raw_fluorite.json

```json
{
  "gives": {
    "count": 12,
    "id": "tutorialmod:raw_fluorite"
  },
  "max_uses": 12.0,
  "reputation_discount": 0.05,
  "wants": {
    "count": 5.0,
    "id": "minecraft:emerald"
  },
  "xp": 23.0
}
```

### src/main/generated/data/tutorialmod/villager_trade/kaupenger/2/emerald_pedestal.json

```json
{
  "gives": {
    "id": "tutorialmod:pedestal"
  },
  "max_uses": 12.0,
  "reputation_discount": 0.05,
  "wants": {
    "count": 24.0,
    "id": "minecraft:emerald"
  },
  "xp": 24.0
}
```

### src/main/generated/data/tutorialmod/villager_trade/kaupenger/2/fluorite_spectre_staff.json

```json
{
  "gives": {
    "id": "tutorialmod:spectre_staff"
  },
  "max_uses": 2.0,
  "reputation_discount": 0.05,
  "wants": {
    "count": 19.0,
    "id": "tutorialmod:fluorite"
  },
  "xp": 19.0
}
```

### src/main/generated/data/tutorialmod/villager_trade/librarian/1/fluorite_enchanted_book.json

```json
{
  "given_item_modifiers": [
    {
      "function": "minecraft:enchant_randomly",
      "include_additional_cost_component": true,
      "only_compatible": false,
      "options": [
        "minecraft:infinity",
        "minecraft:multishot"
      ]
    },
    {
      "function": "minecraft:filtered",
      "item_filter": {
        "items": "minecraft:enchanted_book",
        "predicates": {
          "minecraft:stored_enchantments": [
            {}
          ]
        }
      },
      "on_fail": {
        "function": "minecraft:discard"
      }
    }
  ],
  "gives": {
    "id": "minecraft:enchanted_book"
  },
  "max_uses": 12.0,
  "reputation_discount": 0.05,
  "wants": {
    "count": 32.0,
    "id": "tutorialmod:fluorite"
  },
  "xp": 6.0
}
```

### src/main/generated/data/tutorialmod/villager_trade/mason/1/fluorite_chisel.json

```json
{
  "gives": {
    "id": "tutorialmod:chisel"
  },
  "max_uses": 2.0,
  "reputation_discount": 0.05,
  "wants": {
    "count": 6.0,
    "id": "tutorialmod:fluorite"
  },
  "xp": 19.0
}
```

### src/main/generated/data/tutorialmod/worldgen/configured_feature/balsa.json

```json
{
  "type": "minecraft:tree",
  "config": {
    "below_trunk_provider": {
      "type": "minecraft:simple_state_provider",
      "state": {
        "Name": "minecraft:dirt"
      }
    },
    "decorators": [],
    "foliage_placer": {
      "type": "minecraft:blob_foliage_placer",
      "height": 3,
      "offset": 3,
      "radius": 2
    },
    "foliage_provider": {
      "type": "minecraft:simple_state_provider",
      "state": {
        "Name": "tutorialmod:balsa_leaves",
        "Properties": {
          "distance": "7",
          "persistent": "false",
          "waterlogged": "false"
        }
      }
    },
    "ignore_vines": false,
    "minimum_size": {
      "type": "minecraft:two_layers_feature_size",
      "upper_size": 2
    },
    "trunk_placer": {
      "type": "minecraft:bending_trunk_placer",
      "base_height": 3,
      "bend_length": 5,
      "height_rand_a": 3,
      "height_rand_b": 4,
      "min_height_for_leaves": 2
    },
    "trunk_provider": {
      "type": "minecraft:simple_state_provider",
      "state": {
        "Name": "tutorialmod:balsa_log",
        "Properties": {
          "axis": "y"
        }
      }
    }
  }
}
```

### src/main/generated/data/tutorialmod/worldgen/configured_feature/end_fluorite_ore.json

```json
{
  "type": "minecraft:ore",
  "config": {
    "discard_chance_on_air_exposure": 0.0,
    "size": 12,
    "targets": [
      {
        "state": {
          "Name": "tutorialmod:fluorite_end_ore"
        },
        "target": {
          "block": "minecraft:end_stone",
          "predicate_type": "minecraft:block_match"
        }
      }
    ]
  }
}
```

### src/main/generated/data/tutorialmod/worldgen/configured_feature/honey_berry_bush.json

```json
{
  "type": "minecraft:simple_random_selector",
  "config": {
    "features": {
      "feature": {
        "type": "minecraft:simple_block",
        "config": {
          "to_place": {
            "type": "minecraft:simple_state_provider",
            "state": {
              "Name": "tutorialmod:honey_berry_bush",
              "Properties": {
                "age": "3"
              }
            }
          }
        }
      },
      "placement": [
        {
          "type": "minecraft:count",
          "count": 32
        },
        {
          "type": "minecraft:random_offset",
          "xz_spread": {
            "type": "minecraft:trapezoid",
            "max": 6,
            "min": -6,
            "plateau": 0
          },
          "y_spread": {
            "type": "minecraft:trapezoid",
            "max": 3,
            "min": -3,
            "plateau": 0
          }
        },
        {
          "type": "minecraft:block_predicate_filter",
          "predicate": {
            "type": "minecraft:matching_block_tag",
            "tag": "minecraft:air"
          }
        }
      ]
    }
  }
}
```

### src/main/generated/data/tutorialmod/worldgen/configured_feature/nether_fluorite_ore.json

```json
{
  "type": "minecraft:ore",
  "config": {
    "discard_chance_on_air_exposure": 0.0,
    "size": 10,
    "targets": [
      {
        "state": {
          "Name": "tutorialmod:fluorite_nether_ore"
        },
        "target": {
          "predicate_type": "minecraft:tag_match",
          "tag": "minecraft:base_stone_nether"
        }
      }
    ]
  }
}
```

### src/main/generated/data/tutorialmod/worldgen/configured_feature/overworld_fluorite_ore.json

```json
{
  "type": "minecraft:ore",
  "config": {
    "discard_chance_on_air_exposure": 0.0,
    "size": 9,
    "targets": [
      {
        "state": {
          "Name": "tutorialmod:fluorite_ore"
        },
        "target": {
          "predicate_type": "minecraft:tag_match",
          "tag": "minecraft:stone_ore_replaceables"
        }
      },
      {
        "state": {
          "Name": "tutorialmod:fluorite_deepslate_ore"
        },
        "target": {
          "predicate_type": "minecraft:tag_match",
          "tag": "minecraft:deepslate_ore_replaceables"
        }
      }
    ]
  }
}
```

### src/main/generated/data/tutorialmod/worldgen/placed_feature/balsa_placed.json

```json
{
  "feature": "tutorialmod:balsa",
  "placement": [
    {
      "type": "minecraft:count",
      "count": {
        "type": "minecraft:weighted_list",
        "distribution": [
          {
            "data": 3,
            "weight": 9
          },
          {
            "data": 5,
            "weight": 1
          }
        ]
      }
    },
    {
      "type": "minecraft:in_square"
    },
    {
      "type": "minecraft:surface_water_depth_filter",
      "max_water_depth": 0
    },
    {
      "type": "minecraft:heightmap",
      "heightmap": "OCEAN_FLOOR"
    },
    {
      "type": "minecraft:biome"
    },
    {
      "type": "minecraft:block_predicate_filter",
      "predicate": {
        "type": "minecraft:would_survive",
        "state": {
          "Name": "tutorialmod:balsa_sapling",
          "Properties": {
            "stage": "0"
          }
        }
      }
    }
  ]
}
```

### src/main/generated/data/tutorialmod/worldgen/placed_feature/end_fluorite_ore_placed.json

```json
{
  "feature": "tutorialmod:end_fluorite_ore",
  "placement": [
    {
      "type": "minecraft:count",
      "count": 12
    },
    {
      "type": "minecraft:in_square"
    },
    {
      "type": "minecraft:height_range",
      "height": {
        "type": "minecraft:trapezoid",
        "max_inclusive": {
          "absolute": 100
        },
        "min_inclusive": {
          "absolute": -64
        }
      }
    },
    {
      "type": "minecraft:biome"
    }
  ]
}
```

### src/main/generated/data/tutorialmod/worldgen/placed_feature/honey_berry_bush_placed.json

```json
{
  "feature": "tutorialmod:honey_berry_bush",
  "placement": [
    {
      "type": "minecraft:rarity_filter",
      "chance": 32
    },
    {
      "type": "minecraft:in_square"
    },
    {
      "type": "minecraft:heightmap",
      "heightmap": "WORLD_SURFACE_WG"
    },
    {
      "type": "minecraft:biome"
    }
  ]
}
```

### src/main/generated/data/tutorialmod/worldgen/placed_feature/nether_fluorite_ore_placed.json

```json
{
  "feature": "tutorialmod:nether_fluorite_ore",
  "placement": [
    {
      "type": "minecraft:count",
      "count": 9
    },
    {
      "type": "minecraft:in_square"
    },
    {
      "type": "minecraft:height_range",
      "height": {
        "type": "minecraft:trapezoid",
        "max_inclusive": {
          "absolute": 100
        },
        "min_inclusive": {
          "absolute": -32
        }
      }
    },
    {
      "type": "minecraft:biome"
    }
  ]
}
```

### src/main/generated/data/tutorialmod/worldgen/placed_feature/overworld_fluorite_ore_placed.json

```json
{
  "feature": "tutorialmod:overworld_fluorite_ore",
  "placement": [
    {
      "type": "minecraft:count",
      "count": 12
    },
    {
      "type": "minecraft:in_square"
    },
    {
      "type": "minecraft:height_range",
      "height": {
        "type": "minecraft:trapezoid",
        "max_inclusive": {
          "absolute": 100
        },
        "min_inclusive": {
          "absolute": -32
        }
      }
    },
    {
      "type": "minecraft:biome"
    }
  ]
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/custom/CrystallizerBlock.java

```java
package net.kaupenjoe.tutorialmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.block.entity.custom.CrystallizerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class CrystallizerBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final MapCodec<CrystallizerBlock> CODEC = simpleCodec(CrystallizerBlock::new);


    public CrystallizerBlock(Properties properties) {
        super(properties);
    }

    /* FACING */
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(LIT, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    /* BLOCK ENTITY */
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new CrystallizerBlockEntity(worldPosition, blockState);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state,
                              @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if(level.getBlockEntity(pos) instanceof CrystallizerBlockEntity crystallizerBlockEntity) {
            crystallizerBlockEntity.drops();
        }
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            if(level.getBlockEntity(pos) instanceof CrystallizerBlockEntity crystallizerBlockEntity) {
                player.openMenu(crystallizerBlockEntity);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
                                                                            BlockEntityType<T> type) {
        if(level.isClientSide()) {
            return null;
        }

        return createTickerHelper(type, ModBlockEntities.CRYSTALLIZER_BE,
                (level1, pos, state, entity) -> entity.tick(level1, pos, state));
    }

    /* LIT */
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!state.getValue(LIT)) {
            return;
        }

        double xPos = (double)pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.15) {
            level.playLocalSound(xPos, yPos, zPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0f, 1.0f, false);
        }

        Direction direction = state.getValue(FACING);
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : defaultOffset;

        level.addParticle(ParticleTypes.SMOKE, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);

        if(level.getBlockEntity(pos) instanceof CrystallizerBlockEntity crystallizerBlockEntity && !crystallizerBlockEntity.inventory.get(1).isEmpty()) {
            level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, crystallizerBlockEntity.inventory.get(1).getItem()),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/custom/FluoriteLampBlock.java

```java
package net.kaupenjoe.tutorialmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class FluoriteLampBlock extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");

    public FluoriteLampBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(CLICKED, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        level.setBlockAndUpdate(pos, state.cycle(CLICKED));
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLICKED);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/custom/HoneyBerryBushBlock.java

```java
package net.kaupenjoe.tutorialmod.block.custom;

import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class HoneyBerryBushBlock extends SweetBerryBushBlock {
    public HoneyBerryBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(ModItems.HONEY_BERRIES);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        boolean isGrown = age == 3;
        if(age > 1) {
            int count = 1 + level.getRandom().nextInt(2);
            popResource(level, pos, new ItemStack(ModItems.HONEY_BERRIES, count + (isGrown ? 1 : 0)));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS,
                    1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
            BlockState newState = state.setValue(AGE, 1);
            level.setBlock(pos, newState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/custom/MagicBlock.java

```java
package net.kaupenjoe.tutorialmod.block.custom;

import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.addParticle(ParticleTypes.GLOW, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 1, 0);
        level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS, 2f, 1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        // Add Effect to player
        if(entity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300));
        }

        // Change Item(s) to other Items
        if(entity instanceof ItemEntity itemEntity) {
            if(isValidItem(itemEntity.getItem())) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));
            }
        }

        super.stepOn(level, pos, onState, entity);
    }

    private boolean isValidItem(ItemStack item) {
        return item.is(ModTags.Items.TRANSFORMABLE_ITEMS);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/custom/PedestalBlock.java

```java
package net.kaupenjoe.tutorialmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.kaupenjoe.tutorialmod.block.entity.custom.PedestalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class PedestalBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);
    private static MapCodec<PedestalBlock> CODEC = simpleCodec(PedestalBlock::new);

    public PedestalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new PedestalBlockEntity(worldPosition, blockState);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state,
                              @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
            pedestalBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }

        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level,
                                          BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity) {
            if(player.isCrouching()) {
                player.openMenu(pedestalBlockEntity);
                return InteractionResult.SUCCESS;
            }

            boolean isPedestalEmpty = pedestalBlockEntity.isEmpty();

            // INSERT
            if(isPedestalEmpty && !itemStack.isEmpty()) {
                pedestalBlockEntity.setTheItem(itemStack);
                itemStack.shrink(1);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            }
            // EXTRACT
            else if(!isPedestalEmpty) {
                ItemStack stackOnPedestal = pedestalBlockEntity.getTheItem();
                pedestalBlockEntity.clearContent();

                if(!player.getInventory().add(stackOnPedestal)) {
                    player.drop(stackOnPedestal, false);
                }
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/custom/RiceCropBlock.java

```java
package net.kaupenjoe.tutorialmod.block.custom;

import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RiceCropBlock extends CropBlock {
    public RiceCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.RICE_SHOOT;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        return stateBelow.is(Blocks.WATER);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/custom/StrawberryCropBlock.java

```java
package net.kaupenjoe.tutorialmod.block.custom;

import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class StrawberryCropBlock extends CropBlock {
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

    public StrawberryCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.STRAWBERRY_SEEDS;
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/entity/custom/CrystallizerBlockEntity.java

```java
package net.kaupenjoe.tutorialmod.block.entity.custom;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.kaupenjoe.tutorialmod.block.custom.CrystallizerBlock;
import net.kaupenjoe.tutorialmod.block.entity.ImplementedInventory;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.menu.custom.CrystallizerMenu;
import net.kaupenjoe.tutorialmod.recipe.ModRecipes;
import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipe;
import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class CrystallizerBlockEntity extends BlockEntity implements ExtendedMenuProvider<BlockPos>, ImplementedInventory {
    public final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public CrystallizerBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.CRYSTALLIZER_BE, worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> CrystallizerBlockEntity.this.progress;
                    case 1 -> CrystallizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0: CrystallizerBlockEntity.this.progress = value;
                    case 1: CrystallizerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }


    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("crystallizer.progress", progress);
        output.putInt("crystallizer.max_progress", maxProgress);

        ContainerHelper.saveAllItems(output, inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        progress = input.getIntOr("crystallizer.progress", 0);
        maxProgress = input.getIntOr("crystallizer.max_progress", 72);

        ContainerHelper.loadAllItems(input, inventory);
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return this.worldPosition;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.tutorialmod.crystallizer");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new CrystallizerMenu(containerId, inventory, this, this.data);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if(hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            increaseCraftingProgress();
            level.setBlockAndUpdate(pos, state.setValue(CrystallizerBlock.LIT, true));
            setChanged(level, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
            level.setBlockAndUpdate(pos, state.setValue(CrystallizerBlock.LIT, false));
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<CrystallizerRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().assemble(new CrystallizerRecipeInput(inventory.get(INPUT_SLOT)));
        boolean isItemOutputRight = canInsertItemIntoOutputSlot(output);
        boolean isAmountRight = canInsertAmountIntoOutputSlot(output.getCount());

        return isItemOutputRight && isAmountRight;
    }

    private Optional<RecipeHolder<CrystallizerRecipe>> getCurrentRecipe() {
        return ((ServerLevel) level).recipeAccess()
                .getRecipeFor(ModRecipes.CRYSTALLIZER_TYPE, new CrystallizerRecipeInput(inventory.get(INPUT_SLOT)), level);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.get(OUTPUT_SLOT).isEmpty() ? 64 : inventory.get(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventory.get(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.get(OUTPUT_SLOT).isEmpty() ||
                inventory.get(OUTPUT_SLOT).is(output.getItem());
    }

    private void craftItem() {
        Optional<RecipeHolder<CrystallizerRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().assemble(new CrystallizerRecipeInput(inventory.get(INPUT_SLOT)));

        inventory.set(INPUT_SLOT, inventory.get(INPUT_SLOT).copyWithCount(inventory.get(INPUT_SLOT).getCount() - 1));
        inventory.set(OUTPUT_SLOT, output.copyWithCount(inventory.get(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return inventory.get(OUTPUT_SLOT).isEmpty() ||
                inventory.get(OUTPUT_SLOT).getCount() < inventory.get(OUTPUT_SLOT).getMaxStackSize();
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 72;
    }

    /* BLOCK ENTITY SYNC METHODS */
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if(!level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/entity/custom/PedestalBlockEntity.java

```java
package net.kaupenjoe.tutorialmod.block.entity.custom;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.menu.custom.PedestalMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jspecify.annotations.Nullable;

public class PedestalBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem, ExtendedMenuProvider<BlockPos> {
    public NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    public PedestalBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.PEDESTAL_BE, worldPosition, blockState);
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getTheItem() {
        return inventory.getFirst();
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
        setChanged();
        inventory.set(0, itemStack.copyWithCount(1));
    }

    @Override
    public void clearContent() {
        inventory.set(0, ItemStack.EMPTY);
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, this.inventory);
    }

    /* SAVING DATA */
    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, this.inventory);
    }

    /* MENU METHODS */
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.tutorialmod.pedestal");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new PedestalMenu(containerId, inventory, this);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return this.worldPosition;
    }

    /* BLOCK ENTITY SYNC METHODS */
    @Override
    public void setChanged() {
        super.setChanged();
        if(!level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/entity/ImplementedInventory.java

```java
package net.kaupenjoe.tutorialmod.block.entity;

import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A simple {@code SidedInventory} implementation with only default methods + an item list getter.
 *
 * <h2>Reading and writing to tags</h2>
 * Use {@link ContainerHelper#saveAllItems(ValueOutput, NonNullList)} and
 * {@link ContainerHelper#loadAllItems(ValueInput, NonNullList)}
 * on {@linkplain #getItems() the item list}.
 * <p>
 * License: <a href="https://creativecommons.org/publicdomain/zero/1.0/">CC0</a>
 * @author Juuz
 */
@FunctionalInterface
public interface ImplementedInventory extends WorldlyContainer {
    /**
     * Gets the item list of this inventory.
     * Must return the same instance every time it's called.
     *
     * @return the item list
     */
    NonNullList<ItemStack> getItems();

    /**
     * Creates an inventory from the item list.
     *
     * @param items the item list
     * @return a new inventory
     */
    static ImplementedInventory of(NonNullList<ItemStack> items) {
        return () -> items;
    }

    /**
     * Creates a new inventory with the size.
     *
     * @param size the inventory size
     * @return a new inventory
     */
    static ImplementedInventory ofSize(int size) {
        return of(NonNullList.withSize(size, ItemStack.EMPTY));
    }

    // SidedInventory

    /**
     * Gets the available slots to automation on the side.
     *
     * <p>The default implementation returns an array of all slots.
     *
     * @param side the side
     * @return the available slots
     */
    @Override
    default int[] getSlotsForFace(Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    /**
     * Returns true if the stack can be inserted in the slot at the side.
     *
     * <p>The default implementation returns true.
     *
     * @param slot the slot
     * @param stack the stack
     * @param side the side
     * @return true if the stack can be inserted
     */
    @Override
    default boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side) {
        return true;
    }

    /**
     * Returns true if the stack can be extracted from the slot at the side.
     *
     * <p>The default implementation returns true.
     *
     * @param slot the slot
     * @param stack the stack
     * @param side the side
     * @return true if the stack can be extracted
     */
    @Override
    default boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
        return true;
    }

    // Inventory

    /**
     * Returns the inventory size.
     *
     * <p>The default implementation returns the size of {@link #getItems()}.
     *
     * @return the inventory size
     */
    @Override
    default int getContainerSize() {
        return getItems().size();
    }

    /**
     * @return true if this inventory has only empty stacks, false otherwise
     */
    @Override
    default boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the item in the slot.
     *
     * @param slot the slot
     * @return the item in the slot
     */
    @Override
    default ItemStack getItem(int slot) {
        return getItems().get(slot);
    }

    /**
     * Takes a stack of the size from the slot.
     *
     * <p>(default implementation) If there are less items in the slot than what are requested,
     * takes all items in that slot.
     *
     * @param slot the slot
     * @param count the item count
     * @return a stack
     */
    @Override
    default ItemStack removeItem(int slot, int count) {
        ItemStack result = ContainerHelper.removeItem(getItems(), slot, count);
        if (!result.isEmpty()) {
            setChanged();
        }

        return result;
    }

    /**
     * Removes the current stack in the {@code slot} and returns it.
     *
     * <p>The default implementation uses {@link ContainerHelper#takeItem(List, int)}
     *
     * @param slot the slot
     * @return the removed stack
     */
    @Override
    default ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(getItems(), slot);
    }

    /**
     * Replaces the current stack in the {@code slot} with the provided stack.
     *
     * <p>If the stack is too big for this inventory ({@link Container#getMaxStackSize()}),
     * it gets resized to this inventory's maximum amount.
     *
     * @param slot the slot
     * @param stack the stack
     */
    @Override
    default void setItem(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
    }

    /**
     * Clears {@linkplain #getItems() the item list}}.
     */
    @Override
    default void clearContent() {
        getItems().clear();
    }

    @Override
    default void setChanged() {
        // Override if you want behavior.
    }

    @Override
    default boolean stillValid(Player player) {
        return true;
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/entity/ModBlockEntities.java

```java
package net.kaupenjoe.tutorialmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.entity.custom.CrystallizerBlockEntity;
import net.kaupenjoe.tutorialmod.block.entity.custom.PedestalBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "pedestal_be"),
                    FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL_BLOCK).build());

    public static final BlockEntityType<CrystallizerBlockEntity> CRYSTALLIZER_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "crystallizer_be"),
                    FabricBlockEntityTypeBuilder.create(CrystallizerBlockEntity::new, ModBlocks.CRYSTALLIZER).build());


    public static void registerBlockEntities() {
        TutorialMod.LOGGER.info("Registering ModBlockEntities for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/entity/renderer/PedestalBlockEntityRenderer.java

```java
package net.kaupenjoe.tutorialmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.kaupenjoe.tutorialmod.block.entity.custom.PedestalBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity, PedestalBlockEntityRenderState> {
    private final ItemModelResolver itemModelResolver;

    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        itemModelResolver = context.itemModelResolver();
    }

    @Override
    public PedestalBlockEntityRenderState createRenderState() {
        return new PedestalBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(PedestalBlockEntity blockEntity, PedestalBlockEntityRenderState state,
                                   float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);

        state.level = blockEntity.getLevel();
        state.rotation = (blockEntity.getLevel().getGameTime() + partialTicks * 0.5f) % 360f;

        itemModelResolver.updateForTopItem(state.itemStackRenderState,
                blockEntity.getTheItem(), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
    }

    @Override
    public void submit(PedestalBlockEntityRenderState state, PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();

        poseStack.translate(0.5f, 1.15f, 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.rotation));

        state.itemStackRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

        poseStack.popPose();
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/entity/renderer/PedestalBlockEntityRenderState.java

```java
package net.kaupenjoe.tutorialmod.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.level.Level;

public class PedestalBlockEntityRenderState extends BlockEntityRenderState {
    public Level level;
    public float rotation;

    final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
}
```

### src/main/java/net/kaupenjoe/tutorialmod/block/ModBlocks.java

```java
package net.kaupenjoe.tutorialmod.block;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.custom.*;
import net.kaupenjoe.tutorialmod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModBlocks {
    public static final Block FLUORITE_BLOCK = registerBlock("fluorite_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final Block RAW_FLUORITE_BLOCK = registerBlock("raw_fluorite_block",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final Block FLUORITE_ORE = registerBlock("fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5),
                    properties.strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final Block FLUORITE_DEEPSLATE_ORE = registerBlock("fluorite_deepslate_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3, 6),
                    properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final Block FLUORITE_NETHER_ORE = registerBlock("fluorite_nether_ore",
            properties -> new DropExperienceBlock(UniformInt.of(1, 5),
                    properties.strength(3f).requiresCorrectToolForDrops()));
    public static final Block FLUORITE_END_ORE = registerBlock("fluorite_end_ore",
            properties -> new DropExperienceBlock(UniformInt.of(4, 8),
                    properties.strength(6f).requiresCorrectToolForDrops()));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(properties.strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)), Component.translatable("tooltip.tutorialmod.magic_block"));


    public static final Block FLUORITE_STAIRS = registerBlock("fluorite_stairs",
            properties -> new StairBlock(ModBlocks.FLUORITE_BLOCK.defaultBlockState(),
                    properties.strength(3f).requiresCorrectToolForDrops()));
    public static final Block FLUORITE_SLAB = registerBlock("fluorite_slab",
            properties -> new SlabBlock(properties.strength(3f).requiresCorrectToolForDrops()));

    public static final Block FLUORITE_BUTTON = registerBlock("fluorite_button",
            properties -> new ButtonBlock(BlockSetType.IRON, 20,
                    properties.strength(3f).noCollision()));
    public static final Block FLUORITE_PRESSURE_PLATE = registerBlock("fluorite_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.IRON,
                    properties.mapColor(MapColor.COLOR_BLUE).forceSolidOn().instrument(NoteBlockInstrument.BASS)
                            .noCollision().strength(0.5F).pushReaction(PushReaction.DESTROY)));

    public static final Block FLUORITE_FENCE = registerBlock("fluorite_fence",
            properties -> new FenceBlock(properties.strength(3f).requiresCorrectToolForDrops()));
    public static final Block FLUORITE_FENCE_GATE = registerBlock("fluorite_fence_gate",
            properties -> new FenceGateBlock(WoodType.ACACIA,
                    properties.strength(3f).requiresCorrectToolForDrops()));
    public static final Block FLUORITE_WALL = registerBlock("fluorite_wall",
            properties -> new WallBlock(properties.strength(3f).requiresCorrectToolForDrops()));

    public static final Block FLUORITE_DOOR = registerBlock("fluorite_door",
            properties -> new DoorBlock(BlockSetType.IRON, properties.strength(3f)
                    .requiresCorrectToolForDrops().noOcclusion()));
    public static final Block FLUORITE_TRAPDOOR = registerBlock("fluorite_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.IRON, properties.strength(3f)
                    .requiresCorrectToolForDrops().noOcclusion()));

    public static final Block FLUORITE_LAMP = registerBlock("fluorite_lamp",
            properties -> new FluoriteLampBlock(properties.strength(3f)
                    .requiresCorrectToolForDrops().lightLevel(state -> state.getValue(FluoriteLampBlock.CLICKED) ? 15 : 0)));

    public static final Block PEDESTAL_BLOCK = registerBlock("pedestal",
            properties -> new PedestalBlock(properties.strength(3f).requiresCorrectToolForDrops()));

    public static final Block STRAWBERRY_CROP = registerBlockWithoutBlockItem("strawberry_crop",
            properties -> new StrawberryCropBlock(properties.noCollision().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)));
    public static final Block HONEY_BERRY_BUSH = registerBlockWithoutBlockItem("honey_berry_bush",
            properties -> new HoneyBerryBushBlock(properties.randomTicks().noCollision().sound(SoundType.SWEET_BERRY_BUSH)
                    .pushReaction(PushReaction.DESTROY)));
    public static final Block RICE_CROP = registerBlockWithoutBlockItem("rice_crop",
            properties -> new RiceCropBlock(properties.noCollision().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)));

    public static final Block CRYSTALLIZER = registerBlock("crystallizer",
            properties -> new CrystallizerBlock(properties.strength(3f).requiresCorrectToolForDrops()));

    public static final Block BALSA_LOG = registerBlock("balsa_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block BALSA_WOOD = registerBlock("balsa_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_BALSA_LOG = registerBlock("stripped_balsa_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));
    public static final Block STRIPPED_BALSA_WOOD = registerBlock("stripped_balsa_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD).ignitedByLava()));

    public static final Block BALSA_PLANKS = registerBlock("balsa_planks",
            properties -> new Block(properties
                    .mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Block BALSA_LEAVES = registerBlock("balsa_leaves",
            properties -> new UntintedParticleLeavesBlock(0.01f, ParticleTypes.CHERRY_LEAVES, properties
                    .mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES)
                    .noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(Blocks::never)));

    public static final Block BALSA_SAPLING = registerBlock("balsa_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.BALSA, properties
                    .mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak()
                    .sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Block POTTED_BALSA_SAPLING = registerBlockWithoutBlockItem("potted_balsa_sapling",
            properties -> new FlowerPotBlock(BALSA_SAPLING, properties
                    .instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));


    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function, Component... tooltips) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name))));
        registerBlockItem(name, toRegister, tooltips);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block, Component... tooltips) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))) {
                    @Override
                    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                        for(var component : tooltips) {
                            builder.accept(component);
                        }
                        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                    }
                });
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), toRegister);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/compat/custom/CrystallizerCategory.java

```java
package net.kaupenjoe.tutorialmod.compat.custom;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.compat.TutorialModREICommon;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.LinkedList;
import java.util.List;

public class CrystallizerCategory implements DisplayCategory<Display> {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,
            "textures/gui/crystallizer/crystallizer_gui.png");

    @Override
    public CategoryIdentifier<? extends Display> getCategoryIdentifier() {
        return TutorialModREICommon.CRYSTALLIZER;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.tutorialmod.crystallizer");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRYSTALLIZER.asItem().getDefaultInstance());
    }

    @Override
    public List<Widget> setupDisplay(Display display, Rectangle bounds) {
        List<Widget> widgets = new LinkedList<>();
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);

        widgets.add(Widgets.createTexturedWidget(TEXTURE,
                new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 54, startPoint.y + 34))
                .entries(display.getInputEntries().getFirst()).markInput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 104, startPoint.y + 34))
                .entries(display.getOutputEntries().getFirst()).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/compat/custom/CrystallizerDisplay.java

```java
package net.kaupenjoe.tutorialmod.compat.custom;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.kaupenjoe.tutorialmod.compat.TutorialModREICommon;
import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipe;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record CrystallizerDisplay(EntryIngredient input, EntryIngredient output,
                                  Optional<Identifier> location) implements Display {
    public static final DisplaySerializer<CrystallizerDisplay> SERIALIZER = DisplaySerializer.of(
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    EntryIngredient.codec().fieldOf("input").forGetter(CrystallizerDisplay::input),
                    EntryIngredient.codec().fieldOf("output").forGetter(CrystallizerDisplay::output),
                    Identifier.CODEC.optionalFieldOf("location").forGetter(CrystallizerDisplay::location)
            ).apply(instance, CrystallizerDisplay::new)),
            StreamCodec.composite(
                    EntryIngredient.streamCodec(),
                    CrystallizerDisplay::input,

                    EntryIngredient.streamCodec(),
                    CrystallizerDisplay::output,

                    ByteBufCodecs.optional(Identifier.STREAM_CODEC),
                    CrystallizerDisplay::location,

                    CrystallizerDisplay::new));

    public CrystallizerDisplay(RecipeHolder<CrystallizerRecipe> entry) {
        this(entry.id().identifier(), entry.value());
    }

    public CrystallizerDisplay(Identifier id, CrystallizerRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.inputItem()), EntryIngredients.of(recipe.output().create()), Optional.of(id));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(input);
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(output);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return TutorialModREICommon.CRYSTALLIZER;
    }

    @Override
    public Optional<Identifier> getDisplayLocation() {
        return location;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/compat/TutorialModREIClient.java

```java
package net.kaupenjoe.tutorialmod.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.compat.custom.CrystallizerCategory;
import net.kaupenjoe.tutorialmod.menu.custom.CrystallizerScreen;

public class TutorialModREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrystallizerCategory());

        registry.addWorkstations(TutorialModREICommon.CRYSTALLIZER, EntryStacks.of(ModBlocks.CRYSTALLIZER));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25),
                CrystallizerScreen.class, TutorialModREICommon.CRYSTALLIZER);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/compat/TutorialModREICommon.java

```java
package net.kaupenjoe.tutorialmod.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.compat.custom.CrystallizerDisplay;
import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipe;

public class TutorialModREICommon implements REICommonPlugin {
    public static final CategoryIdentifier<CrystallizerDisplay> CRYSTALLIZER =
            CategoryIdentifier.of(TutorialMod.MOD_ID, "crystallizer");

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(CRYSTALLIZER.getIdentifier(), CrystallizerDisplay.SERIALIZER);
    }

    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(CrystallizerRecipe.class).fill(CrystallizerDisplay::new);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/creativemodetab/ModCreativeModeTabs.java

```java
package net.kaupenjoe.tutorialmod.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab FLUORITE_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fluorite_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FLUORITE))
                    .title(Component.translatable("creativemodetab.tutorialmod.fluorite_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.FLUORITE);
                        output.accept(ModItems.RAW_FLUORITE);

                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.STRAWBERRY);

                        output.accept(ModItems.COMBUSTIBLE_SPORES);

                        output.accept(ModItems.FLUORITE_SWORD);
                        output.accept(ModItems.FLUORITE_PICKAXE);
                        output.accept(ModItems.FLUORITE_SHOVEL);
                        output.accept(ModItems.FLUORITE_AXE);
                        output.accept(ModItems.FLUORITE_HOE);
                        output.accept(ModItems.FLUORITE_SPEAR);

                        output.accept(ModItems.FLUORITE_HELMET);
                        output.accept(ModItems.FLUORITE_CHESTPLATE);
                        output.accept(ModItems.FLUORITE_LEGGINGS);
                        output.accept(ModItems.FLUORITE_BOOTS);

                        output.accept(ModItems.FLUORITE_HORSE_ARMOR);
                        output.accept(ModItems.KAUPEN_BOW);
                        output.accept(ModItems.SCULKBEAM_STAFF);

                        output.accept(ModItems.STRAWBERRY_SEEDS);
                        output.accept(ModItems.HONEY_BERRIES);
                        output.accept(ModItems.RICE_SHOOT);

                        output.accept(ModItems.BAR_BRAWL_MUSIC_DISC);
                        output.accept(ModItems.SPECTRE_STAFF);


                    }).build());

    public static final CreativeModeTab FLUORITE_BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fluorite_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.FLUORITE_BLOCK))
                    .title(Component.translatable("creativemodetab.tutorialmod.fluorite_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.FLUORITE_BLOCK);
                        output.accept(ModBlocks.RAW_FLUORITE_BLOCK);
                        output.accept(ModBlocks.FLUORITE_ORE);
                        output.accept(ModBlocks.FLUORITE_DEEPSLATE_ORE);

                        output.accept(ModBlocks.FLUORITE_NETHER_ORE);
                        output.accept(ModBlocks.FLUORITE_END_ORE);
                        output.accept(ModBlocks.MAGIC_BLOCK);

                        output.accept(ModBlocks.FLUORITE_STAIRS);
                        output.accept(ModBlocks.FLUORITE_SLAB);
                        output.accept(ModBlocks.FLUORITE_BUTTON);
                        output.accept(ModBlocks.FLUORITE_PRESSURE_PLATE);
                        output.accept(ModBlocks.FLUORITE_FENCE);
                        output.accept(ModBlocks.FLUORITE_FENCE_GATE);
                        output.accept(ModBlocks.FLUORITE_WALL);
                        output.accept(ModBlocks.FLUORITE_DOOR);
                        output.accept(ModBlocks.FLUORITE_TRAPDOOR);

                        output.accept(ModBlocks.FLUORITE_LAMP);
                        output.accept(ModBlocks.PEDESTAL_BLOCK);
                        output.accept(ModBlocks.CRYSTALLIZER);

                        output.accept(ModBlocks.BALSA_LOG);
                        output.accept(ModBlocks.BALSA_WOOD);
                        output.accept(ModBlocks.STRIPPED_BALSA_LOG);
                        output.accept(ModBlocks.STRIPPED_BALSA_WOOD);

                        output.accept(ModBlocks.BALSA_PLANKS);
                        output.accept(ModBlocks.BALSA_LEAVES);

                        output.accept(ModBlocks.BALSA_SAPLING);


                    }).build());


    public static void registerModCreativeModeTabs() {
        TutorialMod.LOGGER.info("Registering Creative Mode Tabs for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/data/ModDataComponents.java

```java
package net.kaupenjoe.tutorialmod.data;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<BlockPos> COORDINATES = register("coordinates",
            builder -> builder.persistent(BlockPos.CODEC).networkSynchronized(BlockPos.STREAM_CODEC));


    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void registerDataComponents() {
        TutorialMod.LOGGER.info("Registering Data Components for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModAdvancementsProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.BlockPredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends AdvancementProvider {
    public ModAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new TutorialModAdvancements()));
    }

    public static class TutorialModAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);
            var blocks = registries.lookupOrThrow(Registries.BLOCK);

            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            ModItems.FLUORITE,
                            Component.translatable("advancements.tutorialmod.root.title"),
                            Component.translatable("advancements.tutorialmod.root.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            false,
                            false,
                            false
                    )
                    .addCriterion("has_fluorite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.FLUORITE)))
                    .save(output, TutorialMod.MOD_ID + ":tutorialmod/root");


            AdvancementHolder plantSeed = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.RICE_SHOOT,
                            Component.translatable("advancements.tutorialmod.plant_custom.title"),
                            Component.translatable("advancements.tutorialmod.plant_custom.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("berries", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.HONEY_BERRY_BUSH))
                    .addCriterion("rice", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.RICE_CROP))
                    .addCriterion("strawberry", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.STRAWBERRY_CROP))
                    .save(output, TutorialMod.MOD_ID + ":tutorialmod/plant_custom");

            AdvancementHolder useChisel = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.CHISEL,
                            Component.translatable("advancements.tutorialmod.chisel_stone.title"),
                            Component.translatable("advancements.tutorialmod.chisel_stone.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("chisel_stone", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location(),
                            ItemPredicate.Builder.item().of(items, ModItems.CHISEL.asItem())))
                    .save(output, TutorialMod.MOD_ID + ":tutorialmod/chisel_stone");

        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModBlockLootTableProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.custom.HoneyBerryBushBlock;
import net.kaupenjoe.tutorialmod.block.custom.RiceCropBlock;
import net.kaupenjoe.tutorialmod.block.custom.StrawberryCropBlock;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        var enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        dropSelf(ModBlocks.FLUORITE_BLOCK);
        dropSelf(ModBlocks.RAW_FLUORITE_BLOCK);

        add(ModBlocks.FLUORITE_ORE, createOreDrop(ModBlocks.FLUORITE_ORE, ModItems.RAW_FLUORITE));
        add(ModBlocks.FLUORITE_DEEPSLATE_ORE, createOreDrop(ModBlocks.FLUORITE_DEEPSLATE_ORE, ModItems.RAW_FLUORITE));

        add(ModBlocks.FLUORITE_NETHER_ORE, createMultipleOreDrops(ModBlocks.FLUORITE_NETHER_ORE, ModItems.RAW_FLUORITE, 3, 6));
        add(ModBlocks.FLUORITE_END_ORE, createMultipleOreDrops(ModBlocks.FLUORITE_END_ORE, ModItems.RAW_FLUORITE, 5, 8));

        dropSelf(ModBlocks.MAGIC_BLOCK);
        dropSelf(ModBlocks.FLUORITE_STAIRS);
        add(ModBlocks.FLUORITE_SLAB, this::createSlabItemTable);

        dropSelf(ModBlocks.FLUORITE_BUTTON);
        dropSelf(ModBlocks.FLUORITE_PRESSURE_PLATE);
        dropSelf(ModBlocks.FLUORITE_FENCE);
        dropSelf(ModBlocks.FLUORITE_FENCE_GATE);
        dropSelf(ModBlocks.FLUORITE_WALL);
        dropSelf(ModBlocks.FLUORITE_TRAPDOOR);

        add(ModBlocks.FLUORITE_DOOR, this::createDoorTable);

        dropSelf(ModBlocks.FLUORITE_LAMP);
        dropSelf(ModBlocks.PEDESTAL_BLOCK);
        dropSelf(ModBlocks.CRYSTALLIZER);

        this.add(ModBlocks.STRAWBERRY_CROP, this.createCropDrops(ModBlocks.STRAWBERRY_CROP, ModItems.STRAWBERRY, ModItems.STRAWBERRY_SEEDS,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, StrawberryCropBlock.MAX_AGE))));

        this.add(ModBlocks.HONEY_BERRY_BUSH, block -> this.applyExplosionDecay(block,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HoneyBerryBushBlock.AGE, 3)))
                        .add(LootItem.lootTableItem(ModItems.HONEY_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HoneyBerryBushBlock.AGE, 2)))
                        .add(LootItem.lootTableItem(ModItems.HONEY_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))))
        );

        this.add(ModBlocks.RICE_CROP, this.createCropDrops(ModBlocks.RICE_CROP, ModItems.RICE_SHOOT, ModItems.RICE_SHOOT,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceCropBlock.AGE, RiceCropBlock.MAX_AGE))));


        dropSelf(ModBlocks.BALSA_LOG);
        dropSelf(ModBlocks.BALSA_WOOD);
        dropSelf(ModBlocks.STRIPPED_BALSA_LOG);
        dropSelf(ModBlocks.STRIPPED_BALSA_WOOD);
        dropSelf(ModBlocks.BALSA_PLANKS);
        // NOTE: This should drop the Sapling instead!
        add(ModBlocks.BALSA_LEAVES, block -> createLeavesDrops(block, ModBlocks.BALSA_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.BALSA_SAPLING);
        add(ModBlocks.POTTED_BALSA_SAPLING, block -> createPotFlowerItemTable(ModBlocks.BALSA_SAPLING));

    }

    public LootTable.Builder createMultipleOreDrops(final Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(
                block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModBlockTagsProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.RAW_FLUORITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_DEEPSLATE_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_NETHER_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_END_ORE))
                .add(ModBlocks.getRK(ModBlocks.MAGIC_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE_GATE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_WALL))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_DOOR))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_TRAPDOOR))
                .add(ModBlocks.getRK(ModBlocks.PEDESTAL_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.CRYSTALLIZER));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_DEEPSLATE_ORE));

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_NETHER_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_END_ORE));

        tag(BlockTags.STAIRS).add(ModBlocks.getRK(ModBlocks.FLUORITE_STAIRS));
        tag(BlockTags.SLABS).add(ModBlocks.getRK(ModBlocks.FLUORITE_SLAB));
        tag(BlockTags.BUTTONS).add(ModBlocks.getRK(ModBlocks.FLUORITE_BUTTON));
        tag(BlockTags.PRESSURE_PLATES).add(ModBlocks.getRK(ModBlocks.FLUORITE_PRESSURE_PLATE));

        tag(BlockTags.FENCES).add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE));
        tag(BlockTags.FENCE_GATES).add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE_GATE));
        tag(BlockTags.WALLS).add(ModBlocks.getRK(ModBlocks.FLUORITE_WALL));

        tag(BlockTags.DOORS).add(ModBlocks.getRK(ModBlocks.FLUORITE_DOOR));
        tag(BlockTags.TRAPDOORS).add(ModBlocks.getRK(ModBlocks.FLUORITE_TRAPDOOR));

        tag(ModTags.Blocks.NEEDS_FLUORITE_TOOL)
                .add(ModBlocks.getRK(ModBlocks.MAGIC_BLOCK))
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_FLUORITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(BlockTags.CROPS)
                .add(ModBlocks.getRK(ModBlocks.STRAWBERRY_CROP));

        tag(ModTags.Blocks.BALSA_LOGS)
                .add(ModBlocks.getRK(ModBlocks.BALSA_LOG))
                .add(ModBlocks.getRK(ModBlocks.BALSA_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_BALSA_LOG))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_BALSA_WOOD));

        tag(BlockTags.LOGS)
                .addTag(ModTags.Blocks.BALSA_LOGS);

        tag(BlockTags.PLANKS)
                .add(ModBlocks.getRK(ModBlocks.BALSA_PLANKS));

        tag(BlockTags.LEAVES)
                .add(ModBlocks.getRK(ModBlocks.BALSA_LEAVES));

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.getRK(ModBlocks.POTTED_BALSA_SAPLING));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModDamageTypes.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> STINKY = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "stinky"));

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(STINKY, new DamageType("stinky", 0.1f, DamageEffects.HURT));
    }


    public static DamageSource create(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModEquipmentAssetProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.item.ModArmorMaterials;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public ModEquipmentAssetProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(ModArmorMaterials.FLUORITE_KEY,
                EquipmentClientInfo.builder()
                        .addHumanoidLayers(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fluorite"))
                        .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY,
                                new EquipmentClientInfo.Layer(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fluorite")))
                        .build());
    }

    @Override
    public CompletableFuture<?> run(final CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap();
        bootstrap((id, asset) -> {
            if (equipmentAssets.putIfAbsent(id, asset) != null) {
                throw new IllegalStateException("Tried to register equipment asset twice for id: " + id);
            }
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
    }

    @Override
    public String getName() {
        return "Tutorial Mod Equipment Asset Definitions";
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModItemTagsProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.getRK(ModItems.FLUORITE))
                .add(ItemIds.IRON_INGOT)
                .add(ItemIds.COAL)
                .add(ItemIds.BRICK);

        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.FLUORITE_SWORD));
        tag(ItemTags.PICKAXES).add(ModItems.getRK(ModItems.FLUORITE_PICKAXE));
        tag(ItemTags.SHOVELS).add(ModItems.getRK(ModItems.FLUORITE_SHOVEL));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.FLUORITE_AXE));
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.FLUORITE_HOE));
        tag(ItemTags.SPEARS).add(ModItems.getRK(ModItems.FLUORITE_SPEAR));

        tag(ItemTags.HEAD_ARMOR).add(ModItems.getRK(ModItems.FLUORITE_HELMET));
        tag(ItemTags.CHEST_ARMOR).add(ModItems.getRK(ModItems.FLUORITE_CHESTPLATE));
        tag(ItemTags.LEG_ARMOR).add(ModItems.getRK(ModItems.FLUORITE_LEGGINGS));
        tag(ItemTags.FOOT_ARMOR).add(ModItems.getRK(ModItems.FLUORITE_BOOTS));

        tag(ItemTags.BOW_ENCHANTABLE).add(ModItems.getRK(ModItems.KAUPEN_BOW));

        tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(ModItems.getRK(ModItems.BAR_BRAWL_MUSIC_DISC));

        tag(ModTags.Items.BALSA_LOGS)
                .add(ModItems.getRK(ModBlocks.BALSA_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.BALSA_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_BALSA_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_BALSA_WOOD.asItem()));

        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.BALSA_LOGS);

        tag(ItemTags.PLANKS)
                .add(ModItems.getRK(ModBlocks.BALSA_PLANKS.asItem()));

        tag(ItemTags.LEAVES)
                .add(ModItems.getRK(ModBlocks.BALSA_LEAVES.asItem()));

        tag(ItemTags.SAPLINGS)
                .add(ModItems.getRK(ModBlocks.BALSA_SAPLING.asItem()));

    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModJukeboxSongs.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.sound.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;

public class ModJukeboxSongs {
    public static final ResourceKey<JukeboxSong> BAR_BRAWL_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "bar_brawl"));

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, BAR_BRAWL_KEY, ModSounds.BAR_BRAWL, 162, 15);

    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key,
                                 Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent,
                Component.translatable(Util.makeDescriptionId("jukebox_song", key.identifier())), lengthInSeconds, comparatorOutput));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModModelProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.custom.FluoriteLampBlock;
import net.kaupenjoe.tutorialmod.block.custom.HoneyBerryBushBlock;
import net.kaupenjoe.tutorialmod.block.custom.RiceCropBlock;
import net.kaupenjoe.tutorialmod.block.custom.StrawberryCropBlock;
import net.kaupenjoe.tutorialmod.data.ModDataComponents;
import net.kaupenjoe.tutorialmod.item.ModArmorMaterials;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        // blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.RAW_FLUORITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_DEEPSLATE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_NETHER_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.FLUORITE_END_ORE);

        blockModelGenerators.createTrivialCube(ModBlocks.MAGIC_BLOCK);

        blockModelGenerators.family(ModBlocks.FLUORITE_BLOCK)
                .stairs(ModBlocks.FLUORITE_STAIRS)
                .slab(ModBlocks.FLUORITE_SLAB)
                .button(ModBlocks.FLUORITE_BUTTON)
                .pressurePlate(ModBlocks.FLUORITE_PRESSURE_PLATE)
                .fence(ModBlocks.FLUORITE_FENCE)
                .fenceGate(ModBlocks.FLUORITE_FENCE_GATE)
                .wall(ModBlocks.FLUORITE_WALL);

        blockModelGenerators.createDoor(ModBlocks.FLUORITE_DOOR);
        blockModelGenerators.createTrapdoor(ModBlocks.FLUORITE_TRAPDOOR);

        Identifier lampOffIdentifier = TexturedModel.CUBE.create(ModBlocks.FLUORITE_LAMP, blockModelGenerators.modelOutput);
        Identifier lampOnIdentifier = blockModelGenerators.createSuffixedVariant(ModBlocks.FLUORITE_LAMP, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube);

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.FLUORITE_LAMP)
                .with(BlockModelGenerators.createBooleanModelDispatch(FluoriteLampBlock.CLICKED,
                        new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOnIdentifier)).build()),
                        new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOffIdentifier)).build()))));

        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.PEDESTAL_BLOCK);
        blockModelGenerators.createCropBlock(ModBlocks.STRAWBERRY_CROP, StrawberryCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockModelGenerators.createCrossBlock(ModBlocks.HONEY_BERRY_BUSH, BlockModelGenerators.PlantType.NOT_TINTED,
                HoneyBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModelGenerators.createCropBlock(ModBlocks.RICE_CROP, RiceCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);

        blockModelGenerators.createFurnace(ModBlocks.CRYSTALLIZER, TexturedModel.ORIENTABLE);

        blockModelGenerators.woodProvider(ModBlocks.BALSA_LOG).log(ModBlocks.BALSA_LOG).wood(ModBlocks.BALSA_WOOD);
        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_BALSA_LOG).log(ModBlocks.STRIPPED_BALSA_LOG).wood(ModBlocks.STRIPPED_BALSA_WOOD);

        blockModelGenerators.createTrivialCube(ModBlocks.BALSA_PLANKS);
        blockModelGenerators.createTrivialBlock(ModBlocks.BALSA_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.BALSA_SAPLING, ModBlocks.POTTED_BALSA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RAW_FLUORITE, ModelTemplates.FLAT_ITEM);

        // itemModelGenerators.generateFlatItem(ModItems.CHISEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.STRAWBERRY, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.COMBUSTIBLE_SPORES, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateSpear(ModItems.FLUORITE_SPEAR);

        itemModelGenerators.generateTrimmableItem(ModItems.FLUORITE_HELMET, ModArmorMaterials.FLUORITE_KEY,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(ModItems.FLUORITE_CHESTPLATE, ModArmorMaterials.FLUORITE_KEY,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(ModItems.FLUORITE_LEGGINGS, ModArmorMaterials.FLUORITE_KEY,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(ModItems.FLUORITE_BOOTS, ModArmorMaterials.FLUORITE_KEY,
                ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        itemModelGenerators.generateFlatItem(ModItems.FLUORITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);

        ItemModel.Unbaked unbakedChisel = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(ModItems.CHISEL, ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked unbakedUsedChisel = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(ModItems.CHISEL, "_used", ModelTemplates.FLAT_HANDHELD_ITEM));
        itemModelGenerators.itemModelOutput.accept(ModItems.CHISEL,
                new ClientItem(new ConditionalItemModel.Unbaked(Optional.empty(), new HasComponent(ModDataComponents.COORDINATES, false),
                        unbakedUsedChisel, unbakedChisel), new ClientItem.Properties(false, false, 1f)).model());

        itemModelGenerators.createFlatItemModel(ModItems.KAUPEN_BOW, ModelTemplates.BOW);
        itemModelGenerators.generateBow(ModItems.KAUPEN_BOW);

        itemModelGenerators.declareCustomModelItem(ModItems.SCULKBEAM_STAFF);

        itemModelGenerators.generateFlatItem(ModItems.BAR_BRAWL_MUSIC_DISC, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SPECTRE_STAFF, ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModPaintings.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.Optional;

public class ModPaintings {
    public static final ResourceKey<PaintingVariant> SAW_THEM_KEY = create("saw_them");
    public static final ResourceKey<PaintingVariant> SHRIMP_KEY = create("shrimp");
    public static final ResourceKey<PaintingVariant> WORLD_KEY = create("world");
    public static final ResourceKey<PaintingVariant> WANDERER_KEY = create("wanderer");


    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, SAW_THEM_KEY, 2, 2, true);
        register(context, SHRIMP_KEY, 2, 1, true);
        register(context, WORLD_KEY, 2, 2, true);
        register(context, WANDERER_KEY, 1, 2, true);
    }

    private static ResourceKey<PaintingVariant> create(final String id) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, id));
    }

    private static void register(final BootstrapContext<PaintingVariant> context, final ResourceKey<PaintingVariant> key, final int width,
                                 final int height, final boolean hasAuthor) {
        context.register(key, new PaintingVariant(width, height, key.identifier(),
                Optional.of(Component.translatable(key.identifier().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW)),
                hasAuthor ? Optional.of(Component.translatable(key.identifier().toLanguageKey("painting", "author")).withStyle(ChatFormatting.GRAY)) : Optional.empty()));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModPaintingTagsProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.concurrent.CompletableFuture;

public class ModPaintingTagsProvider extends FabricTagsProvider<PaintingVariant> {
        public ModPaintingTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.PAINTING_VARIANT, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(PaintingVariantTags.PLACEABLE)
                .add(TagEntry.element(ModPaintings.SAW_THEM_KEY.identifier()))
                .add(TagEntry.element(ModPaintings.SHRIMP_KEY.identifier()))
                .add(TagEntry.element(ModPaintings.WORLD_KEY.identifier()))
                .add(TagEntry.element(ModPaintings.WANDERER_KEY.identifier()));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModRecipeProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.datagen.recipe.CrystallizerRecipeBuilder;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> FLUORITE_SMELTABLES = List.of(ModItems.RAW_FLUORITE, ModBlocks.FLUORITE_ORE, ModBlocks.FLUORITE_DEEPSLATE_ORE,
                        ModBlocks.FLUORITE_NETHER_ORE, ModBlocks.FLUORITE_END_ORE);

                oreSmelting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FLUORITE, 0.25f, 200, "fluorite");
                oreBlasting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FLUORITE, 0.25f, 100, "fluorite");

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.FLUORITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_BLOCK);

                shaped(RecipeCategory.MISC, ModBlocks.RAW_FLUORITE_BLOCK)
                        .pattern("RRR")
                        .pattern("RRR")
                        .pattern("RRR")
                        .define('R', ModItems.RAW_FLUORITE)
                        .unlockedBy(getHasName(ModItems.RAW_FLUORITE), has(ModItems.RAW_FLUORITE))
                        .group("fluorite")
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.RAW_FLUORITE, 9)
                        .requires(ModBlocks.RAW_FLUORITE_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.RAW_FLUORITE_BLOCK), has(ModBlocks.RAW_FLUORITE_BLOCK))
                        .group("fluorite")
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.RAW_FLUORITE, 4)
                        .requires(ModItems.FLUORITE)
                        .requires(Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output, "raw_fluorite_from_fluorite_and_stick");

                stairBuilder(ModBlocks.FLUORITE_STAIRS, Ingredient.of(ModBlocks.FLUORITE_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.FLUORITE_BLOCK), has(ModBlocks.FLUORITE_BLOCK))
                        .group("fluorite")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_SLAB, ModBlocks.FLUORITE_BLOCK);

                buttonBuilder(ModBlocks.FLUORITE_BUTTON, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);
                pressurePlate(ModBlocks.FLUORITE_PRESSURE_PLATE, ModItems.FLUORITE);

                fenceBuilder(ModBlocks.FLUORITE_FENCE, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);
                fenceGateBuilder(ModBlocks.FLUORITE_FENCE_GATE, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_WALL, ModBlocks.FLUORITE_BLOCK);

                doorBuilder(ModBlocks.FLUORITE_DOOR, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);
                trapdoorBuilder(ModBlocks.FLUORITE_TRAPDOOR, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.FLUORITE_SWORD)
                        .pattern("F")
                        .pattern("F")
                        .pattern("S")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.FLUORITE_PICKAXE)
                        .pattern("FFF")
                        .pattern(" S ")
                        .pattern(" S ")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.FLUORITE_SHOVEL)
                        .pattern("F")
                        .pattern("S")
                        .pattern("S")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.FLUORITE_AXE)
                        .pattern("FF")
                        .pattern("SF")
                        .pattern("S ")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.FLUORITE_HOE)
                        .pattern("FF")
                        .pattern("S ")
                        .pattern("S ")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.FLUORITE_SPEAR)
                        .pattern("  F")
                        .pattern(" S ")
                        .pattern("S  ")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.FLUORITE_HELMET)
                        .pattern("FFF")
                        .pattern("F F")
                        .define('F', ModItems.FLUORITE)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.FLUORITE_CHESTPLATE)
                        .pattern("F F")
                        .pattern("FFF")
                        .pattern("FFF")
                        .define('F', ModItems.FLUORITE)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.FLUORITE_LEGGINGS)
                        .pattern("FFF")
                        .pattern("F F")
                        .pattern("F F")
                        .define('F', ModItems.FLUORITE)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.FLUORITE_BOOTS)
                        .pattern("F F")
                        .pattern("F F")
                        .define('F', ModItems.FLUORITE)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);


                CrystallizerRecipeBuilder.crystallizerRecipe(RecipeCategory.MISC, Ingredient.of(ModItems.STRAWBERRY), ModItems.RICE_SHOOT, 2)
                        .unlockedBy(getHasName(ModItems.STRAWBERRY), has(ModItems.STRAWBERRY))
                        .save(output, "tutorialmod:rice_shoot_from_crystallizing");

                CrystallizerRecipeBuilder.crystallizerRecipe(RecipeCategory.MISC, Ingredient.of(Items.STICK), Items.END_ROD, 2)
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output, "tutorialmod:end_rod_from_crystallizing");

                CrystallizerRecipeBuilder.crystallizerRecipe(RecipeCategory.MISC, Ingredient.of(ModItems.RAW_FLUORITE), ModItems.FLUORITE, 3)
                        .unlockedBy(getHasName(ModItems.RAW_FLUORITE), has(ModItems.RAW_FLUORITE))
                        .save(output, "tutorialmod:fluorite_from_crystallizing");

                CrystallizerRecipeBuilder.crystallizerRecipe(RecipeCategory.MISC, Ingredient.of(Blocks.DIRT), Items.NETHER_STAR)
                        .unlockedBy(getHasName(Blocks.DIRT), has(Blocks.DIRT))
                        .save(output, "tutorialmod:nether_star_from_crystallizing");

                CrystallizerRecipeBuilder.crystallizerRecipe(RecipeCategory.MISC, Ingredient.of(Items.REDSTONE), ModItems.BAR_BRAWL_MUSIC_DISC)
                        .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                        .save(output, "tutorialmod:bar_brawl_music_disc_from_crystallizing");

                woodFromLogs(ModBlocks.BALSA_WOOD, ModBlocks.BALSA_LOG);
                woodFromLogs(ModBlocks.STRIPPED_BALSA_WOOD, ModBlocks.STRIPPED_BALSA_LOG);
                planksFromLogs(ModBlocks.BALSA_PLANKS, ModTags.Items.BALSA_LOGS, 4);

            }
        };
    }

    @Override
    public String getName() {
        return "TutorialMod Recipes";
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModRegistryDataProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

public class ModRegistryDataProvider extends FabricDynamicRegistryProvider {
    public ModRegistryDataProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.PAINTING_VARIANT));
        entries.addAll(registries.lookupOrThrow(Registries.JUKEBOX_SONG));
        entries.addAll(registries.lookupOrThrow(Registries.DAMAGE_TYPE));

        entries.addAll(registries.lookupOrThrow(Registries.VILLAGER_TRADE));
        entries.addAll(registries.lookupOrThrow(Registries.TRADE_SET));

        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
    }

    @Override
    public String getName() {
        return "TutorialMod Data Provider";
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/ModSoundsProvider.java

```java
package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.sound.ModSounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModSoundsProvider extends FabricSoundsProvider {
    public ModSoundsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registryLookup, SoundExporter exporter) {
        exporter.add(ModSounds.CHISEL_USE, SoundTypeBuilder.of(ModSounds.CHISEL_USE).subtitle("sounds.tutorialmod.chisel_use")
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "chisel_use"))));

        exporter.add(ModSounds.BAR_BRAWL, SoundTypeBuilder.of(ModSounds.BAR_BRAWL.value())
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "bar_brawl")).stream(true)));
    }

    @Override
    public String getName() {
        return "TutorialMod Sounds";
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/recipe/CrystallizerRecipeBuilder.java

```java
package net.kaupenjoe.tutorialmod.datagen.recipe;

import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipe;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.Nullable;

public class CrystallizerRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final ItemStackTemplate result;
    private final Ingredient ingredient;
    private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
    private @Nullable String group;

    private CrystallizerRecipeBuilder(RecipeCategory category, Ingredient ingredient, ItemStackTemplate result) {
        this.category = category;
        this.result = result;
        this.ingredient = ingredient;
    }

    public static CrystallizerRecipeBuilder crystallizerRecipe(RecipeCategory category, Ingredient ingredient, ItemLike item, int count) {
        return new CrystallizerRecipeBuilder(category, ingredient, new ItemStackTemplate(item.asItem(), count));
    }

    public static CrystallizerRecipeBuilder crystallizerRecipe(RecipeCategory category, Ingredient ingredient, ItemLike item) {
        return new CrystallizerRecipeBuilder(category, ingredient, new ItemStackTemplate(item.asItem()));
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        advancementBuilder.unlockedBy(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public ResourceKey<Recipe<?>> defaultId() {
        return RecipeBuilder.getDefaultRecipeId(this.result);
    }

    @Override
    public void save(RecipeOutput output, ResourceKey<Recipe<?>> id) {
        CrystallizerRecipe recipe = new CrystallizerRecipe(this.ingredient, this.result);
        output.accept(id, recipe, this.advancementBuilder.build(output, id, this.category));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/villager/ModPOITags.java

```java
package net.kaupenjoe.tutorialmod.datagen.villager;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.tutorialmod.villager.ModVillagers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.concurrent.CompletableFuture;

public class ModPOITags extends FabricTagsProvider<PoiType> {
    public ModPOITags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .add(TagEntry.element(ModVillagers.KAUPEN_POI_KEY.identifier()));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/villager/ModTradeSets.java

```java
package net.kaupenjoe.tutorialmod.datagen.villager;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import java.util.Optional;

public class ModTradeSets {
    public static final ResourceKey<TradeSet> KAUPENGER_LEVEL_1 = create("kaupenger/level_1");
    public static final ResourceKey<TradeSet> KAUPENGER_LEVEL_2 = create("kaupenger/level_2");

    public static void bootstrap(BootstrapContext<TradeSet> context) {
        register(context, KAUPENGER_LEVEL_1, ModTags.Trades.KAUPENGER_LEVEL_1);
        register(context, KAUPENGER_LEVEL_2, ModTags.Trades.KAUPENGER_LEVEL_2);
    }

    private static ResourceKey<TradeSet> create(final String id) {
        return ResourceKey.create(Registries.TRADE_SET, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, id));
    }

    public static Holder.Reference<TradeSet> register(final BootstrapContext<TradeSet> context,
                                                      final ResourceKey<TradeSet> resourceKey, final TagKey<VillagerTrade> tradeTag) {
        return register(context, resourceKey, tradeTag, ConstantValue.exactly(2.0F));
    }

    public static Holder.Reference<TradeSet> register(final BootstrapContext<TradeSet> context, final ResourceKey<TradeSet> resourceKey,
                                                      final TagKey<VillagerTrade> tradeTag, final NumberProvider numberProvider) {
        return context.register(resourceKey, new TradeSet(context.lookup(Registries.VILLAGER_TRADE).getOrThrow(tradeTag),
                numberProvider, false, Optional.of(resourceKey.identifier().withPrefix("trade_set/"))));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/villager/ModVillagerTrades.java

```java
package net.kaupenjoe.tutorialmod.datagen.villager;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.item.trading.VillagerTrades;

import java.util.List;
import java.util.Optional;

public class ModVillagerTrades {
    public static final ResourceKey<VillagerTrade> FARMER_1_EMERALD_STRAWBERRY = createKey("farmer/1/emerald_strawberry");
    public static final ResourceKey<VillagerTrade> FARMER_1_DIAMOND_STRAWBERRY_SEEDS = createKey("farmer/1/diamond_strawberry_seeds");

    public static final ResourceKey<VillagerTrade> FARMER_2_EMERALD_HONEY_BERRIES = createKey("farmer/2/emerald_honey_berries");

    public static final ResourceKey<VillagerTrade> MASON_1_FLUORITE_CHISEL = createKey("mason/1/fluorite_chisel");
    public static final ResourceKey<VillagerTrade> LIBRARIAN_1_FLUORITE_ENCHANTED_BOOK = createKey("librarian/1/fluorite_enchanted_book");


    public static final ResourceKey<VillagerTrade> KAUPENGER_1_EMERALD_FLUORITE = createKey("kaupenger/1/emerald_fluorite");
    public static final ResourceKey<VillagerTrade> KAUPENGER_1_EMERALD_RAW_FLUORITE = createKey("kaupenger/1/emerald_raw_fluorite");

    public static final ResourceKey<VillagerTrade> KAUPENGER_2_EMERALD_PEDESTAL = createKey("kaupenger/2/emerald_pedestal");
    public static final ResourceKey<VillagerTrade> KAUPENGER_2_FLUORITE_SPECTRE_STAFF = createKey("kaupenger/2/fluorite_spectre_staff");



    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        var items = context.lookup(Registries.ITEM);
        var enchantments = context.lookup(Registries.ENCHANTMENT);

        context.register(FARMER_1_EMERALD_STRAWBERRY, new VillagerTrade(
                new TradeCost(Items.EMERALD, 4),
                new ItemStackTemplate(ModItems.STRAWBERRY),
                12, 8, 0.05f,
                Optional.empty(), List.of()));
        context.register(FARMER_1_DIAMOND_STRAWBERRY_SEEDS, new VillagerTrade(
                new TradeCost(Items.DIAMOND, 12),
                new ItemStackTemplate(ModItems.STRAWBERRY_SEEDS),
                12, 10, 0.05f,
                Optional.empty(), List.of()));

        context.register(FARMER_2_EMERALD_HONEY_BERRIES, new VillagerTrade(
                new TradeCost(Items.EMERALD, 16),
                new ItemStackTemplate(ModItems.HONEY_BERRIES),
                12, 10, 0.05f,
                Optional.empty(), List.of()));


        context.register(MASON_1_FLUORITE_CHISEL, new VillagerTrade(
                new TradeCost(ModItems.FLUORITE, 6),
                new ItemStackTemplate(ModItems.CHISEL),
                2, 19, 0.05f,
                Optional.empty(), List.of()));

        context.register(LIBRARIAN_1_FLUORITE_ENCHANTED_BOOK, new VillagerTrade(
                new TradeCost(ModItems.FLUORITE, 32),
                new ItemStackTemplate(Items.ENCHANTED_BOOK),
                12, 6, 0.05f,
                Optional.empty(),
                VillagerTrades.enchantedBook(items,
                        HolderSet.direct(enchantments.getOrThrow(Enchantments.INFINITY),
                                enchantments.getOrThrow(Enchantments.MULTISHOT)))));


        context.register(KAUPENGER_1_EMERALD_FLUORITE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 6),
                new ItemStackTemplate(ModItems.FLUORITE, 4),
                12, 19, 0.05f,
                Optional.empty(), List.of()));
        context.register(KAUPENGER_1_EMERALD_RAW_FLUORITE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 5),
                new ItemStackTemplate(ModItems.RAW_FLUORITE, 12),
                12, 23, 0.05f,
                Optional.empty(), List.of()));

        context.register(KAUPENGER_2_EMERALD_PEDESTAL, new VillagerTrade(
                new TradeCost(Items.EMERALD, 24),
                new ItemStackTemplate(ModBlocks.PEDESTAL_BLOCK.asItem()),
                12, 24, 0.05f,
                Optional.empty(), List.of()));
        context.register(KAUPENGER_2_FLUORITE_SPECTRE_STAFF, new VillagerTrade(
                new TradeCost(ModItems.FLUORITE, 19),
                new ItemStackTemplate(ModItems.SPECTRE_STAFF),
                2, 19, 0.05f,
                Optional.empty(), List.of()));
    }


    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/datagen/villager/ModVillagerTradeTags.java

```java
package net.kaupenjoe.tutorialmod.datagen.villager;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class ModVillagerTradeTags extends FabricTagsProvider<VillagerTrade> {
    public ModVillagerTradeTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(VillagerTradeTags.FARMER_LEVEL_1)
                .add(TagEntry.element(ModVillagerTrades.FARMER_1_EMERALD_STRAWBERRY.identifier()))
                .add(TagEntry.element(ModVillagerTrades.FARMER_1_DIAMOND_STRAWBERRY_SEEDS.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.FARMER_LEVEL_2)
                .add(TagEntry.element(ModVillagerTrades.FARMER_2_EMERALD_HONEY_BERRIES.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.MASON_LEVEL_1)
                .add(TagEntry.element(ModVillagerTrades.MASON_1_FLUORITE_CHISEL.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.LIBRARIAN_LEVEL_1)
                .add(TagEntry.element(ModVillagerTrades.LIBRARIAN_1_FLUORITE_ENCHANTED_BOOK.identifier()));

        getOrCreateRawBuilder(ModTags.Trades.KAUPENGER_LEVEL_1)
                .add(TagEntry.element(ModVillagerTrades.KAUPENGER_1_EMERALD_FLUORITE.identifier()))
                .add(TagEntry.element(ModVillagerTrades.KAUPENGER_1_EMERALD_RAW_FLUORITE.identifier()));
        getOrCreateRawBuilder(ModTags.Trades.KAUPENGER_LEVEL_2)
                .add(TagEntry.element(ModVillagerTrades.KAUPENGER_2_EMERALD_PEDESTAL.identifier()))
                .add(TagEntry.element(ModVillagerTrades.KAUPENGER_2_FLUORITE_SPECTRE_STAFF.identifier()));

    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/effect/ModEffects.java

```java
package net.kaupenjoe.tutorialmod.effect;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
    public static final Holder<MobEffect> STINKY = registerMobEffect("stinky",
            new StinkyEffect(MobEffectCategory.NEUTRAL, 0xde601d));


    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), effect);
    }

    public static void registerEffects() {
        TutorialMod.LOGGER.info("Registering effects for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/effect/StinkyEffect.java

```java
package net.kaupenjoe.tutorialmod.effect;

import net.kaupenjoe.tutorialmod.datagen.ModDamageTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class StinkyEffect extends MobEffect {
    public StinkyEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        AABB boundingBox = mob.getBoundingBox().inflate(amplification + 1);
        List<Entity> entities = serverLevel.getEntities(mob, boundingBox);

        for(Entity entity : entities) {
            if(entity instanceof LivingEntity livingEntity) {
                livingEntity.hurtServer(serverLevel, ModDamageTypes.create(serverLevel, ModDamageTypes.STINKY), 0.25f * (amplification + 1));
            }
        }

        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/food/ModFoods.java

```java
package net.kaupenjoe.tutorialmod.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {
    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f).build();
    public static final FoodProperties HONEY_BERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.15f).build();

    public static final Consumable STRAWBERRY_CONSUMABLE = Consumables.defaultFood()
            .consumeSeconds(1f).onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 200), 0.15f)).build();


}
```

### src/main/java/net/kaupenjoe/tutorialmod/item/custom/ChiselItem.java

```java
package net.kaupenjoe.tutorialmod.item.custom;

import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.data.ModDataComponents;
import net.kaupenjoe.tutorialmod.sound.ModSounds;
import net.kaupenjoe.tutorialmod.stat.ModStats;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Consumer;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.OAK_LOG, ModBlocks.FLUORITE_BLOCK,
                    ModBlocks.FLUORITE_BLOCK, Blocks.NETHER_BRICKS,
                    Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK
            );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if(player.isCrouching()) {
            player.getMainHandItem().remove(ModDataComponents.COORDINATES);
            return InteractionResult.SUCCESS;
        }

        return super.use(level, player, hand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        // Right Click Block
        // Change Block from A to B...

        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock) && !level.isClientSide()) {
            // We are on the Server!
            level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), context.getHand());
            level.playSound(null, context.getClickedPos(), ModSounds.CHISEL_USE, SoundSource.BLOCKS, 2.0F,
                    0.8F + level.getRandom().nextFloat() * 0.4F);

            context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());
            context.getPlayer().awardStat(ModStats.CHISEL_USED_STAT, 1);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.tutorialmod.chisel.shift_down"));
        } else {
            builder.accept(Component.translatable("tooltip.tutorialmod.chisel"));
        }

        if(itemStack.has(ModDataComponents.COORDINATES)) {
            builder.accept(Component.literal("Last Block chiseled at " + itemStack.get(ModDataComponents.COORDINATES)));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/item/ModArmorMaterials.java

```java
package net.kaupenjoe.tutorialmod.item;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;

public class ModArmorMaterials {
    public static final ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY =
            ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

    public static final ResourceKey<EquipmentAsset> FLUORITE_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fluorite"));

    public static final ArmorMaterial FLUORITE_ARMOR_MATERIAL = new ArmorMaterial(750,
            ArmorMaterials.makeDefense(2, 4, 6, 2, 10),
            20, SoundEvents.ARMOR_EQUIP_CHAIN, 0, 0, ModTags.Items.FLUORITE_REPAIR, FLUORITE_KEY);

}
```

### src/main/java/net/kaupenjoe/tutorialmod/item/ModItems.java

```java
package net.kaupenjoe.tutorialmod.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.datagen.ModJukeboxSongs;
import net.kaupenjoe.tutorialmod.food.ModFoods;
import net.kaupenjoe.tutorialmod.item.custom.ChiselItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {
    public static final Item FLUORITE = registerItem("fluorite", Item::new);
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", Item::new);

    public static final Item CHISEL = registerItem("chisel", properties -> new ChiselItem(properties.durability(32)));
    public static final Item STRAWBERRY = registerItem("strawberry", properties -> new Item(properties
            .food(ModFoods.STRAWBERRY, ModFoods.STRAWBERRY_CONSUMABLE)) {
        @Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            builder.accept(Component.translatable("tooltip.tutorialmod.strawberry"));
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        }
    });

    public static final Item COMBUSTIBLE_SPORES = registerItem("combustible_spores", properties -> new Item(properties.stacksTo(16)));

    public static final Item FLUORITE_SWORD = registerItem("fluorite_sword",
            properties -> new Item(properties.sword(ModToolMaterials.FLUORITE, 3, -2.4f)));
    public static final Item FLUORITE_PICKAXE = registerItem("fluorite_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolMaterials.FLUORITE, 1, -2.8f)));
    public static final Item FLUORITE_SHOVEL = registerItem("fluorite_shovel",
            properties -> new ShovelItem(ModToolMaterials.FLUORITE, 1.5f, -3.0f, properties));
    public static final Item FLUORITE_AXE = registerItem("fluorite_axe",
            properties -> new AxeItem(ModToolMaterials.FLUORITE, 6f, -3.2f, properties));
    public static final Item FLUORITE_HOE = registerItem("fluorite_hoe",
            properties -> new HoeItem(ModToolMaterials.FLUORITE, 0f, -3.0f, properties));
    public static final Item FLUORITE_SPEAR = registerItem("fluorite_spear",
            properties -> new Item(properties.spear(ModToolMaterials.FLUORITE, 0.95F, 0.95F, 0.6F,
                    2.5F, 11.0F, 6.75F, 5.1F, 11.25F, 4.6F)));

    public static final Item FLUORITE_HELMET = registerItem("fluorite_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final Item FLUORITE_CHESTPLATE = registerItem("fluorite_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final Item FLUORITE_LEGGINGS = registerItem("fluorite_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final Item FLUORITE_BOOTS = registerItem("fluorite_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL, ArmorType.BOOTS)));

    public static final Item FLUORITE_HORSE_ARMOR = registerItem("fluorite_horse_armor",
            properties -> new Item(properties.horseArmor(ModArmorMaterials.FLUORITE_ARMOR_MATERIAL)));

    public static final Item KAUPEN_BOW = registerItem("kaupen_bow",
            properties -> new BowItem(properties.durability(500)));

    public static final Item SCULKBEAM_STAFF = registerItem("sculkbeam_staff",
            properties -> new Item(properties.stacksTo(1)));

    public static final Item STRAWBERRY_SEEDS = registerItem("strawberry_seeds",
            properties -> new BlockItem(ModBlocks.STRAWBERRY_CROP, properties.useItemDescriptionPrefix()));
    public static final Item HONEY_BERRIES = registerItem("honey_berries",
            properties -> new BlockItem(ModBlocks.HONEY_BERRY_BUSH,
                    properties.useItemDescriptionPrefix().food(ModFoods.HONEY_BERRIES)));
    public static final Item RICE_SHOOT = registerItem("rice_shoot",
            properties -> new PlaceOnWaterBlockItem(ModBlocks.RICE_CROP, properties.useItemDescriptionPrefix()));

    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            properties -> new Item(properties.jukeboxPlayable(ModJukeboxSongs.BAR_BRAWL_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final Item SPECTRE_STAFF = registerItem("spectre_staff",
            properties -> new Item(properties.stacksTo(1)));


    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))));
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(FLUORITE);
            output.accept(RAW_FLUORITE);
        });
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/item/ModToolMaterials.java

```java
package net.kaupenjoe.tutorialmod.item;

import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {
    public static final ToolMaterial FLUORITE = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_FLUORITE_TOOL,
            1200, 5f, 4f, 20, ModTags.Items.FLUORITE_REPAIR);

}
```

### src/main/java/net/kaupenjoe/tutorialmod/keymapping/ModKeyMappings.java

```java
package net.kaupenjoe.tutorialmod.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeyMappings {
    public static final KeyMapping KAUPEN_KEYMAPPING = KeyMappingHelper.registerKeyMapping(
            new KeyMapping("key.tutorialmod.kaupen_key",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_K, KeyMapping.Category.MISC));


    public static void register() {
        TutorialMod.LOGGER.info("Registering ModKeyMappings for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/loot/ModLootTableModifiers.java

```java
package net.kaupenjoe.tutorialmod.loot;

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModLootTableModifiers {
    public static void modifyLootTables(ResourceKey<LootTable> key, FabricLootTableBuilder builder,
                                        LootTableSource source, HolderLookup.Provider provider) {
        if (key.identifier().equals(Identifier.withDefaultNamespace("blocks/short_grass"))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.25f))
                    .add(LootItem.lootTableItem(ModItems.STRAWBERRY_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)).build());

            builder.pool(poolBuilder.build());
        }

        // This targets all ancient city chest loot tables!
        if(BuiltInLootTables.ANCIENT_CITY.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(1f)) // Drops 100% of the time
                    .add(LootItem.lootTableItem(ModItems.CHISEL))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)).build());

            builder.pool(poolBuilder.build());
        }

        // This targets creeper loot table
        if(key.identifier().equals(Identifier.withDefaultNamespace("entities/creeper"))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.45f)) // Drops 45% of the time
                    .add(LootItem.lootTableItem(ModItems.RAW_FLUORITE))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)).build());

            builder.pool(poolBuilder.build());
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/menu/custom/CrystallizerMenu.java

```java
package net.kaupenjoe.tutorialmod.menu.custom;

import net.kaupenjoe.tutorialmod.block.entity.custom.CrystallizerBlockEntity;
import net.kaupenjoe.tutorialmod.menu.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CrystallizerMenu extends AbstractContainerMenu {
    private final Container inventory;
    public final CrystallizerBlockEntity blockEntity;
    private final ContainerData data;

    public CrystallizerMenu(int pContainerId, Inventory inv, BlockPos blockPos) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(blockPos), new SimpleContainerData(2));
    }

    public CrystallizerMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.CRYSTALLIZER_MENU, pContainerId);
        blockEntity = ((CrystallizerBlockEntity) entity);
        this.data = data;
        this.inventory = blockEntity;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new Slot(inventory,0, 54, 34));
        // Output
        this.addSlot(new Slot(inventory,1, 104, 34) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }
        });

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int arrowPixelSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * arrowPixelSize / maxProgress : 0;
    }

    public int getScaledCrystalProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int crystalPixelSize = 16;

        return maxProgress != 0 && progress != 0 ? progress * crystalPixelSize / maxProgress : 0;
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 2;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.inventory.stillValid(pPlayer);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/menu/custom/CrystallizerScreen.java

```java
package net.kaupenjoe.tutorialmod.menu.custom;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class CrystallizerScreen extends AbstractContainerScreen<CrystallizerMenu> {
    private static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,"textures/gui/crystallizer/crystallizer_gui.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,"textures/gui/crystallizer/arrow_progress.png");
    private static final Identifier CRYSTAL_TEXTURE =
            Identifier.parse("textures/block/amethyst_cluster.png");

    public CrystallizerScreen(CrystallizerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0,
                imageWidth, imageHeight, 256, 256);

        renderProgressArrow(graphics, x, y);
        renderProgressCrystal(graphics, x, y);
    }

    private void renderProgressArrow(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ARROW_TEXTURE,
                    x + 73, y + 35,
                    0, 0,
                    menu.getScaledArrowProgress(), 16,
                    24, 16);
        }
    }

    private void renderProgressCrystal(GuiGraphicsExtractor guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, CRYSTAL_TEXTURE,
                    x + 104, y + 13 + 16 - menu.getScaledCrystalProgress(),
                    0,16 - menu.getScaledCrystalProgress(),
                    16, menu.getScaledCrystalProgress(),
                    16, 16);
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/menu/custom/PedestalMenu.java

```java
package net.kaupenjoe.tutorialmod.menu.custom;

import net.kaupenjoe.tutorialmod.menu.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class PedestalMenu extends AbstractContainerMenu {
    private final Container inventory;

    public PedestalMenu(int containerId, Inventory inv, BlockPos blockPos) {
        this(containerId, inv, inv.player.level().getBlockEntity(blockPos));
    }

    public PedestalMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(ModMenuTypes.PEDESTAL_MENU, containerId);
        this.inventory = ((Container) blockEntity);

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        addSlot(new Slot(inventory, 0, 80, 35) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });
    }


    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 1;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.inventory.stillValid(pPlayer);
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/menu/custom/PedestalScreen.java

```java
package net.kaupenjoe.tutorialmod.menu.custom;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class PedestalScreen extends AbstractContainerScreen<PedestalMenu> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,
            "textures/gui/pedestal/pedestal_gui.png");

    public PedestalScreen(PedestalMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0,
                imageWidth, imageHeight, 256, 256);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/menu/ModMenuTypes.java

```java
package net.kaupenjoe.tutorialmod.menu;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.menu.custom.CrystallizerMenu;
import net.kaupenjoe.tutorialmod.menu.custom.PedestalMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<PedestalMenu> PEDESTAL_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "pedestal_menu"),
                    new ExtendedMenuType<>(PedestalMenu::new, BlockPos.STREAM_CODEC));

    public static final MenuType<CrystallizerMenu> CRYSTALLIZER_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "crystallizer_menu"),
                    new ExtendedMenuType<>(CrystallizerMenu::new, BlockPos.STREAM_CODEC));


    public static void registerModMenuTypes() {
        TutorialMod.LOGGER.info("Registering ModMenuTypes for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/mixin/AbstractClientPlayerMixin.java

```java
package net.kaupenjoe.tutorialmod.mixin;

import com.mojang.authlib.GameProfile;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    public AbstractClientPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Inject(method = "getFieldOfViewModifier", at = @At(value = "TAIL"), cancellable = true)
    private void getFieldOfViewModifierMixin(boolean firstPerson, float effectScale, CallbackInfoReturnable<Float> info) {
        float modifier = 1f;

        if (this.isUsingItem() && this.getUseItem().is(ModItems.KAUPEN_BOW)) {
            float scale = Math.min(this.getTicksUsingItem() / 20.0F, 1.0F);
            modifier *= 1.0F - Mth.square(scale) * 0.15F;
            info.setReturnValue(Mth.lerp(effectScale, 1.0F, modifier));
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/mixin/ExampleMixin.java

```java
package net.kaupenjoe.tutorialmod.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "loadLevel")
	private void init(CallbackInfo info) {
		// This code is injected into the start of MinecraftServer.loadLevel()V
	}
}
```

### src/main/java/net/kaupenjoe/tutorialmod/networking/ClientboundPackets.java

```java
package net.kaupenjoe.tutorialmod.networking;

// Here we are on THE CLIENT!
public class ClientboundPackets {
}
```

### src/main/java/net/kaupenjoe/tutorialmod/networking/ModPackets.java

```java
package net.kaupenjoe.tutorialmod.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kaupenjoe.tutorialmod.networking.packet.TestPayloadC2S;
import net.minecraft.network.RegistryFriendlyByteBuf;

public class ModPackets {
    private static void registerClientbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        // These Payloads are sent from Server to Client (S2C) --> CLIENTBOUND

    }

    private static void registerServerbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        // These Payloads are sent from Client to Server (C2S) --> SERVERBOUND
        registry.register(TestPayloadC2S.TYPE, TestPayloadC2S.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(TestPayloadC2S.TYPE, ServerboundPackets::handleTestPayload);
    }

    public static void registerPackets() {
        registerClientbound(PayloadTypeRegistry.clientboundPlay());
        registerServerbound(PayloadTypeRegistry.serverboundPlay());
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/networking/packet/TestPayloadC2S.java

```java
package net.kaupenjoe.tutorialmod.networking.packet;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record TestPayloadC2S(String name, int value) implements CustomPacketPayload {
    public static final Type<TestPayloadC2S> TYPE = new Type<>(
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "test_payload"));
    // CODEC ---> Codec & StreamCodec
    // Codec ==> Create a Java Object Instance from JSON File
    // And write to JSON

    // StreamCodec
    // Java Object ==> Turn it into Bytes for Network traffic
    // Turn Bytes into a new Object again!
    public static final StreamCodec<RegistryFriendlyByteBuf, TestPayloadC2S> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            TestPayloadC2S::name,

            ByteBufCodecs.VAR_INT,
            TestPayloadC2S::value,

            TestPayloadC2S::new);


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/networking/ServerboundPackets.java

```java
package net.kaupenjoe.tutorialmod.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kaupenjoe.tutorialmod.networking.packet.TestPayloadC2S;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;

// ANYTHING IN THERE WE ARE ON THE SERVER
public class ServerboundPackets {
    public static void handleTestPayload(TestPayloadC2S testPayloadC2S, ServerPlayNetworking.Context context) {
        EntityTypes.COW.spawn(context.player().level(), context.player().getOnPos(), EntitySpawnReason.TRIGGERED);

    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/potion/ModPotions.java

```java
package net.kaupenjoe.tutorialmod.potion;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {
    public static final Holder<Potion> STINKY_POTION = registerPotion("stinky_potion",
            new Potion("stinky_potion", new MobEffectInstance(ModEffects.STINKY, 1200, 0)));

    private static Holder<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        TutorialMod.LOGGER.info("Registering potions for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/recipe/custom/CrystallizerRecipe.java

```java
package net.kaupenjoe.tutorialmod.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kaupenjoe.tutorialmod.recipe.ModRecipes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record CrystallizerRecipe(Ingredient inputItem, ItemStackTemplate output) implements Recipe<CrystallizerRecipeInput> {
    public static final MapCodec<CrystallizerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(CrystallizerRecipe::inputItem),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(CrystallizerRecipe::output)
            ).apply(instance, CrystallizerRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, CrystallizerRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC,
                    CrystallizerRecipe::inputItem,

                    ItemStackTemplate.STREAM_CODEC,
                    CrystallizerRecipe::output,

                    CrystallizerRecipe::new);


    @Override
    public boolean matches(CrystallizerRecipeInput input, Level level) {
        if(level.isClientSide()) {
            return false;
        }

        return inputItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(CrystallizerRecipeInput input) {
        return output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "Crystallizing";
    }

    @Override
    public RecipeSerializer<? extends Recipe<CrystallizerRecipeInput>> getSerializer() {
        return ModRecipes.CRYSTALLIZER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<CrystallizerRecipeInput>> getType() {
        return ModRecipes.CRYSTALLIZER_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/recipe/custom/CrystallizerRecipeInput.java

```java
package net.kaupenjoe.tutorialmod.recipe.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record CrystallizerRecipeInput(ItemStack input) implements RecipeInput {
    @Override
    public ItemStack getItem(int index) {
        return input;
    }

    @Override
    public int size() {
        return 1;
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/recipe/ModRecipes.java

```java
package net.kaupenjoe.tutorialmod.recipe;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static final RecipeSerializer<CrystallizerRecipe> CRYSTALLIZER_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "crystallizing"),
            new RecipeSerializer<>(CrystallizerRecipe.CODEC, CrystallizerRecipe.STREAM_CODEC));
    public static final RecipeType<CrystallizerRecipe> CRYSTALLIZER_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "crystallizing"),
            new RecipeType<CrystallizerRecipe>() {
                @Override
                public String toString() {
                    return "crystallizing";
                }
            });

    public static void registerModRecipes() {
        TutorialMod.LOGGER.info("Registering ModRecipes for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/registries/ModCompostables.java

```java
package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.kaupenjoe.tutorialmod.item.ModItems;

public class ModCompostables {
    public static void registerCompostables() {
        CompostableRegistry.INSTANCE.add(ModItems.STRAWBERRY, 0.5f);
        CompostableRegistry.INSTANCE.add(ModItems.STRAWBERRY_SEEDS, 0.35f);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/registries/ModFlammableBlocks.java

```java
package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.kaupenjoe.tutorialmod.block.ModBlocks;

public class ModFlammableBlocks {
    public static void registerFlammableBlocks() {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BALSA_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BALSA_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BALSA_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BALSA_WOOD, 5, 5);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BALSA_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BALSA_LEAVES, 30, 60);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/registries/ModFuels.java

```java
package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.kaupenjoe.tutorialmod.item.ModItems;

public class ModFuels {
    public static void registerFuels() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.COMBUSTIBLE_SPORES, 1200);
        });
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/registries/ModPotionRecipes.java

```java
package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.kaupenjoe.tutorialmod.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotionRecipes {
    public static void registerPotionRecipes() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(Items.DIRT), ModPotions.STINKY_POTION);

        });
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/registries/ModStrippableBlocks.java

```java
package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kaupenjoe.tutorialmod.block.ModBlocks;

public class ModStrippableBlocks {
    public static void registerStrippableBlocks() {
        StrippableBlockRegistry.register(ModBlocks.BALSA_LOG, ModBlocks.STRIPPED_BALSA_LOG);
        StrippableBlockRegistry.register(ModBlocks.BALSA_WOOD, ModBlocks.STRIPPED_BALSA_WOOD);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/sound/ModSounds.java

```java
package net.kaupenjoe.tutorialmod.sound;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent CHISEL_USE = registerSoundEvent("chisel_use");

    public static final Holder.Reference<SoundEvent> BAR_BRAWL = registerJukeboxSong("bar_brawl");



    private static Holder.Reference<SoundEvent> registerJukeboxSong(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds() {
        TutorialMod.LOGGER.info("Registering sounds for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/stat/ModStats.java

```java
package net.kaupenjoe.tutorialmod.stat;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ModStats {
    public static final Stat<?> CHISEL_USED_STAT = makeCustomStat("chisel_used");


    private static Stat<?> makeCustomStat(String key) {
        Identifier identifier = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, key);
        Identifier newStat = Registry.register(BuiltInRegistries.CUSTOM_STAT, key, identifier);

        return Stats.CUSTOM.get(newStat, StatFormatter.DEFAULT);
    }

    public static void registerStats() {
        TutorialMod.LOGGER.info("Registering Stats for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/tags/ModTags.java

```java
package net.kaupenjoe.tutorialmod.tags;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_FLUORITE_TOOL = createTag("needs_fluorite_tool");
        public static final TagKey<Block> INCORRECT_FOR_FLUORITE_TOOL = createTag("incorrect_for_fluorite_tool");

        public static final TagKey<Block> BALSA_LOGS = createTag("balsa_logs");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> FLUORITE_REPAIR = createTag("fluorite_repair");

        public static final TagKey<Item> BALSA_LOGS = createTag("balsa_logs");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
        }
    }

    public static class Trades {
        public static final TagKey<VillagerTrade> KAUPENGER_LEVEL_1 = createTag("kaupenger/level_1");
        public static final TagKey<VillagerTrade> KAUPENGER_LEVEL_2 = createTag("kaupenger/level_2");

        private static TagKey<VillagerTrade> createTag(String name) {
            return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/TutorialMod.java

```java
package net.kaupenjoe.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.creativemodetab.ModCreativeModeTabs;
import net.kaupenjoe.tutorialmod.data.ModDataComponents;
import net.kaupenjoe.tutorialmod.effect.ModEffects;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.loot.ModLootTableModifiers;
import net.kaupenjoe.tutorialmod.menu.ModMenuTypes;
import net.kaupenjoe.tutorialmod.networking.ModPackets;
import net.kaupenjoe.tutorialmod.potion.ModPotions;
import net.kaupenjoe.tutorialmod.recipe.ModRecipes;
import net.kaupenjoe.tutorialmod.registries.*;
import net.kaupenjoe.tutorialmod.sound.ModSounds;
import net.kaupenjoe.tutorialmod.stat.ModStats;
import net.kaupenjoe.tutorialmod.villager.ModVillagers;
import net.kaupenjoe.tutorialmod.worldgen.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Important Comment
public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModDataComponents.registerDataComponents();
		ModStats.registerStats();

		ModSounds.registerSounds();
		ModEffects.registerEffects();

		ModPotions.registerPotions();
		ModVillagers.register();

		ModPackets.registerPackets();
		ModBlockEntities.registerBlockEntities();

		ModMenuTypes.registerModMenuTypes();
		ModRecipes.registerModRecipes();


		ModWorldGeneration.generateModWorldGen();


		ModFuels.registerFuels();
		ModCompostables.registerCompostables();
		ModPotionRecipes.registerPotionRecipes();
		ModFlammableBlocks.registerFlammableBlocks();
		ModStrippableBlocks.registerStrippableBlocks();

		LootTableEvents.MODIFY.register(ModLootTableModifiers::modifyLootTables);
	}
}
```

### src/main/java/net/kaupenjoe/tutorialmod/TutorialModClient.java

```java
package net.kaupenjoe.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.block.entity.renderer.PedestalBlockEntityRenderer;
import net.kaupenjoe.tutorialmod.keymapping.ModKeyMappings;
import net.kaupenjoe.tutorialmod.menu.ModMenuTypes;
import net.kaupenjoe.tutorialmod.menu.custom.CrystallizerScreen;
import net.kaupenjoe.tutorialmod.menu.custom.PedestalScreen;
import net.kaupenjoe.tutorialmod.networking.packet.TestPayloadC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.network.chat.Component;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeyMappings.register();

        BlockEntityRenderers.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);

        ClientTickEvents.END_CLIENT_TICK.register(TutorialModClient::onEndTick);

        MenuScreens.register(ModMenuTypes.PEDESTAL_MENU, PedestalScreen::new);
        MenuScreens.register(ModMenuTypes.CRYSTALLIZER_MENU, CrystallizerScreen::new);
    }

    public static void onEndTick(Minecraft client) {
        // We are on the CLIENT here
        while(ModKeyMappings.KAUPEN_KEYMAPPING.consumeClick()) {
            client.player.sendSystemMessage(Component.literal("I just pressed the Kaupen Key (Default: K)"));
            ClientPlayNetworking.send(new TestPayloadC2S("Kaupenjoe", 42));
        }
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/TutorialModDataGenerator.java

```java
package net.kaupenjoe.tutorialmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.kaupenjoe.tutorialmod.datagen.*;
import net.kaupenjoe.tutorialmod.datagen.villager.ModPOITags;
import net.kaupenjoe.tutorialmod.datagen.villager.ModTradeSets;
import net.kaupenjoe.tutorialmod.datagen.villager.ModVillagerTradeTags;
import net.kaupenjoe.tutorialmod.datagen.villager.ModVillagerTrades;
import net.kaupenjoe.tutorialmod.worldgen.ModConfiguredFeatures;
import net.kaupenjoe.tutorialmod.worldgen.ModPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModEquipmentAssetProvider::new);
		pack.addProvider(ModRegistryDataProvider::new);
		pack.addProvider(ModPaintingTagsProvider::new);
		pack.addProvider(ModSoundsProvider::new);
		pack.addProvider(ModAdvancementsProvider::new);
		pack.addProvider(ModVillagerTradeTags::new);
		pack.addProvider(ModPOITags::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.PAINTING_VARIANT, ModPaintings::bootstrap);
		registryBuilder.add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap);
		registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);

		registryBuilder.add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap);
		registryBuilder.add(Registries.TRADE_SET, ModTradeSets::bootstrap);

		registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
```

### src/main/java/net/kaupenjoe/tutorialmod/villager/ModVillagers.java

```java
package net.kaupenjoe.tutorialmod.villager;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PoiHelper;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.datagen.villager.ModTradeSets;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;

public class ModVillagers {
    public static final ResourceKey<PoiType> KAUPEN_POI_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "kaupen_poi"));
    public static final PoiType KAUPEN_POI = PoiHelper.register(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "kaupen_poi"),
            1, 1, ModBlocks.MAGIC_BLOCK);

    public static final VillagerProfession KAUPENGER = registerVillagerProfession("kaupenger", "Kaupenger", KAUPEN_POI_KEY,
            SoundEvents.AMETHYST_CLUSTER_PLACE, Int2ObjectMap.ofEntries(
                    Int2ObjectMap.entry(1, ModTradeSets.KAUPENGER_LEVEL_1),
                    Int2ObjectMap.entry(2, ModTradeSets.KAUPENGER_LEVEL_2)
            ));


    private static VillagerProfession registerVillagerProfession(String name, String title, ResourceKey<PoiType> poi,
                                                                 SoundEvent sound, Int2ObjectMap<ResourceKey<TradeSet>> map) {
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                new VillagerProfession(
                Component.literal(title), holder -> holder.is(poi), holder -> holder.is(poi),
                ImmutableSet.of(), ImmutableSet.of(), sound, map));
    }

    public static void register() {
        TutorialMod.LOGGER.info("Registering ModVillagers for " + TutorialMod.MOD_ID);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/worldgen/gen/ModWorldGeneration.java

```java
package net.kaupenjoe.tutorialmod.worldgen.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.kaupenjoe.tutorialmod.worldgen.ModPlacedFeatures;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        /* UNDERGROUND ORES */
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.OVERWORLD_FLUORITE_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.NETHER_FLUORITE_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.END_FLUORITE_ORE_PLACED_KEY);

        // Example for individual Biomes
        // BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DEEP_OCEAN, Biomes.BADLANDS),
        //         GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.OVERWORLD_FLUORITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.MEADOW, Biomes.STONY_PEAKS, Biomes.STONY_SHORE),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BALSA_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.MEADOW, Biomes.FOREST, Biomes.BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.HONEY_BERRY_BUSH_PLACED_KEY);
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/worldgen/ModConfiguredFeatures.java

```java
package net.kaupenjoe.tutorialmod.worldgen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    // CF => Features with Configuration
    // Tree --> height, what trunks etc etc...
    // How something looks like
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_FLUORITE_ORE_KEY = registerKey("overworld_fluorite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_FLUORITE_ORE_KEY = registerKey("nether_fluorite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_FLUORITE_ORE_KEY = registerKey("end_fluorite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BALSA_KEY = registerKey("balsa");

    public static final ResourceKey<ConfiguredFeature<?, ?>> HONEY_BERRY_BUSH_KEY = registerKey("honey_berry_bush");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        register(context, OVERWORLD_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.FLUORITE_ORE.defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables, ModBlocks.FLUORITE_DEEPSLATE_ORE.defaultBlockState())),
                9));
        register(context, NETHER_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherReplaceables,
                ModBlocks.FLUORITE_NETHER_ORE.defaultBlockState(), 10));
        register(context, END_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.FLUORITE_END_ORE.defaultBlockState(), 12));

        register(context, BALSA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BALSA_LOG),
                new BendingTrunkPlacer(3, 3, 4, 2, ConstantInt.of(5)),

                BlockStateProvider.simple(ModBlocks.BALSA_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.DIRT)).build());

        register(context, HONEY_BERRY_BUSH_KEY, Feature.SIMPLE_RANDOM_SELECTOR,
                new CompositeFeatureConfiguration(
                        HolderSet.direct(PlacementUtils.inlinePlaced(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.HONEY_BERRY_BUSH
                                        .defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))),
                                CountPlacement.of(32),
                                RandomOffsetPlacement.ofTriangle(6, 3),
                                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)))));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/worldgen/ModPlacedFeatures.java

```java
package net.kaupenjoe.tutorialmod.worldgen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    // PF has CF
    // CF Placed down in the world
    // How placed, how many placed, where placed.
    public static final ResourceKey<PlacedFeature> OVERWORLD_FLUORITE_ORE_PLACED_KEY = registerKey("overworld_fluorite_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_FLUORITE_ORE_PLACED_KEY = registerKey("nether_fluorite_ore_placed");
    public static final ResourceKey<PlacedFeature> END_FLUORITE_ORE_PLACED_KEY = registerKey("end_fluorite_ore_placed");

    public static final ResourceKey<PlacedFeature> BALSA_PLACED_KEY = registerKey("balsa_placed");

    public static final ResourceKey<PlacedFeature> HONEY_BERRY_BUSH_PLACED_KEY = registerKey("honey_berry_bush_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, OVERWORLD_FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_FLUORITE_ORE_KEY),
                OrePlacements.commonOrePlacement(12,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(100))));
        register(context, NETHER_FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_FLUORITE_ORE_KEY),
                OrePlacements.commonOrePlacement(9,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(100))));
        register(context, END_FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_FLUORITE_ORE_KEY),
                OrePlacements.commonOrePlacement(12,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(100))));

        register(context, BALSA_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BALSA_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.BALSA_SAPLING));

        register(context, HONEY_BERRY_BUSH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HONEY_BERRY_BUSH_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
```

### src/main/java/net/kaupenjoe/tutorialmod/worldgen/tree/ModTreeGrowers.java

```java
package net.kaupenjoe.tutorialmod.worldgen.tree;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower BALSA = new TreeGrower(TutorialMod.MOD_ID + ":balsa",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BALSA_KEY), Optional.empty());
}
```

### src/main/resources/assets/tutorialmod/lang/en_us.json

```json
{
  "item.tutorialmod.fluorite": "Fluorite",
  "item.tutorialmod.raw_fluorite": "Raw Fluorite",
  "item.tutorialmod.chisel": "Chisel",
  "item.tutorialmod.strawberry": "Strawberry",
  "item.tutorialmod.combustible_spores": "Combustible Spores",

  "item.tutorialmod.fluorite_sword": "Fluorite Sword",
  "item.tutorialmod.fluorite_pickaxe": "Fluorite Pickaxe",
  "item.tutorialmod.fluorite_shovel": "Fluorite Shovel",
  "item.tutorialmod.fluorite_axe": "Fluorite Axe",
  "item.tutorialmod.fluorite_hoe": "Fluorite Hoe",
  "item.tutorialmod.fluorite_spear": "Fluorite Spear",

  "item.tutorialmod.fluorite_helmet": "Fluorite Helmet",
  "item.tutorialmod.fluorite_chestplate": "Fluorite Chestplate",
  "item.tutorialmod.fluorite_leggings": "Fluorite Leggings",
  "item.tutorialmod.fluorite_boots": "Fluorite Boots",

  "item.tutorialmod.fluorite_horse_armor": "Fluorite Horse Armor",
  "item.tutorialmod.kaupen_bow": "Kaupen Bow",
  "item.tutorialmod.sculkbeam_staff": "Sculkbeam Staff",

  "item.tutorialmod.strawberry_seeds": "Strawberry Seeds",
  "item.tutorialmod.honey_berries": "Honey Berries",
  "item.tutorialmod.rice_shoot": "Rice Shoot",

  "item.tutorialmod.bar_brawl_music_disc": "Bar Brawl Music Disc",
  "item.tutorialmod.spectre_staff": "Spectre Staff",


  "item.minecraft.potion.effect.stinky_potion": "Stinky Potion",
  "item.minecraft.splash_potion.effect.stinky_potion": "Stinky Splash Potion",
  "item.minecraft.lingering_potion.effect.stinky_potion": "Stinky Lingering Potion",


  "jukebox_song.tutorialmod.bar_brawl": "Bryan Tech - Bar Brawl (CC0)",

  "death.attack.stinky": "%s was stinkied to death!",
  "death.attack.stinky.player": "%s was stinkied to death trying to outrun %s",


  "block.tutorialmod.fluorite_block": "Block of Fluorite",
  "block.tutorialmod.raw_fluorite_block": "Block of Raw Fluorite",
  "block.tutorialmod.fluorite_ore": "Fluorite Ore",
  "block.tutorialmod.fluorite_deepslate_ore": "Fluorite Deepslate Ore",
  "block.tutorialmod.fluorite_nether_ore": "Fluorite Nether Ore",
  "block.tutorialmod.fluorite_end_ore": "Fluorite End Ore",
  "block.tutorialmod.magic_block": "Magic Block",

  "block.tutorialmod.fluorite_stairs": "Fluorite Stairs",
  "block.tutorialmod.fluorite_slab": "Fluorite Slab",
  "block.tutorialmod.fluorite_button": "Fluorite Button",
  "block.tutorialmod.fluorite_pressure_plate": "Fluorite Pressure Plate",
  "block.tutorialmod.fluorite_fence": "Fluorite Fence",
  "block.tutorialmod.fluorite_fence_gate": "Fluorite Fence Gate",
  "block.tutorialmod.fluorite_wall": "Fluorite Wall",
  "block.tutorialmod.fluorite_door": "Fluorite Door",
  "block.tutorialmod.fluorite_trapdoor": "Fluorite Trapdoor",

  "block.tutorialmod.fluorite_lamp": "Fluorite Lamp",
  "block.tutorialmod.pedestal": "Pedestal",
  "block.tutorialmod.crystallizer": "Crystallizer",


  "block.tutorialmod.strawberry_crop": "Strawberry Crop",
  "block.tutorialmod.honey_berry_bush": "Honey Berry Bush",
  "block.tutorialmod.rice_crop": "Rice Crop",

  "block.tutorialmod.balsa_log": "Balsa Log",
  "block.tutorialmod.balsa_wood": "Balsa Wood",
  "block.tutorialmod.stripped_balsa_log": "Stripped Balsa Log",
  "block.tutorialmod.stripped_balsa_wood": "Stripped Balsa Wood",
  "block.tutorialmod.balsa_planks": "Balsa Planks",
  "block.tutorialmod.balsa_leaves": "Balsa Leaves",

  "block.tutorialmod.balsa_sapling": "Balsa Sapling",
  "block.tutorialmod.potted_balsa_sapling": "Potted Balsa Sapling",


  "painting.tutorialmod.world.title": "World",
  "painting.tutorialmod.world.author": "NanoAttack",
  "painting.tutorialmod.shrimp.title": "Shrimp",
  "painting.tutorialmod.shrimp.author": "NanoAttack",
  "painting.tutorialmod.saw_them.title": "Saw Them",
  "painting.tutorialmod.saw_them.author": "NanoAttack",
  "painting.tutorialmod.wanderer.title": "Wanderer",
  "painting.tutorialmod.wanderer.author": "PlatinumG17",

  "effect.tutorialmod.stinky": "Stinky",
  "effect.tutorialmod.stinky.description": "Causes entities around you to take damage",

  "entity.minecraft.villager.tutorialmod.kaupenger": "Kaupenger",

  "stat.tutorialmod.chisel_used": "Blocks Chiseled",

  "sounds.tutorialmod.chisel_use": "Using Chisel",
  "subtitles.tutorialmod.bar_brawl": "Bar Brawl Playing",

  "creativemodetab.tutorialmod.fluorite_items": "Fluorite Tutorial Items",
  "creativemodetab.tutorialmod.fluorite_blocks": "Fluorite Tutorial Blocks",

  "tooltip.tutorialmod.chisel": "Press §eShift§r for more Information!",
  "tooltip.tutorialmod.chisel.shift_down": "Chisels Blocks into other Blocks",
  "tooltip.tutorialmod.strawberry": "This is delicious!",
  "tooltip.tutorialmod.magic_block": "This block seems quite §2MAGICAL§r",

  "key.tutorialmod.kaupen_key": "Kaupen Key",

  "advancements.tutorialmod.root.title": "TutorialMod Advancements",
  "advancements.tutorialmod.root.description": "TutorialMod Advancements Pretty Cool!",

  "advancements.tutorialmod.plant_custom.title": "Plant a Custom Plant",
  "advancements.tutorialmod.plant_custom.description": "Planting these custom plants is pretty neat!",

  "advancements.tutorialmod.chisel_stone.title": "Chiseled in History!",
  "advancements.tutorialmod.chisel_stone.description": "You used a chisel on Stone. Awesome!"
}
```

### src/main/resources/assets/tutorialmod/models/block/pedestal.json

```json
{
	"format_version": "1.21.11",
	"credit": "Made with Blockbench",
	"texture_size": [64, 64],
	"textures": {
		"0": "tutorialmod:block/pedestal",
		"particle": "tutorialmod:block/pedestal"
	},
	"elements": [
		{
			"from": [1, 0, 1],
			"to": [15, 2, 15],
			"rotation": {"angle": 0, "axis": "y", "origin": [1, 0, 1]},
			"faces": {
				"north": {"uv": [6.5, 3, 10, 3.5], "texture": "#0"},
				"east": {"uv": [6.5, 3.5, 10, 4], "texture": "#0"},
				"south": {"uv": [6.5, 4, 10, 4.5], "texture": "#0"},
				"west": {"uv": [6.5, 4.5, 10, 5], "texture": "#0"},
				"up": {"uv": [3.5, 3.5, 0, 0], "texture": "#0"},
				"down": {"uv": [3.5, 3.5, 0, 7], "texture": "#0"}
			}
		},
		{
			"from": [2, 2, 2],
			"to": [14, 5, 14],
			"rotation": {"angle": 0, "axis": "y", "origin": [2, 2, 2]},
			"faces": {
				"north": {"uv": [6.5, 0, 9.5, 0.75], "texture": "#0"},
				"east": {"uv": [6.5, 0.75, 9.5, 1.5], "texture": "#0"},
				"south": {"uv": [6.5, 1.5, 9.5, 2.25], "texture": "#0"},
				"west": {"uv": [6.5, 2.25, 9.5, 3], "texture": "#0"},
				"up": {"uv": [6.5, 3, 3.5, 0], "texture": "#0"},
				"down": {"uv": [6.5, 3, 3.5, 6], "texture": "#0"}
			}
		},
		{
			"from": [6, 5, 6],
			"to": [10, 11, 10],
			"rotation": {"angle": 0, "axis": "y", "origin": [6, 5, 6]},
			"faces": {
				"north": {"uv": [0, 7, 1, 8.5], "texture": "#0"},
				"east": {"uv": [1, 7, 2, 8.5], "texture": "#0"},
				"south": {"uv": [2, 7, 3, 8.5], "texture": "#0"},
				"west": {"uv": [7.5, 5, 8.5, 6.5], "texture": "#0"},
				"up": {"uv": [7.5, 6, 6.5, 5], "texture": "#0"},
				"down": {"uv": [6.5, 8, 5.5, 9], "texture": "#0"}
			}
		},
		{
			"from": [4, 11, 4],
			"to": [12, 12, 12],
			"rotation": {"angle": 0, "axis": "y", "origin": [7, 11, 7]},
			"faces": {
				"north": {"uv": [0, 9, 2, 9.25], "texture": "#0"},
				"east": {"uv": [2, 9, 4, 9.25], "texture": "#0"},
				"south": {"uv": [4, 9, 6, 9.25], "texture": "#0"},
				"west": {"uv": [6, 9, 8, 9.25], "texture": "#0"},
				"up": {"uv": [5.5, 8, 3.5, 6], "texture": "#0"},
				"down": {"uv": [7.5, 6, 5.5, 8], "texture": "#0"}
			}
		},
		{
			"from": [3, 11, 3],
			"to": [13, 13, 4],
			"rotation": {"angle": 0, "axis": "y", "origin": [6, 11, 3]},
			"faces": {
				"north": {"uv": [7.5, 6.5, 10, 7], "texture": "#0"},
				"east": {"uv": [9.5, 0.25, 9.75, 0.75], "texture": "#0"},
				"south": {"uv": [7.5, 7, 10, 7.5], "texture": "#0"},
				"west": {"uv": [9.5, 0.75, 9.75, 1.25], "texture": "#0"},
				"up": {"uv": [10.5, 8.75, 8, 8.5], "texture": "#0"},
				"down": {"uv": [11, 8, 8.5, 8.25], "texture": "#0"}
			}
		},
		{
			"from": [3, 11, 12],
			"to": [13, 13, 13],
			"rotation": {"angle": 0, "axis": "y", "origin": [4, 11, 11]},
			"faces": {
				"north": {"uv": [7.5, 7.5, 10, 8], "texture": "#0"},
				"east": {"uv": [9.5, 1.25, 9.75, 1.75], "texture": "#0"},
				"south": {"uv": [3, 8, 5.5, 8.5], "texture": "#0"},
				"west": {"uv": [9.5, 1.75, 9.75, 2.25], "texture": "#0"},
				"up": {"uv": [11, 8.5, 8.5, 8.25], "texture": "#0"},
				"down": {"uv": [10.5, 8.75, 8, 9], "texture": "#0"}
			}
		},
		{
			"from": [3, 11, 4],
			"to": [4, 13, 12],
			"rotation": {"angle": 0, "axis": "y", "origin": [2, 11, 6]},
			"faces": {
				"north": {"uv": [9.5, 2.25, 9.75, 2.75], "texture": "#0"},
				"east": {"uv": [6.5, 8, 8.5, 8.5], "texture": "#0"},
				"south": {"uv": [3, 9.5, 3.25, 10], "texture": "#0"},
				"west": {"uv": [0, 8.5, 2, 9], "texture": "#0"},
				"up": {"uv": [8.25, 11, 8, 9], "texture": "#0"},
				"down": {"uv": [8.5, 9, 8.25, 11], "texture": "#0"}
			}
		},
		{
			"from": [12, 11, 4],
			"to": [13, 13, 12],
			"rotation": {"angle": 0, "axis": "y", "origin": [12, 11, 4]},
			"faces": {
				"north": {"uv": [3.25, 9.5, 3.5, 10], "texture": "#0"},
				"east": {"uv": [2, 8.5, 4, 9], "texture": "#0"},
				"south": {"uv": [3.5, 9.5, 3.75, 10], "texture": "#0"},
				"west": {"uv": [8.5, 5, 10.5, 5.5], "texture": "#0"},
				"up": {"uv": [8.75, 11, 8.5, 9], "texture": "#0"},
				"down": {"uv": [9, 9, 8.75, 11], "texture": "#0"}
			}
		},
		{
			"from": [5, 9, 5],
			"to": [11, 11, 6],
			"rotation": {"angle": 0, "axis": "y", "origin": [5, 9, 4]},
			"faces": {
				"north": {"uv": [4, 8.5, 5.5, 9], "texture": "#0"},
				"east": {"uv": [3.75, 9.5, 4, 10], "texture": "#0"},
				"south": {"uv": [8.5, 5.5, 10, 6], "texture": "#0"},
				"west": {"uv": [4, 9.5, 4.25, 10], "texture": "#0"},
				"up": {"uv": [4.5, 9.5, 3, 9.25], "texture": "#0"},
				"down": {"uv": [6, 9.25, 4.5, 9.5], "texture": "#0"}
			}
		},
		{
			"from": [5, 9, 6],
			"to": [6, 11, 10],
			"rotation": {"angle": 0, "axis": "y", "origin": [4, 9, 8]},
			"faces": {
				"north": {"uv": [4.25, 9.5, 4.5, 10], "texture": "#0"},
				"east": {"uv": [9, 9, 10, 9.5], "texture": "#0"},
				"south": {"uv": [4.5, 9.5, 4.75, 10], "texture": "#0"},
				"west": {"uv": [0, 9.25, 1, 9.75], "texture": "#0"},
				"up": {"uv": [3.25, 8, 3, 7], "texture": "#0"},
				"down": {"uv": [3.5, 7, 3.25, 8], "texture": "#0"}
			}
		},
		{
			"from": [5, 9, 10],
			"to": [11, 11, 11],
			"rotation": {"angle": 0, "axis": "y", "origin": [10, 9, 10]},
			"faces": {
				"north": {"uv": [8.5, 6, 10, 6.5], "texture": "#0"},
				"east": {"uv": [4.75, 9.5, 5, 10], "texture": "#0"},
				"south": {"uv": [6.5, 8.5, 8, 9], "texture": "#0"},
				"west": {"uv": [5, 9.5, 5.25, 10], "texture": "#0"},
				"up": {"uv": [7.5, 9.5, 6, 9.25], "texture": "#0"},
				"down": {"uv": [11, 0, 9.5, 0.25], "texture": "#0"}
			}
		},
		{
			"from": [10, 9, 6],
			"to": [11, 11, 10],
			"rotation": {"angle": 0, "axis": "y", "origin": [10, 9, 8]},
			"faces": {
				"north": {"uv": [5.25, 9.5, 5.5, 10], "texture": "#0"},
				"east": {"uv": [1, 9.25, 2, 9.75], "texture": "#0"},
				"south": {"uv": [5.5, 9.5, 5.75, 10], "texture": "#0"},
				"west": {"uv": [2, 9.25, 3, 9.75], "texture": "#0"},
				"up": {"uv": [7.75, 10.25, 7.5, 9.25], "texture": "#0"},
				"down": {"uv": [8, 9.25, 7.75, 10.25], "texture": "#0"}
			}
		}
	],
	"display": {
		"thirdperson_righthand": {
			"translation": [0, 1, 2],
			"scale": [0.25, 0.25, 0.25]
		},
		"thirdperson_lefthand": {
			"translation": [0, 1, 2],
			"scale": [0.25, 0.25, 0.25]
		},
		"firstperson_righthand": {
			"scale": [0.85, 0.85, 0.85]
		},
		"firstperson_lefthand": {
			"scale": [0.85, 0.85, 0.85]
		},
		"ground": {
			"translation": [0, 1, 0],
			"scale": [0.65, 0.65, 0.65]
		},
		"gui": {
			"rotation": [25, 36, -12],
			"translation": [0.5, 0.75, 0],
			"scale": [0.75, 0.75, 0.75]
		},
		"head": {
			"translation": [0, 12.5, 0]
		},
		"fixed": {
			"rotation": [-10, -55, 10],
			"translation": [-1, 1, 0.75],
			"scale": [0.85, 0.85, 0.85]
		}
	}
}
```

### src/main/resources/assets/tutorialmod/models/item/sculkbeam_staff.json

```json
{
	"format_version": "1.21.11",
	"credit": "Made with Blockbench",
	"texture_size": [32, 32],
	"textures": {
		"0": "tutorialmod:item/sculkbeam_staff_3d",
		"particle": "tutorialmod:item/sculkbeam_staff_3d"
	},
	"elements": [
		{
			"name": "staff_grip",
			"from": [7.25, 2, 7.25],
			"to": [8.75, 13, 8.75],
			"rotation": {"angle": 0, "axis": "y", "origin": [8, 6.5, 8]},
			"faces": {
				"north": {"uv": [0, 0, 1, 5.5], "texture": "#0"},
				"east": {"uv": [1.5, 0, 2.5, 5.5], "texture": "#0"},
				"south": {"uv": [3, 0, 4, 5.5], "texture": "#0"},
				"west": {"uv": [4.5, 0, 5.5, 5.5], "texture": "#0"},
				"up": {"uv": [7, 8, 6, 7], "texture": "#0"},
				"down": {"uv": [8.5, 0, 7.5, 1], "texture": "#0"}
			}
		},
		{
			"name": "staff_pommel_ring_bottom",
			"from": [7, 3, 7],
			"to": [9, 4, 9],
			"faces": {
				"north": {"uv": [9, 4, 10, 4.5], "texture": "#0"},
				"east": {"uv": [4.5, 9, 5.5, 9.5], "texture": "#0"},
				"south": {"uv": [9, 5, 10, 5.5], "texture": "#0"},
				"west": {"uv": [9, 6, 10, 6.5], "texture": "#0"},
				"up": {"uv": [5.5, 7, 4.5, 6], "texture": "#0"},
				"down": {"uv": [7, 5.5, 6, 6.5], "texture": "#0"}
			}
		},
		{
			"name": "staff_pommel_bottom",
			"from": [7, 0, 7],
			"to": [9, 2, 9],
			"rotation": {"angle": 0, "axis": "y", "origin": [8, 1, 8]},
			"faces": {
				"north": {"uv": [6, 2, 7, 3], "texture": "#0"},
				"east": {"uv": [12.5, 0.5, 13.5, 1.5], "texture": "#0"},
				"south": {"uv": [12, 0.5, 13, 1.5], "texture": "#0"},
				"west": {"uv": [7.5, 1.5, 8.5, 2.5], "texture": "#0"},
				"up": {"uv": [13, 1, 12, 0], "rotation": 180, "texture": "#0"},
				"down": {"uv": [7, 5.5, 6, 6.5], "rotation": 180, "texture": "#0"}
			}
		},
		{
			"name": "staff_pommel_ring_top",
			"from": [7, 11, 7],
			"to": [9, 12, 9],
			"faces": {
				"north": {"uv": [10.5, 1, 11.5, 1.5], "texture": "#0"},
				"east": {"uv": [10.5, 2, 11.5, 2.5], "texture": "#0"},
				"south": {"uv": [10.5, 3, 11.5, 3.5], "texture": "#0"},
				"west": {"uv": [10.5, 4, 11.5, 4.5], "texture": "#0"},
				"up": {"uv": [8.5, 5.5, 7.5, 4.5], "texture": "#0"},
				"down": {"uv": [8.5, 6, 7.5, 7], "texture": "#0"}
			}
		},
		{
			"name": "chappe_base",
			"from": [7, 13, 7],
			"to": [9, 14, 9],
			"faces": {
				"north": {"uv": [7.5, 7.5, 8.5, 8], "texture": "#0"},
				"east": {"uv": [0, 8, 1, 8.5], "texture": "#0"},
				"south": {"uv": [1.5, 8, 2.5, 8.5], "texture": "#0"},
				"west": {"uv": [6, 8.5, 7, 9], "texture": "#0"},
				"up": {"uv": [4, 7, 3, 6], "texture": "#0"},
				"down": {"uv": [7, 4, 6, 5], "texture": "#0"}
			}
		},
		{
			"name": "crystal_base",
			"from": [7, 14, 7],
			"to": [9, 17, 9],
			"faces": {
				"north": {"uv": [0, 6, 1, 7.5], "texture": "#0"},
				"east": {"uv": [6, 0, 7, 1.5], "texture": "#0"},
				"south": {"uv": [1.5, 6, 2.5, 7.5], "texture": "#0"},
				"west": {"uv": [6, 2, 7, 3.5], "texture": "#0"},
				"up": {"uv": [8.5, 2.5, 7.5, 1.5], "texture": "#0"},
				"down": {"uv": [4, 7.5, 3, 8.5], "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_east_west",
			"from": [8, 15, 6.5],
			"to": [8, 20, 9.5],
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"east": {"uv": [12, 0, 13.5, 2.5], "texture": "#0"},
				"south": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"west": {"uv": [12, 0, 13.5, 2.5], "texture": "#0"},
				"up": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 1.5], "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [8, 17, 9.5],
			"to": [8, 19, 10.5],
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"east": {"uv": [13, 1.5, 13.5, 2.5], "texture": "#0"},
				"south": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"west": {"uv": [12, 1.5, 12.5, 2.5], "texture": "#0"},
				"up": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 1.5], "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [8, 20, 7.5],
			"to": [8, 21, 8.5],
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"east": {"uv": [13, 0, 13.5, 0.5], "texture": "#0"},
				"south": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"west": {"uv": [12.5, 1.5, 13, 2], "texture": "#0"},
				"up": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 1.5], "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [8, 17, 5.5],
			"to": [8, 19, 6.5],
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"east": {"uv": [13, 1.5, 13.5, 2.5], "texture": "#0"},
				"south": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"west": {"uv": [12, 1.5, 12.5, 2.5], "texture": "#0"},
				"up": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 1.5], "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_east_west",
			"from": [6.5, 15, 8],
			"to": [9.5, 20, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [8, 16, 8]},
			"faces": {
				"north": {"uv": [12, 0, 13.5, 2.5], "texture": "#0"},
				"east": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"south": {"uv": [12, 0, 13.5, 2.5], "texture": "#0"},
				"west": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [5.5, 17, 8],
			"to": [6.5, 19, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [8, 16, 8]},
			"faces": {
				"north": {"uv": [12, 1.5, 12.5, 2.5], "texture": "#0"},
				"east": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"south": {"uv": [13, 1.5, 13.5, 2.5], "texture": "#0"},
				"west": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [7.5, 20, 8],
			"to": [8.5, 21, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [8, 16, 8]},
			"faces": {
				"north": {"uv": [12.5, 1.5, 13, 2], "texture": "#0"},
				"east": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"south": {"uv": [13, 0, 13.5, 0.5], "texture": "#0"},
				"west": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [9.5, 17, 8],
			"to": [10.5, 19, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [8, 16, 8]},
			"faces": {
				"north": {"uv": [12, 1.5, 12.5, 2.5], "texture": "#0"},
				"east": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"south": {"uv": [13, 1.5, 13.5, 2.5], "texture": "#0"},
				"west": {"uv": [0, 0, 0, 2.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_base",
			"from": [4.5, 14.5, 7.5],
			"to": [7, 15.5, 8.5],
			"rotation": {"angle": 0, "axis": "y", "origin": [4.375, 15, 8]},
			"faces": {
				"north": {"uv": [0, 6, 1, 7.5], "rotation": 90, "texture": "#0"},
				"east": {"uv": [4, 7.5, 3, 8.5], "rotation": 270, "texture": "#0"},
				"south": {"uv": [1.5, 6, 2.5, 7.5], "rotation": 270, "texture": "#0"},
				"west": {"uv": [8.5, 2.5, 7.5, 1.5], "rotation": 270, "texture": "#0"},
				"up": {"uv": [6, 0, 7, 1.5], "rotation": 270, "texture": "#0"},
				"down": {"uv": [6, 2, 7, 3.5], "rotation": 270, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_east_west",
			"from": [2, 15, 7],
			"to": [6, 15, 9],
			"rotation": {"angle": 0, "axis": "y", "origin": [4.375, 15, 8]},
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"},
				"south": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"west": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"},
				"up": {"uv": [12, 0, 13.5, 2.5], "rotation": 270, "texture": "#0"},
				"down": {"uv": [12, 0, 13.5, 2.5], "rotation": 270, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [2, 15, 9],
			"to": [4, 15, 9.5],
			"rotation": {"angle": 0, "axis": "y", "origin": [4.375, 15, 8]},
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"},
				"south": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"west": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"},
				"up": {"uv": [13, 1.5, 13.5, 2.5], "rotation": 270, "texture": "#0"},
				"down": {"uv": [12, 1.5, 12.5, 2.5], "rotation": 270, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [1, 15, 7.5],
			"to": [2, 15, 8.5],
			"rotation": {"angle": 0, "axis": "y", "origin": [4.375, 15, 8]},
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"},
				"south": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"west": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"},
				"up": {"uv": [13, 0, 13.5, 0.5], "rotation": 270, "texture": "#0"},
				"down": {"uv": [12.5, 1.5, 13, 2], "rotation": 270, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [2, 15, 6.5],
			"to": [4, 15, 7],
			"rotation": {"angle": 0, "axis": "y", "origin": [4.375, 15, 8]},
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"},
				"south": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"west": {"uv": [0, 0, 0, 1.5], "rotation": 270, "texture": "#missing"},
				"up": {"uv": [13, 1.5, 13.5, 2.5], "rotation": 270, "texture": "#0"},
				"down": {"uv": [12, 1.5, 12.5, 2.5], "rotation": 270, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_east_west",
			"from": [2, 14, 8],
			"to": [6, 16, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [4.375, 15, 8]},
			"faces": {
				"north": {"uv": [12, 0, 13.5, 2.5], "rotation": 90, "texture": "#0"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 180, "texture": "#missing"},
				"south": {"uv": [12, 0, 13.5, 2.5], "rotation": 270, "texture": "#0"},
				"west": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [2, 13.5, 8],
			"to": [4, 14, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [4.375, 15, 8]},
			"faces": {
				"north": {"uv": [12, 1.5, 12.5, 2.5], "rotation": 90, "texture": "#0"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 180, "texture": "#missing"},
				"south": {"uv": [13, 1.5, 13.5, 2.5], "rotation": 270, "texture": "#0"},
				"west": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [1, 14.5, 8],
			"to": [2, 15.5, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [4.375, 15, 8]},
			"faces": {
				"north": {"uv": [12.5, 1.5, 13, 2], "rotation": 90, "texture": "#0"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 180, "texture": "#missing"},
				"south": {"uv": [13, 0, 13.5, 0.5], "rotation": 270, "texture": "#0"},
				"west": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [2, 16, 8],
			"to": [4, 16.5, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [4.375, 15, 8]},
			"faces": {
				"north": {"uv": [12, 1.5, 12.5, 2.5], "rotation": 90, "texture": "#0"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 180, "texture": "#missing"},
				"south": {"uv": [13, 1.5, 13.5, 2.5], "rotation": 270, "texture": "#0"},
				"west": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_base",
			"from": [8.75, 14.5, 7.5],
			"to": [11.25, 15.5, 8.5],
			"rotation": {"angle": 0, "axis": "y", "origin": [12.75, 13, 8]},
			"faces": {
				"north": {"uv": [0, 6, 1, 7.5], "rotation": 270, "texture": "#0"},
				"east": {"uv": [8.5, 2.5, 7.5, 1.5], "rotation": 90, "texture": "#0"},
				"south": {"uv": [1.5, 6, 2.5, 7.5], "rotation": 90, "texture": "#0"},
				"west": {"uv": [4, 7.5, 3, 8.5], "rotation": 90, "texture": "#0"},
				"up": {"uv": [6, 2, 7, 3.5], "rotation": 90, "texture": "#0"},
				"down": {"uv": [6, 0, 7, 1.5], "rotation": 90, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_east_west",
			"from": [9.75, 15, 7],
			"to": [13.75, 15, 9],
			"rotation": {"angle": 0, "axis": "y", "origin": [12.75, 13, 8]},
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"south": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"west": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"up": {"uv": [12, 0, 13.5, 2.5], "rotation": 90, "texture": "#0"},
				"down": {"uv": [12, 0, 13.5, 2.5], "rotation": 90, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [11.75, 15, 9],
			"to": [13.75, 15, 9.5],
			"rotation": {"angle": 0, "axis": "y", "origin": [12.75, 13, 8]},
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"south": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"west": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"up": {"uv": [12, 1.5, 12.5, 2.5], "rotation": 90, "texture": "#0"},
				"down": {"uv": [13, 1.5, 13.5, 2.5], "rotation": 90, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [13.75, 15, 7.5],
			"to": [14.75, 15, 8.5],
			"rotation": {"angle": 0, "axis": "y", "origin": [12.75, 13, 8]},
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"south": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"west": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"up": {"uv": [12.5, 1.5, 13, 2], "rotation": 90, "texture": "#0"},
				"down": {"uv": [13, 0, 13.5, 0.5], "rotation": 90, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [11.75, 15, 6.5],
			"to": [13.75, 15, 7],
			"rotation": {"angle": 0, "axis": "y", "origin": [12.75, 13, 8]},
			"faces": {
				"north": {"uv": [0, 0, 0, 2.5], "rotation": 270, "texture": "#missing"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"south": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"west": {"uv": [0, 0, 0, 1.5], "rotation": 90, "texture": "#missing"},
				"up": {"uv": [12, 1.5, 12.5, 2.5], "rotation": 90, "texture": "#0"},
				"down": {"uv": [13, 1.5, 13.5, 2.5], "rotation": 90, "texture": "#0"}
			}
		},
		{
			"name": "crystal_frond_east_west",
			"from": [9.75, 14, 8],
			"to": [13.75, 16, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [12.75, 13, 8]},
			"faces": {
				"north": {"uv": [12, 0, 13.5, 2.5], "rotation": 270, "texture": "#0"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 180, "texture": "#missing"},
				"south": {"uv": [12, 0, 13.5, 2.5], "rotation": 90, "texture": "#0"},
				"west": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [11.75, 16, 8],
			"to": [13.75, 16.5, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [12.75, 13, 8]},
			"faces": {
				"north": {"uv": [12, 1.5, 12.5, 2.5], "rotation": 270, "texture": "#0"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 180, "texture": "#missing"},
				"south": {"uv": [13, 1.5, 13.5, 2.5], "rotation": 90, "texture": "#0"},
				"west": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [13.75, 14.5, 8],
			"to": [14.75, 15.5, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [12.75, 13, 8]},
			"faces": {
				"north": {"uv": [12.5, 1.5, 13, 2], "rotation": 270, "texture": "#0"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 180, "texture": "#missing"},
				"south": {"uv": [13, 0, 13.5, 0.5], "rotation": 90, "texture": "#0"},
				"west": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"}
			}
		},
		{
			"name": "crystal_frond_side_east_west",
			"from": [11.75, 13.5, 8],
			"to": [13.75, 14, 8],
			"rotation": {"angle": 0, "axis": "y", "origin": [12.75, 13, 8]},
			"faces": {
				"north": {"uv": [12, 1.5, 12.5, 2.5], "rotation": 270, "texture": "#0"},
				"east": {"uv": [0, 0, 0, 1.5], "rotation": 180, "texture": "#missing"},
				"south": {"uv": [13, 1.5, 13.5, 2.5], "rotation": 90, "texture": "#0"},
				"west": {"uv": [0, 0, 0, 1.5], "texture": "#missing"},
				"up": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"},
				"down": {"uv": [0, 0, 0, 2.5], "rotation": 90, "texture": "#missing"}
			}
		}
	],
	"display": {
		"ground": {
			"rotation": [67.28, -28.64, -41.92],
			"translation": [0, -1.25, 0]
		},
		"gui": {
			"rotation": [0, 0, -46.5],
			"translation": [-0.75, -0.5, 0]
		},
		"fixed": {
			"translation": [0, -1.25, 0]
		}
	},
	"groups": [
		{
			"name": "hilt",
			"origin": [0, 0, 0],
			"scope": 0,
			"color": 0,
			"children": [
				0,
				1,
				2,
				3,
				{
					"name": "chappe",
					"origin": [0, 0, 0],
					"scope": 0,
					"color": 0,
					"children": [4]
				}
			]
		},
		{
			"name": "crystal",
			"origin": [8, 8, 8],
			"scope": 0,
			"color": 0,
			"children": [
				5,
				{
					"name": "crystal_fronds",
					"origin": [8, 8, 8],
					"scope": 0,
					"color": 0,
					"children": [
						{
							"name": "crystal_frond_west_side",
							"origin": [0, 0, 0],
							"scope": 0,
							"color": 0,
							"children": [6, 7, 8, 9]
						},
						{
							"name": "crystal_frond_north_side",
							"origin": [0, 0, 0],
							"scope": 0,
							"color": 0,
							"children": [10, 11, 12, 13]
						}
					]
				}
			]
		},
		{
			"name": "crystal_west",
			"origin": [8, 8, 8],
			"scope": 0,
			"color": 0,
			"children": [
				14,
				{
					"name": "crystal_fronds",
					"origin": [8, 8, 8],
					"scope": 0,
					"color": 0,
					"children": [
						{
							"name": "crystal_frond_west_side",
							"origin": [0, 0, 0],
							"scope": 0,
							"color": 0,
							"children": [15, 16, 17, 18]
						},
						{
							"name": "crystal_frond_north_side",
							"origin": [0, 0, 0],
							"scope": 0,
							"color": 0,
							"children": [19, 20, 21, 22]
						}
					]
				}
			]
		},
		{
			"name": "crystal_east",
			"origin": [8, 8, 8],
			"scope": 0,
			"color": 0,
			"children": [
				23,
				{
					"name": "crystal_fronds",
					"origin": [8, 8, 8],
					"scope": 0,
					"color": 0,
					"children": [
						{
							"name": "crystal_frond_west_side",
							"origin": [0, 0, 0],
							"scope": 0,
							"color": 0,
							"children": [24, 25, 26, 27]
						},
						{
							"name": "crystal_frond_north_side",
							"origin": [0, 0, 0],
							"scope": 0,
							"color": 0,
							"children": [28, 29, 30, 31]
						}
					]
				}
			]
		}
	]
}
```

### src/main/resources/assets/tutorialmod/textures/item/spectre_staff.png.mcmeta

```
{
    "animation":
    {
        "frametime":3,
		"frames": 
		[
		0, 1, 2, 3, 4, 5, 6, 7,	
		{
        "index": 8,
        "time": 9
		}, 
		9, 10, 11, 12
		]
    }
}
```

### src/main/resources/assets/tutorialmod/textures/painting/wanderer.png.mcmeta

```
{
    "animation":
    {
        "frametime":40,
        "width": 16,
        "height": 32
    }
}
```

### src/main/resources/fabric.mod.json

```json
{
	"schemaVersion": 1,
	"id": "tutorialmod",
	"version": "${version}",
	"name": "Tutorial Mod",
	"description": "This is a Tutorial Mod for Kaupenjoe's Tutorial Series on YouTube!",
	"authors": [
		"Kaupenjoe"
	],
	"contact": {
		"homepage": "https://kaupenjoe.net/",
		"sources": "https://github.com/Tutorials-By-Kaupenjoe/Fabric-Tutorial-26.X"
	},
	"license": "MIT-License",
	"icon": "assets/tutorialmod/icon.png",
	"environment": "*",
	"entrypoints": {
		"main": [
			"net.kaupenjoe.tutorialmod.TutorialMod"
		],
		"client": [
			"net.kaupenjoe.tutorialmod.TutorialModClient"
		],
		"fabric-datagen": [
			"net.kaupenjoe.tutorialmod.TutorialModDataGenerator"
		],
		"rei_client": [
			"net.kaupenjoe.tutorialmod.compat.TutorialModREIClient"
		],
		"rei_common": [
			"net.kaupenjoe.tutorialmod.compat.TutorialModREICommon"
		]
	},
	"mixins": [
		"tutorialmod.mixins.json"
	],
	"accessWidener": "tutorialmod.classtweaker",
	"depends": {
		"fabricloader": ">=0.19.2",
		"minecraft": "~26.2",
		"java": ">=25",
		"fabric-api": "*"
	}
}
```

### src/main/resources/tutorialmod.classtweaker

```
classTweaker v1 official
accessible method net/minecraft/data/worldgen/placement/OrePlacements orePlacement (Lnet/minecraft/world/level/levelgen/placement/PlacementModifier;Lnet/minecraft/world/level/levelgen/placement/PlacementModifier;)Ljava/util/List;
accessible method net/minecraft/data/worldgen/placement/OrePlacements commonOrePlacement (ILnet/minecraft/world/level/levelgen/placement/PlacementModifier;)Ljava/util/List;
accessible method net/minecraft/data/worldgen/placement/OrePlacements rareOrePlacement (ILnet/minecraft/world/level/levelgen/placement/PlacementModifier;)Ljava/util/List;
```

### src/main/resources/tutorialmod.mixins.json

```json
{
  "required": true,
  "package": "net.kaupenjoe.tutorialmod.mixin",
  "compatibilityLevel": "JAVA_25",
  "mixins": [
    "ExampleMixin"
  ],
  "injectors": {
    "defaultRequire": 1
  },
  "overwrites": {
    "requireAnnotations": true
  },
  "client": [
    "AbstractClientPlayerMixin"
  ]
}
```

## Skipped files

- `gradle/wrapper/gradle-wrapper.jar` — binary file
- `src/main/resources/assets/tutorialmod/icon.png` — binary file
- `src/main/resources/assets/tutorialmod/sounds/bar_brawl.ogg` — binary file
- `src/main/resources/assets/tutorialmod/sounds/chisel_use.ogg` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/balsa_leaves.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/balsa_log.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/balsa_log_top.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/balsa_planks.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/balsa_sapling.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/crystallizer_bottom.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/crystallizer_front.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/crystallizer_front_on.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/crystallizer_side.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/crystallizer_top.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_block.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_deepslate_ore.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_door_bottom.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_door_top.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_end_ore.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_lamp.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_lamp_on.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_nether_ore.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_ore.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/fluorite_trapdoor.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/honey_berry_bush_stage0.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/honey_berry_bush_stage1.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/honey_berry_bush_stage2.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/honey_berry_bush_stage3.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/magic_block.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/pedestal.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/raw_fluorite_block.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/rice_crop_stage0.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/rice_crop_stage1.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/rice_crop_stage2.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/rice_crop_stage3.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/rice_crop_stage4.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/rice_crop_stage5.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/rice_crop_stage6.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/rice_crop_stage7.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/strawberry_crop_stage0.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/strawberry_crop_stage1.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/strawberry_crop_stage2.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/strawberry_crop_stage3.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/strawberry_crop_stage4.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/strawberry_crop_stage5.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/stripped_balsa_log.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/block/stripped_balsa_log_top.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/entity/equipment/horse_body/fluorite.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/entity/equipment/humanoid/fluorite.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/entity/equipment/humanoid_baby/fluorite.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/entity/equipment/humanoid_leggings/fluorite.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/entity/villager/profession/kaupenger.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/gui/crystallizer/arrow_progress.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/gui/crystallizer/crystallizer_gui.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/gui/pedestal/pedestal_gui.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/bar_brawl_music_disc.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/chisel.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/chisel_used.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/combustible_spores.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_axe.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_boots.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_chestplate.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_door.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_helmet.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_hoe.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_horse_armor.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_leggings.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_pickaxe.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_shovel.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_spear.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_spear_in_hand.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/fluorite_sword.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/honey_berries.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/kaupen_bow.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/kaupen_bow_pulling_0.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/kaupen_bow_pulling_1.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/kaupen_bow_pulling_2.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/raw_fluorite.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/rice_shoot.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/sculkbeam_staff_3d.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/spectre_staff.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/strawberry.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/item/strawberry_seeds.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/mob_effect/stinky.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/painting/saw_them.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/painting/shrimp.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/painting/wanderer.png` — binary file
- `src/main/resources/assets/tutorialmod/textures/painting/world.png` — binary file
