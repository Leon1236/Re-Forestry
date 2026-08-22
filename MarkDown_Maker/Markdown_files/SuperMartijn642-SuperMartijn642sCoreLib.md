# SuperMartijn642-SuperMartijn642sCoreLib

## Directory structure

```text
SuperMartijn642-SuperMartijn642sCoreLib/
├── .github/
│   └── ISSUE_TEMPLATE/
│       ├── bug_report.yml
│       ├── crash_report.yml
│       ├── feature_request.yml
│       └── question.yml
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   ├── generated/
│   │   └── resources/
│   │       ├── .cache/
│   │       │   └── cache
│   │       ├── assets/
│   │       │   └── supermartijn642corelib/
│   │       │       └── lang/
│   │       │           └── en_us.json
│   │       └── data/
│   │           └── minecraft/
│   │               └── tags/
│   │                   └── blocks/
│   │                       ├── mineable/
│   │                       │   ├── axe.json
│   │                       │   ├── hoe.json
│   │                       │   ├── pickaxe.json
│   │                       │   └── shovel.json
│   │                       ├── needs_diamond_tool.json
│   │                       ├── needs_iron_tool.json
│   │                       └── needs_stone_tool.json
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── supermartijn642/
│   │   │           └── core/
│   │   │               ├── block/
│   │   │               │   ├── BaseBlock.java
│   │   │               │   ├── BaseBlockEntity.java
│   │   │               │   ├── BaseBlockEntityType.java
│   │   │               │   ├── BlockProperties.java
│   │   │               │   ├── BlockShape.java
│   │   │               │   ├── EntityHoldingBlock.java
│   │   │               │   └── TickableBlockEntity.java
│   │   │               ├── data/
│   │   │               │   ├── condition/
│   │   │               │   │   ├── AndResourceCondition.java
│   │   │               │   │   ├── ModLoadedResourceCondition.java
│   │   │               │   │   ├── NotResourceCondition.java
│   │   │               │   │   ├── OrResourceCondition.java
│   │   │               │   │   ├── ResourceCondition.java
│   │   │               │   │   ├── ResourceConditionContext.java
│   │   │               │   │   ├── ResourceConditions.java
│   │   │               │   │   ├── ResourceConditionSerializer.java
│   │   │               │   │   └── TagPopulatedResourceCondition.java
│   │   │               │   ├── recipe/
│   │   │               │   │   └── ConditionalRecipeSerializer.java
│   │   │               │   └── tag/
│   │   │               │       ├── entries/
│   │   │               │       │   └── NamespaceTagEntry.java
│   │   │               │       ├── CustomTagEntries.java
│   │   │               │       ├── CustomTagEntry.java
│   │   │               │       ├── CustomTagEntrySerializer.java
│   │   │               │       └── TagEntryAdapter.java
│   │   │               ├── extensions/
│   │   │               │   └── TagLoaderExtension.java
│   │   │               ├── generator/
│   │   │               │   ├── aggregator/
│   │   │               │   │   ├── ResourceAggregator.java
│   │   │               │   │   └── TranslationsAggregator.java
│   │   │               │   ├── standard/
│   │   │               │   │   ├── CoreLibLanguageGenerator.java
│   │   │               │   │   └── CoreLibMiningTagGenerator.java
│   │   │               │   ├── AdvancementGenerator.java
│   │   │               │   ├── BlockStateGenerator.java
│   │   │               │   ├── LanguageGenerator.java
│   │   │               │   ├── LootTableGenerator.java
│   │   │               │   ├── ModelGenerator.java
│   │   │               │   ├── RecipeGenerator.java
│   │   │               │   ├── ResourceCache.java
│   │   │               │   ├── ResourceGenerator.java
│   │   │               │   ├── ResourceType.java
│   │   │               │   └── TagGenerator.java
│   │   │               ├── gui/
│   │   │               │   ├── widget/
│   │   │               │   │   ├── premade/
│   │   │               │   │   │   ├── AbstractButtonWidget.java
│   │   │               │   │   │   ├── ButtonWidget.java
│   │   │               │   │   │   ├── LabelWidget.java
│   │   │               │   │   │   ├── ScissorWidget.java
│   │   │               │   │   │   ├── ScrollbarWidget.java
│   │   │               │   │   │   └── TextFieldWidget.java
│   │   │               │   │   ├── BaseContainerWidget.java
│   │   │               │   │   ├── BaseWidget.java
│   │   │               │   │   ├── BlockEntityBaseContainerWidget.java
│   │   │               │   │   ├── BlockEntityBaseWidget.java
│   │   │               │   │   ├── ContainerWidget.java
│   │   │               │   │   ├── ItemBaseContainerWidget.java
│   │   │               │   │   ├── ItemBaseWidget.java
│   │   │               │   │   ├── ObjectBaseContainerWidget.java
│   │   │               │   │   ├── ObjectBaseWidget.java
│   │   │               │   │   └── Widget.java
│   │   │               │   ├── BaseContainer.java
│   │   │               │   ├── BaseContainerType.java
│   │   │               │   ├── BlockEntityBaseContainer.java
│   │   │               │   ├── CursorType.java
│   │   │               │   ├── CursorTypes.java
│   │   │               │   ├── CustomSlot.java
│   │   │               │   ├── CustomSlotImpl.java
│   │   │               │   ├── ItemBaseContainer.java
│   │   │               │   ├── ObjectBaseContainer.java
│   │   │               │   ├── ScreenUtils.java
│   │   │               │   ├── WidgetContainerScreen.java
│   │   │               │   └── WidgetScreen.java
│   │   │               ├── item/
│   │   │               │   ├── BaseBlockItem.java
│   │   │               │   ├── BaseItem.java
│   │   │               │   ├── CreativeItemGroup.java
│   │   │               │   ├── ItemProperties.java
│   │   │               │   └── ItemRarity.java
│   │   │               ├── mixin/
│   │   │               │   ├── AbstractContainerScreenMixin.java
│   │   │               │   ├── BlockPropertiesAccessor.java
│   │   │               │   ├── CraftingHelperMixin.java
│   │   │               │   ├── DataGeneratorMixin.java
│   │   │               │   ├── DatagenModLoaderAccessor.java
│   │   │               │   ├── ForgeHooksMixin.java
│   │   │               │   ├── ForgeTagHandlerMixin.java
│   │   │               │   ├── GameDataMixin.java
│   │   │               │   ├── GameRendererMixin.java
│   │   │               │   ├── LevelRendererMixin.java
│   │   │               │   ├── TagBuilderMixin.java
│   │   │               │   └── TagCollectionReaderMixin.java
│   │   │               ├── network/
│   │   │               │   ├── BasePacket.java
│   │   │               │   ├── BlockEntityBasePacket.java
│   │   │               │   ├── BlockPosBasePacket.java
│   │   │               │   ├── PacketChannel.java
│   │   │               │   ├── PacketContext.java
│   │   │               │   └── PacketDirection.java
│   │   │               ├── registry/
│   │   │               │   ├── ClientRegistrationHandler.java
│   │   │               │   ├── GeneratorRegistrationHandler.java
│   │   │               │   ├── RegistrationHandler.java
│   │   │               │   ├── Registries.java
│   │   │               │   ├── RegistryEntryAcceptor.java
│   │   │               │   └── RegistryUtil.java
│   │   │               ├── render/
│   │   │               │   ├── BlockEntityCustomItemRenderer.java
│   │   │               │   ├── CustomBlockEntityRenderer.java
│   │   │               │   ├── CustomItemRenderer.java
│   │   │               │   ├── CustomRendererBakedModelWrapper.java
│   │   │               │   ├── RenderConfiguration.java
│   │   │               │   ├── RenderStateConfiguration.java
│   │   │               │   ├── RenderUtils.java
│   │   │               │   ├── RenderWorldEvent.java
│   │   │               │   └── TextureAtlases.java
│   │   │               ├── util/
│   │   │               │   ├── Either.java
│   │   │               │   ├── Holder.java
│   │   │               │   ├── MappedSetView.java
│   │   │               │   ├── Maybe.java
│   │   │               │   ├── Pair.java
│   │   │               │   ├── TriFunction.java
│   │   │               │   ├── Triple.java
│   │   │               │   └── TriPredicate.java
│   │   │               ├── ClientUtils.java
│   │   │               ├── CommonUtils.java
│   │   │               ├── CoreLib.java
│   │   │               ├── CoreSide.java
│   │   │               ├── EnergyFormat.java
│   │   │               └── TextComponents.java
│   │   └── resources/
│   │       ├── assets/
│   │       │   └── supermartijn642corelib/
│   │       │       └── textures/
│   │       │           └── gui/
│   │       │               ├── background.png
│   │       │               ├── buttons.png
│   │       │               ├── scrollbar_background.png
│   │       │               ├── scrollbar_background.png.mcmeta
│   │       │               ├── scroller.png
│   │       │               └── slot.png
│   │       ├── META-INF/
│   │       │   ├── accesstransformer.cfg
│   │       │   └── mods.toml
│   │       ├── icon.png
│   │       ├── modid.mixins.json
│   │       └── pack.mcmeta
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── supermartijn642/
│       │           └── core/
│       │               └── test/
│       │                   ├── TestMod.java
│       │                   ├── TestModClient.java
│       │                   └── TestScreen.java
│       └── resources/
│           ├── META-INF/
│           │   └── mods.toml
│           └── pack.mcmeta
├── .gitattributes
├── .gitignore
├── build.gradle
├── changelog.md
├── gradle.properties
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle
```

## File contents

### .gitattributes

```text
# Disable autocrlf on generated files, they always generate with LF
# Add any extra files or paths here to make git stop saying they
# are changed when only line endings change.
src/generated/**/.cache/cache text eol=lf
src/generated/**/*.json text eol=lf
```

### .github/ISSUE_TEMPLATE/bug_report.yml

```yaml
name: Bug report
title: "[Bug] Your title here"
description: Report of a bug
labels: bug
body:
  - type: input
    id: input-0
    attributes:
      label: Minecraft version
      description: What Minecraft version are you using?
    validations:
      required: true
  - type: dropdown
    id: dropdown-1
    attributes:
      label: Mod Loader
      description: Which mod loader are you using?
      options:
        - Fabric
        - Forge
        - NeoForge
        - Quilt
    validations:
      required: true
  - type: input
    id: input-2
    attributes:
      label: "SuperMartijn642's Core Lib version"
      description: "What version of SuperMartijn642's Core Lib are you using?"
    validations:
      required: true
  - type: textarea
    id: textarea-3
    attributes:
      label: Bug description
      description: Please describe your issue. It is often clearest if you describe
        the current behavior and the expected behavior is.
    validations:
      required: true
  - type: textarea
    id: textarea-4
    attributes:
      label: Steps to reproduce
      description: Please describe the steps needed to recreate the issue. If you
        cannot recreate the issue, please describe what you were doing when the
        issue occurred.
    validations:
      required: true
  - type: textarea
    id: textarea-5
    attributes:
      label: Screenshots
      description: Please share some screenshots of the issue. If screenshots are not
        relevant to your issue, you can omit this section.
```

### .github/ISSUE_TEMPLATE/crash_report.yml

```yaml
name: Crash report
title: "[Crash] Your title here"
description: A crash occurred
labels: crash
body:
  - type: input
    id: input-0
    attributes:
      label: Minecraft version
      description: What Minecraft version are you using?
    validations:
      required: true
  - type: dropdown
    id: dropdown-1
    attributes:
      label: Mod Loader
      description: Which mod loader are you using?
      options:
        - Fabric
        - Forge
        - NeoForge
        - Quilt
    validations:
      required: true
  - type: input
    id: input-2
    attributes:
      label: "SuperMartijn642's Core Lib version"
      description: "What version of SuperMartijn642's Core Lib are you using?"
    validations:
      required: true
  - type: dropdown
    id: dropdown-5
    attributes:
      label: Game environment
      description: Please select whether it was a server or a client that crashed.
      options:
        - Client
        - Server
    validations:
      required: true
  - type: textarea
    id: textarea-3
    attributes:
      label: Steps to reproduce
      description: Please describe the steps needed to recreate the crash. If you
        cannot recreate the crash, please describe what you were doing when the
        crash occurred.
    validations:
      required: true
  - type: textarea
    id: textarea-4
    attributes:
      label: Crash report (~/logs/latest.log)
      description: Please share a link to the `latest.log` file from your game. The
        file is located at `logs/latest.log` next to your mods folder. You can
        use https://gist.github.com/ to upload the log and get a link.
    validations:
      required: true
```

### .github/ISSUE_TEMPLATE/feature_request.yml

```yaml
name: Feature request
title: "[Feature] Your title here"
description: Suggest an idea for this project
labels: feature request
body:
  - type: textarea
    id: textarea-0
    attributes:
      label: Relevant Minecraft versions and other mods/projects
      description: If your feature request is related to specific Minecraft versions
        or other mods or projects, please mention them here. Please also shortly
        explain what the Minecraft versions or other projects do if relevant to
        the feature request.
  - type: textarea
    id: textarea-1
    attributes:
      label: Description of the feature
      description: Please describe the feature you would like to be added. It is often
        clearest if you describe the current behavior and the expected behavior
        with the feature.
    validations:
      required: true
  - type: textarea
    id: textarea-2
    attributes:
      label: Additional context
      description: Please share any other context or screenshots about the feature
        request here.
```

### .github/ISSUE_TEMPLATE/question.yml

```yaml
name: Question
title: "[Question] Your title here"
description: Ask a question about this project
projects: []
labels:
  - question
body:
  - type: markdown
    attributes:
      value: "**If you unsure if something is a bug, please use the 'Bug report'
        template instead.**"
  - type: textarea
    id: textarea-0
    attributes:
      label: Relevant Minecraft versions and other mods/projects
      description: If your question is related to specific Minecraft versions or other
        mods or projects, please mention them here. Please also shortly explain
        what the Minecraft versions or other projects do if relevant to the
        question.
  - type: textarea
    id: textarea-1
    attributes:
      label: Question
      description: Please describe your question. If you think It is often clearest if
        you describe the current behavior and the expected behavior with the
        feature.
    validations:
      required: true
  - type: textarea
    id: textarea-2
    attributes:
      label: Additional context
      description: Please share any other context or screenshots for the question here.
```

### .gitignore

```text
# gradle
.gradle/
build/
out/
classes/

# eclipse
bin/
*.launch
.settings
.metadata

# idea
.idea/
*.ipr
*.iws
*.iml

# vscode
.settings/
.vscode/
.classpath
.project

# macos
*.DS_Store

# neoforge
runs
run-data
repo

# other
eclipse/
run/
libs/
lib/
logs/
images/
desktop.ini
```

### build.gradle

```groovy
plugins {
    id "eclipse"
    id "idea"
    id "java-library"
    id "net.minecraftforge.gradle" version "6.0.25"
    id "org.spongepowered.mixin" version "0.7.+"
    id "me.modmuss50.mod-publish-plugin" version "0.5.2"
}

version = mod_version + "-forge-" + minecraft_suffix
group = maven_group
base.archivesName = mod_id

java.toolchain.languageVersion = JavaLanguageVersion.of(java_target)

println("Java: " + System.getProperty("java.version") + " JVM: " + System.getProperty("java.vm.version") + "(" + System.getProperty("java.vendor") + ") Arch: " + System.getProperty("os.arch"))

// Keep parameter names when compiling
compileJava.options.compilerArgs.add '-parameters'

repositories {
    flatDir { dirs "libs" }
    maven { url "https://www.cursemaven.com" }
    exclusiveContent {
        forRepository {
            maven { url = "https://api.modrinth.com/maven" }
        }
        filter { includeGroup "maven.modrinth" }
    }
    exclusiveContent {
        forRepository {
            maven { url = "https://repo.spongepowered.org/maven" }
        }
        filter { includeGroup "org.spongepowered" }
    }
}

dependencies {
    // Forge
    minecraft "net.minecraftforge:forge:${minecraft_version}-${forge_version}"

    // SpongePowered Mixin
    annotationProcessor "org.spongepowered:mixin:0.8.4:processor"

    // Jetbrains annotations
    compileOnly "org.jetbrains:annotations:26.1.0"
}

// Include resources generated by data generators.
sourceSets.main.resources { srcDir "src/generated/resources" }

processResources {
    inputs.property "version", version

    // Replace the mod version
    Map<String, ?> copyProperties = project.properties.clone() as Map<String, ?>
    //noinspection UnnecessaryQualifiedReference
    var matcher = java.util.regex.Pattern.compile("[^.0-9]").matcher(copyProperties.mod_version as String)
    if (matcher.find())
        copyProperties.mod_version = copyProperties.mod_version.substring(0, matcher.start()) + "+" + copyProperties.mod_version.substring(matcher.start())

    filesMatching(["META-INF/mods.toml", "modid.mixins.json", "pack.mcmeta"]) {
        expand copyProperties
    }

    exclude "**/*.pdn"

    rename "^modid.mixins.json\$", "${mod_id}.mixins.json"
    rename "^icon.png\$", "${mod_id}.png"
}

minecraft {
    mappings channel: "official", version: minecraft_version

    accessTransformer = file("src/main/resources/META-INF/accesstransformer.cfg")

    runs {
        configureEach {
            workingDirectory file("run")

            property "forge.logging.markers", "REGISTRIES"

            mods {
                supermartijn642corelib {
                    //noinspection GroovyAssignabilityCheck
                    source sourceSets.main
                }
//                corelibtestmod {
//                    source sourceSets.test
//                }
            }
        }

        client {
        }

        server {
        }

        data {
            args "--mod", mod_id
            args "--all"
            args "--output", file("src/generated/resources/")
            args "--existing", layout.buildDirectory.file("/data_resources").map { it.asFile }.get()
        }
    }
}

mixin {
    // MixinGradle Settings
    add sourceSets.main, "${mod_id}.mixins.refmap.json"
    config "${mod_id}.mixins.json"

    debug.verbose = true
    debug.export = true
}

// Rename the IntelliJ run configs
gradle.taskGraph.whenReady {
    tasks.genIntellijRuns.doLast {
        def configDir = it.getRunConfigurationsFolder().get().getAsFile()
        minecraft.runs.each {
            //noinspection GroovyAssignabilityCheck
            def configFile = new File(configDir, it.getUniqueFileName() + ".xml")
            if(configFile.exists()){
                def xml = new groovy.xml.XmlSlurper().parse(configFile)
                //noinspection GrUnresolvedAccess
                xml.configuration.@name = "Forge " + it.name.capitalize()
                configFile.withWriter { groovy.xml.XmlUtil.serialize(xml, it) }
            }
        }
    }
}

tasks.register('javadocJar', Jar) {
    dependsOn javadoc
    group = "documentation"
    archiveClassifier = "javadoc"
    from javadoc.destinationDir
}

tasks.register('sourcesJar', Jar) {
    dependsOn classes
    group = "documentation"
    archiveClassifier = "sources"
    from sourceSets.main.allSource
}

tasks.register('prepareDataResources', Sync) {
    from file("src/main/resources")
    into layout.buildDirectory.dir("/data_resources")
    inputs.property "version", version

    // Replace the mod version
    Map<String, ?> copyProperties = project.properties.clone() as Map<String, ?>
    //noinspection UnnecessaryQualifiedReference
    var matcher = java.util.regex.Pattern.compile("[^.0-9]").matcher(copyProperties.mod_version as String)
    if (matcher.find())
        copyProperties.mod_version = copyProperties.mod_version.substring(0, matcher.start()) + "+" + copyProperties.mod_version.substring(matcher.start())

    filesMatching(["META-INF/mods.toml", "modid.mixins.json", "pack.mcmeta"]) {
        expand copyProperties
    }

    exclude "**/*.pdn"

    rename "^modid.mixins.json\$", "${mod_id}.mixins.json"
    rename "^icon.png\$", "${mod_id}.png"
}

// Run prepareDataResources before prepareRunData
tasks.configureEach {
    if (it.name == "prepareRunData" || it.name == "prepareRunDataCompile")
        it.dependsOn "prepareDataResources"
}

// Force the jar to be reobfuscated
jar.finalizedBy('reobfJar')

publishMods {
    file = jar.archiveFile
    displayName = "${mod_name} ${mod_version} for Forge ${minecraft_suffix.substring(2)}"
    version = project.version
    //noinspection UnnecessaryQualifiedReference
    type = me.modmuss50.mpp.ReleaseType.of(publishing_release_type.toUpperCase())
    changelog = file("changelog.md").text
    modLoaders.add("forge")
    maxRetries = 2

    curseforge {
        accessToken = System.getenv("CURSEFORGE_TOKEN")
        projectId = curseforge_project_id
        if (!curseforge_required_dependency_ids.isEmpty())
            curseforge_required_dependency_ids.split(" ").each it::requires
        if (!curseforge_optional_dependency_ids.isEmpty())
            curseforge_optional_dependency_ids.split(" ").each it::optional
        publishing_game_versions.split(" ").each minecraftVersions::add
        clientRequired = true
        serverRequired = true
    }

    modrinth {
        accessToken = System.getenv("MODRINTH_TOKEN")
        projectId = modrinth_project_id
        displayName = "${mod_name} ${mod_version}"
        if (!modrinth_required_dependency_ids.isEmpty())
            modrinth_required_dependency_ids.split(" ").each it::requires
        if (!modrinth_optional_dependency_ids.isEmpty())
            modrinth_optional_dependency_ids.split(" ").each it::optional
        publishing_game_versions.split(" ").each minecraftVersions::add
    }
}

// Rename the 'publishMods' task
tasks.publishMods.group = "other"
tasks.register("publishAll") {
    group = "publishing"
    dependsOn(tasks.publishMods)
}
```

### changelog.md

```markdown
### SuperMartijn642's Core Library 1.1.24a
- Fixed `CustomSlot#isActive` being obfuscated by ForgeGradle

### SuperMartijn642's Core Library 1.1.24
- Fixed `CustomSlot` hover check area being 2 pixels too large

### SuperMartijn642's Core Library 1.1.23
- Added `#getWidget` method for `WidgetScreen` and `WidgetContainerScreen`
- Fixed multipart conditions not being flattened correctly in `BlockStateGenerator`

### SuperMartijn642's Core Library 1.1.22
- Fixed `InteractionFeedback#pass` using `SUCCESS` instead of `PASS`
- Fixed `ElementBuilder#shape` having one parameter as an int instead of float
- Fixed `BaseBlockItem#useOn` returning `CONSUME` instead of `FAIL` when a block cannot be placed

### SuperMartijn642's Core Library 1.1.21
- Fixed client not being updated when `BaseBlockEntity#writeClientData` returns empty tag

### SuperMartijn642's Core Library 1.1.20a
- Update `CustomSlot` stack when if the size of the slot's returned stack is modified

### SuperMartijn642's Core Library 1.1.20
- Added `Widget#cursor` to change the cursor when hovering a widget
- Added `ScrollbarWidget` for creating a configurable scrollbar
- Added `ScissorWidet` that restricts rendering and input handling of child widgets to its bounds
- Added `CustomSlot` for creating container slots
- Added `AbstractButtonWidget#isClickable` and `AbstractButtonWidget#setActive`
- Improved `BaseWidget` focus handling
- Fixed `GuiGraphicsHelper#submitCustomRendering` not respecting active scissor
- Fixed child widgets not getting unfocused when focused widget changes
- Fixed output from `BlockStateGenerator`, `ModelGenerator`, and `TagGenerator` not being consistent

### SuperMartijn642's Core Library 1.1.19
- Added additional properties to `BlockProperties` to match vanilla

### SuperMartijn642's Core Library 1.1.18
- Fixed `TextFieldWidget` allowing one more character than the max length

### SuperMartijn642's Core Library 1.1.17a
- Fixed `TagGenerator` throwing an error for none block, item, or fluid tags

### SuperMartijn642's Core Library 1.1.17
- Added support for custom tag entry types
- Added a namespace tag entry type

### SuperMartijn642's Core Library 1.1.16
- Allow `ClientRegistrationHandler#registerAtlasSprite` to accept a different namespace

### SuperMartijn642's Core Library 1.1.15
- Added `ConditionalRecipeSerializer#wrapRecipe` to serialize conditional recipes

### SuperMartijn642's Core Library 1.1.14
- Fixed error when a `BaseBlockEntity` returns null client data

### SuperMartijn642's Core Library 1.1.13
- Improved `LootTableGenerator.LootPoolBuilder` with additional helpers
- `BaseBlockEntity#dataChanged` will now be true initially to avoid issues with Create contraptions

### SuperMartijn642's Core Library 1.1.12
- Added `CommonUtils#getLogger`

### SuperMartijn642's Core Library 1.1.11
- Initialization of `TextureAtlases` will no longer load the `Sheets` class

### SuperMartijn642's Core Library 1.1.10
- `RegistrationHandler` will now register entries in the same order as they are submitted in

### SuperMartijn642's Core Library 1.1.9b
- Fixed data generators only running when environment is set to client

### SuperMartijn642's Core Library 1.1.9a
- Fixed crash with Emendatus Enigmatica

### SuperMartijn642's Core Library 1.1.9
- Fixed crash when `null` is passed into `BlockProperties#lootTableFrom`

### SuperMartijn642's Core Library 1.1.8
- Added `ResourceAggregator` to allow multiple data generators to write to the same file
- All data generators will now generate before anything gets saved
- Entries in json files from data generators will now always generate in the same order
- Fixed `ClientUtils#getPartialTicks` returning the wrong value when the game is paused
- Fixed `ItemProperties#toUnderlying` causing an exception when durability is set
- Fixed loot table handling in `BlockProperties`
- Fixed static `CreativeItemGroup#get` methods always returning the decorations tab

### SuperMartijn642's Core Library 1.1.7
- Fix `RecipeGenerator`'s smelting smelting recipes not working for multiple smelting types
- Fix `ModelGenerator#itemHandheld` using wrong parent model

### SuperMartijn642's Core Library 1.1.6
- Fix `ModelGenerator#cube` methods ignoring parameters

### SuperMartijn642's Core Library 1.1.5
- Fix performance issues with model overwrites in ClientRegistrationHandler

### SuperMartijn642's Core Library 1.1.4a
- Fix `ConditionalRecipeSerializer` ignoring recipe conditions

### SuperMartijn642's Core Library 1.1.4
- Fix crash on startup

### SuperMartijn642's Core Library 1.1.3
- Fix crash on dedicated servers when certain containers get forcibly closed
- Fix `CommonUtils#getServer` always returning `null`

### SuperMartijn642's Core Library 1.1.2
- Use the given block's namespace for blockstate files in `BlockStateGenerator`

### SuperMartijn642's Core Library 1.1.1a
- Fix translations inside of `WidgetScreen` and `WidgetContainerScreen`

### SuperMartijn642's Core Library 1.1.1
- Fix crash when a recipe condition is registered as `RecipeConditionSerializerRegistry` gets initialized

### SuperMartijn642's Core Library 1.1.0b
- Fix crash on dedicated servers in certain scenarios

### SuperMartijn642's Core Library 1.1.0a
- Fix mixin conflict with certain mods

### SuperMartijn642's Core Library 1.1.0
- All gui functionality has been extracted into `Widget`s
- Added `RegistrationHandler`, `ClientRegistrationHandler`, and `GeneratorRegistrationHandler` for registering everything
- Added `CreativeItemGroup` abstraction for dealing with creative tabs
- Added abstractions for opening `BaseContainer`s with `CommonUtils#openContainer`
- Improved caching for `Object` dependent containers and guis
- Added abstractions for registries in `Registries`
- Added `ResourceGenerator` abstraction for data providers
- Added `RenderConfiguration` for setting up OpenGL properties
- Added `ResourceCondition` abstraction for use in recipes and advancements
- Added `RegistryEntryAcceptor` annotation for getting entries from registries
- Added interaction methods in `BaseBlock`, `BaseItem`, and `BaseBlockItem`
- Added methods to `EnergyFormat` to format text
- Added custom item and block entity renderers
- Added `BaseBlockEntityType` and `BaseContainerType`
- Added `TextureAtlases` to obtain locations of all default atlases
- Improved information in packet errors
- Renamed all 'TileEntity' classes to 'BlockEntity'
- Backported mining tags for use with `BaseBlock`
```

### gradle.properties

```properties
# Gradle settings
org.gradle.jvmargs=-Xmx3G
org.gradle.daemon=false

# Minecraft
minecraft_version=1.16.5
minecraft_dependency=[1.16.2,1.17)
minecraft_suffix=mc1.16
java_target=8
java_dependency=>=8
resource_pack_format=6

# Forge
forge_version=36.2.42
forge_dependency=[32,)
javafml_dependency=[32,)

# Mixin
mixin_minimum_version=0.8
mixin_package=mixin
mixin_compatibility_level=JAVA_8

# Mod properties
mod_name=SuperMartijn642's Core Lib
mod_description=SuperMartijn642's Core Lib adds lots of basic implementations for guis that allow for similar code between Minecraft 1.12, 1.14, 1.15, and 1.16!
mod_id=supermartijn642corelib
mod_version=1.1.24a
mod_license=All rights reserved
mod_page=https://www.curseforge.com/minecraft/mc-mods/supermartijn642s-core-lib
mod_sources=https://github.com/SuperMartijn642/SuperMartijn642sCoreLib
mod_issues=https://github.com/SuperMartijn642/SuperMartijn642sCoreLib/issues
mod_package=com.supermartijn642.core
mod_main_class=CoreLib
maven_group=com.supermartijn642

# Publishing
publishing_release_type=stable
publishing_game_versions=1.16 1.16.1 1.16.2 1.16.3 1.16.4 1.16.5
curseforge_project_id=454372
curseforge_required_dependency_ids=
curseforge_optional_dependency_ids=
modrinth_project_id=rOUBggPv
modrinth_required_dependency_ids=
modrinth_optional_dependency_ids=
```

### gradle/wrapper/gradle-wrapper.properties

```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.7-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

### gradlew

```
#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn () {
    echo "$*"
}

die () {
    echo
    echo "$*"
    echo
    exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
nonstop=false
case "`uname`" in
  CYGWIN* )
    cygwin=true
    ;;
  Darwin* )
    darwin=true
    ;;
  MINGW* )
    msys=true
    ;;
  NONSTOP* )
    nonstop=true
    ;;
esac

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Increase the maximum file descriptors if we can.
if [ "$cygwin" = "false" -a "$darwin" = "false" -a "$nonstop" = "false" ] ; then
    MAX_FD_LIMIT=`ulimit -H -n`
    if [ $? -eq 0 ] ; then
        if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ] ; then
            MAX_FD="$MAX_FD_LIMIT"
        fi
        ulimit -n $MAX_FD
        if [ $? -ne 0 ] ; then
            warn "Could not set maximum file descriptor limit: $MAX_FD"
        fi
    else
        warn "Could not query maximum file descriptor limit: $MAX_FD_LIMIT"
    fi
fi

# For Darwin, add options to specify how the application appears in the dock
if $darwin; then
    GRADLE_OPTS="$GRADLE_OPTS \"-Xdock:name=$APP_NAME\" \"-Xdock:icon=$APP_HOME/media/gradle.icns\""
fi

# For Cygwin, switch paths to Windows format before running java
if $cygwin ; then
    APP_HOME=`cygpath --path --mixed "$APP_HOME"`
    CLASSPATH=`cygpath --path --mixed "$CLASSPATH"`
    JAVACMD=`cygpath --unix "$JAVACMD"`

    # We build the pattern for arguments to be converted via cygpath
    ROOTDIRSRAW=`find -L / -maxdepth 1 -mindepth 1 -type d 2>/dev/null`
    SEP=""
    for dir in $ROOTDIRSRAW ; do
        ROOTDIRS="$ROOTDIRS$SEP$dir"
        SEP="|"
    done
    OURCYGPATTERN="(^($ROOTDIRS))"
    # Add a user-defined pattern to the cygpath arguments
    if [ "$GRADLE_CYGPATTERN" != "" ] ; then
        OURCYGPATTERN="$OURCYGPATTERN|($GRADLE_CYGPATTERN)"
    fi
    # Now convert the arguments - kludge to limit ourselves to /bin/sh
    i=0
    for arg in "$@" ; do
        CHECK=`echo "$arg"|egrep -c "$OURCYGPATTERN" -`
        CHECK2=`echo "$arg"|egrep -c "^-"`                                 ### Determine if an option

        if [ $CHECK -ne 0 ] && [ $CHECK2 -eq 0 ] ; then                    ### Added a condition
            eval `echo args$i`=`cygpath --path --ignore --mixed "$arg"`
        else
            eval `echo args$i`="\"$arg\""
        fi
        i=$((i+1))
    done
    case $i in
        (0) set -- ;;
        (1) set -- "$args0" ;;
        (2) set -- "$args0" "$args1" ;;
        (3) set -- "$args0" "$args1" "$args2" ;;
        (4) set -- "$args0" "$args1" "$args2" "$args3" ;;
        (5) set -- "$args0" "$args1" "$args2" "$args3" "$args4" ;;
        (6) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" ;;
        (7) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" ;;
        (8) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" ;;
        (9) set -- "$args0" "$args1" "$args2" "$args3" "$args4" "$args5" "$args6" "$args7" "$args8" ;;
    esac
fi

# Escape application args
save () {
    for i do printf %s\\n "$i" | sed "s/'/'\\\\''/g;1s/^/'/;\$s/\$/' \\\\/" ; done
    echo " "
}
APP_ARGS=$(save "$@")

# Collect all arguments for the java command, following the shell quoting and substitution rules
eval set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS "\"-Dorg.gradle.appname=$APP_BASE_NAME\"" -classpath "\"$CLASSPATH\"" org.gradle.wrapper.GradleWrapperMain "$APP_ARGS"

# by default we should be in the correct project dir, but when run from Finder on Mac, the cwd is wrong
if [ "$(uname)" = "Darwin" ] && [ "$HOME" = "$PWD" ]; then
  cd "$(dirname "$0")"
fi

exec "$JAVACMD" "$@"
```

### gradlew.bat

```batch
@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%GRADLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
```

### README.md

```markdown
![SuperMartijn642's Core Lib](https://imgur.com/lOYCHZa.png)   
**SuperMartijn642's Config Lib** adds lots of basic implementations for guis that allow for similar code between Minecraft 1.12, 1.14, 1.15, and 1.16!

---

### CurseForge
For more info and downloads, check out the project on [CurseForge](https://www.curseforge.com/minecraft/mc-mods/supermartijn642s-core-lib)

---

### FAQ
Can I use your mod in my modpack?  
Yes, feel free to use my mod in your modpack

---

### Discord
For future content, upcoming mods, and discussion, feel free to join the SuperMartijn642 discord server!  
[<img width='400' src='https://discord.com/assets/cb48d2a8d4991281d7a6a95d2f58195e.svg'>](https://discord.gg/QEbGyUYB2e)
```

### settings.gradle

```groovy
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = 'MinecraftForge'
            url = 'https://maven.minecraftforge.net/'
        }
    }
}

plugins {
    id 'org.gradle.toolchains.foojay-resolver-convention' version '0.7.0'
}
```

### src/generated/resources/.cache/cache

```
73f383ab2d6b6283e0e7e2e0726ae73fc8276f49 assets/supermartijn642corelib/lang/en_us.json
8a23702b05296c8af5014d6aa6e9265ded85c7d9 data/minecraft/tags/blocks/mineable/axe.json
8a23702b05296c8af5014d6aa6e9265ded85c7d9 data/minecraft/tags/blocks/mineable/hoe.json
8a23702b05296c8af5014d6aa6e9265ded85c7d9 data/minecraft/tags/blocks/mineable/pickaxe.json
8a23702b05296c8af5014d6aa6e9265ded85c7d9 data/minecraft/tags/blocks/mineable/shovel.json
8a23702b05296c8af5014d6aa6e9265ded85c7d9 data/minecraft/tags/blocks/needs_diamond_tool.json
8a23702b05296c8af5014d6aa6e9265ded85c7d9 data/minecraft/tags/blocks/needs_iron_tool.json
8a23702b05296c8af5014d6aa6e9265ded85c7d9 data/minecraft/tags/blocks/needs_stone_tool.json
```

### src/generated/resources/assets/supermartijn642corelib/lang/en_us.json

```json
{
  "supermartijn642corelib.widgets.scrollbar.narration": "scroll bar"
}
```

### src/generated/resources/data/minecraft/tags/blocks/mineable/axe.json

```json
{
  "replace": false,
  "values": []
}
```

### src/generated/resources/data/minecraft/tags/blocks/mineable/hoe.json

```json
{
  "replace": false,
  "values": []
}
```

### src/generated/resources/data/minecraft/tags/blocks/mineable/pickaxe.json

```json
{
  "replace": false,
  "values": []
}
```

### src/generated/resources/data/minecraft/tags/blocks/mineable/shovel.json

```json
{
  "replace": false,
  "values": []
}
```

### src/generated/resources/data/minecraft/tags/blocks/needs_diamond_tool.json

```json
{
  "replace": false,
  "values": []
}
```

### src/generated/resources/data/minecraft/tags/blocks/needs_iron_tool.json

```json
{
  "replace": false,
  "values": []
}
```

### src/generated/resources/data/minecraft/tags/blocks/needs_stone_tool.json

```json
{
  "replace": false,
  "values": []
}
```

### src/main/java/com/supermartijn642/core/block/BaseBlock.java

```java
package com.supermartijn642.core.block;

import com.supermartijn642.core.registry.Registries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created 1/26/2021 by SuperMartijn642
 */
public class BaseBlock extends Block {

    private static final ITag.INamedTag<Block> MINEABLE_WITH_AXE = BlockTags.bind("mineable/axe");
    private static final ITag.INamedTag<Block> MINEABLE_WITH_HOE = BlockTags.bind("mineable/hoe");
    private static final ITag.INamedTag<Block> MINEABLE_WITH_PICKAXE = BlockTags.bind("mineable/pickaxe");
    private static final ITag.INamedTag<Block> MINEABLE_WITH_SHOVEL = BlockTags.bind("mineable/shovel");
    private static final ITag.INamedTag<Block> NEEDS_DIAMOND_TOOL = BlockTags.bind("needs_diamond_tool");
    private static final ITag.INamedTag<Block> NEEDS_IRON_TOOL = BlockTags.bind("needs_iron_tool");
    private static final ITag.INamedTag<Block> NEEDS_STONE_TOOL = BlockTags.bind("needs_stone_tool");

    private final boolean saveTileData;

    public BaseBlock(boolean saveTileData, Properties properties){
        super(properties);
        this.saveTileData = saveTileData;
    }

    public BaseBlock(boolean saveTileData, BlockProperties properties){
        this(saveTileData, properties.toUnderlying());
    }

    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack){
        if(!this.saveTileData)
            return;

        CompoundNBT tag = stack.getTag();
        tag = tag == null ? null : tag.contains("tileData") ? tag.getCompound("tileData") : null;
        if(tag == null || tag.isEmpty())
            return;

        TileEntity entity = worldIn.getBlockEntity(pos);
        if(entity instanceof BaseBlockEntity)
            ((BaseBlockEntity)entity).readData(tag);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder){
        List<ItemStack> items = super.getDrops(state, builder);

        if(!this.saveTileData)
            return items;

        TileEntity entity = builder.getOptionalParameter(LootParameters.BLOCK_ENTITY);
        if(!(entity instanceof BaseBlockEntity))
            return items;

        CompoundNBT entityTag = ((BaseBlockEntity)entity).writeItemStackData();
        if(entityTag == null || entityTag.isEmpty())
            return items;

        CompoundNBT tag = new CompoundNBT();
        tag.put("tileData", entityTag);

        for(ItemStack stack : items){
            if(stack.getItem() instanceof BlockItem && ((BlockItem)stack.getItem()).getBlock() == this){
                stack.setTag(tag);
            }
        }

        return items;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player){
        ItemStack stack = super.getPickBlock(state, target, world, pos, player);

        if(!this.saveTileData)
            return stack;

        TileEntity entity = world.getBlockEntity(pos);
        if(!(entity instanceof BaseBlockEntity))
            return stack;

        CompoundNBT entityTag = ((BaseBlockEntity)entity).writeItemStackData();
        if(entityTag == null || entityTag.isEmpty())
            return stack;

        CompoundNBT tag = new CompoundNBT();
        tag.put("tileData", entityTag);

        if(stack.getItem() instanceof BlockItem && ((BlockItem)stack.getItem()).getBlock() == this)
            stack.setTag(tag);

        return stack;
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hitResult){
        return this.interact(state, level, pos, player, hand, hitResult.getDirection(), hitResult.getLocation()).interactionResult;
    }

    /**
     * Called when a player interacts with this block.
     * @return whether the player's interaction should be consumed or passed on
     */
    protected InteractionFeedback interact(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, Direction hitSide, Vector3d hitLocation){
        return InteractionFeedback.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader level, List<ITextComponent> information, ITooltipFlag flag){
        this.appendItemInformation(stack, level, information::add, flag.isAdvanced());
        super.appendHoverText(stack, level, information, flag);
    }

    /**
     * Adds information to be displayed when hovering over the item corresponding to this block in the inventory.
     * @param stack    the stack being hovered over
     * @param level    the world the player is in, may be {@code null}
     * @param info     consumes the information which should be added
     * @param advanced whether advanced tooltips is enabled
     */
    protected void appendItemInformation(ItemStack stack, @Nullable IBlockReader level, Consumer<ITextComponent> info, boolean advanced){
    }

    /**
     * Gets the item corresponding to this block.
     */
    @Override
    public Item asItem(){
        return super.asItem();
    }

    @Override
    public String getDescriptionId(){
        ResourceLocation identifier = Registries.BLOCKS.getIdentifier(this);
        return identifier.getNamespace() + ".block." + identifier.getPath();
    }

    @Override
    public boolean isToolEffective(BlockState state, ToolType tool){
        return (tool == ToolType.AXE && this.is(MINEABLE_WITH_AXE))
            || (tool == ToolType.HOE && this.is(MINEABLE_WITH_HOE))
            || (tool == ToolType.PICKAXE && this.is(MINEABLE_WITH_PICKAXE))
            || (tool == ToolType.SHOVEL && this.is(MINEABLE_WITH_SHOVEL));
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state){
        return this.is(MINEABLE_WITH_AXE) ? ToolType.AXE
            : this.is(MINEABLE_WITH_HOE) ? ToolType.HOE
            : this.is(MINEABLE_WITH_PICKAXE) ? ToolType.PICKAXE
            : this.is(MINEABLE_WITH_SHOVEL) ? ToolType.SHOVEL
            : null;
    }

    @Override
    public int getHarvestLevel(BlockState state){
        return this.is(NEEDS_DIAMOND_TOOL) ? 3
            : this.is(NEEDS_IRON_TOOL) ? 2
            : this.is(NEEDS_STONE_TOOL) ? 1
            : -1;
    }

    protected enum InteractionFeedback {
        PASS(ActionResultType.PASS), CONSUME(ActionResultType.CONSUME), SUCCESS(ActionResultType.SUCCESS);

        private final ActionResultType interactionResult;

        InteractionFeedback(ActionResultType interactionResult){
            this.interactionResult = interactionResult;
        }
    }
}
```

### src/main/java/com/supermartijn642/core/block/BaseBlockEntity.java

```java
package com.supermartijn642.core.block;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.Constants;

/**
 * Created 1/26/2021 by SuperMartijn642
 */
public abstract class BaseBlockEntity extends TileEntity {

    /**
     * Create's contraptions call {@link #getUpdatePacket()} when placing back blocks, so this should be {@code true} initially
     */
    private boolean dataChanged = true;

    public BaseBlockEntity(TileEntityType<?> tileEntityTypeIn){
        super(tileEntityTypeIn);
    }

    /**
     * Marks the tile entity as dirty and send an update packet to clients.
     */
    public void dataChanged(){
        this.dataChanged = true;
        this.setChanged();
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2 | 4);
    }

    /**
     * Writes tile entity data to be saved with the chunk.
     * The stored data will be read in {@link #readData(CompoundNBT)}.
     * @return a {@link CompoundNBT} with the stored data
     */
    protected abstract CompoundNBT writeData();

    /**
     * Writes tile entity data to be sent to the client.
     * The stored data will be read in {@link #readData(CompoundNBT)}.
     * @return a {@link CompoundNBT} with the stored client data
     */
    protected CompoundNBT writeClientData(){
        return this.writeData();
    }

    /**
     * Writes tile entity data to be stored on item stacks.
     * The stored data will be read in {@link #readData(CompoundNBT)}.
     * @return a {@link CompoundNBT} with the stored item stack data
     */
    protected CompoundNBT writeItemStackData(){
        return this.writeData();
    }

    /**
     * Reads data stored by {@link #writeData()}, {@link #writeClientData()},
     * and {@link #writeItemStackData()}.
     * @param tag data to be read
     */
    protected abstract void readData(CompoundNBT tag);

    @Override
    public CompoundNBT save(CompoundNBT compound){
        super.save(compound);
        CompoundNBT data = this.writeData();
        if(data != null && !data.isEmpty())
            compound.put("data", data);
        return compound;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt){
        super.load(state, nbt);
        this.readData(nbt.contains("data", Constants.NBT.TAG_COMPOUND) ? nbt.getCompound("data") : new CompoundNBT());
    }

    @Override
    public CompoundNBT getUpdateTag(){
        CompoundNBT tag = super.save(new CompoundNBT());
        CompoundNBT data = this.writeClientData();
        if(data != null)
            tag.put("data", data);
        return tag;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket(){
        if(this.dataChanged){
            this.dataChanged = false;
            CompoundNBT tag = new CompoundNBT();
            CompoundNBT data = this.writeClientData();
            if(data != null)
                tag.put("data", data);
            return new SUpdateTileEntityPacket(this.worldPosition, 0, tag);
        }
        return null;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        CompoundNBT tag = pkt.getTag();
        if(tag != null && tag.contains("data", Constants.NBT.TAG_COMPOUND))
            this.readData(tag.getCompound("data"));
    }
}
```

### src/main/java/com/supermartijn642/core/block/BaseBlockEntityType.java

```java
package com.supermartijn642.core.block;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.Set;
import java.util.function.Supplier;

/**
 * Created 06/08/2022 by SuperMartijn642
 */
public final class BaseBlockEntityType<T extends TileEntity> extends TileEntityType<T> {

    /**
     * Creates a new block entity type.
     * @param entitySupplier used to create new block entities when a world is loaded
     * @param validBlocks    blocks which may hold the block entity
     */
    public static <T extends BaseBlockEntity> BaseBlockEntityType<T> create(Supplier<T> entitySupplier, Block... validBlocks){
        return new BaseBlockEntityType<>(entitySupplier, ImmutableSet.copyOf(validBlocks));
    }

    private BaseBlockEntityType(Supplier<T> entitySupplier, Set<Block> validBlocks){
        super(entitySupplier, validBlocks, null);
    }
}
```

### src/main/java/com/supermartijn642/core/block/BlockProperties.java

```java
package com.supermartijn642.core.block;

import com.supermartijn642.core.mixin.BlockPropertiesAccessor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.util.TriPredicate;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/**
 * Created 24/07/2022 by SuperMartijn642
 */
public class BlockProperties {

    public static BlockProperties create(Material material, MaterialColor color){
        return new BlockProperties(material).mapColor(color);
    }

    public static BlockProperties create(Material material, DyeColor color){
        return new BlockProperties(material).mapColor(color.getMaterialColor());
    }

    public static BlockProperties create(Material material){
        return new BlockProperties(material);
    }

    public static BlockProperties fromVanilla(AbstractBlock.Properties vanilla){
        BlockProperties properties = create(vanilla.material);
        properties.mapColor = vanilla.materialColor;
        properties.hasCollision = vanilla.hasCollision;
        properties.soundType = vanilla.soundType;
        properties.lightLevel = vanilla.lightEmission;
        properties.explosionResistance = vanilla.explosionResistance;
        properties.destroyTime = vanilla.destroyTime;
        properties.requiresCorrectTool = vanilla.requiresCorrectToolForDrops;
        properties.ticksRandomly = vanilla.isRandomlyTicking;
        properties.friction = vanilla.friction;
        properties.speedFactor = vanilla.speedFactor;
        properties.jumpFactor = vanilla.jumpFactor;
        properties.canOcclude = vanilla.canOcclude;
        properties.isAir = vanilla.isAir;
        properties.isRedstoneConductor = vanilla.isRedstoneConductor::test;
        properties.isSuffocating = vanilla.isSuffocating::test;
        properties.hasDynamicShape = vanilla.dynamicShape;
        properties.lootTableSupplier = vanilla.drops != null ? () -> vanilla.drops : ((BlockPropertiesAccessor)vanilla).getLootTableSupplier();
        return properties;
    }

    public static BlockProperties copy(Block block){
        return fromVanilla(block.properties);
    }

    private final Material material;
    private Function<BlockState,MaterialColor> mapColor;
    private boolean hasCollision = true;
    private SoundType soundType = SoundType.STONE;
    private ToIntFunction<BlockState> lightLevel = state -> 0;
    private float explosionResistance;
    private float destroyTime;
    private boolean requiresCorrectTool = false;
    private boolean ticksRandomly = false;
    private float friction = 0.6f;
    private float speedFactor = 1.0f;
    private float jumpFactor = 1.0f;
    private boolean canOcclude = true;
    private boolean isAir = false;
    private TriPredicate<BlockState,IBlockReader,BlockPos> isRedstoneConductor = (state, level, pos) -> state.getMaterial().isSolidBlocking() && state.isCollisionShapeFullBlock(level, pos);
    private TriPredicate<BlockState,IBlockReader,BlockPos> isSuffocating = (state, level, pos) -> state.getMaterial().blocksMotion() && state.isCollisionShapeFullBlock(level, pos);
    private boolean hasDynamicShape = false;
    private boolean noLootTable = false;
    private Supplier<ResourceLocation> lootTableSupplier;

    private BlockProperties(Material material){
        this.material = material;
        this.mapColor = state -> material.getColor();
    }

    public BlockProperties mapColor(Function<BlockState,MaterialColor> colorFunction){
        if(colorFunction == null){
            Material material = this.material;
            colorFunction = state -> material.getColor();
        }
        this.mapColor = colorFunction;
        return this;
    }

    public BlockProperties mapColor(MaterialColor color){
        return this.mapColor(color == null ? null : state -> color);
    }

    public BlockProperties collision(boolean hasCollision){
        this.hasCollision = hasCollision;
        if(!hasCollision)
            this.canOcclude = false;
        return this;
    }

    public BlockProperties noCollision(){
        return this.collision(false);
    }

    public BlockProperties sound(SoundType soundTypeIn){
        this.soundType = soundTypeIn;
        return this;
    }

    public BlockProperties lightLevel(ToIntFunction<BlockState> stateLightFunction){
        this.lightLevel = stateLightFunction;
        return this;
    }

    public BlockProperties lightLevel(int light){
        this.lightLevel = state -> light;
        return this;
    }

    public BlockProperties explosionResistance(float resistance){
        this.explosionResistance = Math.max(0, resistance);
        return this;
    }

    public BlockProperties destroyTime(float destroyTime){
        this.destroyTime = destroyTime;
        return this;
    }

    /**
     * Sets both explosion resistance and destroy time.
     */
    public BlockProperties strength(float strength){
        return this.explosionResistance(strength).destroyTime(strength);
    }

    public BlockProperties requiresCorrectTool(boolean requiresCorrectTool){
        this.requiresCorrectTool = requiresCorrectTool;
        return this;
    }

    public BlockProperties requiresCorrectTool(){
        return this.requiresCorrectTool(true);
    }

    public BlockProperties randomTicks(boolean receiveRandomTicks){
        this.ticksRandomly = receiveRandomTicks;
        return this;
    }

    public BlockProperties randomTicks(){
        return this.randomTicks(true);
    }

    public BlockProperties friction(float friction){
        this.friction = friction;
        return this;
    }

    public BlockProperties speedFactor(float factor){
        this.speedFactor = factor;
        return this;
    }

    public BlockProperties jumpFactor(float factor){
        this.jumpFactor = factor;
        return this;
    }

    public BlockProperties canOcclude(boolean canOcclude){
        this.canOcclude = canOcclude;
        return this;
    }

    public BlockProperties noOcclusion(){
        return this.canOcclude(false);
    }

    public BlockProperties air(boolean isAir){
        this.isAir = isAir;
        return this;
    }

    public BlockProperties air(){
        return this.air(true);
    }

    public BlockProperties isRedstoneConductor(TriPredicate<BlockState,IBlockReader,BlockPos> isRedstoneConductor){
        this.isRedstoneConductor = isRedstoneConductor;
        return this;
    }

    public BlockProperties isRedstoneConductor(boolean isRedstoneConductor){
        this.isRedstoneConductor = (state, blockGetter, pos) -> isRedstoneConductor;
        return this;
    }

    public BlockProperties isSuffocating(TriPredicate<BlockState,IBlockReader,BlockPos> isSuffocating){
        this.isSuffocating = isSuffocating;
        return this;
    }

    public BlockProperties isSuffocating(boolean isSuffocating){
        this.isSuffocating = (state, blockGetter, pos) -> isSuffocating;
        return this;
    }

    public BlockProperties dynamicShape(){
        this.hasDynamicShape = true;
        return this;
    }

    public BlockProperties noLootTable(){
        this.noLootTable = true;
        this.lootTableSupplier = null;
        return this;
    }

    public BlockProperties lootTable(ResourceLocation lootTable){
        this.noLootTable = false;
        this.lootTableSupplier = () -> lootTable;
        return this;
    }

    public BlockProperties lootTableFrom(Supplier<Block> block){
        this.noLootTable = false;
        this.lootTableSupplier = block == null ? null : () -> block.get().getLootTable();
        return this;
    }

    /**
     * Converts the properties into {@link AbstractBlock.Properties}.
     */
    @Deprecated
    public AbstractBlock.Properties toUnderlying(){
        AbstractBlock.Properties properties = AbstractBlock.Properties.of(this.material, this.mapColor);
        if(!this.hasCollision)
            properties.noCollission();
        properties.sound(this.soundType);
        properties.lightLevel(this.lightLevel);
        properties.strength(this.explosionResistance);
        properties.destroyTime = this.destroyTime;
        if(this.requiresCorrectTool)
            properties.requiresCorrectToolForDrops();
        if(this.ticksRandomly)
            properties.randomTicks();
        properties.friction(this.friction);
        properties.speedFactor(this.speedFactor);
        properties.jumpFactor(this.jumpFactor);
        if(this.noLootTable)
            properties.noDrops();
        ((BlockPropertiesAccessor)properties).setLootTableSupplier(this.lootTableSupplier);
        if(!this.canOcclude)
            properties.noOcclusion();
        if(this.isAir)
            properties.air();
        properties.isRedstoneConductor(this.isRedstoneConductor::test);
        properties.isSuffocating(this.isSuffocating::test);
        properties.isViewBlocking(this.isSuffocating::test);
        if(this.hasDynamicShape)
            properties.dynamicShape();
        return properties;
    }
}
```

### src/main/java/com/supermartijn642/core/block/BlockShape.java

```java
package com.supermartijn642.core.block;

import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created 6/11/2021 by SuperMartijn642
 */
public class BlockShape {

    public static BlockShape create(AxisAlignedBB box){
        return new BlockShape(box);
    }

    public static BlockShape create(VoxelShape box){
        return new BlockShape(box);
    }

    public static BlockShape create(double x1, double y1, double z1, double x2, double y2, double z2){
        return create(VoxelShapes.box(x1, y1, z1, x2, y2, z2));
    }

    /**
     * Creates a shape with coordinates {@code x1 / 16, y1 / 16, z1 / 16, x2 / 16, y2 / 16, z2 / 16}.
     */
    public static BlockShape createBlockShape(double x1, double y1, double z1, double x2, double y2, double z2){
        return create(VoxelShapes.box(x1 / 16, y1 / 16, z1 / 16, x2 / 16, y2 / 16, z2 / 16));
    }

    /**
     * Combines two shapes.
     */
    public static BlockShape or(BlockShape shape, BlockShape... shapes){
        return new BlockShape(VoxelShapes.or(shape.shape, Arrays.stream(shapes).map(s -> s.shape).toArray(VoxelShape[]::new)));
    }

    public static BlockShape fullCube(){
        return new BlockShape(VoxelShapes.block());
    }

    public static BlockShape empty(){
        return new BlockShape(VoxelShapes.empty());
    }

    /**
     * Checks whether the given shapes intersect.
     */
    public static boolean intersects(BlockShape shape1, BlockShape shape2){
        return shape1.intersects(shape2);
    }

    private final VoxelShape shape;

    public BlockShape(VoxelShape shape){
        this.shape = shape;
    }

    public BlockShape(AxisAlignedBB shape){
        this(VoxelShapes.create(shape));
    }

    public BlockShape(List<AxisAlignedBB> shapes){
        this(VoxelShapes.or(VoxelShapes.empty(), shapes.stream().map(VoxelShapes::create).toArray(VoxelShape[]::new)));
    }

    public List<AxisAlignedBB> toBoxes(){
        return this.shape.toAabbs();
    }

    public void forEachBox(Consumer<AxisAlignedBB> action){
        this.toBoxes().forEach(action);
    }

    public void forEachEdge(LineConsumer action){
        this.shape.forAllEdges(action::apply);
    }

    public void forEachCorner(PointConsumer action){
        this.shape.forAllBoxes((x1, y1, z1, x2, y2, z2) -> {
            action.apply(x1, y1, z1);
            action.apply(x2, y1, z1);
            action.apply(x1, y1, z2);
            action.apply(x2, y1, z2);
            action.apply(x1, y2, z1);
            action.apply(x2, y2, z1);
            action.apply(x1, y2, z2);
            action.apply(x2, y2, z2);
        });
    }

    /**
     * Creates the smallest box that encapsulate the entire shape.
     */
    public AxisAlignedBB simplify(){
        return this.shape.bounds();
    }

    /**
     * @return the minimum coordinate for the given axis.
     */
    public double getStart(Direction.Axis axis){
        return this.shape.min(axis);
    }

    /**
     * @return the maximum coordinate for the given axis.
     */
    public double getEnd(Direction.Axis axis){
        return this.shape.max(axis);
    }

    public double minX(){
        return this.getStart(Direction.Axis.X);
    }

    public double minY(){
        return this.getStart(Direction.Axis.Y);
    }

    public double minZ(){
        return this.getStart(Direction.Axis.Z);
    }

    public double maxX(){
        return this.getEnd(Direction.Axis.X);
    }

    public double maxY(){
        return this.getEnd(Direction.Axis.Y);
    }

    public double maxZ(){
        return this.getEnd(Direction.Axis.Z);
    }

    /**
     * @return whether the shape has a volume greater than 0.
     */
    public boolean isEmpty(){
        return this.shape.isEmpty();
    }

    public BlockShape offset(double x, double y, double z){
        return new BlockShape(this.shape.move(x, y, z));
    }

    public BlockShape offset(BlockPos pos){
        return new BlockShape(this.shape.move(pos.getX(), pos.getY(), pos.getZ()));
    }

    /**
     * Offsets the shape by 1 in the given direction.
     */
    public BlockShape offset(Direction direction){
        return this.offset(direction.getStepX(), direction.getStepY(), direction.getStepZ());
    }

    /**
     * Checks whether the shape intersects with the given shape.
     */
    public boolean intersects(BlockShape shape){
        if(this.isEmpty() || shape.isEmpty())
            return false;

        return this.maxX() > shape.minX() && this.minX() < shape.maxX() &&
            this.maxY() > shape.minY() && this.minY() < shape.maxY() &&
            this.maxZ() > shape.minZ() && this.minZ() < shape.maxZ();
    }

    public BlockShape grow(double amount){
        return this.transformBoxes(box -> box.inflate(amount));
    }

    public BlockShape shrink(double amount){
        return this.transformBoxes(box -> box.deflate(amount));
    }

    /**
     * Flips the shape on the given axis.
     */
    public BlockShape flip(Direction.Axis axis){
        return this.transformBoxes(box -> new AxisAlignedBB(
            axis == Direction.Axis.X ? 1 - box.minX : box.minX,
            axis == Direction.Axis.Y ? 1 - box.minY : box.minY,
            axis == Direction.Axis.Z ? 1 - box.minZ : box.minZ,
            axis == Direction.Axis.X ? 1 - box.maxX : box.maxX,
            axis == Direction.Axis.Y ? 1 - box.maxY : box.maxY,
            axis == Direction.Axis.Z ? 1 - box.maxZ : box.maxZ
        ));
    }

    /**
     * Rotates the shape by 90° around the given axis.
     */
    public BlockShape rotate(Direction.Axis axis){
        if(axis == null)
            throw new IllegalArgumentException("axis must not be null!");
        if(axis == Direction.Axis.X)
            return this.transformBoxes(box -> new AxisAlignedBB(box.minX, box.minZ, -box.minY + 1, box.maxX, box.maxZ, -box.maxY + 1));
        if(axis == Direction.Axis.Y)
            return this.transformBoxes(box -> new AxisAlignedBB(-box.minZ + 1, box.minY, box.minX, -box.maxZ + 1, box.maxY, box.maxX));
        if(axis == Direction.Axis.Z)
            return this.transformBoxes(box -> new AxisAlignedBB(box.minY, -box.minX + 1, box.minZ, box.maxY, -box.maxX + 1, box.maxZ));
        return null;
    }

    private BlockShape transformBoxes(Function<AxisAlignedBB,AxisAlignedBB> transformer){
        return new BlockShape(this.toBoxes().stream().map(transformer::apply).collect(Collectors.toList()));
    }

    @Deprecated
    public VoxelShape getUnderlying(){
        return this.shape;
    }

    public interface LineConsumer {

        void apply(double x1, double y1, double z1, double x2, double y2, double z2);
    }

    public interface PointConsumer {

        void apply(double x, double y, double z);
    }
}
```

### src/main/java/com/supermartijn642/core/block/EntityHoldingBlock.java

```java
package com.supermartijn642.core.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

/**
 * Created 17/07/2022 by SuperMartijn642
 */
public interface EntityHoldingBlock extends ITileEntityProvider {

    TileEntity createNewBlockEntity();

    @Nullable
    @Override
    default TileEntity newBlockEntity(IBlockReader level){
        return this.createNewBlockEntity();
    }
}
```

### src/main/java/com/supermartijn642/core/block/TickableBlockEntity.java

```java
package com.supermartijn642.core.block;

import net.minecraft.tileentity.ITickableTileEntity;

/**
 * Created 17/07/2022 by SuperMartijn642
 */
public interface TickableBlockEntity extends ITickableTileEntity {

    /**
     * Called once per tick.
     */
    void update();

    @Override
    default void tick(){
        this.update();
    }
}
```

### src/main/java/com/supermartijn642/core/ClientUtils.java

```java
package com.supermartijn642.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.animation.Animation;

/**
 * Created 1/26/2021 by SuperMartijn642
 */
public class ClientUtils {

    public static Minecraft getMinecraft(){
        return Minecraft.getInstance();
    }

    public static TextureManager getTextureManager(){
        return getMinecraft().getTextureManager();
    }

    public static FontRenderer getFontRenderer(){
        return getMinecraft().font;
    }

    public static PlayerEntity getPlayer(){
        return getMinecraft().player;
    }

    public static World getWorld(){
        return getMinecraft().level;
    }

    public static BlockRendererDispatcher getBlockRenderer(){
        return getMinecraft().getBlockRenderer();
    }

    public static ItemRenderer getItemRenderer(){
        return getMinecraft().getItemRenderer();
    }

    public static float getPartialTicks(){
        return Animation.getPartialTickTime();
    }

    /**
     * Closes the player's opened screen
     */
    public static void closeScreen(){
        getPlayer().closeContainer();
    }

    /**
     * Queues the given task on the main thread
     * @param task task to be queued
     */
    public static void queueTask(Runnable task){
        getMinecraft().tell(task);
    }

    public static String translate(String translationKey, Object... args){
        return I18n.get(translationKey, args);
    }

    public static void displayScreen(Screen screen){
        getMinecraft().setScreen(screen);
    }

}
```

### src/main/java/com/supermartijn642/core/CommonUtils.java

```java
package com.supermartijn642.core;

import com.supermartijn642.core.gui.BaseContainer;
import com.supermartijn642.core.gui.BaseContainerType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Created 20/03/2022 by SuperMartijn642
 */
public class CommonUtils {

    private static MinecraftServer server;

    static void initialize(){
        MinecraftForge.EVENT_BUS.addListener((Consumer<FMLServerAboutToStartEvent>)(e -> server = e.getServer()));
        MinecraftForge.EVENT_BUS.addListener((Consumer<FMLServerStoppedEvent>)(e -> server = null));
    }

    /**
     * @return the integrated server on the client or the server instance a dedicated server
     */
    public static MinecraftServer getServer(){
        return server;
    }

    public static World getLevel(RegistryKey<World> resourceKey){
        MinecraftServer server = getServer();
        return server == null ? null : server.getLevel(resourceKey);
    }

    /**
     * @return which environment the game is running in.
     */
    public static CoreSide getEnvironmentSide(){
        return CoreSide.fromUnderlying(FMLEnvironment.dist);
    }

    /**
     * Checks whether a mod with the given modid is loaded and active.
     */
    public static boolean isModLoaded(String modid){
        return ModList.get().isLoaded(modid);
    }

    /**
     * Opens the given container. This method will do nothing if called client-side.
     * @param container the container to be opened
     */
    public static void openContainer(BaseContainer container){
        PlayerEntity player = container.player;
        if(!(container.player instanceof ServerPlayerEntity))
            return;

        // Open the container
        //noinspection unchecked,rawtypes
        NetworkHooks.openGui((ServerPlayerEntity)player, new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName(){
                return TextComponents.empty().get();
            }

            @Nullable
            @Override
            public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity player){
                container.setContainerId(windowId);
                return container;
            }
        }, data -> ((BaseContainerType)container.getContainerType()).writeContainer(container, data));
    }

    /**
     * Closes the currently open container for the given player. If the player is not in a container, this method won't do anything.
     */
    public static void closeContainer(PlayerEntity player){
        player.closeContainer();
    }

    public static Logger getLogger(String modid){
        return LogManager.getLogger(modid);
    }
}
```

### src/main/java/com/supermartijn642/core/CoreLib.java

```java
package com.supermartijn642.core;

import com.supermartijn642.core.data.condition.*;
import com.supermartijn642.core.data.recipe.ConditionalRecipeSerializer;
import com.supermartijn642.core.data.tag.entries.NamespaceTagEntry;
import com.supermartijn642.core.generator.standard.CoreLibLanguageGenerator;
import com.supermartijn642.core.generator.standard.CoreLibMiningTagGenerator;
import com.supermartijn642.core.registry.GeneratorRegistrationHandler;
import com.supermartijn642.core.registry.RegistrationHandler;
import com.supermartijn642.core.registry.RegistryEntryAcceptor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Logger;

/**
 * Created 7/7/2020 by SuperMartijn642
 */
@Mod("supermartijn642corelib")
public class CoreLib {

    public static final Logger LOGGER = CommonUtils.getLogger("supermartijn642corelib");

    public CoreLib(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConstructMod);
        CommonUtils.initialize();

        // Register conditional recipe type
        RegistrationHandler handler = RegistrationHandler.get("supermartijn642corelib");
        handler.registerRecipeSerializer("conditional", ConditionalRecipeSerializer.INSTANCE);
        handler.registerResourceConditionSerializer("mod_loaded", ModLoadedResourceCondition.SERIALIZER);
        handler.registerResourceConditionSerializer("not", NotResourceCondition.SERIALIZER);
        handler.registerResourceConditionSerializer("or", OrResourceCondition.SERIALIZER);
        handler.registerResourceConditionSerializer("and", AndResourceCondition.SERIALIZER);
        handler.registerResourceConditionSerializer("tag_populated", TagPopulatedResourceCondition.SERIALIZER);

        // Register custom tag entry types
        handler.registerCustomTagEntrySerializer("namespace", NamespaceTagEntry.SERIALIZER);

        // Register generator for default tags
        GeneratorRegistrationHandler.get("supermartijn642corelib").addGenerator(cache -> new CoreLibMiningTagGenerator("supermartijn642corelib", cache));
        // Register generator for translations
        GeneratorRegistrationHandler.get("supermartijn642corelib").addGenerator(cache -> new CoreLibLanguageGenerator("supermartijn642corelib", cache));
    }

    private void onConstructMod(FMLConstructModEvent e){
        RegistryEntryAcceptor.Handler.gatherAnnotatedFields();
    }
}
```

### src/main/java/com/supermartijn642/core/CoreSide.java

```java
package com.supermartijn642.core;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.LogicalSide;

/**
 * Created 5/30/2021 by SuperMartijn642
 */
public enum CoreSide {
    CLIENT(Dist.CLIENT), SERVER(Dist.DEDICATED_SERVER);

    private final Dist environment;

    CoreSide(Dist environment){
        this.environment = environment;
    }

    public boolean isClient(){
        return this == CLIENT;
    }

    public boolean isServer(){
        return this == SERVER;
    }

    @Deprecated
    public Dist getUnderlyingSide(){
        return this.environment;
    }

    @Deprecated
    public static CoreSide fromUnderlying(Dist environment){
        return environment == Dist.CLIENT ? CLIENT : SERVER;
    }

    @Deprecated
    public static CoreSide fromUnderlying(LogicalSide environment){
        return environment == LogicalSide.CLIENT ? CLIENT : SERVER;
    }
}
```

### src/main/java/com/supermartijn642/core/data/condition/AndResourceCondition.java

```java
package com.supermartijn642.core.data.condition;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created 29/11/2022 by SuperMartijn642
 */
public class AndResourceCondition implements ResourceCondition {

    public static final Serializer SERIALIZER = new Serializer();

    private final List<ICondition> conditions;

    public AndResourceCondition(ICondition... conditions){
        this.conditions = new ArrayList<>(Arrays.asList(conditions));
    }

    public AndResourceCondition(ResourceCondition... conditions){
        this(Arrays.stream(conditions).map(ResourceCondition::createForgeCondition).toArray(ICondition[]::new));
    }

    @Override
    public boolean test(ResourceConditionContext context){
        for(ICondition condition : this.conditions){
            if(!condition.test())
                return false;
        }
        return true;
    }

    @Override
    public ResourceConditionSerializer<?> getSerializer(){
        return SERIALIZER;
    }

    @Override
    public ResourceCondition and(ResourceCondition condition){
        this.conditions.add(ResourceCondition.createForgeCondition(condition));
        return this;
    }

    private static class Serializer implements ResourceConditionSerializer<AndResourceCondition> {

        @Override
        public void serialize(JsonObject json, AndResourceCondition condition){
            JsonArray conditions = new JsonArray();
            for(ICondition alternative : condition.conditions)
                conditions.add(CraftingHelper.serialize(alternative));
            json.add("conditions", conditions);
        }

        @Override
        public AndResourceCondition deserialize(JsonObject json){
            if(!json.has("conditions") || !json.get("conditions").isJsonArray())
                throw new RuntimeException("Condition must have key 'conditions' with a json array!");

            JsonArray conditionsJson = json.getAsJsonArray("conditions");
            ICondition[] conditions = new ICondition[conditionsJson.size()];
            for(int i = 0; i < conditionsJson.size(); i++)
                conditions[i] = CraftingHelper.getCondition(conditionsJson.get(i).getAsJsonObject());
            return new AndResourceCondition(conditions);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/data/condition/ModLoadedResourceCondition.java

```java
package com.supermartijn642.core.data.condition;

import com.google.gson.JsonObject;
import net.minecraftforge.fml.ModList;

/**
 * Created 26/08/2022 by SuperMartijn642
 */
public class ModLoadedResourceCondition implements ResourceCondition {

    public static final Serializer SERIALIZER = new Serializer();

    private final String modid;

    public ModLoadedResourceCondition(String modid){
        this.modid = modid;
    }

    @Override
    public boolean test(ResourceConditionContext context){
        return ModList.get().isLoaded(this.modid);
    }

    @Override
    public ResourceConditionSerializer<?> getSerializer(){
        return SERIALIZER;
    }

    private static class Serializer implements ResourceConditionSerializer<ModLoadedResourceCondition> {

        @Override
        public void serialize(JsonObject json, ModLoadedResourceCondition condition){
            json.addProperty("modid", condition.modid);
        }

        @Override
        public ModLoadedResourceCondition deserialize(JsonObject json){
            if(!json.has("modid") || !json.get("modid").isJsonPrimitive() || !json.get("modid").getAsJsonPrimitive().isString())
                throw new RuntimeException("Condition must have key 'modid' with a string value!");

            return new ModLoadedResourceCondition(json.get("modid").getAsString());
        }
    }
}
```

### src/main/java/com/supermartijn642/core/data/condition/NotResourceCondition.java

```java
package com.supermartijn642.core.data.condition;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

/**
 * Created 14/11/2022 by SuperMartijn642
 */
public class NotResourceCondition implements ResourceCondition {

    public static final Serializer SERIALIZER = new Serializer();

    private final ICondition condition;

    public NotResourceCondition(ICondition condition){
        this.condition = condition;
    }

    public NotResourceCondition(ResourceCondition condition){
        this(ResourceCondition.createForgeCondition(condition));
    }

    @Override
    public boolean test(ResourceConditionContext context){
        return !this.condition.test();
    }

    @Override
    public ResourceConditionSerializer<?> getSerializer(){
        return SERIALIZER;
    }

    private static class Serializer implements ResourceConditionSerializer<NotResourceCondition> {

        @Override
        public void serialize(JsonObject json, NotResourceCondition condition){
            json.add("condition", CraftingHelper.serialize(condition.condition));
        }

        @Override
        public NotResourceCondition deserialize(JsonObject json){
            if(!json.has("condition") || !json.get("condition").isJsonObject())
                throw new RuntimeException("Condition must have key 'condition' with a json object!");

            return new NotResourceCondition(CraftingHelper.getCondition(json.get("condition").getAsJsonObject()));
        }
    }
}
```

### src/main/java/com/supermartijn642/core/data/condition/OrResourceCondition.java

```java
package com.supermartijn642.core.data.condition;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created 29/11/2022 by SuperMartijn642
 */
public class OrResourceCondition implements ResourceCondition {

    public static final Serializer SERIALIZER = new Serializer();

    private final List<ICondition> conditions;

    public OrResourceCondition(ICondition... alternatives){
        this.conditions = new ArrayList<>(Arrays.asList(alternatives));
    }

    public OrResourceCondition(ResourceCondition... alternatives){
        this(Arrays.stream(alternatives).map(ResourceCondition::createForgeCondition).toArray(ICondition[]::new));
    }

    @Override
    public boolean test(ResourceConditionContext context){
        for(ICondition condition : this.conditions){
            if(condition.test())
                return true;
        }
        return false;
    }

    @Override
    public ResourceConditionSerializer<?> getSerializer(){
        return SERIALIZER;
    }

    @Override
    public ResourceCondition or(ResourceCondition alternative){
        this.conditions.add(ResourceCondition.createForgeCondition(alternative));
        return this;
    }

    private static class Serializer implements ResourceConditionSerializer<OrResourceCondition> {

        @Override
        public void serialize(JsonObject json, OrResourceCondition condition){
            JsonArray conditions = new JsonArray();
            for(ICondition alternative : condition.conditions)
                conditions.add(CraftingHelper.serialize(alternative));
            json.add("conditions", conditions);
        }

        @Override
        public OrResourceCondition deserialize(JsonObject json){
            if(!json.has("conditions") || !json.get("conditions").isJsonArray())
                throw new RuntimeException("Condition must have key 'conditions' with a json array!");

            JsonArray conditionsJson = json.getAsJsonArray("conditions");
            ICondition[] conditions = new ICondition[conditionsJson.size()];
            for(int i = 0; i < conditionsJson.size(); i++)
                conditions[i] = CraftingHelper.getCondition(conditionsJson.get(i).getAsJsonObject());
            return new OrResourceCondition(conditions);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/data/condition/ResourceCondition.java

```java
package com.supermartijn642.core.data.condition;

import net.minecraftforge.common.crafting.conditions.ICondition;

/**
 * Created 26/08/2022 by SuperMartijn642
 */
public interface ResourceCondition {

    static ICondition createForgeCondition(ResourceCondition condition){
        return ResourceConditions.wrap(condition);
    }

    boolean test(ResourceConditionContext context);

    ResourceConditionSerializer<?> getSerializer();

    /**
     * Negates the effect of the resource condition.
     * @see NotResourceCondition
     */
    default ResourceCondition negate(){
        return new NotResourceCondition(this);
    }

    /**
     * Adds an alternative to the resource condition.
     * @see OrResourceCondition
     */
    default ResourceCondition or(ResourceCondition alternative){
        return new OrResourceCondition(this, alternative);
    }

    /**
     * Adds a requirement to the resource condition.
     * @see AndResourceCondition
     */
    default ResourceCondition and(ResourceCondition condition){
        return new AndResourceCondition(this, condition);
    }
}
```

### src/main/java/com/supermartijn642/core/data/condition/ResourceConditionContext.java

```java
package com.supermartijn642.core.data.condition;

/**
 * TODO eventually add stuff to get tags and such
 * <p>
 * Created 14/11/2022 by SuperMartijn642
 */
public class ResourceConditionContext {

    ResourceConditionContext(){
    }
}
```

### src/main/java/com/supermartijn642/core/data/condition/ResourceConditions.java

```java
package com.supermartijn642.core.data.condition;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created 27/08/2022 by SuperMartijn642
 */
class ResourceConditions {

    private static final Map<ResourceConditionSerializer<?>,IConditionSerializer<?>> TO_UNDERLYING_MAP = new HashMap<>();

    static ConditionWrapper wrap(ResourceCondition condition){
        return new ConditionWrapper(condition);
    }

    static IConditionSerializer<?> wrap(ResourceLocation identifier, ResourceConditionSerializer<?> serializer){
        IConditionSerializer<?> forgeSerializer = new ConditionSerializerWrapper(identifier, serializer);
        TO_UNDERLYING_MAP.put(serializer, forgeSerializer);
        return forgeSerializer;
    }

    private static class ConditionWrapper implements ICondition {

        private final ResourceCondition condition;

        ConditionWrapper(ResourceCondition condition){
            this.condition = condition;
        }

        @Override
        public ResourceLocation getID(){
            return TO_UNDERLYING_MAP.get(this.condition.getSerializer()).getID();
        }

        @Override
        public boolean test(){
            return this.condition.test(new ResourceConditionContext());
        }
    }

    private static class ConditionSerializerWrapper implements IConditionSerializer<ConditionWrapper> {

        private final ResourceLocation identifier;
        private final ResourceConditionSerializer<ResourceCondition> serializer;

        private ConditionSerializerWrapper(ResourceLocation identifier, ResourceConditionSerializer<?> serializer){
            this.identifier = identifier;
            //noinspection unchecked
            this.serializer = (ResourceConditionSerializer<ResourceCondition>)serializer;
        }

        @Override
        public void write(JsonObject json, ConditionWrapper value){
            this.serializer.serialize(json, value.condition);
        }

        @Override
        public ConditionWrapper read(JsonObject json){
            return wrap(this.serializer.deserialize(json));
        }

        @Override
        public ResourceLocation getID(){
            return this.identifier;
        }
    }
}
```

### src/main/java/com/supermartijn642/core/data/condition/ResourceConditionSerializer.java

```java
package com.supermartijn642.core.data.condition;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

/**
 * Created 26/08/2022 by SuperMartijn642
 */
public interface ResourceConditionSerializer<T extends ResourceCondition> {

    static IConditionSerializer<?> createForgeConditionSerializer(ResourceLocation identifier, ResourceConditionSerializer<?> serializer){
        return ResourceConditions.wrap(identifier, serializer);
    }

    void serialize(JsonObject json, T condition);

    T deserialize(JsonObject json);
}
```

### src/main/java/com/supermartijn642/core/data/condition/TagPopulatedResourceCondition.java

```java
package com.supermartijn642.core.data.condition;

import com.google.gson.JsonObject;
import com.supermartijn642.core.registry.Registries;
import com.supermartijn642.core.registry.RegistryUtil;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * TODO properly do tags
 * Created 30/11/2022 by SuperMartijn642
 */
public class TagPopulatedResourceCondition implements ResourceCondition {

    /**
     * TODO this is stupid
     */
    public static final Map<Registries.Registry<?>,Supplier<ITagCollection<?>>> TAGS = new HashMap<>();

    static{
        TAGS.put(Registries.ITEMS, ItemTags::getAllTags);
        TAGS.put(Registries.BLOCKS, BlockTags::getAllTags);
        TAGS.put(Registries.ENTITY_TYPES, EntityTypeTags::getAllTags);
        TAGS.put(Registries.FLUIDS, FluidTags::getAllTags);
    }

    public static final Serializer SERIALIZER = new Serializer();

    private final Registries.Registry<?> registry;
    private final ResourceLocation tag;

    public TagPopulatedResourceCondition(Registries.Registry<?> registry, ResourceLocation tag){
        if(!TAGS.containsKey(registry))
            throw new IllegalArgumentException("Registry '" + registry.getRegistryIdentifier() + "' is not supported!");

        this.registry = registry;
        this.tag = tag;
    }

    @Override
    public boolean test(ResourceConditionContext context){
        return TAGS.get(this.registry).get().getTagOrEmpty(this.tag).getValues().isEmpty();
    }

    @Override
    public ResourceConditionSerializer<?> getSerializer(){
        return SERIALIZER;
    }

    private static class Serializer implements ResourceConditionSerializer<TagPopulatedResourceCondition> {

        @Override
        public void serialize(JsonObject json, TagPopulatedResourceCondition condition){
            json.addProperty("registry", condition.registry.getRegistryIdentifier().toString());
            json.addProperty("tag", condition.tag.toString());
        }

        @Override
        public TagPopulatedResourceCondition deserialize(JsonObject json){
            if(!json.has("registry") || !json.get("registry").isJsonPrimitive() || !json.getAsJsonPrimitive("registry").isString())
                throw new RuntimeException("Condition must have key 'registry' of type string!");
            if(!json.has("tag") || !json.get("tag").isJsonPrimitive() || !json.getAsJsonPrimitive("tag").isString())
                throw new RuntimeException("Condition must have key 'tag' of type string!");
            if(!RegistryUtil.isValidIdentifier(json.get("registry").getAsString()))
                throw new RuntimeException("Value for 'registry' must be a valid identifier!");
            if(!RegistryUtil.isValidIdentifier(json.get("tag").getAsString()))
                throw new RuntimeException("Value for 'tag' must be a valid identifier!");

            Registries.Registry<?> registry = Registries.getRegistry(new ResourceLocation(json.get("registry").getAsString()));
            if(registry == null)
                throw new RuntimeException("Could not find a registry with identifier '" + json.get("registry").getAsString() + "'!");

            ResourceLocation tag = new ResourceLocation(json.get("tag").getAsString());
            return new TagPopulatedResourceCondition(registry, tag);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/data/recipe/ConditionalRecipeSerializer.java

```java
package com.supermartijn642.core.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.supermartijn642.core.data.condition.ResourceCondition;
import com.supermartijn642.core.registry.Registries;
import com.supermartijn642.core.registry.RegistryUtil;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created 26/08/2022 by SuperMartijn642
 */
public class ConditionalRecipeSerializer implements IRecipeSerializer<IRecipe<?>> {

    public static final ConditionalRecipeSerializer INSTANCE = new ConditionalRecipeSerializer();

    public static JsonObject wrapRecipeWithForgeConditions(JsonObject recipe, Collection<ICondition> conditions){
        JsonObject json = new JsonObject();
        json.addProperty("type", Registries.RECIPE_SERIALIZERS.getIdentifier(ConditionalRecipeSerializer.INSTANCE).toString());
        JsonArray conditionsJson = new JsonArray();
        for(ICondition condition : conditions)
            conditionsJson.add(CraftingHelper.serialize(condition));
        json.add("conditions", conditionsJson);
        json.add("recipe", recipe);
        return json;
    }

    public static JsonObject wrapRecipe(JsonObject recipe, Collection<ResourceCondition> conditions){
        return wrapRecipeWithForgeConditions(
            recipe,
            conditions.stream()
                .map(ResourceCondition::createForgeCondition)
                .collect(Collectors.toList())
        );
    }

    private ResourceLocation registryName;

    private ConditionalRecipeSerializer(){
    }

    @Override
    public IRecipe<?> fromJson(ResourceLocation location, JsonObject json){
        if(!json.has("conditions") || !json.get("conditions").isJsonArray())
            throw new RuntimeException("Conditional recipe '" + location + "' must have 'conditions' array!");
        if(!json.has("recipe") || !json.get("recipe").isJsonObject())
            throw new RuntimeException("Conditional recipe '" + location + "' must have 'recipe' object!");

        // Test all conditions
        JsonArray conditions = json.getAsJsonArray("conditions");
        for(JsonElement conditionElement : conditions){
            if(!conditionElement.isJsonObject())
                throw new RuntimeException("Conditions array for recipe '" + location + "' must only contain objects!");
            JsonObject conditionJson = conditionElement.getAsJsonObject();
            if(!conditionJson.has("type") || !conditionJson.get("type").isJsonPrimitive() || !conditionJson.get("type").getAsJsonPrimitive().isString())
                throw new RuntimeException("Condition for recipe '" + location + "' is missing 'type' key!");
            String type = conditionJson.get("type").getAsString();
            if(!RegistryUtil.isValidIdentifier(type))
                throw new RuntimeException("Condition for recipe '" + location + "' has invalid type '" + type + "'!");

            IConditionSerializer<?> serializer = Registries.RECIPE_CONDITION_SERIALIZERS.getValue(new ResourceLocation(type));
            if(serializer == null)
                throw new RuntimeException("Condition for recipe '" + location + "' has unknown type '" + new ResourceLocation(type) + "'!");

            ICondition condition;
            try{
                condition = serializer.read(conditionJson);
            }catch(Exception e){
                throw new RuntimeException("Encountered exception whilst testing condition '" + new ResourceLocation(type) + "' for recipe '" + location + "'!");
            }

            if(!condition.test())
                return null;
        }

        // Now return the recipe
        return RecipeManager.fromJson(location, json.getAsJsonObject("recipe"));
    }

    @Override
    public IRecipe<?> fromNetwork(ResourceLocation resourceLocation, PacketBuffer friendlyByteBuf){
        return null;
    }

    @Override
    public void toNetwork(PacketBuffer friendlyByteBuf, IRecipe<?> recipe){
    }

    @Override
    public IRecipeSerializer<?> setRegistryName(ResourceLocation name){
        this.registryName = name;
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName(){
        return this.registryName;
    }

    @Override
    public Class<IRecipeSerializer<?>> getRegistryType(){
        return ForgeRegistries.RECIPE_SERIALIZERS.getRegistrySuperType();
    }
}
```

### src/main/java/com/supermartijn642/core/data/tag/CustomTagEntries.java

```java
package com.supermartijn642.core.data.tag;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.supermartijn642.core.registry.Registries;
import com.supermartijn642.core.registry.RegistryUtil;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

/**
 * Created 09/02/2024 by SuperMartijn642
 */
public class CustomTagEntries {

    static JsonObject serialize(TagEntryAdapter entry){
        JsonObject json = new JsonObject();
        CustomTagEntrySerializer<?> serializer = entry.customEntry.getSerializer();
        json.addProperty("type", Registries.CUSTOM_TAG_ENTRY_SERIALIZERS.getIdentifier(serializer).toString());
        try{
            //noinspection unchecked,rawtypes
            ((CustomTagEntrySerializer)serializer).serialize(json, entry.customEntry);
        }catch(Exception e){
            throw new RuntimeException("Encountered an exception whilst serializing custom tag entry for type '" + Registries.CUSTOM_TAG_ENTRY_SERIALIZERS.getIdentifier(serializer).toString() + "'!");
        }
        return json;
    }

    public static TagEntryAdapter potentiallyDeserialize(JsonElement input){
        if(!(input instanceof JsonObject))
            return null;
        JsonObject json = (JsonObject)input;
        if(!json.has("type") || !json.get("type").isJsonPrimitive() || !json.getAsJsonPrimitive("type").isString())
            return null;
        String typeString = json.get("type").getAsString();
        if(!RegistryUtil.isValidIdentifier(typeString))
            return null;
        ResourceLocation type = new ResourceLocation(typeString);
        if(!Registries.CUSTOM_TAG_ENTRY_SERIALIZERS.hasIdentifier(type))
            return null;
        CustomTagEntrySerializer<?> serializer = Registries.CUSTOM_TAG_ENTRY_SERIALIZERS.getValue(type);
        CustomTagEntry customEntry;
        try{
            customEntry = serializer.deserialize(json);
        }catch(JsonParseException e){
            throw new RuntimeException("Encountered an exception whilst deserializing custom tag entry for type '" + type + "'!", e);
        }
        return new TagEntryAdapter(type, customEntry);
    }

    static ITag.ITagEntry wrap(CustomTagEntry customEntry){
        return new TagEntryAdapter(Registries.CUSTOM_TAG_ENTRY_SERIALIZERS.getIdentifier(customEntry.getSerializer()), customEntry);
    }
}
```

### src/main/java/com/supermartijn642/core/data/tag/CustomTagEntry.java

```java
package com.supermartijn642.core.data.tag;

import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Created 09/02/2024 by SuperMartijn642
 */
public interface CustomTagEntry {

    static ITag.ITagEntry createVanillaEntry(CustomTagEntry customEntry){
        return CustomTagEntries.wrap(customEntry);
    }

    <T> Collection<T> resolve(TagEntryResolutionContext<T> context);

    default Collection<ResourceLocation> getTagDependencies(){
        return Collections.emptyList();
    }

    CustomTagEntrySerializer<?> getSerializer();

    interface TagEntryResolutionContext<T> {
        T getElement(ResourceLocation identifier);

        Collection<T> getTag(ResourceLocation identifier);

        Collection<T> getAllElements();

        Set<ResourceLocation> getAllIdentifiers();
    }
}
```

### src/main/java/com/supermartijn642/core/data/tag/CustomTagEntrySerializer.java

```java
package com.supermartijn642.core.data.tag;

import com.google.gson.JsonObject;

/**
 * Created 09/02/2024 by SuperMartijn642
 */
public interface CustomTagEntrySerializer<T extends CustomTagEntry> {

    void serialize(JsonObject json, T entry);

    T deserialize(JsonObject json);
}
```

### src/main/java/com/supermartijn642/core/data/tag/entries/NamespaceTagEntry.java

```java
package com.supermartijn642.core.data.tag.entries;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.supermartijn642.core.data.tag.CustomTagEntry;
import com.supermartijn642.core.data.tag.CustomTagEntrySerializer;
import com.supermartijn642.core.registry.RegistryUtil;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created 09/02/2024 by SuperMartijn642
 */
public class NamespaceTagEntry implements CustomTagEntry {

    public static final CustomTagEntrySerializer<NamespaceTagEntry> SERIALIZER = new Serializer();

    private final String namespace;

    public NamespaceTagEntry(String namespace){
        this.namespace = namespace;
    }

    @Override
    public <T> Collection<T> resolve(TagEntryResolutionContext<T> context){
        return context.getAllIdentifiers().stream()
            .filter(i -> i.getNamespace().equals(this.namespace))
            .map(context::getElement)
            .collect(Collectors.toList());
    }

    @Override
    public CustomTagEntrySerializer<?> getSerializer(){
        return SERIALIZER;
    }

    private static class Serializer implements CustomTagEntrySerializer<NamespaceTagEntry> {

        @Override
        public void serialize(JsonObject json, NamespaceTagEntry entry){
            json.addProperty("namespace", entry.namespace);
        }

        @Override
        public NamespaceTagEntry deserialize(JsonObject json){
            if(!json.has("namespace") || !json.get("namespace").isJsonPrimitive() || !json.getAsJsonPrimitive("namespace").isString())
                throw new JsonParseException("Tag entry must have string key 'namespace'!");
            String namespace = json.get("namespace").getAsString();
            if(!RegistryUtil.isValidNamespace(namespace))
                throw new JsonParseException("Invalid namespace '" + namespace + "'!");
            return new NamespaceTagEntry(namespace);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/data/tag/TagEntryAdapter.java

```java
package com.supermartijn642.core.data.tag;

import com.google.gson.JsonArray;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created 09/02/2024 by SuperMartijn642
 */
public class TagEntryAdapter implements ITag.ITagEntry {

    final ResourceLocation identifier;
    final CustomTagEntry customEntry;
    private Registry<?> registry;
    private ForgeRegistry<?> forgeRegistry;

    TagEntryAdapter(ResourceLocation identifier, CustomTagEntry customEntry){
        this.identifier = identifier;
        this.customEntry = customEntry;
    }

    public void setRegistry(Registry<?> registry, ForgeRegistry<?> forgeRegistry){
        this.registry = registry;
        this.forgeRegistry = forgeRegistry;
    }

    @Override
    public <T> boolean build(Function<ResourceLocation,ITag<T>> tagLookup, Function<ResourceLocation,T> elementLookup, Consumer<T> entryConsumer){
        CustomTagEntry.TagEntryResolutionContext<T> context = new CustomTagEntry.TagEntryResolutionContext<T>() {
            @Override
            public T getElement(ResourceLocation identifier){
                return elementLookup.apply(identifier);
            }

            @Override
            public Collection<T> getTag(ResourceLocation identifier){
                return tagLookup.apply(identifier).getValues();
            }

            @Override
            public Collection<T> getAllElements(){
                //noinspection unchecked
                return TagEntryAdapter.this.registry == null ?
                    (Collection<T>)TagEntryAdapter.this.forgeRegistry.getValues() :
                    (Collection<T>)TagEntryAdapter.this.registry.stream().collect(Collectors.toList());
            }

            @Override
            public Set<ResourceLocation> getAllIdentifiers(){
                return TagEntryAdapter.this.registry == null ?
                    TagEntryAdapter.this.forgeRegistry.getKeys() :
                    TagEntryAdapter.this.registry.keySet();
            }
        };
        Collection<T> entries = this.customEntry.resolve(context);
        if(entries != null)
            entries.forEach(entryConsumer);
        return true;
    }

    @Override
    public String toString(){
        return "'" + this.identifier + "'{" + this.customEntry + "}";
    }

    @Override
    public void serializeTo(JsonArray array){
        array.add(CustomTagEntries.serialize(this));
    }
}
```

### src/main/java/com/supermartijn642/core/EnergyFormat.java

```java
package com.supermartijn642.core;

import net.minecraft.client.resources.LanguageManager;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created 7/30/2021 by SuperMartijn642
 */
public class EnergyFormat {

    private static EnergyType type = EnergyType.RF;

    /**
     * Cycles through the available energy types.
     */
    public static void cycleEnergyType(boolean forward){
        type = EnergyType.values()[(type.ordinal() + (forward ? 1 : EnergyType.values().length - 1)) % EnergyType.values().length];
    }

    /**
     * Formats an amount of energy for displaying to players. For input {@code 10000} this would result in {@code "10,000"}.
     */
    public static String formatEnergy(int energy){
        return type.convertEnergy(energy);
    }

    /**
     * Formats an amount of energy including the unit for displaying to players. For input {@code 100} this would result in {@code "100 X"}.
     */
    public static String formatEnergyWithUnit(int energy){
        return formatEnergy(energy) + " " + formatUnit();
    }

    /**
     * Formats an amount of energy including the unit per tick for displaying to players. For input {@code 100} this would result in {@code "100 X/t"}.
     */
    public static String formatEnergyPerTick(int energy){
        return formatEnergy(energy) + " " + formatUnitPerTick();
    }

    /**
     * Formats a given amount of energy and capacity for displaying to players. For inputs {@code 5} and {@code 100} this would result in {@code "5 / 100"}.
     */
    public static String formatCapacity(int energy, int capacity){
        return formatEnergy(energy) + " / " + formatEnergy(capacity);
    }

    /**
     * Formats a given amount of energy and capacity including the unit for displaying to players. For inputs {@code 5} and {@code 100} this would result in {@code "5 / 100 X"}.
     */
    public static String formatCapacityWithUnit(int energy, int capacity){
        return formatCapacity(energy, capacity) + " " + formatUnit();
    }

    /**
     * Formats the selected energy type's unit for displaying to players.
     */
    public static String formatUnit(){
        return type.getUnit();
    }

    /**
     * Formats the selected energy type's unit per tick for displaying to players.
     */
    public static String formatUnitPerTick(){
        return type.unit + "/t";
    }

    private enum EnergyType {
        FE("FE"), RF("RF")/*, MJ("MJ")*/;

        private final String unit;

        EnergyType(String unit){
            this.unit = unit;
        }

        public String getUnit(){
            return this.unit;
        }

        public String convertEnergy(int energy){
            LanguageManager manager = ClientUtils.getMinecraft().getLanguageManager();
            Locale locale = manager == null || manager.getSelected() == null ? Locale.getDefault() : manager.getSelected().getJavaLocale();
            return NumberFormat.getNumberInstance(locale).format(energy);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/extensions/TagLoaderExtension.java

```java
package com.supermartijn642.core.extensions;

import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistry;

/**
 * Created 09/02/2024 by SuperMartijn642
 */
public interface TagLoaderExtension {

    void supermartijn642corelibSetRegistry(Registry<?> registry, ForgeRegistry<?> forgeRegistry);
}
```

### src/main/java/com/supermartijn642/core/generator/AdvancementGenerator.java

```java
package com.supermartijn642.core.generator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.core.data.condition.ModLoadedResourceCondition;
import com.supermartijn642.core.data.condition.NotResourceCondition;
import com.supermartijn642.core.data.condition.ResourceCondition;
import com.supermartijn642.core.registry.Registries;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.item.Item;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.*;

/**
 * Created 22/08/2022 by SuperMartijn642
 */
public abstract class AdvancementGenerator extends ResourceGenerator {

    private final Map<ResourceLocation,AdvancementBuilder> advancements = new HashMap<>();

    public AdvancementGenerator(String modid, ResourceCache cache){
        super(modid, cache);
    }

    @Override
    public void save(){
        // Loop over all advancements
        for(AdvancementBuilder advancementBuilder : this.advancements.values()){
            // Verify the advancement has any criteria
            if(advancementBuilder.criteria.isEmpty())
                throw new RuntimeException("Advancement '" + advancementBuilder.identifier + "' does not have any criteria!");
            // Verify all requirements
            if(advancementBuilder.requirements.isEmpty()){
                if(advancementBuilder.criteria.size() != 1)
                    throw new RuntimeException("Advancement '" + advancementBuilder.identifier + "' does not have any requirements set!");
                advancementBuilder.requirements.add(advancementBuilder.criteria.keySet().toArray(new String[0]));
            }
            for(String criterion : advancementBuilder.requirements.stream().flatMap(Arrays::stream).toArray(String[]::new)){
                if(advancementBuilder.criteria.containsKey(criterion))
                    continue;
                throw new RuntimeException("Found requirement for unknown criterion '" + criterion + "' in advancement '" + advancementBuilder.identifier + "'!");
            }

            JsonObject json = new JsonObject();
            // Conditions
            if(!advancementBuilder.conditions.isEmpty()){
                JsonArray conditionsJson = new JsonArray();
                for(ICondition condition : advancementBuilder.conditions)
                    conditionsJson.add(CraftingHelper.serialize(condition));
                json.add("conditions", conditionsJson);
            }
            // Parent
            if(advancementBuilder.parent != null){
                ResourceLocation parent = advancementBuilder.parent;
                if(!this.advancements.containsKey(parent) && !this.cache.doesResourceExist(ResourceType.DATA, parent.getNamespace(), "advancements", parent.getPath(), ".json"))
                    throw new RuntimeException("Could not find parent '" + parent + "' for advancement '" + advancementBuilder.identifier + "'!");
                json.addProperty("parent", parent.toString());
            }
            // Display
            JsonObject displayJson = new JsonObject();
            // Icon
            if(advancementBuilder.icon == null && !new ResourceLocation("minecraft", "recipes/root").equals(advancementBuilder.parent))
                throw new RuntimeException("Advancement '" + advancementBuilder.identifier + "' must have an icon!");
            if(advancementBuilder.icon != null){
                JsonObject iconJson = new JsonObject();
                iconJson.addProperty("item", Registries.ITEMS.getIdentifier(advancementBuilder.icon).toString());
                if(advancementBuilder.iconTag != null)
                    iconJson.addProperty("nbt", advancementBuilder.iconTag.toString());
                displayJson.add("icon", iconJson);
            }
            // Title
            JsonObject titleJson = new JsonObject();
            titleJson.addProperty("translate", advancementBuilder.titleKey);
            displayJson.add("title", titleJson);
            // Description
            JsonObject description = new JsonObject();
            description.addProperty("translate", advancementBuilder.descriptionKey);
            displayJson.add("description", description);
            // Frame
            displayJson.addProperty("frame", advancementBuilder.frame.getName());
            // Background
            if(advancementBuilder.background != null){
                if(!this.cache.doesResourceExist(ResourceType.ASSET, advancementBuilder.background.getNamespace(), "textures", advancementBuilder.background.getPath(), ".png"))
                    throw new RuntimeException("Could not find background texture '" + advancementBuilder.background + "' for advancement '" + advancementBuilder.identifier + "'!");

                displayJson.addProperty("background", advancementBuilder.background.getNamespace() + ":textures/" + advancementBuilder.background.getPath() + ".png");
            }
            // Show toast
            displayJson.addProperty("show_toast", advancementBuilder.showToast);
            // Announce to chat
            displayJson.addProperty("announce_to_chat", advancementBuilder.announceToChat);
            // Hidden
            displayJson.addProperty("hidden", advancementBuilder.hidden);
            json.add("display", displayJson);
            // Criteria
            JsonObject criteriaJson = new JsonObject();
            for(Map.Entry<String,CriterionInstance> criterion : advancementBuilder.criteria.entrySet()){
                JsonObject criterionJson = new JsonObject();
                criterionJson.addProperty("trigger", criterion.getValue().getCriterion().toString());
                JsonObject conditionsJson = criterion.getValue().serializeToJson(ConditionArraySerializer.INSTANCE);
                if(conditionsJson.size() > 0)
                    criterionJson.add("conditions", conditionsJson);
                criteriaJson.add(criterion.getKey(), criterionJson);
            }
            json.add("criteria", criteriaJson);
            // Requirements
            JsonArray requirementsArray = new JsonArray();
            for(String[] requirementGroup : advancementBuilder.requirements){
                JsonArray groupArray = new JsonArray();
                Arrays.stream(requirementGroup).forEach(groupArray::add);
                requirementsArray.add(groupArray);
            }
            json.add("requirements", requirementsArray);
            // Rewards
            JsonObject rewardsJson = new JsonObject();
            // Recipe rewards
            if(!advancementBuilder.rewardRecipes.isEmpty()){
                JsonArray recipesJson = new JsonArray();
                for(ResourceLocation rewardRecipe : advancementBuilder.rewardRecipes){
                    if(!this.cache.doesResourceExist(ResourceType.DATA, rewardRecipe.getNamespace(), "recipes", rewardRecipe.getPath(), ".json"))
                        throw new RuntimeException("Could not find reward recipe '" + rewardRecipe + "' for advancement '" + advancementBuilder.identifier + "'!");

                    recipesJson.add(rewardRecipe.toString());
                }
                rewardsJson.add("recipes", recipesJson);
            }
            // Loot table rewards
            if(!advancementBuilder.rewardLootTables.isEmpty()){
                JsonArray lootTablesJson = new JsonArray();
                for(ResourceLocation rewardLootTable : advancementBuilder.rewardLootTables){
                    if(!this.cache.doesResourceExist(ResourceType.DATA, rewardLootTable.getNamespace(), "loot_tables", rewardLootTable.getPath(), ".json"))
                        throw new RuntimeException("Could not find reward loot table '" + rewardLootTable + "' for advancement '" + advancementBuilder.identifier + "'!");

                    lootTablesJson.add(rewardLootTable.toString());
                }
                rewardsJson.add("loot", lootTablesJson);
            }
            // Reward experience
            if(advancementBuilder.rewardExperience != 0)
                rewardsJson.addProperty("experience", advancementBuilder.rewardExperience);
            if(rewardsJson.size() != 0)
                json.add("rewards", rewardsJson);

            // Save the object to the cache
            ResourceLocation identifier = advancementBuilder.identifier;
            this.cache.saveJsonResource(ResourceType.DATA, json, identifier.getNamespace(), "advancements", identifier.getPath());
        }
    }

    /**
     * Creates a new advancement builder for the given identifier.
     * @param identifier location of the advancement
     */
    public AdvancementBuilder advancement(ResourceLocation identifier){
        if(this.advancements.containsKey(identifier))
            throw new RuntimeException("Duplicate advancement with identifier '" + identifier + "'!");

        this.cache.trackToBeGeneratedResource(ResourceType.DATA, identifier.getNamespace(), "advancements", identifier.getPath(), ".json");
        return this.advancements.computeIfAbsent(identifier, i -> new AdvancementBuilder(this.modid, i));
    }

    /**
     * Creates a new advancement builder for the given namespace and path.
     * @param namespace namespace of the advancement
     * @param path      path of the advancement
     */
    public AdvancementBuilder advancement(String namespace, String path){
        return this.advancement(new ResourceLocation(namespace, path));
    }

    /**
     * Creates a new advancement builder for the given namespace and path.
     * @param identifier location of the advancement
     */
    public AdvancementBuilder advancement(String identifier){
        return this.advancement(this.modid, identifier);
    }

    @Override
    public String getName(){
        return this.modName + " Advancement Generator";
    }

    protected static class AdvancementBuilder {

        protected final String modid;
        protected final ResourceLocation identifier;
        private final List<ICondition> conditions = new ArrayList<>();
        private final Map<String,CriterionInstance> criteria = new LinkedHashMap<>();
        private final List<String[]> requirements = new ArrayList<>();
        private final List<ResourceLocation> rewardLootTables = new ArrayList<>();
        private final List<ResourceLocation> rewardRecipes = new ArrayList<>();
        private ResourceLocation parent;
        private Item icon;
        private CompoundNBT iconTag;
        private String titleKey;
        private String descriptionKey;
        private FrameType frame = FrameType.TASK;
        private ResourceLocation background;
        private boolean showToast = true;
        private boolean announceToChat = true;
        private boolean hidden;
        private int rewardExperience;

        public AdvancementBuilder(String modid, ResourceLocation identifier){
            this.modid = modid;
            this.identifier = identifier;
            this.titleKey = identifier.getNamespace() + ".advancement." + identifier.getPath() + ".title";
            this.descriptionKey = identifier.getNamespace() + ".advancement." + identifier.getPath() + ".description";
        }

        /**
         * Adds a condition for this advancement to be loaded.
         */
        public AdvancementBuilder condition(ICondition condition){
            this.conditions.add(condition);
            return this;
        }

        /**
         * Adds a condition for this advancement to be loaded.
         */
        public AdvancementBuilder condition(ResourceCondition condition){
            return this.condition(ResourceCondition.createForgeCondition(condition));
        }

        /**
         * Adds a condition to only load this advancement when the given condition is <b>not</b> satisfied.
         */
        public AdvancementBuilder notCondition(ICondition condition){
            return this.condition(new NotResourceCondition(condition));
        }

        /**
         * Adds a condition to only load this advancement when the given condition is <b>not</b> satisfied.
         */
        public AdvancementBuilder notCondition(ResourceCondition condition){
            return this.condition(new NotResourceCondition(condition));
        }

        /**
         * Adds a condition to only load this advancement when a mod with the given modid is present.
         */
        public AdvancementBuilder modLoadedCondition(String modid){
            return this.condition(new ModLoadedResourceCondition(modid));
        }

        /**
         * Sets the parent advancement for this advancement.
         * @param advancement location of the parent advancement
         */
        public AdvancementBuilder parent(ResourceLocation advancement){
            if(this.identifier.equals(advancement))
                throw new IllegalArgumentException("Advancement '" + this.identifier + "' cannot have itself as parent!");

            this.parent = advancement;
            return this;
        }

        /**
         * Sets the parent advancement for this advancement.
         * @param namespace namespace of the parent advancement
         * @param path      path of the parent advancement
         */
        public AdvancementBuilder parent(String namespace, String path){
            return this.parent(new ResourceLocation(namespace, path));
        }

        /**
         * Sets the parent advancement for this advancement.
         * @param advancement location of the parent advancement
         */
        public AdvancementBuilder parent(String advancement){
            return this.parent(this.modid, advancement);
        }

        /**
         * Sets the icon for this advancement.
         * @param item item to use as icon
         * @param tag  tag for the item
         */
        public AdvancementBuilder icon(IItemProvider item, CompoundNBT tag){
            this.icon = item.asItem();
            this.iconTag = tag;
            return this;
        }

        /**
         * Sets the icon for this advancement.
         * @param item item to use as icon
         */
        public AdvancementBuilder icon(IItemProvider item){
            return this.icon(item, null);
        }

        /**
         * Sets the icon for this advancement.
         * @param item identifier of the item to use as icon
         */
        public AdvancementBuilder icon(ResourceLocation item){
            if(!Registries.ITEMS.hasIdentifier(item))
                throw new IllegalArgumentException("Could not find any item registered under '" + item + "'!");

            return this.icon(Registries.ITEMS.getValue(item), null);
        }

        /**
         * Sets the icon for this advancement.
         * @param namespace  namespace of the item to use as icon
         * @param identifier path of the item to use as icon
         */
        public AdvancementBuilder icon(String namespace, String identifier){
            return this.icon(new ResourceLocation(namespace, identifier));
        }

        /**
         * Sets the translation key for the title of this advancement.
         * @param translationKey key to use for the title
         */
        public AdvancementBuilder title(String translationKey){
            if(translationKey == null || translationKey.trim().isEmpty())
                throw new IllegalArgumentException("Title translation key '" + translationKey + "' for advancement '" + this.identifier + "' must not be empty!");

            this.titleKey = translationKey;
            return this;
        }

        /**
         * Sets the translation key for the description of this advancement.
         * @param translationKey key to use for the description
         */
        public AdvancementBuilder description(String translationKey){
            if(translationKey == null || translationKey.trim().isEmpty())
                throw new IllegalArgumentException("Description translation key '" + translationKey + "' for advancement '" + this.identifier + "' must not be empty!");

            this.descriptionKey = translationKey;
            return this;
        }

        /**
         * Sets the frame type for this advancement.
         * @param frameType frame type to use
         */
        public AdvancementBuilder frame(FrameType frameType){
            this.frame = frameType;
            return this;
        }

        /**
         * Sets the challenge frame type for this advancement.
         */
        public AdvancementBuilder challengeFrame(){
            return this.frame(FrameType.CHALLENGE);
        }

        /**
         * Sets the goal frame type for this advancement.
         */
        public AdvancementBuilder goalFrame(){
            return this.frame(FrameType.GOAL);
        }

        /**
         * Sets the task frame type for this advancement.
         */
        public AdvancementBuilder taskFrame(){
            return this.frame(FrameType.TASK);
        }

        /**
         * Sets the background texture for this advancement. Only has effect if this advancement has no parent.
         * @param texture location of the background texture
         */
        public AdvancementBuilder background(ResourceLocation texture){
            this.background = texture;
            return this;
        }

        /**
         * Sets the background texture for this advancement. Only has effect if this advancement has no parent.
         * @param namespace namespace of the background texture
         * @param path      path of the background texture
         */
        public AdvancementBuilder background(String namespace, String path){
            return this.background(new ResourceLocation(namespace, path));
        }

        /**
         * Sets whether to show a toast when this advancement is obtained.
         * @param show whether to show a toast
         */
        public AdvancementBuilder showToast(boolean show){
            this.showToast = show;
            return this;
        }

        /**
         * Sets to not show a toast when this advancement is obtained.
         */
        public AdvancementBuilder dontShowToast(){
            return this.showToast(false);
        }

        /**
         * Sets whether to broadcast a chat message when this advancement is obtained.
         * @param announce whether to broadcast a chat message
         */
        public AdvancementBuilder announceToChat(boolean announce){
            this.announceToChat = announce;
            return this;
        }

        /**
         * Sets to not broadcast a chat message when this advancement is obtained.
         */
        public AdvancementBuilder dontAnnounceToChat(){
            return this.announceToChat(false);
        }

        /**
         * Sets whether this advancement is hidden.
         * @param hidden whether this advancement is hidden
         */
        public AdvancementBuilder hidden(boolean hidden){
            this.hidden = hidden;
            return this;
        }

        /**
         * Sets this advancement to be hidden.
         */
        public AdvancementBuilder hidden(){
            return this.hidden(true);
        }

        /**
         * Adds a criterion to this advancement. The criterion should be added to be requirements using the given name.
         * @param name      name for the criterion
         * @param criterion criterion to be added
         */
        public AdvancementBuilder criterion(String name, CriterionInstance criterion){
            if(this.criteria.containsKey(name))
                throw new RuntimeException("Duplicate criterion with name '" + name + "' for advancement '" + this.identifier + "'!");

            this.criteria.put(name, criterion);
            return this;
        }

        /**
         * Adds a criterion for the player to have the given items. The criterion should be added to be requirements using the given name.
         * @param name  name for the criterion
         * @param items items needed to satisfy the criterion
         */
        public AdvancementBuilder hasItemsCriterion(String name, IItemProvider... items){
            this.criterion(name, InventoryChangeTrigger.Instance.hasItems(items));
            return this;
        }

        /**
         * Adds a new group of requirements. The advancement will be obtained when any group is satisfied.
         * @param criteria criteria for the group
         */
        public AdvancementBuilder requirementGroup(String... criteria){
            if(this.requirements.contains(criteria))
                throw new RuntimeException("Duplicate requirement group '" + Arrays.toString(criteria) + "' for advancement '" + this.identifier + "'!");

            this.requirements.add(criteria);
            return this;
        }

        /**
         * Adds the given groups to requirements. The advancement will be obtained when any group is satisfied.
         * @param groups groups to be added
         */
        public AdvancementBuilder requirements(String[]... groups){
            Arrays.stream(groups).forEach(this::requirementGroup);
            return this;
        }

        /**
         * Sets the experience to be awarded when the advancement is obtained.
         * @param experience the amount of experience
         */
        public AdvancementBuilder rewardExperience(int experience){
            if(experience < 0)
                throw new IllegalArgumentException("Reward experience for advancement '" + this.identifier + "' must be greater than 0, not '" + experience + "'!");

            this.rewardExperience = experience;
            return this;
        }

        /**
         * Adds a loot table to be awarded when the advancement is obtained.
         * @param lootTable location of the loot table
         */
        public AdvancementBuilder rewardLootTable(ResourceLocation lootTable){
            this.rewardLootTables.add(lootTable);
            return this;
        }

        /**
         * Adds a loot table to be awarded when the advancement is obtained.
         * @param namespace namespace of the loot table
         * @param path      path of the loot table
         */
        public AdvancementBuilder rewardLootTable(String namespace, String path){
            return this.rewardLootTable(new ResourceLocation(namespace, path));
        }

        /**
         * Adds a recipe to be awarded when the advancement is obtained.
         * @param recipe location of the recipe
         */
        public AdvancementBuilder rewardRecipe(ResourceLocation recipe){
            if(this.rewardRecipes.contains(recipe))
                throw new RuntimeException("Duplicate recipe reward '" + recipe + "' for advancement '" + this.identifier + "'!");

            this.rewardRecipes.add(recipe);
            return this;
        }

        /**
         * Adds a recipe to be awarded when the advancement is obtained.
         * @param namespace namespace of the recipe
         * @param path      path of the recipe
         */
        public AdvancementBuilder rewardRecipe(String namespace, String path){
            return this.rewardRecipe(new ResourceLocation(namespace, path));
        }
    }
}
```

### src/main/java/com/supermartijn642/core/generator/aggregator/ResourceAggregator.java

```java
package com.supermartijn642.core.generator.aggregator;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created 06/05/2023 by SuperMartijn642
 */
public interface ResourceAggregator<S, T> {

    S initialData();

    S combine(S data, T newData);

    void write(OutputStream stream, S data) throws IOException;
}
```

### src/main/java/com/supermartijn642/core/generator/aggregator/TranslationsAggregator.java

```java
package com.supermartijn642.core.generator.aggregator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created 06/05/2023 by SuperMartijn642
 */
public class TranslationsAggregator implements ResourceAggregator<Map<String,String>,Map<String,String>> {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public static final TranslationsAggregator INSTANCE = new TranslationsAggregator();

    private TranslationsAggregator(){
    }

    @Override
    public Map<String,String> initialData(){
        return new LinkedHashMap<>();
    }

    @Override
    public Map<String,String> combine(Map<String,String> data, Map<String,String> newData){
        newData.forEach((key, translation) -> {
            String oldValue = data.put(key, translation);
            if(oldValue != null && !oldValue.equals(translation))
                throw new IllegalArgumentException("Conflicting translations for key '" + key + "'!");
        });
        return data;
    }

    @Override
    public void write(OutputStream stream, Map<String,String> data) throws IOException{
        JsonObject json = new JsonObject();
        data.forEach(json::addProperty);
        try(Writer writer = new OutputStreamWriter(stream)){
            GSON.toJson(json, writer);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/generator/BlockStateGenerator.java

```java
package com.supermartijn642.core.generator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.supermartijn642.core.registry.Registries;
import com.supermartijn642.core.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.ResourceLocation;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created 19/08/2022 by SuperMartijn642
 */
public abstract class BlockStateGenerator extends ResourceGenerator {

    private final Map<Block,BlockStateBuilder> blockStates = new HashMap<>();

    public BlockStateGenerator(String modid, ResourceCache cache){
        super(modid, cache);
    }

    @Override
    public void save(){
        // Loop over all block states
        for(BlockStateBuilder blockStateBuilder : this.blockStates.values()){
            ResourceLocation block = Registries.BLOCKS.getIdentifier(blockStateBuilder.block);
            JsonObject json = new JsonObject();

            // Serialize all variants
            JsonObject variantsJson = new JsonObject();
            ArrayList<Map.Entry<PartialBlockState,VariantBuilder>> variants = new ArrayList<>(blockStateBuilder.variants.entrySet());
            variants.sort(Map.Entry.comparingByKey());
            for(Map.Entry<PartialBlockState,VariantBuilder> variantEntry : variants){
                if(variantEntry.getValue().models.isEmpty())
                    continue;
                String name = formatVariantName(variantEntry.getKey());
                variantsJson.add(name, this.serializeVariant(variantEntry.getValue(), block));
            }
            if(variantsJson.size() > 0)
                json.add("variants", variantsJson);

            // Serialize all multiparts
            JsonArray multipartsJson = new JsonArray();
            for(Pair<MultipartConditionBuilder,VariantBuilder> multipartEntry : blockStateBuilder.multipartVariants){
                if(multipartEntry.right().models.isEmpty())
                    continue;
                JsonObject multipartJson = new JsonObject();
                multipartJson.add("apply", this.serializeVariant(multipartEntry.right(), block));
                multipartEntry.left().flatten();
                JsonArray whenJson = new JsonArray();
                for(MultipartConditionBuilder condition : multipartEntry.left().or){
                    if(condition.properties.isEmpty())
                        continue;
                    JsonObject conditionJson = new JsonObject();
                    //noinspection rawtypes,unchecked
                    condition.properties.forEach(
                        (property, values) ->
                            conditionJson.addProperty(property.getName(), Arrays.stream(values).map(((Property)property)::getName).collect(Collectors.joining("|")))
                    );
                    whenJson.add(conditionJson);
                }
                if(whenJson.size() == 1)
                    multipartJson.add("when", whenJson.get(0));
                else if(whenJson.size() != 0){
                    JsonObject newWhenJson = new JsonObject();
                    newWhenJson.add("OR", whenJson);
                    multipartJson.add("when", newWhenJson);
                }
                multipartsJson.add(multipartJson);
            }
            if(multipartsJson.size() != 0)
                json.add("multipart", multipartsJson);

            // Check if there's at one entry in the block state
            if(variantsJson.size() == 0 && multipartsJson.size() == 0)
                throw new RuntimeException("Block state for block '" + block + "' is empty!");

            // Save the object to the cache
            this.cache.saveJsonResource(ResourceType.ASSET, json, block.getNamespace(), "blockstates", block.getPath());
        }
    }

    private JsonElement serializeVariant(VariantBuilder builder, ResourceLocation block){
        JsonObject[] models = new JsonObject[builder.models.size()];
        for(int i = 0; i < models.length; i++){
            VariantModel model = builder.models.get(i);
            JsonObject modelJson = new JsonObject();
            // Model location
            if(!this.cache.doesResourceExist(ResourceType.ASSET, model.modelLocation.getNamespace(), "models", model.modelLocation.getPath(), ".json"))
                throw new RuntimeException("Could not find model '" + model.modelLocation + "' in block state for block '" + block + "'!");
            modelJson.addProperty("model", model.modelLocation.toString());
            // Rotation
            if(model.xRotation != 0)
                modelJson.addProperty("x", model.xRotation);
            if(model.yRotation != 0)
                modelJson.addProperty("y", model.yRotation);
            // UV lock
            if(model.uvLock)
                modelJson.addProperty("uvlock", true);
            // Weight
            if(model.weight != 1 && models.length > 1)
                modelJson.addProperty("weight", model.weight);
            models[i] = modelJson;
        }
        return models.length > 1 ? createJsonArray(models) : models[0];
    }

    private static JsonArray createJsonArray(JsonElement... elements){
        // Because they can't just make a proper json array constructor...
        JsonArray array = new JsonArray();
        for(JsonElement element : elements)
            array.add(element);
        return array;
    }

    private static String formatVariantName(PartialBlockState state){
        //noinspection unchecked,rawtypes
        return state.properties.entrySet().stream().map(entry -> entry.getKey().getName() + "=" + ((Property)entry.getKey()).getName(entry.getValue())).collect(Collectors.joining(","));
    }

    /**
     * Creates a partial state builder for the given block.
     * @param block block to create a partial state builder for
     */
    protected PartialBlockStateBuilder createPartialStateBuilder(Block block){
        return new PartialBlockStateBuilder(block);
    }

    /**
     * Creates an empty partial state for the given block.
     * @param block block to create an empty partial state for
     */
    protected PartialBlockState createEmptyPartialState(Block block){
        return this.createPartialStateBuilder(block).build();
    }

    /**
     * Creates a partial state with the properties from the given state.
     * @param state state to copy the properties from
     */
    protected PartialBlockState createPartialState(BlockState state){
        return this.createPartialStateBuilder(state.getBlock()).copy(state).build();
    }

    /**
     * Gets a block state builder for the given block. The returned block state builder may be a new block state builder or an existing one if requested before.
     * @param block block to get a block state builder for
     */
    protected BlockStateBuilder blockState(Block block){
        ResourceLocation identifier = Registries.BLOCKS.getIdentifier(block);
        this.cache.trackToBeGeneratedResource(ResourceType.ASSET, identifier.getNamespace(), "blockstates", identifier.getPath(), ".json");
        return this.blockStates.computeIfAbsent(block, o -> new BlockStateBuilder(this.modid, o));
    }

    @Override
    public String getName(){
        return this.modName + " Block State Generator";
    }

    protected class BlockStateBuilder {

        private final String modid;
        private final Block block;
        private final Map<PartialBlockState,VariantBuilder> variants = new LinkedHashMap<>();
        private final List<Pair<MultipartConditionBuilder,VariantBuilder>> multipartVariants = new ArrayList<>();

        public BlockStateBuilder(String modid, Block block){
            this.modid = modid;
            this.block = block;
        }

        /**
         * Constructs model options for a given variant.
         * @param state                  the variant
         * @param variantBuilderConsumer consumer to build the model options
         */
        public BlockStateBuilder variant(PartialBlockState state, Consumer<VariantBuilder> variantBuilderConsumer){
            if(state.block != this.block)
                throw new IllegalArgumentException("Cannot use state from block '" + state.block + "' in block state builder for block '" + this.block + "'!");

            variantBuilderConsumer.accept(this.variants.computeIfAbsent(state, o -> new VariantBuilder(this.modid)));
            return this;
        }

        /**
         * Constructs model options for a given variant.
         * @param state                  the variant
         * @param variantBuilderConsumer consumer to build the model options
         */
        public BlockStateBuilder variant(BlockState state, Consumer<VariantBuilder> variantBuilderConsumer){
            return this.variant(BlockStateGenerator.this.createPartialState(state), variantBuilderConsumer);
        }

        /**
         * Constructs model options for the variant with empty key.
         * @param variantBuilderConsumer consumer to build the model options
         */
        public BlockStateBuilder emptyVariant(Consumer<VariantBuilder> variantBuilderConsumer){
            return this.variant(BlockStateGenerator.this.createEmptyPartialState(this.block), variantBuilderConsumer);
        }

        /**
         * Constructs model options for all values of the given property.
         * @param property               property to iterate all values for
         * @param variantBuilderConsumer consumer to build the model options
         */
        public BlockStateBuilder variantsForProperty(Property<?> property, BiConsumer<PartialBlockState,VariantBuilder> variantBuilderConsumer){
            if(!this.block.getStateDefinition().getProperties().contains(property))
                throw new IllegalArgumentException("Property '" + property + "' is not a property of block '" + Registries.BLOCKS.getIdentifier(this.block) + "'!");

            PartialBlockStateBuilder builder = BlockStateGenerator.this.createPartialStateBuilder(this.block);
            for(Comparable<?> value : property.getPossibleValues()){
                //noinspection rawtypes,unchecked
                PartialBlockState state = builder.set((Property)property, (Comparable)value).build();
                variantBuilderConsumer.accept(state, this.variants.computeIfAbsent(state, o -> new VariantBuilder(this.modid)));
            }
            return this;
        }

        /**
         * Constructs model options for all possible variants except the given property.
         * @param variantBuilderConsumer consumer to build the model options
         * @param excluded               properties which should not be considered
         */
        public BlockStateBuilder variantsForAllExcept(BiConsumer<PartialBlockState,VariantBuilder> variantBuilderConsumer, Property<?>... excluded){
            PartialBlockStateBuilder builder = BlockStateGenerator.this.createPartialStateBuilder(this.block);
            List<Property<?>> properties = this.block.getStateDefinition().getProperties().stream().filter(property -> Arrays.stream(excluded).noneMatch(p -> p == property)).collect(Collectors.toList());
            this.loopThroughAll(builder, properties, 0, variantBuilderConsumer);
            return this;
        }

        private void loopThroughAll(PartialBlockStateBuilder builder, List<Property<?>> properties, int index, BiConsumer<PartialBlockState,VariantBuilder> variantBuilderConsumer){
            if(index == properties.size()){
                PartialBlockState state = builder.build();
                variantBuilderConsumer.accept(state, this.variants.computeIfAbsent(state, o -> new VariantBuilder(this.modid)));
                return;
            }

            Property<?> property = properties.get(index);
            for(Comparable<?> value : property.getPossibleValues()){
                //noinspection rawtypes,unchecked
                builder.set((Property)property, (Comparable)value);
                this.loopThroughAll(builder, properties, index + 1, variantBuilderConsumer);
            }
        }

        /**
         * Constructs model options for all possible variants.
         * @param variantBuilderConsumer consumer to build the model options
         */
        public BlockStateBuilder variantsForAll(BiConsumer<PartialBlockState,VariantBuilder> variantBuilderConsumer){
            return this.variantsForAllExcept(variantBuilderConsumer);
        }

        /**
         * Constructs a condition and model options which will be added whenever a state matches all properties in constructed condition.
         * @param conditionBuilderConsumer consumer to build the condition for the multipart
         * @param variantBuilderConsumer   consumer to build the model options
         */
        public BlockStateBuilder multipart(Consumer<MultipartConditionBuilder> conditionBuilderConsumer, Consumer<VariantBuilder> variantBuilderConsumer){
            MultipartConditionBuilder condition = new MultipartConditionBuilder(this.block);
            conditionBuilderConsumer.accept(condition);
            VariantBuilder variant = new VariantBuilder(this.modid);
            variantBuilderConsumer.accept(variant);
            this.multipartVariants.add(Pair.of(condition, variant));
            return this;
        }

        /**
         * Constructs model options which will be added whenever a state matches all properties in the given state.
         * @param state                  the properties to match
         * @param variantBuilderConsumer consumer to build the model options
         */
        public BlockStateBuilder multipart(PartialBlockState state, Consumer<VariantBuilder> variantBuilderConsumer){
            if(state.block != this.block)
                throw new IllegalArgumentException("Cannot use state from block '" + state.block + "' in block state builder for block '" + this.block + "'!");

            //noinspection unchecked,rawtypes
            return this.multipart(condition -> state.properties.forEach((property, value) -> condition.requireProperty((Property)property, (Comparable)value)), variantBuilderConsumer);
        }

        /**
         * Constructs model options which will be added whenever a state matches all properties in the given state.
         * @param state                  the properties to match
         * @param variantBuilderConsumer consumer to build the model options
         */
        public BlockStateBuilder multipart(BlockState state, Consumer<VariantBuilder> variantBuilderConsumer){
            return this.multipart(BlockStateGenerator.this.createPartialState(state), variantBuilderConsumer);
        }

        /**
         * Constructs model options which will be added to the regular variants.
         * @param variantBuilderConsumer consumer to build the model options
         */
        public BlockStateBuilder unconditionalMultipart(Consumer<VariantBuilder> variantBuilderConsumer){
            return this.multipart(BlockStateGenerator.this.createEmptyPartialState(this.block), variantBuilderConsumer);
        }
    }

    protected static class VariantBuilder {

        private final String modid;
        private final List<VariantModel> models = new ArrayList<>();

        protected VariantBuilder(String modid){
            this.modid = modid;
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param modelLocation location of the model
         * @param xRotation     rotation around the x-axis for the model
         * @param yRotation     rotation around the y-axis for the model
         * @param uvLock        whether to apply uv lock to the model
         * @param weight        weight of the model when considering which model to pick
         */
        public VariantBuilder model(ResourceLocation modelLocation, int xRotation, int yRotation, boolean uvLock, int weight){
            this.models.add(new VariantModel(modelLocation, xRotation, yRotation, uvLock, weight));
            return this;
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param namespace  namespace of the model
         * @param identifier path of the model
         * @param xRotation  rotation around the x-axis for the model
         * @param yRotation  rotation around the y-axis for the model
         * @param uvLock     whether to apply uv lock to the model
         * @param weight     weight of the model when considering which model to pick
         */
        public VariantBuilder model(String namespace, String identifier, int xRotation, int yRotation, boolean uvLock, int weight){
            return this.model(new ResourceLocation(namespace, identifier), xRotation, yRotation, uvLock, weight);
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param identifier path of the model
         * @param xRotation  rotation around the x-axis for the model
         * @param yRotation  rotation around the y-axis for the model
         * @param uvLock     whether to apply uv lock to the model
         * @param weight     weight of the model when considering which model to pick
         */
        public VariantBuilder model(String identifier, int xRotation, int yRotation, boolean uvLock, int weight){
            return this.model(this.modid, identifier, xRotation, yRotation, uvLock, weight);
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param modelLocation location of the model
         * @param xRotation     rotation around the x-axis for the model
         * @param yRotation     rotation around the y-axis for the model
         * @param uvLock        whether to apply uv lock to the model
         */
        public VariantBuilder model(ResourceLocation modelLocation, int xRotation, int yRotation, boolean uvLock){
            return this.model(modelLocation, xRotation, yRotation, uvLock, 1);
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param namespace  namespace of the model
         * @param identifier path of the model
         * @param xRotation  rotation around the x-axis for the model
         * @param yRotation  rotation around the y-axis for the model
         * @param uvLock     whether to apply uv lock to the model
         */
        public VariantBuilder model(String namespace, String identifier, int xRotation, int yRotation, boolean uvLock){
            return this.model(new ResourceLocation(namespace, identifier), xRotation, yRotation, uvLock);
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param identifier path of the model
         * @param xRotation  rotation around the x-axis for the model
         * @param yRotation  rotation around the y-axis for the model
         * @param uvLock     whether to apply uv lock to the model
         */
        public VariantBuilder model(String identifier, int xRotation, int yRotation, boolean uvLock){
            return this.model(this.modid, identifier, xRotation, yRotation, uvLock);
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param modelLocation location of the model
         * @param xRotation     rotation around the x-axis for the model
         * @param yRotation     rotation around the y-axis for the model
         */
        public VariantBuilder model(ResourceLocation modelLocation, int xRotation, int yRotation){
            return this.model(modelLocation, xRotation, yRotation, false, 1);
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param namespace  namespace of the model
         * @param identifier path of the model
         * @param xRotation  rotation around the x-axis for the model
         * @param yRotation  rotation around the y-axis for the model
         */
        public VariantBuilder model(String namespace, String identifier, int xRotation, int yRotation){
            return this.model(new ResourceLocation(namespace, identifier), xRotation, yRotation);
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param identifier path of the model
         * @param xRotation  rotation around the x-axis for the model
         * @param yRotation  rotation around the y-axis for the model
         */
        public VariantBuilder model(String identifier, int xRotation, int yRotation){
            return this.model(this.modid, identifier, xRotation, yRotation);
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param modelLocation location of the model
         */
        public VariantBuilder model(ResourceLocation modelLocation){
            return this.model(modelLocation, 0, 0, false, 1);
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param namespace  namespace of the model
         * @param identifier path of the model
         */
        public VariantBuilder model(String namespace, String identifier){
            return this.model(new ResourceLocation(namespace, identifier));
        }

        /**
         * Adds a model to the list of options for this variant.
         * @param identifier path of the model
         */
        public VariantBuilder model(String identifier){
            return this.model(this.modid, identifier);
        }
    }

    protected static class VariantModel {

        public final ResourceLocation modelLocation;
        public final int xRotation;
        public final int yRotation;
        public final boolean uvLock;
        public final int weight;

        public VariantModel(ResourceLocation modelLocation, int xRotation, int yRotation, boolean uvLock, int weight){
            this.modelLocation = modelLocation;
            this.xRotation = xRotation;
            this.yRotation = yRotation;
            this.uvLock = uvLock;
            this.weight = weight;
        }
    }

    protected static class MultipartConditionBuilder {

        private final Block block;
        private Map<Property<?>,Comparable<?>[]> properties = new HashMap<>();
        private final List<MultipartConditionBuilder> or = new ArrayList<>();

        private MultipartConditionBuilder(Block block){
            this.block = block;
        }

        /**
         * Adds required values for a property for this condition to be met.
         * @param property       property which should have any of the given values
         * @param acceptedValues values which the property may have for the condition to be met
         */
        public <T extends Comparable<T>> MultipartConditionBuilder requireProperty(Property<T> property, T... acceptedValues){
            if(acceptedValues.length == 0)
                throw new RuntimeException("Accepted values cannot be empty for multipart condition property!");
            if(this.properties.containsKey(property))
                throw new RuntimeException("Duplicate requirements for property '" + property + "' for multipart condition for block '" + Registries.BLOCKS.getIdentifier(this.block) + "'!");
            if(!this.block.getStateDefinition().getProperties().contains(property))
                throw new IllegalArgumentException("Property '" + property + "' is not a property of block '" + Registries.BLOCKS.getIdentifier(this.block) + "'!");
            for(T value : acceptedValues){
                if(!property.getPossibleValues().contains(value))
                    throw new IllegalArgumentException("Value '" + value + "' does not belong to property '" + property + "'!");
            }

            this.properties.put(property, acceptedValues);
            return this;
        }

        /**
         * Adds an alternative condition to this one.
         * @param alternativeBuilderConsumer consumer to construct the alternative condition
         */
        public MultipartConditionBuilder or(Consumer<MultipartConditionBuilder> alternativeBuilderConsumer){
            MultipartConditionBuilder builder = new MultipartConditionBuilder(this.block);
            alternativeBuilderConsumer.accept(builder);
            if(builder.properties.isEmpty())
                throw new IllegalArgumentException("Alternative condition cannot be empty!");
            this.or.add(builder);
            return this;
        }

        /**
         * Constructs an alternative condition to this one.
         * @return builder for the alternative condition
         */
        public MultipartConditionBuilder or(){
            MultipartConditionBuilder builder = new MultipartConditionBuilder(this.block);
            this.or.add(builder);
            return builder;
        }

        private void flatten(){
            this.or.add(0, this);
            for(int i = 1; i < this.or.size(); i++)
                this.or.addAll(this.or.get(i).or);
            for(MultipartConditionBuilder b : this.or){
                b.properties = Collections.unmodifiableMap(
                    b.properties.entrySet().stream()
                        .sorted(Comparator.comparing(e -> e.getKey().getName()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (p1, p2) -> p1, LinkedHashMap::new))
                );
            }
            this.or.sort(Comparator.comparing(c -> c.properties, BlockStateGenerator::compareMultiProperties));
        }
    }

    protected static class PartialBlockState implements Comparable<PartialBlockState> {

        private final Block block;
        private final Map<Property<?>,Comparable<?>> properties;

        protected PartialBlockState(Block block, Map<Property<?>,Comparable<?>> properties){
            this.block = block;
            this.properties = Collections.unmodifiableMap(
                properties.entrySet().stream()
                    .sorted(Comparator.comparing(e -> e.getKey().getName()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (p1, p2) -> p1, LinkedHashMap::new))
            );
        }

        /**
         * Gets the block this partial state is for.
         * @return parent block of this partial state
         */
        public Block getBlock(){
            return this.block;
        }

        /**
         * Checks whether the given property is set on this partial state.
         * @param property property to be checked
         */
        public boolean has(Property<?> property){
            return this.properties.containsKey(property);
        }

        /**
         * Gets the value of the given property in this partial state.
         * @param property property to get
         * @return this state's value for the given property
         * @throws IllegalArgumentException when the given property is not part of this state's parent block
         */
        public <T extends Comparable<T>> T get(Property<T> property){
            if(!this.block.getStateDefinition().getProperties().contains(property))
                throw new IllegalArgumentException("Property '" + property + "' is not a property of block '" + Registries.BLOCKS.getIdentifier(this.block) + "'!");

            //noinspection unchecked
            return (T)this.properties.get(property);
        }

        @Override
        public int compareTo(BlockStateGenerator.PartialBlockState o){
            if(this.block != o.block){
                ResourceLocation identifier = Registries.BLOCKS.getIdentifier(this.block);
                ResourceLocation otherIdentifier = Registries.BLOCKS.getIdentifier(o.block);
                return identifier.compareTo(otherIdentifier);
            }
            return compareProperties(this.properties, o.properties);
        }
    }

    protected static class PartialBlockStateBuilder {

        private final Block block;
        private final Map<Property<?>,Comparable<?>> properties = new HashMap<>();

        protected PartialBlockStateBuilder(Block block){
            this.block = block;
        }

        public Block getBlock(){
            return this.block;
        }

        /**
         * Sets the value for the given property on this partial state.
         * @param property property to be set
         * @param value    value of the property
         * @throws IllegalArgumentException when the given property is not part of this state's parent block
         * @throws IllegalArgumentException when the given value is not a valid value for the given property
         */
        public <T extends Comparable<T>> PartialBlockStateBuilder set(Property<T> property, T value){
            if(!this.block.getStateDefinition().getProperties().contains(property))
                throw new IllegalArgumentException("Property '" + property + "' is not a property of block '" + Registries.BLOCKS.getIdentifier(this.block) + "'!");
            if(!property.getPossibleValues().contains(value))
                throw new IllegalArgumentException("Value '" + value + "' does not belong to property '" + property + "'!");

            this.properties.put(property, value);
            return this;
        }

        /**
         * Copies all properties from the given state to this partial state.
         * @param state state to copy properties from
         */
        public PartialBlockStateBuilder copy(BlockState state){
            if(this.block != state.getBlock())
                throw new IllegalArgumentException("Cannot copy properties of state for block '" + Registries.BLOCKS.getIdentifier(state.getBlock()) + "' to block '" + Registries.BLOCKS.getIdentifier(this.block) + "'!");

            this.properties.putAll(state.getValues());
            return this;
        }

        /**
         * Checks whether the given property is set on this partial state.
         * @param property property to be checked
         */
        public boolean has(Property<?> property){
            return this.properties.containsKey(property);
        }

        /**
         * Gets the value of the given property in this partial state.
         * @param property property to get
         * @return this state's value for the given property
         * @throws IllegalArgumentException when the given property is not part of this state's parent block
         */
        public <T extends Comparable<T>> T get(Property<T> property){
            if(!this.block.getStateDefinition().getProperties().contains(property))
                throw new IllegalArgumentException("Property '" + property + "' is not a property of block '" + Registries.BLOCKS.getIdentifier(this.block) + "'!");

            //noinspection unchecked
            return (T)this.properties.get(property);
        }

        /**
         * Builds this partial state builder.
         * @return a partial state with this builder's properties
         */
        public PartialBlockState build(){
            return new PartialBlockState(this.block, this.properties);
        }
    }

    private static int compareProperties(Map<Property<?>,Comparable<?>> properties1, Map<Property<?>,Comparable<?>> properties2){
        if(properties1.size() != properties2.size())
            return properties1.size() - properties2.size();
        Iterator<Map.Entry<Property<?>,Comparable<?>>> iterator1 = properties1.entrySet().iterator();
        Iterator<Map.Entry<Property<?>,Comparable<?>>> iterator2 = properties2.entrySet().iterator();
        while(iterator1.hasNext() && iterator2.hasNext()){
            Map.Entry<Property<?>,Comparable<?>> entry1 = iterator1.next();
            Map.Entry<Property<?>,Comparable<?>> entry2 = iterator2.next();
            Property<?> property1 = entry1.getKey();
            Property<?> property2 = entry2.getKey();
            if(property1 != property2)
                return property1.getName().compareTo(property2.getName());
            Comparable<?> value1 = entry1.getValue();
            Comparable<?> value2 = entry2.getValue();
            if(!value1.equals(value2))
                //noinspection unchecked,rawtypes
                return ((Property)property1).getName(value1).compareTo(((Property)property2).getName(value2));
        }
        if(iterator1.hasNext() || iterator2.hasNext())
            throw new AssertionError();
        return 0;
    }

    private static int compareMultiProperties(Map<Property<?>,Comparable<?>[]> properties1, Map<Property<?>,Comparable<?>[]> properties2){
        if(properties1.size() != properties2.size())
            return properties1.size() - properties2.size();
        Iterator<Map.Entry<Property<?>,Comparable<?>[]>> iterator1 = properties1.entrySet().iterator();
        Iterator<Map.Entry<Property<?>,Comparable<?>[]>> iterator2 = properties2.entrySet().iterator();
        while(iterator1.hasNext() && iterator2.hasNext()){
            Map.Entry<Property<?>,Comparable<?>[]> entry1 = iterator1.next();
            Map.Entry<Property<?>,Comparable<?>[]> entry2 = iterator2.next();
            Property<?> property1 = entry1.getKey();
            Property<?> property2 = entry2.getKey();
            if(property1 != property2)
                return property1.getName().compareTo(property2.getName());
            Comparable<?>[] values1 = entry1.getValue();
            Comparable<?>[] values2 = entry2.getValue();
            if(values1.length != values2.length)
                return values1.length - values2.length;
            for(int i = 0; i < values1.length; i++){
                //noinspection unchecked,rawtypes
                String name1 = ((Property)property1).getName(values1[i]);
                //noinspection unchecked,rawtypes
                String name2 = ((Property)property2).getName(values2[i]);
                if(!name1.equals(name2))
                    return name1.compareTo(name2);
            }
        }
        if(iterator1.hasNext() || iterator2.hasNext())
            throw new AssertionError();
        return 0;
    }
}
```

### src/main/java/com/supermartijn642/core/generator/LanguageGenerator.java

```java
package com.supermartijn642.core.generator;

import com.supermartijn642.core.generator.aggregator.TranslationsAggregator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created 04/08/2022 by SuperMartijn642
 */
public abstract class LanguageGenerator extends ResourceGenerator {

    private final Map<String,String> translations = new LinkedHashMap<>();
    protected final String langCode;

    public LanguageGenerator(String modid, ResourceCache cache, String langCode){
        super(modid, cache);
        if(!langCode.matches("[a-z]{2}_[a-z]{2}"))
            throw new IllegalArgumentException("Invalid lang code '" + langCode + "'!");
        this.langCode = langCode;
    }

    @Override
    public void save(){
        // Save the object to the cache
        this.cache.saveResource(ResourceType.ASSET, TranslationsAggregator.INSTANCE, this.translations, this.modid, "lang", this.langCode, ".json");
    }

    /**
     * Adds the given translation.
     * @param translationKey key for the translation
     * @param translation    the translation
     */
    protected void translation(String translationKey, String translation){
        if(translationKey.trim().isEmpty())
            throw new IllegalArgumentException("Translation key '" + translation + "' for translation '" + translation + "' must not be empty!");

        this.translations.put(translationKey, translation);
    }

    /**
     * Adds the given translation for the item group.
     * @param group       group to add the translation for
     * @param translation translation of the group name
     */
    protected void itemGroup(ItemGroup group, String translation){
        ITextComponent component = group.getDisplayName();
        if(component instanceof TranslationTextComponent)
            this.translation(((TranslationTextComponent)component).getKey(), translation);
        this.translation(group.langId, translation);
    }

    /**
     * Adds the given translation for the item.
     * @param item        item to add the translation for
     * @param translation translation of the item name
     */
    protected void item(Item item, String translation){
        this.translation(item.getDescriptionId(), translation);
    }

    /**
     * Adds the given translation for the item.
     * @param block       block to add the translation for
     * @param translation translation of the block name
     */
    protected void block(Block block, String translation){
        this.translation(block.getDescriptionId(), translation);
    }

    public String getName(){
        return this.modName + " Language Generator";
    }
}
```

### src/main/java/com/supermartijn642/core/generator/LootTableGenerator.java

```java
package com.supermartijn642.core.generator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.core.registry.Registries;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.loot.functions.EnchantWithLevels;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created 20/08/2022 by SuperMartijn642
 */
public abstract class LootTableGenerator extends ResourceGenerator {

    private static final Gson GSON = LootSerializers.createLootTableSerializer().create();

    private final Map<ResourceLocation,LootTableBuilder> lootTables = new HashMap<>();

    public LootTableGenerator(String modid, ResourceCache cache){
        super(modid, cache);
    }

    @Override
    public void save(){
        // Loop over all loot tables
        for(LootTableBuilder lootTableBuilder : this.lootTables.values()){
            JsonObject json = new JsonObject();
            // Type
            if(lootTableBuilder.parameters != LootParameterSets.ALL_PARAMS)
                json.addProperty("type", LootParameterSets.getKey(lootTableBuilder.parameters).toString());
            // Functions
            if(!lootTableBuilder.functions.isEmpty()){
                JsonArray functionsJson = new JsonArray();
                for(ILootFunction function : lootTableBuilder.functions)
                    functionsJson.add(GSON.toJsonTree(function));
                json.add("functions", functionsJson);
            }
            // Pools
            if(!lootTableBuilder.pools.isEmpty()){
                JsonArray poolsJson = new JsonArray();
                // Loop over all pools
                for(LootPoolBuilder pool : lootTableBuilder.pools){
                    JsonObject poolJson = new JsonObject();
                    // Name
                    if(pool.name != null && !pool.name.isEmpty())
                        poolJson.addProperty("name", pool.name);
                    // Rolls
                    poolJson.add("rolls", GSON.toJsonTree(pool.rolls));
                    // Bonus rolls
                    if(!(pool.bonusRolls instanceof ConstantRange) || pool.bonusRolls.getInt(null) != 0)
                        poolJson.add("bonus_rolls", GSON.toJsonTree(pool.bonusRolls));
                    // Conditions
                    if(!pool.conditions.isEmpty()){
                        JsonArray conditionsJson = new JsonArray();
                        for(ILootCondition condition : pool.conditions)
                            conditionsJson.add(GSON.toJsonTree(condition));
                        poolJson.add("conditions", conditionsJson);
                    }
                    // Functions
                    if(!pool.functions.isEmpty()){
                        JsonArray functionsJson = new JsonArray();
                        for(ILootFunction function : pool.functions)
                            functionsJson.add(GSON.toJsonTree(function));
                        poolJson.add("functions", functionsJson);
                    }
                    // Entries
                    if(pool.entries.isEmpty())
                        throw new RuntimeException("Loot table '" + lootTableBuilder.identifier + "' has loot pool without any entries!");
                    JsonArray entriesJson = new JsonArray();
                    for(LootEntry entry : pool.entries)
                        entriesJson.add(GSON.toJsonTree(entry));
                    poolJson.add("entries", entriesJson);

                    poolsJson.add(poolJson);
                }
                json.add("pools", poolsJson);
            }

            // Save the object to the cache
            ResourceLocation identifier = lootTableBuilder.identifier;
            this.cache.saveJsonResource(ResourceType.DATA, json, identifier.getNamespace(), "loot_tables", identifier.getPath());
        }
    }

    /**
     * Gets a loot table builder for the given identifier. The returned loot table builder may be a new loot table builder or an existing one if requested before.
     * @param identifier resource location of the loot table
     */
    protected LootTableBuilder lootTable(ResourceLocation identifier){
        this.cache.trackToBeGeneratedResource(ResourceType.DATA, identifier.getNamespace(), "loot_tables", identifier.getPath(), ".json");
        return this.lootTables.computeIfAbsent(identifier, LootTableBuilder::new);
    }

    /**
     * Gets a loot table builder for the given namespace and path. The returned loot table builder may be a new loot table builder or an existing one if requested before.
     * @param namespace namespace of the loot table
     * @param path      path of the loot table
     */
    protected LootTableBuilder lootTable(String namespace, String path){
        return this.lootTable(new ResourceLocation(namespace, path));
    }

    /**
     * Gets a loot table builder for the given block. The returned loot table builder may be a new loot table builder or an existing one if requested before.
     * @param block block to create the loot table for
     */
    protected LootTableBuilder lootTable(Block block){
        return this.lootTable(block.getLootTable());
    }

    /**
     * Creates a basic loot table for the given block to drop itself when broken.
     * @param block block to create the loot table for
     */
    protected LootTableBuilder dropSelf(Block block){
        return this.lootTable(block).blockParameters().pool(poolBuilder -> poolBuilder.survivesExplosionCondition().itemEntry(block));
    }

    /**
     * Creates a basic loot table for the given to drop itself when broken with a silk touch tool.
     * @param block block to create the loot table for
     */
    protected LootTableBuilder dropSelfWhenSilkTouch(Block block){
        return this.lootTable(block).blockParameters().pool(poolBuilder -> poolBuilder.hasEnchantmentCondition(Enchantments.SILK_TOUCH).itemEntry(block));
    }

    @Override
    public String getName(){
        return this.modName + " Loot Table Generator";
    }

    public static class LootTableBuilder {

        protected final ResourceLocation identifier;
        private final List<LootPoolBuilder> pools = new ArrayList<>();
        private final List<ILootFunction> functions = new ArrayList<>();
        private LootParameterSet parameters = LootParameterSets.ALL_PARAMS;

        protected LootTableBuilder(ResourceLocation identifier){
            this.identifier = identifier;
        }

        /**
         * Sets the loot table type to the given parameter set.
         */
        public LootTableBuilder parameters(LootParameterSet parameters){
            if(LootParameterSets.getKey(parameters) == null)
                throw new IllegalArgumentException("Cannot use unregistered parameter set '" + parameters + "'!");

            this.parameters = parameters;
            return this;
        }

        /**
         * Sets the loot table type to the block parameter set.
         */
        public LootTableBuilder blockParameters(){
            return this.parameters(LootParameterSets.BLOCK);
        }

        /**
         * Sets the loot table type to the chest parameter set.
         */
        public LootTableBuilder chestParameters(){
            return this.parameters(LootParameterSets.CHEST);
        }

        /**
         * Constructs a new loot pool for this loot table.
         * @param poolBuilderConsumer consumer to build the loot pool
         */
        public LootTableBuilder pool(Consumer<LootPoolBuilder> poolBuilderConsumer){
            LootPoolBuilder poolBuilder = new LootPoolBuilder();
            poolBuilderConsumer.accept(poolBuilder);
            this.pools.add(poolBuilder);
            return this;
        }

        /**
         * Adds the given item function to this loot table.
         * @param function item function to be added
         */
        public LootTableBuilder function(ILootFunction function){
            if(Registry.LOOT_FUNCTION_TYPE.getKey(function.getType()) == null)
                throw new IllegalArgumentException("Cannot use unregistered item function '" + function + "'!");

            this.functions.add(function);
            return this;
        }
    }

    public static class LootPoolBuilder {

        private final List<ILootCondition> conditions = new ArrayList<>();
        private final List<ILootFunction> functions = new ArrayList<>();
        private final List<LootEntry> entries = new ArrayList<>();
        private IRandomRange rolls = ConstantRange.exactly(1);
        private IRandomRange bonusRolls = ConstantRange.exactly(0);
        private String name;

        protected LootPoolBuilder(){
        }

        /**
         * Sets the number provider for the number of rolls for this loot pool.
         * @param provider number provider for number of rolls
         */
        public LootPoolBuilder rolls(IRandomRange provider){
            this.rolls = provider;
            return this;
        }

        /**
         * Sets the number provider for the number of rolls to a constant with the given value.
         * @param rolls number of rolls
         */
        public LootPoolBuilder constantRolls(int rolls){
            return this.rolls(ConstantRange.exactly(rolls));
        }

        /**
         * Sets the number provider for the number of rolls to a uniform chance between the given minimum and maximum.
         * @param min minimum number of rolls
         * @param max maximum number of rolls
         */
        public LootPoolBuilder uniformRolls(int min, int max){
            return this.rolls(RandomValueRange.between(min, max));
        }

        /**
         * Sets the number provider for the number of rolls to a binomial distribution with the given chance and attempts.
         * @param n number of attempts
         * @param p chance that an attempt succeeds
         */
        public LootPoolBuilder binomialRolls(int n, int p){
            return this.rolls(BinomialRange.binomial(n, p));
        }

        /**
         * Sets the number provider for the number of bonus rolls for this loot pool.
         * @param provider number provider for number of bonus rolls
         */
        public LootPoolBuilder bonusRolls(IRandomRange provider){
            this.bonusRolls = provider;
            return this;
        }

        /**
         * Sets the number provider for the number of bonus rolls to a constant with the given value.
         * @param rolls number of bonus rolls
         */
        public LootPoolBuilder constantBonusRolls(int rolls){
            return this.bonusRolls(ConstantRange.exactly(rolls));
        }

        /**
         * Sets the number provider for the number of bonus rolls to a uniform chance between the given minimum and maximum.
         * @param min minimum number of bonus rolls
         * @param max maximum number of bonus rolls
         */
        public LootPoolBuilder uniformBonusRolls(int min, int max){
            return this.bonusRolls(RandomValueRange.between(min, max));
        }

        /**
         * Sets the number provider for the number of bonus rolls to a binomial distribution with the given chance and attempts.
         * @param n number of attempts
         * @param p chance that an attempt succeeds
         */
        public LootPoolBuilder binomialBonusRolls(int n, int p){
            return this.bonusRolls(BinomialRange.binomial(n, p));
        }

        /**
         * Sets the name for this loot pool.
         * @param name name for the pool
         */
        public LootPoolBuilder name(String name){
            this.name = name;
            return this;
        }

        /**
         * Adds the given item condition to this loot pool.
         * @param condition condition to be added
         */
        public LootPoolBuilder condition(ILootCondition condition){
            if(Registry.LOOT_CONDITION_TYPE.getKey(condition.getType()) == null)
                throw new IllegalArgumentException("Cannot use unregistered loot pool condition '" + condition + "'!");

            this.conditions.add(condition);
            return this;
        }

        /**
         * Adds a survives explosion condition to this loot pool
         */
        public LootPoolBuilder survivesExplosionCondition(){
            return this.condition(SurvivesExplosion.survivesExplosion().build());
        }

        /**
         * Adds a condition for the used tool to have the given enchantment.
         * @param enchantment enchantment required
         * @param minLevel    minimum level of the enchantment (inclusive)
         * @param maxLevel    maximum level of the enchantment (inclusive)
         */
        public LootPoolBuilder hasEnchantmentCondition(Enchantment enchantment, int minLevel, int maxLevel){
            return this.condition(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(enchantment, new MinMaxBounds.IntBound(minLevel, maxLevel)))).build());
        }

        /**
         * Adds a condition for the used tool to have the given enchantment.
         * @param enchantment enchantment required
         * @param minLevel    minimum level of the enchantment
         */
        public LootPoolBuilder hasEnchantmentCondition(Enchantment enchantment, int minLevel){
            return this.condition(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(enchantment, MinMaxBounds.IntBound.atLeast(minLevel)))).build());
        }

        /**
         * Adds a condition for the used tool to have the given enchantment.
         * @param enchantment enchantment required
         */
        public LootPoolBuilder hasEnchantmentCondition(Enchantment enchantment){
            return this.hasEnchantmentCondition(enchantment, 1);
        }

        /**
         * Adds an entry to this loot pool.
         * @param entry entry to be added
         */
        public LootPoolBuilder entry(LootEntry entry){
            if(Registry.LOOT_POOL_ENTRY_TYPE.getKey(entry.getType()) == null)
                throw new IllegalArgumentException("Cannot use unregistered loot pool entry '" + entry + "'!");

            this.entries.add(entry);
            return this;
        }

        private LootPoolBuilder entry(StandaloneLootEntry.Builder<?> entry, int weight){
            if(weight <= 0)
                throw new IllegalArgumentException("Loot entry weight must be greater than zero, not '" + weight + "'!");

            return this.entry(entry.setWeight(weight).build());
        }

        /**
         * Adds an empty entry to this loot pool.
         * @param weight weight of the entry
         */
        public LootPoolBuilder emptyEntry(int weight){
            return this.entry(EmptyLootEntry.emptyItem(), weight);
        }

        /**
         * Adds an empty entry to this loot pool.
         */
        public LootPoolBuilder emptyEntry(){
            return this.emptyEntry(1);
        }

        /**
         * Adds an item entry to this loot pool.
         * @param item   item to be added as an entry
         * @param weight weight of the entry
         */
        public LootPoolBuilder itemEntry(IItemProvider item, int weight){
            return this.entry(ItemLootEntry.lootTableItem(item), weight);
        }

        /**
         * Adds an item entry to this loot pool.
         * @param item item to be added as an entry
         */
        public LootPoolBuilder itemEntry(IItemProvider item){
            return this.itemEntry(item, 1);
        }

        /**
         * Adds an item entry to this loot pool.
         * @param item   item to be added as an entry
         * @param count  the number of items in the item stack
         * @param weight weight of the entry
         */
        public LootPoolBuilder itemEntry(IItemProvider item, int count, int weight){
            return this.entry(ItemLootEntry.lootTableItem(item).apply(SetCount.setCount(ConstantRange.exactly(count))), weight);
        }

        /**
         * Adds an item entry to this loot pool.
         * @param item   item to be added as an entry
         * @param min    the minimum size of the item stack
         * @param max    the maximum size of the item stack
         * @param weight weight of the entry
         */
        public LootPoolBuilder itemEntry(IItemProvider item, int min, int max, int weight){
            return this.entry(ItemLootEntry.lootTableItem(item).apply(SetCount.setCount(RandomValueRange.between(min, max))), weight);
        }

        /**
         * Adds an item entry to this loot pool.
         * @param item item to be added as an entry
         */
        public LootPoolBuilder itemEntry(ResourceLocation item){
            if(!Registries.ITEMS.hasIdentifier(item))
                throw new IllegalArgumentException("Could not find any item registered under '" + item + "'!");

            return this.itemEntry(Registries.ITEMS.getValue(item));
        }

        /**
         * Adds an item entry to this loot pool.
         * @param namespace  namespace of the item to be added as an entry
         * @param identifier path of the item to be added as an entry
         */
        public LootPoolBuilder itemEntry(String namespace, String identifier){
            return this.itemEntry(new ResourceLocation(namespace, identifier));
        }

        /**
         * Adds an item entry which will be enchanted.
         * @param item        item to be enchanted
         * @param levels      the number of levels the item will be enchanted with
         * @param allowCurses whether the items may be enchanted with curses
         * @param weight      weight of the entry
         */
        public LootPoolBuilder enchantedItemEntry(IItemProvider item, int levels, boolean allowCurses, int weight){
            EnchantWithLevels.Builder builder = EnchantWithLevels.enchantWithLevels(ConstantRange.exactly(levels));
            if(allowCurses)
                builder.allowTreasure();
            return this.entry(ItemLootEntry.lootTableItem(item).apply(builder), weight);
        }

        /**
         * Adds an item entry which will be enchanted.
         * @param item        item to be enchanted
         * @param minLevels   the minimum number of levels the item will be enchanted with
         * @param maxLevels   the maximum number of levels the item will be enchanted with
         * @param allowCurses whether the items may be enchanted with curses
         * @param weight      weight of the entry
         */
        public LootPoolBuilder enchantedItemEntry(IItemProvider item, int minLevels, int maxLevels, boolean allowCurses, int weight){
            EnchantWithLevels.Builder builder = EnchantWithLevels.enchantWithLevels(RandomValueRange.between(minLevels, maxLevels));
            if(allowCurses)
                builder.allowTreasure();
            return this.entry(ItemLootEntry.lootTableItem(item).apply(builder), weight);
        }

        /**
         * Adds a tag entry to this loot pool.
         * @param tagKey tag to be added as an entry
         * @param weight weight of the entry
         */
        public LootPoolBuilder tagEntry(ITag<Item> tagKey, int weight){
            return this.entry(TagLootEntry.expandTag(tagKey), weight);
        }

        /**
         * Adds a tag entry to this loot pool.
         * @param tag tag to be added as an entry
         */
        public LootPoolBuilder tagEntry(ITag<Item> tag){
            return this.entry(TagLootEntry.expandTag(tag).build());
        }

        /**
         * Adds a tag entry to this loot pool.
         * @param tag    tag to be added as an entry
         * @param weight weight of the entry
         */
        public LootPoolBuilder tagEntry(ResourceLocation tag, int weight){
            return this.tagEntry(ItemTags.createOptional(tag), weight);
        }

        /**
         * Adds a tag entry to this loot pool.
         * @param tag tag to be added as an entry
         */
        public LootPoolBuilder tagEntry(ResourceLocation tag){
            return this.tagEntry(ItemTags.createOptional(tag));
        }

        /**
         * Adds a tag entry to this loot pool.
         * @param namespace namespace of the tag to be added as an entry
         * @param path      path of the tag to be added as an entry
         * @param weight    weight of the entry
         */
        public LootPoolBuilder tagEntry(String namespace, String path, int weight){
            return this.tagEntry(new ResourceLocation(namespace, path), weight);
        }

        /**
         * Adds a tag entry to this loot pool.
         * @param namespace namespace of the tag to be added as an entry
         * @param path      path of the tag to be added as an entry
         */
        public LootPoolBuilder tagEntry(String namespace, String path){
            return this.tagEntry(new ResourceLocation(namespace, path));
        }

        /**
         * Adds a loot table entry to this loot pool.
         * @param lootTable loot table to be added as an entry
         * @param weight    weight of the entry
         */
        public LootPoolBuilder lootTableEntry(ResourceLocation lootTable, int weight){
            return this.entry(TableLootEntry.lootTableReference(lootTable), weight);
        }

        /**
         * Adds a loot table entry to this loot pool.
         * @param lootTable loot table to be added as an entry
         */
        public LootPoolBuilder lootTableEntry(ResourceLocation lootTable){
            return this.lootTableEntry(lootTable, 1);
        }

        /**
         * Adds a loot table entry to this loot pool.
         * @param namespace namespace of the loot table to be added as an entry
         * @param path      path of the loot table to be added as an entry
         * @param weight    weight of the entry
         */
        public LootPoolBuilder lootTableEntry(String namespace, String path, int weight){
            return this.lootTableEntry(new ResourceLocation(namespace, path), weight);
        }

        /**
         * Adds a loot table entry to this loot pool.
         * @param namespace namespace of the loot table to be added as an entry
         * @param path      path of the loot table to be added as an entry
         */
        public LootPoolBuilder lootTableEntry(String namespace, String path){
            return this.lootTableEntry(new ResourceLocation(namespace, path));
        }

        /**
         * Adds an item function to this loot table.
         * @param function item function to be added
         */
        public LootPoolBuilder function(ILootFunction function){
            if(Registry.LOOT_FUNCTION_TYPE.getKey(function.getType()) == null)
                throw new IllegalArgumentException("Cannot use unregistered item function '" + function + "'!");

            this.functions.add(function);
            return this;
        }
    }
}
```

### src/main/java/com/supermartijn642/core/generator/ModelGenerator.java

```java
package com.supermartijn642.core.generator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.core.registry.Registries;
import com.supermartijn642.core.registry.RegistryUtil;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Created 18/08/2022 by SuperMartijn642
 */
public abstract class ModelGenerator extends ResourceGenerator {

    private final Map<ResourceLocation,ModelBuilder> models = new HashMap<>();

    public ModelGenerator(String modid, ResourceCache cache){
        super(modid, cache);
    }

    @Override
    public void save(){
        // Loop over all models
        for(ModelBuilder modelBuilder : this.models.values()){
            JsonObject json = this.convertToJson(modelBuilder);

            // Save the object to the cache
            ResourceLocation identifier = modelBuilder.identifier;
            this.cache.saveJsonResource(ResourceType.ASSET, json, identifier.getNamespace(), "models", identifier.getPath());
        }
    }

    protected JsonObject convertToJson(ModelBuilder modelBuilder){
        JsonObject json = new JsonObject();

        // Parent model
        ResourceLocation parentModel = modelBuilder.parent;
        if(parentModel != null){
            if(!this.models.containsKey(parentModel) && !this.cache.doesResourceExist(ResourceType.ASSET, parentModel.getNamespace(), "models", parentModel.getPath(), ".json"))
                throw new RuntimeException("Could find parent model '" + parentModel + "' for model '" + modelBuilder.identifier + "'!");
            json.addProperty("parent", parentModel.toString());
        }
        // Render type
        if(modelBuilder.renderType != null)
            json.addProperty("render_type", modelBuilder.renderType.toString());
        // Ambient occlusion
        if(!modelBuilder.ambientOcclusion)
            json.addProperty("ambientocclusion", false);
        // Transforms
        if(!modelBuilder.transforms.isEmpty()){
            JsonObject displayJson = new JsonObject();
            // Add each transform
            for(Map.Entry<ItemCameraTransforms.TransformType,TransformBuilder> transform : modelBuilder.transforms.entrySet()){
                JsonObject transformJson = new JsonObject();
                transformJson.add("rotation", createJsonArray(transform.getValue().rotation.x(), transform.getValue().rotation.y(), transform.getValue().rotation.z()));
                transformJson.add("translation", createJsonArray(transform.getValue().translation.x(), transform.getValue().translation.y(), transform.getValue().translation.z()));
                transformJson.add("scale", createJsonArray(transform.getValue().scale.x(), transform.getValue().scale.y(), transform.getValue().scale.z()));
                String transformName = "unknown";
                if(transform.getKey() == ItemCameraTransforms.TransformType.NONE)
                    transformName = "none";
                else if(transform.getKey() == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
                    transformName = "thirdperson_lefthand";
                else if(transform.getKey() == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
                    transformName = "thirdperson_righthand";
                else if(transform.getKey() == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
                    transformName = "firstperson_lefthand";
                else if(transform.getKey() == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
                    transformName = "firstperson_righthand";
                else if(transform.getKey() == ItemCameraTransforms.TransformType.HEAD)
                    transformName = "head";
                else if(transform.getKey() == ItemCameraTransforms.TransformType.GUI)
                    transformName = "gui";
                else if(transform.getKey() == ItemCameraTransforms.TransformType.GROUND)
                    transformName = "ground";
                else if(transform.getKey() == ItemCameraTransforms.TransformType.FIXED)
                    transformName = "fixed";
                displayJson.add(transformName, transformJson);
            }
            json.add("display", displayJson);
        }
        // Textures
        if(!modelBuilder.textures.isEmpty()){
            JsonObject texturesJson = new JsonObject();
            for(Map.Entry<String,String> entry : modelBuilder.textures.entrySet()){
                // Validate the texture exists
                if(entry.getValue().charAt(0) != '#'){
                    ResourceLocation texture = new ResourceLocation(entry.getValue());
                    if(!this.cache.doesResourceExist(ResourceType.ASSET, texture.getNamespace(), "textures", texture.getPath(), ".png"))
                        throw new IllegalArgumentException("Could not find texture '" + texture + "' for model '" + modelBuilder.identifier + "'!");
                }

                texturesJson.addProperty(entry.getKey(), entry.getValue());
            }
            json.add("textures", texturesJson);
        }
        // Gui lighting
        if(modelBuilder.lighting != null)
            json.addProperty("gui_light", modelBuilder.lighting.getSerializedName());
        // Elements
        if(!modelBuilder.elements.isEmpty()){
            JsonArray elementsJson = new JsonArray();
            // Loop over the individual elements
            for(ElementBuilder element : modelBuilder.elements){
                JsonObject elementJson = new JsonObject();
                // From & to
                elementJson.add("from", createJsonArray(element.from.x(), element.from.y(), element.from.z()));
                elementJson.add("to", createJsonArray(element.to.x(), element.to.y(), element.to.z()));
                // Rotation
                if(element.rotation != null){
                    JsonObject rotationJson = new JsonObject();
                    rotationJson.add("origin", createJsonArray(element.rotation.origin.x(), element.rotation.origin.y(), element.rotation.origin.z()));
                    rotationJson.addProperty("axis", element.rotation.axis.getSerializedName());
                    rotationJson.addProperty("angle", element.rotation.angle);
                    rotationJson.addProperty("rescale", element.rotation.rescale);
                    elementJson.add("rotation", rotationJson);
                }
                // Shade
                if(!element.shading)
                    elementJson.addProperty("shade", false);
                // Faces
                if(!element.faces.isEmpty()){
                    JsonObject facesJson = new JsonObject();
                    for(Map.Entry<Direction,FaceBuilder> entry : element.faces.entrySet()){
                        JsonObject faceJson = new JsonObject();
                        if(entry.getValue().texture == null)
                            throw new RuntimeException("Model '" + modelBuilder.identifier + "' has face without a texture!");
                        faceJson.addProperty("texture", entry.getValue().texture);
                        if(entry.getValue().uv != null)
                            faceJson.add("uv", createJsonArray(entry.getValue().uv));
                        if(entry.getValue().cullface != null)
                            faceJson.addProperty("cullface", entry.getValue().cullface.getSerializedName());
                        if(entry.getValue().emissivity != 0)
                            faceJson.addProperty("emissivity", entry.getValue().emissivity);
                        if(entry.getValue().rotation != 0)
                            faceJson.addProperty("rotation", entry.getValue().rotation);
                        if(entry.getValue().tintIndex != -1)
                            faceJson.addProperty("tintindex", entry.getValue().tintIndex);
                        facesJson.add(entry.getKey().getSerializedName(), faceJson);
                    }
                    elementJson.add("faces", facesJson);
                }else
                    throw new RuntimeException("Element in model '" + modelBuilder.identifier + "' has no faces!");
                elementsJson.add(elementJson);
            }
            json.add("elements", elementsJson);
        }

        return json;
    }

    private static JsonArray createJsonArray(float... elements){
        // Because they can't just make a proper json array constructor...
        JsonArray array = new JsonArray();
        for(Number element : elements)
            array.add(element);
        return array;
    }

    /**
     * Gets a model builder for the given location. The returned model builder may be a new model builder or an existing one if requested before.
     * @param location resource location of the model
     */
    protected ModelBuilder model(ResourceLocation location){
        this.cache.trackToBeGeneratedResource(ResourceType.ASSET, location.getNamespace(), "models", location.getPath(), ".json");
        return this.models.computeIfAbsent(location, i -> new ModelBuilder(this.modid, i));
    }

    /**
     * Gets a model builder for the given location. The returned model builder may be a new model builder or an existing one if requested before.
     * @param namespace namespace of the model location
     * @param path      path of the model location
     */
    protected ModelBuilder model(String namespace, String path){
        return this.model(new ResourceLocation(namespace, path));
    }

    /**
     * Gets a model builder for the given location. The returned model builder may be a new model builder or an existing one if requested before.
     * @param location path of the model location
     */
    protected ModelBuilder model(String location){
        return this.model(this.modid, location);
    }

    /**
     * Creates a new model with parent 'minecraft:block/cube' and the given textures for the faces.
     * @param location resource location of the model
     * @param up       resource location of the texture for the top face
     * @param down     resource location of the texture for the bottom face
     * @param north    resource location of the texture for the north face
     * @param east     resource location of the texture for the east face
     * @param south    resource location of the texture for the south face
     * @param west     resource location of the texture for the west face
     */
    protected ModelBuilder cube(ResourceLocation location, ResourceLocation up, ResourceLocation down, ResourceLocation north, ResourceLocation east, ResourceLocation south, ResourceLocation west){
        return this.model(location).parent("minecraft", "block/cube").texture("up", up).texture("down", down).texture("north", north).texture("east", east).texture("south", south).texture("west", west);
    }

    /**
     * Creates a new model with parent 'minecraft:block/cube' and the given textures for the faces.
     * @param namespace namespace of the model location
     * @param path      path of the model location
     * @param up        resource location of the texture for the top face
     * @param down      resource location of the texture for the bottom face
     * @param north     resource location of the texture for the north face
     * @param east      resource location of the texture for the east face
     * @param south     resource location of the texture for the south face
     * @param west      resource location of the texture for the west face
     */
    protected ModelBuilder cube(String namespace, String path, ResourceLocation up, ResourceLocation down, ResourceLocation north, ResourceLocation east, ResourceLocation south, ResourceLocation west){
        return this.model(namespace, path).parent("minecraft", "block/cube").texture("up", up).texture("down", down).texture("north", north).texture("east", east).texture("south", south).texture("west", west);
    }

    /**
     * Creates a new model with parent 'minecraft:block/cube' and the given textures for the faces.
     * @param location resource location of the model
     * @param up       resource location of the texture for the top face
     * @param down     resource location of the texture for the bottom face
     * @param north    resource location of the texture for the north face
     * @param east     resource location of the texture for the east face
     * @param south    resource location of the texture for the south face
     * @param west     resource location of the texture for the west face
     */
    protected ModelBuilder cube(String location, ResourceLocation up, ResourceLocation down, ResourceLocation north, ResourceLocation east, ResourceLocation south, ResourceLocation west){
        return this.model(location).parent("minecraft", "block/cube").texture("up", up).texture("down", down).texture("north", north).texture("east", east).texture("south", south).texture("west", west);
    }

    /**
     * Creates a new model with parent 'minecraft:block/cube_all' and the given texture for '#all'.
     * @param location resource location of the model
     * @param texture  resource location of the texture for the cube's sides
     */
    protected ModelBuilder cubeAll(ResourceLocation location, ResourceLocation texture){
        return this.model(location).parent("minecraft", "block/cube_all").texture("all", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:block/cube_all' and the given texture for '#all'.
     * @param namespace namespace of the model location
     * @param path      path of the model location
     * @param texture   resource location of the texture for the cube's sides
     */
    protected ModelBuilder cubeAll(String namespace, String path, ResourceLocation texture){
        return this.model(namespace, path).parent("minecraft", "block/cube_all").texture("all", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:block/cube_all' and the given texture for '#all'.
     * @param location resource location of the model
     * @param texture  resource location of the texture for the cube's sides
     */
    protected ModelBuilder cubeAll(String location, ResourceLocation texture){
        return this.model(location).parent("minecraft", "block/cube_all").texture("all", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:block/slab' and the given textures for the faces.
     * @param location resource location of the model
     * @param side     resource location of the texture for the side faces
     * @param top      resource location of the texture for the top face
     * @param bottom   resource location of the texture for bottom face
     */
    protected ModelBuilder slabBottom(ResourceLocation location, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        return this.model(location).parent("minecraft", "block/slab").texture("side", side).texture("top", top).texture("bottom", bottom);
    }

    /**
     * Creates a new model with parent 'minecraft:block/slab' and the given textures for the faces.
     * @param namespace namespace of the model location
     * @param path      path of the model location
     * @param side      resource location of the texture for the side faces
     * @param top       resource location of the texture for the top face
     * @param bottom    resource location of the texture for bottom face
     */
    protected ModelBuilder slabBottom(String namespace, String path, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        return this.model(namespace, path).parent("minecraft", "block/slab").texture("side", side).texture("top", top).texture("bottom", bottom);
    }

    /**
     * Creates a new model with parent 'minecraft:block/slab' and the given textures for the faces.
     * @param location resource location of the model
     * @param side     resource location of the texture for the side faces
     * @param top      resource location of the texture for the top face
     * @param bottom   resource location of the texture for bottom face
     */
    protected ModelBuilder slabBottom(String location, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        return this.model(location).parent("minecraft", "block/slab").texture("side", side).texture("top", top).texture("bottom", bottom);
    }

    /**
     * Creates a new model with parent 'minecraft:block/slab_top' and the given textures for the faces.
     * @param location resource location of the model
     * @param side     resource location of the texture for the side faces
     * @param top      resource location of the texture for the top face
     * @param bottom   resource location of the texture for bottom face
     */
    protected ModelBuilder slabTop(ResourceLocation location, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        return this.model(location).parent("minecraft", "block/slab_top").texture("side", side).texture("top", top).texture("bottom", bottom);
    }

    /**
     * Creates a new model with parent 'minecraft:block/slab_top' and the given textures for the faces.
     * @param namespace namespace of the model location
     * @param path      path of the model location
     * @param side      resource location of the texture for the side faces
     * @param top       resource location of the texture for the top face
     * @param bottom    resource location of the texture for bottom face
     */
    protected ModelBuilder slabTop(String namespace, String path, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        return this.model(namespace, path).parent("minecraft", "block/slab_top").texture("side", side).texture("top", top).texture("bottom", bottom);
    }

    /**
     * Creates a new model with parent 'minecraft:block/slab_top' and the given textures for the faces.
     * @param location resource location of the model
     * @param side     resource location of the texture for the side faces
     * @param top      resource location of the texture for the top face
     * @param bottom   resource location of the texture for bottom face
     */
    protected ModelBuilder slabTop(String location, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        return this.model(location).parent("minecraft", "block/slab_top").texture("side", side).texture("top", top).texture("bottom", bottom);
    }

    /**
     * Creates a new model with parent 'minecraft:block/stairs' and the given textures for the faces.
     * @param location resource location of the model
     * @param side     resource location of the texture for the side faces
     * @param top      resource location of the texture for the top face
     * @param bottom   resource location of the texture for bottom face
     */
    protected ModelBuilder stairs(ResourceLocation location, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        return this.model(location).parent("minecraft", "block/stairs").texture("side", side).texture("top", top).texture("bottom", bottom);
    }

    /**
     * Creates a new model with parent 'minecraft:block/stairs' and the given textures for the faces.
     * @param namespace namespace of the model location
     * @param path      path of the model location
     * @param side      resource location of the texture for the side faces
     * @param top       resource location of the texture for the top face
     * @param bottom    resource location of the texture for bottom face
     */
    protected ModelBuilder stairs(String namespace, String path, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        return this.model(namespace, path).parent("minecraft", "block/stairs").texture("side", side).texture("top", top).texture("bottom", bottom);
    }

    /**
     * Creates a new model with parent 'minecraft:block/stairs' and the given textures for the faces.
     * @param location resource location of the model
     * @param side     resource location of the texture for the side faces
     * @param top      resource location of the texture for the top face
     * @param bottom   resource location of the texture for bottom face
     */
    protected ModelBuilder stairs(String location, ResourceLocation side, ResourceLocation top, ResourceLocation bottom){
        return this.model(location).parent("minecraft", "block/stairs").texture("side", side).texture("top", top).texture("bottom", bottom);
    }

    /**
     * Creates a new model with parent 'minecraft:item/generated' and the given textures for '#layer0'.
     * @param location resource location of the model
     * @param texture  resource location of the texture for the item
     */
    protected ModelBuilder itemGenerated(ResourceLocation location, ResourceLocation texture){
        return this.model(location).parent("minecraft", "item/generated").texture("layer0", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:item/generated' and the given textures for '#layer0'.
     * @param namespace namespace of the model location
     * @param path      path of the model location
     * @param texture   resource location of the texture for the item
     */
    protected ModelBuilder itemGenerated(String namespace, String path, ResourceLocation texture){
        return this.model(namespace, path).parent("minecraft", "item/generated").texture("layer0", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:item/generated' and the given textures for '#layer0'.
     * @param location resource location of the model
     * @param texture  resource location of the texture for the item
     */
    protected ModelBuilder itemGenerated(String location, ResourceLocation texture){
        return this.model(location).parent("minecraft", "item/generated").texture("layer0", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:item/generated' and the given textures for '#layer0'.
     * @param item    item to use the location of
     * @param texture resource location of the texture for the item
     */
    protected ModelBuilder itemGenerated(IItemProvider item, ResourceLocation texture){
        ResourceLocation identifier = Registries.ITEMS.getIdentifier(item.asItem());
        return this.model(identifier.getNamespace(), "item/" + identifier.getPath()).parent("minecraft", "item/generated").texture("layer0", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:item/handheld' and the given textures for '#layer0'.
     * @param location resource location of the model
     * @param texture  resource location of the texture for the item
     */
    protected ModelBuilder itemHandheld(ResourceLocation location, ResourceLocation texture){
        return this.model(location).parent("minecraft", "item/handheld").texture("layer0", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:item/handheld' and the given textures for '#layer0'.
     * @param namespace namespace of the model location
     * @param path      path of the model location
     * @param texture   resource location of the texture for the item
     */
    protected ModelBuilder itemHandheld(String namespace, String path, ResourceLocation texture){
        return this.model(namespace, path).parent("minecraft", "item/handheld").texture("layer0", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:item/handheld' and the given textures for '#layer0'.
     * @param location resource location of the model
     * @param texture  resource location of the texture for the item
     */
    protected ModelBuilder itemHandheld(String location, ResourceLocation texture){
        return this.model(location).parent("minecraft", "item/handheld").texture("layer0", texture);
    }

    /**
     * Creates a new model with parent 'minecraft:item/handheld' and the given textures for '#layer0'.
     * @param item    item to use the location of
     * @param texture resource location of the texture for the item
     */
    protected ModelBuilder itemHandheld(IItemProvider item, ResourceLocation texture){
        ResourceLocation identifier = Registries.ITEMS.getIdentifier(item.asItem());
        return this.model(identifier.getNamespace(), "item/" + identifier.getPath()).parent("minecraft", "item/handheld").texture("layer0", texture);
    }

    @Override
    public String getName(){
        return this.modName + " Model Generator";
    }

    protected static class ModelBuilder {

        protected final String modid;
        protected final ResourceLocation identifier;
        private final Map<String,String> textures = new LinkedHashMap<>();
        private final Map<ItemCameraTransforms.TransformType,TransformBuilder> transforms = new LinkedHashMap<>();
        private final List<ElementBuilder> elements = new ArrayList<>();
        private ResourceLocation parent;
        private ResourceLocation renderType;
        private boolean ambientOcclusion = true;
        private BlockModel.GuiLight lighting = null;

        protected ModelBuilder(String modid, ResourceLocation identifier){
            this.modid = modid;
            this.identifier = identifier;
        }

        /**
         * Sets the parent model.
         * @param model the parent model location
         */
        public ModelBuilder parent(ResourceLocation model){
            if(this.identifier.equals(model))
                throw new IllegalArgumentException("Cannot add self as parent model '" + model + "'!");

            this.parent = model;
            return this;
        }

        /**
         * Sets the parent model.
         * @param namespace namespace of the parent model location
         * @param path      path of the parent model location
         */
        public ModelBuilder parent(String namespace, String path){
            return this.parent(new ResourceLocation(namespace, path));
        }

        /**
         * Sets the parent model.
         * @param model the parent model location
         */
        public ModelBuilder parent(String model){
            return this.parent(this.modid, model);
        }

        /**
         * Sets whether ambient occlusion should be applied when rendering this model.
         */
        public ModelBuilder ambientOcclusion(boolean useAmbientOcclusion){
            this.ambientOcclusion = useAmbientOcclusion;
            return this;
        }

        /**
         * Sets no ambient occlusion to be applied when rendering this model.
         */
        public ModelBuilder noAmbientOcclusion(){
            return this.ambientOcclusion(false);
        }

        /**
         * Sets the lighting used when rendering this model in a gui to FRONT.
         */
        public ModelBuilder frontLit(){
            this.lighting = BlockModel.GuiLight.FRONT;
            return this;
        }

        /**
         * Sets the lighting used when rendering this model in a gui to SIDE.
         */
        public ModelBuilder sideLit(){
            this.lighting = BlockModel.GuiLight.SIDE;
            return this;
        }

        /**
         * Puts the given texture under the given key. These keys may be used when on faces for elements of this model.
         * @param key     key to be assigned
         * @param texture texture to be assigned to the given key
         */
        public ModelBuilder texture(String key, ResourceLocation texture){
            this.textures.put(key, texture.toString());
            return this;
        }

        /**
         * Puts the given texture or reference under the given key. These keys may be used when on faces for elements of this model.
         * @param key     key to be assigned
         * @param texture texture or reference to another key to be assigned to the given key
         */
        public ModelBuilder texture(String key, String texture){
            if(texture.charAt(0) != '#' && !RegistryUtil.isValidIdentifier(texture))
                throw new IllegalArgumentException("Texture entry must either start with '#' or be a valid resource location, not '" + texture + "'!");

            if(texture.charAt(0) != '#')
                return this.texture(key, texture.contains(":") ? new ResourceLocation(texture) : new ResourceLocation(this.modid, texture));
            this.textures.put(key, texture);
            return this;
        }

        /**
         * Puts the given texture or reference under the given key. These keys may be used when on faces for elements of this model.
         * @param key        key to be assigned
         * @param namespace  namespace of the texture
         * @param identifier path of the texture
         */
        public ModelBuilder texture(String key, String namespace, String identifier){
            if(!RegistryUtil.isValidNamespace(namespace))
                throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
            if(!RegistryUtil.isValidPath(identifier))
                throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

            this.texture(key, new ResourceLocation(namespace, identifier));
            return this;
        }

        /**
         * Sets the given texture to be used for the particles from this model.
         * @param texture texture for the particles
         */
        public ModelBuilder particleTexture(ResourceLocation texture){
            return this.texture("particle", texture);
        }

        /**
         * Sets the given texture or reference to be used for the particles from this model.
         * @param texture texture or reference to a key for the particles
         */
        public ModelBuilder particleTexture(String texture){
            return this.texture("particle", texture);
        }

        /**
         * Sets the given texture to be used for the particles from this model.
         * @param namespace  namespace of the texture
         * @param identifier path of the texture
         */
        public ModelBuilder particleTexture(String namespace, String identifier){
            return this.texture("particle", namespace, identifier);
        }

        /**
         * Constructs a transformation for the given transform type to be used when rendering this model.
         * @param transformType            transform type to be build
         * @param transformBuilderConsumer consumer to build the transformation
         */
        public ModelBuilder transform(ItemCameraTransforms.TransformType transformType, Consumer<TransformBuilder> transformBuilderConsumer){
            transformBuilderConsumer.accept(this.transforms.computeIfAbsent(transformType, o -> new TransformBuilder()));
            return this;
        }

        /**
         * Constructs a new element for this model.
         * @param elementBuilderConsumer consumer to build the element
         */
        public ModelBuilder element(Consumer<ElementBuilder> elementBuilderConsumer){
            ElementBuilder builder = new ElementBuilder();
            elementBuilderConsumer.accept(builder);
            this.elements.add(builder);
            return this;
        }
    }

    protected static class TransformBuilder {

        private Vector3f rotation = new Vector3f(0, 0, 0);
        private Vector3f translation = new Vector3f(0, 0, 0);
        private Vector3f scale = new Vector3f(1, 1, 1);

        protected TransformBuilder(){
        }

        /**
         * Sets the rotation.
         * @param x rotation around the x-axis
         * @param y rotation around the y-axis
         * @param z rotation around the z-axis
         */
        public TransformBuilder rotation(float x, float y, float z){
            this.rotation = new Vector3f(x, y, z);
            return this;
        }

        /**
         * Sets the translation.
         * @param x translation on the x-axis
         * @param y translation on the y-axis
         * @param z translation on the z-axis
         */
        public TransformBuilder translation(float x, float y, float z){
            this.translation = new Vector3f(x, y, z);
            return this;
        }

        /**
         * Sets the scaling.
         * @param x scaling on the x-axis
         * @param y scaling on the y-axis
         * @param z scaling on the z-axis
         */
        public TransformBuilder scale(float x, float y, float z){
            this.scale = new Vector3f(x, y, z);
            return this;
        }

        /**
         * Sets the scaling for all axis.
         * @param scale scaling for all axis
         */
        public TransformBuilder scale(float scale){
            return this.scale(scale, scale, scale);
        }
    }

    protected static class ElementBuilder {

        private final Map<Direction,FaceBuilder> faces = new LinkedHashMap<>();
        private Vector3f from = new Vector3f(), to = new Vector3f(16, 16, 16);
        private RotationBuilder rotation;
        private boolean shading = true;

        protected ElementBuilder(){
        }

        /**
         * Sets the from-position of this element.
         * @param from position which the element starts at
         */
        public ElementBuilder from(Vector3f from){
            this.from = from;
            return this;
        }

        /**
         * Sets the from-position of this element.
         * @param x x-position which the element starts at
         * @param y y-position which the element starts at
         * @param z z-position which the element starts at
         */
        public ElementBuilder from(float x, float y, float z){
            return this.from(new Vector3f(x, y, z));
        }

        /**
         * Sets the to-position of this element.
         * @param to position which the element ends at
         */
        public ElementBuilder to(Vector3f to){
            this.to = to;
            return this;
        }

        /**
         * Sets the to-position of this element.
         * @param x x-position which the element ends at
         * @param y y-position which the element ends at
         * @param z z-position which the element ends at
         */
        public ElementBuilder to(float x, float y, float z){
            return this.to(new Vector3f(x, y, z));
        }

        /**
         * Sets the start and end position of this element.
         * @param from the start position
         * @param to   the end position
         */
        public ElementBuilder shape(Vector3f from, Vector3f to){
            this.from(from);
            this.to(to);
            return this;
        }

        /**
         * Sets the start and end position of this element.
         */
        public ElementBuilder shape(float minX, float minY, float minZ, float maxX, float maxY, float maxZ){
            return this.shape(new Vector3f(minX, minY, minZ), new Vector3f(maxX, maxY, maxZ));
        }

        /**
         * Sets whether shading should be applied when rendering this element.
         * @param doShading whether shading should be applied
         */
        public ElementBuilder shading(boolean doShading){
            this.shading = doShading;
            return this;
        }

        /**
         * Sets no shading to be applied when rendering this element.
         */
        public ElementBuilder noShading(){
            return this.shading(false);
        }

        /**
         * Constructs a face for the given side of this element.
         * @param side                side to be constructed
         * @param faceBuilderConsumer consumer to build the face
         */
        public ElementBuilder face(Direction side, Consumer<FaceBuilder> faceBuilderConsumer){
            faceBuilderConsumer.accept(this.faces.computeIfAbsent(side, FaceBuilder::new));
            return this;
        }

        /**
         * Constructs faces for all sides of this element
         * @param faceBuilderFunction function to build the faces, returns whether to discard the face
         */
        public ElementBuilder allFaces(BiFunction<Direction,FaceBuilder,Boolean> faceBuilderFunction){
            for(Direction side : Direction.values()){
                if(!faceBuilderFunction.apply(side, this.faces.computeIfAbsent(side, FaceBuilder::new)))
                    this.faces.remove(side);
            }
            return this;
        }

        /**
         * Constructs faces for all sides of this element
         * @param faceBuilderConsumer consumer to build the faces
         */
        public ElementBuilder allFaces(BiConsumer<Direction,FaceBuilder> faceBuilderConsumer){
            return this.allFaces(((direction, faceBuilder) -> {
                faceBuilderConsumer.accept(direction, faceBuilder);
                return true;
            }));
        }

        /**
         * Constructs faces for all sides of this element
         * @param faceBuilderConsumer consumer to build the faces
         */
        public ElementBuilder allFaces(Consumer<FaceBuilder> faceBuilderConsumer){
            return this.allFaces(((direction, faceBuilder) -> {
                faceBuilderConsumer.accept(faceBuilder);
                return true;
            }));
        }

        /**
         * Constructs the rotation of this element.
         * @param rotationBuilderConsumer consumer to build the rotation
         */
        public ElementBuilder rotation(Consumer<RotationBuilder> rotationBuilderConsumer){
            if(this.rotation == null)
                this.rotation = new RotationBuilder();
            rotationBuilderConsumer.accept(this.rotation);
            return this;
        }
    }

    protected static class RotationBuilder {

        private Vector3f origin;
        private Direction.Axis axis;
        private float angle;
        private boolean rescale;

        protected RotationBuilder(){
        }

        /**
         * Sets the origin for the rotation transformation.
         * @param origin position to be rotated around
         */
        public RotationBuilder origin(Vector3f origin){
            this.origin = origin;
            return this;
        }

        /**
         * Sets the origin for the rotation transformation.
         * @param x x-position to be rotated around
         * @param y y-position to be rotated around
         * @param z z-position to be rotated around
         */
        public RotationBuilder origin(float x, float y, float z){
            return this.origin(new Vector3f(x, y, z));
        }

        /**
         * Sets the axis which should rotated around.
         * @param axis axis of rotation
         */
        public RotationBuilder axis(Direction.Axis axis){
            this.axis = axis;
            return this;
        }

        /**
         * Sets the angle to be rotated by. Angle must be one of -45, -22.5, 0, 22.5, or 45.
         * @param angle angle to be rotated by
         */
        public RotationBuilder angle(float angle){
            if(angle != 0 && Math.abs(angle) != 22.5f && Math.abs(angle) != 45)
                throw new IllegalArgumentException("Angle must be one of -45, -22.5, 0, 22.5, or 45, not '" + angle + "'!");

            this.angle = angle;
            return this;
        }

        /**
         * Sets whether to rescale the faces to across the whole block.
         * @param rescale whether to rescale the faces
         */
        public RotationBuilder rescale(boolean rescale){
            this.rescale = rescale;
            return this;
        }

        /**
         * Sets the faces to be rescaled across the whole block.
         */
        public RotationBuilder rescale(){
            return this.rescale(true);
        }
    }

    protected static class FaceBuilder {

        private final Direction side;
        private float[] uv;
        private String texture;
        private Direction cullface;
        private int rotation = 0;
        private int tintIndex = -1;
        private int emissivity = 0;

        protected FaceBuilder(Direction side){
            this.side = side;
        }

        /**
         * Sets the texture uv coordinates for this face. If no uv is set, the coordinates will be determined from the relevant element.
         */
        public FaceBuilder uv(float minX, float minY, float maxX, float maxY){
            this.uv = new float[]{minX, minY, maxX, maxY};
            return this;
        }

        /**
         * Sets the texture to be used on this face. Must be a reference to a key in the top level textures of the model.
         */
        public FaceBuilder texture(String reference){
            if(!(reference.charAt(0) == '#' ? reference.substring(1) : reference).matches("[a-zA-Z_-]*"))
                throw new IllegalArgumentException("Texture reference '" + reference + "' must only contain characters [a-zA-Z_-]!");

            this.texture = reference.charAt(0) == '#' ? reference : "#" + reference;
            return this;
        }

        /**
         * Sets the side which should be covered for this face to be culled, may be {@code null}.
         * @param side side which should be covered
         */
        public FaceBuilder cullface(Direction side){
            this.cullface = side;
            return this;
        }

        /**
         * Sets the side which should be covered for this face to be culled to the side which this face is on.
         */
        public FaceBuilder cullface(){
            this.cullface(this.side);
            return this;
        }

        /**
         * Sets the rotation of the texture. Must be a multiple of 90, default rotation is 0.
         * @param rotation rotation of the texture
         */
        public FaceBuilder rotation(int rotation){
            if(rotation % 90 != 0)
                throw new IllegalArgumentException("Rotation must be a multiple of 90, not '" + rotation + "'!");

            this.rotation = rotation;
            return this;
        }

        /**
         * Sets the tint index for this face.
         * The effect of the tint index depends on the {@link IBlockColor} registered for the block which this model is used for.
         * A tint index of -1 means no tinting will be applied.
         */
        public FaceBuilder tintIndex(int index){
            this.tintIndex = index;
            return this;
        }

        /**
         * Sets the emissivity of this face. Must be in the range 0 to 15.
         */
        public FaceBuilder emissivity(int emissivity){
            if(emissivity < 0 || emissivity > 15)
                throw new IllegalArgumentException("Emissivity must be between 0 and 15, not '" + emissivity + "'!");

            this.emissivity = emissivity;
            return this;
        }
    }
}





























```

### src/main/java/com/supermartijn642/core/generator/RecipeGenerator.java

```java
package com.supermartijn642.core.generator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.core.data.condition.ModLoadedResourceCondition;
import com.supermartijn642.core.data.condition.NotResourceCondition;
import com.supermartijn642.core.data.condition.ResourceCondition;
import com.supermartijn642.core.data.recipe.ConditionalRecipeSerializer;
import com.supermartijn642.core.registry.Registries;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.*;

/**
 * Created 23/08/2022 by SuperMartijn642
 */
public abstract class RecipeGenerator extends ResourceGenerator {

    private final Map<ResourceLocation,RecipeBuilder<?>> recipes = new HashMap<>();
    private final Advancements advancements;

    public RecipeGenerator(String modid, ResourceCache cache){
        super(modid, cache);
        this.advancements = new Advancements(modid, cache);
    }

    @Override
    public void save(){
        // Generate the advancements
        this.advancements.generate();

        // Loop over all recipes
        for(RecipeBuilder<?> recipeBuilder : this.recipes.values()){
            JsonObject json = new JsonObject();
            Map<String,JsonObject> subRecipes = new HashMap<>();
            subRecipes.put("", json);

            // Set the recipe serializer
            json.addProperty("type", Registries.RECIPE_SERIALIZERS.getIdentifier(recipeBuilder.serializer).toString());

            // Filter by recipe builder
            if(recipeBuilder instanceof ShapedRecipeBuilder){
                // Verify all keys are defined
                Set<Character> characters = new HashSet<>();
                for(String row : ((ShapedRecipeBuilder)recipeBuilder).pattern){
                    for(char c : row.toCharArray()){
                        if(c != ' ' && characters.add(c) && !((ShapedRecipeBuilder)recipeBuilder).inputs.containsKey(c))
                            throw new RuntimeException("Recipe '" + recipeBuilder.identifier + "' is missing an input for character '" + c + "'!");
                    }
                }
                for(Character character : ((ShapedRecipeBuilder)recipeBuilder).inputs.keySet()){
                    if(!characters.contains(character))
                        throw new RuntimeException("Recipe '" + recipeBuilder.identifier + "' has unused input with key '" + character + "'!");
                }

                // Group
                json.addProperty("group", recipeBuilder.group);
                // Pattern
                json.add("pattern", createArray(((ShapedRecipeBuilder)recipeBuilder).pattern));
                // Keys
                JsonObject keysJson = new JsonObject();
                for(Map.Entry<Character,Ingredient> input : ((ShapedRecipeBuilder)recipeBuilder).inputs.entrySet())
                    keysJson.add(input.getKey().toString(), input.getValue().toJson());
                json.add("key", keysJson);
                // Result
                JsonObject resultJson = new JsonObject();
                resultJson.addProperty("item", Registries.ITEMS.getIdentifier(recipeBuilder.output.asItem()).toString());
                if(recipeBuilder.outputCount != 1)
                    resultJson.addProperty("count", recipeBuilder.outputCount);
                if(recipeBuilder.outputTag != null)
                    resultJson.addProperty("nbt", recipeBuilder.outputTag.toString());
                json.add("result", resultJson);

            }else if(recipeBuilder instanceof ShapelessRecipeBuilder){
                // Group
                json.addProperty("group", recipeBuilder.group);
                // Ingredients
                JsonArray ingredientsJson = new JsonArray();
                for(Ingredient input : ((ShapelessRecipeBuilder)recipeBuilder).inputs)
                    ingredientsJson.add(input.toJson());
                json.add("ingredients", ingredientsJson);
                // Result
                JsonObject resultJson = new JsonObject();
                resultJson.addProperty("item", Registries.ITEMS.getIdentifier(recipeBuilder.output.asItem()).toString());
                if(recipeBuilder.outputCount != 1)
                    resultJson.addProperty("count", recipeBuilder.outputCount);
                if(recipeBuilder.outputTag != null)
                    resultJson.addProperty("nbt", recipeBuilder.outputTag.toString());
                json.add("result", resultJson);

            }else if(recipeBuilder instanceof SmeltingRecipeBuilder){
                if(((SmeltingRecipeBuilder)recipeBuilder).includeBlasting){
                    JsonObject recipeJson = new JsonObject();
                    recipeJson.addProperty("type", "minecraft:blasting");
                    serializeCookingRecipe(recipeJson, (SmeltingRecipeBuilder)recipeBuilder, 2, 100);
                    subRecipes.put("_blasting", recipeJson);
                }
                if(((SmeltingRecipeBuilder)recipeBuilder).includeSmoking){
                    JsonObject recipeJson = new JsonObject();
                    recipeJson.addProperty("type", "minecraft:smoking");
                    serializeCookingRecipe(json, (SmeltingRecipeBuilder)recipeBuilder, 2, 100);
                    subRecipes.put("_smoking", recipeJson);
                }
                if(((SmeltingRecipeBuilder)recipeBuilder).includeCampfire){
                    JsonObject recipeJson = new JsonObject();
                    recipeJson.addProperty("type", "minecraft:campfire_cooking");
                    serializeCookingRecipe(json, (SmeltingRecipeBuilder)recipeBuilder, 2, 100);
                    subRecipes.put("_campfire", recipeJson);
                }
                if(((SmeltingRecipeBuilder)recipeBuilder).includeSmelting)
                    serializeCookingRecipe(json, (SmeltingRecipeBuilder)recipeBuilder, 1, 200);
                else
                    subRecipes.remove("");
            }else if(recipeBuilder instanceof SmithingRecipeBuilder){
                // Group
                json.addProperty("group", recipeBuilder.group);
                // Base
                json.add("base", ((SmithingRecipeBuilder)recipeBuilder).base.toJson());
                // Addition
                json.add("addition", ((SmithingRecipeBuilder)recipeBuilder).addition.toJson());
                // Result
                JsonObject resultJson = new JsonObject();
                resultJson.addProperty("item", Registries.ITEMS.getIdentifier(recipeBuilder.output.asItem()).toString());
                if(recipeBuilder.outputCount != 1)
                    resultJson.addProperty("count", recipeBuilder.outputCount);
                if(recipeBuilder.outputTag != null)
                    resultJson.addProperty("nbt", recipeBuilder.outputTag.toString());
                json.add("result", resultJson);

            }else if(recipeBuilder instanceof StoneCuttingRecipeBuilder){
                // Group
                json.addProperty("group", recipeBuilder.group);
                // Ingredient
                json.add("ingredient", ((StoneCuttingRecipeBuilder)recipeBuilder).input.toJson());
                // Result
                json.addProperty("result", Registries.ITEMS.getIdentifier(recipeBuilder.output.asItem()).toString());
                // Count
                json.addProperty("count", recipeBuilder.outputCount);
            }

            for(Map.Entry<String,JsonObject> subRecipe : subRecipes.entrySet()){
                json = subRecipe.getValue();

                // Conditions
                if(!recipeBuilder.conditions.isEmpty())
                    json = ConditionalRecipeSerializer.wrapRecipeWithForgeConditions(json, recipeBuilder.conditions);

                // Save the object to the cache
                ResourceLocation identifier = recipeBuilder.identifier;
                this.cache.saveJsonResource(ResourceType.DATA, json, identifier.getNamespace(), "recipes", identifier.getPath() + subRecipe.getKey());
            }
        }

        // Save the advancements
        this.advancements.save();
    }

    private static void serializeCookingRecipe(JsonObject json, SmeltingRecipeBuilder recipeBuilder, int durationDivider, int defaultDuration){
        // Group
        json.addProperty("group", ((RecipeBuilder<?>)recipeBuilder).group);
        // Ingredient
        json.add("ingredient", recipeBuilder.input.toJson());
        // Result
        if(((RecipeBuilder<?>)recipeBuilder).outputTag == null && ((RecipeBuilder<?>)recipeBuilder).outputCount == 1)
            json.addProperty("result", Registries.ITEMS.getIdentifier(((RecipeBuilder<?>)recipeBuilder).output.asItem()).toString());
        else{
            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("item", Registries.ITEMS.getIdentifier(((RecipeBuilder<?>)recipeBuilder).output.asItem()).toString());
            if(((RecipeBuilder<?>)recipeBuilder).outputCount != 1)
                resultJson.addProperty("count", ((RecipeBuilder<?>)recipeBuilder).outputCount);
            if(((RecipeBuilder<?>)recipeBuilder).outputTag != null)
                resultJson.addProperty("nbt", ((RecipeBuilder<?>)recipeBuilder).outputTag.toString());
            json.add("result", resultJson);
        }
        // Experience
        if(recipeBuilder.experience != 0)
            json.addProperty("experience", recipeBuilder.experience);
        // Duration
        int duration = recipeBuilder.duration / durationDivider;
        if(duration != defaultDuration)
            json.addProperty("cookingtime", duration);
    }

    private static JsonArray createArray(Iterable<String> elements){
        JsonArray array = new JsonArray();
        for(String element : elements)
            array.add(element);
        return array;
    }

    protected <T extends RecipeBuilder<T>> T recipe(ResourceLocation recipeLocation, T builder){
        if(this.recipes.containsKey(recipeLocation))
            throw new RuntimeException("Duplicate recipe '" + recipeLocation + "' of types '" + this.recipes.get(recipeLocation).getClass().getName() + "' and '" + builder.getClass().getName() + "'!");

        this.cache.trackToBeGeneratedResource(ResourceType.DATA, builder.identifier.getNamespace(), "recipes", builder.identifier.getPath(), ".json");
        this.recipes.put(recipeLocation, builder);
        return builder;
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     * @param nbt            nbt tag of the recipe result
     * @param amount         count of the recipe result
     */
    protected ShapedRecipeBuilder shaped(ResourceLocation recipeLocation, IItemProvider output, CompoundNBT nbt, int amount){
        return this.recipe(recipeLocation, new ShapedRecipeBuilder(recipeLocation, output, nbt, amount));
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param nbt        nbt tag of the recipe result
     * @param amount     count of the recipe result
     */
    protected ShapedRecipeBuilder shaped(String namespace, String identifier, IItemProvider output, CompoundNBT nbt, int amount){
        return this.shaped(new ResourceLocation(namespace, identifier), output, nbt, amount);
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param nbt        nbt tag of the recipe result
     * @param amount     count of the recipe result
     */
    protected ShapedRecipeBuilder shaped(String identifier, IItemProvider output, CompoundNBT nbt, int amount){
        return this.shaped(this.modid, identifier, output, nbt, amount);
    }

    /**
     * Creates a new shaped recipe builder with the output's identifier as location.
     * @param output recipe result
     * @param nbt    nbt tag of the recipe result
     * @param amount count of the recipe result
     */
    protected ShapedRecipeBuilder shaped(IItemProvider output, CompoundNBT nbt, int amount){
        ResourceLocation identifier = Registries.ITEMS.getIdentifier(output.asItem());
        return this.recipe(identifier, new ShapedRecipeBuilder(identifier, output, nbt, amount));
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     * @param amount         count of the recipe result
     */
    protected ShapedRecipeBuilder shaped(ResourceLocation recipeLocation, IItemProvider output, int amount){
        return this.shaped(recipeLocation, output, null, amount);
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected ShapedRecipeBuilder shaped(String namespace, String identifier, IItemProvider output, int amount){
        return this.shaped(new ResourceLocation(namespace, identifier), output, null, amount);
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected ShapedRecipeBuilder shaped(String identifier, IItemProvider output, int amount){
        return this.shaped(this.modid, identifier, output, null, amount);
    }

    /**
     * Creates a new shaped recipe builder with the output's identifier as location.
     * @param output recipe result
     * @param amount count of the recipe result
     */
    protected ShapedRecipeBuilder shaped(IItemProvider output, int amount){
        return this.shaped(output, null, amount);
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     */
    protected ShapedRecipeBuilder shaped(ResourceLocation recipeLocation, IItemProvider output){
        return this.shaped(recipeLocation, output, null, 1);
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected ShapedRecipeBuilder shaped(String namespace, String identifier, IItemProvider output){
        return this.shaped(new ResourceLocation(namespace, identifier), output, null, 1);
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected ShapedRecipeBuilder shaped(String identifier, IItemProvider output){
        return this.shaped(this.modid, identifier, output, null, 1);
    }

    /**
     * Creates a new shaped recipe builder with the output's identifier as location.
     * @param output recipe result
     */
    protected ShapedRecipeBuilder shaped(IItemProvider output){
        return this.shaped(output, null, 1);
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     */
    protected ShapedRecipeBuilder shaped(ResourceLocation recipeLocation, ItemStack output){
        return this.shaped(recipeLocation, output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected ShapedRecipeBuilder shaped(String namespace, String identifier, ItemStack output){
        return this.shaped(new ResourceLocation(namespace, identifier), output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new shaped recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected ShapedRecipeBuilder shaped(String identifier, ItemStack output){
        return this.shaped(this.modid, identifier, output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new shaped recipe builder with the output's identifier as location.
     * @param output recipe result
     */
    protected ShapedRecipeBuilder shaped(ItemStack output){
        return this.shaped(output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     * @param nbt            nbt tag of the recipe result
     * @param amount         count of the recipe result
     */
    protected ShapelessRecipeBuilder shapeless(ResourceLocation recipeLocation, IItemProvider output, CompoundNBT nbt, int amount){
        return this.recipe(recipeLocation, new ShapelessRecipeBuilder(recipeLocation, output, nbt, amount));
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param nbt        nbt tag of the recipe result
     * @param amount     count of the recipe result
     */
    protected ShapelessRecipeBuilder shapeless(String namespace, String identifier, IItemProvider output, CompoundNBT nbt, int amount){
        return this.shapeless(new ResourceLocation(namespace, identifier), output, nbt, amount);
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param nbt        nbt tag of the recipe result
     * @param amount     count of the recipe result
     */
    protected ShapelessRecipeBuilder shapeless(String identifier, IItemProvider output, CompoundNBT nbt, int amount){
        return this.shapeless(this.modid, identifier, output, nbt, amount);
    }

    /**
     * Creates a new shapeless recipe builder with the output's identifier as location.
     * @param output recipe result
     * @param nbt    nbt tag of the recipe result
     * @param amount count of the recipe result
     */
    protected ShapelessRecipeBuilder shapeless(IItemProvider output, CompoundNBT nbt, int amount){
        ResourceLocation identifier = Registries.ITEMS.getIdentifier(output.asItem());
        return this.shapeless(identifier, output, nbt, amount);
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     * @param amount         count of the recipe result
     */
    protected ShapelessRecipeBuilder shapeless(ResourceLocation recipeLocation, IItemProvider output, int amount){
        return this.shapeless(recipeLocation, output, null, amount);
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected ShapelessRecipeBuilder shapeless(String namespace, String identifier, IItemProvider output, int amount){
        return this.shapeless(new ResourceLocation(namespace, identifier), output, null, amount);
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected ShapelessRecipeBuilder shapeless(String identifier, IItemProvider output, int amount){
        return this.shapeless(this.modid, identifier, output, null, amount);
    }

    /**
     * Creates a new shapeless recipe builder with the output's identifier as location.
     * @param output recipe result
     * @param amount count of the recipe result
     */
    protected ShapelessRecipeBuilder shapeless(IItemProvider output, int amount){
        return this.shapeless(output, null, amount);
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     */
    protected ShapelessRecipeBuilder shapeless(ResourceLocation recipeLocation, IItemProvider output){
        return this.shapeless(recipeLocation, output, null, 1);
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected ShapelessRecipeBuilder shapeless(String namespace, String identifier, IItemProvider output){
        return this.shapeless(new ResourceLocation(namespace, identifier), output, null, 1);
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected ShapelessRecipeBuilder shapeless(String identifier, IItemProvider output){
        return this.shapeless(this.modid, identifier, output, null, 1);
    }

    /**
     * Creates a new shapeless recipe builder with the output's identifier as location.
     * @param output recipe result
     */
    protected ShapelessRecipeBuilder shapeless(IItemProvider output){
        return this.shapeless(output, null, 1);
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     */
    protected ShapelessRecipeBuilder shapeless(ResourceLocation recipeLocation, ItemStack output){
        return this.shapeless(recipeLocation, output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected ShapelessRecipeBuilder shapeless(String namespace, String identifier, ItemStack output){
        return this.shapeless(new ResourceLocation(namespace, identifier), output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new shapeless recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected ShapelessRecipeBuilder shapeless(String identifier, ItemStack output){
        return this.shapeless(this.modid, identifier, output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new shapeless recipe builder with the output's identifier as location.
     * @param output recipe result
     */
    protected ShapelessRecipeBuilder shapeless(ItemStack output){
        return this.shapeless(output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     * @param nbt            nbt tag of the recipe result
     * @param amount         count of the recipe result
     */
    protected SmeltingRecipeBuilder smelting(ResourceLocation recipeLocation, IItemProvider output, CompoundNBT nbt, int amount){
        return this.recipe(recipeLocation, new SmeltingRecipeBuilder(recipeLocation, output, nbt, amount)).includeSmelting();
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param nbt        nbt tag of the recipe result
     * @param amount     count of the recipe result
     */
    protected SmeltingRecipeBuilder smelting(String namespace, String identifier, IItemProvider output, CompoundNBT nbt, int amount){
        return this.smelting(new ResourceLocation(namespace, identifier), output, nbt, amount);
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param nbt        nbt tag of the recipe result
     * @param amount     count of the recipe result
     */
    protected SmeltingRecipeBuilder smelting(String identifier, IItemProvider output, CompoundNBT nbt, int amount){
        return this.smelting(this.modid, identifier, output, nbt, amount);
    }

    /**
     * Creates a new smelting recipe builder with the output's identifier as location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param output recipe result
     * @param nbt    nbt tag of the recipe result
     * @param amount count of the recipe result
     */
    protected SmeltingRecipeBuilder smelting(IItemProvider output, CompoundNBT nbt, int amount){
        ResourceLocation identifier = Registries.ITEMS.getIdentifier(output.asItem());
        return this.smelting(identifier, output, nbt, amount);
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     * @param amount         count of the recipe result
     */
    protected SmeltingRecipeBuilder smelting(ResourceLocation recipeLocation, IItemProvider output, int amount){
        return this.smelting(recipeLocation, output, null, amount);
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected SmeltingRecipeBuilder smelting(String namespace, String identifier, IItemProvider output, int amount){
        return this.smelting(new ResourceLocation(namespace, identifier), output, null, amount);
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected SmeltingRecipeBuilder smelting(String identifier, IItemProvider output, int amount){
        return this.smelting(this.modid, identifier, output, null, amount);
    }

    /**
     * Creates a new smelting recipe builder with the output's identifier as location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param output recipe result
     * @param amount count of the recipe result
     */
    protected SmeltingRecipeBuilder smelting(IItemProvider output, int amount){
        return this.smelting(output, null, amount);
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     */
    protected SmeltingRecipeBuilder smelting(ResourceLocation recipeLocation, IItemProvider output){
        return this.smelting(recipeLocation, output, null, 1);
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected SmeltingRecipeBuilder smelting(String namespace, String identifier, IItemProvider output){
        return this.smelting(new ResourceLocation(namespace, identifier), output, null, 1);
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected SmeltingRecipeBuilder smelting(String identifier, IItemProvider output){
        return this.smelting(this.modid, identifier, output, null, 1);
    }

    /**
     * Creates a new smelting recipe builder with the output's identifier as location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param output recipe result
     */
    protected SmeltingRecipeBuilder smelting(IItemProvider output){
        return this.smelting(output, null, 1);
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     */
    protected SmeltingRecipeBuilder smelting(ResourceLocation recipeLocation, ItemStack output){
        return this.smelting(recipeLocation, output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected SmeltingRecipeBuilder smelting(String namespace, String identifier, ItemStack output){
        return this.smelting(new ResourceLocation(namespace, identifier), output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new smelting recipe builder for the given location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected SmeltingRecipeBuilder smelting(String identifier, ItemStack output){
        return this.smelting(this.modid, identifier, output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new smelting recipe builder with the output's identifier as location. The smelting recipe builder can be used for furnace, blasting, smoking, campfire recipes.
     * @param output recipe result
     */
    protected SmeltingRecipeBuilder smelting(ItemStack output){
        return this.smelting(output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     * @param nbt            nbt tag of the recipe result
     * @param amount         count of the recipe result
     */
    protected SmithingRecipeBuilder smithing(ResourceLocation recipeLocation, IItemProvider output, CompoundNBT nbt, int amount){
        return this.recipe(recipeLocation, new SmithingRecipeBuilder(recipeLocation, output, nbt, amount));
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param nbt        nbt tag of the recipe result
     * @param amount     count of the recipe result
     */
    protected SmithingRecipeBuilder smithing(String namespace, String identifier, IItemProvider output, CompoundNBT nbt, int amount){
        return this.smithing(new ResourceLocation(namespace, identifier), output, nbt, amount);
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param nbt        nbt tag of the recipe result
     * @param amount     count of the recipe result
     */
    protected SmithingRecipeBuilder smithing(String identifier, IItemProvider output, CompoundNBT nbt, int amount){
        return this.smithing(this.modid, identifier, output, nbt, amount);
    }

    /**
     * Creates a new smithing recipe builder with the output's identifier as location.
     * @param output recipe result
     * @param nbt    nbt tag of the recipe result
     * @param amount count of the recipe result
     */
    protected SmithingRecipeBuilder smithing(IItemProvider output, CompoundNBT nbt, int amount){
        ResourceLocation identifier = Registries.ITEMS.getIdentifier(output.asItem());
        return this.smithing(identifier, output, nbt, amount);
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     * @param amount         count of the recipe result
     */
    protected SmithingRecipeBuilder smithing(ResourceLocation recipeLocation, IItemProvider output, int amount){
        return this.smithing(recipeLocation, output, null, amount);
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected SmithingRecipeBuilder smithing(String namespace, String identifier, IItemProvider output, int amount){
        return this.smithing(new ResourceLocation(namespace, identifier), output, null, amount);
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected SmithingRecipeBuilder smithing(String identifier, IItemProvider output, int amount){
        return this.smithing(this.modid, identifier, output, null, amount);
    }

    /**
     * Creates a new smithing recipe builder with the output's identifier as location.
     * @param output recipe result
     * @param amount count of the recipe result
     */
    protected SmithingRecipeBuilder smithing(IItemProvider output, int amount){
        return this.smithing(output, null, amount);
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     */
    protected SmithingRecipeBuilder smithing(ResourceLocation recipeLocation, IItemProvider output){
        return this.smithing(recipeLocation, output, null, 1);
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected SmithingRecipeBuilder smithing(String namespace, String identifier, IItemProvider output){
        return this.smithing(new ResourceLocation(namespace, identifier), output, null, 1);
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected SmithingRecipeBuilder smithing(String identifier, IItemProvider output){
        return this.smithing(this.modid, identifier, output, null, 1);
    }

    /**
     * Creates a new smithing recipe builder with the output's identifier as location.
     * @param output recipe result
     */
    protected SmithingRecipeBuilder smithing(IItemProvider output){
        return this.smithing(output, null, 1);
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     */
    protected SmithingRecipeBuilder smithing(ResourceLocation recipeLocation, ItemStack output){
        return this.smithing(recipeLocation, output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected SmithingRecipeBuilder smithing(String namespace, String identifier, ItemStack output){
        return this.smithing(new ResourceLocation(namespace, identifier), output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new smithing recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected SmithingRecipeBuilder smithing(String identifier, ItemStack output){
        return this.smithing(this.modid, identifier, output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new smithing recipe builder with the output's identifier as location.
     * @param output recipe result
     */
    protected SmithingRecipeBuilder smithing(ItemStack output){
        return this.smithing(output.getItem(), output.hasTag() && !output.getTag().isEmpty() ? output.getTag() : null, output.getCount());
    }

    /**
     * Creates a new stonecutting recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     * @param amount         count of the recipe result
     */
    protected StoneCuttingRecipeBuilder stoneCutting(ResourceLocation recipeLocation, IItemProvider output, int amount){
        return this.recipe(recipeLocation, new StoneCuttingRecipeBuilder(recipeLocation, output, amount));
    }

    /**
     * Creates a new stonecutting recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected StoneCuttingRecipeBuilder stoneCutting(String namespace, String identifier, IItemProvider output, int amount){
        return this.stoneCutting(new ResourceLocation(namespace, identifier), output, amount);
    }

    /**
     * Creates a new stonecutting recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     * @param amount     count of the recipe result
     */
    protected StoneCuttingRecipeBuilder stoneCutting(String identifier, IItemProvider output, int amount){
        return this.stoneCutting(this.modid, identifier, output, amount);
    }

    /**
     * Creates a new stonecutting recipe builder with the output's identifier as location.
     * @param output recipe result
     * @param amount count of the recipe result
     */
    protected StoneCuttingRecipeBuilder stoneCutting(IItemProvider output, int amount){
        ResourceLocation identifier = Registries.ITEMS.getIdentifier(output.asItem());
        return this.stoneCutting(identifier, output, amount);
    }

    /**
     * Creates a new stonecutting recipe builder for the given location.
     * @param recipeLocation location of the recipe
     * @param output         recipe result
     */
    protected StoneCuttingRecipeBuilder stoneCutting(ResourceLocation recipeLocation, IItemProvider output){
        return this.stoneCutting(recipeLocation, output, 1);
    }

    /**
     * Creates a new stonecutting recipe builder for the given location.
     * @param namespace  namespace of the recipe
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected StoneCuttingRecipeBuilder stoneCutting(String namespace, String identifier, IItemProvider output){
        return this.stoneCutting(new ResourceLocation(namespace, identifier), output, 1);
    }

    /**
     * Creates a new stonecutting recipe builder for the given location.
     * @param identifier path of the recipe
     * @param output     recipe result
     */
    protected StoneCuttingRecipeBuilder stoneCutting(String identifier, IItemProvider output){
        return this.stoneCutting(this.modid, identifier, output, 1);
    }

    /**
     * Creates a new stonecutting recipe builder with the output's identifier as location.
     * @param output recipe result
     */
    protected StoneCuttingRecipeBuilder stoneCutting(IItemProvider output){
        return this.stoneCutting(output, 1);
    }

    @Override
    public String getName(){
        return this.modName + " Recipe Generator";
    }

    public static abstract class RecipeBuilder<T extends RecipeBuilder<T>> {

        protected final ResourceLocation identifier;
        private final List<ICondition> conditions = new ArrayList<>();
        private final IItemProvider output;
        private final CompoundNBT outputTag;
        private final int outputCount;
        private IRecipeSerializer<?> serializer;
        private String group;
        private boolean hasAdvancement = true;
        private final List<CriterionInstance> unlockedBy = new ArrayList<>();

        protected RecipeBuilder(ResourceLocation identifier, IRecipeSerializer serializer, IItemProvider output, CompoundNBT outputTag, int outputCount){
            this.identifier = identifier;
            this.output = output;
            this.outputTag = outputTag;
            this.outputCount = outputCount;
            this.serializer = serializer;
        }

        /**
         * Sets the group for this recipe. Multiple recipes with the same group will be grouped together in the recipe book.
         * @param group group for the recipe
         */
        public T group(String group){
            this.group = group == null || group.trim().isEmpty() ? null : group;
            return this.self();
        }

        /**
         * Adds a condition for this recipe to be loaded.
         */
        public T condition(ICondition condition){
            this.conditions.add(condition);
            return this.self();
        }

        /**
         * Adds a condition for this recipe to be loaded.
         */
        public T condition(ResourceCondition condition){
            return this.condition(ResourceCondition.createForgeCondition(condition));
        }

        /**
         * Adds a condition to only load this recipe when the given condition is <b>not</b> satisfied.
         */
        public T notCondition(ICondition condition){
            return this.condition(new NotResourceCondition(condition));
        }

        /**
         * Adds a condition to only load this recipe when the given condition is <b>not</b> satisfied.
         */
        public T notCondition(ResourceCondition condition){
            return this.condition(new NotResourceCondition(condition));
        }

        /**
         * Adds a condition to only load this recipe when a mod with the given modid is present.
         */
        public T modLoadedCondition(String modid){
            return this.condition(new ModLoadedResourceCondition(modid));
        }

        /**
         * Sets whether to generate an advancement to unlock this recipe.
         * @param generate whether to generate an advancement
         */
        public T advancement(boolean generate){
            this.hasAdvancement = generate;
            return this.self();
        }

        /**
         * Sets to not generate an advancement for this recipe.
         */
        public T noAdvancement(){
            return this.advancement(false);
        }

        /**
         * Sets which criterion should be met to unlock this recipe in its generated advancement.
         */
        public T unlockedBy(CriterionInstance criterion){
            if(this.unlockedBy.contains(criterion))
                throw new RuntimeException("Duplicate unlockedBy criterion '" + criterion + "'!");

            this.unlockedBy.add(criterion);
            return this.self();
        }

        /**
         * Sets which items the player should have to unlock this recipe in its generated advancement.
         */
        public T unlockedBy(IItemProvider... items){
            return this.unlockedBy(InventoryChangeTrigger.Instance.hasItems(items));
        }

        /**
         * Sets which items the player should have to unlock this recipe in its generated advancement.
         */
        public T unlockedBy(ITag<Item> tagKey){
            return this.unlockedBy(InventoryChangeTrigger.Instance.hasItems(ItemPredicate.Builder.item().of(tagKey).build()));
        }

        /**
         * Sets a different recipe serializer. This may not have an effect for all recipe types, most notably the smelting recipes.
         */
        public T customSerializer(IRecipeSerializer<?> serializer){
            this.serializer = serializer;
            return this.self();
        }

        private T self(){
            //noinspection unchecked
            return (T)this;
        }
    }

    protected static class ShapedRecipeBuilder extends RecipeBuilder<ShapedRecipeBuilder> {

        private final List<String> pattern = new ArrayList<>();
        private final Map<Character,Ingredient> inputs = new HashMap<>();

        private ShapedRecipeBuilder(ResourceLocation identifier, IItemProvider output, CompoundNBT outputTag, int outputCount){
            super(identifier, IRecipeSerializer.SHAPED_RECIPE, output, outputTag, outputCount);
        }

        /**
         * Adds a row to the pattern for this recipe.
         * The row should consist of at most 3 characters, where {@code ' '} (space) may be used for an empty space.
         * All characters used should be defined using {@link #input(char, Ingredient)}.
         * @param row a row for the pattern
         */
        public ShapedRecipeBuilder pattern(String row){
            if(row.isEmpty())
                throw new IllegalArgumentException("Pattern row for recipe '" + this.identifier + "' cannot be empty!");
            if(row.length() > 3)
                throw new IllegalArgumentException("Pattern row for recipe '" + this.identifier + "' can have at most 3 characters, not '" + row.length() + "'!");
            for(String otherRow : this.pattern){
                if(row.length() != otherRow.length())
                    throw new IllegalArgumentException("Pattern rows for recipe '" + this.identifier + "' must have the same length!");
            }

            this.pattern.add(row);
            return this;
        }

        /**
         * Adds the given rows to the pattern for this recipe.
         * Each row should consist of at most 3 characters, where {@code ' '} (space) may be used for an empty space.
         * All characters used should be defined using {@link #input(char, Ingredient)}.
         * @param rows rows for the pattern
         */
        public ShapedRecipeBuilder pattern(String... rows){
            for(String row : rows)
                this.pattern(row);
            return this;
        }

        /**
         * Defines the ingredient corresponding to the given character. These characters may be used in the pattern for this recipe.
         * @param key        key to be defined
         * @param ingredient ingredient to be associated with the key
         */
        public ShapedRecipeBuilder input(char key, Ingredient ingredient){
            if(this.inputs.containsKey(key))
                throw new RuntimeException("Duplicate key '" + key + "' for recipe '" + this.identifier + "'!");

            this.inputs.put(key, ingredient);
            return this;
        }

        /**
         * Defines the ingredient corresponding to the given character. These characters may be used in the pattern for this recipe.
         * @param key         key to be defined
         * @param ingredients ingredients to be associated with the key
         */
        public ShapedRecipeBuilder input(char key, Ingredient... ingredients){
            return this.input(key, Ingredient.merge(Arrays.asList(ingredients)));
        }

        /**
         * Defines the ingredient corresponding to the given character. These characters may be used in the pattern for this recipe.
         * @param key   key to be defined
         * @param items items to be associated with the key
         */
        public ShapedRecipeBuilder input(char key, IItemProvider... items){
            return this.input(key, Ingredient.of(items));
        }

        /**
         * Defines the ingredient corresponding to the given character. These characters may be used in the pattern for this recipe.
         * @param key        key to be defined
         * @param itemStacks items to be associated with the key
         */
        public ShapedRecipeBuilder input(char key, ItemStack... itemStacks){
            return this.input(key, Ingredient.of(itemStacks));
        }

        /**
         * Defines the ingredient corresponding to the given character. These characters may be used in the pattern for this recipe.
         * @param key key to be defined
         * @param tag tag to be associated with the key
         */
        public ShapedRecipeBuilder input(char key, ITag<Item> tag){
            return this.input(key, Ingredient.of(tag));
        }
    }

    protected static class ShapelessRecipeBuilder extends RecipeBuilder<ShapelessRecipeBuilder> {

        private final List<Ingredient> inputs = new ArrayList<>();

        private ShapelessRecipeBuilder(ResourceLocation identifier, IItemProvider output, CompoundNBT outputTag, int outputCount){
            super(identifier, IRecipeSerializer.SHAPELESS_RECIPE, output, outputTag, outputCount);
        }

        /**
         * Adds an ingredient for this recipe. The ingredient will be added {@code count} times.
         * @param ingredient ingredient to be added
         * @param count      the number of times to add the ingredient
         */
        public ShapelessRecipeBuilder input(Ingredient ingredient, int count){
            if(count <= 0)
                throw new IllegalArgumentException("Cannot add an ingredient '" + count + "' times to recipe '" + this.identifier + "'!");
            if(this.inputs.size() + count > 9)
                throw new RuntimeException("Recipe '" + this.identifier + "' can have at most 9 inputs!");

            for(int i = 0; i < count; i++)
                this.inputs.add(ingredient);
            return this;
        }

        /**
         * Adds an ingredient for this recipe.
         * @param ingredient ingredient to be added
         */
        public ShapelessRecipeBuilder input(Ingredient ingredient){
            return this.input(ingredient, 1);
        }

        /**
         * Adds an item ingredient for this recipe. The ingredient will be added {@code count} times.
         * @param item  ingredient to be added
         * @param count the number of times to add the ingredient
         */
        public ShapelessRecipeBuilder input(IItemProvider item, int count){
            return this.input(Ingredient.of(item), count);
        }

        /**
         * Adds an item ingredient for this recipe.
         * @param item ingredient to be added
         */
        public ShapelessRecipeBuilder input(IItemProvider item){
            return this.input(item, 1);
        }

        /**
         * Adds an item stack ingredient for this recipe. The ingredient will be added {@code count} times.
         * @param itemStack ingredient to be added
         * @param count     the number of times to add the ingredient
         */
        public ShapelessRecipeBuilder input(ItemStack itemStack, int count){
            return this.input(Ingredient.of(itemStack), count);
        }

        /**
         * Adds an item stack ingredient for this recipe.
         * @param itemStack ingredient to be added
         */
        public ShapelessRecipeBuilder input(ItemStack itemStack){
            return this.input(itemStack, 1);
        }

        /**
         * Adds a tag ingredient for this recipe. The ingredient will be added {@code count} times.
         * @param tag   ingredient to be added
         * @param count the number of times to add the ingredient
         */
        public ShapelessRecipeBuilder input(ITag<Item> tag, int count){
            return this.input(Ingredient.of(tag), count);
        }

        /**
         * Adds a tag ingredient for this recipe. The ingredient will be added {@code count} times.
         * @param tag ingredient to be added
         */
        public ShapelessRecipeBuilder input(ITag<Item> tag){
            return this.input(Ingredient.of(tag), 1);
        }

        /**
         * Adds all the given ingredients to this recipe.
         * @param ingredients ingredients to be added
         */
        public ShapelessRecipeBuilder inputs(Ingredient... ingredients){
            for(Ingredient ingredient : ingredients)
                this.input(ingredient);
            return this;
        }

        /**
         * Adds all the given items as ingredients to this recipe.
         * @param items ingredients to be added
         */
        public ShapelessRecipeBuilder inputs(IItemProvider... items){
            for(IItemProvider item : items)
                this.input(item);
            return this;
        }

        /**
         * Adds all the given item stacks as ingredients to this recipe.
         * @param itemStacks ingredients to be added
         */
        public ShapelessRecipeBuilder inputs(ItemStack... itemStacks){
            for(ItemStack itemStack : itemStacks)
                this.input(itemStack);
            return this;
        }
    }

    protected static class SmeltingRecipeBuilder extends RecipeBuilder<SmeltingRecipeBuilder> {

        private boolean includeSmelting;
        private boolean includeBlasting;
        private boolean includeCampfire;
        private boolean includeSmoking;
        private Ingredient input;
        private int experience;
        private int duration = 200;

        private SmeltingRecipeBuilder(ResourceLocation identifier, IItemProvider output, CompoundNBT outputTag, int count){
            super(identifier, IRecipeSerializer.SMELTING_RECIPE, output, outputTag, count);
        }

        /**
         * Whether to generate a furnace smelting recipe.
         */
        public SmeltingRecipeBuilder includeSmelting(boolean includeSmelting){
            this.includeSmelting = includeSmelting;
            return this;
        }

        /**
         * Sets to generate a furnace smelting recipe.
         */
        public SmeltingRecipeBuilder includeSmelting(){
            return this.includeSmelting(true);
        }

        /**
         * Whether to generate a blasting recipe.
         */
        public SmeltingRecipeBuilder includeBlasting(boolean includeBlasting){
            this.includeBlasting = includeBlasting;
            return this;
        }

        /**
         * Sets to generate a blasting recipe.
         */
        public SmeltingRecipeBuilder includeBlasting(){
            return this.includeBlasting(true);
        }

        /**
         * Whether to generate a campfire cooking recipe.
         */
        public SmeltingRecipeBuilder includeCampfire(boolean includeCampfire){
            this.includeCampfire = includeCampfire;
            return this;
        }

        /**
         * Sets to generate a campfire cooking recipe.
         */
        public SmeltingRecipeBuilder includeCampfire(){
            return this.includeCampfire(true);
        }

        /**
         * Whether to generate a smoking recipe.
         */
        public SmeltingRecipeBuilder includeSmoking(boolean includeSmoking){
            this.includeSmoking = includeSmoking;
            return this;
        }

        /**
         * Sets to generate a smoking recipe.
         */
        public SmeltingRecipeBuilder includeSmoking(){
            return this.includeSmoking(true);
        }

        /**
         * Sets the input for this recipe.
         * @param ingredient input ingredient
         */
        public SmeltingRecipeBuilder input(Ingredient ingredient){
            this.input = ingredient;
            return this;
        }

        /**
         * Sets the input for this recipe.
         * @param ingredients ingredients to be accepted as input
         */
        public SmeltingRecipeBuilder input(Ingredient... ingredients){
            return this.input(Ingredient.merge(Arrays.asList(ingredients)));
        }

        /**
         * Sets the input for this recipe.
         * @param items items to be accepted as input
         */
        public SmeltingRecipeBuilder input(IItemProvider... items){
            return this.input(Ingredient.of(items));
        }

        /**
         * Sets the input for this recipe.
         * @param itemStacks items to be accepted as input
         */
        public SmeltingRecipeBuilder input(ItemStack... itemStacks){
            return this.input(Ingredient.of(itemStacks));
        }

        /**
         * Sets the input for this recipe.
         * @param tag item tag to be accepted as input
         */
        public SmeltingRecipeBuilder input(ITag<Item> tag){
            return this.input(Ingredient.of(tag));
        }

        /**
         * Sets the experience gained from this recipe.
         * @param experience the amount of experience
         */
        public SmeltingRecipeBuilder experience(int experience){
            if(experience < 0)
                throw new IllegalArgumentException("Experience for recipe '" + this.identifier + "' cannot be negative!");

            this.experience = experience;
            return this;
        }

        /**
         * Sets the duration of this recipe. The given duration corresponds to the duration for the furnace recipe.
         * Blasting, smoking and campfire cooking will have half the given duration.
         * @param ticks the duration in ticks
         */
        public SmeltingRecipeBuilder duration(int ticks){
            if(ticks <= 0)
                throw new IllegalArgumentException("Duration for recipe '" + this.identifier + "' must be greater than 0!");

            this.duration = ticks;
            return this;
        }

        /**
         * Sets the duration of this recipe. The given duration corresponds to the duration for the furnace recipe.
         * Blasting, smoking and campfire cooking will have half the given duration.
         * @param seconds the duration in seconds
         */
        public SmeltingRecipeBuilder durationSeconds(int seconds){
            return this.duration(seconds * 20);
        }
    }

    protected static class SmithingRecipeBuilder extends RecipeBuilder<SmithingRecipeBuilder> {

        private Ingredient base, addition;

        private SmithingRecipeBuilder(ResourceLocation identifier, IItemProvider output, CompoundNBT outputTag, int outputCount){
            super(identifier, IRecipeSerializer.SMITHING, output, outputTag, outputCount);
        }

        /**
         * Sets the base ingredient for this recipe.
         * @param ingredient ingredient to be used as base
         */
        public SmithingRecipeBuilder base(Ingredient ingredient){
            this.base = ingredient;
            return this;
        }

        /**
         * Sets the base ingredient for this recipe.
         * @param ingredients ingredients to be accepted as base
         */
        public SmithingRecipeBuilder base(Ingredient... ingredients){
            return this.base(Ingredient.merge(Arrays.asList(ingredients)));
        }

        /**
         * Sets the base ingredient for this recipe.
         * @param items items to be accepted as base
         */
        public SmithingRecipeBuilder base(IItemProvider... items){
            return this.base(Ingredient.of(items));
        }

        /**
         * Sets the base ingredient for this recipe.
         * @param itemStacks items to be accepted as base
         */
        public SmithingRecipeBuilder base(ItemStack... itemStacks){
            return this.base(Ingredient.of(itemStacks));
        }

        /**
         * Sets the base ingredient for this recipe.
         * @param tag item tag to be accepted as base
         */
        public SmithingRecipeBuilder base(ITag<Item> tag){
            return this.base(Ingredient.of(tag));
        }

        /**
         * Sets the addition ingredient for this recipe.
         * @param ingredient ingredient to be used as addition
         */
        public SmithingRecipeBuilder addition(Ingredient ingredient){
            this.addition = ingredient;
            return this;
        }

        /**
         * Sets the addition ingredient for this recipe.
         * @param ingredients ingredients to be accepted as addition
         */
        public SmithingRecipeBuilder addition(Ingredient... ingredients){
            return this.addition(Ingredient.merge(Arrays.asList(ingredients)));
        }

        /**
         * Sets the addition ingredient for this recipe.
         * @param items items to be accepted as addition
         */
        public SmithingRecipeBuilder addition(IItemProvider... items){
            return this.addition(Ingredient.of(items));
        }

        /**
         * Sets the addition ingredient for this recipe.
         * @param itemStacks items to be accepted as addition
         */
        public SmithingRecipeBuilder addition(ItemStack... itemStacks){
            return this.addition(Ingredient.of(itemStacks));
        }

        /**
         * Sets the addition ingredient for this recipe.
         * @param tag item tag to be accepted as addition
         */
        public SmithingRecipeBuilder addition(ITag<Item> tag){
            return this.addition(Ingredient.of(tag));
        }
    }

    protected static class StoneCuttingRecipeBuilder extends RecipeBuilder<StoneCuttingRecipeBuilder> {

        private Ingredient input;

        private StoneCuttingRecipeBuilder(ResourceLocation identifier, IItemProvider output, int outputCount){
            super(identifier, IRecipeSerializer.STONECUTTER, output, null, outputCount);
        }

        /**
         * Sets the input ingredient for this recipe.
         * @param ingredient ingredient to be used as input
         */
        public StoneCuttingRecipeBuilder input(Ingredient ingredient){
            this.input = ingredient;
            return this;
        }

        /**
         * Sets the input ingredient for this recipe.
         * @param ingredients ingredients to be accepted as input
         */
        public StoneCuttingRecipeBuilder input(Ingredient... ingredients){
            return this.input(Ingredient.merge(Arrays.asList(ingredients)));
        }

        /**
         * Sets the input ingredient for this recipe.
         * @param items items to be accepted as input
         */
        public StoneCuttingRecipeBuilder input(IItemProvider... items){
            return this.input(Ingredient.of(items));
        }

        /**
         * Sets the input ingredient for this recipe.
         * @param itemStacks items to be accepted as input
         */
        public StoneCuttingRecipeBuilder input(ItemStack... itemStacks){
            return this.input(Ingredient.of(itemStacks));
        }

        /**
         * Sets the input ingredient for this recipe.
         * @param tag item tag to be accepted as input
         */
        public StoneCuttingRecipeBuilder input(ITag<Item> tag){
            return this.input(Ingredient.of(tag));
        }
    }

    private final class Advancements extends AdvancementGenerator {

        public Advancements(String modid, ResourceCache cache){
            super(modid, cache);
        }

        @Override
        public void generate(){
            for(RecipeBuilder<?> recipe : RecipeGenerator.this.recipes.values()){
                if(!recipe.hasAdvancement)
                    continue;

                ItemGroup tab = recipe.output.asItem().getItemCategory();
                String namespace = recipe.identifier.getNamespace();
                String identifier = "recipes/" + (tab == null ? "" : tab.getRecipeFolderName() + "/") + recipe.identifier.getPath();

                if(recipe instanceof SmeltingRecipeBuilder){
                    if(((SmeltingRecipeBuilder)recipe).includeSmelting)
                        this.createAdvancement(namespace, identifier + "_smelting", recipe);
                    if(((SmeltingRecipeBuilder)recipe).includeBlasting)
                        this.createAdvancement(namespace, identifier + "_blasting", recipe);
                    if(((SmeltingRecipeBuilder)recipe).includeSmoking)
                        this.createAdvancement(namespace, identifier + "_smoking", recipe);
                    if(((SmeltingRecipeBuilder)recipe).includeCampfire)
                        this.createAdvancement(namespace, identifier + "_campfire", recipe);
                }else
                    this.createAdvancement(namespace, identifier, recipe);
            }
        }

        private void createAdvancement(String namespace, String identifier, RecipeBuilder<?> recipe){
            AdvancementBuilder builder = this.advancement(namespace, identifier)
                .parent(new ResourceLocation("minecraft", "recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipe.identifier))
                .icon(recipe.output, recipe.outputTag)
                .dontShowToast()
                .dontAnnounceToChat()
                .rewardRecipe(recipe.identifier);
            String[] triggers = new String[recipe.unlockedBy.size() + 1];
            triggers[0] = "has_the_recipe";
            if(recipe.unlockedBy.size() == 1){
                builder.criterion("recipe_condition", recipe.unlockedBy.get(0));
                triggers[1] = "recipe_condition";
            }else{
                for(int i = 0; i < recipe.unlockedBy.size(); i++){
                    builder.criterion("recipe_condition" + (i + 1), recipe.unlockedBy.get(i));
                    triggers[i + 1] = "recipe_condition" + (i + 1);
                }
            }
            builder.requirementGroup(triggers);
            // Add the same conditions the recipe has to its advancement
            recipe.conditions.forEach(builder::condition);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/generator/ResourceCache.java

```java
package com.supermartijn642.core.generator;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.supermartijn642.core.generator.aggregator.ResourceAggregator;
import com.supermartijn642.core.util.Pair;
import net.minecraft.data.DirectoryCache;
import net.minecraft.resources.IResource;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created 16/08/2022 by SuperMartijn642
 */
public abstract class ResourceCache {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    /**
     * Checks whether a resource exists. The resource may be either a generated file, or a file from a loaded resource pack.
     * @param resourceType whether the resource is part of the server data or the client assets
     * @param namespace    the namespace of the resource
     * @param directory    name of the directory within the namespace
     * @param fileName     name of the file
     * @param extension    the file's extension
     */
    public abstract boolean doesResourceExist(ResourceType resourceType, String namespace, String directory, String fileName, String extension);

    /**
     * Tracks the given location as if a file has been saved there.
     * Specifically, this means {@link #doesResourceExist(ResourceType, String, String, String, String)} will return {@code true} for the given location.
     * A resource should later be saved for the given location.
     * @param resourceType whether the resource is part of the server data or the client assets
     * @param namespace    the namespace of the resource
     * @param directory    name of the directory within the namespace
     * @param fileName     name of the file
     * @param extension    the file's extension
     */
    public abstract void trackToBeGeneratedResource(ResourceType resourceType, String namespace, String directory, String fileName, String extension);

    /**
     * Saves the given data in the appropriate location. Also checks if a file is already present to avoid redundant writes.
     * @param resourceType whether the given data is part of the server data or the client assets
     * @param namespace    the namespace which the data should be saved under
     * @param directory    name of the directory within the namespace
     * @param fileName     name of the file
     * @param extension    extension of the file
     */
    public abstract void saveResource(ResourceType resourceType, byte[] data, String namespace, String directory, String fileName, String extension);

    /**
     * Saves the given data in the appropriate location. Also checks if a file is already present to avoid redundant writes.
     * @param resourceType whether the given data is part of the server data or the client assets
     * @param aggregator   aggregator used when multiple generator write to the same file location
     * @param namespace    the namespace which the data should be saved under
     * @param directory    name of the directory within the namespace
     * @param fileName     name of the file
     * @param extension    extension of the file
     */
    public abstract <T> void saveResource(ResourceType resourceType, ResourceAggregator<?,T> aggregator, T data, String namespace, String directory, String fileName, String extension);

    /**
     * Saves the given data in the appropriate location. Also checks if a file is already present to avoid redundant writes.
     * @param resourceType whether the given data is part of the server data or the client assets
     * @param json         the data to be saved
     * @param namespace    the namespace which the data should be saved under
     * @param directory    name of the directory within the namespace
     * @param fileName     name of the file
     */
    public void saveJsonResource(ResourceType resourceType, JsonObject json, String namespace, String directory, String fileName){
        byte[] bytes = GSON.toJson(json).getBytes(StandardCharsets.UTF_8);
        this.saveResource(resourceType, bytes, namespace, directory, fileName, fileName.endsWith(".json") ? "" : ".json");
    }

    /**
     * Opens an input stream for the requested resource.
     * @param resourceType whether the resource is part of the server data or the client assets
     * @param namespace    the namespace of the resource
     * @param directory    name of the directory within the namespace
     * @param fileName     name of the file
     * @param extension    the file's extension
     * @return an input stream for the requested resource, or an empty optional if the resource does not exist
     */
    public abstract Optional<InputStream> getExistingResource(ResourceType resourceType, String namespace, String directory, String fileName, String extension);

    /**
     * @deprecated for internal use only
     */
    @Deprecated
    public static ResourceCache wrap(ExistingFileHelper existingFileHelper, DirectoryCache hashCache, Path outputDirectory){
        return new HashCacheWrapper(existingFileHelper, hashCache, outputDirectory);
    }

    /**
     * @deprecated for internal use only
     */
    @Deprecated
    public static class HashCacheWrapper extends ResourceCache {

        private final Map<Path,HashCode> presentFiles = new HashMap<>();
        private final Map<Path,HashCode> writtenFiles = new HashMap<>();
        private final Map<Path,Pair<ResourceAggregator<Object,Object>,Object>> aggregatedResources = new HashMap<>();
        private final Set<Path> toBeGenerated = new HashSet<>();

        private final ExistingFileHelper existingFileHelper;
        private final Path outputDirectory;
        private final DirectoryCache cache;
        private boolean allowWrites = true;

        private HashCacheWrapper(ExistingFileHelper existingFileHelper, DirectoryCache cache, Path outputFolder){
            if(outputFolder == null)
                throw new IllegalArgumentException("Output directory must not be null!");
            this.outputDirectory = outputFolder;
            this.existingFileHelper = existingFileHelper;
            this.cache = cache;
            // Copy all the paths from the hash cache
            for(Map.Entry<Path,String> entry : this.cache.oldCache.entrySet())
                this.presentFiles.put(this.outputDirectory.relativize(entry.getKey()), entry.getValue().isEmpty() ? HashCode.fromInt(0) : HashCode.fromString(entry.getValue()));
        }

        private boolean existsInGeneratedFiles(Path path){
            return this.toBeGenerated.contains(path) || this.aggregatedResources.containsKey(path) || this.cache.newCache.containsKey(this.outputDirectory.resolve(path));
        }

        private boolean existsInLoadedResources(ResourceType resourceType, String namespace, String directory, String fileName, String extension){
            ResourceLocation location = new ResourceLocation(namespace, directory + "/" + fileName + extension);
            return this.existingFileHelper.exists(location, resourceType == ResourceType.DATA ? ResourcePackType.SERVER_DATA : ResourcePackType.CLIENT_RESOURCES);
        }

        private Path constructPath(ResourceType resourceType, String namespace, String directory, String fileName, String extension){
            return Paths.get(resourceType.getDirectoryName(), namespace, directory, fileName + extension);
        }

        @Override
        public boolean doesResourceExist(ResourceType resourceType, String namespace, String directory, String fileName, String extension){
            Path path = this.constructPath(resourceType, namespace, directory, fileName, extension);
            return this.existsInGeneratedFiles(path)
                || this.existsInLoadedResources(resourceType, namespace, directory, fileName, extension);
        }

        @Override
        public void trackToBeGeneratedResource(ResourceType resourceType, String namespace, String directory, String fileName, String extension){
            this.toBeGenerated.add(this.constructPath(resourceType, namespace, directory, fileName, extension));
            ResourceLocation location = new ResourceLocation(namespace, directory + "/" + fileName + extension);
            this.existingFileHelper.trackGenerated(location, resourceType == ResourceType.DATA ? ResourcePackType.SERVER_DATA : ResourcePackType.CLIENT_RESOURCES, extension, directory);
        }

        @Override
        public Optional<InputStream> getExistingResource(ResourceType resourceType, String namespace, String directory, String fileName, String extension){
            try{
                IResource resource = this.existingFileHelper.getResource(new ResourceLocation(namespace, directory + "/" + fileName + extension), resourceType == ResourceType.DATA ? ResourcePackType.SERVER_DATA : ResourcePackType.CLIENT_RESOURCES);
                return Optional.of(resource.getInputStream());
            }catch(FileNotFoundException | NoSuchElementException e){
                return Optional.empty();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }

        @Override
        public void saveResource(ResourceType resourceType, byte[] data, String namespace, String directory, String fileName, String extension){
            if(!this.allowWrites)
                throw new RuntimeException("Resources cannot be saved during this stage!");

            Path path = this.constructPath(resourceType, namespace, directory, fileName, extension);
            Path fullPath = this.outputDirectory.resolve(path);
            if(this.writtenFiles.containsKey(path) || this.aggregatedResources.containsKey(path) || this.cache.newCache.containsKey(fullPath))
                throw new RuntimeException("Duplicate file '" + path + "'!");

            // Skip writing if the present file matches the one to be written
            HashCode hashCode = Hashing.sha1().hashBytes(data);
            if(this.presentFiles.containsKey(path) && this.presentFiles.get(path).equals(hashCode) && fullPath.toFile().exists()){
                this.writtenFiles.put(path, hashCode);
                this.toBeGenerated.remove(path);
                this.cache.putNew(fullPath, hashCode.toString());
                return;
            }

            // Write the data to file
            fullPath.toFile().getParentFile().mkdirs();
            try(OutputStream outputStream = Files.newOutputStream(fullPath)){
                outputStream.write(data);
            }catch(IOException e){
                throw new RuntimeException(e);
            }
            this.writtenFiles.put(path, hashCode);
            this.toBeGenerated.remove(path);
            this.cache.putNew(fullPath, hashCode.toString());
        }

        @Override
        public <T> void saveResource(ResourceType resourceType, ResourceAggregator<?,T> aggregator, T data, String namespace, String directory, String fileName, String extension){
            if(!this.allowWrites)
                throw new RuntimeException("Resources cannot be saved during this stage!");

            Path path = this.constructPath(resourceType, namespace, directory, fileName, extension);
            Path fullPath = this.outputDirectory.resolve(path);
            if(this.writtenFiles.containsKey(path) || this.cache.newCache.containsKey(fullPath))
                throw new RuntimeException("Duplicate file '" + path + "'!");

            // Validate the aggregators match
            Pair<ResourceAggregator<Object,Object>,Object> oldEntry = this.aggregatedResources.get(path);
            if(oldEntry != null && oldEntry.left() != aggregator)
                throw new RuntimeException("Incompatible aggregators for file '" + path + "': '" + oldEntry.left().getClass() + "' and '" + aggregator.getClass() + "'!");

            // Combine the old with the new data
            Object oldData = oldEntry == null ? aggregator.initialData() : oldEntry.right();
            try{
                //noinspection unchecked
                oldData = ((ResourceAggregator<Object,Object>)aggregator).combine(oldData, data);
            }catch(Exception e){
                throw new RuntimeException("Failed to combine data for file '" + path + "'!", e);
            }
            //noinspection unchecked
            this.aggregatedResources.put(path, Pair.of((ResourceAggregator<Object,Object>)aggregator, oldData));
        }

        public void allowWrites(boolean allow){
            this.allowWrites = allow;
        }

        public void finish(){
            // Write all aggregated resources
            this.aggregatedResources.forEach((path, pair) -> {
                // Convert the data to bytes
                ResourceAggregator<Object,Object> aggregator = pair.left();
                Object data = pair.right();
                byte[] bytes;
                try(ByteArrayOutputStream stream = new ByteArrayOutputStream()){
                    aggregator.write(stream, data);
                    bytes = stream.toByteArray();
                }catch(Exception e){
                    throw new RuntimeException(e);
                }

                // Skip writing if the present file matches the one to be written
                Path fullPath = this.outputDirectory.resolve(path);
                HashCode hashCode = Hashing.sha1().hashBytes(bytes);
                if(this.presentFiles.containsKey(path) && this.presentFiles.get(path).equals(hashCode) && fullPath.toFile().exists()){
                    this.writtenFiles.put(path, hashCode);
                    this.toBeGenerated.remove(path);
                    this.cache.putNew(fullPath, hashCode.toString());
                    return;
                }

                // Write the data to file
                fullPath.toFile().getParentFile().mkdirs();
                try(OutputStream outputStream = Files.newOutputStream(fullPath)){
                    outputStream.write(bytes);
                }catch(IOException e){
                    throw new RuntimeException(e);
                }
                this.writtenFiles.put(path, hashCode);
                this.toBeGenerated.remove(path);
                this.cache.putNew(fullPath, hashCode.toString());
            });

            // Validate all promised files have actually been written
            if(!this.toBeGenerated.isEmpty())
                throw new RuntimeException("Some tracked files did not get written: " + this.toBeGenerated.stream().map(Path::toString).map(s -> "'" + s + "'").collect(Collectors.joining(",")));
        }
    }
}
```

### src/main/java/com/supermartijn642/core/generator/ResourceGenerator.java

```java
package com.supermartijn642.core.generator;

import com.supermartijn642.core.CoreLib;
import com.supermartijn642.core.registry.RegistryUtil;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.forgespi.language.IModInfo;

import java.util.Optional;

/**
 * Created 04/08/2022 by SuperMartijn642
 */
public abstract class ResourceGenerator {

    /**
     * Wraps the given resource generator in a data provider using the given file helper and data generator.
     * @return a data provider wrapping the resource generator
     */
    public static IDataProvider createDataProvider(ResourceGenerator generator){
        return new DataProviderInstance(generator);
    }

    protected final String modid;
    protected final String modName;
    protected final ResourceCache cache;

    public ResourceGenerator(String modid, ResourceCache cache){
        if(!RegistryUtil.isValidNamespace(modid))
            throw new IllegalArgumentException("Modid '" + modid + "' must only contain characters [a-z0-9_.-]!");
        String activeMod = ModLoadingContext.get().getActiveNamespace();
        if(activeMod != null && !activeMod.equals("minecraft") && !activeMod.equals("forge")){
            if(!activeMod.equals(modid))
                CoreLib.LOGGER.warn("Mod '" + ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName() + "' is creating a resource generator with different modid '" + modid + "'!");
        }else if(modid.equals("minecraft") || modid.equals("forge"))
            CoreLib.LOGGER.warn("Mod is creating a resource generator with modid '" + modid + "'!");

        this.modid = modid;
        this.cache = cache;

        Optional<? extends ModContainer> modContainer = ModList.get().getModContainerById(modid);
        this.modName = modContainer.map(ModContainer::getModInfo).map(IModInfo::getDisplayName).orElse(modid);
    }

    /**
     * Generates all data. All files that will be generated should be tracked using {@link ResourceCache#trackToBeGeneratedResource(ResourceType, String, String, String, String)}.
     */
    public abstract void generate();

    /**
     * Saves any generated resources. {@link #cache} may be used to check for existing files and to save the generated files.
     */
    public abstract void save();

    /**
     * Gives the name of this data generator. A good name should include the name of the owning mod and the type of data the generator generates, e.g. Your Mod's model generator.
     */
    public String getName(){
        return this.modName + " Resource Generator";
    }

    /**
     * Gives the modid of the mod which owns this generator.
     */
    public final String getOwnerModid(){
        return this.modid;
    }

    /**
     * @deprecated for internal use only
     */
    @Deprecated
    public static class DataProviderInstance implements IDataProvider {

        private final ResourceGenerator generator;
        private boolean generated = false;

        public DataProviderInstance(ResourceGenerator generator){
            this.generator = generator;
        }

        public void generate(){
            this.generated = true;
            this.generator.generate();
        }

        @Override
        public void run(DirectoryCache output){
            // Run the resource generator
            if(!this.generated)
                this.generator.generate();
            this.generator.save();
        }

        @Override
        public String getName(){
            return this.generator.getName();
        }
    }
}
```

### src/main/java/com/supermartijn642/core/generator/ResourceType.java

```java
package com.supermartijn642.core.generator;

/**
 * Created 04/08/2022 by SuperMartijn642
 */
public enum ResourceType {

    DATA("data"), ASSET("assets");

    private final String directory;

    ResourceType(String directory){
        this.directory = directory;
    }

    public String getDirectoryName(){
        return this.directory;
    }
}
```

### src/main/java/com/supermartijn642/core/generator/standard/CoreLibLanguageGenerator.java

```java
package com.supermartijn642.core.generator.standard;

import com.supermartijn642.core.generator.LanguageGenerator;
import com.supermartijn642.core.generator.ResourceCache;

/**
 * Created 10/01/2026 by SuperMartijn642
 */
public class CoreLibLanguageGenerator extends LanguageGenerator {

    public CoreLibLanguageGenerator(String modid, ResourceCache cache){
        super(modid, cache, "en_us");
    }

    @Override
    public void generate(){
        this.translation("supermartijn642corelib.widgets.scrollbar.narration", "scroll bar");
    }
}
```

### src/main/java/com/supermartijn642/core/generator/standard/CoreLibMiningTagGenerator.java

```java
package com.supermartijn642.core.generator.standard;

import com.supermartijn642.core.generator.ResourceCache;
import com.supermartijn642.core.generator.TagGenerator;
import net.minecraft.util.ResourceLocation;

/**
 * Created 05/08/2022 by SuperMartijn642
 */
public class CoreLibMiningTagGenerator extends TagGenerator {

    private static final ResourceLocation MINEABLE_WITH_AXE = new ResourceLocation("mineable/axe");
    private static final ResourceLocation MINEABLE_WITH_HOE = new ResourceLocation("mineable/hoe");
    private static final ResourceLocation MINEABLE_WITH_PICKAXE = new ResourceLocation("mineable/pickaxe");
    private static final ResourceLocation MINEABLE_WITH_SHOVEL = new ResourceLocation("mineable/shovel");
    private static final ResourceLocation NEEDS_DIAMOND_TOOL = new ResourceLocation("needs_diamond_tool");
    private static final ResourceLocation NEEDS_IRON_TOOL = new ResourceLocation("needs_iron_tool");
    private static final ResourceLocation NEEDS_STONE_TOOL = new ResourceLocation("needs_stone_tool");

    public CoreLibMiningTagGenerator(String modid, ResourceCache cache){
        super(modid, cache);
    }

    @Override
    public void generate(){
        // Cause all these tags to generate
        this.blockTag(MINEABLE_WITH_AXE);
        this.blockTag(MINEABLE_WITH_HOE);
        this.blockTag(MINEABLE_WITH_PICKAXE);
        this.blockTag(MINEABLE_WITH_SHOVEL);
        this.blockTag(NEEDS_DIAMOND_TOOL);
        this.blockTag(NEEDS_IRON_TOOL);
        this.blockTag(NEEDS_STONE_TOOL);
    }
}
```

### src/main/java/com/supermartijn642/core/generator/TagGenerator.java

```java
package com.supermartijn642.core.generator;

import com.google.gson.*;
import com.supermartijn642.core.data.tag.CustomTagEntry;
import com.supermartijn642.core.generator.aggregator.ResourceAggregator;
import com.supermartijn642.core.registry.Registries;
import com.supermartijn642.core.registry.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistry;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

/**
 * Created 05/08/2022 by SuperMartijn642
 */
public abstract class TagGenerator extends ResourceGenerator {

    private static final Map<Registries.Registry<?>,String> TAG_DIRECTORIES = new HashMap<>();

    static{
        TAG_DIRECTORIES.put(Registries.BLOCKS, "blocks");
        TAG_DIRECTORIES.put(Registries.FLUIDS, "fluids");
        TAG_DIRECTORIES.put(Registries.ITEMS, "items");
        TAG_DIRECTORIES.put(Registries.ENTITY_TYPES, "entity_types");
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final ResourceAggregator<TagBuilder<?>,TagBuilder<?>> AGGREGATOR = new ResourceAggregator<TagBuilder<?>,TagBuilder<?>>() {
        @Override
        public TagBuilder<?> initialData(){
            return null;
        }

        @Override
        public TagBuilder<?> combine(TagBuilder<?> data, TagBuilder<?> newData){
            if(data != null){
                //noinspection unchecked,rawtypes
                ((TagBuilder)data).addAll(newData);
                return data;
            }
            return newData;
        }

        @Override
        public void write(OutputStream stream, TagBuilder<?> tag) throws IOException{
            // Convert the tag into a json object
            JsonObject json = new JsonObject();
            // Replace
            json.addProperty("replace", tag.replace);
            // Entries & references
            JsonArray entries = new JsonArray();
            tag.entries.forEach(entry -> entry.serializeTo(entries));
            sortJsonArray(entries);
            if(entries.size() > 0 || tag.remove.isEmpty())
                json.add("values", entries);
            // Removed
            JsonArray removedEntries = new JsonArray();
            tag.remove.forEach(entry -> entry.serializeTo(removedEntries));
            sortJsonArray(removedEntries);
            if(removedEntries.size() > 0)
                json.add("remove", removedEntries);

            // Write the data
            try(Writer writer = new OutputStreamWriter(stream)){
                GSON.toJson(json, writer);
            }
        }
    };

    private final Map<Registries.Registry<?>,Map<ResourceLocation,TagBuilder<?>>> tags = new HashMap<>();

    public TagGenerator(String modid, ResourceCache cache){
        super(modid, cache);
    }

    @Override
    public void save(){
        // Loop over all registries
        for(Map.Entry<Registries.Registry<?>,Map<ResourceLocation,TagBuilder<?>>> registryEntry : this.tags.entrySet()){
            String directoryName = getTagDirectoryName(registryEntry.getKey());
            // Loop over all tags
            for(TagBuilder<?> tag : registryEntry.getValue().values()){
                // Validate tag references
                for(ITag.ITagEntry entry : tag.entries){
                    if(!(entry instanceof ITag.TagEntry))
                        continue;
                    ResourceLocation reference = ((ITag.TagEntry)entry).getId();
                    if(registryEntry.getValue().containsKey(reference))
                        continue;
                    if(this.cache.doesResourceExist(ResourceType.DATA, reference.getNamespace(), directoryName, reference.getPath(), ".json"))
                        continue;

                    throw new RuntimeException("Could not find tag reference '" + reference + "' in '" + tag.identifier + "'!");
                }
                // Save the object to the cache
                ResourceLocation identifier = tag.identifier;
                this.cache.saveResource(ResourceType.DATA, AGGREGATOR, tag, identifier.getNamespace(), directoryName, identifier.getPath(), ".json");
            }
        }
    }

    private static String getTagDirectoryName(Registries.Registry<?> registry){
        return "tags/" + TAG_DIRECTORIES.computeIfAbsent(registry, r -> {
            String folder = ((ForgeRegistry<?>)r.getForgeRegistry()).getTagFolder();
            if(folder != null && folder.startsWith("tags/"))
                folder = folder.substring("tags/".length());
            return folder;
        });
    }

    /**
     * Gets a tag builder for the given identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param identifier resource location of the tag
     */
    protected <T> TagBuilder<T> tag(Registries.Registry<T> registry, ResourceLocation identifier){
        this.cache.trackToBeGeneratedResource(ResourceType.DATA, identifier.getNamespace(), getTagDirectoryName(registry), identifier.getPath(), ".json");
        //noinspection unchecked
        return (TagBuilder<T>)this.tags.computeIfAbsent(registry, o -> new HashMap<>()).computeIfAbsent(identifier, identifier1 -> new TagBuilder<>(registry, identifier1));
    }

    /**
     * Gets a tag builder for the given key. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param tag key of the tag
     */
    protected <T> TagBuilder<T> tag(Registries.Registry<T> registry, ITag.INamedTag<T> tag){
        return this.tag(registry, tag.getName());
    }

    /**
     * Gets a tag builder for the given namespace and identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param namespace  namespace of the tag's identifier
     * @param identifier path of the tag's identifier
     */
    protected <T> TagBuilder<T> tag(Registries.Registry<T> registry, String namespace, String identifier){
        return this.tag(registry, new ResourceLocation(namespace, identifier));
    }

    /**
     * Gets a tag builder for the given identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param identifier path of the tag's identifier
     */
    protected <T> TagBuilder<T> tag(Registries.Registry<T> registry, String identifier){
        return this.tag(registry, this.modid, identifier);
    }

    /**
     * Gets a tag builder for the given identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param identifier resource location of the tag
     */
    protected TagBuilder<Block> blockTag(ResourceLocation identifier){
        return this.tag(Registries.BLOCKS, identifier);
    }

    /**
     * Gets a tag builder for the given key. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param tag key of the tag
     */
    protected TagBuilder<Block> blockTag(ITag.INamedTag<Block> tag){
        return this.tag(Registries.BLOCKS, tag);
    }

    /**
     * Gets a tag builder for the given namespace and identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param namespace  namespace of the tag's identifier
     * @param identifier path of the tag's identifier
     */
    protected TagBuilder<Block> blockTag(String namespace, String identifier){
        return this.tag(Registries.BLOCKS, namespace, identifier);
    }

    /**
     * Gets a tag builder for the given identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param identifier path of the tag's identifier
     */
    protected TagBuilder<Block> blockTag(String identifier){
        return this.tag(Registries.BLOCKS, identifier);
    }

    /**
     * Gets a tag builder for the given identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param identifier resource location of the tag
     */
    protected TagBuilder<Item> itemTag(ResourceLocation identifier){
        return this.tag(Registries.ITEMS, identifier);
    }

    /**
     * Gets a tag builder for the given key. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param tag key of the tag
     */
    protected TagBuilder<Item> itemTag(ITag.INamedTag<Item> tag){
        return this.tag(Registries.ITEMS, tag);
    }

    /**
     * Gets a tag builder for the given namespace and identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param namespace  namespace of the tag's identifier
     * @param identifier path of the tag's identifier
     */
    protected TagBuilder<Item> itemTag(String namespace, String identifier){
        return this.tag(Registries.ITEMS, namespace, identifier);
    }

    /**
     * Gets a tag builder for the given identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param identifier path of the tag's identifier
     */
    protected TagBuilder<Item> itemTag(String identifier){
        return this.tag(Registries.ITEMS, identifier);
    }

    /**
     * Gets a tag builder for the given identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param identifier resource location of the tag
     */
    protected TagBuilder<EntityType<?>> entityTag(ResourceLocation identifier){
        return this.tag(Registries.ENTITY_TYPES, identifier);
    }

    /**
     * Gets a tag builder for the given key. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param tag key of the tag
     */
    protected TagBuilder<EntityType<?>> entityTag(ITag.INamedTag<EntityType<?>> tag){
        return this.tag(Registries.ENTITY_TYPES, tag);
    }

    /**
     * Gets a tag builder for the given namespace and identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param namespace  namespace of the tag's identifier
     * @param identifier path of the tag's identifier
     */
    protected TagBuilder<EntityType<?>> entityTag(String namespace, String identifier){
        return this.tag(Registries.ENTITY_TYPES, namespace, identifier);
    }

    /**
     * Gets a tag builder for the given identifier. The returned tag builder may be a new tag builder or an existing one if requested before.
     * @param identifier path of the tag's identifier
     */
    protected TagBuilder<EntityType<?>> entityTag(String identifier){
        return this.tag(Registries.ENTITY_TYPES, identifier);
    }

    /**
     * Gets a tag builder for the 'minecraft:mineable/axe' tag.
     */
    protected TagBuilder<Block> blockMineableWithAxe(){
        return this.blockTag("minecraft", "mineable/axe");
    }

    /**
     * Gets a tag builder for the 'minecraft:mineable/hoe' tag.
     */
    protected TagBuilder<Block> blockMineableWithHoe(){
        return this.blockTag("minecraft", "mineable/hoe");
    }

    /**
     * Gets a tag builder for the 'minecraft:mineable/pickaxe' tag.
     */
    protected TagBuilder<Block> blockMineableWithPickaxe(){
        return this.blockTag("minecraft", "mineable/pickaxe");
    }

    /**
     * Gets a tag builder for the 'minecraft:mineable/shovel' tag.
     */
    protected TagBuilder<Block> blockMineableWithShovel(){
        return this.blockTag("minecraft", "mineable/shovel");
    }

    /**
     * Gets a tag builder for the 'minecraft:needs_stone_tool' tag.
     */
    protected TagBuilder<Block> blockNeedsStoneTool(){
        return this.blockTag("minecraft", "needs_stone_tool");
    }

    /**
     * Gets a tag builder for the 'minecraft:needs_iron_tool' tag.
     */
    protected TagBuilder<Block> blockNeedsIronTool(){
        return this.blockTag("minecraft", "needs_iron_tool");
    }

    /**
     * Gets a tag builder for the 'minecraft:needs_diamond_tool' tag.
     */
    protected TagBuilder<Block> blockNeedsDiamondTool(){
        return this.blockTag("minecraft", "needs_diamond_tool");
    }

    @Override
    public String getName(){
        return this.modName + " Tag Generator";
    }

    protected static class TagBuilder<T> {

        private final Registries.Registry<T> registry;
        protected final ResourceLocation identifier;
        private final Set<ITag.ITagEntry> entries = new HashSet<>();
        private final Set<ITag.ITagEntry> remove = new HashSet<>();
        private boolean replace;

        protected TagBuilder(Registries.Registry<T> registry, ResourceLocation identifier){
            this.registry = registry;
            this.identifier = identifier;
        }

        /**
         * Set whether to replace tag files lower in the datapack order. By default, this is set to {@code false}.
         * @param replace whether to overwrite tag files lower in the datapack order
         */
        public TagBuilder<T> replace(boolean replace){
            this.replace = replace;
            return this;
        }

        /**
         * Sets to replace tag files lower in the datapack order. By default, this is not the case.
         */
        public TagBuilder<T> replace(){
            return this.replace(true);
        }

        /**
         * Adds an entry to this tag.
         * @param entry entry to be added
         */
        public TagBuilder<T> add(T entry){
            this.entries.add(new ITag.ItemEntry(this.registry.getIdentifier(entry)));
            return this;
        }

        /**
         * Adds an entry to this tag.
         * @param entry entry to be added
         */
        public TagBuilder<T> add(ResourceLocation entry){
            if(!this.registry.hasIdentifier(entry))
                throw new RuntimeException("Could not find any object registered under '" + entry + "'!");

            this.entries.add(new ITag.ItemEntry(entry));
            return this;
        }

        /**
         * Adds an entry to this tag.
         * @param namespace  namespace of the entry to be added
         * @param identifier path of the entry to be added
         */
        public TagBuilder<T> add(String namespace, String identifier){
            if(!RegistryUtil.isValidNamespace(namespace))
                throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
            if(!RegistryUtil.isValidPath(identifier))
                throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

            this.add(new ResourceLocation(namespace, identifier));
            return this;
        }

        /**
         * Adds an entry to this tag.
         * @param entry entry to be added, must be a valid identifier
         */
        public TagBuilder<T> add(String entry){
            if(!RegistryUtil.isValidIdentifier(entry))
                throw new IllegalArgumentException("Entry identifier '" + entry + "' contains invalid characters!");

            this.add(new ResourceLocation(entry));
            return this;
        }

        /**
         * Adds an optional entry to this tag. The entry can be absent when the tag is loaded without an error being thrown.
         * @param entry entry to be added
         */
        public TagBuilder<T> addOptional(T entry){
            this.entries.add(new ITag.OptionalItemEntry(this.registry.getIdentifier(entry)));
            return this;
        }

        /**
         * Adds an optional entry to this tag. The entry can be absent when the tag is loaded without an error being thrown.
         * @param entry entry to be added
         */
        public TagBuilder<T> addOptional(ResourceLocation entry){
            this.entries.add(new ITag.OptionalItemEntry(entry));
            return this;
        }

        /**
         * Adds an optional entry to this tag. The entry can be absent when the tag is loaded without an error being thrown.
         * @param namespace  namespace of the entry to be added
         * @param identifier path of the entry to be added
         */
        public TagBuilder<T> addOptional(String namespace, String identifier){
            if(!RegistryUtil.isValidNamespace(namespace))
                throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
            if(!RegistryUtil.isValidPath(identifier))
                throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

            this.addOptional(new ResourceLocation(namespace, identifier));
            return this;
        }

        /**
         * Adds an optional entry to this tag. The entry can be absent when the tag is loaded without an error being thrown.
         * @param entry entry to be added
         */
        public TagBuilder<T> addOptional(String entry){
            if(!RegistryUtil.isValidIdentifier(entry))
                throw new IllegalArgumentException("Identifier '" + entry + "' contains invalid characters!");

            this.addOptional(new ResourceLocation(entry));
            return this;
        }

        /**
         * Adds an optional custom entry to this tag.
         * @param entry entry to be added
         */
        public TagBuilder<T> addOptional(CustomTagEntry entry){
            this.entries.add(CustomTagEntry.createVanillaEntry(entry));
            return this;
        }

        /**
         * Adds a reference to the given tag.
         */
        public TagBuilder<T> addReference(ResourceLocation tag){
            if(this.identifier.equals(tag))
                throw new IllegalArgumentException("Cannot add self reference to tag '" + tag + "'!");

            this.entries.add(new ITag.TagEntry(tag));
            return this;
        }

        /**
         * Adds a reference to the given tag.
         */
        public TagBuilder<T> addReference(ITag.INamedTag<T> tag){
            return this.addReference(tag.getName());
        }

        /**
         * Adds a reference to the given tag.
         */
        public TagBuilder<T> addReference(String namespace, String identifier){
            if(!RegistryUtil.isValidNamespace(namespace))
                throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
            if(!RegistryUtil.isValidPath(identifier))
                throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

            this.entries.add(new ITag.TagEntry(new ResourceLocation(namespace, identifier)));
            return this;
        }

        /**
         * Adds a reference to the given tag.
         */
        public TagBuilder<T> addReference(String tag){
            if(!RegistryUtil.isValidIdentifier(tag))
                throw new IllegalArgumentException("Tag identifier '" + tag + "' contains invalid characters!");

            this.entries.add(new ITag.TagEntry(new ResourceLocation(tag)));
            return this;
        }

        /**
         * Adds an optional reference to the given tag.
         */
        public TagBuilder<T> addOptionalReference(ResourceLocation tag){
            if(this.identifier.equals(tag))
                throw new IllegalArgumentException("Cannot add self reference to tag '" + tag + "'!");

            this.entries.add(new ITag.OptionalTagEntry(tag));
            return this;
        }

        /**
         * Adds a reference to the given tag.
         */
        public TagBuilder<T> addOptionalReference(ITag.INamedTag<T> tag){
            return this.addOptionalReference(tag.getName());
        }

        /**
         * Adds a reference to the given tag.
         */
        public TagBuilder<T> addOptionalReference(String namespace, String identifier){
            if(!RegistryUtil.isValidNamespace(namespace))
                throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
            if(!RegistryUtil.isValidPath(identifier))
                throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

            this.addOptionalReference(new ResourceLocation(namespace, identifier));
            return this;
        }

        /**
         * Adds an optional reference to the given tag.
         */
        public TagBuilder<T> addOptionalReference(String tag){
            if(!RegistryUtil.isValidIdentifier(tag))
                throw new IllegalArgumentException("Tag identifier '" + tag + "' contains invalid characters!");

            this.addOptionalReference(new ResourceLocation(tag));
            return this;
        }

        /**
         * Adds an entry to be removed from the tag files lower in the datapack order. Has no effect if {@link #replace(boolean)} is set to {@code true}.
         * @param entry entry to be removed
         */
        public TagBuilder<T> remove(T entry){
            this.remove.add(new ITag.ItemEntry(this.registry.getIdentifier(entry)));
            return this;
        }

        /**
         * Adds an entry to be removed from the tag files lower in the datapack order. Has no effect if {@link #replace(boolean)} is set to {@code true}.
         * @param entry entry to be removed
         */
        public TagBuilder<T> remove(ResourceLocation entry){
            if(!this.registry.hasIdentifier(entry))
                throw new RuntimeException("Could not find any object registered under '" + entry + "'!");

            this.remove.add(new ITag.ItemEntry(entry));
            return this;
        }

        /**
         * Adds an entry to be removed from the tag files lower in the datapack order. Has no effect if {@link #replace(boolean)} is set to {@code true}.
         * @param namespace  namespace of the entry to be removed
         * @param identifier path of the entry to be removed
         */
        public TagBuilder<T> remove(String namespace, String identifier){
            if(!RegistryUtil.isValidNamespace(namespace))
                throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
            if(!RegistryUtil.isValidPath(identifier))
                throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

            this.remove(new ResourceLocation(namespace, identifier));
            return this;
        }

        /**
         * Adds an entry to be removed from the tag files lower in the datapack order. Has no effect if {@link #replace(boolean)} is set to {@code true}.
         * @param entry entry to be removed
         */
        public TagBuilder<T> remove(String entry){
            if(!RegistryUtil.isValidIdentifier(entry))
                throw new IllegalArgumentException("Entry identifier '" + entry + "' contains invalid characters!");

            this.remove(new ResourceLocation(entry));
            return this;
        }

        /**
         * Adds an entry to be removed from the tag files lower in the datapack order. Has no effect if {@link #replace(boolean)} is set to {@code true}.
         * @param entry entry to be removed
         */
        public TagBuilder<T> removeOptional(T entry){
            return this.removeOptional(this.registry.getIdentifier(entry));
        }

        /**
         * Adds an entry to be removed from the tag files lower in the datapack order. Has no effect if {@link #replace(boolean)} is set to {@code true}.
         * @param entry entry to be removed
         */
        public TagBuilder<T> removeOptional(ResourceLocation entry){
            this.remove.add(new ITag.OptionalItemEntry(entry));
            return this;
        }

        /**
         * Adds an entry to be removed from the tag files lower in the datapack order. Has no effect if {@link #replace(boolean)} is set to {@code true}.
         * @param namespace  namespace of the entry to be removed
         * @param identifier path of the entry to be removed
         */
        public TagBuilder<T> removeOptional(String namespace, String identifier){
            if(!RegistryUtil.isValidNamespace(namespace))
                throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
            if(!RegistryUtil.isValidPath(identifier))
                throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

            this.removeOptional(new ResourceLocation(namespace, identifier));
            return this;
        }

        /**
         * Adds an entry to be removed from the tag files lower in the datapack order. Has no effect if {@link #replace(boolean)} is set to {@code true}.
         * @param entry entry to be removed
         */
        public TagBuilder<T> removeOptional(String entry){
            if(!RegistryUtil.isValidIdentifier(entry))
                throw new IllegalArgumentException("Identifier '" + entry + "' contains invalid characters!");

            this.removeOptional(new ResourceLocation(entry));
            return this;
        }

        private void addAll(TagBuilder<T> other){
            this.entries.addAll(other.entries);
            this.remove.addAll(other.remove);
        }
    }

    private static void sortJsonArray(JsonArray array){
        List<JsonElement> elements = new ArrayList<>(array.size());
        for(JsonElement element : array)
            elements.add(element);
        elements.sort(TagGenerator::compareJson);
        for(int i = 0; i < elements.size(); i++)
            array.set(i, elements.get(i));
    }

    private static int compareJson(JsonElement element1, JsonElement element2){
        if(element1.isJsonNull()){
            if(!element2.isJsonNull())
                return -1;
            return 0;
        }else if(element2.isJsonNull())
            return 1;
        if(element1.isJsonPrimitive()){
            if(!element2.isJsonPrimitive())
                return -1;
            JsonPrimitive primitive1 = element1.getAsJsonPrimitive();
            JsonPrimitive primitive2 = element2.getAsJsonPrimitive();
            if(primitive1.isString()){
                if(!primitive2.isString())
                    return -1;
                return primitive1.getAsString().compareTo(primitive2.getAsString());
            }else if(primitive2.isString())
                return 1;
            if(primitive1.isNumber()){
                if(!primitive2.isNumber())
                    return -1;
                return Double.compare(primitive1.getAsDouble(), primitive2.getAsDouble());
            }else if(primitive2.isNumber())
                return 1;
            if(primitive1.isBoolean()){
                if(!primitive2.isBoolean())
                    return -1;
            }else if(primitive2.isBoolean())
                return 1;
        }else if(element2.isJsonPrimitive())
            return 1;
        if(element1.isJsonObject()){
            if(!element2.isJsonObject())
                return -1;
            JsonObject object1 = element1.getAsJsonObject();
            JsonObject object2 = element2.getAsJsonObject();
            if(object1.size() != object2.size())
                return object1.size() - object2.size();
            Iterator<Map.Entry<String,JsonElement>> iterator1 = object1.entrySet().iterator();
            Iterator<Map.Entry<String,JsonElement>> iterator2 = object2.entrySet().iterator();
            while(iterator1.hasNext() && iterator2.hasNext()){
                Map.Entry<String,JsonElement> entry1 = iterator1.next();
                Map.Entry<String,JsonElement> entry2 = iterator2.next();
                if(!entry1.getKey().equals(entry2.getKey()))
                    return entry1.getKey().compareTo(entry2.getKey());
                int compare = compareJson(entry1.getValue(), entry2.getValue());
                if(compare != 0)
                    return compare;
            }
            if(iterator1.hasNext() || iterator2.hasNext())
                throw new AssertionError();
            return 0;
        }else if(element2.isJsonObject())
            return 1;
        if(element1.isJsonArray()){
            if(!element2.isJsonArray())
                return -1;
            JsonArray array1 = element1.getAsJsonArray();
            JsonArray array2 = element2.getAsJsonArray();
            if(array1.size() != array2.size())
                return array1.size() - array2.size();
            for(int i = 0; i < array1.size(); i++){
                int compare = compareJson(array1.get(i), array2.get(i));
                if(compare != 0)
                    return compare;
            }
            return 0;
        }else if(element2.isJsonArray())
            return 1;
        throw new AssertionError("Unknown json element type '" + element1.getClass() + "'!");
    }
}
```

### src/main/java/com/supermartijn642/core/gui/BaseContainer.java

```java
package com.supermartijn642.core.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.world.World;

/**
 * Created 1/19/2021 by SuperMartijn642
 */
public abstract class BaseContainer extends Container {

    public final PlayerEntity player;
    public final World level;

    public BaseContainer(BaseContainerType<?> type, PlayerEntity player){
        super(type, 0);
        this.player = player;
        this.level = player.level;
    }

    public void setContainerId(int id){
        this.containerId = id;
    }

    public BaseContainerType<?> getContainerType(){
        return (BaseContainerType<?>)this.getType();
    }

    /**
     * Adds slots to the container by calling {@link #addSlots(PlayerEntity)}.
     */
    protected void addSlots(){
        this.addSlots(this.player);
    }

    /**
     * Adds slots to the container
     */
    protected abstract void addSlots(PlayerEntity player);

    /**
     * Adds the player's slots to the container at the given {@code x} and {@code y}.
     * @param x the x-coordinate of the left side of the left most slots
     * @param y the y-coordinate of the top edge of the top most slots
     */
    protected void addPlayerSlots(int x, int y){
        // player
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 9; column++){
                this.addSlot(
                    CustomSlot.builder()
                        .position(x + 18 * column, y + 18 * row)
                        .playerInventory(row * 9 + column + 9, this.player.inventory)
                        .build()
                        .getVanillaSlot()
                );
            }
        }

        // hot bar
        for(int column = 0; column < 9; column++)
            this.addSlot(
                CustomSlot.builder()
                    .position(x + 18 * column, y + 58)
                    .playerInventory(column, this.player.inventory)
                    .build()
                    .getVanillaSlot()
            );
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn){
        return true;
    }
}
```

### src/main/java/com/supermartijn642/core/gui/BaseContainerType.java

```java
package com.supermartijn642.core.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.IContainerFactory;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Created 05/08/2022 by SuperMartijn642
 */
public final class BaseContainerType<T extends BaseContainer> extends ContainerType<T> {

    /**
     * Creates a new container type.
     * @param containerSerializer   used to write container's data for the client
     * @param containerDeserializer used to read data from the server to create a container
     */
    public static <T extends BaseContainer> BaseContainerType<T> create(BiConsumer<T,PacketBuffer> containerSerializer, BiFunction<PlayerEntity,PacketBuffer,T> containerDeserializer){
        return new BaseContainerType<>(containerSerializer, containerDeserializer);
    }

    private final BiConsumer<T,PacketBuffer> containerSerializer;
    private final BiFunction<PlayerEntity,PacketBuffer,T> containerDeserializer;

    private BaseContainerType(BiConsumer<T,PacketBuffer> containerSerializer, BiFunction<PlayerEntity,PacketBuffer,T> containerDeserializer){
        super((IContainerFactory<T>)(id, inventory, data) -> {
            T container = containerDeserializer.apply(inventory.player, data);
            container.setContainerId(id);
            return container;
        });
        this.containerSerializer = containerSerializer;
        this.containerDeserializer = containerDeserializer;
    }

    public void writeContainer(T container, PacketBuffer buffer){
        this.containerSerializer.accept(container, buffer);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/BlockEntityBaseContainer.java

```java
package com.supermartijn642.core.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created 1/26/2021 by SuperMartijn642
 */
public abstract class BlockEntityBaseContainer<T extends TileEntity> extends ObjectBaseContainer<T> {

    protected final World blockEntityLevel;
    protected final BlockPos blockEntityPos;

    public BlockEntityBaseContainer(BaseContainerType<?> type, PlayerEntity player, World blockEntityLevel, BlockPos blockEntityPos){
        super(type, player);
        this.blockEntityLevel = blockEntityLevel;
        this.blockEntityPos = blockEntityPos;
    }

    public BlockEntityBaseContainer(BaseContainerType<?> type, PlayerEntity player, BlockPos blockEntityPos){
        this(type, player, player.level, blockEntityPos);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T getObject(T oldObject){
        TileEntity entity = this.blockEntityLevel.getBlockEntity(this.blockEntityPos);
        if(entity == null)
            return null;

        try{
            return (T)entity;
        }catch(ClassCastException ignore){}
        return null;
    }

    @Override
    protected boolean validateObject(T object){
        return object != null && !object.isRemoved();
    }
}
```

### src/main/java/com/supermartijn642/core/gui/CursorType.java

```java
package com.supermartijn642.core.gui;

import org.lwjgl.glfw.GLFW;

/**
 * Created 15/01/2026 by SuperMartijn642
 */
public final class CursorType {

    private final int glfwCursorEnum;
    private long cursorHandle;
    private boolean created = false;

    CursorType(int glfwCursorEnum){
        this.glfwCursorEnum = glfwCursorEnum;
    }

    long getCursorHandle(){
        if(!this.created){
            this.cursorHandle = GLFW.glfwCreateStandardCursor(this.glfwCursorEnum);
            this.created = true;
        }
        return this.cursorHandle;
    }
}
```

### src/main/java/com/supermartijn642/core/gui/CursorTypes.java

```java
package com.supermartijn642.core.gui;


import com.supermartijn642.core.ClientUtils;
import org.lwjgl.glfw.GLFW;

/**
 * Created 10/01/2026 by SuperMartijn642
 */
public class CursorTypes {

    static CursorType pendingCursor;
    private static long lastCursorHandle;

    /**
     * Internal method
     */
    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public static void applyPending(){
        long handle = pendingCursor == null ? 0 : pendingCursor.getCursorHandle();
        if(handle != lastCursorHandle){
            GLFW.glfwSetCursor(ClientUtils.getMinecraft().getWindow().getWindow(), handle);
            lastCursorHandle = handle;
        }
        pendingCursor = null;
    }

    private static final CursorType ARROW = new CursorType(GLFW.GLFW_ARROW_CURSOR);
    private static final CursorType IBEAM = new CursorType(GLFW.GLFW_IBEAM_CURSOR);
    private static final CursorType CROSSHAIR = new CursorType(GLFW.GLFW_CROSSHAIR_CURSOR);
    private static final CursorType POINTING_HAND = new CursorType(GLFW.GLFW_HAND_CURSOR);
    private static final CursorType RESIZE_VERTICAL = new CursorType(GLFW.GLFW_VRESIZE_CURSOR);
    private static final CursorType RESIZE_HORIZONTAL = new CursorType(GLFW.GLFW_HRESIZE_CURSOR);

    public static CursorType arrow(){
        return ARROW;
    }

    public static CursorType iBeam(){
        return IBEAM;
    }

    public static CursorType crosshair(){
        return CROSSHAIR;
    }

    public static CursorType pointingHand(){
        return POINTING_HAND;
    }

    public static CursorType resizeVertical(){
        return RESIZE_VERTICAL;
    }

    public static CursorType resizeHorizontal(){
        return RESIZE_HORIZONTAL;
    }
}
```

### src/main/java/com/supermartijn642/core/gui/CustomSlot.java

```java
package com.supermartijn642.core.gui;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.*;

/**
 * Created 29/01/2023 by SuperMartijn642
 */
@ApiStatus.NonExtendable
public interface CustomSlot {

    static Builder builder(){
        return CustomSlotImpl.builder();
    }

    Slot getVanillaSlot();

    /**
     * Moves this slot to the given position.
     */
    void move(int x, int y);

    /**
     * Whether this slot is rendered and interactable.
     */
    boolean isActive();

    /**
     * Sets whether this slot is rendered and interactable.
     */
    void setActive(boolean active);

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    boolean scaleItemToSize();

    boolean showBackground();

    boolean showItem();

    boolean showHighlight();

    ItemStack getItem();

    interface Builder {

        Builder position(int x, int y);

        Builder size(int width, int height);

        Builder size(int size);

        /**
         * Used to query the item stack currently in the slot.
         */
        Builder getter(Supplier<ItemStack> getter);

        /**
         * Used to overwrite the item stack currently in the slot.
         */
        Builder setter(Consumer<ItemStack> getter);

        /**
         * Used to insert items into the slot. The given function should return the amount that was inserted.
         */
        Builder inserter(ToIntFunction<ItemStack> inserter);

        /**
         * Used to insert items into the slot. The given function should return the items that were extracted.
         */
        Builder extractor(Function<Integer,ItemStack> extractor);

        Builder capacity(ToIntFunction<ItemStack> capacity);

        /**
         * Filter for items that can be inserted into the slot.
         * <p>
         * Note that the setter is not affected by this filter.
         */
        Builder filter(Predicate<ItemStack> filter);

        Builder onInsert(SlotChangeListener onInsert);

        Builder onExtract(SlotChangeListener onExtract);

        Builder onChange(SlotChangeListener onChange);

        Builder vanillaContainer(int index, IInventory container);

        Builder playerInventory(int index, PlayerInventory inventory);

        default Builder itemHandler(Supplier<IItemHandler> handlerSupplier){
            return this.itemHandler(0, handlerSupplier);
        }

        Builder itemHandler(int index, Supplier<IItemHandler> handlerSupplier);

        /**
         * Note that the setter is not affected by this flag.
         */
        Builder canInsert(boolean canInsert);

        /**
         * Note that the setter is not affected by this flag.
         */
        Builder canExtract(boolean canExtract);

        /**
         * Note that the setter is not affected by this flag.
         */
        Builder canInsertExtract(boolean mutable);

        Builder scaleItemToSize(boolean scaleItemToSize);

        Builder scaleItemToSize();

        Builder showBackground(boolean showBackground);

        Builder noBackground();

        Builder showItem(boolean showItem);

        Builder showHighlight(boolean showHighlight);

        CustomSlot build();
    }

    interface SlotChangeListener {
        void onChange(ItemStack oldStack, ItemStack newStack);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/CustomSlotImpl.java

```java
package com.supermartijn642.core.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.function.*;

/**
 * Created 08/01/2026 by SuperMartijn642
 */
public class CustomSlotImpl implements CustomSlot {

    static Builder builder(){
        return new BuilderImpl();
    }

    private static final IInventory EMPTY_CONTAINER = new IInventory() {
        @Override
        public int getContainerSize(){
            return 0;
        }

        @Override
        public boolean isEmpty(){
            return true;
        }

        @Override
        public ItemStack getItem(int i){
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeItem(int i, int j){
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeItemNoUpdate(int i){
            return ItemStack.EMPTY;
        }

        @Override
        public void setItem(int i, ItemStack itemStack){
        }

        @Override
        public void setChanged(){
        }

        @Override
        public boolean stillValid(PlayerEntity player){
            return false;
        }

        @Override
        public void clearContent(){
        }
    };

    /*
     * We have to separate the vanilla Slot implementation from the CustomSlot implementation
     * Otherwise, ForgeGradle will obfuscate CustomSlot#isActive to Slot#isActive eventhough the interface method has nothing to do with Slot
     * https://github.com/SuperMartijn642/TrashCans/issues/56
     */

    private final VanillaSlot vanillaSlot;
    private final int width, height;
    private final Supplier<ItemStack> getter;
    private final Consumer<ItemStack> setter;
    private final ToIntFunction<ItemStack> inserter;
    private final Function<Integer,ItemStack> extractor;
    private final ToIntFunction<ItemStack> capacity;
    private final Predicate<ItemStack> filter;
    private final SlotChangeListener onInsert, onExtract;
    private final boolean canInsert, canExtract;
    private final boolean scaleItemToSize;
    private final boolean showBackground, showItem, showHighlight;

    private boolean active = true;

    private CustomSlotImpl(IInventory vanillaContainer, int vanillaSlot, int x, int y, int width, int height, Supplier<ItemStack> getter, Consumer<ItemStack> setter, ToIntFunction<ItemStack> inserter, Function<Integer,ItemStack> extractor, ToIntFunction<ItemStack> capacity, Predicate<ItemStack> filter, SlotChangeListener onInsert, SlotChangeListener onExtract, boolean canInsert, boolean canExtract, boolean scaleItemToSize, boolean showBackground, boolean showItem, boolean showHighlight){
        this.vanillaSlot = new VanillaSlot(vanillaContainer, vanillaSlot, x, y);
        this.width = width;
        this.height = height;
        this.getter = getter == null ? () -> ItemStack.EMPTY : getter;
        this.setter = setter == null ? stack -> {} : setter;
        this.inserter = inserter == null ? setter == null ? stack -> 0 : stack -> {
            if(stack.isEmpty())
                return 0;
            ItemStack currentStack = this.getter.get();
            if(!currentStack.isEmpty() && !Container.consideredTheSameItem(stack, currentStack))
                return 0;
            int inserted = Math.min(stack.getCount(), stack.getMaxStackSize() - currentStack.getCount());
            if(capacity != null)
                inserted = Math.min(inserted, capacity.applyAsInt(stack));
            setter.accept(copyWithCount(stack, currentStack.getCount() + inserted));
            return inserted;
        } : inserter;
        this.extractor = extractor == null ? setter == null ? amount -> ItemStack.EMPTY : amount -> {
            if(amount <= 0)
                return ItemStack.EMPTY;
            ItemStack currentStack = this.getter.get();
            if(currentStack.isEmpty())
                return ItemStack.EMPTY;
            int extracted = Math.min(amount, currentStack.getCount());
            ItemStack extractedStack = copyWithCount(currentStack, extracted);
            setter.accept(copyWithCount(currentStack, currentStack.getCount() - extracted));
            return extractedStack;
        } : extractor;
        this.capacity = capacity == null ? stack -> stack.isEmpty() ? 64 : stack.getMaxStackSize() : capacity;
        this.filter = filter == null ? stack -> true : filter;
        this.onInsert = onInsert == null ? (oldStack, newStack) -> {} : onInsert;
        this.onExtract = onExtract == null ? (oldStack, newStack) -> {} : onExtract;
        this.canInsert = canInsert;
        this.canExtract = canExtract;
        this.scaleItemToSize = scaleItemToSize;
        this.showBackground = showBackground;
        this.showItem = showItem;
        this.showHighlight = showHighlight;
    }

    @Override
    public Slot getVanillaSlot(){
        return this.vanillaSlot;
    }

    @Override
    public void move(int x, int y){
        this.vanillaSlot.x = x;
        this.vanillaSlot.y = y;
    }

    @Override
    public boolean isActive(){
        return this.active;
    }

    @Override
    public void setActive(boolean active){
        this.active = active;
    }

    @Override
    public int getX(){
        return this.vanillaSlot.x;
    }

    @Override
    public int getY(){
        return this.vanillaSlot.y;
    }

    @Override
    public int getWidth(){
        return this.width;
    }

    @Override
    public int getHeight(){
        return this.height;
    }

    @Override
    public boolean scaleItemToSize(){
        return this.scaleItemToSize;
    }

    @Override
    public boolean showBackground(){
        return this.showBackground;
    }

    @Override
    public boolean showItem(){
        return this.showItem;
    }

    @Override
    public boolean showHighlight(){
        return this.showHighlight;
    }

    @Override
    public ItemStack getItem(){
        return this.vanillaSlot.getItem();
    }

    private class VanillaSlot extends Slot {

        /*
         * Minecraft (,1.16.5] just modify the slots returned stack rather than updating it explicitly.
         * As returned stacks may be arbitrary instances, we need to keep track and update the stack manually.
         */
        private ItemStack lastReturnedStack;
        private int lastReturnedStackCount = 0;

        public VanillaSlot(IInventory vanillaContainer, int vanillaSlot, int x, int y){
            super(vanillaContainer == null ? EMPTY_CONTAINER : vanillaContainer, vanillaSlot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack){
            return CustomSlotImpl.this.canInsert && CustomSlotImpl.this.filter.test(stack);
        }

        @Override
        public ItemStack getItem(){
            ItemStack stack = CustomSlotImpl.this.getter.get();
            this.lastReturnedStack = stack;
            this.lastReturnedStackCount = stack.getCount();
            return stack;
        }

        @Override
        public void set(ItemStack stack){
            ItemStack original = CustomSlotImpl.this.getter.get();
            CustomSlotImpl.this.setter.accept(stack);
            ItemStack newStack = CustomSlotImpl.this.getter.get();
            if(!ItemStack.matches(original, newStack)){
                if(newStack.isEmpty())
                    CustomSlotImpl.this.onExtract.onChange(original, newStack);
                else
                    CustomSlotImpl.this.onInsert.onChange(original, newStack);
            }
            this.lastReturnedStack = stack;
            this.lastReturnedStackCount = stack.getCount();
        }

        @Override
        public int getMaxStackSize(){
            return CustomSlotImpl.this.capacity.applyAsInt(ItemStack.EMPTY);
        }

        @Override
        public int getMaxStackSize(ItemStack stack){
            return CustomSlotImpl.this.capacity.applyAsInt(stack);
        }

        @Override
        public ItemStack remove(int amount){
            ItemStack original = CustomSlotImpl.this.getter.get();
            ItemStack extracted = CustomSlotImpl.this.extractor.apply(amount);
            ItemStack newStack = CustomSlotImpl.this.getter.get();
            if(!ItemStack.matches(original, newStack))
                CustomSlotImpl.this.onExtract.onChange(original, newStack);
            this.lastReturnedStack = null;
            return extracted;
        }

        @Override
        public boolean mayPickup(PlayerEntity player){
            return CustomSlotImpl.this.canExtract;
        }

        @Override
        public boolean isActive(){
            return CustomSlotImpl.this.active;
        }

        @Override
        public void setChanged(){
            // Check if the stack size of the last given stack was modified
            if(this.lastReturnedStack != null && this.lastReturnedStack.getCount() != this.lastReturnedStackCount)
                this.set(this.lastReturnedStack);
            super.setChanged();
        }
    }

    private static ItemStack copyWithCount(ItemStack stack, int count){
        ItemStack copy = stack.copy();
        copy.setCount(count);
        return copy;
    }

    private static class BuilderImpl implements Builder {

        private int x, y;
        private int width = 18, height = 18;
        private Supplier<ItemStack> getter;
        private Consumer<ItemStack> setter;
        private ToIntFunction<ItemStack> inserter;
        private Function<Integer,ItemStack> extractor;
        private ToIntFunction<ItemStack> capacity;
        private Predicate<ItemStack> filter;
        private SlotChangeListener onInsert, onExtract;
        private boolean canInsert = true, canExtract = true;
        private boolean scaleItemToSize = false;
        private boolean showBackground = true, showItem = true, showHighlight = true;

        /**
         * Set the original container field for the slot in case mods try to access it directly.
         */
        private IInventory vanillaContainer;
        private int vanillaSlot;

        private BuilderImpl(){
        }

        @Override
        public Builder position(int x, int y){
            this.x = x;
            this.y = y;
            return this;
        }

        @Override
        public Builder size(int width, int height){
            this.width = width;
            this.height = height;
            return this;
        }

        @Override
        public Builder size(int size){
            return this.size(size, size);
        }

        @Override
        public Builder getter(Supplier<ItemStack> getter){
            this.getter = getter;
            return this;
        }

        @Override
        public Builder setter(Consumer<ItemStack> getter){
            this.setter = getter;
            return this;
        }

        @Override
        public Builder inserter(ToIntFunction<ItemStack> inserter){
            this.inserter = inserter;
            return this;
        }

        @Override
        public Builder extractor(Function<Integer,ItemStack> extractor){
            this.extractor = extractor;
            return this;
        }

        @Override
        public Builder capacity(ToIntFunction<ItemStack> capacity){
            this.capacity = capacity;
            return this;
        }

        @Override
        public Builder filter(Predicate<ItemStack> filter){
            this.filter = filter;
            return this;
        }

        @Override
        public Builder onInsert(SlotChangeListener onInsert){
            this.onInsert = onInsert;
            return this;
        }

        @Override
        public Builder onExtract(SlotChangeListener onExtract){
            this.onExtract = onExtract;
            return this;
        }

        @Override
        public Builder onChange(SlotChangeListener onChange){
            return this.onInsert(onChange).onExtract(onChange);
        }

        @Override
        public Builder vanillaContainer(int index, IInventory container){
            this.getter(() -> container.getItem(index));
            this.setter(stack -> {
                container.setItem(index, stack);
                container.setChanged();
            });
            this.inserter(stack -> {
                if(stack.isEmpty())
                    return 0;
                ItemStack currentStack = container.getItem(index);
                int amount = Math.min(stack.getCount(), container.getMaxStackSize() - currentStack.getCount());
                if(amount <= 0 || (!currentStack.isEmpty() && !Container.consideredTheSameItem(stack, currentStack)))
                    return 0;
                container.setItem(index, copyWithCount(stack, currentStack.getCount() + amount));
                container.setChanged();
                return amount;
            });
            this.extractor(amount -> {
                ItemStack extracted = container.removeItem(index, amount);
                if(!extracted.isEmpty()){
                    if(container.getItem(index).isEmpty()) // For some reason vanilla explicitly sets the slot to empty
                        container.setItem(index, ItemStack.EMPTY);
                    container.setChanged();
                }
                return extracted;
            });
            this.capacity(stack -> container.getMaxStackSize());
            this.vanillaContainer = container;
            this.vanillaSlot = index;
            return this;
        }

        @Override
        public Builder playerInventory(int index, PlayerInventory inventory){
            return this.vanillaContainer(index, inventory);
        }

        @Override
        public Builder itemHandler(int index, Supplier<IItemHandler> handlerSupplier){
            this.getter(() -> handlerSupplier.get().getStackInSlot(0));
            this.inserter(stack -> {
                if(stack.isEmpty())
                    return 0;
                IItemHandler handler = handlerSupplier.get();
                int initialCount = stack.getCount();
                ItemStack leftover = handler.insertItem(0, stack, false);
                if(stack.getCount() != initialCount)
                    throw new RuntimeException("Item handler of class '" + handler.getClass() + "' modified input stack to #insertItem!");
                return initialCount - leftover.getCount();
            });
            this.extractor(amount -> {
                if(amount <= 0)
                    return ItemStack.EMPTY;
                IItemHandler handler = handlerSupplier.get();
                return handler.extractItem(index, amount, false);
            });
            this.capacity(stack -> handlerSupplier.get().getSlotLimit(0));
            return this;
        }

        @Override
        public Builder canInsert(boolean canInsert){
            this.canInsert = canInsert;
            return this;
        }

        @Override
        public Builder canExtract(boolean canExtract){
            this.canExtract = canExtract;
            return this;
        }

        @Override
        public Builder canInsertExtract(boolean mutable){
            return this.canExtract(mutable).canInsert(mutable);
        }

        @Override
        public Builder scaleItemToSize(boolean scaleItemToSize){
            this.scaleItemToSize = scaleItemToSize;
            return this;
        }

        @Override
        public Builder scaleItemToSize(){
            return this.scaleItemToSize(true);
        }

        @Override
        public Builder showBackground(boolean showBackground){
            this.showBackground = showBackground;
            return this;
        }

        @Override
        public Builder noBackground(){
            this.showBackground = false;
            return this;
        }

        @Override
        public Builder showItem(boolean showItem){
            this.showItem = showItem;
            return this;
        }

        @Override
        public Builder showHighlight(boolean showHighlight){
            this.showHighlight = showHighlight;
            return this;
        }

        @Override
        public CustomSlot build(){
            return new CustomSlotImpl(
                this.vanillaContainer, this.vanillaSlot,
                this.x, this.y,
                this.width, this.height,
                this.getter, this.setter,
                this.inserter, this.extractor,
                this.capacity,
                this.filter,
                this.onInsert, this.onExtract,
                this.canInsert, this.canExtract,
                this.scaleItemToSize,
                this.showBackground, this.showItem, this.showHighlight
            );
        }
    }
}
```

### src/main/java/com/supermartijn642/core/gui/ItemBaseContainer.java

```java
package com.supermartijn642.core.gui;

import com.supermartijn642.core.ClientUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created 1/26/2021 by SuperMartijn642
 */
public abstract class ItemBaseContainer extends ObjectBaseContainer<ItemStack> {

    private final Supplier<ItemStack> stackSupplier;
    protected final Predicate<ItemStack> stackValidator;

    private ItemBaseContainer(BaseContainerType<?> type, PlayerEntity player, Supplier<ItemStack> itemStackSupplier, Predicate<ItemStack> stackValidator){
        super(type, player, true);
        this.stackSupplier = itemStackSupplier;
        this.stackValidator = stackValidator;
    }

    protected ItemBaseContainer(BaseContainerType<?> type, PlayerEntity player, int playerSlot, Predicate<ItemStack> stackValidator){
        this(type, player, () -> player.inventory.getItem(playerSlot), stackValidator);
    }

    protected ItemBaseContainer(BaseContainerType<?> type, PlayerEntity player, Hand hand, Predicate<ItemStack> stackValidator){
        this(type, player, () -> ClientUtils.getPlayer().getItemInHand(hand), stackValidator);
    }

    @Override
    protected ItemStack getObject(ItemStack oldObject){
        return this.stackSupplier.get();
    }

    @Override
    protected boolean validateObject(ItemStack object){
        return object != null && this.stackValidator.test(object);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/ObjectBaseContainer.java

```java
package com.supermartijn642.core.gui;

import com.supermartijn642.core.CommonUtils;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nonnull;

/**
 * Created 1/26/2021 by SuperMartijn642
 */
public abstract class ObjectBaseContainer<T> extends BaseContainer {

    protected T object;
    private final boolean alwaysRenewObject;

    public ObjectBaseContainer(BaseContainerType<?> type, PlayerEntity player, boolean alwaysRenewObject){
        super(type, player);
        this.alwaysRenewObject = alwaysRenewObject;
    }

    public ObjectBaseContainer(BaseContainerType<?> type, PlayerEntity player){
        this(type, player, false);
    }

    @Override
    protected void addSlots(PlayerEntity player){
        if(this.validateObjectOrClose())
            this.addSlots(player, this.object);
    }

    /**
     * Adds slots to the container
     */
    protected abstract void addSlots(PlayerEntity player, @Nonnull T object);

    /**
     * Called to obtain object needed for this widget to remain active. May be called at any time.
     * @param oldObject the old object, will be {@code null} when the widget is first added
     * @return the object required for the container to remain open
     */
    protected abstract T getObject(T oldObject);

    /**
     * Validates the object obtained from {@link #getObject(Object)}.
     * The associated screen will be closed if {@code false} is returned.
     * @param object object to be validated, may be null
     * @return true if the object is valid
     */
    protected abstract boolean validateObject(T object);

    /**
     * Validates the object. If the object is not valid the screen will be closed.
     * @return true if the object is valid
     */
    protected boolean validateObjectOrClose(){
        if(this.alwaysRenewObject || !this.validateObject(this.object)){
            this.object = this.getObject(this.object);
            if(!this.validateObject(this.object)){
                CommonUtils.closeContainer(this.player);
                return false;
            }
        }
        return true;
    }
}
```

### src/main/java/com/supermartijn642/core/gui/ScreenUtils.java

```java
package com.supermartijn642.core.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.render.RenderUtils;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created 1/20/2021 by SuperMartijn642
 */
public class ScreenUtils {

    private static final ResourceLocation BUTTON_BACKGROUND = new ResourceLocation("supermartijn642corelib", "textures/gui/buttons.png");
    private static final ResourceLocation SCREEN_BACKGROUND = new ResourceLocation("supermartijn642corelib", "textures/gui/background.png");

    public static final int DEFAULT_TEXT_COLOR = 4210752, ACTIVE_TEXT_COLOR = 14737632, INACTIVE_TEXT_COLOR = 7368816;

    public static void drawString(MatrixStack matrixStack, FontRenderer fontRenderer, ITextComponent text, float x, float y, int color){
        fontRenderer.draw(matrixStack, text, x, y, color);
    }

    public static void drawString(MatrixStack matrixStack, FontRenderer fontRenderer, ITextComponent text, float x, float y){
        fontRenderer.draw(matrixStack, text, x, y, DEFAULT_TEXT_COLOR);
    }

    public static void drawString(MatrixStack matrixStack, ITextComponent text, float x, float y, int color){
        drawString(matrixStack, ClientUtils.getFontRenderer(), text, x, y, color);
    }

    public static void drawString(MatrixStack matrixStack, ITextComponent text, float x, float y){
        drawString(matrixStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawStringWithShadow(MatrixStack matrixStack, FontRenderer fontRenderer, ITextComponent text, float x, float y, int color){
        fontRenderer.drawShadow(matrixStack, text, x, y, color);
    }

    public static void drawStringWithShadow(MatrixStack matrixStack, FontRenderer fontRenderer, ITextComponent text, float x, float y){
        fontRenderer.drawShadow(matrixStack, text, x, y, DEFAULT_TEXT_COLOR);
    }

    public static void drawStringWithShadow(MatrixStack matrixStack, ITextComponent text, float x, float y, int color){
        drawStringWithShadow(matrixStack, ClientUtils.getFontRenderer(), text, x, y, color);
    }

    public static void drawStringWithShadow(MatrixStack matrixStack, ITextComponent text, float x, float y){
        drawStringWithShadow(matrixStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawCenteredString(MatrixStack matrixStack, FontRenderer fontRenderer, ITextComponent text, float x, float y, int color){
        fontRenderer.draw(matrixStack, text, x - fontRenderer.width(text) / 2f, y, color);
    }

    public static void drawCenteredString(MatrixStack matrixStack, FontRenderer fontRenderer, ITextComponent text, float x, float y){
        fontRenderer.draw(matrixStack, text, x - fontRenderer.width(text) / 2f, y, DEFAULT_TEXT_COLOR);
    }

    public static void drawCenteredString(MatrixStack matrixStack, ITextComponent text, float x, float y, int color){
        drawCenteredString(matrixStack, ClientUtils.getFontRenderer(), text, x, y, color);
    }

    public static void drawCenteredString(MatrixStack matrixStack, ITextComponent text, float x, float y){
        drawCenteredString(matrixStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawCenteredStringWithShadow(MatrixStack matrixStack, FontRenderer fontRenderer, ITextComponent text, float x, float y, int color){
        fontRenderer.drawShadow(matrixStack, text, x - fontRenderer.width(text) / 2f, y, color);
    }

    public static void drawCenteredStringWithShadow(MatrixStack matrixStack, FontRenderer fontRenderer, ITextComponent text, float x, float y){
        fontRenderer.drawShadow(matrixStack, text, x - fontRenderer.width(text) / 2f, y, DEFAULT_TEXT_COLOR);
    }

    public static void drawCenteredStringWithShadow(MatrixStack matrixStack, ITextComponent text, float x, float y, int color){
        drawCenteredStringWithShadow(matrixStack, ClientUtils.getFontRenderer(), text, x, y, color);
    }

    public static void drawCenteredStringWithShadow(MatrixStack matrixStack, ITextComponent text, float x, float y){
        drawCenteredStringWithShadow(matrixStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawString(MatrixStack matrixStack, FontRenderer fontRenderer, String text, float x, float y, int color){
        fontRenderer.draw(matrixStack, text, x, y, color);
    }

    public static void drawString(MatrixStack matrixStack, FontRenderer fontRenderer, String text, float x, float y){
        fontRenderer.draw(matrixStack, text, x, y, DEFAULT_TEXT_COLOR);
    }

    public static void drawString(MatrixStack matrixStack, String text, float x, float y, int color){
        drawString(matrixStack, ClientUtils.getFontRenderer(), text, x, y, color);
    }

    public static void drawString(MatrixStack matrixStack, String text, float x, float y){
        drawString(matrixStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawStringWithShadow(MatrixStack matrixStack, FontRenderer fontRenderer, String text, float x, float y, int color){
        fontRenderer.drawShadow(matrixStack, text, x - fontRenderer.width(text) / 2f, y, color);
    }

    public static void drawStringWithShadow(MatrixStack matrixStack, FontRenderer fontRenderer, String text, float x, float y){
        fontRenderer.drawShadow(matrixStack, text, x - fontRenderer.width(text) / 2f, y, DEFAULT_TEXT_COLOR);
    }

    public static void drawStringWithShadow(MatrixStack matrixStack, String text, float x, float y, int color){
        drawStringWithShadow(matrixStack, ClientUtils.getFontRenderer(), text, x, y, color);
    }

    public static void drawStringWithShadow(MatrixStack matrixStack, String text, float x, float y){
        drawStringWithShadow(matrixStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawCenteredString(MatrixStack matrixStack, FontRenderer fontRenderer, String text, float x, float y, int color){
        fontRenderer.draw(matrixStack, text, x - fontRenderer.width(text) / 2f, y, color);
    }

    public static void drawCenteredString(MatrixStack matrixStack, FontRenderer fontRenderer, String text, float x, float y){
        fontRenderer.draw(matrixStack, text, x - fontRenderer.width(text) / 2f, y, DEFAULT_TEXT_COLOR);
    }

    public static void drawCenteredString(MatrixStack matrixStack, String text, float x, float y, int color){
        drawCenteredString(matrixStack, ClientUtils.getFontRenderer(), text, x, y, color);
    }

    public static void drawCenteredString(MatrixStack matrixStack, String text, float x, float y){
        drawCenteredString(matrixStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawCenteredStringWithShadow(MatrixStack matrixStack, FontRenderer fontRenderer, String text, float x, float y, int color){
        fontRenderer.drawShadow(matrixStack, text, x - fontRenderer.width(text) / 2f, y, color);
    }

    public static void drawCenteredStringWithShadow(MatrixStack matrixStack, FontRenderer fontRenderer, String text, float x, float y){
        fontRenderer.drawShadow(matrixStack, text, x - fontRenderer.width(text) / 2f, y, DEFAULT_TEXT_COLOR);
    }

    public static void drawCenteredStringWithShadow(MatrixStack matrixStack, String text, float x, float y, int color){
        drawCenteredStringWithShadow(matrixStack, ClientUtils.getFontRenderer(), text, x, y, color);
    }

    public static void drawCenteredStringWithShadow(MatrixStack matrixStack, String text, float x, float y){
        drawCenteredStringWithShadow(matrixStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawScreenBackground(MatrixStack matrixStack, float x, float y, float width, float height){
        Minecraft.getInstance().textureManager.bind(SCREEN_BACKGROUND);
        // corners
        drawTexture(matrixStack, x, y, 4, 4, 0, 0, 4 / 9f, 4 / 9f);
        drawTexture(matrixStack, x + width - 4, y, 4, 4, 5 / 9f, 0, 4 / 9f, 4 / 9f);
        drawTexture(matrixStack, x + width - 4, y + height - 4, 4, 4, 5 / 9f, 5 / 9f, 4 / 9f, 4 / 9f);
        drawTexture(matrixStack, x, y + height - 4, 4, 4, 0, 5 / 9f, 4 / 9f, 4 / 9f);
        // edges
        drawTexture(matrixStack, x + 4, y, width - 8, 4, 4 / 9f, 0, 1 / 9f, 4 / 9f);
        drawTexture(matrixStack, x + 4, y + height - 4, width - 8, 4, 4 / 9f, 5 / 9f, 1 / 9f, 4 / 9f);
        drawTexture(matrixStack, x, y + 4, 4, height - 8, 0, 4 / 9f, 4 / 9f, 1 / 9f);
        drawTexture(matrixStack, x + width - 4, y + 4, 4, height - 8, 5 / 9f, 4 / 9f, 4 / 9f, 1 / 9f);
        // center
        drawTexture(matrixStack, x + 4, y + 4, width - 8, height - 8, 4 / 9f, 4 / 9f, 1 / 9f, 1 / 9f);
    }

    public static void drawButtonBackground(MatrixStack matrixStack, float x, float y, float width, float height, float yOffset){
        Minecraft.getInstance().getTextureManager().bind(BUTTON_BACKGROUND);
        // corners
        drawTexture(matrixStack, x, y, 2, 2, 0, yOffset, 2 / 5f, 2 / 15f);
        drawTexture(matrixStack, x + width - 2, y, 2, 2, 3 / 5f, yOffset, 2 / 5f, 2 / 15f);
        drawTexture(matrixStack, x + width - 2, y + height - 2, 2, 2, 3 / 5f, yOffset + 3 / 15f, 2 / 5f, 2 / 15f);
        drawTexture(matrixStack, x, y + height - 2, 2, 2, 0, yOffset + 3 / 15f, 2 / 5f, 2 / 15f);
        // edges
        drawTexture(matrixStack, x + 2, y, width - 4, 2, 2 / 5f, yOffset, 1 / 5f, 2 / 15f);
        drawTexture(matrixStack, x + 2, y + height - 2, width - 4, 2, 2 / 5f, yOffset + 3 / 15f, 1 / 5f, 2 / 15f);
        drawTexture(matrixStack, x, y + 2, 2, height - 4, 0, yOffset + 2 / 15f, 2 / 5f, 1 / 15f);
        drawTexture(matrixStack, x + width - 2, y + 2, 2, height - 4, 3 / 5f, yOffset + 2 / 15f, 2 / 5f, 1 / 15f);
        // center
        drawTexture(matrixStack, x + 2, y + 2, width - 4, height - 4, 2 / 5f, yOffset + 2 / 15f, 1 / 5f, 1 / 15f);
    }

    public static void drawTexture(MatrixStack matrixStack, float x, float y, float width, float height){
        drawTexture(matrixStack, x, y, width, height, 0, 0, 1, 1);
    }

    public static void drawTexture(MatrixStack matrixStack, float x, float y, float width, float height, float tx, float ty, float twidth, float theight){
        GlStateManager._color4f(1, 1, 1, 1);

        Matrix4f matrix = matrixStack.last().pose();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuilder();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.vertex(matrix, x, y + height, 0).uv(tx, ty + theight).endVertex();
        buffer.vertex(matrix, x + width, y + height, 0).uv(tx + twidth, ty + theight).endVertex();
        buffer.vertex(matrix, x + width, y, 0).uv(tx + twidth, ty).endVertex();
        buffer.vertex(matrix, x, y, 0).uv(tx, ty).endVertex();
        tessellator.end();
    }

    public static void fillRect(MatrixStack matrixStack, float x, float y, float width, float height, int color){
        float alpha = (float)(color >> 24 & 255) / 255.0F;
        float red = (float)(color >> 16 & 255) / 255.0F;
        float green = (float)(color >> 8 & 255) / 255.0F;
        float blue = (float)(color & 255) / 255.0F;
        fillRect(matrixStack, x, y, width, height, red, green, blue, alpha);
    }

    public static void fillRect(MatrixStack matrixStack, float x, float y, float width, float height, float red, float green, float blue, float alpha){
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();

        Matrix4f matrix = matrixStack.last().pose();
        Tessellator tesselator = Tessellator.getInstance();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.vertex(matrix, x, y + height, 0).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, x + width, y + height, 0).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, x + width, y, 0).color(red, green, blue, alpha).endVertex();
        buffer.vertex(matrix, x, y, 0).color(red, green, blue, alpha).endVertex();
        tesselator.end();

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void bindTexture(ResourceLocation location){
        Minecraft.getInstance().textureManager.bind(location);
    }

    public static void drawTooltip(MatrixStack poseStack, FontRenderer fontRenderer, List<ITextComponent> text, int x, int y){
        drawTooltipInternal(poseStack, fontRenderer, text.stream().map(ITextComponent::getVisualOrderText).collect(Collectors.toList()), x, y);
    }

    public static void drawTooltip(MatrixStack poseStack, FontRenderer fontRenderer, ITextComponent text, int x, int y){
        drawTooltip(poseStack, fontRenderer, Collections.singletonList(text), x, y);
    }

    public static void drawTooltip(MatrixStack poseStack, FontRenderer fontRenderer, String text, int x, int y){
        drawTooltip(poseStack, fontRenderer, new StringTextComponent(text), x, y);
    }

    public static void drawTooltip(MatrixStack poseStack, List<ITextComponent> text, int x, int y){
        drawTooltip(poseStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawTooltip(MatrixStack poseStack, ITextComponent text, int x, int y){
        drawTooltip(poseStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    public static void drawTooltip(MatrixStack poseStack, String text, int x, int y){
        drawTooltip(poseStack, ClientUtils.getFontRenderer(), text, x, y);
    }

    /**
     * Copied from {@link Screen#renderToolTip(MatrixStack, List, int, int, FontRenderer)}.
     */
    private static void drawTooltipInternal(MatrixStack poseStack, FontRenderer fontRenderer, List<? extends IReorderingProcessor> components, int x, int y){
        if(components.isEmpty())
            return;

        int windowWidth = ClientUtils.getMinecraft().getWindow().getGuiScaledWidth();
        int windowHeight = ClientUtils.getMinecraft().getWindow().getGuiScaledHeight();

        int tooltipWidth = 0;
        int tooltipHeight = components.size() == 1 ? -2 : 0;

        for(IReorderingProcessor component : components){
            int componentWidth = fontRenderer.width(component);
            if(componentWidth > tooltipWidth)
                tooltipWidth = componentWidth;

            tooltipHeight += 10;
        }

        int tooltipX = x + 12;
        int tooltipY = y - 12;
        if(tooltipX + tooltipWidth > windowWidth){
            tooltipX -= 28 + tooltipWidth;
        }

        if(tooltipY + tooltipHeight + 6 > windowHeight){
            tooltipY = windowHeight - tooltipHeight - 6;
        }

        if(y - tooltipHeight - 8 < 0){
            tooltipY = y + 8;
        }

        poseStack.pushPose();

        Tessellator tesselator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        Matrix4f matrix4f = poseStack.last().pose();
        AbstractGui.fillGradient(matrix4f, bufferbuilder, tooltipX - 3, tooltipY - 4, tooltipX + tooltipWidth + 3, tooltipY - 3, 400, -267386864, -267386864);
        AbstractGui.fillGradient(matrix4f, bufferbuilder, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipWidth + 3, tooltipY + tooltipHeight + 4, 400, -267386864, -267386864);
        AbstractGui.fillGradient(matrix4f, bufferbuilder, tooltipX - 3, tooltipY - 3, tooltipX + tooltipWidth + 3, tooltipY + tooltipHeight + 3, 400, -267386864, -267386864);
        AbstractGui.fillGradient(matrix4f, bufferbuilder, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, 400, -267386864, -267386864);
        AbstractGui.fillGradient(matrix4f, bufferbuilder, tooltipX + tooltipWidth + 3, tooltipY - 3, tooltipX + tooltipWidth + 4, tooltipY + tooltipHeight + 3, 400, -267386864, -267386864);
        AbstractGui.fillGradient(matrix4f, bufferbuilder, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, 400, 1347420415, 1344798847);
        AbstractGui.fillGradient(matrix4f, bufferbuilder, tooltipX + tooltipWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipWidth + 3, tooltipY + tooltipHeight + 3 - 1, 400, 1347420415, 1344798847);
        AbstractGui.fillGradient(matrix4f, bufferbuilder, tooltipX - 3, tooltipY - 3, tooltipX + tooltipWidth + 3, tooltipY - 3 + 1, 400, 1347420415, 1347420415);
        AbstractGui.fillGradient(matrix4f, bufferbuilder, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipWidth + 3, tooltipY + tooltipHeight + 3, 400, 1344798847, 1344798847);
        RenderSystem.enableDepthTest();
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.shadeModel(7425);
        bufferbuilder.end();
        WorldVertexBufferUploader.end(bufferbuilder);
        RenderSystem.shadeModel(7424);
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
        IRenderTypeBuffer.Impl bufferSource = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
        poseStack.translate(0.0D, 0.0D, 400.0D);

        for(int index = 0; index < components.size(); ++index){
            IReorderingProcessor component = components.get(index);
            if(component != null)
                fontRenderer.drawInBatch(component, tooltipX, tooltipY, -1, true, matrix4f, bufferSource, false, 0, 15728880);
            tooltipY += index == 0 ? 12 : 10;
        }

        bufferSource.endBatch();
        poseStack.popPose();
    }

    public static void withScissor(MatrixStack poseStack, int x, int y, int width, int height, Runnable rendering){
        // Draw current buffers before enabling scissor
        RenderUtils.getMainBufferSource().endBatch();

        // Apply matrix stack to given coordinates
        Vector4f scissorStart = new Vector4f(x, y, 0, 1);
        Vector4f scissorEnd = new Vector4f(x + width, y + height, 0, 1);
        scissorStart.transform(poseStack.last().pose());
        scissorEnd.transform(poseStack.last().pose());
        // Convert coordinates to window
        MainWindow window = Minecraft.getInstance().getWindow();
        double guiScale = window.getGuiScale();
        double scissorX = scissorStart.x() * guiScale;
        double scissorY = window.getHeight() - scissorEnd.y() * guiScale;
        double scissorWidth = (scissorEnd.x() - scissorStart.x()) * guiScale;
        double scissorHeight = (scissorEnd.y() - scissorStart.y()) * guiScale;
        // Apply scissor and run rendering function
        RenderSystem.enableScissor((int)scissorX, (int)scissorY, Math.max(0, (int)scissorWidth), Math.max(0, (int)scissorHeight));
        try{
            rendering.run();
            RenderUtils.getMainBufferSource().endBatch();
        }finally{
            RenderSystem.disableScissor();
        }
    }

    /**
     * @see CursorTypes
     */
    public static void requestCursor(CursorType cursorType){
        CursorTypes.pendingCursor = cursorType;
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/BaseContainerWidget.java

```java
package com.supermartijn642.core.gui.widget;

import net.minecraft.inventory.container.Container;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
public abstract class BaseContainerWidget<T extends Container> extends BaseWidget implements ContainerWidget<T> {

    protected final List<ContainerWidget<? super T>> containerWidgets = new ArrayList<>();
    protected T container;

    public BaseContainerWidget(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    @Override
    public void initialize(T container){
        if(container == null)
            throw new IllegalArgumentException("Cannot initialize ContainerWidget with a null container!");
        this.container = container;

        this.initialize();
    }

    @Override
    public void initialize(){
        if(this.container == null)
            throw new IllegalStateException("Container widgets must be initialized with a container!");

        this.addWidgets();
        this.containerWidgets.forEach(w -> w.initialize(this.container));
        this.widgets.forEach(w -> {
            if(!(w instanceof ContainerWidget))
                w.initialize();
        });
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/BaseWidget.java

```java
package com.supermartijn642.core.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.gui.CursorType;
import com.supermartijn642.core.gui.ScreenUtils;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
public abstract class BaseWidget implements Widget {

    protected final List<Widget> widgets = new ArrayList<>();
    protected Widget focusedWidget = null;
    protected int x, y, width, height;
    protected boolean dragging = false;
    private boolean focused;
    protected long nextNarration = Long.MAX_VALUE;

    public BaseWidget(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int width(){
        return this.width;
    }

    @Override
    public int height(){
        return this.height;
    }

    @Override
    public int left(){
        return this.x;
    }

    @Override
    public int top(){
        return this.y;
    }

    @Override
    public void initialize(){
        this.addWidgets();
        this.widgets.forEach(Widget::initialize);
    }

    @Override
    public void setFocused(boolean focused){
        if(this.focused != focused)
            this.nextNarration = focused ? Util.getMillis() + 750 : Long.MAX_VALUE;
        this.focused = focused;
        if(!focused)
            this.widgets.forEach(w -> w.setFocused(false));
    }

    public boolean isFocused(){
        return this.focused;
    }

    /**
     * Adds widgets to the screen via {@link #addWidget(Widget)}.
     */
    protected void addWidgets(){
    }

    /**
     * Add the given {@code widget} to the screen.
     * @param widget widget to be added
     * @return the given {@code widget}
     */
    protected <T extends Widget> T addWidget(T widget){
        if(widget == null)
            throw new IllegalArgumentException("Widget must not be null!");
        if(widget == this)
            throw new IllegalArgumentException("Cannot add a widget to itself!");
        if(widget instanceof ContainerWidget<?>)
            throw new IllegalArgumentException("Cannot add a container widget to a regular widget!");
        this.widgets.add(widget);
        return widget;
    }

    /**
     * Removes the given {@code widget} from the screen.
     * @param widget widget to be removed
     * @return true if this widget contained the given widget
     */
    protected boolean removeWidget(Widget widget){
        return this.widgets.remove(widget);
    }

    @Override
    public void update(){
        this.widgets.forEach(Widget::update);
    }

    @Override
    public void renderBackground(MatrixStack poseStack, int mouseX, int mouseY){
        // Update the focused widget
        if(!this.focused)
            this.focusedWidget = null;
        else if(!this.dragging){
            if(this.focusedWidget != null && !(mouseX > this.focusedWidget.left() && mouseX < this.focusedWidget.left() + this.focusedWidget.width() && mouseY > this.focusedWidget.top() && mouseY < this.focusedWidget.top() + this.focusedWidget.height())){
                Widget focusedWidget = this.focusedWidget;
                this.focusedWidget = null;
                focusedWidget.setFocused(false);
                this.nextNarration = Util.getMillis() + 750;
            }
            if(this.focusedWidget == null){
                for(Widget widget : this.widgets){
                    if(mouseX >= widget.left() && mouseX < widget.left() + widget.width() && mouseY >= widget.top() && mouseY < widget.top() + widget.height()){
                        this.focusedWidget = widget;
                        widget.setFocused(true);
                        this.nextNarration = Long.MAX_VALUE;
                        break;
                    }
                }
            }
        }

        // Narrate this widget's narration message
        if(this.focused && this.focusedWidget == null && Util.getMillis() > this.nextNarration){
            ITextComponent message = this.getNarrationMessage();
            String s = message == null ? "" : message.getString();
            if(!s.isEmpty()){
                NarratorChatListener.INSTANCE.sayNow(s);
                this.nextNarration = Long.MAX_VALUE;
            }
        }

        // Render internal widgets' background
        this.widgets.stream().filter(w -> w != this.focusedWidget).forEach(w -> w.renderBackground(poseStack, mouseX, mouseY));
        if(this.focusedWidget != null)
            this.focusedWidget.renderBackground(poseStack, mouseX, mouseY);
    }

    @Override
    public void render(MatrixStack poseStack, int mouseX, int mouseY){
        // Render internal widgets
        this.widgets.stream().filter(w -> w != this.focusedWidget).forEach(w -> w.render(poseStack, mouseX, mouseY));
        if(this.focusedWidget != null)
            this.focusedWidget.render(poseStack, mouseX, mouseY);
    }

    @Override
    public void renderForeground(MatrixStack poseStack, int mouseX, int mouseY){
        // Render internal widgets' foreground
        this.widgets.stream().filter(w -> w != this.focusedWidget).forEach(w -> w.renderForeground(poseStack, mouseX, mouseY));
        if(this.focusedWidget != null)
            this.focusedWidget.renderForeground(poseStack, mouseX, mouseY);
    }

    @Override
    public void renderOverlay(MatrixStack poseStack, int mouseX, int mouseY){
        // Render internal widgets
        this.widgets.stream().filter(w -> w != this.focusedWidget).forEach(w -> w.renderOverlay(poseStack, mouseX, mouseY));
        if(this.focusedWidget != null)
            this.focusedWidget.renderOverlay(poseStack, mouseX, mouseY);
    }

    @Override
    public void renderTooltips(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.focused){
            if(this.focusedWidget != null)
                this.focusedWidget.renderTooltips(poseStack, mouseX, mouseY);
            else{
                // Find a better way to do this, preferably without instantiating an array list unless needed
                List<ITextComponent> tooltips = new ArrayList<>(0);
                this.getTooltips(tooltips::add);
                ScreenUtils.drawTooltip(poseStack, tooltips, mouseX, mouseY);
            }
        }
    }

    /**
     * Gathers the tooltips to be rendered in {@link #renderTooltips(MatrixStack, int, int)}. Tooltips will only be shown when this widget is focused.
     * @param tooltips consumer for tooltips to be rendered
     */
    protected void getTooltips(Consumer<ITextComponent> tooltips){
    }

    @Override
    public void discard(){
    }

    @Override
    public CursorType curser(int mouseX, int mouseY){
        if(this.focusedWidget != null && mouseX > this.focusedWidget.left() && mouseX < this.focusedWidget.left() + this.focusedWidget.width() && mouseY > this.focusedWidget.top() && mouseY < this.focusedWidget.top() + this.focusedWidget.height())
            return this.focusedWidget.curser(mouseX, mouseY);
        for(Widget widget : this.widgets){
            if(mouseX >= widget.left() && mouseX < widget.left() + widget.width() && mouseY >= widget.top() && mouseY < widget.top() + widget.height()){
                return widget.curser(mouseX, mouseY);
            }
        }
        return null;
    }

    @Override
    public boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        this.dragging = true;
        if(this.focusedWidget != null)
            hasBeenHandled = this.focusedWidget.mousePressed(mouseX, mouseY, button, hasBeenHandled) || hasBeenHandled;
        for(Widget widget : this.widgets){
            if(widget != this.focusedWidget)
                hasBeenHandled = widget.mousePressed(mouseX, mouseY, button, hasBeenHandled) || hasBeenHandled;
        }
        return hasBeenHandled;
    }

    @Override
    public boolean mouseReleased(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        this.dragging = false;
        if(this.focusedWidget != null)
            hasBeenHandled = this.focusedWidget.mouseReleased(mouseX, mouseY, button, hasBeenHandled) || hasBeenHandled;
        for(Widget widget : this.widgets){
            if(widget != this.focusedWidget)
                hasBeenHandled = widget.mouseReleased(mouseX, mouseY, button, hasBeenHandled) || hasBeenHandled;
        }
        return hasBeenHandled;
    }

    @Override
    public boolean mouseScrolled(int mouseX, int mouseY, double scrollAmount, boolean hasBeenHandled){
        if(this.focusedWidget != null)
            hasBeenHandled = this.focusedWidget.mouseScrolled(mouseX, mouseY, scrollAmount, hasBeenHandled) || hasBeenHandled;
        for(Widget widget : this.widgets){
            if(widget != this.focusedWidget)
                hasBeenHandled = widget.mouseScrolled(mouseX, mouseY, scrollAmount, hasBeenHandled) || hasBeenHandled;
        }
        return hasBeenHandled;
    }

    @Override
    public boolean keyPressed(int keyCode, boolean hasBeenHandled){
        if(this.focusedWidget != null)
            hasBeenHandled = this.focusedWidget.keyPressed(keyCode, hasBeenHandled) || hasBeenHandled;
        for(Widget widget : this.widgets){
            if(widget != this.focusedWidget)
                hasBeenHandled = widget.keyPressed(keyCode, hasBeenHandled) || hasBeenHandled;
        }
        return hasBeenHandled;
    }

    @Override
    public boolean keyReleased(int keyCode, boolean hasBeenHandled){
        if(this.focusedWidget != null)
            hasBeenHandled = this.focusedWidget.keyReleased(keyCode, hasBeenHandled) || hasBeenHandled;
        for(Widget widget : this.widgets){
            if(widget != this.focusedWidget)
                hasBeenHandled = widget.keyReleased(keyCode, hasBeenHandled) || hasBeenHandled;
        }
        return hasBeenHandled;
    }

    @Override
    public boolean charTyped(char character, boolean hasBeenHandled){
        if(this.focusedWidget != null)
            hasBeenHandled = this.focusedWidget.charTyped(character, hasBeenHandled) || hasBeenHandled;
        for(Widget widget : this.widgets){
            if(widget != this.focusedWidget)
                hasBeenHandled = widget.charTyped(character, hasBeenHandled) || hasBeenHandled;
        }
        return hasBeenHandled;
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/BlockEntityBaseContainerWidget.java

```java
package com.supermartijn642.core.gui.widget;

import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created 23/07/2022 by SuperMartijn642
 */
public abstract class BlockEntityBaseContainerWidget<T extends TileEntity, C extends Container> extends ObjectBaseContainerWidget<T,C> {

    protected final World blockEntityLevel;
    protected final BlockPos blockEntityPos;

    public BlockEntityBaseContainerWidget(int x, int y, int width, int height, World blockEntityLevel, BlockPos blockEntityPos){
        super(x, y, width, height);
        this.blockEntityLevel = blockEntityLevel;
        this.blockEntityPos = blockEntityPos;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T getObject(T oldObject){
        TileEntity entity = this.blockEntityLevel.getBlockEntity(this.blockEntityPos);
        if(entity == null)
            return null;

        try{
            return (T)entity;
        }catch(ClassCastException ignore){}
        return null;
    }

    @Override
    protected boolean validateObject(T object){
        return object != null && !object.isRemoved();
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/BlockEntityBaseWidget.java

```java
package com.supermartijn642.core.gui.widget;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created 23/07/2022 by SuperMartijn642
 */
public abstract class BlockEntityBaseWidget<T extends TileEntity> extends ObjectBaseWidget<T> {

    protected final World blockEntityLevel;
    protected final BlockPos blockEntityPos;

    public BlockEntityBaseWidget(int x, int y, int width, int height, World blockEntityLevel, BlockPos blockEntityPos){
        super(x, y, width, height);
        this.blockEntityLevel = blockEntityLevel;
        this.blockEntityPos = blockEntityPos;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T getObject(T oldObject){
        TileEntity entity = this.blockEntityLevel.getBlockEntity(this.blockEntityPos);
        if(entity == null)
            return null;

        try{
            return (T)entity;
        }catch(ClassCastException ignore){}
        return null;
    }

    @Override
    protected boolean validateObject(T object){
        return object != null && !object.isRemoved();
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/ContainerWidget.java

```java
package com.supermartijn642.core.gui.widget;

import net.minecraft.inventory.container.Container;

/**
 * Created 17/07/2022 by SuperMartijn642
 */
public interface ContainerWidget<T extends Container> extends Widget {

    @Override
    default void initialize(){
        throw new IllegalStateException("Container widgets must be initialized with a container!");
    }

    /**
     * Called when the widget is added.
     * @param container the container this widget is in, must not be {@code null}
     */
    void initialize(T container);
}
```

### src/main/java/com/supermartijn642/core/gui/widget/ItemBaseContainerWidget.java

```java
package com.supermartijn642.core.gui.widget;

import com.supermartijn642.core.ClientUtils;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created 23/07/2022 by SuperMartijn642
 */
public abstract class ItemBaseContainerWidget<C extends Container> extends ObjectBaseContainerWidget<ItemStack,C> {

    protected final Supplier<ItemStack> stackSupplier;
    protected final Predicate<ItemStack> stackValidator;

    public ItemBaseContainerWidget(int x, int y, int width, int height, Supplier<ItemStack> stackSupplier, Predicate<ItemStack> stackValidator){
        super(x, y, width, height, true);
        this.stackSupplier = stackSupplier;
        this.stackValidator = stackValidator;
    }

    public ItemBaseContainerWidget(int x, int y, int width, int height, int slotIndex, Predicate<ItemStack> stackValidator){
        this(x, y, width, height, () -> ClientUtils.getPlayer().inventory.getItem(slotIndex), stackValidator);
    }

    public ItemBaseContainerWidget(int x, int y, int width, int height, int slotIndex, Item itemType){
        this(x, y, width, height, () -> ClientUtils.getPlayer().inventory.getItem(slotIndex), stack -> stack.getItem() == itemType);
    }

    public ItemBaseContainerWidget(int x, int y, int width, int height, Hand hand, Predicate<ItemStack> stackValidator){
        this(x, y, width, height, () -> ClientUtils.getPlayer().getItemInHand(hand), stackValidator);
    }

    public ItemBaseContainerWidget(int x, int y, int width, int height, Hand hand, Item itemType){
        this(x, y, width, height, () -> ClientUtils.getPlayer().getItemInHand(hand), stack -> stack.getItem() == itemType);
    }

    @Override
    protected ItemStack getObject(ItemStack oldObject){
        return this.stackSupplier.get();
    }

    @Override
    protected boolean validateObject(ItemStack object){
        return object != null && this.stackValidator.test(object);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/ItemBaseWidget.java

```java
package com.supermartijn642.core.gui.widget;

import com.supermartijn642.core.ClientUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created 23/07/2022 by SuperMartijn642
 */
public abstract class ItemBaseWidget extends ObjectBaseWidget<ItemStack> {

    protected final Supplier<ItemStack> stackSupplier;
    protected final Predicate<ItemStack> stackValidator;

    public ItemBaseWidget(int x, int y, int width, int height, Supplier<ItemStack> stackSupplier, Predicate<ItemStack> stackValidator){
        super(x, y, width, height, true);
        this.stackSupplier = stackSupplier;
        this.stackValidator = stackValidator;
    }

    public ItemBaseWidget(int x, int y, int width, int height, int slotIndex, Predicate<ItemStack> stackValidator){
        this(x, y, width, height, () -> ClientUtils.getPlayer().inventory.getItem(slotIndex), stackValidator);
    }

    public ItemBaseWidget(int x, int y, int width, int height, int slotIndex, Item itemType){
        this(x, y, width, height, () -> ClientUtils.getPlayer().inventory.getItem(slotIndex), stack -> stack.getItem() == itemType);
    }

    public ItemBaseWidget(int x, int y, int width, int height, Hand hand, Predicate<ItemStack> stackValidator){
        this(x, y, width, height, () -> ClientUtils.getPlayer().getItemInHand(hand), stackValidator);
    }

    public ItemBaseWidget(int x, int y, int width, int height, Hand hand, Item itemType){
        this(x, y, width, height, () -> ClientUtils.getPlayer().getItemInHand(hand), stack -> stack.getItem() == itemType);
    }

    @Override
    protected ItemStack getObject(ItemStack oldObject){
        return this.stackSupplier.get();
    }

    @Override
    protected boolean validateObject(ItemStack object){
        return object != null && this.stackValidator.test(object);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/ObjectBaseContainerWidget.java

```java
package com.supermartijn642.core.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.ClientUtils;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

import java.util.function.Consumer;

/**
 * Created 17/07/2022 by SuperMartijn642
 */
public abstract class ObjectBaseContainerWidget<T, C extends Container> extends BaseContainerWidget<C> {

    protected T object;
    private final boolean alwaysRenewObject;

    public ObjectBaseContainerWidget(int x, int y, int width, int height, boolean alwaysRenewObject){
        super(x, y, width, height);
        this.alwaysRenewObject = alwaysRenewObject;
    }

    public ObjectBaseContainerWidget(int x, int y, int width, int height){
        this(x, y, width, height, false);
    }

    /**
     * Called to obtain object needed for this widget to remain active. May be called at any time.
     * @param oldObject the old object, will be {@code null} when the widget is first added
     * @return the object required for the container to remain open
     */
    protected abstract T getObject(T oldObject);

    /**
     * Validates the object obtained from {@link #getObject(Object)}.
     * The associated screen will be closed if {@code false} is returned.
     * @param object object to be validated
     * @return true if the object is valid
     */
    protected abstract boolean validateObject(T object);

    /**
     * Validates the object. If the object is not valid the screen will be closed.
     * @return true if the object is valid
     */
    protected boolean validateObjectOrClose(){
        if(this.alwaysRenewObject || !this.validateObject(this.object)){
            this.object = this.getObject(this.object);
            if(!this.validateObject(this.object)){
                ClientUtils.closeScreen();
                return false;
            }
        }
        return true;
    }

    @Override
    public final ITextComponent getNarrationMessage(){
        return this.validateObjectOrClose() ? this.getNarrationMessage(this.object) : null;
    }

    /**
     * @return the title to be read by the narrator when the widget is focused by the user
     */
    protected abstract ITextComponent getNarrationMessage(T object);

    @Override
    public final int width(){
        return this.validateObjectOrClose() ? this.width(this.object) : 0;
    }

    /**
     * @return the width of the widget
     */
    protected int width(T object){
        return super.width();
    }

    @Override
    public final int height(){
        return this.validateObjectOrClose() ? this.height(this.object) : 0;
    }

    /**
     * @return the height of the widget
     */
    protected int height(T object){
        return super.height();
    }

    @Override
    public final int left(){
        return this.validateObjectOrClose() ? this.width(this.object) : 0;
    }

    /**
     * @return the x-position of this widget
     */
    protected int left(T object){
        return super.left();
    }

    @Override
    public final int top(){
        return this.validateObjectOrClose() ? this.top(this.object) : 0;
    }

    /**
     * @return the y-position of this widget
     */
    protected int top(T object){
        return super.top();
    }

    @Override
    public final void initialize(){
        if(this.validateObjectOrClose())
            this.initialize(this.object);
    }

    /**
     * Called when the widget is added.
     */
    protected void initialize(T object){
        super.initialize();
    }

    @Override
    protected void addWidgets(){
        if(this.validateObjectOrClose())
            this.addWidgets(this.object);
    }

    /**
     * Adds widgets to the screen via {@link #addWidget(Widget)}.
     */
    protected void addWidgets(T object){
        super.addWidgets();
    }

    @Override
    public final void update(){
        if(this.validateObjectOrClose())
            this.update(this.object);
    }

    /**
     * Called once per tick when the widget is shown.
     */
    protected void update(T object){
        super.update();
    }

    @Override
    public final void renderBackground(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.renderBackground(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Renders the widget's background. This will be called first in the render chain.
     */
    protected void renderBackground(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.renderBackground(poseStack, mouseX, mouseY);
    }

    @Override
    public final void render(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.render(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Renders the widget's main features.
     * Called after the background and slots are drawn, but before items are drawn.
     */
    protected void render(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.render(poseStack, mouseX, mouseY);
    }

    @Override
    public final void renderForeground(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.renderForeground(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Renders the widget's foreground.
     * Called after main features and items are drawn, but before cursor item and overlay are drawn.
     */
    protected void renderForeground(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.renderForeground(poseStack, mouseX, mouseY);
    }

    @Override
    public final void renderOverlay(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.renderOverlay(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Called after foreground and cursor item are drawn, but before tooltips are drawn.
     */
    protected void renderOverlay(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.renderOverlay(poseStack, mouseX, mouseY);
    }

    @Override
    public final void renderTooltips(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.renderTooltips(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Renders tooltips for the given {@code mouseX} and {@code mouseY}.
     * This will be called last in the render chain.
     */
    protected void renderTooltips(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.renderTooltips(poseStack, mouseX, mouseY);
    }

    @Override
    protected void getTooltips(Consumer<ITextComponent> tooltips){
        if(this.validateObjectOrClose())
            this.getTooltips(tooltips, this.object);
    }

    /**
     * Gathers the tooltips to be rendered in {@link #renderTooltips(MatrixStack, int, int)}. Tooltips will only be shown when this widget is focused.
     * @param tooltips consumer for tooltips to be rendered
     */
    protected void getTooltips(Consumer<ITextComponent> tooltips, T object){
        super.getTooltips(tooltips);
    }

    @Override
    public final boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.mousePressed(mouseX, mouseY, button, hasBeenHandled, this.object);
    }

    /**
     * Called when a mouse button is pressed down.
     * @param mouseX         x-position of the mouse
     * @param mouseY         y-position of the mouse
     * @param button         the button which is pressed down
     * @param hasBeenHandled whether the mouse press has already been handled
     * @return whether this widget has handled the mouse press
     */
    protected boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled, T object){
        return super.mousePressed(mouseX, mouseY, button, hasBeenHandled);
    }

    @Override
    public final boolean mouseReleased(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.mouseReleased(mouseX, mouseY, button, hasBeenHandled, this.object);
    }

    /**
     * Called when a mouse button is released.
     * @param mouseX         x-position of the mouse
     * @param mouseY         y-position of the mouse
     * @param button         the button which is pressed down
     * @param hasBeenHandled whether the mouse press has already been handled
     * @return whether this widget has handled the mouse release
     */
    protected boolean mouseReleased(int mouseX, int mouseY, int button, boolean hasBeenHandled, T object){
        return super.mouseReleased(mouseX, mouseY, button, hasBeenHandled);
    }

    @Override
    public final boolean mouseScrolled(int mouseX, int mouseY, double scrollAmount, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.mouseScrolled(mouseX, mouseY, scrollAmount, hasBeenHandled, this.object);
    }

    /**
     * Called when the mouse wheel is scrolled.
     * @param mouseX         x-position of the mouse
     * @param mouseY         y-position of the mouse
     * @param scrollAmount   the amount the mouse wheel was scrolled by
     * @param hasBeenHandled whether the mouse press has already been handled
     * @return whether this widget has handled the mouse scroll
     */
    protected boolean mouseScrolled(int mouseX, int mouseY, double scrollAmount, boolean hasBeenHandled, T object){
        return super.mouseScrolled(mouseX, mouseY, scrollAmount, hasBeenHandled);
    }

    @Override
    public final boolean keyPressed(int keyCode, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.keyPressed(keyCode, hasBeenHandled, this.object);
    }

    /**
     * Called when a key is pressed down.
     * @param keyCode code of the key which was pressed
     * @return whether this widget has handled the key press
     */
    protected boolean keyPressed(int keyCode, boolean hasBeenHandled, T object){
        return super.keyPressed(keyCode, hasBeenHandled);
    }

    @Override
    public final boolean keyReleased(int keyCode, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.keyReleased(keyCode, hasBeenHandled, this.object);
    }

    /**
     * Called when a key is released.
     * @param keyCode code of the key which was released
     * @return whether this widget has handled the key release
     */
    protected boolean keyReleased(int keyCode, boolean hasBeenHandled, T object){
        return super.keyReleased(keyCode, hasBeenHandled);
    }

    @Override
    public final boolean charTyped(char character, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.charTyped(character, hasBeenHandled, this.object);
    }

    /**
     * Called when a character is typed. May be called in addition to {@link #keyPressed(int, boolean)}.
     * @param character the character which was typed
     * @return whether this widget has handled the character
     */
    protected boolean charTyped(char character, boolean hasBeenHandled, T object){
        return super.charTyped(character, hasBeenHandled);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/ObjectBaseWidget.java

```java
package com.supermartijn642.core.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.ClientUtils;
import net.minecraft.util.text.ITextComponent;

import java.util.function.Consumer;

/**
 * Created 17/07/2022 by SuperMartijn642
 */
public abstract class ObjectBaseWidget<T> extends BaseWidget {

    protected T object;
    private final boolean alwaysRenewObject;

    public ObjectBaseWidget(int x, int y, int width, int height, boolean alwaysRenewObject){
        super(x, y, width, height);
        this.alwaysRenewObject = alwaysRenewObject;
    }

    public ObjectBaseWidget(int x, int y, int width, int height){
        this(x, y, width, height, false);
    }

    /**
     * Called to obtain object needed for this widget to remain active. May be called at any time.
     * @param oldObject the old object, will be {@code null} when the widget is first added
     * @return the object required for the container to remain open
     */
    protected abstract T getObject(T oldObject);

    /**
     * Validates the object obtained from {@link #getObject(Object)}.
     * The associated screen will be closed if {@code false} is returned.
     * @param object object to be validated, may be null
     * @return true if the object is valid
     */
    protected abstract boolean validateObject(T object);

    /**
     * Validates the object. If the object is not valid the screen will be closed.
     * @return true if the object is valid
     */
    protected boolean validateObjectOrClose(){
        if(this.alwaysRenewObject || !this.validateObject(this.object)){
            this.object = this.getObject(this.object);
            if(!this.validateObject(this.object)){
                ClientUtils.closeScreen();
                return false;
            }
        }
        return true;
    }

    @Override
    public final ITextComponent getNarrationMessage(){
        return this.validateObjectOrClose() ? this.getNarrationMessage(this.object) : null;
    }

    /**
     * @return the title to be read by the narrator when the widget is focused by the user
     */
    protected abstract ITextComponent getNarrationMessage(T object);

    @Override
    public final int width(){
        return this.validateObjectOrClose() ? this.width(this.object) : 0;
    }

    /**
     * @return the width of the widget
     */
    protected int width(T object){
        return super.width();
    }

    @Override
    public final int height(){
        return this.validateObjectOrClose() ? this.height(this.object) : 0;
    }

    /**
     * @return the height of the widget
     */
    protected int height(T object){
        return super.height();
    }

    @Override
    public final int left(){
        return this.validateObjectOrClose() ? this.width(this.object) : 0;
    }

    /**
     * @return the x-position of this widget
     */
    protected int left(T object){
        return super.left();
    }

    @Override
    public final int top(){
        return this.validateObjectOrClose() ? this.top(this.object) : 0;
    }

    /**
     * @return the y-position of this widget
     */
    protected int top(T object){
        return super.top();
    }

    @Override
    public final void initialize(){
        if(this.validateObjectOrClose())
            this.initialize(this.object);
    }

    /**
     * Called when the widget is added.
     */
    protected void initialize(T object){
        super.initialize();
    }

    @Override
    protected void addWidgets(){
        if(this.validateObjectOrClose())
            this.addWidgets(this.object);
    }

    /**
     * Adds widgets to the screen via {@link #addWidget(Widget)}.
     */
    protected void addWidgets(T object){
        super.addWidgets();
    }

    @Override
    public final void update(){
        if(this.validateObjectOrClose())
            this.update(this.object);
    }

    /**
     * Called once per tick when the widget is shown.
     */
    protected void update(T object){
        super.update();
    }

    @Override
    public final void renderBackground(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.renderBackground(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Renders the widget's background. This will be called first in the render chain.
     */
    protected void renderBackground(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.renderBackground(poseStack, mouseX, mouseY);
    }

    @Override
    public final void render(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.render(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Renders the widget's main features.
     * Called after the background and slots are drawn, but before items are drawn.
     */
    protected void render(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.render(poseStack, mouseX, mouseY);
    }

    @Override
    public final void renderForeground(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.renderForeground(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Renders the widget's foreground.
     * Called after main features and items are drawn, but before cursor item and overlay are drawn.
     */
    protected void renderForeground(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.renderForeground(poseStack, mouseX, mouseY);
    }

    @Override
    public final void renderOverlay(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.renderOverlay(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Called after foreground and cursor item are drawn, but before tooltips are drawn.
     */
    protected void renderOverlay(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.renderOverlay(poseStack, mouseX, mouseY);
    }

    @Override
    public final void renderTooltips(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.validateObjectOrClose())
            this.renderTooltips(poseStack, mouseX, mouseY, this.object);
    }

    /**
     * Renders tooltips for the given {@code mouseX} and {@code mouseY}.
     * This will be called last in the render chain.
     */
    protected void renderTooltips(MatrixStack poseStack, int mouseX, int mouseY, T object){
        super.renderTooltips(poseStack, mouseX, mouseY);
    }

    @Override
    protected void getTooltips(Consumer<ITextComponent> tooltips){
        if(this.validateObjectOrClose())
            this.getTooltips(tooltips, this.object);
    }

    /**
     * Gathers the tooltips to be rendered in {@link #renderTooltips(MatrixStack, int, int)}. Tooltips will only be shown when this widget is focused.
     * @param tooltips consumer for tooltips to be rendered
     */
    protected void getTooltips(Consumer<ITextComponent> tooltips, T object){
        super.getTooltips(tooltips);
    }

    @Override
    public final boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.mousePressed(mouseX, mouseY, button, hasBeenHandled, this.object);
    }

    /**
     * Called when a mouse button is pressed down.
     * @param mouseX         x-position of the mouse
     * @param mouseY         y-position of the mouse
     * @param button         the button which is pressed down
     * @param hasBeenHandled whether the mouse press has already been handled
     * @return whether this widget has handled the mouse press
     */
    protected boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled, T object){
        return super.mousePressed(mouseX, mouseY, button, hasBeenHandled);
    }

    @Override
    public final boolean mouseReleased(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.mouseReleased(mouseX, mouseY, button, hasBeenHandled, this.object);
    }

    /**
     * Called when a mouse button is released.
     * @param mouseX         x-position of the mouse
     * @param mouseY         y-position of the mouse
     * @param button         the button which is pressed down
     * @param hasBeenHandled whether the mouse press has already been handled
     * @return whether this widget has handled the mouse release
     */
    protected boolean mouseReleased(int mouseX, int mouseY, int button, boolean hasBeenHandled, T object){
        return super.mouseReleased(mouseX, mouseY, button, hasBeenHandled);
    }

    @Override
    public final boolean mouseScrolled(int mouseX, int mouseY, double scrollAmount, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.mouseScrolled(mouseX, mouseY, scrollAmount, hasBeenHandled, this.object);
    }

    /**
     * Called when the mouse wheel is scrolled.
     * @param mouseX         x-position of the mouse
     * @param mouseY         y-position of the mouse
     * @param scrollAmount   the amount the mouse wheel was scrolled by
     * @param hasBeenHandled whether the mouse press has already been handled
     * @return whether this widget has handled the mouse scroll
     */
    protected boolean mouseScrolled(int mouseX, int mouseY, double scrollAmount, boolean hasBeenHandled, T object){
        return super.mouseScrolled(mouseX, mouseY, scrollAmount, hasBeenHandled);
    }

    @Override
    public final boolean keyPressed(int keyCode, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.keyPressed(keyCode, hasBeenHandled, this.object);
    }

    /**
     * Called when a key is pressed down.
     * @param keyCode code of the key which was pressed
     * @return whether this widget has handled the key press
     */
    protected boolean keyPressed(int keyCode, boolean hasBeenHandled, T object){
        return super.keyPressed(keyCode, hasBeenHandled);
    }

    @Override
    public final boolean keyReleased(int keyCode, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.keyReleased(keyCode, hasBeenHandled, this.object);
    }

    /**
     * Called when a key is released.
     * @param keyCode code of the key which was released
     * @return whether this widget has handled the key release
     */
    protected boolean keyReleased(int keyCode, boolean hasBeenHandled, T object){
        return super.keyReleased(keyCode, hasBeenHandled);
    }

    @Override
    public final boolean charTyped(char character, boolean hasBeenHandled){
        return this.validateObjectOrClose() && this.charTyped(character, hasBeenHandled, this.object);
    }

    /**
     * Called when a character is typed. May be called in addition to {@link #keyPressed(int, boolean)}.
     * @param character the character which was typed
     * @return whether this widget has handled the character
     */
    protected boolean charTyped(char character, boolean hasBeenHandled, T object){
        return super.charTyped(character, hasBeenHandled);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/premade/AbstractButtonWidget.java

```java
package com.supermartijn642.core.gui.widget.premade;

import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.gui.CursorType;
import com.supermartijn642.core.gui.CursorTypes;
import com.supermartijn642.core.gui.widget.BaseWidget;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.util.SoundEvents;

/**
 * Created 10/8/2020 by SuperMartijn642
 */
public abstract class AbstractButtonWidget extends BaseWidget {

    private final Runnable pressable;
    private boolean active = true;

    /**
     * @param onPress the action which will called when the user clicks the
     *                widget
     */
    public AbstractButtonWidget(int x, int y, int width, int height, Runnable onPress){
        super(x, y, width, height);
        this.pressable = onPress;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public boolean isActive(){
        return this.active;
    }

    protected boolean isClickable(){
        return this.active;
    }

    /**
     * Called when the user clicks the widget.
     */
    public void onPress(){
        playClickSound();
        if(this.pressable != null)
            this.pressable.run();
    }

    @Override
    public CursorType curser(int mouseX, int mouseY){
        return this.isClickable() ? CursorTypes.pointingHand() : null;
    }

    @Override
    public boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        if(!hasBeenHandled && mouseX >= this.x && mouseX < this.x + this.width && mouseY >= this.y && mouseY < this.y + this.height && this.isClickable()){
            this.onPress();
            return true;
        }
        return false;
    }

    /**
     * Plays the default Minecraft button sound.
     */
    public static void playClickSound(){
        ClientUtils.getMinecraft().getSoundManager().play(SimpleSound.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/premade/ButtonWidget.java

```java
package com.supermartijn642.core.gui.widget.premade;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.gui.ScreenUtils;
import net.minecraft.util.text.ITextComponent;

/**
 * Created 10/15/2020 by SuperMartijn642
 */
public class ButtonWidget extends AbstractButtonWidget {

    private ITextComponent text;

    /**
     * @param text    the text to be displayed on the button
     * @param onPress the action which will called when the user clicks the
     *                widget
     */
    public ButtonWidget(int x, int y, int width, int height, ITextComponent text, Runnable onPress){
        super(x, y, width, height, onPress);
        this.text = text;
    }

    /**
     * Sets the text which is displayed on the button.
     */
    public void setText(ITextComponent text){
        this.text = text;
    }

    public ITextComponent getText(){
        return this.text;
    }

    @Override
    public ITextComponent getNarrationMessage(){
        return this.text;
    }

    @Override
    public void render(MatrixStack poseStack, int mouseX, int mouseY){
        ScreenUtils.drawButtonBackground(poseStack, this.x, this.y, this.width, this.height, (this.isActive() ? this.isFocused() ? 5 : 0 : 10) / 15f);
        ScreenUtils.drawCenteredStringWithShadow(poseStack, ClientUtils.getFontRenderer(), this.text, this.x + this.width / 2f, this.y + this.height / 2f - 5, this.isActive() ? 0xFFFFFFFF : Integer.MAX_VALUE);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/premade/LabelWidget.java

```java
package com.supermartijn642.core.gui.widget.premade;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.gui.ScreenUtils;
import com.supermartijn642.core.gui.widget.BaseWidget;
import net.minecraft.util.text.ITextComponent;

import java.util.function.Supplier;

/**
 * Created 10/29/2020 by SuperMartijn642
 */
public class LabelWidget extends BaseWidget {

    private final Supplier<ITextComponent> text;
    private boolean active = true;

    /**
     * @param text the text to be displayed on the label
     */
    public LabelWidget(int x, int y, int width, int height, Supplier<ITextComponent> text){
        super(x, y, width, height);
        this.text = text;
    }

    /**
     * @param text the text to be displayed on the label
     */
    public LabelWidget(int x, int y, int width, int height, ITextComponent text){
        this(x, y, width, height, () -> text);
    }

    public void setActive(boolean active){
        this.active = active;
    }

    @Override
    public ITextComponent getNarrationMessage(){
        return this.text.get();
    }

    @Override
    public void render(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.active){
            ScreenUtils.fillRect(poseStack, this.x, this.y, this.width, this.height, -6250336);
            ScreenUtils.fillRect(poseStack, this.x + 1, this.y + 1, this.width - 2, this.height - 2, 0xff404040);

            ScreenUtils.drawCenteredStringWithShadow(poseStack, this.text.get(), this.x + this.width / 2f, this.y + 2, this.active ? ScreenUtils.ACTIVE_TEXT_COLOR : ScreenUtils.INACTIVE_TEXT_COLOR);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/premade/ScissorWidget.java

```java
package com.supermartijn642.core.gui.widget.premade;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.gui.CursorType;
import com.supermartijn642.core.gui.ScreenUtils;
import com.supermartijn642.core.gui.widget.BaseWidget;
import com.supermartijn642.core.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;

import java.util.function.IntSupplier;

/**
 * A widgets that restricts the rendering and mouse input handling of its children to within its boundary.
 * <p>
 * Created 09/01/2026 by SuperMartijn642
 */
public class ScissorWidget extends BaseWidget {

    /**
     * @see ScissorWidget
     */
    public static ScissorWidget create(int x, int y, int width, int height, Widget... children){
        return new ScissorWidget(x, y, width, height, children);
    }

    private IntSupplier scissorOffsetX;
    private IntSupplier scissorOffsetY;

    private ScissorWidget(int x, int y, int width, int height, Widget... children){
        super(x, y, width, height);
        for(Widget child : children)
            this.addWidget(child);
    }

    public void setScissorOffset(IntSupplier offsetX, IntSupplier offsetY){
        this.scissorOffsetX = offsetX;
        this.scissorOffsetY = offsetY;
    }

    @Override
    public ITextComponent getNarrationMessage(){
        return null;
    }

    @Override
    public <T extends Widget> T addWidget(T widget){
        return super.addWidget(widget);
    }

    @Override
    public boolean removeWidget(Widget widget){
        return super.removeWidget(widget);
    }

    private void renderScissored(MatrixStack poseStack, int mouseX, int mouseY, RenderFunction renderFunction){
        if(mouseX < this.x || mouseX > this.x + this.width || mouseY < this.y || mouseY > this.y + this.height)
            mouseX = mouseY = -100;
        int x = this.x;
        if(this.scissorOffsetX != null)
            x += this.scissorOffsetX.getAsInt();
        int y = this.y;
        if(this.scissorOffsetY != null)
            y += this.scissorOffsetY.getAsInt();
        int finalMouseX = mouseX;
        int finalMouseY = mouseY;
        ScreenUtils.withScissor(poseStack, x, y, this.width, this.height, () -> renderFunction.render(poseStack, finalMouseX, finalMouseY));
    }

    private interface RenderFunction {
        void render(MatrixStack poseStack, int mouseX, int mouseY);
    }

    @Override
    public void renderBackground(MatrixStack poseStack, int mouseX, int mouseY){
        this.renderScissored(poseStack, mouseX, mouseY, super::renderBackground);
    }

    @Override
    public void render(MatrixStack poseStack, int mouseX, int mouseY){
        this.renderScissored(poseStack, mouseX, mouseY, super::render);
    }

    @Override
    public void renderForeground(MatrixStack poseStack, int mouseX, int mouseY){
        this.renderScissored(poseStack, mouseX, mouseY, super::renderForeground);
    }

    @Override
    public void renderOverlay(MatrixStack poseStack, int mouseX, int mouseY){
        this.renderScissored(poseStack, mouseX, mouseY, super::renderOverlay);
    }

    @Override
    public void renderTooltips(MatrixStack poseStack, int mouseX, int mouseY){
        if(mouseX < this.x || mouseX > this.x + this.width || mouseY < this.y || mouseY > this.y + this.height)
            mouseX = mouseY = -100;
        super.renderTooltips(poseStack, mouseX, mouseY);
    }

    @Override
    public CursorType curser(int mouseX, int mouseY){
        if(mouseX < this.x || mouseX > this.x + this.width || mouseY < this.y || mouseY > this.y + this.height)
            return null;
        return super.curser(mouseX, mouseY);
    }

    @Override
    public boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        if(mouseX < this.x || mouseX > this.x + this.width || mouseY < this.y || mouseY > this.y + this.height)
            mouseX = mouseY = -100;
        return super.mousePressed(mouseX, mouseY, button, hasBeenHandled);
    }

    @Override
    public boolean mouseReleased(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        if(mouseX < this.x || mouseX > this.x + this.width || mouseY < this.y || mouseY > this.y + this.height)
            mouseX = mouseY = -100;
        return super.mouseReleased(mouseX, mouseY, button, hasBeenHandled);
    }

    @Override
    public boolean mouseScrolled(int mouseX, int mouseY, double scrollAmount, boolean hasBeenHandled){
        if(mouseX < this.x || mouseX > this.x + this.width || mouseY < this.y || mouseY > this.y + this.height)
            mouseX = mouseY = -100;
        return super.mouseScrolled(mouseX, mouseY, scrollAmount, hasBeenHandled);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/premade/ScrollbarWidget.java

```java
package com.supermartijn642.core.gui.widget.premade;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.CursorType;
import com.supermartijn642.core.gui.CursorTypes;
import com.supermartijn642.core.gui.ScreenUtils;
import com.supermartijn642.core.gui.widget.BaseWidget;
import com.supermartijn642.core.util.Holder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

/**
 * Created 09/01/2026 by SuperMartijn642
 */
public class ScrollbarWidget extends BaseWidget {

    public static Builder builder(int height){
        return new Builder(height);
    }

    private static final ResourceLocation BACKGROUND = new ResourceLocation("supermartijn642corelib", "textures/gui/scrollbar_background.png");
    private static final ResourceLocation SCROLLER = new ResourceLocation("supermartijn642corelib", "textures/gui/scroller.png");

    private final int scrollerHeight;
    private final DoubleSupplier value, minValue, maxValue;
    private final boolean invertScrolling;
    private final Double stepSize;
    private final ScrollListener onChange;
    private final Double scrollerSpeed;
    private final boolean smoothValues;
    private final ResourceLocation background, scroller;
    private final Double scrollWheelStep;
    /**
     * Scrolling position on scale from 0 to 1
     */
    private float scrollerPosition = 0;
    private boolean dragging = false;
    private boolean active = true, scrollable = true;

    private ScrollbarWidget(int x, int y, int width, int height, int scrollerHeight, DoubleSupplier value, DoubleSupplier minValue, DoubleSupplier maxValue, boolean invertScrolling, Double stepSize, ScrollListener onChange, Integer scrollerSpeed, boolean smoothValues, ResourceLocation background, ResourceLocation scroller, Double scrollWheelStep){
        super(x, y, width, height);
        this.scrollerHeight = scrollerHeight;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.invertScrolling = invertScrolling;
        this.stepSize = stepSize;
        this.onChange = onChange;
        this.scrollWheelStep = scrollWheelStep;
        this.scrollerSpeed = scrollerSpeed == null ? null : (double)scrollerSpeed / (height - scrollerHeight);
        this.smoothValues = smoothValues;
        this.background = background;
        this.scroller = scroller;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public void setScrollable(boolean scrollable){
        this.scrollable = scrollable;
    }

    private boolean canUserMoveScroller(){
        return this.scrollable && this.maxValue.getAsDouble() > this.minValue.getAsDouble();
    }

    @Override
    public ITextComponent getNarrationMessage(){
        return TextComponents.translation("supermartijn642corelib.widgets.scrollbar.narration").get();
    }

    @Override
    public void renderBackground(MatrixStack poseStack, int mouseX, int mouseY){
        // Update dragging
        if(this.dragging){
            if(!this.canUserMoveScroller())
                this.dragging = false;
            else{
                this.updateDrag(mouseY);
                ScreenUtils.requestCursor(CursorTypes.resizeVertical());
            }
        }

        super.renderBackground(poseStack, mouseX, mouseY);

        // Render background
        if(this.background != null){
            ScreenUtils.bindTexture(this.background);
            ScreenUtils.drawTexture(poseStack, this.x - 1, this.y - 1, this.width + 2, this.height + 2);
        }
    }

    @Override
    public void render(MatrixStack poseStack, int mouseX, int mouseY){
        double min = this.minValue.getAsDouble(), max = this.maxValue.getAsDouble();
        double range = max - min;
        if(range <= 0 || !this.active){
            ScreenUtils.bindTexture(this.scroller);
            ScreenUtils.drawTexture(poseStack, this.x, this.y, this.width, this.scrollerHeight, 0, 2 / 3f, 1, 1 / 3f);
            return;
        }

        // Calculate the target scroller position
        double value = MathHelper.clamp(this.value.getAsDouble() - min, 0, range);
        if(this.stepSize != null)
            value = Math.round((value) / this.stepSize) * this.stepSize;
        float targetPosition = (float)(value / range);
        if(this.invertScrolling)
            targetPosition = 1 - targetPosition;
        // Update the scroller position
        if(this.scrollerSpeed != null)
            this.scrollerPosition = (float)MathHelper.clamp(targetPosition, this.scrollerPosition - this.scrollerSpeed, this.scrollerPosition + this.scrollerSpeed);
        else
            this.scrollerPosition = targetPosition;
        float offset = (this.height - this.scrollerHeight) * this.scrollerPosition;
        ScreenUtils.bindTexture(this.scroller);
        ScreenUtils.drawTexture(poseStack, this.x, this.y + offset, this.width, this.scrollerHeight, 0, this.scrollable && this.isFocused() ? 1 / 3f : 0, 1, 1 / 3f);
    }

    private void tryScrollTo(double targetPosition, boolean fromScrollWheel){
        targetPosition = MathHelper.clamp(targetPosition, 0, 1);
        if(this.smoothValues && !fromScrollWheel)
            targetPosition = MathHelper.clamp(targetPosition, this.scrollerPosition - this.scrollerSpeed, this.scrollerPosition + this.scrollerSpeed);
        double min = this.minValue.getAsDouble(), max = this.maxValue.getAsDouble();
        double range = max - min;
        if(range <= 0)
            return; // Give up
        if(this.invertScrolling)
            targetPosition = 1 - targetPosition;
        double value = targetPosition * range + min;
        if(this.stepSize != null)
            value = min + Math.round((value - min) / this.stepSize) * this.stepSize;
        double oldValue = MathHelper.clamp(this.value.getAsDouble(), min, max);
        this.onChange.onChange(oldValue, value);
    }

    private void updateDrag(int mouseY){
        this.tryScrollTo((mouseY - this.y - this.scrollerHeight / 2f) / (this.height - this.scrollerHeight), false);
    }

    @Override
    public CursorType curser(int mouseX, int mouseY){
        if(!this.active || !this.canUserMoveScroller())
            return null;
        return this.dragging ? CursorTypes.resizeVertical() : CursorTypes.pointingHand();
    }

    @Override
    public boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        if(this.active && this.canUserMoveScroller() && !hasBeenHandled && button == 0 && this.isFocused()){
            this.dragging = true;
            this.updateDrag(mouseY);
            hasBeenHandled = true;
        }
        return super.mousePressed(mouseX, mouseY, button, hasBeenHandled);
    }

    @Override
    public boolean mouseReleased(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        this.dragging = false;
        return super.mouseReleased(mouseX, mouseY, button, hasBeenHandled);
    }

    @Override
    public boolean mouseScrolled(int mouseX, int mouseY, double scrollAmount, boolean hasBeenHandled){
        if(this.active && this.canUserMoveScroller() && !hasBeenHandled){
            if(this.scrollWheelStep == null)
                this.tryScrollTo(this.scrollerPosition - scrollAmount / 5f, true);
            else if(this.scrollWheelStep != 0){
                double min = this.minValue.getAsDouble(), max = this.maxValue.getAsDouble();
                double range = max - min;
                if(range > 0){
                    double value = MathHelper.clamp(this.value.getAsDouble(), min, max);
                    if(this.invertScrolling)
                        scrollAmount = -scrollAmount;
                    value += scrollAmount * this.scrollWheelStep;
                    this.tryScrollTo((value - min) / range, true);
                }
            }
        }
        return super.mouseScrolled(mouseX, mouseY, scrollAmount, hasBeenHandled);
    }

    public static class Builder {

        private int x, y;
        private int width = 12, height;
        private int scrollerHeight = 15;
        private DoubleSupplier value, minValue, maxValue;
        private boolean invertScrolling;
        private Double stepSize;
        private ScrollListener onChange;
        private Integer scrollerSpeed;
        private boolean smoothValues;
        private ResourceLocation background = BACKGROUND, scroller = SCROLLER;
        private Double scrollWheelStep;

        private Builder(int height){
            this.height = height;
            this.scrollRange(0, 0, 1);
        }

        public Builder position(int x, int y){
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder width(int width){
            this.width = width;
            return this;
        }

        public Builder height(int height){
            this.height = height;
            return this;
        }

        public Builder size(int width, int height){
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder scrollerHeight(int height){
            this.scrollerHeight = height;
            return this;
        }

        public Builder scrollValue(DoubleSupplier value, DoubleSupplier min, DoubleSupplier max){
            this.value = value;
            this.minValue = min;
            this.maxValue = max;
            return this;
        }

        public Builder scrollValue(DoubleSupplier value, double min, double max){
            if(min > max)
                throw new IllegalArgumentException("Minimum value must be smaller than maximum value!");
            return this.scrollValue(value, () -> min, () -> max);
        }

        public Builder scrollRange(double initialValue, double min, double max){
            if(initialValue < min || initialValue > max)
                throw new IllegalArgumentException("Initial value must be between min and max value!");
            Holder<Double> value = new Holder<>(initialValue);
            return this.scrollValue(value::get, min, max).onChange((oldValue, newValue) -> value.set(newValue));
        }

        public Builder scrollStepSize(double step){
            this.stepSize = step;
            return this;
        }

        /**
         * Sets the maximum change in value per frame.
         * @param speed        maximum number of pixels that the scroller can move per frame
         * @param smoothValues whether to limit the speed at which the underlying value is updated according to the scroller's speed
         */
        public Builder scrollerSpeed(int speed, boolean smoothValues){
            this.scrollerSpeed = speed;
            this.smoothValues = smoothValues;
            return this;
        }

        /**
         * Limits the speed at which the scroller moves to 8 pixels per frame.
         */
        public Builder smoothScrolling(){
            return this.scrollerSpeed(8, true);
        }

        public Builder onChange(ScrollListener onChange){
            this.onChange = onChange;
            return this;
        }

        public Builder onChange(DoubleConsumer onChange){
            return this.onChange((oldValue, newValue) -> onChange.accept(newValue));
        }

        public Builder background(ResourceLocation texture){
            this.background = texture;
            return this;
        }

        public Builder noBackground(){
            return this.background(null);
        }

        public Builder scroller(ResourceLocation texture){
            this.scroller = texture;
            return this;
        }

        public Builder scrollWheelValueChange(double step){
            this.scrollWheelStep = step;
            return this;
        }

        public Builder invertScrolling(){
            this.invertScrolling = true;
            return this;
        }

        public ScrollbarWidget build(){
            if(this.scrollerHeight >= this.height)
                throw new IllegalStateException("Scroller height must be smaller than the height of the scrollbar!");
            return new ScrollbarWidget(
                this.x, this.y, this.width, this.height, this.scrollerHeight,
                this.value, this.minValue, this.maxValue,
                this.invertScrolling, this.stepSize,
                this.onChange,
                this.scrollerSpeed, this.smoothValues,
                this.background, this.scroller,
                this.scrollWheelStep
            );
        }
    }

    public interface ScrollListener {
        void onChange(double oldValue, double newValue);
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/premade/TextFieldWidget.java

```java
package com.supermartijn642.core.gui.widget.premade;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.CursorType;
import com.supermartijn642.core.gui.CursorTypes;
import com.supermartijn642.core.gui.ScreenUtils;
import com.supermartijn642.core.gui.widget.BaseWidget;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created 1/20/2021 by SuperMartijn642
 */
public class TextFieldWidget extends BaseWidget {

    private String text;
    private String suggestion = "";
    protected int maxLength;
    private int cursorBlinkCounter;
    protected boolean selected;
    private boolean active = true;
    protected int lineScrollOffset;
    protected int cursorPosition;
    protected int selectionPos;
    protected boolean drawBackground = true;
    protected int activeTextColor = 14737632, inactiveTextColor = 7368816;

    private final BiConsumer<String,String> changeListener;

    public TextFieldWidget(int x, int y, int width, int height, String defaultText, int maxLength, BiConsumer<String,String> changeListener){
        super(x, y, width, height);
        this.text = defaultText;
        this.maxLength = maxLength;
        this.changeListener = changeListener;
        this.cursorPosition = this.selectionPos = defaultText.length();
    }

    public TextFieldWidget(int x, int y, int width, int height, String defaultText, int maxLength, Consumer<String> changeListener){
        this(x, y, width, height, defaultText, maxLength, changeListener == null ? null : (a, b) -> changeListener.accept(b));
    }

    public TextFieldWidget(int x, int y, int width, int height, String defaultText, int maxLength){
        this(x, y, width, height, defaultText, maxLength, (BiConsumer<String,String>)null);
    }

    @Override
    public ITextComponent getNarrationMessage(){
        return TextComponents.translation("gui.narrate.editBox", this.suggestion, this.text).get();
    }

    @Override
    public void update(){
        this.cursorBlinkCounter++;
    }

    @Override
    public void render(MatrixStack poseStack, int mouseX, int mouseY){
        if(this.drawBackground)
            this.drawBackground(poseStack);

        int textColor = this.active ? this.activeTextColor : this.inactiveTextColor;
        int relativeCursor = this.cursorPosition - this.lineScrollOffset;
        int relativeSelection = this.selectionPos - this.lineScrollOffset;
        FontRenderer fontRenderer = ClientUtils.getFontRenderer();
        String s = fontRenderer.plainSubstrByWidth(this.text.substring(this.lineScrollOffset), this.width - 8);
        boolean cursorInView = relativeCursor >= 0 && relativeCursor <= s.length();
        boolean shouldBlink = this.selected && this.cursorBlinkCounter / 8 % 2 == 0 && cursorInView;
        int left = this.x + 4;
        int top = this.y + (this.height - 8) / 2;
        int leftOffset = left;

        if(relativeSelection > s.length())
            relativeSelection = s.length();

        if(!s.isEmpty()){
            String s1 = cursorInView ? s.substring(0, relativeCursor) : s;
            leftOffset = fontRenderer.draw(poseStack, s1, left, top, textColor) + 1;
        }

        boolean cursorAtEnd = this.cursorPosition < this.text.length();
        int cursorX = leftOffset;

        if(!cursorInView)
            cursorX = relativeCursor > 0 ? left + this.width : left;
        else if(cursorAtEnd){
            cursorX = leftOffset - 1;
            leftOffset--;
        }

        // draw text
        if(!s.isEmpty() && cursorInView && relativeCursor < s.length())
            fontRenderer.draw(poseStack, s.substring(relativeCursor), leftOffset, top, textColor);

        // draw suggestion
        if(!this.suggestion.isEmpty() && this.text.isEmpty())
            fontRenderer.drawShadow(poseStack, fontRenderer.plainSubstrByWidth(this.suggestion, this.width - 8 - fontRenderer.width("...")) + "...", cursorX, top, -8355712);

        // draw cursor
        if(shouldBlink){
            if(cursorAtEnd)
                ScreenUtils.fillRect(poseStack, cursorX - 0.5f, top - 1, 1, fontRenderer.lineHeight, -3092272);
            else
                fontRenderer.drawShadow(poseStack, "_", cursorX, top, textColor);
        }

        if(relativeSelection != relativeCursor){
            int l1 = left + fontRenderer.width(s.substring(0, relativeSelection));
            this.drawSelectionBox(poseStack, cursorX, top - 1, l1 - 1, top + 1 + fontRenderer.lineHeight);
        }
    }

    protected void drawBackground(MatrixStack poseStack){
        ScreenUtils.fillRect(poseStack, this.x, this.y, this.width, this.height, this.selected ? -1 : -6250336);
        ScreenUtils.fillRect(poseStack, this.x + 1, this.y + 1, this.width - 2, this.height - 2, -16777216);
    }

    protected void drawSelectionBox(MatrixStack poseStack, int startX, int startY, int endX, int endY){
        if(startX < endX){
            int i = startX;
            startX = endX;
            endX = i;
        }

        if(startY < endY){
            int j = startY;
            startY = endY;
            endY = j;
        }

        if(endX > this.x + this.width){
            endX = this.x + this.width;
        }

        if(startX > this.x + this.width){
            startX = this.x + this.width;
        }

        Matrix4f matrix = poseStack.last().pose();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        RenderSystem.color4f(0.0F, 0.0F, 255.0F, 255.0F);
        RenderSystem.disableTexture();
        RenderSystem.enableColorLogicOp();
        RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.vertex(matrix, startX, endY, 0).endVertex();
        bufferbuilder.vertex(matrix, endX, endY, 0).endVertex();
        bufferbuilder.vertex(matrix, endX, startY, 0).endVertex();
        bufferbuilder.vertex(matrix, startX, startY, 0).endVertex();
        tessellator.end();
        RenderSystem.disableColorLogicOp();
        RenderSystem.enableTexture();
    }

    public void clear(){
        this.setText("");
    }

    public void setText(String text){
        String oldText = this.text;

        this.setTextSuppressed(text);

        if(!oldText.equals(this.text))
            this.onTextChanged(oldText, text);
    }

    public String getText(){
        return this.text;
    }

    /**
     * Sets {@code text} without calling {@link TextFieldWidget#onTextChanged(String, String)}
     */
    public void setTextSuppressed(String text){
        if(text == null)
            text = "";
        else if(text.length() > this.maxLength)
            text = SharedConstants.filterText(text.substring(0, this.maxLength));

        this.lineScrollOffset = 0;
        this.cursorPosition = 0;
        this.selectionPos = 0;
        this.text = text;
    }

    protected void addTextAtCursor(String text){
        String oldText = this.text;

        text = SharedConstants.filterText(text);
        if(text.length() + this.text.length() - this.getSelectedText().length() > this.maxLength)
            text = text.substring(0, this.maxLength - this.text.length() + this.getSelectedText().length());

        int min = Math.min(this.cursorPosition, this.selectionPos);
        int max = Math.max(this.cursorPosition, this.selectionPos);
        this.text = this.text.substring(0, min) + text + this.text.substring(max);
        this.cursorPosition = min + text.length();
        this.selectionPos = this.cursorPosition;
        this.moveLineOffsetToCursor();

        if(!oldText.equals(this.text)){
            this.cursorBlinkCounter = 1;
            this.onTextChanged(oldText, this.text);
        }
    }

    protected void removeAtCursor(boolean left){
        if(this.text.isEmpty())
            return;

        String oldText = text;
        if(this.cursorPosition != this.selectionPos){
            this.text = this.text.substring(0, Math.min(this.cursorPosition, this.selectionPos)) + this.text.substring(Math.max(this.cursorPosition, this.selectionPos));
            this.cursorPosition = this.selectionPos = Math.min(this.cursorPosition, this.selectionPos);
        }else if(left && this.cursorPosition > 0){
            this.text = this.text.substring(0, this.cursorPosition - 1) + this.text.substring(this.cursorPosition);
            this.cursorPosition -= 1;
            this.selectionPos -= 1;
        }else if(!left && this.cursorPosition < this.text.length())
            this.text = this.text.substring(0, this.cursorPosition) + this.text.substring(this.cursorPosition + 1);

        this.moveLineOffsetToCursor();

        this.cursorBlinkCounter = 1;

        this.onTextChanged(oldText, this.text);
    }

    protected void moveLineOffsetToCursor(){
        FontRenderer fontRenderer = ClientUtils.getFontRenderer();
        int availableWidth = this.width - 8 - (this.cursorPosition == this.text.length() ? fontRenderer.width("_") : 0);
        int min = Math.min(this.cursorPosition + 1, this.text.length()) - fontRenderer.plainSubstrByWidth(new StringBuilder(this.text.substring(0, Math.min(this.text.length(), this.cursorPosition + 2))).reverse().toString(), availableWidth).length();
        int max = Math.max(this.cursorPosition - 1, 0) + fontRenderer.plainSubstrByWidth(this.text.substring(Math.max(this.cursorPosition - 1, 0)), availableWidth).length();
        max = max - fontRenderer.plainSubstrByWidth(new StringBuilder(this.text.substring(0, max)).reverse().toString(), availableWidth).length();
        this.lineScrollOffset = Math.min(Math.max(this.lineScrollOffset, min), max);
    }

    public String getSelectedText(){
        if(this.cursorPosition == this.selectionPos)
            return "";

        return this.text.substring(Math.min(this.cursorPosition, this.selectionPos), Math.max(this.cursorPosition, this.selectionPos));
    }

    protected void onTextChanged(String oldText, String newText){
        if(this.changeListener != null)
            this.changeListener.accept(oldText, newText);
    }

    public void setSuggestion(String suggestion){
        this.suggestion = suggestion == null ? "" : suggestion;
    }

    public String getSuggestion(){
        return this.suggestion;
    }

    public void setTextColors(int activeTextColor, int inactiveTextColor){
        this.activeTextColor = activeTextColor;
        this.inactiveTextColor = inactiveTextColor;
    }

    public void setDrawBackground(boolean drawBackground){
        this.drawBackground = drawBackground;
    }

    public boolean isSelected(){
        return this.selected;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public void setActive(boolean active){
        this.active = active;
        if(!active)
            this.setSelected(false);
    }

    @Override
    public CursorType curser(int mouseX, int mouseY){
        return this.active ? CursorTypes.iBeam() : null;
    }

    @Override
    public boolean keyPressed(int keyCode, boolean hasBeenHandled){
        if(hasBeenHandled || !this.canWrite() || !this.selected)
            return false;

        boolean shift = Screen.hasShiftDown();
        if(keyCode == 256){
            this.setSelected(false);
        }else if(Screen.isSelectAll(keyCode)){
            this.lineScrollOffset = 0;
            this.cursorPosition = this.text.length();
            this.selectionPos = 0;
        }else if(Screen.isCopy(keyCode)){
            ClientUtils.getMinecraft().keyboardHandler.setClipboard(this.getSelectedText());
        }else if(Screen.isPaste(keyCode)){
            this.addTextAtCursor(ClientUtils.getMinecraft().keyboardHandler.getClipboard());
        }else if(Screen.isCut(keyCode)){
            ClientUtils.getMinecraft().keyboardHandler.setClipboard(this.getSelectedText());
            this.addTextAtCursor("");
        }else{
            switch(keyCode){
                case 259: // backspace
                    this.removeAtCursor(true);
                    break;
                case 260: // insert
                case 264: // ?
                case 265: // ?
                case 266: // page up
                case 267: // page down
                default:
                    return true;
                case 261: // delete
                    this.removeAtCursor(false);
                    break;
                case 262: // right
                    if(!shift && this.cursorPosition != this.selectionPos)
                        this.cursorPosition = this.selectionPos = Math.max(this.cursorPosition, this.selectionPos);
                    else if(this.cursorPosition < this.text.length()){
                        this.cursorPosition = this.cursorPosition + 1;
                        if(!shift)
                            this.selectionPos = this.cursorPosition;
                    }
                    this.moveLineOffsetToCursor();
                    break;
                case 263: // left
                    if(!shift && this.cursorPosition != this.selectionPos)
                        this.cursorPosition = this.selectionPos = Math.min(this.cursorPosition, this.selectionPos);
                    else if(this.cursorPosition > 0){
                        this.cursorPosition = this.cursorPosition - 1;
                        if(!shift)
                            this.selectionPos = this.cursorPosition;
                    }
                    this.moveLineOffsetToCursor();
                    break;
                case 268: // home
                    this.cursorPosition = this.selectionPos = 0;
                    this.moveLineOffsetToCursor();
                    break;
                case 269: // end
                    this.cursorPosition = this.selectionPos = this.text.length();
                    this.moveLineOffsetToCursor();
                    break;
            }

            return true;
        }

        return true;
    }

    @Override
    public boolean charTyped(char character, boolean hasBeenHandled){
        if(hasBeenHandled || !this.canWrite())
            return false;

        if(SharedConstants.isAllowedChatCharacter(character))
            this.addTextAtCursor(Character.toString(character));

        return true;
    }

    public boolean canWrite(){
        return this.active && this.selected;
    }

    @Override
    public boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled){
        if(!hasBeenHandled && this.active && this.isHovered(mouseX, mouseY)){
            this.setSelected(true);
            if(button == 1)
                this.clear();
            else{
                int offset = MathHelper.floor(mouseX) - this.x - 4;

                FontRenderer font = ClientUtils.getFontRenderer();
                String s = font.plainSubstrByWidth(this.text.substring(this.lineScrollOffset), Math.min(offset, this.width - 8));
                this.cursorPosition = s.length() + this.lineScrollOffset;
                if(!Screen.hasShiftDown())
                    this.selectionPos = this.cursorPosition;
            }
            return true;
        }else
            this.setSelected(false);

        return false;
    }

    private boolean isHovered(int mouseX, int mouseY){
        return this.x <= mouseX && this.x + this.width > mouseX && this.y <= mouseY && this.y + this.height > mouseY;
    }
}
```

### src/main/java/com/supermartijn642/core/gui/widget/Widget.java

```java
package com.supermartijn642.core.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.gui.CursorType;
import net.minecraft.util.text.ITextComponent;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
public interface Widget {

    /**
     * @return the title to be read by the narrator when the widget is focused by the user
     */
    ITextComponent getNarrationMessage();

    /**
     * @return the width of the widget
     */
    int width();

    /**
     * @return the height of the widget
     */
    int height();

    /**
     * @return the x-position of this widget
     */
    int left();

    /**
     * @return the y-position of this widget
     */
    int top();

    /**
     * Called when the widget is added.
     */
    void initialize();

    /**
     * Sets whether this widget is the one the user is focused on.
     */
    void setFocused(boolean focused);

    /**
     * Called once per tick when the widget is shown.
     */
    void update();

    /**
     * Renders the widget's background. This will be called first in the render chain.
     */
    void renderBackground(MatrixStack poseStack, int mouseX, int mouseY);

    /**
     * Renders the widget's main features.
     * Called after the background and slots are drawn, but before items are drawn.
     */
    void render(MatrixStack poseStack, int mouseX, int mouseY);

    /**
     * Renders the widget's foreground.
     * Called after main features and items are drawn, but before cursor item and overlay are drawn.
     */
    void renderForeground(MatrixStack poseStack, int mouseX, int mouseY);

    /**
     * Called after foreground and cursor item are drawn, but before tooltips are drawn.
     */
    void renderOverlay(MatrixStack poseStack, int mouseX, int mouseY);

    /**
     * Renders tooltips for the given {@code mouseX} and {@code mouseY}.
     * This will be called last in the render chain.
     */
    void renderTooltips(MatrixStack poseStack, int mouseX, int mouseY);

    /**
     * Called when the widget is disposed of.
     */
    void discard();

    /**
     * Gets the cursor to be used when hovering this widget.
     */
    CursorType curser(int mouseX, int mouseY);

    /**
     * Called when a mouse button is pressed down.
     * @param mouseX         x-position of the mouse
     * @param mouseY         y-position of the mouse
     * @param button         the button which is pressed down
     * @param hasBeenHandled whether the mouse press has already been handled
     * @return whether this widget has handled the mouse press
     */
    boolean mousePressed(int mouseX, int mouseY, int button, boolean hasBeenHandled);

    /**
     * Called when a mouse button is released.
     * @param mouseX         x-position of the mouse
     * @param mouseY         y-position of the mouse
     * @param button         the button which is pressed down
     * @param hasBeenHandled whether the mouse press has already been handled
     * @return whether this widget has handled the mouse release
     */
    boolean mouseReleased(int mouseX, int mouseY, int button, boolean hasBeenHandled);

    /**
     * Called when the mouse wheel is scrolled.
     * @param mouseX         x-position of the mouse
     * @param mouseY         y-position of the mouse
     * @param scrollAmount   the amount the mouse wheel was scrolled by
     * @param hasBeenHandled whether the mouse press has already been handled
     * @return whether this widget has handled the mouse scroll
     */
    boolean mouseScrolled(int mouseX, int mouseY, double scrollAmount, boolean hasBeenHandled);

    /**
     * Called when a key is pressed down.
     * @param keyCode code of the key which was pressed
     * @return whether this widget has handled the key press
     */
    boolean keyPressed(int keyCode, boolean hasBeenHandled);

    /**
     * Called when a key is released.
     * @param keyCode code of the key which was released
     * @return whether this widget has handled the key release
     */
    boolean keyReleased(int keyCode, boolean hasBeenHandled);

    /**
     * Called when a character is typed. May be called in addition to {@link #keyPressed(int, boolean)}.
     * @param character the character which was typed
     * @return whether this widget has handled the character
     */
    boolean charTyped(char character, boolean hasBeenHandled);

}
```

### src/main/java/com/supermartijn642/core/gui/WidgetContainerScreen.java

```java
package com.supermartijn642.core.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.widget.ContainerWidget;
import com.supermartijn642.core.gui.widget.Widget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.util.InputMappings;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
public class WidgetContainerScreen<T extends Widget, X extends BaseContainer> extends ContainerScreen<X> {

    private static final ResourceLocation SLOT_TEXTURE = new ResourceLocation("supermartijn642corelib", "textures/gui/slot.png");

    public static <T extends Widget, X extends BaseContainer> WidgetContainerScreen<T,X> of(T widget, X container, boolean drawSlots, boolean isPauseScreen){
        return new WidgetContainerScreen<>(widget, container, drawSlots, isPauseScreen);
    }

    public static <T extends Widget, X extends BaseContainer> WidgetContainerScreen<T,X> of(T widget, X container, boolean drawSlots){
        return new WidgetContainerScreen<>(widget, container, drawSlots);
    }

    protected final X container;
    protected final T widget;
    private boolean initialized = false;
    private final boolean drawSlots;
    private final boolean isPauseScreen;

    public WidgetContainerScreen(T widget, X container, boolean drawSlots, boolean isPauseScreen){
        super(container, container.player.inventory, TextComponents.empty().get());
        this.widget = widget;
        this.container = container;
        this.drawSlots = drawSlots;
        this.isPauseScreen = isPauseScreen;
    }

    public WidgetContainerScreen(T widget, X container, boolean drawSlots){
        this(widget, container, drawSlots, false);
    }

    public T getWidget(){
        return this.widget;
    }

    @Override
    public void init(){
        if(!this.initialized){
            if(this.widget instanceof ContainerWidget<?>)
                //noinspection unchecked,rawtypes
                ((ContainerWidget)this.widget).initialize(this.container);
            else
                this.widget.initialize();
            this.initialized = true;
        }

        this.imageWidth = this.widget.width();
        this.imageHeight = this.widget.height();
        super.init();
    }

    @Override
    public void onClose(){
        this.widget.discard();
        super.onClose();
    }

    @Override
    public void tick(){
        this.widget.update();
    }

    @Override
    public void render(MatrixStack poseStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(poseStack);

        int offsetX = (this.width - this.widget.width()) / 2, offsetY = (this.height - this.widget.height()) / 2;
        int offsetMouseX = mouseX - offsetX;
        int offsetMouseY = mouseY - offsetY;

        RenderSystem.pushMatrix();
        RenderSystem.translatef(offsetX, offsetY, 0);
        RenderSystem.disableDepthTest();

        // Update whether the widget is focused
        this.widget.setFocused(offsetMouseX >= 0 && offsetMouseX < this.widget.width() && offsetMouseY >= 0 && offsetMouseY < this.widget.height());

        // Update cursor
        CursorType curser = this.widget.curser(offsetMouseX, offsetMouseY);
        if(curser != null)
            ScreenUtils.requestCursor(curser);

        // Render the widget background
        this.widget.renderBackground(poseStack, offsetMouseX, offsetMouseY);

        if(this.drawSlots){
            for(Slot slot : this.container.slots){
                if(!slot.isActive())
                    continue;
                if(slot instanceof CustomSlot){
                    if(((CustomSlot)slot).showBackground()){
                        ScreenUtils.bindTexture(SLOT_TEXTURE);
                        ScreenUtils.drawTexture(poseStack, slot.x - 1, slot.y - 1, ((CustomSlot)slot).getWidth(), ((CustomSlot)slot).getHeight());
                    }
                }else{
                    ScreenUtils.bindTexture(SLOT_TEXTURE);
                    ScreenUtils.drawTexture(poseStack, slot.x - 1, slot.y - 1, 18, 18);
                }
            }
        }

        RenderSystem.popMatrix();

        MinecraftForge.EVENT_BUS.post(new GuiContainerEvent.DrawBackground(this, poseStack, mouseX, mouseY));

        RenderSystem.pushMatrix();
        RenderSystem.translatef(offsetX, offsetY, 0);

        // Render the widget
        this.widget.render(poseStack, offsetMouseX, offsetMouseY);

        this.hoveredSlot = null;
        for(Slot slot : this.container.slots){
            if(!slot.isActive())
                continue;

            if(slot instanceof CustomSlot){
                // Custom slot
                CustomSlot customSlot = (CustomSlot)slot;
                int slotWidth = customSlot.getWidth();
                int slotHeight = customSlot.getHeight();
                if(customSlot.showItem()){
                    float scale = Math.min(slotWidth / 18f, slotHeight / 18f);

                    RenderSystem.pushMatrix();
                    if(customSlot.scaleItemToSize() && scale != 1){
                        RenderSystem.translatef(slot.x, slot.y, 0);
                        RenderSystem.scaled(scale, scale, scale);
                        RenderSystem.translatef(-slot.x, -slot.y, 0);
                        this.renderSlot(poseStack, slot);
                    }else{
                        RenderSystem.translatef((customSlot.getWidth() - 18) / 2f, (customSlot.getHeight() - 18) / 2f, 0);
                        this.renderSlot(poseStack, slot);
                    }
                    RenderSystem.popMatrix();
                }
                if(this.isHovering(slot.x, slot.y, slotWidth - 2, slotHeight - 2, mouseX, mouseY) && customSlot.showHighlight()){
                    this.hoveredSlot = slot;
                    int slotColor = this.getSlotColor(0);
                    RenderSystem.disableDepthTest();
                    RenderSystem.colorMask(true, true, true, false);
                    this.fillGradient(poseStack, slot.x, slot.y, slot.x + slotWidth - 2, slot.y + slotHeight - 2, slotColor, slotColor);
                    RenderSystem.colorMask(true, true, true, true);
                    RenderSystem.enableDepthTest();
                }
            }else{
                // Regular slot
                this.renderSlot(poseStack, slot);
                if(this.isHovering(slot.x, slot.y, 16, 16, mouseX, mouseY)){
                    this.hoveredSlot = slot;
                    RenderSystem.disableDepthTest();
                    RenderSystem.colorMask(true, true, true, false);
                    int slotColor = this.getSlotColor(0);
                    this.fillGradient(poseStack, slot.x, slot.y, slot.x + 16, slot.y + 16, slotColor, slotColor);
                    RenderSystem.colorMask(true, true, true, true);
                    RenderSystem.enableDepthTest();
                }
            }
        }

        // Render the widget's foreground
        this.widget.renderForeground(poseStack, offsetMouseX, offsetMouseY);

        this.renderTooltip(poseStack, offsetMouseX, offsetMouseY);

        MinecraftForge.EVENT_BUS.post(new GuiContainerEvent.DrawForeground(this, poseStack, mouseX, mouseY));

        ItemStack cursorStack = this.draggingItem.isEmpty() ? this.inventory.getCarried() : this.draggingItem;
        if(!cursorStack.isEmpty()){
            int offset = this.draggingItem.isEmpty() ? 8 : 16;
            String s = null;
            if(!this.draggingItem.isEmpty() && this.isSplittingStack){
                cursorStack = cursorStack.copy();
                cursorStack.setCount(MathHelper.ceil(cursorStack.getCount() / 2f));
            }else if(this.isQuickCrafting && this.quickCraftSlots.size() > 1){
                cursorStack = cursorStack.copy();
                cursorStack.setCount(this.quickCraftingRemainder);
                if(cursorStack.isEmpty())
                    s = TextFormatting.YELLOW + "0";
            }

            this.renderFloatingItem(cursorStack, offsetMouseX - 8, offsetMouseY - offset, s);
        }

        if(!this.snapbackItem.isEmpty()){
            float f = (float)(Util.getMillis() - this.snapbackTime) / 100.0F;
            if(f >= 1.0F){
                f = 1.0F;
                this.snapbackItem = ItemStack.EMPTY;
            }

            int j2 = this.snapbackEnd.x - this.snapbackStartX;
            int k2 = this.snapbackEnd.y - this.snapbackStartY;
            int j1 = this.snapbackStartX + (int)(j2 * f);
            int k1 = this.snapbackStartY + (int)(k2 * f);
            this.renderFloatingItem(this.snapbackItem, j1, k1, null);
        }

        // Render the widget's overlay
        this.widget.renderOverlay(poseStack, offsetMouseX, offsetMouseY);
        // Render the widget's tooltips
        this.widget.renderTooltips(poseStack, offsetMouseX, offsetMouseY);

        RenderSystem.popMatrix();
        RenderSystem.enableDepthTest();
    }

    @Override
    protected void renderBg(MatrixStack poseStack, float partialTicks, int mouseX, int mouseY){
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button){
        int offsetX = (this.width - this.widget.width()) / 2, offsetY = (this.height - this.widget.height()) / 2;
        return this.widget.mousePressed((int)mouseX - offsetX, (int)mouseY - offsetY, button, false) || super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button){
        int offsetX = (this.width - this.widget.width()) / 2, offsetY = (this.height - this.widget.height()) / 2;
        return this.widget.mouseReleased((int)mouseX - offsetX, (int)mouseY - offsetY, button, false) || super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount){
        int offsetX = (this.width - this.widget.width()) / 2, offsetY = (this.height - this.widget.height()) / 2;
        return this.widget.mouseScrolled((int)mouseX - offsetX, (int)mouseY - offsetY, amount, false) || super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers){
        if(this.widget.keyPressed(keyCode, false))
            return true;

        InputMappings.Input key = InputMappings.getKey(keyCode, scanCode);
        if(ClientUtils.getMinecraft().options.keyInventory.isActiveAndMatches(key)){
            this.onClose();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers){
        return this.widget.keyReleased(keyCode, false) || super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char character, int modifiers){
        return this.widget.charTyped(character, false) || super.charTyped(character, modifiers);
    }

    @Override
    public boolean isPauseScreen(){
        return this.isPauseScreen;
    }

    @Override
    public String getNarrationMessage(){
        ITextComponent message = this.widget.getNarrationMessage();
        return message == null ? "" : TextComponents.fromTextComponent(message).format();
    }
}
```

### src/main/java/com/supermartijn642/core/gui/WidgetScreen.java

```java
package com.supermartijn642.core.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.widget.Widget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.ITextComponent;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
public class WidgetScreen<T extends Widget> extends Screen {

    public static <T extends Widget> WidgetScreen<T> of(T widget){
        return new WidgetScreen<>(widget);
    }

    public static <T extends Widget> WidgetScreen<T> of(T widget, boolean isPauseScreen){
        return new WidgetScreen<>(widget, isPauseScreen);
    }

    protected final T widget;
    private boolean initialized = false;
    private boolean isPauseScreen = false;

    public WidgetScreen(T widget, boolean isPauseScreen){
        super(TextComponents.empty().get());
        this.widget = widget;
        this.isPauseScreen = isPauseScreen;
    }

    public WidgetScreen(T widget){
        this(widget, false);
    }

    public T getWidget(){
        return this.widget;
    }

    @Override
    protected void init(){
        if(!this.initialized){
            this.widget.initialize();
            this.initialized = true;
        }
    }

    @Override
    public void onClose(){
        this.widget.discard();
        super.onClose();
    }

    @Override
    public void tick(){
        this.widget.update();
    }

    @Override
    public void render(MatrixStack poseStack, int mouseX, int mouseY, float partialTicks){
        this.renderBackground(poseStack);

        int offsetX = (this.width - this.widget.width()) / 2, offsetY = (this.height - this.widget.height()) / 2;
        mouseX -= offsetX;
        mouseY -= offsetY;

        RenderSystem.pushMatrix();
        RenderSystem.translatef(offsetX, offsetY, 0);

        // Update whether the widget is focused
        this.widget.setFocused(mouseX >= 0 && mouseX < this.widget.width() && mouseY >= 0 && mouseY < this.widget.height());

        // Render the widget background
        this.widget.renderBackground(poseStack, mouseX, mouseY);
        // Render the widget
        this.widget.render(poseStack, mouseX, mouseY);
        // Render the widget's foreground
        this.widget.renderForeground(poseStack, mouseX, mouseY);
        // Render the widget's overlay
        this.widget.renderOverlay(poseStack, mouseX, mouseY);
        // Render the widget's tooltips
        this.widget.renderTooltips(poseStack, mouseX, mouseY);

        RenderSystem.popMatrix();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button){
        int offsetX = (this.width - this.widget.width()) / 2, offsetY = (this.height - this.widget.height()) / 2;
        mouseX -= offsetX;
        mouseY -= offsetY;
        return this.widget.mousePressed((int)mouseX, (int)mouseY, button, false) || super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button){
        int offsetX = (this.width - this.widget.width()) / 2, offsetY = (this.height - this.widget.height()) / 2;
        mouseX -= offsetX;
        mouseY -= offsetY;
        return this.widget.mouseReleased((int)mouseX, (int)mouseY, button, false) || super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount){
        int offsetX = (this.width - this.widget.width()) / 2, offsetY = (this.height - this.widget.height()) / 2;
        mouseX -= offsetX;
        mouseY -= offsetY;
        return this.widget.mouseScrolled((int)mouseX, (int)mouseY, amount, false) || super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers){
        if(this.widget.keyPressed(keyCode, false))
            return true;

        InputMappings.Input key = InputMappings.getKey(keyCode, scanCode);
        if(ClientUtils.getMinecraft().options.keyInventory.isActiveAndMatches(key)){
            this.onClose();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers){
        return this.widget.keyReleased(keyCode, false) || super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char character, int modifiers){
        return this.widget.charTyped(character, false) || super.charTyped(character, modifiers);
    }

    @Override
    public boolean isPauseScreen(){
        return this.isPauseScreen;
    }

    @Override
    public String getNarrationMessage(){
        ITextComponent message = this.widget.getNarrationMessage();
        return message == null ? "" : TextComponents.fromTextComponent(message).format();
    }
}
```

### src/main/java/com/supermartijn642/core/item/BaseBlockItem.java

```java
package com.supermartijn642.core.item;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created 24/07/2022 by SuperMartijn642
 */
public class BaseBlockItem extends BlockItem {

    private final ItemProperties properties;

    public BaseBlockItem(Block block, Properties properties){
        super(block, properties);
        this.properties = null;
    }

    public BaseBlockItem(Block block, ItemProperties properties){
        super(block, properties.toUnderlying());
        this.properties = properties;
    }

    /**
     * Adds information to be displayed when hovering over this item in the inventory.
     * @param stack    the stack being hovered over
     * @param level    the world the player is in, may be {@code null}
     * @param info     consumes the information which should be added
     * @param advanced whether advanced tooltips is enabled
     */
    protected void appendItemInformation(ItemStack stack, @Nullable IBlockReader level, Consumer<ITextComponent> info, boolean advanced){
    }

    /**
     * Called when a player right-clicks with this item.
     * @return whether the player's interaction should be consumed or passed on, together with the new item stack
     */
    public ItemUseResult interact(ItemStack stack, PlayerEntity player, Hand hand, World level){
        return ItemUseResult.fromUnderlying(super.use(level, player, hand));
    }

    /**
     * Called when a player right-clicks on a block with this item, before the block is interacted with.
     * @return whether the player's interaction should be consumed or passed on
     */
    public InteractionFeedback interactWithBlockFirst(ItemStack stack, PlayerEntity player, Hand hand, World level, BlockPos hitPos, Direction hitSide, Vector3d hitLocation){
        return InteractionFeedback.PASS;
    }

    /**
     * Called when a player right-clicks on a block with this item, after the block is interacted with.
     * @return whether the player's interaction should be consumed or passed on
     */
    public InteractionFeedback interactWithBlock(ItemStack stack, PlayerEntity player, Hand hand, World level, BlockPos hitPos, Direction hitSide, Vector3d hitLocation){
        return InteractionFeedback.fromUnderlying(super.useOn(new ItemUseContext(level, player, hand, stack, new BlockRayTraceResult(hitLocation, hitSide, hitPos, false))));
    }

    /**
     * Called when a player right-clicks on an entity.
     * @return whether the player's interaction should be consumed or passed on
     */
    public InteractionFeedback interactWithEntity(ItemStack stack, LivingEntity target, PlayerEntity player, Hand hand){
        return InteractionFeedback.PASS;
    }

    /**
     * Called once every tick when this item is in an entity's inventory.
     */
    public void inventoryUpdate(ItemStack stack, World level, Entity entity, int itemSlot, boolean isSelected){
    }

    @OnlyIn(Dist.CLIENT) // Needed in 1.16 because ITooltipFlag is client-side only
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> information, ITooltipFlag flag){
        this.appendItemInformation(stack, level, information::add, flag.isAdvanced());
        super.appendHoverText(stack, level, information, flag);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand){
        return this.interact(player.getItemInHand(hand), player, hand, level).toUnderlying(level.isClientSide);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand){
        return this.interactWithEntity(stack, target, player, hand).interactionResult;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context){
        return this.interactWithBlock(context.getItemInHand(), context.getPlayer(), context.getHand(), context.getLevel(), context.getClickedPos(), context.getClickedFace(), context.getClickLocation()).interactionResult;
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context){
        return this.interactWithBlockFirst(stack, context.getPlayer(), context.getHand(), context.getLevel(), context.getClickedPos(), context.getClickedFace(), context.getClickLocation()).interactionResult;
    }

    @Override
    public void inventoryTick(ItemStack stack, World level, Entity entity, int slot, boolean isSelected){
        this.inventoryUpdate(stack, level, entity, slot, isSelected);
    }

    public boolean isInCreativeGroup(ItemGroup tab){
        return this.properties != null && this.properties.groups.contains(tab);
    }

    @Override
    public Collection<ItemGroup> getCreativeTabs(){
        return this.properties != null ? this.properties.groups : super.getCreativeTabs();
    }

    protected static class ItemUseResult {

        public static ItemUseResult pass(ItemStack stack){
            return new ItemUseResult(ActionResultType.PASS, stack);
        }

        public static ItemUseResult consume(ItemStack stack){
            return new ItemUseResult(ActionResultType.CONSUME, stack);
        }

        public static ItemUseResult success(ItemStack stack){
            return new ItemUseResult(ActionResultType.SUCCESS, stack);
        }

        public static ItemUseResult fail(ItemStack stack){
            return new ItemUseResult(ActionResultType.FAIL, stack);
        }

        @Deprecated
        public static ItemUseResult fromUnderlying(ActionResult<ItemStack> underlying){
            return new ItemUseResult(underlying.getResult(), underlying.getObject());
        }

        private final ActionResultType result;
        private final ItemStack resultingStack;

        private ItemUseResult(ActionResultType result, ItemStack resultingStack){
            this.result = result;
            this.resultingStack = resultingStack;
        }

        @Deprecated
        public ActionResult<ItemStack> toUnderlying(boolean isClientSide){
            return new ActionResult<>(this.result == ActionResultType.SUCCESS ? isClientSide ? ActionResultType.SUCCESS : ActionResultType.CONSUME : this.result, this.resultingStack);
        }
    }

    public enum InteractionFeedback {
        PASS(ActionResultType.PASS), CONSUME(ActionResultType.CONSUME), SUCCESS(ActionResultType.SUCCESS), FAIL(ActionResultType.FAIL);

        private final ActionResultType interactionResult;

        InteractionFeedback(ActionResultType interactionResult){
            this.interactionResult = interactionResult;
        }

        @Deprecated
        public static InteractionFeedback fromUnderlying(ActionResultType interactionResult){
            switch(interactionResult){
                case SUCCESS:
                    return SUCCESS;
                case CONSUME:
                    return CONSUME;
                case FAIL:
                    return FAIL;
                case PASS:
                    return PASS;
            }
            return null;
        }
    }
}
```

### src/main/java/com/supermartijn642/core/item/BaseItem.java

```java
package com.supermartijn642.core.item;

import com.supermartijn642.core.registry.Registries;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created 24/07/2022 by SuperMartijn642
 */
public class BaseItem extends Item {

    private final ItemProperties properties;

    public BaseItem(Properties properties){
        super(properties);
        this.properties = null;
    }

    public BaseItem(ItemProperties properties){
        super(properties.toUnderlying());
        this.properties = properties;
    }

    /**
     * Adds information to be displayed when hovering over this item in the inventory.
     * @param stack    the stack being hovered over
     * @param level    the world the player is in, may be {@code null}
     * @param info     consumes the information which should be added
     * @param advanced whether advanced tooltips is enabled
     */
    protected void appendItemInformation(ItemStack stack, @Nullable IBlockReader level, Consumer<ITextComponent> info, boolean advanced){
    }

    /**
     * Called when a player right-clicks with this item.
     * @return whether the player's interaction should be consumed or passed on, together with the new item stack
     */
    public ItemUseResult interact(ItemStack stack, PlayerEntity player, Hand hand, World level){
        return ItemUseResult.fromUnderlying(super.use(level, player, hand));
    }

    /**
     * Called when a player right-clicks on a block with this item, before the block is interacted with.
     * @return whether the player's interaction should be consumed or passed on
     */
    public InteractionFeedback interactWithBlockFirst(ItemStack stack, PlayerEntity player, Hand hand, World level, BlockPos hitPos, Direction hitSide, Vector3d hitLocation){
        return InteractionFeedback.PASS;
    }

    /**
     * Called when a player right-clicks on a block with this item, after the block is interacted with.
     * @return whether the player's interaction should be consumed or passed on
     */
    public InteractionFeedback interactWithBlock(ItemStack stack, PlayerEntity player, Hand hand, World level, BlockPos hitPos, Direction hitSide, Vector3d hitLocation){
        return InteractionFeedback.PASS;
    }

    /**
     * Called when a player right-clicks on an entity.
     * @return whether the player's interaction should be consumed or passed on
     */
    public InteractionFeedback interactWithEntity(ItemStack stack, LivingEntity target, PlayerEntity player, Hand hand){
        return InteractionFeedback.PASS;
    }

    /**
     * Called once every tick when this item is in an entity's inventory.
     */
    public void inventoryUpdate(ItemStack stack, World level, Entity entity, int itemSlot, boolean isSelected){
    }

    @OnlyIn(Dist.CLIENT) // Needed in 1.16 because ITooltipFlag is client-side only
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> information, ITooltipFlag flag){
        this.appendItemInformation(stack, level, information::add, flag.isAdvanced());
        super.appendHoverText(stack, level, information, flag);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand){
        return this.interact(player.getItemInHand(hand), player, hand, level).toUnderlying(level.isClientSide);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand){
        return this.interactWithEntity(stack, target, player, hand).interactionResult;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context){
        return this.interactWithBlock(context.getItemInHand(), context.getPlayer(), context.getHand(), context.getLevel(), context.getClickedPos(), context.getClickedFace(), context.getClickLocation()).interactionResult;
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context){
        return this.interactWithBlockFirst(stack, context.getPlayer(), context.getHand(), context.getLevel(), context.getClickedPos(), context.getClickedFace(), context.getClickLocation()).interactionResult;
    }

    @Override
    public void inventoryTick(ItemStack stack, World level, Entity entity, int slot, boolean isSelected){
        this.inventoryUpdate(stack, level, entity, slot, isSelected);
    }

    @Override
    protected String getOrCreateDescriptionId(){
        ResourceLocation identifier = Registries.ITEMS.getIdentifier(this);
        return identifier.getNamespace() + ".item." + identifier.getPath();
    }

    public boolean isInCreativeGroup(ItemGroup tab){
        return this.properties != null && this.properties.groups.contains(tab);
    }

    @Override
    public Collection<ItemGroup> getCreativeTabs(){
        return this.properties != null ? this.properties.groups : super.getCreativeTabs();
    }

    protected static class ItemUseResult {

        public static ItemUseResult pass(ItemStack stack){
            return new ItemUseResult(ActionResultType.PASS, stack);
        }

        public static ItemUseResult consume(ItemStack stack){
            return new ItemUseResult(ActionResultType.CONSUME, stack);
        }

        public static ItemUseResult success(ItemStack stack){
            return new ItemUseResult(ActionResultType.SUCCESS, stack);
        }

        public static ItemUseResult fail(ItemStack stack){
            return new ItemUseResult(ActionResultType.FAIL, stack);
        }

        @Deprecated
        public static ItemUseResult fromUnderlying(ActionResult<ItemStack> underlying){
            return new ItemUseResult(underlying.getResult(), underlying.getObject());
        }

        private final ActionResultType result;
        private final ItemStack resultingStack;

        private ItemUseResult(ActionResultType result, ItemStack resultingStack){
            this.result = result;
            this.resultingStack = resultingStack;
        }

        @Deprecated
        public ActionResult<ItemStack> toUnderlying(boolean isClientSide){
            return new ActionResult<>(this.result == ActionResultType.SUCCESS ? isClientSide ? ActionResultType.SUCCESS : ActionResultType.CONSUME : this.result, this.resultingStack);
        }
    }

    public enum InteractionFeedback {
        PASS(ActionResultType.PASS), CONSUME(ActionResultType.CONSUME), SUCCESS(ActionResultType.SUCCESS);

        private final ActionResultType interactionResult;

        InteractionFeedback(ActionResultType interactionResult){
            this.interactionResult = interactionResult;
        }

        @Deprecated
        public ActionResultType getUnderlying(){
            return this.interactionResult;
        }
    }
}
```

### src/main/java/com/supermartijn642/core/item/CreativeItemGroup.java

```java
package com.supermartijn642.core.item;

import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.registry.RegistryUtil;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created 24/07/2022 by SuperMartijn642
 */
public final class CreativeItemGroup extends ItemGroup {

    public static CreativeItemGroup create(String modid, String name, Supplier<ItemStack> icon){
        if(!RegistryUtil.isValidNamespace(modid))
            throw new IllegalArgumentException("Modid '" + modid + "' must only contain characters [a-z0-9_.-]!");
        if(!RegistryUtil.isValidNamespace(modid))
            throw new IllegalArgumentException("Item group name '" + name + "' must only contain characters [a-z0-9_.-]!");

        String identifier = modid + "." + name;
        String translationKey = modid + ".item_group." + name;
        return new CreativeItemGroup(identifier, translationKey, icon);
    }

    public static CreativeItemGroup create(String modid, String name, IItemProvider icon){
        return create(modid, name, () -> icon.asItem().getDefaultInstance());
    }

    public static CreativeItemGroup create(String modid, Supplier<ItemStack> icon){
        if(!RegistryUtil.isValidNamespace(modid))
            throw new IllegalArgumentException("Modid '" + modid + "' must only contain characters [a-z0-9_.-]!");

        String translationKey = modid + ".item_group";
        return new CreativeItemGroup(modid, translationKey, icon);
    }

    public static CreativeItemGroup create(String modid, IItemProvider icon){
        return create(modid, () -> icon.asItem().getDefaultInstance());
    }

    public static ItemGroup getBuildingBlocks(){
        return ItemGroup.TAB_BUILDING_BLOCKS;
    }

    public static ItemGroup getDecoration(){
        return ItemGroup.TAB_DECORATIONS;
    }

    public static ItemGroup getRedstone(){
        return ItemGroup.TAB_REDSTONE;
    }

    public static ItemGroup getTransportation(){
        return ItemGroup.TAB_TRANSPORTATION;
    }

    public static ItemGroup getMisc(){
        return ItemGroup.TAB_MISC;
    }

    public static ItemGroup getSearch(){
        return ItemGroup.TAB_SEARCH;
    }

    public static ItemGroup getFood(){
        return ItemGroup.TAB_FOOD;
    }

    public static ItemGroup getTools(){
        return ItemGroup.TAB_TOOLS;
    }

    public static ItemGroup getCombat(){
        return ItemGroup.TAB_COMBAT;
    }

    public static ItemGroup getBrewing(){
        return ItemGroup.TAB_BREWING;
    }

    private final String identifier;
    private final ITextComponent displayName;
    private final Supplier<ItemStack> icon;
    private Consumer<Consumer<ItemStack>> filler;
    private Comparator<ItemStack> sorter;

    private CreativeItemGroup(String identifier, String translationKey, Supplier<ItemStack> icon){
        super(identifier);
        this.identifier = identifier;
        this.displayName = TextComponents.translation(translationKey).get();
        this.icon = icon;
    }

    /**
     * Sets a custom filler for this creative tab. By default, the creative will be filled by items with this tab set in their properties.
     * @param filler a functions which pushes items to the given consumer
     */
    public CreativeItemGroup filler(Consumer<Consumer<ItemStack>> filler){
        this.filler = filler;
        return this;
    }

    /**
     * Sets a sorter for the items in this creative tab.
     * @param sorter compares two item stacks
     */
    public CreativeItemGroup sorter(Comparator<ItemStack> sorter){
        this.sorter = sorter;
        return this;
    }

    /**
     * Set the sorter to sort items alphabetically based on their display name.
     */
    public CreativeItemGroup sortAlphabetically(){
        return this.sorter(Comparator.comparing(stack -> TextComponents.itemStack(stack).format()));
    }

    @Override
    public ItemStack makeIcon(){
        ItemStack stack = this.icon.get();
        if(stack == null || stack.isEmpty())
            throw new RuntimeException("Item group '" + this.identifier + "'s icon stack must not be empty!");
        return stack;
    }

    @Override
    public ITextComponent getDisplayName(){
        return this.displayName;
    }

    @Override
    public String getRecipeFolderName(){
        return this.identifier;
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> items){
        // Fill the list with items
        if(this.filler == null)
            super.fillItemList(items);
        else
            this.filler.accept(items::add);
        // Sort the items
        if(this.sorter != null)
            items.sort(this.sorter);
    }
}
```

### src/main/java/com/supermartijn642/core/item/ItemProperties.java

```java
package com.supermartijn642.core.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created 24/07/2022 by SuperMartijn642
 */
public class ItemProperties {

    public static ItemProperties create(){
        return new ItemProperties();
    }

    private int maxStackSize = 64;
    private int durability;
    private Item craftingRemainingItem;
    private Rarity rarity = Rarity.COMMON;
    private Food foodProperties;
    private boolean isFireResistant;
    final Set<ItemGroup> groups = new HashSet<>();

    private ItemProperties(){
    }

    public ItemProperties maxStackSize(int maxStackSize){
        if(maxStackSize < 1)
            throw new IllegalArgumentException("Maximum stack size must be greater than zero!");
        if(maxStackSize > 1 && this.durability != 0)
            throw new RuntimeException("An item cannot have durability and be stackable!");

        this.maxStackSize = maxStackSize;
        return this;
    }

    public ItemProperties durability(int durability){
        if(this.maxStackSize != 64 && this.maxStackSize > 1)
            throw new RuntimeException("An item cannot have durability and be stackable!");

        this.durability = durability;
        this.maxStackSize = 1;
        return this;
    }

    public ItemProperties craftRemainder(Item item){
        this.craftingRemainingItem = item;
        return this;
    }

    public ItemProperties group(ItemGroup group){
        this.groups.add(group);
        return this;
    }

    public ItemProperties rarity(Rarity rarity){
        this.rarity = rarity;
        return this;
    }

    public ItemProperties rarity(ItemRarity rarity){
        this.rarity = rarity.getUnderlying();
        return this;
    }

    public ItemProperties food(Food foodProperties){
        this.foodProperties = foodProperties;
        return this;
    }

    public ItemProperties fireResistant(){
        this.isFireResistant = true;
        return this;
    }

    /**
     * Converts the properties into {@link Item.Properties}.
     */
    @Deprecated
    public Item.Properties toUnderlying(){
        Item.Properties properties = new Item.Properties();
        properties.stacksTo(this.maxStackSize);
        if(this.durability != 0)
            properties.durability(this.durability);
        properties.craftRemainder(this.craftingRemainingItem);
        properties.rarity(this.rarity);
        properties.food(this.foodProperties);
        if(this.isFireResistant)
            properties.fireResistant();
        if(!this.groups.isEmpty())
            properties.tab(this.groups.iterator().next());
        return properties;
    }
}
```

### src/main/java/com/supermartijn642/core/item/ItemRarity.java

```java
package com.supermartijn642.core.item;

import net.minecraft.item.Rarity;

/**
 * Created 24/07/2022 by SuperMartijn642
 */
public enum ItemRarity {
    COMMON(Rarity.COMMON),
    UNCOMMON(Rarity.UNCOMMON),
    RARE(Rarity.RARE),
    EPIC(Rarity.EPIC);

    private final Rarity rarity;

    ItemRarity(Rarity rarity){
        this.rarity = rarity;
    }

    @Deprecated
    public Rarity getUnderlying(){
        return this.rarity;
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/AbstractContainerScreenMixin.java

```java
package com.supermartijn642.core.mixin;

import com.supermartijn642.core.gui.CustomSlot;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Created 08/01/2026 by SuperMartijn642
 */
@Mixin(ContainerScreen.class)
public class AbstractContainerScreenMixin {

    @Inject(
        method = "isHovering(Lnet/minecraft/inventory/container/Slot;DD)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void isHovering(Slot slot, double mouseX, double mouseY, CallbackInfoReturnable<Boolean> ci){
        if(slot instanceof CustomSlot){
            CustomSlot customSlot = (CustomSlot)slot;
            ci.setReturnValue(this.isHovering(
                slot.x, slot.y,
                customSlot.getWidth() - 2, customSlot.getHeight() - 2,
                mouseX, mouseY
            ));
        }
    }

    @Shadow
    private boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY){
        throw new AssertionError();
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/BlockPropertiesAccessor.java

```java
package com.supermartijn642.core.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

/**
 * Created 07/05/2023 by SuperMartijn642
 */
@Mixin(AbstractBlock.Properties.class)
public interface BlockPropertiesAccessor {

    @Accessor(value = "lootTableSupplier", remap = false)
    Supplier<ResourceLocation> getLootTableSupplier();

    @Accessor(value = "lootTableSupplier", remap = false)
    void setLootTableSupplier(Supplier<ResourceLocation> supplier);
}
```

### src/main/java/com/supermartijn642/core/mixin/CraftingHelperMixin.java

```java
package com.supermartijn642.core.mixin;

import com.supermartijn642.core.registry.Registries;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Created 27/08/2022 by SuperMartijn642
 */
@Mixin(CraftingHelper.class)
public class CraftingHelperMixin {

    @Inject(
        method = "register(Lnet/minecraftforge/common/crafting/conditions/IConditionSerializer;)Lnet/minecraftforge/common/crafting/conditions/IConditionSerializer;",
        at = @At("TAIL"),
        remap = false
    )
    private static void registerConditionSerializer(IConditionSerializer<?> conditionSerializer, CallbackInfoReturnable<?> ci){
        Registries.onRecipeConditionSerializerAdded(conditionSerializer);
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/DataGeneratorMixin.java

```java
package com.supermartijn642.core.mixin;

import com.google.common.base.Stopwatch;
import com.supermartijn642.core.generator.ResourceCache;
import com.supermartijn642.core.generator.ResourceGenerator;
import com.supermartijn642.core.registry.GeneratorRegistrationHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created 06/05/2023 by SuperMartijn642
 */
@Mixin(DataGenerator.class)
public class DataGeneratorMixin {

    @Shadow
    @Final
    private static Logger LOGGER;

    @Unique
    private ResourceCache resourceCache;
    @Shadow
    @Final
    private List<IDataProvider> providers;
    @Shadow
    @Final
    private Path outputFolder;

    @Inject(
        method = "run()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/data/DirectoryCache;keep(Ljava/nio/file/Path;)V",
            shift = At.Shift.AFTER
        ),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void runHead(CallbackInfo ci, DirectoryCache hashCache){
        GatherDataEvent.DataGeneratorConfig dataGeneratorConfig = DatagenModLoaderAccessor.getDataGeneratorConfig();
        if(dataGeneratorConfig != null){ // Some mods run data generators themselves
            dataGeneratorConfig.getMods().stream().filter(GeneratorRegistrationHandler::hasHandlerForModid).forEach(modid -> {
                GeneratorRegistrationHandler handler = GeneratorRegistrationHandler.get(modid);
                DataGenerator dataGenerator = (DataGenerator)(Object)this;
                // Get the output folder
                Path outputFolder = this.outputFolder;
                // Create a ResourceCache instance
                if(this.resourceCache == null)
                    this.resourceCache = ResourceCache.wrap(DatagenModLoaderAccessor.getExistingFileHelper(), hashCache, outputFolder);
                ((ResourceCache.HashCacheWrapper)this.resourceCache).allowWrites(false);
                handler.registerProviders(dataGenerator, DatagenModLoaderAccessor.getExistingFileHelper(), this.resourceCache);
            });
        }
    }

    @Inject(
        method = "run()V",
        at = @At(
            value = "INVOKE",
            target = "Lcom/google/common/base/Stopwatch;createUnstarted()Lcom/google/common/base/Stopwatch;",
            shift = At.Shift.AFTER
        )
    )
    private void runBeforeGenerators(CallbackInfo ci){
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        for(IDataProvider provider : this.providers){
            if(provider instanceof ResourceGenerator.DataProviderInstance){
                LOGGER.info("Running generator: {}", provider.getName());
                stopwatch.start();
                ((ResourceGenerator.DataProviderInstance)provider).generate();
                stopwatch.stop();
                LOGGER.info("{} finished after {} ms", provider.getName(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
                stopwatch.reset();
            }
        }
        if(this.resourceCache != null)
            ((ResourceCache.HashCacheWrapper)this.resourceCache).allowWrites(true);
    }

    @Inject(
        method = "run()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/data/DirectoryCache;purgeStaleAndWrite()V",
            shift = At.Shift.BEFORE
        )
    )
    private void runTail(CallbackInfo ci){
        if(this.resourceCache != null)
            ((ResourceCache.HashCacheWrapper)this.resourceCache).finish();
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/DatagenModLoaderAccessor.java

```java
package com.supermartijn642.core.mixin;

import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.DatagenModLoader;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Created 07/05/2023 by SuperMartijn642
 */
@Mixin(value = DatagenModLoader.class, remap = false)
public interface DatagenModLoaderAccessor {

    @Accessor(value = "dataGeneratorConfig", remap = false)
    static GatherDataEvent.DataGeneratorConfig getDataGeneratorConfig(){
        throw new AssertionError();
    }

    @Accessor(value = "existingFileHelper", remap = false)
    static ExistingFileHelper getExistingFileHelper(){
        throw new AssertionError();
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/ForgeHooksMixin.java

```java
package com.supermartijn642.core.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.core.block.BaseBlock;
import com.supermartijn642.core.data.tag.CustomTagEntries;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Created 01/08/2022 by SuperMartijn642
 */
@Mixin(ForgeHooks.class)
public class ForgeHooksMixin {

    @Redirect(
        method = "canHarvestBlock",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;getHarvestLevel(Lnet/minecraftforge/common/ToolType;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/block/BlockState;)I"
        ),
        remap = false
    )
    private static int canHarvestBlockRedirect(ItemStack stack, ToolType type, PlayerEntity player, BlockState state){
        if(state.getBlock() instanceof BaseBlock){
            int bestHarvestLevel = -1;
            for(ToolType toolType : stack.getToolTypes()){
                if(state.isToolEffective(toolType)){
                    int harvestLevel = stack.getHarvestLevel(toolType, player, state);
                    if(harvestLevel > bestHarvestLevel)
                        bestHarvestLevel = harvestLevel;
                }
            }
            if(bestHarvestLevel == -1)
                bestHarvestLevel = stack.getHarvestLevel(type, player, state);
            return bestHarvestLevel;
        }
        return stack.getHarvestLevel(type, player, state);
    }

    @Inject(
        method = "deserializeTagAdditions",
        at = @At("HEAD"),
        remap = false
    )
    private static void deserializeTagAdditions(List<ITag.ITagEntry> list, JsonObject json, List<ITag.Proxy> allList, CallbackInfo ci){
        if(json.has("optional") && json.get("optional").isJsonArray()){
            JsonArray optionalArray = json.getAsJsonArray("optional");
            for(int i = 0; i < optionalArray.size(); i++){
                ITag.ITagEntry entry = CustomTagEntries.potentiallyDeserialize(optionalArray.get(i));
                if(entry != null){
                    optionalArray.remove(i);
                    list.add(entry);
                    i--;
                }
            }
        }
        if(json.has("remove") && json.get("remove").isJsonArray()){
            JsonArray removeArray = json.getAsJsonArray("remove");
            for(int i = 0; i < removeArray.size(); i++){
                ITag.ITagEntry entry = CustomTagEntries.potentiallyDeserialize(removeArray.get(i));
                if(entry != null){
                    removeArray.remove(i);
                    i--;
                }
            }
        }
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/ForgeTagHandlerMixin.java

```java
package com.supermartijn642.core.mixin;

import com.supermartijn642.core.extensions.TagLoaderExtension;
import net.minecraft.tags.TagCollectionReader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.registries.RegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

/**
 * Created 11/02/2024 by SuperMartijn642
 */
@Mixin(ForgeTagHandler.class)
public class ForgeTagHandlerMixin {

    @Inject(
        method = "createCustomTagTypeReaders",
        at = @At("RETURN"),
        remap = false
    )
    private static void createCustomTagTypeReaders(CallbackInfoReturnable<Map<ResourceLocation,TagCollectionReader<?>>> ci){
        ci.getReturnValue().forEach((registryName, tagLoader) -> ((TagLoaderExtension)tagLoader).supermartijn642corelibSetRegistry(null, RegistryManager.ACTIVE.getRegistry(registryName)));
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/GameDataMixin.java

```java
package com.supermartijn642.core.mixin;

import com.supermartijn642.core.registry.RegistryEntryAcceptor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.GameData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created 25/07/2022 by SuperMartijn642
 */
@Mixin(GameData.class)
public class GameDataMixin {

    private static RegistryEvent.Register<?> registerEvent;

    @ModifyVariable(method = "lambda$postRegistryEventDispatch$15(Lnet/minecraftforge/fml/ModLoadingStage$EventGenerator;)V", at = @At("STORE"), ordinal = 0, remap = false)
    private static RegistryEvent.Register<?> modifyRegisterEvent(RegistryEvent.Register<?> registerEvent){
        GameDataMixin.registerEvent = registerEvent;
        return registerEvent;
    }

    @Inject(
        method = "lambda$postRegistryEventDispatch$15(Lnet/minecraftforge/fml/ModLoadingStage$EventGenerator;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/registries/ObjectHolderRegistry;applyObjectHolders(Ljava/util/function/Predicate;)V"
        ),
        remap = false
    )
    private static void postRegistryEventDispatch(CallbackInfo ci){
        RegistryEntryAcceptor.Handler.onRegisterEvent(registerEvent);
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/GameRendererMixin.java

```java
package com.supermartijn642.core.mixin;

import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.gui.CursorTypes;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created 15/01/2026 by SuperMartijn642
 */
@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(
        method = "render",
        at = @At("TAIL")
    )
    private void changeCursor(float f, long l, boolean bl, CallbackInfo ci){
        if(!ClientUtils.getMinecraft().noRender)
            CursorTypes.applyPending();
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/LevelRendererMixin.java

```java
package com.supermartijn642.core.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.render.RenderWorldEvent;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created 17/11/2021 by SuperMartijn642
 */
@Mixin(WorldRenderer.class)
public class LevelRendererMixin {

    private MatrixStack poseStack;
    private float partialTicks;

    @ModifyVariable(method = "renderLevel", at = @At("HEAD"))
    public MatrixStack modifyPoseStack(MatrixStack poseStack){
        this.poseStack = poseStack;
        return poseStack;
    }

    @ModifyVariable(method = "renderLevel", at = @At("HEAD"))
    public float modifyPartialTicks(float partialTicks){
        this.partialTicks = partialTicks;
        return partialTicks;
    }

    @Inject(method = "renderLevel",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;pushMatrix()V"),
        slice = @Slice(
            from = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockRayTraceResult;getBlockPos()Lnet/minecraft/util/math/BlockPos;"),
            to = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;multMatrix(Lnet/minecraft/util/math/vector/Matrix4f;)V")
        ))
    public void renderLevel(CallbackInfo ci){
        MinecraftForge.EVENT_BUS.post(new RenderWorldEvent(this.poseStack, this.partialTicks));
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/TagBuilderMixin.java

```java
package com.supermartijn642.core.mixin;

import com.google.gson.JsonElement;
import com.supermartijn642.core.data.tag.CustomTagEntries;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Created 11/02/2024 by SuperMartijn642
 */
@Mixin(Tag.Builder.class)
public class TagBuilderMixin {

    @Inject(
        method = "parseEntry(Lcom/google/gson/JsonElement;)Lnet/minecraft/tags/ITag$ITagEntry;",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void parseEntry(JsonElement element, CallbackInfoReturnable<ITag.ITagEntry> ci){
        ITag.ITagEntry entry = CustomTagEntries.potentiallyDeserialize(element);
        if(entry != null)
            ci.setReturnValue(entry);
    }
}
```

### src/main/java/com/supermartijn642/core/mixin/TagCollectionReaderMixin.java

```java
package com.supermartijn642.core.mixin;

import com.supermartijn642.core.data.tag.TagEntryAdapter;
import com.supermartijn642.core.extensions.TagLoaderExtension;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.TagCollectionReader;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

/**
 * Created 09/02/2024 by SuperMartijn642
 */
@Mixin(TagCollectionReader.class)
public class TagCollectionReaderMixin implements TagLoaderExtension {

    @Unique
    private Registry<?> registry;
    @Unique
    private ForgeRegistry<?> forgeRegistry;
    @Final
    @Shadow
    private String directory;

    @Override
    public void supermartijn642corelibSetRegistry(Registry<?> registry, ForgeRegistry<?> forgeRegistry){
        this.registry = registry;
        this.forgeRegistry = forgeRegistry;
    }

    @Inject(
        method = "load(Ljava/util/Map;)Lnet/minecraft/tags/ITagCollection;",
        at = @At("HEAD")
    )
    private void build(Map<ResourceLocation,ITag.Builder> builders, CallbackInfoReturnable<ITagCollection<?>> ci){
        if(this.registry == null && this.forgeRegistry == null){
            //noinspection IfCanBeSwitch
            if(this.directory.equals("tags/blocks"))
                //noinspection deprecation
                this.registry = Registry.BLOCK;
            else if(this.directory.equals("tags/items"))
                //noinspection deprecation
                this.registry = Registry.ITEM;
            else if(this.directory.equals("tags/fluids"))
                //noinspection deprecation
                this.registry = Registry.FLUID;
            else if(this.directory.equals("tags/entity_types"))
                //noinspection deprecation
                this.registry = Registry.ENTITY_TYPE;
        }
        for(ITag.Builder builder : builders.values()){
            for(ITag.Proxy entry : builder.entries){
                if(entry.entry instanceof TagEntryAdapter)
                    ((TagEntryAdapter)entry.entry).setRegistry(this.registry, this.forgeRegistry);
            }
        }
    }
}
```

### src/main/java/com/supermartijn642/core/network/BasePacket.java

```java
package com.supermartijn642.core.network;

import net.minecraft.network.PacketBuffer;

/**
 * Created 5/30/2021 by SuperMartijn642
 */
public interface BasePacket {

    /**
     * Writes the data in the packet to the given {@code buffer}.
     * The written data will be decoded in {@link #read(PacketBuffer)}.
     * @param buffer data buffer to write to
     */
    void write(PacketBuffer buffer);

    /**
     * Reads data written by {@link #write(PacketBuffer)} from the given
     * {@code buffer} into the packet.
     * @param buffer data buffer to read from
     */
    void read(PacketBuffer buffer);

    /**
     * Checks whether the received values are valid.
     * If {@code false} is returned, the packet will be discarded.
     * @return {@code true} if all received values are valid
     */
    default boolean verify(PacketContext context){
        return true;
    }

    void handle(PacketContext context);

}
```

### src/main/java/com/supermartijn642/core/network/BlockEntityBasePacket.java

```java
package com.supermartijn642.core.network;

import com.supermartijn642.core.CoreSide;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

/**
 * Created 5/30/2021 by SuperMartijn642
 */
public abstract class BlockEntityBasePacket<T extends TileEntity> extends BlockPosBasePacket {

    public RegistryKey<World> dimension;

    public BlockEntityBasePacket(){
    }

    /**
     * Grabs the tile entity in {@code dimension} at {@code pos}.
     * @param dimension dimension of the tile entity
     * @param pos position of the tile entity
     */
    public BlockEntityBasePacket(RegistryKey<World> dimension, BlockPos pos){
        super(pos);
        this.dimension = dimension;
    }

    /**
     * Grabs the tile entity in {@code world} at {@code pos}.
     * @param world world the tile entity is in
     * @param pos position of the tile entity
     */
    public BlockEntityBasePacket(World world, BlockPos pos){
        this(world == null ? null : world.dimension(), pos);
    }

    /**
     * Grabs the tile entity at {@code pos} in the relevant player's dimension.
     * @param pos position of the tile entity
     */
    public BlockEntityBasePacket(BlockPos pos){
        this((RegistryKey<World>)null, pos);
    }

    @Override
    public void write(PacketBuffer buffer){
        super.write(buffer);
        buffer.writeBoolean(this.dimension != null);
        if(this.dimension != null)
            buffer.writeResourceLocation(this.dimension.location());
    }

    @Override
    public void read(PacketBuffer buffer){
        super.read(buffer);
        if(buffer.readBoolean())
            this.dimension = RegistryKey.create(Registry.DIMENSION_REGISTRY, buffer.readResourceLocation());
    }

    @Override
    protected void handle(BlockPos pos, PacketContext context){
        T tile = this.getTileEntity(context);
        if(tile != null)
            this.handle(tile, context);
    }

    protected abstract void handle(T tile, PacketContext context);

    @SuppressWarnings("unchecked")
    private T getTileEntity(PacketContext context){
        World world = this.dimension == null ? context.getWorld() :
            context.getHandlingSide() == CoreSide.CLIENT ?
                context.getWorld().dimension() == this.dimension ? context.getWorld() : null :
                context.getWorld().getServer().getLevel(this.dimension);

        if(world == null)
            return null;

        TileEntity tile = world.getBlockEntity(this.pos);

        if(tile == null)
            return null;

        try{
            return (T)tile;
        }catch(ClassCastException ignore){}
        return null;
    }
}
```

### src/main/java/com/supermartijn642/core/network/BlockPosBasePacket.java

```java
package com.supermartijn642.core.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;

/**
 * Created 5/30/2021 by SuperMartijn642
 */
public abstract class BlockPosBasePacket implements BasePacket {

    public BlockPos pos;

    public BlockPosBasePacket(){
    }

    /**
     * Stores the given {@code pos} with the packet data.
     * @param pos position to be stored
     */
    public BlockPosBasePacket(BlockPos pos){
        this.pos = pos;
    }

    @Override
    public void write(PacketBuffer buffer){
        buffer.writeBlockPos(this.pos);
    }

    @Override
    public void read(PacketBuffer buffer){
        this.pos = buffer.readBlockPos();
    }

    @Override
    public void handle(PacketContext context){
        this.handle(this.pos, context);
    }

    protected abstract void handle(BlockPos pos, PacketContext context);
}
```

### src/main/java/com/supermartijn642/core/network/PacketChannel.java

```java
package com.supermartijn642.core.network;

import com.supermartijn642.core.CoreLib;
import com.supermartijn642.core.registry.RegistryUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created 5/30/2021 by SuperMartijn642
 */
public class PacketChannel {

    /**
     * Creates a channel with the given {@code channelName}.
     * @param channelName registry channelName of the channel
     * @return a new channel with the given {@code channelName}
     * @throws IllegalArgumentException if {@code channelName == null}
     */
    public static PacketChannel create(String modid, String channelName){
        if(!RegistryUtil.isValidNamespace(modid))
            throw new IllegalArgumentException("Modid '" + modid + "' must only contain characters [a-z0-9_.-]!");
        if(!RegistryUtil.isValidNamespace(channelName))
            throw new IllegalArgumentException("Channel name '" + channelName + "' must only contain characters [a-z0-9_.-]!");
        String activeMod = ModLoadingContext.get().getActiveNamespace();
        if(activeMod != null && !activeMod.equals("minecraft") && !activeMod.equals("forge")){
            if(!activeMod.equals(modid))
                CoreLib.LOGGER.warn("Mod '{}' is creating a packet channel for different modid '{}'!", ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName(), modid);
        }else if(modid.equals("minecraft") || modid.equals("forge"))
            CoreLib.LOGGER.warn("Mod is creating a packet channel for modid '{}'!", modid);

        return new PacketChannel(modid, channelName);
    }

    /**
     * Creates a new channel.
     * @return a new channel with channel name 'main'
     */
    public static PacketChannel create(String modid){
        return create(modid, "main");
    }

    @Deprecated
    public static PacketChannel create(){
        return create(ModLoadingContext.get().getActiveNamespace(), "main");
    }

    private final String modid, name;
    private final ResourceLocation channelName;
    private final SimpleChannel channel;

    private final List<PacketProperties<?>> packetsByIndex = new ArrayList<>();
    private final Map<Class<? extends BasePacket>,PacketProperties<?>> packetsByClass = new HashMap<>();

    private PacketChannel(String modid, String name){
        this.modid = modid;
        this.name = name;
        this.channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(modid, name), () -> "1", "1"::equals, "1"::equals);
        this.channelName = new ResourceLocation(modid, name);

        this.channel.messageBuilder(Payload.class, 0)
            .encoder((payload, buffer) -> this.write(payload.packet, buffer))
            .decoder(buffer -> new Payload(this.read(buffer)))
            .consumer((payload, contextSupplier) -> {
                NetworkEvent.Context context = contextSupplier.get();
                context.setPacketHandled(true);
                this.handle(payload.packet, new PacketContext(context), context.getDirection().getReceptionSide().isClient() ? PacketDirection.SERVER_TO_CLIENT : PacketDirection.CLIENT_TO_SERVER);
            }).add();
    }

    /**
     * Registers a packet for this channel
     * @param packetClass    class of the packet
     * @param packetSupplier supplier for new packet instances
     * @param direction      direction that the packet is allowed to be sent
     * @param shouldBeQueued whether the packet should be handled on the main thread
     */
    public <T extends BasePacket> void registerMessage(Class<T> packetClass, Supplier<T> packetSupplier, PacketDirection direction, boolean shouldBeQueued){
        if(this.packetsByClass.containsKey(packetClass))
            throw new IllegalArgumentException("Class '" + packetClass + "' has already been registered!");

        int index = this.packetsByIndex.size();
        PacketProperties<T> properties = new PacketProperties<>(index, packetClass, packetSupplier, direction, shouldBeQueued);
        this.packetsByIndex.add(properties);
        this.packetsByClass.put(packetClass, properties);
    }

    /**
     * Registers a packet for this channel
     * @param packetClass    class of the packet
     * @param packetSupplier supplier for new packet instances
     * @param shouldBeQueued whether the packet should be handled on the main thread
     * @deprecated Use {@link #registerMessage(Class, Supplier, PacketDirection, boolean)}.
     */
    @Deprecated
    public <T extends BasePacket> void registerMessage(Class<T> packetClass, Supplier<T> packetSupplier, boolean shouldBeQueued){
        this.registerMessage(packetClass, packetSupplier, PacketDirection.BOTH_WAYS, shouldBeQueued);
    }

    /**
     * Sends the given {@code packet} to the server. Must only be used client-side.
     * @param packet packet to be sent
     */
    public void sendToServer(BasePacket packet){
        this.checkRegistration(packet, PacketDirection.CLIENT_TO_SERVER);
        this.channel.sendToServer(new Payload(packet));
    }

    /**
     * Sends the given {@code packet} to the server. Must only be used server-side.
     * @param player player to send the packet to
     * @param packet packet to be sent
     */
    public void sendToPlayer(PlayerEntity player, BasePacket packet){
        if(!(player instanceof ServerPlayerEntity))
            throw new IllegalStateException("This must only be called server-side!");
        this.checkRegistration(packet, PacketDirection.SERVER_TO_CLIENT);
        this.channel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)player), new Payload(packet));
    }

    /**
     * Sends the given {@code packet} to all players. Must only be used server-side.
     * @param packet packet to be sent
     */
    public void sendToAllPlayers(BasePacket packet){
        this.checkRegistration(packet, PacketDirection.SERVER_TO_CLIENT);
        this.channel.send(PacketDistributor.ALL.noArg(), new Payload(packet));
    }

    /**
     * Sends the given {@code packet} to all players in the given {@code dimension}. Must only be used server-side.
     * @param dimension dimension to send the packet to
     * @param packet    packet to be sent
     */
    public void sendToDimension(RegistryKey<World> dimension, BasePacket packet){
        this.checkRegistration(packet, PacketDirection.SERVER_TO_CLIENT);
        this.channel.send(PacketDistributor.DIMENSION.with(() -> dimension), new Payload(packet));
    }

    /**
     * Sends the given {@code packet} to all players in the given {@code world}. Must only be used server-side.
     * @param world  world to send the packet to
     * @param packet packet to be sent
     */
    public void sendToDimension(World world, BasePacket packet){
        if(world.isClientSide)
            throw new IllegalStateException("This must only be called server-side!");
        this.sendToDimension(world.dimension(), packet);
    }

    /**
     * Sends the given {@code packet} to all players tracking the given {@code entity}. Must only be used server-side.
     * @param entity entity which should be tracked
     * @param packet packet to be sent
     */
    public void sendToAllTrackingEntity(Entity entity, BasePacket packet){
        if(entity.level.isClientSide)
            throw new IllegalStateException("This must only be called server-side!");
        this.checkRegistration(packet, PacketDirection.SERVER_TO_CLIENT);
        this.channel.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new Payload(packet));
    }

    /**
     * Sends the given {@code packet} to all players tracking the given position in the given {@code world}. Must only be used server-side.
     * @param packet packet to be sent
     */
    public void sendToAllNear(RegistryKey<World> world, double x, double y, double z, double radius, BasePacket packet){
        this.checkRegistration(packet, PacketDirection.SERVER_TO_CLIENT);
        this.channel.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(x, y, z, radius, world)), new Payload(packet));
    }

    /**
     * Sends the given {@code packet} to all players tracking the given position in the given {@code world}. Must only be used server-side.
     * @param packet packet to be sent
     */
    public void sendToAllNear(RegistryKey<World> world, BlockPos pos, double radius, BasePacket packet){
        this.sendToAllNear(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, radius, packet);
    }

    /**
     * Sends the given {@code packet} to all players tracking the given position in the given {@code world}. Must only be used server-side.
     * @param packet packet to be sent
     */
    public void sendToAllNear(World world, double x, double y, double z, double radius, BasePacket packet){
        if(world.isClientSide)
            throw new IllegalStateException("This must only be called server-side!");
        this.sendToAllNear(world.dimension(), x, y, z, radius, packet);
    }

    /**
     * Sends the given {@code packet} to all players tracking the given position in the given {@code world}. Must only be used server-side.
     * @param packet packet to be sent
     */
    public void sendToAllNear(World world, BlockPos pos, double radius, BasePacket packet){
        if(world.isClientSide)
            throw new IllegalStateException("This must only be called server-side!");
        this.sendToAllNear(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, radius, packet);
    }

    private void checkRegistration(BasePacket packet, PacketDirection direction){
        PacketProperties<?> properties = this.packetsByClass.get(packet.getClass());
        if(properties == null)
            throw new IllegalArgumentException("Tried to send unregistered packet '" + packet.getClass() + "' on channel '" + this.modid + ":" + this.name + "'!");
        if(properties.direction != PacketDirection.BOTH_WAYS && properties.direction != direction)
            throw new IllegalArgumentException("Tried to send packet '" + packet.getClass() + "' on channel '" + this.modid + ":" + this.name + "' in invalid direction '" + direction + "'!");
    }

    private void write(BasePacket packet, PacketBuffer buffer){
        // assume the packet has already been checked for registration here
        int index = this.packetsByClass.get(packet.getClass()).index;
        buffer.writeInt(index);
        try{
            packet.write(buffer);
        }catch(Exception e){
            throw new RuntimeException("Encountered an exception whilst writing packet of class '" + packet.getClass().getName() + "' for channel '" + this.modid + ":" + this.name + "'!", e);
        }
    }

    private BasePacket read(PacketBuffer buffer){
        int index = buffer.readInt();
        if(this.packetsByIndex.size() < index)
            throw new RuntimeException("Received an unregistered packet with index '" + index + "' on channel '" + this.modid + ":" + this.name + "'!");

        PacketProperties<?> properties = this.packetsByIndex.get(index);
        BasePacket packet = properties.supplier.get();
        try{
            packet.read(buffer);
        }catch(Exception e){
            throw new RuntimeException("Encountered an exception whilst reading packet of class '" + packet.getClass().getName() + "' for channel '" + this.modid + ":" + this.name + "'!", e);
        }
        return packet;
    }

    void handle(BasePacket packet, PacketContext context, PacketDirection direction){
        PacketProperties<?> properties = this.packetsByClass.get(packet.getClass());
        if(properties.direction != PacketDirection.BOTH_WAYS && properties.direction != direction)
            throw new RuntimeException("Received packet of class '" + properties.clazz + "' on channel '" + this.modid + ":" + this.name + "' for invalid direction '" + (direction == PacketDirection.CLIENT_TO_SERVER ? PacketDirection.SERVER_TO_CLIENT : PacketDirection.CLIENT_TO_SERVER) + "'!");

        // Verify packet
        try{
            boolean verify = packet.verify(context);
            if(!verify)
                return;
        }catch(Exception e){
            throw new RuntimeException("Encountered an exception whilst verifying packet of class '" + packet.getClass().getName() + "' for channel '" + this.modid + ":" + this.name + "'!", e);
        }
        // Handle packet
        Runnable handle = () -> {
            try{
                packet.handle(context);
            }catch(Exception e){
                throw new RuntimeException("Encountered an exception whilst processing packet of class '" + packet.getClass().getName() + "' for channel '" + this.modid + ":" + this.name + "'!", e);
            }
        };
        if(properties.shouldBeQueued)
            context.queueTask(handle);
        else
            handle.run();
    }

    private static class PacketProperties<T extends BasePacket> {
        private final int index;
        /**
         * The packet's class
         */
        private final Class<T> clazz;
        /**
         * Supplier to create new packet instances
         */
        private final Supplier<T> supplier;
        /**
         * Direction that the packet is allowed to be sent
         */
        private final PacketDirection direction;
        /**
         * Whether the packet should be handled on the main thread or off thread
         */
        private final boolean shouldBeQueued;

        private PacketProperties(int index, Class<T> clazz, Supplier<T> supplier, PacketDirection direction, boolean shouldBeQueued){
            this.index = index;
            this.clazz = clazz;
            this.supplier = supplier;
            this.direction = direction;
            this.shouldBeQueued = shouldBeQueued;
        }
    }

    private static class Payload {
        private final BasePacket packet;

        private Payload(BasePacket packet){
            this.packet = packet;
        }
    }
}
```

### src/main/java/com/supermartijn642/core/network/PacketContext.java

```java
package com.supermartijn642.core.network;

import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.CoreSide;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Created 5/30/2021 by SuperMartijn642
 */
public class PacketContext {

    private final NetworkEvent.Context context;

    public PacketContext(NetworkEvent.Context context){
        this.context = context;
    }

    /**
     * @return the side the packet is received on
     */
    public CoreSide getHandlingSide(){
        return this.context.getDirection().getReceptionSide() == LogicalSide.CLIENT ? CoreSide.CLIENT : CoreSide.SERVER;
    }

    /**
     * @return the side the packet is originating from
     */
    public CoreSide getOriginatingSide(){
        return this.context.getDirection().getOriginationSide() == LogicalSide.CLIENT ? CoreSide.CLIENT : CoreSide.SERVER;
    }

    /**
     * @return the local player on the client, the player entity corresponding to the client that sent the packet on the server
     */
    public PlayerEntity getPlayer(){
        if(this.getHandlingSide().isClient())
            return ClientUtils.getPlayer();
        return this.context.getSender();
    }

    /**
     * @deprecated Use {@link #getPlayer()}.
     */
    @Deprecated
    public PlayerEntity getSendingPlayer(){
        return this.context.getSender();
    }

    /**
     * @return the client world if client-side, or the sending player's world if server-side
     */
    public World getWorld(){
        return this.getHandlingSide() == CoreSide.CLIENT ? ClientUtils.getWorld() : this.getSendingPlayer().level;
    }

    public void queueTask(Runnable task){
        if(this.getHandlingSide() == CoreSide.SERVER)
            this.context.enqueueWork(task);
        else
            ClientUtils.queueTask(task);
    }

    @Deprecated
    public NetworkEvent.Context getUnderlyingContext(){
        return this.context;
    }

}
```

### src/main/java/com/supermartijn642/core/network/PacketDirection.java

```java
package com.supermartijn642.core.network;

/**
 * Created 26/12/2025 by SuperMartijn642
 */
public enum PacketDirection {

    SERVER_TO_CLIENT,
    CLIENT_TO_SERVER,
    BOTH_WAYS
}
```

### src/main/java/com/supermartijn642/core/registry/ClientRegistrationHandler.java

```java
package com.supermartijn642.core.registry;

import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.CoreLib;
import com.supermartijn642.core.render.CustomBlockEntityRenderer;
import com.supermartijn642.core.render.CustomItemRenderer;
import com.supermartijn642.core.util.Pair;
import com.supermartijn642.core.util.TriFunction;
import net.minecraft.block.Block;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
public class ClientRegistrationHandler {

    /**
     * {@link Item.ister}
     */
    @SuppressWarnings("JavadocReference")
    private static final Field itemIster;

    static{
        try{
            itemIster = Item.class.getDeclaredField("ister");
        }catch(NoSuchFieldException e){
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static Supplier<ItemStackTileEntityRenderer> getItemCustomRenderer(Item item){
        try{
            return (Supplier<ItemStackTileEntityRenderer>)itemIster.get(item);
        }catch(IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    private static void setItemCustomRenderer(Item item, Supplier<ItemStackTileEntityRenderer> customRenderer){
        try{
            itemIster.set(item, customRenderer);
        }catch(IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Contains one registration helper per modid
     */
    private static final Map<String,ClientRegistrationHandler> REGISTRATION_HELPER_MAP = new HashMap<>();

    /**
     * Get a registration handler for a given modid. This will always return one unique registration handler per modid.
     * @param modid modid of the mod registering entries
     * @return a unique registration handler for the given modid
     */
    public static synchronized ClientRegistrationHandler get(String modid){
        if(!RegistryUtil.isValidNamespace(modid))
            throw new IllegalArgumentException("Modid '" + modid + "' must only contain characters [a-z0-9_.-]!");
        String activeMod = ModLoadingContext.get().getActiveNamespace();
        if(activeMod != null && !activeMod.equals("minecraft") && !activeMod.equals("forge")){
            if(!activeMod.equals(modid))
                CoreLib.LOGGER.warn("Mod '" + ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName() + "' is requesting registration helper for different modid '" + modid + "'!");
        }else if(modid.equals("minecraft") || modid.equals("forge"))
            CoreLib.LOGGER.warn("Mod is requesting registration helper for modid '" + modid + "'!");

        return REGISTRATION_HELPER_MAP.computeIfAbsent(modid, ClientRegistrationHandler::new);
    }

    private final String modid;

    private final Set<ResourceLocation> models = new HashSet<>();
    private final Map<ResourceLocation,Supplier<IBakedModel>> specialModels = new HashMap<>();
    private final List<Pair<Supplier<Stream<ResourceLocation>>,Function<IBakedModel,IBakedModel>>> modelOverwrites = new ArrayList<>();

    private final List<Pair<Supplier<EntityType<?>>,Supplier<EntityRenderer<?>>>> entityRenderers = new ArrayList<>();
    private final List<Pair<Supplier<TileEntityType<?>>,Function<TileEntityRendererDispatcher,TileEntityRenderer<?>>>> blockEntityRenderers = new ArrayList<>();

    private final Map<ResourceLocation,Set<ResourceLocation>> textureAtlasSprites = new HashMap<>();

    private final List<Pair<Supplier<Item>,Supplier<ItemStackTileEntityRenderer>>> customItemRenderers = new ArrayList<>();

    private final List<Pair<Supplier<ContainerType<?>>,TriFunction<Container,PlayerInventory,ITextComponent,Screen>>> containerScreens = new ArrayList<>();
    private final List<Pair<Supplier<Block>,Supplier<RenderType>>> blockRenderTypes = new ArrayList<>();

    private boolean passedModelRegistry;
    private boolean passedModelBake;
    private boolean passedClientSetup;
    private boolean passedTextureStitch;

    private ClientRegistrationHandler(String modid){
        this.modid = modid;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::handleModelRegistryEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::handleModelBakeEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::handleRegisterRenderersEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::handleTextureStitchEvent);
    }

    /**
     * Registers the given model location to be loaded from a json file.
     */
    public void registerModel(ResourceLocation identifier){
        if(this.passedModelRegistry)
            throw new IllegalStateException("Cannot register new models after ModelRegistryEvent has been fired!");
        if(this.models.contains(identifier))
            throw new RuntimeException("Duplicate model location '" + identifier + "'!");
        if(this.specialModels.containsKey(identifier))
            throw new RuntimeException("Overlapping special model and model location '" + identifier + "'!");

        this.models.add(identifier);
    }

    /**
     * Registers the given model location to be loaded from a json file.
     */
    public void registerModel(String namespace, String identifier){
        if(!RegistryUtil.isValidNamespace(namespace))
            throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
        if(!RegistryUtil.isValidPath(identifier))
            throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

        this.registerModel(new ResourceLocation(namespace, identifier));
    }

    /**
     * Registers the given model location to be loaded from a json file.
     */
    public void registerModel(String identifier){
        this.registerModel(this.modid, identifier);
    }

    /**
     * Registers the given baked model under the given identifier. The identifier must not already contain a model.
     */
    public void registerSpecialModel(String identifier, Supplier<IBakedModel> model){
        if(this.passedModelBake)
            throw new IllegalStateException("Cannot register new special models after ModelBakeEvent has been fired!");
        if(!RegistryUtil.isValidPath(identifier))
            throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

        ResourceLocation fullIdentifier = new ResourceLocation(this.modid, identifier);
        if(this.specialModels.containsKey(fullIdentifier))
            throw new RuntimeException("Duplicate special model entry '" + fullIdentifier + "'!");

        this.specialModels.put(fullIdentifier, model);
    }

    /**
     * Registers the given baked model under the given identifier. The identifier must not already contain a model.
     */
    public void registerSpecialModel(String identifier, IBakedModel model){
        this.registerSpecialModel(identifier, () -> model);
    }

    /**
     * Registers an overwrite for an already present baked model.
     */
    public void registerModelOverwrite(ResourceLocation identifier, Function<IBakedModel,IBakedModel> modelOverwrite){
        if(this.passedModelBake)
            throw new IllegalStateException("Cannot register new model overwrites after ModelBakeEvent has been fired!");
        if(this.specialModels.containsKey(identifier))
            throw new RuntimeException("Overlapping special model and model overwrite '" + identifier + "'!");

        this.modelOverwrites.add(Pair.of(() -> Stream.of(identifier), modelOverwrite));
    }

    /**
     * Registers an overwrite for an already present baked model.
     */
    public void registerModelOverwrite(String namespace, String identifier, String variant, Function<IBakedModel,IBakedModel> modelOverwrite){
        if(!RegistryUtil.isValidNamespace(namespace))
            throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
        if(!RegistryUtil.isValidPath(identifier))
            throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");
        if(!RegistryUtil.isValidPath(variant))
            throw new IllegalArgumentException("Variant '" + variant + "' must only contain characters [a-z0-9_./-]!");

        ResourceLocation fullIdentifier = new ModelResourceLocation(namespace + ":" + identifier + "#" + variant);
        this.registerModelOverwrite(fullIdentifier, modelOverwrite);
    }

    /**
     * Registers an overwrite for an already present baked model.
     */
    public void registerModelOverwrite(String namespace, String identifier, Function<IBakedModel,IBakedModel> modelOverwrite){
        if(!RegistryUtil.isValidNamespace(namespace))
            throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
        if(!RegistryUtil.isValidPath(identifier))
            throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

        ResourceLocation fullIdentifier = new ResourceLocation(namespace, identifier);
        this.registerModelOverwrite(fullIdentifier, modelOverwrite);
    }

    /**
     * Registers an overwrite for an already present baked model.
     */
    public void registerModelOverwrite(String namespace, String identifier, String variant, Supplier<IBakedModel> modelOverwrite){
        this.registerModelOverwrite(namespace, identifier, variant, model -> modelOverwrite.get());
    }

    /**
     * Registers an overwrite for an already present baked model.
     */
    public void registerModelOverwrite(String namespace, String identifier, Supplier<IBakedModel> modelOverwrite){
        this.registerModelOverwrite(namespace, identifier, model -> modelOverwrite.get());
    }

    /**
     * Registers an overwrite for an already present baked model.
     */
    public void registerModelOverwrite(String namespace, String identifier, String variant, IBakedModel modelOverwrite){
        this.registerModelOverwrite(namespace, identifier, variant, model -> modelOverwrite);
    }

    /**
     * Registers an overwrite for an already present baked model.
     */
    public void registerModelOverwrite(String namespace, String identifier, IBakedModel modelOverwrite){
        this.registerModelOverwrite(namespace, identifier, model -> modelOverwrite);
    }

    /**
     * Registers an overwrite for all models for the given block, including the block's item model.
     */
    public void registerBlockModelOverwrite(Supplier<Block> block, Function<IBakedModel,IBakedModel> modelOverwrite){
        if(this.passedModelBake)
            throw new IllegalStateException("Cannot register new model overwrites after ModelBakeEvent has been fired!");

        this.modelOverwrites.add(Pair.of(
            () -> block.get().getStateDefinition().getPossibleStates().stream()
                .map(BlockModelShapes::stateToModelLocation)
                .map(ResourceLocation.class::cast),
            modelOverwrite)
        );
        this.registerItemModelOverwrite(() -> block.get().asItem(), modelOverwrite);
    }

    /**
     * Registers an overwrite for all models for the given block, including the block's item model.
     */
    public void registerBlockModelOverwrite(Supplier<Block> block, Supplier<IBakedModel> modelOverwrite){
        this.registerBlockModelOverwrite(block, model -> modelOverwrite.get());
    }

    /**
     * Registers an overwrite for all models for the given block, including the block's item model.
     */
    public void registerBlockModelOverwrite(Supplier<Block> block, IBakedModel modelOverwrite){
        this.registerBlockModelOverwrite(block, model -> modelOverwrite);
    }

    /**
     * Registers an overwrite for the given item's model.
     */
    public void registerItemModelOverwrite(Supplier<Item> item, Function<IBakedModel,IBakedModel> modelOverwrite){
        if(this.passedModelBake)
            throw new IllegalStateException("Cannot register new model overwrites after ModelBakeEvent has been fired!");

        this.modelOverwrites.add(Pair.of(
            () -> Stream.of(new ModelResourceLocation(Registries.ITEMS.getIdentifier(item.get()), "inventory")),
            modelOverwrite
        ));
    }

    /**
     * Registers an overwrite for the given item's model.
     */
    public void registerItemModelOverwrite(Supplier<Item> item, Supplier<IBakedModel> modelOverwrite){
        this.registerItemModelOverwrite(item, model -> modelOverwrite.get());
    }

    /**
     * Registers an overwrite for the given item's model.
     */
    public void registerItemModelOverwrite(Supplier<Item> item, IBakedModel modelOverwrite){
        this.registerItemModelOverwrite(item, model -> modelOverwrite);
    }

    /**
     * Registers the given entity renderer for the given entity type.
     */
    @SuppressWarnings("unchecked")
    public <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> entityType, Supplier<EntityRenderer<? super T>> entityRenderer){
        if(this.passedClientSetup)
            throw new IllegalStateException("Cannot register new renderers after RegisterRenderers has been fired!");

        this.entityRenderers.add(Pair.of((Supplier<EntityType<?>>)(Object)entityType, (Supplier<EntityRenderer<?>>)(Object)entityRenderer));
    }

    /**
     * Registers the given entity renderer for the given entity type.
     */
    public <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> entityType, EntityRenderer<? super T> entityRenderer){
        this.registerEntityRenderer(entityType, () -> entityRenderer);
    }

    /**
     * Registers the given block entity renderer for the given block entity type.
     */
    @SuppressWarnings("unchecked")
    public <T extends TileEntity> void registerBlockEntityRenderer(Supplier<TileEntityType<T>> entityType, Function<TileEntityRendererDispatcher,TileEntityRenderer<? super T>> blockEntityRenderer){
        if(this.passedClientSetup)
            throw new IllegalStateException("Cannot register new renderers after RegisterRenderers has been fired!");

        this.blockEntityRenderers.add(Pair.of((Supplier<TileEntityType<?>>)(Object)entityType, (Function<TileEntityRendererDispatcher,TileEntityRenderer<?>>)(Object)blockEntityRenderer));
    }

    /**
     * Registers the given block entity renderer for the given block entity type.
     */
    public <T extends TileEntity> void registerBlockEntityRenderer(Supplier<TileEntityType<T>> entityType, Supplier<TileEntityRenderer<? super T>> blockEntityRenderer){
        this.registerBlockEntityRenderer(entityType, context -> blockEntityRenderer.get());
    }

    /**
     * Registers the given block entity renderer for the given block entity type.
     */
    public <T extends TileEntity> void registerBlockEntityRenderer(Supplier<TileEntityType<T>> entityType, TileEntityRenderer<? super T> blockEntityRenderer){
        this.registerBlockEntityRenderer(entityType, context -> blockEntityRenderer);
    }

    /**
     * Registers the given block entity renderer for the given block entity type.
     */
    public <T extends TileEntity> void registerCustomBlockEntityRenderer(Supplier<TileEntityType<T>> entityType, Supplier<CustomBlockEntityRenderer<? super T>> blockEntityRenderer){
        this.registerBlockEntityRenderer(entityType, context -> CustomBlockEntityRenderer.of(blockEntityRenderer.get()));
    }

    /**
     * Registers the given block entity renderer for the given block entity type.
     */
    public <T extends TileEntity> void registerCustomBlockEntityRenderer(Supplier<TileEntityType<T>> entityType, CustomBlockEntityRenderer<? super T> blockEntityRenderer){
        this.registerBlockEntityRenderer(entityType, context -> CustomBlockEntityRenderer.of(blockEntityRenderer));
    }

    /**
     * Adds the given sprite to the given atlas.
     */
    public void registerAtlasSprite(ResourceLocation textureAtlas, ResourceLocation spriteLocation){
        if(this.passedTextureStitch)
            throw new IllegalStateException("Cannot register new models after TextureStitchEvent has been fired!");
        if(textureAtlas == null)
            throw new IllegalArgumentException("Texture atlas must not be null!");

        this.textureAtlasSprites.putIfAbsent(textureAtlas, new HashSet<>());
        if(this.textureAtlasSprites.get(textureAtlas).contains(spriteLocation))
            throw new RuntimeException("Duplicate sprite registration '" + spriteLocation + "' for atlas '" + textureAtlas + "'!");

        this.textureAtlasSprites.get(textureAtlas).add(spriteLocation);
    }

    /**
     * Adds the given sprite to the given atlas.
     */
    public void registerAtlasSprite(ResourceLocation textureAtlas, String spriteLocation){
        if(!RegistryUtil.isValidPath(spriteLocation))
            throw new IllegalArgumentException("Sprite location '" + spriteLocation + "' must only contain characters [a-z0-9_./-]!");

        this.registerAtlasSprite(textureAtlas, new ResourceLocation(this.modid, spriteLocation));
    }

    /**
     * Registers the given custom item renderer for the given item.
     */
    public void registerItemRenderer(Supplier<Item> item, Supplier<ItemStackTileEntityRenderer> itemRenderer){
        if(this.passedClientSetup)
            throw new IllegalStateException("Cannot register new renderers after item RegistryEvent has been fired!");

        this.customItemRenderers.add(Pair.of(item, itemRenderer));
    }

    /**
     * Registers the given custom item renderer for the given item.
     */
    public void registerItemRenderer(Supplier<Item> item, ItemStackTileEntityRenderer itemRenderer){
        this.registerItemRenderer(item, () -> itemRenderer);
    }

    /**
     * Registers the given custom item renderer for the given item.
     */
    public void registerItemRenderer(Item item, Supplier<ItemStackTileEntityRenderer> itemRenderer){
        this.registerItemRenderer(() -> item, itemRenderer);
    }

    /**
     * Registers the given custom item renderer for the given item.
     */
    public void registerItemRenderer(Item item, ItemStackTileEntityRenderer itemRenderer){
        this.registerItemRenderer(() -> item, () -> itemRenderer);
    }

    /**
     * Registers the given custom item renderer for the given item.
     */
    public void registerCustomItemRenderer(Supplier<Item> item, Supplier<CustomItemRenderer> itemRenderer){
        this.registerItemRenderer(item, () -> CustomItemRenderer.of(itemRenderer.get()));
    }

    /**
     * Registers the given custom item renderer for the given item.
     */
    public void registerCustomItemRenderer(Supplier<Item> item, CustomItemRenderer itemRenderer){
        this.registerItemRenderer(item, () -> CustomItemRenderer.of(itemRenderer));
    }

    /**
     * Registers the given custom item renderer for the given item.
     */
    public void registerCustomItemRenderer(Item item, Supplier<CustomItemRenderer> itemRenderer){
        this.registerItemRenderer(() -> item, () -> CustomItemRenderer.of(itemRenderer.get()));
    }

    /**
     * Registers the given custom item renderer for the given item.
     */
    public void registerCustomItemRenderer(Item item, CustomItemRenderer itemRenderer){
        this.registerItemRenderer(() -> item, () -> CustomItemRenderer.of(itemRenderer));
    }

    /**
     * Registers the given screen constructor for the given menu type.
     */
    public <T extends Container, U extends Screen & IHasContainer<T>> void registerContainerScreen(Supplier<ContainerType<T>> menuType, TriFunction<T,PlayerInventory,ITextComponent,U> screenSupplier){
        if(this.passedClientSetup)
            throw new IllegalStateException("Cannot register new menu screens after the ClientInitialization event has been fired!");

        //noinspection unchecked
        this.containerScreens.add(Pair.of((Supplier<ContainerType<?>>)(Object)menuType, (TriFunction<Container,PlayerInventory,ITextComponent,Screen>)(Object)screenSupplier));
    }

    /**
     * Registers the given screen constructor for the given menu type.
     */
    public <T extends Container, U extends Screen & IHasContainer<T>> void registerContainerScreen(Supplier<ContainerType<T>> menuType, Function<T,U> screenSupplier){
        this.registerContainerScreen(menuType, (container, inventory, title) -> screenSupplier.apply(container));
    }

    /**
     * Registers the given screen constructor for the given menu type.
     */
    public <T extends Container, U extends Screen & IHasContainer<T>> void registerContainerScreen(ContainerType<T> menuType, TriFunction<T,PlayerInventory,ITextComponent,U> screenSupplier){
        this.registerContainerScreen(() -> menuType, screenSupplier);
    }

    /**
     * Registers the given screen constructor for the given menu type.
     */
    public <T extends Container, U extends Screen & IHasContainer<T>> void registerContainerScreen(ContainerType<T> menuType, Function<T,U> screenSupplier){
        this.registerContainerScreen(() -> menuType, (container, inventory, title) -> screenSupplier.apply(container));
    }

    /**
     * Registers the given render type to be used when rendering the given block.
     */
    public void registerBlockModelRenderType(Supplier<Block> block, Supplier<RenderType> renderTypeSupplier){
        if(this.passedClientSetup)
            throw new IllegalStateException("Cannot register new menu screens after the ClientInitialization event has been fired!");

        this.blockRenderTypes.add(Pair.of(block, renderTypeSupplier));
    }

    /**
     * Registers the given render type to be used when rendering the given block.
     */
    public void registerBlockModelRenderType(Supplier<Block> block, RenderType renderType){
        this.registerBlockModelRenderType(block, renderType);
    }

    /**
     * Registers the given render type to be used when rendering the given block.
     */
    public void registerBlockModelRenderType(Block block, Supplier<RenderType> renderTypeSupplier){
        this.registerBlockModelRenderType(() -> block, renderTypeSupplier);
    }

    /**
     * Registers the solid render type to be used when rendering the given block.
     */
    public void registerBlockModelSolidRenderType(Supplier<Block> block){
        this.registerBlockModelRenderType(block, RenderType::solid);
    }

    /**
     * Registers the solid render type to be used when rendering the given block.
     */
    public void registerBlockModelSolidRenderType(Block block){
        this.registerBlockModelRenderType(block, RenderType::solid);
    }

    /**
     * Registers the cutout mipped render type to be used when rendering the given block.
     */
    public void registerBlockModelCutoutMippedRenderType(Supplier<Block> block){
        this.registerBlockModelRenderType(block, RenderType::cutoutMipped);
    }

    /**
     * Registers the cutout mipped render type to be used when rendering the given block.
     */
    public void registerBlockModelCutoutMippedRenderType(Block block){
        this.registerBlockModelRenderType(block, RenderType::cutoutMipped);
    }

    /**
     * Registers the cutout render type to be used when rendering the given block.
     */
    public void registerBlockModelCutoutRenderType(Supplier<Block> block){
        this.registerBlockModelRenderType(block, RenderType::cutout);
    }

    /**
     * Registers the cutout render type to be used when rendering the given block.
     */
    public void registerBlockModelCutoutRenderType(Block block){
        this.registerBlockModelRenderType(block, RenderType::cutout);
    }

    /**
     * Registers the translucent render type to be used when rendering the given block.
     */
    public void registerBlockModelTranslucentRenderType(Supplier<Block> block){
        this.registerBlockModelRenderType(block, RenderType::translucent);
    }

    /**
     * Registers the translucent render type to be used when rendering the given block.
     */
    public void registerBlockModelTranslucentRenderType(Block block){
        this.registerBlockModelRenderType(block, RenderType::translucent);
    }

    private void handleModelRegistryEvent(ModelRegistryEvent e){
        this.passedModelRegistry = true;

        // Additional models
        for(ResourceLocation model : this.models)
            ModelLoader.addSpecialModel(model);
    }

    private void handleModelBakeEvent(ModelBakeEvent e){
        this.passedModelBake = true;

        // Special models
        for(Map.Entry<ResourceLocation,Supplier<IBakedModel>> entry : this.specialModels.entrySet()){
            ResourceLocation identifier = entry.getKey();
            if(e.getModelRegistry().containsKey(identifier))
                throw new RuntimeException("Special model '" + identifier + "' is trying to overwrite another model!");

            IBakedModel model = entry.getValue().get();
            if(model == null)
                throw new RuntimeException("Got null object for special model '" + entry.getKey() + "'!");

            e.getModelRegistry().put(entry.getKey(), model);
        }

        // Model overwrites
        for(Pair<Supplier<Stream<ResourceLocation>>,Function<IBakedModel,IBakedModel>> pair : this.modelOverwrites){
            // Get all the identifiers which should be replaced
            List<ResourceLocation> modelIdentifiers;
            try(Stream<ResourceLocation> stream = pair.left().get()){
                modelIdentifiers = stream.collect(Collectors.toList());
            }

            for(ResourceLocation identifier : modelIdentifiers){
                if(!e.getModelRegistry().containsKey(identifier))
                    throw new RuntimeException("No model registered for model overwrite '" + identifier + "'!");

                IBakedModel model = e.getModelRegistry().get(identifier);
                model = pair.right().apply(model);
                if(model == null)
                    throw new RuntimeException("Model overwrite for '" + identifier + "' returned a null model!");

                e.getModelRegistry().put(identifier, model);
            }
        }
    }

    private void handleRegisterRenderersEvent(FMLClientSetupEvent e){
        this.passedClientSetup = true;

        // Entity renderers
        Set<EntityType<?>> entityTypes = new HashSet<>();
        for(Pair<Supplier<EntityType<?>>,Supplier<EntityRenderer<?>>> entry : this.entityRenderers){
            EntityType<?> entityType = entry.left().get();
            if(entityType == null)
                throw new RuntimeException("Entity renderer registered with null entity type!");
            if(entityTypes.contains(entityType))
                throw new RuntimeException("Duplicate entity renderer for entity type '" + Registries.ENTITY_TYPES.getIdentifier(entityType) + "'!");

            EntityRenderer<?> entityRenderer = entry.right().get();
            if(entityRenderer == null)
                throw new RuntimeException("Got null entity renderer for entity type '" + Registries.ENTITY_TYPES.getIdentifier(entityType) + "!");

            entityTypes.add(entityType);
            //noinspection unchecked,rawtypes
            ClientUtils.getMinecraft().getEntityRenderDispatcher().register((EntityType)entityType, (EntityRenderer)entityRenderer);
        }

        // Entity renderers
        Set<TileEntityType<?>> blockEntityTypes = new HashSet<>();
        for(Pair<Supplier<TileEntityType<?>>,Function<TileEntityRendererDispatcher,TileEntityRenderer<?>>> entry : this.blockEntityRenderers){
            TileEntityType<?> blockEntityType = entry.left().get();
            if(blockEntityType == null)
                throw new RuntimeException("Block entity renderer registered with null block entity type!");
            if(blockEntityTypes.contains(blockEntityType))
                throw new RuntimeException("Duplicate block entity renderer for block entity type '" + Registries.BLOCK_ENTITY_TYPES.getIdentifier(blockEntityType) + "'!");

            blockEntityTypes.add(blockEntityType);
            //noinspection unchecked,rawtypes
            ClientRegistry.bindTileEntityRenderer((TileEntityType)blockEntityType, (Function)entry.right());
        }

        // Custom item renderers
        Set<Item> items = new HashSet<>();
        for(Pair<Supplier<Item>,Supplier<ItemStackTileEntityRenderer>> entry : this.customItemRenderers){
            Item item = entry.left().get();
            if(item == null)
                throw new RuntimeException("Custom item renderer registered with null item!");
            if(items.contains(item))
                throw new RuntimeException("Duplicate custom item renderer for item '" + Registries.ITEMS.getIdentifier(item) + "'!");
            if(getItemCustomRenderer(item) != null)
                throw new RuntimeException("Item '" + Registries.ITEMS.getIdentifier(item) + "' already has a custom item renderer set!");

            ItemStackTileEntityRenderer customRenderer = entry.right().get();
            if(customRenderer == null)
                throw new RuntimeException("Got null custom item renderer for item '" + Registries.ITEMS.getIdentifier(item) + "'!");

            items.add(item);
            setItemCustomRenderer(item, () -> customRenderer);
        }

        // Container Screens
        Set<ContainerType<?>> menuTypes = new HashSet<>();
        for(Pair<Supplier<ContainerType<?>>,TriFunction<Container,PlayerInventory,ITextComponent,Screen>> entry : this.containerScreens){
            ContainerType<?> menuType = entry.left().get();
            if(menuType == null)
                throw new RuntimeException("Container screen registered with null menu type!");
            if(menuTypes.contains(menuType))
                throw new RuntimeException("Duplicate container screen for menu type '" + Registries.MENU_TYPES.getIdentifier(menuType) + "'!");

            menuTypes.add(menuType);
            //noinspection unchecked,rawtypes,NullableProblems
            ScreenManager.register((ContainerType)menuType, (ScreenManager.IScreenFactory)entry.right()::apply);
        }

        // Block render types
        Set<Block> blocks = new HashSet<>();
        for(Pair<Supplier<Block>,Supplier<RenderType>> entry : this.blockRenderTypes){
            Block block = entry.left().get();
            if(block == null)
                throw new RuntimeException("Block render type registered for null block!");
            if(blocks.contains(block))
                throw new RuntimeException("Duplicate render type for block '" + Registries.BLOCKS.getIdentifier(block) + "'!");
            RenderType renderType = entry.right().get();
            if(renderType == null)
                throw new RuntimeException("Got null render type for block '" + Registries.BLOCKS.getIdentifier(block) + "'!");

            blocks.add(block);
            RenderTypeLookup.setRenderLayer(block, renderType);
        }
    }

    private void handleTextureStitchEvent(TextureStitchEvent.Pre e){
        this.passedTextureStitch = true;

        // Texture atlas sprites
        Set<ResourceLocation> sprites = this.textureAtlasSprites.get(e.getMap().location());
        if(sprites == null)
            return;

        sprites.forEach(e::addSprite);
    }
}
```

### src/main/java/com/supermartijn642/core/registry/GeneratorRegistrationHandler.java

```java
package com.supermartijn642.core.registry;

import com.supermartijn642.core.CoreLib;
import com.supermartijn642.core.generator.ResourceCache;
import com.supermartijn642.core.generator.ResourceGenerator;
import com.supermartijn642.core.util.Either;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModLoadingContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created 04/08/2022 by SuperMartijn642
 */
public class GeneratorRegistrationHandler {

    /**
     * Contains one registration helper per modid
     */
    private static final Map<String,GeneratorRegistrationHandler> REGISTRATION_HELPER_MAP = new HashMap<>();

    /**
     * @deprecated for internal use only
     */
    @Deprecated
    public static boolean hasHandlerForModid(String modid){
        return REGISTRATION_HELPER_MAP.containsKey(modid);
    }

    /**
     * Get a registration handler for a given modid. This will always return one unique registration handler per modid.
     * @param modid modid of the mod registering entries
     * @return a unique registration handler for the given modid
     */
    public static synchronized GeneratorRegistrationHandler get(String modid){
        if(!RegistryUtil.isValidNamespace(modid))
            throw new IllegalArgumentException("Modid '" + modid + "' must only contain characters [a-z0-9_.-]!");
        String activeMod = ModLoadingContext.get().getActiveNamespace();
        if(activeMod != null && !activeMod.equals("minecraft") && !activeMod.equals("forge")){
            if(!activeMod.equals(modid))
                CoreLib.LOGGER.warn("Mod '" + ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName() + "' is requesting registration helper for different modid '" + modid + "'!");
        }else if(modid.equals("minecraft") || modid.equals("forge"))
            CoreLib.LOGGER.warn("Mod is requesting registration helper for modid '" + modid + "'!");

        return REGISTRATION_HELPER_MAP.computeIfAbsent(modid, GeneratorRegistrationHandler::new);
    }

    private final String modid;
    private final List<Either<Function<ResourceCache,ResourceGenerator>,BiFunction<DataGenerator,ExistingFileHelper,IDataProvider>>> generatorsAndProviders = new ArrayList<>();

    private boolean hasEventBeenFired;

    private GeneratorRegistrationHandler(String modid){
        this.modid = modid;
    }

    /**
     * Adds the given generator to the list of generators to be run.
     */
    public void addGenerator(Function<ResourceCache,ResourceGenerator> generator){
        if(generator == null)
            throw new IllegalArgumentException("Generator must not be null!");
        if(this.hasEventBeenFired)
            throw new RuntimeException("Generators supplier must be added before the GatherDataEvent gets fired!");

        this.generatorsAndProviders.add(Either.left(generator));
    }

    /**
     * Adds the given generator to the list of generators to be run.
     */
    public void addGenerator(Supplier<ResourceGenerator> generator){
        if(generator == null)
            throw new IllegalArgumentException("Generator supplier must not be null!");

        this.addGenerator(cache -> generator.get());
    }

    /**
     * Adds the given generator to the list of generators to be run.
     */
    public void addGenerator(ResourceGenerator generator){
        if(generator == null)
            throw new IllegalArgumentException("Generator must not be null!");

        this.addGenerator(cache -> generator);
    }

    /**
     * Adds the given data provider to the list of providers to be run.
     */
    public void addProvider(BiFunction<DataGenerator,ExistingFileHelper,IDataProvider> provider){
        if(provider == null)
            throw new IllegalArgumentException("Provider must not be null!");
        if(this.hasEventBeenFired)
            throw new RuntimeException("Providers supplier must be added before the GatherDataEvent gets fired!");

        this.generatorsAndProviders.add(Either.right(provider));
    }

    /**
     * Adds the given data provider to the list of providers to be run.
     */
    public void addProvider(Function<DataGenerator,IDataProvider> provider){
        if(provider == null)
            throw new IllegalArgumentException("Provider must not be null!");

        this.addProvider((generator, existingFileHelper) -> provider.apply(generator));
    }

    /**
     * Adds the given data provider to the list of providers to be run.
     */
    public void addProvider(Supplier<IDataProvider> provider){
        if(provider == null)
            throw new IllegalArgumentException("Provider must not be null!");

        this.addProvider((dataGenerator, existingFileHelper) -> provider.get());
    }

    /**
     * Adds the given data provider to the list of providers to be run.
     */
    public void addProvider(IDataProvider provider){
        if(provider == null)
            throw new IllegalArgumentException("Provider must not be null!");

        this.addProvider((dataGenerator, existingFileHelper) -> provider);
    }

    /**
     * @deprecated for internal use only
     */
    @Deprecated
    public void registerProviders(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper, ResourceCache cache){
        this.hasEventBeenFired = true;

        // Resolve and add all the generators and providers
        this.generatorsAndProviders
            .stream()
            .map(either -> either.mapLeft(generator -> generator.apply(cache)))
            .map(either -> either.mapLeft(ResourceGenerator::createDataProvider))
            .map(either -> either.mapRight(provider -> provider.apply(dataGenerator, existingFileHelper)))
            .map(either -> either.leftOrElseGet(either::right))
            .forEach(dataGenerator::addProvider);
    }
}
```

### src/main/java/com/supermartijn642/core/registry/RegistrationHandler.java

```java
package com.supermartijn642.core.registry;

import com.supermartijn642.core.CoreLib;
import com.supermartijn642.core.data.condition.ResourceConditionSerializer;
import com.supermartijn642.core.data.tag.CustomTagEntrySerializer;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
public class RegistrationHandler {

    /**
     * Contains one registration helper per modid
     */
    private static final Map<String,RegistrationHandler> REGISTRATION_HELPER_MAP = new HashMap<>();

    /**
     * Get a registration handler for a given modid. This will always return one unique registration handler per modid.
     * @param modid modid of the mod registering entries
     * @return a unique registration handler for the given modid
     */
    public static synchronized RegistrationHandler get(String modid){
        if(!RegistryUtil.isValidNamespace(modid))
            throw new IllegalArgumentException("Modid '" + modid + "' must only contain characters [a-z0-9_.-]!");
        String activeMod = ModLoadingContext.get().getActiveNamespace();
        if(activeMod != null && !activeMod.equals("minecraft") && !activeMod.equals("forge")){
            if(!activeMod.equals(modid))
                CoreLib.LOGGER.warn("Mod '" + ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName() + "' is requesting registration helper for different modid '" + modid + "'!");
        }else if(modid.equals("minecraft") || modid.equals("forge"))
            CoreLib.LOGGER.warn("Mod is requesting registration helper for modid '" + modid + "'!");

        return REGISTRATION_HELPER_MAP.computeIfAbsent(modid, RegistrationHandler::new);
    }

    private final String modid;
    private final Map<Registries.Registry<?>,Map<ResourceLocation,Supplier<?>>> entryMap = new HashMap<>();
    private final Map<Registries.Registry<?>,List<Consumer<Helper<?>>>> callbacks = new HashMap<>();
    private final Set<Registries.Registry<?>> encounteredEvents = new HashSet<>();

    private RegistrationHandler(String modid){
        this.modid = modid;
        //noinspection unchecked,rawtypes
        Registries.FORGE_REGISTRY_MAP.values().forEach(registry -> this.registerRegistryEventHandler((Registries.Registry)registry));
    }

    @SuppressWarnings("unchecked")
    private <T extends IForgeRegistryEntry<T>> void registerRegistryEventHandler(Registries.Registry<T> registry){
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(registry.getValueClass(), (Consumer<RegistryEvent.Register<T>>)(Object)(Consumer<RegistryEvent.Register<?>>)this::handleRegisterEvent);
    }

    public void registerBlock(String identifier, Supplier<Block> block){
        this.addEntry(Registries.BLOCKS, identifier, block);
    }

    public void registerBlock(String identifier, Block block){
        this.addEntry(Registries.BLOCKS, identifier, () -> block);
    }

    public void registerBlockOverride(String namespace, String identifier, Supplier<Block> block){
        this.addEntry(Registries.BLOCKS, namespace, identifier, block);
    }

    public void registerBlockOverride(String namespace, String identifier, Block block){
        this.addEntry(Registries.BLOCKS, namespace, identifier, () -> block);
    }

    public void registerBlockCallback(Consumer<Helper<Block>> callback){
        this.addCallback(Registries.BLOCKS, callback);
    }

    public void registerFluid(String identifier, Supplier<Fluid> fluid){
        this.addEntry(Registries.FLUIDS, identifier, fluid);
    }

    public void registerFluid(String identifier, Fluid fluid){
        this.addEntry(Registries.FLUIDS, identifier, () -> fluid);
    }

    public void registerFluidOverride(String namespace, String identifier, Supplier<Fluid> fluid){
        this.addEntry(Registries.FLUIDS, namespace, identifier, fluid);
    }

    public void registerFluidOverride(String namespace, String identifier, Fluid fluid){
        this.addEntry(Registries.FLUIDS, namespace, identifier, () -> fluid);
    }

    public void registerFluidCallback(Consumer<Helper<Fluid>> callback){
        this.addCallback(Registries.FLUIDS, callback);
    }

    public void registerItem(String identifier, Supplier<Item> item){
        this.addEntry(Registries.ITEMS, identifier, item);
    }

    public void registerItem(String identifier, Item item){
        this.addEntry(Registries.ITEMS, identifier, () -> item);
    }

    public void registerItemOverride(String namespace, String identifier, Supplier<Item> item){
        this.addEntry(Registries.ITEMS, namespace, identifier, item);
    }

    public void registerItemOverride(String namespace, String identifier, Item item){
        this.addEntry(Registries.ITEMS, namespace, identifier, () -> item);
    }

    public void registerItemCallback(Consumer<Helper<Item>> callback){
        this.addCallback(Registries.ITEMS, callback);
    }

    public void registerMobEffect(String identifier, Supplier<Effect> effect){
        this.addEntry(Registries.MOB_EFFECTS, identifier, effect);
    }

    public void registerMobEffect(String identifier, Effect effect){
        this.addEntry(Registries.MOB_EFFECTS, identifier, () -> effect);
    }

    public void registerMobEffectOverride(String namespace, String identifier, Supplier<Effect> effect){
        this.addEntry(Registries.MOB_EFFECTS, namespace, identifier, effect);
    }

    public void registerMobEffectOverride(String namespace, String identifier, Effect effect){
        this.addEntry(Registries.MOB_EFFECTS, namespace, identifier, () -> effect);
    }

    public void registerMobEffectCallback(Consumer<Helper<Effect>> callback){
        this.addCallback(Registries.MOB_EFFECTS, callback);
    }

    public void registerSoundEvent(String identifier, Supplier<SoundEvent> sound){
        this.addEntry(Registries.SOUND_EVENTS, identifier, sound);
    }

    public void registerSoundEvent(String identifier, SoundEvent sound){
        this.addEntry(Registries.SOUND_EVENTS, identifier, () -> sound);
    }

    public void registerSoundEventOverride(String namespace, String identifier, Supplier<SoundEvent> sound){
        this.addEntry(Registries.SOUND_EVENTS, namespace, identifier, sound);
    }

    public void registerSoundEventOverride(String namespace, String identifier, SoundEvent sound){
        this.addEntry(Registries.SOUND_EVENTS, namespace, identifier, () -> sound);
    }

    public void registerSoundEventCallback(Consumer<Helper<SoundEvent>> callback){
        this.addCallback(Registries.SOUND_EVENTS, callback);
    }

    public void registerPotion(String identifier, Supplier<Potion> potion){
        this.addEntry(Registries.POTIONS, identifier, potion);
    }

    public void registerPotion(String identifier, Potion potion){
        this.addEntry(Registries.POTIONS, identifier, () -> potion);
    }

    public void registerPotionOverride(String namespace, String identifier, Supplier<Potion> potion){
        this.addEntry(Registries.POTIONS, namespace, identifier, potion);
    }

    public void registerPotionOverride(String namespace, String identifier, Potion potion){
        this.addEntry(Registries.POTIONS, namespace, identifier, () -> potion);
    }

    public void registerPotionCallback(Consumer<Helper<Potion>> callback){
        this.addCallback(Registries.POTIONS, callback);
    }

    public void registerEnchantment(String identifier, Supplier<Enchantment> enchantment){
        this.addEntry(Registries.ENCHANTMENTS, identifier, enchantment);
    }

    public void registerEnchantment(String identifier, Enchantment enchantment){
        this.addEntry(Registries.ENCHANTMENTS, identifier, () -> enchantment);
    }

    public void registerEnchantmentOverride(String namespace, String identifier, Supplier<Enchantment> enchantment){
        this.addEntry(Registries.ENCHANTMENTS, namespace, identifier, enchantment);
    }

    public void registerEnchantmentOverride(String namespace, String identifier, Enchantment enchantment){
        this.addEntry(Registries.ENCHANTMENTS, namespace, identifier, () -> enchantment);
    }

    public void registerEnchantmentCallback(Consumer<Helper<Enchantment>> callback){
        this.addCallback(Registries.ENCHANTMENTS, callback);
    }

    public void registerEntityType(String identifier, Supplier<EntityType<?>> entityType){
        this.addEntry(Registries.ENTITY_TYPES, identifier, entityType);
    }

    public void registerEntityType(String identifier, EntityType<?> entityType){
        this.addEntry(Registries.ENTITY_TYPES, identifier, () -> entityType);
    }

    public void registerEntityTypeOverride(String namespace, String identifier, Supplier<EntityType<?>> entityType){
        this.addEntry(Registries.ENTITY_TYPES, namespace, identifier, entityType);
    }

    public void registerEntityTypeOverride(String namespace, String identifier, EntityType<?> entityType){
        this.addEntry(Registries.ENTITY_TYPES, namespace, identifier, () -> entityType);
    }

    public void registerEntityTypeCallback(Consumer<Helper<EntityType<?>>> callback){
        this.addCallback(Registries.ENTITY_TYPES, callback);
    }

    public void registerBlockEntityType(String identifier, Supplier<TileEntityType<?>> blockEntityType){
        this.addEntry(Registries.BLOCK_ENTITY_TYPES, identifier, blockEntityType);
    }

    public void registerBlockEntityType(String identifier, TileEntityType<?> blockEntityType){
        this.addEntry(Registries.BLOCK_ENTITY_TYPES, identifier, () -> blockEntityType);
    }

    public void registerBlockEntityTypeOverride(String namespace, String identifier, Supplier<TileEntityType<?>> blockEntityType){
        this.addEntry(Registries.BLOCK_ENTITY_TYPES, namespace, identifier, blockEntityType);
    }

    public void registerBlockEntityTypeOverride(String namespace, String identifier, TileEntityType<?> blockEntityType){
        this.addEntry(Registries.BLOCK_ENTITY_TYPES, namespace, identifier, () -> blockEntityType);
    }

    public void registerBlockEntityTypeCallback(Consumer<Helper<TileEntityType<?>>> callback){
        this.addCallback(Registries.BLOCK_ENTITY_TYPES, callback);
    }

    public void registerParticleType(String identifier, Supplier<ParticleType<?>> particleType){
        this.addEntry(Registries.PARTICLE_TYPES, identifier, particleType);
    }

    public void registerParticleType(String identifier, ParticleType<?> particleType){
        this.addEntry(Registries.PARTICLE_TYPES, identifier, () -> particleType);
    }

    public void registerParticleTypeOverride(String namespace, String identifier, Supplier<ParticleType<?>> particleType){
        this.addEntry(Registries.PARTICLE_TYPES, namespace, identifier, particleType);
    }

    public void registerParticleTypeOverride(String namespace, String identifier, ParticleType<?> particleType){
        this.addEntry(Registries.PARTICLE_TYPES, namespace, identifier, () -> particleType);
    }

    public void registerParticleTypeCallback(Consumer<Helper<ParticleType<?>>> callback){
        this.addCallback(Registries.PARTICLE_TYPES, callback);
    }

    public void registerMenuType(String identifier, Supplier<ContainerType<?>> menuType){
        this.addEntry(Registries.MENU_TYPES, identifier, menuType);
    }

    public void registerMenuType(String identifier, ContainerType<?> menuType){
        this.addEntry(Registries.MENU_TYPES, identifier, () -> menuType);
    }

    public void registerMenuTypeOverride(String namespace, String identifier, Supplier<ContainerType<?>> menuType){
        this.addEntry(Registries.MENU_TYPES, namespace, identifier, menuType);
    }

    public void registerMenuTypeOverride(String namespace, String identifier, ContainerType<?> menuType){
        this.addEntry(Registries.MENU_TYPES, namespace, identifier, () -> menuType);
    }

    public void registerMenuTypeCallback(Consumer<Helper<ContainerType<?>>> callback){
        this.addCallback(Registries.MENU_TYPES, callback);
    }

    public void registerPaintingVariant(String identifier, Supplier<PaintingType> paintingVariant){
        this.addEntry(Registries.PAINTING_VARIANTS, identifier, paintingVariant);
    }

    public void registerPaintingVariant(String identifier, PaintingType paintingVariant){
        this.addEntry(Registries.PAINTING_VARIANTS, identifier, () -> paintingVariant);
    }

    public void registerPaintingVariantOverride(String namespace, String identifier, Supplier<PaintingType> paintingVariant){
        this.addEntry(Registries.PAINTING_VARIANTS, namespace, identifier, paintingVariant);
    }

    public void registerPaintingVariantOverride(String namespace, String identifier, PaintingType paintingVariant){
        this.addEntry(Registries.PAINTING_VARIANTS, namespace, identifier, () -> paintingVariant);
    }

    public void registerPaintingVariantCallback(Consumer<Helper<PaintingType>> callback){
        this.addCallback(Registries.PAINTING_VARIANTS, callback);
    }

    public void registerRecipeSerializer(String identifier, Supplier<IRecipeSerializer<?>> recipeSerializer){
        this.addEntry(Registries.RECIPE_SERIALIZERS, identifier, recipeSerializer);
    }

    public void registerRecipeSerializer(String identifier, IRecipeSerializer<?> recipeSerializer){
        this.addEntry(Registries.RECIPE_SERIALIZERS, identifier, () -> recipeSerializer);
    }

    public void registerRecipeSerializerOverride(String namespace, String identifier, Supplier<IRecipeSerializer<?>> recipeSerializer){
        this.addEntry(Registries.RECIPE_SERIALIZERS, namespace, identifier, recipeSerializer);
    }

    public void registerRecipeSerializerOverride(String namespace, String identifier, IRecipeSerializer<?> recipeSerializer){
        this.addEntry(Registries.RECIPE_SERIALIZERS, namespace, identifier, () -> recipeSerializer);
    }

    public void registerRecipeSerializerCallback(Consumer<Helper<IRecipeSerializer<?>>> callback){
        this.addCallback(Registries.RECIPE_SERIALIZERS, callback);
    }

    public void registerAttribute(String identifier, Supplier<Attribute> attribute){
        this.addEntry(Registries.ATTRIBUTES, identifier, attribute);
    }

    public void registerAttribute(String identifier, Attribute attribute){
        this.addEntry(Registries.ATTRIBUTES, identifier, () -> attribute);
    }

    public void registerAttributeOverride(String namespace, String identifier, Supplier<Attribute> attribute){
        this.addEntry(Registries.ATTRIBUTES, namespace, identifier, attribute);
    }

    public void registerAttributeOverride(String namespace, String identifier, Attribute attribute){
        this.addEntry(Registries.ATTRIBUTES, namespace, identifier, () -> attribute);
    }

    public void registerAttributeCallback(Consumer<Helper<Attribute>> callback){
        this.addCallback(Registries.ATTRIBUTES, callback);
    }

    public void registerStatType(String identifier, Supplier<StatType<?>> statType){
        this.addEntry(Registries.STAT_TYPES, identifier, statType);
    }

    public void registerStatType(String identifier, StatType<?> statType){
        this.addEntry(Registries.STAT_TYPES, identifier, () -> statType);
    }

    public void registerStatTypeOverride(String namespace, String identifier, Supplier<StatType<?>> statType){
        this.addEntry(Registries.STAT_TYPES, namespace, identifier, statType);
    }

    public void registerStatTypeOverride(String namespace, String identifier, StatType<?> statType){
        this.addEntry(Registries.STAT_TYPES, namespace, identifier, () -> statType);
    }

    public void registerStatTypeCallback(Consumer<Helper<StatType<?>>> callback){
        this.addCallback(Registries.STAT_TYPES, callback);
    }

    public void registerConditionSerializer(String identifier, Supplier<IConditionSerializer<?>> recipeSerializer){
        this.addEntry(Registries.RECIPE_CONDITION_SERIALIZERS, identifier, recipeSerializer);
    }

    public void registerConditionSerializer(String identifier, IConditionSerializer<?> recipeSerializer){
        this.addEntry(Registries.RECIPE_CONDITION_SERIALIZERS, identifier, () -> recipeSerializer);
    }

    public void registerConditionSerializerOverride(String namespace, String identifier, Supplier<IConditionSerializer<?>> recipeSerializer){
        this.addEntry(Registries.RECIPE_CONDITION_SERIALIZERS, namespace, identifier, recipeSerializer);
    }

    public void registerConditionSerializerOverride(String namespace, String identifier, IConditionSerializer<?> recipeSerializer){
        this.addEntry(Registries.RECIPE_CONDITION_SERIALIZERS, namespace, identifier, () -> recipeSerializer);
    }

    public void registerConditionSerializerCallback(Consumer<Helper<IConditionSerializer<?>>> callback){
        this.addCallback(Registries.RECIPE_CONDITION_SERIALIZERS, callback);
    }

    public void registerResourceConditionSerializer(String identifier, Supplier<ResourceConditionSerializer<?>> conditionSerializer){
        this.registerConditionSerializer(identifier, () -> ResourceConditionSerializer.createForgeConditionSerializer(new ResourceLocation(this.modid, identifier), conditionSerializer.get()));
    }

    public void registerResourceConditionSerializer(String identifier, ResourceConditionSerializer<?> conditionSerializer){
        this.registerConditionSerializer(identifier, () -> ResourceConditionSerializer.createForgeConditionSerializer(new ResourceLocation(this.modid, identifier), conditionSerializer));
    }

    public void registerResourceConditionSerializerOverride(String namespace, String identifier, Supplier<ResourceConditionSerializer<?>> conditionSerializer){
        this.registerConditionSerializerOverride(namespace, identifier, () -> ResourceConditionSerializer.createForgeConditionSerializer(new ResourceLocation(namespace, identifier), conditionSerializer.get()));
    }

    public void registerResourceConditionSerializerOverride(String namespace, String identifier, ResourceConditionSerializer<?> conditionSerializer){
        this.registerConditionSerializerOverride(namespace, identifier, () -> ResourceConditionSerializer.createForgeConditionSerializer(new ResourceLocation(namespace, identifier), conditionSerializer));
    }

    public void registerResourceConditionSerializerCallback(Consumer<Helper<ResourceConditionSerializer<?>>> callback){
        this.registerConditionSerializerCallback(helper -> callback.accept(new Helper<ResourceConditionSerializer<?>>(null) {
            @Override
            public <X extends ResourceConditionSerializer<?>> X register(String identifier, X object){
                helper.register(identifier, ResourceConditionSerializer.createForgeConditionSerializer(new ResourceLocation(RegistrationHandler.this.modid, identifier), object));
                return object;
            }

            @Override
            public <X extends ResourceConditionSerializer<?>> X registerOverride(String namespace, String identifier, X object){
                helper.register(namespace, identifier, ResourceConditionSerializer.createForgeConditionSerializer(new ResourceLocation(namespace, identifier), object));
                return object;
            }
        }));
    }

    public void registerCustomTagEntrySerializer(String identifier, Supplier<CustomTagEntrySerializer<?>> serializer){
        this.addEntry(Registries.CUSTOM_TAG_ENTRY_SERIALIZERS, identifier, serializer);
    }

    public void registerCustomTagEntrySerializer(String identifier, CustomTagEntrySerializer<?> serializer){
        this.addEntry(Registries.CUSTOM_TAG_ENTRY_SERIALIZERS, identifier, () -> serializer);
    }

    public void registerCustomTagEntrySerializerOverride(String namespace, String identifier, Supplier<CustomTagEntrySerializer<?>> serializer){
        this.addEntry(Registries.CUSTOM_TAG_ENTRY_SERIALIZERS, namespace, identifier, serializer);
    }

    public void registerCustomTagEntrySerializerOverride(String namespace, String identifier, CustomTagEntrySerializer<?> serializer){
        this.addEntry(Registries.CUSTOM_TAG_ENTRY_SERIALIZERS, namespace, identifier, () -> serializer);
    }

    public void registerCustomTagEntrySerializerCallback(Consumer<Helper<CustomTagEntrySerializer<?>>> callback){
        this.addCallback(Registries.CUSTOM_TAG_ENTRY_SERIALIZERS, callback);
    }

    private <T> void addEntry(Registries.Registry<T> registry, String identifier, Supplier<T> entry){
        this.addEntry(registry, this.modid, identifier, entry);
    }

    private <T> void addEntry(Registries.Registry<T> registry, String namespace, String identifier, Supplier<T> entry){
        if(this.encounteredEvents.contains(registry))
            throw new IllegalStateException("Cannot register new entries after RegisterEvent has been fired!");
        if(!RegistryUtil.isValidNamespace(namespace))
            throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
        if(!RegistryUtil.isValidPath(identifier))
            throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");
        if(entry == null)
            throw new IllegalArgumentException("Entry supplier for '" + namespace + ":" + identifier + "' must not be null!");

        ResourceLocation fullIdentifier = new ResourceLocation(namespace, identifier);
        Map<ResourceLocation,Supplier<?>> entries = this.entryMap.computeIfAbsent(registry, o -> new LinkedHashMap<>());
        if(entries.containsKey(fullIdentifier))
            throw new RuntimeException("Duplicate entry '" + fullIdentifier + "' for registry '" + registry.getRegistryIdentifier() + "'!");

        entries.put(fullIdentifier, entry);
    }

    private <T> void addCallback(Registries.Registry<T> registry, Consumer<Helper<T>> callback){
        if(this.encounteredEvents.contains(registry))
            throw new IllegalStateException("Cannot register callbacks after RegisterEvent has been fired!");
        if(callback == null)
            throw new IllegalArgumentException("Registration callback must not be null!");

        //noinspection unchecked,rawtypes
        this.callbacks.computeIfAbsent(registry, o -> new ArrayList<>()).add((Consumer)callback);
    }

    private void handleRegisterEvent(RegistryEvent.Register<?> event){
        Registries.Registry<?> registry = Registries.fromUnderlying(event.getRegistry());
        if(registry == null)
            return;

        this.handleRegistry(registry);
        for(Registries.Registry<?> otherRegistry : Registries.REGISTRATION_ORDER_MAP.getOrDefault(registry, Collections.emptyList()))
            this.handleRegistry(otherRegistry);
    }

    private void handleRegistry(Registries.Registry<?> registry){
        this.encounteredEvents.add(registry);

        // Register entries
        if(this.entryMap.containsKey(registry))
            this.registerEntries(registry);

        // Call callbacks
        if(this.callbacks.containsKey(registry))
            this.callCallbacks(registry);
    }

    @SuppressWarnings("unchecked")
    private <T> void registerEntries(Registries.Registry<T> registry){
        Map<ResourceLocation,Supplier<?>> entries = this.entryMap.get(registry);
        for(Map.Entry<ResourceLocation,Supplier<?>> entry : entries.entrySet()){
            T object = (T)entry.getValue().get();
            registry.register(entry.getKey(), object);
        }
    }

    private void callCallbacks(Registries.Registry<?> registry){
        Helper<?> helper = new Helper<>(registry);
        List<Consumer<Helper<?>>> callbacks = this.callbacks.get(registry);
        for(Consumer<Helper<?>> callback : callbacks)
            callback.accept(helper);
    }

    public class Helper<T> {

        private final Registries.Registry<T> registry;

        public Helper(Registries.Registry<T> registry){
            this.registry = registry;
        }

        public <X extends T> X register(String identifier, X object){
            this.register(RegistrationHandler.this.modid, identifier, object);
            return object;
        }

        public <X extends T> X registerOverride(String namespace, String identifier, X object){
            this.register(namespace, identifier, object);
            return object;
        }

        private void register(String namespace, String identifier, T object){
            if(!RegistryUtil.isValidNamespace(namespace))
                throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
            if(!RegistryUtil.isValidPath(identifier))
                throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

            ResourceLocation fullIdentifier = new ResourceLocation(namespace, identifier);
            Map<ResourceLocation,Supplier<?>> entries = RegistrationHandler.this.entryMap.computeIfAbsent(this.registry, o -> new LinkedHashMap<>());
            if(entries.containsKey(fullIdentifier))
                throw new RuntimeException("Duplicate entry '" + fullIdentifier + "' for registry '" + this.registry.getRegistryIdentifier() + "'!");

            this.registry.register(fullIdentifier, object);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/registry/Registries.java

```java
package com.supermartijn642.core.registry;

import com.google.common.collect.Lists;
import com.supermartijn642.core.data.tag.CustomTagEntrySerializer;
import com.supermartijn642.core.util.MappedSetView;
import com.supermartijn642.core.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;

import static net.minecraft.util.registry.Registry.*;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
public final class Registries {

    static final Map<ResourceLocation,Registry<?>> IDENTIFIER_TO_REGISTRY = new HashMap<>();
    static final Map<net.minecraft.util.registry.Registry<?>,Registry<?>> VANILLA_REGISTRY_MAP = new HashMap<>();
    static final Map<IForgeRegistry<?>,Registry<?>> FORGE_REGISTRY_MAP = new HashMap<>();
    /**
     * Each entry is a registry which has a vanilla registry and a list of registries which do not have a vanilla registry.
     */
    static final Map<Registry<?>,List<Registry<?>>> REGISTRATION_ORDER_MAP = new HashMap<>();

    private static void addRegistry(Registry<?> registry){
        if(IDENTIFIER_TO_REGISTRY.containsKey(registry.getRegistryIdentifier()))
            throw new RuntimeException("Duplicate registry registration for identifier '" + registry.getRegistryIdentifier() + "'!");
        if(registry.hasVanillaRegistry() && VANILLA_REGISTRY_MAP.containsKey(registry.getVanillaRegistry()))
            throw new RuntimeException("Duplicate registry wrapper for objects of type '" + registry.getValueClass() + "'!");
        if(registry.hasForgeRegistry() && FORGE_REGISTRY_MAP.containsKey(registry.getForgeRegistry()))
            throw new RuntimeException("Duplicate registry wrapper for objects of type '" + registry.getValueClass() + "'!");

        IDENTIFIER_TO_REGISTRY.put(registry.getRegistryIdentifier(), registry);
        if(registry.hasVanillaRegistry())
            VANILLA_REGISTRY_MAP.put(registry.getVanillaRegistry(), registry);
        if(registry.hasForgeRegistry())
            FORGE_REGISTRY_MAP.put(registry.getForgeRegistry(), registry);
    }

    @Deprecated
    public static void onRecipeConditionSerializerAdded(IConditionSerializer<?> serializer){
        ((RecipeConditionSerializerRegistry)RECIPE_CONDITION_SERIALIZERS).onObjectAdded(serializer);
    }

    @SuppressWarnings("unchecked")
    @Deprecated
    public static <T> Registry<T> fromUnderlying(net.minecraft.util.registry.Registry<T> registry){
        return (Registry<T>)VANILLA_REGISTRY_MAP.get(registry);
    }

    @SuppressWarnings("unchecked")
    @Deprecated
    public static <T extends IForgeRegistryEntry<T>> Registry<T> fromUnderlying(IForgeRegistry<T> registry){
        return (Registry<T>)FORGE_REGISTRY_MAP.get(registry);
    }

    /**
     * Gets the registry registered under the given identifier.
     * @param identifier identifier of the registry
     * @return the registry registered under the given identifier or {@code null} if no registry is registered
     */
    public static Registry<?> getRegistry(ResourceLocation identifier){
        return IDENTIFIER_TO_REGISTRY.get(identifier);
    }

    public static final Registry<Block> BLOCKS = forge(BLOCK, ForgeRegistries.BLOCKS, Block.class);
    public static final Registry<Fluid> FLUIDS = forge(FLUID, ForgeRegistries.FLUIDS, Fluid.class);
    public static final Registry<Item> ITEMS = forge(ITEM, ForgeRegistries.ITEMS, Item.class);
    public static final Registry<Effect> MOB_EFFECTS = forge(MOB_EFFECT, ForgeRegistries.POTIONS, Effect.class);
    public static final Registry<SoundEvent> SOUND_EVENTS = forge(SOUND_EVENT, ForgeRegistries.SOUND_EVENTS, SoundEvent.class);
    public static final Registry<Potion> POTIONS = forge(POTION, ForgeRegistries.POTION_TYPES, Potion.class);
    public static final Registry<Enchantment> ENCHANTMENTS = forge(ENCHANTMENT, ForgeRegistries.ENCHANTMENTS, Enchantment.class);
    public static final Registry<EntityType<?>> ENTITY_TYPES = forge(ENTITY_TYPE, ForgeRegistries.ENTITIES, EntityType.class);
    public static final Registry<TileEntityType<?>> BLOCK_ENTITY_TYPES = forge(BLOCK_ENTITY_TYPE, ForgeRegistries.TILE_ENTITIES, TileEntityType.class);
    public static final Registry<ParticleType<?>> PARTICLE_TYPES = forge(PARTICLE_TYPE, ForgeRegistries.PARTICLE_TYPES, ParticleType.class);
    public static final Registry<ContainerType<?>> MENU_TYPES = forge(MENU, ForgeRegistries.CONTAINERS, ContainerType.class);
    public static final Registry<PaintingType> PAINTING_VARIANTS = forge(MOTIVE, ForgeRegistries.PAINTING_TYPES, PaintingType.class);
    public static final Registry<IRecipeType<?>> RECIPE_TYPES = vanilla(RECIPE_TYPE, IRecipeType.class);
    public static final Registry<IRecipeSerializer<?>> RECIPE_SERIALIZERS = forge(RECIPE_SERIALIZER, ForgeRegistries.RECIPE_SERIALIZERS, IRecipeSerializer.class);
    public static final Registry<Attribute> ATTRIBUTES = forge(ATTRIBUTE, ForgeRegistries.ATTRIBUTES, Attribute.class);
    public static final Registry<StatType<?>> STAT_TYPES = forge(STAT_TYPE, ForgeRegistries.STAT_TYPES, StatType.class);
    public static final Registry<IConditionSerializer<?>> RECIPE_CONDITION_SERIALIZERS = new RecipeConditionSerializerRegistry();
    public static final Registry<CustomTagEntrySerializer<?>> CUSTOM_TAG_ENTRY_SERIALIZERS = new MapBackedRegistry<>(new ResourceLocation("supermartijn642corelib", "custom_tag_entries"), CustomTagEntrySerializer.class);

    static{
        // Add all registries which don't have a forge registry
        REGISTRATION_ORDER_MAP.put(POTIONS, Lists.newArrayList(RECIPE_TYPES));
        REGISTRATION_ORDER_MAP.put(RECIPE_SERIALIZERS, Lists.newArrayList(RECIPE_CONDITION_SERIALIZERS, CUSTOM_TAG_ENTRY_SERIALIZERS));
    }

    private static <T> Registry<T> vanilla(net.minecraft.util.registry.Registry<T> registry, Class<? super T> valueClass){
        return new VanillaRegistryWrapper<>(registry, valueClass);
    }

    private static <T extends IForgeRegistryEntry<T>> Registry<T> forge(net.minecraft.util.registry.Registry<T> registry, IForgeRegistry<T> forgeRegistry, Class<? super T> valueClass){
        return new ForgeRegistryWrapper<>(registry, forgeRegistry, valueClass);
    }

    public interface Registry<T> {

        ResourceLocation getRegistryIdentifier();

        @Nullable
        net.minecraft.util.registry.Registry<T> getVanillaRegistry();

        boolean hasVanillaRegistry();

        @Nullable
        <X extends IForgeRegistryEntry<X>> IForgeRegistry<X> getForgeRegistry();

        boolean hasForgeRegistry();

        void register(ResourceLocation identifier, T object);

        ResourceLocation getIdentifier(T object);

        boolean hasIdentifier(ResourceLocation identifier);

        T getValue(ResourceLocation identifier);

        Set<ResourceLocation> getIdentifiers();

        Collection<T> getValues();

        Set<Pair<ResourceLocation,T>> getEntries();

        Class<T> getValueClass();
    }

    private static class VanillaRegistryWrapper<T> implements Registry<T> {

        private final net.minecraft.util.registry.Registry<T> registry;
        private final ResourceLocation identifier;
        private final Class<T> valueClass;

        private VanillaRegistryWrapper(net.minecraft.util.registry.Registry<T> registry, Class<? super T> valueClass){
            this.registry = registry;
            this.identifier = registry.key().location();
            //noinspection unchecked
            this.valueClass = (Class<T>)valueClass;

            addRegistry(this);
        }

        @Override
        public ResourceLocation getRegistryIdentifier(){
            return this.identifier;
        }

        @Nullable
        @Deprecated
        public net.minecraft.util.registry.Registry<T> getVanillaRegistry(){
            return this.registry;
        }

        @Override
        public boolean hasVanillaRegistry(){
            return true;
        }

        @Nullable
        @Deprecated
        public <X extends IForgeRegistryEntry<X>> IForgeRegistry<X> getForgeRegistry(){
            return null;
        }

        @Override
        public boolean hasForgeRegistry(){
            return false;
        }

        public void register(ResourceLocation identifier, T object){
            net.minecraft.util.registry.Registry.register(this.registry, identifier, object);
        }

        public ResourceLocation getIdentifier(T object){
            return this.registry.getKey(object);
        }

        @Override
        public boolean hasIdentifier(ResourceLocation identifier){
            return this.registry.containsKey(identifier);
        }

        public T getValue(ResourceLocation identifier){
            return this.registry.get(identifier);
        }

        public Set<ResourceLocation> getIdentifiers(){
            return this.registry.keySet();
        }

        public Collection<T> getValues(){
            return MappedSetView.map(this.registry.entrySet(), Map.Entry::getValue);
        }

        public Set<Pair<ResourceLocation,T>> getEntries(){
            return MappedSetView.map(this.registry.entrySet(), entry -> Pair.of(entry.getKey().location(), entry.getValue()));
        }

        public Class<T> getValueClass(){
            return this.valueClass;
        }

        @Override
        public int hashCode(){
            int result = this.registry.hashCode();
            result = 31 * result + this.valueClass.hashCode();
            return result;
        }
    }

    private static class ForgeRegistryWrapper<T extends IForgeRegistryEntry<T>> implements Registry<T> {

        private final net.minecraft.util.registry.Registry<T> registry;
        private final IForgeRegistry<T> forgeRegistry;
        private final ResourceLocation identifier;
        private final Class<T> valueClass;

        private ForgeRegistryWrapper(net.minecraft.util.registry.Registry<T> registry, IForgeRegistry<T> forgeRegistry, Class<? super T> valueClass){
            this.registry = registry;
            this.forgeRegistry = forgeRegistry;
            this.identifier = forgeRegistry.getRegistryName();
            //noinspection unchecked
            this.valueClass = (Class<T>)valueClass;

            addRegistry(this);
        }

        @Override
        public ResourceLocation getRegistryIdentifier(){
            return this.identifier;
        }

        @Nullable
        @Deprecated
        public net.minecraft.util.registry.Registry<T> getVanillaRegistry(){
            return this.registry;
        }

        @Override
        public boolean hasVanillaRegistry(){
            return this.registry != null;
        }

        @Nullable
        @Deprecated
        public <X extends IForgeRegistryEntry<X>> IForgeRegistry<X> getForgeRegistry(){
            //noinspection unchecked
            return (IForgeRegistry<X>)this.forgeRegistry;
        }

        @Override
        public boolean hasForgeRegistry(){
            return true;
        }

        public void register(ResourceLocation identifier, T object){
            object.setRegistryName(identifier);
            this.forgeRegistry.register(object);
        }

        public ResourceLocation getIdentifier(T object){
            return this.forgeRegistry.getKey(object);
        }

        @Override
        public boolean hasIdentifier(ResourceLocation identifier){
            return this.forgeRegistry.containsKey(identifier);
        }

        public T getValue(ResourceLocation identifier){
            return this.forgeRegistry.getValue(identifier);
        }

        public Set<ResourceLocation> getIdentifiers(){
            return this.forgeRegistry.getKeys();
        }

        public Collection<T> getValues(){
            return this.forgeRegistry.getValues();
        }

        public Set<Pair<ResourceLocation,T>> getEntries(){
            return MappedSetView.map(this.forgeRegistry.getEntries(), entry -> Pair.of(entry.getKey().location(), entry.getValue()));
        }

        public Class<T> getValueClass(){
            return this.valueClass;
        }

        @Override
        public int hashCode(){
            int result = this.registry.hashCode();
            result = 31 * result + this.forgeRegistry.hashCode();
            result = 31 * result + this.valueClass.hashCode();
            return result;
        }
    }

    private static class RecipeConditionSerializerRegistry implements Registry<IConditionSerializer<?>> {

        private static final Supplier<Map<ResourceLocation,IConditionSerializer<?>>> craftingHelperConditions;

        static{
            try{
                Field field = CraftingHelper.class.getDeclaredField("conditions");
                field.setAccessible(true);
                craftingHelperConditions = () -> {
                    try{
                        //noinspection unchecked
                        return (Map<ResourceLocation,IConditionSerializer<?>>)field.get(null);
                    }catch(IllegalAccessException e){
                        throw new RuntimeException(e);
                    }
                };
            }catch(NoSuchFieldException e){
                throw new RuntimeException(e);
            }
        }

        private static final ResourceLocation IDENTIFIER = new ResourceLocation("supermartijn642corelib", "resource_conditions");

        private final Map<ResourceLocation,IConditionSerializer<?>> identifierToObject;
        private final Map<IConditionSerializer<?>,ResourceLocation> objectToIdentifier = new HashMap<>();
        private final Set<Pair<ResourceLocation,IConditionSerializer<?>>> entries = new HashSet<>();
        private final Class<IConditionSerializer<?>> valueClass;

        private RecipeConditionSerializerRegistry(){
            this.identifierToObject = craftingHelperConditions.get();
            //noinspection unchecked
            this.valueClass = (Class<IConditionSerializer<?>>)(Object)IConditionSerializer.class;
        }

        @Override
        public ResourceLocation getRegistryIdentifier(){
            return IDENTIFIER;
        }

        @Nullable
        @Override
        public net.minecraft.util.registry.Registry<IConditionSerializer<?>> getVanillaRegistry(){
            return null;
        }

        @Override
        public boolean hasVanillaRegistry(){
            return false;
        }

        @Override
        public @Nullable <X extends IForgeRegistryEntry<X>> IForgeRegistry<X> getForgeRegistry(){
            return null;
        }

        @Override
        public boolean hasForgeRegistry(){
            return false;
        }

        @Override
        public void register(ResourceLocation identifier, IConditionSerializer<?> object){
            if(this.identifierToObject.containsKey(identifier))
                throw new RuntimeException("Duplicate registry for identifier '" + identifier + "'!");
            if(this.objectToIdentifier.containsKey(object))
                throw new RuntimeException("Duplicate registry for object under '" + this.objectToIdentifier.get(object) + "' and '" + identifier + "'!");
            if(!identifier.equals(object.getID()))
                throw new IllegalArgumentException("Condition serializer's id '" + object.getID() + "' does not match the given id '" + identifier + "'!");

            CraftingHelper.register(object);
        }

        public void onObjectAdded(IConditionSerializer<?> object){
            this.objectToIdentifier.put(object, object.getID());
            this.entries.add(Pair.of(object.getID(), object));
        }

        @Override
        public ResourceLocation getIdentifier(IConditionSerializer<?> object){
            return this.objectToIdentifier.get(object);
        }

        @Override
        public boolean hasIdentifier(ResourceLocation identifier){
            return this.identifierToObject.containsKey(identifier);
        }

        @Override
        public IConditionSerializer<?> getValue(ResourceLocation identifier){
            return this.identifierToObject.get(identifier);
        }

        @Override
        public Set<ResourceLocation> getIdentifiers(){
            return Collections.unmodifiableSet(this.identifierToObject.keySet());
        }

        @Override
        public Collection<IConditionSerializer<?>> getValues(){
            return Collections.unmodifiableCollection(this.objectToIdentifier.keySet());
        }

        @Override
        public Set<Pair<ResourceLocation,IConditionSerializer<?>>> getEntries(){
            return Collections.unmodifiableSet(this.entries);
        }

        @Override
        public Class<IConditionSerializer<?>> getValueClass(){
            return this.valueClass;
        }
    }

    private static class MapBackedRegistry<T> implements Registry<T> {

        private final ResourceLocation identifier;
        private final Map<ResourceLocation,T> identifierToObject = new HashMap<>();
        private final Map<T,ResourceLocation> objectToIdentifier = new HashMap<>();
        private final Set<Pair<ResourceLocation,T>> entries = new HashSet<>();
        private final Class<T> valueClass;

        private MapBackedRegistry(ResourceLocation identifier, Class<? super T> valueClass){
            this.identifier = identifier;
            //noinspection unchecked
            this.valueClass = (Class<T>)valueClass;
        }

        @Override
        public ResourceLocation getRegistryIdentifier(){
            return this.identifier;
        }

        @Nullable
        @Override
        public net.minecraft.util.registry.Registry<T> getVanillaRegistry(){
            return null;
        }

        @Override
        public boolean hasVanillaRegistry(){
            return false;
        }

        @Nullable
        @Override
        public <X extends IForgeRegistryEntry<X>> IForgeRegistry<X> getForgeRegistry(){
            return null;
        }

        @Override
        public boolean hasForgeRegistry(){
            return false;
        }

        @Override
        public void register(ResourceLocation identifier, T object){
            if(this.identifierToObject.containsKey(identifier))
                throw new RuntimeException("Duplicate registry for identifier '" + identifier + "'!");
            if(this.objectToIdentifier.containsKey(object))
                throw new RuntimeException("Duplicate registry for object under '" + this.objectToIdentifier.get(object) + "' and '" + identifier + "'!");

            this.identifierToObject.put(identifier, object);
            this.objectToIdentifier.put(object, identifier);
            this.entries.add(Pair.of(identifier, object));
        }

        @Override
        public ResourceLocation getIdentifier(T object){
            return this.objectToIdentifier.get(object);
        }

        @Override
        public boolean hasIdentifier(ResourceLocation identifier){
            return this.identifierToObject.containsKey(identifier);
        }

        @Override
        public T getValue(ResourceLocation identifier){
            return this.identifierToObject.get(identifier);
        }

        @Override
        public Set<ResourceLocation> getIdentifiers(){
            return Collections.unmodifiableSet(this.identifierToObject.keySet());
        }

        @Override
        public Collection<T> getValues(){
            return Collections.unmodifiableCollection(this.objectToIdentifier.keySet());
        }

        @Override
        public Set<Pair<ResourceLocation,T>> getEntries(){
            return Collections.unmodifiableSet(this.entries);
        }

        @Override
        public Class<T> getValueClass(){
            return this.valueClass;
        }
    }
}
```

### src/main/java/com/supermartijn642/core/registry/RegistryEntryAcceptor.java

```java
package com.supermartijn642.core.registry;

import com.supermartijn642.core.CoreLib;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLModIdMappingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.moddiscovery.ModAnnotation;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.objectweb.asm.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface RegistryEntryAcceptor {

    String namespace();

    String identifier();

    Registry registry();

    enum Registry {
        BLOCKS(Registries.BLOCKS),
        FLUIDS(Registries.FLUIDS),
        ITEMS(Registries.ITEMS),
        MOB_EFFECTS(Registries.MOB_EFFECTS),
        SOUND_EVENTS(Registries.SOUND_EVENTS),
        POTIONS(Registries.POTIONS),
        ENCHANTMENTS(Registries.ENCHANTMENTS),
        ENTITY_TYPES(Registries.ENTITY_TYPES),
        BLOCK_ENTITY_TYPES(Registries.BLOCK_ENTITY_TYPES),
        PARTICLE_TYPES(Registries.PARTICLE_TYPES),
        MENU_TYPES(Registries.MENU_TYPES),
        PAINTING_VARIANTS(Registries.PAINTING_VARIANTS),
        RECIPE_SERIALIZERS(Registries.RECIPE_SERIALIZERS),
        ATTRIBUTES(Registries.ATTRIBUTES),
        STAT_TYPES(Registries.STAT_TYPES),
        RECIPE_CONDITION_SERIALIZERS(Registries.RECIPE_CONDITION_SERIALIZERS);

        public final Registries.Registry<?> registry;

        Registry(Registries.Registry<?> registry){
            this.registry = registry;
        }
    }

    class Handler {

        private static final Type TYPE = Type.getType(RegistryEntryAcceptor.class);

        private static final Map<Registries.Registry<?>,Map<ResourceLocation,Set<Field>>> FIELDS = new HashMap<>();
        private static final Map<Registries.Registry<?>,Map<ResourceLocation,Set<Method>>> METHODS = new HashMap<>();

        public static void gatherAnnotatedFields(){
            for(ModFileScanData scanData : ModList.get().getAllScanData()){
                for(ModFileScanData.AnnotationData annotationData : scanData.getAnnotations()){
                    // Skip other annotations
                    if(!TYPE.equals(annotationData.getAnnotationType()))
                        continue;

                    try{
                        String namespace = (String)annotationData.getAnnotationData().get("namespace");
                        if(!RegistryUtil.isValidNamespace(namespace))
                            throw new IllegalArgumentException("Namespace '" + namespace + "' must only contain characters [a-z0-9_.-]!");
                        String identifier = (String)annotationData.getAnnotationData().get("identifier");
                        if(!RegistryUtil.isValidPath(identifier))
                            throw new IllegalArgumentException("Identifier '" + identifier + "' must only contain characters [a-z0-9_./-]!");

                        Registry registry = Registry.valueOf(((ModAnnotation.EnumHolder)annotationData.getAnnotationData().get("registry")).getValue());

                        // Get the class the annotation is located in
                        Class<?> clazz = Class.forName(annotationData.getClassType().getClassName(), false, RegistryEntryAcceptor.class.getClassLoader());

                        // Now get the targeted field or method
                        if(annotationData.getTargetType().equals(ElementType.FIELD)){
                            Field field = clazz.getDeclaredField(annotationData.getMemberName());

                            // Check if the field is static
                            if(!Modifier.isStatic(field.getModifiers()))
                                throw new RuntimeException("Field must be static!");
                            // Check if the field is non-final
                            if(Modifier.isFinal(field.getModifiers()))
                                throw new RuntimeException("Field must not be final!");
                            // Check if the field has the correct type
                            if(!registry.registry.getValueClass().isAssignableFrom(field.getType()))
                                throw new RuntimeException("Field must have a type assignable from '" + registry.registry.getValueClass().getName() + "'!");

                            // Make the field accessible
                            field.setAccessible(true);

                            // Add the field
                            FIELDS.computeIfAbsent(registry.registry, o -> new HashMap<>())
                                .computeIfAbsent(new ResourceLocation(namespace, identifier), o -> new HashSet<>())
                                .add(field);
                        }else if(annotationData.getTargetType().equals(ElementType.METHOD)){
                            Method method = clazz.getDeclaredMethod(annotationData.getMemberName());

                            // Check if the method is static
                            if(!Modifier.isStatic(method.getModifiers()))
                                throw new RuntimeException("Method must be static!");
                            // Check if the method has exactly one parameter
                            if(method.getParameterCount() != 1)
                                throw new RuntimeException("Method must have exactly 1 parameter!");
                            // Check if the parameter has the correct type
                            if(!registry.registry.getValueClass().isAssignableFrom(method.getParameterTypes()[0]))
                                throw new RuntimeException("Method parameter must have a type assignable from '" + registry.registry.getValueClass().getName() + "'!");

                            // Make the method accessible
                            method.setAccessible(true);

                            // Add the method
                            METHODS.computeIfAbsent(registry.registry, o -> new HashMap<>())
                                .computeIfAbsent(new ResourceLocation(namespace, identifier), o -> new HashSet<>())
                                .add(method);
                        }else
                            throw new RuntimeException("@RegistryEntryAcceptor only supports field and method targets!");
                    }catch(Exception e){
                        throw new RuntimeException("Failed to register @RegistryEntryAcceptor annotation target '" + annotationData.getMemberName() + "' in '" + annotationData.getClassType().getClassName() + "'!", e);
                    }
                }
            }

            // Register event listeners
            FMLJavaModLoadingContext.get().getModEventBus().addListener(Handler::onIdRemapping);
        }

        public static void onRegisterEvent(RegistryEvent.Register<?> e){
            Registries.Registry<?> registry = Registries.fromUnderlying(e.getRegistry());
            if(registry == null)
                return;

            applyToFields(registry);
            applyToMethods(registry);

            for(Registries.Registry<?> otherRegistry : Registries.REGISTRATION_ORDER_MAP.getOrDefault(registry, Collections.emptyList())){
                applyToFields(otherRegistry);
                applyToMethods(otherRegistry);
            }
        }

        public static void onIdRemapping(FMLModIdMappingEvent e){
            FIELDS.keySet().forEach(Handler::applyToFields);
            METHODS.keySet().forEach(Handler::applyToMethods);
        }

        private static <T> void applyToFields(Registries.Registry<T> registry){
            if(registry == null || !FIELDS.containsKey(registry))
                return;

            for(Map.Entry<ResourceLocation,Set<Field>> entry : FIELDS.get(registry).entrySet()){
                // Skip if no value is registered with the identifier
                if(!registry.hasIdentifier(entry.getKey())){
                    CoreLib.LOGGER.warn("Could not find value '" + entry.getKey() + "' in registry '" + registry.getRegistryIdentifier() + "' for @RegistryEntryAcceptor!");
                    continue;
                }

                // Get the value
                T value = registry.getValue(entry.getKey());
                // Apply the value to all fields
                for(Field field : entry.getValue()){
                    // Check if the value can be assigned to the field
                    if(!field.getType().isAssignableFrom(value.getClass())){
                        CoreLib.LOGGER.warn("@RegistryEntryAcceptor field '" + field.getDeclaringClass().getName() + "." + field.getName() + "' for '" + entry.getKey() + "' could not be assigned value of type '" + value.getClass() + "'.");
                        continue;
                    }
                    // Set the field's value
                    try{
                        field.set(null, value);
                    }catch(IllegalAccessException e){
                        CoreLib.LOGGER.error("Encountered an error when trying to apply @RegistryEntryAcceptor annotation on field '" + field.getDeclaringClass().getName() + "." + field.getName() + "'!", e);
                    }
                }
            }
        }

        private static <T> void applyToMethods(Registries.Registry<T> registry){
            if(registry == null || !METHODS.containsKey(registry))
                return;

            for(Map.Entry<ResourceLocation,Set<Method>> entry : METHODS.get(registry).entrySet()){
                // Skip if no value is registered with the identifier
                if(!registry.hasIdentifier(entry.getKey())){
                    CoreLib.LOGGER.warn("Could not find value '" + entry.getKey() + "' in registry '" + registry.getRegistryIdentifier() + "' for @RegistryEntryAcceptor!");
                    continue;
                }

                // Get the value
                T value = registry.getValue(entry.getKey());
                // Apply the value to all methods
                for(Method method : entry.getValue()){
                    // Check if the value can be passed to the method
                    if(!method.getParameterTypes()[0].isAssignableFrom(value.getClass())){
                        CoreLib.LOGGER.warn("@RegistryEntryAcceptor method '" + method.getDeclaringClass().getName() + "." + method.getName() + "' for '" + entry.getKey() + "' could not be assigned value of type '" + value.getClass() + "'.");
                        continue;
                    }
                    // Set the method's value
                    try{
                        method.invoke(null, value);
                    }catch(InvocationTargetException |
                           IllegalAccessException e){
                        CoreLib.LOGGER.error("Encountered an error when trying to apply @RegistryEntryAcceptor annotation on method '" + method.getDeclaringClass().getName() + "." + method.getName() + "'!", e);
                    }
                }
            }
        }
    }
}
```

### src/main/java/com/supermartijn642/core/registry/RegistryUtil.java

```java
package com.supermartijn642.core.registry;

/**
 * Created 14/07/2022 by SuperMartijn642
 */
public class RegistryUtil {

    /**
     * Checks whether the given namespace contains illegal characters
     * @param namespace namespace to be checked
     * @return {@code true} if the namespace is valid
     */
    public static boolean isValidNamespace(String namespace){
        return namespace != null && namespace.length() > 0 && namespace.matches("[a-z0-9_.-]*");
    }

    /**
     * Checks whether the given path contains illegal characters
     * @param path identifier path to be checked
     * @return {@code true} if the path is valid
     */
    public static boolean isValidPath(String path){
        return path != null && path.length() > 0 && path.matches("[a-z0-9_./-]*");
    }

    /**
     * Checks whether given identifier contains illegal characters
     * @param namespace identifier namespace to be checked
     * @param path      identifier path to be checked
     * @return {@code true} if the identifier is valid
     */
    public static boolean isValidIdentifier(String namespace, String path){
        return isValidNamespace(namespace) && isValidPath(path);
    }

    /**
     * Checks whether given identifier contains illegal characters
     * @param identifier identifier to be checked
     * @return {@code true} if the identifier is valid
     */
    public static boolean isValidIdentifier(String identifier){
        String[] parts = identifier.split(":");
        return (parts.length == 1 && isValidPath(parts[0])) || (parts.length == 2 && isValidIdentifier(parts[0], parts[1]));
    }
}
```

### src/main/java/com/supermartijn642/core/render/BlockEntityCustomItemRenderer.java

```java
package com.supermartijn642.core.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.supermartijn642.core.ClientUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Created 24/07/2022 by SuperMartijn642
 */
public class BlockEntityCustomItemRenderer<T extends TileEntity> implements CustomItemRenderer {

    protected final boolean renderItemModel;
    protected final Supplier<T> initEntity;
    protected final BiConsumer<ItemStack,T> entityUpdater;
    protected T blockEntity;

    public BlockEntityCustomItemRenderer(boolean renderItemModel, Supplier<T> initEntity, BiConsumer<ItemStack,T> entityUpdater){
        this.renderItemModel = renderItemModel;
        this.initEntity = initEntity;
        this.entityUpdater = entityUpdater;
    }

    @Override
    public void render(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, MatrixStack poseStack, IRenderTypeBuffer bufferSource, int combinedLight, int combinedOverlay){
        if(this.blockEntity == null){
            this.blockEntity = this.initEntity.get();
            if(this.blockEntity == null)
                throw new RuntimeException("Init entity function must not return a null block entity!");
        }
        this.entityUpdater.accept(itemStack, this.blockEntity);

        if(this.renderItemModel)
            this.renderDefaultModel(itemStack, transformType, poseStack, bufferSource, combinedLight, combinedOverlay);
        TileEntityRendererDispatcher.instance.renderItem(this.blockEntity, poseStack, bufferSource, combinedLight, combinedOverlay);
    }

    /**
     * Renders the baked model corresponding to the given item stack. Ignores any custom renderers associated with the item.
     */
    protected void renderDefaultModel(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, MatrixStack poseStack, IRenderTypeBuffer bufferSource, int combinedLight, int combinedOverlay){
        if(itemStack.isEmpty())
            return;

        ItemRenderer itemRenderer = ClientUtils.getItemRenderer();
        IBakedModel model = itemRenderer.getModel(itemStack, null, null);

        boolean fabulous;
        if(transformType != ItemCameraTransforms.TransformType.GUI && !transformType.firstPerson() && itemStack.getItem() instanceof BlockItem){
            Block block = ((BlockItem)itemStack.getItem()).getBlock();
            fabulous = !(block instanceof BreakableBlock) && !(block instanceof StainedGlassPaneBlock);
        }else
            fabulous = true;

        if(model.isLayered())
            ForgeHooksClient.drawItemLayered(itemRenderer, model, itemStack, poseStack, bufferSource, combinedLight, combinedOverlay, fabulous);
        else{
            RenderType renderType = RenderTypeLookup.getRenderType(itemStack, fabulous);
            IVertexBuilder vertexConsumer = fabulous ?
                ItemRenderer.getFoilBufferDirect(bufferSource, renderType, true, itemStack.hasFoil()) :
                ItemRenderer.getFoilBuffer(bufferSource, renderType, true, itemStack.hasFoil());
            itemRenderer.renderModelLists(model, itemStack, combinedLight, combinedOverlay, poseStack, vertexConsumer);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/render/CustomBlockEntityRenderer.java

```java
package com.supermartijn642.core.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created 27/07/2022 by SuperMartijn642
 */
public interface CustomBlockEntityRenderer<T extends TileEntity> {

    static <T extends TileEntity> TileEntityRenderer<T> of(CustomBlockEntityRenderer<T> customRenderer){
        return new TileEntityRenderer<T>(null) {
            @Override
            public void render(T entity, float partialTicks, MatrixStack poseStack, IRenderTypeBuffer bufferSource, int combinedLight, int combinedOverlay){
                customRenderer.render(entity, partialTicks, poseStack, bufferSource, combinedLight, combinedOverlay);
            }
        };
    }

    /**
     * Renders the given block entity.
     */
    void render(T entity, float partialTicks, MatrixStack poseStack, IRenderTypeBuffer bufferSource, int combinedLight, int combinedOverlay);
}
```

### src/main/java/com/supermartijn642/core/render/CustomItemRenderer.java

```java
package com.supermartijn642.core.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;

/**
 * Created 27/07/2022 by SuperMartijn642
 */
public interface CustomItemRenderer {

    static ItemStackTileEntityRenderer of(CustomItemRenderer customRenderer){
        return new ItemStackTileEntityRenderer() {
            @Override
            public void renderByItem(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, MatrixStack poseStack, IRenderTypeBuffer bufferSource, int combinedLight, int combinedOverlay){
                customRenderer.render(itemStack, transformType, poseStack, bufferSource, combinedLight, combinedOverlay);
            }
        };
    }

    /**
     * Renders the given item stack.
     */
    void render(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, MatrixStack poseStack, IRenderTypeBuffer bufferSource, int combinedLight, int combinedOverlay);
}
```

### src/main/java/com/supermartijn642/core/render/CustomRendererBakedModelWrapper.java

```java
package com.supermartijn642.core.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraftforge.client.model.BakedModelWrapper;

/**
 * Created 25/07/2022 by SuperMartijn642
 */
public final class CustomRendererBakedModelWrapper extends BakedModelWrapper<IBakedModel> {

    public static IBakedModel wrap(IBakedModel originalModel){
        return new CustomRendererBakedModelWrapper(originalModel);
    }

    private CustomRendererBakedModelWrapper(IBakedModel originalModel){
        super(originalModel);
    }

    @Override
    public boolean isCustomRenderer(){
        return true;
    }

    @Override
    public IBakedModel handlePerspective(ItemCameraTransforms.TransformType cameraTransformType, MatrixStack poseStack){
        super.handlePerspective(cameraTransformType, poseStack);
        return this;
    }
}
```

### src/main/java/com/supermartijn642/core/render/RenderConfiguration.java

```java
package com.supermartijn642.core.render;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.lwjgl.opengl.GL11;

/**
 * Created 23/07/2022 by SuperMartijn642
 */
public class RenderConfiguration extends RenderType {

    public static RenderConfiguration create(String modid, String name, VertexFormat format, PrimitiveType primitive, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, RenderStateConfiguration renderStateConfiguration){
        return new RenderConfiguration(modid + ":" + name, format, primitive.getGlMode(), bufferSize, affectsCrumbling, sortOnUpload, renderStateConfiguration::setup, renderStateConfiguration::clear);
    }

    public static RenderConfiguration wrap(RenderType renderType){
        if(renderType instanceof RenderConfiguration)
            return (RenderConfiguration)renderType;
        return new RenderConfiguration(renderType.toString(), renderType.format(), renderType.mode(), renderType.bufferSize(), renderType.affectsCrumbling(), renderType.sortOnUpload, renderType::setupRenderState, renderType::clearRenderState);
    }

    private RenderConfiguration(String name, VertexFormat format, int glMode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setup, Runnable clear){
        super(name, format, glMode, bufferSize, affectsCrumbling, sortOnUpload, setup, clear);
    }

    public void setupState(){
        super.setupRenderState();
    }

    public void clearState(){
        super.clearRenderState();
    }

    public IVertexBuilder begin(IRenderTypeBuffer bufferSource){
        return bufferSource.getBuffer(this);
    }

    public void end(IRenderTypeBuffer.Impl bufferSource){
        bufferSource.endBatch(this);
    }

    public enum PrimitiveType {
        LINES(GL11.GL_LINES, 2, 2, false),
        LINE_STRIP(GL11.GL_LINE_STRIP, 2, 1, true),
        TRIANGLE_LINES(GL11.GL_TRIANGLES, 2, 2, false),
        TRIANGLE_LINE_STRIP(GL11.GL_TRIANGLE_STRIP, 2, 1, true),
        TRIANGLES(GL11.GL_TRIANGLES, 3, 3, false),
        TRIANGLE_STRIP(GL11.GL_TRIANGLE_STRIP, 3, 1, true),
        TRIANGLE_FAN(GL11.GL_TRIANGLE_FAN, 3, 1, true),
        QUADS(GL11.GL_QUADS, 4, 4, false);

        private final int glMode;
        private final int vertexCount;
        private final int vertexOffset;
        private final boolean isConnected;

        PrimitiveType(int glMode, int vertexCount, int vertexOffset, boolean isConnected){
            this.glMode = glMode;
            this.vertexCount = vertexCount;
            this.vertexOffset = vertexOffset;
            this.isConnected = isConnected;
        }

        public int getGlMode(){
            return this.glMode;
        }

        public int getVertexCount(){
            return this.vertexCount;
        }

        public int getVertexOffset(){
            return this.vertexOffset;
        }

        public boolean isConnected(){
            return this.isConnected;
        }
    }
}
```

### src/main/java/com/supermartijn642/core/render/RenderStateConfiguration.java

```java
package com.supermartijn642.core.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.supermartijn642.core.ClientUtils;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 23/07/2022 by SuperMartijn642
 */
public class RenderStateConfiguration {

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private RenderStateEntry textureState;
        private RenderStateEntry transparencyState;
        private RenderStateEntry depthTestState;
        private RenderStateEntry cullingState;
        private RenderStateEntry lightmapState;
        private RenderStateEntry overlayState;
        private RenderStateEntry layeringState;
        private RenderStateEntry depthMaskState;
        private RenderStateEntry colorMaskState;
        private RenderStateEntry lineWidthState;

        private final List<RenderStateEntry> entries = new ArrayList<>();

        private Builder(){
            this.disableTexture();
            this.disableTransparency();
            this.useLessThanOrEqualDepthTest();
            this.enableCulling();
            this.disableLightmap();
            this.disableOverlay();
            this.disableLayering();
            this.enableDepthMask();
            this.enableColorMask();
            this.useDefaultLineWidth();
        }

        public Builder append(RenderStateEntry entry){
            this.entries.add(entry);
            return this;
        }

        public Builder disableTexture(){
            this.textureState = new RenderStateEntry(RenderSystem::disableTexture, RenderSystem::enableTexture);
            return this;
        }

        public Builder useTexture(ResourceLocation texture, boolean useBlur, boolean useMipmap){
            this.textureState = new RenderStateEntry(() -> {
                RenderSystem.enableTexture();
                ClientUtils.getTextureManager().bind(texture);
                ClientUtils.getTextureManager().getTexture(texture).setFilter(useBlur, useMipmap);
            }, null);
            return this;
        }

        public Builder disableTransparency(){
            this.transparencyState = new RenderStateEntry(RenderSystem::disableBlend, null);
            return this;
        }

        public Builder useAdditiveTransparency(){
            this.transparencyState = new RenderStateEntry(() -> {
                RenderSystem.enableBlend();
                RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            }, () -> {
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
            });
            return this;
        }

        public Builder useTranslucentTransparency(){
            this.transparencyState = new RenderStateEntry(() -> {
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            }, () -> {
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
            });
            return this;
        }

        public Builder disableDepthTest(){
            this.depthTestState = new RenderStateEntry(RenderSystem::disableDepthTest, null);
            return this;
        }

        public Builder useEqualDepthTest(){
            this.depthTestState = new RenderStateEntry(() -> {
                RenderSystem.enableDepthTest();
                RenderSystem.depthFunc(GL11.GL_EQUAL);
            }, () -> {
                RenderSystem.disableDepthTest();
                RenderSystem.depthFunc(GL11.GL_LEQUAL);
            });
            return this;
        }

        public Builder useLessThanOrEqualDepthTest(){
            this.depthTestState = new RenderStateEntry(() -> {
                RenderSystem.enableDepthTest();
                RenderSystem.depthFunc(GL11.GL_LEQUAL);
            }, RenderSystem::disableDepthTest);
            return this;
        }

        public Builder disableCulling(){
            this.cullingState = new RenderStateEntry(RenderSystem::disableCull, RenderSystem::enableCull);
            return this;
        }

        public Builder enableCulling(){
            this.cullingState = new RenderStateEntry(RenderSystem::enableCull, null);
            return this;
        }

        public Builder disableLightmap(){
            this.lightmapState = new RenderStateEntry(() -> ClientUtils.getMinecraft().gameRenderer.lightTexture().turnOffLightLayer(), null);
            return this;
        }

        public Builder enableLightmap(){
            this.lightmapState = new RenderStateEntry(() -> ClientUtils.getMinecraft().gameRenderer.lightTexture().turnOnLightLayer(), () -> ClientUtils.getMinecraft().gameRenderer.lightTexture().turnOffLightLayer());
            return this;
        }

        public Builder disableOverlay(){
            this.overlayState = new RenderStateEntry(() -> ClientUtils.getMinecraft().gameRenderer.overlayTexture().teardownOverlayColor(), null);
            return this;
        }

        public Builder enableOverlay(){
            this.overlayState = new RenderStateEntry(() -> ClientUtils.getMinecraft().gameRenderer.overlayTexture().setupOverlayColor(), () -> ClientUtils.getMinecraft().gameRenderer.overlayTexture().teardownOverlayColor());
            return this;
        }

        public Builder disableLayering(){
            this.layeringState = new RenderStateEntry(RenderSystem::disablePolygonOffset, null);
            return this;
        }

        public Builder usePolygonOffsetLayering(){
            this.layeringState = new RenderStateEntry(() -> {
                RenderSystem.polygonOffset(-1.0F, -10.0F);
                RenderSystem.enablePolygonOffset();
            }, () -> {
                RenderSystem.polygonOffset(0.0F, 0.0F);
                RenderSystem.disablePolygonOffset();
            });
            return this;
        }

        public Builder useViewOffsetZLayering(){
            this.layeringState = new RenderStateEntry(() -> {
                RenderSystem.pushMatrix();
                RenderSystem.scalef(0.99975586F, 0.99975586F, 0.99975586F);
            }, RenderSystem::popMatrix);
            return this;
        }

        public Builder disableDepthMask(){
            this.depthMaskState = new RenderStateEntry(() -> RenderSystem.depthMask(false), () -> RenderSystem.depthMask(true));
            return this;
        }

        public Builder enableDepthMask(){
            this.depthMaskState = new RenderStateEntry(() -> RenderSystem.depthMask(true), null);
            return this;
        }

        public Builder disableColorMask(){
            this.colorMaskState = new RenderStateEntry(() -> RenderSystem.colorMask(false, false, false, false), () -> RenderSystem.colorMask(true, true, true, true));
            return this;
        }

        public Builder enableColorMask(){
            this.colorMaskState = new RenderStateEntry(() -> RenderSystem.colorMask(true, true, true, true), null);
            return this;
        }

        public Builder useColorMask(boolean writeRed, boolean writeGreen, boolean writeBlue, boolean writeAlpha){
            this.colorMaskState = new RenderStateEntry(() -> RenderSystem.colorMask(writeRed, writeGreen, writeBlue, writeAlpha), () -> RenderSystem.colorMask(true, true, true, true));
            return this;
        }

        public Builder useDefaultLineWidth(){
            this.lineWidthState = new RenderStateEntry(() -> RenderSystem.lineWidth(1), null);
            return this;
        }

        public Builder useLineWidth(float width){
            this.lineWidthState = new RenderStateEntry(() -> RenderSystem.lineWidth(width), () -> RenderSystem.lineWidth(1));
            return this;
        }

        public Builder useWindowRelativeLineWidth(){
            this.lineWidthState = new RenderStateEntry(() -> RenderSystem.lineWidth(Math.max(2.5F, ClientUtils.getMinecraft().getWindow().getWidth() / 1920f * 2.5f)), () -> RenderSystem.lineWidth(1));
            return this;
        }

        public RenderStateConfiguration build(){
            List<RenderStateEntry> combinedEntries = new ArrayList<>(11 + this.entries.size());
            combinedEntries.add(this.textureState);
            combinedEntries.add(this.transparencyState);
            combinedEntries.add(this.depthTestState);
            combinedEntries.add(this.cullingState);
            combinedEntries.add(this.lightmapState);
            combinedEntries.add(this.overlayState);
            combinedEntries.add(this.layeringState);
            combinedEntries.add(this.depthMaskState);
            combinedEntries.add(this.colorMaskState);
            combinedEntries.add(this.lineWidthState);
            combinedEntries.addAll(this.entries);
            return new RenderStateConfiguration(combinedEntries);
        }
    }

    public static final class RenderStateEntry {

        private final Runnable setup, clear;

        public RenderStateEntry(Runnable setup, Runnable clear){
            this.setup = setup == null ? () -> {
            } : setup;
            this.clear = clear == null ? () -> {
            } : clear;
        }

        /**
         * Initializes any OpenGl properties
         */
        public void setup(){
            this.setup.run();
        }

        /**
         * Resets any OpenGl properties set in {@link #setup()}
         */
        public void clear(){
            this.clear.run();
        }
    }

    private final List<RenderStateEntry> renderStateEntries;

    private RenderStateConfiguration(List<RenderStateEntry> renderStateEntries){
        this.renderStateEntries = renderStateEntries;
    }

    /**
     * Initializes any render states
     */
    public void setup(){
        this.renderStateEntries.forEach(RenderStateEntry::setup);
    }

    /**
     * Resets any render states
     */
    public void clear(){
        this.renderStateEntries.forEach(RenderStateEntry::clear);
    }
}
```

### src/main/java/com/supermartijn642/core/render/RenderUtils.java

```java
package com.supermartijn642.core.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.block.BlockShape;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;

/**
 * Created 6/12/2021 by SuperMartijn642
 */
public class RenderUtils {

    private static final RenderConfiguration LINES = RenderConfiguration.create(
        "supermartijn642corelib",
        "lines",
        DefaultVertexFormats.POSITION_COLOR,
        RenderConfiguration.PrimitiveType.LINES,
        128,
        true,
        true,
        RenderStateConfiguration.builder()
            .useDefaultLineWidth()
            .useTranslucentTransparency()
            .useViewOffsetZLayering()
            .disableCulling()
            .useLessThanOrEqualDepthTest()
            .disableDepthMask()
            .build()
    );
    private static final RenderConfiguration LINES_NO_DEPTH = RenderConfiguration.create(
        "supermartijn642corelib",
        "lines_no_depth",
        DefaultVertexFormats.POSITION_COLOR,
        RenderConfiguration.PrimitiveType.LINES,
        128,
        true,
        true,
        RenderStateConfiguration.builder()
            .useDefaultLineWidth()
            .useTranslucentTransparency()
            .useViewOffsetZLayering()
            .disableCulling()
            .disableDepthTest()
            .disableDepthMask()
            .build()
    );
    private static final RenderConfiguration QUADS = RenderConfiguration.create(
        "supermartijn642corelib",
        "quads",
        DefaultVertexFormats.POSITION_COLOR,
        RenderConfiguration.PrimitiveType.QUADS,
        256,
        false,
        true,
        RenderStateConfiguration.builder()
            .useTranslucentTransparency()
            .disableTexture()
            .disableCulling()
            .useLessThanOrEqualDepthTest()
            .disableDepthMask()
            .build()
    );
    private static final RenderConfiguration QUADS_NO_DEPTH = RenderConfiguration.create(
        "supermartijn642corelib",
        "quads_no_depth",
        DefaultVertexFormats.POSITION_COLOR,
        RenderConfiguration.PrimitiveType.QUADS,
        256,
        false,
        true,
        RenderStateConfiguration.builder()
            .useTranslucentTransparency()
            .disableTexture()
            .disableCulling()
            .disableDepthTest()
            .disableDepthMask()
            .build()
    );

    /**
     * @return the current interpolated camera position
     */
    public static Vector3d getCameraPosition(){
        return ClientUtils.getMinecraft().getEntityRenderDispatcher().camera.getPosition();
    }

    /**
     * @return the current interpolated camera position
     */
    public static IRenderTypeBuffer.Impl getMainBufferSource(){
        return ClientUtils.getMinecraft().renderBuffers().bufferSource();
    }

    /**
     * Draws an outline for the given shape
     */
    public static void renderShape(MatrixStack poseStack, BlockShape shape, float red, float green, float blue, float alpha, boolean depthTest){
        RenderConfiguration renderConfiguration = depthTest ? LINES : LINES_NO_DEPTH;
        IRenderTypeBuffer.Impl bufferSource = getMainBufferSource();
        IVertexBuilder builder = renderConfiguration.begin(bufferSource);
        Matrix4f matrix4f = poseStack.last().pose();
        shape.forEachEdge((x1, y1, z1, x2, y2, z2) -> {
            builder.vertex(matrix4f, (float)x1, (float)y1, (float)z1).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix4f, (float)x2, (float)y2, (float)z2).color(red, green, blue, alpha).endVertex();
        });
        renderConfiguration.end(bufferSource);
    }

    /**
     * Draws the sides of the given shape
     */
    public static void renderShapeSides(MatrixStack poseStack, BlockShape shape, float red, float green, float blue, float alpha, boolean depthTest){
        RenderConfiguration renderConfiguration = depthTest ? QUADS : QUADS_NO_DEPTH;
        IRenderTypeBuffer.Impl bufferSource = getMainBufferSource();
        IVertexBuilder builder = renderConfiguration.begin(bufferSource);
        Matrix4f matrix = poseStack.last().pose();
        shape.forEachBox(box -> {
            float minX = (float)box.minX, maxX = (float)box.maxX;
            float minY = (float)box.minY, maxY = (float)box.maxY;
            float minZ = (float)box.minZ, maxZ = (float)box.maxZ;

            builder.vertex(matrix, minX, minY, minZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, minY, minZ).color(red, green, blue, alpha).endVertex();

            builder.vertex(matrix, minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();


            builder.vertex(matrix, minX, minY, minZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, minX, minY, maxZ).color(red, green, blue, alpha).endVertex();

            builder.vertex(matrix, minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();


            builder.vertex(matrix, minX, minY, minZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, minX, maxY, minZ).color(red, green, blue, alpha).endVertex();

            builder.vertex(matrix, maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
            builder.vertex(matrix, maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        });
        renderConfiguration.end(bufferSource);
    }

    /**
     * Draws an outline for the given shape
     */
    public static void renderShape(MatrixStack poseStack, VoxelShape shape, float red, float green, float blue, float alpha, boolean depthTest){
        renderShape(poseStack, BlockShape.create(shape), red, green, blue, alpha, depthTest);
    }

    /**
     * Draws the sides of the given shape
     */
    public static void renderShapeSides(MatrixStack poseStack, VoxelShape shape, float red, float green, float blue, float alpha, boolean depthTest){
        renderShapeSides(poseStack, BlockShape.create(shape), red, green, blue, alpha, depthTest);
    }

    /**
     * Draws an outline for the given box
     */
    public static void renderBox(MatrixStack poseStack, AxisAlignedBB box, float red, float green, float blue, float alpha, boolean depthTest){
        renderShape(poseStack, BlockShape.create(box), red, green, blue, alpha, depthTest);
    }

    /**
     * Draws the sides of the given box
     */
    public static void renderBoxSides(MatrixStack poseStack, AxisAlignedBB box, float red, float green, float blue, float alpha, boolean depthTest){
        renderShapeSides(poseStack, BlockShape.create(box), red, green, blue, alpha, depthTest);
    }

    /**
     * Draws an outline for the given shape
     */
    public static void renderShape(MatrixStack poseStack, BlockShape shape, float red, float green, float blue, boolean depthTest){
        renderShape(poseStack, shape, red, green, blue, 1, depthTest);
    }

    /**
     * Draws the sides of the given shape
     */
    public static void renderShapeSides(MatrixStack poseStack, BlockShape shape, float red, float green, float blue, boolean depthTest){
        renderShapeSides(poseStack, shape, red, green, blue, 1, depthTest);
    }

    /**
     * Draws an outline for the given shape
     */
    public static void renderShape(MatrixStack poseStack, VoxelShape shape, float red, float green, float blue, boolean depthTest){
        renderShape(poseStack, BlockShape.create(shape), red, green, blue, 1, depthTest);
    }

    /**
     * Draws the sides of the given shape
     */
    public static void renderShapeSides(MatrixStack poseStack, VoxelShape shape, float red, float green, float blue, boolean depthTest){
        renderShapeSides(poseStack, BlockShape.create(shape), red, green, blue, 1, depthTest);
    }

    /**
     * Draws an outline for the given box
     */
    public static void renderBox(MatrixStack poseStack, AxisAlignedBB box, float red, float green, float blue, boolean depthTest){
        renderShape(poseStack, BlockShape.create(box), red, green, blue, 1, depthTest);
    }

    /**
     * Draws the sides of the given box
     */
    public static void renderBoxSides(MatrixStack poseStack, AxisAlignedBB box, float red, float green, float blue, boolean depthTest){
        renderShapeSides(poseStack, BlockShape.create(box), red, green, blue, 1, depthTest);
    }
}
```

### src/main/java/com/supermartijn642/core/render/RenderWorldEvent.java

```java
package com.supermartijn642.core.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.eventbus.api.Event;

/**
 * Created 17/11/2021 by SuperMartijn642
 * <p>
 * Fired right after blocks are rendered and the {@link net.minecraftforge.client.event.DrawHighlightEvent} is fired.
 */
public class RenderWorldEvent extends Event {

    private final MatrixStack poseStack;
    private final float partialTicks;

    public RenderWorldEvent(MatrixStack poseStack, float partialTicks){
        this.poseStack = poseStack;
        this.partialTicks = partialTicks;
    }

    public MatrixStack getPoseStack(){
        return this.poseStack;
    }

    public float getPartialTicks(){
        return this.partialTicks;
    }
}
```

### src/main/java/com/supermartijn642/core/render/TextureAtlases.java

```java
package com.supermartijn642.core.render;


import net.minecraft.util.ResourceLocation;

/**
 * Created 23/07/2022 by SuperMartijn642
 */
public class TextureAtlases {

    private static final ResourceLocation BLOCKS = new ResourceLocation("textures/atlas/blocks.png");
    private static final ResourceLocation PARTICLES = new ResourceLocation("textures/atlas/particles.png");
    private static final ResourceLocation MOB_EFFECTS = new ResourceLocation("textures/atlas/mob_effects.png");
    private static final ResourceLocation PAINTINGS = new ResourceLocation("textures/atlas/paintings.png");
    private static final ResourceLocation SHULKER_BOXES = new ResourceLocation("textures/atlas/shulker_boxes.png");
    private static final ResourceLocation BEDS = new ResourceLocation("textures/atlas/beds.png");
    private static final ResourceLocation BANNERS = new ResourceLocation("textures/atlas/banner_patterns.png");
    private static final ResourceLocation SHIELDS = new ResourceLocation("textures/atlas/shield_patterns.png");
    private static final ResourceLocation SIGNS = new ResourceLocation("textures/atlas/signs.png");
    private static final ResourceLocation CHESTS = new ResourceLocation("textures/atlas/chest.png");

    public static ResourceLocation getBlocks(){
        return BLOCKS;
    }

    public static ResourceLocation getParticles(){
        return PARTICLES;
    }

    public static ResourceLocation getMobEffects(){
        return MOB_EFFECTS;
    }

    public static ResourceLocation getPaintings(){
        return PAINTINGS;
    }

    public static ResourceLocation getShulkerBoxes(){
        return SHULKER_BOXES;
    }

    public static ResourceLocation getBeds(){
        return BEDS;
    }

    public static ResourceLocation getBanners(){
        return BANNERS;
    }

    public static ResourceLocation getShields(){
        return SHIELDS;
    }

    public static ResourceLocation getSigns(){
        return SIGNS;
    }

    public static ResourceLocation getChests(){
        return CHESTS;
    }
}
```

### src/main/java/com/supermartijn642/core/TextComponents.java

```java
package com.supermartijn642.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Function;

/**
 * Created 6/9/2021 by SuperMartijn642
 */
public class TextComponents {

    /**
     * Creates a new empty {@link TextComponentBuilder}.
     */
    public static TextComponentBuilder empty(){
        return string("");
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given {@code text}.
     */
    public static TextComponentBuilder string(String text){
        return new TextComponentBuilder(new StringTextComponent(text));
    }

    /**
     * Creates a new {@link TextComponentBuilder} for the given {@code number}.
     */
    public static TextComponentBuilder number(int number){
        return new TextComponentBuilder(new StringTextComponent(Integer.toString(number)));
    }

    /**
     * Creates a new {@link TextComponentBuilder} for the given {@code number}.
     */
    public static TextComponentBuilder number(double number, int decimals){
        return new TextComponentBuilder(new StringTextComponent(String.format("%." + decimals + "f", number)));
    }

    /**
     * Creates a new {@link TextComponentBuilder} for the given {@code number}.
     */
    public static TextComponentBuilder number(double number){
        return new TextComponentBuilder(new StringTextComponent(Double.toString(number)));
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given translation.
     */
    public static TextComponentBuilder translation(String translationKey, Object... arguments){
        return new TextComponentBuilder(new TranslationTextComponent(translationKey, arguments));
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given translation.
     */
    public static TextComponentBuilder translation(String translationKey){
        return new TextComponentBuilder(new TranslationTextComponent(translationKey));
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given text component.
     */
    public static TextComponentBuilder fromTextComponent(IFormattableTextComponent textComponent){
        return new TextComponentBuilder(textComponent);
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given text component.
     */
    public static TextComponentBuilder fromTextComponent(ITextComponent textComponent){
        return fromTextComponent(textComponent.plainCopy());
    }

    /**
     * Formats the given text component. Must only be side client side.
     * @return the formatted string
     */
    public static String format(ITextComponent textComponent){
        return textComponent.getString();
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given block's name.
     */
    public static TextComponentBuilder block(Block block){
        return translation(block.getDescriptionId());
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given block state's
     * name.
     */
    public static TextComponentBuilder blockState(BlockState state){
        return block(state.getBlock());
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given item's name.
     */
    public static TextComponentBuilder item(Item item){
        return translation(item.getDescriptionId());
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given item stack's
     * display name. The display name includes any custom name.
     */
    public static TextComponentBuilder itemStack(ItemStack stack){
        return fromTextComponent(stack.getHoverName().plainCopy());
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given fluid's name.
     */
    public static TextComponentBuilder fluid(Fluid fluid){
        return translation(fluid.getAttributes().getTranslationKey());
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given fluid stack's
     * display name.
     */
    public static TextComponentBuilder fluidStack(FluidStack stack){
        return fromTextComponent(stack.getDisplayName().plainCopy());
    }

    /**
     * Creates a new {@link TextComponentBuilder} around the given entity's
     * display name. The display name includes custom names.
     */
    public static TextComponentBuilder entity(Entity entity){
        return fromTextComponent(entity.hasCustomName() ? entity.getCustomName() : entity.getDisplayName());
    }

    /**
     * Converts the dimension registry name to a capitalized name and creates a
     * new {@link TextComponentBuilder} around it.
     */
    public static TextComponentBuilder dimension(RegistryKey<World> dimension){
        String dimensionName = dimension.location().getPath();
        dimensionName = dimensionName.substring(Math.min(dimensionName.length() - 1, Math.max(0, dimensionName.indexOf('/') + 1))).toLowerCase();
        dimensionName = dimensionName.substring(0, 1).toUpperCase() + dimensionName.substring(1);
        for(int i = 0; i < dimensionName.length() - 1; i++)
            if(dimensionName.charAt(i) == '_' && Character.isAlphabetic(dimensionName.charAt(i + 1)))
                dimensionName = dimensionName.substring(0, i) + ' ' + (i + 2 < dimensionName.length() ? dimensionName.substring(i + 1, i + 2).toUpperCase() + dimensionName.substring(i + 2) : dimensionName.substring(i + 1).toUpperCase());
        return string(dimensionName);
    }

    /**
     * Converts the dimension registry name to a capitalized name and creates a
     * new {@link TextComponentBuilder} around it.
     */
    public static TextComponentBuilder dimension(World world){
        return dimension(world.dimension());
    }

    public static class TextComponentBuilder {

        private final TextComponentBuilder parent;
        private final IFormattableTextComponent textComponent;

        private TextComponentBuilder(IFormattableTextComponent textComponent, TextComponentBuilder parent){
            this.textComponent = textComponent;
            this.parent = parent;
        }

        private TextComponentBuilder(IFormattableTextComponent textComponent){
            this(textComponent, null);
        }

        /**
         * Sets the formatting for the text component.
         */
        public TextComponentBuilder formatting(TextFormatting color){
            this.updateStyle(style -> style.withColor(color));
            return this;
        }

        /**
         * Sets the formatting for the text component.
         */
        public TextComponentBuilder color(TextFormatting color){
            return this.formatting(color);
        }

        /**
         * Makes the text component <b>bold</b>.
         */
        public TextComponentBuilder bold(){
            this.updateStyle(style -> style.withBold(true));
            return this;
        }

        /**
         * Makes the text component <i>italic</i>.
         */
        public TextComponentBuilder italic(){
            this.updateStyle(style -> style.withItalic(true));
            return this;
        }

        /**
         * Makes the text component <u>underlined</u>.
         */
        public TextComponentBuilder underline(){
            this.updateStyle(style -> style.setUnderlined(true));
            return this;
        }

        /**
         * Makes the text component <s>strikethrough</s>.
         */
        public TextComponentBuilder strikethrough(){
            this.updateStyle(style -> style.setStrikethrough(true));
            return this;
        }

        /**
         * Makes the text component obfuscated, i.e. random characters.
         */
        public TextComponentBuilder obfuscate(){
            this.updateStyle(style -> style.setObfuscated(true));
            return this;
        }

        /**
         * Makes the text component's style.
         */
        public TextComponentBuilder reset(){
            this.updateStyle(style -> Style.EMPTY.withBold(false).withItalic(false).setUnderlined(false).setStrikethrough(false).setObfuscated(false));
            return this;
        }

        private void updateStyle(Function<Style,Style> updater){
            this.textComponent.setStyle(updater.apply(this.textComponent.getStyle()));
        }

        /**
         * Appends the given string to the text component and returns a new
         * {@link TextComponentBuilder} for the given string.
         * @return a new {@link TextComponentBuilder} for the given string
         */
        public TextComponentBuilder string(String text){
            return this.append(new StringTextComponent(text));
        }

        /**
         * Appends the given translation to the text component and returns a new
         * {@link TextComponentBuilder} for the given translation.
         * @return a new {@link TextComponentBuilder} for the given translation
         */
        public TextComponentBuilder translation(String translationKey, Object... arguments){
            return this.append(new TranslationTextComponent(translationKey, arguments));
        }

        /**
         * Appends the given translation to the text component and returns a new
         * {@link TextComponentBuilder} for the given translation.
         * @return a new {@link TextComponentBuilder} for the given translation
         */
        public TextComponentBuilder translation(String translationKey){
            return this.append(new TranslationTextComponent(translationKey));
        }

        /**
         * Appends the given translation to the text component and returns a new
         * {@link TextComponentBuilder} for the given translation.
         * @return a new {@link TextComponentBuilder} for the given text component
         */
        public TextComponentBuilder append(IFormattableTextComponent textComponent){
            this.textComponent.append(textComponent);
            return new TextComponentBuilder(textComponent, this);
        }

        /**
         * @return the constructed text component
         */
        public IFormattableTextComponent get(){
            return this.parent == null ? this.textComponent : this.parent.get();
        }

        /**
         * Formats the text component. Must only be used client side.
         * @return the constructed text component
         */
        public String format(){
            return TextComponents.format(this.get());
        }
    }

}
```

### src/main/java/com/supermartijn642/core/util/Either.java

```java
package com.supermartijn642.core.util;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created 09/09/2022 by SuperMartijn642
 */
public abstract class Either<X, Y> {

    /**
     * Creates a left either instance with the given value.
     */
    public static <X, Y> Either<X,Y> left(X object){
        return new Left<>(object);
    }

    /**
     * Creates a right either instance with the given value.
     */
    public static <X, Y> Either<X,Y> right(Y object){
        return new Right<>(object);
    }

    private Either(){
    }

    /**
     * Whether the either is a left value or not.
     */
    public abstract boolean isLeft();

    /**
     * Whether the either is a right value or not.
     */
    public abstract boolean isRight();

    /**
     * Will take this either as a left value.
     * @throws NoSuchElementException if the either is a right value
     */
    public abstract X left();

    /**
     * Will take this either as a right value.
     * @throws NoSuchElementException if the either is a left value
     */
    public abstract Y right();

    /**
     * Will take this either as a left value. If this either is a right value, the given alternative will be returned.
     */
    public abstract X leftOrElse(X other);

    /**
     * Will take this either as a right value. If this either is a left value, the given alternative will be returned.
     */
    public abstract Y rightOrElse(Y other);

    /**
     * Will take this either as a left value. If this either is a right value, the given alternative will be resolved and returned.
     */
    public abstract X leftOrElseGet(Supplier<X> other);

    /**
     * Will take this either as a right value. If this either is a left value, the given alternative will be resolved and returned.
     */
    public abstract Y rightOrElseGet(Supplier<Y> other);

    /**
     * Will take this either as a left value. If this either is a right value, {@code null} will be returned.
     */
    public X leftOrNull(){
        return this.leftOrElse(null);
    }

    /**
     * Will take this either as a right value. If this either is a left value, {@code null} will be returned.
     */
    public Y rightOrNull(){
        return this.rightOrElse(null);
    }

    /**
     * Applies the given mapper if this either is a left value.
     */
    public abstract <S> Either<S,Y> mapLeft(Function<X,S> mapper);

    /**
     * Applies the given mapper if this either is a right value.
     */
    public abstract <S> Either<X,S> mapRight(Function<Y,S> mapper);

    /**
     * Applies the respective mapper given whether this either is a left or right value.
     */
    public <R, S> Either<R,S> map(Function<X,R> mapLeft, Function<Y,S> mapRight){
        return this.mapLeft(mapLeft).mapRight(mapRight);
    }

    /**
     * Applies the respective mapper to obtain an object of type {@link S}.
     */
    public abstract <S> S flatMap(Function<X,S> mapLeft, Function<Y,S> mapRight);

    /**
     * Applies the given consumer if this either is a left value.
     */
    public abstract void ifLeft(Consumer<X> consumer);

    /**
     * Applies the given consumer if this either is a right value.
     */
    public abstract void ifRight(Consumer<Y> consumer);

    private static class Left<X, Y> extends Either<X,Y> {

        private final X value;

        private Left(X value){
            this.value = value;
        }

        @Override
        public boolean isLeft(){
            return true;
        }

        @Override
        public boolean isRight(){
            return false;
        }

        @Override
        public X left(){
            return this.value;
        }

        @Override
        public Y right(){
            throw new NoSuchElementException("Right value is not present!");
        }

        @Override
        public X leftOrElse(X other){
            return this.value;
        }

        @Override
        public Y rightOrElse(Y other){
            return other;
        }

        @Override
        public X leftOrElseGet(Supplier<X> other){
            return this.value;
        }

        @Override
        public Y rightOrElseGet(Supplier<Y> other){
            return other.get();
        }

        @Override
        public <S> Either<S,Y> mapLeft(Function<X,S> mapper){
            return new Left<>(mapper.apply(this.value));
        }

        @Override
        public <S> Either<X,S> mapRight(Function<Y,S> mapper){
            //noinspection unchecked
            return (Either<X,S>)this;
        }

        @Override
        public <S> S flatMap(Function<X,S> mapLeft, Function<Y,S> mapRight){
            return mapLeft.apply(this.value);
        }

        @Override
        public void ifLeft(Consumer<X> consumer){
            consumer.accept(this.value);
        }

        @Override
        public void ifRight(Consumer<Y> consumer){
        }
    }

    private static class Right<X, Y> extends Either<X,Y> {

        private final Y value;

        private Right(Y value){
            this.value = value;
        }

        @Override
        public boolean isLeft(){
            return false;
        }

        @Override
        public boolean isRight(){
            return true;
        }

        @Override
        public X left(){
            throw new NoSuchElementException("Left value is not present!");
        }

        @Override
        public Y right(){
            return this.value;
        }

        @Override
        public X leftOrElse(X other){
            return other;
        }

        @Override
        public Y rightOrElse(Y other){
            return this.value;
        }

        @Override
        public X leftOrElseGet(Supplier<X> other){
            return other.get();
        }

        @Override
        public Y rightOrElseGet(Supplier<Y> other){
            return this.value;
        }

        @Override
        public <S> Either<S,Y> mapLeft(Function<X,S> mapper){
            //noinspection unchecked
            return (Either<S,Y>)this;
        }

        @Override
        public <S> Either<X,S> mapRight(Function<Y,S> mapper){
            return new Right<>(mapper.apply(this.value));
        }

        @Override
        public <S> S flatMap(Function<X,S> mapLeft, Function<Y,S> mapRight){
            return mapRight.apply(this.value);
        }

        @Override
        public void ifLeft(Consumer<X> consumer){
        }

        @Override
        public void ifRight(Consumer<Y> consumer){
            consumer.accept(this.value);
        }
    }
}
```

### src/main/java/com/supermartijn642/core/util/Holder.java

```java
package com.supermartijn642.core.util;

/**
 * Simply holds an object.
 * <p>
 * Created 26/07/2022 by SuperMartijn642
 */
public class Holder<T> {

    private T object;

    public Holder(T object){
        this.object = object;
    }

    public Holder(){
    }

    /**
     * Sets the held object.
     * @param object object to be held
     */
    public void set(T object){
        this.object = object;
    }

    /**
     * Gets the held object.
     */
    public T get(){
        return this.object;
    }
}
```

### src/main/java/com/supermartijn642/core/util/MappedSetView.java

```java
package com.supermartijn642.core.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created 30/07/2022 by SuperMartijn642
 */
public class MappedSetView<R, T> implements Set<T> {

    public static <R, T> Set<T> map(Set<R> set, Function<R,T> mapper){
        return new MappedSetView<>(set, mapper);
    }

    private final Set<R> set;
    private final Function<R,T> mapper;

    private MappedSetView(Set<R> set, Function<R,T> mapper){
        this.set = set;
        this.mapper = mapper;
    }

    @Override
    public int size(){
        return this.set.size();
    }

    @Override
    public boolean isEmpty(){
        return this.set.isEmpty();
    }

    @Override
    public boolean contains(Object o){
        throw new UnsupportedOperationException("Mapped sets cannot be checked for contained elements!");
    }

    @Override
    public Iterator<T> iterator(){
        Iterator<R> iterator = this.set.iterator();
        return new Iterator<T>() {
            @Override
            public boolean hasNext(){
                return iterator.hasNext();
            }

            @Override
            public T next(){
                return MappedSetView.this.mapper.apply(iterator.next());
            }
        };
    }

    @Override
    public Object[] toArray(){
        return this.set.stream().map(this.mapper).toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a){
        return this.set.stream().map(this.mapper).collect(Collectors.toList()).toArray(a);
    }

    @Override
    public boolean add(T t){
        throw new UnsupportedOperationException("Mapped set views cannot be added to!");
    }

    @Override
    public boolean remove(Object o){
        throw new UnsupportedOperationException("Mapped set views cannot be removed from!");
    }

    @Override
    public boolean containsAll(Collection<?> c){
        throw new UnsupportedOperationException("Mapped sets cannot be checked for contained elements!");
    }

    @Override
    public boolean addAll(Collection<? extends T> c){
        throw new UnsupportedOperationException("Mapped set views cannot be added to!");
    }

    @Override
    public boolean retainAll(Collection<?> c){
        throw new UnsupportedOperationException("Mapped set views cannot be removed from!");
    }

    @Override
    public boolean removeAll(Collection<?> c){
        throw new UnsupportedOperationException("Mapped set views cannot be removed from!");
    }

    @Override
    public void clear(){
        throw new UnsupportedOperationException("Mapped set views cannot be removed from!");
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter){
        throw new UnsupportedOperationException("Mapped set views cannot be removed from!");
    }
}
```

### src/main/java/com/supermartijn642/core/util/Maybe.java

```java
package com.supermartijn642.core.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created 17/07/2022 by SuperMartijn642
 */
public abstract class Maybe<T> {

    private static final Maybe<?> EMPTY = new Empty();

    /**
     * Create a new maybe instance with a present object. The object may be {@code null}.
     * @param object the present object, may be {@code null}
     * @param <T>    type of the object
     * @return the created instance
     */
    public static <T> Maybe<T> of(T object){
        return new Present<>(object);
    }

    /**
     * Creates an empty maybe instance.
     * @param <T> type of the maybe object
     * @return the empty maybe instance
     */
    public static <T> Maybe<T> empty(){
        //noinspection unchecked
        return (Maybe<T>)EMPTY;
    }

    private Maybe(){
    }

    /**
     * Whether the maybe is present or not. Note that the maybe may be present even when its object is {@code null}.
     * @return whether the maybe is present
     */
    public abstract boolean isPresent();

    public abstract <S> Maybe<S> map(Function<T,S> mapper);

    /**
     * Returns the object held by this maybe. If this maybe is empty, this will throw an {@link IllegalStateException}.
     * @return the object held by this maybe
     * @throws IllegalStateException if the maybe is empty
     */
    public abstract T get() throws IllegalStateException;

    /**
     * Returns the object held by this maybe. If this maybe is empty, the given alternative will be returned.
     */
    public abstract T orElse(T other);

    /**
     * Returns the object held by this maybe. If this maybe is empty, the given alternative will be resolved and returned.
     */
    public abstract T orElseGet(Supplier<T> other);

    /**
     * Applies the given consumer if this maybe is present.
     */
    public abstract void ifPresent(Consumer<T> consumer);

    private static class Present<T> extends Maybe<T> {

        private final T object;

        private Present(T object){
            this.object = object;
        }

        @Override
        public boolean isPresent(){
            return true;
        }

        @Override
        public <S> Maybe<S> map(Function<T,S> mapper){
            return Maybe.of(mapper.apply(this.object));
        }

        @Override
        public T get(){
            return this.object;
        }

        @Override
        public T orElse(T other){
            return this.object;
        }

        @Override
        public T orElseGet(Supplier<T> other){
            return this.object;
        }

        @Override
        public void ifPresent(Consumer<T> consumer){
            consumer.accept(this.object);
        }
    }

    private static class Empty extends Maybe<Object> {

        @Override
        public boolean isPresent(){
            return false;
        }

        @Override
        public <S> Maybe<S> map(Function<Object,S> mapper){
            //noinspection unchecked
            return (Maybe<S>)this;
        }

        @Override
        public Object get(){
            throw new IllegalStateException("Cannot call get on an empty maybe!");
        }

        @Override
        public Object orElse(Object other){
            return other;
        }

        @Override
        public Object orElseGet(Supplier<Object> other){
            return other.get();
        }

        @Override
        public void ifPresent(Consumer<Object> consumer){
        }
    }
}
```

### src/main/java/com/supermartijn642/core/util/Pair.java

```java
package com.supermartijn642.core.util;

import com.google.common.base.Objects;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created 23/07/2022 by SuperMartijn642
 */
public class Pair<X, Y> {

    public static <X, Y> Pair<X,Y> of(X left, Y right){
        return new Pair<>(left, right);
    }

    private final X left;
    private final Y right;

    private Pair(X left, Y right){
        this.left = left;
        this.right = right;
    }

    public X left(){
        return this.left;
    }

    public Y right(){
        return this.right;
    }

    /**
     * Applies the given mapper to the left value.
     */
    public <S> Pair<S,Y> mapLeft(Function<X,S> mapper){
        return Pair.of(mapper.apply(this.left), this.right);
    }

    /**
     * Applies the given mapper to the right value.
     */
    public <S> Pair<X,S> mapRight(Function<Y,S> mapper){
        return Pair.of(this.left, mapper.apply(this.right));
    }

    /**
     * Applies the respective mapper to the left or right values.
     */
    public <R, S> Pair<R,S> map(Function<X,R> mapLeft, Function<Y,S> mapRight){
        return Pair.of(mapLeft.apply(this.left), mapRight.apply(this.right));
    }

    /**
     * Maps the values held by this pair to an object of type {@link S}.
     */
    public <S> S flatMap(BiFunction<X,Y,S> mapper){
        return mapper.apply(this.left, this.right);
    }

    /**
     * Applies the given consumer to the values held by this pair.
     */
    public void apply(BiConsumer<X,Y> consumer){
        consumer.accept(this.left, this.right);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Pair<?,?> pair = (Pair<?,?>)o;
        return Objects.equal(this.left, pair.left) && Objects.equal(this.right, pair.right);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.left, this.right);
    }
}
```

### src/main/java/com/supermartijn642/core/util/TriFunction.java

```java
package com.supermartijn642.core.util;

import java.util.Objects;
import java.util.function.Function;

/**
 * Created 16/08/2022 by SuperMartijn642
 */
@FunctionalInterface
public interface TriFunction<R, S, T, U> {

    U apply(R r, S s, T t);

    default <V> TriFunction<R,S,T,V> andThen(Function<? super U,? extends V> after){
        Objects.requireNonNull(after);
        return (r, s, t) -> after.apply(this.apply(r, s, t));
    }
}
```

### src/main/java/com/supermartijn642/core/util/Triple.java

```java
package com.supermartijn642.core.util;

/**
 * Created 23/07/2022 by SuperMartijn642
 */
public class Triple<X, Y, Z> {

    public static <X,Y,Z> Triple<X,Y,Z> of(X left, Y middle, Z right){
        return new Triple<>(left, middle, right);
    }

    private final X left;
    private final Y middle;
    private final Z right;

    public Triple(X left, Y middle, Z right){
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public X left(){
        return this.left;
    }

    public Y middle(){
        return this.middle;
    }

    public Z right(){
        return this.right;
    }
}
```

### src/main/java/com/supermartijn642/core/util/TriPredicate.java

```java
package com.supermartijn642.core.util;

import java.util.Objects;

/**
 * Created 24/07/2022 by SuperMartijn642
 */
@FunctionalInterface
public interface TriPredicate<X, Y, Z> {

    boolean test(X x, Y y, Z z);

    default TriPredicate<X,Y,Z> and(TriPredicate<? super X,? super Y,? super Z> other){
        Objects.requireNonNull(other);
        return (x, y, z) -> this.test(x, y, z) && other.test(x, y, z);
    }

    default TriPredicate<X,Y,Z> negate(){
        return (x, y, z) -> !this.test(x, y, z);
    }

    default TriPredicate<X,Y,Z> or(TriPredicate<? super X,? super Y,? super Z> other){
        Objects.requireNonNull(other);
        return (x, y, z) -> this.test(x, y, z) || other.test(x, y, z);
    }
}
```

### src/main/resources/assets/supermartijn642corelib/textures/gui/scrollbar_background.png.mcmeta

```
{
  "gui": {
    "scaling": {
      "type": "nine_slice",
      "width": 3,
      "height": 3,
      "border": 1,
      "stretch_inner": true
    }
  }
}
```

### src/main/resources/META-INF/accesstransformer.cfg

```ini
# AbstractContainerScreen
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_147005_v # clickedSlot
public-f net.minecraft.inventory.container.Container field_75152_c # containerId
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_147012_x # draggingItem
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_147004_w # isSplittingStack
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_146996_I # quickCraftingRemainder
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_146989_A # snapbackEnd
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_146991_C # snapbackItem
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_147011_y # snapbackStartX
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_147010_z # snapbackStartY
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_146990_B # snapbackTime
protected net.minecraft.client.gui.screen.inventory.ContainerScreen field_146987_F # quickCraftingType
protected net.minecraft.client.gui.screen.inventory.ContainerScreen func_146980_g()V # recalculateQuickCraftRemaining
protected net.minecraft.client.gui.screen.inventory.ContainerScreen func_146982_a(Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V # renderFloatingItem
protected net.minecraft.client.gui.screen.inventory.ContainerScreen func_238746_a_(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/inventory/container/Slot;)V # renderSlot
# BlockBehaviour
public net.minecraft.block.AbstractBlock field_235684_aB_ # properties
# BlockBehaviour$Properties
public net.minecraft.block.AbstractBlock$Properties field_226895_m_ # canOcclude
public net.minecraft.block.AbstractBlock$Properties field_200959_g # destroyTime
public net.minecraft.block.AbstractBlock$Properties field_222381_j # drops
public net.minecraft.block.AbstractBlock$Properties field_208772_j # dynamicShape
public net.minecraft.block.AbstractBlock$Properties field_200958_f # explosionResistance
public net.minecraft.block.AbstractBlock$Properties field_200961_i # friction
public net.minecraft.block.AbstractBlock$Properties field_200955_c # hasCollision
public net.minecraft.block.AbstractBlock$Properties field_235813_o_ # isAir
public net.minecraft.block.AbstractBlock$Properties field_200960_h # isRandomlyTicking
public net.minecraft.block.AbstractBlock$Properties field_235815_q_ # isRedstoneConductor
public net.minecraft.block.AbstractBlock$Properties field_235816_r_ # isSuffocating
public net.minecraft.block.AbstractBlock$Properties field_226894_k_ # jumpFactor
public net.minecraft.block.AbstractBlock$Properties field_235803_e_ # lightEmission
public net.minecraft.block.AbstractBlock$Properties field_200953_a # material
public net.minecraft.block.AbstractBlock$Properties field_235800_b_ # materialColor
public net.minecraft.block.AbstractBlock$Properties field_235806_h_ # requiresCorrectToolForDrops
public net.minecraft.block.AbstractBlock$Properties field_200956_d # soundType
public net.minecraft.block.AbstractBlock$Properties field_226893_j_ # speedFactor
# DirectoryCache
public net.minecraft.data.DirectoryCache field_208329_f # newCache
public net.minecraft.data.DirectoryCache field_208328_e # oldCache
# GuiComponent
public net.minecraft.client.gui.AbstractGui func_238462_a_(Lnet/minecraft/util/math/vector/Matrix4f;Lnet/minecraft/client/renderer/BufferBuilder;IIIIIII)V # fillGradient
# ITag$Builder
public net.minecraft.tags.ITag$Builder field_232953_a_ # entries
# ITag$Proxy
public net.minecraft.tags.ITag$Proxy field_232966_a_ # entry
# Item
public-f net.minecraft.item.Item ister # ister
# ItemGroup
public net.minecraft.item.ItemGroup field_78034_o # langId
# MinMaxBounds$IntBound
public net.minecraft.advancements.criterion.MinMaxBounds$IntBound <init>(Ljava/lang/Integer;Ljava/lang/Integer;)V # <init>
# RenderType
public net.minecraft.client.renderer.RenderType field_228629_af_ # sortOnUpload
# Slot
public-f net.minecraft.inventory.container.Slot field_75223_e # x
public-f net.minecraft.inventory.container.Slot field_75221_f # y
```

### src/main/resources/META-INF/mods.toml

```toml
# The name of the mod loader type to load - for regular FML @Mod mods it should be javafml
modLoader="javafml" #mandatory
# A version range to match for said mod loader - for regular FML @Mod it will be the forge version
loaderVersion="${javafml_dependency}" #mandatory
# The license for your mod
license="${mod_license}" #mandatory
# A URL to refer people to when problems occur with this mod
issueTrackerURL="${mod_issues}" #optional
# A list of mods - how many allowed here is determined by the individual mod loader
[[mods]] #mandatory
# The modid of the mod
modId="${mod_id}" #mandatory
# The version number of the mod
version="${mod_version}" #mandatory
# A display name for the mod
displayName="${mod_name}" #mandatory
# A URL to query for updates for this mod. See the JSON update specification <here>
#updateJSONURL="" #optional
# A URL for the "homepage" for this mod, displayed in the mod UI
displayURL="${mod_page}" #optional
# A file name (in the root of the mod JAR) containing a logo for display
logoFile="${mod_id}.png" #optional
# A text field displayed in the mod UI
#credits="" #optional
# A text field displayed in the mod UI
authors="SuperMartijn642" #optional
# The description text for the mod (multi line!) (#mandatory)
description='''${mod_description}'''
# A dependency - use the . to indicate dependency for a specific modid. Dependencies are optional.
[[dependencies.${mod_id}]] #optional
    # the modid of the dependency
    modId="forge" #mandatory
    # Does this dependency have to exist - if not, ordering below must be specified
    mandatory=true #mandatory
    # The version range of the dependency
    versionRange="${forge_dependency}" #mandatory
    # An ordering relationship for the dependency - BEFORE or AFTER required if the relationship is not mandatory
    ordering="NONE"
    # Side this dependency is applied on - BOTH, CLIENT or SERVER
    side="BOTH"
# Here's another dependency
[[dependencies.${mod_id}]]
    modId="minecraft"
    mandatory=true
    versionRange="${minecraft_dependency}"
    ordering="NONE"
    side="BOTH"
```

### src/main/resources/modid.mixins.json

```json
{
  "required": true,
  "minVersion": "${mixin_minimum_version}",
  "package": "${mod_package}.${mixin_package}",
  "compatibilityLevel": "${mixin_compatibility_level}",
  "refmap": "${mod_id}.mixins.refmap.json",
  "mixins": [
    "BlockPropertiesAccessor",
    "CraftingHelperMixin",
    "DataGeneratorMixin",
    "DatagenModLoaderAccessor",
    "ForgeHooksMixin",
    "ForgeTagHandlerMixin",
    "GameDataMixin",
    "TagBuilderMixin",
    "TagCollectionReaderMixin"
  ],
  "client": [
    "AbstractContainerScreenMixin",
    "GameRendererMixin",
    "LevelRendererMixin"
  ],
  "server": [],
  "injectors": {
    "defaultRequire": 1
  }
}
```

### src/main/resources/pack.mcmeta

```
{
    "pack": {
        "description": "${mod_name}'s resources",
        "pack_format": ${resource_pack_format}
    }
}
```

### src/test/java/com/supermartijn642/core/test/TestMod.java

```java
package com.supermartijn642.core.test;

import com.supermartijn642.core.item.BaseItem;
import com.supermartijn642.core.item.ItemProperties;
import com.supermartijn642.core.registry.ClientRegistrationHandler;
import com.supermartijn642.core.registry.RegistrationHandler;
import com.supermartijn642.core.registry.RegistryEntryAcceptor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;

/**
 * Created 1/23/2021 by SuperMartijn642
 */
@Mod("corelibtestmod")
public class TestMod {

    @RegistryEntryAcceptor(namespace = "corelibtestmod", identifier = "test_item", registry = RegistryEntryAcceptor.Registry.ITEMS)
    public static Item test_item;

    public TestMod(){
        RegistrationHandler handler = RegistrationHandler.get("corelibtestmod");
        handler.registerItem("test_item", () -> new BaseItem(ItemProperties.create()));
        ClientRegistrationHandler.get("corelibtestmod").registerCustomItemRenderer(() -> test_item, () -> (itemStack, transformType, poseStack, bufferSource, combinedLight, combinedOverlay) -> {

        });
    }
}
```

### src/test/java/com/supermartijn642/core/test/TestModClient.java

```java
package com.supermartijn642.core.test;

import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.block.BlockShape;
import com.supermartijn642.core.gui.WidgetScreen;
import com.supermartijn642.core.render.RenderUtils;
import com.supermartijn642.core.render.RenderWorldEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Created 1/22/2021 by SuperMartijn642
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestModClient {

    @SubscribeEvent
    public static void onBlockBreak(LivingEvent.LivingJumpEvent e){
        if(e.getEntity().level.isClientSide)
            ClientUtils.displayScreen(WidgetScreen.of(new TestScreen()));
    }

    @SubscribeEvent
    public static void onDrawSelection(DrawHighlightEvent e){
        Vector3d camera = RenderUtils.getCameraPosition();
        e.getMatrix().pushPose();
        e.getMatrix().translate(-camera.x, -camera.y, -camera.z);
        RenderUtils.renderShape(e.getMatrix(), BlockShape.fullCube(), 1, 1, 0, 0.5f, false);
        RenderUtils.renderShapeSides(e.getMatrix(), BlockShape.fullCube(), 0, 1, 1, 0.5f, false);
        e.getMatrix().popPose();
    }

    @SubscribeEvent
    public static void onRenderWorld(RenderWorldEvent e){
        Vector3d camera = RenderUtils.getCameraPosition();
        e.getPoseStack().pushPose();
        e.getPoseStack().translate(-camera.x, -camera.y, -camera.z);
        RenderUtils.renderShape(e.getPoseStack(), BlockShape.fullCube(), 1, 1, 0, 0.5f, false);
        RenderUtils.renderShapeSides(e.getPoseStack(), BlockShape.fullCube(), 0, 1, 1, 0.5f, false);
        e.getPoseStack().popPose();
    }
}
```

### src/test/java/com/supermartijn642/core/test/TestScreen.java

```java
package com.supermartijn642.core.test;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.gui.ScreenUtils;
import com.supermartijn642.core.gui.widget.BaseWidget;
import com.supermartijn642.core.gui.widget.premade.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;

/**
 * Created 1/22/2021 by SuperMartijn642
 */
public class TestScreen extends BaseWidget {

    protected TestScreen(){
        super(0, 0, 100, 100);
    }

    @Override
    public ITextComponent getNarrationMessage(){
        return TextComponents.string("Test Screen").get();
    }

    @Override
    protected void addWidgets(){
        TextFieldWidget textField = this.addWidget(new TextFieldWidget(10, 10, 50, 20, "Default", 50));
        textField.setSuggestion("Suggestion");
    }

    @Override
    public void renderBackground(MatrixStack poseStack, int mouseX, int mouseY){
        ScreenUtils.drawScreenBackground(poseStack, this.x, this.y, this.width, this.height);
        super.renderBackground(poseStack, mouseX, mouseY);
    }
}
```

### src/test/resources/META-INF/mods.toml

```toml
# This is an example mods.toml file. It contains the data relating to the loading mods.
# There are several mandatory fields (#mandatory), and many more that are optional (#optional).
# The overall format is standard TOML format, v0.5.0.
# Note that there are a couple of TOML lists in this file.
# Find more information on toml format here:  https://github.com/toml-lang/toml
# The name of the mod loader type to load - for regular FML @Mod mods it should be javafml
modLoader="javafml" #mandatory
# A version range to match for said mod loader - for regular FML @Mod it will be the forge version
loaderVersion="[32,)" #mandatory This is typically bumped every Minecraft version by Forge. See our download page for lists of versions.
# The license for you mod. This is mandatory metadata and allows for easier comprehension of your redistributive properties.
# Review your options at https://choosealicense.com/. All rights reserved is the default copyright stance, and is thus the default here.
license="All rights reserved"
# A URL to refer people to when problems occur with this mod
#issueTrackerURL="" #optional
# A list of mods - how many allowed here is determined by the individual mod loader
[[mods]] #mandatory
# The modid of the mod
modId="corelibtestmod" #mandatory
# The version number of the mod - there's a few well known ${} variables useable here or just hardcode it
version="1.0.0" #mandatory
 # A display name for the mod
displayName="SuperMartijn642's Core Lib Test Mod" #mandatory
# A URL to query for updates for this mod. See the JSON update specification <here>
#updateJSONURL="" #optional
# A URL for the "homepage" for this mod, displayed in the mod UI
#displayURL="" #optional
# A file name (in the root of the mod JAR) containing a logo for display
logoFile="supermartijn642corelib.png" #optional
# A text field displayed in the mod UI
#credits="" #optional
# A text field displayed in the mod UI
authors="SuperMartijn642" #optional
# The description text for the mod (multi line!) (#mandatory)
description='''Test mod for SuperMartijn642's Core Lib'''
# A dependency - use the . to indicate dependency for a specific modid. Dependencies are optional.
[[dependencies.corelibtestmod]] #optional
    # the modid of the dependency
    modId="forge" #mandatory
    # Does this dependency have to exist - if not, ordering below must be specified
    mandatory=true #mandatory
    # The version range of the dependency
    versionRange="[34,)" #mandatory
    # An ordering relationship for the dependency - BEFORE or AFTER required if the relationship is not mandatory
    ordering="NONE"
    # Side this dependency is applied on - BOTH, CLIENT or SERVER
    side="BOTH"
# Here's another dependency
[[dependencies.corelibtestmod]]
    modId="minecraft"
    mandatory=true
    versionRange="[1.16.2,1.17)"
    ordering="NONE"
    side="BOTH"
# Add a dependency on SuperMartijn642's Core Lib
[[dependencies.corelibtestmod]]
    modId="supermartijn642corelib"
    mandatory=true
    versionRange="[1.0.0,)"
    ordering="NONE"
    side="BOTH"
```

### src/test/resources/pack.mcmeta

```
{
    "pack": {
        "description": "test mod resources",
        "pack_format": 6
    }
}
```

## Skipped files

- `gradle/wrapper/gradle-wrapper.jar` — binary file
- `src/main/resources/assets/supermartijn642corelib/textures/gui/background.png` — binary file
- `src/main/resources/assets/supermartijn642corelib/textures/gui/buttons.png` — binary file
- `src/main/resources/assets/supermartijn642corelib/textures/gui/scrollbar_background.png` — binary file
- `src/main/resources/assets/supermartijn642corelib/textures/gui/scroller.png` — binary file
- `src/main/resources/assets/supermartijn642corelib/textures/gui/slot.png` — binary file
- `src/main/resources/icon.png` — binary file
