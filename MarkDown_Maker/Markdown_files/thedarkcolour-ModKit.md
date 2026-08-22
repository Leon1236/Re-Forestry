# thedarkcolour-ModKit

## Directory structure

```text
thedarkcolour-ModKit/
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   ├── generated/
│   │   └── resources/
│   │       ├── .cache/
│   │       │   ├── 0a15f4bed94024158c4226286f0d11ba04da9d03
│   │       │   ├── 996182122831dcc16f8ecdcd6d537fc91be8a74b
│   │       │   └── fb52d079d99b2a74ebc754ac6902543bd0a700eb
│   │       └── assets/
│   │           └── modkit/
│   │               ├── blockstates/
│   │               │   └── infinite_power.json
│   │               ├── lang/
│   │               │   └── en_us.json
│   │               └── models/
│   │                   ├── block/
│   │                   │   └── infinite_power.json
│   │                   └── item/
│   │                       ├── clear_wand.json
│   │                       ├── clone_wand.json
│   │                       ├── distance_wand.json
│   │                       ├── fill_wand.json
│   │                       ├── infinite_power.json
│   │                       └── kill_wand.json
│   ├── generated_test/
│   │   └── resources/
│   │       ├── .cache/
│   │       │   ├── 0a4ff18e22024fd71ca6217f67b3b3853a6b256f
│   │       │   ├── 1ffa5604fe005754b87cfdc3204a51c879c092ed
│   │       │   ├── 2b3c91f1414cd6be82dd4888fd0567fc1b34cda9
│   │       │   ├── 34231ad40e53b27eec739443c8909c5569894901
│   │       │   ├── 8ce30b96dd84a24d7ca68d48767dd97dc4e8f436
│   │       │   ├── 9fb1092f32d4fcbf9e061ffd718d4ec689c6c95e
│   │       │   └── ee9805ffe7ae848560a3ad4019a7616844f079cc
│   │       ├── assets/
│   │       │   └── testmod/
│   │       │       ├── blockstates/
│   │       │       │   ├── orange_block.json
│   │       │       │   └── red_block.json
│   │       │       ├── lang/
│   │       │       │   └── en_us.json
│   │       │       └── models/
│   │       │           ├── block/
│   │       │           │   ├── orange_block.json
│   │       │           │   └── red_block.json
│   │       │           └── item/
│   │       │               ├── orange_block.json
│   │       │               └── red_block.json
│   │       └── data/
│   │           ├── minecraft/
│   │           │   ├── advancements/
│   │           │   │   └── recipes/
│   │           │   │       └── building_blocks/
│   │           │   │           └── blackstone.json
│   │           │   ├── recipes/
│   │           │   │   └── blackstone.json
│   │           │   └── tags/
│   │           │       ├── blocks/
│   │           │       │   ├── impermeable.json
│   │           │       │   └── warped_stems.json
│   │           │       └── items/
│   │           │           ├── fox_food.json
│   │           │           └── warped_stems.json
│   │           └── testmod/
│   │               ├── advancements/
│   │               │   └── recipes/
│   │               │       ├── building_blocks/
│   │               │       │   ├── cactus.json
│   │               │       │   ├── ice.json
│   │               │       │   └── orange_block.json
│   │               │       ├── misc/
│   │               │       │   └── orange_from_orange_block.json
│   │               │       └── redstone/
│   │               │           └── iron_door.json
│   │               ├── damage_type/
│   │               │   └── test_damage.json
│   │               └── recipes/
│   │                   ├── apples_if_true.json
│   │                   ├── cactus.json
│   │                   ├── ice.json
│   │                   ├── iron_door.json
│   │                   ├── orange_block.json
│   │                   └── orange_from_orange_block.json
│   ├── main/
│   │   ├── java/
│   │   │   └── thedarkcolour/
│   │   │       └── modkit/
│   │   │           ├── block/
│   │   │           │   ├── InfinitePowerBlock.java
│   │   │           │   └── package-info.java
│   │   │           ├── blockentity/
│   │   │           │   ├── InfinitePowerBlockEntity.java
│   │   │           │   └── package-info.java
│   │   │           ├── data/
│   │   │           │   ├── loot/
│   │   │           │   │   ├── MKLootProvider.java
│   │   │           │   │   └── package-info.java
│   │   │           │   ├── model/
│   │   │           │   │   ├── package-info.java
│   │   │           │   │   ├── SafeBlockModelBuilder.java
│   │   │           │   │   ├── SafeBlockModelProvider.java
│   │   │           │   │   └── SafeItemModelBuilder.java
│   │   │           │   ├── recipe/
│   │   │           │   │   ├── NbtResultRecipe.java
│   │   │           │   │   ├── NbtShapedRecipeBuilder.java
│   │   │           │   │   ├── NbtShapelessRecipeBuilder.java
│   │   │           │   │   └── package-info.java
│   │   │           │   ├── DataHelper.java
│   │   │           │   ├── DirectTagAppender.java
│   │   │           │   ├── MKBlockModelProvider.java
│   │   │           │   ├── MKDamageTypeProvider.java
│   │   │           │   ├── MKEnglishProvider.java
│   │   │           │   ├── MKItemModelProvider.java
│   │   │           │   ├── MKRecipeProvider.java
│   │   │           │   ├── MKTagsProvider.java
│   │   │           │   └── package-info.java
│   │   │           ├── item/
│   │   │           │   ├── AbstractFillWand.java
│   │   │           │   ├── ClearWandItem.java
│   │   │           │   ├── CloneWandItem.java
│   │   │           │   ├── DistanceWandItem.java
│   │   │           │   ├── FillWandItem.java
│   │   │           │   ├── KillWand.java
│   │   │           │   └── package-info.java
│   │   │           ├── MKUtils.java
│   │   │           ├── ModKit.java
│   │   │           ├── ModKitDataGen.java
│   │   │           └── package-info.java
│   │   └── resources/
│   │       ├── assets/
│   │       │   └── modkit/
│   │       │       └── textures/
│   │       │           ├── block/
│   │       │           │   └── infinite_power.png
│   │       │           └── item/
│   │       │               ├── clear_wand.png
│   │       │               ├── clone_wand.png
│   │       │               ├── distance_wand.png
│   │       │               ├── fill_wand.png
│   │       │               └── kill_wand.png
│   │       ├── META-INF/
│   │       │   └── mods.toml
│   │       └── pack.mcmeta
│   └── test/
│       ├── java/
│       │   └── thedarkcolour/
│       │       └── testmod/
│       │           ├── data/
│       │           │   ├── BlockModels.java
│       │           │   ├── DamageTypes.java
│       │           │   ├── DataGen.java
│       │           │   ├── English.java
│       │           │   ├── ItemModels.java
│       │           │   ├── ModTags.java
│       │           │   └── Recipes.java
│       │           └── TestMod.java
│       └── resources/
│           ├── assets/
│           │   └── testmod/
│           │       └── textures/
│           │           ├── block/
│           │           │   └── orange_block.png
│           │           └── item/
│           │               └── orange.png
│           ├── META-INF/
│           │   └── mods.toml
│           └── pack.mcmeta
├── .gitattributes
├── .gitignore
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── jitpack.yml
├── LICENSE
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

### .gitignore

```text
# eclipse
bin
*.launch
.settings
.metadata
.classpath
.project

# idea
out
*.ipr
*.iws
*.iml
.idea

# gradle
build
.gradle

# other
eclipse
run
runs

# Files from Forge MDK
forge*changelog.txt
```

### build.gradle

```groovy
plugins {
    id 'net.minecraftforge.gradle' version '[6.0,6.2)'
    id 'maven-publish'
}

version = '1.0'
group = 'thedarkcolour.modkit'
base.archivesName = 'modkit'

java.toolchain.languageVersion = JavaLanguageVersion.of(17)

minecraft {
    mappings channel: 'official', version: '1.20.1'

    runs {
        client {
            workingDirectory project.file('run')
			
            property 'forge.logging.console.level', 'debug'

            mods {
                modkit {
                    source sourceSets.main
                }
            }
        }

        server {
            workingDirectory project.file('run')

            property 'forge.logging.console.level', 'debug'

            mods {
                modkit {
                    source sourceSets.main
                }
            }
        }

        data {
            workingDirectory project.file('run')
        }

        modkitData {
            workingDirectory project.file('run')

            parent runs.data

            property 'forge.logging.console.level', 'debug'
            args '--mod', 'modkit', '--all', '--output', file('src/generated/resources/'), '--existing', file('src/main/resources/')

            mods {
                modkit {
                    source sourceSets.main
                }
            }
        }

        testmodClient {
            workingDirectory project.file('run')

            property 'forge.logging.console.level', 'debug'

            parent runs.client
            ideaModule "${project.name}.test"

            mods {
                modkit {
                    source sourceSets.main
                }
                testmod {
                    source sourceSets.test
                }
            }
        }

        testmodData {
            workingDirectory project.file('run')

            parent runs.data
            ideaModule "${project.name}.test"

            property 'forge.logging.console.level', 'debug'
            args '--mod', 'testmod', '--all', '--output', file('src/generated_test/resources/'), '--existing', file('src/test/resources')

            mods {
                modkit {
                    source sourceSets.main
                }
                testmod {
                    source sourceSets.test
                }
            }
        }
    }
}

// Include resources generated by data generators.
sourceSets.main.resources { srcDir 'src/generated/resources' }
sourceSets.test.resources { srcDir 'src/generated_test/resources' }

dependencies {
    minecraft 'net.minecraftforge:forge:1.20.1-47.0.3'
}

processResources {
    doLast {
        fileTree(dir: outputs.files.asPath, include: "**/*.json").each {
            File file -> file.text = groovy.json.JsonOutput.toJson(new groovy.json.JsonSlurper().parse(file))
        }
    }
}

// Example for how to get properties into the manifest for reading at runtime.
jar {
    manifest {
        attributes([
                "Specification-Title"     : "modkit",
                "Specification-Vendor"    : "thedarkcolour",
                "Specification-Version"   : "1", // We are version 1 of ourselves
                "Implementation-Title"    : project.name,
                "Implementation-Version"  : project.jar.archiveVersion,
                "Implementation-Vendor"   : "examplemodsareus",
                "Implementation-Timestamp": new Date().format("yyyy-MM-dd'T'HH:mm:ssZ")
        ])
    }
}

jar.finalizedBy('reobfJar')

publishing {
    publications {
        mavenJava(MavenPublication) {
            artifact jar
        }
    }
    repositories {
        maven {
            url "file://${project.projectDir}/mcmodsrepo"
        }
    }
}

tasks.withType(JavaCompile).configureEach {
    options.encoding = 'UTF-8' // Use the UTF-8 charset for Java compilation
}
```

### gradle.properties

```properties
# Sets default memory used for gradle commands. Can be overridden by user or command line properties.
# This is required to provide enough memory for the Minecraft decompilation process.
org.gradle.jvmargs=-Xmx3G
org.gradle.daemon=false
```

### gradle/wrapper/gradle-wrapper.properties

```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.10.1-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

### gradlew

```
#!/bin/sh

#
# Copyright © 2015-2021 the original authors.
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
#       https://github.com/gradle/gradle/blob/master/subprojects/plugins/src/main/resources/org/gradle/api/internal/plugins/unixStartScript.txt
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

APP_HOME=$( cd "${APP_HOME:-./}" && pwd -P ) || exit

APP_NAME="Gradle"
APP_BASE_NAME=${0##*/}

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

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

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar


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
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Increase the maximum file descriptors if we can.
if ! "$cygwin" && ! "$darwin" && ! "$nonstop" ; then
    case $MAX_FD in #(
      max*)
        MAX_FD=$( ulimit -H -n ) ||
            warn "Could not query maximum file descriptor limit"
    esac
    case $MAX_FD in  #(
      '' | soft) :;; #(
      *)
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
    CLASSPATH=$( cygpath --path --mixed "$CLASSPATH" )

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

# Collect all arguments for the java command;
#   * $DEFAULT_JVM_OPTS, $JAVA_OPTS, and $GRADLE_OPTS can contain fragments of
#     shell script including quotes and variable substitutions, so put them in
#     double quotes to make sure that they get re-expanded; and
#   * put everything else in single quotes, so that it's not re-expanded.

set -- \
        "-Dorg.gradle.appname=$APP_BASE_NAME" \
        -classpath "$CLASSPATH" \
        org.gradle.wrapper.GradleWrapperMain \
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

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar


@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

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

### jitpack.yml

```yaml
before_install:
    - ./gradlew --refresh-dependencies
```

### LICENSE

```text
MIT License

Copyright (c) 2023 thedarkcolour

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

### settings.gradle

```groovy
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven { url = 'https://maven.minecraftforge.net/' }
        maven { url = 'https://maven.parchmentmc.org' }
    }
}

plugins {
    id 'org.gradle.toolchains.foojay-resolver-convention' version '0.5.0'
}
```

### src/generated/resources/.cache/0a15f4bed94024158c4226286f0d11ba04da9d03

```
// 1.20.1	2025-01-13T16:21:46.8820547	ModKit Item Models for mod 'modkit'
33a1320eb2ce72387140b51876d8c846d14ec1fe assets/modkit/models/item/clear_wand.json
63b46cfee75913ae8c736b72b6496c0dc7456a2b assets/modkit/models/item/clone_wand.json
4244bff20534b90da48216c6c9333deacc089ad4 assets/modkit/models/item/distance_wand.json
c008e5151b9e00b772be5dd79789aa86e1368f6c assets/modkit/models/item/fill_wand.json
c55943473c9bdfc926d74745d763c538c622e950 assets/modkit/models/item/infinite_power.json
a7741dfb65553a25ac36cde1caffca4f1f0ee620 assets/modkit/models/item/kill_wand.json
```

### src/generated/resources/.cache/996182122831dcc16f8ecdcd6d537fc91be8a74b

```
// 1.20.1	2025-01-13T16:20:30.2919986	ModKit Block Models for mod 'modkit'
40751e95e6486d4ea95326718f518ddc4b7582e8 assets/modkit/blockstates/infinite_power.json
100d5857bc40618a86bd04ab3707adeb4f314991 assets/modkit/models/block/infinite_power.json
```

### src/generated/resources/.cache/fb52d079d99b2a74ebc754ac6902543bd0a700eb

```
// 1.20.1	2025-01-13T16:20:30.2929979	ModKit Language: en_us for mod 'modkit'
2f31f24f03187befe21ecd2acbe3ed903d469647 assets/modkit/lang/en_us.json
```

### src/generated/resources/assets/modkit/blockstates/infinite_power.json

```json
{
  "variants": {
    "": {
      "model": "modkit:block/infinite_power"
    }
  }
}
```

### src/generated/resources/assets/modkit/lang/en_us.json

```json
{
  "block.modkit.infinite_power": "Infinite Power",
  "item.modkit.clear_wand": "Clear Wand",
  "item.modkit.clone_wand": "Clone Wand",
  "item.modkit.distance_wand": "Distance Wand",
  "item.modkit.fill_wand": "Fill Wand",
  "item.modkit.kill_wand": "Kill Wand",
  "itemGroup.modkit": "ModKit"
}
```

### src/generated/resources/assets/modkit/models/block/infinite_power.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "modkit:block/infinite_power"
  }
}
```

### src/generated/resources/assets/modkit/models/item/clear_wand.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "modkit:item/clear_wand"
  }
}
```

### src/generated/resources/assets/modkit/models/item/clone_wand.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "modkit:item/clone_wand"
  }
}
```

### src/generated/resources/assets/modkit/models/item/distance_wand.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "modkit:item/distance_wand"
  }
}
```

### src/generated/resources/assets/modkit/models/item/fill_wand.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "modkit:item/fill_wand"
  }
}
```

### src/generated/resources/assets/modkit/models/item/infinite_power.json

```json
{
  "parent": "modkit:block/infinite_power"
}
```

### src/generated/resources/assets/modkit/models/item/kill_wand.json

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "modkit:item/kill_wand"
  }
}
```

### src/generated_test/resources/.cache/0a4ff18e22024fd71ca6217f67b3b3853a6b256f

```
// 1.20.1	2023-06-28T20:11:44.8686285	ModKit Block Models for mod 'testmod'
248bb8da675f40dd933a1895864035271dee2544 assets/testmod/blockstates/orange_block.json
5b078c176f11315b05092e670a6c8edaceb13969 assets/testmod/blockstates/red_block.json
7698fcc228917bd56d964f5a1b5b3b0883fb7f9f assets/testmod/models/block/orange_block.json
81617e750db63c29c21d18dc9662990c01fb7dcd assets/testmod/models/block/red_block.json
```

### src/generated_test/resources/.cache/1ffa5604fe005754b87cfdc3204a51c879c092ed

```
// 1.20.1	2023-06-28T20:19:06.1683982	ModKit Language: en_us for mod 'testmod'
88f5b594189962997cb10f7cd1df00e57a29ce68 assets/testmod/lang/en_us.json
```

### src/generated_test/resources/.cache/2b3c91f1414cd6be82dd4888fd0567fc1b34cda9

```
// 1.20.1	2023-06-28T20:11:44.8656285	ModKit Item Models for mod 'testmod'
489c20449548731b63b21d3c31e514fe413aabb1 assets/testmod/models/item/orange_block.json
37c304f6c69ed7096e9af41f16bc4820df25573c assets/testmod/models/item/red_block.json
```

### src/generated_test/resources/.cache/34231ad40e53b27eec739443c8909c5569894901

```
// 1.20.1	2023-07-27T17:21:34.2584883	Tags for minecraft:block mod id testmod
b458ad735ba972ba7d6cf33690e5c0c4260f6223 data/minecraft/tags/blocks/impermeable.json
3a45147ed4506a77baf16d660d7ed753d0d8d33d data/minecraft/tags/blocks/warped_stems.json
```

### src/generated_test/resources/.cache/8ce30b96dd84a24d7ca68d48767dd97dc4e8f436

```
// 1.20.1	2024-11-19T23:55:32.8524188	damage_type generator for testmod
d0baf60433c196788f638095e37648bc51bf60c0 data/testmod/damage_type/test_damage.json
```

### src/generated_test/resources/.cache/9fb1092f32d4fcbf9e061ffd718d4ec689c6c95e

```
// 1.20.1	2023-11-24T22:11:48.871284	Recipes
134459d219b0a46965fe2456902850735a5037cf data/minecraft/advancements/recipes/building_blocks/blackstone.json
2840b929e2b597eb433d98d2903c7c0dd7640253 data/minecraft/recipes/blackstone.json
13735411499dd66a73323e3b25e45f0a54dcf203 data/testmod/advancements/recipes/building_blocks/cactus.json
74a97120d1649361468c33913fe337b22ffbd471 data/testmod/advancements/recipes/building_blocks/ice.json
f4b8bc2d6f73acfc381266afa44cc3d49759fef7 data/testmod/advancements/recipes/building_blocks/orange_block.json
466f0d72751bb9ea681a0107816b8cc9fa73f46b data/testmod/advancements/recipes/misc/orange_from_orange_block.json
e28fdf7a13c0e03a7bdf2e1dd16f3015ab4cb073 data/testmod/advancements/recipes/redstone/iron_door.json
cb3a8a30877520f783d4db00d6224ee3ec487c98 data/testmod/recipes/apples_if_true.json
db4530a99abbb74b5f5cc99129351151dcf9c11f data/testmod/recipes/cactus.json
c6170979875d2d7df11404196e36bc1533418c98 data/testmod/recipes/ice.json
809262aab1d3f18b200ac218a8e8cf3bbc359b37 data/testmod/recipes/iron_door.json
1349b14bb28ba3bb51edabac65a5308106a2b806 data/testmod/recipes/orange_block.json
9743edfb8089187cccec1f892b119b552ae3d8d0 data/testmod/recipes/orange_from_orange_block.json
```

### src/generated_test/resources/.cache/ee9805ffe7ae848560a3ad4019a7616844f079cc

```
// 1.20.1	2023-07-27T17:21:34.2604894	Tags for minecraft:item mod id testmod
cd4c50d33b48d0b6adf4643d4c909443140610c8 data/minecraft/tags/items/fox_food.json
3a45147ed4506a77baf16d660d7ed753d0d8d33d data/minecraft/tags/items/warped_stems.json
```

### src/generated_test/resources/assets/testmod/blockstates/orange_block.json

```json
{
  "variants": {
    "": {
      "model": "testmod:block/orange_block"
    }
  }
}
```

### src/generated_test/resources/assets/testmod/blockstates/red_block.json

```json
{
  "variants": {
    "": {
      "model": "testmod:block/red_block"
    }
  }
}
```

### src/generated_test/resources/assets/testmod/lang/en_us.json

```json
{
  "block.testmod.orange_block": "Orange Block",
  "block.testmod.red_block": "Red Block",
  "effect.testmod.bruise": "Bruise",
  "item.testmod.orange": "Orange"
}
```

### src/generated_test/resources/assets/testmod/models/block/orange_block.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "testmod:block/orange_block"
  }
}
```

### src/generated_test/resources/assets/testmod/models/block/red_block.json

```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "testmod:block/red_block"
  }
}
```

### src/generated_test/resources/assets/testmod/models/item/orange_block.json

```json
{
  "parent": "testmod:block/orange_block"
}
```

### src/generated_test/resources/assets/testmod/models/item/red_block.json

```json
{
  "parent": "testmod:block/red_block"
}
```

### src/generated_test/resources/data/minecraft/advancements/recipes/building_blocks/blackstone.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "tag": "forge:cobblestone"
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "minecraft:blackstone"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "minecraft:blackstone"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated_test/resources/data/minecraft/recipes/blackstone.json

```json
{
  "type": "minecraft:crafting_shapeless",
  "category": "building",
  "ingredients": [
    {
      "tag": "forge:cobblestone"
    },
    {
      "tag": "forge:cobblestone"
    },
    {
      "tag": "forge:cobblestone"
    },
    {
      "tag": "forge:cobblestone"
    },
    {
      "tag": "forge:cobblestone"
    },
    {
      "tag": "forge:cobblestone"
    },
    {
      "tag": "forge:cobblestone"
    },
    {
      "tag": "forge:cobblestone"
    },
    {
      "item": "minecraft:black_dye"
    }
  ],
  "result": {
    "count": 8,
    "item": "minecraft:blackstone"
  }
}
```

### src/generated_test/resources/data/minecraft/tags/blocks/impermeable.json

```json
{
  "values": [
    "testmod:orange_block"
  ]
}
```

### src/generated_test/resources/data/minecraft/tags/blocks/warped_stems.json

```json
{
  "values": [
    "testmod:red_block"
  ]
}
```

### src/generated_test/resources/data/minecraft/tags/items/fox_food.json

```json
{
  "values": [
    "testmod:orange"
  ]
}
```

### src/generated_test/resources/data/minecraft/tags/items/warped_stems.json

```json
{
  "values": [
    "testmod:red_block"
  ]
}
```

### src/generated_test/resources/data/testmod/advancements/recipes/building_blocks/cactus.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "minecraft:red_candle"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "testmod:cactus"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "testmod:cactus"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated_test/resources/data/testmod/advancements/recipes/building_blocks/ice.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "minecraft:black_bed"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "testmod:ice"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "testmod:ice"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated_test/resources/data/testmod/advancements/recipes/building_blocks/orange_block.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "testmod:orange"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "testmod:orange_block"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "testmod:orange_block"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated_test/resources/data/testmod/advancements/recipes/misc/orange_from_orange_block.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "testmod:orange_block"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "testmod:orange_from_orange_block"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "testmod:orange_from_orange_block"
    ]
  },
  "sends_telemetry_event": false
}
```

### src/generated_test/resources/data/testmod/advancements/recipes/redstone/iron_door.json

```json
{
  "parent": "minecraft:recipes/root",
  "criteria": {
    "has_item": {
      "conditions": {
        "items": [
          {
            "items": [
              "minecraft:iron_block"
            ]
          }
        ]
      },
      "trigger": "minecraft:inventory_changed"
    },
    "has_the_recipe": {
      "conditions": {
        "recipe": "testmod:iron_door"
      },
      "trigger": "minecraft:recipe_unlocked"
    }
  },
  "requirements": [
    [
      "has_item",
      "has_the_recipe"
    ]
  ],
  "rewards": {
    "recipes": [
      "testmod:iron_door"
    ]
  },
  "sends_telemetry_event": true
}
```

### src/generated_test/resources/data/testmod/damage_type/test_damage.json

```json
{
  "death_message_type": "intentional_game_design",
  "effects": "freezing",
  "exhaustion": 0.5,
  "message_id": "testmod.test_damage",
  "scaling": "never"
}
```

### src/generated_test/resources/data/testmod/recipes/apples_if_true.json

```json
{
  "type": "forge:conditional",
  "recipes": [
    {
      "conditions": [
        {
          "type": "forge:item_exists",
          "item": "minecraft:bundle"
        }
      ],
      "recipe": {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {
          "#": {
            "item": "minecraft:dirt"
          }
        },
        "pattern": [
          "##",
          "##"
        ],
        "result": {
          "item": "minecraft:apple"
        },
        "show_notification": true
      }
    }
  ]
}
```

### src/generated_test/resources/data/testmod/recipes/cactus.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "building",
  "key": {
    "#": {
      "item": "minecraft:red_candle"
    }
  },
  "pattern": [
    "###"
  ],
  "result": {
    "count": 6,
    "item": "minecraft:cactus"
  },
  "show_notification": true
}
```

### src/generated_test/resources/data/testmod/recipes/ice.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "building",
  "key": {
    "#": {
      "item": "minecraft:black_bed"
    }
  },
  "pattern": [
    "#  ",
    "## ",
    "###"
  ],
  "result": {
    "count": 4,
    "item": "minecraft:ice"
  },
  "show_notification": true
}
```

### src/generated_test/resources/data/testmod/recipes/iron_door.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "redstone",
  "group": "wooden_door",
  "key": {
    "#": {
      "item": "minecraft:iron_block"
    }
  },
  "pattern": [
    "##",
    "##",
    "##"
  ],
  "result": {
    "item": "minecraft:iron_door"
  },
  "show_notification": true
}
```

### src/generated_test/resources/data/testmod/recipes/orange_block.json

```json
{
  "type": "minecraft:crafting_shaped",
  "category": "building",
  "key": {
    "#": {
      "item": "testmod:orange"
    }
  },
  "pattern": [
    "###",
    "###",
    "###"
  ],
  "result": {
    "item": "testmod:orange_block"
  },
  "show_notification": true
}
```

### src/generated_test/resources/data/testmod/recipes/orange_from_orange_block.json

```json
{
  "type": "minecraft:crafting_shapeless",
  "category": "misc",
  "ingredients": [
    {
      "item": "testmod:orange_block"
    }
  ],
  "result": {
    "count": 9,
    "item": "testmod:orange"
  }
}
```

### src/main/java/thedarkcolour/modkit/block/InfinitePowerBlock.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2025 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.Nullable;
import thedarkcolour.modkit.ModKit;
import thedarkcolour.modkit.blockentity.InfinitePowerBlockEntity;

public class InfinitePowerBlock extends Block implements EntityBlock {
	public InfinitePowerBlock() {
		super(Properties.of());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new InfinitePowerBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return level.isClientSide ? null : createTickerHelper(type, ModKit.INFINITE_POWER_TYPE.get(), InfinitePowerBlockEntity::tick);
	}

	@Nullable
	@SuppressWarnings("unchecked")
	protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> expected, BlockEntityTicker<? super E> ticker) {
		return expected == type ? (BlockEntityTicker<A>) ticker : null;
	}
}
```

### src/main/java/thedarkcolour/modkit/block/package-info.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.modkit.block;
```

### src/main/java/thedarkcolour/modkit/blockentity/InfinitePowerBlockEntity.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2025 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import thedarkcolour.modkit.ModKit;

public class InfinitePowerBlockEntity extends BlockEntity implements IEnergyStorage {
	private final LazyOptional<IEnergyStorage> energyCap;

	public InfinitePowerBlockEntity(BlockPos pos, BlockState state) {
		super(ModKit.INFINITE_POWER_TYPE.get(), pos, state);

		this.energyCap = LazyOptional.of(() -> this);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity tile) {
		if (!level.isClientSide) {
			Direction.stream().forEach(direction -> {
				BlockPos adjacentPos = pos.relative(direction);

				BlockEntity blockEntity = level.getBlockEntity(adjacentPos);
				if (blockEntity != null) {
					blockEntity.getCapability(ForgeCapabilities.ENERGY)
							.ifPresent(energy -> energy.receiveEnergy(Integer.MAX_VALUE, false));
				}
			});
		}
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (this.remove && cap == ForgeCapabilities.ENERGY) {
			return this.energyCap.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public int receiveEnergy(int i, boolean b) {
		return 0;
	}

	@Override
	public int extractEnergy(int i, boolean b) {
		return Integer.MAX_VALUE;
	}

	@Override
	public int getEnergyStored() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int getMaxEnergyStored() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean canExtract() {
		return true;
	}

	@Override
	public boolean canReceive() {
		return false;
	}
}
```

### src/main/java/thedarkcolour/modkit/blockentity/package-info.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.modkit.blockentity;
```

### src/main/java/thedarkcolour/modkit/data/DataHelper.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thedarkcolour.modkit.ModKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Use this in your event handler for GatherDataEvent. To avoid requiring ModKit in-game,
 * GatherDataEvent should be in a separate class from mod code. See the examples package.
 * For a more detailed example, check the "src/test/" directory on ModKit's GitHub.
 * <p>
 * Methods which specify what to generate follow the naming scheme "createBlah" and always
 * have a consumer (usually nullable) as their last parameter which gets run at the appropriate
 * time during data generation. For example, while you'd normally write translation code in
 * LanguageProvider#addTranslations, you would write it in the consumer you pass as the last
 * argument for {@link #createEnglish}, either with a method reference to a static method
 * "addTranslations" in your own data generation class. If you do not need any additional
 * translations aside from what's automatically generated by ModKit, you may simply pass null.
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class DataHelper {
    protected final String modid;
    protected final GatherDataEvent event;
    protected final Logger logger;
    protected final Map<ResourceKey<?>, MKTagsProvider<?>> tags;

    @Nullable
    protected MKEnglishProvider english;
    @Nullable
    protected MKItemModelProvider itemModels;
    @Nullable
    protected MKBlockModelProvider blockModels;
    @Nullable
    protected MKRecipeProvider recipes;
    @Nullable
    protected BiFunction<MKEnglishProvider, PackOutput, List<DataProvider>> addModonomiconBooks;
    @Nullable
    private MKDamageTypeProvider damageTypes;

    public DataHelper(String modid, GatherDataEvent event) {
        this.modid = modid;
        this.event = event;
        this.logger = LoggerFactory.getLogger(ModKit.ID + "/" + modid);
        this.tags = new HashMap<>();
    }

    /**
     * Generates English language translations for your mod.
     *
     * @param generateNames   Whether to automatically generate names based on registry names.
     *                        For example, "minecraft:gold_ingot" would become "Gold Ingot".
     *                        Cases where this does not work is if your registry names do not follow
     *                        proper convention, or if you use acronyms: "projecte:emc_gun" would be "Emc Gun"
     *                        instead of "EMC Gun" and "mymod:exampleblock" would be "Exampleblock" instead of "Example Block"
     * @param addTranslations If not null, a consumer ran in LanguageProvider.addTranslations AFTER names are autogenerated,
     *                        so you can add in names that were generated incorrectly or names for things like Creative Tabs.
     */
    public MKEnglishProvider createEnglish(boolean generateNames, @Nullable Consumer<MKEnglishProvider> addTranslations) {
        this.checkNotCreated(this.english, "English language");

        this.english = new MKEnglishProvider(event.getGenerator().getPackOutput(), this.modid, this.logger, generateNames, addTranslations);

        if (addModonomiconBooks != null) {
            for (DataProvider book : addModonomiconBooks.apply(this.english, this.event.getGenerator().getPackOutput())) {
                this.event.getGenerator().addProvider(true, book);
            }
        }

        this.event.getGenerator().addProvider(this.event.includeClient(), this.english);

        return this.english;
    }

    /**
     * Use this to register your Modonomicon books before the language generation is created.
     *
     * @param customEnglish Whether you are calling {{@link #createEnglish(boolean, Consumer)}} later. If false,
     *                      this method will use an MKEnglishProvider that doesn't generate names.
     * @param addBooks      Create your book providers here, return them as a list.
     */
    public void createModonomiconBooks(boolean customEnglish, BiFunction<MKEnglishProvider, PackOutput, List<DataProvider>> addBooks) {
        this.addModonomiconBooks = addBooks;

        if (!customEnglish) {
            this.createEnglish(false, null);
        }
    }

    /**
     * Generates item models for your mod. If you also have Block Models, call this AFTER calling {@link #createBlockModels}
     *
     * @param generate3dBlockItems If true, BlockItems are given generic 3D item models (ex. dirt, diamond block)
     * @param generate2dItems      If true, non-BlockItems are given generic 2D item models (ex. gold ingot)
     *                             and SwordItem/PickaxeItem/ShovelItem/etc. items will be given handheld item models (ex. wooden pickaxe)
     * @param generateSpawnEggs    If true, SpawnEggItems are given spawn egg item models
     * @param addItemModels        Function (nullable) to add/override generated models for items. If you are using generate3dBlockItems
     *                             or generate2dItems and you want to change which model an item is generated with, do so in
     *                             this function.
     */
    public MKItemModelProvider createItemModels(boolean generate3dBlockItems, boolean generate2dItems, boolean generateSpawnEggs, @Nullable Consumer<MKItemModelProvider> addItemModels) {
        this.checkNotCreated(this.itemModels, "Item models");

        this.itemModels = new MKItemModelProvider(this.event.getGenerator().getPackOutput(), this.event.getExistingFileHelper(), this.modid, this.logger, generate3dBlockItems, generate2dItems, generateSpawnEggs, addItemModels);
        this.event.getGenerator().addProvider(this.event.includeClient(), this.itemModels);

        return this.itemModels;
    }

    /**
     * Generates block models for your mod. The MKBlockModelProvider provides some template models
     * you can use, but if you need anything more complicated it's best to write your own methods and
     * use them in the method you pass in for addBlockModels.
     *
     * @param addBlockModels Non-null function which receives the MKBlockModelProvider, which inherits methods from
     *                       BlockStateProvider and has some other methods to generate models.
     */
    public MKBlockModelProvider createBlockModels(Consumer<MKBlockModelProvider> addBlockModels) {
        this.checkNotCreated(this.blockModels, "Block models");

        if (this.itemModels != null) {
            // Ex. Item models which use block models as parents will log errors that those block models don't exist,
            // because data providers run in creation order so block models wouldn't have generated yet.
            this.logger.warn("Item model generation was added BEFORE block model generation; this is incorrect, expect some false alarm errors");
        }

        // Lazy is used so that createItemModels is called automatically if your mod isn't using it
        Lazy<MKItemModelProvider> lazyItemModels = Lazy.of(() -> {
            if (this.itemModels == null) {
                this.createItemModels(false, false, false, null);
                this.event.getGenerator().addProvider(this.event.includeClient(), this.itemModels);
            }
            return this.itemModels;
        });

        this.blockModels = new MKBlockModelProvider(this.event.getGenerator().getPackOutput(), this.event.getExistingFileHelper(), lazyItemModels, this.modid, this.logger, addBlockModels);
        this.event.getGenerator().addProvider(this.event.includeClient(), this.blockModels);

        return this.blockModels;
    }

    /**
     * Generates recipes of all kinds for your mod.
     *
     * @param addRecipes Non-null function which receives the finished recipe writer and MKRecipeProvider, which has
     *                   built-in methods for common recipe types. If you need something more advanced, you may write
     *                   methods using the given finished recipe writer and call them in your addRecipes function.
     */
    public MKRecipeProvider createRecipes(BiConsumer<Consumer<FinishedRecipe>, MKRecipeProvider> addRecipes) {
        this.checkNotCreated(this.recipes, "Recipes");

        this.recipes = new MKRecipeProvider(this.event.getGenerator().getPackOutput(), this.modid, addRecipes);
        this.event.getGenerator().addProvider(this.event.includeServer(), this.recipes);

        return this.recipes;
    }

    /**
     * Generate damage source types.
     *
     * @param addTypes A function that takes in a damage type provider to add new damage types.
     */
    public MKDamageTypeProvider createDamageTypes(Consumer<MKDamageTypeProvider> addTypes) {
        this.checkNotCreated(this.damageTypes, "Damage Types");

        this.damageTypes = new MKDamageTypeProvider(this.event.getGenerator().getPackOutput(), this.event.getExistingFileHelper(), this.modid, addTypes);
        this.event.getGenerator().addProvider(this.event.includeServer(), this.damageTypes);

        return this.damageTypes;
    }

    /**
     * Generates tags for a specific registry. For item tags, you may use the copy() method
     * to copy equivalent block tags into your item tags.
     *
     * @param registry The registry to generate tags for
     * @param addTags  A function that takes in the tag provider and a holder lookup provider in order to generate tags.
     * @param <T>      The type of objects to generate tags for
     * @return The tag provider, not sure what you'd use this for.
     */
    public <T> MKTagsProvider<T> createTags(ResourceKey<? extends Registry<T>> registry, BiConsumer<MKTagsProvider<T>, HolderLookup.Provider> addTags) {
        this.checkNotCreated(this.tags.get(registry), "Tags for " + registry.location());

        var provider = new MKTagsProvider<>(this, registry, addTags);
        this.tags.put(registry, provider);
        this.event.getGenerator().addProvider(this.event.includeServer(), provider);

        return provider;
    }

    /**
     * Alternative method which omits the often unused HolderLookup.Provider parameter.
     *
     * @see #createTags(ResourceKey, BiConsumer)
     */
    public <T> MKTagsProvider<T> createTags(ResourceKey<? extends Registry<T>> registry, Consumer<MKTagsProvider<T>> addTags) {
        return this.createTags(registry, (tags, lookup) -> addTags.accept(tags));
    }

    private void checkNotCreated(@Nullable Object obj, String provider) {
        if (obj != null) {
            throw new IllegalStateException(provider + " generation already created!");
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/data/DirectTagAppender.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.extensions.IForgeTagAppender;

import java.util.function.Function;
import java.util.function.Supplier;

public class DirectTagAppender<T> extends TagsProvider.TagAppender<T> implements IForgeTagAppender<T> {
    private final Function<T, ResourceKey<T>> keyGetter;

    public DirectTagAppender(TagBuilder builder, Function<T, ResourceKey<T>> keyGetter, String modId) {
        super(builder, modId);
        this.keyGetter = keyGetter;
    }

    public final DirectTagAppender<T> add(T obj) {
        this.add(keyGetter.apply(obj));
        return this;
    }

    @SafeVarargs
    public final DirectTagAppender<T> add(T... objs) {
        for (var obj : objs) {
            this.add(keyGetter.apply(obj));
        }
        return this;
    }

    public final DirectTagAppender<T> add(Supplier<? extends T> obj) {
        this.add(keyGetter.apply(obj.get()));
        return this;
    }

    @SafeVarargs
    public final DirectTagAppender<T> add(Supplier<? extends T>... objs) {
        for (var obj : objs) {
            this.add(keyGetter.apply(obj.get()));
        }
        return this;
    }

    public DirectTagAppender<T> addKey(ResourceKey<T> key) {
        this.add(key);
        return this;
    }

    @SafeVarargs
    public final DirectTagAppender<T> addKey(ResourceKey<T>... keys) {
        this.add(keys);
        return this;
    }

    @Override
    public DirectTagAppender<T> addOptional(ResourceLocation p_176840_) {
        super.addOptional(p_176840_);
        return this;
    }

    @Override
    public DirectTagAppender<T> addOptionalTag(ResourceLocation p_176842_) {
        super.addOptionalTag(p_176842_);
        return this;
    }

    @Override
    public DirectTagAppender<T> add(TagEntry tag) {
        super.add(tag);
        return this;
    }

    @SafeVarargs
    @Override
    public final DirectTagAppender<T> addTags(TagKey<T>... values) {
        super.addTags(values);
        return this;
    }

    @Override
    public DirectTagAppender<T> addTag(TagKey<T> tag) {
        super.addTag(tag);
        return this;
    }

    public DirectTagAppender<T> remove(T entry) {
        remove(keyGetter.apply(entry));
        return this;
    }

    @SafeVarargs
    public final DirectTagAppender<T> remove(final T first, final T... entries) {
        this.remove(first);
        for (T entry : entries) {
            this.remove(entry);
        }
        return this;
    }

    @Override
    public DirectTagAppender<T> replace() {
        super.replace();
        return this;
    }

    @Override
    public DirectTagAppender<T> replace(boolean value) {
        super.replace(value);
        return this;
    }

    @Override
    public DirectTagAppender<T> remove(ResourceLocation location) {
        super.remove(location);
        return this;
    }

    @Override
    public DirectTagAppender<T> remove(ResourceLocation first, ResourceLocation... locations) {
        super.remove(first, locations);
        return this;
    }

    @Override
    public DirectTagAppender<T> remove(ResourceKey<T> resourceKey) {
        super.remove(resourceKey);
        return this;
    }

    @SafeVarargs
    @Override
    public final DirectTagAppender<T> remove(ResourceKey<T> firstResourceKey, ResourceKey<T>... resourceKeys) {
        super.remove(firstResourceKey, resourceKeys);
        return this;
    }

    @Override
    public DirectTagAppender<T> remove(TagKey<T> tag) {
        super.remove(tag);
        return this;
    }

    @SafeVarargs
    @Override
    public final DirectTagAppender<T> remove(TagKey<T> first, TagKey<T>... tags) {
        super.remove(first, tags);
        return this;
    }
}
```

### src/main/java/thedarkcolour/modkit/data/loot/MKLootProvider.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MKLootProvider extends LootTableProvider {
    private final List<SubProviderEntry> providers = new ArrayList<>();

    public MKLootProvider(PackOutput output) {
        super(output, Set.of(), null);
    }

    @Override
    public List<SubProviderEntry> getTables() {
        return providers;
    }
}
```

### src/main/java/thedarkcolour/modkit/data/loot/package-info.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.modkit.data.loot;
```

### src/main/java/thedarkcolour/modkit/data/MKBlockModelProvider.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import thedarkcolour.modkit.data.model.SafeBlockModelProvider;

import java.util.Objects;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class MKBlockModelProvider extends BlockStateProvider {
    private final Lazy<MKItemModelProvider> itemModels;
    private final String modid;
    private final Consumer<MKBlockModelProvider> addBlockModels;

    private final SafeBlockModelProvider blockModels;

    @ApiStatus.Internal
    public MKBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper, Lazy<MKItemModelProvider> itemModels, String modid, Logger logger, Consumer<MKBlockModelProvider> addBlockModels) {
        super(output, modid, existingFileHelper);
        this.itemModels = itemModels;
        this.modid = modid;
        this.addBlockModels = addBlockModels;
        this.blockModels = new SafeBlockModelProvider(output, modid, logger, existingFileHelper);
    }

    public ModelFile.UncheckedModelFile file(ResourceLocation resourceLoc) {
        return new ModelFile.UncheckedModelFile(resourceLoc);
    }

    public ModelFile.UncheckedModelFile modFile(String path) {
        return this.file(this.modBlock(path));
    }

    public ModelFile.UncheckedModelFile mcFile(String path) {
        return this.file(this.mcBlock(path));
    }

    public ResourceLocation modBlock(String name) {
        return this.modLoc("block/" + name);
    }

    public ResourceLocation mcBlock(String name) {
        return this.mcLoc("block/" + name);
    }

    public ResourceLocation key(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
    }

    public String name(Block block) {
        return this.key(block).getPath();
    }

    @Override
    public BlockModelBuilder cubeAll(Block block) {
        return this.models().cubeAll(this.name(block), this.blockTexture(block));
    }

    @Override
    public void simpleBlockItem(Block block, ModelFile model) {
        this.itemModels.get().getBuilder(key(block).getPath()).parent(model);
    }

    /**
     * @deprecated Do not use this method, use the MKItemModelProvider from your IDataHelper
     */
    @Override
    @Deprecated
    public final ItemModelProvider itemModels() {
        try {
            if (!Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).isInstance(this)) {
                throw new UnsupportedOperationException("Do not use MKBlockModelProvider to generate item models");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return super.itemModels();
    }

    @Override
    public BlockModelProvider models() {
        return this.blockModels;
    }

    @Override
    protected void registerStatesAndModels() {
        this.addBlockModels.accept(this);
    }

    @Override
    @NotNull
    public String getName() {
        return "ModKit Block Models for mod '" + this.modid + "'";
    }
}
```

### src/main/java/thedarkcolour/modkit/data/MKDamageTypeProvider.java

```java
package thedarkcolour.modkit.data;

import com.mojang.serialization.JsonOps;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DeathMessageType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MKDamageTypeProvider extends JsonCodecProvider<DamageType> {
    private final Consumer<MKDamageTypeProvider> addTypes;
    private final HashMap<ResourceLocation, DamageTypeBuilder> types = new HashMap<>();

    public MKDamageTypeProvider(PackOutput output, ExistingFileHelper helper, String modid, Consumer<MKDamageTypeProvider> addTypes) {
        super(output, helper, modid, JsonOps.INSTANCE, PackType.SERVER_DATA, "damage_type", DamageType.CODEC, Map.of());
        this.addTypes = addTypes;
    }

    public DamageTypeBuilder add(ResourceKey<DamageType> type) {
        return this.types.computeIfAbsent(type.location(), key -> new DamageTypeBuilder(this.modid + '.' + type.location().getPath()));
    }

    @Override
    protected void gather(BiConsumer<ResourceLocation, DamageType> consumer) {
        this.addTypes.accept(this);
        this.types.forEach((id, builder) -> consumer.accept(id, builder.createType()));
    }

    public static class DamageTypeBuilder {
        private final String msgId;

        private DamageScaling scaling = DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER;
        private float exhaustion = 0.0f;
        private DamageEffects effects = DamageEffects.HURT;
        private DeathMessageType deathMessageType = DeathMessageType.DEFAULT;

        public DamageTypeBuilder(String msgId) {
            this.msgId = msgId;
        }

        public DamageTypeBuilder scaling(DamageScaling scaling) {
            this.scaling = scaling;
            return this;
        }

        public DamageTypeBuilder exhaustion(float exhaustion) {
            this.exhaustion = exhaustion;
            return this;
        }

        public DamageTypeBuilder effects(DamageEffects effects) {
            this.effects = effects;
            return this;
        }

        public DamageTypeBuilder deathMessageType(DeathMessageType deathMessageType) {
            this.deathMessageType = deathMessageType;
            return this;
        }

        public DamageType createType() {
            return new DamageType(this.msgId, this.scaling, this.exhaustion, this.effects, this.deathMessageType);
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/data/MKEnglishProvider.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import thedarkcolour.modkit.MKUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * English language generation.
 * Can even generate names for modded types, just use {@link #addTranslationHandler(Class, Function)} and {@link #addRegistryForAutoTranslation(ResourceKey)}.
 */
@SuppressWarnings({"unchecked", "deprecation", "unused"})
public class MKEnglishProvider extends LanguageProvider {
    private static final Field FIELD_DATA;

    static {
        try {
            FIELD_DATA = LanguageProvider.class.getDeclaredField("data");
            FIELD_DATA.setAccessible(true);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to reflect into LanguageProvider, overrides of generated names will not work!", e);
        }
    }

    private final String modid;
    private final Logger logger;
    private final boolean generateNames;
    @Nullable
    private final Consumer<MKEnglishProvider> addNames;
    private final Map<String, String> data;
    private final Map<Class<?>, Function<Object, String>> registryObjectHandlers;
    private final List<ResourceKey<? extends Registry<?>>> autoTranslatedRegistries;

    @ApiStatus.Internal
    public MKEnglishProvider(PackOutput output, String modid, Logger logger, boolean generateNames, @Nullable Consumer<MKEnglishProvider> addNames) {
        super(output, modid, "en_us");
        this.modid = modid;
        this.logger = logger;
        this.generateNames = generateNames;
        this.addNames = addNames;

        try {
            this.data = (Map<String, String>) FIELD_DATA.get(this);
        } catch (IllegalAccessException ignored) {
            throw new IllegalStateException("Failed to create MKEnglishProvider");
        }

        // Default translation key handlers
        this.registryObjectHandlers = new HashMap<>();
        addTranslationHandler(Block.class, Block::getDescriptionId);
        addTranslationHandler(Item.class, Item::getDescriptionId);
        addTranslationHandler(EntityType.class, EntityType::getDescriptionId);
        addTranslationHandler(MobEffect.class, MobEffect::getDescriptionId);
        addTranslationHandler(Enchantment.class, Enchantment::getDescriptionId);
        addTranslationHandler(ItemStack.class, ItemStack::getDescriptionId);
        addTranslationHandler(FluidType.class, FluidType::getDescriptionId);

        // Registries which will have names generated automatically
        this.autoTranslatedRegistries = new ArrayList<>();
        autoTranslatedRegistries.add(ForgeRegistries.Keys.ITEMS);
        autoTranslatedRegistries.add(ForgeRegistries.Keys.BLOCKS);
        autoTranslatedRegistries.add(ForgeRegistries.Keys.ENTITY_TYPES);
        autoTranslatedRegistries.add(ForgeRegistries.Keys.ENCHANTMENTS);
        autoTranslatedRegistries.add(ForgeRegistries.Keys.FLUID_TYPES);
    }

    @Override
    protected void addTranslations() {
        if (this.addNames != null) {
            this.addNames.accept(this);
        }

        if (this.generateNames) {
            for (ResourceKey<? extends Registry<?>> registryKey : this.autoTranslatedRegistries) {
                var registry = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY).registryOrThrow(registryKey);
                MutableInt i = new MutableInt();

                try {
                    MKUtils.forModRegistry(registry, this.modid, (id, obj) -> {
                        String name = WordUtils.capitalize(id.getPath().replace('_', ' '));
                        String key = getTranslationKey(obj);

                        if (!this.data.containsKey(key)) {
                            add(key, name);
                            i.increment();
                        }
                    });
                } catch (IllegalArgumentException e) {
                    this.logger.error("No translation key handler registered by mod {} for registry {} (use MKEnglishProvider.addTranslationHandler)", this.modid, registryKey.location());
                    continue;
                }

                if (i.intValue() > 0) {
                    this.logger.info("Automatically generated {} names for mod {}'s entries in registry {}", i, this.modid, registryKey.location());
                }
            }
        }
    }

    @Override
    public String getName() {
        return "ModKit Language: en_us for mod '" + this.modid + "'";
    }

    @Override
    public void add(String key, String value) {
        String old = this.data.put(key, value);
        if (old != null && !old.equals(value)) {
            this.logger.info("Overridden/duplicate translation key '" + key + "' (old: '" + old + "' new: '" + value + "')");
        }
    }

    /**
     * If you have a translatable object not included by default for adding translation keys,
     * add a mapping function here so that {@link #add(Object, String)} can properly add
     * translation keys for your specific type of registry object.
     *
     * @param type            The superclass to handle (ex. Block, Item)
     * @param translationKeys The mapping function, returns a translation key for the object (ex. Block::getDescriptionId)
     * @param <T>             The generic type for the registry
     */
    public <T> void addTranslationHandler(Class<T> type, Function<T, String> translationKeys) {
        this.registryObjectHandlers.put(type, (Function<Object, String>) translationKeys);
    }

    /**
     * If your mod adds its own registry, you can register it here so that English names
     * will be automatically generated for its members.
     * <p>
     * IMPORTANT: Make sure to call {@link #addTranslationHandler(Class, Function)} to register a translation key
     * handler for your registryKey objects, otherwise ModKit will not be able to translate them!
     *
     * @param registryKey The ID of the registry to iterate for automatically generating English names
     * @param <T>         The generic type for the registry
     */
    public <T> void addRegistryForAutoTranslation(ResourceKey<? extends Registry<T>> registryKey) {
        if (!this.generateNames) {
            this.logger.error("Tried to automatically generate English names for registryKey {}, but {} MKEnglishProvider has 'generateNames' set to false!", registryKey.location(), this.modid);
            throw new IllegalStateException("MKEnglishGenerator.generateNames is false");
        } else {
            this.autoTranslatedRegistries.add(registryKey);
        }
    }

    public void add(Object key, String name) {
        add(getTranslationKey(key), name);
    }

    public void addGeneric(RegistryObject<?> key, String name) {
        add(key.get(), name);
    }

    public String getTranslationKey(Object object) {
        for (var entry : this.registryObjectHandlers.entrySet()) {
            if (entry.getKey().isInstance(object)) {
                return entry.getValue().apply(object);
            }
        }

        throw new IllegalArgumentException("Unsupported registry object type for translation keys");
    }
}
```

### src/main/java/thedarkcolour/modkit/data/MKItemModelProvider.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import thedarkcolour.modkit.MKUtils;
import thedarkcolour.modkit.data.model.SafeItemModelBuilder;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class MKItemModelProvider extends ModelProvider<SafeItemModelBuilder> {
    private final Logger logger;
    private final boolean generate3dBlockItems;
    private final boolean generate2dItems;
    private final boolean generateSpawnEggs;
    @Nullable
    private final Consumer<MKItemModelProvider> addItemModels;
    private final Set<ResourceLocation> excluded = new HashSet<>();

    @ApiStatus.Internal
    public MKItemModelProvider(PackOutput output,
                                  ExistingFileHelper helper,
                                  String modid,
                                  Logger logger,
                                  boolean generate3dBlockItems,
                                  boolean generate2dItems,
                                  boolean generateSpawnEggs,
                                  @Nullable Consumer<MKItemModelProvider> addItemModels) {
        super(output, modid, "item", (outputLoc, efh) -> new SafeItemModelBuilder(outputLoc, logger, efh), helper);

        this.logger = logger;
        this.generate3dBlockItems = generate3dBlockItems;
        this.generate2dItems = generate2dItems;
        this.generateSpawnEggs = generateSpawnEggs;
        this.addItemModels = addItemModels;
    }

    public void exclude(RegistryObject<? extends ItemLike> item) {
        exclude(item.get());
    }

    /**
     * If any of {@link #generate2dItems}, {@link #generate3dBlockItems}, {@link #generateSpawnEggs} are true,
     * this method prevents the specified item from having a model autogenerated for it.
     * @param item Will have no models automatically generated by ModKit (can still add them in #addItemModels)
     */
    public void exclude(ItemLike item) {
        excluded.add(extendWithFolder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem()))));
    }

    public void exclude(ResourceLocation id) {
        excluded.add(extendWithFolder(id));
    }

    public SafeItemModelBuilder generic2d(RegistryObject<? extends ItemLike> supplier) {
        // Don't use the registry object id because item id may be different
        return generic2d(supplier.get());
    }

    public SafeItemModelBuilder generic2d(ItemLike item) {
        return generic2d(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem())));
    }

    /**
     * Makes a 2d single layer item like hopper, gold ingot, or redstone dust item models
     */
    public SafeItemModelBuilder generic2d(ResourceLocation itemId) {
        return layer0(itemId, "item/generated");
    }

    public SafeItemModelBuilder handheld(RegistryObject<? extends Item> supplier) {
        return handheld(supplier.getId());
    }

    /**
     * Makes a 2d single layer item with special transformations like the pickaxe or sword models.
     */
    public SafeItemModelBuilder handheld(ResourceLocation itemId) {
        return layer0(itemId, "item/handheld");
    }

    public SafeItemModelBuilder layer0(ResourceLocation itemId, String parentName) {
        String path = itemId.getPath();

        return getBuilder(path)
                .parent(new ModelFile.UncheckedModelFile(parentName)) // handheld
                .texture("layer0", new ResourceLocation(itemId.getNamespace(), "item/" + path));
    }

    /**
     * Makes a 3d cube of a block for item model
     */
    public SafeItemModelBuilder generic3d(RegistryObject<? extends Item> supplier) {
        String path = supplier.getId().getPath();
        return withExistingParent(path, new ResourceLocation(supplier.getId().getNamespace(), "block/" + path));
    }

    public SafeItemModelBuilder generic3d(ResourceLocation id) {
        String path = id.getPath();
        return withExistingParent(path, new ResourceLocation(id.getNamespace(), "block/" + path));
    }

    private SafeItemModelBuilder spawnEgg(RegistryObject<? extends Item> supplier) {
        return spawnEgg(supplier.getId());
    }

    private SafeItemModelBuilder spawnEgg(ResourceLocation itemId) {
        return getBuilder(itemId.getPath()).parent(new ModelFile.UncheckedModelFile("item/template_spawn_egg"));
    }

    @Override
    public SafeItemModelBuilder withExistingParent(String name, ResourceLocation parent) {
        try {
            return super.withExistingParent(name, parent);
        } catch (IllegalStateException e) {
            logger.error(e.getMessage());
            return getBuilder(name).parent(new ModelFile.UncheckedModelFile(parent));
        }
    }

    public ResourceLocation extendWithFolder(ResourceLocation rl) {
        if (rl.getPath().contains("/")) {
            return rl;
        }
        return new ResourceLocation(rl.getNamespace(), folder + "/" + rl.getPath());
    }

    @Override
    public String getName() {
        return "ModKit Item Models for mod '" + modid + "'";
    }

    @Override
    protected void registerModels() {
        if (addItemModels != null) {
            addItemModels.accept(this);
        }

        if (generate3dBlockItems || generate2dItems || generateSpawnEggs) {
            MKUtils.forModRegistry(Registries.ITEM, modid, (id, item) -> {
                if (excluded.contains(id) || excluded.contains(extendWithFolder(id))) return;

                if (generate3dBlockItems && item instanceof BlockItem) {
                    generic3d(id);
                } else if (generateSpawnEggs && item instanceof SpawnEggItem) {
                    spawnEgg(id);
                } else if (generate2dItems) {
                    if (item instanceof ShovelItem || item instanceof SwordItem || item instanceof HoeItem || item instanceof AxeItem || item instanceof PickaxeItem) {
                        handheld(id);
                    } else {
                        generic2d(id);
                    }
                }
            });
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/data/MKRecipeProvider.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import thedarkcolour.modkit.data.recipe.NbtShapedRecipeBuilder;
import thedarkcolour.modkit.data.recipe.NbtShapelessRecipeBuilder;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static net.minecraft.data.recipes.SmithingTransformRecipeBuilder.smithing;

/**
 * ModKit's implementation of RecipeProvider, along with some static utility methods which can be found at the bottom of this file.
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class MKRecipeProvider extends RecipeProvider {
    private final String modid;
    private final BiConsumer<Consumer<FinishedRecipe>, MKRecipeProvider> addRecipes;
    @Nullable
    private Consumer<FinishedRecipe> writer;

    protected MKRecipeProvider(PackOutput output, String modid, BiConsumer<Consumer<FinishedRecipe>, MKRecipeProvider> addRecipes) {
        super(output);
        this.modid = modid;
        this.addRecipes = addRecipes;
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        this.writer = writer;
        this.addRecipes.accept(writer, this);
        this.writer = null;
    }

    public void conditional(String recipeId, List<ICondition> conditions, Consumer<Consumer<FinishedRecipe>> addRecipes) {
        conditional(new ResourceLocation(this.modid, recipeId), conditions, addRecipes);
    }

    /**
     * Allows creation of conditional recipes.
     *
     * @param recipeId   The ID of the conditional recipe
     * @param conditions The list of conditions used for all recipe(s) added in addRecipes
     * @param addRecipes Add recipe(s) to the conditional recipe. Make sure you are using the Consumer from this lambda!
     */
    public void conditional(ResourceLocation recipeId, List<ICondition> conditions, Consumer<Consumer<FinishedRecipe>> addRecipes) {
        Preconditions.checkNotNull(this.writer);
        Preconditions.checkArgument(!conditions.isEmpty(), "Cannot add a recipe with no conditions.");

        var builder = ConditionalRecipe.builder();

        pushWriter(recipe -> {
            for (var condition : conditions) {
                builder.addCondition(condition);
            }
            builder.addRecipe(recipe);
        }, addRecipes);

        builder.build(this.writer, recipeId);
    }

    /**
     * This method temporarily changes the {@link Consumer<FinishedRecipe>} used for the finished recipe writer.
     * By default, the writer used by MKRecipeProvider is provided in {@link RecipeProvider#run(CachedOutput)}.
     * This method may be useful when an alternative behavior for handling FinishedRecipes generated by this class
     * is desired. One example is with {@link ConditionalRecipe}, which provides its own recipe builder for
     * accepting multiple recipes.
     *
     * @param newWriter The finished recipe consumer used to handle FinishedRecipe generated in this method call
     * @param action    A consumer which receives the new recipe writer for generating new recipes
     * @see #conditional(String, List, Consumer)
     */
    public void pushWriter(Consumer<FinishedRecipe> newWriter, Consumer<Consumer<FinishedRecipe>> action) {
        Preconditions.checkNotNull(newWriter);

        var realWriter = this.writer;
        this.writer = newWriter;

        try {
            action.accept(this.writer);
        } finally {
            this.writer = realWriter;
        }
    }

    /**
     * When you run into conflicting recipe names, put the conflicting recipe inside the runnable of this method and
     * pass the desired name of the recipe as name.
     * <p>
     * Example:
     * // one recipe that has the same name
     * recipes.renameRecipes(oldName -> oldName.withSuffix("_from_block", newWriter -> {
     *   // another recipe that has the same name
     * });
     *
     * @param renaming The function responsible for renaming the recipes.
     * @param runnable Add your recipes here. You must use the recipe writer passed here INSTEAD of the original recipe writer variable.
     */
    public void renameRecipes(UnaryOperator<ResourceLocation> renaming, Consumer<Consumer<FinishedRecipe>> runnable) {
        Consumer<FinishedRecipe> oldWriter = this.writer;

        pushWriter(finishedRecipe -> {
            oldWriter.accept(new FinishedRecipe() {
                @Override
                public void serializeRecipeData(JsonObject pJson) {
                    finishedRecipe.serializeRecipeData(pJson);
                }

                @Override
                public ResourceLocation getId() {
                    return renaming.apply(finishedRecipe.getId());
                }

                @Override
                public RecipeSerializer<?> getType() {
                    return finishedRecipe.getType();
                }

                @Nullable
                @Override
                public JsonObject serializeAdvancement() {
                    return finishedRecipe.serializeAdvancement();
                }

                @Nullable
                @Override
                public ResourceLocation getAdvancementId() {
                    return finishedRecipe.getAdvancementId();
                }
            });
        }, runnable);
    }

    public void shapedCrafting(String recipeId, RecipeCategory category, ItemLike result, Consumer<NbtShapedRecipeBuilder> recipe) {
        shapedCrafting(recipeId, category, result, 1, recipe);
    }

    public void shapedCrafting(String recipeId, RecipeCategory category, ItemLike result, int resultCount, Consumer<NbtShapedRecipeBuilder> recipe) {
        shapedCrafting(recipeId, category, result, resultCount, null, recipe);
    }

    public void shapedCrafting(RecipeCategory category, ItemLike result, Consumer<NbtShapedRecipeBuilder> recipe) {
        shapedCrafting(category, result, 1, recipe);
    }

    public void shapedCrafting(RecipeCategory category, ItemLike result, int resultCount, Consumer<NbtShapedRecipeBuilder> recipe) {
        shapedCrafting(category, result, resultCount, null, recipe);
    }

    public void shapedCrafting(RecipeCategory category, ItemLike result, int resultCount, @Nullable CompoundTag resultNbt, Consumer<NbtShapedRecipeBuilder> recipe) {
        shapedCrafting(null, category, result, resultCount, resultNbt, recipe);
    }

    /**
     * Generates a shaped recipe with the recipe layout defined by the {@code recipe} Consumer.
     * Will make a best-guess attempt for an unlockedBy criterion, using the first tag/item declared in the key.
     *
     * @param recipeId    Recipe id to use when generating the recipe, or null for the default name.
     * @param category    Recipe category for displaying in the green recipe book
     * @param result      The result item
     * @param resultCount The number of result items resulting from one craft of this recipe
     * @param resultNbt   The NBT of the result item(s)
     * @param recipe      Function, usually a lambda, which defines the recipe layout by calling define and key on the recipe builder.
     */
    public void shapedCrafting(@Nullable String recipeId, RecipeCategory category, ItemLike result, int resultCount, @Nullable CompoundTag resultNbt, Consumer<NbtShapedRecipeBuilder> recipe) {
        Preconditions.checkNotNull(this.writer);

        NbtShapedRecipeBuilder builder = new NbtShapedRecipeBuilder(category, result, resultCount, resultNbt);
        recipe.accept(builder);
        if (builder.isMissingCriterion()) {
            builder.attemptAutoCriterion();
        }

        ResourceLocation id = createRecipeId(recipeId, builder.getResult());

        builder.save(this.writer, id);
    }

    public ResourceLocation defaultRecipeId(ItemLike result) {
        return createRecipeId(null, result);
    }

    /**
     * Returns a recipe ID for the given string or returns a default recipe ID based on the result item. Unlike Vanilla
     * data generation, your {@link #modid} is the default namespace, avoiding accidental recipe conflicts.
     *
     * @param recipeId A (nullable) recipe ID to convert. Default namespace is {@link #modid}, NOT "minecraft"
     * @param result   The resulting item of the crafting recipe, used only when recipeId is null.
     * @return An ID to use for a newly generated recipe
     */
    public ResourceLocation createRecipeId(@Nullable String recipeId, ItemLike result) {
        if (recipeId != null) {
            if (recipeId.contains(":")) {
                return new ResourceLocation(recipeId);
            } else {
                return new ResourceLocation(this.modid, recipeId);
            }
        } else {
            return new ResourceLocation(this.modid, MKRecipeProvider.path(result));
        }
    }

    /**
     * Simplest overload which accepts a category, result, and count.
     */
    public void shapelessCrafting(RecipeCategory category, ItemLike result, int resultCount, Object... ingredients) {
        shapelessCrafting(category, new ItemStack(result, resultCount, null), ingredients);
    }

    /**
     * Overload that accepts a group.
     */
    public void shapelessCrafting(RecipeCategory category, ItemLike result, int resultCount, @Nullable String group, Object... ingredients) {
        shapelessCrafting(category, new ItemStack(result, resultCount, null), ingredients);
    }

    /**
     * Overload that accepts an ID path.
     */
    public void shapelessCrafting(String path, RecipeCategory category, ItemLike result, int resultCount, Object... ingredients) {
        shapelessCrafting(new ResourceLocation(this.modid, path), category, result, resultCount, ingredients);
    }

    /**
     * Overload that accepts an ID.
     */
    public void shapelessCrafting(ResourceLocation id, RecipeCategory category, ItemLike result, int resultCount, Object... ingredients) {
        shapelessCrafting(id, category, new ItemStack(result, resultCount, null), null, ingredients);
    }

    /**
     * Overload that accepts an ItemStack instead of a result and count.
     */
    public void shapelessCrafting(RecipeCategory category, ItemStack result, Object... ingredients) {
        shapelessCrafting(category, result, null, null, ingredients);
    }

    /**
     * Overload that accepts a group and an ItemStack instead of a result and count.
     */
    public void shapelessCrafting(RecipeCategory category, ItemStack result, @Nullable String group, Object... ingredients) {
        shapelessCrafting(category, result, group, null, ingredients);
    }

    /**
     * Overload that accepts an ID path and recipe group.
     */
    public void shapelessCrafting(String path, RecipeCategory category, ItemLike result, int resultCount, @Nullable String group, Object... ingredients) {
        shapelessCrafting(new ResourceLocation(this.modid, path), category, result, resultCount, ingredients);
    }

    /**
     * Overload that accepts an ID and an ItemStack instead of a result and count.
     */
    public void shapelessCrafting(RecipeCategory category, ItemStack result, @Nullable Pair<String, CriterionTriggerInstance> unlockedBy, Object... ingredients) {
        shapelessCrafting(null, category, result, unlockedBy, ingredients);
    }

    public void shapelessCrafting(@Nullable ResourceLocation id, RecipeCategory category, ItemStack result, @Nullable Pair<String, CriterionTriggerInstance> unlockedBy, Object... ingredients) {
        shapelessCrafting(id, category, result, null, unlockedBy, ingredients);
    }

    public void shapelessCrafting(RecipeCategory category, ItemStack result, @Nullable String group, @Nullable Pair<String, CriterionTriggerInstance> unlockedBy, Object... ingredients) {
        shapelessCrafting(null, category, result, group, unlockedBy, ingredients);
    }

    /**
     * Generates a shapeless recipe with a list of ingredients (can be a mix of ItemLike, Ingredient, and/or TagKey)
     * and attempts to also generate a recipe criterion so (hopefully) you don't need to call {@code unlockedBy}.
     * <p>
     * Additionally, it is possible to use {@link ObjectIntPair} or {@link IntObjectPair} containing one of the above
     * types to specify that the ingredient should appear multiple times (specified by the integer of the pair). This
     * helps avoid repetition of the same ingredient several times in the ingredients list.
     * <p>
     * There are many overloads that accept different variations and combinations of these arguments in the same order.
     * Generally, recipes start with an optional ID (string or ResourceLocation), a category, a result (ItemStack or
     * pair of item + count), then an optional group name, an optional unlock criterion.
     * The last argument is always the list of ingredients.
     *
     * @param id          The ID to use for this recipe. If {@code null}, then one is chosen according to {@link RecipeBuilder#getDefaultRecipeId}.
     * @param category    The recipe category for showing in the green recipe book
     * @param result      The resulting item of this recipe (NBT and count are included in the generated recipe)
     * @param group       A nullable group name that this recipe should be displayed with in the recipe book.
     * @param unlockedBy  A (nullable) pair of criterion name and criterion instance for unlocking the recipe.
     *                    In most cases it is easier to leave this null, but it may be desirable to pick a specific
     *                    criterion or required if ModKit cannot determine a criterion automatically.
     * @param ingredients Can be ItemLike, Ingredient, RegistryObject or TagKey. Can also be ObjectIntPair or IntObjectPair of one of the previous types.
     * @throws IllegalArgumentException if any element of {@code ingredients} is not ItemLike, Ingredient, or TagKey,
     *                                  or if {@code ingredients} exceeds 9 ingredients, including any expanded pairs.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void shapelessCrafting(@Nullable ResourceLocation id, RecipeCategory category, ItemStack result, @Nullable String group, @Nullable Pair<String, CriterionTriggerInstance> unlockedBy, Object... ingredients) {
        Preconditions.checkNotNull(writer);

        NbtShapelessRecipeBuilder shapeless = new NbtShapelessRecipeBuilder(category, result.getItem(), result.getCount(), result.getTag());

        if (group != null) {
            shapeless.group(group);
        }

        if (unlockedBy != null) {
            shapeless.unlockedBy(unlockedBy.left(), unlockedBy.right());
        } else {
            boolean noCriterion = true;
            // Expand the ObjectIntPair and IntObjectPair ingredients to several references to the same ingredient
            ArrayList<Object> rawIngredients = expandPairIngredients(ingredients);

            for (Object ingredient : rawIngredients) {
                Preconditions.checkNotNull(ingredient);

                if (ingredient instanceof RegistryObject<?> obj) {
                    ingredient = obj.get();
                    Preconditions.checkArgument(ingredient instanceof ItemLike);
                }

                if (ingredient instanceof ItemLike itemLike) {
                    shapeless.requires(itemLike);

                    if (noCriterion) {
                        MKRecipeProvider.unlockedByHaving(shapeless, itemLike);
                        noCriterion = false;
                    }
                } else if (ingredient instanceof TagKey tagKey) {
                    shapeless.requires(tagKey);

                    if (noCriterion) {
                        MKRecipeProvider.unlockedByHaving(shapeless, tagKey);
                        noCriterion = false;
                    }
                } else if (ingredient instanceof Ingredient ing) {
                    shapeless.requires(ing);

                    if (noCriterion) {
                        noCriterion = !MKRecipeProvider.unlockedByHaving(shapeless, ing);
                    }
                } else {
                    throw MKRecipeProvider.nonIngredientArgument(ingredient);
                }
            }

            if (noCriterion && shapeless.isMissingCriterion()) {
                throw new IllegalStateException("Argument list must contain one TagKey or ItemLike for adding automatic advancement criterion");
            }
        }

        if (id != null) {
            shapeless.save(this.writer, id);
        } else {
            shapeless.save(this.writer);
        }
    }

    // Helper method to handle ingredient-object pairs
    private static ArrayList<Object> expandPairIngredients(Object... ingredients) {
        ArrayList<Object> flattened = new ArrayList<>();

        for (Object o : ingredients) {
            // Default is to just add the ingredient once
            Object ingredient = o;
            int count = 1;

            // Handle pairs
            if (ingredient instanceof ObjectIntPair<?> pair) {
                count = pair.rightInt();
                ingredient = pair.left();
            } else if (ingredient instanceof IntObjectPair<?> pair) {
                count = pair.leftInt();
                ingredient = pair.right();
            }

            // make it clear that recursive expanding is not permitted
            if (ingredient instanceof ObjectIntPair<?> || ingredient instanceof IntObjectPair<?>) {
                throw new IllegalArgumentException("Cannot have an ingredient which is a pair of pairs");
            }

            // Add the correct ingredient the correct number of times
            for (int j = 0; j < count; j++) {
                flattened.add(ingredient);
            }
        }

        if (flattened.size() > 9) {
            throw new IllegalArgumentException("Cannot have more than 9 ingredients in a shapeless crafting recipe");
        }

        return flattened;
    }

    /**
     * Template for recipes which convert ingot <---> block. Also works for nugget <---> ingot.
     * Two recipes are generated by this method, but the recipe to convert from storage back
     * into material has the id "[modid]:[material]_from_storage" to avoid conflicts.
     *
     * @param storage  The result of the 3x3 recipe (iron block from ingots, iron ingot from nuggets, etc.)
     * @param material The ingredient of the 3x3 (iron ingot for block, iron nugget for ingot, diamond for block, etc.)
     */
    public void storage3x3(ItemLike storage, ItemLike material) {
        Preconditions.checkNotNull(this.writer);

        grid3x3(RecipeCategory.BUILDING_BLOCKS, storage, Ingredient.of(material));

        ShapelessRecipeBuilder fromStorage = new ShapelessRecipeBuilder(RecipeCategory.MISC, material, 9);
        unlockedByHaving(fromStorage, storage);
        fromStorage.requires(storage);
        fromStorage.save(this.writer, id(material).withSuffix("_from_" + id(storage).getPath()));
    }

    public void grid3x3(ItemLike result, Ingredient ingredient) {
        this.grid3x3(RecipeCategory.MISC, result, ingredient);
    }

    public void grid3x3(RecipeCategory category, ItemLike result, Ingredient ingredient) {
        Preconditions.checkNotNull(this.writer);

        shapedCrafting(category, result, recipe -> {
            recipe.define('#', ingredient);
            recipe.pattern("###");
            recipe.pattern("###");
            recipe.pattern("###");
        });
    }

    /**
     * @deprecated Use the version which accepts a RecipeCategory
     */
    @ApiStatus.ScheduledForRemoval(inVersion = "1.21")
    @Deprecated(forRemoval = true)
    public void grid2x2(ItemLike result, Ingredient ingredient) {
        grid2x2(RecipeCategory.MISC, result, ingredient);
    }

    public void grid2x2(RecipeCategory category, ItemLike result, Ingredient ingredient) {
        grid2x2(category, result, 1, ingredient);
    }

    public void grid2x2(RecipeCategory category, ItemLike result, ItemLike ingredient) {
        grid2x2(category, result, 1, ingredient);
    }

    public void grid2x2(RecipeCategory category, ItemLike result, int resultCount, Ingredient ingredient) {
        grid2x2(category, result, resultCount, ingredient, null);
    }

    public void grid2x2(RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient) {
        grid2x2(category, result, resultCount, Ingredient.of(ingredient));
    }

    public void grid2x2(RecipeCategory category, ItemLike result, int resultCount, Ingredient ingredient, @Nullable String group) {
        Preconditions.checkNotNull(this.writer);

        shapedCrafting(category, result, recipe -> {
            recipe.define('#', ingredient);
            recipe.pattern("##");
            recipe.pattern("##");
            if (group != null)
                recipe.group(group);
        });
    }

    public void grid3x2(RecipeCategory category, ItemLike result, ItemLike ingredient) {
        grid3x2(category, result, 1, Ingredient.of(ingredient));
    }

    public void grid3x2(RecipeCategory category, ItemLike result, Ingredient ingredient) {
        grid3x2(category, result, 1, ingredient);
    }

    public void grid3x2(RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient) {
        grid3x2(category, result, resultCount, Ingredient.of(ingredient), null);
    }

    public void grid3x2(RecipeCategory category, ItemLike result, int resultCount, Ingredient ingredient) {
        grid3x2(category, result, resultCount, ingredient, null);
    }

    /**
     * A recipe whose ingredients are the same in 3 wide by 2 high grid shape, like a wall or a trapdoor.
     *
     * @param category    The recipe category tab used for displaying in the green recipe book from Vanilla
     * @param result      The result item (ex. Wall, Trapdoor)
     * @param resultCount The number of the result item crafted by this recipe
     * @param ingredient  The ingredient used by every slot of this recipe
     * @param group       If specified, the group of recipes to be shown along with in the green recipe book from Vanilla
     */
    public void grid3x2(RecipeCategory category, ItemLike result, int resultCount, Ingredient ingredient, @Nullable String group) {
        Preconditions.checkNotNull(this.writer);

        shapedCrafting(category, result, resultCount, recipe -> {
            recipe.define('#', ingredient);
            recipe.pattern("###");
            recipe.pattern("###");
            if (group != null)
                recipe.group(group);
        });
    }

    public void grid2x3(RecipeCategory category, ItemLike result, ItemLike ingredient) {
        grid2x3(category, result, 1, Ingredient.of(ingredient));
    }

    public void grid2x3(RecipeCategory category, ItemLike result, Ingredient ingredient) {
        grid2x3(category, result, 1, ingredient);
    }

    public void grid2x3(RecipeCategory category, ItemLike result, int resultCount, ItemLike ingredient) {
        grid2x3(category, result, resultCount, Ingredient.of(ingredient), null);
    }

    public void grid2x3(RecipeCategory category, ItemLike result, int resultCount, Ingredient ingredient) {
        grid2x3(category, result, resultCount, ingredient, null);
    }

    /**
     * A recipe whose ingredients are the same in 2 wide by 3 high grid shape, like a door.
     *
     * @param category    The recipe category tab used for displaying in the green recipe book from Vanilla
     * @param result      The result item (ex. Door)
     * @param resultCount The number of the result item crafted by this recipe
     * @param ingredient  The ingredient used by every slot of this recipe
     * @param group       If specified, the group of recipes to be shown along with in the green recipe book from Vanilla
     */
    public void grid2x3(RecipeCategory category, ItemLike result, int resultCount, Ingredient ingredient, @Nullable String group) {
        Preconditions.checkNotNull(this.writer);

        shapedCrafting(category, result, resultCount, recipe -> {
            recipe.define('#', ingredient);
            recipe.pattern("##");
            recipe.pattern("##");
            recipe.pattern("##");
            if (group != null)
                recipe.group(group);
        });
    }

    public void woodenDoor(ItemLike result, ItemLike input) {
        woodenDoor(result, Ingredient.of(input));
    }

    public void woodenDoor(ItemLike result, Ingredient ingredient) {
        grid2x3(RecipeCategory.REDSTONE, result, 3, ingredient, "wooden_door");
    }

    public void woodenTrapdoor(ItemLike result, ItemLike input) {
        woodenTrapdoor(result, Ingredient.of(input));
    }

    public void woodenTrapdoor(ItemLike result, Ingredient ingredient) {
        grid3x2(RecipeCategory.REDSTONE, result, 2, ingredient, "wooden_trapdoor");
    }

    public void woodenFence(ItemLike fence, ItemLike planks) {
        shapedCrafting(RecipeCategory.BUILDING_BLOCKS, fence, 3, recipe -> {
            recipe.define('#', Tags.Items.RODS_WOODEN);
            recipe.define('W', planks);
            recipe.pattern("W#W");
            recipe.pattern("W#W");
            recipe.group("wooden_fence");
        });
    }

    public void woodenFenceGate(ItemLike fenceGate, ItemLike planks) {
        shapedCrafting(RecipeCategory.BUILDING_BLOCKS, fenceGate, recipe -> {
            recipe.define('#', Tags.Items.RODS_WOODEN);
            recipe.define('W', planks);
            recipe.pattern("#W#");
            recipe.pattern("#W#");
            recipe.group("wooden_fence_gate");
        });
    }

    public void stairs(ItemLike result, ItemLike input) {
        stairs(result, input, null);
    }

    public void stairs(ItemLike result, Ingredient ingredient) {
        stairs(result, ingredient, null);
    }

    public void stairs(ItemLike result, ItemLike input, @Nullable String group) {
        stairs(result, Ingredient.of(input), group);
    }

    public void stairs(ItemLike result, Ingredient ingredient, @Nullable String group) {
        Preconditions.checkNotNull(this.writer);

        shapedCrafting(RecipeCategory.BUILDING_BLOCKS, result, 4, builder -> {
            builder.define('#', ingredient);
            builder.pattern("#  ");
            builder.pattern("## ");
            builder.pattern("###");
            if (group != null) builder.group(group);
        });
    }

    public void woodenStairs(ItemLike result, ItemLike planks) {
        stairs(result, planks, "wooden_stairs");
    }

    public void slab(ItemLike result, ItemLike input) {
        slab(result, input, null);
    }

    public void slab(ItemLike result, Ingredient ingredient) {
        slab(result, ingredient, null);
    }

    public void slab(ItemLike result, ItemLike input, @Nullable String group) {
        slab(result, Ingredient.of(input), group);
    }

    public void slab(ItemLike result, Ingredient ingredient, @Nullable String group) {
        Preconditions.checkNotNull(this.writer);

        shapedCrafting(RecipeCategory.BUILDING_BLOCKS, result, 6, builder -> {
            builder.define('#', ingredient);
            builder.pattern("###");
            if (group != null) builder.group(group);
        });
    }

    public void woodenSlab(ItemLike result, ItemLike planks) {
        slab(result, planks, "wooden_slab");
    }

    public void special(String id, Supplier<? extends RecipeSerializer<? extends CraftingRecipe>> serializer) {
        special(new ResourceLocation(this.modid, id), serializer.get());
    }

    /**
     * Used for special recipes like firework stars or butterfly breeding.
     *
     * @param id         The ID of this recipe.
     * @param serializer The serializer for reading this recipe from a datapack.
     */
    public void special(ResourceLocation id, RecipeSerializer<? extends CraftingRecipe> serializer) {
        Preconditions.checkNotNull(this.writer);

        SpecialRecipeBuilder.special(serializer).save(this.writer, id.toString());
    }

    public void foodCooking(ItemLike input, ItemLike result, float experience) {
        foodCooking(Ingredient.of(input), result, experience);
    }

    public void foodCooking(Ingredient ingredient, ItemLike result, float experience) {
        foodCooking(ingredient, result, experience, 200);
    }

    /**
     * Adds a furnace recipe, smoker recipe, and a campfire recipe for the given ingredient and result.
     * Useful for cooking raw foods into their cooked forms.
     *
     * @param ingredient The input ingredient, ex. Raw Beef
     * @param result     The resulting item, ex. Cooked Beef
     * @param experience The amount of experience points awarded for cooking in the furnace or smoker
     * @param duration   The time to smelt in a regular furnace. Smoker takes 0.5x as long, campfire takes 3x as long.
     */
    public void foodCooking(Ingredient ingredient, ItemLike result, float experience, int duration) {
        smelting(ingredient, result, experience, duration);
        smoking(ingredient, result, experience, duration / 2);
        campfire(ingredient, result, experience, duration * 3);
    }

    public void oreSmelting(ItemLike input, ItemLike result, float experience) {
        oreSmelting(Ingredient.of(input), result, experience);
    }

    public void oreSmelting(Ingredient ingredient, ItemLike result, float experience) {
        oreSmelting(ingredient, result, experience, 200);
    }

    /**
     * Adds a furnace recipe and blast furnace recipe for the given input and output. Ideal for ore recipes.
     *
     * @param ingredient The input ingredient, ex. Raw Gold Ore
     * @param result     The resulting item, ex. Gold Ingot
     * @param experience The amount of experience points awarded for smelting in the furnace or blast furnace
     * @param duration   The time to smelt in a regular furnace. Blast furnace takes 0.5x as long.
     */
    public void oreSmelting(Ingredient ingredient, ItemLike result, float experience, int duration) {
        smelting(ingredient, result, experience, duration);
        blasting(ingredient, result, experience, duration / 2);
    }

    public void smelting(ItemLike input, ItemLike result, float experience) {
        smelting(Ingredient.of(input), result, experience);
    }

    public void smelting(Ingredient ingredient, ItemLike result, float experience) {
        smelting(ingredient, result, experience, 200);
    }

    public void smelting(Ingredient ingredient, ItemLike result, float experience, int duration) {
        genericCooking(RecipeSerializer.SMELTING_RECIPE, ingredient, result, experience, duration);
    }

    public void blasting(ItemLike input, ItemLike result, float experience) {
        blasting(Ingredient.of(input), result, experience);
    }

    public void blasting(Ingredient ingredient, ItemLike result, float experience) {
        blasting(ingredient, result, experience, 100);
    }

    public void blasting(Ingredient ingredient, ItemLike result, float experience, int duration) {
        genericCooking(RecipeSerializer.BLASTING_RECIPE, ingredient, result, experience, duration);
    }

    public void smoking(ItemLike input, ItemLike result, float experience) {
        smoking(Ingredient.of(input), result, experience);
    }

    public void smoking(Ingredient ingredient, ItemLike result, float experience) {
        smoking(ingredient, result, experience, 100);
    }

    public void smoking(Ingredient ingredient, ItemLike result, float experience, int duration) {
        genericCooking(RecipeSerializer.SMOKING_RECIPE, ingredient, result, experience, duration);
    }

    public void campfire(ItemLike input, ItemLike result, float experience) {
        campfire(Ingredient.of(input), result, experience, 600);
    }

    public void campfire(Ingredient ingredient, ItemLike result, float experience) {
        campfire(ingredient, result, experience, 600);
    }

    public void campfire(Ingredient ingredient, ItemLike result, float experience, int duration) {
        genericCooking(RecipeSerializer.CAMPFIRE_COOKING_RECIPE, ingredient, result, experience, duration);
    }

    public void genericCooking(RecipeSerializer<? extends AbstractCookingRecipe> serializer, Ingredient ingredient, ItemLike result, float experience, int duration) {
        genericCooking(RecipeCategory.MISC, serializer, ingredient, result, experience, duration);
    }

    public void genericCooking(RecipeCategory category, RecipeSerializer<? extends AbstractCookingRecipe> serializer, Ingredient ingredient, ItemLike result, float experience, int duration) {
        Preconditions.checkNotNull(this.writer);

        var builder = SimpleCookingRecipeBuilder.generic(ingredient, category, result, experience, duration, serializer);
        unlockedByHaving(builder, ingredient);
        String id = path(result);
        if (serializer == RecipeSerializer.CAMPFIRE_COOKING_RECIPE) {
            id += "_from_campfire_cooking";
        } else if (serializer == RecipeSerializer.BLASTING_RECIPE) {
            id += "_from_blasting";
        } else if (serializer == RecipeSerializer.SMOKING_RECIPE) {
            id += "_from_smoking";
        }
        builder.save(this.writer, createRecipeId(id, result.asItem()));
    }

    public void netheriteUpgrade(RecipeCategory category, Ingredient input, ItemLike result) {
        Preconditions.checkNotNull(this.writer);

        unlockedByHaving(smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), input, Ingredient.of(Tags.Items.INGOTS_NETHERITE), category, result.asItem()), Tags.Items.INGOTS_NETHERITE).save(this.writer, defaultRecipeId(result));
    }

    /**
     * @return The registry name/ID of the given item
     */
    @SuppressWarnings("deprecation")
    public static ResourceLocation id(ItemLike item) {
        return item.asItem().builtInRegistryHolder().key().location();
    }

    /**
     * Takes in an Ingredient and tries to extract its Item or TagKey for making a recipe criterion.
     * This is necessary because an Ingredient cannot be used for normal recipe criterion.
     *
     * @param builder    The recipe builder to add a criterion to (can be RecipeBuilder or the smithing recipe builders)
     * @param ingredient The ingredient to try to use as a criterion
     * @return True if a criterion was added to the recipe
     */
    public static boolean unlockedByHaving(Object builder, Ingredient ingredient) {
        if (ingredient.getItems().length == 1) {
            if (ingredient.toJson() instanceof JsonObject ingredientObj) {
                if (ingredientObj.has("item")) {
                    ItemStack stack = ingredient.getItems()[0];
                    MKRecipeProvider.unlockedByHaving(builder, stack.getItem());
                    return true;
                } else if (ingredientObj.has("tag")) {
                    TagKey<Item> tag = TagKey.create(Registries.ITEM, new ResourceLocation(ingredientObj.get("tag").getAsString()));
                    MKRecipeProvider.unlockedByHaving(builder, tag);
                    return true;
                }
            }
        } else if (ingredient.getItems().length > 1) {
            if (ingredient.toJson() instanceof JsonArray arrayObj) {
                LinkedHashSet<ItemLike> items = new LinkedHashSet<>();
                LinkedHashSet<TagKey<Item>> tags = new LinkedHashSet<>();

                for (JsonElement element : arrayObj) {
                    if (element instanceof JsonObject jsonObj) {
                        if (jsonObj.has("tag")) {
                            tags.add(TagKey.create(Registries.ITEM, new ResourceLocation(jsonObj.get("tag").getAsString())));
                        } else if (jsonObj.has("item")) {
                            items.add(ShapedRecipe.itemFromJson(jsonObj));
                        }
                    }
                }

                if (tags.isEmpty()) {
                    MKRecipeProvider.unlockedByHaving(builder, items.iterator().next());
                } else {
                    MKRecipeProvider.unlockedByHaving(builder, tags.iterator().next());
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Sets a recipe's unlockedBy criterion to InventoryChangeTrigger.TriggerInstance.has(TagKey),
     * which is protected and thus normally restricted to subclasses of RecipeProvider.
     *
     * @param recipeBuilder The recipe builder
     * @param item          The tag the player must have in their inventory to unlock the recipe
     * @return The recipe builder
     */
    public static <T> T unlockedByHaving(T recipeBuilder, ItemLike item) {
        return unlockedBy(recipeBuilder, has(item));
    }

    /**
     * Sets a recipe's unlockedBy criterion to InventoryChangeTrigger.TriggerInstance.has(TagKey),
     * which is protected and thus normally restricted to subclasses of RecipeProvider.
     *
     * @param recipeBuilder The recipe builder
     * @param tag           The tag the player must have in their inventory to unlock the recipe
     * @return The recipe builder
     */
    public static <T> T unlockedByHaving(T recipeBuilder, TagKey<Item> tag) {
        return unlockedBy(recipeBuilder, has(tag));
    }

    private static <T> T unlockedBy(T recipeBuilder, CriterionTriggerInstance criterion) {
        if (recipeBuilder instanceof RecipeBuilder b) {
            b.unlockedBy("has_item", criterion);
        } else if (recipeBuilder instanceof SmithingTrimRecipeBuilder b) {
            b.unlocks("has_item", criterion);
        } else if (recipeBuilder instanceof SmithingTransformRecipeBuilder b) {
            b.unlocks("has_item", criterion);
        } else {
            throw new IllegalArgumentException("Unknown recipe builder type: " + recipeBuilder.getClass().getName());
        }

        return recipeBuilder;
    }

    public static String path(ItemLike item) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.asItem()), "Item " + item.asItem() + " not found in items registry!").getPath();
    }

    public static Ingredient ingredient(ItemLike item) {
        return Ingredient.of(item);
    }

    public static Ingredient ingredient(ItemLike... items) {
        return Ingredient.of(items);
    }

    public static Ingredient ingredient(Supplier<? extends ItemLike> item) {
        return ingredient(item.get());
    }

    @SafeVarargs
    public static Ingredient ingredient(Supplier<? extends ItemLike>... items) {
        ItemLike[] values = new ItemLike[items.length];
        for (int i = 0; i < items.length; i++) {
            values[i] = items[i].get();
        }
        return ingredient(values);
    }

    public static Ingredient ingredient(TagKey<Item> tag) {
        return Ingredient.of(tag);
    }

    @SafeVarargs
    public static Ingredient ingredient(TagKey<Item>... tags) {
        return Ingredient.fromValues(Arrays.stream(tags).map(Ingredient.TagValue::new));
    }

    /**
     * Creates an ingredient with a series of values, a combination of the following types:
     * <ul>
     *     <li>{@link ItemLike}</li>
     *     <li>{@link TagKey}&lt;{@link Item}&gt;</li>
     *     <li>{@link Supplier}&lt;? extends {@link ItemLike}&gt;</li>
     *     <li>{@link Ingredient.Value}</li>
     * </ul>
     *
     * @param values The values the ingredient can match against
     * @return An ingredient with the specified values
     * @throws IllegalArgumentException If any element in {@code values} is not one of the permitted types listed above
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Ingredient ingredient(Object... values) {
        return Ingredient.fromValues(Arrays.stream(values).map(value -> {
            if (value instanceof Ingredient.Value ingredientValue) {
                return ingredientValue;
            } else if (value instanceof TagKey itemTag && itemTag.registry().equals(Registries.ITEM)) {
                return new Ingredient.TagValue(itemTag);
            } else if (value instanceof ItemLike itemLike) {
                return new Ingredient.ItemValue(new ItemStack(itemLike));
            } else if (value instanceof Supplier<?> supplier && supplier.get() instanceof ItemLike itemLike) {
                return new Ingredient.ItemValue(new ItemStack(itemLike));
            } else {
                throw new IllegalArgumentException("Invalid Ingredient value: " + value.getClass() + " is not subclass of Ingredient.Value, TagKey<Item>, ItemLike, or Supplier<? extends ItemLike>");
            }
        }));
    }

    private static IllegalArgumentException nonIngredientArgument(Object item) {
        return new IllegalArgumentException("Argument " + item + " is not instance of Ingredient, TagKey, or ItemLike");
    }
}
```

### src/main/java/thedarkcolour/modkit/data/MKTagsProvider.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.util.Lazy;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * If you are a Kotlin user, this class implements {@link java.util.function.Function} with its "tag" method, so
 * you can write more concisely if you use "tagProvider()" instead of "tagProvider.tag()" to generate tags.
 *
 * @param <T> The type of objects this tag provider is generating tags for.
 */
@SuppressWarnings("deprecation")
public class MKTagsProvider<T> extends TagsProvider<T> implements Function<TagKey<T>, DirectTagAppender<T>> {
    private static final Function<EntityType<?>, ResourceKey<EntityType<?>>> ENTITY_TYPE_KEY_GETTER;
    private static final Function<Item, ResourceKey<Item>> ITEM_KEY_GETTER;
    private static final Function<Block, ResourceKey<Block>> BLOCK_KEY_GETTER;
    private static final Function<GameEvent, ResourceKey<GameEvent>> GAME_EVENT_KEY_GETTER;
    private static final Function<Fluid, ResourceKey<Fluid>> FLUID_KEY_GETTER;
    private final Function<T, ResourceKey<T>> keyGetter;
    private final BiConsumer<MKTagsProvider<T>, HolderLookup.Provider> addTags;
    private final Logger logger;

    // only used for ITEMS registry
    private final Lazy<CompletableFuture<TagLookup<Block>>> blockTags;
    private final Map<TagKey<Block>, TagKey<Item>> tagsToCopy;

    @SuppressWarnings("unchecked")
    protected MKTagsProvider(DataHelper helper, ResourceKey<? extends Registry<T>> registry, BiConsumer<MKTagsProvider<T>, HolderLookup.Provider> addTags) {
        super(helper.event.getGenerator().getPackOutput(), registry, helper.event.getLookupProvider(), helper.modid, helper.event.getExistingFileHelper());

        this.keyGetter = chooseKeyGetter(registry);
        this.addTags = addTags;
        this.logger = helper.logger;
        this.tagsToCopy = new HashMap<>();
        this.blockTags = Lazy.of(() -> {
            var blockTags = ((MKTagsProvider<Block>) helper.tags.get(Registries.BLOCK));
            if (blockTags == null) {
                return CompletableFuture.completedFuture(null);
            } else {
                return blockTags.contentsGetter();
            }
        });
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        this.addTags.accept(this, lookup);
    }

    @Override
    public DirectTagAppender<T> tag(TagKey<T> tag) {
        var builder = this.getOrCreateRawBuilder(tag);
        return new DirectTagAppender<>(builder, this.keyGetter, this.modId);
    }

    public DirectTagAppender<T> tag(String id) {
        return tag(new ResourceLocation(id));
    }

    public DirectTagAppender<T> tag(ResourceLocation id) {
        return tag(TagKey.create(this.registryKey, id));
    }

    @Override
    public DirectTagAppender<T> apply(TagKey<T> tag) {
        return tag(tag);
    }

    public void copy(TagKey<Block> blockTag, TagKey<Item> itemTag) {
        if (this.registryKey.equals(Registries.ITEM)) {
            this.tagsToCopy.put(blockTag, itemTag);
        } else {
            logger.warn("Tried to copy a block tag in a tag provider for registry " + registryKey.location());
        }
    }

    @Override
    protected CompletableFuture<HolderLookup.Provider> createContentsProvider() {
        if (registryKey.equals(Registries.ITEM)) {
            return super.createContentsProvider().thenCombineAsync(blockTags.get(), (itemTags, blockTags) -> {
                // if no block tags are registered, this will be null per the second MKTagsProvider constructor
                if (blockTags != null) {
                    this.tagsToCopy.forEach((blockTag, itemTag) -> {
                        @SuppressWarnings("unchecked")
                        var builder = this.getOrCreateRawBuilder((TagKey<T>) itemTag);
                        var blockTagBuilder = blockTags.apply(blockTag).orElseThrow(() -> new IllegalStateException("Missing block tag " + itemTag.location())).build();

                        for (var entry : blockTagBuilder) {
                            builder.add(entry);
                        }
                    });
                }
                return itemTags;
            });
        } else {
            return super.createContentsProvider();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T> Function<T, ResourceKey<T>> chooseKeyGetter(ResourceKey<? extends Registry<T>> registry) {
        Function keyGetter;

        if (registry.equals(Registries.ENTITY_TYPE)) {
            keyGetter = ENTITY_TYPE_KEY_GETTER;
        } else if (registry.equals(Registries.BLOCK)) {
            keyGetter = BLOCK_KEY_GETTER;
        } else if (registry.equals(Registries.ITEM)) {
            keyGetter = ITEM_KEY_GETTER;
        } else if (registry.equals(Registries.FLUID)) {
            keyGetter = FLUID_KEY_GETTER;
        } else if (registry.equals(Registries.GAME_EVENT)) {
            keyGetter = GAME_EVENT_KEY_GETTER;
        } else {
            keyGetter = registryKeyGetter(registry);
        }

        return keyGetter;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static <T> Function<T, ResourceKey<T>> registryKeyGetter(ResourceKey<? extends Registry<T>> registry) {
        return obj -> RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY).registryOrThrow(registry).getResourceKey(obj).get();
    }

    static {
        ENTITY_TYPE_KEY_GETTER = entityType -> entityType.builtInRegistryHolder().key();
        ITEM_KEY_GETTER = item -> item.builtInRegistryHolder().key();
        BLOCK_KEY_GETTER = block -> block.builtInRegistryHolder().key();
        GAME_EVENT_KEY_GETTER = gameEvent -> gameEvent.builtInRegistryHolder().key();
        FLUID_KEY_GETTER = fluid -> fluid.builtInRegistryHolder().key();
    }
}
```

### src/main/java/thedarkcolour/modkit/data/model/package-info.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.modkit.data.model;
```

### src/main/java/thedarkcolour/modkit/data/model/SafeBlockModelBuilder.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

public class SafeBlockModelBuilder extends BlockModelBuilder {
    private final Logger logger;

    public SafeBlockModelBuilder(ResourceLocation outputLocation, Logger logger, ExistingFileHelper existingFileHelper) {
        super(outputLocation, existingFileHelper);
        this.logger = logger;
    }

    // Ignore exceptions and generate models anyway
    @Override
    public BlockModelBuilder texture(String key, ResourceLocation texture) {
        try {
            return super.texture(key, texture);
        } catch (IllegalArgumentException e) {
            this.logger.error(e.getMessage());
            this.textures.put(key, texture.toString());
            return this;
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/data/model/SafeBlockModelProvider.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data.model;

import com.google.common.base.Preconditions;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

public class SafeBlockModelProvider extends BlockModelProvider {
    private final Logger logger;

    public SafeBlockModelProvider(PackOutput output, String modid, Logger logger, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
        this.logger = logger;
    }

    @Override
    protected void registerModels() {}

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return CompletableFuture.allOf();
    }

    public ResourceLocation extendWithFolder(ResourceLocation loc) {
        if (loc.getPath().contains("/")) {
            return loc;
        }
        return new ResourceLocation(loc.getNamespace(), folder + "/" + loc.getPath());
    }

    @Override
    public BlockModelBuilder getBuilder(String path) {
        Preconditions.checkNotNull(path, "Path must not be null");
        ResourceLocation outputLoc = extendWithFolder(path.contains(":") ? new ResourceLocation(path) : new ResourceLocation(modid, path));
        this.existingFileHelper.trackGenerated(outputLoc, MODEL);
        return generatedModels.computeIfAbsent(outputLoc, loc -> new SafeBlockModelBuilder(loc, logger, existingFileHelper));
    }

    @Override
    public BlockModelBuilder nested() {
        return new SafeBlockModelBuilder(new ResourceLocation("dummy:dummy"), logger, existingFileHelper);
    }

    @Override
    public BlockModelBuilder withExistingParent(String name, ResourceLocation parent) {
        try {
            return super.withExistingParent(name, parent);
        } catch (IllegalStateException e) {
            logger.error(e.getMessage());
            return getBuilder(name).parent(new ModelFile.UncheckedModelFile(parent));
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/data/model/SafeItemModelBuilder.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data.model;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class SafeItemModelBuilder extends ModelBuilder<SafeItemModelBuilder> {
    private final Logger logger;
    protected List<OverrideBuilder> overrides = new ArrayList<>();

    public SafeItemModelBuilder(ResourceLocation outputLocation, Logger logger, ExistingFileHelper existingFileHelper) {
        super(outputLocation, existingFileHelper);
        this.logger = logger;
    }

    public OverrideBuilder override(ModelFile model) {
        OverrideBuilder ret = new OverrideBuilder(model);
        overrides.add(ret);
        return ret;
    }

    /**
     * Get an existing override builder
     *
     * @param index the index of the existing override builder
     * @return the override builder
     * @throws IndexOutOfBoundsException if {@code} index is out of bounds
     */
    public OverrideBuilder override(int index) {
        Preconditions.checkElementIndex(index, overrides.size(), "override");
        return overrides.get(index);
    }

    @Override
    public JsonObject toJson() {
        JsonObject root = super.toJson();
        if (!overrides.isEmpty()) {
            JsonArray overridesJson = new JsonArray();
            overrides.stream().map(OverrideBuilder::toJson).forEach(overridesJson::add);
            root.add("overrides", overridesJson);
        }
        return root;
    }

    // Ignore exceptions and generate models anyway
    @Override
    public SafeItemModelBuilder texture(String key, ResourceLocation texture) {
        try {
            return super.texture(key, texture);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            textures.put(key, texture.toString());
            return this;
        }
    }

    public class OverrideBuilder {
        private final ModelFile model;
        private final Map<ResourceLocation, Float> predicates;

        public OverrideBuilder(ModelFile model) {
            this.model = model;
            this.predicates = new LinkedHashMap<>();
        }

        public OverrideBuilder predicate(ResourceLocation key, float value) {
            this.predicates.put(key, value);
            return this;
        }

        public SafeItemModelBuilder end() {
            return SafeItemModelBuilder.this;
        }

        // Public because why not
        public JsonObject toJson() {
            JsonObject ret = new JsonObject();
            JsonObject predicatesJson = new JsonObject();
            predicates.forEach((key, val) -> predicatesJson.addProperty(key.toString(), val));
            ret.add("predicate", predicatesJson);
            ret.addProperty("model", model.getLocation().toString());
            return ret;
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/data/package-info.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.modkit.data;
```

### src/main/java/thedarkcolour/modkit/data/recipe/NbtResultRecipe.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class NbtResultRecipe<T extends RecipeBuilder> implements RecipeBuilder {
    protected final RecipeCategory category;
    protected final Item result;
    protected final int resultCount;
    @Nullable
    protected final CompoundTag resultNbt;
    protected final Advancement.Builder advancement = Advancement.Builder.advancement();
    @Nullable
    protected String group;

    public NbtResultRecipe(RecipeCategory category, Item result, int resultCount, @Nullable CompoundTag resultNbt) {
        this.category = category;
        this.result = result;
        this.resultCount = resultCount;
        this.resultNbt = resultNbt;
    }

    @Override
    public T unlockedBy(String name, CriterionTriggerInstance trigger) {
        this.advancement.addCriterion(name, trigger);
        return self();
    }

    @Override
    public T group(@Nullable String group) {
        this.group = group;
        return self();
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    protected void ensureValid(ResourceLocation id) {
        if (isMissingCriterion()) {
            throw new IllegalStateException("Now way of obtaining recipe " + id);
        }
    }

    public boolean isMissingCriterion() {
        return advancement.getCriteria().isEmpty();
    }

    public static String getCategoryName(RecipeCategory category) {
        return switch (category) {
            case BUILDING_BLOCKS -> "building";
            case TOOLS, COMBAT -> "equipment";
            case REDSTONE -> "redstone";
            default -> "misc";
        };
    }

    public static JsonObject serializeResult(Item item, int count, @Nullable CompoundTag nbt) {
        JsonObject resultObj = new JsonObject();
        resultObj.addProperty("item", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString());

        if (count > 1) {
            resultObj.addProperty("count", count);
        }
        if (nbt != null) {
            resultObj.addProperty("nbt", nbt.getAsString());
        }

        return resultObj;
    }

    @SuppressWarnings("unchecked")
    protected final T self() {
        return (T) this;
    }
}
```

### src/main/java/thedarkcolour/modkit/data/recipe/NbtShapedRecipeBuilder.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.chars.CharOpenHashSet;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import thedarkcolour.modkit.data.MKRecipeProvider;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class NbtShapedRecipeBuilder extends NbtResultRecipe<NbtShapedRecipeBuilder> {
    private final List<String> rows = new ArrayList<>();
    private final LinkedHashMap<Character, Ingredient> key = new LinkedHashMap<>(9);
    private boolean showNotification = true;

    public NbtShapedRecipeBuilder(RecipeCategory category, ItemLike result, int resultCount, @Nullable CompoundTag resultNbt) {
        super(category, result.asItem(), resultCount, resultNbt);
    }

    public static NbtShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result) {
        return shaped(category, result, 1);
    }

    public static NbtShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result, int count) {
        return shaped(category, result, count, null);
    }

    public static NbtShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result, int count, @Nullable CompoundTag nbt) {
        return new NbtShapedRecipeBuilder(category, result, count, nbt);
    }

    public NbtShapedRecipeBuilder define(char symbol, RegistryObject<? extends ItemLike> item) {
        return define(symbol, item.get());
    }

    public NbtShapedRecipeBuilder define(char symbol, ItemLike item) {
        return define(symbol, Ingredient.of(item));
    }

    public NbtShapedRecipeBuilder define(char symbol, TagKey<Item> tag) {
        return define(symbol, Ingredient.of(tag));
    }

    public NbtShapedRecipeBuilder define(char symbol, Ingredient ingredient) {
        if (key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
        } else if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            key.put(symbol, ingredient);
            return this;
        }
    }

    public NbtShapedRecipeBuilder pattern(String pattern) {
        if (!rows.isEmpty() && pattern.length() != rows.get(0).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            rows.add(pattern);
            return this;
        }
    }

    /**
     * Whether this recipe displays a toast on the top right of the player's screen upon unlocking.
     * The only Vanilla recipe where this is ever set to false is the Crafting Table recipe, which
     * is also the only recipe the player has unlocked by default.
     */
    public NbtShapedRecipeBuilder showNotification(boolean showNotification) {
        this.showNotification = showNotification;
        return this;
    }

    /**
     * Using the given ingredients, attempts to generate a recipe criterion instead of requiring each
     * recipe to have {@link #unlockedBy} with tons of extra information.
     */
    public void attemptAutoCriterion() {
        for (Ingredient ingredient : key.values()) {
            if (MKRecipeProvider.unlockedByHaving(this, ingredient)) {
                return;
            }
        }
    }

    @Override
    public void save(Consumer<FinishedRecipe> writer, ResourceLocation id) {
        ensureValid(id);
        advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        writer.accept(new Result(id, category, result, resultCount, resultNbt, group, rows, key, advancement, id.withPrefix("recipes/" + category.getFolderName() + "/"), showNotification));
    }

    @Override
    protected void ensureValid(ResourceLocation id) {
        if (rows.isEmpty()) {
            throw new IllegalStateException("No pattern is defined for shaped recipe " + id + "!");
        } else {
            var set = new CharOpenHashSet(key.keySet());
            set.remove(' ');

            for (String s : rows) {
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);

                    if (!key.containsKey(c) && c != ' ') {
                        throw new IllegalStateException("Pattern in recipe " + id + " uses undefined symbol '" + c + "'");
                    }

                    set.remove(c);
                }
            }

            if (!set.isEmpty()) {
                throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + id);
            } else if (rows.size() == 1 && rows.get(0).length() == 1) {
                throw new IllegalStateException("Shaped recipe " + id + " only takes in a single item - should it be a shapeless recipe instead?");
            }
        }

        super.ensureValid(id);
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final RecipeCategory category;
        private final Item result;
        private final int resultCount;
        @Nullable
        private final CompoundTag resultNbt;
        @Nullable
        private final String group;
        private final List<String> pattern;
        private final LinkedHashMap<Character, Ingredient> key;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final boolean showNotification;

        public Result(ResourceLocation id, RecipeCategory category, Item result, int resultCount, @Nullable CompoundTag resultNbt, @Nullable String group, List<String> pattern, LinkedHashMap<Character, Ingredient> key, Advancement.Builder advancement, ResourceLocation advancementId, boolean showNotification) {
            this.id = id;
            this.category = category;
            this.result = result;
            this.resultCount = resultCount;
            this.resultNbt = resultNbt;
            this.group = group;
            this.pattern = pattern;
            this.key = key;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.showNotification = showNotification;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.addProperty("category", getCategoryName(category));

            if (group != null) {
                json.addProperty("group", group);
            }

            JsonArray patternObj = new JsonArray();

            for (String s : pattern) {
                patternObj.add(s);
            }

            json.add("pattern", patternObj);
            JsonObject keyObj = new JsonObject();

            for (var entry : key.entrySet()) {
                keyObj.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
            }

            json.add("key", keyObj);
            json.add("result", serializeResult(result, resultCount, resultNbt));
            json.addProperty("show_notification", showNotification);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return RecipeSerializer.SHAPED_RECIPE;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/data/recipe/NbtShapelessRecipeBuilder.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Same as regular ShapelessRecipeBuilder but you can add an NBT tag to the crafting result,
 * which is supported by Forge but is not included in Minecraft's data generation.
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class NbtShapelessRecipeBuilder extends NbtResultRecipe<NbtShapelessRecipeBuilder> {
    private final List<Ingredient> ingredients = new ArrayList<>();

    public NbtShapelessRecipeBuilder(RecipeCategory category, Item result, int resultCount, @Nullable CompoundTag resultNbt) {
        super(category, result, resultCount, resultNbt);
    }

    public static NbtShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result) {
        return new NbtShapelessRecipeBuilder(category, result.asItem(), 1, null);
    }

    public static NbtShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, CompoundTag tag) {
        return new NbtShapelessRecipeBuilder(category, result.asItem(), 1, tag);
    }

    public static NbtShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int resultCount) {
        return new NbtShapelessRecipeBuilder(category, result.asItem(), resultCount, null);
    }

    public static NbtShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int resultCount, CompoundTag tag) {
        return new NbtShapelessRecipeBuilder(category, result.asItem(), resultCount, tag);
    }

    public NbtShapelessRecipeBuilder requires(TagKey<Item> tag) {
        return requires(Ingredient.of(tag));
    }

    public NbtShapelessRecipeBuilder requires(ItemLike tag) {
        return requires(Ingredient.of(tag));
    }

    public NbtShapelessRecipeBuilder requires(ItemLike tag, int quantity) {
        return requires(Ingredient.of(tag), quantity);
    }

    public NbtShapelessRecipeBuilder requires(Ingredient ingredient) {
        return requires(ingredient, 1);
    }

    public NbtShapelessRecipeBuilder requires(Ingredient ingredient, int quantity) {
        for (int i = 0; i < quantity; i++) {
            ingredients.add(ingredient);
        }

        return this;
    }

    @Override
    public void save(Consumer<FinishedRecipe> writer, ResourceLocation id) {
        ensureValid(id);
        advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        writer.accept(new Result(id, category, this.result, resultCount, resultNbt, this.group, this.ingredients, this.advancement, id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final RecipeCategory category;
        private final Item result;
        private final int resultCount;
        @Nullable
        private final CompoundTag resultNbt;
        @Nullable
        private final String group;
        private final List<Ingredient> ingredients;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, RecipeCategory category, Item result, int resultCount, @Nullable CompoundTag resultNbt, @Nullable String group, List<Ingredient> ingredients, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.category = category;
            this.result = result;
            this.resultCount = resultCount;
            this.resultNbt = resultNbt;
            this.group = group;
            this.ingredients = ingredients;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.addProperty("category", getCategoryName(category));

            if (group != null) {
                json.addProperty("group", group);
            }
            JsonArray array = new JsonArray();

            for (var ingredient : ingredients) {
                array.add(ingredient.toJson());
            }

            json.add("ingredients", array);
            json.add("result", serializeResult(result, resultCount, resultNbt));
        }

        @Override
        public RecipeSerializer<?> getType() {
            return RecipeSerializer.SHAPELESS_RECIPE;
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/data/recipe/package-info.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.modkit.data.recipe;
```

### src/main/java/thedarkcolour/modkit/item/AbstractFillWand.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFillWand extends Item {
    protected final Map<Player, Map<BlockPos, BlockState>> undoMap = new HashMap<>();

    public AbstractFillWand(Properties pProperties) {
        super(pProperties);
    }

    protected abstract MutableComponent getFillMessage();

    protected void fill(ItemStack stack, BlockState state, BlockPos pos, Level level, @Nullable Player player) {
        var startPosNbt = stack.getTagElement("StartPos");
        if (startPosNbt != null) {
            var startPos = NbtUtils.readBlockPos(startPosNbt);
            var builder = ImmutableMap.<BlockPos, BlockState>builder();

            for (var blockPos : BlockPos.betweenClosed(startPos, pos)) {
                var immutable = blockPos.immutable();
                builder.put(immutable, level.getBlockState(immutable));
                level.setBlock(immutable, state, 2);
            }

            if (player != null) {
                undoMap.put(player, builder.build());

                player.displayClientMessage(getFillMessage().append(String.format("(%d %d %d) to (%d %d %d)", startPos.getX(), startPos.getY(), startPos.getZ(), pos.getX(), pos.getY(), pos.getZ())), true);
            }
            stack.removeTagKey("StartPos");
        }
    }

    protected void saveStartPos(ItemStack stack, BlockPos pos, @Nullable Player player) {
        stack.addTagElement("StartPos", NbtUtils.writeBlockPos(pos));
        if (player != null) {
            player.displayClientMessage(Component.literal(String.format("Starting position: %d %d %d", pos.getX(), pos.getY(), pos.getZ())), true);
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 40;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand hand) {
        if (!pLevel.isClientSide) {
            if (pPlayer.isShiftKeyDown()) {
                pPlayer.getItemInHand(hand).removeTagKey("StartPos");
                pPlayer.displayClientMessage(Component.literal("Cleared start position"), true);
            } else if (undoMap.get(pPlayer) != null) {
                pPlayer.displayClientMessage(Component.literal("Hold to undo"), true);
                pPlayer.startUsingItem(hand);
            }
        }

        return InteractionResultHolder.pass(pPlayer.getItemInHand(hand));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();

        if (!level.isClientSide) {
            var stack = context.getItemInHand();
            var pos = context.getClickedPos();
            var player = context.getPlayer();

            if (player != null) {
                handleUse(level, stack, pos, player);
            }
        }

        return InteractionResult.SUCCESS;
    }

    protected abstract void handleUse(Level level, ItemStack stack, BlockPos pos, Player player);

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            var undoBlocks = undoMap.get(player);

            if (undoBlocks != null) {
                for (var entry : undoBlocks.entrySet()) {
                    level.setBlock(entry.getKey(), entry.getValue(), 2);
                }
                player.displayClientMessage(Component.literal("Undo!"), true);
                undoMap.remove(player);
            }
        }

        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advanced) {
        var startPosNbt = stack.getTagElement("StartPos");
        if (startPosNbt != null) {
            var startPos = NbtUtils.readBlockPos(startPosNbt);
            tooltip.add(Component.literal("Start Position: (" + startPos.getX() + ", " + startPos.getY() + ", " + startPos.getZ() + ")"));
        } else {
            tooltip.add(Component.literal("Tip: Hold sneak click in the air to undo last operation").withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        return stack.getTagElement("StartPos") == null ? super.getName(stack) : Component.translatable(this.getDescriptionId(stack)).append("*");
    }
}
```

### src/main/java/thedarkcolour/modkit/item/ClearWandItem.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClearWandItem extends AbstractFillWand {
    public ClearWandItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MutableComponent getFillMessage() {
        return Component.literal("Cleared blocks from ");
    }

    @Override
    protected void handleUse(Level level, ItemStack stack, BlockPos pos, Player player) {
        if (stack.getTagElement("StartPos") == null) {
            saveStartPos(stack, pos, player);
        } else {
            player.getCooldowns().addCooldown(this, 5);
            fill(stack, Blocks.AIR.defaultBlockState(), pos, level, player);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advanced) {
        super.appendHoverText(stack, level, tooltip, advanced);
    }
}
```

### src/main/java/thedarkcolour/modkit/item/CloneWandItem.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class CloneWandItem extends AbstractFillWand {
    private final Map<Player, ImmutableMap<BlockPos, BlockState>> structureMap = new HashMap<>();

    public CloneWandItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MutableComponent getFillMessage() {
        return Component.literal("Placed the structure ");
    }

    @Override
    protected void handleUse(Level level, ItemStack stack, BlockPos pos, Player player) {
        if (player.isShiftKeyDown()) {
            var startPosNbt = stack.getTagElement("StartPos");
            if (startPosNbt == null) {
                saveStartPos(stack, pos, player);
            } else {
                var builder = ImmutableMap.<BlockPos, BlockState>builder();
                var start = NbtUtils.readBlockPos(startPosNbt);

                for (var mutable : BlockPos.betweenClosed(start, pos)) {
                    builder.put(mutable.subtract(start), level.getBlockState(mutable));
                }

                structureMap.put(player, builder.build());
                player.displayClientMessage(Component.literal(String.format("Saved blocks from (%d %d %d) to (%d %d %d)", start.getX(), start.getY(), start.getZ(), pos.getX(), pos.getY(), pos.getZ())), true);
                stack.removeTagKey("StartPos");
            }
        } else {
            if (!structureMap.containsKey(player)) return;
            var pos2BlockState = structureMap.get(player).entrySet();
            var builder = ImmutableMap.<BlockPos, BlockState>builder();

            for (var entry : pos2BlockState) {
                var state = entry.getValue();
                var posOffset = entry.getKey().offset(pos);
                builder.put(posOffset, level.getBlockState(posOffset));

                level.setBlockAndUpdate(posOffset, state);
            }

            undoMap.put(player, builder.build());
            player.getCooldowns().addCooldown(this, 25);
            player.displayClientMessage(Component.literal(String.format("Cloned structure anchored at (%d %d %d)", pos.getX(), pos.getY(), pos.getZ())), true);
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/item/DistanceWandItem.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.item;

import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class DistanceWandItem extends Item {
    public DistanceWandItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        var level = ctx.getLevel();

        if (!level.isClientSide) {
            var stack = ctx.getItemInHand();
            var pos = ctx.getClickedPos();
            var player = ctx.getPlayer();

            if (player == null) return InteractionResult.PASS;

            var startPosNbt = stack.getTagElement("StartPos");
            if (startPosNbt != null) {
                var start = NbtUtils.readBlockPos(startPosNbt);

                var dx = pos.getX() == start.getX() ? 0 : Math.abs(pos.getX() - start.getX()) + 1;
                var dy = pos.getY() == start.getY() ? 0 : Math.abs(pos.getY() - start.getY()) + 1;
                var dz = pos.getZ() == start.getZ() ? 0 : Math.abs(pos.getZ() - start.getZ()) + 1;

                player.displayClientMessage(Component.literal(String.format("Distance (XYZ): (%d, %d, %d)", dx, dy, dz)), false);
                stack.removeTagKey("StartPos");
            } else {
                stack.addTagElement("StartPos", NbtUtils.writeBlockPos(pos));
                player.displayClientMessage(Component.literal(String.format("Measurement starting position: (%d %d %d)", pos.getX(), pos.getY(), pos.getZ())), true);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
```

### src/main/java/thedarkcolour/modkit/item/FillWandItem.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FillWandItem extends AbstractFillWand {
    public FillWandItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MutableComponent getFillMessage() {
        return Component.literal("Filled blocks from ");
    }

    @Override
    protected void handleUse(Level level, ItemStack stack, BlockPos pos, Player player) {
        if (player.isShiftKeyDown()) {
            var state = level.getBlockState(pos);
            stack.addTagElement("FillBlock", NbtUtils.writeBlockState(state));
            player.displayClientMessage(Component.literal("Set block to " + state.getBlock()), true);
        } else {
            if (stack.getTagElement("StartPos") == null) {
                saveStartPos(stack, pos, player);
            } else {
                var savedFillBlock = stack.getTagElement("FillBlock");
                if (savedFillBlock == null) {
                    player.displayClientMessage(Component.literal("No filler block (use sneak click on a block)"), true);
                } else {
                    fill(stack, NbtUtils.readBlockState(level.holderLookup(Registries.BLOCK), savedFillBlock), pos, level, player);
                    player.getCooldowns().addCooldown(this, 5);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advanced) {
        super.appendHoverText(stack, level, tooltip, advanced);

        if (level != null) {
            var fillBlockNbt = stack.getTagElement("FillBlock");
            if (fillBlockNbt != null) {
                tooltip.add(Component.literal("Filler Block: ").append(Component.translatable(NbtUtils.readBlockState(level.holderLookup(Registries.BLOCK), fillBlockNbt).getBlock().getDescriptionId()).withStyle(ChatFormatting.YELLOW)));
            }
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/item/KillWand.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class KillWand extends Item {
    public KillWand(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.skipDropExperience();
        target.kill();
        target.setHealth(0);

        // no children
        if (target instanceof Slime) {
            ((Slime) target).setSize(0, false);
        }
        return true;
    }
}
```

### src/main/java/thedarkcolour/modkit/item/package-info.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

@javax.annotation.ParametersAreNonnullByDefault
@net.minecraft.MethodsReturnNonnullByDefault
package thedarkcolour.modkit.item;
```

### src/main/java/thedarkcolour/modkit/MKUtils.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.language.IModInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MKUtils {
    // only works for vanilla registries
    public static <T> void forModRegistry(ResourceKey<? extends Registry<T>> registryKey, String modid, BiConsumer<ResourceLocation, T> consumer) {
        forModRegistry(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY).registryOrThrow(registryKey), modid, consumer);
    }

    public static <T> void forModRegistry(Registry<T> registry, String modid, BiConsumer<ResourceLocation, T> consumer) {
        // Iterate in a deterministic order sorted by id. MappedRegistry#entrySet is backed by a HashMap
        // keyed on ResourceKey, whose identity-based hashCode makes iteration order vary between JVM runs.
        // Sorting keeps generated data (e.g. auto-generated lang names) stable across datagen runs.
        List<Map.Entry<ResourceKey<T>, T>> entries = new ArrayList<>();
        for (var entry : registry.entrySet()) {
            if (entry.getKey().location().getNamespace().equals(modid)) {
                entries.add(entry);
            }
        }
        entries.sort(Comparator.comparing(entry -> entry.getKey().location()));
        for (var entry : entries) {
            consumer.accept(entry.getKey().location(), entry.getValue());
        }
    }

    public static void forInDevMods(Consumer<IModInfo> action) {
        for (IModFileInfo modsToml : ModList.get().getModFiles()) {
            for (IModInfo modInfo : modsToml.getMods()) {
                if (!modsToml.getFile().getFilePath().toAbsolutePath().toString().contains(".jar")) {
                    action.accept(modInfo);
                }
            }
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/ModKit.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit;

import java.util.Set;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thedarkcolour.modkit.block.InfinitePowerBlock;
import thedarkcolour.modkit.blockentity.InfinitePowerBlockEntity;
import thedarkcolour.modkit.item.ClearWandItem;
import thedarkcolour.modkit.item.CloneWandItem;
import thedarkcolour.modkit.item.DistanceWandItem;
import thedarkcolour.modkit.item.FillWandItem;
import thedarkcolour.modkit.item.KillWand;

@Mod(ModKit.ID)
public class ModKit {
    public static final String ID = "modkit";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ID);
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ID);

    public static final RegistryObject<Block> INFINITE_POWER = BLOCKS.register("infinite_power", InfinitePowerBlock::new);
    public static final RegistryObject<BlockEntityType<?>> INFINITE_POWER_TYPE = BLOCK_ENTITIES.register("infinite_power", () -> new BlockEntityType<>(InfinitePowerBlockEntity::new, Set.of(INFINITE_POWER.get()), null));
    public static final RegistryObject<BlockItem> INFINITE_POWER_ITEM = ITEMS.register("infinite_power", () -> new BlockItem(INFINITE_POWER.get(), new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> FILL_WAND = ITEMS.register("fill_wand", () -> new FillWandItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CLEAR_WAND = ITEMS.register("clear_wand", () -> new ClearWandItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> DISTANCE_WAND = ITEMS.register("distance_wand", () -> new DistanceWandItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CLONE_WAND = ITEMS.register("clone_wand", () -> new CloneWandItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> KILL_WAND = ITEMS.register("kill_wand", () -> new KillWand(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    static {
        CREATIVE_TABS.register(ID, () -> Util.make(new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 0), builder -> {
            builder.icon(() -> new ItemStack(CLONE_WAND.get()));
            builder.title(Component.translatable("itemGroup.modkit"));
            builder.displayItems((params, output) -> {
                output.accept(INFINITE_POWER_ITEM.get());
                output.accept(FILL_WAND.get());
                output.accept(CLEAR_WAND.get());
                output.accept(DISTANCE_WAND.get());
                output.accept(CLONE_WAND.get());
                output.accept(KILL_WAND.get());
            });
            builder.withTabsBefore(CreativeModeTabs.SPAWN_EGGS);
        }).build());
    }

    public ModKit() {
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modBus);
        BLOCK_ENTITIES.register(modBus);
        ITEMS.register(modBus);
        CREATIVE_TABS.register(modBus);
        modBus.addListener(ModKit::postRegistry);
        modBus.addListener(ModKitDataGen::gatherData);
        modBus.addListener(EventPriority.LOWEST, ModKit::postCreativeTabs);
    }

    private static void postRegistry(FMLLoadCompleteEvent event) {
        MKUtils.forInDevMods(modInfo -> {
            MKUtils.forModRegistry(Registries.BLOCK, modInfo.getModId(), (id, block) -> {
                if (Item.BY_BLOCK.get(block) == null) {
                    ModKit.LOGGER.warn("Block '{}' has no block item", id);
                }
            });
            // Maybe something about entities without spawn eggs next?
        });
    }

    /**
     * Triggers upon first opening the Creative Menu. Warns about registered items which do not
     * show in any creative tab, which means they will not show in JEI.
     */
    private static void postCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        var allTabs = CreativeModeTabs.allTabs();

        // only print errors on the last tab
        if (allTabs.indexOf(event.getTab()) + 1 == allTabs.size()) {
            MKUtils.forInDevMods(modInfo -> MKUtils.forModRegistry(Registries.ITEM, modInfo.getModId(), (id, item) -> {
                for (var tab : allTabs) {
                    for (var entry : tab.getDisplayItems()) {
                        if (entry.getItem() == item) {
                            return;
                        }
                    }
                }

                ModKit.LOGGER.warn("Item '{}' is not found in any creative tabs (will not show in JEI!)", id);
            }));
        }
    }
}
```

### src/main/java/thedarkcolour/modkit/ModKitDataGen.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.modkit;

import net.minecraftforge.data.event.GatherDataEvent;
import thedarkcolour.modkit.data.DataHelper;
import thedarkcolour.modkit.data.MKBlockModelProvider;
import thedarkcolour.modkit.data.MKEnglishProvider;

/**
 * For a more detailed example of data generation, go to the "test" source set of ModKit on
 * <a href="https://github.com/thedarkcolour/ModKit/tree/1.20.1/src/test/java/thedarkcolour/testmod">GitHub</a>
 */
final class ModKitDataGen {
    static void gatherData(GatherDataEvent event) {
        // Instead of manually adding data providers to the event, use the IDataHelper class
        var dataHelper = new DataHelper(ModKit.ID, event);
        dataHelper.createEnglish(true, ModKitDataGen::addNames);
        dataHelper.createItemModels(true, true, false, null);
        dataHelper.createBlockModels(ModKitDataGen::addBlockModels);
    }

    // Although english generation gives appropriate names for most things, some are still done by hand
    private static void addNames(MKEnglishProvider english) {
        english.add("itemGroup.modkit", "ModKit");
    }

    private static void addBlockModels(MKBlockModelProvider models) {
        models.simpleBlock(ModKit.INFINITE_POWER.get());
    }
}
```

### src/main/java/thedarkcolour/modkit/package-info.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

@net.minecraft.MethodsReturnNonnullByDefault
@net.minecraft.FieldsAreNonnullByDefault
@javax.annotation.ParametersAreNonnullByDefault
package thedarkcolour.modkit;
```

### src/main/resources/META-INF/mods.toml

```toml
modLoader="javafml"
loaderVersion="[45,)"
license='MIT License'

[[mods]]
modId="modkit"
version="${file.jarVersion}"
displayName="ModKit"
credits=""
authors="TheDarkColour"
description='''
Make mods without all the boilerplate
'''
[[dependencies.modkit]]
modId="forge"
mandatory=true
versionRange="[45,)"
ordering="NONE"
side="BOTH"
[[dependencies.modkit]]
modId="minecraft"
mandatory=true
versionRange="[1.20.1]"
ordering="NONE"
side="BOTH"
```

### src/main/resources/pack.mcmeta

```
{
  "pack": {
    "description": {
      "text": "ModKit resources"
    },
    "forge:server_data_pack_format": 12,
    "pack_format": 13
  }
}
```

### src/test/java/thedarkcolour/testmod/data/BlockModels.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.testmod.data;

import thedarkcolour.modkit.data.MKBlockModelProvider;
import thedarkcolour.testmod.TestMod;

// Package private so that multiple mods can use these names without having a ton of autocomplete options
class BlockModels {
    static void addBlockModels(MKBlockModelProvider models) {
        models.simpleBlock(TestMod.ORANGE_BLOCK.get());
        models.simpleBlock(TestMod.RED_BLOCK.get());
    }
}
```

### src/test/java/thedarkcolour/testmod/data/DamageTypes.java

```java
package thedarkcolour.testmod.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DeathMessageType;
import thedarkcolour.modkit.data.MKDamageTypeProvider;
import thedarkcolour.testmod.TestMod;

public class DamageTypes {
    public static final ResourceKey<DamageType> TEST_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(TestMod.ID, "test_damage"));

    public static void addTypes(MKDamageTypeProvider types) {
        types.add(TEST_DAMAGE)
                .exhaustion(0.5f)
                .effects(DamageEffects.FREEZING)
                .deathMessageType(DeathMessageType.INTENTIONAL_GAME_DESIGN)
                .scaling(DamageScaling.NEVER);
    }
}
```

### src/test/java/thedarkcolour/testmod/data/DataGen.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.testmod.data;

import net.minecraft.core.registries.Registries;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.data.loading.DatagenModLoader;
import thedarkcolour.modkit.data.DataHelper;
import thedarkcolour.testmod.TestMod;

/**
 * GatherDataEvent is fired on the MOD BUS!!!
 * Do NOT use {@code @Mod.EventBusSubscriber} to register to the mod bus. The annotation class loads
 * this class regardless of if ModKit is present, which can crash a player outside of dev.
 */
public final class DataGen {
    public static void gatherData(GatherDataEvent event) {
        DataHelper helper = new DataHelper(TestMod.ID, event);

        helper.createEnglish(true, English::addTranslations);
        helper.createBlockModels(BlockModels::addBlockModels);
        helper.createItemModels(true, true, false, ItemModels::addItemModels);
        helper.createRecipes(Recipes::addRecipes);
        helper.createTags(Registries.BLOCK, ModTags::addBlockTags);
        helper.createTags(Registries.ITEM, ModTags::addItemTags);
        helper.createDamageTypes(DamageTypes::addTypes);
    }

    // Do not load this class outside of data gen
    static {
        if (!DatagenModLoader.isRunningDataGen()) {
            throw new RuntimeException("Class loaded!");
        }
    }
}
```

### src/test/java/thedarkcolour/testmod/data/English.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.testmod.data;

import net.minecraft.core.registries.Registries;
import thedarkcolour.modkit.data.MKEnglishProvider;

class English {
    static void addTranslations(MKEnglishProvider lang) {
        // Since MobEffect.getDescriptionId is handled by default in ModKit,
        // there is no need to call addTranslationHandler. If you are using
        // a modded registry, (ex. "Bee Species") you must add one yourself.
        lang.addRegistryForAutoTranslation(Registries.MOB_EFFECT);
    }
}
```

### src/test/java/thedarkcolour/testmod/data/ItemModels.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.testmod.data;

import thedarkcolour.modkit.data.MKItemModelProvider;
import thedarkcolour.testmod.TestMod;

// Package private so that multiple mods can use these names without having a ton of autocomplete options
class ItemModels {
    static void addItemModels(MKItemModelProvider models) {
        models.exclude(TestMod.ORANGE);
    }
}
```

### src/test/java/thedarkcolour/testmod/data/ModTags.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.testmod.data;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import thedarkcolour.modkit.data.MKTagsProvider;
import thedarkcolour.testmod.TestMod;

public class ModTags {
    public static void addBlockTags(MKTagsProvider<Block> tags) {
        tags.tag(BlockTags.IMPERMEABLE).add(TestMod.ORANGE_BLOCK);
        tags.tag(BlockTags.WARPED_STEMS).add(TestMod.RED_BLOCK);
        // should print a warning and do nothing
        tags.copy(BlockTags.WARPED_STEMS, ItemTags.WARPED_STEMS);
    }

    public static void addItemTags(MKTagsProvider<Item> tags) {
        tags.copy(BlockTags.WARPED_STEMS, ItemTags.WARPED_STEMS);
        tags.tag(ItemTags.FOX_FOOD).add(TestMod.ORANGE);
    }
}
```

### src/test/java/thedarkcolour/testmod/data/Recipes.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.testmod.data;

import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.ItemExistsCondition;
import thedarkcolour.modkit.data.MKRecipeProvider;
import thedarkcolour.testmod.TestMod;

import java.util.List;
import java.util.function.Consumer;

// Package private so that multiple mods can use these names without having a ton of autocomplete options
class Recipes {
    static void addRecipes(Consumer<FinishedRecipe> writer, MKRecipeProvider recipes) {
        recipes.storage3x3(TestMod.ORANGE_BLOCK.get(), TestMod.ORANGE.get());

        recipes.conditional("apples_if_true", List.of(new ItemExistsCondition("minecraft", "bundle")), appender -> {
            recipes.grid2x2(Items.APPLE, MKRecipeProvider.ingredient(Items.DIRT));
        });

        // a recipe with 8 cobblestone and 1 black dye to give blackstone
        recipes.shapelessCrafting(RecipeCategory.BUILDING_BLOCKS, Items.BLACKSTONE, 8, ObjectIntPair.of(Tags.Items.COBBLESTONE, 8), Items.BLACK_DYE);

        recipes.woodenDoor(Items.IRON_DOOR, Items.IRON_BLOCK);
        recipes.slab(Items.CACTUS, Items.RED_CANDLE);
        recipes.stairs(Items.ICE, Items.BLACK_BED);
    }
}
```

### src/test/java/thedarkcolour/testmod/TestMod.java

```java
/*
 * MIT License
 *
 * Copyright (c) 2023 thedarkcolour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package thedarkcolour.testmod;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import thedarkcolour.testmod.data.DataGen;

@Mod(TestMod.ID)
public class TestMod {
    public static final String ID = "testmod";

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.ID);
    private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TestMod.ID);

    public static final RegistryObject<Block> RED_BLOCK = BLOCKS.register("red_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0f).sound(SoundType.HONEY_BLOCK))) ;
    public static final RegistryObject<Item> RED_BLOCK_ITEM = ITEMS.register("red_block", () -> new BlockItem(RED_BLOCK.get(), new Item.Properties().stacksTo(3)));

    public static final RegistryObject<Item> ORANGE = ITEMS.register("orange", () -> new Item(new Item.Properties().stacksTo(6)));
    public static final RegistryObject<Block> ORANGE_BLOCK = BLOCKS.register("orange_block", () -> new Block(BlockBehaviour.Properties.of().strength(12.0f).sound(SoundType.HONEY_BLOCK))) ;
    public static final RegistryObject<Item> ORANGE_BLOCK_ITEM = ITEMS.register("orange_block", () -> new BlockItem(ORANGE_BLOCK.get(), new Item.Properties().stacksTo(10)));

    public static final RegistryObject<MobEffect> BRUISE = MOB_EFFECTS.register("bruise", () -> new MobEffect(MobEffectCategory.HARMFUL, 1) {});

    public TestMod() {
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        MOB_EFFECTS.register(modBus);

        modBus.addListener(DataGen::gatherData);
    }
}
```

### src/test/resources/META-INF/mods.toml

```toml
modLoader="javafml"
loaderVersion="[45,)"
license='MIT License'

[[mods]]
modId="testmod"
version="${file.jarVersion}"
displayName="TestMod"
credits=""
authors="TheDarkColour"
description='''
Blah
'''
# No reason to bother with dependencies if I'm in test environment
```

### src/test/resources/pack.mcmeta

```
{
  "pack": {
    "description": {
      "text": "testmod resources"
    },
    "forge:server_data_pack_format": 12,
    "pack_format": 13
  }
}
```

## Skipped files

- `gradle/wrapper/gradle-wrapper.jar` — binary file
- `src/main/resources/assets/modkit/textures/block/infinite_power.png` — binary file
- `src/main/resources/assets/modkit/textures/item/clear_wand.png` — binary file
- `src/main/resources/assets/modkit/textures/item/clone_wand.png` — binary file
- `src/main/resources/assets/modkit/textures/item/distance_wand.png` — binary file
- `src/main/resources/assets/modkit/textures/item/fill_wand.png` — binary file
- `src/main/resources/assets/modkit/textures/item/kill_wand.png` — binary file
- `src/test/resources/assets/testmod/textures/block/orange_block.png` — binary file
- `src/test/resources/assets/testmod/textures/item/orange.png` — binary file
